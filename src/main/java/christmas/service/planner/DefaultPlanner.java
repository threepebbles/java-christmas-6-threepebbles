package christmas.service.planner;

import christmas.domain.Date;
import christmas.domain.DiscountResults;
import christmas.domain.Orders;
import christmas.domain.constant.EventBadge;
import christmas.domain.constant.Gift;

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
    public DiscountResults calculateDiscountResults() {
        return DiscountResults.createEmptyDiscountResults();
    }

    @Override
    public int calculateTotalDiscount() {
        return 0;
    }

    @Override
    public int calculateExpectedAmountAfterDiscount() {
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