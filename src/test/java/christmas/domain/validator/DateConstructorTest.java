package christmas.domain.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.Date;
import java.time.LocalDate;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DateConstructorTest {
    @ParameterizedTest
    @ValueSource(ints = {2021, 2022, 2024, 2025})
    void 연도_예외_테스트(int year) {
        assertThatThrownBy(() -> new Date(LocalDate.of(year, 12, 1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11})
    void 달_예외_테스트(int month) {
        assertThatThrownBy(() -> new Date(LocalDate.of(2023, month, 1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }
}