package christmas.model.planner;

import christmas.constant.EventBadge;
import christmas.constant.Gift;
import christmas.model.Date;
import christmas.model.DiscountDetails;
import christmas.model.Orders;
import christmas.model.discount.GiftDiscount;

public class EventPlanner implements Planner {
    public static final int MINIMUM_REQUIRED_AMOUNT_TO_APPLY_EVENTS = 10000;
    private final Date date;
    private final Orders orders;

    public EventPlanner(Date date, Orders orders) {
        this.date = date;
        this.orders = orders;
    }

    public boolean isRequired(int totalPrice) {
        return totalPrice >= MINIMUM_REQUIRED_AMOUNT_TO_APPLY_EVENTS;
    }

    public int calculateTotalPriceBeforeDiscount() {
        return orders.calculateTotalPrice();
    }

    public Gift calculateGift() {
        int totalPriceBeforeDiscount = orders.calculateTotalPrice();
        if (totalPriceBeforeDiscount >= GiftDiscount.THRESHOLD) {
            return Gift.CHAMPAGNE;
        }
        return Gift.NOTHING;
    }

    public DiscountDetails calculateDiscountDetails() {
        return DiscountDetails.createDiscountDetails(date, orders);
    }

    public int calculateTotalDiscount() {
        DiscountDetails discountDetails = DiscountDetails.createDiscountDetails(date, orders);
        return discountDetails.calculateTotalDiscount();
    }

    public int calculateExpectedPayAfterDiscount() {
        int totalPriceBeforeDiscount = calculateTotalPriceBeforeDiscount();
        DiscountDetails discountDetails = DiscountDetails.createDiscountDetails(date, orders);
        return totalPriceBeforeDiscount - discountDetails.calculateTotalDiscountWithoutGift();
    }

    public EventBadge calculateEventBadge() {
        DiscountDetails discountDetails = DiscountDetails.createDiscountDetails(date, orders);
        int totalDiscount = discountDetails.calculateTotalDiscount();
        return EventBadge.valueOf(totalDiscount);
    }
}