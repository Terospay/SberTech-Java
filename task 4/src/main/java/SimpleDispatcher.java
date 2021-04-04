import java.util.LinkedList;
import java.util.Queue;

public class SimpleDispatcher implements Dispatcher {
    private volatile Queue<Taxi> availTaxis = new LinkedList<Taxi>();
    private volatile Queue<Taxi> allTaxis = new LinkedList<Taxi>();
    private volatile Queue<Order> orders = new LinkedList<Order>();
    private volatile boolean workFinished = false;
    private volatile int count_taxi = 0;
    private volatile int count_finished = 0;

    public void addTaxi(Taxi taxi) {
        synchronized (availTaxis) {
            availTaxis.add(taxi);
            ++count_taxi;
        }
        synchronized (allTaxis) {
            allTaxis.add(taxi);
        }
    }

    public void addOrder(Order order) {
        synchronized (orders) {
            orders.add(order);
            orders.notify();
        }
    }

    public void finishOrder(Taxi taxi, Order order) {
        if (order == null) {
            synchronized (availTaxis) {
                availTaxis.add(taxi);
            }
            synchronized (this) {
                this.notify();
            }
        }
    }

    public void finishWork() {
        workFinished = true;
        synchronized (allTaxis) {
            for (var taxi : allTaxis) {
                synchronized (taxi) {
                    taxi.notify();
                }
            }
        }
        while (count_finished < count_taxi) {
            while (availTaxis.isEmpty()) {
                try {
                    synchronized (this) {
                        this.wait();
                    }
                } catch (InterruptedException i) {
                }
            }
            availTaxis.remove().finishWork();
            ++count_finished;
        }
        System.out.println("Finish");
    }


    @Override
    public void notifyAvailable(Taxi taxi) {
        synchronized (taxi) {
            taxi.notify();
        }
    }

    @Override
    public void run() {
        while (!workFinished) {
            while (orders.isEmpty()) {
                try {
                    synchronized (orders) {
                        orders.wait();
                    }
                } catch (InterruptedException i) {
                }
            }
            while (availTaxis.isEmpty()) {
                try {
                    synchronized (this) {
                        this.wait();
                    }
                } catch (InterruptedException i) {
                }
            }
            Order order = new Order();
            synchronized (orders) {
                order = orders.remove();
            }
            synchronized (availTaxis) {
                Taxi taxi = availTaxis.remove();
                taxi.placeOrder(order);
                notifyAvailable(taxi);
            }
        }
    }
}
