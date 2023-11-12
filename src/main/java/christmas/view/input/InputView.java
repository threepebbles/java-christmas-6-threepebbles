package christmas.view.input;

import camp.nextstep.edu.missionutils.Console;
import christmas.constant.InputType;
import christmas.domain.Date;
import christmas.domain.Orders;
import java.util.function.Function;

public class InputView {
    private final InputValidatorFinder inputValidatorFinder;
    private final InputConverter inputConverter;

    public InputView(InputValidatorFinder inputValidatorFinder, InputConverter inputConverter) {
        this.inputValidatorFinder = inputValidatorFinder;
        this.inputConverter = inputConverter;
    }

    public Date askDate() {
        return (Date) retryUntilSuccess(
                inputView -> {
                    String day = scanDayOfVisit();
                    inputValidatorFinder.findValidatorByInputType(InputType.DAY_OF_VISIT)
                            .validate(day);
                    return inputConverter.createDate(day);
                });
    }

    private String scanDayOfVisit() {
        System.out.println(InputViewText.ENTER_DAY_OF_VISIT_TEXT);
        return Console.readLine();
    }

    public Orders askOrders() {
        return (Orders) retryUntilSuccess(
                inputView -> {
                    String orders = inputView.scanOrders();
                    inputValidatorFinder.findValidatorByInputType(InputType.ORDERS)
                            .validate(orders);
                    return inputConverter.createOrders(orders);
                });
    }

    private String scanOrders() {
        System.out.println(InputViewText.ENTER_ORDERS_TEXT);
        return Console.readLine();
    }

    private void printMessage(String message) {
        System.out.println(message);
    }

    private Object retryUntilSuccess(Function<InputView, Object> function) {
        while (true) {
            try {
                return function.apply(this);
            } catch (IllegalArgumentException e) {
                printMessage(e.getMessage());
            }
        }
    }
}