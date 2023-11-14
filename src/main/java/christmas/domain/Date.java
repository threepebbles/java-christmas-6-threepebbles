package christmas.domain;

import christmas.domain.dto.input.DateInputDTO;
import christmas.domain.validator.DateValidator;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Date {
    private final LocalDate localDate;

    public Date(LocalDate localDate) {
        this.localDate = localDate;
        DateValidator.getInstance().validate(this);
    }

    public static Date createDate(DateInputDTO dateInputDTO) {
        return new Date(dateInputDTO.getLocalDate());
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