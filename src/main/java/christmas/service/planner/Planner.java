package christmas.service.planner;

import christmas.domain.Date;
import christmas.domain.DiscountResults;
import christmas.domain.Orders;
import christmas.domain.constant.EventBadge;
import christmas.domain.constant.Gift;
import christmas.domain.event.ChristmasDDayDiscountEvent;
import christmas.domain.event.GiftEvent;
import christmas.domain.event.SpecialDiscountEvent;
import christmas.domain.event.WeekdayDiscountEvent;
import christmas.domain.event.WeekendDiscountEvent;

public interface Planner {
    ChristmasDDayDiscountEvent christmasDDayDiscountEvent = new ChristmasDDayDiscountEvent();
    WeekdayDiscountEvent weekdayDiscountEvent = new WeekdayDiscountEvent();
    WeekendDiscountEvent weekendDiscountEvent = new WeekendDiscountEvent();
    SpecialDiscountEvent specialDiscountEvent = new SpecialDiscountEvent();
    GiftEvent giftEvent = new GiftEvent();

    int calculateTotalPriceBeforeDiscount();

    Gift requestGift();

    DiscountResults calculateDiscountResults();

    int calculateTotalDiscount();

    int calculateExpectedPriceAfterDiscount();

    EventBadge requestEventBadge();

    Date getDate();

    Orders getOrders();
}