package christmas.domain.event;

import christmas.domain.Date;
import christmas.domain.DiscountResult;
import christmas.domain.Orders;
import christmas.domain.constant.EventType;
import christmas.domain.constant.MenuType;

public class WeekdayDiscountEvent {
    private final int UNIT = 2023;

    public DiscountResult calculateDiscountResult(Date date, Orders orders) {
        int amount = 0;
        if (date.isWeekDay()) {
            amount = orders.countByMenuType(MenuType.DESSERT) * UNIT;
        }
        return new DiscountResult(EventType.WEEKDAY_DISCOUNT, amount);
    }
}