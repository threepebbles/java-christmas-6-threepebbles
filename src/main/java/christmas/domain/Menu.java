package christmas.domain;

public enum Menu {
    MUSHROOM_SOUP(MenuType.APPETIZER, "양송이수프", 6000, "원"),
    TAPAS(MenuType.APPETIZER, "타파스", 5500, "원"),
    CAESAR_SALAD(MenuType.APPETIZER, "시저샐러드", 8000, "원"),

    T_BONE_STEAK(MenuType.MAIN, "티본스테이크", 55000, "원"),
    BARBECUE_LIP(MenuType.MAIN, "바비큐립", 54000, "원"),
    SEAFOOD_PASTA(MenuType.MAIN, "해산물파스타", 35000, "원"),
    CHRISTMAS_PASTA(MenuType.MAIN, "크리스마스파스타", 25000, "원"),

    CHOCOLATE_CAKE(MenuType.DESSERT, "초코케이크", 15000, "원"),
    ICE_CREAM(MenuType.DESSERT, "아이스크림", 5000, "원"),


    ZERO_COKE(MenuType.BEVERAGE, "제로콜라", 3000, "원"),
    RED_WINE(MenuType.BEVERAGE, "레드와인", 60000, "원"),
    CHAMPAGNE(MenuType.BEVERAGE, "샴페인", 25000, "원");

    private MenuType menuType;
    private String name;
    private int price;
    private String currencyUnit;

    Menu(MenuType menuType, String name, int price, String currencyUnit) {
        this.menuType = menuType;
        this.name = name;
        this.price = price;
        this.currencyUnit = currencyUnit;
    }

    public static Menu findMenuByName(String name) {
        for (Menu menu : Menu.values()) {
            if (menu.name.equals(name)) {
                return menu;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return getName();
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

    public String getCurrencyUnit() {
        return currencyUnit;
    }
}