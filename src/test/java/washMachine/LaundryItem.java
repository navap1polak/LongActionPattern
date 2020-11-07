package washMachine;

import nava.polak.onik.buisness.Item;

public class LaundryItem implements Item {
    private String name;

    public LaundryItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getAction() {
        return "to laundry bag";
    }

    @Override
    public String toString() {
        return getName();
    }
}
