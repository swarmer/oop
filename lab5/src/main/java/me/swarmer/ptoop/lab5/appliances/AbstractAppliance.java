package me.swarmer.ptoop.lab5.appliances;


import java.util.Objects;


/**
 * Base appliance functionality
 */
public abstract class AbstractAppliance implements Appliance {
    private String name;
    private double consumedPower;
    private boolean isTurnedOn;

    public AbstractAppliance(String name, double consumedPower, boolean isTurnedOn) {
        this.name = name;
        this.consumedPower = consumedPower;
        this.isTurnedOn = isTurnedOn;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public double getConsumedPower() {
        return consumedPower;
    }

    @Override
    public void setConsumedPower(double power) {
        consumedPower = power;
    }

    @Override
    public boolean isTurnedOn() {
        return isTurnedOn;
    }

    @Override
    public void setTurnedOn(boolean turnedOn) {
        isTurnedOn = turnedOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractAppliance that = (AbstractAppliance) o;
        return Double.compare(that.consumedPower, consumedPower) == 0 &&
                isTurnedOn == that.isTurnedOn &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, consumedPower, isTurnedOn);
    }

    @Override
    public String toString() {
        String className = this.getClass().getSimpleName();

        return String.format("%s{", className) +
                "name='" + name + '\'' +
                ", consumedPower=" + consumedPower +
                ", isTurnedOn=" + isTurnedOn +
                '}';
    }
}
