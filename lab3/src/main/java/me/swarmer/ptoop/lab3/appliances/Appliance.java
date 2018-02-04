package me.swarmer.ptoop.lab3.appliances;


public interface Appliance {
    String getName();

    void setName(String name);

    double getConsumedPower();

    void setConsumedPower(double power);

    boolean isTurnedOn();

    void setTurnedOn(boolean turnedOn);

    boolean needsSoftwareUpdates();

    int getWarrantyMonths();
}
