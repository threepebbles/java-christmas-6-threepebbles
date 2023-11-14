package christmas.domain.event;

import christmas.domain.Date;
import christmas.domain.DiscountResult;
import christmas.domain.constant.EventType;

public class ChristmasDDayDiscountEvent implements DiscountableEvent {
    public static final int DEAD_LINE_DAY = 25;
    private static final int DEFAULT_DISCOUNT = 1000;
    private static final int DISCOUNT_PER_DAY = 100;
    private final Date date;

    public ChristmasDDayDiscountEvent(Date date) {
        this.date = date;
    }

    @Override
    public DiscountResult calculateDiscountResult() {
        int amount = 0;
        int day = date.getDay();
        if (day <= DEAD_LINE_DAY) {
            amount = DEFAULT_DISCOUNT + (day - 1) * DISCOUNT_PER_DAY;
        }
        return new DiscountResult(EventType.CHRISTMAS_D_DAY_DISCOUNT, amount);
    }
}