package age.of.civilizations2.jakowski.lukasz.TextB;

import age.of.civilizations2.jakowski.lukasz.Button.MenuElemUI;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Renderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text
extends MenuElemUI {
    public String sText = null;
    public int iTextWidth = -1;
    public int iTextHeight = -1;
    public int iTextPositionX;
    public TextPosition textPosition;

    public Text() {
    }

    public Text(String sText, int iPosX, int iPosY) {
        this.typeOfMenuElemUI = MenuElemUI.TypeOfMenuElemUI.TEXT;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setHeightE(CFG.TEXT_HEIGHT_DEFAULT);
        this.setTextE(sText);
        this.textPosition = new TextPosition(){

            @Override
            public int getTextPosition() {
                return 0;
            }
        };
    }

    public Text(String sText, int iPosX, int iPosY, int fontID) {
        this.typeOfMenuElemUI = MenuElemUI.TypeOfMenuElemUI.TEXT;
        this.fontID = fontID;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setHeightE(CFG.TEXT_HEIGHT_DEFAULT);
        this.setTextE(sText);
        this.textPosition = new TextPosition(){

            @Override
            public int getTextPosition() {
                return 0;
            }
        };
    }

    public Text(String sText, int iTextPositionX, int iPosX, int iPosY, int iHeight) {
        this.typeOfMenuElemUI = MenuElemUI.TypeOfMenuElemUI.TEXT;
        this.iTextPositionX = iTextPositionX;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setHeightE(iHeight);
        this.setTextE(sText);
        this.updateTextPosition();
    }

    public Text(String sText, int iTextPositionX, int iPosX, int iPosY, int iHeight, float fontID) {
        this.typeOfMenuElemUI = MenuElemUI.TypeOfMenuElemUI.TEXT;
        this.fontID = (int)fontID;
        this.iTextPositionX = iTextPositionX;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setHeightE(iHeight);
        this.setTextE(sText);
        this.updateTextPosition();
    }

    public Text(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight) {
        this.typeOfMenuElemUI = MenuElemUI.TypeOfMenuElemUI.TEXT;
        this.iTextPositionX = iTextPositionX;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidthE(iWidth);
        this.setHeightE(iHeight);
        this.setTextE(sText);
        this.updateTextPosition();
    }

    public Text(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, int fontID) {
        this.fontID = fontID;
        this.typeOfMenuElemUI = MenuElemUI.TypeOfMenuElemUI.TEXT;
        this.iTextPositionX = iTextPositionX;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidthE(iWidth);
        this.setHeightE(iHeight);
        this.setTextE(sText);
        this.updateTextPosition();
    }

    public void updateTextPosition() {
        this.textPosition = this.iTextPositionX < 0 ? new TextPosition(){

            @Override
            public int getTextPosition() {
                return Text.this.getWidthE() / 2 - Text.this.iTextWidth / 2;
            }
        } : new TextPosition(){

            @Override
            public int getTextPosition() {
                return Text.this.iTextPositionX;
            }
        };
    }

    @Override
    public void drawE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sText, this.getPosXE() + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + (this.getHeightE() - this.getTextHeight()) / 2 + iTranslateY, this.getColor(isActive));
    }

    public Color getColor(boolean isActive) {
        return isActive ? new Color(0.56f, 0.56f, 0.56f, 1.0f) : (this.getIsClickable() ? (this.getIsHovered() ? new Color(0.68f, 0.68f, 0.68f, 1.0f) : CFG.COLOR_BUTTON_MENU_TEXT) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
    }

    @Override
    public final String getTextE() {
        return this.sText;
    }

    @Override
    public void setTextE(String sText) {
        this.sText = sText;
        try {
            if (sText != null && sText.length() > 0) {
                CFG.glyphLay.setText(CFG.fontMain.get(this.fontID), sText);
                this.iTextWidth = (int)CFG.glyphLay.width;
                this.iTextHeight = (int)CFG.glyphLay.height;
                if (super.getWidthE() < this.iTextWidth) {
                    this.setWidthE(this.iTextWidth);
                }
                if (this.getHeightE() < this.iTextHeight) {
                    this.setHeightE(this.iTextHeight);
                }
            } else {
                this.iTextWidth = 0;
                this.iTextHeight = CFG.TEXT_HEIGHT_DEFAULT;
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    @Override
    public int getTextWidthU() {
        return this.iTextWidth;
    }

    @Override
    public int getTextHeight() {
        return this.iTextHeight;
    }

    public static interface TextPosition {
        public int getTextPosition();
    }
}
