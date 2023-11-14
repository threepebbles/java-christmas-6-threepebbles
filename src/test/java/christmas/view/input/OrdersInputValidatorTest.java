package christmas.view.input;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.view.input.constant.InputType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class OrdersInputValidatorTest {
    private final String ORDER_INPUT_ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private InputValidatorFinder inputValidatorFinder;

    @BeforeEach
    void init() {
        inputValidatorFinder = new InputValidatorFinder();
    }

    @DisplayName("주문 목록 정상 입력에 대한 테스트입니다.")
    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-2,레드와인-1,초코케이크-3", "타파스-1,제로콜라-1", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1"})
    void 주문_목록_입력_검증_테스트(String orderInput) {
        // when & then
        assertThatCode(() ->
                inputValidatorFinder.findValidatorByInputType(InputType.ORDERS)
                        .validate(orderInput)).doesNotThrowAnyException();
    }

    @DisplayName("{주문 메뉴}-{개수} 포맷에 맞지 않으면 예외가 발생해야 합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"바비큐립:2,레드와인:2", "양송이수프-4,제로콜라:3"})
    void 주문_목록_입력_검증_예외_테스트_올바르지_않은_주문_포맷(String orderInput) {
        // when & then
        assertThatThrownBy(() ->
                inputValidatorFinder.findValidatorByInputType(InputType.ORDERS)
                        .validate(orderInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ORDER_INPUT_ERROR_MESSAGE);
    }

    @DisplayName("주문 개수가 정수가 아니면 예외가 발생해야 합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"바비큐립-두개,레드와인-두개", "양송이수프-셋,제로콜라-둘"})
    void 주문_목록_입력_검증_예외_테스트_주문_개수가_정수가_아닌_경우(String orderInput) {
        // when & then
        assertThatThrownBy(() ->
                inputValidatorFinder.findValidatorByInputType(InputType.ORDERS)
                        .validate(orderInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ORDER_INPUT_ERROR_MESSAGE);
    }

    @DisplayName("메뉴 이름이 공백이면 예외가 발생해야 합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"-1,레드와인-2", "양송이수프-3,-3"})
    void 메뉴_이름_공백_예외_테스트(String orderInput) {
        // when & then
        assertThatThrownBy(() ->
                inputValidatorFinder.findValidatorByInputType(InputType.ORDERS)
                        .validate(orderInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ORDER_INPUT_ERROR_MESSAGE);
    }

    @DisplayName("주문 개수가 공백이면 예외가 발생해야 합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프-,레드와인-2", "레드와인-3,양송이수프-"})
    void 주문_개수_공백_예외_테스트(String orderInput) {
        // when & then
        assertThatThrownBy(() ->
                inputValidatorFinder.findValidatorByInputType(InputType.ORDERS)
                        .validate(orderInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ORDER_INPUT_ERROR_MESSAGE);
    }
}
