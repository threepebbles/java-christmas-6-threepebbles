package christmas.domain.event;

import christmas.domain.Date;
import christmas.domain.DiscountResult;
import christmas.domain.constant.EventType;
import java.util.List;

public class SpecialDiscountEvent implements DiscountableEvent {
    private static final List<Integer> specialDays = List.of(3, 10, 17, 24, 25, 31);
    private static final int DISCOUNT_UNIT = 1000;
    private final Date date;

    public SpecialDiscountEvent(Date date) {
        this.date = date;
    }

    @Override
    public DiscountResult calculateDiscountResult() {
        int amount = 0;
        if (specialDays.contains(date.getDay())) {
            amount = DISCOUNT_UNIT;
        }
        return new DiscountResult(EventType.SPECIAL_DISCOUNT, amount);
    }
}