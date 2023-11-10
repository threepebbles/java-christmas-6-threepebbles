package christmas.controller;

import christmas.controller.validator.DayOfVisitValidator;
import christmas.domain.Date;
import christmas.view.InputView;
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

    private Date createDate(String day) {
        return new Date(Integer.parseInt(day));
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