package christmas.model.discount;

import christmas.model.DiscountType;
import christmas.model.Gift;
import christmas.model.Order;

public class GiftDiscount extends Discount {
    public static final int THRESHOLD = 120000;

    public GiftDiscount(Order order) {
        super(DiscountType.GIFT);
        this.amount = calculateAmount(order);
    }

    public int calculateAmount(Order order) {
        int amount = 0;
        if (order.calculateTotalPrice() >= THRESHOLD) {
            amount = Gift.CHAMPAGNE.getPrice();
        }
        return amount;
    }
}
