package christmas.controller;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.discount.Discount;
import christmas.view.OutputView;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class OutputController {
    private final OutputView outputView;

    public OutputController(OutputView outputView) {
        this.outputView = outputView;
    }

    public void printEventStatisticsHeader() {
        outputView.printEventStatisticsHeader();
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
    public void printGift(Menu menu) {
        outputView.printGift(createGiftText(menu));
    }

    private String createGiftText(Menu menu) {
        if (menu == null) {
            return "";
        }
        return String.format("%s 1개\n", menu.getName());
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
            return "";
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

    public void printTotalDiscount(int totalDiscount) {
        outputView.printTotalDiscount(createTotalDiscountText(totalDiscount));
    }

    private String createTotalDiscountText(int totalDiscount) {
        DecimalFormat decFormat = new DecimalFormat("###,###");
        return String.format("%s%s\n",
                decFormat.format(-totalDiscount),
                Menu.CURRENCY_UNIT);
    }

    public void printExpectedPayAfterDiscount(int expectedPayAfterDiscount) {
        outputView.printExpectedPayAfterDiscount(createExpectedPayAfterDiscountText(expectedPayAfterDiscount));
    }

    private String createExpectedPayAfterDiscountText(int expectedPayAfterDiscount) {
        DecimalFormat decFormat = new DecimalFormat("###,###");
        return String.format("%s%s\n",
                decFormat.format(expectedPayAfterDiscount),
                Menu.CURRENCY_UNIT);
    }
}