package me.swarmer.ptoop.lab3.appliances.smart;


public class SmartTv extends SmartAppliance {
    public SmartTv() {
        super("Unnamed TV", 0, false);
    }

    public SmartTv(String name, double consumedPower, boolean isTurnedOn) {
        super(name, consumedPower, isTurnedOn);
    }

    @Override
    public int getWarrantyMonths() {
        return 12;
    }
}
