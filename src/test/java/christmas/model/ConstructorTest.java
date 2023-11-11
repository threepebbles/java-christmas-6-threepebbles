package christmas.model;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.view.input.InputConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ConstructorTest {
    private final String ERROR_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    @ParameterizedTest
    // given
    @ValueSource(ints = {1, 10, 31})
    void 방문_날짜_정상_입력_테스트(int day) {
        // when & then
        assertThatCode(() -> new Date(day))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    // given
    @ValueSource(ints = {-11, 0, 32})
    void 방문_날짜_비정상_입력_테스트(int day) {
        // when & then
        assertThatThrownBy(() -> new Date(day))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE);
    }

    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-4"})
    void 주문_목록_정상_입력_테스트(String input) {
        // when & then
        InputConverter inputConverter = new InputConverter();

        assertThatCode(() -> {
            inputConverter.createOrder(input);
        }).doesNotThrowAnyException();
    }

    @DisplayName("주문 목록에 중복된 메뉴가 있으면 예외가 발생해야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-2,티본스테이크-3"})
    void 주문_목록_중복_메뉴_예외_테스트(String input) {
        // given
        InputConverter inputConverter = new InputConverter();

        // when & then
        assertThatThrownBy(() -> inputConverter.createOrder(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
