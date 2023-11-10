package christmas.model;

import java.util.List;

public class DiscountDetails {
    private final List<Discount> details;

    public DiscountDetails(List<Discount> details) {
        this.details = details;
    }

    public static DiscountDetails createDiscountDetails(Date date, Order order) {
        List<Discount> discounts = List.of(
                Discount.createChristmasDiscount(date),
                Discount.createWeekdayDiscount(date, order),
                Discount.createWeekendDiscount(date, order),
                Discount.createSpecialDiscount(date),
                Discount.createGiftDiscount(order)
        );
        return new DiscountDetails(
                discounts.stream()
                        .filter(discount -> discount.getAmount() != 0)
                        .toList());
    }

    public int calculateTotalDiscount() {
        return details.stream()
                .mapToInt(Discount::getAmount)
                .sum();
    }

    public int calculateTotalDiscountWithoutGift() {
        return details.stream()
                .filter(discount -> discount.getDiscountType() != DiscountType.GIFT)
                .mapToInt(Discount::getAmount)
                .sum();
    }

    public List<Discount> getDetails() {
        return details;
    }
}