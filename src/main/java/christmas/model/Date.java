package christmas.model;

import christmas.model.validator.DateValidator;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Date {
    private final LocalDate localDate;

    public Date(LocalDate localDate) {
        this.localDate = localDate;
        DateValidator.getInstance().validate(this);
    }

    public boolean isWeekDay() {
        return !isWeekend();
    }

    public boolean isWeekend() {
        return localDate.getDayOfWeek() == DayOfWeek.FRIDAY || localDate.getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    public int getYear() {
        return localDate.getYear();
    }

    public int getMonth() {
        return localDate.getMonthValue();
    }

    public int getDay() {
        return localDate.getDayOfMonth();
    }

    public LocalDate getLocalDate() {
        return localDate;
    }
}