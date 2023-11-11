package christmas.view.input;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.view.input.validator.DayOfVisitInputValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DayOfVisitInputValidatorTest {
    private final String ERROR_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    @DisplayName("방문 날짜 정상 입력에 대한 테스트입니다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "11", "31"})
    void 방문_날짜_입력_검증_테스트(String dayOfVisitInput) {
        // when & then
        assertThatCode(
                () -> DayOfVisitInputValidator.getInstance().validate(dayOfVisitInput)).doesNotThrowAnyException();
    }

    @DisplayName("방문 날짜 입력이 정수가 아닌 경우")
    @ParameterizedTest
    @ValueSource(strings = {"첫째날", "이십이일", "둘"})
    void 방문_날짜_입력_검증_예외_테스트_정수가_아닌_꼉우(String dayOfVisitInput) {
        // when & then
        assertThatThrownBy(() -> DayOfVisitInputValidator.getInstance().validate(dayOfVisitInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE);
    }
}
