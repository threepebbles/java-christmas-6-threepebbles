package christmas.view.input;

import christmas.view.input.constant.InputType;
import christmas.view.input.validator.DayOfVisitInputValidator;
import christmas.view.input.validator.InputValidator;
import christmas.view.input.validator.OrderInputValidator;
import java.util.HashMap;
import java.util.Map;

public class InputValidatorFinder {
    private final Map<InputType, InputValidator> validatorStore;

    public InputValidatorFinder() {
        validatorStore = new HashMap<>() {{
            put(InputType.DAY_OF_VISIT, DayOfVisitInputValidator.getInstance());
            put(InputType.ORDERS, OrderInputValidator.getInstance());
        }};
    }

    public InputValidator findValidatorByInputType(InputType inputType) {
        return validatorStore.get(inputType);
    }
}