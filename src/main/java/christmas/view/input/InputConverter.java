package christmas.view.input;

import christmas.constant.ErrorMessage;
import christmas.model.Date;
import christmas.model.Menu;
import christmas.model.Order;
import christmas.utils.Parser;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputConverter {
    public static final String COMMA = ",";
    public static final String HYPHEN = "-";

    public Date createDate(String userInput) {
        int day = Integer.parseInt(userInput);
        return new Date(day);
    }

    public Order createOrder(String userInput) {
        Map<Menu, Integer> menuCounter = convertToMenuCounter(userInput);
        return new Order(menuCounter);
    }

    public Map<Menu, Integer> convertToMenuCounter(String userInput) {
        Map<Menu, Integer> menuCounter = new HashMap<>();
        List<String> menuCountFormats = Parser.parseWithDelimiter(userInput, COMMA);
        menuCountFormats.forEach(menuCountFormat -> {
            List<String> menuCountBundle = Parser.parseWithDelimiter(menuCountFormat, HYPHEN);
            String menuName = menuCountBundle.get(0);
            int count = Integer.parseInt(menuCountBundle.get(1));
            if (menuCounter.get(Menu.findMenuByName(menuName)) != null) {
                throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_ORDER_FORMAT.getMessage());
            }
            menuCounter.put(Menu.findMenuByName(menuName), count);
        });
        return menuCounter;
    }
}