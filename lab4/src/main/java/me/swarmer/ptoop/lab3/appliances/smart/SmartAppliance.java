package me.swarmer.ptoop.lab3.appliances.smart;


import me.swarmer.ptoop.lab3.appliances.AbstractAppliance;

import java.util.Date;


public abstract class SmartAppliance extends AbstractAppliance {
    public SmartAppliance(String name, double consumedPower, boolean isTurnedOn) {
        super(name, consumedPower, isTurnedOn);
    }

    @Override
    public boolean needsSoftwareUpdates() {
        return true;
    }
}
