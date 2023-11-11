package christmas.model.validator;

import christmas.constant.ErrorMessage;

public class DateValidator {
    private static DateValidator dateValidator;

    private DateValidator() {
    }

    public static DateValidator getInstance() {
        if (dateValidator == null) {
            dateValidator = new DateValidator();
        }
        return dateValidator;
    }

    public void validateDay(int day) {
        final int FIRST_DAY_OF_DECEMBER = 1;
        final int LAST_DAY_OF_DECEMBER = 31;

        if (day < FIRST_DAY_OF_DECEMBER || day > LAST_DAY_OF_DECEMBER) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_DAY_FORMAT.getMessage());
        }
    }
}