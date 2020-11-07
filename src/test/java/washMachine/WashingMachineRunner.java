package washMachine;

import nava.polak.onik.buisness.ItemListManager;
import nava.polak.onik.buisness.LongActionConsumerRunner;

import java.util.List;

public class WashingMachineRunner extends LongActionConsumerRunner<LaundryItem> {

    private long programTime;


    public WashingMachineRunner(ItemListManager<LaundryItem> laundryBag,long programTime) {
        super(laundryBag);
        this.programTime = programTime;
    }


    @Override
    public void makeLongProcess(List<LaundryItem> items) {
        try {
            System.out.println("waiting " + programTime + " for wash machine program to finish");
            Thread.sleep(programTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
