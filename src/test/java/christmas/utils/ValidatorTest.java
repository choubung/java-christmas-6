package christmas.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ValidatorTest {
    @DisplayName("유효하지 않은 일자 예외 처리")
    @ParameterizedTest(name = "입력값 \"{0}\" 일 때 예외 발생")
    @ValueSource(strings = {"", " ", "0", "32", "냥"})
    void validateIsDate_예외_테스트(String input) {
        assertThatThrownBy(() -> { Validator.validateIsDate(input); })
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("정상 일자 시 예외가 발생하지 않는다")
    @ParameterizedTest(name = "입력값 \"{0}\" 통과")
    @ValueSource(strings = {"1", "15", "31"})
    void validateIsDateTest(String input) {
        assertThatCode(() -> { Validator.validateIsDate(input); })
                .doesNotThrowAnyException();
    }
}
