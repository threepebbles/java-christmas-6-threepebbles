package christmas.service.planner;

import christmas.constant.EventBadge;
import christmas.constant.Gift;
import christmas.model.Date;
import christmas.model.DiscountDetails;
import christmas.model.DiscountResult;
import christmas.model.Orders;
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
    public DiscountDetails calculateDiscountDetails() {
        List<DiscountResult> discountResults = new ArrayList<>() {{
            add(christmasDDayDiscountEvent.calculateDiscountResult(date));
            add(weekdayDiscountEvent.calculateDiscountResult(date, orders));
            add(weekendDiscountEvent.calculateDiscountResult(date, orders));
            add(specialDiscountEvent.calculateDiscountResult(date));
            add(giftEvent.calculateAmount(orders));
        }};
        return new DiscountDetails(discountResults.stream()
                .filter(discountResult -> discountResult.getAmount() != 0)
                .toList());
    }

    @Override
    public int calculateTotalDiscount() {
        DiscountDetails discountDetails = calculateDiscountDetails();
        return Math.min(discountDetails.calculateTotalDiscount(), orders.calculateTotalPrice());
    }

    @Override
    public int calculateExpectedPayAfterDiscount() {
        int totalPriceBeforeDiscount = calculateTotalPriceBeforeDiscount();
        DiscountDetails discountDetails = calculateDiscountDetails();
        return Math.max(totalPriceBeforeDiscount - discountDetails.calculateTotalDiscountWithoutGift(), 0);
    }

    @Override
    public EventBadge requestEventBadge() {
        int totalDiscount = calculateTotalDiscount();
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