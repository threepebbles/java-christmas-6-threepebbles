package christmas.domain;

import christmas.domain.dto.output.EventPlanDTO;

public class EventPlan {
    private final Date date;
    private final Orders orders;
    private final int totalPriceBeforeDiscount;
    private final Gift gift;
    private final DiscountResults discountResults;
    private final int totalDiscount;
    private final int expectedPriceAfterDiscount;
    private final EventBadge eventBadge;

    public EventPlan(Date date, Orders orders, int totalPriceBeforeDiscount, Gift gift,
                     DiscountResults discountResults,
                     int totalDiscount, int expectedPriceAfterDiscount, EventBadge eventBadge) {
        this.date = date;
        this.orders = orders;
        this.totalPriceBeforeDiscount = totalPriceBeforeDiscount;
        this.gift = gift;
        this.discountResults = discountResults;
        this.totalDiscount = totalDiscount;
        this.expectedPriceAfterDiscount = expectedPriceAfterDiscount;
        this.eventBadge = eventBadge;
    }

    public EventPlanDTO toDTO() {
        return new EventPlanDTO(
                date.getLocalDate(),
                orders.toDTO(),
                totalPriceBeforeDiscount,
                gift.toDTO(),
                discountResults.toDTO(),
                totalDiscount,
                expectedPriceAfterDiscount,
                eventBadge.getName());
    }
}