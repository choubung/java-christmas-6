package christmas.domain;

import java.util.*;

public class MenuRepository {
    private Map<Menu, Integer> orders = new HashMap<>();
    private int totalAmount = 0;
    private int totalCount = 0;

    public Map<Menu, Integer> getOrders() {
        return orders;
    }

    // 1. 저장
    public void save(Menu menu, int count) {
        validateDuplicate(menu); // 필요 시 중복 검사
        totalAmount += menu.getPrice() * count;
        totalCount += count;
        orders.put(menu, count);
    }

    private void validateDuplicate(Menu menu) {
        List<Menu> oldMenus = new ArrayList<>(orders.keySet());
        if (oldMenus.contains(menu)) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public List<Menu> findAllMenu() {
        return new ArrayList<>(orders.keySet());
    }

    public int getCountByMenu(Menu menu) {
        return orders.get(menu);
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void clear() {
        orders.clear();
    }
}
