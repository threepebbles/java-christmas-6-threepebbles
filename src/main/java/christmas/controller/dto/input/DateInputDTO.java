package christmas.controller.dto.input;

import java.time.LocalDate;

public class DateInputDTO {
    private LocalDate localDate;

    public DateInputDTO(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }
}
