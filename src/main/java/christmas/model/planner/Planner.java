package christmas.model.planner;

import christmas.constant.EventBadge;
import christmas.model.DiscountDetails;
import christmas.model.Gift;

public interface Planner {
    int calculateTotalPriceBeforeDiscount();

    Gift calculateGift();

    DiscountDetails calculateDiscountDetails();

    int calculateTotalDiscount();

    int calculateExpectedPayAfterDiscount();

    EventBadge calculateEventBadge();
}
