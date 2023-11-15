package christmas.controller;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.Date;
import christmas.domain.Orders;
import christmas.view.input.InputConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class UserInputToModelTest {
    private final String ERROR_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    InputConverter inputConverter;

    @BeforeEach
    void init() {
        inputConverter = new InputConverter();
    }

    @DisplayName("방문 날짜 정상 입력에 대한 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"1", "10", "31"})
    void 방문_날짜_정상_입력_테스트(String day) {
        // when & then
        assertThatCode(() -> Date.createDate(inputConverter.createDateInputDTO(day)))
                .doesNotThrowAnyException();
    }

    @DisplayName("정수지만 날짜의 범위를 벗어난 경우 예외가 발생해야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"-11", "0", "32"})
    void 방문_날짜_비정상_입력_테스트(String day) {
        // when & then
        assertThatThrownBy(() -> Date.createDate(inputConverter.createDateInputDTO(day)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE);
    }

    @DisplayName("주문 목록 정상 입력 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-4"})
    void 주문_목록_정상_입력_테스트(String input) {
        // when & then
        InputConverter inputConverter = new InputConverter();

        assertThatCode(() -> {
            Orders.createOrders(inputConverter.createOrdersInputDTO(input));
        }).doesNotThrowAnyException();
    }

    @DisplayName("주문 목록에 중복된 메뉴가 있으면 예외가 발생해야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-2,티본스테이크-3"})
    void 주문_목록_중복_메뉴_예외_테스트(String input) {
        // given
        InputConverter inputConverter = new InputConverter();

        // when & then
        assertThatThrownBy(() -> Orders.createOrders(inputConverter.createOrdersInputDTO(input)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문개수가 양수가 아니면 예외가 발생해야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"멀티비타민-0,수학의정석-1,홍삼녹용-5"})
    void 주문_개수_양수_아닌_경우_예외_테스트(String input) {
        // given
        InputConverter inputConverter = new InputConverter();

        // when & then
        assertThatThrownBy(() -> Orders.createOrders(inputConverter.createOrdersInputDTO(input)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("음료만 주문한 경우 예외가 발생해야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"제로콜라-20", "레드와인-1,제로콜라-10,샴페인-2"})
    void 음료만_주문한_경우_예외_테스트(String input) {
        // given
        InputConverter inputConverter = new InputConverter();

        // when & then
        assertThatThrownBy(() -> Orders.createOrders(inputConverter.createOrdersInputDTO(input)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴판에 있는 메뉴가 아니면 예외가 발생해야 한다. 공백도 포함하여 이름으로 취급한다.")
    @ParameterizedTest
    @ValueSource(strings = {"멀티비타민-3,수학의정석-4,홍삼녹용-5", "초코 케이크-3", " 초코케이크-3"})
    void 주문_목록_없는_메뉴_예외_테스트(String input) {
        // given
        InputConverter inputConverter = new InputConverter();

        // when & then
        assertThatThrownBy(() -> Orders.createOrders(inputConverter.createOrdersInputDTO(input)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문한 메뉴 개수의 합이 20개 초과면 예외가 발생해야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-21", "양송이수프-6,티본스테이크-6,아이스크림-6,제로콜라-3"})
    void 주문_개수_20초과인_경우_예외_테스트(String input) {
        // given
        InputConverter inputConverter = new InputConverter();

        // when & then
        assertThatThrownBy(() -> Orders.createOrders(inputConverter.createOrdersInputDTO(input)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}