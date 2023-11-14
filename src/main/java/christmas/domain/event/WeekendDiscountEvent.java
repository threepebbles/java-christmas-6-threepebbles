package christmas.domain.event;

import christmas.domain.Date;
import christmas.domain.DiscountResult;
import christmas.domain.Orders;
import christmas.domain.constant.EventType;
import christmas.domain.constant.MenuType;

public class WeekendDiscountEvent implements DiscountableEvent {
    private static final int DISCOUNT_UNIT = 2023;
    private final Date date;
    private final Orders orders;

    public WeekendDiscountEvent(Date date, Orders orders) {
        this.date = date;
        this.orders = orders;
    }

    @Override
    public DiscountResult calculateDiscountResult() {
        int amount = 0;
        if (date.isWeekend()) {
            amount = orders.countByMenuType(MenuType.MAIN) * DISCOUNT_UNIT;
        }
        return new DiscountResult(EventType.WEEKEND_DISCOUNT, amount);
    }
}