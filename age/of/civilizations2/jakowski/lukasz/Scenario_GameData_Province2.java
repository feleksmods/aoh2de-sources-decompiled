package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Scenario_GameData_Province2
implements Serializable {
    private static final long serialVersionUID = 0L;
    private List<Integer> lProvinceOwners = null;

    public final void buildProvinceOwners() {
        this.lProvinceOwners = new ArrayList<Integer>();
        for (int i = 0; i < CFG.core.getProvinSize(); ++i) {
            this.lProvinceOwners.add(CFG.core.getProv(i).getCivId());
        }
    }

    public final List<Integer> getProvinceOwners() {
        return this.lProvinceOwners;
    }
}
