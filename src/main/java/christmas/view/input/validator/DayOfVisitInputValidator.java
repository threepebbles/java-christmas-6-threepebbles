package christmas.view.input.validator;

import christmas.constant.ErrorMessage;

public class DayOfVisitInputValidator implements InputValidator {
    public static DayOfVisitInputValidator dayOfVisitInputValidator;

    private DayOfVisitInputValidator() {
    }

    public static DayOfVisitInputValidator getInstance() {
        if (dayOfVisitInputValidator == null) {
            dayOfVisitInputValidator = new DayOfVisitInputValidator();
        }
        return dayOfVisitInputValidator;
    }

    @Override
    public void validate(String target) {
        validateBlank(target);
        validateInteger(target);
    }

    private void validateBlank(String target) {
        if (target.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_DAY.getMessage());
        }
    }

    private void validateInteger(String target) {
        try {
            Integer.parseInt(target);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_DAY.getMessage());
        }
    }
}