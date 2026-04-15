package age.of.civilizations2.jakowski.lukasz.MoveUnitsB;

import age.of.civilizations2.jakowski.lukasz.CFG;

public class MoveUnits_Plunder {
    private int iFromProvinceID;
    private int iNumOfUnits;
    private int iNumOfUnitsWidth;

    public MoveUnits_Plunder(int iFromProvinceID, int iNumOfUnits) {
        this.iFromProvinceID = iFromProvinceID;
        this.setNumOfUnits(iNumOfUnits);
    }

    public final int getFromProvinceID() {
        return this.iFromProvinceID;
    }

    public final int getNumOfUnits() {
        return this.iNumOfUnits;
    }

    public final void setNumOfUnits(int iNumOfUnits) {
        try {
            this.iNumOfUnits = iNumOfUnits;
            CFG.glyphLay.setText(CFG.fontArmy, "" + iNumOfUnits);
            this.iNumOfUnitsWidth = (int)CFG.glyphLay.width;
        }
        catch (IllegalArgumentException illegalArgumentException) {
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public final int getUnitsWidth() {
        return this.iNumOfUnitsWidth;
    }
}
