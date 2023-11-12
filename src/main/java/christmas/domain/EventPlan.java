package christmas.domain;

import christmas.constant.EventBadge;
import christmas.constant.Gift;
import christmas.domain.dto.EventPlanDTO;

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
        EventPlanDTO eventPlanDTO = new EventPlanDTO();
        eventPlanDTO.setLocalDate(date.getLocalDate());
        eventPlanDTO.setOrders(orders.toDTO());
        eventPlanDTO.setTotalPriceBeforeDiscount(totalPriceBeforeDiscount);
        eventPlanDTO.setGift(gift.toDTO());
        eventPlanDTO.setDiscountResults(discountResults.toDTO());
        eventPlanDTO.setTotalDiscount(totalDiscount);
        eventPlanDTO.setExpectedAmountAfterDiscount(expectedAmountAfterDiscount);
        eventPlanDTO.setEventBadgeName(eventBadge.getName());
        return eventPlanDTO;
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