public interface Dispatcher extends Runnable {
    void notifyAvailable(Taxi taxi);

    void run();

    void finishOrder(Taxi simpleTaxi, Order currOrder);

    void addTaxi(Taxi taxi);

    void addOrder(Order order);

    void finishWork();
}
