package christmas.view.input;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.dto.input.DateInputDTO;
import christmas.controller.dto.input.OrdersInputDTO;
import christmas.view.input.constant.InputType;
import christmas.view.input.constant.InputViewText;
import java.util.function.Function;

public class InputView {
    private final InputValidatorFinder inputValidatorFinder;
    private final InputConverter inputConverter;

    public InputView(InputValidatorFinder inputValidatorFinder, InputConverter inputConverter) {
        this.inputValidatorFinder = inputValidatorFinder;
        this.inputConverter = inputConverter;
    }

    public DateInputDTO askDateDTO() {
        return (DateInputDTO) retryUntilSuccess(
                inputView -> {
                    String day = scanDayOfVisit();
                    inputValidatorFinder.findValidatorByInputType(InputType.DAY_OF_VISIT)
                            .validate(day);
                    return inputConverter.createDateDTO(day);
                });
    }

    private String scanDayOfVisit() {
        System.out.println(InputViewText.ENTER_DAY_OF_VISIT_TEXT);
        return Console.readLine();
    }

    public OrdersInputDTO askOrdersDTO() {
        return (OrdersInputDTO) retryUntilSuccess(
                inputView -> {
                    String orders = inputView.scanOrders();
                    inputValidatorFinder.findValidatorByInputType(InputType.ORDERS)
                            .validate(orders);
                    return inputConverter.createOrdersDTO(orders);
                });
    }

    private String scanOrders() {
        System.out.println(InputViewText.ENTER_ORDERS_TEXT);
        return Console.readLine();
    }

    public void printMessage(String message) {
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