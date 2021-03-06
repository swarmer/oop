package me.swarmer.ptoop.lab4.appliances.basic;


import me.swarmer.ptoop.lab4.appliances.AbstractAppliance;


public abstract class BasicAppliance extends AbstractAppliance {
    public BasicAppliance(String name, double consumedPower, boolean isTurnedOn) {
        super(name, consumedPower, isTurnedOn);
    }

    @Override
    public boolean needsSoftwareUpdates() {
        return false;
    }
}
