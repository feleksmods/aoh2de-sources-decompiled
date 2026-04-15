package age.of.civilizations2.jakowski.lukasz.Button.Build;

import age.of.civilizations2.jakowski.lukasz.Button.Build.Button_Build;
import age.of.civilizations2.jakowski.lukasz.Button.Diplomacy.ButtonDiplomacy;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.IMGManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Renderer;
import age.of.civilizations2.jakowski.lukasz.TextB.Texts.TextD;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Build_InvestOverPenalty
extends Button_Build {
    public TextD penalty;
    public TextD invests;
    public TextD investsNum;

    public Button_Build_InvestOverPenalty(String sText, int iPosX, int iPosY, int iWidth, String nPenalty, String nInvestsNum) {
        super(sText, Images.overInvest, 0, 0, iPosX, iPosY, iWidth, true, false, 0, 0.0f);
        this.setHeightE(IMGManager.getIMG(Images.overInvest).getHeight() + CFG.PADD * 4);
        this.penalty = new TextD(nPenalty, CFG.FONT_BOLD_SMALL);
        this.invests = new TextD(CFG.lang.get("Investments") + ": ", CFG.FONT_REGULAR_SMALL);
        this.investsNum = new TextD(nInvestsNum, CFG.FONT_BOLD_SMALL);
    }

    @Override
    public void drawTextE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        IMGManager.getIMG(this.iImageID).drawO(oSB, this.getPosXE() + ButtonDiplomacy.iDiploWidth / 2 - IMGManager.getIMG(this.iImageID).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeightE() / 2 - IMGManager.getIMG(this.iImageID).getHeight() / 2 + iTranslateY);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextE(), this.getPosXE() + CFG.PADD + ButtonDiplomacy.iDiploWidth + iTranslateX, this.getPosY() + this.getHeightE() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColorE(isActive));
        Renderer.drawTextWithShadow(oSB, CFG.FONT_BOLD_SMALL, this.penalty.text, this.getPosXE() + CFG.PADD + ButtonDiplomacy.iDiploWidth + this.getTextWidthU() + iTranslateX, this.getPosY() + this.getHeightE() / 2 - this.getTextHeight() / 2 + iTranslateY, CFG.COLOR_TEXT_NUM_OF_PROVINCES);
        int img = Images.economy;
        int imgW = (int)((float)IMGManager.getIMG(img).getWidth() * this.getImageScale(img));
        int imgH = (int)((float)IMGManager.getIMG(img).getHeight() * this.getImageScale(img));
        IMGManager.getIMG(img).draw(oSB, this.getPosXE() + this.getWidthE() - CFG.PADD * 2 - imgW + iTranslateX, this.getPosY() + this.getHeightE() / 2 - imgH / 2 + iTranslateY, imgW, imgH);
        Renderer.drawTextWithShadow(oSB, CFG.FONT_BOLD_SMALL, this.invests.text, this.getPosXE() + this.getWidthE() - CFG.PADD * 3 - imgW - this.investsNum.textW - this.invests.textW + iTranslateX, this.getPosY() + this.getHeightE() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColorE(isActive));
        Renderer.drawTextWithShadow(oSB, CFG.FONT_BOLD_SMALL, this.investsNum.text, this.getPosXE() + this.getWidthE() - CFG.PADD * 3 - imgW - this.investsNum.textW + iTranslateX, this.getPosY() + this.getHeightE() / 2 - this.getTextHeight() / 2 + iTranslateY, CFG.COLOR_TEXT_NUM_OF_PROVINCES);
    }

    public float getImageScale(int nImageID) {
        return (float)CFG.TEXT_HEIGHT_DEFAULT / (float)IMGManager.getIMG(nImageID).getHeight();
    }
}
