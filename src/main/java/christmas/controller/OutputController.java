package christmas.controller;

import christmas.domain.Date;
import christmas.domain.Discount;
import christmas.domain.EventBadge;
import christmas.domain.Gift;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.view.OutputView;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class OutputController {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String EVENT_STATISTICS_HEADER_FORMAT = "%s에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    public static final String ORDER_FORMAT = "<주문 메뉴>%s";
    public static final String TOTAL_PRICE_BEFORE_DISCOUNT_FORMAT = "<할인 전 총주문 금액>%s";
    public static final String GIFT_FORMAT = "<증정 메뉴>%s";
    public static final String DISCOUNT_DETAILS_FORMAT = "<혜택 내역>%s";
    public static final String TOTAL_DISCOUNT_FORMAT = "<총혜택 금액>%s";
    public static final String EXPECTED_PAY_AFTER_DISCOUNT_FORMAT = "<할인 후 예상 결제 금액>%s";
    public static final String EVENT_BADGE_FORMAT = "<12월 이벤트 배지>%s";
    private static final String NOTHING = "없음";
    private final OutputView outputView;

    public OutputController(OutputView outputView) {
        this.outputView = outputView;
    }

    // 이벤트 플래어 헤더 메세지
    public void printEventStatisticsHeader(Date date) {
        String dateText = createDateText(date);
        String text = String.format(EVENT_STATISTICS_HEADER_FORMAT, dateText);
        outputView.printText(text);
    }

    private String createDateText(Date date) {
        LocalDate localDate = date.getDate();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M월 d일");
        return localDate.format(dateTimeFormatter);
    }

    // 주문 메뉴
    public void printOrder(Order order) {
        String orderText = createOrderText(order);
        String text = LINE_SEPARATOR.repeat(2)
                + String.format(ORDER_FORMAT, orderText);
        outputView.printText(text);
    }

    private String createOrderText(Order order) {
        StringBuilder sb = new StringBuilder();
        Map<Menu, Integer> menuCounter = order.getMenuCounter();
        menuCounter.forEach((menu, count) -> {
            sb.append(LINE_SEPARATOR).
                    append(String.format("%s %d개", menu.getName(), count));
        });
        return sb.toString();
    }

    // 할인 전 총 주문 금액
    public void printTotalPriceBeforeDiscount(int amount) {
        String amountText = createTotalPriceBeforeDiscountText(amount);
        String text = LINE_SEPARATOR.repeat(2)
                + String.format(TOTAL_PRICE_BEFORE_DISCOUNT_FORMAT, amountText);
        outputView.printText(text);
    }

    private String createTotalPriceBeforeDiscountText(int amount) {
        DecimalFormat decFormat = new DecimalFormat("###,###");
        return LINE_SEPARATOR + String.format("%s%s",
                decFormat.format(amount),
                Menu.CURRENCY_UNIT);
    }

    // 증정 메뉴
    public void printGift(Gift gift) {
        String giftText = createGiftText(gift);
        String text = LINE_SEPARATOR.repeat(2)
                + String.format(GIFT_FORMAT, giftText);
        outputView.printText(text);
    }

    private String createGiftText(Gift gift) {
        if (gift == Gift.NOTHING) {
            return LINE_SEPARATOR + NOTHING;
        }
        return LINE_SEPARATOR + String.format("%s 1개", gift.getName());
    }

    // 혜택 내역
    public void printDiscountDetails(List<Discount> discounts) {
        String discountsText = createDiscountDetailsText(discounts);
        String text = LINE_SEPARATOR.repeat(2)
                + String.format(DISCOUNT_DETAILS_FORMAT, discountsText);
        outputView.printText(text);
    }

    private String createDiscountDetailsText(List<Discount> discounts) {
        if (discounts.isEmpty()) {
            return LINE_SEPARATOR + NOTHING;
        }
        DecimalFormat decFormat = new DecimalFormat("###,###");
        StringBuilder sb = new StringBuilder();
        discounts.forEach(discount -> sb.append(LINE_SEPARATOR)
                .append(String.format("%s: -%s%s",
                        discount.getName(),
                        decFormat.format(discount.getAmount()),
                        Menu.CURRENCY_UNIT)));
        return sb.toString();
    }

    // 총혜택 금액
    public void printTotalDiscount(int totalDiscount) {
        String totalDiscountText = createTotalDiscountText(totalDiscount);
        String text = LINE_SEPARATOR.repeat(2)
                + String.format(TOTAL_DISCOUNT_FORMAT, totalDiscountText);
        outputView.printText(text);
    }

    private String createTotalDiscountText(int totalDiscount) {
        DecimalFormat decFormat = new DecimalFormat("###,###");
        return LINE_SEPARATOR
                + String.format("%s%s",
                decFormat.format(-totalDiscount),
                Menu.CURRENCY_UNIT);
    }

    // 할인 후 예상 결제 금액
    public void printExpectedPayAfterDiscount(int expectedPay) {
        String expectedPayText = createExpectedPayAfterDiscountText(expectedPay);
        String text = LINE_SEPARATOR.repeat(2)
                + String.format(EXPECTED_PAY_AFTER_DISCOUNT_FORMAT, expectedPayText);
        outputView.printText(text);
    }

    private String createExpectedPayAfterDiscountText(int expectedPayAfterDiscount) {
        DecimalFormat decFormat = new DecimalFormat("###,###");
        return LINE_SEPARATOR
                + String.format("%s%s",
                decFormat.format(expectedPayAfterDiscount),
                Menu.CURRENCY_UNIT);
    }

    // 12월 이벤트 배지
    public void printEventBadge(EventBadge eventBadge) {
        String eventBadgeText = createEventBadgeText(eventBadge);
        String text = LINE_SEPARATOR.repeat(2)
                + String.format(EVENT_BADGE_FORMAT, eventBadgeText);
        outputView.printText(text);
    }

    private String createEventBadgeText(EventBadge eventBadge) {
        if (eventBadge == EventBadge.NOTHING) {
            return LINE_SEPARATOR + NOTHING;
        }
        return LINE_SEPARATOR
                + String.format(eventBadge.getName());
    }
}