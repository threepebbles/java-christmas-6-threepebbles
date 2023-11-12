package christmas.service.planner;

import christmas.constant.EventBadge;
import christmas.constant.Gift;
import christmas.domain.Date;
import christmas.domain.DiscountDetails;
import christmas.domain.Orders;
import christmas.service.event.ChristmasDDayDiscountEvent;
import christmas.service.event.GiftEvent;
import christmas.service.event.SpecialDiscountEvent;
import christmas.service.event.WeekdayDiscountEvent;
import christmas.service.event.WeekendDiscountEvent;

public interface Planner {
    ChristmasDDayDiscountEvent christmasDDayDiscountEvent = new ChristmasDDayDiscountEvent();
    WeekdayDiscountEvent weekdayDiscountEvent = new WeekdayDiscountEvent();
    WeekendDiscountEvent weekendDiscountEvent = new WeekendDiscountEvent();
    SpecialDiscountEvent specialDiscountEvent = new SpecialDiscountEvent();
    GiftEvent giftEvent = new GiftEvent();

    Date getDate();

    Orders getOrders();

    int calculateTotalPriceBeforeDiscount();

    Gift requestGift();

    DiscountDetails calculateDiscountDetails();

    int calculateTotalDiscount();

    int calculateExpectedPayAfterDiscount();

    EventBadge requestEventBadge();
}