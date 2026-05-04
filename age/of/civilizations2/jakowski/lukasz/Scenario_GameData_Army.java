package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class Scenario_GameData_Army
implements Serializable {
    private static final long serialVersionUID = 0L;
    private int iProvinceID;
    private int iCivID;
    private int iArmy;

    public Scenario_GameData_Army(int iProvinceID, int iCivID, int iArmy) {
        this.iProvinceID = iProvinceID;
        this.iCivID = iCivID;
        this.iArmy = iArmy;
    }

    public final int getProvinceID() {
        return this.iProvinceID;
    }

    public final int getCivID() {
        return this.iCivID;
    }

    public final int getArmy() {
        return this.iArmy;
    }
}
