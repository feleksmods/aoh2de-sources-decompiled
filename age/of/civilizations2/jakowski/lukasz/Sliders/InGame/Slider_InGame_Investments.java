package age.of.civilizations2.jakowski.lukasz.Sliders.InGame;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.IMGManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menus.Budget.Menu_InGame_Budget;
import age.of.civilizations2.jakowski.lukasz.Renderer;
import age.of.civilizations2.jakowski.lukasz.Sliders.InGame.Slider_InGame;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Slider_InGame_Investments
extends Slider_InGame {
    public int iconWidth;
    public int iconHeight;

    public Slider_InGame_Investments(String sText, int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
        super(sText, iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
    }

    @Override
    public void setCurr(int nCurrent) {
        super.setCurr(nCurrent);
        int tempSpend = (int)CFG.gameUpdate.getInvestmentsSpending(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).iBudget);
        this.drawSpendings = tempSpend != 0;
        this.sSpendings = CFG.getNumberWthSpaces("" + tempSpend);
        CFG.glyphLay.setText(CFG.fontMain.get(this.fontID), this.sSpendings);
        this.iSpendingsWidth = (int)CFG.glyphLay.width;
        int nIMGID = Images.development;
        float iconScale = Slider_InGame_Investments.getImageScale(nIMGID);
        this.iconWidth = (int)((float)IMGManager.getIMG(nIMGID).getWidth() * iconScale);
        this.iconHeight = (int)((float)IMGManager.getIMG(nIMGID).getHeight() * iconScale);
    }

    @Override
    public void drawE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        if ((float)this.getCurr() < CFG.ideologiesMgr.getInvestments(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getIdeology(), CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) * 100.0f) {
            oSB.setColor(new Color(CFG.COLOR_NEGATIVE_1.r, CFG.COLOR_NEGATIVE_1.g, CFG.COLOR_NEGATIVE_1.b, 0.275f));
            IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD - this.getSliderHeight() - IMGManager.getIMG(Images.pix255).getHeight() + iTranslateY, (int)((float)this.getWidthE() * CFG.ideologiesMgr.getInvestments(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getIdeology(), CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())), this.getSliderHeight());
            IMGManager.getIMG(Images.sliderGradient).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD - this.getSliderHeight() - IMGManager.getIMG(Images.sliderGradient).getHeight() + iTranslateY, (int)((float)this.getWidthE() * CFG.ideologiesMgr.getInvestments(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getIdeology(), CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())), this.getSliderHeight());
            oSB.setColor(Color.WHITE);
        }
        super.drawE(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
        oSB.setColor(new Color(CFG.COLOR_NEW_GAME_EDGE_LINE.r, CFG.COLOR_NEW_GAME_EDGE_LINE.g, CFG.COLOR_NEW_GAME_EDGE_LINE.b, 0.9f));
        IMGManager.getIMG(Images.pix255).draw2O(oSB, this.getPosXE() + (int)((float)this.getWidthE() * CFG.ideologiesMgr.getInvestments(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getIdeology(), CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())) + iTranslateX, this.getPosY() + 1 + this.getHeightE() - CFG.PADD - this.getSliderHeight() - IMGManager.getIMG(Images.pix255).getHeight() + iTranslateY, 1, this.getSliderHeight() - 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.375f));
        IMGManager.getIMG(Images.pix255).draw2O(oSB, this.getPosXE() - 1 + (int)((float)this.getWidthE() * CFG.ideologiesMgr.getInvestments(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getIdeology(), CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())) + iTranslateX, this.getPosY() + 1 + this.getHeightE() - CFG.PADD - this.getSliderHeight() - IMGManager.getIMG(Images.pix255).getHeight() + iTranslateY, 1, this.getSliderHeight() - 1);
        IMGManager.getIMG(Images.pix255).draw2O(oSB, this.getPosXE() + 1 + (int)((float)this.getWidthE() * CFG.ideologiesMgr.getInvestments(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getIdeology(), CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())) + iTranslateX, this.getPosY() + 1 + this.getHeightE() - CFG.PADD - this.getSliderHeight() - IMGManager.getIMG(Images.pix255).getHeight() + iTranslateY, 1, this.getSliderHeight() - 1);
        oSB.setColor(Color.WHITE);
    }

    public static final float getImageScale(int iImageID) {
        return Math.min(1.0f, (float)CFG.TEXT_HEIGHT_DEFAULT / (float)IMGManager.getIMG(iImageID).getHeight());
    }

    @Override
    public void drawTextLeft(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        IMGManager.getIMG(Images.development).draw(oSB, this.getPosXE() + CFG.PADD + (Menu_InGame_Budget.maxIconWidth - this.iconWidth) / 2 + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD * 2 - this.getSliderHeight() - this.iconHeight + iTranslateY, this.iconWidth, this.iconHeight);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextE(), this.getPosXE() + CFG.PADD * 2 + Menu_InGame_Budget.maxIconWidth + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD * 2 - this.getSliderHeight() - CFG.TEXT_HEIGHT_DEFAULT + iTranslateY, this.getColor(isActive));
    }
}
