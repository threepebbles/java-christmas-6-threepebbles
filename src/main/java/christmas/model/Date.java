package christmas.model;

import christmas.model.validator.DateValidator;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Date {
    private final LocalDate localDate;

    public Date(int day) {
        DateValidator.getInstance().validateDay(day);

        final int CURRENT_YEAR = 2023;
        final int CURRENT_MONTH = 12;
        localDate = LocalDate.of(CURRENT_YEAR, CURRENT_MONTH, day);
    }

    public boolean isWeekDay() {
        return !isWeekend();
    }

    public boolean isWeekend() {
        return localDate.getDayOfWeek() == DayOfWeek.FRIDAY || localDate.getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    public int getDay() {
        return localDate.getDayOfMonth();
    }

    public LocalDate getLocalDate() {
        return localDate;
    }
}