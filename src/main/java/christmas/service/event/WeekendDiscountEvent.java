package christmas.service.event;

import christmas.constant.EventType;
import christmas.constant.MenuType;
import christmas.domain.Date;
import christmas.domain.DiscountResult;
import christmas.domain.Orders;

public class WeekendDiscountEvent {
    private final int UNIT = 2023;

    public DiscountResult calculateDiscountResult(Date date, Orders orders) {
        int amount = 0;
        if (date.isWeekend()) {
            amount = orders.countByMenuType(MenuType.MAIN) * UNIT;
        }
        return new DiscountResult(EventType.WEEKEND_DISCOUNT, amount);
    }
}