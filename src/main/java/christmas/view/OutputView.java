package christmas.view;

import christmas.domain.EventBadge;

import java.util.ArrayList;
import java.util.List;
import java.text.NumberFormat;

public class OutputView {
    private static final String ERROR_PREFIX = "[ERROR] ";

    public void printError(String message) {
        System.out.println(ERROR_PREFIX + message);
    }

    public void printOrders(int day, ArrayList<String[]> orders) {
        StringBuilder sb;

        System.out.println("12월 " + day + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");
        System.out.println("<주문 메뉴>");

        for (String[] strings : orders) {
            sb = new StringBuilder();
            sb.append(strings[0]).append(" ").append(strings[1]).append("개");
            System.out.println(sb);
        }

        System.out.println();
    }

    public void printTotalMoney(int total) {
        NumberFormat nf = NumberFormat.getInstance();

        System.out.println("<할인 전 총주문 금액>");
        System.out.println("" + nf.format(total) + "원");
    }

    public void printBenefits(Boolean cham, ArrayList<String[]> benefits, int originalTotal, EventBadge eventBadge) {

    }

    // 리스트 출력 예시
    public void printList(List<String> results) {
        results.forEach(System.out::println);
    }
}