package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EventBadgeTest {

    @DisplayName("총혜택 금액이 음수가 입력된 경우 배지를 부여하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {-1000, -1})
    void 음수_테스트(int totalDiscount) {
        // when & then
        assertThat(EventBadge.getEventBadgeByTotalDiscount(totalDiscount))
                .isEqualTo(EventBadge.NOTHING);
    }

    @DisplayName("배지를 부여 하지 않는 경우")
    @ParameterizedTest
    @ValueSource(ints = {0, 1000, 2023, 4999})
    void 배지를_부여하지_않는_경우_테스트(int totalDiscount) {
        // when & then
        assertThat(EventBadge.getEventBadgeByTotalDiscount(totalDiscount))
                .isEqualTo(EventBadge.NOTHING);
    }

    @DisplayName("별 배지를 부여하는 경우")
    @ParameterizedTest
    @ValueSource(ints = {5000, 9999})
    void 별_배지_부여하는_경우_테스트(int totalDiscount) {
        // when & then
        assertThat(EventBadge.getEventBadgeByTotalDiscount(totalDiscount))
                .isEqualTo(EventBadge.STAR);
    }

    @DisplayName("트리 배지를 부여하는 경우")
    @ParameterizedTest
    @ValueSource(ints = {10000, 19999})
    void 트리_배지_부여하는_경우_테스트(int totalDiscount) {
        // when & then
        assertThat(EventBadge.getEventBadgeByTotalDiscount(totalDiscount))
                .isEqualTo(EventBadge.TREE);
    }

    @DisplayName("산타 배지를 부여하는 경우")
    @ParameterizedTest
    @ValueSource(ints = {20000, 30000})
    void 산타_배지_부여하는_경우_테스트(int totalDiscount) {
        // when & then
        assertThat(EventBadge.getEventBadgeByTotalDiscount(totalDiscount))
                .isEqualTo(EventBadge.SANTA);
    }
}