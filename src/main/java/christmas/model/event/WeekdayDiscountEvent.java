package christmas.model.event;

import christmas.constant.DiscountType;
import christmas.constant.MenuType;
import christmas.model.Date;
import christmas.model.Orders;

public class WeekdayDiscountEvent extends DiscountEvent {
    private final int UNIT = 2023;

    public WeekdayDiscountEvent(Date date, Orders orders) {
        super(DiscountType.WEEKDAY);
        this.amount = calculateAmount(date, orders);
    }

    private int calculateAmount(Date date, Orders orders) {
        int amount = 0;
        if (date.isWeekDay()) {
            amount = orders.countByMenuType(MenuType.DESSERT) * UNIT;
        }
        return amount;
    }
}