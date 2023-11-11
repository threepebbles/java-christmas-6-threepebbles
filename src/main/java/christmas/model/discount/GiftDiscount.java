package christmas.model.discount;

import christmas.constant.DiscountType;
import christmas.model.Gift;
import christmas.model.Orders;

public class GiftDiscount extends Discount {
    public static final int THRESHOLD = 120000;

    public GiftDiscount(Orders orders) {
        super(DiscountType.GIFT);
        this.amount = calculateAmount(orders);
    }

    public int calculateAmount(Orders orders) {
        int amount = 0;
        if (orders.calculateTotalPrice() >= THRESHOLD) {
            amount = Gift.CHAMPAGNE.getPrice();
        }
        return amount;
    }
}
