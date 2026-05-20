package age.of.civilizations2.jakowski.lukasz.Button2;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.Button.ButtonM;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Colors;
import age.of.civilizations2.jakowski.lukasz.GameN;
import age.of.civilizations2.jakowski.lukasz.GameValues.GameValues;
import age.of.civilizations2.jakowski.lukasz.IMGManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menus.Civilization.Menu_InGame_Civ_Decisions;
import age.of.civilizations2.jakowski.lukasz.NationalBank;
import age.of.civilizations2.jakowski.lukasz.Renderer;
import age.of.civilizations2.jakowski.lukasz.TextB.Sparks.SparksAnimation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_NationalBankBuild
extends ButtonM {
    public int civID = 0;
    private boolean row = false;
    private String sCost;
    private int iCostWidth;
    private String sReserveLimit;
    private String sReserveLimitValue;
    private int iReserveLimitWidth;
    private int iReserveLimitValueWidth;
    private String sTech;
    private int iTechWidth;
    private String sProvinces;
    private int iProvinceWidth;
    public SparksAnimation sparksAnimation;

    public Button_NationalBankBuild(int iPosX, int iPosY, int iWidth, int minHeight, int rowID, int civID) {
        this.civID = civID;
        this.fontID = CFG.FONT_BOLD;
        super.init(CFG.core.getCiv(civID).getCivName() + ": " + CFG.lang.get("NationalBank"), 0, iPosX, iPosY, iWidth, Math.max(minHeight, Math.max(Menu_InGame_Civ_Decisions.getButtonH(), Math.max(IMGManager.getIMG(Images.flagBigOver).getHeight() + CFG.PADD * 4, (CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD) * 2 + CFG.PADD))), true, true, false, false);
        this.sparksAnimation = new SparksAnimation();
        this.row = rowID % 2 == 1;
        this.sCost = CFG.getNumberWthSpaces("" + NationalBank.getBankCost());
        CFG.glyphLay.setText(CFG.fontMain.get(CFG.FONT_BOLD_SMALL), this.sCost);
        this.iCostWidth = (int)CFG.glyphLay.width;
        this.sTech = CFG.getPrecision2(GameValues.gvIncome.BANK_REQUIRED_TECH_LVL, 100);
        CFG.glyphLay.setText(CFG.fontMain.get(CFG.FONT_BOLD_SMALL), this.sTech);
        this.iTechWidth = (int)CFG.glyphLay.width;
        this.sProvinces = CFG.getNumberWthSpaces("" + GameValues.gvIncome.BANK_REQUIRED_PROVINCES);
        CFG.glyphLay.setText(CFG.fontMain.get(CFG.FONT_BOLD_SMALL), this.sProvinces);
        this.iProvinceWidth = (int)CFG.glyphLay.width;
        this.sReserveLimit = CFG.lang.get("ReserveLimit") + ": ";
        this.sReserveLimitValue = CFG.getNumberWthSpaces("" + NationalBank.getReserveLimit(civID));
        CFG.glyphLay.setText(CFG.fontMain.get(CFG.FONT_BOLD_SMALL), this.sReserveLimit);
        this.iReserveLimitWidth = (int)CFG.glyphLay.width;
        CFG.glyphLay.setText(CFG.fontMain.get(CFG.FONT_BOLD_SMALL), this.sReserveLimitValue);
        this.iReserveLimitValueWidth = (int)CFG.glyphLay.width;
    }

    @Override
    public void drawButtonBGE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        if (this.row) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.4f));
            IMGManager.getIMG(Images.sliderGradient).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.sliderGradient).getHeight() + iTranslateY, this.getWidthE() / 2, this.getHeightE());
            IMGManager.getIMG(Images.sliderGradient).drawO(oSB, this.getPosXE() + this.getWidthE() - this.getWidthE() / 2 + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.sliderGradient).getHeight() + iTranslateY, this.getWidthE() / 2, this.getHeightE(), true, false);
            oSB.setColor(new Color(CFG.COLOR_BOX_GRADIENT.r, CFG.COLOR_BOX_GRADIENT.g, CFG.COLOR_BOX_GRADIENT.b, 0.35f));
            IMGManager.getIMG(Images.gradient).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gradient).getHeight() + iTranslateY, this.getWidthE(), this.getHeightE() / 4);
            IMGManager.getIMG(Images.gradient).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() + this.getHeightE() - this.getHeightE() / 4 - IMGManager.getIMG(Images.gradient).getHeight() + iTranslateY, this.getWidthE(), this.getHeightE() / 4, false, true);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.6f));
            IMGManager.getIMG(Images.line32Off1).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() + this.getHeightE() - 1 - IMGManager.getIMG(Images.line32Off1).getHeight() + iTranslateY, this.getWidthE(), 1);
            IMGManager.getIMG(Images.line32Off1).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.line32Off1).getHeight() + iTranslateY, this.getWidthE(), 1);
            if (isActive || this.getIsHovered()) {
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.35f));
                IMGManager.getIMG(Images.sliderGradient).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() + 1 - IMGManager.getIMG(Images.sliderGradient).getHeight() + iTranslateY, this.getWidthE(), this.getHeightE() - 2, true, false);
            }
        } else {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.6f));
            IMGManager.getIMG(Images.sliderGradient).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.sliderGradient).getHeight() + iTranslateY, this.getWidthE() / 2, this.getHeightE());
            IMGManager.getIMG(Images.sliderGradient).drawO(oSB, this.getPosXE() + this.getWidthE() - this.getWidthE() / 2 + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.sliderGradient).getHeight() + iTranslateY, this.getWidthE() / 2, this.getHeightE(), true, false);
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.45f));
            IMGManager.getIMG(Images.gradient).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gradient).getHeight() + iTranslateY, this.getWidthE(), this.getHeightE() / 4);
            IMGManager.getIMG(Images.gradient).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() + this.getHeightE() - this.getHeightE() / 4 - IMGManager.getIMG(Images.gradient).getHeight() + iTranslateY, this.getWidthE(), this.getHeightE() / 4, false, true);
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.85f));
            IMGManager.getIMG(Images.line32Off1).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() + this.getHeightE() - 1 - IMGManager.getIMG(Images.line32Off1).getHeight() + iTranslateY, this.getWidthE(), 1);
            IMGManager.getIMG(Images.line32Off1).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.line32Off1).getHeight() + iTranslateY, this.getWidthE(), 1);
            if (isActive || this.getIsHovered()) {
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.45f));
                IMGManager.getIMG(Images.sliderGradient).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() + 1 - IMGManager.getIMG(Images.sliderGradient).getHeight() + iTranslateY, this.getWidthE(), this.getHeightE() - 2, true, false);
            }
        }
        oSB.setColor(SparksAnimation.sparksColors2);
        this.sparksAnimation.draw2(oSB, this.getPosXE() + iTranslateX, this.getPosY() + iTranslateY, this.getWidthE(), this.getHeightE());
        oSB.setColor(Color.WHITE);
    }

    @Override
    public void drawTextE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        int posX = this.getPosXE() + CFG.PADD * 2 + iTranslateX;
        int posY = this.getPosY() + CFG.PADD * 2 + iTranslateY;
        if (CFG.core.getCiv((int)this.civID).isFlagNearest || GameN.FUEVG || !CFG.settingsGD.ENABLE_FLAG_WAVING) {
            oSB.setShader(Renderer.shaderAlpha);
            CFG.core.getCiv(this.civID).getFlagC().getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            IMGManager.getIMG(Images.flagBigMask2).draw(oSB, posX, posY, IMGManager.getIMG(Images.flagBigMask2).getWidth(), IMGManager.getIMG(Images.flagBigMask2).getHeight());
        } else {
            Renderer.setShaderWater3(oSB);
            Renderer.shaderWater3.setUniformf("u_maskScale", 1.0f);
            Renderer.shaderWater3.setUniformf("u_maskScaleY", 1.0f);
            IMGManager.getIMG(Images.flagBigMask2).getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            CFG.core.getCiv(this.civID).getFlagC().draw(oSB, posX, posY, IMGManager.getIMG(Images.flagBigMask2).getWidth(), IMGManager.getIMG(Images.flagBigMask2).getHeight());
        }
        oSB.flush();
        oSB.setShader(AoCGame.shaderDef);
        IMGManager.getIMG(Images.flagBigOver2).draw(oSB, posX + (IMGManager.getIMG(Images.flagBigMask2).getWidth() - IMGManager.getIMG(Images.flagBigOver2).getWidth()) / 2, posY);
        if (this.getIsHovered() || isActive) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.3f));
            IMGManager.getIMG(Images.flagBigOver2).draw(oSB, posX + (IMGManager.getIMG(Images.flagBigMask2).getWidth() - IMGManager.getIMG(Images.flagBigOver2).getWidth()) / 2, posY);
        }
        int barH = (this.getHeightE() - CFG.PADD * 5) / 2;
        int barTopY = CFG.PADD * 2;
        int barBotY = this.getHeightE() - CFG.PADD * 2 - barH;
        if (isActive) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.45f));
        } else if (this.getIsHovered()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.35f));
        } else {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.225f));
        }
        Renderer.drawBox2(oSB, Images.statsRectBG, this.getPosXE() + (CFG.PADD * 4 + IMGManager.getIMG(Images.flagBigOver2).getWidth()) + iTranslateX, this.getPosY() + barTopY + iTranslateY, this.getWidthE() - (CFG.PADD * 6 + IMGManager.getIMG(Images.flagBigOver2).getWidth()), barH, 1.0f);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.325f));
        Renderer.drawBox2(oSB, Images.statsRectBGBorder, this.getPosXE() + (CFG.PADD * 4 + IMGManager.getIMG(Images.flagBigOver2).getWidth()) + iTranslateX, this.getPosY() + barTopY + iTranslateY, this.getWidthE() - (CFG.PADD * 6 + IMGManager.getIMG(Images.flagBigOver2).getWidth()), barH, 1.0f);
        if (isActive) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.45f));
        } else if (this.getIsHovered()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.35f));
        } else {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.225f));
        }
        Renderer.drawBox2(oSB, Images.statsRectBG, this.getPosXE() + (CFG.PADD * 4 + IMGManager.getIMG(Images.flagBigOver2).getWidth()) + iTranslateX, this.getPosY() + barBotY + iTranslateY, this.getWidthE() - (CFG.PADD * 6 + IMGManager.getIMG(Images.flagBigOver2).getWidth()), barH, 1.0f);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.325f));
        Renderer.drawBox2(oSB, Images.statsRectBGBorder, this.getPosXE() + (CFG.PADD * 4 + IMGManager.getIMG(Images.flagBigOver2).getWidth()) + iTranslateX, this.getPosY() + barBotY + iTranslateY, this.getWidthE() - (CFG.PADD * 6 + IMGManager.getIMG(Images.flagBigOver2).getWidth()), barH, 1.0f);
        oSB.setColor(Color.WHITE);
        Renderer.drawText(oSB, this.fontID, this.getTextE(), this.getPosXE() + (CFG.PADD * 6 + IMGManager.getIMG(Images.flagBigOver2).getWidth()) + iTranslateX, this.getPosY() + barTopY + barH / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColorE(isActive));
        IMGManager.getIMG(Images.bank).draw(oSB, this.getPosXE() + (CFG.PADD * 6 + IMGManager.getIMG(Images.flagBigOver2).getWidth()) + this.getTextWidthU() + CFG.PADD + iTranslateX, this.getPosY() + barTopY + barH / 2 - (int)((float)IMGManager.getIMG(Images.bank).getHeight() * this.getImageScale(Images.bank)) / 2 + iTranslateY, (int)((float)IMGManager.getIMG(Images.bank).getWidth() * this.getImageScale(Images.bank)), (int)((float)IMGManager.getIMG(Images.bank).getHeight() * this.getImageScale(Images.bank)));
        Renderer.drawText(oSB, CFG.FONT_BOLD_SMALL, this.sCost, this.getPosXE() + this.getWidthE() - CFG.PADD * 4 - this.iCostWidth - CFG.PADD - (int)((float)IMGManager.getIMG(Images.topGold()).getWidth() * this.getImageScale(Images.topGold())) + iTranslateX, this.getPosY() + barTopY + barH / 2 - CFG.TEXT_HEIGHT_DEFAULT_SMALL / 2 + iTranslateY, CFG.core.getCiv(this.civID).getGold() < (long)NationalBank.getBankCost() ? CFG.COLOR_NEGATIVE_2 : CFG.COLOR_GOLD);
        IMGManager.getIMG(Images.topGold()).draw(oSB, this.getPosXE() + this.getWidthE() - CFG.PADD * 4 - (int)((float)IMGManager.getIMG(Images.topGold()).getWidth() * this.getImageScale(Images.topGold())) + iTranslateX, this.getPosY() + barTopY + barH / 2 - (int)((float)IMGManager.getIMG(Images.topGold()).getHeight() * this.getImageScale(Images.topGold())) / 2 + iTranslateY, (int)((float)IMGManager.getIMG(Images.topGold()).getWidth() * this.getImageScale(Images.topGold())), (int)((float)IMGManager.getIMG(Images.topGold()).getHeight() * this.getImageScale(Images.topGold())));
        Renderer.drawText(oSB, CFG.FONT_BOLD_SMALL, this.sTech, this.getPosXE() + this.getWidthE() - CFG.PADD * 4 - this.iTechWidth - CFG.PADD - (int)((float)IMGManager.getIMG(Images.technology).getWidth() * this.getImageScale(Images.technology)) + iTranslateX, this.getPosY() + barBotY + barH / 2 - CFG.TEXT_HEIGHT_DEFAULT_SMALL / 2 + iTranslateY, CFG.core.getCiv(this.civID).getTechLevel() < GameValues.gvIncome.BANK_REQUIRED_TECH_LVL ? CFG.COLOR_NEGATIVE_2 : CFG.COLOR_TECHNOLOGY);
        IMGManager.getIMG(Images.technology).draw(oSB, this.getPosXE() + this.getWidthE() - CFG.PADD * 4 - (int)((float)IMGManager.getIMG(Images.technology).getWidth() * this.getImageScale(Images.technology)) + iTranslateX, this.getPosY() + barBotY + barH / 2 - (int)((float)IMGManager.getIMG(Images.technology).getHeight() * this.getImageScale(Images.technology)) / 2 + iTranslateY, (int)((float)IMGManager.getIMG(Images.technology).getWidth() * this.getImageScale(Images.technology)), (int)((float)IMGManager.getIMG(Images.technology).getHeight() * this.getImageScale(Images.technology)));
        Renderer.drawText(oSB, CFG.FONT_BOLD_SMALL, this.sProvinces, this.getPosXE() + this.getWidthE() - CFG.PADD * 4 - (int)((float)IMGManager.getIMG(Images.technology).getWidth() * this.getImageScale(Images.technology)) - this.iTechWidth - CFG.PADD * 3 - this.iProvinceWidth - (int)((float)IMGManager.getIMG(Images.provinces).getWidth() * this.getImageScale(Images.provinces)) + iTranslateX, this.getPosY() + barBotY + barH / 2 - CFG.TEXT_HEIGHT_DEFAULT_SMALL / 2 + iTranslateY, CFG.core.getCiv(this.civID).getNumOfProvs() < GameValues.gvIncome.BANK_REQUIRED_PROVINCES ? CFG.COLOR_NEGATIVE_2 : CFG.COLOR_TEXT_NUM_OF_PROVINCES);
        IMGManager.getIMG(Images.provinces).draw(oSB, this.getPosXE() + this.getWidthE() - CFG.PADD * 4 - (int)((float)IMGManager.getIMG(Images.technology).getWidth() * this.getImageScale(Images.technology)) - this.iTechWidth - CFG.PADD * 2 - (int)((float)IMGManager.getIMG(Images.provinces).getWidth() * this.getImageScale(Images.provinces)) + iTranslateX, this.getPosY() + barBotY + barH / 2 - (int)((float)IMGManager.getIMG(Images.provinces).getHeight() * this.getImageScale(Images.provinces)) / 2 + iTranslateY, (int)((float)IMGManager.getIMG(Images.provinces).getWidth() * this.getImageScale(Images.provinces)), (int)((float)IMGManager.getIMG(Images.provinces).getHeight() * this.getImageScale(Images.provinces)));
        Renderer.drawText(oSB, CFG.FONT_BOLD_SMALL, this.sReserveLimit, this.getPosXE() + (CFG.PADD * 6 + IMGManager.getIMG(Images.flagBigOver2).getWidth()) + iTranslateX, this.getPosY() + barBotY + barH / 2 - CFG.TEXT_HEIGHT_DEFAULT_SMALL / 2 + iTranslateY, this.getColorE(isActive));
        Renderer.drawText(oSB, CFG.FONT_BOLD_SMALL, this.sReserveLimitValue, this.getPosXE() + (CFG.PADD * 6 + IMGManager.getIMG(Images.flagBigOver2).getWidth()) + this.iReserveLimitWidth + iTranslateX, this.getPosY() + barBotY + barH / 2 - CFG.TEXT_HEIGHT_DEFAULT_SMALL / 2 + iTranslateY, CFG.COLOR_HOVER_TITLE);
        IMGManager.getIMG(Images.topGold()).draw(oSB, this.getPosXE() + (CFG.PADD * 6 + IMGManager.getIMG(Images.flagBigOver2).getWidth()) + this.iReserveLimitWidth + this.iReserveLimitValueWidth + CFG.PADD + iTranslateX, this.getPosY() + barBotY + barH / 2 - (int)((float)IMGManager.getIMG(Images.topGold()).getHeight() * this.getImageScale(Images.topGold())) / 2 + iTranslateY, (int)((float)IMGManager.getIMG(Images.topGold()).getWidth() * this.getImageScale(Images.topGold())), (int)((float)IMGManager.getIMG(Images.topGold()).getHeight() * this.getImageScale(Images.topGold())));
    }

    private final float getImageScale(int nImageID) {
        return Math.min(1.0f, (float)CFG.TEXT_HEIGHT_DEFAULT_SMALL / (float)IMGManager.getIMG(nImageID).getHeight());
    }

    @Override
    public Color getColorE(boolean isActive) {
        return isActive ? CFG.COLOR_TEXT_GRAY_NS_ACTIVE : (this.getIsClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_GRAY_NS_HOVER : CFG.COLOR_TEXT_GRAY_NS) : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE);
    }

    @Override
    public void buildElemHover() {
        this.menuElemHover = NationalBank.getHoverBank(this.civID);
    }
}
