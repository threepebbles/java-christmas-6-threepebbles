package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.event.WeekdayDiscountEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class WeekDayDiscountEventTest {
    @DisplayName("주말(금요일, 토요일)을 제외한 평일 할인 가격 계산 테스트")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 10, 11, 17, 18, 24, 31})
    void 평일_할인가_계산_테스트(int day) {
        // given
        Date date = new Date(LocalDate.of(2023, 12, day));
        List<Order> orderList = new ArrayList<>() {{
            add(new Order(Menu.ICE_CREAM, 1));   // 디저트 메뉴 1
            add(new Order(Menu.BARBECUE_LIP, 2));
            add(new Order(Menu.CHOCOLATE_CAKE, 3));   // 디저트 메뉴 3개
            add(new Order(Menu.ZERO_COKE, 3));
        }};
        Orders orders = new Orders(orderList);
        WeekdayDiscountEvent weekdayDiscountEvent = new WeekdayDiscountEvent(date, orders);
        int expected = 2023 * 4;

        // when
        int actual = weekdayDiscountEvent.calculateDiscountResult().getAmount();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("평일 할인이 적용 되지 않는 날 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 8, 15, 22, 29})
    void 평일_아닌_날_할인가_계산_테스트(int day) {
        // given
        Date date = new Date(LocalDate.of(2023, 12, day));   // 일요일
        List<Order> orderList = new ArrayList<>() {{
            add(new Order(Menu.ICE_CREAM, 1));   // 디저트 메뉴 1
            add(new Order(Menu.BARBECUE_LIP, 2));
            add(new Order(Menu.CHOCOLATE_CAKE, 3));   // 디저트 메뉴 3개
            add(new Order(Menu.ZERO_COKE, 3));
        }};
        Orders orders = new Orders(orderList);
        WeekdayDiscountEvent weekdayDiscountEvent = new WeekdayDiscountEvent(date, orders);
        int expected = 0;

        // when
        int actual = weekdayDiscountEvent.calculateDiscountResult().getAmount();

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
