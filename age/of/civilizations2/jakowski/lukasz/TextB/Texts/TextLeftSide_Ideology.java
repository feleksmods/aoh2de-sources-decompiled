package age.of.civilizations2.jakowski.lukasz.TextB.Texts;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.IMGManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Renderer;
import age.of.civilizations2.jakowski.lukasz.TextB.Texts.TextLeftSide;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextLeftSide_Ideology
extends TextLeftSide {
    private int iCurrent = 0;
    public int iWidth2 = 1;

    public TextLeftSide_Ideology(String sText, int iPosX, int iPosY, int iWidth) {
        super(sText, iPosX, iPosY, iWidth);
        this.iWidth2 = iWidth;
    }

    @Override
    public int getPosXE() {
        return this.iPosX - this.getWidthE();
    }

    @Override
    public int getWidthE() {
        return this.iWidth2;
    }

    @Override
    public void drawE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        if (this.iCurrent < 0) {
            IMGManager.getIMG(Images.hreCrownScaled).drawO(oSB, this.getPosXE() + this.getWidthE() - this.iTextWidth - CFG.PADD - (int)((float)IMGManager.getIMG(Images.hreCrownScaled).getWidth() * this.getImageScale(IMGManager.getIMG(Images.hreCrownScaled).getHeight())) + iTranslateX, this.getPosY() + (this.getHeightE() - (int)((float)IMGManager.getIMG(Images.hreCrownScaled).getHeight() * this.getImageScale(IMGManager.getIMG(Images.hreCrownScaled).getHeight()) * 6.0f / 5.0f)) / 2 - IMGManager.getIMG(Images.hreCrownScaled).getHeight() + iTranslateY, (int)((float)IMGManager.getIMG(Images.hreCrownScaled).getWidth() * this.getImageScale(IMGManager.getIMG(Images.hreCrownScaled).getHeight())), (int)((float)IMGManager.getIMG(Images.hreCrownScaled).getHeight() * this.getImageScale(IMGManager.getIMG(Images.hreCrownScaled).getHeight())));
            Renderer.drawTextWithShadow(oSB, this.fontID, this.sText, this.getPosXE() + this.getWidthE() - this.iTextWidth + iTranslateX, this.getPosY() + (this.getHeightE() - this.iTextHeight) / 2 + iTranslateY, this.getColor(isActive));
        } else {
            CFG.ideologiesMgr.getIdeologyID(this.iCurrent).getCrownImageScaled().draw(oSB, this.getPosXE() + this.getWidthE() - this.iTextWidth - CFG.PADD - (int)((float)CFG.ideologiesMgr.getIdeologyID(this.iCurrent).getCrownImageScaled().getWidth() * this.getImageScale(CFG.ideologiesMgr.getIdeologyID(this.iCurrent).getCrownImageScaled().getHeight())) + iTranslateX, this.getPosY() + (this.getHeightE() - (int)((float)CFG.ideologiesMgr.getIdeologyID(this.iCurrent).getCrownImageScaled().getHeight() * this.getImageScale(CFG.ideologiesMgr.getIdeologyID(this.iCurrent).getCrownImageScaled().getHeight()))) / 2 + iTranslateY, (int)((float)CFG.ideologiesMgr.getIdeologyID(this.iCurrent).getCrownImageScaled().getWidth() * this.getImageScale(CFG.ideologiesMgr.getIdeologyID(this.iCurrent).getCrownImageScaled().getHeight())), (int)((float)CFG.ideologiesMgr.getIdeologyID(this.iCurrent).getCrownImageScaled().getHeight() * this.getImageScale(CFG.ideologiesMgr.getIdeologyID(this.iCurrent).getCrownImageScaled().getHeight())));
            Renderer.drawTextWithShadow(oSB, this.fontID, this.sText, this.getPosXE() + this.getWidthE() - this.iTextWidth + iTranslateX, this.getPosY() + (this.getHeightE() - this.iTextHeight) / 2 + iTranslateY, this.getColor(isActive));
        }
    }

    private final float getImageScale(int nImageHeight) {
        return (float)CFG.TEXT_HEIGHT_DEFAULT / (float)nImageHeight < 1.0f ? (float)CFG.TEXT_HEIGHT_DEFAULT / (float)nImageHeight : 1.0f;
    }

    @Override
    public Color getColor(boolean isActive) {
        return isActive ? CFG.COLOR_TEXT_GRAY_NS_ACTIVE : (this.getIsClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_GRAY_NS_HOVER : CFG.COLOR_TEXT_GRAY_NS) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
    }

    @Override
    public void setTextE(String sText) {
        block7: {
            try {
                this.sText = sText;
                if (sText != null && sText.length() > 0) {
                    CFG.glyphLay.setText(CFG.fontMain.get(this.fontID), sText);
                    this.iTextWidth = (int)CFG.glyphLay.width;
                    this.iTextHeight = (int)CFG.glyphLay.height;
                    int crownW = CFG.PADD;
                    try {
                        crownW = (int)((float)CFG.ideologiesMgr.getIdeologyID(this.iCurrent).getCrownImageScaled().getWidth() * this.getImageScale(CFG.ideologiesMgr.getIdeologyID(this.iCurrent).getCrownImageScaled().getHeight())) + CFG.PADD;
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                    int tWMax = 0;
                    while (this.iTextWidth > this.getWidthE() - crownW && this.getTextE().length() > 5 && ++tWMax < 100) {
                        this.sText = this.getTextE().substring(0, Math.max(1, this.getTextE().length() - 3)) + "..";
                        CFG.glyphLay.setText(CFG.fontMain.get(this.fontID), this.sText);
                        this.iTextWidth = (int)CFG.glyphLay.width;
                        this.iTextHeight = (int)CFG.glyphLay.height;
                    }
                    if (this.getHeightE() < this.iTextHeight) {
                        this.setHeightE(this.iTextHeight);
                    }
                    break block7;
                }
                this.iTextWidth = 0;
                this.iTextHeight = CFG.TEXT_HEIGHT_DEFAULT;
            }
            catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
    }

    @Override
    public int getCurr() {
        return this.iCurrent;
    }

    @Override
    public void setCurr(int nCurrent) {
        this.iCurrent = nCurrent;
    }
}
