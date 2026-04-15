package age.of.civilizations2.jakowski.lukasz.Menus.Settings;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.Button.MenuElemUI;
import age.of.civilizations2.jakowski.lukasz.Button.NewGame.Button_NewGameStyle;
import age.of.civilizations2.jakowski.lukasz.Button.NewGame.Button_NewGameStyle_Clear;
import age.of.civilizations2.jakowski.lukasz.Button.NewGame.Button_NewGameStyle_Left;
import age.of.civilizations2.jakowski.lukasz.Button.NewGame.Button_NewGameStyle_Middle;
import age.of.civilizations2.jakowski.lukasz.Button.NewGame.Button_NewGameStyle_Right;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Core.Core;
import age.of.civilizations2.jakowski.lukasz.IMGManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MapA.CitiesManager;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.Menus.Menu_InitGame;
import age.of.civilizations2.jakowski.lukasz.Render;
import age.of.civilizations2.jakowski.lukasz.Save.SaveGameManager;
import age.of.civilizations2.jakowski.lukasz.SettingsGD;
import age.of.civilizations2.jakowski.lukasz.Sliders.InGame.Clear.Slider_InGame_Clear;
import age.of.civilizations2.jakowski.lukasz.TextB.Texts.TextBudgetTitle;
import age.of.civilizations2.jakowski.lukasz.Title.TitleM;
import age.of.civilizations2.jakowski.lukasz.View;
import age.of.civilizations2.jakowski.lukasz.Z_Other.ColorPicker.ColorPicker_AoC;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_Settings_Province
extends Menu {
    private String sScale;

    public Menu_Settings_Province() {
        int tempW = CFG.CIV_INFO_MENU_WIDTH + CFG.CIV_INFO_MENU_WIDTH * 3 / 4;
        ArrayList<MenuElemUI> menuElements = new ArrayList<MenuElemUI>();
        int tPosY = CFG.PADD;
        float buttonH_Mod = 0.75f;
        menuElements.add(new Button_NewGameStyle(null, -1, CFG.PADD, tPosY, tempW - CFG.PADD * 2, (int)((float)CFG.BUTTON_H * buttonH_Mod), true));
        menuElements.add(new Button_NewGameStyle_Left("-", -1, CFG.PADD, tPosY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, CFG.BUTTON_H, (int)((float)CFG.BUTTON_H * buttonH_Mod), true));
        menuElements.add(new Button_NewGameStyle_Middle(null, -1, CFG.PADD + CFG.BUTTON_H, tPosY, tempW - CFG.PADD * 2 - CFG.BUTTON_H * 2, (int)((float)CFG.BUTTON_H * buttonH_Mod), true));
        menuElements.add(new Button_NewGameStyle_Right("+", -1, CFG.PADD + CFG.BUTTON_H + (tempW - CFG.PADD * 2 - CFG.BUTTON_H * 2), tPosY, CFG.BUTTON_H, (int)((float)CFG.BUTTON_H * buttonH_Mod), true));
        menuElements.add(new Slider_InGame_Clear("", CFG.PADD * 2, tPosY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD * 2, tempW - CFG.PADD * 4, (int)((float)CFG.BUTTON_H * 0.8f), 25, 255, CFG.settingsGD.PROV_ALPHA));
        menuElements.add(new Slider_InGame_Clear("", CFG.PADD * 2, tPosY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD * 2, tempW - CFG.PADD * 4, (int)((float)CFG.BUTTON_H * 0.8f), 100, 2000, (int)((CFG.getIsDesktop() ? CFG.settingsGD.STOP_SCALING_ARMY : CFG.settingsGD.STOP_SCALING_ARMY_MOBILE) * 100.0f)){

            @Override
            public String getDrawText() {
                return "" + (float)this.getCurr() / 100.0f;
            }
        });
        menuElements.add(new Button_NewGameStyle(null, -1, CFG.PADD, tPosY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD * 2, tempW - CFG.PADD * 2, true, CFG.settingsGD.ENABLE_INNERBORDERS){

            @Override
            public boolean getCheckboxSt() {
                return CFG.settingsGD.ENABLE_INNERBORDERS;
            }
        });
        menuElements.add(new TextBudgetTitle("", -1, 2, tPosY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - 2, CFG.BUTTON_H / 2){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_GRAY_NS_HOVER : (this.getIsClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_GRAY_NS : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        menuElements.add(new Slider_InGame_Clear("", CFG.PADD * 2, tPosY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 4, (int)((float)CFG.BUTTON_H * 0.8f), 0, 100, CFG.settingsGD.PERCENTAGE_OF_CITIES_ON_MAP){

            @Override
            public String getDrawText() {
                return super.getDrawText() + "%";
            }
        });
        menuElements.add(new Slider_InGame_Clear("", CFG.PADD * 2, tPosY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD * 2, tempW - CFG.PADD * 4, (int)((float)CFG.BUTTON_H * 0.8f), 10, 200, (int)(CFG.settingsGD.CITIES_FONT_SCALE * 100.0f)){

            @Override
            public String getDrawText() {
                return super.getDrawText() + "%";
            }
        });
        menuElements.add(new TextBudgetTitle("", -1, 2, tPosY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD * 2, tempW - 2, CFG.BUTTON_H / 2){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_GRAY_NS_HOVER : (this.getIsClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_GRAY_NS : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        menuElements.add(new Button_NewGameStyle(null, -1, CFG.PADD, tPosY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, true, CFG.settingsGD.DRAW_CIVILIZATIONS_NAMES_OVER_PROVINCES_IN_GAME){

            @Override
            public boolean getCheckboxSt() {
                return CFG.settingsGD.DRAW_CIVILIZATIONS_NAMES_OVER_PROVINCES_IN_GAME;
            }
        });
        menuElements.add(new Button_NewGameStyle_Left("-", -1, CFG.PADD, tPosY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, CFG.BUTTON_H, (int)((float)CFG.BUTTON_H * buttonH_Mod), true));
        menuElements.add(new Button_NewGameStyle_Middle(null, -1, CFG.PADD + CFG.BUTTON_H, tPosY, tempW - CFG.PADD * 2 - CFG.BUTTON_H * 2, (int)((float)CFG.BUTTON_H * buttonH_Mod), true));
        menuElements.add(new Button_NewGameStyle_Right("+", -1, CFG.PADD + CFG.BUTTON_H + (tempW - CFG.PADD * 2 - CFG.BUTTON_H * 2), tPosY, CFG.BUTTON_H, (int)((float)CFG.BUTTON_H * buttonH_Mod), true));
        menuElements.add(new Slider_InGame_Clear("", CFG.PADD * 2, tPosY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD * 2, tempW - CFG.PADD * 4, (int)((float)CFG.BUTTON_H * 0.8f), 0, 100, (int)(CFG.settingsGD.CIV_NAMES_MIN_SCALE_OF_FONT * 100.0f)){

            @Override
            public String getDrawText() {
                return super.getDrawText() + "%";
            }
        });
        menuElements.add(new Button_NewGameStyle_Clear("", -1, CFG.PADD, tPosY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD * 2, tempW - CFG.PADD * 2, (int)((float)CFG.BUTTON_H * buttonH_Mod), true){
            int iCurrent;

            @Override
            public void drawTextE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawTextE(oSB, iTranslateX, iTranslateY, isActive);
                oSB.setColor(CFG.settingsGD.civNamesFontColor.getR(), CFG.settingsGD.civNamesFontColor.getG(), CFG.settingsGD.civNamesFontColor.getB(), 1.0f);
                IMGManager.getIMG(Images.sliderGradient).drawO(oSB, this.getPosXE() + this.getWidthE() / 2 - this.getTextWidthU() / 2 + iTranslateX, this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeightE() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_W, CFG.PADD, CFG.CIV_COLOR_W, true, false);
                IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosXE() + this.getWidthE() / 2 - this.getTextWidthU() / 2 + CFG.PADD + iTranslateX, this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeightE() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_W, this.getTextWidthU() - CFG.PADD * 2, CFG.CIV_COLOR_W);
                IMGManager.getIMG(Images.sliderGradient).drawO(oSB, this.getPosXE() + this.getWidthE() / 2 - this.getTextWidthU() / 2 + this.getTextWidthU() - CFG.PADD + iTranslateX, this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeightE() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_W, CFG.PADD, CFG.CIV_COLOR_W);
                oSB.setColor(Color.WHITE);
            }

            @Override
            public void setCurr(int nCurrent) {
                this.iCurrent = nCurrent;
            }
        });
        menuElements.add(new Slider_InGame_Clear("", CFG.PADD * 2, tPosY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 4, (int)((float)CFG.BUTTON_H * 0.8f), 0, 100, (int)(CFG.settingsGD.civNamesFontColor_ALPHA * 100.0f)){

            @Override
            public String getDrawText() {
                return super.getDrawText() + "%";
            }
        });
        menuElements.add(new Button_NewGameStyle_Clear("", -1, CFG.PADD, tPosY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD * 2, tempW - CFG.PADD * 2, (int)((float)CFG.BUTTON_H * buttonH_Mod), true){
            int iCurrent;

            @Override
            public void drawTextE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawTextE(oSB, iTranslateX, iTranslateY, isActive);
                oSB.setColor(CFG.settingsGD.civNamesFontColorBorder.getR(), CFG.settingsGD.civNamesFontColorBorder.getG(), CFG.settingsGD.civNamesFontColorBorder.getB(), 1.0f);
                IMGManager.getIMG(Images.sliderGradient).drawO(oSB, this.getPosXE() + this.getWidthE() / 2 - this.getTextWidthU() / 2 + iTranslateX, this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeightE() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_W, CFG.PADD, CFG.CIV_COLOR_W, true, false);
                IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosXE() + this.getWidthE() / 2 - this.getTextWidthU() / 2 + CFG.PADD + iTranslateX, this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeightE() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_W, this.getTextWidthU() - CFG.PADD * 2, CFG.CIV_COLOR_W);
                IMGManager.getIMG(Images.sliderGradient).drawO(oSB, this.getPosXE() + this.getWidthE() / 2 - this.getTextWidthU() / 2 + this.getTextWidthU() - CFG.PADD + iTranslateX, this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeightE() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_W, CFG.PADD, CFG.CIV_COLOR_W);
                oSB.setColor(Color.WHITE);
            }

            @Override
            public void setCurr(int nCurrent) {
                this.iCurrent = nCurrent;
            }
        });
        menuElements.add(new Slider_InGame_Clear("", CFG.PADD * 2, tPosY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 4, (int)((float)CFG.BUTTON_H * 0.8f), 0, 100, (int)(CFG.settingsGD.civNamesFontColorBorder_ALPHA * 100.0f)){

            @Override
            public String getDrawText() {
                return super.getDrawText() + "%";
            }
        });
        menuElements.add(new Slider_InGame_Clear("", CFG.PADD * 2, tPosY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD * 2, tempW - CFG.PADD * 4, (int)((float)CFG.BUTTON_H * 0.8f), 0, (int)((float)CFG.settingsGD.FONT_BORDER_SIZEX * 0.4f), CFG.settingsGD.FONT_BORDER_WIDTH){

            @Override
            public String getDrawText() {
                return super.getDrawText() + "px";
            }
        });
        menuElements.add(new Slider_InGame_Clear("", CFG.PADD * 2, tPosY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD * 2, tempW - CFG.PADD * 4, (int)((float)CFG.BUTTON_H * 0.8f), 0, 5000, CFG.settingsGD.CIVILIZATIONS_NAMES_INTERVAL){

            @Override
            public String getDrawText() {
                return super.getDrawText() + "ms";
            }
        });
        menuElements.add(new TextBudgetTitle("", -1, 2, tPosY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD * 2, tempW - 2, CFG.BUTTON_H / 2){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_GRAY_NS_HOVER : (this.getIsClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_GRAY_NS : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        menuElements.add(new Button_NewGameStyle_Clear("", -1, CFG.PADD, tPosY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, (int)((float)CFG.BUTTON_H * buttonH_Mod), true){
            int iCurrent;

            @Override
            public void drawTextE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawTextE(oSB, iTranslateX, iTranslateY, isActive);
                oSB.setColor(CFG.settingsGD.civNamesFontColor.getR(), CFG.settingsGD.civNamesFontColor.getG(), CFG.settingsGD.civNamesFontColor.getB(), 1.0f);
                IMGManager.getIMG(Images.sliderGradient).drawO(oSB, this.getPosXE() + this.getWidthE() / 2 - this.getTextWidthU() / 2 + iTranslateX, this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeightE() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_W, CFG.PADD, CFG.CIV_COLOR_W, true, false);
                IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosXE() + this.getWidthE() / 2 - this.getTextWidthU() / 2 + CFG.PADD + iTranslateX, this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeightE() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_W, this.getTextWidthU() - CFG.PADD * 2, CFG.CIV_COLOR_W);
                IMGManager.getIMG(Images.sliderGradient).drawO(oSB, this.getPosXE() + this.getWidthE() / 2 - this.getTextWidthU() / 2 + this.getTextWidthU() - CFG.PADD + iTranslateX, this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeightE() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_W, CFG.PADD, CFG.CIV_COLOR_W);
                oSB.setColor(Color.WHITE);
            }

            @Override
            public void setCurr(int nCurrent) {
                this.iCurrent = nCurrent;
            }
        });
        menuElements.add(new Slider_InGame_Clear("", CFG.PADD * 2, tPosY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 4, (int)((float)CFG.BUTTON_H * 0.8f), 0, 255, (int)(CFG.settingsGD.PROVINCE_ALPHA_WASTELAND * 255.0f)){

            @Override
            public String getDrawText() {
                return super.getDrawText() + "";
            }
        });
        menuElements.add(new TextBudgetTitle("", -1, 2, tPosY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD * 2, tempW - 2, CFG.BUTTON_H / 2){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_GRAY_NS_HOVER : (this.getIsClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_GRAY_NS : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        menuElements.add(new Button_NewGameStyle_Clear("", -1, CFG.PADD, tPosY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, (int)((float)CFG.BUTTON_H * buttonH_Mod), true){
            int iCurrent;

            @Override
            public void drawTextE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawTextE(oSB, iTranslateX, iTranslateY, isActive);
                oSB.setColor(CFG.settingsGD.civNamesFontColorBorder.getR(), CFG.settingsGD.civNamesFontColorBorder.getG(), CFG.settingsGD.civNamesFontColorBorder.getB(), 1.0f);
                IMGManager.getIMG(Images.sliderGradient).drawO(oSB, this.getPosXE() + this.getWidthE() / 2 - this.getTextWidthU() / 2 + iTranslateX, this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeightE() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_W, CFG.PADD, CFG.CIV_COLOR_W, true, false);
                IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosXE() + this.getWidthE() / 2 - this.getTextWidthU() / 2 + CFG.PADD + iTranslateX, this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeightE() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_W, this.getTextWidthU() - CFG.PADD * 2, CFG.CIV_COLOR_W);
                IMGManager.getIMG(Images.sliderGradient).drawO(oSB, this.getPosXE() + this.getWidthE() / 2 - this.getTextWidthU() / 2 + this.getTextWidthU() - CFG.PADD + iTranslateX, this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeightE() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_W, CFG.PADD, CFG.CIV_COLOR_W);
                oSB.setColor(Color.WHITE);
            }

            @Override
            public void setCurr(int nCurrent) {
                this.iCurrent = nCurrent;
            }
        });
        menuElements.add(new Slider_InGame_Clear("", CFG.PADD * 2, tPosY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 4, (int)((float)CFG.BUTTON_H * 0.8f), 0, 255, (int)(CFG.settingsGD.COLOR_PROVINCE_DISCOVERY_ALPHA * 255.0f)){

            @Override
            public String getDrawText() {
                return super.getDrawText() + "";
            }
        });
        menuElements.add(new Button_NewGameStyle_Left("<<", -1, CFG.PADD, tPosY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD * 2, CFG.BUTTON_H, (int)((float)CFG.BUTTON_H * buttonH_Mod), true));
        menuElements.add(new Button_NewGameStyle_Middle("", -1, CFG.PADD + CFG.BUTTON_H, tPosY, tempW - CFG.PADD * 2 - CFG.BUTTON_H * 2, (int)((float)CFG.BUTTON_H * buttonH_Mod), true){

            @Override
            public void drawTextE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                oSB.setColor(Color.WHITE);
                CFG.linesManager.moveLandImage.draw2O(oSB, this.getPosXE() + CFG.PADD * 2 + iTranslateX, this.getPosY() + this.getHeightE() / 2 - CFG.linesManager.moveLandImage.getHeight() / 2 - CFG.linesManager.moveLandImage.getHeight() + iTranslateY, this.getWidthE() - CFG.PADD * 4, CFG.linesManager.moveLandImage.getHeight());
            }
        });
        menuElements.add(new Button_NewGameStyle_Right(">>", -1, CFG.PADD + CFG.BUTTON_H + (tempW - CFG.PADD * 2 - CFG.BUTTON_H * 2), tPosY, CFG.BUTTON_H, (int)((float)CFG.BUTTON_H * buttonH_Mod), true));
        menuElements.add(new Button_NewGameStyle_Left("<<", -1, CFG.PADD, tPosY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD * 2, CFG.BUTTON_H, (int)((float)CFG.BUTTON_H * buttonH_Mod), true));
        menuElements.add(new Button_NewGameStyle_Middle("", -1, CFG.PADD + CFG.BUTTON_H, tPosY, tempW - CFG.PADD * 2 - CFG.BUTTON_H * 2, (int)((float)CFG.BUTTON_H * buttonH_Mod), true){

            @Override
            public void drawTextE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                oSB.setColor(Color.WHITE);
                CFG.linesManager.highlightImage.draw2O(oSB, this.getPosXE() + CFG.PADD * 2 + iTranslateX, this.getPosY() + this.getHeightE() / 2 - CFG.linesManager.highlightImage.getHeight() / 2 - CFG.linesManager.highlightImage.getHeight() + iTranslateY, this.getWidthE() - CFG.PADD * 4, CFG.linesManager.highlightImage.getHeight());
            }
        });
        menuElements.add(new Button_NewGameStyle_Right(">>", -1, CFG.PADD + CFG.BUTTON_H + (tempW - CFG.PADD * 2 - CFG.BUTTON_H * 2), tPosY, CFG.BUTTON_H, (int)((float)CFG.BUTTON_H * buttonH_Mod), true));
        menuElements.add(new TextBudgetTitle("", -1, 2, tPosY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD * 2, tempW - 2, CFG.BUTTON_H / 2){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_GRAY_NS_HOVER : (this.getIsClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_GRAY_NS : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        menuElements.add(new Slider_InGame_Clear("", CFG.PADD * 2, tPosY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 4, (int)((float)CFG.BUTTON_H * 0.8f), 25, 255, CFG.settingsGD.OCCUPIED_PROV_ALPHA){

            @Override
            public String getDrawText() {
                return "" + (int)((float)this.getCurr() / 255.0f * 100.0f) + "%";
            }
        });
        menuElements.add(new Slider_InGame_Clear("", CFG.PADD * 2, tPosY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD * 2, tempW - CFG.PADD * 4, (int)((float)CFG.BUTTON_H * 0.8f), 1, 100, (int)(CFG.settingsGD.OCCUPIED_STRIPES_SIZE * 10.0f)){

            @Override
            public String getDrawText() {
                return "" + (float)this.getCurr() / 10.0f;
            }
        });
        menuElements.add(new Button_NewGameStyle(null, -1, CFG.PADD, tPosY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD * 2, tempW - CFG.PADD * 2, (int)((float)CFG.BUTTON_H * buttonH_Mod), true));
        this.initMenu(new TitleM(null, CFG.BUTTON_H * 3 / 5, false, false){

            @Override
            public void drawT(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                IMGManager.getIMG(Images.gameTopEdge).draw2O(oSB, nPosX - 2 - Core.PADDING + iTranslateX, nPosY - this.getHeightT() - IMGManager.getIMG(Images.gameTopEdge).getHeight() - Core.PADDING, nWidth + 4 + Core.PADDING, this.getHeightT() + Core.PADDING);
                oSB.setColor(new Color(0.003921569f, 0.32941177f, 0.50980395f, 0.165f));
                IMGManager.getIMG(Images.line32Off1).drawO(oSB, nPosX + iTranslateX, nPosY - this.getHeightT() + 2 - IMGManager.getIMG(Images.line32Off1).getHeight(), nWidth, this.getHeightT() - 2, false, true);
                oSB.setColor(new Color(0.003921569f, 0.32941177f, 0.50980395f, 0.375f));
                IMGManager.getIMG(Images.gradient).drawO(oSB, nPosX + iTranslateX, nPosY - this.getHeightT() * 2 / 3 - IMGManager.getIMG(Images.gradient).getHeight(), nWidth, this.getHeightT() * 2 / 3, false, true);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
                IMGManager.getIMG(Images.gradient).drawO(oSB, nPosX + iTranslateX, nPosY - CFG.PADD - IMGManager.getIMG(Images.gradient).getHeight(), nWidth, CFG.PADD, false, true);
                oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
                IMGManager.getIMG(Images.pix255).drawO(oSB, nPosX + iTranslateX, nPosY - 1 - IMGManager.getIMG(Images.pix255).getHeight(), nWidth, 1);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
                IMGManager.getIMG(Images.line32Off1).drawO(oSB, nPosX + iTranslateX, nPosY - 2 - IMGManager.getIMG(Images.line32Off1).getHeight(), nWidth, 1);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.8f));
                IMGManager.getIMG(Images.line32Off1).drawO(oSB, nPosX + iTranslateX, nPosY - 1 - IMGManager.getIMG(Images.line32Off1).getHeight(), nWidth, 1);
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45f));
                IMGManager.getIMG(Images.sliderGradient).drawO(oSB, nPosX + iTranslateX, nPosY - 1 - IMGManager.getIMG(Images.sliderGradient).getHeight(), nWidth / 2, 1);
                IMGManager.getIMG(Images.sliderGradient).drawO(oSB, nPosX + nWidth - nWidth / 2 + iTranslateX, nPosY - 1 - IMGManager.getIMG(Images.sliderGradient).getHeight(), nWidth / 2, 1, true, false);
                oSB.setColor(Color.WHITE);
                CFG.fontMain.get(0).getData().setScale(0.8f);
                CFG.drawTextDefault(oSB, this.getText(), nPosX + (int)((float)nWidth - (float)this.getTextWidth() * 0.8f) / 2 + iTranslateX, 2 + nPosY - this.getHeightT() + (int)((float)this.getHeightT() - (float)this.getTextHeight() * 0.8f) / 2, Color.WHITE);
                CFG.fontMain.get(0).getData().setScale(1.0f);
            }
        }, CFG.GAMEWIDTH - tempW, CFG.PADD + CFG.BUTTON_H * 3 / 4, tempW, Math.min(tPosY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, CFG.GAMEHEIGHT - (CFG.PADD + CFG.BUTTON_H * 3 / 4)), menuElements);
        this.updateLang();
    }

    @Override
    public void updateLang() {
        this.getMenuElem(0).setTextE(CFG.lang.get("Back"));
        this.getMenuElem(2).setTextE(CFG.lang.get("FontSizeofArmy") + ": " + CFG.settingsGD.FONT_ARMY_SIZEX);
        this.getMenuElem(4).setTextE(CFG.lang.get("ProvinceAlpha"));
        this.getMenuElem(5).setTextE(CFG.lang.get("Scale"));
        this.getMenuElem(6).setTextE(CFG.lang.get("InnerBorders"));
        this.getMenuElem(7).setTextE(CFG.lang.get("Cities"));
        this.getMenuElem(8).setTextE(CFG.lang.get("NumberOfCities"));
        this.getMenuElem(9).setTextE(CFG.lang.get("ScaleOfCitiesNames"));
        this.getMenuElem(10).setTextE(CFG.lang.get("CivilizationsNames"));
        this.getMenuElem(11).setTextE(CFG.lang.get("NamesOfCivilizationsOverProvinces"));
        this.getMenuElem(13).setTextE(CFG.lang.get("FontSize") + ": " + CFG.settingsGD.FONT_BORDER_SIZEX);
        this.getMenuElem(15).setTextE(CFG.lang.get("MinScaleofCivilizationsNames"));
        this.getMenuElem(16).setTextE(CFG.lang.get("Color"));
        this.getMenuElem(17).setTextE(CFG.lang.get("Alpha"));
        this.getMenuElem(18).setTextE(CFG.lang.get("BorderColor"));
        this.getMenuElem(19).setTextE(CFG.lang.get("Alpha"));
        this.getMenuElem(20).setTextE(CFG.lang.get("Width"));
        this.getMenuElem(21).setTextE(CFG.lang.get("AnimationTime"));
        this.getMenuElem(22).setTextE(CFG.lang.get("Wasteland"));
        this.getMenuElem(23).setTextE(CFG.lang.get("Color"));
        this.getMenuElem(24).setTextE(CFG.lang.get("Alpha"));
        this.getMenuElem(25).setTextE(CFG.lang.get("Fogofwar"));
        this.getMenuElem(26).setTextE(CFG.lang.get("Color"));
        this.getMenuElem(27).setTextE(CFG.lang.get("Alpha"));
        this.getMenuElem(34).setTextE(CFG.lang.get("OccupiedProvinces"));
        this.getMenuElem(35).setTextE(CFG.lang.get("Alpha"));
        this.getMenuElem(36).setTextE(CFG.lang.get("Scale"));
        this.getMenuElem(37).setTextE(CFG.lang.get("Defaults"));
        this.getMenuElem(4).setCurr(CFG.settingsGD.PROV_ALPHA);
        this.getMenuElem(5).setCurr((int)((CFG.getIsDesktop() ? CFG.settingsGD.STOP_SCALING_ARMY : CFG.settingsGD.STOP_SCALING_ARMY_MOBILE) * 100.0f));
        this.getMenuElem(7).setCurr(CFG.settingsGD.PERCENTAGE_OF_CITIES_ON_MAP);
        this.getMenuElem(9).setCurr((int)(CFG.settingsGD.CITIES_FONT_SCALE * 100.0f));
        this.getMenuElem(15).setCurr((int)(CFG.settingsGD.CIV_NAMES_MIN_SCALE_OF_FONT * 100.0f));
        this.getMenuElem(17).setCurr((int)(CFG.settingsGD.civNamesFontColor_ALPHA * 100.0f));
        this.getMenuElem(19).setCurr((int)(CFG.settingsGD.civNamesFontColorBorder_ALPHA * 100.0f));
        this.getMenuElem(20).setCurr(CFG.settingsGD.FONT_BORDER_WIDTH);
        this.getMenuElem(21).setCurr(CFG.settingsGD.CIVILIZATIONS_NAMES_INTERVAL);
        this.getMenuElem(24).setCurr((int)(CFG.settingsGD.PROVINCE_ALPHA_WASTELAND * 255.0f));
        this.getMenuElem(27).setCurr((int)(CFG.settingsGD.COLOR_PROVINCE_DISCOVERY_ALPHA * 255.0f));
        this.getMenuElem(35).setCurr(CFG.settingsGD.PROV_ALPHA);
        this.getMenuElem(36).setCurr((int)(CFG.settingsGD.OCCUPIED_STRIPES_SIZE * 10.0f));
        this.getTitleM().setText(CFG.lang.get("ProvinceSettings"));
        this.sScale = CFG.lang.get("Scale") + ": ";
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        IMGManager.getIMG(Images.gradient).drawO(oSB, iTranslateX, -IMGManager.getIMG(Images.gradient).getHeight() + iTranslateY, CFG.GAMEWIDTH, CFG.BUTTON_H * 3 / 4);
        oSB.setColor(Color.WHITE);
        IMGManager.getIMG(Images.gameTopEdgeLine).draw2O(oSB, this.getPosX() - 2 - Core.PADDING + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gameTopEdgeLine).getHeight() + iTranslateY, this.getWidthM() + 2 + Core.PADDING, this.getHeightM(), false, true);
        CFG.fontBorder.getData().setScale(1.0f);
        CFG.drawTextBorder(oSB, "Age of History 2: Definitive Edition", CFG.PADD * 2 + iTranslateX, CFG.PADD * 2, Color.WHITE);
        CFG.drawTextDefaultWithShadow(oSB, this.sScale + CFG.map.getMpS().getCurrSc(), CFG.PADD + iTranslateX, CFG.GAMEHEIGHT - CFG.PADD - CFG.TEXT_HEIGHT_DEFAULT, CFG.COLOR_NEUTRAL);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
        IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosX() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.pix255).getHeight() + this.getHeightM(), this.getWidthM());
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
        IMGManager.getIMG(Images.line32Off1).drawO(oSB, this.getPosX() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.pix255).getHeight() - IMGManager.getIMG(Images.line32Off1).getHeight() + this.getHeightM(), this.getWidthM(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() + this.getHeightM(), this.getWidthM() + 2);
        oSB.setColor(Color.WHITE);
    }

    public final void updateArmyWidth() {
        int j;
        int i;
        for (i = 0; i < CFG.core.getProvinSize(); ++i) {
            for (j = 0; j < CFG.core.getProv(i).getCivsSize(); ++j) {
                CFG.core.getProv(i).getArmyObject(j).updateArmyWidth_Just(i);
            }
        }
        for (i = 1; i < CFG.core.getCivsSize(); ++i) {
            for (j = 0; j < CFG.core.getCiv(i).getRecruitArmySize(); ++j) {
                CFG.core.getCiv(i).getRecruitArmy(j).setArmy(CFG.core.getCiv(i).getRecruitArmy(j).getArmy());
            }
            for (j = 0; j < CFG.core.getCiv(i).getMoveUnitsPlunderSize(); ++j) {
                CFG.core.getCiv(i).getMoveUnitsPlunder(j).setNumOfUnits(CFG.core.getCiv(i).getMoveUnitsPlunder(j).getNumOfUnits());
            }
        }
    }

    @Override
    public void actionEL(int iID) {
        switch (iID) {
            case 0: {
                this.onBackPressed();
                break;
            }
            case 1: {
                --CFG.settingsGD.FONT_ARMY_SIZEX;
                if (CFG.settingsGD.FONT_ARMY_SIZEX < 12) {
                    CFG.settingsGD.FONT_ARMY_SIZEX = 12;
                }
                CFG.loadFontArmy();
                if (SaveGameManager.gameCanBeContinued) {
                    this.updateArmyWidth();
                } else {
                    for (int i = 0; i < CFG.core.getProvinSize(); ++i) {
                        CFG.core.getProv(i).getArmyObject(0).updateArmyWidth(i);
                    }
                }
                Menu_InitGame.loadArmyBGImages();
                this.updateLang();
                break;
            }
            case 2: {
                CFG.toastM.addM(this.getMenuElem(iID).getTextE(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                break;
            }
            case 3: {
                ++CFG.settingsGD.FONT_ARMY_SIZEX;
                if (CFG.settingsGD.FONT_ARMY_SIZEX > 128) {
                    CFG.settingsGD.FONT_ARMY_SIZEX = 128;
                }
                CFG.loadFontArmy();
                if (SaveGameManager.gameCanBeContinued) {
                    this.updateArmyWidth();
                } else {
                    for (int i = 0; i < CFG.core.getProvinSize(); ++i) {
                        CFG.core.getProv(i).getArmyObject(0).updateArmyWidth(i);
                    }
                }
                Menu_InitGame.loadArmyBGImages();
                this.updateLang();
                break;
            }
            case 4: {
                CFG.settingsGD.PROV_ALPHA = this.getMenuElem(iID).getCurr();
                break;
            }
            case 5: {
                if (CFG.getIsDesktop()) {
                    CFG.settingsGD.STOP_SCALING_ARMY = (float)this.getMenuElem(iID).getCurr() / 100.0f;
                    break;
                }
                CFG.settingsGD.STOP_SCALING_ARMY_MOBILE = (float)this.getMenuElem(iID).getCurr() / 100.0f;
                break;
            }
            case 6: {
                CFG.settingsGD.ENABLE_INNERBORDERS = !CFG.settingsGD.ENABLE_INNERBORDERS;
                for (int i = 0; i < CFG.core.getProvinSize(); ++i) {
                    CFG.core.getProv(i).updateProvinceBorder();
                }
                break;
            }
            case 8: {
                CFG.settingsGD.PERCENTAGE_OF_CITIES_ON_MAP = this.getMenuElem(iID).getCurr();
                for (int i = 1; i < CFG.core.getCivsSize(); ++i) {
                    CitiesManager.updateCities(i);
                }
                break;
            }
            case 9: {
                CFG.settingsGD.CITIES_FONT_SCALE = (float)this.getMenuElem(iID).getCurr() / 100.0f;
                for (int i = 0; i < CFG.core.getProvinSize(); ++i) {
                    for (int j = 0; j < CFG.core.getProv(i).getCitSize(); ++j) {
                        CFG.core.getProv(i).getCit(j).updateCityNameWidth();
                    }
                }
                break;
            }
            case 11: {
                CFG.settingsGD.DRAW_CIVILIZATIONS_NAMES_OVER_PROVINCES_IN_GAME = !CFG.settingsGD.DRAW_CIVILIZATIONS_NAMES_OVER_PROVINCES_IN_GAME;
                Render.updateRenderer_CivNames();
                break;
            }
            case 12: {
                --CFG.settingsGD.FONT_BORDER_SIZEX;
                if (CFG.settingsGD.FONT_BORDER_SIZEX < 8) {
                    CFG.settingsGD.FONT_BORDER_SIZEX = 8;
                }
                CFG.loadFontBorder();
                for (int i = 0; i < CFG.core.getCivsSize(); ++i) {
                    for (int j = 0; j < CFG.core.getCiv(i).getCivRegionsSize(); ++j) {
                        CFG.core.getCiv(i).getCivRegion(j).buildScaleOfText();
                    }
                }
                this.updateLang();
                break;
            }
            case 14: {
                ++CFG.settingsGD.FONT_BORDER_SIZEX;
                if (CFG.settingsGD.FONT_BORDER_SIZEX > 256) {
                    CFG.settingsGD.FONT_BORDER_SIZEX = 256;
                }
                CFG.loadFontBorder();
                for (int i = 0; i < CFG.core.getCivsSize(); ++i) {
                    for (int j = 0; j < CFG.core.getCiv(i).getCivRegionsSize(); ++j) {
                        CFG.core.getCiv(i).getCivRegion(j).buildScaleOfText();
                    }
                }
                this.updateLang();
                break;
            }
            case 15: {
                CFG.settingsGD.CIV_NAMES_MIN_SCALE_OF_FONT = (float)this.getMenuElem(iID).getCurr() / 100.0f;
                break;
            }
            case 16: {
                CFG.menus.getColorPicker().setActiveRGBColor(CFG.settingsGD.civNamesFontColor.getR(), CFG.settingsGD.civNamesFontColor.getG(), CFG.settingsGD.civNamesFontColor.getB());
                CFG.menus.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.CIV_NAMES_OVER_PROVINCES);
                break;
            }
            case 17: {
                CFG.settingsGD.civNamesFontColor_ALPHA = (float)this.getMenuElem(iID).getCurr() / 100.0f;
                CFG.loadFontBorder();
                break;
            }
            case 18: {
                CFG.menus.getColorPicker().setActiveRGBColor(CFG.settingsGD.civNamesFontColorBorder.getR(), CFG.settingsGD.civNamesFontColorBorder.getG(), CFG.settingsGD.civNamesFontColorBorder.getB());
                CFG.menus.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.CIV_NAMES_OVER_PROVINCES_BORDER);
                break;
            }
            case 19: {
                CFG.settingsGD.civNamesFontColorBorder_ALPHA = (float)this.getMenuElem(iID).getCurr() / 100.0f;
                CFG.loadFontBorder();
                break;
            }
            case 20: {
                CFG.settingsGD.FONT_BORDER_WIDTH = this.getMenuElem(iID).getCurr();
                CFG.loadFontBorder();
                break;
            }
            case 21: {
                CFG.settingsGD.CIVILIZATIONS_NAMES_INTERVAL = this.getMenuElem(iID).getCurr();
                break;
            }
            case 23: {
                CFG.menus.getColorPicker().setActiveRGBColor(CFG.settingsGD.COLOR_PROVINCE_BG_WASTELAND.getR(), CFG.settingsGD.COLOR_PROVINCE_BG_WASTELAND.getG(), CFG.settingsGD.COLOR_PROVINCE_BG_WASTELAND.getB());
                CFG.menus.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.PROVINCE_SETTINGS_WASTELAND_COLOR);
                break;
            }
            case 24: {
                CFG.settingsGD.PROVINCE_ALPHA_WASTELAND = (float)this.getMenuElem(iID).getCurr() / 255.0f;
                break;
            }
            case 26: {
                CFG.menus.getColorPicker().setActiveRGBColor(CFG.settingsGD.COLOR_PROVINCE_DISCOVERY.getR(), CFG.settingsGD.COLOR_PROVINCE_DISCOVERY.getG(), CFG.settingsGD.COLOR_PROVINCE_DISCOVERY.getB());
                CFG.menus.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.PROVINCE_SETTINGS_DISCOVERY_COLOR);
                break;
            }
            case 27: {
                CFG.settingsGD.COLOR_PROVINCE_DISCOVERY_ALPHA = (float)this.getMenuElem(iID).getCurr() / 255.0f;
                break;
            }
            case 28: {
                CFG.linesManager.moveLandTAG = CFG.settingsGD.sMoveLine = CFG.linesManager.loadNext(CFG.linesManager.moveLandTAG, false);
                CFG.linesManager.loadMoveLand();
                break;
            }
            case 29: {
                break;
            }
            case 30: {
                CFG.linesManager.moveLandTAG = CFG.settingsGD.sMoveLine = CFG.linesManager.loadNext(CFG.linesManager.moveLandTAG, true);
                CFG.linesManager.loadMoveLand();
                break;
            }
            case 31: {
                CFG.linesManager.highlightTAG = CFG.settingsGD.sHighlightLine = CFG.linesManager.loadNext(CFG.linesManager.highlightTAG, false);
                CFG.linesManager.loadHighlight();
                break;
            }
            case 32: {
                break;
            }
            case 33: {
                CFG.linesManager.highlightTAG = CFG.settingsGD.sHighlightLine = CFG.linesManager.loadNext(CFG.linesManager.highlightTAG, true);
                CFG.linesManager.loadHighlight();
                break;
            }
            case 35: {
                CFG.settingsGD.OCCUPIED_PROV_ALPHA = this.getMenuElem(iID).getCurr();
                break;
            }
            case 36: {
                CFG.settingsGD.OCCUPIED_STRIPES_SIZE = (float)this.getMenuElem(iID).getCurr() / 10.0f;
                break;
            }
            case 37: {
                int i;
                SettingsGD tempS = new SettingsGD();
                CFG.settingsGD.PROV_ALPHA = tempS.PROV_ALPHA;
                CFG.settingsGD.DRAW_CIVILIZATIONS_NAMES_OVER_PROVINCES_IN_GAME = tempS.DRAW_CIVILIZATIONS_NAMES_OVER_PROVINCES_IN_GAME;
                CFG.settingsGD.OCCUPIED_PROV_ALPHA = tempS.OCCUPIED_PROV_ALPHA;
                CFG.settingsGD.OCCUPIED_STRIPES_SIZE = tempS.OCCUPIED_STRIPES_SIZE;
                CFG.settingsGD.FONT_ARMY_SIZEX = tempS.FONT_ARMY_SIZEX;
                AoCGame.updateArmyFontSize();
                CFG.loadFontArmy();
                for (i = 0; i < CFG.core.getProvinSize(); ++i) {
                    CFG.core.getProv(i).getArmyObject(0).updateArmyWidth(i);
                }
                CFG.settingsGD.PERCENTAGE_OF_CITIES_ON_MAP = tempS.PERCENTAGE_OF_CITIES_ON_MAP;
                CFG.settingsGD.STOP_SCALING_ARMY = tempS.STOP_SCALING_ARMY;
                CFG.settingsGD.STOP_SCALING_ARMY_MOBILE = tempS.STOP_SCALING_ARMY_MOBILE;
                for (i = 1; i < CFG.core.getCivsSize(); ++i) {
                    CitiesManager.updateCities(i);
                }
                CFG.settingsGD.updateCitiesFontScale();
                for (i = 0; i < CFG.core.getProvinSize(); ++i) {
                    for (int j = 0; j < CFG.core.getProv(i).getCitSize(); ++j) {
                        CFG.core.getProv(i).getCit(j).updateCityNameWidth();
                    }
                }
                CFG.settingsGD.FONT_BORDER_WIDTH = tempS.FONT_BORDER_WIDTH;
                CFG.settingsGD.ENABLE_INNERBORDERS = tempS.ENABLE_INNERBORDERS;
                for (i = 0; i < CFG.core.getProvinSize(); ++i) {
                    CFG.core.getProv(i).updateProvinceBorder();
                }
                CFG.settingsGD.civNamesFontColor.setR(tempS.civNamesFontColor.getR());
                CFG.settingsGD.civNamesFontColor.setG(tempS.civNamesFontColor.getG());
                CFG.settingsGD.civNamesFontColor.setB(tempS.civNamesFontColor.getB());
                CFG.settingsGD.civNamesFontColor_ALPHA = tempS.civNamesFontColor_ALPHA;
                CFG.settingsGD.civNamesFontColorBorder.setR(tempS.civNamesFontColorBorder.getR());
                CFG.settingsGD.civNamesFontColorBorder.setG(tempS.civNamesFontColorBorder.getG());
                CFG.settingsGD.civNamesFontColorBorder.setB(tempS.civNamesFontColorBorder.getB());
                CFG.settingsGD.civNamesFontColorBorder_ALPHA = tempS.civNamesFontColorBorder_ALPHA;
                CFG.settingsGD.CIV_NAMES_MIN_SCALE_OF_FONT = tempS.CIV_NAMES_MIN_SCALE_OF_FONT;
                CFG.settingsGD.CIVILIZATIONS_NAMES_INTERVAL = tempS.CIVILIZATIONS_NAMES_INTERVAL;
                CFG.settingsGD.COLOR_PROVINCE_BG_WASTELAND.setR(tempS.COLOR_PROVINCE_BG_WASTELAND.getR());
                CFG.settingsGD.COLOR_PROVINCE_BG_WASTELAND.setG(tempS.COLOR_PROVINCE_BG_WASTELAND.getG());
                CFG.settingsGD.COLOR_PROVINCE_BG_WASTELAND.setB(tempS.COLOR_PROVINCE_BG_WASTELAND.getB());
                CFG.settingsGD.PROVINCE_ALPHA_WASTELAND = tempS.PROVINCE_ALPHA_WASTELAND;
                CFG.settingsGD.COLOR_PROVINCE_DISCOVERY = tempS.COLOR_PROVINCE_DISCOVERY;
                CFG.settingsGD.COLOR_PROVINCE_DISCOVERY_ALPHA = tempS.COLOR_PROVINCE_DISCOVERY_ALPHA;
                CFG.settingsGD.sMoveLine = tempS.sMoveLine;
                CFG.linesManager.loadMoveLand();
                CFG.settingsGD.sHighlightLine = tempS.sHighlightLine;
                CFG.linesManager.loadHighlight();
                CFG.loadFontBorder();
                Render.updateRenderer_CivNames();
                this.updateLang();
            }
        }
        CFG.saveSettings();
    }

    @Override
    public void onBackPressed() {
        CFG.menus.getColorPicker().setVisible(false, null);
        CFG.menus.setMenuID(View.eSETTINGS);
        CFG.menus.setBackAnimation(true);
        this.updateArmyWidth();
    }
}
