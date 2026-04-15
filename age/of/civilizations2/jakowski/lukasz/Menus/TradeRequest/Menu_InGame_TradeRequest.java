package age.of.civilizations2.jakowski.lukasz.Menus.TradeRequest;

import age.of.civilizations2.jakowski.lukasz.Button.Diplomacy.Button_Diplomacy_LikelihoodOfSuccess;
import age.of.civilizations2.jakowski.lukasz.Button.Diplomacy.Message.Button_Diplomacy_MessageAlliance;
import age.of.civilizations2.jakowski.lukasz.Button.Flag.Button_Flag_JustFrame;
import age.of.civilizations2.jakowski.lukasz.Button.Flag.Button_InGameAction;
import age.of.civilizations2.jakowski.lukasz.Button.MenuElemUI;
import age.of.civilizations2.jakowski.lukasz.Button.Stats.ButtonStats;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Core.Core;
import age.of.civilizations2.jakowski.lukasz.GameManager;
import age.of.civilizations2.jakowski.lukasz.GameValues.GameValues;
import age.of.civilizations2.jakowski.lukasz.IMGManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.MEHover_2E;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Flag_Big;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Text;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Text_Big;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.Menus.Relations.Actions.Menu_InGameOfferAlliance;
import age.of.civilizations2.jakowski.lukasz.Menus.Z_Rest.Menu_InGame_SelectProvinces;
import age.of.civilizations2.jakowski.lukasz.RenderProvince;
import age.of.civilizations2.jakowski.lukasz.Renderer;
import age.of.civilizations2.jakowski.lukasz.SFXManager;
import age.of.civilizations2.jakowski.lukasz.Sliders.InGame.Clear.Slider_InGame_Clear;
import age.of.civilizations2.jakowski.lukasz.Title.TitleM;
import age.of.civilizations2.jakowski.lukasz.Title.TitleM_TextSmall;
import age.of.civilizations2.jakowski.lukasz.View;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_TradeRequest
extends Menu {
    public static int iOnCivID = -1;

    public Menu_InGame_TradeRequest() {
        ArrayList<MenuElemUI> menuElements = new ArrayList<MenuElemUI>();
        int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
        int tY = CFG.PADD;
        menuElements.add(new Button_Flag_JustFrame(CFG.PADD, tY, true));
        tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        int tempMenuPosY = IMGManager.getIMG(Images.topBar).getHeight() + CFG.PADD * 2 + CFG.BUTTON_H * 3 / 5;
        this.initMenu(new TitleM(CFG.lang.get("TradeRequest"), CFG.BUTTON_H * 3 / 5, true, true), CFG.GAMEWIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD + tempMenuPosY > CFG.GAMEHEIGHT - CFG.BUTTON_H - CFG.PADD * 2 ? Math.max(CFG.GAMEHEIGHT - CFG.BUTTON_H - CFG.PADD * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 2) * 6) : ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, menuElements, false, true);
        this.updateLang();
    }

    public Menu_InGame_TradeRequest(int onCivID) {
        ArrayList<MenuElemUI> menuElements = new ArrayList<MenuElemUI>();
        iOnCivID = onCivID;
        int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
        int tY = 0;
        menuElements.add(new Button_Diplomacy_LikelihoodOfSuccess(CFG.lang.get("LikelihoodOfSuccess") + ": ", GameManager.getTradeRequest_LikelihoodOfSuccess_Text(), "" + (float)GameValues.gvTrade.COST_OFFER_TRADE_REQUEST_DIPLOMACY_POINTS / 10.0f, 2, tY, CFG.BUTTON_W * 2){

            @Override
            public int getWidthE() {
                return Menu_InGame_TradeRequest.this.getElementW() * 2 - 4;
            }
        });
        tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        if (CFG.tradeRequest.listLEFT.iGold > 0) {
            menuElements.add(new Slider_InGame_Clear(CFG.lang.get("Gold"), CFG.PADD * 2, tY, tempWidth - CFG.PADD * 3 - CFG.BUTTON_W, CFG.BUTTON_H * 3 / 4, 1, (int)CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getGold(), CFG.tradeRequest.listLEFT.iGold, 0.65f){

                @Override
                public int getWidthE() {
                    return Menu_InGame_TradeRequest.this.getElementW() - CFG.PADD * 4;
                }

                @Override
                public int getSliderHeight() {
                    return CFG.PADD * 2;
                }

                @Override
                public Color getColorLEFT() {
                    return new Color(CFG.COLOR_GOLD.r, CFG.COLOR_GOLD.g, CFG.COLOR_GOLD.b, 0.65f);
                }

                @Override
                public void actionElem(int iID) {
                    CFG.tradeRequest.listLEFT.iGold = this.getCurr();
                    try {
                        Menu_InGame_TradeRequest.this.getMenuElem(0).setText2(GameManager.getTradeRequest_LikelihoodOfSuccess_Text());
                    }
                    catch (Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            });
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        }
        if (CFG.tradeRequest.listLEFT.lProvinces.size() > 0) {
            menuElements.add(new Button_Diplomacy_MessageAlliance(Images.provinces, CFG.lang.get("Provinces") + ": " + CFG.tradeRequest.listLEFT.lProvinces.size(), CFG.tradeRequest.iCivLEFT, 2, tY, CFG.BUTTON_W * 2, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 2 + CFG.PADD * 4){

                @Override
                public int getWidthE() {
                    return Menu_InGame_TradeRequest.this.getElementW() - 2;
                }

                @Override
                public void buildElemHover() {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    for (int i = 0; i < CFG.tradeRequest.listLEFT.lProvinces.size(); ++i) {
                        nData.add(new ME_Hover_2Type_Flag(CFG.core.getProv(CFG.tradeRequest.listLEFT.lProvinces.get(i)).getCivId()));
                        nData.add(new ME_Hover_2Type_Text(CFG.core.getProv(CFG.tradeRequest.listLEFT.lProvinces.get(i)).getName(), CFG.COLOR_HOVER_TITLE));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    }
                    if (nElements.size() == 0) {
                        this.menuElemHover = null;
                        return;
                    }
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }

                @Override
                public void actionElem(int iID) {
                    CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.tradeRequest.iCivLEFT;
                    CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).iACTIVE_VIEW_MODE = CFG.mapModesManager.getActiveMapModeID();
                    CFG.mapModesManager.disableAllViews();
                    CFG.core.setActiveProvID(-1);
                    Menu_InGame_SelectProvinces.typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.TRADE_LEFT;
                    CFG.VIEW_SHOW_VALUES = false;
                    CFG.selectMode = true;
                    CFG.core.getProvSelected().clearSelectedProvinces();
                    for (int i = 0; i < CFG.tradeRequest.listLEFT.lProvinces.size(); ++i) {
                        CFG.core.getProvSelected().addProv(CFG.tradeRequest.listLEFT.lProvinces.get(i));
                    }
                    CFG.menus.setMenuID(View.eINGAME_SELECT_PROVINCES);
                    RenderProvince.updateDrawProvinces();
                }
            });
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        }
        if (CFG.tradeRequest.listLEFT.iDeclareWarOnCivID > 0) {
            menuElements.add(new Button_Diplomacy_MessageAlliance(Images.diploWar, CFG.lang.get("DeclareWar") + ": " + CFG.core.getCiv(CFG.tradeRequest.listLEFT.iDeclareWarOnCivID).getCivName(), CFG.tradeRequest.listLEFT.iDeclareWarOnCivID, 2, tY, CFG.BUTTON_W * 2, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 2 + CFG.PADD * 4){

                @Override
                public int getWidthE() {
                    return Menu_InGame_TradeRequest.this.getElementW() - 2;
                }
            });
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        }
        if (CFG.tradeRequest.listLEFT.iFormCoalitionAgainst > 0) {
            menuElements.add(new Button_Diplomacy_MessageAlliance(Images.diploWarPreparations, CFG.lang.get("FormACoalitionAgainst") + ": " + CFG.core.getCiv(CFG.tradeRequest.listLEFT.iFormCoalitionAgainst).getCivName(), CFG.tradeRequest.listLEFT.iFormCoalitionAgainst, 2, tY, CFG.BUTTON_W * 2, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 2 + CFG.PADD * 4){

                @Override
                public int getWidthE() {
                    return Menu_InGame_TradeRequest.this.getElementW() - 2;
                }
            });
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        }
        if (CFG.tradeRequest.listLEFT.defensivePact) {
            menuElements.add(new ButtonStats(CFG.lang.get("DefensivePact"), CFG.PADD * 2, 2, tY, CFG.BUTTON_W * 2, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 2 + CFG.PADD * 4){

                @Override
                public int getWidthE() {
                    return Menu_InGame_TradeRequest.this.getElementW() - 2;
                }
            });
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        }
        if (CFG.tradeRequest.listLEFT.nonAggressionPact) {
            menuElements.add(new ButtonStats(CFG.lang.get("NonAggressionPact"), CFG.PADD * 2, 2, tY, CFG.BUTTON_W * 2, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 2 + CFG.PADD * 4){

                @Override
                public int getWidthE() {
                    return Menu_InGame_TradeRequest.this.getElementW() - 2;
                }
            });
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        }
        if (CFG.tradeRequest.listLEFT.proclaimIndependence) {
            menuElements.add(new ButtonStats(CFG.lang.get("ProclaimIndependence"), CFG.PADD * 2, 2, tY, CFG.BUTTON_W * 2, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 2 + CFG.PADD * 4){

                @Override
                public int getWidthE() {
                    return Menu_InGame_TradeRequest.this.getElementW() - 2;
                }
            });
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        }
        if (CFG.tradeRequest.listLEFT.militaryAccess) {
            menuElements.add(new ButtonStats(CFG.lang.get("MilitaryAccess"), CFG.PADD * 2, 2, tY, CFG.BUTTON_W * 2, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 2 + CFG.PADD * 4){

                @Override
                public int getWidthE() {
                    return Menu_InGame_TradeRequest.this.getElementW() - 2;
                }
            });
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD;
        }
        tY = ((MenuElemUI)menuElements.get(0)).getPosY() + ((MenuElemUI)menuElements.get(0)).getHeightE();
        if (CFG.tradeRequest.listRight.iGold > 0) {
            menuElements.add(new Slider_InGame_Clear(CFG.lang.get("Gold"), CFG.PADD, tY, tempWidth - CFG.PADD * 3 - CFG.BUTTON_W, CFG.BUTTON_H * 3 / 4, 1, 100000, CFG.tradeRequest.listRight.iGold, 0.65f){

                @Override
                public int getWidthE() {
                    return Menu_InGame_TradeRequest.this.getElementW() - CFG.PADD * 4;
                }

                @Override
                public int getPosXE() {
                    return Menu_InGame_TradeRequest.this.getElementW() + CFG.PADD * 2;
                }

                @Override
                public int getSliderHeight() {
                    return CFG.PADD * 2;
                }

                @Override
                public Color getColorLEFT() {
                    return new Color(CFG.COLOR_GOLD.r, CFG.COLOR_GOLD.g, CFG.COLOR_GOLD.b, 0.65f);
                }

                @Override
                public void actionElem(int iID) {
                    CFG.tradeRequest.listRight.iGold = this.getCurr();
                    try {
                        Menu_InGame_TradeRequest.this.getMenuElem(0).setText2(GameManager.getTradeRequest_LikelihoodOfSuccess_Text());
                    }
                    catch (Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            });
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        }
        if (CFG.tradeRequest.listRight.lProvinces.size() > 0) {
            menuElements.add(new Button_Diplomacy_MessageAlliance(Images.provinces, CFG.lang.get("Provinces") + ": " + CFG.tradeRequest.listRight.lProvinces.size(), CFG.tradeRequest.iCivRIGHT, 2, tY, CFG.BUTTON_W * 2, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 2 + CFG.PADD * 4){

                @Override
                public int getPosXE() {
                    return Menu_InGame_TradeRequest.this.getElementW() - 2;
                }

                @Override
                public int getWidthE() {
                    return Menu_InGame_TradeRequest.this.getElementW();
                }

                @Override
                public void buildElemHover() {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    for (int i = 0; i < CFG.tradeRequest.listRight.lProvinces.size(); ++i) {
                        nData.add(new ME_Hover_2Type_Flag(CFG.core.getProv(CFG.tradeRequest.listRight.lProvinces.get(i)).getCivId()));
                        nData.add(new ME_Hover_2Type_Text(CFG.core.getProv(CFG.tradeRequest.listRight.lProvinces.get(i)).getName(), CFG.COLOR_HOVER_TITLE));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    }
                    if (nElements.size() == 0) {
                        this.menuElemHover = null;
                        return;
                    }
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }

                @Override
                public void actionElem(int iID) {
                    CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.tradeRequest.iCivRIGHT;
                    CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).iACTIVE_VIEW_MODE = CFG.mapModesManager.getActiveMapModeID();
                    CFG.mapModesManager.disableAllViews();
                    CFG.core.setActiveProvID(-1);
                    Menu_InGame_SelectProvinces.typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.TRADE_RIGHT;
                    CFG.VIEW_SHOW_VALUES = false;
                    CFG.selectMode = true;
                    CFG.core.getProvSelected().clearSelectedProvinces();
                    for (int i = 0; i < CFG.tradeRequest.listRight.lProvinces.size(); ++i) {
                        CFG.core.getProvSelected().addProv(CFG.tradeRequest.listRight.lProvinces.get(i));
                    }
                    CFG.menus.setMenuID(View.eINGAME_SELECT_PROVINCES);
                    RenderProvince.updateDrawProvinces();
                }
            });
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        }
        if (CFG.tradeRequest.listRight.iDeclareWarOnCivID > 0) {
            menuElements.add(new Button_Diplomacy_MessageAlliance(Images.diploWar, CFG.lang.get("DeclareWar") + ": " + CFG.core.getCiv(CFG.tradeRequest.listRight.iDeclareWarOnCivID).getCivName(), CFG.tradeRequest.listRight.iDeclareWarOnCivID, 2, tY, CFG.BUTTON_W * 2, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 2 + CFG.PADD * 4){

                @Override
                public int getPosXE() {
                    return Menu_InGame_TradeRequest.this.getElementW() - 2;
                }

                @Override
                public int getWidthE() {
                    return Menu_InGame_TradeRequest.this.getElementW();
                }
            });
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        }
        if (CFG.tradeRequest.listRight.iFormCoalitionAgainst > 0) {
            menuElements.add(new Button_Diplomacy_MessageAlliance(Images.diploWarPreparations, CFG.lang.get("FormACoalitionAgainst") + ": " + CFG.core.getCiv(CFG.tradeRequest.listRight.iFormCoalitionAgainst).getCivName(), CFG.tradeRequest.listRight.iFormCoalitionAgainst, 2, tY, CFG.BUTTON_W * 2, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 2 + CFG.PADD * 4){

                @Override
                public int getPosXE() {
                    return Menu_InGame_TradeRequest.this.getElementW() - 2;
                }

                @Override
                public int getWidthE() {
                    return Menu_InGame_TradeRequest.this.getElementW();
                }
            });
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        }
        if (CFG.tradeRequest.listRight.defensivePact) {
            menuElements.add(new ButtonStats(CFG.lang.get("DefensivePact"), CFG.PADD * 2, 2, tY, CFG.BUTTON_W * 2, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 2 + CFG.PADD * 4){

                @Override
                public int getPosXE() {
                    return Menu_InGame_TradeRequest.this.getElementW() - 2;
                }

                @Override
                public int getWidthE() {
                    return Menu_InGame_TradeRequest.this.getElementW();
                }
            });
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        }
        if (CFG.tradeRequest.listRight.nonAggressionPact) {
            menuElements.add(new ButtonStats(CFG.lang.get("NonAggressionPact"), CFG.PADD * 2, 2, tY, CFG.BUTTON_W * 2, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 2 + CFG.PADD * 4){

                @Override
                public int getPosXE() {
                    return Menu_InGame_TradeRequest.this.getElementW() - 2;
                }

                @Override
                public int getWidthE() {
                    return Menu_InGame_TradeRequest.this.getElementW();
                }
            });
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        }
        if (CFG.tradeRequest.listRight.proclaimIndependence) {
            menuElements.add(new ButtonStats(CFG.lang.get("ProclaimIndependence"), CFG.PADD * 2, 2, tY, CFG.BUTTON_W * 2, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 2 + CFG.PADD * 4){

                @Override
                public int getPosXE() {
                    return Menu_InGame_TradeRequest.this.getElementW() - 2;
                }

                @Override
                public int getWidthE() {
                    return Menu_InGame_TradeRequest.this.getElementW();
                }
            });
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        }
        if (CFG.tradeRequest.listRight.militaryAccess) {
            menuElements.add(new ButtonStats(CFG.lang.get("MilitaryAccess"), CFG.PADD * 2, 2, tY, CFG.BUTTON_W * 2, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 2 + CFG.PADD * 4){

                @Override
                public int getPosXE() {
                    return Menu_InGame_TradeRequest.this.getElementW() - 2;
                }

                @Override
                public int getWidthE() {
                    return Menu_InGame_TradeRequest.this.getElementW();
                }
            });
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        }
        for (int i = 0; i < menuElements.size(); ++i) {
            if (((MenuElemUI)menuElements.get(i)).getPosY() + ((MenuElemUI)menuElements.get(i)).getHeightE() <= tY) continue;
            tY = ((MenuElemUI)menuElements.get(i)).getPosY() + ((MenuElemUI)menuElements.get(i)).getHeightE();
        }
        menuElements.add(new Button_InGameAction(CFG.lang.get("Cancel"), -1, CFG.PADD, tY += CFG.PADD, CFG.BUTTON_W, true){

            @Override
            public int getWidthE() {
                return Menu_InGame_TradeRequest.this.getElementW() - CFG.PADD - CFG.PADD / 2;
            }

            @Override
            public int getPosY() {
                return Menu_InGame_TradeRequest.this.getH() - this.getHeightE() - CFG.PADD > super.getPosY() ? Menu_InGame_TradeRequest.this.getH() - this.getHeightE() - CFG.PADD : super.getPosY();
            }
        });
        menuElements.add(new Button_InGameAction(CFG.lang.get("SendProposal"), -1, 0, tY, CFG.BUTTON_W, true){

            @Override
            public int getPosXE() {
                return Menu_InGame_TradeRequest.this.getElementW() + CFG.PADD / 2;
            }

            @Override
            public int getWidthE() {
                return Menu_InGame_TradeRequest.this.getElementW() - CFG.PADD - CFG.PADD / 2;
            }

            @Override
            public int getPosY() {
                return Menu_InGame_TradeRequest.this.getH() - this.getHeightE() - CFG.PADD > super.getPosY() ? Menu_InGame_TradeRequest.this.getH() - this.getHeightE() - CFG.PADD : super.getPosY();
            }

            @Override
            public void buildElemHover() {
                ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("SendProposal") + ":", CFG.COLOR_HOVER_TITLE));
                nData.add(new ME_Hover_2Type_Flag_Big(iOnCivID, CFG.PADD, CFG.PADD));
                nData.add(new ME_Hover_2Type_Text_Big(CFG.core.getCiv(iOnCivID).getCivName()));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Text(CFG.lang.get("DiplomacyPoints") + ": ", CFG.COLOR_HOVER_TITLE));
                nData.add(new ME_Hover_2Type_Text("-" + (float)GameValues.gvTrade.COST_OFFER_TRADE_REQUEST_DIPLOMACY_POINTS / 10.0f, CFG.COLOR_NEGATIVE_2));
                nData.add(new ME_Hover_2Type_Image(Images.topDiplomacyPoints, CFG.PADD, 0));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                this.menuElemHover = new ME_Hover_v2(nElements);
            }

            @Override
            public void drawTextE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                IMGManager.getIMG(Images.diploTrade).drawO(oSB, this.getPosXE() + this.getWidthE() / 2 - (this.getTextWidthU() + IMGManager.getIMG(Images.diploTrade).getWidth() + CFG.PADD) / 2 + iTranslateX, this.getPosY() + this.getHeightE() / 2 - IMGManager.getIMG(Images.diploTrade).getHeight() / 2 + iTranslateY);
                Renderer.drawText(oSB, this.fontID, this.getTextE(), this.getPosXE() + (this.getTextPosElem() < 0 ? this.getWidthE() / 2 - (this.getTextWidthU() + IMGManager.getIMG(Images.diploTrade).getWidth() + CFG.PADD) / 2 + IMGManager.getIMG(Images.diploTrade).getWidth() + CFG.PADD : this.getTextPosElem()) + iTranslateX, this.getPosY() + this.getHeightE() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColorE(isActive));
            }

            @Override
            public boolean getIsClickable() {
                return CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getDiploPoints() >= GameValues.gvTrade.COST_OFFER_TRADE_REQUEST_DIPLOMACY_POINTS && CFG.tradeRequest.canBeSend();
            }

            @Override
            public int getSFXElem() {
                return SFXManager.getSend();
            }
        });
        int tempMenuPosY = IMGManager.getIMG(Images.topBar).getHeight() + CFG.PADD * 2 + CFG.BUTTON_H * 3 / 4;
        this.initMenu(new TitleM_TextSmall(CFG.lang.get("TradeRequest"), CFG.BUTTON_H * 3 / 4, true, true){

            @Override
            public void drawT(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                IMGManager.getIMG(Images.gameTopEdge).draw2O(oSB, nPosX - Core.PADDING + iTranslateX, nPosY - this.getHeightT() - Core.PADDING - IMGManager.getIMG(Images.gameTopEdge).getHeight(), nWidth - IMGManager.getIMG(Images.gameTopEdge).getWidth() + Core.PADDING * 2, this.getHeightT() + Core.PADDING);
                IMGManager.getIMG(Images.gameTopEdge).draw2O(oSB, nPosX + Core.PADDING + nWidth - IMGManager.getIMG(Images.gameTopEdge).getWidth() + iTranslateX, nPosY - Core.PADDING - this.getHeightT() - IMGManager.getIMG(Images.gameTopEdge).getHeight(), IMGManager.getIMG(Images.gameTopEdge).getWidth(), this.getHeightT() + Core.PADDING, true, false);
                oSB.setColor(new Color(0.28235295f, 0.4627451f, 1.0f, 0.165f));
                IMGManager.getIMG(Images.line32Off1).drawO(oSB, nPosX + 2 + iTranslateX, nPosY - this.getHeightT() - IMGManager.getIMG(Images.line32Off1).getHeight(), nWidth - 4, this.getHeightT() - 2, false, true);
                oSB.setColor(new Color(0.28235295f, 0.4627451f, 1.0f, 0.375f));
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
                try {
                    CFG.core.getCiv(CFG.tradeRequest.iCivLEFT).getFlagC().drawO(oSB, Menu_InGame_TradeRequest.this.getPosX() + CFG.PADD * 2 + iTranslateX, Menu_InGame_TradeRequest.this.getPosY() - this.getHeightT() / 2 - IMGManager.getIMG(Images.flagRectSmall).getHeight() / 2 - CFG.core.getCiv(CFG.tradeRequest.iCivLEFT).getFlagC().getHeight(), IMGManager.getIMG(Images.flagRectSmall).getWidth(), IMGManager.getIMG(Images.flagRectSmall).getHeight());
                    IMGManager.getIMG(Images.flagRectSmall).drawO(oSB, Menu_InGame_TradeRequest.this.getPosX() + CFG.PADD * 2 + iTranslateX, Menu_InGame_TradeRequest.this.getPosY() - this.getHeightT() / 2 - IMGManager.getIMG(Images.flagRectSmall).getHeight() / 2 - IMGManager.getIMG(Images.flagRectSmall).getHeight(), IMGManager.getIMG(Images.flagRectSmall).getWidth(), IMGManager.getIMG(Images.flagRectSmall).getHeight());
                    CFG.core.getCiv(CFG.tradeRequest.iCivRIGHT).getFlagC().drawO(oSB, Menu_InGame_TradeRequest.this.getPosX() + Menu_InGame_TradeRequest.this.getWidthM() - IMGManager.getIMG(Images.flagRectSmall).getWidth() - CFG.PADD * 2 + iTranslateX, Menu_InGame_TradeRequest.this.getPosY() - this.getHeightT() / 2 - IMGManager.getIMG(Images.flagRectSmall).getHeight() / 2 - CFG.core.getCiv(CFG.tradeRequest.iCivRIGHT).getFlagC().getHeight(), IMGManager.getIMG(Images.flagRectSmall).getWidth(), IMGManager.getIMG(Images.flagRectSmall).getHeight());
                    IMGManager.getIMG(Images.flagRectSmall).drawO(oSB, Menu_InGame_TradeRequest.this.getPosX() + Menu_InGame_TradeRequest.this.getWidthM() - IMGManager.getIMG(Images.flagRectSmall).getWidth() - CFG.PADD * 2 + iTranslateX, Menu_InGame_TradeRequest.this.getPosY() - this.getHeightT() / 2 - IMGManager.getIMG(Images.flagRectSmall).getHeight() / 2 - IMGManager.getIMG(Images.flagRectSmall).getHeight(), IMGManager.getIMG(Images.flagRectSmall).getWidth(), IMGManager.getIMG(Images.flagRectSmall).getHeight());
                }
                catch (IndexOutOfBoundsException ex) {
                    Menu_InGame_TradeRequest.this.setVisibleM(false);
                }
                IMGManager.getIMG(Images.diploTrade).drawO(oSB, nPosX + (nWidth - this.getTextWidth()) / 2 - CFG.PADD - IMGManager.getIMG(Images.diploTrade).getWidth() + iTranslateX, 2 + nPosY - this.getHeightT() + this.getHeightT() / 2 - IMGManager.getIMG(Images.diploTrade).getHeight() / 2);
                Renderer.drawText(oSB, CFG.FONT_BOLD_SMALL, this.getText(), nPosX + (nWidth - this.getTextWidth()) / 2 + iTranslateX, 2 + nPosY - this.getHeightT() + (this.getHeightT() - this.getTextHeight()) / 2, Color.WHITE);
            }
        }, CFG.GAMEWIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD + tempMenuPosY > CFG.GAMEHEIGHT - CFG.BUTTON_H - CFG.PADD * 2 ? Math.max(CFG.GAMEHEIGHT - CFG.BUTTON_H - CFG.PADD * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 2) * 6) : ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, menuElements, true, false);
        this.updateLang();
        for (int i = 0; i < this.getMenuElemsSize(); ++i) {
            if (this.getMenuElem(i).getCurr() <= 0) continue;
            this.getMenuElem(i).setCurr(this.getMenuElem(i).getCurr());
        }
        Menu_InGameOfferAlliance.lTime = System.currentTimeMillis();
    }

    @Override
    public void updateLang() {
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        oSB.setColor(Color.WHITE);
        IMGManager.getIMG(Images.gameTopEdge).draw2O(oSB, this.getPosX() - Core.PADDING + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gameTopEdge).getHeight() + iTranslateY, this.getWidthM() + Core.PADDING * 2 - IMGManager.getIMG(Images.gameTopEdge).getWidth(), this.getHeightM() + CFG.PADD, false, true);
        IMGManager.getIMG(Images.gameTopEdge).draw2O(oSB, this.getPosX() + this.getWidthM() + Core.PADDING - IMGManager.getIMG(Images.gameTopEdge).getWidth() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gameTopEdge).getHeight() + iTranslateY, IMGManager.getIMG(Images.gameTopEdge).getWidth(), this.getHeightM() + CFG.PADD, true, true);
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_BLUE.r, CFG.COLOR_GRADIENT_BLUE.g, CFG.COLOR_GRADIENT_BLUE.b, 0.75f));
        IMGManager.getIMG(Images.line32Vertical).drawO(oSB, this.getPosX() + this.getWidthM() / 2 + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.line32Vertical).getHeight() + iTranslateY, 1, this.getHeightM() - 2);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
        IMGManager.getIMG(Images.gradient).drawO(oSB, this.getPosX() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gradient).getHeight() + iTranslateY, this.getWidthM(), this.getHeightM() / 4);
        IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosX() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.pix255).getHeight() + iTranslateY, this.getWidthM(), 1);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
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
        if (iID == this.getMenuElemsSize() - 1) {
            GameManager.sendTradeRequest(iOnCivID, CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.tradeRequest);
            CFG.menus.updateInGameTopAll(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId());
            CFG.toastM.addM(CFG.lang.get("Sent") + "!", CFG.COLOR_POSITIVE);
            CFG.toastM.setTimeInView(3500);
            this.setVisibleM(false);
            return;
        }
        if (iID == this.getMenuElemsSize() - 2) {
            this.setVisibleM(false);
            return;
        }
        if (iID == 0) {
            return;
        }
        this.getMenuElem(iID).actionElem(iID);
    }

    public final int getW() {
        return this.getWidthM();
    }

    public final int getElementW() {
        return this.getW() / 2;
    }

    @Override
    public void setVisibleM(boolean visible) {
        super.setVisibleM(visible);
        if (!visible) {
            for (int i = 0; i < this.getMenuElemsSize(); ++i) {
                this.getMenuElem(i).setVisibleE(false);
            }
            CFG.menus.getInGame_SendMessage_TradeLEFT().setVisibleM(false);
            CFG.menus.getInGame_SendMessage_TradeRIGHT().setVisibleM(false);
        }
    }

    @Override
    public void setPosX(int iPosX) {
        super.setPosX(iPosX);
        try {
            CFG.menus.getInGame_SendMessage_TradeLEFT().setPosX(this.getPosX() - CFG.menus.getInGame_SendMessage_TradeLEFT().getWidthM());
            CFG.menus.getInGame_SendMessage_TradeRIGHT().setPosX(this.getPosX() + this.getWidthM());
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
    }

    @Override
    public void setPosY(int iPosY) {
        super.setPosY(iPosY);
        try {
            CFG.menus.getInGame_SendMessage_TradeLEFT().setPosY(this.getPosY());
            CFG.menus.getInGame_SendMessage_TradeRIGHT().setPosY(this.getPosY());
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
    }

    @Override
    public boolean setWidth(int iWidth) {
        boolean out = super.setWidth(iWidth);
        try {
            CFG.menus.getInGame_SendMessage_TradeRIGHT().setPosX(this.getPosX() + this.getWidthM());
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        return out;
    }

    @Override
    public void setHeight(int iHeight) {
        super.setHeight(iHeight);
        try {
            CFG.menus.getInGame_SendMessage_TradeLEFT().setHeight(this.getHeightM());
            CFG.menus.getInGame_SendMessage_TradeRIGHT().setHeight(this.getHeightM());
            CFG.menus.getInGame_SendMessage_TradeLEFT().updateScrollable();
            CFG.menus.getInGame_SendMessage_TradeRIGHT().updateScrollable();
            CFG.menus.getInGame_SendMessage_TradeLEFT().updateMenuElements_IsInView();
            CFG.menus.getInGame_SendMessage_TradeRIGHT().updateMenuElements_IsInView();
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
    }

    public final int getH() {
        return this.getHeightM();
    }
}
