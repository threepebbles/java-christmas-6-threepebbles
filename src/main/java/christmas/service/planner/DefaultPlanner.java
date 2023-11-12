package christmas.service.planner;

import christmas.constant.EventBadge;
import christmas.constant.Gift;
import christmas.model.Date;
import christmas.model.DiscountDetails;
import christmas.model.Orders;

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
    public Gift calculateGift() {
        return Gift.NOTHING;
    }

    @Override
    public DiscountDetails calculateDiscountDetails() {
        return DiscountDetails.createEmptyDiscountDetails();
    }

    @Override
    public int calculateTotalDiscount() {
        return DiscountDetails.createEmptyDiscountDetails()
                .calculateTotalDiscount();
    }

    @Override
    public int calculateExpectedPayAfterDiscount() {
        return calculateTotalPriceBeforeDiscount();
    }

    @Override
    public EventBadge calculateEventBadge() {
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