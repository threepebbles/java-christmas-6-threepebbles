package christmas.model.domain;

import christmas.model.domain.discount.ChristmasDiscount;
import christmas.model.domain.discount.Discount;
import christmas.model.domain.discount.GiftDiscount;
import christmas.model.domain.discount.SpecialDiscount;
import christmas.model.domain.discount.WeekdayDiscount;
import christmas.model.domain.discount.WeekendDiscount;
import java.util.ArrayList;
import java.util.List;

public record DiscountDetails(List<Discount> details) {
    public static DiscountDetails createEmptyDiscountDetails() {
        return new DiscountDetails(new ArrayList<>());
    }

    public static DiscountDetails createDiscountDetails(Date date, Order order) {
        List<Discount> discounts = List.of(
                new ChristmasDiscount(date),
                new WeekdayDiscount(date, order),
                new WeekendDiscount(date, order),
                new SpecialDiscount(date),
                new GiftDiscount(order)
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
}