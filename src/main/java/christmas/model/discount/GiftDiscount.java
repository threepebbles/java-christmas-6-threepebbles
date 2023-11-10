package christmas.model.discount;

import christmas.model.Gift;
import christmas.model.Menu;
import christmas.model.Order;

public class GiftDiscount extends Discount {
    public GiftDiscount(Order order) {
        super(DiscountType.GIFT);
        this.amount = calculateAmount(order);
    }

    public int calculateAmount(Order order) {
        int amount = 0;
        if (order.calculateTotalPrice() >= Gift.THRESHOLD) {
            amount = Menu.CHAMPAGNE.getPrice();
        }
        return amount;
    }
}
