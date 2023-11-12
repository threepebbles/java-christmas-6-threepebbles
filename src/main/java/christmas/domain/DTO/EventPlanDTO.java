package christmas.domain.DTO;

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

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public OrdersDTO getOrders() {
        return orders;
    }

    public void setOrders(OrdersDTO orders) {
        this.orders = orders;
    }

    public int getTotalPriceBeforeDiscount() {
        return totalPriceBeforeDiscount;
    }

    public void setTotalPriceBeforeDiscount(int totalPriceBeforeDiscount) {
        this.totalPriceBeforeDiscount = totalPriceBeforeDiscount;
    }

    public GiftDTO getGift() {
        return gift;
    }

    public void setGift(GiftDTO gift) {
        this.gift = gift;
    }

    public DiscountResultsDTO getDiscountResults() {
        return DiscountResults;
    }

    public void setDiscountResults(DiscountResultsDTO discountResults) {
        DiscountResults = discountResults;
    }

    public int getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(int totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public int getExpectedAmountAfterDiscount() {
        return expectedAmountAfterDiscount;
    }

    public void setExpectedAmountAfterDiscount(int expectedAmountAfterDiscount) {
        this.expectedAmountAfterDiscount = expectedAmountAfterDiscount;
    }

    public String getEventBadgeName() {
        return eventBadgeName;
    }

    public void setEventBadgeName(String eventBadgeName) {
        this.eventBadgeName = eventBadgeName;
    }
}