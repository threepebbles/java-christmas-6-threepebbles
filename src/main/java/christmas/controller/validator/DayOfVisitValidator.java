package christmas.controller.validator;

import christmas.constant.ErrorMessage;

public class DayOfVisitValidator implements InputValidator {
    public static DayOfVisitValidator dayOfVisitValidator;

    private DayOfVisitValidator() {
    }

    public static DayOfVisitValidator getInstance() {
        if (dayOfVisitValidator == null) {
            dayOfVisitValidator = new DayOfVisitValidator();
        }
        return dayOfVisitValidator;
    }

    @Override
    public void validate(String target) {
        validateInteger(target);
    }

    private void validateInteger(String target) {
        try {
            Integer.parseInt(target);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_DAY_FORMAT.getMessage());
        }
    }
}