package christmas.view.input;

import christmas.constant.InputType;
import christmas.view.input.validator.DayOfVisitInputValidator;
import christmas.view.input.validator.InputValidator;
import christmas.view.input.validator.OrderInputValidator;
import java.util.HashMap;
import java.util.Map;

public class InputValidatorFinder {
    private Map<InputType, InputValidator> validatorStore;

    public InputValidatorFinder() {
        validatorStore = new HashMap<>() {{
            put(InputType.DAY_OF_VISIT, DayOfVisitInputValidator.getInstance());
            put(InputType.ORDER, OrderInputValidator.getInstance());
        }};
    }

    public InputValidator findValidatorByInputType(InputType inputType) {
        return validatorStore.get(inputType);
    }
}