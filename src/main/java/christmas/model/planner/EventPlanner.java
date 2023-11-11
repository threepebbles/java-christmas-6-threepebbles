package christmas.model.planner;

import christmas.constant.EventBadge;
import christmas.model.Date;
import christmas.model.DiscountDetails;
import christmas.model.Gift;
import christmas.model.Order;
import christmas.model.discount.GiftDiscount;

public class EventPlanner implements Planner {
    public static final int MINIMUM_REQUIRED_AMOUNT_TO_APPLY_EVENTS = 10000;
    private final Date date;
    private final Order order;

    public EventPlanner(Date date, Order order) {
        this.date = date;
        this.order = order;
    }

    public boolean isRequired(int totalPrice) {
        return totalPrice >= MINIMUM_REQUIRED_AMOUNT_TO_APPLY_EVENTS;
    }

    public int calculateTotalPriceBeforeDiscount() {
        return order.calculateTotalPrice();
    }

    public Gift calculateGift() {
        int totalPriceBeforeDiscount = order.calculateTotalPrice();
        if (totalPriceBeforeDiscount >= GiftDiscount.THRESHOLD) {
            return Gift.CHAMPAGNE;
        }
        return Gift.NOTHING;
    }

    public DiscountDetails calculateDiscountDetails() {
        return DiscountDetails.createDiscountDetails(date, order);
    }

    public int calculateTotalDiscount() {
        DiscountDetails discountDetails = DiscountDetails.createDiscountDetails(date, order);
        return discountDetails.calculateTotalDiscount();
    }

    public int calculateExpectedPayAfterDiscount() {
        int totalPriceBeforeDiscount = calculateTotalPriceBeforeDiscount();
        DiscountDetails discountDetails = DiscountDetails.createDiscountDetails(date, order);
        return totalPriceBeforeDiscount - discountDetails.calculateTotalDiscountWithoutGift();
    }

    public EventBadge calculateEventBadge() {
        DiscountDetails discountDetails = DiscountDetails.createDiscountDetails(date, order);
        int totalDiscount = discountDetails.calculateTotalDiscount();
        return EventBadge.valueOf(totalDiscount);
    }
}