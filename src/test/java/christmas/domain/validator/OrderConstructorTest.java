package christmas.domain.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.Menu;
import christmas.domain.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class OrderConstructorTest {
    @DisplayName("메뉴 이름이 주문할 수 없는 메뉴인 경우 예외가 발생해야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"펩시", "백숙"})
    void 메뉴_이름_예외_테스트(String menuName) {
        // given
        Menu menu = Menu.findMenuByName(menuName);
        int count = 1;

        // when && then
        assertThatThrownBy(() -> new Order(menu, count))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }


    @DisplayName("개수가 양수가 아닌 경우 예외가 발생해야 한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void 개수_예외_테스트(int count) {
        // given
        Menu menu = Menu.findMenuByName("양송이수프");

        // when && then
        assertThatThrownBy(() -> new Order(menu, count))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}