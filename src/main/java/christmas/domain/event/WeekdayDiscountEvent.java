package christmas.domain.event;

import christmas.domain.Date;
import christmas.domain.DiscountResult;
import christmas.domain.Orders;
import christmas.domain.constant.EventType;
import christmas.domain.constant.MenuType;

public class WeekdayDiscountEvent implements DiscountableEvent {
    private static final int UNIT = 2023;
    private final Date date;
    private final Orders orders;

    public WeekdayDiscountEvent(Date date, Orders orders) {
        this.date = date;
        this.orders = orders;
    }

    @Override
    public DiscountResult calculateDiscountResult() {
        int amount = 0;
        if (date.isWeekDay()) {
            amount = orders.countByMenuType(MenuType.DESSERT) * UNIT;
        }
        return new DiscountResult(EventType.WEEKDAY_DISCOUNT, amount);
    }
}