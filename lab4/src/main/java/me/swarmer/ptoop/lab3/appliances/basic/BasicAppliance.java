package me.swarmer.ptoop.lab3.appliances.basic;


import me.swarmer.ptoop.lab3.appliances.AbstractAppliance;

import java.util.Date;


public abstract class BasicAppliance extends AbstractAppliance {
    public BasicAppliance(String name, double consumedPower, boolean isTurnedOn) {
        super(name, consumedPower, isTurnedOn);
    }

    @Override
    public boolean needsSoftwareUpdates() {
        return false;
    }
}
