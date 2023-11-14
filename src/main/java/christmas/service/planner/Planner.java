package christmas.service.planner;

import christmas.domain.Date;
import christmas.domain.DiscountResults;
import christmas.domain.Orders;
import christmas.domain.constant.EventBadge;
import christmas.domain.constant.Gift;

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