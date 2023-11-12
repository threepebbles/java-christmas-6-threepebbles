package christmas.Service.planner;

import christmas.constant.EventBadge;
import christmas.constant.Gift;
import christmas.model.Date;
import christmas.model.DiscountDetails;
import christmas.model.Orders;
import christmas.model.discount.GiftDiscountEvent;

public class EventPlanner implements Planner {
    public static final int MINIMUM_REQUIRED_AMOUNT_TO_APPLY_EVENTS = 10000;
    private final Date date;
    private final Orders orders;

    public EventPlanner(Date date, Orders orders) {
        this.date = date;
        this.orders = orders;
    }

    public boolean isEnoughAmount(int totalPrice) {
        return totalPrice >= MINIMUM_REQUIRED_AMOUNT_TO_APPLY_EVENTS;
    }

    @Override
    public int calculateTotalPriceBeforeDiscount() {
        return orders.calculateTotalPrice();
    }

    @Override
    public Gift calculateGift() {
        int totalPriceBeforeDiscount = orders.calculateTotalPrice();
        if (totalPriceBeforeDiscount >= GiftDiscountEvent.MINIMUM_AMOUNT_TO_RECEIVE_GIFT) {
            return GiftDiscountEvent.gift;
        }
        return Gift.NOTHING;
    }

    @Override
    public DiscountDetails calculateDiscountDetails() {
        return DiscountDetails.createDiscountDetails(date, orders);
    }

    @Override
    public int calculateTotalDiscount() {
        DiscountDetails discountDetails = DiscountDetails.createDiscountDetails(date, orders);
        return Math.min(discountDetails.calculateTotalDiscount(), orders.calculateTotalPrice());
    }

    @Override
    public int calculateExpectedPayAfterDiscount() {
        int totalPriceBeforeDiscount = calculateTotalPriceBeforeDiscount();
        DiscountDetails discountDetails = DiscountDetails.createDiscountDetails(date, orders);
        return Math.max(totalPriceBeforeDiscount - discountDetails.calculateTotalDiscountWithoutGift(), 0);
    }

    @Override
    public EventBadge calculateEventBadge() {
        DiscountDetails discountDetails = DiscountDetails.createDiscountDetails(date, orders);
        int totalDiscount = discountDetails.calculateTotalDiscount();
        return EventBadge.valueOf(totalDiscount);
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public Orders getOrders() {
        return orders;
    }
}