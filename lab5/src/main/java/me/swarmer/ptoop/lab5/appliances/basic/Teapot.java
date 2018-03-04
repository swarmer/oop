package me.swarmer.ptoop.lab5.appliances.basic;

import me.swarmer.ptoop.lab5.appliances.ConcreteAppliance;


/**
 * An electric teapot
 */
@ConcreteAppliance
public class Teapot extends BasicAppliance {
    public Teapot() {
        super("Unnamed teapot", 0, false);
    }

    public Teapot(String name, double consumedPower, boolean isTurnedOn) {
        super(name, consumedPower, isTurnedOn);
    }

    @Override
    public int getWarrantyMonths() {
        return 3;
    }
}
