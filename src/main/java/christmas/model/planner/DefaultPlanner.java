package christmas.model.planner;

import christmas.constant.EventBadge;
import christmas.model.DiscountDetails;
import christmas.model.Gift;
import christmas.model.Orders;

public class DefaultPlanner implements Planner {
    private final Orders orders;

    public DefaultPlanner(Orders orders) {
        this.orders = orders;
    }

    public int calculateTotalPriceBeforeDiscount() {
        return orders.calculateTotalPrice();
    }

    public Gift calculateGift() {
        return Gift.NOTHING;
    }

    public DiscountDetails calculateDiscountDetails() {
        return DiscountDetails.createEmptyDiscountDetails();
    }

    public int calculateTotalDiscount() {
        return DiscountDetails.createEmptyDiscountDetails()
                .calculateTotalDiscount();
    }

    public int calculateExpectedPayAfterDiscount() {
        return calculateTotalPriceBeforeDiscount();
    }

    public EventBadge calculateEventBadge() {
        return EventBadge.NOTHING;
    }
}