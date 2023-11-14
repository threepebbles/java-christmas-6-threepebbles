package christmas.view.input;

import christmas.constant.ErrorMessage;
import christmas.domain.dto.input.DateInputDTO;
import christmas.domain.dto.input.OrderInputDTO;
import christmas.domain.dto.input.OrdersInputDTO;
import christmas.utils.Parser;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InputConverter {
    private static final String COMMA = ",";
    private static final String HYPHEN = "-";

    public DateInputDTO createDateInputDTO(String userInput) {
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

    public OrdersInputDTO createOrdersInputDTO(String userInput) {
        List<OrderInputDTO> orders = new ArrayList<>();
        List<String> menuCountFormats = Parser.parseWithDelimiter(userInput, COMMA);
        menuCountFormats.forEach(menuCountFormat -> {
            orders.add(convertToOrderInputDTO(menuCountFormat));
        });
        return new OrdersInputDTO(orders);
    }

    private OrderInputDTO convertToOrderInputDTO(String menuCountFormat) {
        List<String> menuCountBundle = Parser.parseWithDelimiter(menuCountFormat, HYPHEN);
        String menuName = menuCountBundle.get(0);
        int count = Integer.parseInt(menuCountBundle.get(1));
        return new OrderInputDTO(menuName, count);
    }
}