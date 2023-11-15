package christmas.domain.validator;

import christmas.constant.ErrorMessage;
import christmas.domain.Date;

public class DateValidator {
    private static final int EVENT_YEAR = 2023;
    private static final int EVENT_MONTH = 12;
    private static DateValidator dateValidator;

    private DateValidator() {
    }

    public static DateValidator getInstance() {
        if (dateValidator == null) {
            dateValidator = new DateValidator();
        }
        return dateValidator;
    }

    public void validate(Date date) {
        validateYear(date.getYear());
        validateMonth(date.getMonth());
    }

    private void validateYear(int year) {
        if (year != EVENT_YEAR) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_DAY.getMessage());
        }
    }

    private void validateMonth(int month) {
        if (month != EVENT_MONTH) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_DAY.getMessage());
        }
    }
}