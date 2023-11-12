package christmas.model;

import christmas.constant.EventBadge;
import christmas.constant.Gift;

public class EventPlan {
    private Date date;
    private Orders orders;
    private int totalPriceBeforeDiscount;
    private Gift gift;
    private DiscountDetails discountDetails;
    private int totalDiscount;
    private int ExpectedPayAfterDiscount;
    private EventBadge eventBadge;

    public EventPlan(Date date, Orders orders, int totalPriceBeforeDiscount, Gift gift,
                     DiscountDetails discountDetails,
                     int totalDiscount, int expectedPayAfterDiscount, EventBadge eventBadge) {
        this.date = date;
        this.orders = orders;
        this.totalPriceBeforeDiscount = totalPriceBeforeDiscount;
        this.gift = gift;
        this.discountDetails = discountDetails;
        this.totalDiscount = totalDiscount;
        ExpectedPayAfterDiscount = expectedPayAfterDiscount;
        this.eventBadge = eventBadge;
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

    public DiscountDetails getDiscountDetails() {
        return discountDetails;
    }

    public int getTotalDiscount() {
        return totalDiscount;
    }

    public int getExpectedPayAfterDiscount() {
        return ExpectedPayAfterDiscount;
    }

    public EventBadge getEventBadge() {
        return eventBadge;
    }
}