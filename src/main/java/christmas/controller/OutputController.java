package christmas.controller;

import christmas.domain.Date;
import christmas.domain.Discount;
import christmas.domain.EventBadge;
import christmas.domain.Gift;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.utils.Converter;
import christmas.view.OutputView;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class OutputController {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String NOTHING = "없음";
    private final OutputView outputView;

    public OutputController(OutputView outputView) {
        this.outputView = outputView;
    }

    // 이벤트 플래어 헤더 메세지
    public void printEventStatisticsHeader(Date date) {
        String dateText = createDateText(date);
        outputView.printEventStatisticsHeader(dateText);
    }

    private String createDateText(Date date) {
        LocalDate localDate = date.getDate();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M월 d일");
        return localDate.format(dateTimeFormatter);
    }

    // 주문 메뉴
    public void printOrder(Order order) {
        String orderText = createOrderText(order);
        outputView.printOrder(orderText);
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
        outputView.printTotalPriceBeforeDiscount(amountText);
    }

    private String createTotalPriceBeforeDiscountText(int amount) {
        return String.format("%s%s",
                Converter.intToLocaleString(amount),
                Menu.CURRENCY_UNIT);
    }

    // 증정 메뉴
    public void printGift(Gift gift) {
        String giftText = createGiftText(gift);
        outputView.printGift(giftText);
    }

    private String createGiftText(Gift gift) {
        if (gift == Gift.NOTHING) {
            return NOTHING;
        }
        return String.format("%s 1개", gift.getName());
    }

    // 혜택 내역
    public void printDiscountDetails(List<Discount> details) {
        String detailsText = createDiscountDetailsText(details);
        outputView.printDiscountDetails(detailsText);
    }

    private String createDiscountDetailsText(List<Discount> discounts) {
        if (discounts.isEmpty()) {
            return NOTHING;
        }
        StringBuilder sb = new StringBuilder();
        discounts.forEach(discount -> sb.append(LINE_SEPARATOR)
                .append(String.format("%s: -%s%s",
                        discount.getName(),
                        Converter.intToLocaleString(discount.getAmount()),
                        Menu.CURRENCY_UNIT)));
        return sb.toString();
    }

    // 총혜택 금액
    public void printTotalDiscount(int totalDiscount) {
        String totalDiscountText = createTotalDiscountText(totalDiscount);
        outputView.printTotalDiscount(totalDiscountText);
    }

    private String createTotalDiscountText(int totalDiscount) {
        return String.format("%s%s",
                Converter.intToLocaleString(-totalDiscount),
                Menu.CURRENCY_UNIT);
    }

    // 할인 후 예상 결제 금액
    public void printExpectedPayAfterDiscount(int expectedPay) {
        String expectedPayText = createExpectedPayAfterDiscountText(expectedPay);
        outputView.printExpectedPayAfterDiscount(expectedPayText);
    }

    private String createExpectedPayAfterDiscountText(int expectedPayAfterDiscount) {
        return String.format("%s%s",
                Converter.intToLocaleString(expectedPayAfterDiscount),
                Menu.CURRENCY_UNIT);
    }

    // 12월 이벤트 배지
    public void printEventBadge(EventBadge eventBadge) {
        String eventBadgeText = createEventBadgeText(eventBadge);
        outputView.printEventBadge(eventBadgeText);
    }

    private String createEventBadgeText(EventBadge eventBadge) {
        if (eventBadge == EventBadge.NOTHING) {
            return NOTHING;
        }
        return String.format(eventBadge.getName());
    }
}