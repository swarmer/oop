package me.swarmer.ptoop.lab5.appliances.basic;

import me.swarmer.ptoop.lab5.appliances.ConcreteAppliance;


/**
 * An electric oven
 */
@ConcreteAppliance
public class Oven extends BasicAppliance {
    public Oven() {
        super("Unnamed oven", 0, false);
    }

    public Oven(String name, double consumedPower, boolean isTurnedOn) {
        super(name, consumedPower, isTurnedOn);
    }

    @Override
    public int getWarrantyMonths() {
        return 24;
    }
}
