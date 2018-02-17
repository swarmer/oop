package me.swarmer.ptoop.lab4_plugin.plugins;


import javafx.util.Pair;
import me.swarmer.ptoop.lab4.appliances.Appliance;
import me.swarmer.ptoop.lab4.plugins.Plugin;
import me.swarmer.ptoop.lab4.ui.ApplianceSet;
import me.swarmer.ptoop.lab4_plugin.appliances.Microwave;
import me.swarmer.ptoop.lab4_plugin.appliances.WashingMachine;


/**
 * A plugin specification
 */
public class WashingMachinePlugin implements Plugin {
    @Override
    public Class<?>[] getApplianceClasses() {
        return new Class[] { WashingMachine.class, Microwave.class};
    }

    @Override
    public Pair<String, Runnable>[] getCommands(ApplianceSet applianceSet) {
        return new Pair[] {
                new Pair<String, Runnable>("List washing machines", () -> listWashingMachines(applianceSet)),
        };
    }

    private void listWashingMachines(ApplianceSet applianceSet) {
        for (Appliance appliance : applianceSet.getAppliances()) {
            if (appliance instanceof WashingMachine) {
                System.out.printf("%s\n", appliance);
            }
        }
    }
}
