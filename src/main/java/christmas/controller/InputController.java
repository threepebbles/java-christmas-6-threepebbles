package christmas.controller;

import christmas.controller.validator.DayOfVisitValidator;
import christmas.controller.validator.OrderValidator;
import christmas.domain.Date;
import christmas.domain.Order;
import christmas.utils.Parser;
import christmas.view.InputView;
import java.util.List;
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
                    DayOfVisitValidator.getInstance().validate(day);
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
                    OrderValidator.getInstance().validate(order);
                    return createOrder(order);
                });
    }

    private Order createOrder(String userInput) {
        Order order = new Order();
        List<String> menuCountFormats = Parser.parseWithDelimiter(userInput, ",");
        menuCountFormats.forEach(menuCountFormat -> {
            List<String> menuCountBundle = Parser.parseWithDelimiter(menuCountFormat, "-");
            String menuName = menuCountBundle.get(0);
            int count = Integer.parseInt(menuCountBundle.get(1));
            order.addMenu(menuName, count);
        });
        return order;
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