package age.of.civilizations2.jakowski.lukasz.Button.Build;

import age.of.civilizations2.jakowski.lukasz.Button.Build.Button_Build;
import age.of.civilizations2.jakowski.lukasz.Button.Diplomacy.ButtonDiplomacy;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.IMGManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Renderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Build_Level2
extends Button_Build {
    private String sLevelID;
    private String sLevel;
    private int iLevelWidth = 0;
    private String sPop;
    private int iPopWidth = 0;
    private String sEco;
    private int iEcoWidth = 0;
    public int provinceID;

    @Override
    public int getCurr() {
        return this.provinceID;
    }

    public Button_Build_Level2(String sText, int nImageID, String nLevelID, int nCost, int nMovementCost, int iPosX, int iPosY, int iWidth, boolean isClickable, boolean isBuildMax, int inConstruction, float fTech, int provinceID) {
        super(sText, nImageID, nCost, nMovementCost, iPosX, iPosY, iWidth, isClickable, isBuildMax, inConstruction, fTech);
        this.provinceID = provinceID;
        this.sLevelID = nLevelID;
        this.sLevel = CFG.lang.get("Level") + ": ";
        CFG.glyphLay.setText(CFG.fontMain.get(this.fontID), this.sLevel);
        this.iLevelWidth = (int)CFG.glyphLay.width;
        this.sPop = CFG.getNumber_SHORT(CFG.core.getProv(provinceID).getPop().getPops());
        CFG.glyphLay.setText(CFG.fontMain.get(this.fontID), this.sPop);
        this.iPopWidth = (int)CFG.glyphLay.width;
        this.sEco = CFG.getNumber_SHORT(CFG.core.getProv(provinceID).getEco());
        CFG.glyphLay.setText(CFG.fontMain.get(this.fontID), this.sEco);
        this.iEcoWidth = (int)CFG.glyphLay.width;
    }

    @Override
    public void drawTextE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        boolean red;
        if (this.getIsHovered()) {
            if (this.lTime < System.currentTimeMillis() - 30L) {
                if (this.backAnimation) {
                    this.fAlphaMod -= 0.02f;
                    if (this.fAlphaMod < 0.0f) {
                        this.backAnimation = false;
                    }
                } else {
                    this.fAlphaMod += 0.02f;
                    if (this.fAlphaMod > 0.4f) {
                        this.backAnimation = true;
                    }
                }
                this.lTime = System.currentTimeMillis();
            }
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f - this.fAlphaMod));
            CFG.setRenderO(true);
        } else {
            this.backAnimation = false;
            this.fAlphaMod = 0.0f;
            this.lTime = System.currentTimeMillis();
        }
        boolean bl = red = this.canBuild_MoneyCost && this.canBuild_Movement;
        if (!red || this.iTechWidth > 0) {
            oSB.setColor(CFG.COLOR_NEGATIVE_2.r, CFG.COLOR_NEGATIVE_2.g, CFG.COLOR_NEGATIVE_2.b, 0.65f);
            IMGManager.getIMG(this.iImageID).drawO(oSB, this.getPosXE() + ButtonDiplomacy.iDiploWidth / 2 - IMGManager.getIMG(this.iImageID).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeightE() / 2 - IMGManager.getIMG(this.iImageID).getHeight() / 2 + iTranslateY);
        } else {
            IMGManager.getIMG(this.iImageID).drawO(oSB, this.getPosXE() + ButtonDiplomacy.iDiploWidth / 2 - IMGManager.getIMG(this.iImageID).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeightE() / 2 - IMGManager.getIMG(this.iImageID).getHeight() / 2 + iTranslateY);
        }
        oSB.setColor(Color.WHITE);
        if (this.getIsHovered()) {
            if (!this.inConstruction) {
                if (this.iTechWidth > 0) {
                    IMGManager.getIMG(Images.technology).drawO(oSB, this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - (int)((float)IMGManager.getIMG(Images.technology).getWidth() * this.getImageScale(Images.technology, ICON_SCALE)) + iTranslateX, this.getPosY() + this.getHeightE() / 2 - IMGManager.getIMG(Images.technology).getHeight() - (int)((float)IMGManager.getIMG(Images.technology).getHeight() * this.getImageScale(Images.technology, ICON_SCALE)) / 2 + iTranslateY, (int)((float)IMGManager.getIMG(Images.technology).getWidth() * this.getImageScale(Images.technology, ICON_SCALE)), (int)((float)IMGManager.getIMG(Images.technology).getHeight() * this.getImageScale(Images.technology, ICON_SCALE)));
                    Renderer.drawTextWithShadow(oSB, this.fontID, this.sTech, this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - this.iTechWidth - (int)((float)IMGManager.getIMG(Images.technology).getWidth() * this.getImageScale(Images.technology, ICON_SCALE)) - CFG.PADD + iTranslateX, this.getPosY() + this.getHeightE() / 2 - this.getTextHeight() / 2 + iTranslateY, CFG.COLOR_NEGATIVE_2);
                } else if (this.sCost.length() > 0 && this.sMovementCost.length() > 0) {
                    if (this.sCost.length() > 0) {
                        IMGManager.getIMG(Images.topGold()).drawO(oSB, this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - (int)((float)IMGManager.getIMG(Images.topGold()).getWidth() * this.getImageScale(Images.topGold(), ICON_SCALE)) + iTranslateX, this.getPosY() + this.getHeightE() / 2 - (int)((float)IMGManager.getIMG(Images.topGold()).getHeight() * this.getImageScale(Images.topGold(), ICON_SCALE)) - IMGManager.getIMG(Images.topGold()).getHeight() - CFG.PADD / 2 + iTranslateY, (int)((float)IMGManager.getIMG(Images.topGold()).getWidth() * this.getImageScale(Images.topGold(), ICON_SCALE)), (int)((float)IMGManager.getIMG(Images.topGold()).getHeight() * this.getImageScale(Images.topGold(), ICON_SCALE)));
                        Renderer.drawTextWithShadow(oSB, this.fontID, this.sCost, this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - Math.max((int)((float)IMGManager.getIMG(Images.topMovementPoints).getWidth() * this.getImageScale(Images.topMovementPoints, ICON_SCALE)), (int)((float)IMGManager.getIMG(Images.topGold()).getWidth() * this.getImageScale(Images.topGold(), ICON_SCALE))) - CFG.PADD - this.iCostWidth + iTranslateX, this.getPosY() + this.getHeightE() / 2 - CFG.PADD / 2 - this.getTextHeight() + iTranslateY, this.canBuild_MoneyCost ? CFG.COLOR_GOLD : CFG.COLOR_NEGATIVE_2);
                    }
                    if (this.sMovementCost.length() > 0) {
                        IMGManager.getIMG(Images.topMovementPoints).drawO(oSB, this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - (int)((float)IMGManager.getIMG(Images.topMovementPoints).getWidth() * this.getImageScale(Images.topMovementPoints, ICON_SCALE)) + iTranslateX, this.getPosY() + this.getHeightE() / 2 - IMGManager.getIMG(Images.topMovementPoints).getHeight() + CFG.PADD / 2 + iTranslateY, (int)((float)IMGManager.getIMG(Images.topMovementPoints).getWidth() * this.getImageScale(Images.topMovementPoints, ICON_SCALE)), (int)((float)IMGManager.getIMG(Images.topMovementPoints).getHeight() * this.getImageScale(Images.topMovementPoints, ICON_SCALE)));
                        Renderer.drawTextWithShadow(oSB, this.fontID, this.sMovementCost, this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - this.iMovementCostWidth - Math.max((int)((float)IMGManager.getIMG(Images.topMovementPoints).getWidth() * this.getImageScale(Images.topMovementPoints, ICON_SCALE)), (int)((float)IMGManager.getIMG(Images.topGold()).getWidth() * this.getImageScale(Images.topGold(), ICON_SCALE))) - CFG.PADD + iTranslateX, this.getPosY() + this.getHeightE() / 2 + CFG.PADD / 2 + iTranslateY, this.canBuild_Movement ? CFG.COLOR_MOVEMENT : CFG.COLOR_NEGATIVE_2);
                    }
                } else if (this.sMovementCost.length() > 0) {
                    IMGManager.getIMG(Images.topMovementPoints).drawO(oSB, this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - (int)((float)IMGManager.getIMG(Images.topMovementPoints).getWidth() * this.getImageScale(Images.topMovementPoints, ICON_SCALE)) + iTranslateX, this.getPosY() + this.getHeightE() / 2 - IMGManager.getIMG(Images.topMovementPoints).getHeight() - (int)((float)IMGManager.getIMG(Images.topMovementPoints).getHeight() * this.getImageScale(Images.topMovementPoints, ICON_SCALE)) / 2 + iTranslateY, (int)((float)IMGManager.getIMG(Images.topMovementPoints).getWidth() * this.getImageScale(Images.topMovementPoints, ICON_SCALE)), (int)((float)IMGManager.getIMG(Images.topMovementPoints).getHeight() * this.getImageScale(Images.topMovementPoints, ICON_SCALE)));
                    Renderer.drawTextWithShadow(oSB, this.fontID, this.sMovementCost, this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - this.iMovementCostWidth - (int)((float)IMGManager.getIMG(Images.topMovementPoints).getWidth() * this.getImageScale(Images.topMovementPoints, ICON_SCALE)) - CFG.PADD + iTranslateX, this.getPosY() + this.getHeightE() / 2 - this.getTextHeight() / 2 + iTranslateY, this.canBuild_Movement ? CFG.COLOR_MOVEMENT : CFG.COLOR_NEGATIVE_2);
                } else if (this.getCheckboxSt()) {
                    IMGManager.getIMG(Images.iconTrue).drawO(oSB, this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - (int)((float)IMGManager.getIMG(Images.iconTrue).getWidth() * this.getImageScale(Images.iconTrue, ICON_SCALE)) + iTranslateX, this.getPosY() + this.getHeightE() / 2 - IMGManager.getIMG(Images.iconTrue).getHeight() - (int)((float)IMGManager.getIMG(Images.iconTrue).getHeight() * this.getImageScale(Images.iconTrue, ICON_SCALE)) / 2 + iTranslateY, (int)((float)IMGManager.getIMG(Images.iconTrue).getWidth() * this.getImageScale(Images.iconTrue, ICON_SCALE)), (int)((float)IMGManager.getIMG(Images.iconTrue).getHeight() * this.getImageScale(Images.iconTrue, ICON_SCALE)));
                }
            } else {
                IMGManager.getIMG(Images.time).drawO(oSB, this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - (int)((float)IMGManager.getIMG(Images.time).getWidth() * this.getImageScale(Images.time, ICON_SCALE)) + iTranslateX, this.getPosY() + this.getHeightE() / 2 - IMGManager.getIMG(Images.time).getHeight() - (int)((float)IMGManager.getIMG(Images.time).getHeight() * this.getImageScale(Images.time, ICON_SCALE)) / 2 + iTranslateY, (int)((float)IMGManager.getIMG(Images.time).getWidth() * this.getImageScale(Images.time, ICON_SCALE)), (int)((float)IMGManager.getIMG(Images.time).getHeight() * this.getImageScale(Images.time, ICON_SCALE)));
                Renderer.drawTextWithShadow(oSB, this.fontID, this.sConstruction, this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - this.iConstructionWidth - (int)((float)IMGManager.getIMG(Images.time).getWidth() * this.getImageScale(Images.time, ICON_SCALE)) - CFG.PADD + iTranslateX, this.getPosY() + this.getHeightE() / 2 - this.getTextHeight() / 2 + iTranslateY, CFG.COLOR_NEUTRAL);
            }
        } else {
            IMGManager.getIMG(Images.pop).drawO(oSB, this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - (int)((float)IMGManager.getIMG(Images.pop).getWidth() * this.getImageScale(Images.pop, ICON_SCALE)) + iTranslateX, this.getPosY() + this.getHeightE() / 2 - (int)((float)IMGManager.getIMG(Images.pop).getHeight() * this.getImageScale(Images.pop, ICON_SCALE)) - IMGManager.getIMG(Images.pop).getHeight() - CFG.PADD / 2 + iTranslateY, (int)((float)IMGManager.getIMG(Images.pop).getWidth() * this.getImageScale(Images.pop, ICON_SCALE)), (int)((float)IMGManager.getIMG(Images.pop).getHeight() * this.getImageScale(Images.pop, ICON_SCALE)));
            Renderer.drawTextWithShadow(oSB, this.fontID, this.sPop, this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - Math.max((int)((float)IMGManager.getIMG(Images.economy).getWidth() * this.getImageScale(Images.economy, ICON_SCALE)), (int)((float)IMGManager.getIMG(Images.pop).getWidth() * this.getImageScale(Images.pop, ICON_SCALE))) - CFG.PADD - this.iPopWidth + iTranslateX, this.getPosY() + this.getHeightE() / 2 - CFG.PADD / 2 - this.getTextHeight() + iTranslateY, red ? CFG.COLOR_POPULATION : CFG.COLOR_NEGATIVE_2);
            if (this.iTechWidth > 0) {
                IMGManager.getIMG(Images.technology).drawO(oSB, this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - (int)((float)IMGManager.getIMG(Images.technology).getWidth() * this.getImageScale(Images.technology, ICON_SCALE)) + iTranslateX, this.getPosY() + this.getHeightE() / 2 - IMGManager.getIMG(Images.technology).getHeight() + CFG.PADD / 2 + iTranslateY, (int)((float)IMGManager.getIMG(Images.technology).getWidth() * this.getImageScale(Images.technology, ICON_SCALE)), (int)((float)IMGManager.getIMG(Images.technology).getHeight() * this.getImageScale(Images.technology, ICON_SCALE)));
                Renderer.drawTextWithShadow(oSB, this.fontID, this.sTech, this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - this.iTechWidth - Math.max((int)((float)IMGManager.getIMG(Images.economy).getWidth() * this.getImageScale(Images.economy, ICON_SCALE)), (int)((float)IMGManager.getIMG(Images.pop).getWidth() * this.getImageScale(Images.topGold(), ICON_SCALE))) - CFG.PADD + iTranslateX, this.getPosY() + this.getHeightE() / 2 + CFG.PADD / 2 + iTranslateY, CFG.COLOR_NEGATIVE_2);
            } else {
                IMGManager.getIMG(Images.economy).drawO(oSB, this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - (int)((float)IMGManager.getIMG(Images.economy).getWidth() * this.getImageScale(Images.economy, ICON_SCALE)) + iTranslateX, this.getPosY() + this.getHeightE() / 2 - IMGManager.getIMG(Images.economy).getHeight() + CFG.PADD / 2 + iTranslateY, (int)((float)IMGManager.getIMG(Images.economy).getWidth() * this.getImageScale(Images.economy, ICON_SCALE)), (int)((float)IMGManager.getIMG(Images.economy).getHeight() * this.getImageScale(Images.economy, ICON_SCALE)));
                Renderer.drawTextWithShadow(oSB, this.fontID, this.sEco, this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - this.iEcoWidth - Math.max((int)((float)IMGManager.getIMG(Images.economy).getWidth() * this.getImageScale(Images.economy, ICON_SCALE)), (int)((float)IMGManager.getIMG(Images.pop).getWidth() * this.getImageScale(Images.topGold(), ICON_SCALE))) - CFG.PADD + iTranslateX, this.getPosY() + this.getHeightE() / 2 + CFG.PADD / 2 + iTranslateY, CFG.COLOR_ECONOMY);
            }
        }
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextE(), this.getPosXE() + CFG.PADD + ButtonDiplomacy.iDiploWidth + iTranslateX, this.getPosY() + this.getHeightE() / 2 - (this.getTextHeight() + this.getTextHeight() + CFG.PADD) / 2 + iTranslateY, this.getColorE(isActive));
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sLevel, this.getPosXE() + CFG.PADD + ButtonDiplomacy.iDiploWidth + iTranslateX, this.getPosY() + this.getHeightE() / 2 - (this.getTextHeight() + this.getTextHeight() + CFG.PADD) / 2 + CFG.PADD + this.getTextHeight() + iTranslateY, CFG.COLOR_TEXT_GRAY_NS_HOVER);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sLevelID, this.getPosXE() + CFG.PADD + ButtonDiplomacy.iDiploWidth + this.iLevelWidth + iTranslateX, this.getPosY() + this.getHeightE() / 2 - (this.getTextHeight() + this.getTextHeight() + CFG.PADD) / 2 + CFG.PADD + this.getTextHeight() + iTranslateY, CFG.COLOR_TEXT_NUM_OF_PROVINCES);
    }
}
