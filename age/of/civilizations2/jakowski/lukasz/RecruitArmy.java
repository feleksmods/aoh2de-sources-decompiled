package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;

public class RecruitArmy {
    private int iArmy;
    private int iProvinceID;
    private int iArmyWidth = 0;

    public RecruitArmy(int iProvinceID, int iArmy) {
        this.iProvinceID = iProvinceID;
        this.setArmy(iArmy);
    }

    public final int getArmy() {
        return this.iArmy;
    }

    public final void setArmy(int iArmy) {
        this.iArmy = iArmy;
        try {
            CFG.glyphLayoutArmy.setText(CFG.fontArmy, "" + iArmy);
            this.iArmyWidth = (int)CFG.glyphLayoutArmy.width;
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public final int getProvinceID() {
        return this.iProvinceID;
    }

    public final void setProvinceID(int iProvinceID) {
        this.iProvinceID = iProvinceID;
    }

    public final int getArmyWidth() {
        return this.iArmyWidth;
    }
}
