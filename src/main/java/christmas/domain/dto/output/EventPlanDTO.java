package christmas.domain.dto.output;

import java.time.LocalDate;

public class EventPlanDTO {
    private final LocalDate localDate;
    private final OrdersDTO orders;
    private final int totalPriceBeforeDiscount;
    private final GiftDTO gift;
    private final DiscountResultsDTO DiscountResults;
    private final int totalDiscount;
    private final int expectedPriceAfterDiscount;
    private final String eventBadgeName;

    public EventPlanDTO(LocalDate localDate, OrdersDTO orders, int totalPriceBeforeDiscount, GiftDTO gift,
                        DiscountResultsDTO discountResults, int totalDiscount, int expectedPriceAfterDiscount,
                        String eventBadgeName) {
        this.localDate = localDate;
        this.orders = orders;
        this.totalPriceBeforeDiscount = totalPriceBeforeDiscount;
        this.gift = gift;
        DiscountResults = discountResults;
        this.totalDiscount = totalDiscount;
        this.expectedPriceAfterDiscount = expectedPriceAfterDiscount;
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

    public int getExpectedPriceAfterDiscount() {
        return expectedPriceAfterDiscount;
    }

    public String getEventBadgeName() {
        return eventBadgeName;
    }
}