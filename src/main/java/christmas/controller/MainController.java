package christmas.controller;

import christmas.domain.EventBadge;
import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.ArrayList;

public class MainController {
    private final OrderService orderService;
    private final InputView inputView;
    private final OutputView outputView;

    public MainController(OrderService orderService) {
        this.orderService = orderService;
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        // 1. 초기 설정 (void 메서드 재시도)
        retryUntilValid(this::selectDate);

        retryUntilValid(this::order);

        retryUntilValid(this::doPlanning);

        // 2. 값 입력 (return 있는 메서드 재시도)
        // String result = retryUntilValid(inputView::readSomething);
    }

    private void selectDate() {
        orderService.setDay(inputView.readAndParseDate());
    }

    private void order() {
        orderService.saveOrders(inputView.readMenu());
    }

    private void doPlanning() {
        int originalTotal = orderService.getTotal();
        outputView.printOrders(orderService.getDay(), orderService.getMenuAndCnt());
        outputView.printTotalMoney(originalTotal);

        if (originalTotal < 10000) {
            outputView.printBenefits("없음", new ArrayList<String[]>(), originalTotal, 0, EventBadge.NONE);
            return;
        }

        outputView.printBenefits(orderService.isPresent(), orderService.getBenefits(), originalTotal, orderService.getTotalDiscountAmount(), orderService.getEventBadge());
    }

    // 1. 반환값이 있는 경우 (Supplier)
    private <T> T retryUntilValid(java.util.function.Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    // 2. 반환값이 없는 경우 (Runnable) - ★ 이거 필수 추가
    private void retryUntilValid(Runnable action) {
        while (true) {
            try {
                action.run();
                return;
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}