package christmas.view.input;

import christmas.constant.ErrorMessage;
import christmas.controller.dto.input.DateInputDTO;
import christmas.controller.dto.input.OrderInputDTO;
import christmas.controller.dto.input.OrdersInputDTO;
import christmas.utils.Parser;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InputConverter {
    public static final String COMMA = ",";
    public static final String HYPHEN = "-";

    public DateInputDTO createDateDTO(String userInput) {
        LocalDate localDate = convertToLocalDate(userInput);
        return new DateInputDTO(localDate);
    }

    private LocalDate convertToLocalDate(String day) {
        try {
            final int CURRENT_YEAR = 2023;
            final int CURRENT_MONTH = 12;
            return LocalDate.of(CURRENT_YEAR, CURRENT_MONTH, Integer.parseInt(day));
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_DAY.getMessage());
        }
    }

    public OrdersInputDTO createOrdersDTO(String userInput) {
        List<OrderInputDTO> orders = new ArrayList<>();
        List<String> menuCountFormats = Parser.parseWithDelimiter(userInput, COMMA);
        menuCountFormats.forEach(menuCountFormat -> {
            orders.add(convertToOrderDTO(menuCountFormat));
        });
        return new OrdersInputDTO(orders);
    }

    private OrderInputDTO convertToOrderDTO(String menuCountFormat) {
        List<String> menuCountBundle = Parser.parseWithDelimiter(menuCountFormat, HYPHEN);
        String menuName = menuCountBundle.get(0);
        int count = Integer.parseInt(menuCountBundle.get(1));
        return new OrderInputDTO(menuName, count);
    }
}