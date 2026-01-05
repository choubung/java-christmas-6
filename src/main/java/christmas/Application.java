package christmas;

import christmas.controller.MainController;
import christmas.domain.MenuRepository;
import christmas.service.OrderService;

public class Application {
    public static void main(String[] args) {
        MenuRepository menuRepository = new MenuRepository();
        OrderService orderService = new OrderService(menuRepository);
        MainController controller = new MainController(orderService);

        controller.run();
    }
}