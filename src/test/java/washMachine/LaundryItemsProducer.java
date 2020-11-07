package washMachine;

import nava.polak.onik.buisness.ItemListManager;
import nava.polak.onik.buisness.ProducerRunner;

import java.util.concurrent.Callable;

public class LaundryItemsProducer implements ProducerRunner {
    private ItemListManager<LaundryItem> laundryItemListManager;
    private long timeFor1CircleLaundry;

    public LaundryItemsProducer(ItemListManager<LaundryItem> laundryItemListManager, long timeFor1CircleLaundry) {
        this.laundryItemListManager = laundryItemListManager;
        this.timeFor1CircleLaundry = timeFor1CircleLaundry;
    }


    public Boolean call() throws Exception {
        System.out.println("Start adding items");
        //add first item to laundry
        laundryItemListManager.addItem(new LaundryItem("item1"));

        //wait 500 msec
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //add 2 additional items
        laundryItemListManager.addItem(new LaundryItem("item2"));
        laundryItemListManager.addItem(new LaundryItem("item3"));

        //wait 500 msec
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //add 2 additional items
        laundryItemListManager.addItem(new LaundryItem("item4"));
        laundryItemListManager.addItem(new LaundryItem("item5"));
        laundryItemListManager.addItem(new LaundryItem("item6"));
        laundryItemListManager.addItem(new LaundryItem("item7"));

        //wait till machine wash finish first cycle
        try {
            Thread.sleep(timeFor1CircleLaundry - 1000 + 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //add 2 additional items
        laundryItemListManager.addItem(new LaundryItem("item8"));
        laundryItemListManager.addItem(new LaundryItem("item9"));

        return true;

    }
}
