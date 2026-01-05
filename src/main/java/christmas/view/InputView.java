package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Menu;
import christmas.utils.Parser;
import christmas.utils.Validator;

import java.util.*;

public class InputView {
    private static final String INTRO_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n" +
            "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String MENU_SELECT_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public static int readAndParseDate() {
        System.out.println(INTRO_MESSAGE);
        String input = Console.readLine();
        Validator.validateIsDate(input);
        return Integer.parseInt(input);
    }

    public static Map<Menu, Integer> readMenu() {
        Map<Menu, Integer> menus = new HashMap<>();

        System.out.println(MENU_SELECT_MESSAGE);
        String input = Console.readLine();

        List<String> orders = Arrays.asList(input.split(","));

        for(String order : orders) {
            String[] str = Parser.menuParse(order);
            menus.put(Menu.findByName(str[0]), Integer.parseInt(str[1]));
        }

        return menus;
    }
}