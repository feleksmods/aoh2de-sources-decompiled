package age.of.civilizations2.jakowski.lukasz.Sliders.InGame;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.IMGManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menus.Budget.Menu_InGame_Budget;
import age.of.civilizations2.jakowski.lukasz.Renderer;
import age.of.civilizations2.jakowski.lukasz.Sliders.Slider;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Slider_InGame_Taxes
extends Slider {
    public static final Color bgColor = new Color(0.0f, 0.0f, 0.0f, 0.3f);
    private String sTextLeft;
    private String sHappiness;
    private int iHappinessWidth = 0;
    private int iImageID;
    private String sText2;
    private int iText2Width = 0;
    private Color tColor;
    private String sText3;
    private int iText3Width = 0;
    private String sText4;
    private int iText4Width = 0;
    public int iconWidth;
    public int iconHeight;

    public Slider_InGame_Taxes(String sText, String nTextLeft, int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
        this.initSlider(sText, iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
        this.setTextE(sText);
        this.sText2 = " " + CFG.lang.get("PerTurn");
        CFG.glyphLay.setText(CFG.fontMain.get(this.fontID), this.sText2);
        this.iText2Width = (int)CFG.glyphLay.width;
        this.sTextLeft = nTextLeft;
        this.sText3 = " " + CFG.lang.get("Happiness") + ": ";
        this.sText4 = "" + CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getHappiness() + "%";
        CFG.glyphLay.setText(CFG.fontMain.get(this.fontID), this.sText3);
        this.iText3Width = (int)CFG.glyphLay.width;
        CFG.glyphLay.setText(CFG.fontMain.get(this.fontID), this.sText4);
        this.iText4Width = (int)CFG.glyphLay.width;
        int nIMGID = Images.topGold();
        float iconScale = Slider_InGame_Taxes.getImageScale(nIMGID);
        this.iconWidth = (int)((float)IMGManager.getIMG(nIMGID).getWidth() * iconScale);
        this.iconHeight = (int)((float)IMGManager.getIMG(nIMGID).getHeight() * iconScale);
    }

    public static final float getImageScale(int iImageID) {
        return Math.min(1.0f, (float)CFG.TEXT_HEIGHT_DEFAULT / (float)IMGManager.getIMG(iImageID).getHeight());
    }

    @Override
    public void drawE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        this.drawSliderBG_UpdateAnimation();
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_BLUE.r, CFG.COLOR_GRADIENT_BLUE.g, CFG.COLOR_GRADIENT_BLUE.b, 0.25f));
        IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosXE() - CFG.PADD + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.pix255).getHeight() + iTranslateY, this.getWidthE() + CFG.PADD * 2, this.getHeightE());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        IMGManager.getIMG(Images.gradient).drawO(oSB, this.getPosXE() - CFG.PADD + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gradient).getHeight() + iTranslateY, this.getWidthE() + CFG.PADD * 2, this.getHeightE() * 3 / 5, false, false);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.275f));
        IMGManager.getIMG(Images.sliderGradient).drawO(oSB, this.getPosXE() - CFG.PADD + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.sliderGradient).getHeight() + iTranslateY, this.getWidthE() / 4, this.getHeightE(), false, false);
        IMGManager.getIMG(Images.sliderGradient).drawO(oSB, this.getPosXE() - CFG.PADD + this.getWidthE() + CFG.PADD * 2 - this.getWidthE() / 4 + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.sliderGradient).getHeight() + iTranslateY, this.getWidthE() / 4, this.getHeightE(), true, false);
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_BLUE.r, CFG.COLOR_GRADIENT_BLUE.g, CFG.COLOR_GRADIENT_BLUE.b, 0.65f));
        IMGManager.getIMG(Images.line32Off1).drawO(oSB, this.getPosXE() - CFG.PADD + iTranslateX, this.getPosY() + 1 - IMGManager.getIMG(Images.line32Off1).getHeight() + iTranslateY, this.getWidthE() + CFG.PADD * 2, 1);
        IMGManager.getIMG(Images.line32Off1).drawO(oSB, this.getPosXE() - CFG.PADD + iTranslateX, this.getPosY() + this.getHeightE() - 2 - IMGManager.getIMG(Images.line32Off1).getHeight() + iTranslateY, this.getWidthE() + CFG.PADD * 2, 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.175f));
        IMGManager.getIMG(Images.line32Off1).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD - this.getSliderHeight() - IMGManager.getIMG(Images.line32Off1).getHeight() + iTranslateY, this.getWidthE(), this.getSliderHeight());
        oSB.setColor(new Color(this.getColorLEFT().r, this.getColorLEFT().g, this.getColorLEFT().b, this.getColorLEFT().a));
        IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD - this.getSliderHeight() - IMGManager.getIMG(Images.pix255).getHeight() + iTranslateY, this.iCurrentPosX + this.iDifference_CurrentPosX, this.getSliderHeight());
        IMGManager.getIMG(Images.gradient).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD - this.getSliderHeight() - IMGManager.getIMG(Images.gradient).getHeight() + iTranslateY, this.iCurrentPosX + this.iDifference_CurrentPosX, this.getSliderHeight());
        oSB.setColor(new Color(this.getColorLEFT().r, this.getColorLEFT().g, this.getColorLEFT().b, this.getColorLEFT().a * 0.92f));
        IMGManager.getIMG(Images.line32Off1).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD - this.getSliderHeight() - IMGManager.getIMG(Images.line32Off1).getHeight() + iTranslateY, this.iCurrentPosX + this.iDifference_CurrentPosX, this.getSliderHeight());
        for (int i = 1; i < 10; ++i) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.04f));
            IMGManager.getIMG(Images.line32Vertical).draw2O(oSB, this.getPosXE() + this.getWidthE() / 10 * i + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD - this.getSliderHeight() - IMGManager.getIMG(Images.line32Vertical).getHeight() + iTranslateY, 1, this.getSliderHeight());
        }
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        IMGManager.getIMG(Images.gradient).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD - this.getSliderHeight() - IMGManager.getIMG(Images.gradient).getHeight() + iTranslateY, this.getWidthE(), CFG.PADD);
        IMGManager.getIMG(Images.gradient).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD - CFG.PADD + 1 - IMGManager.getIMG(Images.gradient).getHeight() + iTranslateY, this.getWidthE(), CFG.PADD, false, true);
        oSB.setColor(new Color(CFG.COLOR_NEW_GAME_EDGE_LINE.r, CFG.COLOR_NEW_GAME_EDGE_LINE.g, CFG.COLOR_NEW_GAME_EDGE_LINE.b, 0.7f));
        IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD - this.getSliderHeight() - IMGManager.getIMG(Images.pix255).getHeight() + iTranslateY, this.getWidthE(), 1);
        IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD - IMGManager.getIMG(Images.pix255).getHeight() + iTranslateY, this.getWidthE(), 1);
        if (isActive) {
            oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.045f));
            IMGManager.getIMG(Images.line32Off1).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD - this.getSliderHeight() - IMGManager.getIMG(Images.line32Off1).getHeight() + iTranslateY, this.getWidthE(), 1);
            IMGManager.getIMG(Images.line32Off1).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD - IMGManager.getIMG(Images.line32Off1).getHeight() + iTranslateY, this.getWidthE(), 1);
        }
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        IMGManager.getIMG(Images.line32Off1).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD - this.getSliderHeight() + 1 - IMGManager.getIMG(Images.line32Off1).getHeight() + iTranslateY, this.getWidthE(), 1);
        IMGManager.getIMG(Images.line32Off1).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD - 1 - IMGManager.getIMG(Images.line32Off1).getHeight() + iTranslateY, this.getWidthE(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD - this.getSliderHeight() - 1 - IMGManager.getIMG(Images.pix255).getHeight() + iTranslateY, this.getWidthE(), 1);
        IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD + 1 - IMGManager.getIMG(Images.pix255).getHeight() + iTranslateY, this.getWidthE(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.8f));
        IMGManager.getIMG(Images.sliderGradient).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD - this.getSliderHeight() - IMGManager.getIMG(Images.sliderGradient).getHeight() + iTranslateY, this.getWidthE() / 8, 1);
        IMGManager.getIMG(Images.sliderGradient).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD - IMGManager.getIMG(Images.sliderGradient).getHeight() + iTranslateY, this.getWidthE() / 8, 1);
        IMGManager.getIMG(Images.sliderGradient).drawO(oSB, this.getPosXE() + this.getWidthE() - this.getWidthE() / 8 + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD - this.getSliderHeight() - IMGManager.getIMG(Images.sliderGradient).getHeight() + iTranslateY, this.getWidthE() / 8, 1, true, false);
        IMGManager.getIMG(Images.sliderGradient).drawO(oSB, this.getPosXE() + this.getWidthE() - this.getWidthE() / 8 + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD - IMGManager.getIMG(Images.sliderGradient).getHeight() + iTranslateY, this.getWidthE() / 8, 1, true, false);
        oSB.setColor(new Color(CFG.COLOR_NEW_GAME_EDGE_LINE.r, CFG.COLOR_NEW_GAME_EDGE_LINE.g, CFG.COLOR_NEW_GAME_EDGE_LINE.b, 0.9f));
        IMGManager.getIMG(Images.pix255).draw2O(oSB, this.getPosXE() + (int)((float)this.getWidthE() * CFG.ideologiesMgr.getAcceptableTaxation(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getIdeology(), CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())) + iTranslateX, this.getPosY() + 1 + this.getHeightE() - CFG.PADD - this.getSliderHeight() - IMGManager.getIMG(Images.pix255).getHeight() + iTranslateY, 1, this.getSliderHeight() - 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.375f));
        IMGManager.getIMG(Images.pix255).draw2O(oSB, this.getPosXE() - 1 + (int)((float)this.getWidthE() * CFG.ideologiesMgr.getAcceptableTaxation(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getIdeology(), CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())) + iTranslateX, this.getPosY() + 1 + this.getHeightE() - CFG.PADD - this.getSliderHeight() - IMGManager.getIMG(Images.pix255).getHeight() + iTranslateY, 1, this.getSliderHeight() - 1);
        IMGManager.getIMG(Images.pix255).draw2O(oSB, this.getPosXE() + 1 + (int)((float)this.getWidthE() * CFG.ideologiesMgr.getAcceptableTaxation(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getIdeology(), CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())) + iTranslateX, this.getPosY() + 1 + this.getHeightE() - CFG.PADD - this.getSliderHeight() - IMGManager.getIMG(Images.pix255).getHeight() + iTranslateY, 1, this.getSliderHeight() - 1);
        oSB.setColor(Color.WHITE);
        IMGManager.getIMG(Images.topGold()).draw(oSB, this.getPosXE() + CFG.PADD + (Menu_InGame_Budget.maxIconWidth - this.iconWidth) / 2 + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD * 2 - this.getSliderHeight() - this.iconHeight + iTranslateY, this.iconWidth, this.iconHeight);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sTextLeft, this.getPosXE() + CFG.PADD * 2 + Menu_InGame_Budget.maxIconWidth + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD * 2 - this.getSliderHeight() - this.getTextHeight() + iTranslateY, this.getColor(isActive));
        IMGManager.getIMG(this.iImageID).drawO(oSB, this.getPosXE() + this.getWidthE() - CFG.PADD * 3 - this.iHappinessWidth - this.iText2Width - (int)((float)IMGManager.getIMG(this.iImageID).getWidth() * this.getImageScale(1.0f, this.iImageID)) + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD * 2 - this.getSliderHeight() - (int)((float)IMGManager.getIMG(this.iImageID).getHeight() * this.getImageScale(1.0f, this.iImageID)) - IMGManager.getIMG(this.iImageID).getHeight() + iTranslateY, (int)((float)IMGManager.getIMG(this.iImageID).getWidth() * this.getImageScale(1.0f, this.iImageID)), (int)((float)IMGManager.getIMG(this.iImageID).getHeight() * this.getImageScale(1.0f, this.iImageID)));
        oSB.setColor(new Color(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.r, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.g, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.b, 0.85f));
        IMGManager.getIMG(Images.line32Vertical).drawO(oSB, this.getPosXE() + this.getWidthE() - CFG.PADD * 4 - this.iHappinessWidth - this.iText2Width - (int)((float)IMGManager.getIMG(this.iImageID).getWidth() * this.getImageScale(1.0f, this.iImageID)) + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD * 2 - this.getSliderHeight() - (int)((float)IMGManager.getIMG(this.iImageID).getHeight() * this.getImageScale(1.0f, this.iImageID)) - IMGManager.getIMG(Images.line32Vertical).getHeight() + iTranslateY, 1, (int)((float)IMGManager.getIMG(this.iImageID).getHeight() * this.getImageScale(1.0f, this.iImageID)));
        oSB.setColor(Color.WHITE);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sText4, this.getPosXE() + this.getWidthE() - CFG.PADD * 5 - this.iText4Width - this.iHappinessWidth - this.iText2Width - (int)((float)IMGManager.getIMG(this.iImageID).getWidth() * this.getImageScale(1.0f, this.iImageID)) + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD * 2 - this.getSliderHeight() - this.getTextHeight() + iTranslateY, CFG.COLOR_TEXT_NUM_OF_PROVINCES);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sText3, this.getPosXE() + this.getWidthE() - CFG.PADD * 5 - this.iText4Width - this.iText3Width - this.iHappinessWidth - this.iText2Width - (int)((float)IMGManager.getIMG(this.iImageID).getWidth() * this.getImageScale(1.0f, this.iImageID)) + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD * 2 - this.getSliderHeight() - this.getTextHeight() + iTranslateY, CFG.COLOR_TEXT_GRAY_NS);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sHappiness, this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - this.iHappinessWidth - this.iText2Width + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD * 2 - this.getSliderHeight() - this.getTextHeight() + iTranslateY, this.tColor);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sText2, this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - this.iText2Width + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD * 2 - this.getSliderHeight() - this.getTextHeight() + iTranslateY, CFG.COLOR_TEXT_GRAY_NS);
    }

    @Override
    public Color getColorLEFT() {
        return new Color(CFG.COLOR_GRADIENT_BLUE.r, CFG.COLOR_GRADIENT_BLUE.g, CFG.COLOR_GRADIENT_BLUE.b, 0.75f);
    }

    public Color getColor(boolean isActive) {
        return isActive ? new Color(0.71f, 0.71f, 0.71f, 1.0f) : (this.getIsHovered() ? new Color(0.82f, 0.82f, 0.82f, 1.0f) : Color.WHITE);
    }

    @Override
    public String getDrawText() {
        return this.getTextE();
    }

    public int getSliderHeight() {
        return CFG.PADD * 3;
    }

    private final float getImageScale(float fScale, int nImageID) {
        return (float)this.getTextHeight() * fScale / (float)IMGManager.getIMG(nImageID).getHeight();
    }

    @Override
    public final void setTextE(String sText) {
        this.sHappiness = sText.substring(0, sText.length() > 7 ? 7 : sText.length());
        CFG.glyphLay.setText(CFG.fontMain.get(this.fontID), this.sHappiness);
        this.iHappinessWidth = (int)CFG.glyphLay.width;
    }

    @Override
    public void setMax(int iMax) {
        this.iImageID = iMax == 0 ? Images.happiness : (iMax == 1 ? Images.happiness1 : Images.happiness2);
        this.tColor = iMax == 0 ? CFG.COLOR_POSITIVE : CFG.COLOR_NEGATIVE_2;
    }
}
