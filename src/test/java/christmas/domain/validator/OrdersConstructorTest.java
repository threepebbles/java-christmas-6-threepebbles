package christmas.domain.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.Orders;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrdersConstructorTest {
    @DisplayName("메뉴 이름에 중복이 있는 경우 예외가 발생해야 한다.")
    @Test
    void 메뉴_이름_중복_예외_테스트() {
        assertThatThrownBy(() ->
                new Orders(
                        new ArrayList<>() {{
                            add(new Order(Menu.findMenuByName("양송이수프"), 2));
                            add(new Order(Menu.findMenuByName("해산물파스타"), 2));
                            add(new Order(Menu.findMenuByName("아이스크림"), 3));
                            add(new Order(Menu.findMenuByName("해산물파스타"), 1));
                            add(new Order(Menu.findMenuByName("제로콜라"), 5));
                        }}
                ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("전체 주문 개수가 20개가 넘은 경우 예외가 발생해야 한다.")
    @Test
    void 메뉴_개수_예외_테스트() {
        // given
        Menu menu = Menu.findMenuByName("양송이수프");
        int count = 21;

        // when && then
        assertThatThrownBy(() ->
                new Orders(
                        new ArrayList<>() {{
                            add(new Order(Menu.findMenuByName("양송이수프"), 5));
                            add(new Order(Menu.findMenuByName("크리스마스파스타"), 5));
                            add(new Order(Menu.findMenuByName("바비큐립"), 5));
                            add(new Order(Menu.findMenuByName("티본스테이크"), 5));
                            add(new Order(Menu.findMenuByName("제로콜라"), 20));
                        }}
                ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}