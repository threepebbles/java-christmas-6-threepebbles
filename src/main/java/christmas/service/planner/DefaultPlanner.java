package christmas.service.planner;

import christmas.constant.EventBadge;
import christmas.constant.Gift;
import christmas.domain.Date;
import christmas.domain.DiscountDetails;
import christmas.domain.Orders;

public class DefaultPlanner implements Planner {
    private final Date date;
    private final Orders orders;

    public DefaultPlanner(Date date, Orders orders) {
        this.date = date;
        this.orders = orders;
    }

    @Override
    public int calculateTotalPriceBeforeDiscount() {
        return orders.calculateTotalPrice();
    }

    @Override
    public Gift requestGift() {
        return giftEvent.requestGift(orders.calculateTotalPrice());
    }

    @Override
    public DiscountDetails calculateDiscountDetails() {
        return DiscountDetails.createEmptyDiscountDetails();
    }

    @Override
    public int calculateTotalDiscount() {
        return 0;
    }

    @Override
    public int calculateExpectedPayAfterDiscount() {
        return orders.calculateTotalPrice();
    }

    @Override
    public EventBadge requestEventBadge() {
        return EventBadge.NOTHING;
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