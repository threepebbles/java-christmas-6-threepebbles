package christmas.model;

public record Order(Menu menu, int count) {
    public int calculatePrice() {
        return menu.getPrice() * count;
    }
}