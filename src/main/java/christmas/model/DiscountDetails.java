package christmas.model;

import christmas.constant.DiscountType;
import christmas.model.event.ChristmasDDayDiscountEvent;
import christmas.model.event.DiscountEvent;
import christmas.model.event.GiftDiscountEvent;
import christmas.model.event.SpecialDiscountEvent;
import christmas.model.event.WeekdayDiscountEvent;
import christmas.model.event.WeekendDiscountEvent;
import java.util.ArrayList;
import java.util.List;

public record DiscountDetails(List<DiscountEvent> details) {
    public static DiscountDetails createEmptyDiscountDetails() {
        return new DiscountDetails(new ArrayList<>());
    }

    public static DiscountDetails createDiscountDetails(Date date, Orders orders) {
        List<DiscountEvent> discountEvents = List.of(
                new ChristmasDDayDiscountEvent(date),
                new WeekdayDiscountEvent(date, orders),
                new WeekendDiscountEvent(date, orders),
                new SpecialDiscountEvent(date),
                new GiftDiscountEvent(orders)
        );
        return new DiscountDetails(
                discountEvents.stream()
                        .filter(discount -> discount.getAmount() != 0)
                        .toList());
    }

    public int calculateTotalDiscount() {
        return details.stream()
                .mapToInt(DiscountEvent::getAmount)
                .sum();
    }

    public int calculateTotalDiscountWithoutGift() {
        return details.stream()
                .filter(discount -> discount.getDiscountType() != DiscountType.GIFT)
                .mapToInt(DiscountEvent::getAmount)
                .sum();
    }
}