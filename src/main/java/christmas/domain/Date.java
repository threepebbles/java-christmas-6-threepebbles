package christmas.domain;

import christmas.constant.ErrorMessage;

public class Date {
    private final int day;

    public Date(int day) {
        validate(day);
        this.day = day;
    }

    public void validate(int day) {
        int FIRST_DAY_OF_DECEMBER = 1;
        int LAST_DAY_OF_DECEMBER = 31;
        if (day < FIRST_DAY_OF_DECEMBER || day > LAST_DAY_OF_DECEMBER) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_DAY_FORMAT.getMessage());
        }
    }

    public int getDay() {
        return day;
    }
}
