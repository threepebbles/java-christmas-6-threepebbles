package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.event.WeekendDiscountEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class WeekendDiscountEventTest {
    @DisplayName("주말(금요일, 토요일) 할인 가격 계산 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    void 주말_할인가_계산_테스트() {
        // given
        Date date = new Date(LocalDate.of(2023, 12, 16));
        List<Order> orderList = new ArrayList<>() {{
            add(new Order(Menu.BARBECUE_LIP, 2));   // 메인 메뉴 2개
            add(new Order(Menu.CHRISTMAS_PASTA, 3));   // 메인 메뉴 3개
            add(new Order(Menu.ZERO_COKE, 4));      // 12000원
        }};
        Orders orders = new Orders(orderList);
        WeekendDiscountEvent weekendDiscountEvent = new WeekendDiscountEvent(date, orders);
        int expected = 2023 * 5;

        // when
        int actual = weekendDiscountEvent.calculateDiscountResult().getAmount();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("주말(금요일, 토요일)이 아닌 날 할인 가격 계산 테스트")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 10, 11, 17, 18, 24, 31})
    void 주말_아닌_날_할인가_계산_테스트(int day) {
        // given
        Date date = new Date(LocalDate.of(2023, 12, day));
        List<Order> orderList = new ArrayList<>() {{
            add(new Order(Menu.BARBECUE_LIP, 2));   // 메인 메뉴 2개
            add(new Order(Menu.CHRISTMAS_PASTA, 3));   // 메인 메뉴 3개
            add(new Order(Menu.ZERO_COKE, 4));      // 12000원
        }};
        Orders orders = new Orders(orderList);
        WeekendDiscountEvent weekendDiscountEvent = new WeekendDiscountEvent(date, orders);
        int expected = 0;

        // when
        int actual = weekendDiscountEvent.calculateDiscountResult().getAmount();

        // then
        assertThat(actual).isEqualTo(expected);
    }
}