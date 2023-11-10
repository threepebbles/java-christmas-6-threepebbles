package christmas.domain;

import java.util.List;

public class Discounter {
    private static Discounter discounter;

    private Discounter() {
    }

    public static Discounter getInstance() {
        if (discounter == null) {
            discounter = new Discounter();
        }
        return discounter;
    }

    public List<Discount> calculateDiscountDetails(Date date, Order order) {
        List<Discount> discounts = List.of(
                calculateChristmasDiscount(date),
                calculateWeekdayDiscount(date, order),
                calculateWeekendDiscount(date, order),
                calculateSpecialDiscount(date),
                calculateGiftDiscount(order)
        );
        return discounts.stream()
                .filter(discount -> discount.getAmount() != 0)
                .toList();
    }

    public Discount calculateChristmasDiscount(Date date) {
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

    public Discount calculateWeekdayDiscount(Date date, Order order) {
        int amount = 0;
        if (date.isWeekDay()) {
            final int UNIT = 2023;
            amount = order.countByMenuType(MenuType.DESSERT) * UNIT;
        }
        return new Discount(DiscountType.WEEKDAY, amount);
    }

    public Discount calculateWeekendDiscount(Date date, Order order) {
        int amount = 0;
        if (date.isWeekend()) {
            final int UNIT = 2023;
            amount = order.countByMenuType(MenuType.MAIN) * UNIT;
        }
        return new Discount(DiscountType.WEEKEND, amount);
    }

    public Discount calculateSpecialDiscount(Date date) {
        int amount = 0;
        List<Integer> specialDays = List.of(3, 10, 17, 24, 25, 31);
        if (specialDays.contains(date.getDay())) {
            amount = 1000;
        }
        return new Discount(DiscountType.SPECIAL, amount);
    }


    private Discount calculateGiftDiscount(Order order) {
        int amount = 0;
        if (order.calculateTotalPriceBeforeDiscount() >= Gift.THRESHOLD) {
            amount = Menu.CHAMPAGNE.getPrice();
        }
        return new Discount(DiscountType.GIFT, amount);
    }
}
