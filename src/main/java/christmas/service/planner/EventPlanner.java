package christmas.service.planner;

import christmas.constant.EventBadge;
import christmas.constant.Gift;
import christmas.model.Date;
import christmas.model.DiscountDetails;
import christmas.model.DiscountResult;
import christmas.model.Orders;
import christmas.service.event.ChristmasDDayDiscountEvent;
import christmas.service.event.GiftEvent;
import christmas.service.event.SpecialDiscountEvent;
import christmas.service.event.WeekdayDiscountEvent;
import christmas.service.event.WeekendDiscountEvent;
import java.util.ArrayList;
import java.util.List;

public class EventPlanner implements Planner {
    public static final int MINIMUM_REQUIRED_AMOUNT_TO_APPLY_EVENTS = 10000;
    private final Date date;
    private final Orders orders;
    private final ChristmasDDayDiscountEvent christmasDDayDiscountEvent;
    private final WeekdayDiscountEvent weekdayDiscountEvent;
    private final WeekendDiscountEvent weekendDiscountEvent;
    private final SpecialDiscountEvent specialDiscountEvent;
    private final GiftEvent giftEvent;

    public EventPlanner(Date date, Orders orders) {
        this.date = date;
        this.orders = orders;
        christmasDDayDiscountEvent = new ChristmasDDayDiscountEvent();
        weekdayDiscountEvent = new WeekdayDiscountEvent();
        weekendDiscountEvent = new WeekendDiscountEvent();
        specialDiscountEvent = new SpecialDiscountEvent();
        giftEvent = new GiftEvent();
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
        if (giftEvent.isEnoughAmount(totalPriceBeforeDiscount)) {
            return giftEvent.getGift();
        }
        return Gift.NOTHING;
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
    public EventBadge calculateEventBadge() {
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