package christmas.model.discount;

import christmas.model.Date;
import christmas.model.DiscountType;
import christmas.model.MenuType;
import christmas.model.Order;

public class WeekdayDiscount extends Discount {
    private final int UNIT = 2023;

    public WeekdayDiscount(Date date, Order order) {
        super(DiscountType.WEEKDAY);
        this.amount = calculateAmount(date, order);
    }

    private int calculateAmount(Date date, Order order) {
        int amount = 0;
        if (date.isWeekDay()) {
            amount = order.countByMenuType(MenuType.DESSERT) * UNIT;
        }
        return amount;
    }
}