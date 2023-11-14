package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.event.GiftEvent;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class GiftEventTest {
    @Test
    void 증정_메뉴_증정하는_경우_테스트() {
        // given
        List<Order> orderList = new ArrayList<>() {{
            add(new Order(Menu.BARBECUE_LIP, 2));   // 108000원
            add(new Order(Menu.ZERO_COKE, 4));      // 12000원
        }};
        Orders orders = new Orders(orderList);
        GiftEvent giftEvent = new GiftEvent(orders);

        // when
        Gift gift = giftEvent.requestGift();

        // then
        assertThat(gift).isEqualTo(Gift.CHAMPAGNE);
    }

    @Test
    void 증정_메뉴_증정하지_않는_경우_테스트() {
        // given
        List<Order> orderList = new ArrayList<>() {{
            add(new Order(Menu.BARBECUE_LIP, 2));   // 108000원
            add(new Order(Menu.ZERO_COKE, 3));      // 9000원
        }};
        Orders orders = new Orders(orderList);
        GiftEvent giftEvent = new GiftEvent(orders);

        // when
        Gift gift = giftEvent.requestGift();

        // then
        assertThat(gift).isEqualTo(Gift.NOTHING);
    }

    @Test
    void 증정_메뉴_가격_계산_테스트() {
        // given
        List<Order> orderList = new ArrayList<>() {{
            add(new Order(Menu.BARBECUE_LIP, 2));   // 108000원
            add(new Order(Menu.ZERO_COKE, 4));      // 12000원
        }};
        Orders orders = new Orders(orderList);
        GiftEvent giftEvent = new GiftEvent(orders);

        // when
        int actual = giftEvent.calculateDiscountResult()
                .getAmount();

        // then
        assertThat(actual).isEqualTo(Gift.CHAMPAGNE.getPrice());
    }
}