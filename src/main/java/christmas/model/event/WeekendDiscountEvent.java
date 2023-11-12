package christmas.model.event;

import christmas.constant.DiscountType;
import christmas.constant.MenuType;
import christmas.model.Date;
import christmas.model.Orders;

public class WeekendDiscountEvent extends DiscountEvent {
    private final int UNIT = 2023;

    public WeekendDiscountEvent(Date date, Orders orders) {
        super(DiscountType.WEEKEND);
        this.amount = calculateAmount(date, orders);
    }

    private int calculateAmount(Date date, Orders orders) {
        int amount = 0;
        if (date.isWeekend()) {
            amount = orders.countByMenuType(MenuType.MAIN) * UNIT;
        }
        return amount;
    }
}