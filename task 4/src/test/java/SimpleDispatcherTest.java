
import org.junit.jupiter.api.Test;

class SimpleDispatcherTest {
//    @Test
//    void simpleSynchronizedTest() {
//        Dispatcher dispatcher = new SimpleDispatcher();
//        new Thread(dispatcher).start();
//        for (int i = 0; i < 10; ++i) {
//            Order order = new Order(i);
//            Taxi taxi = new SimpleTaxi(dispatcher, i);
//            new Thread(taxi).start();
//            dispatcher.addTaxi(taxi);
//            dispatcher.addOrder(order);
//        }
//        dispatcher.finishWork();
//    }

//    @Test
//    void manyOrdersFewTaxis() throws InterruptedException {
//        Dispatcher dispatcher = new SimpleDispatcher();
//        Thread mainThread = new  Thread(dispatcher);
//        mainThread.start();
//        Taxi taxi1 = new SimpleTaxi(dispatcher, 0);
//        Taxi taxi2 = new SimpleTaxi(dispatcher, 1);
//        dispatcher.addTaxi(taxi1);
//        dispatcher.addTaxi(taxi2);
//        new Thread(taxi1).start();
//        new Thread(taxi2).start();
//        for (int i = 0; i < 1000; ++i) {
//            Order order = new Order(i);
//            dispatcher.addOrder(order);
//        }
//        Thread.sleep(500);
//        dispatcher.finishWork();
//        mainThread.join();
//    }

    @Test
    void ManyOrdersManyTaxis() throws InterruptedException {
        Dispatcher dispatcher = new SimpleDispatcher();
        Thread mainThread = new Thread(dispatcher);
        mainThread.start();
        for (int i = 0; i < 100; ++i) {
            Taxi taxi = new SimpleTaxi(dispatcher, i);
            new Thread(taxi).start();
            dispatcher.addTaxi(taxi);
        }
        for (int i = 0; i < 10000; ++i) {
            Order order = new Order(i);
            dispatcher.addOrder(order);
        }
        for (int i = 100; i < 200; ++i) {
            Taxi taxi = new SimpleTaxi(dispatcher, i);
            new Thread(taxi).start();
            dispatcher.addTaxi(taxi);
        }
        Thread.sleep(10000);
        dispatcher.finishWork();
        mainThread.join();
    }
}