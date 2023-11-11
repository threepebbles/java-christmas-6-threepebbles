package christmas.model.domain.discount;

import christmas.model.domain.DiscountType;
import christmas.model.domain.Gift;
import christmas.model.domain.Order;

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
