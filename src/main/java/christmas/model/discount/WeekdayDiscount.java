package christmas.model.discount;

import christmas.constant.DiscountType;
import christmas.constant.MenuType;
import christmas.model.Date;
import christmas.model.Orders;

public class WeekdayDiscount extends Discount {
    private final int UNIT = 2023;

    public WeekdayDiscount(Date date, Orders orders) {
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