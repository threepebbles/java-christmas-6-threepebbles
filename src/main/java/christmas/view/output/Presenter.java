package christmas.view.output;

import christmas.model.domain.Date;
import christmas.model.domain.EventBadge;
import christmas.model.domain.Gift;
import christmas.model.domain.Menu;
import christmas.model.domain.Order;
import christmas.model.domain.discount.Discount;
import christmas.utils.Converter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class Presenter {
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String NOTHING = "없음";

    public String createDateText(Date date) {
        LocalDate localDate = date.getLocalDate();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M월 d일");
        return localDate.format(dateTimeFormatter);
    }

    public String createOrderText(Order order) {
        StringBuilder sb = new StringBuilder();
        Map<Menu, Integer> menuCounter = order.getMenuCounter();
        menuCounter.forEach((menu, count) -> {
            sb.append(String.format("%s %d개", menu.getName(), count))
                    .append(LINE_SEPARATOR);
        });
        return sb.toString();
    }

    public String createTotalPriceBeforeDiscountText(int amount) {
        return String.format("%s%s",
                Converter.intToLocaleString(amount),
                Menu.CURRENCY_UNIT) + LINE_SEPARATOR;
    }

    public String createGiftText(Gift gift) {
        if (gift == Gift.NOTHING) {
            return NOTHING + LINE_SEPARATOR;
        }
        return String.format("%s 1개", gift.getName()) + LINE_SEPARATOR;
    }

    public String createDiscountDetailsText(List<Discount> discounts) {
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

    public String createTotalDiscountText(int totalDiscount) {
        return String.format("%s%s",
                Converter.intToLocaleString(-totalDiscount),
                Menu.CURRENCY_UNIT) + LINE_SEPARATOR;
    }

    public String createExpectedPayAfterDiscountText(int expectedPayAfterDiscount) {
        return String.format("%s%s",
                Converter.intToLocaleString(expectedPayAfterDiscount),
                Menu.CURRENCY_UNIT) + LINE_SEPARATOR;
    }

    public String createEventBadgeText(EventBadge eventBadge) {
        if (eventBadge == EventBadge.NOTHING) {
            return NOTHING + LINE_SEPARATOR;
        }
        return String.format(eventBadge.getName()) + LINE_SEPARATOR;
    }
}
