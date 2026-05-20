package age.of.civilizations2.jakowski.lukasz.TextB.Texts;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.IMGManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menus.Civilization.Menu_InGame_Civ;
import age.of.civilizations2.jakowski.lukasz.Renderer;
import age.of.civilizations2.jakowski.lukasz.TextB.Texts.TextBudgetTitle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextBudgetTitle_TextLeft
extends TextBudgetTitle {
    public String leftText;
    public int iconLeft;
    public String rightText;
    public int rightTextW = 0;
    public String rightText2;
    public int rightText2W = 0;
    public int iconRight;

    public TextBudgetTitle_TextLeft(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, String textLeft, int iconLeft) {
        super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight);
        this.leftText = textLeft;
        this.iconLeft = iconLeft;
        this.rightText = CFG.lang.get("Reserves") + ": ";
        this.rightText2 = CFG.getNumberWthSpaces("" + CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).civGD.nationalBankReserves);
        try {
            if (this.rightText != null && this.rightText.length() > 0) {
                CFG.glyphLay.setText(CFG.fontMain.get(this.fontID), this.rightText);
                this.rightTextW = (int)CFG.glyphLay.width;
            }
            if (this.rightText2 != null && this.rightText2.length() > 0) {
                CFG.glyphLay.setText(CFG.fontMain.get(this.fontID), this.rightText2);
                this.rightText2W = (int)CFG.glyphLay.width;
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
        this.iconRight = Images.bank;
    }

    @Override
    public void drawE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        if (isActive) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_BLUE.r, CFG.COLOR_GRADIENT_BLUE.g, CFG.COLOR_GRADIENT_BLUE.b, 0.5f));
        } else if (this.getIsHovered()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_BLUE.r, CFG.COLOR_GRADIENT_BLUE.g, CFG.COLOR_GRADIENT_BLUE.b, 0.35f));
        } else {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_BLUE.r, CFG.COLOR_GRADIENT_BLUE.g, CFG.COLOR_GRADIENT_BLUE.b, 0.25f));
        }
        Renderer.drawBox2(oSB, Images.statsRectBG, this.getPosXE() + iTranslateX, this.getPosY() + iTranslateY, this.getWidthE(), this.getHeightE(), 1.0f);
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_BLUE.r, CFG.COLOR_GRADIENT_BLUE.g, CFG.COLOR_GRADIENT_BLUE.b, 0.3f));
        IMGManager.getIMG(Images.line32Off1).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.line32Off1).getHeight() + iTranslateY, this.getWidthE(), this.getHeightE());
        oSB.setColor(new Color(CFG.COLOR_NEW_GAME_EDGE_LINE.r, CFG.COLOR_NEW_GAME_EDGE_LINE.g, CFG.COLOR_NEW_GAME_EDGE_LINE.b, 0.55f));
        Renderer.drawBox2(oSB, Images.statsRectBGBorder, this.getPosXE() + iTranslateX, this.getPosY() + iTranslateY, this.getWidthE(), this.getHeightE(), 1.0f);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.35f));
        Renderer.drawBox2(oSB, Images.statsRectBGBorder, this.getPosXE() + 1 + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidthE() - 2, this.getHeightE() - 2, 1.0f);
        oSB.setColor(Color.WHITE);
        if (Menu_InGame_Civ.getUseMenu_UI2()) {
            Renderer.drawTextWithShadow(oSB, this.fontID, this.sText, this.getPosXE() + this.getWidthE() - this.getTextWidthU() - CFG.PADD * 2 + iTranslateX, this.getPosY() + (this.getHeightE() - this.iTextHeight) / 2 + iTranslateY, this.getColor(isActive));
        } else {
            Renderer.drawTextWithShadow(oSB, this.fontID, this.sText, this.getPosXE() + (this.getWidthE() - this.getTextWidthU()) / 2 + iTranslateX, this.getPosY() + (this.getHeightE() - this.iTextHeight) / 2 + iTranslateY, this.getColor(isActive));
        }
        oSB.setColor(Color.WHITE);
        IMGManager.getIMG(this.iconLeft).draw(oSB, this.getPosXE() + CFG.PADD * 2 + iTranslateX, this.getPosY() + this.getHeightE() / 2 - IMGManager.getIMG(this.iconLeft).getHeight() / 2 + iTranslateY);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.leftText, this.getPosXE() + CFG.PADD * 3 + IMGManager.getIMG(this.iconLeft).getWidth() + iTranslateX, this.getPosY() + (this.getHeightE() - this.iTextHeight) / 2 + iTranslateY, this.getColor(isActive));
        IMGManager.getIMG(this.iconRight).draw(oSB, this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - IMGManager.getIMG(this.iconRight).getWidth() + iTranslateX, this.getPosY() + this.getHeightE() / 2 - IMGManager.getIMG(this.iconRight).getHeight() / 2 + iTranslateY);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.rightText2, this.getPosXE() + this.getWidthE() - CFG.PADD * 3 - IMGManager.getIMG(this.iconRight).getWidth() - this.rightText2W + iTranslateX, this.getPosY() + (this.getHeightE() - this.iTextHeight) / 2 + iTranslateY, CFG.COLOR_GOLD);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.rightText, this.getPosXE() + this.getWidthE() - CFG.PADD * 3 - IMGManager.getIMG(this.iconRight).getWidth() - this.rightText2W - this.rightTextW + iTranslateX, this.getPosY() + (this.getHeightE() - this.iTextHeight) / 2 + iTranslateY, this.getColor(isActive));
    }
}
