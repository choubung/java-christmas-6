package christmas.domain;

import java.util.*;

public class MenuRepository {
    private Map<Menu, Integer> orders = new HashMap<>();
    private int total = 0;

    public Map<Menu, Integer> getOrders() {
        return orders;
    }

    // 1. 저장
    public void save(Menu menu, int count) {
        validateDuplicate(menu); // 필요 시 중복 검사
        total += menu.getPrice() * count;
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

    public int getTotal() {
        return total;
    }

    //
//    // 2. 단건 조회 (Optional 추천)
//    public Optional<Menu> findByName(String name) {
//        return Optional.ofNullable(orders.get(name));
//    }
//
//    // 3. 전체 조회 (불변 리스트 반환 추천)
//    public List<Menu> findAll() {
//        return Collections.unmodifiableList(new ArrayList<>(storage.values()));
//    }
//
//    // 4. 삭제 (필요 시)
//    public void deleteByName(String name) {
//        storage.remove(name);
//    }
//
//    // 5. 초기화 (테스트 시 필수!)
//    public void clear() {
//        storage.clear();
//    }
}
