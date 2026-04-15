package age.of.civilizations2.jakowski.lukasz.Button.Build;

import age.of.civilizations2.jakowski.lukasz.Button.Build.Button_Build;
import age.of.civilizations2.jakowski.lukasz.Button.Diplomacy.ButtonDiplomacy;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.IMGManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menus.Invest.Menu_InGame_Invest;
import age.of.civilizations2.jakowski.lukasz.Renderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Build_Invest
extends Button_Build {
    public String sProvinceName;
    public String sDate;
    public int iDateWidth;
    public String sEconomy;
    public int iEconomyWidth;

    public Button_Build_Invest(String sText, String sProvinceID, int nImageID, int nCost, int nMovementCost, int iPosX, int iPosY, int iWidth) {
        super(sText, nImageID, nCost, nMovementCost, iPosX, iPosY, iWidth, true, false, 0, 0.0f);
        this.sProvinceName = sProvinceID;
        this.sDate = CFG.lang.get("Economy") + ": ";
        CFG.glyphLay.setText(CFG.fontMain.get(this.fontID), this.sDate);
        this.iDateWidth = (int)CFG.glyphLay.width;
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
                IMGManager.getIMG(Images.topMovementPoints).drawO(oSB, this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - (int)((float)IMGManager.getIMG(Images.topMovementPoints).getWidth() * this.getImageScale(Images.topMovementPoints, ICON_SCALE)) + iTranslateX, this.getPosY() + this.getHeightE() / 2 - IMGManager.getIMG(Images.topMovementPoints).getHeight() + CFG.PADD / 2 + iTranslateY, (int)((float)IMGManager.getIMG(Images.topMovementPoints).getWidth() * this.getImageScale(Images.topMovementPoints, ICON_SCALE)), (int)((float)IMGManager.getIMG(Images.topMovementPoints).getHeight() * this.getImageScale(Images.topMovementPoints, ICON_SCALE)));
                Renderer.drawTextWithShadow(oSB, this.fontID, this.sMovementCost, this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - this.iMovementCostWidth - (int)((float)IMGManager.getIMG(Images.topMovementPoints).getWidth() * this.getImageScale(Images.topMovementPoints, ICON_SCALE)) - CFG.PADD + iTranslateX, this.getPosY() + this.getHeightE() / 2 + CFG.PADD / 2 + iTranslateY, this.canBuild_Movement ? CFG.COLOR_MOVEMENT : CFG.COLOR_NEGATIVE_1);
            }
        } else if (this.sMovementCost.length() > 0) {
            IMGManager.getIMG(Images.topMovementPoints).drawO(oSB, this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - (int)((float)IMGManager.getIMG(Images.topMovementPoints).getWidth() * this.getImageScale(Images.topMovementPoints, ICON_SCALE)) + iTranslateX, this.getPosY() + this.getHeightE() / 2 - IMGManager.getIMG(Images.topMovementPoints).getHeight() - (int)((float)IMGManager.getIMG(Images.topMovementPoints).getHeight() * this.getImageScale(Images.topMovementPoints, ICON_SCALE)) / 2 + iTranslateY, (int)((float)IMGManager.getIMG(Images.topMovementPoints).getWidth() * this.getImageScale(Images.topMovementPoints, ICON_SCALE)), (int)((float)IMGManager.getIMG(Images.topMovementPoints).getHeight() * this.getImageScale(Images.topMovementPoints, ICON_SCALE)));
            Renderer.drawTextWithShadow(oSB, this.fontID, this.sMovementCost, this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - this.iMovementCostWidth - (int)((float)IMGManager.getIMG(Images.topMovementPoints).getWidth() * this.getImageScale(Images.topMovementPoints, ICON_SCALE)) - CFG.PADD + iTranslateX, this.getPosY() + this.getHeightE() / 2 - this.getTextHeight() / 2 + iTranslateY, this.canBuild_Movement ? CFG.COLOR_MOVEMENT : CFG.COLOR_NEGATIVE_1);
        }
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextE(), this.getPosXE() + CFG.PADD + ButtonDiplomacy.iDiploWidth + iTranslateX, this.getPosY() + this.getHeightE() / 2 - this.getTextHeight() - CFG.PADD / 2 + iTranslateY, this.getColorE(isActive));
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sProvinceName, this.getPosXE() + CFG.PADD + ButtonDiplomacy.iDiploWidth + this.getTextWidthU() + iTranslateX, this.getPosY() + this.getHeightE() / 2 - this.getTextHeight() - CFG.PADD / 2 + iTranslateY, CFG.COLOR_TEXT_NUM_OF_PROVINCES);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sDate, this.getPosXE() + CFG.PADD + ButtonDiplomacy.iDiploWidth + iTranslateX, this.getPosY() + this.getHeightE() / 2 + CFG.PADD / 2 + iTranslateY, CFG.COLOR_TEXT_GRAY_NS_HOVER);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sEconomy, this.getPosXE() + CFG.PADD + ButtonDiplomacy.iDiploWidth + this.iDateWidth + iTranslateX, this.getPosY() + this.getHeightE() / 2 + CFG.PADD / 2 + iTranslateY, CFG.COLOR_ECONOMY);
    }

    @Override
    public void setMin(int iMin) {
        try {
            this.sEconomy = "+" + CFG.getNumberWthSpaces("" + iMin) + " [+" + CFG.getPercentage_Max100(iMin, CFG.core.getProv(Menu_InGame_Invest.iProvinceID).getEco(), 4) + "%]";
        }
        catch (IndexOutOfBoundsException ex) {
            this.sEconomy = "+" + CFG.getNumberWthSpaces("" + iMin);
        }
        catch (ArithmeticException ex) {
            this.sEconomy = "+" + CFG.getNumberWthSpaces("" + iMin);
        }
        CFG.glyphLay.setText(CFG.fontMain.get(this.fontID), this.sEconomy);
        this.iEconomyWidth = (int)CFG.glyphLay.width;
    }
}
