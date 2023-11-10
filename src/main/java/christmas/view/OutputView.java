package christmas.view;

public class OutputView {
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String EVENT_STATISTICS_HEADER_FORMAT = "%s에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    public static final String ORDER_FORMAT = "<주문 메뉴>" + LINE_SEPARATOR + "%s";
    public static final String TOTAL_PRICE_BEFORE_DISCOUNT_FORMAT = "<할인 전 총주문 금액>" + LINE_SEPARATOR + "%s";
    public static final String GIFT_FORMAT = "<증정 메뉴>" + LINE_SEPARATOR + "%s";
    public static final String DISCOUNT_DETAILS_FORMAT = "<혜택 내역>" + LINE_SEPARATOR + "%s";
    public static final String TOTAL_DISCOUNT_FORMAT = "<총혜택 금액>" + LINE_SEPARATOR + "%s";
    public static final String EXPECTED_PAY_AFTER_DISCOUNT_FORMAT = "<할인 후 예상 결제 금액>" + LINE_SEPARATOR + "%s";
    public static final String EVENT_BADGE_FORMAT = "<12월 이벤트 배지>" + LINE_SEPARATOR + "%s";
    
    public void printEventStatisticsHeader(String dateText) {
        printByFormat(EVENT_STATISTICS_HEADER_FORMAT, dateText);
    }

    public void printOrder(String orderText) {
        printLineSpacing(2);
        printByFormat(ORDER_FORMAT, orderText);
    }

    public void printTotalPriceBeforeDiscount(String amountText) {
        printLineSpacing(1);
        printByFormat(TOTAL_PRICE_BEFORE_DISCOUNT_FORMAT, amountText);
    }

    public void printGift(String giftText) {
        printLineSpacing(1);
        printByFormat(GIFT_FORMAT, giftText);
    }

    public void printDiscountDetails(String detailsText) {
        printLineSpacing(1);
        printByFormat(DISCOUNT_DETAILS_FORMAT, detailsText);
    }

    public void printTotalDiscount(String totalDiscountText) {
        printLineSpacing(1);
        printByFormat(TOTAL_DISCOUNT_FORMAT, totalDiscountText);
    }

    public void printExpectedPayAfterDiscount(String expectedPayText) {
        printLineSpacing(1);
        printByFormat(EXPECTED_PAY_AFTER_DISCOUNT_FORMAT, expectedPayText);
    }

    public void printEventBadge(String eventBadgeText) {
        printLineSpacing(1);
        printByFormat(EVENT_BADGE_FORMAT, eventBadgeText);
    }

    public void printByFormat(String format, String... args) {
        System.out.printf(format, args);
    }

    public void printLineSpacing(int repeat) {
        System.out.print(LINE_SEPARATOR.repeat(repeat));
    }
}