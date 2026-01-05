package christmas.service;

import christmas.domain.Category;
import christmas.domain.EventBadge;
import christmas.domain.Menu;
import christmas.domain.MenuRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class OrderService {
    private final MenuRepository menuRepository;
    private int day;
    private ArrayList<Integer> weekend = new ArrayList<>(Arrays.asList(1, 2, 8, 9, 15, 16, 22, 23, 29, 30));
    private ArrayList<Integer> specialDiscountDate = new ArrayList<>(Arrays.asList(3, 10, 17, 24, 31));
    private int totalDiscountAmount = 0;

    public OrderService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public void saveOrders(Map<Menu, Integer> map) {
        boolean isOnlyDrink = true;

        for (Menu menu : map.keySet()) {
            if (!menu.getCategory().equals(Category.DESSERT)) {
                isOnlyDrink = false;
            }

            menuRepository.save(menu, map.get(menu));
        }

        validateOrder(isOnlyDrink);
    }

    private void validateOrder(Boolean isOnlyDrink) {
        if (isOnlyDrink) {
            throw new IllegalArgumentException("음료만 주문 시, 주문할 수 없습니다.");
        }

        if (menuRepository.getTotalCount() > 20) {
            throw new IllegalArgumentException("메뉴는 한 번에 최대 20개까지만 주문 가능합니다.");
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
        return menuRepository.getTotalAmount();
    }

    // 증정 메뉴
    public String isPresent() {
        if (getTotal() >= 120000) {
            return "샴페인 1개";
        }

        return "없음";
    }

    // 혜택 내역
    public ArrayList<String[]> getBenefits() {
        ArrayList<String[]> benefits = new ArrayList<>();
        int amount = 0;

        if (getTotal() >= 120000) {
            amount -= 25000;
            totalDiscountAmount -= 25000;
        }

        benefits.add(new String[]{"크리스마스 디데이 할인", String.valueOf(christmasDiscount())});
        benefits.add(dateDiscount());
        benefits.add(new String[]{"특별 할인", String.valueOf(specialDiscount())});
        benefits.add(new String[]{"증정 이벤트", String.valueOf(amount)});

        return benefits;
    }

    // 크리스마스 디데이 할인
    private int christmasDiscount() {
        int discount = 0;

        if (this.day < 26) {
            discount -= 1000 + (this.day-1) * 100;
            totalDiscountAmount += discount;
        }

        return discount;
    }

    // 평일 또는 주말 할인
    public String[] dateDiscount() {
        if (weekend.contains(this.day)) {
            return weekendDiscount();
        }

        return weekdaysDiscount();
    }

    private String[] weekdaysDiscount() {
        Map<Menu, Integer> map = menuRepository.getOrders();
        int discountAmount = 0;

        for (Menu menu : menuRepository.findAllMenu()) {
            if (menu.getCategory().equals(Category.MAIN)) {
                discountAmount -= 2023 * map.get(menu);
            }
        }

        totalDiscountAmount += discountAmount;
        return new String[]{"평일 할인", String.valueOf(discountAmount)};
    }

    private String[] weekendDiscount() {
        int discountAmount = 0;
        Map<Menu, Integer> map = menuRepository.getOrders();

        for (Menu menu : menuRepository.findAllMenu()) {
            if (menu.getCategory().equals(Category.MAIN)) {
                discountAmount -= 2023 * map.get(menu);
            }
        }

        totalDiscountAmount += discountAmount;
        return new String[]{"주말 할인", String.valueOf(discountAmount)};
    }

    // 특별 할인
    public int specialDiscount() {
        if (specialDiscountDate.contains(day)) {
            totalDiscountAmount -= 1000;
            return -1000;
        }

        return 0;
    }

    // 총 혜택 금액
    public int getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    // 이벤트 뱃지
    public EventBadge getEventBadge() {
        int totalDiscountAmountForCal = totalDiscountAmount * -1;
        if (totalDiscountAmountForCal >= 5000 && totalDiscountAmountForCal < 10000) {
            return EventBadge.STAR;
        }

        if (totalDiscountAmountForCal < 20000) {
            return EventBadge.TREE;
        }

        return EventBadge.SANTA;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }
}
