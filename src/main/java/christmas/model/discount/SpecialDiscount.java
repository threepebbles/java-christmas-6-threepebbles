package christmas.model.discount;

import christmas.model.Date;
import christmas.model.DiscountType;
import java.util.List;

public class SpecialDiscount extends Discount {
    public static final List<Integer> specialDays = List.of(3, 10, 17, 24, 25, 31);
    private final int UNIT = 1000;

    public SpecialDiscount(Date date) {
        super(DiscountType.SPECIAL);
        this.amount = calculateAmount(date);
    }

    private int calculateAmount(Date date) {
        int amount = 0;
        if (specialDays.contains(date.getDay())) {
            amount = UNIT;
        }
        return amount;
    }
}