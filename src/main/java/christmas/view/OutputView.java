package christmas.view;

import christmas.domain.EventBadge;

import java.util.ArrayList;
import java.util.List;
import java.text.NumberFormat;

public class OutputView {
    NumberFormat nf = NumberFormat.getInstance();
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
        System.out.println("<할인 전 총주문 금액>");
        System.out.println("" + nf.format(total) + "원\n");
    }

    public void printBenefits(String present, ArrayList<String[]> benefits, int originalTotal, int discount, EventBadge eventBadge) {
        System.out.println("<증정 메뉴>\n" + present + "\n");

        System.out.println("<혜택 내역>");
        printBenefitsContents(benefits);

        System.out.println("<총혜택 금액>\n" + nf.format(discount) + "원\n");

        System.out.println("<할인 후 예상 결제 금액>\n" + nf.format(originalTotal - discount) + "원\n");

        System.out.println("<12월 이벤트 배지>\n" + eventBadge.getName());
    }

    private void printBenefitsContents(ArrayList<String[]> benefits) {
        if (benefits.isEmpty()) {
            System.out.println("없음");
            return;
        }

        StringBuilder sb = new StringBuilder();

        for (String[] s : benefits) {
            sb.append(s[0]).append(": ").append(nf.format(Integer.parseInt(s[1]))).append("원\n");
        }

        System.out.println(sb);
    }

    // 리스트 출력 예시
    public void printList(List<String> results) {
        results.forEach(System.out::println);
    }
}