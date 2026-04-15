package age.of.civilizations2.jakowski.lukasz.Menus.TradeRequest;

import age.of.civilizations2.jakowski.lukasz.Button.Flag.Button_Flag_JustFrame;
import age.of.civilizations2.jakowski.lukasz.Button.MenuElemUI;
import age.of.civilizations2.jakowski.lukasz.Button.Stats.ButtonStats;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Core.Core;
import age.of.civilizations2.jakowski.lukasz.GameValues.GameValues;
import age.of.civilizations2.jakowski.lukasz.IMGManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.Menus.TradeRequest.Menu_InGame_TradeRequest_SelectCiv;
import age.of.civilizations2.jakowski.lukasz.Menus.Z_Rest.Menu_InGame_SelectProvinces;
import age.of.civilizations2.jakowski.lukasz.RenderProvince;
import age.of.civilizations2.jakowski.lukasz.Renderer;
import age.of.civilizations2.jakowski.lukasz.Title.TitleM;
import age.of.civilizations2.jakowski.lukasz.Title.TitleM_TextSmall;
import age.of.civilizations2.jakowski.lukasz.View;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_TradeRequest_Side
extends Menu {
    private boolean left = false;
    private int iOnCivID = -1;

    public Menu_InGame_TradeRequest_Side() {
        ArrayList<MenuElemUI> menuElements = new ArrayList<MenuElemUI>();
        int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 3 / 4;
        int tY = CFG.PADD;
        menuElements.add(new Button_Flag_JustFrame(CFG.PADD, tY, true));
        tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        int tempMenuPosY = IMGManager.getIMG(Images.topBar).getHeight() + CFG.PADD * 2 + CFG.BUTTON_H * 3 / 5;
        this.initMenu(new TitleM(CFG.lang.get("TradeRequest"), CFG.BUTTON_H * 3 / 5, true, true), CFG.GAMEWIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD + tempMenuPosY > CFG.GAMEHEIGHT - CFG.BUTTON_H - CFG.PADD * 2 ? Math.max(CFG.GAMEHEIGHT - CFG.BUTTON_H - CFG.PADD * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 2) * 6) : ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, menuElements, false, true);
        this.updateLang();
    }

    public Menu_InGame_TradeRequest_Side(int onCivID, final boolean left) {
        ArrayList<MenuElemUI> menuElements = new ArrayList<MenuElemUI>();
        this.iOnCivID = onCivID;
        this.left = left;
        int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 3 / 4;
        int tY = 0;
        menuElements.add(new ButtonStats(CFG.lang.get("Gold"), CFG.PADD * 2, 2, tY, CFG.BUTTON_W * 2, (int)((float)CFG.BUTTON_H * 0.75f), false){

            @Override
            public int getWidthE() {
                return Menu_InGame_TradeRequest_Side.this.getElementW();
            }

            @Override
            public boolean getCheckboxSt() {
                return left ? CFG.tradeRequest.listLEFT.iGold > 0 : CFG.tradeRequest.listRight.iGold > 0;
            }

            @Override
            public void actionElemPPM() {
                if (CFG.tradeRequest.listLEFT.iGold > 0) {
                    CFG.tradeRequest.listLEFT.iGold = GameValues.gvTrade.DECLARE_WAR_MAGIC_NUM_ALWAYS_ACCEPT;
                    CFG.menus.rebuildInGame_TradeRequest_Just();
                }
            }
        });
        tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        if (left) {
            ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setClickable(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getGold() > 0L);
        }
        menuElements.add(new ButtonStats(CFG.lang.get("Provinces"), CFG.PADD * 2, 2, tY, CFG.BUTTON_W * 2, (int)((float)CFG.BUTTON_H * 0.75f), false){

            @Override
            public int getWidthE() {
                return Menu_InGame_TradeRequest_Side.this.getElementW();
            }

            @Override
            public boolean getCheckboxSt() {
                return left ? CFG.tradeRequest.listLEFT.lProvinces.size() > 0 : CFG.tradeRequest.listRight.lProvinces.size() > 0;
            }
        });
        menuElements.add(new ButtonStats(CFG.lang.get("DeclareWar"), CFG.PADD * 2, 2, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE(), CFG.BUTTON_W * 2, (int)((float)CFG.BUTTON_H * 0.75f), false){

            @Override
            public int getWidthE() {
                return Menu_InGame_TradeRequest_Side.this.getElementW();
            }

            @Override
            public boolean getCheckboxSt() {
                return left ? CFG.tradeRequest.listLEFT.iDeclareWarOnCivID > 0 : CFG.tradeRequest.listRight.iDeclareWarOnCivID > 0;
            }
        });
        menuElements.add(new ButtonStats(CFG.lang.get("FormACoalitionAgainst"), CFG.PADD * 2, 2, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE(), CFG.BUTTON_W * 2, (int)((float)CFG.BUTTON_H * 0.75f), false){

            @Override
            public int getWidthE() {
                return Menu_InGame_TradeRequest_Side.this.getElementW();
            }

            @Override
            public boolean getCheckboxSt() {
                return CFG.tradeRequest.listLEFT.iFormCoalitionAgainst > 0 || CFG.tradeRequest.listRight.iFormCoalitionAgainst > 0;
            }
        });
        menuElements.add(new ButtonStats(CFG.lang.get("DefensivePact"), CFG.PADD * 2, 2, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE(), CFG.BUTTON_W * 2, (int)((float)CFG.BUTTON_H * 0.75f), false){

            @Override
            public int getWidthE() {
                return Menu_InGame_TradeRequest_Side.this.getElementW();
            }

            @Override
            public boolean getCheckboxSt() {
                return left ? CFG.tradeRequest.listLEFT.defensivePact : CFG.tradeRequest.listRight.defensivePact;
            }
        });
        menuElements.add(new ButtonStats(CFG.lang.get("NonAggressionPact"), CFG.PADD * 2, 2, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE(), CFG.BUTTON_W * 2, (int)((float)CFG.BUTTON_H * 0.75f), false){

            @Override
            public int getWidthE() {
                return Menu_InGame_TradeRequest_Side.this.getElementW();
            }

            @Override
            public boolean getCheckboxSt() {
                return left ? CFG.tradeRequest.listLEFT.nonAggressionPact : CFG.tradeRequest.listRight.nonAggressionPact;
            }
        });
        menuElements.add(new ButtonStats(CFG.lang.get("ProclaimIndependence"), CFG.PADD * 2, 2, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE(), CFG.BUTTON_W * 2, (int)((float)CFG.BUTTON_H * 0.75f), false){

            @Override
            public int getWidthE() {
                return Menu_InGame_TradeRequest_Side.this.getElementW();
            }

            @Override
            public boolean getCheckboxSt() {
                return left ? CFG.tradeRequest.listLEFT.proclaimIndependence : CFG.tradeRequest.listRight.proclaimIndependence;
            }
        });
        menuElements.add(new ButtonStats(CFG.lang.get("MilitaryAccess"), CFG.PADD * 2, 2, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE(), CFG.BUTTON_W * 2, (int)((float)CFG.BUTTON_H * 0.75f), false){

            @Override
            public int getWidthE() {
                return Menu_InGame_TradeRequest_Side.this.getElementW();
            }

            @Override
            public boolean getCheckboxSt() {
                return left ? CFG.tradeRequest.listLEFT.militaryAccess : CFG.tradeRequest.listRight.militaryAccess;
            }
        });
        tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        int tempMenuPosY = IMGManager.getIMG(Images.topBar).getHeight() + CFG.PADD * 2 + CFG.BUTTON_H * 3 / 4;
        this.initMenu(new TitleM_TextSmall(CFG.core.getCiv(onCivID).getCivName(), CFG.BUTTON_H * 3 / 4, false, false){

            @Override
            public void drawT(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                IMGManager.getIMG(Images.gameTopEdge).draw2O(oSB, nPosX - Core.PADDING + iTranslateX, nPosY - this.getHeightT() - Core.PADDING - IMGManager.getIMG(Images.gameTopEdge).getHeight(), nWidth - IMGManager.getIMG(Images.gameTopEdge).getWidth() + Core.PADDING * 2, this.getHeightT() + Core.PADDING);
                IMGManager.getIMG(Images.gameTopEdge).draw2O(oSB, nPosX + Core.PADDING + nWidth - IMGManager.getIMG(Images.gameTopEdge).getWidth() + iTranslateX, nPosY - Core.PADDING - this.getHeightT() - IMGManager.getIMG(Images.gameTopEdge).getHeight(), IMGManager.getIMG(Images.gameTopEdge).getWidth(), this.getHeightT() + Core.PADDING, true, false);
                oSB.setColor(new Color((float)CFG.core.getCiv(Menu_InGame_TradeRequest_Side.this.iOnCivID).getR() / 255.0f, (float)CFG.core.getCiv(Menu_InGame_TradeRequest_Side.this.iOnCivID).getG() / 255.0f, (float)CFG.core.getCiv(Menu_InGame_TradeRequest_Side.this.iOnCivID).getB() / 255.0f, 0.165f));
                IMGManager.getIMG(Images.line32Off1).drawO(oSB, nPosX + 2 + iTranslateX, nPosY - this.getHeightT() - IMGManager.getIMG(Images.line32Off1).getHeight(), nWidth - 4, this.getHeightT() - 2, false, true);
                oSB.setColor(new Color((float)CFG.core.getCiv(Menu_InGame_TradeRequest_Side.this.iOnCivID).getR() / 255.0f, (float)CFG.core.getCiv(Menu_InGame_TradeRequest_Side.this.iOnCivID).getG() / 255.0f, (float)CFG.core.getCiv(Menu_InGame_TradeRequest_Side.this.iOnCivID).getB() / 255.0f, 0.375f));
                IMGManager.getIMG(Images.gradient).drawO(oSB, nPosX + 2 + iTranslateX, nPosY - this.getHeightT() * 2 / 3 - IMGManager.getIMG(Images.gradient).getHeight(), nWidth - 4, this.getHeightT() * 2 / 3, false, true);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
                IMGManager.getIMG(Images.gradient).drawO(oSB, nPosX + 2 + iTranslateX, nPosY - CFG.PADD - IMGManager.getIMG(Images.gradient).getHeight(), nWidth - 4, CFG.PADD, false, true);
                oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
                IMGManager.getIMG(Images.pix255).drawO(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - IMGManager.getIMG(Images.pix255).getHeight(), nWidth - 4, 1);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
                IMGManager.getIMG(Images.line32Off1).drawO(oSB, nPosX + 2 + iTranslateX, nPosY - 2 - IMGManager.getIMG(Images.line32Off1).getHeight(), nWidth - 4, 1);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.8f));
                IMGManager.getIMG(Images.line32Off1).drawO(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - IMGManager.getIMG(Images.line32Off1).getHeight(), nWidth - 4, 1);
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45f));
                IMGManager.getIMG(Images.sliderGradient).drawO(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - IMGManager.getIMG(Images.sliderGradient).getHeight(), (nWidth - 4) / 2, 1);
                IMGManager.getIMG(Images.sliderGradient).drawO(oSB, nPosX + (nWidth - 2) - (nWidth - 4) / 2 + iTranslateX, nPosY - 1 - IMGManager.getIMG(Images.sliderGradient).getHeight(), (nWidth - 4) / 2, 1, true, false);
                oSB.setColor(Color.WHITE);
                Renderer.drawText(oSB, CFG.FONT_BOLD_SMALL, this.getText(), nPosX + (nWidth - this.getTextWidth()) / 2 + iTranslateX, 2 + nPosY - this.getHeightT() + (this.getHeightT() - this.getTextHeight()) / 2, Color.WHITE);
            }
        }, CFG.GAMEWIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD + tempMenuPosY > CFG.GAMEHEIGHT - CFG.BUTTON_H - CFG.PADD * 2 ? Math.max(CFG.GAMEHEIGHT - CFG.BUTTON_H - CFG.PADD * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 2) * 6) : ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, menuElements, true, false);
        this.updateLang();
        for (int i = 0; i < this.getMenuElemsSize(); ++i) {
            this.getMenuElem(i).setCurr(i % 2);
        }
    }

    @Override
    public void updateLang() {
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        oSB.setColor(Color.WHITE);
        IMGManager.getIMG(Images.gameTopEdge).draw2O(oSB, this.getPosX() - Core.PADDING + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gameTopEdge).getHeight() + iTranslateY, this.getWidthM() + Core.PADDING * 2 - IMGManager.getIMG(Images.gameTopEdge).getWidth(), this.getHeightM() + CFG.PADD, false, true);
        IMGManager.getIMG(Images.gameTopEdge).draw2O(oSB, this.getPosX() + this.getWidthM() + Core.PADDING - IMGManager.getIMG(Images.gameTopEdge).getWidth() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gameTopEdge).getHeight() + iTranslateY, IMGManager.getIMG(Images.gameTopEdge).getWidth(), this.getHeightM() + CFG.PADD, true, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
        IMGManager.getIMG(Images.gradient).drawO(oSB, this.getPosX() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gradient).getHeight() + iTranslateY, this.getWidthM(), this.getHeightM() / 4);
        IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosX() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.pix255).getHeight() + iTranslateY, this.getWidthM(), 1);
        oSB.setColor(Color.WHITE);
        this.beginClipM(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        this.drawMenuM(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(Color.WHITE);
        this.endClipM(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    public void onHovered() {
        CFG.menus.setOrderOfMenu_InGame_TradeRequest();
    }

    @Override
    public final void actionEL(int iID) {
        int tempPosY = this.getMenuPosY();
        switch (iID) {
            case 0: {
                if (this.left) {
                    if (CFG.tradeRequest.listLEFT.iGold > 0) {
                        CFG.tradeRequest.listLEFT.iGold = 0;
                    } else {
                        CFG.tradeRequest.listLEFT.iGold = 100;
                        if ((long)CFG.tradeRequest.listLEFT.iGold > CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getGold()) {
                            CFG.tradeRequest.listLEFT.iGold = (int)CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getGold();
                        }
                    }
                } else {
                    CFG.tradeRequest.listRight.iGold = CFG.tradeRequest.listRight.iGold > 0 ? 0 : 100;
                }
                CFG.menus.rebuildInGame_TradeRequest_Just();
                this.setMenuPosY(tempPosY);
                return;
            }
            case 1: {
                if (this.left) {
                    if (CFG.tradeRequest.listLEFT.lProvinces.size() == 0) {
                        CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.tradeRequest.iCivLEFT;
                        CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).iACTIVE_VIEW_MODE = CFG.mapModesManager.getActiveMapModeID();
                        CFG.mapModesManager.disableAllViews();
                        CFG.core.setActiveProvID(-1);
                        Menu_InGame_SelectProvinces.typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.TRADE_LEFT;
                        CFG.VIEW_SHOW_VALUES = false;
                        CFG.selectMode = true;
                        CFG.core.getProvSelected().clearSelectedProvinces();
                        CFG.menus.setMenuID(View.eINGAME_SELECT_PROVINCES);
                        RenderProvince.updateDrawProvinces();
                    } else {
                        CFG.tradeRequest.listLEFT.lProvinces.clear();
                        CFG.menus.rebuildInGame_TradeRequest_Just();
                    }
                } else if (CFG.tradeRequest.listRight.lProvinces.size() == 0) {
                    CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.tradeRequest.iCivRIGHT;
                    CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).iACTIVE_VIEW_MODE = CFG.mapModesManager.getActiveMapModeID();
                    CFG.mapModesManager.disableAllViews();
                    CFG.core.setActiveProvID(-1);
                    Menu_InGame_SelectProvinces.typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.TRADE_RIGHT;
                    CFG.VIEW_SHOW_VALUES = false;
                    CFG.selectMode = true;
                    CFG.core.getProvSelected().clearSelectedProvinces();
                    CFG.menus.setMenuID(View.eINGAME_SELECT_PROVINCES);
                    RenderProvince.updateDrawProvinces();
                } else {
                    CFG.tradeRequest.listRight.lProvinces.clear();
                    CFG.menus.rebuildInGame_TradeRequest_Just();
                }
                this.setMenuPosY(tempPosY);
                return;
            }
            case 2: {
                if (this.left) {
                    if (CFG.tradeRequest.listLEFT.iDeclareWarOnCivID <= 0) {
                        CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).iACTIVE_VIEW_MODE = CFG.mapModesManager.getActiveMapModeID();
                        CFG.mapModesManager.disableAllViews();
                        CFG.core.setActiveProvID(-1);
                        Menu_InGame_TradeRequest_SelectCiv.typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.TRADE_LEFT_DECLAREWAR;
                        CFG.menus.setMenuID(View.eINGAME_TRADE_SELECT_CIV);
                        CFG.toastM.addM(CFG.lang.get("SelectProvince"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                        RenderProvince.updateDrawProvinces();
                    } else {
                        CFG.tradeRequest.listLEFT.iDeclareWarOnCivID = -1;
                        CFG.menus.rebuildInGame_TradeRequest_Just();
                    }
                } else if (CFG.tradeRequest.listRight.iDeclareWarOnCivID <= 0) {
                    CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).iACTIVE_VIEW_MODE = CFG.mapModesManager.getActiveMapModeID();
                    CFG.mapModesManager.disableAllViews();
                    CFG.core.setActiveProvID(-1);
                    Menu_InGame_TradeRequest_SelectCiv.typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.TRADE_RIGHT_DECLAREWAR;
                    CFG.menus.setMenuID(View.eINGAME_TRADE_SELECT_CIV);
                    CFG.toastM.addM(CFG.lang.get("SelectProvince"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    RenderProvince.updateDrawProvinces();
                } else {
                    CFG.tradeRequest.listRight.iDeclareWarOnCivID = -1;
                    CFG.menus.rebuildInGame_TradeRequest_Just();
                }
                this.setMenuPosY(tempPosY);
                return;
            }
            case 3: {
                if (this.left) {
                    if (CFG.tradeRequest.listLEFT.iFormCoalitionAgainst <= 0) {
                        CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).iACTIVE_VIEW_MODE = CFG.mapModesManager.getActiveMapModeID();
                        CFG.mapModesManager.disableAllViews();
                        CFG.core.setActiveProvID(-1);
                        Menu_InGame_TradeRequest_SelectCiv.typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.TRADE_LEFT_COALITION;
                        CFG.menus.setMenuID(View.eINGAME_TRADE_SELECT_CIV);
                        CFG.toastM.addM(CFG.lang.get("SelectProvince"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                        RenderProvince.updateDrawProvinces();
                    } else {
                        CFG.tradeRequest.listLEFT.iFormCoalitionAgainst = -1;
                        CFG.menus.rebuildInGame_TradeRequest_Just();
                    }
                } else if (CFG.tradeRequest.listRight.iFormCoalitionAgainst <= 0) {
                    CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).iACTIVE_VIEW_MODE = CFG.mapModesManager.getActiveMapModeID();
                    CFG.mapModesManager.disableAllViews();
                    CFG.core.setActiveProvID(-1);
                    Menu_InGame_TradeRequest_SelectCiv.typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.TRADE_RIGHT_COALITION;
                    CFG.menus.setMenuID(View.eINGAME_TRADE_SELECT_CIV);
                    CFG.toastM.addM(CFG.lang.get("SelectProvince"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    RenderProvince.updateDrawProvinces();
                } else {
                    CFG.tradeRequest.listRight.iFormCoalitionAgainst = -1;
                    CFG.menus.rebuildInGame_TradeRequest_Just();
                }
                this.setMenuPosY(tempPosY);
                return;
            }
            case 4: {
                CFG.tradeRequest.listLEFT.defensivePact = !CFG.tradeRequest.listLEFT.defensivePact;
                CFG.tradeRequest.listRight.defensivePact = !CFG.tradeRequest.listRight.defensivePact;
                CFG.menus.rebuildInGame_TradeRequest_Just();
                this.setMenuPosY(tempPosY);
                return;
            }
            case 5: {
                CFG.tradeRequest.listLEFT.nonAggressionPact = !CFG.tradeRequest.listLEFT.nonAggressionPact;
                CFG.tradeRequest.listRight.nonAggressionPact = !CFG.tradeRequest.listRight.nonAggressionPact;
                CFG.menus.rebuildInGame_TradeRequest_Just();
                this.setMenuPosY(tempPosY);
                return;
            }
            case 6: {
                if (this.left) {
                    CFG.tradeRequest.listLEFT.proclaimIndependence = !CFG.tradeRequest.listLEFT.proclaimIndependence;
                } else {
                    CFG.tradeRequest.listRight.proclaimIndependence = !CFG.tradeRequest.listRight.proclaimIndependence;
                }
                CFG.menus.rebuildInGame_TradeRequest_Just();
                this.setMenuPosY(tempPosY);
                return;
            }
            case 7: {
                if (this.left) {
                    CFG.tradeRequest.listLEFT.militaryAccess = !CFG.tradeRequest.listLEFT.militaryAccess;
                } else {
                    CFG.tradeRequest.listRight.militaryAccess = !CFG.tradeRequest.listRight.militaryAccess;
                }
                CFG.menus.rebuildInGame_TradeRequest_Just();
                this.setMenuPosY(tempPosY);
                return;
            }
        }
    }

    public final int getW() {
        return this.getWidthM();
    }

    public final int getElementW() {
        return this.getW() - 4;
    }
}
