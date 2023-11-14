package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.event.ChristmasDDayDiscountEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ChristmasDDayDiscountEventTest {
    @DisplayName("크리스마스 디데이 이벤트 할인가 계산 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1:1000", "2:1100", "20:2900", "25:3400", "26:0", "31:0"}, delimiter = ':')
    void 크리스마스_디데이_이벤트_할인가_계산_테스트(int day, int expectedAmount) {
        // given
        Date date = new Date(LocalDate.of(2023, 12, day));
        List<Order> orderList = new ArrayList<>() {{
            add(new Order(Menu.ICE_CREAM, 1));
            add(new Order(Menu.BARBECUE_LIP, 2));
            add(new Order(Menu.CHOCOLATE_CAKE, 3));
            add(new Order(Menu.ZERO_COKE, 3));
        }};
        Orders orders = new Orders(orderList);
        ChristmasDDayDiscountEvent christmasDDayDiscountEvent = new ChristmasDDayDiscountEvent(date);

        // when
        int actual = christmasDDayDiscountEvent.calculateDiscountResult().getAmount();

        // then
        assertThat(actual).isEqualTo(expectedAmount);
    }
}