package age.of.civilizations2.jakowski.lukasz.MoveUnitsB;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.MoveUnitsB.Line.MoveUnits_Line;
import age.of.civilizations2.jakowski.lukasz.MoveUnitsB.Line.MoveUnits_Line_Migrate;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MoveUnits {
    private int fromProvinceID;
    private int toProvinceID;
    private int numOfUnits;
    private int iNumOfUnitsWidth;
    private MoveUnits_Line moveUnits_Line = null;

    public MoveUnits(int fromProvinceID, int toProvinceID, int numOfUnits, boolean buildLane) {
        this.fromProvinceID = fromProvinceID;
        this.toProvinceID = toProvinceID;
        this.numOfUnits = numOfUnits;
        this.buildMoveUnitsLine();
    }

    public MoveUnits(int fromProvinceID, int toProvinceID, int numOfUnits, boolean buildLane, boolean migrateLine) {
        this.fromProvinceID = fromProvinceID;
        this.toProvinceID = toProvinceID;
        this.numOfUnits = numOfUnits;
        if (buildLane) {
            this.buildMoveUnitsLine_Migrate();
        }
    }

    public final void draw(SpriteBatch oSB, float nScale) {
        this.moveUnits_Line.drawLine(oSB, nScale);
    }

    public final void draw2(SpriteBatch oSB, float nScale) {
        this.moveUnits_Line.drawLine2(oSB, nScale);
    }

    public final int getNumberOfUnits() {
        return this.numOfUnits;
    }

    public final void setNumberOfUnits(int iNumOfUnits) {
        try {
            this.numOfUnits = iNumOfUnits;
            if (this.moveUnits_Line != null) {
                this.moveUnits_Line.lMovingTime = System.currentTimeMillis();
                this.moveUnits_Line.fMovingPercentage = 0.1f;
            }
            CFG.glyphLayoutMoveUnits2.setText(CFG.fontArmy, "" + iNumOfUnits);
            this.iNumOfUnitsWidth = (int)CFG.glyphLayoutMoveUnits2.width;
        }
        catch (IllegalArgumentException illegalArgumentException) {
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public final int getFromProviID() {
        return this.fromProvinceID;
    }

    public final int getToProvID() {
        return this.toProvinceID;
    }

    public final MoveUnits_Line getMoveUnits_Line() {
        return this.moveUnits_Line;
    }

    public final int getUnitsWidth() {
        return this.iNumOfUnitsWidth;
    }

    public final void buildMoveUnitsLine() {
        try {
            this.moveUnits_Line = new MoveUnits_Line(this.fromProvinceID, this.toProvinceID);
            CFG.glyphLayoutMoveUnits.setText(CFG.fontArmy, "" + this.numOfUnits);
            this.iNumOfUnitsWidth = (int)CFG.glyphLayoutMoveUnits.width;
        }
        catch (IllegalArgumentException illegalArgumentException) {
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public final void buildMoveUnitsLine_Migrate() {
        try {
            this.moveUnits_Line = new MoveUnits_Line_Migrate(this.fromProvinceID, this.toProvinceID);
            CFG.glyphLayoutMoveUnits.setText(CFG.fontArmy, "" + this.numOfUnits);
            this.iNumOfUnitsWidth = (int)CFG.glyphLayoutMoveUnits.width;
        }
        catch (IllegalArgumentException illegalArgumentException) {
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    public static interface LittleAnimation {
        public void update();
    }
}
