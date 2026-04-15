package age.of.civilizations2.jakowski.lukasz.Sliders.InGame.Diplomacy;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.IMGManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Renderer;
import age.of.civilizations2.jakowski.lukasz.Sliders.Slider;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Slider_Relations
extends Slider {
    public Slider_Relations(int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
        super.initSlider("", iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
    }

    @Override
    public void drawE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        if (this.getCurr() <= -100) {
            oSB.setColor(0.6f, 0.0f, 0.0f, 0.8f);
            IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosXE() + this.iCurrentPosX + iTranslateX, this.getPosY() - 1 + iTranslateY, this.getWidthE() - this.iCurrentPosX, this.getHeightE());
        } else if (this.getCurr() == 0) {
            oSB.setColor(0.97f, 0.97f, 0.97f, this.getIsClickable() ? 0.68f : 0.5f);
            IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() - 1 + iTranslateY, this.getWidthE(), this.getHeightE());
        } else if (this.getCurr() > 0) {
            oSB.setColor(0.97f, 0.97f, 0.97f, 0.68f);
            IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosXE() + this.iCurrentPosX + iTranslateX, this.getPosY() - 1 + iTranslateY, this.getWidthE() - this.iCurrentPosX, this.getHeightE());
            oSB.setColor(CFG.getRelationColor(this.getCurr(), 0.68f));
            IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() - 1 + iTranslateY, this.iCurrentPosX, this.getHeightE());
            oSB.setColor(CFG.getColorStep(CFG.getRelationColor(this.getCurr(), 1.0f), new Color(0.16862746f, 0.3019608f, 0.5137255f, 1.0f), -this.getCurr(), 100, 0.68f));
            IMGManager.getIMG(Images.sliderGradient).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.sliderGradient).getHeight() + iTranslateY, this.iCurrentPosX, this.getHeightE());
        } else {
            oSB.setColor(0.97f, 0.97f, 0.97f, 0.68f);
            IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() - 1 + iTranslateY, this.iCurrentPosX, this.getHeightE());
            oSB.setColor(CFG.getRelationColor(this.getCurr(), 0.68f));
            IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosXE() + this.iCurrentPosX + iTranslateX, this.getPosY() - 1 + iTranslateY, this.getWidthE() - this.iCurrentPosX, this.getHeightE());
            oSB.setColor(CFG.getColorStep(CFG.getRelationColor(this.getCurr(), 1.0f), new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getB(), CFG.ALPHA_DIPLOMACY), -this.getCurr(), 100, 0.68f));
            IMGManager.getIMG(Images.sliderGradient).drawO(oSB, this.getPosXE() + this.iCurrentPosX + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.sliderGradient).getHeight() + iTranslateY, this.getWidthE() - this.iCurrentPosX, this.getHeightE(), true, false);
        }
        this.drawSliderText(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
        oSB.setColor(new Color(CFG.COLOR_SLIDER_BORDER.r, CFG.COLOR_SLIDER_BORDER.g, CFG.COLOR_SLIDER_BORDER.b, this.getIsClickable() ? 1.0f : 0.5f));
        IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosXE() + this.iCurrentPosX - 1 + iTranslateX, this.getPosY() - 1 + iTranslateY, 1, this.getHeightE());
        oSB.setColor(Color.WHITE);
        this.drawSliderBorder(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, this.getIsClickable() ? 1.0f : 0.5f));
        CFG.core.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getFlagC().drawO(oSB, this.getPosXE() + this.getWidthE() / 2 - CFG.CIV_FLAG_WIDTH + iTranslateX, this.getPosY() - CFG.core.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getFlagC().getHeight() + this.getHeightE() - CFG.CIV_FLAG_HEIGHT - 1 + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        IMGManager.getIMG(Images.flagRectSmall).drawO(oSB, this.getPosXE() + this.getWidthE() / 2 - CFG.CIV_FLAG_WIDTH + iTranslateX, this.getPosY() + this.getHeightE() - CFG.CIV_FLAG_HEIGHT - 1 + iTranslateY);
        if (CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID2 > 0) {
            CFG.core.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID2).getFlagC().drawO(oSB, this.getPosXE() + this.getWidthE() / 2 + iTranslateX, this.getPosY() + this.getHeightE() - CFG.core.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID2).getFlagC().getHeight() - CFG.CIV_FLAG_HEIGHT - 1 + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        } else {
            IMGManager.getIMG(Images.randomCivilizationFlag).drawO(oSB, this.getPosXE() + this.getWidthE() / 2 + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.randomCivilizationFlag).getHeight() + this.getHeightE() - CFG.CIV_FLAG_HEIGHT - 1 + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        }
        IMGManager.getIMG(Images.flagRectSmall).drawO(oSB, this.getPosXE() + this.getWidthE() / 2 + iTranslateX, this.getPosY() + this.getHeightE() - CFG.CIV_FLAG_HEIGHT - 1 + iTranslateY);
        IMGManager.getIMG(Images.slider_rect_edge).draw2O(oSB, this.getPosXE() + this.getWidthE() / 2 - CFG.CIV_FLAG_WIDTH + iTranslateX, this.getPosY() + this.getHeightE() - CFG.CIV_FLAG_HEIGHT - IMGManager.getIMG(Images.slider_rect_edge).getHeight() - 1 + iTranslateY, CFG.CIV_FLAG_WIDTH * 2 - IMGManager.getIMG(Images.slider_rect_edge).getWidth(), CFG.CIV_FLAG_HEIGHT);
        IMGManager.getIMG(Images.slider_rect_edge).draw2O(oSB, this.getPosXE() + this.getWidthE() / 2 + CFG.CIV_FLAG_WIDTH - IMGManager.getIMG(Images.slider_rect_edge).getWidth() + iTranslateX, this.getPosY() + this.getHeightE() - CFG.CIV_FLAG_HEIGHT - IMGManager.getIMG(Images.slider_rect_edge).getHeight() - 1 + iTranslateY, IMGManager.getIMG(Images.slider_rect_edge).getWidth(), CFG.CIV_FLAG_HEIGHT, true, false);
    }

    @Override
    public void drawSliderText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        if (this.getCurr() <= -100) {
            Renderer.drawText(oSB, this.fontID, CFG.sAtWar + " " + CFG.core.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID2).getCivName() + " - " + CFG.core.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getCivName(), this.getPosXE() + CFG.PADD * 2 + iTranslateX, this.getPosY() + this.getHeightE() / 2 - this.getTextHeight() / 2 + iTranslateY, new Color(0.945f, 0.945f, 0.945f, 1.0f));
            Renderer.drawText(oSB, this.fontID, this.getDrawText(), this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - this.getTextWidthU() + iTranslateX, this.getPosY() + this.getHeightE() / 2 - this.getTextHeight() / 2 + iTranslateY, new Color(0.945f, 0.945f, 0.945f, 1.0f));
        } else {
            Renderer.drawText(oSB, this.fontID, this.getDrawText(), this.getPosXE() + CFG.PADD * 2 + iTranslateX, this.getPosY() + this.getHeightE() / 2 - this.getTextHeight() / 2 + iTranslateY, new Color(0.945f, 0.945f, 0.945f, 1.0f));
            Renderer.drawText(oSB, this.fontID, this.getDrawText(), this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - this.getTextWidthU() + iTranslateX, this.getPosY() + this.getHeightE() / 2 - this.getTextHeight() / 2 + iTranslateY, new Color(0.945f, 0.945f, 0.945f, 1.0f));
        }
    }
}
