package christmas.view.output;

import christmas.constant.EventBadge;
import christmas.model.Date;
import christmas.model.DiscountDetails;
import christmas.model.Gift;
import christmas.model.Order;

public class OutputView {
    public static final String LINE_SEPARATOR = System.lineSeparator();
    private final Presenter presenter;
    private String eventPlanHeaderScreen;
    private String orderScreen;
    private String totalPriceBeforeDiscountScreen;
    private String giftScreen;
    private String discountDetailsScreen;
    private String totalDiscountScreen;
    private String expectedPayAfterDiscountScreen;
    private String eventBadgeScreen;

    public OutputView(Presenter presenter) {
        this.presenter = presenter;
        init();
    }

    public void init() {
        // default screen
        eventPlanHeaderScreen = OutputViewFormat.EVENT_PLAN_HEADER_FORMAT;
        orderScreen = OutputViewFormat.ORDER_FORMAT;
        totalPriceBeforeDiscountScreen = OutputViewFormat.TOTAL_PRICE_BEFORE_DISCOUNT_FORMAT;
        giftScreen = OutputViewFormat.GIFT_FORMAT;
        discountDetailsScreen = OutputViewFormat.DISCOUNT_DETAILS_FORMAT;
        totalDiscountScreen = OutputViewFormat.TOTAL_DISCOUNT_FORMAT;
        expectedPayAfterDiscountScreen = OutputViewFormat.EXPECTED_PAY_AFTER_DISCOUNT_FORMAT;
        eventBadgeScreen = OutputViewFormat.EVENT_BADGE_FORMAT;
    }

    // 헤더 메세지 화면 업데이트 및 출력
    public void updateEventPlanHeaderScreen(Date date) {
        eventPlanHeaderScreen = String.format(
                OutputViewFormat.EVENT_PLAN_HEADER_FORMAT,
                presenter.createDateText(date));
    }

    public void renderEventPlanHeaderScreen() {
        System.out.print(eventPlanHeaderScreen);
    }

    // <주문 메뉴> 화면 업데이트 및 출력
    public void updateOrderScreen(Order order) {
        orderScreen = String.format(
                OutputViewFormat.ORDER_FORMAT,
                presenter.createOrderText(order)
        );
    }

    public void renderOrderScreen() {
        System.out.print(orderScreen);
    }

    // <할인 전 총주문 금액> 화면 업데이트 및 출력
    public void updateTotalPriceBeforeDiscountScreen(int amount) {
        String amountText = presenter.createTotalPriceBeforeDiscountText(amount);
        totalPriceBeforeDiscountScreen = String.format(OutputViewFormat.TOTAL_PRICE_BEFORE_DISCOUNT_FORMAT, amountText);
    }

    public void renderTotalPriceBeforeDiscountScreen() {
        System.out.print(totalPriceBeforeDiscountScreen);
    }

    // <증정 메뉴> 화면 업데이트 및 출력
    public void updateGiftScreen(Gift gift) {
        giftScreen = String.format(OutputViewFormat.GIFT_FORMAT, presenter.createGiftText(gift));
    }

    public void renderGiftScreen() {
        System.out.print(giftScreen);
    }

    // <혜택 내역> 화면 업데이트 및 출력
    public void updateDiscountDetailsScreen(DiscountDetails details) {
        discountDetailsScreen = String.format(OutputViewFormat.DISCOUNT_DETAILS_FORMAT,
                presenter.createDiscountDetailsText(details.details()));
    }

    public void renderDiscountDetailsScreen() {
        System.out.print(discountDetailsScreen);
    }

    // <총혜택 금액> 화면 업데이트 및 출력
    public void updateTotalDiscountScreen(int totalDiscount) {
        totalDiscountScreen = String.format(OutputViewFormat.TOTAL_DISCOUNT_FORMAT,
                presenter.createTotalDiscountText(totalDiscount));
    }

    public void renderTotalDiscountScreen() {
        System.out.print(totalDiscountScreen);
    }

    // <할인 후 예상 결제 금액> 화면 업데이트 및 출력
    public void updateExpectedPayAfterDiscountScreen(int expectedPay) {
        expectedPayAfterDiscountScreen = String.format(OutputViewFormat.EXPECTED_PAY_AFTER_DISCOUNT_FORMAT,
                presenter.createExpectedPayAfterDiscountText(expectedPay));
    }

    public void renderExpectedPayAfterDiscountScreen() {
        System.out.print(expectedPayAfterDiscountScreen);
    }

    // <12월 이벤트 배지> 화면 업데이트 및 출력
    public void updateEventBadgeScreen(EventBadge eventBadge) {
        eventBadgeScreen = String.format(OutputViewFormat.EVENT_BADGE_FORMAT,
                presenter.createEventBadgeText(eventBadge));
    }

    public void renderEventBadgeScreen() {
        System.out.print(eventBadgeScreen);
    }

    public void printLineSeparator(int repeat) {
        System.out.print(LINE_SEPARATOR.repeat(repeat));
    }
}