package me.swarmer.ptoop.lab3.ui;


import me.swarmer.ptoop.lab3.appliances.Appliance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class InterfaceState {
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
