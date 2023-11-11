package christmas.model.domain;

import christmas.constant.ErrorMessage;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Date {
    private final LocalDate date;

    public Date(int day) {
        validate(day);
        int CURRENT_YEAR = 2023;
        int CURRENT_MONTH = 12;
        date = LocalDate.of(CURRENT_YEAR, CURRENT_MONTH, day);
    }

    public void validate(int day) {
        int LAST_DAY_OF_DECEMBER = 31;
        int FIRST_DAY_OF_DECEMBER = 1;
        if (day < FIRST_DAY_OF_DECEMBER || day > LAST_DAY_OF_DECEMBER) {
            throw new IllegalArgumentException(ErrorMessage.NOT_PROPER_DAY_FORMAT.getMessage());
        }
    }

    public boolean isWeekDay() {
        return !isWeekend();
    }

    public boolean isWeekend() {
        return date.getDayOfWeek() == DayOfWeek.FRIDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    public int getDay() {
        return date.getDayOfMonth();
    }

    public LocalDate getDate() {
        return date;
    }
}