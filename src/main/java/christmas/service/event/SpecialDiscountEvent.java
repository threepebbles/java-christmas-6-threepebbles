package christmas.service.event;

import christmas.constant.EventType;
import christmas.model.Date;
import christmas.model.DiscountResult;
import java.util.List;

public class SpecialDiscountEvent {
    private final List<Integer> specialDays = List.of(3, 10, 17, 24, 25, 31);
    private final int UNIT = 1000;

    public DiscountResult calculateDiscountResult(Date date) {
        int amount = 0;
        if (specialDays.contains(date.getDay())) {
            amount = UNIT;
        }
        return new DiscountResult(EventType.SPECIAL_DISCOUNT, amount);
    }
}