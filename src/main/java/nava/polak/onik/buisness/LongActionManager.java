package nava.polak.onik.buisness;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class LongActionManager {

    private LongActionConsumerRunner longActionConsumer;
    private Callable<Boolean> producerRunner;
    private long maxTimeOutInMs;



    public LongActionManager(LongActionConsumerRunner longActionConsumerRunner,
                             ProducerRunner producerRunner,
                             long maxTimeOutInMs) {
        this.longActionConsumer = longActionConsumerRunner;
        this.producerRunner = producerRunner;
        this.maxTimeOutInMs = maxTimeOutInMs;
    }

    public void startProcessing() {
        //start thread waiting for notification on file change,
        //upon notification it will analyze the changes
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Callable<Boolean>> taskList = new ArrayList<>();

        taskList.add(producerRunner);
        taskList.add(longActionConsumer);
        try {
            //I use callable but these can be replaced easily by Runnable.
            //This depends if you need output from the process
            List <Future <Boolean>> futures;
            if(maxTimeOutInMs > 0)
                futures = executor.invokeAll(taskList, maxTimeOutInMs, TimeUnit.MILLISECONDS);
            else
                futures = executor.invokeAll(taskList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdownNow();
            System.out.println("Process Finished");
        }

            System.out.println("Going to close all");
            longActionConsumer.setProceed(false);
            executor.shutdownNow();

        ;
    }

}
