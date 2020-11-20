package nava.polak.onik.buisness.washMachine;

import nava.polak.onik.buisness.ItemListManager;
import nava.polak.onik.buisness.LongActionManager;

public class Main {

    public static void main(String[] args) {
        long timeFor1CircleLaundryInMs = 5000;
        int capacity = 5;
        ItemListManager<LaundryItem> laundryBag = new ItemListManager<>(capacity);
        WashingMachineRunner washingMachineRunner = new WashingMachineRunner(laundryBag,timeFor1CircleLaundryInMs);
        LaundryItemsProducer laundryItemsProducer = new LaundryItemsProducer(laundryBag,timeFor1CircleLaundryInMs);

        LongActionManager longActionManager = new LongActionManager(washingMachineRunner, laundryItemsProducer,20000);
        longActionManager.startProcessing();

    }
}
