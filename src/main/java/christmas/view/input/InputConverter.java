package christmas.view.input;

import christmas.model.Date;
import christmas.model.Menu;
import christmas.model.Order;
import christmas.model.Orders;
import christmas.utils.Parser;
import java.util.ArrayList;
import java.util.List;

public class InputConverter {
    public static final String COMMA = ",";
    public static final String HYPHEN = "-";

    public Date createDate(String userInput) {
        int day = Integer.parseInt(userInput);
        return new Date(day);
    }

    public Orders createOrder(String userInput) {
        List<Order> orders = convertToOrders(userInput);
        return new Orders(orders);
    }

    private List<Order> convertToOrders(String userInput) {
        List<Order> orders = new ArrayList<>();

        List<String> menuCountFormats = Parser.parseWithDelimiter(userInput, COMMA);
        menuCountFormats.forEach(menuCountFormat -> {
            orders.add(convertToOrder(menuCountFormat));
        });
        return orders;
    }

    private Order convertToOrder(String menuCountFormat) {
        List<String> menuCountBundle = Parser.parseWithDelimiter(menuCountFormat, HYPHEN);
        Menu menu = Menu.findMenuByName(menuCountBundle.get(0));
        int count = Integer.parseInt(menuCountBundle.get(1));
        return new Order(menu, count);
    }
}