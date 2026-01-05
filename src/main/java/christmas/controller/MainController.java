package christmas.controller;

import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;

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
        retryUntilValid(this::initialize);

        // 2. 값 입력 (return 있는 메서드 재시도)
        // String result = retryUntilValid(inputView::readSomething);
    }

    private void initialize() {
        // ...
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