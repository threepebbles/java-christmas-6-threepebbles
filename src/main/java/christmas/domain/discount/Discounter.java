package christmas.domain.discount;

import christmas.domain.Date;
import christmas.domain.Menu;
import christmas.domain.MenuType;
import christmas.domain.Order;
import java.util.ArrayList;
import java.util.List;

public class Discounter {

    public List<Discount> calculateAllDiscounts(Date date, Order order) {
        List<Discount> discounts = new ArrayList<>();
        discounts.add(calculateChristmasDiscount(date));
        discounts.add(calculateWeekdayDiscount(date, order));
        discounts.add(calculateWeekendDiscount(date, order));
        discounts.add(calculateSpecialDiscount(date));
        discounts.add(calculateGiftDiscount(order));
        return discounts.stream()
                .filter(discount -> discount.getAmount() != 0)
                .toList();
    }

    public Discount calculateChristmasDiscount(Date date) {
        final String name = "크리스마스 디데이 할인";
        int amount = 0;
        final int DEAD_LINE_DAY = 25;

        int day = date.getDay();
        if (day <= DEAD_LINE_DAY) {
            amount = 1000 + (day - 1) * 100;
        }
        return new Discount(name, amount);
    }

    public Discount calculateWeekdayDiscount(Date date, Order order) {
        final String name = "평일 할인";
        int amount = 0;
        if (date.isWeekDay()) {
            final int UNIT = 2023;
            amount = order.countByMenuType(MenuType.DESSERT) * UNIT;
        }
        return new Discount(name, amount);
    }

    public Discount calculateWeekendDiscount(Date date, Order order) {
        final String name = "주말 할인";
        int amount = 0;
        if (date.isWeekend()) {
            final int UNIT = 2023;
            amount = order.countByMenuType(MenuType.MAIN) * UNIT;
        }
        return new Discount(name, amount);
    }

    public Discount calculateSpecialDiscount(Date date) {
        final String name = "특별 할인";
        int amount = 0;
        List<Integer> specialDays = List.of(3, 10, 17, 24, 25, 31);
        if (specialDays.contains(date.getDay())) {
            amount = 1000;
        }
        return new Discount(name, amount);
    }


    private Discount calculateGiftDiscount(Order order) {
        final String name = "증정 이벤트";
        int amount = 0;
        final int THRESHOLD = 120000;
        if (order.calculateTotalPriceBeforeDiscount() >= THRESHOLD) {
            amount = Menu.CHAMPAGNE.getPrice();
        }
        return new Discount(name, amount);
    }
}
