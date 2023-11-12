package christmas.service.event;

import christmas.constant.EventType;
import christmas.constant.MenuType;
import christmas.model.Date;
import christmas.model.DiscountResult;
import christmas.model.Orders;

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