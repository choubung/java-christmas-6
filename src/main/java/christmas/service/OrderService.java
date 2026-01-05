package christmas.service;

import christmas.domain.Menu;
import christmas.domain.MenuRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderService {
    private final MenuRepository menuRepository;
    private int day;
    private int[] weekend = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30};
    private int[] specialDiscountDate = {3, 10, 17, 24, 31};

    public OrderService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public void saveOrders(Map<Menu, Integer> map) {
        for (Menu menu : map.keySet()) {
            menuRepository.save(menu, map.get(menu));
        }
    }

    // 주문 메뉴 (메뉴와 수량 반환)
    public ArrayList<String[]> getMenuAndCnt() {
        ArrayList<String[]> menus = new ArrayList<>();
        List<Menu> orderedMenu = menuRepository.findAllMenu();

        for (Menu menu : orderedMenu) {
            int count = menuRepository.getCountByMenu(menu);

            menus.add(new String[]{menu.getName(), String.valueOf(count)});
        }

        return menus;
    }

    // 할인 전 총 주문 금액 반환
    public int getTotal() {
        return menuRepository.getTotal();
    }

    // TODO: 증정 메뉴
    // TODO: 혜택 내역
    // TODO: 총 혜택 금액
    // TODO: 할인 후 결제 금액
    // TODO: 이벤트 뱃지

    public void setDay(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }
}
