package christmas.model.discount;

import christmas.model.Date;

public class ChristmasDiscount extends Discount {
    private final int DEAD_LINE_DAY = 25;
    private final int DEFAULT = 1000;
    private final int UNIT = 100;

    public ChristmasDiscount(Date date) {
        super(DiscountType.CHRISTMAS_D_DAY);
        this.amount = calculateAmount(date);
    }

    private int calculateAmount(Date date) {
        int amount = 0;
        int day = date.getDay();
        if (day <= DEAD_LINE_DAY) {

            amount = DEFAULT + (day - 1) * UNIT;
        }
        return amount;
    }
}