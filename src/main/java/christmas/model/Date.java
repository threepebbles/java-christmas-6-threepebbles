package christmas.model;

import christmas.constant.ErrorMessage;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Date {
    private final LocalDate date;

    public Date(int day) {
        validate(day);
        date = LocalDate.of(2023, 12, day);
    }

    public void validate(int day) {
        int FIRST_DAY_OF_DECEMBER = 1;
        int LAST_DAY_OF_DECEMBER = 31;
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