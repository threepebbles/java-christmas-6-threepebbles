package christmas.domain.dto.input;

import java.time.LocalDate;

public class DateInputDTO {
    private final LocalDate localDate;

    public DateInputDTO(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }
}
