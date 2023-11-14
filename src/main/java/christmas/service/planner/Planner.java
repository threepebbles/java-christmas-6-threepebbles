package christmas.service.planner;

import christmas.domain.Date;
import christmas.domain.DiscountResults;
import christmas.domain.EventBadge;
import christmas.domain.Gift;
import christmas.domain.Orders;

public interface Planner {
    int calculateTotalPriceBeforeDiscount();

    Gift requestGift();

    DiscountResults calculateDiscountResults();

    int calculateTotalDiscount();

    int calculateExpectedPriceAfterDiscount();

    EventBadge requestEventBadge();

    Date getDate();

    Orders getOrders();
}