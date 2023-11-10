package christmas.view;

public class OutputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String EVENT_STATISTICS_HEADER_FORMAT = "%s에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    public static final String ORDER_FORMAT = "<주문 메뉴>%s";
    public static final String TOTAL_PRICE_BEFORE_DISCOUNT_FORMAT = "<할인 전 총주문 금액>%s";
    public static final String GIFT_FORMAT = "<증정 메뉴>%s";
    public static final String DISCOUNT_DETAILS_FORMAT = "<혜택 내역>%s";
    public static final String TOTAL_DISCOUNT_FORMAT = "<총혜택 금액>%s";
    public static final String EXPECTED_PAY_AFTER_DISCOUNT_FORMAT = "<할인 후 예상 결제 금액>%s";
    public static final String EVENT_BADGE_FORMAT = "<12월 이벤트 배지>%s";

    public void printEventStatisticsHeader(String dateText) {
        String text = String.format(EVENT_STATISTICS_HEADER_FORMAT, dateText);
        System.out.print(text);
    }

    public void printOrder(String orderText) {
        String text = LINE_SEPARATOR.repeat(2)
                + String.format(ORDER_FORMAT, orderText);
        System.out.print(text);
    }

    public void printTotalPriceBeforeDiscount(String amountText) {
        String text = LINE_SEPARATOR.repeat(2)
                + String.format(TOTAL_PRICE_BEFORE_DISCOUNT_FORMAT, amountText);
        System.out.print(text);
    }

    public void printGift(String giftText) {
        String text = LINE_SEPARATOR.repeat(2)
                + String.format(GIFT_FORMAT, giftText);
        System.out.print(text);
    }

    public void printDiscountDetails(String detailsText) {
        String text = LINE_SEPARATOR.repeat(2)
                + String.format(DISCOUNT_DETAILS_FORMAT, detailsText);
        System.out.print(text);
    }

    public void printTotalDiscount(String totalDiscountText) {
        String text = LINE_SEPARATOR.repeat(2)
                + String.format(TOTAL_DISCOUNT_FORMAT, totalDiscountText);
        System.out.print(text);
    }

    public void printExpectedPayAfterDiscount(String expectedPayText) {
        String text = LINE_SEPARATOR.repeat(2)
                + String.format(EXPECTED_PAY_AFTER_DISCOUNT_FORMAT, expectedPayText);
        System.out.print(text);
    }

    public void printEventBadge(String eventBadgeText) {
        String text = LINE_SEPARATOR.repeat(2)
                + String.format(EVENT_BADGE_FORMAT, eventBadgeText);
        System.out.print(text);
    }

    public void printText(String text) {
        System.out.print(text);
    }
}
