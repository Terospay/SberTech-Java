public class Order {
    private final int id;

    Order(int id) {
        this.id = id;
    }

    Order() {
        this.id = 0;
    }

    public int getId() {
        return this.id;
    }
}
