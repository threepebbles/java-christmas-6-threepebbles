package christmas.view;

import christmas.model.Date;
import christmas.model.Discount;
import christmas.model.DiscountDetails;
import christmas.model.EventBadge;
import christmas.model.Gift;
import christmas.model.Menu;
import christmas.model.Order;
import christmas.utils.Converter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

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
    private static final String NOTHING = "없음";

    // 헤더 메세지 출력
    public void printEventStatisticsHeader(Date date) {
        String dateText = createDateText(date);
        printByFormat(EVENT_STATISTICS_HEADER_FORMAT, dateText);
    }

    private String createDateText(Date date) {
        LocalDate localDate = date.getDate();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M월 d일");
        return localDate.format(dateTimeFormatter);
    }

    // <주문 메뉴> 출력
    public void printOrder(Order order) {
        printLineSeparator(2);
        String orderText = createOrderText(order);
        printByFormat(ORDER_FORMAT, orderText);
    }

    private String createOrderText(Order order) {
        StringBuilder sb = new StringBuilder();
        Map<Menu, Integer> menuCounter = order.getMenuCounter();
        menuCounter.forEach((menu, count) -> {
            sb.append(String.format("%s %d개", menu.getName(), count))
                    .append(LINE_SEPARATOR);
        });
        return sb.toString();
    }

    // <할인 전 총주문 금액> 출력
    public void printTotalPriceBeforeDiscount(int amount) {
        printLineSeparator(1);
        String amountText = createTotalPriceBeforeDiscountText(amount);
        printByFormat(TOTAL_PRICE_BEFORE_DISCOUNT_FORMAT, amountText);
    }

    private String createTotalPriceBeforeDiscountText(int amount) {
        return String.format("%s%s",
                Converter.intToLocaleString(amount),
                Menu.CURRENCY_UNIT) + LINE_SEPARATOR;
    }

    // <증정 메뉴>
    public void printGift(Gift gift) {
        printLineSeparator(1);
        String giftText = createGiftText(gift);
        printByFormat(GIFT_FORMAT, giftText);
    }

    private String createGiftText(Gift gift) {
        if (gift == Gift.NOTHING) {
            return NOTHING + LINE_SEPARATOR;
        }
        return String.format("%s 1개", gift.getName()) + LINE_SEPARATOR;
    }

    // <혜택 내역>
    public void printDiscountDetails(DiscountDetails details) {
        printLineSeparator(1);
        String detailsText = createDiscountDetailsText(details.getDetails());
        printByFormat(DISCOUNT_DETAILS_FORMAT, detailsText);
    }

    private String createDiscountDetailsText(List<Discount> discounts) {
        if (discounts.isEmpty()) {
            return NOTHING + LINE_SEPARATOR;
        }
        StringBuilder sb = new StringBuilder();
        discounts.forEach(discount ->
                sb.append(String.format("%s: -%s%s",
                                discount.getName(),
                                Converter.intToLocaleString(discount.getAmount()),
                                Menu.CURRENCY_UNIT))
                        .append(LINE_SEPARATOR));
        return sb.toString();
    }

    // <총혜택 금액>
    public void printTotalDiscount(int totalDiscount) {
        printLineSeparator(1);
        String totalDiscountText = createTotalDiscountText(totalDiscount);
        printByFormat(TOTAL_DISCOUNT_FORMAT, totalDiscountText);
    }

    private String createTotalDiscountText(int totalDiscount) {
        return String.format("%s%s",
                Converter.intToLocaleString(-totalDiscount),
                Menu.CURRENCY_UNIT) + LINE_SEPARATOR;
    }

    // <할인 후 예상 결제 금액>
    public void printExpectedPayAfterDiscount(int expectedPay) {
        printLineSeparator(1);
        String expectedPayText = createExpectedPayAfterDiscountText(expectedPay);
        printByFormat(EXPECTED_PAY_AFTER_DISCOUNT_FORMAT, expectedPayText);
    }

    private String createExpectedPayAfterDiscountText(int expectedPayAfterDiscount) {
        return String.format("%s%s",
                Converter.intToLocaleString(expectedPayAfterDiscount),
                Menu.CURRENCY_UNIT) + LINE_SEPARATOR;
    }

    // 12월 이벤트 배지
    public void printEventBadge(EventBadge eventBadge) {
        printLineSeparator(1);
        String eventBadgeText = createEventBadgeText(eventBadge);
        printByFormat(EVENT_BADGE_FORMAT, eventBadgeText);
    }

    private String createEventBadgeText(EventBadge eventBadge) {
        if (eventBadge == EventBadge.NOTHING) {
            return NOTHING + LINE_SEPARATOR;
        }
        return String.format(eventBadge.getName()) + LINE_SEPARATOR;
    }

    public void printByFormat(String format, String... args) {
        System.out.printf(format, args);
    }

    public void printLineSeparator(int repeat) {
        System.out.print(LINE_SEPARATOR.repeat(repeat));
    }
}