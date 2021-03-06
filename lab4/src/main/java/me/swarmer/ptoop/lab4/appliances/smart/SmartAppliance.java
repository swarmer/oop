package me.swarmer.ptoop.lab4.appliances.smart;


import me.swarmer.ptoop.lab4.appliances.AbstractAppliance;


public abstract class SmartAppliance extends AbstractAppliance {
    public SmartAppliance(String name, double consumedPower, boolean isTurnedOn) {
        super(name, consumedPower, isTurnedOn);
    }

    @Override
    public boolean needsSoftwareUpdates() {
        return true;
    }
}
