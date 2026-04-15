package age.of.civilizations2.jakowski.lukasz.Sliders.ZRest;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.IMGManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Sliders.Slider;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Slider_BG_ZeroInMiddle
extends Slider {
    public Slider_BG_ZeroInMiddle(int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
        super.initSlider("", iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
    }

    @Override
    public void drawE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        IMGManager.getIMG(Images.btnMenu1H).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() - CFG.PADD + iTranslateY, this.getWidthE());
        if (this.getCurr() == 0) {
            oSB.setColor(0.97f, 0.97f, 0.97f, this.getIsClickable() ? 0.68f : 0.5f);
            IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() - 1 + iTranslateY, this.getWidthE(), this.getHeightE());
        } else if (this.getCurr() > 0) {
            oSB.setColor(0.97f, 0.97f, 0.97f, 0.68f);
            IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosXE() + this.iCurrentPosX + iTranslateX, this.getPosY() - 1 + iTranslateY, this.getWidthE() - this.iCurrentPosX, this.getHeightE());
            oSB.setColor(new Color(this.getColorRIGHT().r, this.getColorRIGHT().g, this.getColorRIGHT().b, 0.68f));
            IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() - 1 + iTranslateY, this.iCurrentPosX, this.getHeightE());
            IMGManager.getIMG(Images.sliderGradient).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.sliderGradient).getHeight() + iTranslateY, this.iCurrentPosX, this.getHeightE());
        } else {
            oSB.setColor(0.97f, 0.97f, 0.97f, 0.68f);
            IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() - 1 + iTranslateY, this.iCurrentPosX, this.getHeightE());
            oSB.setColor(new Color(this.getColorLEFT().r, this.getColorLEFT().g, this.getColorLEFT().b, 0.68f));
            IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosXE() + this.iCurrentPosX + iTranslateX, this.getPosY() - 1 + iTranslateY, this.getWidthE() - this.iCurrentPosX, this.getHeightE());
            IMGManager.getIMG(Images.sliderGradient).drawO(oSB, this.getPosXE() + this.iCurrentPosX + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.sliderGradient).getHeight() + iTranslateY, this.getWidthE() - this.iCurrentPosX, this.getHeightE(), true, false);
        }
        this.drawSliderText(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
        oSB.setColor(new Color(CFG.COLOR_SLIDER_BORDER.r, CFG.COLOR_SLIDER_BORDER.g, CFG.COLOR_SLIDER_BORDER.b, 0.68f));
        IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosXE() + this.iCurrentPosX - 1 + iTranslateX, this.getPosY() - 1 + iTranslateY, 1, this.getHeightE());
        oSB.setColor(Color.WHITE);
        this.drawSliderBorder(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
    }

    @Override
    public Color getColorLEFT() {
        return CFG.COLOR_NEGATIVE_1;
    }

    @Override
    public Color getColorRIGHT() {
        return CFG.COLOR_POSITIVE;
    }
}
