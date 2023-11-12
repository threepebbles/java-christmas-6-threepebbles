package christmas.Service.planner;

import christmas.constant.EventBadge;
import christmas.constant.Gift;
import christmas.model.Date;
import christmas.model.DiscountDetails;
import christmas.model.Orders;

public interface Planner {
    Date getDate();

    Orders getOrders();

    int calculateTotalPriceBeforeDiscount();

    Gift calculateGift();

    DiscountDetails calculateDiscountDetails();

    int calculateTotalDiscount();

    int calculateExpectedPayAfterDiscount();

    EventBadge calculateEventBadge();
}
