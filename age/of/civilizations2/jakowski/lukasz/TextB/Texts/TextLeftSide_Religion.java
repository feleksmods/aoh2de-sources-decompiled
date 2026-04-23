package age.of.civilizations2.jakowski.lukasz.TextB.Texts;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Renderer;
import age.of.civilizations2.jakowski.lukasz.TextB.Texts.TextLeftSide;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextLeftSide_Religion
extends TextLeftSide {
    private int iCurrent = 0;
    public int iWidth2 = 1;

    public TextLeftSide_Religion(String sText, int iPosX, int iPosY, int iWidth) {
        super(sText, iPosX, iPosY);
        this.iWidth2 = iWidth;
    }

    @Override
    public void drawE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        CFG.religionManager.religionImages.get(this.iCurrent).drawO(oSB, this.getPosXE() + this.getWidthE() - this.iTextWidth - CFG.PADD - (int)((float)CFG.religionManager.religionImages.get(this.iCurrent).getWidth() * this.getImageScale(CFG.religionManager.religionImages.get(this.iCurrent).getHeight())) + iTranslateX, this.getPosY() + (this.getHeightE() - (int)((float)CFG.religionManager.religionImages.get(this.iCurrent).getHeight() * this.getImageScale(CFG.religionManager.religionImages.get(this.iCurrent).getHeight()))) / 2 - CFG.religionManager.religionImages.get(this.iCurrent).getHeight() + iTranslateY, (int)((float)CFG.religionManager.religionImages.get(this.iCurrent).getWidth() * this.getImageScale(CFG.religionManager.religionImages.get(this.iCurrent).getHeight())), (int)((float)CFG.religionManager.religionImages.get(this.iCurrent).getHeight() * this.getImageScale(CFG.religionManager.religionImages.get(this.iCurrent).getHeight())));
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sText, this.getPosXE() + this.getWidthE() - this.iTextWidth + iTranslateX, this.getPosY() + (this.getHeightE() - this.iTextHeight) / 2 + iTranslateY, this.getColor(isActive));
    }

    @Override
    public int getPosXE() {
        return this.iPosX - this.getWidthE();
    }

    @Override
    public int getWidthE() {
        return this.iWidth2;
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
                        crownW = (int)((float)CFG.religionManager.religionImages.get(this.iCurrent).getWidth() * this.getImageScale(CFG.religionManager.religionImages.get(this.iCurrent).getHeight())) + CFG.PADD;
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
