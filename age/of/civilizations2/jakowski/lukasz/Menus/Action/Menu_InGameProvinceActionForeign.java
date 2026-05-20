package age.of.civilizations2.jakowski.lukasz.Menus.Action;

import age.of.civilizations2.jakowski.lukasz.Button.Game.Button_Game;
import age.of.civilizations2.jakowski.lukasz.Button.Game.Button_GameInvestForeign;
import age.of.civilizations2.jakowski.lukasz.Button.Game.Button_GameNuke;
import age.of.civilizations2.jakowski.lukasz.Button.MenuElemUI;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.GameManager;
import age.of.civilizations2.jakowski.lukasz.GameValues.GameValues;
import age.of.civilizations2.jakowski.lukasz.IMGManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MapA.Plagues.Nuke.NukeManager;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.MEHover_2E;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Flag_Big;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Image_Big;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Text;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_TextDesc;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Text_Big;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.Menus.Action.Menu_InGameProvAction;
import age.of.civilizations2.jakowski.lukasz.Menus.Build.Menu_InGame_BuildForeign;
import age.of.civilizations2.jakowski.lukasz.Menus.Civilization.Menu_InGame_Civ;
import age.of.civilizations2.jakowski.lukasz.Menus.Civilization.Menu_InGame_Civ_Actions;
import age.of.civilizations2.jakowski.lukasz.Menus.ProvinceM.More.Menu_InGame_Province_More;
import age.of.civilizations2.jakowski.lukasz.Renderer;
import age.of.civilizations2.jakowski.lukasz.Touch;
import age.of.civilizations2.jakowski.lukasz.TouchManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGameProvinceActionForeign
extends Menu {
    @Override
    public int getPosX() {
        if (CFG.menus.getInGame_ProvincemMore_Visible() && Menu_InGame_Province_More.toTheBottom) {
            return super.getPosX() + CFG.CIV_INFO_MENU_WIDTH + Menu_InGame_Province_More.getExtraW() + Menu_InGame_Province_More.extraPosX;
        }
        if (CFG.menus.getInGame_Civ_Info().getVisibleM() && CFG.menus.getInGame_Civ_Info_Actions().getVisibleM() && Menu_InGame_Civ_Actions.toTheBottom) {
            return super.getPosX() + Menu_InGame_Civ.getMenuCivInfoWidth() + Menu_InGame_Civ_Actions.extraPosX;
        }
        return super.getPosX();
    }

    @Override
    public int getMenuPosX() {
        if (CFG.menus.getInGame_ProvincemMore_Visible() && Menu_InGame_Province_More.toTheBottom) {
            return super.getMenuPosX() + CFG.CIV_INFO_MENU_WIDTH + Menu_InGame_Province_More.getExtraW() + Menu_InGame_Province_More.extraPosX;
        }
        if (CFG.menus.getInGame_Civ_Info().getVisibleM() && CFG.menus.getInGame_Civ_Info_Actions().getVisibleM() && Menu_InGame_Civ_Actions.toTheBottom) {
            return super.getMenuPosX() + Menu_InGame_Civ.getMenuCivInfoWidth() + Menu_InGame_Civ_Actions.extraPosX;
        }
        return super.getMenuPosX();
    }

    public Menu_InGameProvinceActionForeign() {
        ArrayList<MenuElemUI> menuElements = new ArrayList<MenuElemUI>();
        int nX = CFG.PADD;
        menuElements.add(new Button_GameInvestForeign(null, -1, nX, CFG.PADD, true, Images.investF1){
            public String sKey;
            public int iKeyWidth;

            @Override
            public void setTextE(String sText) {
                try {
                    super.setTextE(sText);
                    int tWMax = 0;
                    while (this.iTextWidth > this.getWidthE() - CFG.PADD && this.getTextE().length() > 5 && ++tWMax < 100) {
                        super.setTextE(this.getTextE().substring(0, Math.max(1, this.getTextE().length() - 3)) + "..");
                    }
                }
                catch (Exception ex) {
                    CFG.exceptionStack(ex);
                }
                this.sKey = "1";
                CFG.glyphLay.setText(CFG.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sKey);
                this.iKeyWidth = (int)CFG.glyphLay.width;
            }

            @Override
            public void drawTextE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                Renderer.drawTextWithShadowAlpha(oSB, CFG.FONT_REGULAR_SMALL, this.sKey, this.getPosXE() + this.getWidthE() - CFG.PADD - this.iKeyWidth + iTranslateX, this.getPosY() + CFG.PADD + iTranslateY, this.getColorE(isActive));
                super.drawTextE(oSB, iTranslateX, iTranslateY, isActive);
            }

            @Override
            public void actionElem(int iID) {
                Menu_InGameProvinceActionForeign.investForeign();
            }

            @Override
            public void actionElemPPM() {
                if (CFG.core.getActiveProvID() >= 0) {
                    try {
                        int provinceID = CFG.core.getActiveProvID();
                        if (CFG.core.getProv(provinceID).getCivId() <= 0) {
                            CFG.toastM.addM(CFG.lang.get("Civilization") + ": " + CFG.lang.get("Neutral"), CFG.COLOR_NEGATIVE_1);
                        } else if (CFG.core.getProv(provinceID).getCivId() == CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) {
                            CFG.toastM.addM(CFG.lang.get("Civilization") + ": " + CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getCivName(), CFG.COLOR_NEGATIVE_1);
                        } else if (CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).areSanctionsAdded(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.core.getProv(provinceID).getCivId()) || CFG.core.getCiv(CFG.core.getProv(provinceID).getCivId()).areSanctionsAdded(CFG.core.getProv(provinceID).getCivId(), CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())) {
                            CFG.toastM.addM(CFG.lang.get("SanctionsBox1"), CFG.COLOR_NEGATIVE_1);
                        } else if (CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getGold() > 49L) {
                            GameManager.investForeignEconomy(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), provinceID, GameManager.invest_MaxEconomy_Gold(provinceID, CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()));
                            CFG.menus.updateInGameTopAll(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId());
                            CFG.gameAction.updateInGame_ProvinceInfo();
                            CFG.toastM.addM(CFG.lang.get("Ok") + "!", CFG.COLOR_POSITIVE);
                            CFG.toastM.setTimeInView(3500);
                            CFG.menus.rebuildMenu_InGame_Infobox(CFG.lang.get("InvestInForeignProvince"), CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.core.getProv(provinceID).getCivId(), Images.infoEconomy);
                        }
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                }
            }

            @Override
            public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElemHover != null) {
                    this.menuElemHover.drawAlwaysOverM(oSB, Touch.getMousePosX(), CFG.GAMEHEIGHT - CFG.map.getMpB().getMinimapHeight() - CFG.BUTTON_H - CFG.PADD * 2);
                }
            }

            @Override
            public void buildElemHover() {
                ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("InvestInForeignProvince"), CFG.COLOR_HOVER_TITLE));
                nData.add(new ME_Hover_2Type_Image_Big(Images.investF1, CFG.PADD, 0));
                try {
                    nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getProv(CFG.core.getActiveProvID()).getCivId(), CFG.PADD, 0));
                }
                catch (Exception exception) {
                    // empty catch block
                }
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Space());
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_TextDesc(CFG.lang.get("ForeignInvestYourGoldDirectlyDesc")));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                this.menuElemHover = new ME_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_GameInvestForeign(null, -1, nX += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getWidthE() + CFG.PADD, CFG.PADD, true, Images.investB1){
            public String sKey;
            public int iKeyWidth;

            @Override
            public void setTextE(String sText) {
                try {
                    super.setTextE(sText);
                    int tWMax = 0;
                    while (this.iTextWidth > this.getWidthE() - CFG.PADD && this.getTextE().length() > 5 && ++tWMax < 100) {
                        super.setTextE(this.getTextE().substring(0, Math.max(1, this.getTextE().length() - 3)) + "..");
                    }
                }
                catch (Exception ex) {
                    CFG.exceptionStack(ex);
                }
                this.sKey = "2";
                CFG.glyphLay.setText(CFG.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sKey);
                this.iKeyWidth = (int)CFG.glyphLay.width;
            }

            @Override
            public void drawTextE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                Renderer.drawTextWithShadowAlpha(oSB, CFG.FONT_REGULAR_SMALL, this.sKey, this.getPosXE() + this.getWidthE() - CFG.PADD - this.iKeyWidth + iTranslateX, this.getPosY() + CFG.PADD + iTranslateY, this.getColorE(isActive));
                super.drawTextE(oSB, iTranslateX, iTranslateY, isActive);
            }

            @Override
            public void actionElem(int iID) {
                Menu_InGameProvinceActionForeign.buildForeign();
            }

            @Override
            public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElemHover != null) {
                    this.menuElemHover.drawAlwaysOverM(oSB, Touch.getMousePosX(), CFG.GAMEHEIGHT - CFG.map.getMpB().getMinimapHeight() - CFG.BUTTON_H - CFG.PADD * 2);
                }
            }

            @Override
            public void buildElemHover() {
                ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("BuildInForeignProvince"), CFG.COLOR_HOVER_TITLE));
                nData.add(new ME_Hover_2Type_Image_Big(Images.investB1, CFG.PADD, 0));
                try {
                    nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getProv(CFG.core.getActiveProvID()).getCivId(), CFG.PADD, 0));
                }
                catch (Exception exception) {
                    // empty catch block
                }
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Space());
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_TextDesc(CFG.lang.get("ForeignInvestYourGoldDirectlyDesc")));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                this.menuElemHover = new ME_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Game(null, -1, nX += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getWidthE() + CFG.PADD, CFG.PADD, true){
            public String sKey;
            public int iKeyWidth;

            @Override
            public void setTextE(String sText) {
                try {
                    super.setTextE(sText);
                    int tWMax = 0;
                    while (this.iTextWidth > this.getWidthE() - CFG.PADD && this.getTextE().length() > 5 && ++tWMax < 100) {
                        super.setTextE(this.getTextE().substring(0, Math.max(1, this.getTextE().length() - 3)) + "..");
                    }
                }
                catch (Exception ex) {
                    CFG.exceptionStack(ex);
                }
                this.sKey = "Y";
                CFG.glyphLay.setText(CFG.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sKey);
                this.iKeyWidth = (int)CFG.glyphLay.width;
            }

            @Override
            public void drawTextE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                Renderer.drawTextWithShadowAlpha(oSB, CFG.FONT_REGULAR_SMALL, this.sKey, this.getPosXE() + this.getWidthE() - CFG.PADD - this.iKeyWidth + iTranslateX, this.getPosY() + CFG.PADD + iTranslateY, this.getColorE(isActive));
                super.drawTextE(oSB, iTranslateX, iTranslateY, isActive);
            }

            @Override
            public void actionElem(int iID) {
                Menu_InGameProvAction.clickOffensive();
            }

            @Override
            public boolean getVisibleE() {
                try {
                    if (TouchManager.lMABX.size() > 1) {
                        return true;
                    }
                    if (CFG.core.getProv(CFG.core.getActiveProvID()).getArmyCivID1(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) > 0) {
                        for (int a = 0; a < CFG.core.getProv(CFG.core.getActiveProvID()).getNeighProvincesSize(); ++a) {
                            if (!CFG.core.getCivsAtWar(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.core.getProv(CFG.core.getProv(CFG.core.getActiveProvID()).getNeighProvinces(a)).getCivId())) continue;
                            return true;
                        }
                    }
                    return false;
                }
                catch (Exception ex) {
                    return false;
                }
            }

            @Override
            public void drawButtonBGE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawButtonBGE(oSB, iTranslateX, iTranslateY, isActive);
                if (isActive) {
                    oSB.setColor(Color.WHITE);
                } else if (this.getIsHovered()) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.75f));
                } else {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.575f));
                }
                IMGManager.getIMG(Images.actAttack).drawO(oSB, this.getPosXE() + this.getWidthE() - IMGManager.getIMG(Images.actAttack).getWidth() - CFG.PADD + iTranslateX, this.getPosY() + this.getHeightE() - IMGManager.getIMG(Images.actAttack).getHeight() - CFG.PADD + iTranslateY);
                oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElemHover() {
                try {
                    if (CFG.getIsDesktop()) {
                        ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                        ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                        nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()));
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("Offensive"), CFG.COLOR_HOVER_TITLE));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Space());
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Shortcut") + ": "));
                        nData.add(new ME_Hover_2Type_Text("Y", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nData.add(new ME_Hover_2Type_Image(Images.key, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Space());
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_TextDesc(CFG.lang.get("AttackAllNeighboringEnemyProvinces")));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        this.menuElemHover = new ME_Hover_v2(nElements);
                    } else {
                        this.menuElemHover = null;
                    }
                }
                catch (Exception ex) {
                    this.menuElemHover = null;
                }
            }
        });
        menuElements.add(new Button_GameNuke(null, -1, nX += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getWidthE() + CFG.PADD, CFG.PADD, true){
            public String sKey;
            public int iKeyWidth;

            @Override
            public void setTextE(String sText) {
                try {
                    super.setTextE(sText);
                    int tWMax = 0;
                    while (this.iTextWidth > this.getWidthE() - CFG.PADD && this.getTextE().length() > 5 && ++tWMax < 100) {
                        super.setTextE(this.getTextE().substring(0, Math.max(1, this.getTextE().length() - 3)) + "..");
                    }
                }
                catch (Exception ex) {
                    CFG.exceptionStack(ex);
                }
                this.sKey = "U";
                CFG.glyphLay.setText(CFG.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sKey);
                this.iKeyWidth = (int)CFG.glyphLay.width;
            }

            @Override
            public int getPosXE() {
                if (!Menu_InGameProvinceActionForeign.this.getMenuElem(2).getVisibleE()) {
                    return Menu_InGameProvinceActionForeign.this.getMenuElem(2).getPosXE();
                }
                return super.getPosXE();
            }

            @Override
            public void drawTextE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                Renderer.drawTextWithShadowAlpha(oSB, CFG.FONT_REGULAR_SMALL, this.sKey, this.getPosXE() + this.getWidthE() - CFG.PADD - this.iKeyWidth + iTranslateX, this.getPosY() + CFG.PADD + iTranslateY, this.getColorE(isActive));
                super.drawTextE(oSB, iTranslateX, iTranslateY, isActive);
            }

            @Override
            public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElemHover != null) {
                    this.menuElemHover.drawAlwaysOverM(oSB, Touch.getMousePosX(), CFG.GAMEHEIGHT - CFG.map.getMpB().getMinimapHeight() - CFG.BUTTON_H - CFG.PADD * 2);
                }
            }

            @Override
            public void buildElemHover() {
                ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                if (CFG.core.getActiveProvID() >= 0) {
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("DropAtomicBomb") + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.core.getProv(CFG.core.getActiveProvID()).getProvName()));
                    nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getProv(CFG.core.getActiveProvID()).getTrueOwnerOfProv(), CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Space());
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Population") + ": "));
                    nData.add(new ME_Hover_2Type_Text(CFG.getNumberWthSpaces("" + CFG.core.getProv(CFG.core.getActiveProvID()).getPop().getPops()), CFG.COLOR_POPULATION));
                    nData.add(new ME_Hover_2Type_Image(Images.pop, CFG.PADD, 0));
                    for (int a = 0; a < CFG.core.getProv(CFG.core.getActiveProvID()).getPop().getNatsSize() && a < 4; ++a) {
                        nData.add(new ME_Hover_2Type_Flag(CFG.core.getProv(CFG.core.getActiveProvID()).getPop().getCivID(a), CFG.PADD, 0));
                    }
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Economy") + ": "));
                    nData.add(new ME_Hover_2Type_Text(CFG.getNumberWthSpaces("" + CFG.core.getProv(CFG.core.getActiveProvID()).getEco()), CFG.COLOR_ECONOMY));
                    nData.add(new ME_Hover_2Type_Image(Images.economy, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                }
                nData.add(new ME_Hover_2Type_Space());
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), 0, CFG.PADD));
                nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("AtomicBombs") + ": "));
                nData.add(new ME_Hover_2Type_Text_Big(CFG.getNumberWthSpaces("" + CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).civGD.iNukes), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nData.add(new ME_Hover_2Type_Image_Big(Images.nuke, CFG.PADD, 0));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                this.menuElemHover = new ME_Hover_v2(nElements);
            }

            @Override
            public boolean getVisibleE() {
                return super.getVisibleE() && CFG.ENABLE_NUKES && (GameValues.gvAtomic.PROVINCE_ACTION_NUKE_VISIBLE_ALL_THE_TIME || CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).civGD.iNukes > 0 || NukeManager.canBuildNuke_TechLvl(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()));
            }

            @Override
            public void actionElem(int iID) {
                Menu_InGameProvinceActionForeign.useNuke();
            }
        });
        this.initMenu(null, 0, CFG.GAMEHEIGHT - CFG.map.getMpB().getMinimapHeight() - CFG.BUTTON_H - CFG.PADD * 2, CFG.GAMEWIDTH, CFG.BUTTON_H + CFG.PADD * 2, menuElements, false, false);
        this.updateLang();
        CFG.fMOVE_MENU_PERCENTAGE = 5.0f;
        CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
    }

    @Override
    public void updateLang() {
        this.getMenuElem(0).setTextE(CFG.lang.get("Invest"));
        this.getMenuElem(1).setTextE(CFG.lang.get("Build"));
        this.getMenuElem(2).setTextE(CFG.lang.get("Attack"));
        this.getMenuElem(3).setTextE(CFG.lang.get("Nuke"));
        this.updatedButtonsWidth(CFG.PADD, CFG.BUTTON_W + CFG.BUTTON_W / 2);
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if ((CFG.fMOVE_MENU_PERCENTAGE += (float)(System.currentTimeMillis() - CFG.lMOVE_MENU_TIME) / 300.0f * 95.0f) > 100.0f) {
            CFG.fMOVE_MENU_PERCENTAGE = 100.0f;
        } else {
            CFG.setRenderO(true);
        }
        CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
        if (this.getMenuElem(this.getMenuElemsSize() - 1).getVisibleE()) {
            IMGManager.getIMG(Images.bgGameAction).draw2O(oSB, this.getPosX() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.bgGameAction).getHeight() + -1 + iTranslateY, this.getMenuElem(this.getMenuElemsSize() - 1).getPosXE() + this.getMenuElem(this.getMenuElemsSize() - 1).getWidthE() + CFG.PADD + 1, this.getHeightM() + 1, true, false);
        } else if (this.getMenuElem(this.getMenuElemsSize() - 2).getVisibleE()) {
            IMGManager.getIMG(Images.bgGameAction).draw2O(oSB, this.getPosX() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.bgGameAction).getHeight() + -1 + iTranslateY, this.getMenuElem(this.getMenuElemsSize() - 2).getPosXE() + this.getMenuElem(this.getMenuElemsSize() - 2).getWidthE() + CFG.PADD + 1, this.getHeightM() + 1, true, false);
        } else {
            IMGManager.getIMG(Images.bgGameAction).draw2O(oSB, this.getPosX() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.bgGameAction).getHeight() + -1 + iTranslateY, this.getMenuElem(this.getMenuElemsSize() - 3).getPosXE() + this.getMenuElem(this.getMenuElemsSize() - 3).getWidthE() + CFG.PADD + 1, this.getHeightM() + 1, true, false);
        }
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        Menu_InGameProvAction.draw4(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, sliderMenuIsActive);
    }

    @Override
    public void beginClipM(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
    }

    public static void buildForeign() {
        if (CFG.core.getActiveProvID() >= 0 && CFG.core.getProv(CFG.core.getActiveProvID()).getCivId() > 0) {
            Menu_InGame_BuildForeign.buildBuildList();
            CFG.menus.rebuildInGame_BuildForeign(CFG.core.getProv(CFG.core.getActiveProvID()).getCivId(), CFG.core.getActiveProvID());
        } else {
            Menu_InGame_BuildForeign.buildBuildList();
            CFG.toastM.addM(CFG.lang.get("ChooseAProvince"), CFG.COLOR_NEGATIVE_1);
        }
    }

    public static void investForeign() {
        if (CFG.core.getActiveProvID() >= 0 && CFG.core.getProv(CFG.core.getActiveProvID()).getCivId() > 0) {
            CFG.menus.rebuildInGame_InvestForeign(CFG.core.getProv(CFG.core.getActiveProvID()).getCivId(), CFG.core.getActiveProvID());
        } else {
            CFG.toastM.addM(CFG.lang.get("ChooseAProvince"), CFG.COLOR_NEGATIVE_1);
        }
    }

    public static void useNuke() {
        if (CFG.ENABLE_NUKES && (GameValues.gvAtomic.PROVINCE_ACTION_NUKE_VISIBLE_ALL_THE_TIME || CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).civGD.iNukes > 0 || NukeManager.canBuildNuke_TechLvl(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()))) {
            try {
                if (CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).civGD.iNukes <= 0) {
                    CFG.toastM.addM(CFG.lang.get("AtomicBombs") + ": " + CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).civGD.iNukes, CFG.COLOR_NEGATIVE_1);
                    CFG.toastM.setTimeInView(3500);
                } else if (!CFG.core.getCivsAtWar(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.core.getProv(CFG.core.getActiveProvID()).getCivId())) {
                    CFG.toastM.addM(CFG.lang.get("DeclareWar") + "!", CFG.COLOR_NEGATIVE_1);
                    CFG.toastM.setTimeInView(3500);
                } else {
                    int out = NukeManager.dropNuke(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.core.getActiveProvID());
                    if (out > 0) {
                        CFG.menus.setVisible_Menu_InGame_CurrentWars(true);
                        CFG.menus.rebuildMenu_InGame_Infobox(CFG.lang.get("AtomicBombing") + ": " + CFG.core.getProv(CFG.core.getActiveProvID()).getProvName(), CFG.lang.get("Casualties") + ": " + CFG.getNumberWthSpaces("" + out), Images.infoNuke);
                        CFG.menus.updateInGame_ProvinceInfoGraph(CFG.core.getActiveProvID());
                    }
                }
            }
            catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
    }

    @Override
    public void setVisibleM(boolean visible) {
        if (visible && this.getVisibleM() != visible) {
            CFG.fMOVE_MENU_PERCENTAGE = 5.0f;
            CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
        }
        super.setVisibleM(visible);
    }

    @Override
    public void onHovered() {
        CFG.menus.setOrderOfMenu_InGame();
    }
}
