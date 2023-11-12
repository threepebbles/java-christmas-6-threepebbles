package christmas.view.input;

import christmas.constant.ErrorMessage;
import christmas.constant.Menu;
import christmas.domain.Date;
import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.utils.Parser;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InputConverter {
    public static final String COMMA = ",";
    public static final String HYPHEN = "-";

    public Date createDate(String userInput) {
        LocalDate localDate = convertToLocalDate(userInput);
        return new Date(localDate);
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

    public Orders createOrders(String userInput) {
        List<Order> orders = new ArrayList<>();
        List<String> menuCountFormats = Parser.parseWithDelimiter(userInput, COMMA);
        menuCountFormats.forEach(menuCountFormat -> {
            orders.add(convertToOrder(menuCountFormat));
        });
        return new Orders(orders);
    }

    private Order convertToOrder(String menuCountFormat) {
        List<String> menuCountBundle = Parser.parseWithDelimiter(menuCountFormat, HYPHEN);
        Menu menu = Menu.findMenuByName(menuCountBundle.get(0));
        int count = Integer.parseInt(menuCountBundle.get(1));
        return new Order(menu, count);
    }
}