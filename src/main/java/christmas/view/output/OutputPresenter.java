package christmas.view.output;

import christmas.constant.Menu;
import christmas.controller.dto.DiscountResultsDTO;
import christmas.controller.dto.GiftDTO;
import christmas.controller.dto.OrderDTO;
import christmas.controller.dto.OrdersDTO;
import christmas.utils.Converter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OutputPresenter {
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String NOTHING = "없음";

    public String createDateText(LocalDate localDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M월 d일");
        return localDate.format(dateTimeFormatter);
    }

    public String createOrderText(OrdersDTO ordersDTO) {
        StringBuilder sb = new StringBuilder();
        for (OrderDTO orderDTO : ordersDTO) {
            sb.append(String.format("%s %d개", orderDTO.getMenuName(), orderDTO.getCount()))
                    .append(LINE_SEPARATOR);
        }
        return sb.toString();
    }

    public String createTotalPriceBeforeDiscountText(int amount) {
        return String.format("%s원",
                Converter.intToLocaleString(amount)) + LINE_SEPARATOR;
    }

    public String createGiftText(GiftDTO giftDTO) {
        if (giftDTO.getGiftName().isBlank()) {
            return NOTHING + LINE_SEPARATOR;
        }
        return String.format("%s %d개", giftDTO.getGiftName(), giftDTO.getCount()) + LINE_SEPARATOR;
    }

    public String createDiscountResultsText(DiscountResultsDTO discountResultsDTO) {
        if (discountResultsDTO.isEmpty()) {
            return NOTHING + LINE_SEPARATOR;
        }
        StringBuilder sb = new StringBuilder();
        discountResultsDTO.forEach(discountResultDTO ->
                sb.append(String.format("%s: -%s원",
                                discountResultDTO.getEventName(),
                                Converter.intToLocaleString(discountResultDTO.getAmount())))
                        .append(LINE_SEPARATOR));
        return sb.toString();
    }

    public String createTotalDiscountText(int totalDiscount) {
        return String.format("%s원",
                Converter.intToLocaleString(-totalDiscount)) + LINE_SEPARATOR;
    }

    public String createExpectedAmountAfterDiscountText(int expectedAmountAfterDiscount) {
        return String.format("%s%s",
                Converter.intToLocaleString(expectedAmountAfterDiscount),
                Menu.CURRENCY_UNIT) + LINE_SEPARATOR;
    }

    public String createEventBadgeText(String eventBadgeName) {
        if (eventBadgeName.isBlank()) {
            return NOTHING + LINE_SEPARATOR;
        }
        return eventBadgeName + LINE_SEPARATOR;
    }
}