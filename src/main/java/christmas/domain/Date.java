package christmas.domain;

import christmas.constant.ErrorMessage;

public class Date {
    private final int day;

    public Date(int day) {
        validate(day);
        this.day = day;
    }

    public void validate(int day) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_DAY_FORMAT.getMessage());
        }
    }

    public int getDay() {
        return day;
    }
}
