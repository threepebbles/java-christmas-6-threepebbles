package christmas.domain;

import christmas.controller.dto.EventPlanDTO;
import christmas.domain.constant.EventBadge;
import christmas.domain.constant.Gift;

public class EventPlan {
    private Date date;
    private Orders orders;
    private int totalPriceBeforeDiscount;
    private Gift gift;
    private DiscountResults discountResults;
    private int totalDiscount;
    private int expectedAmountAfterDiscount;
    private EventBadge eventBadge;

    public EventPlan(Date date, Orders orders, int totalPriceBeforeDiscount, Gift gift,
                     DiscountResults discountResults,
                     int totalDiscount, int expectedAmountAfterDiscount, EventBadge eventBadge) {
        this.date = date;
        this.orders = orders;
        this.totalPriceBeforeDiscount = totalPriceBeforeDiscount;
        this.gift = gift;
        this.discountResults = discountResults;
        this.totalDiscount = totalDiscount;
        this.expectedAmountAfterDiscount = expectedAmountAfterDiscount;
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
                expectedAmountAfterDiscount,
                eventBadge.getName());
    }

    public Date getDate() {
        return date;
    }

    public Orders getOrders() {
        return orders;
    }

    public int getTotalPriceBeforeDiscount() {
        return totalPriceBeforeDiscount;
    }

    public Gift getGift() {
        return gift;
    }

    public DiscountResults getDiscountResults() {
        return discountResults;
    }

    public int getTotalDiscount() {
        return totalDiscount;
    }

    public int getExpectedAmountAfterDiscount() {
        return expectedAmountAfterDiscount;
    }

    public EventBadge getEventBadge() {
        return eventBadge;
    }
}