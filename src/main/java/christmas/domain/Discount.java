package christmas.domain;

import java.util.List;

public class Discount {
    private final DiscountType discountType;
    private final int amount;

    public Discount(DiscountType discountType, int amount) {
        this.discountType = discountType;
        this.amount = amount;
    }

    public static Discount createChristmasDiscount(Date date) {
        int amount = 0;
        final int DEAD_LINE_DAY = 25;

        int day = date.getDay();
        if (day <= DEAD_LINE_DAY) {
            final int DEFAULT = 1000;
            final int UNIT = 100;
            amount = DEFAULT + (day - 1) * UNIT;
        }
        return new Discount(DiscountType.CHRISTMAS_D_DAY, amount);
    }

    public static Discount createWeekdayDiscount(Date date, Order order) {
        int amount = 0;
        if (date.isWeekDay()) {
            final int UNIT = 2023;
            amount = order.countByMenuType(MenuType.DESSERT) * UNIT;
        }
        return new Discount(DiscountType.WEEKDAY, amount);
    }

    public static Discount createWeekendDiscount(Date date, Order order) {
        int amount = 0;
        if (date.isWeekend()) {
            final int UNIT = 2023;
            amount = order.countByMenuType(MenuType.MAIN) * UNIT;
        }
        return new Discount(DiscountType.WEEKEND, amount);
    }

    public static Discount createSpecialDiscount(Date date) {
        int amount = 0;
        List<Integer> specialDays = List.of(3, 10, 17, 24, 25, 31);
        if (specialDays.contains(date.getDay())) {
            amount = 1000;
        }
        return new Discount(DiscountType.SPECIAL, amount);
    }


    public static Discount createGiftDiscount(Order order) {
        int amount = 0;
        if (order.calculateTotalPriceBeforeDiscount() >= Gift.THRESHOLD) {
            amount = Menu.CHAMPAGNE.getPrice();
        }
        return new Discount(DiscountType.GIFT, amount);
    }

    public String getName() {
        return discountType.getName();
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public int getAmount() {
        return amount;
    }
}
