package me.swarmer.ptoop.lab5_plugin1.appliances;

import me.swarmer.ptoop.lab5.appliances.ConcreteAppliance;
import me.swarmer.ptoop.lab5.appliances.AbstractAppliance;


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
