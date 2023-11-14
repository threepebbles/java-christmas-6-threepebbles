package christmas.domain.event;

import christmas.domain.DiscountResult;
import christmas.domain.Orders;
import christmas.domain.constant.EventType;
import christmas.domain.constant.Gift;

public class GiftEvent implements DiscountableEvent {
    private static final int MINIMUM_AMOUNT_TO_RECEIVE_GIFT = 120000;
    private static final Gift gift = Gift.CHAMPAGNE;
    private final Orders orders;

    public GiftEvent(Orders orders) {
        this.orders = orders;
    }

    @Override
    public DiscountResult calculateDiscountResult() {
        int amount = 0;
        if (isEnoughAmount(orders.calculateTotalPrice())) {
            amount = gift.getPrice();
        }
        return new DiscountResult(EventType.GIFT, amount);
    }

    public Gift requestGift(int totalPriceBeforeDiscount) {
        if (isEnoughAmount(totalPriceBeforeDiscount)) {
            return gift;
        }
        return Gift.NOTHING;
    }

    private boolean isEnoughAmount(int totalPrice) {
        return totalPrice >= MINIMUM_AMOUNT_TO_RECEIVE_GIFT;
    }
}
