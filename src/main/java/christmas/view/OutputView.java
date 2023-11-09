package christmas.view;

public class OutputView {
    public static final String ORDER_FORMAT = "<주문 메뉴>\n%s";
    public static final String TOTAL_PRICE_BEFORE_DISCOUNT_FORMAT = "<할인 전 총 주문 금액>\n%s";
    public static final String GIFT_FORMAT = "<증정 메뉴>\n%s";
    public static final String DISCOUNT_DETAILS_FORMAT = "<혜택 내역>\n%s";
    public static final String TOTAL_DISCOUNT_FORMAT = "<총혜택 금액>\n%s";
    public static final String EXPECTED_PAY_AFTER_DISCOUNT_FORMAT = "<할인 후 예상 결제 금액>\n%s";
    public static final String EVENT_BADGE_FORMAT = "<12월 이벤트 배지>\n%s";

    public static final String NOTHING_MESSAGE = "없음";

    public void printOrder(String order) {
        String text = String.format(ORDER_FORMAT, order);
        System.out.println(text);
    }

    public void printTotalPriceBeforeDiscount(String money) {
        String text = String.format(TOTAL_PRICE_BEFORE_DISCOUNT_FORMAT, money);
        System.out.println(text);
    }

    public void printGift(String gift) {
        if (gift.isBlank()) {
            System.out.println(NOTHING_MESSAGE);
            return;
        }
        String text = String.format(GIFT_FORMAT, gift);
        System.out.println(text);
    }

    public void printDiscountDetails(String discountDetails) {
        if (discountDetails.isBlank()) {
            System.out.println(NOTHING_MESSAGE);
            return;
        }
        String text = String.format(DISCOUNT_DETAILS_FORMAT, discountDetails);
        System.out.println(text);
    }

    public void printTotalDiscount(String money) {
        String text = String.format(TOTAL_DISCOUNT_FORMAT, money);
        System.out.println(text);
    }

    public void printExpectedPayAfterDiscount(String pay) {
        String text = String.format(EXPECTED_PAY_AFTER_DISCOUNT_FORMAT, pay);
        System.out.println(text);
    }

    public void printEventBadge(String badge) {
        if (badge.isBlank()) {
            System.out.println(NOTHING_MESSAGE);
            return;
        }
        String text = String.format(EVENT_BADGE_FORMAT, badge);
        System.out.println(text);
    }
}
