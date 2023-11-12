package christmas.service.event;

import christmas.constant.EventType;
import christmas.constant.Gift;
import christmas.domain.DiscountResult;
import christmas.domain.Orders;

public class GiftEvent {
    private final int MINIMUM_AMOUNT_TO_RECEIVE_GIFT = 120000;
    private final Gift gift = Gift.CHAMPAGNE;

    public DiscountResult calculateAmount(Orders orders) {
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
