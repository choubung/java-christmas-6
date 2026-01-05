package christmas.service;

import christmas.domain.MenuRepository;

public class OrderService {
    private final MenuRepository menuRepository;

    public OrderService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }


}
