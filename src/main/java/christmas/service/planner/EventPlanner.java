package christmas.service.planner;

import christmas.domain.Date;
import christmas.domain.DiscountResult;
import christmas.domain.DiscountResults;
import christmas.domain.EventBadge;
import christmas.domain.Gift;
import christmas.domain.Orders;
import christmas.domain.event.ChristmasDDayDiscountEvent;
import christmas.domain.event.DiscountableEvent;
import christmas.domain.event.GiftEvent;
import christmas.domain.event.SpecialDiscountEvent;
import christmas.domain.event.WeekdayDiscountEvent;
import christmas.domain.event.WeekendDiscountEvent;
import java.util.ArrayList;
import java.util.List;

public class EventPlanner implements Planner {
    private final Date date;
    private final Orders orders;
    private GiftEvent giftEvent;
    private List<DiscountableEvent> discountableEvents;

    public EventPlanner(Date date, Orders orders) {
        init(date, orders);
        this.date = date;
        this.orders = orders;
    }

    private void init(Date date, Orders orders) {
        giftEvent = new GiftEvent(orders);
        discountableEvents = new ArrayList<>() {{
            add(new ChristmasDDayDiscountEvent(date));
            add(new WeekdayDiscountEvent(date, orders));
            add(new WeekendDiscountEvent(date, orders));
            add(new SpecialDiscountEvent(date));
            add(new GiftEvent(orders));
        }};
    }

    @Override
    public int calculateTotalPriceBeforeDiscount() {
        return orders.calculateTotalPrice();
    }

    @Override
    public Gift requestGift() {
        return giftEvent.requestGift();

    }

    @Override
    public DiscountResults calculateDiscountResults() {
        List<DiscountResult> discountResults = discountableEvents.stream()
                .map(DiscountableEvent::calculateDiscountResult)
                .toList();

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
    public int calculateExpectedPriceAfterDiscount() {
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