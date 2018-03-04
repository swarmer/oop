package me.swarmer.ptoop.lab4_plugin.appliances;

import me.swarmer.ptoop.lab4.appliances.ConcreteAppliance;
import me.swarmer.ptoop.lab4.appliances.AbstractAppliance;


/**
 * A washing machine
 */
@ConcreteAppliance
public class WashingMachine extends AbstractAppliance {
    public WashingMachine() {
        super("Unnamed washing mashine", 0, false);
    }

    public WashingMachine(String name, double consumedPower, boolean isTurnedOn) {
        super(name, consumedPower, isTurnedOn);
    }

    @Override
    public boolean needsSoftwareUpdates() {
        return false;
    }

    @Override
    public int getWarrantyMonths() {
        return 24;
    }
}
