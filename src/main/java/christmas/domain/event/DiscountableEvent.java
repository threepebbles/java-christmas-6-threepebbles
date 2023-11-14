package christmas.domain.event;

import christmas.domain.DiscountResult;

public interface DiscountableEvent {
    DiscountResult calculateDiscountResult();
}