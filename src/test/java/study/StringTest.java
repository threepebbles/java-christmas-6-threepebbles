package study;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class StringTest {
    @Test
    void localeString() {
        int amount = 1102020;
        String expected = "1,102,020";
        String actual = String.format("%,d", amount);

        assertThat(actual).isEqualTo(expected);
    }
}
