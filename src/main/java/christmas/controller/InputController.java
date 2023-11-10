package christmas.controller;

import christmas.controller.validator.DayOfVisitInputValidator;
import christmas.controller.validator.OrderInputValidator;
import christmas.domain.Date;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.utils.Parser;
import christmas.view.InputView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class InputController {
    private final InputView inputView;

    public InputController(InputView inputView) {
        this.inputView = inputView;
    }

    public Date askDayOfVisit() {
        return (Date) retryUntilSuccess(inputView,
                inputView -> {
                    String day = inputView.scanDayOfVisit();
                    DayOfVisitInputValidator.getInstance().validate(day);
                    return createDate(day);
                });
    }

    private Date createDate(String userInput) {
        return new Date(Integer.parseInt(userInput));
    }

    public Order askOrder() {
        return (Order) retryUntilSuccess(inputView,
                inputView -> {
                    String order = inputView.scanOrder();
                    OrderInputValidator.getInstance().validate(order);
                    return createOrder(order);
                });
    }

    private Order createOrder(String userInput) {
        Map<Menu, Integer> menuCounter = convertToMenuCounter(userInput);
        return new Order(menuCounter);
    }

    private Map<Menu, Integer> convertToMenuCounter(String userInput) {
        Map<Menu, Integer> menuCounter = new HashMap<>();
        List<String> menuCountFormats = Parser.parseWithDelimiter(userInput, ",");
        menuCountFormats.forEach(menuCountFormat -> {
            List<String> menuCountBundle = Parser.parseWithDelimiter(menuCountFormat, "-");
            String menuName = menuCountBundle.get(0);
            int count = Integer.parseInt(menuCountBundle.get(1));
            menuCounter.put(Menu.findMenuByName(menuName), count);
        });
        return menuCounter;
    }

    public Object retryUntilSuccess(InputView inputView, Function<InputView, Object> function) {
        while (true) {
            try {
                return function.apply(inputView);
            } catch (IllegalArgumentException e) {
                inputView.printMessage(e.getMessage());
            }
        }
    }
}