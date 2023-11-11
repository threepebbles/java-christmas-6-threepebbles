package christmas.model.planner;

import christmas.constant.EventBadge;
import christmas.constant.Gift;
import christmas.model.DiscountDetails;

public interface Planner {
    int calculateTotalPriceBeforeDiscount();

    Gift calculateGift();

    DiscountDetails calculateDiscountDetails();

    int calculateTotalDiscount();

    int calculateExpectedPayAfterDiscount();

    EventBadge calculateEventBadge();
}
