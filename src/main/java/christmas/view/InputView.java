package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.utils.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {
    private static final String INTRO_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n" +
            "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";

    public int readAndParseDate() {
        System.out.println(INTRO_MESSAGE);
        String input = Console.readLine();
        Validator.validateIsDate(input);
        return Integer.parseInt(input);
    }

    // 1. 기본 문자열 입력
    public String readString() {
        System.out.println("문자열을 입력해 주세요.");
        String input = Console.readLine();
        Validator.validateHasText(input);
        return input;
    }

    // 2. 숫자 입력 (자동 형변환)
    public int readNumber() {
        System.out.println("숫자를 입력해 주세요.");
        String input = Console.readLine();
        Validator.validateIsNumeric(input);
        return Integer.parseInt(input);
    }

    // 3. 쉼표 구분 입력 (리스트 반환)
    public List<String> readList() {
        System.out.println("쉼표로 구분하여 입력해 주세요.");
        String input = Console.readLine();
        Validator.validateHasText(input);

        return Arrays.stream(input.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}