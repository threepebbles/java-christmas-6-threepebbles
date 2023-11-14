package christmas.domain;

import christmas.domain.constant.MenuType;

public enum Menu {
    MUSHROOM_SOUP(MenuType.APPETIZER, "양송이수프", 6000),
    TAPAS(MenuType.APPETIZER, "타파스", 5500),
    CAESAR_SALAD(MenuType.APPETIZER, "시저샐러드", 8000),

    T_BONE_STEAK(MenuType.MAIN, "티본스테이크", 55000),
    BARBECUE_LIP(MenuType.MAIN, "바비큐립", 54000),
    SEAFOOD_PASTA(MenuType.MAIN, "해산물파스타", 35000),
    CHRISTMAS_PASTA(MenuType.MAIN, "크리스마스파스타", 25000),

    CHOCOLATE_CAKE(MenuType.DESSERT, "초코케이크", 15000),
    ICE_CREAM(MenuType.DESSERT, "아이스크림", 5000),


    ZERO_COKE(MenuType.BEVERAGE, "제로콜라", 3000),
    RED_WINE(MenuType.BEVERAGE, "레드와인", 60000),
    CHAMPAGNE(MenuType.BEVERAGE, "샴페인", 25000),

    NOTHING(MenuType.NOTHING, "", 0);

    public static final String CURRENCY_UNIT = "원";
    private final MenuType menuType;
    private final String name;
    private final int price;

    Menu(MenuType menuType, String name, int price) {
        this.menuType = menuType;
        this.name = name;
        this.price = price;
    }

    public static Menu findMenuByName(String name) {
        for (Menu menu : Menu.values()) {
            if (menu.name.equals(name)) {
                return menu;
            }
        }
        return NOTHING;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}