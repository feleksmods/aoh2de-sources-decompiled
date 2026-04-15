package age.of.civilizations2.jakowski.lukasz.Button.Build;

import age.of.civilizations2.jakowski.lukasz.Button.Build.Button_Build;
import age.of.civilizations2.jakowski.lukasz.Button.Diplomacy.ButtonDiplomacy;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.IMGManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MapA.Plagues.Nuke.NukeManager;
import age.of.civilizations2.jakowski.lukasz.Renderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Build_Nuke
extends Button_Build {
    public String sDate;
    public int iDateWidth;
    public String sEconomy;
    public int iEconomyWidth;
    public Color oColor = Color.WHITE;

    public Button_Build_Nuke(String sText, int nImageID, int nCost, String nLimit, int iPosX, int iPosY, int iWidth) {
        super(sText, nImageID, nCost, 0, iPosX, iPosY, iWidth, true, false, 0, CFG.NUKES_REQUIRED_TECH_LVL);
        this.sMovementCost = nLimit;
        CFG.glyphLay.setText(CFG.fontMain.get(this.fontID), "" + this.sMovementCost);
        this.iMovementCostWidth = (int)CFG.glyphLay.width;
        this.sDate = CFG.lang.get("RequiredTechnologyLevel") + ": ";
        CFG.glyphLay.setText(CFG.fontMain.get(this.fontID), this.sDate);
        this.iDateWidth = (int)CFG.glyphLay.width;
        this.canBuild_Movement = NukeManager.canBuildMore(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId());
        this.setMin(0);
    }

    @Override
    public void drawTextE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        IMGManager.getIMG(this.iImageID).drawO(oSB, this.getPosXE() + ButtonDiplomacy.iDiploWidth / 2 - IMGManager.getIMG(this.iImageID).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeightE() / 2 - IMGManager.getIMG(this.iImageID).getHeight() / 2 + iTranslateY);
        if (this.sCost.length() > 0 && this.sMovementCost.length() > 0) {
            if (this.sCost.length() > 0) {
                IMGManager.getIMG(Images.topGold()).drawO(oSB, this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - (int)((float)IMGManager.getIMG(Images.topGold()).getWidth() * this.getImageScale(Images.topGold(), ICON_SCALE)) + iTranslateX, this.getPosY() + this.getHeightE() / 2 - (int)((float)IMGManager.getIMG(Images.topGold()).getHeight() * this.getImageScale(Images.topGold(), ICON_SCALE)) - IMGManager.getIMG(Images.topGold()).getHeight() - CFG.PADD / 2 + iTranslateY, (int)((float)IMGManager.getIMG(Images.topGold()).getWidth() * this.getImageScale(Images.topGold(), ICON_SCALE)), (int)((float)IMGManager.getIMG(Images.topGold()).getHeight() * this.getImageScale(Images.topGold(), ICON_SCALE)));
                Renderer.drawTextWithShadow(oSB, this.fontID, this.sCost, this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - (int)((float)IMGManager.getIMG(Images.topGold()).getWidth() * this.getImageScale(Images.topGold(), ICON_SCALE)) - CFG.PADD - this.iCostWidth + iTranslateX, this.getPosY() + this.getHeightE() / 2 - CFG.PADD / 2 - this.getTextHeight() + iTranslateY, this.canBuild_MoneyCost ? CFG.COLOR_GOLD : CFG.COLOR_NEGATIVE_2);
            }
            if (this.sMovementCost.length() > 0) {
                IMGManager.getIMG(Images.nuke).drawO(oSB, this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - (int)((float)IMGManager.getIMG(Images.nuke).getWidth() * this.getImageScale(Images.nuke, ICON_SCALE)) + iTranslateX, this.getPosY() + this.getHeightE() / 2 - IMGManager.getIMG(Images.nuke).getHeight() + CFG.PADD / 2 + iTranslateY, (int)((float)IMGManager.getIMG(Images.nuke).getWidth() * this.getImageScale(Images.nuke, ICON_SCALE)), (int)((float)IMGManager.getIMG(Images.nuke).getHeight() * this.getImageScale(Images.nuke, ICON_SCALE)));
                Renderer.drawTextWithShadow(oSB, this.fontID, this.sMovementCost, this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - this.iMovementCostWidth - (int)((float)IMGManager.getIMG(Images.nuke).getWidth() * this.getImageScale(Images.nuke, ICON_SCALE)) - CFG.PADD + iTranslateX, this.getPosY() + this.getHeightE() / 2 + CFG.PADD / 2 + iTranslateY, this.canBuild_Movement ? CFG.COLOR_TEXT_GRAY_NS : CFG.COLOR_NEGATIVE_1);
            }
        } else if (this.sMovementCost.length() > 0) {
            IMGManager.getIMG(Images.nuke).drawO(oSB, this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - (int)((float)IMGManager.getIMG(Images.nuke).getWidth() * this.getImageScale(Images.nuke, ICON_SCALE)) + iTranslateX, this.getPosY() + this.getHeightE() / 2 - IMGManager.getIMG(Images.nuke).getHeight() - (int)((float)IMGManager.getIMG(Images.nuke).getHeight() * this.getImageScale(Images.nuke, ICON_SCALE)) / 2 + iTranslateY, (int)((float)IMGManager.getIMG(Images.nuke).getWidth() * this.getImageScale(Images.nuke, ICON_SCALE)), (int)((float)IMGManager.getIMG(Images.nuke).getHeight() * this.getImageScale(Images.nuke, ICON_SCALE)));
            Renderer.drawTextWithShadow(oSB, this.fontID, this.sMovementCost, this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - this.iMovementCostWidth - (int)((float)IMGManager.getIMG(Images.nuke).getWidth() * this.getImageScale(Images.nuke, ICON_SCALE)) - CFG.PADD + iTranslateX, this.getPosY() + this.getHeightE() / 2 - this.getTextHeight() / 2 + iTranslateY, this.canBuild_Movement ? CFG.COLOR_TEXT_GRAY_NS : CFG.COLOR_NEGATIVE_1);
        }
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextE(), this.getPosXE() + CFG.PADD + ButtonDiplomacy.iDiploWidth + iTranslateX, this.getPosY() + this.getHeightE() / 2 - this.getTextHeight() - CFG.PADD / 2 + iTranslateY, this.getColorE(isActive));
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sDate, this.getPosXE() + CFG.PADD + ButtonDiplomacy.iDiploWidth + iTranslateX, this.getPosY() + this.getHeightE() / 2 + CFG.PADD / 2 + iTranslateY, CFG.COLOR_TEXT_GRAY_NS_HOVER);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sEconomy, this.getPosXE() + CFG.PADD + ButtonDiplomacy.iDiploWidth + this.iDateWidth + iTranslateX, this.getPosY() + this.getHeightE() / 2 + CFG.PADD / 2 + iTranslateY, this.oColor);
        IMGManager.getIMG(Images.technology).drawO(oSB, this.getPosXE() + CFG.PADD * 2 + this.iEconomyWidth + ButtonDiplomacy.iDiploWidth + this.iDateWidth + iTranslateX, this.getPosY() + this.getHeightE() / 2 + CFG.PADD / 2 - IMGManager.getIMG(Images.technology).getHeight() + iTranslateY, (int)((float)IMGManager.getIMG(Images.technology).getWidth() * this.getImageScale(Images.technology, ICON_SCALE)), (int)((float)IMGManager.getIMG(Images.technology).getHeight() * this.getImageScale(Images.technology, ICON_SCALE)));
    }

    @Override
    public void setMin(int iMin) {
        this.sEconomy = "" + (float)iMin / 100.0f;
        this.oColor = (float)iMin / 100.0f <= CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getTechLevel() ? CFG.COLOR_TECHNOLOGY : CFG.COLOR_NEGATIVE_2;
        CFG.glyphLay.setText(CFG.fontMain.get(this.fontID), this.sEconomy);
        this.iEconomyWidth = (int)CFG.glyphLay.width;
    }
}
