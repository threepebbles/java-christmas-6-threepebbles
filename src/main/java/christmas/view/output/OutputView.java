package christmas.view.output;

import christmas.domain.dto.output.DiscountResultsDTO;
import christmas.domain.dto.output.EventPlanDTO;
import christmas.domain.dto.output.GiftDTO;
import christmas.domain.dto.output.OrdersDTO;
import christmas.view.output.constant.OutputViewFormat;
import java.time.LocalDate;

public class OutputView {
    private final OutputPresenter outputPresenter;
    private String eventPlanHeaderScreen;
    private String ordersScreen;
    private String totalPriceBeforeDiscountScreen;
    private String giftScreen;
    private String discountResultsScreen;
    private String totalDiscountScreen;
    private String expectedPriceAfterDiscountScreen;
    private String eventBadgeScreen;

    public OutputView(OutputPresenter outputPresenter) {
        init();
        this.outputPresenter = outputPresenter;
    }

    public void init() {
        eventPlanHeaderScreen = OutputViewFormat.EVENT_PLAN_HEADER_FORMAT;
        ordersScreen = OutputViewFormat.ORDERS_FORMAT;
        totalPriceBeforeDiscountScreen = OutputViewFormat.TOTAL_PRICE_BEFORE_DISCOUNT_FORMAT;
        giftScreen = OutputViewFormat.GIFT_FORMAT;
        discountResultsScreen = OutputViewFormat.DISCOUNT_DETAILS_FORMAT;
        totalDiscountScreen = OutputViewFormat.TOTAL_DISCOUNT_FORMAT;
        expectedPriceAfterDiscountScreen = OutputViewFormat.EXPECTED_PAY_AFTER_DISCOUNT_FORMAT;
        eventBadgeScreen = OutputViewFormat.EVENT_BADGE_FORMAT;
    }

    public void updateEventPlanView(EventPlanDTO eventPlanDTO) {
        updateEventPlanHeaderScreen(eventPlanDTO.getLocalDate());
        updateOrdersScreen(eventPlanDTO.getOrders());
        updateTotalPriceBeforeDiscountScreen(eventPlanDTO.getTotalPriceBeforeDiscount());
        updateGiftScreen(eventPlanDTO.getGift());
        updateDiscountResultsScreen(eventPlanDTO.getDiscountResults());
        updateTotalDiscountScreen(eventPlanDTO.getTotalDiscount());
        updateExpectedPriceAfterDiscountScreen(eventPlanDTO.getExpectedPriceAfterDiscount());
        updateEventBadgeScreen(eventPlanDTO.getEventBadgeName());
    }

    public void renderEventPlanView() {
        renderWithLineSeparator(this::renderEventPlanHeaderScreen, 1);
        renderWithLineSeparator(this::renderOrdersScreen, 1);
        renderWithLineSeparator(this::renderTotalPriceBeforeDiscountScreen, 1);
        renderWithLineSeparator(this::renderGiftScreen, 1);
        renderWithLineSeparator(this::renderDiscountResultsScreen, 1);
        renderWithLineSeparator(this::renderTotalDiscountScreen, 1);
        renderWithLineSeparator(this::renderExpectedPriceAfterDiscountScreen, 1);
        renderEventBadgeScreen();
    }

    public void updateEventPlanHeaderScreen(LocalDate localDate) {
        eventPlanHeaderScreen = String.format(
                OutputViewFormat.EVENT_PLAN_HEADER_FORMAT,
                outputPresenter.createDateText(localDate));
    }

    public void renderEventPlanHeaderScreen() {
        System.out.print(eventPlanHeaderScreen);
    }

    public void updateOrdersScreen(OrdersDTO ordersDTO) {
        ordersScreen = String.format(
                OutputViewFormat.ORDERS_FORMAT,
                outputPresenter.createOrdersText(ordersDTO)
        );
    }

    public void renderOrdersScreen() {
        System.out.print(ordersScreen);
    }

    public void updateTotalPriceBeforeDiscountScreen(int amount) {
        String amountText = outputPresenter.createTotalPriceBeforeDiscountText(amount);
        totalPriceBeforeDiscountScreen = String.format(OutputViewFormat.TOTAL_PRICE_BEFORE_DISCOUNT_FORMAT, amountText);
    }

    public void renderTotalPriceBeforeDiscountScreen() {
        System.out.print(totalPriceBeforeDiscountScreen);
    }

    public void updateGiftScreen(GiftDTO giftDTO) {
        giftScreen = String.format(OutputViewFormat.GIFT_FORMAT, outputPresenter.createGiftText(giftDTO));
    }

    public void renderGiftScreen() {
        System.out.print(giftScreen);
    }

    public void updateDiscountResultsScreen(DiscountResultsDTO discountResultsDTO) {
        discountResultsScreen = String.format(OutputViewFormat.DISCOUNT_DETAILS_FORMAT,
                outputPresenter.createDiscountResultsText(discountResultsDTO));
    }

    public void renderDiscountResultsScreen() {
        System.out.print(discountResultsScreen);
    }

    public void updateTotalDiscountScreen(int totalDiscount) {
        totalDiscountScreen = String.format(OutputViewFormat.TOTAL_DISCOUNT_FORMAT,
                outputPresenter.createTotalDiscountText(totalDiscount));
    }

    public void renderTotalDiscountScreen() {
        System.out.print(totalDiscountScreen);
    }

    public void updateExpectedPriceAfterDiscountScreen(int expectedPay) {
        expectedPriceAfterDiscountScreen = String.format(OutputViewFormat.EXPECTED_PAY_AFTER_DISCOUNT_FORMAT,
                outputPresenter.createExpectedPriceAfterDiscountText(expectedPay));
    }

    public void renderExpectedPriceAfterDiscountScreen() {
        System.out.print(expectedPriceAfterDiscountScreen);
    }

    public void updateEventBadgeScreen(String eventBadgeName) {
        eventBadgeScreen = String.format(OutputViewFormat.EVENT_BADGE_FORMAT,
                outputPresenter.createEventBadgeText(eventBadgeName));
    }

    public void renderEventBadgeScreen() {
        System.out.print(eventBadgeScreen);
    }

    public void renderWithLineSeparator(Runnable toRun, int lineSeparatorCount) {
        toRun.run();
        printLineSeparator(lineSeparatorCount);
    }

    public void printLineSeparator(int repeat) {
        final String LINE_SEPARATOR = System.lineSeparator();
        System.out.print(LINE_SEPARATOR.repeat(repeat));
    }
}