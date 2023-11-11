package christmas.model.discount;

import christmas.constant.DiscountType;
import christmas.constant.MenuType;
import christmas.model.Date;
import christmas.model.Order;

public class WeekendDiscount extends Discount {
    private final int UNIT = 2023;

    public WeekendDiscount(Date date, Order order) {
        super(DiscountType.WEEKEND);
        this.amount = calculateAmount(date, order);
    }

    private int calculateAmount(Date date, Order order) {
        int amount = 0;
        if (date.isWeekend()) {
            amount = order.countByMenuType(MenuType.MAIN) * UNIT;
        }
        return amount;
    }
}