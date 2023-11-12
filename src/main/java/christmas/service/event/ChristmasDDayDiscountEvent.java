package christmas.service.event;

import christmas.constant.EventType;
import christmas.domain.Date;
import christmas.domain.DiscountResult;

public class ChristmasDDayDiscountEvent {
    public static final int DEAD_LINE_DAY = 25;
    private final int DEFAULT_DISCOUNT = 1000;
    private final int UNIT_AMOUNT = 100;

    public DiscountResult calculateDiscountResult(Date date) {
        int amount = 0;
        int day = date.getDay();
        if (day <= DEAD_LINE_DAY) {
            amount = DEFAULT_DISCOUNT + (day - 1) * UNIT_AMOUNT;
        }
        return new DiscountResult(EventType.CHRISTMAS_D_DAY_DISCOUNT, amount);
    }
}