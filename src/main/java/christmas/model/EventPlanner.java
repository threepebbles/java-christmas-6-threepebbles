package christmas.model;

import christmas.constant.EventBadge;
import christmas.model.discount.GiftDiscount;

public class EventPlanner {
    private final Date date;
    private final Order order;

    public EventPlanner(Date date, Order order) {
        this.date = date;
        this.order = order;
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