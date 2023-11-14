package christmas.view.output;

import christmas.domain.dto.output.DiscountResultDTO;
import christmas.domain.dto.output.DiscountResultsDTO;
import christmas.domain.dto.output.GiftDTO;
import christmas.domain.dto.output.OrderDTO;
import christmas.domain.dto.output.OrdersDTO;
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

    public String createOrdersText(OrdersDTO ordersDTO) {
        StringBuilder sb = new StringBuilder();
        for (OrderDTO orderDTO : ordersDTO.getOrders()) {
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
        if (discountResultsDTO.getDiscountResults().isEmpty()) {
            return NOTHING + LINE_SEPARATOR;
        }
        StringBuilder sb = new StringBuilder();
        for (DiscountResultDTO discountResultDTO : discountResultsDTO.getDiscountResults()) {
            sb.append(String.format("%s: -%s원",
                            discountResultDTO.getEventName(),
                            Converter.intToLocaleString(discountResultDTO.getAmount())))
                    .append(LINE_SEPARATOR);
        }
        return sb.toString();
    }

    public String createTotalDiscountText(int totalDiscount) {
        return String.format("%s원",
                Converter.intToLocaleString(-totalDiscount)) + LINE_SEPARATOR;
    }

    public String createExpectedPriceAfterDiscountText(int expectedPriceAfterDiscount) {
        return String.format("%s원",
                Converter.intToLocaleString(expectedPriceAfterDiscount)) + LINE_SEPARATOR;
    }

    public String createEventBadgeText(String eventBadgeName) {
        if (eventBadgeName.isBlank()) {
            return NOTHING + LINE_SEPARATOR;
        }
        return eventBadgeName + LINE_SEPARATOR;
    }
}