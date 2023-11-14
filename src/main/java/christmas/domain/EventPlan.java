package christmas.domain;

import christmas.controller.dto.output.EventPlanDTO;
import christmas.domain.constant.EventBadge;
import christmas.domain.constant.Gift;

public class EventPlan {
    private Date date;
    private Orders orders;
    private int totalPriceBeforeDiscount;
    private Gift gift;
    private DiscountResults discountResults;
    private int totalDiscount;
    private int expectedPriceAfterDiscount;
    private EventBadge eventBadge;

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