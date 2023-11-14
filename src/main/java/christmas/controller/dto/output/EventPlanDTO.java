package christmas.controller.dto.output;

import java.time.LocalDate;

public class EventPlanDTO {
    private LocalDate localDate;
    private OrdersDTO orders;
    private int totalPriceBeforeDiscount;
    private GiftDTO gift;
    private DiscountResultsDTO DiscountResults;
    private int totalDiscount;
    private int expectedAmountAfterDiscount;
    private String eventBadgeName;

    public EventPlanDTO(LocalDate localDate, OrdersDTO orders, int totalPriceBeforeDiscount, GiftDTO gift,
                        DiscountResultsDTO discountResults, int totalDiscount, int expectedAmountAfterDiscount,
                        String eventBadgeName) {
        this.localDate = localDate;
        this.orders = orders;
        this.totalPriceBeforeDiscount = totalPriceBeforeDiscount;
        this.gift = gift;
        DiscountResults = discountResults;
        this.totalDiscount = totalDiscount;
        this.expectedAmountAfterDiscount = expectedAmountAfterDiscount;
        this.eventBadgeName = eventBadgeName;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public OrdersDTO getOrders() {
        return orders;
    }

    public int getTotalPriceBeforeDiscount() {
        return totalPriceBeforeDiscount;
    }

    public GiftDTO getGift() {
        return gift;
    }

    public DiscountResultsDTO getDiscountResults() {
        return DiscountResults;
    }

    public int getTotalDiscount() {
        return totalDiscount;
    }

    public int getExpectedAmountAfterDiscount() {
        return expectedAmountAfterDiscount;
    }

    public String getEventBadgeName() {
        return eventBadgeName;
    }
}