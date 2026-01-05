package christmas.service;

import christmas.domain.Category;
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

    // 증정 메뉴
    public boolean isPresent() {
        if (getTotal() >= 120000) {
            return true;
        }

        return false;
    }

    // TODO: 혜택 내역
    public ArrayList<String[]> getBenefits() {
        ArrayList<String[]> benefits = new ArrayList<>();
        int amount = 0;

        if (isPresent()) {
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
        int discountAmount = 0;

        for (Menu menu : menuRepository.findAllMenu()) {
            if (menu.getCategory().equals(Category.DESSERT)) {
                discountAmount -= 2023;
            }
        }

        totalDiscountAmount += discountAmount;
        return new String[]{"주말 할인", String.valueOf(discountAmount)};
    }

    private String[] weekendDiscount() {
        int discountAmount = 0;

        for (Menu menu : menuRepository.findAllMenu()) {
            if (menu.getCategory().equals(Category.MAIN)) {
                discountAmount -= 2023;
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


    // TODO: 할인 후 결제 금액
    // TODO: 이벤트 뱃지

    public void setDay(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }
}
