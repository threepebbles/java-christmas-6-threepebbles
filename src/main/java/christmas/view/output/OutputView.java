package christmas.view.output;

import christmas.domain.DTO.DiscountResultsDTO;
import christmas.domain.DTO.EventPlanDTO;
import christmas.domain.DTO.GiftDTO;
import christmas.domain.DTO.OrdersDTO;
import java.time.LocalDate;

public class OutputView {
    private final OutputPresenter outputPresenter;
    private String eventPlanHeaderScreen;
    private String orderScreen;
    private String totalPriceBeforeDiscountScreen;
    private String giftScreen;
    private String discountResultsScreen;
    private String totalDiscountScreen;
    private String expectedAmountAfterDiscountScreen;
    private String eventBadgeScreen;

    public OutputView(OutputPresenter outputPresenter) {
        init();
        this.outputPresenter = outputPresenter;
    }

    public void init() {
        // default screen
        eventPlanHeaderScreen = OutputViewFormat.EVENT_PLAN_HEADER_FORMAT;
        orderScreen = OutputViewFormat.ORDER_FORMAT;
        totalPriceBeforeDiscountScreen = OutputViewFormat.TOTAL_PRICE_BEFORE_DISCOUNT_FORMAT;
        giftScreen = OutputViewFormat.GIFT_FORMAT;
        discountResultsScreen = OutputViewFormat.DISCOUNT_DETAILS_FORMAT;
        totalDiscountScreen = OutputViewFormat.TOTAL_DISCOUNT_FORMAT;
        expectedAmountAfterDiscountScreen = OutputViewFormat.EXPECTED_PAY_AFTER_DISCOUNT_FORMAT;
        eventBadgeScreen = OutputViewFormat.EVENT_BADGE_FORMAT;
    }

    // 이벤트 플랜 화면 전체 업데이트
    public void updateEventPlanView(EventPlanDTO eventPlanDTO) {
        updateEventPlanHeaderScreen(eventPlanDTO.getLocalDate());
        updateOrderScreen(eventPlanDTO.getOrders());
        updateTotalPriceBeforeDiscountScreen(eventPlanDTO.getTotalPriceBeforeDiscount());
        updateGiftScreen(eventPlanDTO.getGift());
        updateDiscountResultsScreen(eventPlanDTO.getDiscountResults());
        updateTotalDiscountScreen(eventPlanDTO.getTotalDiscount());
        updateExpectedAmountAfterDiscountScreen(eventPlanDTO.getExpectedAmountAfterDiscount());
        updateEventBadgeScreen(eventPlanDTO.getEventBadgeName());
    }

    // 이벤트 플랜 화면 전체 출력
    public void renderEventPlanView() {
        renderWithLineSeparator(this::renderEventPlanHeaderScreen, 1);
        renderWithLineSeparator(this::renderOrderScreen, 1);
        renderWithLineSeparator(this::renderTotalPriceBeforeDiscountScreen, 1);
        renderWithLineSeparator(this::renderGiftScreen, 1);
        renderWithLineSeparator(this::renderDiscountResultsScreen, 1);
        renderWithLineSeparator(this::renderTotalDiscountScreen, 1);
        renderWithLineSeparator(this::renderExpectedAmountAfterDiscountScreen, 1);
        renderEventBadgeScreen();
    }

    // 헤더 메세지 화면 업데이트 및 출력
    public void updateEventPlanHeaderScreen(LocalDate localDate) {
        eventPlanHeaderScreen = String.format(
                OutputViewFormat.EVENT_PLAN_HEADER_FORMAT,
                outputPresenter.createDateText(localDate));
    }

    public void renderEventPlanHeaderScreen() {
        System.out.print(eventPlanHeaderScreen);
    }

    // <주문 메뉴> 화면 업데이트 및 출력
    public void updateOrderScreen(OrdersDTO ordersDTO) {
        orderScreen = String.format(
                OutputViewFormat.ORDER_FORMAT,
                outputPresenter.createOrderText(ordersDTO)
        );
    }

    public void renderOrderScreen() {
        System.out.print(orderScreen);
    }

    // <할인 전 총주문 금액> 화면 업데이트 및 출력
    public void updateTotalPriceBeforeDiscountScreen(int amount) {
        String amountText = outputPresenter.createTotalPriceBeforeDiscountText(amount);
        totalPriceBeforeDiscountScreen = String.format(OutputViewFormat.TOTAL_PRICE_BEFORE_DISCOUNT_FORMAT, amountText);
    }

    public void renderTotalPriceBeforeDiscountScreen() {
        System.out.print(totalPriceBeforeDiscountScreen);
    }

    // <증정 메뉴> 화면 업데이트 및 출력
    public void updateGiftScreen(GiftDTO giftDTO) {
        giftScreen = String.format(OutputViewFormat.GIFT_FORMAT, outputPresenter.createGiftText(giftDTO));
    }

    public void renderGiftScreen() {
        System.out.print(giftScreen);
    }

    // <혜택 내역> 화면 업데이트 및 출력
    public void updateDiscountResultsScreen(DiscountResultsDTO discountResultsDTO) {
        discountResultsScreen = String.format(OutputViewFormat.DISCOUNT_DETAILS_FORMAT,
                outputPresenter.createDiscountResultsText(discountResultsDTO));
    }

    public void renderDiscountResultsScreen() {
        System.out.print(discountResultsScreen);
    }

    // <총혜택 금액> 화면 업데이트 및 출력
    public void updateTotalDiscountScreen(int totalDiscount) {
        totalDiscountScreen = String.format(OutputViewFormat.TOTAL_DISCOUNT_FORMAT,
                outputPresenter.createTotalDiscountText(totalDiscount));
    }

    public void renderTotalDiscountScreen() {
        System.out.print(totalDiscountScreen);
    }

    // <할인 후 예상 결제 금액> 화면 업데이트 및 출력
    public void updateExpectedAmountAfterDiscountScreen(int expectedPay) {
        expectedAmountAfterDiscountScreen = String.format(OutputViewFormat.EXPECTED_PAY_AFTER_DISCOUNT_FORMAT,
                outputPresenter.createExpectedAmountAfterDiscountText(expectedPay));
    }

    public void renderExpectedAmountAfterDiscountScreen() {
        System.out.print(expectedAmountAfterDiscountScreen);
    }

    // <12월 이벤트 배지> 화면 업데이트 및 출력
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