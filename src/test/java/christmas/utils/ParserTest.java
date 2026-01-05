package christmas.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ParserTest {
    @DisplayName("유효하지 않은 형식의 주문 예외 처리")
    @ParameterizedTest(name = "입력값 \"{0}\" 일 때 예외 발생")
    @ValueSource(strings = {"", " ", "3", "제로콜라-a"})
    void menuParse_예외_테스트(String input) {
        assertThatThrownBy(() -> { Parser.menuParse(input); })
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("유효한 형식의 경우 예외가 발생하지 않는다")
    @ParameterizedTest(name = "입력값 \"{0}\" 통과")
    @ValueSource(strings = {"타파스-1"})
    void validateIsDateTest(String input) {
        assertThatCode(() -> { Parser.menuParse(input); })
                .doesNotThrowAnyException();
    }
}
