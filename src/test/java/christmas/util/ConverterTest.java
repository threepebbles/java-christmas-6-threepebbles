package christmas.util;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.utils.Converter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ConverterTest {
    @ParameterizedTest
    @CsvSource(value = {"1000:1,000", "135754:135,754", "1421000:1,421,000"}, delimiter = ':')
    void LocaleString_테스트(int amount, String expected) {
        // when
        String actual = Converter.intToLocaleString(amount);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
