package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.constant.MenuType;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class OrdersTest {
    @Test
    void 총가격_계산_테스트() {
        // given
        List<Order> orderList = new ArrayList<>() {{
            add(new Order(Menu.BARBECUE_LIP, 2));   // 108000원
            add(new Order(Menu.ZERO_COKE, 3));      // 9000원
        }};
        Orders orders = new Orders(orderList);

        int expected = 117000;

        // when
        int actual = orders.calculateTotalPrice();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 특정_메뉴_종류_개수_계산_테스트() {
        // given
        List<Order> orderList = new ArrayList<>() {{
            add(new Order(Menu.BARBECUE_LIP, 2));   // 메인 2개
            add(new Order(Menu.ZERO_COKE, 3));
            add(new Order(Menu.CHRISTMAS_PASTA, 4));    // 메인 4개
            add(new Order(Menu.CHOCOLATE_CAKE, 3));
        }};
        Orders orders = new Orders(orderList);

        int expected = 6;

        // when
        int actual = orders.countByMenuType(MenuType.MAIN);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}