package age.of.civilizations2.jakowski.lukasz.Sliders.InGame.Clear;

import age.of.civilizations2.jakowski.lukasz.AI.AI_Playstyle.AIPlaystyle;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Core.Core;
import age.of.civilizations2.jakowski.lukasz.IMGManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Renderer;
import age.of.civilizations2.jakowski.lukasz.Sliders.Slider;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Slider_InGame_Clear_Flag_Tribute
extends Slider {
    public int iCivID;
    public static final Color bgColor = new Color(0.0f, 0.0f, 0.0f, 0.3f);
    private String sTributePaid = "";
    private int iTributeWidth = 0;
    private Color oColorTribute = CFG.COLOR_GOLD;
    private int iCivNameWidth = 0;
    public String sLiberty;
    public int iLibertyWidth;
    public String sLibertyValue;
    public int iLibertyValueWidth;
    public String sLibertyChange = "";
    private Color oColorLibertyChange = CFG.COLOR_NEUTRAL;

    public Slider_InGame_Clear_Flag_Tribute(int iCivID, String sText, int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
        this.initSlider(sText, iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
        this.iCivID = iCivID;
        CFG.glyphLay.setText(CFG.fontMain.get(this.fontID), sText);
        this.iCivNameWidth = (int)CFG.glyphLay.width;
        this.sLiberty = CFG.lang.get("LibertyDesire") + ": ";
        CFG.glyphLay.setText(CFG.fontMain.get(this.fontID), this.sLiberty);
        this.iLibertyWidth = (int)CFG.glyphLay.width;
        this.sLibertyValue = "" + CFG.getPrecision2(CFG.core.getCiv(iCivID).getVassalLibertyDesire(), 100) + "%";
        CFG.glyphLay.setText(CFG.fontMain.get(this.fontID), this.sLibertyValue);
        this.iLibertyValueWidth = (int)CFG.glyphLay.width;
        this.updateLibertyChange();
    }

    @Override
    public void drawE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        this.drawSliderBG_UpdateAnimation();
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_BLUE.r, CFG.COLOR_GRADIENT_BLUE.g, CFG.COLOR_GRADIENT_BLUE.b, 0.25f));
        IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosXE() - CFG.PADD + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.pix255).getHeight() + iTranslateY, this.getWidthE() + CFG.PADD * 2, this.getHeightE());
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_BLUE.r, CFG.COLOR_GRADIENT_BLUE.g, CFG.COLOR_GRADIENT_BLUE.b, 0.8f));
        IMGManager.getIMG(Images.pattern).draw2O(oSB, this.getPosXE() - CFG.PADD + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.pattern).getHeight() + iTranslateY, this.getWidthE() + CFG.PADD * 2, this.getHeightE());
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
        oSB.setColor(new Color(CFG.COLOR_NEW_GAME_EDGE_LINE.r, CFG.COLOR_NEW_GAME_EDGE_LINE.g, CFG.COLOR_NEW_GAME_EDGE_LINE.b, 0.675f));
        IMGManager.getIMG(Images.line32Off1).drawO(oSB, this.getPosXE() + this.iCurrentPosX + this.iDifference_CurrentPosX + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD - this.getSliderHeight() + 1 - IMGManager.getIMG(Images.line32Off1).getHeight() + iTranslateY, 1, this.getSliderHeight() - 2);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        IMGManager.getIMG(Images.line32Off1).drawO(oSB, this.getPosXE() - 1 + this.iCurrentPosX + this.iDifference_CurrentPosX + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD - this.getSliderHeight() + 1 - IMGManager.getIMG(Images.line32Off1).getHeight() + iTranslateY, 1, this.getSliderHeight() - 2);
        IMGManager.getIMG(Images.line32Off1).drawO(oSB, this.getPosXE() + 1 + this.iCurrentPosX + this.iDifference_CurrentPosX + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD - this.getSliderHeight() + 1 - IMGManager.getIMG(Images.line32Off1).getHeight() + iTranslateY, 1, this.getSliderHeight() - 2);
        oSB.setColor(Color.WHITE);
        try {
            Core.drawFlagRect(oSB, this.getPosXE() + CFG.PADD + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD * 2 - this.getSliderHeight() - IMGManager.getIMG(Images.flagRect2).getHeight() + iTranslateY, this.iCivID);
        }
        catch (Exception exception) {
            // empty catch block
        }
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextE(), this.getPosXE() + CFG.PADD * 2 + IMGManager.getIMG(Images.flagRect2).getWidth() + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD * 2 - this.getSliderHeight() - this.getTextHeight() + iTranslateY, this.getColor(isActive));
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sTributePaid, this.getPosXE() + this.iCivNameWidth + CFG.PADD + CFG.PADD * 2 + IMGManager.getIMG(Images.flagRect2).getWidth() + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD * 2 - this.getSliderHeight() - this.getTextHeight() + iTranslateY, this.oColorTribute);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getDrawText(), this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - this.getTextWidthU() + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD * 2 - this.getSliderHeight() - this.getTextHeight() + iTranslateY, CFG.COLOR_TEXT_GRAY_NS);
        IMGManager.getIMG(Images.topGold()).drawO(oSB, this.getPosXE() + this.iCivNameWidth + this.iTributeWidth + CFG.PADD / 2 + CFG.PADD + CFG.PADD * 2 + IMGManager.getIMG(Images.flagRect2).getWidth() + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD * 2 - this.getSliderHeight() - this.getTextHeight() / 2 - (int)((float)IMGManager.getIMG(Images.topGold()).getHeight() * this.getImageScale(Images.topGold(), 1.0f)) / 2 - IMGManager.getIMG(Images.topGold()).getHeight() + iTranslateY, (int)((float)IMGManager.getIMG(Images.topGold()).getWidth() * this.getImageScale(Images.topGold(), 1.0f)), (int)((float)IMGManager.getIMG(Images.topGold()).getHeight() * this.getImageScale(Images.topGold(), 1.0f)));
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sLiberty, this.getPosXE() + this.iCivNameWidth + this.iTributeWidth + CFG.PADD / 2 + CFG.PADD + CFG.PADD * 3 + (int)((float)IMGManager.getIMG(Images.topGold()).getWidth() * this.getImageScale(Images.topGold(), 1.0f)) + IMGManager.getIMG(Images.flagRect2).getWidth() + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD * 2 - this.getSliderHeight() - this.getTextHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColor(isActive));
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sLibertyValue, this.getPosXE() + this.iCivNameWidth + this.iTributeWidth + CFG.PADD / 2 + CFG.PADD + CFG.PADD * 3 + this.iLibertyWidth + (int)((float)IMGManager.getIMG(Images.topGold()).getWidth() * this.getImageScale(Images.topGold(), 1.0f)) + IMGManager.getIMG(Images.flagRect2).getWidth() + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD * 2 - this.getSliderHeight() - this.getTextHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, CFG.COLOR_TEXT_NUM_OF_PROVINCES);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sLibertyChange, this.getPosXE() + this.iCivNameWidth + this.iTributeWidth + CFG.PADD / 2 + CFG.PADD + CFG.PADD * 4 + this.iLibertyWidth + this.iLibertyValueWidth + (int)((float)IMGManager.getIMG(Images.topGold()).getWidth() * this.getImageScale(Images.topGold(), 1.0f)) + IMGManager.getIMG(Images.flagRect2).getWidth() + iTranslateX, this.getPosY() + this.getHeightE() - CFG.PADD * 2 - this.getSliderHeight() - this.getTextHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, this.oColorLibertyChange);
    }

    private final float getImageScale(int nImageHeight) {
        return (float)CFG.TEXT_HEIGHT_DEFAULT / (float)nImageHeight < 1.0f ? (float)CFG.TEXT_HEIGHT_DEFAULT / (float)nImageHeight : 1.0f;
    }

    @Override
    public Color getColorLEFT() {
        try {
            return new Color((float)CFG.core.getCiv(this.iCivID).getR() / 255.0f, (float)CFG.core.getCiv(this.iCivID).getG() / 255.0f, (float)CFG.core.getCiv(this.iCivID).getB() / 255.0f, 0.525f);
        }
        catch (IndexOutOfBoundsException ex) {
            return new Color(CFG.COLOR_GRADIENT_BLUE.r, CFG.COLOR_GRADIENT_BLUE.g, CFG.COLOR_GRADIENT_BLUE.b, 0.75f);
        }
    }

    public Color getColor(boolean isActive) {
        return isActive ? new Color(0.71f, 0.71f, 0.71f, 1.0f) : (this.getIsHovered() ? new Color(0.82f, 0.82f, 0.82f, 1.0f) : Color.WHITE);
    }

    @Override
    public String getDrawText() {
        return "" + this.getCurr();
    }

    public int getSliderHeight() {
        return CFG.PADD * 3;
    }

    @Override
    public void setCurr(int nCurrent) {
        super.setCurr(nCurrent);
        int tempTribute = (int)CFG.gameUpdate.getIncomeVassals(CFG.core.getCiv(this.iCivID).getPuppetOfCiv(), this.iCivID);
        this.sTributePaid = tempTribute > 0 ? "+" + CFG.getNumberWthSpaces("" + tempTribute) : "0";
        this.oColorTribute = tempTribute > 0 ? CFG.COLOR_GOLD : CFG.COLOR_NEUTRAL2;
        CFG.glyphLay.setText(CFG.fontMain.get(this.fontID), this.sTributePaid);
        this.iTributeWidth = (int)CFG.glyphLay.width;
    }

    @Override
    public void actionElem(int iID) {
        int tempTribute = (int)CFG.gameUpdate.getIncomeVassals(CFG.core.getCiv(this.iCivID).getPuppetOfCiv(), this.iCivID);
        this.sTributePaid = tempTribute > 0 ? "+" + CFG.getNumberWthSpaces("" + tempTribute) : "0";
        this.oColorTribute = tempTribute > 0 ? CFG.COLOR_GOLD : CFG.COLOR_NEUTRAL2;
        CFG.glyphLay.setText(CFG.fontMain.get(this.fontID), this.sTributePaid);
        this.iTributeWidth = (int)CFG.glyphLay.width;
        this.updateLibertyChange();
    }

    public void updateLibertyChange() {
        float value = AIPlaystyle.getLibertyDesireChange_JustInfo(this.iCivID);
        this.sLibertyChange = "[" + (value > 0.0f ? "+" : "") + CFG.getPrecision2(value, 1000) + "]";
        this.oColorLibertyChange = value > 0.0f ? CFG.COLOR_NEGATIVE_2 : (value < 0.0f ? CFG.COLOR_POSITIVE : CFG.COLOR_NEUTRAL);
    }

    public float getImageScale(int nImageID, float nTextScale) {
        return (float)CFG.TEXT_HEIGHT_DEFAULT * nTextScale / (float)IMGManager.getIMG(nImageID).getHeight();
    }
}
