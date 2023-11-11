package christmas.view.input.validator;

import christmas.view.input.InputType;
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