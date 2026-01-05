package christmas.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final Pattern PATTERN = Pattern.compile("([가-힣]+)\\-([0-9]+)");

    public static String[] menuParse(String input) {
        Matcher matcher = PATTERN.matcher(input);

        if (matcher.matches()) {
            String menuName = matcher.group(1).trim();
            String count = matcher.group(2).trim();


            Validator.validateIsOrderCount(count);

            return new String[]{menuName, count};
        }

        throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}