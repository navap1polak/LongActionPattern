package nava.polak.onik.buisness;

import java.util.List;
import java.util.concurrent.Callable;

public abstract class LongActionConsumerRunner<E extends Item> implements Callable<Boolean> {



    private int cycleIndex;

    private ItemListManager<E> itemListManager;

    private boolean proceedLongProcessCycling = true;

    /**
     * This making the process on items taking time
     */
    public abstract void makeLongProcess(List<E> items);

    public LongActionConsumerRunner(ItemListManager<E> itemListManager) {
        this.itemListManager = itemListManager;
    }

    /**
     * The builsness getting the items from list and running the long action
     * @return
     */
    public Boolean call() {
        System.out.println("Starting machine " + System.currentTimeMillis());
        while (proceedLongProcessCycling) {
            cycleIndex++;
            System.out.println("Following items will be processed in cycle: " + cycleIndex);

            try {
                List<E> items = itemListManager.getAllItemsInList();

                items.forEach(System.out::println);

                makeLongProcess(items);
            } catch (InterruptedException e) {
               // e.printStackTrace();
            }
            System.out.println("Machine cycle number " +  cycleIndex +  " wash finished " + System.currentTimeMillis()) ;
        }
        return true;
    }

    public void setProceed(boolean b) {
        proceedLongProcessCycling = false;
    }
}
