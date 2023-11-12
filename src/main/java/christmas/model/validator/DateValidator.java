package christmas.model.validator;

import christmas.constant.ErrorMessage;
import christmas.constant.EventConstant;
import christmas.model.Date;

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

    public void validate(Date date) {
        validateYear(date.getYear());
        validateMonth(date.getMonth());
    }

    private void validateYear(int year) {
        if (year != EventConstant.EVENT_YEAR) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_DAY.getMessage());
        }
    }

    private void validateMonth(int month) {
        if (month != EventConstant.EVENT_MONTH) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_DAY.getMessage());
        }
    }
}
