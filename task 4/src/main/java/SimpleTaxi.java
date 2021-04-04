public class SimpleTaxi implements Taxi {
    private final int id;
    private final Dispatcher dispatcher;
    private volatile Order currOrder = null;
    private volatile boolean workFinished = false;

    SimpleTaxi(Dispatcher dispatcher, int id) {
        this.dispatcher = dispatcher;
        this.id = id;
    }

    @Override
    public void run() {
        while (!workFinished) {
            while (currOrder == null) {
                try {
                    synchronized (this) {
                        this.wait();
                    }
                } catch (InterruptedException ignore) {
                }
            }
            if (!workFinished) {
                try {
                    makeOrder();
                } catch (InterruptedException ignore) {
                }
            }
            dispatcher.finishOrder(this, currOrder);
        }
    }

    public void makeOrder() throws InterruptedException {
        long time = Math.round(Math.random() * 500 + 500);
        Thread.sleep(time);
        System.out.println("Taxi " + id + " make " + currOrder.getId() + " order");
        currOrder = null;
    }

    public void finishWork() {
        workFinished = true;
    }

    @Override
    public void placeOrder(Order order) {
        currOrder = order;
    }

    public int getId() {
        return id;
    }
}
