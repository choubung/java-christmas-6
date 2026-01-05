package christmas.domain;

import static christmas.domain.Category.*;

public enum Menu {
    SOUP("양송이수프", 6000, APPETIZER),
    TAPAS("타파스", 5500, APPETIZER),
    SALAD("시저샐러드", 8000, APPETIZER),

    STEAK("티본스테이크", 55000, MAIN),
    BBQ("바비큐립", 54000, MAIN),
    SEAFOOD_PASTA("해산물파스타", 35000, MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, MAIN),

    CHOCO_CAKE("초코케이크", 15000, DESSERT),
    ICE_CREAM("아이스크림", 5000, DESSERT),

    COLA("제로콜라", 3000, DRINK),
    WINE("레드와인", 60000, DRINK),
    CHAMPAGNE("샴페인", 25000, DRINK);

    private final String name;
    private final int price;
    private final Category category;

    Menu(String name, int price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
