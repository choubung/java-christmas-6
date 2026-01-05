package christmas.utils;

import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Validator {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]*$");

    public static void validateIsDate(String input) {
        try {
            int day = Integer.parseInt(input);

            if (day < 1 || day > 31) {
                throw new RuntimeException();
            }

        } catch (Exception e) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public static void validateIsOrderCount(String input) {
        try {
            int cnt = Integer.parseInt(input);

            if (cnt < 1) {
                throw new RuntimeException();
            }

        } catch (Exception e) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public static <T> void validateDuplicateMenu(List<T> items) {
        Set<T> uniqueItems = new HashSet<>(items);
        if (uniqueItems.size() != items.size()) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    // 1. 빈 문자열 체크
    public static void validateHasText(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("입력값이 비어있습니다.");
        }
    }

    // 2-1. 숫자 여부 체크 (정규식 활용)
    public static void validateIsNumeric(String input) {
        if (!NUMBER_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException("숫자만 입력해야 합니다.");
        }
    }

    // 2-2. 숫자 변환 + 예외 처리 통합
    public static int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다.");
        }
    }

    // 4. 리스트 크기(길이) 체크
    public static <T> void validateSize(List<T> items, int expectedSize) {
        if (items.size() != expectedSize) {
            throw new IllegalArgumentException("입력 개수가 올바르지 않습니다.");
        }
    }

    // 5. 범위 체크
    public static void validateRange(int number, int min, int max) {
        if (number < min || number > max) {
            throw new IllegalArgumentException("입력 값이 범위를 벗어났습니다.");
        }
    }



}