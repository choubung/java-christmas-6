package christmas.utils;

import christmas.domain.Menu;

import java.util.*;
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
}