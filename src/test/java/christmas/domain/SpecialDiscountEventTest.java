package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.event.SpecialDiscountEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class SpecialDiscountEventTest {
    @DisplayName("특별 할인 가격 계산 테스트")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void 특별_할인가_계산_테스트(int day) {
        // given
        Date date = new Date(LocalDate.of(2023, 12, day));   // 달력에 별 있는 날
        List<Order> orderList = new ArrayList<>() {{
            add(new Order(Menu.ICE_CREAM, 1));
            add(new Order(Menu.BARBECUE_LIP, 2));
            add(new Order(Menu.CHOCOLATE_CAKE, 3));
            add(new Order(Menu.ZERO_COKE, 3));
        }};
        Orders orders = new Orders(orderList);
        SpecialDiscountEvent specialDiscountEvent = new SpecialDiscountEvent(date);
        int expected = 1000;

        // when
        int actual = specialDiscountEvent.calculateDiscountResult().getAmount();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("특별 할인 가격 계산 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 13, 26, 27, 28})
    void 특별_할인_없는_날_계산_테스트(int day) {
        // given
        Date date = new Date(LocalDate.of(2023, 12, day));   // 달력에 별 없는 날
        List<Order> orderList = new ArrayList<>() {{
            add(new Order(Menu.ICE_CREAM, 1));
            add(new Order(Menu.BARBECUE_LIP, 2));
            add(new Order(Menu.CHOCOLATE_CAKE, 3));
            add(new Order(Menu.ZERO_COKE, 3));
        }};
        Orders orders = new Orders(orderList);
        SpecialDiscountEvent specialDiscountEvent = new SpecialDiscountEvent(date);
        int expected = 0;

        // when
        int actual = specialDiscountEvent.calculateDiscountResult().getAmount();

        // then
        assertThat(actual).isEqualTo(expected);
    }
}