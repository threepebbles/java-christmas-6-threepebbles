package christmas.model.discount;

import christmas.constant.DiscountType;
import christmas.model.Date;

public class ChristmasDDayDiscountEvent extends DiscountEvent {
    public static final int DEAD_LINE_DAY = 25;
    private final int DEFAULT_DISCOUNT = 1000;
    private final int UNIT_AMOUNT = 100;

    public ChristmasDDayDiscountEvent(Date date) {
        super(DiscountType.CHRISTMAS_D_DAY);
        this.amount = calculateAmount(date);
    }

    public int calculateAmount(Date date) {
        int amount = 0;
        int day = date.getDay();
        if (day <= DEAD_LINE_DAY) {
            amount = DEFAULT_DISCOUNT + (day - 1) * UNIT_AMOUNT;
        }
        return amount;
    }
}