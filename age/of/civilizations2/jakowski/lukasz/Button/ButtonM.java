package age.of.civilizations2.jakowski.lukasz.Button;

import age.of.civilizations2.jakowski.lukasz.Button.MenuElemUI;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.IMGManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Renderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ButtonM
extends MenuElemUI {
    public TypeOfButton typeOfButton;
    private Checkbox oCheckbox;
    public String sText = null;
    public int iTextWidth = -1;
    public int iTextHeight;
    public int fontID = 0;
    public int iTextPositionX;
    public TextPosition textPosition;
    public boolean checkbox = false;
    private boolean checkboxState = false;

    public final void init(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable, boolean isVisible, boolean checkbox, boolean checkboxState) {
        this.init(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable, isVisible, checkbox, checkboxState, null);
    }

    public final void init(String sText, int nTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable, boolean isVisible, boolean checkbox, boolean checkboxState, TypeOfButton typeOfButton) {
        this.typeOfMenuElemUI = MenuElemUI.TypeOfMenuElemUI.BUTTON;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidthE(iWidth);
        this.setHeightE(iHeight);
        this.setTextE(sText);
        this.iTextPositionX = nTextPositionX;
        this.textPosition = nTextPositionX < 0 ? new TextPosition(){

            @Override
            public int getTextPosition() {
                return ButtonM.this.getWidthE() / 2 - ButtonM.this.getTextWidthU() / 2;
            }
        } : new TextPosition(){

            @Override
            public int getTextPosition() {
                return ButtonM.this.iTextPositionX;
            }
        };
        this.checkbox = checkbox;
        this.checkboxState = checkboxState;
        this.oCheckbox = this.buildCheckbox();
        this.setClickable(isClickable);
        this.setVisibleE(isVisible);
        this.typeOfButton = typeOfButton;
    }

    @Override
    public final void drawE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        if (this.getIsClickable()) {
            this.drawButtonBGE(oSB, iTranslateX, iTranslateY, isActive);
        } else {
            oSB.setColor(1.0f, 1.0f, 1.0f, 0.45f);
            this.drawButtonBGE(oSB, iTranslateX, iTranslateY, isActive);
            oSB.setColor(Color.WHITE);
        }
        this.oCheckbox.drawCheckBox(oSB, iTranslateX, iTranslateY, scrollableY);
        this.drawTextE(oSB, iTranslateX, iTranslateY, isActive);
    }

    public Checkbox buildCheckbox() {
        if (this.checkbox) {
            return new Checkbox(){

                @Override
                public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
                    if (ButtonM.this.getCheckboxSt()) {
                        oSB.setColor(new Color(0.55f, 0.8f, 0.0f, 0.25f));
                    } else {
                        oSB.setColor(new Color(0.8f, 0.137f, 0.0f, 0.25f));
                    }
                    IMGManager.getIMG(Images.sliderGradient).drawO(oSB, ButtonM.this.getPosXE() + iTranslateX, ButtonM.this.getPosY() - IMGManager.getIMG(Images.sliderGradient).getHeight() + 1 + iTranslateY, ButtonM.this.getWidthE() / 4, ButtonM.this.getHeightE() - 2, false, false);
                    oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.3f));
                    IMGManager.getIMG(Images.gradient).drawO(oSB, ButtonM.this.getPosXE() + iTranslateX, ButtonM.this.getPosY() - IMGManager.getIMG(Images.gradient).getHeight() + 1 + iTranslateY, ButtonM.this.getWidthE(), ButtonM.this.getHeightE() / 4, false, false);
                    IMGManager.getIMG(Images.gradient).drawO(oSB, ButtonM.this.getPosXE() + iTranslateX, ButtonM.this.getPosY() - IMGManager.getIMG(Images.gradient).getHeight() + ButtonM.this.getHeightE() - 1 + iTranslateY - ButtonM.this.getHeightE() / 4, ButtonM.this.getWidthE(), ButtonM.this.getHeightE() / 4, false, true);
                    oSB.setColor(Color.WHITE);
                }
            };
        }
        return new Checkbox(){

            @Override
            public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
            }
        };
    }

    public void drawButtonBGE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
    }

    public void drawTextE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        if (isActive) {
            Renderer.drawText(oSB, this.fontID, this.getTextToDrawElem(), this.getPosXE() + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + this.getHeightE() / 2 - this.iTextHeight / 2 + iTranslateY, this.getColorE(isActive));
        } else {
            Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDrawElem(), this.getPosXE() + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + this.getHeightE() / 2 - this.iTextHeight / 2 + iTranslateY, this.getColorE(isActive));
        }
    }

    public Color getColorE(boolean isActive) {
        return isActive ? CFG.COLOR_HOVER_TITLE : (this.getIsClickable() ? CFG.COLOR_BTN_M : CFG.COLOR_BTN_M_NOT_CLICKABLE);
    }

    @Override
    public String getTextE() {
        return this.sText;
    }

    @Override
    public String getTextToDrawElem() {
        return this.sText;
    }

    @Override
    public void setTextE(String sText) {
        try {
            this.sText = sText;
            if (sText != null && sText.length() > 0) {
                CFG.glyphLay.setText(CFG.fontMain.get(this.fontID), this.getTextE());
                this.iTextWidth = (int)CFG.glyphLay.width;
                this.iTextHeight = (int)CFG.glyphLay.height;
                if (this.iTextHeight == 0) {
                    this.iTextHeight = CFG.TEXT_HEIGHT_DEFAULT;
                }
            } else {
                this.iTextWidth = 0;
                this.iTextHeight = CFG.TEXT_HEIGHT_DEFAULT;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public final void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    @Override
    public boolean getCheckboxSt() {
        return this.checkboxState;
    }

    @Override
    public final void setCheckboxSt(boolean checkboxState) {
        this.checkboxState = checkboxState;
    }

    @Override
    public void setTypeOfButton(TypeOfButton typeOfButton) {
        this.typeOfButton = typeOfButton;
    }

    @Override
    public int getTextWidthU() {
        return this.iTextWidth;
    }

    @Override
    public int getTextPosElem() {
        return this.iTextPositionX;
    }

    @Override
    public int getTextHeight() {
        return this.iTextHeight;
    }

    public static enum TypeOfButton {
        KEYBOARD,
        KEYBOARD_NUM,
        KEYBOARD_ACTIVE,
        KEYBOARD_SAVE,
        KEYBOARD_OPTIONS;

    }

    public static interface TextPosition {
        public int getTextPosition();
    }

    public static interface Checkbox {
        public void drawCheckBox(SpriteBatch var1, int var2, int var3, boolean var4);
    }
}
