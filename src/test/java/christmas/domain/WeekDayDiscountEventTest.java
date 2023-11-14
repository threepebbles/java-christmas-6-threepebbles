package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.constant.Menu;
import christmas.domain.event.WeekdayDiscountEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WeekDayDiscountEventTest {
    @DisplayName("주말(금요일, 토요일)을 제외한 평일 할인 가격 계산 테스트")
    @Test
    void 평일_할인가_계산_테스트() {
        // given
        Date date = new Date(LocalDate.of(2023, 12, 24));   // 일요일
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
}
