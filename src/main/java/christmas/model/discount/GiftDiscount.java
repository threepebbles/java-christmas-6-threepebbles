package christmas.model.discount;

import christmas.constant.DiscountType;
import christmas.constant.Gift;
import christmas.model.Orders;

public class GiftDiscount extends Discount {
    public static final int MINIMUM_AMOUNT_TO_RECEIVE_GIFT = 120000;
    public static final Gift gift = Gift.CHAMPAGNE;

    public GiftDiscount(Orders orders) {
        super(DiscountType.GIFT);
        this.amount = calculateAmount(orders);
    }

    private int calculateAmount(Orders orders) {
        int amount = 0;
        if (isEnoughAmount(orders.calculateTotalPrice())) {
            amount = gift.getPrice();
        }
        return amount;
    }

    public boolean isEnoughAmount(int totalPrice) {
        return totalPrice >= MINIMUM_AMOUNT_TO_RECEIVE_GIFT;
    }
}
