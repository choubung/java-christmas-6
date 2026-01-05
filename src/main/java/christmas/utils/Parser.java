package christmas.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    // TODO: 문제 형식에 맞게 정규식 수정
    // 예: [콜라,1000,10] -> "\\[([가-힣]+),([0-9]+),([0-9]+)\\]"
    // 예: 1. 커스텀 구분자 -> "//(.)\n(.*)"
    private static final Pattern PATTERN = Pattern.compile("\\[(.*?),(.*?),(.*?)\\]");

    public static String[] parse(String input) {
        Matcher matcher = PATTERN.matcher(input);

        if (matcher.matches()) {
            // 그룹 1부터 시작함에 주의
            String part1 = matcher.group(1).trim();
            String part2 = matcher.group(2).trim();
            String part3 = matcher.group(3).trim();

            // TODO: 비즈니스 로직 검증

            return new String[]{part1, part2, part3};
        }

        throw new IllegalArgumentException("입력 형식이 올바르지 않습니다.");
    }
}