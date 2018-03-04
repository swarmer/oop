package me.swarmer.ptoop.lab5.ui;


import me.swarmer.ptoop.lab5.appliances.Appliance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * An appliance set container, serves as the application state storage and is serializable to file
 */
public class ApplianceSet implements Serializable {
    private List<Appliance> appliances = new ArrayList<>();

    public void addAppliance(Appliance appliance) {
        appliances.add(appliance);
    }

    public Appliance getAppliance(int index) {
        return appliances.get(index);
    }

    public Appliance removeAppliance(int index) {
        return appliances.remove(index);
    }

    public List<Appliance> getAppliances() {
        return Collections.unmodifiableList(appliances);
    }
}
