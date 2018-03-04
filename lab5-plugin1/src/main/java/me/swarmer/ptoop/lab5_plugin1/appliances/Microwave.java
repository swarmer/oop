package me.swarmer.ptoop.lab5_plugin1.appliances;


import me.swarmer.ptoop.lab5.appliances.AbstractAppliance;
import me.swarmer.ptoop.lab5.appliances.ConcreteAppliance;


/**
 * A smart microwave
 */
@ConcreteAppliance
public class Microwave extends AbstractAppliance {
    public Microwave() {
        super("Unnamed microwave", 0, false);
    }

    public Microwave(String name, double consumedPower, boolean isTurnedOn) {
        super(name, consumedPower, isTurnedOn);
    }

    @Override
    public boolean needsSoftwareUpdates() {
        return true;
    }

    @Override
    public int getWarrantyMonths() {
        return 16;
    }
}
