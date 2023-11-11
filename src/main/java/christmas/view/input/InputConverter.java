package christmas.view.input;

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
        return new Date(Integer.parseInt(userInput));
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
            menuCounter.put(Menu.findMenuByName(menuName), count);
        });
        return menuCounter;
    }
}