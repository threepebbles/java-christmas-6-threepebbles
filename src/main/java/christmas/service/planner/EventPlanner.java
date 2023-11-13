package christmas.service.planner;

import christmas.domain.Date;
import christmas.domain.DiscountResult;
import christmas.domain.DiscountResults;
import christmas.domain.Orders;
import christmas.domain.constant.EventBadge;
import christmas.domain.constant.Gift;
import java.util.ArrayList;
import java.util.List;

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
    public Gift requestGift() {
        return giftEvent.requestGift(orders.calculateTotalPrice());

    }

    @Override
    public DiscountResults calculateDiscountResults() {
        List<DiscountResult> discountResults = new ArrayList<>() {{
            add(christmasDDayDiscountEvent.calculateDiscountResult(date));
            add(weekdayDiscountEvent.calculateDiscountResult(date, orders));
            add(weekendDiscountEvent.calculateDiscountResult(date, orders));
            add(specialDiscountEvent.calculateDiscountResult(date));
            add(giftEvent.calculateAmount(orders));
        }};
        return new DiscountResults(discountResults.stream()
                .filter(discountResult -> discountResult.getAmount() != 0)
                .toList());
    }

    @Override
    public int calculateTotalDiscount() {
        DiscountResults discountResults = calculateDiscountResults();
        return Math.min(discountResults.calculateTotalDiscount(), orders.calculateTotalPrice());
    }

    @Override
    public int calculateExpectedAmountAfterDiscount() {
        int totalPriceBeforeDiscount = calculateTotalPriceBeforeDiscount();
        DiscountResults discountResults = calculateDiscountResults();
        return Math.max(totalPriceBeforeDiscount - discountResults.calculateTotalDiscountWithoutGift(), 0);
    }

    @Override
    public EventBadge requestEventBadge() {
        int totalDiscount = calculateTotalDiscount();
        return EventBadge.getEventBadgeByTotalDiscount(totalDiscount);
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