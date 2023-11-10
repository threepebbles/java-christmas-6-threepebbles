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
    private static final String NOTHING = "없음";
    private final OutputView outputView;

    public OutputController(OutputView outputView) {
        this.outputView = outputView;
    }

    // 헤더
    public void printEventStatisticsHeader(Date date) {
        outputView.printEventStatisticsHeader(createDateText(date));
    }

    private String createDateText(Date date) {
        LocalDate localDate = date.getDate();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M월 d일");
        return localDate.format(dateTimeFormatter);
    }

    // 주문 메뉴
    public void printOrder(Order order) {
        outputView.printOrder(createOrderText(order));
    }

    public String createOrderText(Order order) {
        StringBuilder sb = new StringBuilder();
        Map<Menu, Integer> menuCounter = order.getMenuCounter();
        menuCounter.forEach((menu, count) -> {
            sb.append(String.format("%s %d개\n", menu.getName(), count));
        });
        return sb.toString();
    }

    // 증정 메뉴
    public void printGift(Gift gift) {
        outputView.printGift(createGiftText(gift));
    }

    private String createGiftText(Gift gift) {
        if (gift == Gift.NOTHING) {
            return NOTHING;
        }
        return String.format("%s 1개\n", gift.getName());
    }

    // 할인 전 총 주문 금액
    public void printTotalPriceBeforeDiscount(int amount) {
        outputView.printTotalPriceBeforeDiscount(createTotalPriceBeforeDiscountText(amount));
    }

    public String createTotalPriceBeforeDiscountText(int amount) {
        DecimalFormat decFormat = new DecimalFormat("###,###");
        return String.format("%s%s\n",
                decFormat.format(amount),
                Menu.CURRENCY_UNIT);
    }

    // 혜택 내역
    public void printDiscountDetails(List<Discount> discounts) {
        outputView.printDiscountDetails(createDiscountDetailsText(discounts));
    }

    private String createDiscountDetailsText(List<Discount> discounts) {
        if (discounts.isEmpty()) {
            return NOTHING;
        }
        DecimalFormat decFormat = new DecimalFormat("###,###");
        StringBuilder sb = new StringBuilder();
        discounts.forEach(discount -> {
            sb.append(String.format("%s: -%s%s\n",
                    discount.getName(),
                    decFormat.format(discount.getAmount()),
                    Menu.CURRENCY_UNIT));
        });
        return sb.toString();
    }

    // 총혜택 금액
    public void printTotalDiscount(int totalDiscount) {
        outputView.printTotalDiscount(createTotalDiscountText(totalDiscount));
    }

    private String createTotalDiscountText(int totalDiscount) {
        DecimalFormat decFormat = new DecimalFormat("###,###");
        return String.format("%s%s\n",
                decFormat.format(-totalDiscount),
                Menu.CURRENCY_UNIT);
    }

    // 할인 후 예상 결제 금액
    public void printExpectedPayAfterDiscount(int expectedPayAfterDiscount) {
        outputView.printExpectedPayAfterDiscount(createExpectedPayAfterDiscountText(expectedPayAfterDiscount));
    }

    private String createExpectedPayAfterDiscountText(int expectedPayAfterDiscount) {
        DecimalFormat decFormat = new DecimalFormat("###,###");
        return String.format("%s%s\n",
                decFormat.format(expectedPayAfterDiscount),
                Menu.CURRENCY_UNIT);
    }

    // 12월 이벤트 배지
    public void printEventBadge(EventBadge eventBadge) {
        outputView.printEventBadge(createEventBadgeText(eventBadge));
    }

    private String createEventBadgeText(EventBadge eventBadge) {
        if (eventBadge == EventBadge.NOTHING) {
            return NOTHING;
        }
        return String.format(eventBadge.getName());
    }
}