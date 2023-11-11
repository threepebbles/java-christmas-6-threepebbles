package christmas.model.domain.discount;

import christmas.model.domain.Date;
import christmas.model.domain.DiscountType;
import christmas.model.domain.MenuType;
import christmas.model.domain.Order;

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