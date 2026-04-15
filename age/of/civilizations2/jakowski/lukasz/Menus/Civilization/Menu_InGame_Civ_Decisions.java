package age.of.civilizations2.jakowski.lukasz.Menus.Civilization;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.Button.Diplomacy.Action.Button_DiplomacyAction;
import age.of.civilizations2.jakowski.lukasz.Button.Diplomacy.Action.Button_DiplomacyAction_TextRight;
import age.of.civilizations2.jakowski.lukasz.Button.Diplomacy.Action.Button_Diplomacy_Action_Government;
import age.of.civilizations2.jakowski.lukasz.Button.Diplomacy.Action.Button_Diplomacy_Action_Religion;
import age.of.civilizations2.jakowski.lukasz.Button.Diplomacy.Action.Button_Diplomacy_Action_Tech;
import age.of.civilizations2.jakowski.lukasz.Button.Diplomacy.Button_Diplomacy_Civilize;
import age.of.civilizations2.jakowski.lukasz.Button.Diplomacy.Button_Diplomacy_FormCivilization;
import age.of.civilizations2.jakowski.lukasz.Button.GameN.ButtonN_ActionAll;
import age.of.civilizations2.jakowski.lukasz.Button.MenuElemUI;
import age.of.civilizations2.jakowski.lukasz.Button2.ButtonFlagBig;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.City;
import age.of.civilizations2.jakowski.lukasz.Colors;
import age.of.civilizations2.jakowski.lukasz.Core.Core;
import age.of.civilizations2.jakowski.lukasz.CreateVassal_Data;
import age.of.civilizations2.jakowski.lukasz.Diplomacy.Loans;
import age.of.civilizations2.jakowski.lukasz.GameAction;
import age.of.civilizations2.jakowski.lukasz.GameCalendar;
import age.of.civilizations2.jakowski.lukasz.GameValues.GameValues;
import age.of.civilizations2.jakowski.lukasz.IMGManager;
import age.of.civilizations2.jakowski.lukasz.IdeologiesManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MapA.BuildingsManager;
import age.of.civilizations2.jakowski.lukasz.MapA.Challenge.ChallengesManager;
import age.of.civilizations2.jakowski.lukasz.MapA.Mode.MapModesManager;
import age.of.civilizations2.jakowski.lukasz.MapA.Plagues.Nuke.NukeManager;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.MEHover_2E;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Flag_Big;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Ideology_Big;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Ideology_Vassal_Big;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Image_Big;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Image_Big2;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Religion_Big;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Text;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_TextDesc;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Text_Big;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.Menus.Civilization.Menu_InGame_Civ;
import age.of.civilizations2.jakowski.lukasz.Menus.Civilization.Menu_InGame_Civ_DiplomacyORActions;
import age.of.civilizations2.jakowski.lukasz.Menus.Continents.Menu_InGame_LeaderC;
import age.of.civilizations2.jakowski.lukasz.Menus.Formable.AddCiv.Menu_InGame_AddCiv;
import age.of.civilizations2.jakowski.lukasz.Menus.Menu_InitGame;
import age.of.civilizations2.jakowski.lukasz.Menus.Province.Menu_InGame_RelocatePopulation;
import age.of.civilizations2.jakowski.lukasz.Menus.Send.Army.Menu_InGame_SendArmy;
import age.of.civilizations2.jakowski.lukasz.Menus.ZRest.Menu_InGame_CivilizationView;
import age.of.civilizations2.jakowski.lukasz.RenderProvince;
import age.of.civilizations2.jakowski.lukasz.Renderer;
import age.of.civilizations2.jakowski.lukasz.SFXManager;
import age.of.civilizations2.jakowski.lukasz.Title.TitleM_TextSmall;
import age.of.civilizations2.jakowski.lukasz.Touch;
import age.of.civilizations2.jakowski.lukasz.View;
import age.of.civilizations2.jakowski.lukasz.Z_Other.DialogType;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_Civ_Decisions
extends Menu {
    public static boolean toTheBottom = false;
    public static int LAST_ELEMENT_POSY = 0;
    public static int extraPosX = 0;

    public static int getButtonH() {
        return Math.max(CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 4, CFG.BUTTON_H * 7 / 10);
    }

    public Menu_InGame_Civ_Decisions() {
        int i;
        int menuW;
        ArrayList<MenuElemUI> menuElems = new ArrayList<MenuElemUI>();
        int tempW = menuW = Menu_InGame_Civ.getMenuCivInfoWidth();
        int tempElemH = Menu_InGame_Civ_Decisions.getButtonH();
        int tY = 0;
        if (CFG.SPECTATOR_MODE) {
            menuElems.add(new Button_DiplomacyAction(Images.diploAZ, CFG.lang.get("LockCivilization") + ": " + (CFG.SPECTATOR_MODE_LOCK_CIV ? CFG.lang.get("On") : CFG.lang.get("Off")), 0, 0, tY, menuW - 2, tempElemH, true){

                @Override
                public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    if (this.menuElemHover != null) {
                        this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                    }
                }

                @Override
                public void buildElemHover() {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("LockCivilization"), CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(Images.diploAZ, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Space());
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_TextDesc(CFG.lang.get("LockCivilizationDesc")));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }

                @Override
                public void actionElem(int iID) {
                    CFG.SPECTATOR_MODE_LOCK_CIV = !CFG.SPECTATOR_MODE_LOCK_CIV;
                    this.setTextE(CFG.lang.get("LockCivilization") + ": " + (CFG.SPECTATOR_MODE_LOCK_CIV ? CFG.lang.get("On") : CFG.lang.get("Off")));
                }
            });
            menuElems.add(new Button_DiplomacyAction(Images.diploWar, CFG.lang.get("DeclareWar"), 0, 0, tY += tempElemH, menuW - 2, tempElemH, true){

                @Override
                public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    if (this.menuElemHover != null) {
                        this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                    }
                }

                @Override
                public void buildElemHover() {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("DeclareWar"), CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(Images.diploWar, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }

                @Override
                public void actionElem(int iID) {
                    CFG.SPECTATOR_MODE_DECLARE_WAR_MODE = 0;
                    CFG.menus.rebuildMenu_InGame_Infobox(CFG.lang.get("DeclareWar"), CFG.lang.get("ChooseAProvince"), Images.infoDiplomacy);
                }

                @Override
                public int getSFXElem() {
                    return this.getIsClickable() ? SFXManager.SFX_WAR : super.getSFXElem();
                }
            });
            tY += tempElemH;
        }
        if (CFG.SPECTATOR_MODE || CFG.SANDBOX_MODE) {
            menuElems.add(new Button_DiplomacyAction(Images.topDiplomacyPoints, CFG.lang.get("ManageDiplomacy"), 0, 0, tY, menuW - 2, tempElemH, true){

                @Override
                public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    if (this.menuElemHover != null) {
                        this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                    }
                }

                @Override
                public void buildElemHover() {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("ManageDiplomacy"), CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(Images.topDiplomacyPoints, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }

                @Override
                public void actionElem(int iID) {
                    CFG.core.setActiveProvID(-1);
                    CFG.menus.rebuildManageDiplomacy_Alliances();
                    CFG.core.disableDrawCivlizationsRegions_Players();
                    CFG.chosenAlphabetCharachter = null;
                    CFG.resetManageDiplomacyIDs();
                    CFG.backToMenu = View.eINGAME;
                    CFG.menus.setMenuID(View.eMANAGE_DIPLOMACY);
                    RenderProvince.updateDrawProvinces();
                    CFG.map.getTouchMgr().ueExA();
                }
            });
            tY += tempElemH;
        }
        if (CFG.SPECTATOR_MODE) {
            menuElems.add(new Button_DiplomacyAction(Images.topDiplomacyPoints, CFG.lang.get("Diplomacy") + ": " + CFG.lang.get("Actions"), 0, 0, tY, menuW - 2, tempElemH, true){

                @Override
                public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    if (this.menuElemHover != null) {
                        this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                    }
                }

                @Override
                public void buildElemHover() {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("Diplomacy") + ": " + CFG.lang.get("Actions"), CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(Images.topDiplomacyPoints, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }

                @Override
                public void actionElem(int iID) {
                    CFG.SPECTATOR_MODE_DIPLOMACY_ACTIONS_MODE = true;
                    CFG.menus.rebuildMenu_InGame_Infobox(CFG.lang.get("Diplomacy") + ": " + CFG.lang.get("Actions"), CFG.lang.get("ChooseAProvince"), Images.infoDiplomacy);
                }
            });
            tY += tempElemH;
        }
        if (GameValues.gvInGame.SHOW_ALL_ACTIONS_IN_CIV_DECISIONS) {
            menuElems.add(new ButtonN_ActionAll(Colors.COLOR_TEXT_MODIFIER_POSITIVE, CFG.lang.get("Assimilate") + ": " + CFG.lang.get("AllProvinces"), CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.lang.get("Provinces") + ": ", CFG.getNumberWthSpaces("" + CFG.core.assimilateAllProvinces_Number(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())), CFG.getNumberWthSpaces("" + CFG.core.assimilateAllProvinces_Cost(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())), CFG.getPrecision2(CFG.core.assimilateAllProvinces_CostDiplomacy(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()), 10), Images.topDiplomacyPoints, CFG.COLOR_DIPLOMACY_POINTS, Images.diploStability, CFG.getColorStep(CFG.COLOR_PROVINCE_STABILITY_MIN, CFG.COLOR_PROVINCE_STABILITY_MAX, (int)(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getStabilityCiv() * 100.0f), 100, 1.0f), 0, tY, tempW - 2, tempElemH){

                @Override
                public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    if (this.menuElemHover != null) {
                        this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                    }
                }

                @Override
                public void actionElem(int iID) {
                    CFG.setDialogType(DialogType.ALL_ASSIMILATE);
                }

                @Override
                public void actionElemPPM() {
                    CFG.assimilateAll();
                }

                @Override
                public void buildElemHover() {
                    try {
                        ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                        ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                        nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), 0, CFG.PADD));
                        nData.add(new ME_Hover_2Type_Text_Big(this.getTextE(), Colors.COLOR_TEXT_GOLD));
                        nData.add(new ME_Hover_2Type_Image_Big(this.iImageID, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Space());
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(this.sProvincesText));
                        nData.add(new ME_Hover_2Type_Text(this.sProvinceNumText, CFG.COLOR_HOVER_TITLE));
                        nData.add(new ME_Hover_2Type_Image(Images.provinces, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Space());
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Cost") + ": "));
                        nData.add(new ME_Hover_2Type_Text(this.sCostText, CFG.COLOR_GOLD));
                        nData.add(new ME_Hover_2Type_Image(Images.topGold(), CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        if (this.imgCost2 == Images.topMovementPoints) {
                            nData.add(new ME_Hover_2Type_Text(CFG.lang.get("MovementPoints") + ": "));
                        } else if (this.imgCost2 == Images.topDiplomacyPoints) {
                            nData.add(new ME_Hover_2Type_Text(CFG.lang.get("DiplomacyPoints") + ": "));
                        }
                        nData.add(new ME_Hover_2Type_Text(this.sCostText2, this.costColor));
                        nData.add(new ME_Hover_2Type_Image(this.imgCost2, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Happiness") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + CFG.getPrecision2(GameValues.gvAssimilate.ASSIMILATE_HAPPINESS_CHANGE_PER_TURN * 100.0f, 100) + " " + CFG.lang.get("PerTurn"), CFG.COLOR_NEGATIVE_2));
                        nData.add(new ME_Hover_2Type_Image(Images.happiness, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        this.menuElemHover = new ME_Hover_v2(nElements);
                    }
                    catch (Exception e) {
                        this.menuElemHover = null;
                    }
                }
            });
            menuElems.add(new ButtonN_ActionAll(Colors.COLOR_TEXT_MODIFIER_POSITIVE, CFG.lang.get("Invest") + ": " + CFG.lang.get("AllProvinces"), CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.lang.get("Provinces") + ": ", CFG.getNumberWthSpaces("" + CFG.core.investEconomyAllProvinces_Number(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())), CFG.getNumber_SHORT(CFG.core.investEconomyAllProvinces_Cost(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())), CFG.getPrecision2(CFG.core.investEconomyAllProvinces_CostMovement(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()), 10), Images.topMovementPoints, CFG.COLOR_MOVEMENT, Images.investEco, CFG.COLOR_ECONOMY, 0, tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE(), tempW, tempElemH){

                @Override
                public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    if (this.menuElemHover != null) {
                        this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                    }
                }

                @Override
                public void actionElem(int iID) {
                    CFG.setDialogType(DialogType.ALL_INVEST_ECO);
                }

                @Override
                public void actionElemPPM() {
                    CFG.investAllEconomy();
                }
            });
            menuElems.add(new ButtonN_ActionAll(Colors.COLOR_TEXT_MODIFIER_POSITIVE, CFG.lang.get("Invest") + ": " + CFG.lang.get("AllProvinces"), CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.lang.get("Provinces") + ": ", CFG.getNumberWthSpaces("" + CFG.core.investDevAllProvinces_Number(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())), CFG.getNumber_SHORT(CFG.core.investDevAllProvinces_Cost(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())), CFG.getPrecision2(CFG.core.investDevAllProvinces_CostMovement(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()), 10), Images.topMovementPoints, CFG.COLOR_MOVEMENT, Images.investDev, CFG.COLOR_DEVELOPMENT, 0, tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE(), tempW, tempElemH){

                @Override
                public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    if (this.menuElemHover != null) {
                        this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                    }
                }

                @Override
                public void actionElem(int iID) {
                    CFG.setDialogType(DialogType.ALL_INVEST_DEV);
                }

                @Override
                public void actionElemPPM() {
                    CFG.investAllDevelopment();
                }
            });
            menuElems.add(new ButtonN_ActionAll(Colors.COLOR_TEXT_MODIFIER_POSITIVE, CFG.lang.get("Festival") + ": " + CFG.lang.get("AllProvinces"), CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.lang.get("Provinces") + ": ", CFG.getNumberWthSpaces("" + CFG.core.festivalAllProvinces_Number(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())), CFG.getNumber_SHORT(CFG.core.festivalAllProvinces_Cost(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())), CFG.getPrecision2(CFG.core.festivalAllProvinces_CostMovement(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()), 10), Images.topMovementPoints, CFG.COLOR_MOVEMENT, Images.diploFestival, CFG.COLOR_TEXT_HAPPINESS_ACTIVE, 0, tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE(), tempW, tempElemH){

                @Override
                public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    if (this.menuElemHover != null) {
                        this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                    }
                }

                @Override
                public void actionElem(int iID) {
                    CFG.setDialogType(DialogType.ALL_INVEST_FESTIVAL);
                }

                @Override
                public void actionElemPPM() {
                    CFG.festivalAll();
                }
            });
            tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE();
        }
        try {
            if (Menu_InitGame.TDSX) {
                menuElems.add(new Button_DiplomacyAction_TextRight(Images.diploMessage, "Classified Missions", 0, 0, tY, menuW - 2, tempElemH, true, "" + CFG.getNumberWthSpaces("7"), Images.key){

                    @Override
                    public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                        if (this.menuElemHover != null) {
                            this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                        }
                    }

                    @Override
                    public void actionElem(int iID) {
                        CFG.menus.rebuildInGame_MissionsReal();
                    }

                    @Override
                    public void actionElemPPM() {
                        CFG.menus.rebuildInGame_MissionsReal();
                    }

                    @Override
                    public void buildElemHover() {
                        ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                        ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                        nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), 0, CFG.PADD));
                        nData.add(new ME_Hover_2Type_Text_Big("Classified Missions: "));
                        nData.add(new ME_Hover_2Type_Text_Big("" + CFG.getNumberWthSpaces("7"), CFG.COLOR_HOVER_TITLE));
                        nData.add(new ME_Hover_2Type_Image_Big(Images.key, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        this.menuElemHover = new ME_Hover_v2(nElements);
                    }
                });
                tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE();
            }
            menuElems.add(new Button_DiplomacyAction_TextRight(Images.diploMessage, CFG.lang.get("Missions"), 0, 0, tY, menuW - 2, tempElemH, true, "" + CFG.getNumberWthSpaces("" + CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).iDMAS.size()), Images.diploMessage){

                @Override
                public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    if (this.menuElemHover != null) {
                        this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                    }
                }

                @Override
                public void actionElem(int iID) {
                    CFG.menus.rebuildInGame_Missions();
                }

                @Override
                public void actionElemPPM() {
                    CFG.menus.rebuildInGame_MissionsReal();
                }

                @Override
                public void buildElemHover() {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), 0, CFG.PADD));
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("Missions") + ": "));
                    nData.add(new ME_Hover_2Type_Text_Big("" + CFG.getNumberWthSpaces("" + CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).iDMAS.size()), CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(Images.diploMessage, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
            });
            tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE();
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
        if (!CFG.SPECTATOR_MODE && CFG.core.getPlayer((int)0).playerGD.challengeID >= 0 && CFG.core.getPlayersSize() == 1 && CFG.core.getPlayer((int)0).playerGD.challengeID < ChallengesManager.challengeList.size()) {
            try {
                menuElems.add(new Button_DiplomacyAction(Images.victoryPoints, CFG.lang.get("Challenge") + ": " + CFG.lang.getCiv(ChallengesManager.challengeList.get((int)CFG.core.getPlayer((int)0).playerGD.challengeID).FORM_TAG), 0, 0, tY, menuW - 2, tempElemH, true){

                    @Override
                    public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                        if (this.menuElemHover != null) {
                            this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                        }
                    }

                    @Override
                    public void actionElem(int iID) {
                        try {
                            if (!CFG.SPECTATOR_MODE && CFG.core.getPlayer((int)0).playerGD.challengeID >= 0 && CFG.core.getPlayersSize() == 1 && CFG.core.getPlayer((int)0).playerGD.challengeID < ChallengesManager.challengeList.size() && CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getTagsCanFormCSize() > 0) {
                                int i;
                                int formID = -1;
                                for (i = 0; i < CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getTagsCanFormCSize(); ++i) {
                                    if (!CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getTagsCanFormC(i).equals(ChallengesManager.challengeList.get((int)CFG.core.getPlayer((int)0).playerGD.challengeID).FORM_TAG)) continue;
                                    formID = i;
                                    break;
                                }
                                if (formID < 0) {
                                    for (i = 0; i < CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getTagsCanFormCSize(); ++i) {
                                        if (!CFG.ideologiesMgr.getRealTag(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getTagsCanFormC(i)).equals(ChallengesManager.challengeList.get((int)CFG.core.getPlayer((int)0).playerGD.challengeID).FORM_TAG)) continue;
                                        formID = i;
                                        break;
                                    }
                                }
                                if (formID >= 0) {
                                    try {
                                        CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).iACTIVE_VIEW_MODE = CFG.mapModesManager.getActiveMapModeID();
                                        CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).iBefore_ActiveProvince = CFG.core.getActiveProvID();
                                        CFG.mapModesManager.disableAllViews();
                                        CFG.core.resetChooseProvinceData();
                                        CFG.core.resetRegroupArmy_Data();
                                        CFG.core.setActiveProvID(-1);
                                        CFG.core.resetChooseProvinceData_Immediately();
                                        CFG.gameAction.hideAllProvinceActionViews();
                                        CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId();
                                        CFG.loadFormableCiv_GameData(CFG.core.getCiv(CFG.getActiveCivInfoId()).getTagsCanFormC(formID));
                                        CFG.menus.setMenuID(View.eINGAME_FORMABLE_CIV_PROVINCES);
                                        CFG.map.getMpB().updateWorldMap_Shaders();
                                    }
                                    catch (Exception ex) {
                                        CFG.exceptionStack(ex);
                                    }
                                } else {
                                    CFG.toastM.addM(CFG.lang.get("Civilization") + ": " + CFG.lang.get("NotFound") + " -> " + CFG.lang.getCiv(ChallengesManager.challengeList.get((int)CFG.core.getPlayer((int)0).playerGD.challengeID).FORM_TAG));
                                }
                            }
                        }
                        catch (Exception exr) {
                            CFG.exceptionStack(exr);
                        }
                    }

                    @Override
                    public void buildElemHover() {
                        ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                        ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                        try {
                            int id = CFG.core.getPlayer((int)0).playerGD.challengeID;
                            nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Challenge") + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                            nData.add(new ME_Hover_2Type_Text("#" + ChallengesManager.challengeList.get((int)id).ID, CFG.COLOR_NEUTRAL));
                            nElements.add(new MEHover_2E(nData));
                            nData.clear();
                            nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("PlayAs") + ": "));
                            nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.getCiv(ChallengesManager.challengeList.get((int)id).PLAY_AS), CFG.COLOR_HOVER_TITLE));
                            nData.add(new ME_Hover_2Type_Image_Big2(Images.iconTrue, CFG.PADD, 0));
                            nElements.add(new MEHover_2E(nData));
                            nData.clear();
                            nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("FormCivilization") + ": "));
                            nData.add(new ME_Hover_2Type_Text_Big(this.getTextE(), CFG.COLOR_HOVER_TITLE));
                            nData.add(new ME_Hover_2Type_Image_Big2(Images.victoryPoints, CFG.PADD, 0));
                            if (ChallengesManager.challengeList.get((int)id).PROVINCES > 0 && ChallengesManager.challengeList.get((int)id).PROVINCES_FORM > 0) {
                                nData.add(new ME_Hover_2Type_Text_Big(" " + CFG.getNumberWthSpaces("" + ChallengesManager.challengeList.get((int)id).PROVINCES_FORM), CFG.COLOR_NEUTRAL));
                                nData.add(new ME_Hover_2Type_Image_Big2(Images.provinces, CFG.PADD, 0));
                            }
                            nElements.add(new MEHover_2E(nData));
                            nData.clear();
                            if (ChallengesManager.challengeList.get((int)id).DESC != null && ChallengesManager.challengeList.get((int)id).DESC.length() > 0) {
                                nData.add(new ME_Hover_2Type_Space());
                                nElements.add(new MEHover_2E(nData));
                                nData.clear();
                                nData.add(new ME_Hover_2Type_TextDesc(CFG.lang.get(ChallengesManager.challengeList.get((int)id).DESC), CFG.COLOR_NEUTRAL, CFG.FONT_REGULAR_SMALL));
                                nElements.add(new MEHover_2E(nData));
                                nData.clear();
                            }
                        }
                        catch (Exception exception) {
                            // empty catch block
                        }
                        this.menuElemHover = new ME_Hover_v2(nElements);
                    }
                });
                tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE();
            }
            catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
        if (GameValues.gvInGame.CIV_DECISIONS_SHOW_ALL_FORMABLE_CIVS && CFG.core.getCiv(CFG.getActiveCivInfoId()).getTagsCanFormCSize() > 0) {
            for (int i2 = 0; i2 < CFG.core.getCiv(CFG.getActiveCivInfoId()).getTagsCanFormCSize(); ++i2) {
                menuElems.add(new Button_Diplomacy_FormCivilization(CFG.core.getCiv(CFG.getActiveCivInfoId()).getTagsCanFormC(i2), 0, tY, tempW - 2, true, CFG.canFormACiv(CFG.getActiveCivInfoId(), CFG.core.getCiv(CFG.getActiveCivInfoId()).getTagsCanFormC(i2), true), i2, 0){

                    @Override
                    public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                        if (this.menuElemHover != null) {
                            this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                        }
                    }

                    @Override
                    public void actionElem(int iID) {
                        try {
                            CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).iACTIVE_VIEW_MODE = CFG.mapModesManager.getActiveMapModeID();
                            CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).iBefore_ActiveProvince = CFG.core.getActiveProvID();
                            CFG.mapModesManager.disableAllViews();
                            CFG.core.resetChooseProvinceData();
                            CFG.core.resetRegroupArmy_Data();
                            CFG.core.setActiveProvID(-1);
                            CFG.core.resetChooseProvinceData_Immediately();
                            CFG.gameAction.hideAllProvinceActionViews();
                            CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId();
                            CFG.loadFormableCiv_GameData(CFG.core.getCiv(CFG.getActiveCivInfoId()).getTagsCanFormC(this.id));
                            CFG.menus.setMenuID(View.eINGAME_FORMABLE_CIV_PROVINCES);
                            CFG.map.getMpB().updateWorldMap_Shaders();
                        }
                        catch (Exception ex) {
                            CFG.exceptionStack(ex);
                        }
                    }
                });
                tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE();
            }
        } else {
            menuElems.add(new Button_DiplomacyAction_TextRight(Images.administration, CFG.lang.get("FormCivilization"), 0, 0, tY, menuW - 2, tempElemH, true, "" + CFG.core.getCiv(CFG.getActiveCivInfoId()).getTagsCanFormCSize(), Images.diploAZ){

                @Override
                public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    if (this.menuElemHover != null) {
                        this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                    }
                }

                @Override
                public void actionElem(int iID) {
                    CFG.menus.rebuildInGame_FormCivList();
                }

                @Override
                public void buildElemHover() {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    int iCivID = CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId();
                    nData.add(new ME_Hover_2Type_Flag_Big(iCivID, 0, CFG.PADD));
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("FormableCivilizations") + ": ", CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Text_Big("" + CFG.core.getCiv(iCivID).getTagsCanFormCSize()));
                    nData.add(new ME_Hover_2Type_Image_Big(Images.diploAZ, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    if (CFG.core.getCiv(CFG.getActiveCivInfoId()).getTagsCanFormCSize() > 0) {
                        nData.add(new ME_Hover_2Type_Space());
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        for (int i = 0; i < CFG.core.getCiv(CFG.getActiveCivInfoId()).getTagsCanFormCSize(); ++i) {
                            nData.add(new ME_Hover_2Type_Text(CFG.lang.getCiv(CFG.core.getCiv(CFG.getActiveCivInfoId()).getTagsCanFormC(i))));
                            nData.add(new ME_Hover_2Type_Image(CFG.canFormACiv(CFG.getActiveCivInfoId(), CFG.core.getCiv(CFG.getActiveCivInfoId()).getTagsCanFormC(i), true) ? Images.iconTrue : Images.iconFalse, CFG.PADD, 0));
                            nElements.add(new MEHover_2E(nData));
                            nData.clear();
                        }
                    }
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
            });
            tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE();
        }
        if (CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).getIdeology()).CAN_BECOME_CIVILIZED >= 0) {
            menuElems.add(new Button_Diplomacy_Civilize(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), 0, tY, tempW - 2, tempElemH, true, CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getTechLevel() >= CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).getIdeology()).CIVILIZE_TECH_LEVEL){

                @Override
                public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    if (this.menuElemHover != null) {
                        this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                    }
                }

                @Override
                public void actionElem(int iID) {
                    CFG.menus.rebuildInGame_Civilize(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId());
                    CFG.mapModesManager.setActiveMapModeID(MapModesManager.VIEW_IDEOLOGIES_MODE);
                }
            });
            ((MenuElemUI)menuElems.get(menuElems.size() - 1)).setMax((menuElems.size() + 1) % 2);
            tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE();
        }
        menuElems.add(new Button_Diplomacy_Action_Tech(Images.technology, CFG.lang.get("Technology"), 0, 0, tY, menuW - 2, tempElemH, true){

            @Override
            public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElemHover != null) {
                    this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                }
            }

            @Override
            public void actionElem(int iID) {
                if (CFG.menus.getVisibleInGame_Technology()) {
                    CFG.menus.setVisibleInGame_Technology(false);
                } else {
                    CFG.menus.rebuildInGame_Technology(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId());
                }
            }

            @Override
            public void buildElemHover() {
                ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                int pointsLeft = CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).civGD.techPoints.getPointsLeft(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId());
                int iCivID = CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId();
                nData.add(new ME_Hover_2Type_Flag_Big(iCivID, 0, CFG.PADD));
                nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("PointsLeft") + ": "));
                nData.add(new ME_Hover_2Type_Text_Big("" + pointsLeft, CFG.COLOR_HOVER_TITLE));
                nData.add(new ME_Hover_2Type_Text_Big("/" + (int)(CFG.core.getCiv(iCivID).getTechLevel() * 100.0f), CFG.COLOR_HOVER_TITLE));
                nData.add(new ME_Hover_2Type_Image_Big(Images.technology, CFG.PADD, 0));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Space());
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Technology") + ": "));
                nData.add(new ME_Hover_2Type_Text("" + CFG.core.getCiv(iCivID).getTechLevel(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nData.add(new ME_Hover_2Type_Text("/" + GameValues.gvTechnology.MAX_TECHNOLOGY_LEVEL, CFG.COLOR_HOVER_TITLE));
                nData.add(new ME_Hover_2Type_Image(Images.technology, CFG.PADD, 0));
                nData.add(new ME_Hover_2Type_Flag(iCivID, CFG.PADD, 0));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Space());
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Attack") + ": "));
                nData.add(new ME_Hover_2Type_Text("+" + CFG.getPrecision2(CFG.gameAction.getAttackersBonusFromTechnology(iCivID), 100) + "%", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nData.add(new ME_Hover_2Type_Image(Images.attack, CFG.PADD, 0));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Defense") + ": "));
                nData.add(new ME_Hover_2Type_Text("+" + CFG.getPrecision2(CFG.gameAction.getDefenseBonusFromTechnology(iCivID), 100) + "%", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nData.add(new ME_Hover_2Type_Image(Images.defense, CFG.PADD, 0));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Space());
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_TextDesc(CFG.lang.get("AttackDefenseDesc")));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                this.menuElemHover = new ME_Hover_v2(nElements);
            }
        });
        tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE();
        try {
            menuElems.add(new Button_DiplomacyAction(Images.gov, CFG.lang.get(GameValues.gvAdministrationPolicy.POLICY_NAME[CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).civGD.policyID]), 0, 0, tY, menuW - 2, tempElemH, true){

                @Override
                public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    if (this.menuElemHover != null) {
                        this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                    }
                }

                @Override
                public void actionElem(int iID) {
                    CFG.menus.rebuildInGame_AdministrationPolicy();
                }

                @Override
                public void buildElemHover() {
                    this.menuElemHover = CFG.ideologiesMgr.getHover_AdministrationPolicy(CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).civGD.policyID, CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId());
                }
            });
            tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE();
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
        if (CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getAlliance() > 0) {
            menuElems.add(new Button_DiplomacyAction(Images.diploAlliance, CFG.lang.get("LeaveAlliance"), 0, 0, tY, menuW - 2, tempElemH, true){

                @Override
                public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    if (this.menuElemHover != null) {
                        this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                    }
                }

                @Override
                public void actionElem(int iID) {
                    CFG.menus.rebuildInGame_LeaveAlliance(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId());
                }

                @Override
                public void buildElemHover() {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("LeaveAlliance"), CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(Images.diploAlliance, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
            });
            tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE();
        }
        menuElems.add(new Button_DiplomacyAction_TextRight(Images.diploVassal, CFG.lang.get("Vassals") + ": " + CFG.lang.get("Tribute"), 0, 0, tY, menuW - 2, tempElemH, true, "" + CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).civGD.iVassalsSize, Images.diploVassal){

            @Override
            public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElemHover != null) {
                    this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                }
            }

            @Override
            public void actionElem(int iID) {
                if (CFG.menus.getVisibleInGame_Tribute()) {
                    CFG.menus.setVisibleInGame_Tribute(false);
                } else {
                    CFG.menus.rebuildInGame_Tribute();
                }
            }

            @Override
            public void buildElemHover() {
                int i;
                ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("Vassals") + ": " + CFG.lang.get("Tribute"), CFG.COLOR_HOVER_TITLE));
                nData.add(new ME_Hover_2Type_Ideology_Vassal_Big(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getIdeology(), CFG.PADD, 0));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Space());
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_TextDesc(CFG.lang.get("VassalsLibertyDesireDesc")));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Space());
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                for (i = CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId() - 1; i > 0; --i) {
                    if (CFG.core.getCiv(i).getNumOfProvs() <= 0 || CFG.core.getCiv(i).getPuppetOfCiv() != CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) continue;
                    nData.add(new ME_Hover_2Type_Flag(i));
                    nData.add(new ME_Hover_2Type_Text("" + CFG.core.getCiv(i).getCivName() + ": "));
                    nData.add(new ME_Hover_2Type_Text("" + (int)CFG.gameUpdate.getIncomeVassals(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), i), CFG.COLOR_POSITIVE));
                    nData.add(new ME_Hover_2Type_Image(Images.topGold(), CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                }
                for (i = CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId() + 1; i < CFG.core.getCivsSize(); ++i) {
                    if (CFG.core.getCiv(i).getNumOfProvs() <= 0 || CFG.core.getCiv(i).getPuppetOfCiv() != CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) continue;
                    nData.add(new ME_Hover_2Type_Flag(i));
                    nData.add(new ME_Hover_2Type_Text("" + CFG.core.getCiv(i).getCivName() + ": "));
                    nData.add(new ME_Hover_2Type_Text("" + (int)CFG.gameUpdate.getIncomeVassals(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), i), CFG.COLOR_POSITIVE));
                    nData.add(new ME_Hover_2Type_Image(Images.topGold(), CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                }
                if (nElements.size() <= 1) {
                    nElements.clear();
                    nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()));
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("NoVassals"), CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Ideology_Vassal_Big(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getIdeology(), CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                }
                this.menuElemHover = new ME_Hover_v2(nElements);
            }
        });
        tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE();
        if (CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getPuppetOfCiv() != CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) {
            menuElems.add(new Button_DiplomacyAction(Images.diploVassal, CFG.lang.get("DeclarationOfIndependence"), 0, 0, tY, menuW - 2, tempElemH, CFG.core.getCivTruce(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getPuppetOfCiv()) == 0){

                @Override
                public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    if (this.menuElemHover != null) {
                        this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                    }
                }

                @Override
                public void actionElem(int iID) {
                    CFG.menus.rebuildInGame_DeclarationOfIndependence(CFG.getActiveCivInfoId());
                }

                @Override
                public void buildElemHover() {
                    try {
                        ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                        ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                        if (CFG.core.getCivTruce(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getPuppetOfCiv()) > 0) {
                            nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("WeHaveATruceUntil") + ": ", CFG.COLOR_HOVER_TITLE));
                            nData.add(new ME_Hover_2Type_Text_Big(GameCalendar.getDate_ByTurnID(GameCalendar.TURNID + CFG.core.getCivTruce(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getPuppetOfCiv()))));
                            nData.add(new ME_Hover_2Type_Text_Big(" [" + CFG.lang.get("TurnsX", CFG.core.getCivTruce(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getPuppetOfCiv())) + "]", CFG.COLOR_TEXT_GRAY_NS_HOVER));
                            nData.add(new ME_Hover_2Type_Image_Big(Images.diploTruce, CFG.PADD, 0));
                            nElements.add(new MEHover_2E(nData));
                            nData.clear();
                            this.menuElemHover = new ME_Hover_v2(nElements);
                        } else {
                            nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("DeclarationOfIndependence"), CFG.COLOR_HOVER_TITLE));
                            nData.add(new ME_Hover_2Type_Image_Big(Images.diploVassal, CFG.PADD, 0));
                            nElements.add(new MEHover_2E(nData));
                            nData.clear();
                            this.menuElemHover = new ME_Hover_v2(nElements);
                        }
                    }
                    catch (Exception ex) {
                        this.menuElemHover = null;
                    }
                }
            });
            tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE();
        }
        if (GameValues.gvInGame.CIV_DECISIONS_ALLIANCE_SHOW_POSSIBLE_BUTTON) {
            if (CFG.getIsDesktop() && GameValues.gvInGame.CIV_DECISIONS_ALLIANCE_SHOW_POSSIBLE_CIVS_NUM || !CFG.getIsDesktop() && GameValues.gvInGame.CIV_DECISIONS_ALLIANCE_SHOW_POSSIBLE_CIVS_NUM_MOBILE) {
                menuElems.add(new Button_DiplomacyAction_TextRight(Images.diploAlliance, CFG.lang.get("FormAlliance"), 0, 0, tY, menuW - 2, tempElemH, true, "" + CFG.core.getPossibleAlliances(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).size(), Images.iconTrue){

                    @Override
                    public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                        if (this.menuElemHover != null) {
                            this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                        }
                    }

                    @Override
                    public void actionElem(int iID) {
                        CFG.menus.rebuildInGame_FormAlliance();
                    }

                    @Override
                    public void buildElemHover() {
                        if (CFG.getIsDesktop()) {
                            ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                            ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                            nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("FormAlliance"), CFG.COLOR_HOVER_TITLE));
                            nData.add(new ME_Hover_2Type_Image_Big(Images.diploAlliance, CFG.PADD, 0));
                            nElements.add(new MEHover_2E(nData));
                            nData.clear();
                            nData.add(new ME_Hover_2Type_Space());
                            nElements.add(new MEHover_2E(nData));
                            nData.clear();
                            nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Civilizations") + ": "));
                            nData.add(new ME_Hover_2Type_Text("" + CFG.core.getPossibleAlliances(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).size(), CFG.COLOR_HOVER_TITLE));
                            nElements.add(new MEHover_2E(nData));
                            nData.clear();
                            this.menuElemHover = new ME_Hover_v2(nElements);
                        } else {
                            this.menuElemHover = null;
                        }
                    }
                });
                tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE();
            } else {
                menuElems.add(new Button_DiplomacyAction(Images.diploAlliance, CFG.lang.get("FormAlliance"), 0, 0, tY, menuW - 2, tempElemH, true){

                    @Override
                    public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                        if (this.menuElemHover != null) {
                            this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                        }
                    }

                    @Override
                    public void actionElem(int iID) {
                        CFG.menus.rebuildInGame_FormAlliance();
                    }

                    @Override
                    public void buildElemHover() {
                        if (CFG.getIsDesktop()) {
                            ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                            ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                            nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("FormAlliance"), CFG.COLOR_HOVER_TITLE));
                            nData.add(new ME_Hover_2Type_Image_Big(Images.diploAlliance, CFG.PADD, 0));
                            nElements.add(new MEHover_2E(nData));
                            nData.clear();
                            nData.add(new ME_Hover_2Type_Space());
                            nElements.add(new MEHover_2E(nData));
                            nData.clear();
                            nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Civilizations") + ": "));
                            nData.add(new ME_Hover_2Type_Text("" + CFG.core.getPossibleAlliances(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).size(), CFG.COLOR_HOVER_TITLE));
                            nElements.add(new MEHover_2E(nData));
                            nData.clear();
                            this.menuElemHover = new ME_Hover_v2(nElements);
                        } else {
                            this.menuElemHover = null;
                        }
                    }
                });
                tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE();
            }
        }
        if (GameValues.gvInGame.CIV_DECISIONS_UNION_SHOW_POSSIBLE_BUTTON) {
            if (CFG.getIsDesktop() && GameValues.gvInGame.CIV_DECISIONS_UNION_SHOW_POSSIBLE_CIVS_NUM || !CFG.getIsDesktop() && GameValues.gvInGame.CIV_DECISIONS_UNION_SHOW_POSSIBLE_CIVS_NUM_MOBILE) {
                menuElems.add(new Button_DiplomacyAction_TextRight(Images.diploUnion, CFG.lang.get("FormUnion"), 0, 0, tY, menuW - 2, tempElemH, true, "" + CFG.core.getPossibleUnions(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).size(), Images.iconTrue){

                    @Override
                    public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                        if (this.menuElemHover != null) {
                            this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                        }
                    }

                    @Override
                    public void actionElem(int iID) {
                        CFG.menus.rebuildInGame_FormUnionList();
                    }

                    @Override
                    public void buildElemHover() {
                        if (CFG.getIsDesktop()) {
                            ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                            ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                            nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("FormUnion"), CFG.COLOR_HOVER_TITLE));
                            nData.add(new ME_Hover_2Type_Image_Big(Images.diploUnion, CFG.PADD, 0));
                            nElements.add(new MEHover_2E(nData));
                            nData.clear();
                            nData.add(new ME_Hover_2Type_Space());
                            nElements.add(new MEHover_2E(nData));
                            nData.clear();
                            nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Civilizations") + ": "));
                            nData.add(new ME_Hover_2Type_Text("" + CFG.core.getPossibleUnions(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).size(), CFG.COLOR_HOVER_TITLE));
                            nElements.add(new MEHover_2E(nData));
                            nData.clear();
                            this.menuElemHover = new ME_Hover_v2(nElements);
                        } else {
                            this.menuElemHover = null;
                        }
                    }
                });
                tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE();
            } else {
                menuElems.add(new Button_DiplomacyAction(Images.diploUnion, CFG.lang.get("FormUnion"), 0, 0, tY, menuW - 2, tempElemH, true){

                    @Override
                    public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                        if (this.menuElemHover != null) {
                            this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                        }
                    }

                    @Override
                    public void actionElem(int iID) {
                        CFG.menus.rebuildInGame_FormUnionList();
                    }

                    @Override
                    public void buildElemHover() {
                        if (CFG.getIsDesktop()) {
                            ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                            ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                            nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("FormUnion"), CFG.COLOR_HOVER_TITLE));
                            nData.add(new ME_Hover_2Type_Image_Big(Images.diploUnion, CFG.PADD, 0));
                            nElements.add(new MEHover_2E(nData));
                            nData.clear();
                            nData.add(new ME_Hover_2Type_Space());
                            nElements.add(new MEHover_2E(nData));
                            nData.clear();
                            nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Civilizations") + ": "));
                            nData.add(new ME_Hover_2Type_Text("" + CFG.core.getPossibleUnions(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).size(), CFG.COLOR_HOVER_TITLE));
                            nElements.add(new MEHover_2E(nData));
                            nData.clear();
                            this.menuElemHover = new ME_Hover_v2(nElements);
                        } else {
                            this.menuElemHover = null;
                        }
                    }
                });
                tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE();
            }
        }
        menuElems.add(new Button_DiplomacyAction(Images.topDiplomacyPoints, CFG.lang.get("ReleaseAVassal"), 0, 0, tY, menuW - 2, tempElemH, true){

            @Override
            public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElemHover != null) {
                    this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                }
            }

            @Override
            public void actionElem(int iID) {
                CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).iACTIVE_VIEW_MODE = CFG.mapModesManager.getActiveMapModeID();
                CFG.mapModesManager.disableAllViews();
                CFG.core.resetChooseProvinceData();
                CFG.core.resetRegroupArmy_Data();
                CFG.core.setActiveProvID(-1);
                CFG.core.resetChooseProvinceData_Immediately();
                CFG.gameAction.hideAllProvinceActionViews();
                CFG.core.getProvSelected().clearSelectedProvinces();
                CFG.createVassalData = new CreateVassal_Data();
                CFG.selectMode = true;
                CFG.brushMode = false;
                CFG.VIEW_SHOW_VALUES = false;
                CFG.menus.setMenuID(View.eINGAME_CREATE_VASSAL);
                RenderProvince.updateDrawProvinces();
                CFG.map.getMpB().updateWorldMap_Shaders();
            }

            @Override
            public void buildElemHover() {
                ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("ReleaseAVassal"), CFG.COLOR_HOVER_TITLE));
                nData.add(new ME_Hover_2Type_Image_Big(Images.topDiplomacyPoints, CFG.PADD, 0));
                nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.PADD, 0));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                this.menuElemHover = new ME_Hover_v2(nElements);
            }
        });
        menuElems.add(new Button_Diplomacy_Action_Government(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getIdeology(), CFG.lang.get("ChangeTypeOfGovernment"), 0, 0, tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE(), menuW - 2, tempElemH, true){

            @Override
            public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElemHover != null) {
                    this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                }
            }

            @Override
            public void actionElem(int iID) {
                CFG.menus.rebuildInGame_ChangeGovernment();
            }

            @Override
            public void buildElemHover() {
                try {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    int pointsLeft = CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).civGD.techPoints.getPointsLeft(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId());
                    nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()));
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("ChangeTypeOfGovernment"), CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Ideology_Big(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getIdeology(), CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Space());
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Cost") + ": "));
                    nData.add(new ME_Hover_2Type_Text(CFG.getNumberWthSpaces("" + IdeologiesManager.getChangeGovernmentCost(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())), CFG.COLOR_GOLD));
                    nData.add(new ME_Hover_2Type_Image(Images.topGold(), CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Text(CFG.lang.get("MovementPoints") + ": "));
                    nData.add(new ME_Hover_2Type_Text("" + (float)GameValues.gvGovernment.CHANGE_GOV_MOVEMENT_COST / 10.0f, CFG.COLOR_MOVEMENT));
                    nData.add(new ME_Hover_2Type_Image(Images.topMovementPoints, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Happiness") + ": "));
                    nData.add(new ME_Hover_2Type_Text("-" + CFG.getPrecision2(GameValues.gvGovernment.CHANGE_GOV_DECREASE_HAPPINESS, 100), CFG.COLOR_NEGATIVE_1));
                    nData.add(new ME_Hover_2Type_Image(Images.happiness, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
                catch (IndexOutOfBoundsException ex) {
                    this.menuElemHover = null;
                }
            }
        });
        menuElems.add(new Button_Diplomacy_Action_Religion(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getReligionID(), CFG.lang.get("Religion") + ": " + CFG.religionManager.getReligion(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getReligionID()).getName(), 0, 0, tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE(), menuW - 2, tempElemH, true){

            @Override
            public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElemHover != null) {
                    this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                }
            }

            @Override
            public void actionElem(int iID) {
                CFG.menus.rebuildInGame_ChangeReligion();
            }

            @Override
            public void buildElemHover() {
                try {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()));
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("ChangeReligion"), CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(Images.religion, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()));
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("Religion") + ": "));
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.religionManager.getReligion(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getReligionID()).getName(), CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Religion_Big(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getReligionID(), CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Space());
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Cost") + ": "));
                    nData.add(new ME_Hover_2Type_Text("" + CFG.getNumberWthSpaces("" + IdeologiesManager.getChangeReligionCost(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())), CFG.COLOR_GOLD));
                    nData.add(new ME_Hover_2Type_Image(Images.topGold(), CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Happiness") + ": "));
                    nData.add(new ME_Hover_2Type_Text("-" + CFG.getPrecision2(GameValues.gvGovernment.CHANGE_RELIGION_DECREASE_HAPPINESS, 100), CFG.COLOR_NEGATIVE_1));
                    nData.add(new ME_Hover_2Type_Image(Images.happiness, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
                catch (Exception ex) {
                    this.menuElemHover = null;
                }
            }
        });
        tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE();
        if (CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getIsPartOfHolyRomanEmpire()) {
            menuElems.add(new Button_DiplomacyAction(Images.hreIcon, CFG.lang.get("LeaveHRE"), 0, 0, tY, menuW - 2, tempElemH, true){

                @Override
                public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    if (this.menuElemHover != null) {
                        this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                    }
                }

                @Override
                public void actionElem(int iID) {
                    CFG.setDialogType(DialogType.LEAVE_HRE);
                }

                @Override
                public void buildElemHover() {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("LeaveAlliance") + ": "));
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("HolyRomanEmpire"), CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(Images.hreIcon, CFG.PADD, 0));
                    nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
            });
            menuElems.add(new Button_DiplomacyAction(Images.hreIcon, CFG.lang.get("UniteTheAlliance") + ": " + CFG.lang.get("HolyRomanEmpire"), 0, 0, tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE(), menuW - 2, tempElemH, CFG.hreMgr.getHRE().getIsEmperor(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) && CFG.hreMgr.holyRomanEmpire.canUnitHRE()){

                @Override
                public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    if (this.menuElemHover != null) {
                        this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                    }
                }

                @Override
                public void actionElem(int iID) {
                    CFG.setDialogType(DialogType.UNITE_HRE);
                }

                @Override
                public void buildElemHover() {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("UniteTheAlliance") + ": "));
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("HolyRomanEmpire"), CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(Images.hreIcon, CFG.PADD, 0));
                    nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Space());
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Image_Big(Images.hreIcon, 0, CFG.PADD));
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getCivName() + ": "));
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("IsTheEmperor"), CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(CFG.hreMgr.getHRE().getIsEmperor(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) ? Images.iconTrue : Images.iconFalse, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Image_Big(Images.hreIcon, 0, CFG.PADD));
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("HRE") + ": " + CFG.lang.get("Civilizations") + " <= "));
                    nData.add(new ME_Hover_2Type_Text_Big(GameValues.gvHre.UNITE_CIVS_BELOW + "", CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(CFG.hreMgr.getHRE().canUnitHRE() ? Images.iconTrue : Images.iconFalse, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Image_Big(Images.hreIcon, 0, CFG.PADD));
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("HRE") + ": " + CFG.lang.get("Civilizations") + ": "));
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.getNumberWthSpaces("" + CFG.hreMgr.holyRomanEmpire.getCivsSizeThatExists()) + "", CFG.COLOR_HOVER_TITLE));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
            });
            menuElems.add(new Button_DiplomacyAction(Images.hreIcon, CFG.lang.get("DisolveAlliance") + ": " + CFG.lang.get("HolyRomanEmpire"), 0, 0, tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE(), menuW - 2, tempElemH, CFG.hreMgr.getHRE().getIsEmperor(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())){

                @Override
                public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    if (this.menuElemHover != null) {
                        this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                    }
                }

                @Override
                public void actionElem(int iID) {
                    CFG.setDialogType(DialogType.DISSOLVE_HRE);
                }

                @Override
                public void buildElemHover() {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("DisolveAlliance") + ": "));
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("HolyRomanEmpire"), CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(Images.hreIcon, CFG.PADD, 0));
                    nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Space());
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Image_Big(Images.hreIcon, 0, CFG.PADD));
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getCivName() + ": "));
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("IsTheEmperor"), CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(CFG.hreMgr.getHRE().getIsEmperor(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) ? Images.iconTrue : Images.iconFalse, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
            });
            tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE();
        }
        menuElems.add(new Button_DiplomacyAction(Images.editorCity, CFG.lang.get("MoveCapital"), 0, 0, tY, menuW - 2, tempElemH, CFG.gameAction.moveCapital_CanMove(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())){

            @Override
            public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElemHover != null) {
                    this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                }
            }

            @Override
            public void buildElemHover() {
                if (CFG.core.getActiveProvID() >= 0 && !CFG.core.getProv(CFG.core.getActiveProvID()).getSeaProv()) {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    if (!CFG.gameAction.moveCapital_CanMove(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())) {
                        nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()));
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("TheCapitalCityHasRecentlyBeenMoved"), CFG.COLOR_NEGATIVE_2));
                        nData.add(new ME_Hover_2Type_Image_Big(Images.editorCity, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(GameCalendar.getDate_ByTurnID(GameCalendar.TURNID + Math.abs(GameCalendar.TURNID - (CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getCapitalMoved_LastTurnID() + GameValues.gvMoveCapital.MOVE_CAPITAL_LOCK_MOVING_FOR_X_TURNS)))));
                        nData.add(new ME_Hover_2Type_Text(" [" + CFG.lang.get("TurnsX", Math.abs(GameCalendar.TURNID - (CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getCapitalMoved_LastTurnID() + GameValues.gvMoveCapital.MOVE_CAPITAL_LOCK_MOVING_FOR_X_TURNS))) + "]", CFG.COLOR_NEUTRAL));
                        nData.add(new ME_Hover_2Type_Image(Images.time, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    } else if (CFG.core.getProv(CFG.core.getActiveProvID()).getTrueOwnerOfProv() == CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) {
                        if (CFG.core.getProv(CFG.core.getActiveProvID()).getCivId() == CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) {
                            if (CFG.core.getProv(CFG.core.getActiveProvID()).getName().length() > 0) {
                                nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()));
                                nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("MoveCapitalTo") + ": "));
                                nData.add(new ME_Hover_2Type_Text_Big(CFG.core.getProv(CFG.core.getActiveProvID()).getCitSize() > 0 ? CFG.core.getProv(CFG.core.getActiveProvID()).getCit(0).getCityName() : CFG.core.getProv(CFG.core.getActiveProvID()).getName(), CFG.COLOR_HOVER_TITLE));
                                nElements.add(new MEHover_2E(nData));
                                nData.clear();
                            }
                        } else {
                            nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()));
                            nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("SelectProvince"), CFG.COLOR_HOVER_TITLE));
                            nElements.add(new MEHover_2E(nData));
                            nData.clear();
                        }
                        if (CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getCapitalProvID() >= 0 && CFG.core.getProv(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getCapitalProvID()).getCivId() != CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId() && CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).isAtWarC()) {
                            nData.add(new ME_Hover_2Type_Text(CFG.lang.get("TheCapitalCityIsLost"), CFG.COLOR_NEGATIVE_2));
                            nElements.add(new MEHover_2E(nData));
                            nData.clear();
                        } else {
                            nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Cost") + ": "));
                            nData.add(new ME_Hover_2Type_Text("" + CFG.getNumberWthSpaces("" + CFG.gameAction.moveCapital_Cost(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())), CFG.COLOR_GOLD));
                            nData.add(new ME_Hover_2Type_Image(Images.topGold(), CFG.PADD, 0));
                            nElements.add(new MEHover_2E(nData));
                            nData.clear();
                        }
                    } else {
                        nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getProv(CFG.core.getActiveProvID()).getTrueOwnerOfProv(), 0, CFG.PADD));
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("OccupiedProvince"), CFG.COLOR_NEGATIVE_2));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        if (CFG.core.getProv(CFG.core.getActiveProvID()).getName().length() > 0) {
                            nData.add(new ME_Hover_2Type_Text(CFG.core.getProv(CFG.core.getActiveProvID()).getName()));
                            nData.add(new ME_Hover_2Type_Flag(CFG.core.getProv(CFG.core.getActiveProvID()).getCivId(), CFG.PADD, 0));
                            nElements.add(new MEHover_2E(nData));
                            nData.clear();
                        }
                    }
                    this.menuElemHover = new ME_Hover_v2(nElements);
                } else {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()));
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("SelectProvince"), CFG.COLOR_HOVER_TITLE));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    if (CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getCapitalProvID() >= 0 && CFG.core.getProv(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getCapitalProvID()).getCivId() != CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId() && CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).isAtWarC()) {
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("TheCapitalCityIsLost"), CFG.COLOR_NEGATIVE_2));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    }
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
            }

            @Override
            public boolean getIsClickable() {
                return super.getIsClickable() && CFG.core.getActiveProvID() >= 0 && CFG.core.getProv(CFG.core.getActiveProvID()).getTrueOwnerOfProv() == CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId() && CFG.core.getProv(CFG.core.getActiveProvID()).getCivId() == CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId() && CFG.core.getActiveProvID() != CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getCapitalProvID() && (CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getCapitalProvID() < 0 || CFG.core.getProv(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getCapitalProvID()).getCivId() == CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId() || !CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).isAtWarC());
            }

            @Override
            public void actionElem(int iID) {
                CFG.menus.rebuildInGame_MoveCapital(CFG.core.getActiveProvID());
            }
        });
        menuElems.add(new Button_DiplomacyAction(Images.editorCity, CFG.lang.get("FoundCity"), 0, 0, tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE(), menuW - 2, tempElemH, true){

            @Override
            public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElemHover != null) {
                    this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                }
            }

            @Override
            public void buildElemHover() {
                ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()));
                nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("FoundCity"), CFG.COLOR_HOVER_TITLE));
                nData.add(new ME_Hover_2Type_Image_Big(Images.editorCity, CFG.PADD, 0));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Space());
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_TextDesc(CFG.lang.get("FoundCityDesc")));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                this.menuElemHover = new ME_Hover_v2(nElements);
            }

            @Override
            public void actionElem(int iID) {
                CFG.backToMenu = View.eINGAME;
                CFG.EDITOR_ACTIVE_GAMEDATA_TAG = System.currentTimeMillis() + CFG.extraRandomTag();
                CFG.editorCity = new City("", -1, -1, Images.city3);
                CFG.core.setActiveProvID(-1);
                CFG.menus.setMenuID(View.eCC);
                CFG.updateKeyboard_Actions();
            }
        });
        menuElems.add(new Button_DiplomacyAction_TextRight(Images.diploLoan, CFG.lang.get("TakeLoan"), 0, 0, tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE(), menuW - 2, tempElemH, true, "" + CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getLoansSize() + "/" + GameValues.gvLoan.LOAN_MAX_NUM_OF_LOANS, Images.diploLoan){

            @Override
            public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElemHover != null) {
                    this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                }
            }

            @Override
            public void actionElem(int iID) {
                CFG.menus.rebuildInGame_TakeLoan(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId());
            }

            @Override
            public void buildElemHover() {
                ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), 0, CFG.PADD));
                nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("TakeLoan"), CFG.COLOR_HOVER_TITLE));
                nData.add(new ME_Hover_2Type_Image_Big(Images.diploLoan, CFG.PADD, 0));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Space());
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Text(CFG.lang.get("MovementPoints") + ": "));
                nData.add(new ME_Hover_2Type_Text("-" + (float)GameValues.gvLoan.COST_TAKE_LOAN / 10.0f, CFG.COLOR_NEGATIVE_2));
                nData.add(new ME_Hover_2Type_Image(Images.topMovementPoints, CFG.PADD, 0));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Max") + ": "));
                nData.add(new ME_Hover_2Type_Text("" + CFG.getNumberWthSpaces("" + Loans.takeLoan_MaxValue(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())), CFG.COLOR_GOLD));
                nData.add(new ME_Hover_2Type_Image(Images.topGold(), CFG.PADD, 0));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Space());
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Loans") + ": "));
                nData.add(new ME_Hover_2Type_Text("" + CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getLoansSize() + " / ", CFG.COLOR_HOVER_TITLE));
                nData.add(new ME_Hover_2Type_Text("" + GameValues.gvLoan.LOAN_MAX_NUM_OF_LOANS, CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nData.add(new ME_Hover_2Type_Image(Images.debt, CFG.PADD, 0));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                this.menuElemHover = new ME_Hover_v2(nElements);
            }
        });
        menuElems.add(new Button_DiplomacyAction_TextRight(Images.diploLoan2, CFG.lang.get("RepayLoans"), 0, 0, tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE(), menuW - 2, tempElemH, true, "" + CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getLoansSize(), Images.diploLoan){

            @Override
            public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElemHover != null) {
                    this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                }
            }

            @Override
            public void actionElem(int iID) {
                CFG.menus.rebuildInGame_Loans(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId());
            }

            @Override
            public void buildElemHover() {
                try {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    int pointsLeft = CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).civGD.techPoints.getPointsLeft(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId());
                    nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()));
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("Loans") + ": ", CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Text_Big("" + CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getLoansSize(), CFG.COLOR_NEUTRAL));
                    nData.add(new ME_Hover_2Type_Image_Big(Images.diploLoan2, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    if (CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getLoansSize() > 0) {
                        nData.add(new ME_Hover_2Type_Space());
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        for (int i = 0; i < CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getLoansSize(); ++i) {
                            nData.add(new ME_Hover_2Type_Text(i + 1 + ". "));
                            nData.add(new ME_Hover_2Type_Text(CFG.getNumberWthSpaces("" + CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).getLoan((int)i).iGoldPerTurn * CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).getLoan((int)i).iTurnsLeft), CFG.COLOR_GOLD));
                            nData.add(new ME_Hover_2Type_Image(Images.topGold(), CFG.PADD, CFG.PADD));
                            nData.add(new ME_Hover_2Type_Text(CFG.lang.get("TurnsX", CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).getLoan((int)i).iTurnsLeft)));
                            nData.add(new ME_Hover_2Type_Image(Images.time, CFG.PADD, 0));
                            nElements.add(new MEHover_2E(nData));
                            nData.clear();
                        }
                    }
                    nData.add(new ME_Hover_2Type_Space());
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Loans") + ": "));
                    nData.add(new ME_Hover_2Type_Text("" + CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getLoansSize() + " / ", CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Text("" + GameValues.gvLoan.LOAN_MAX_NUM_OF_LOANS, CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nData.add(new ME_Hover_2Type_Image(Images.debt, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
                catch (Exception ex) {
                    this.menuElemHover = null;
                }
            }
        });
        menuElems.add(new Button_DiplomacyAction(Images.diploArmy, CFG.lang.get("SendVolunteerArmy"), 0, 0, tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE(), menuW - 2, tempElemH, true){

            @Override
            public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElemHover != null) {
                    this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                }
            }

            @Override
            public void actionElem(int iID) {
                Menu_InGame_SendArmy.toProvinceID = -1;
                CFG.menus.rebuildInGame_SendArmy(CFG.core.getActiveProvID());
            }

            @Override
            public void buildElemHover() {
                ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), 0, CFG.PADD));
                nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("SendVolunteerArmy"), CFG.COLOR_HOVER_TITLE));
                nData.add(new ME_Hover_2Type_Image_Big(Images.diploArmy, CFG.PADD, 0));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Space());
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_TextDesc(CFG.lang.get("SendVolunteerArmyText")));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Space());
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Province") + ": "));
                nData.add(new ME_Hover_2Type_Text(CFG.core.getProv(CFG.core.getActiveProvID()).getProvName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nData.add(new ME_Hover_2Type_Flag(CFG.core.getProv(CFG.core.getActiveProvID()).getCivId(), CFG.PADD, 0));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                this.menuElemHover = new ME_Hover_v2(nElements);
            }
        });
        menuElems.add(new Button_DiplomacyAction(Images.pop, CFG.lang.get("PopulationTransfer"), 0, 0, tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE(), menuW - 2, tempElemH, true){

            @Override
            public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElemHover != null) {
                    this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                }
            }

            @Override
            public void actionElem(int iID) {
                CFG.menus.rebuildInGameForceMigration(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId());
            }

            @Override
            public void buildElemHover() {
                ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), 0, CFG.PADD));
                nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("PopulationTransfer"), CFG.COLOR_HOVER_TITLE));
                nData.add(new ME_Hover_2Type_Image_Big(Images.pop, CFG.PADD, 0));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Space());
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_TextDesc(CFG.lang.get("PopulationTransferDesc")));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Space());
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_TextDesc(CFG.lang.get("PopulationTransferNationality2")));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                this.menuElemHover = new ME_Hover_v2(nElements);
            }
        });
        menuElems.add(new Button_DiplomacyAction(Images.pop, CFG.lang.get("RelocatePopulation"), 0, 0, tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE(), menuW - 2, tempElemH, true){

            @Override
            public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElemHover != null) {
                    this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                }
            }

            @Override
            public void actionElem(int iID) {
                Menu_InGame_RelocatePopulation.toProvinceID = -1;
                Menu_InGame_RelocatePopulation.relocate.clear();
                CFG.menus.rebuildInGame_Build_RelocatePopulation(CFG.core.getActiveProvID());
            }

            @Override
            public boolean getIsClickable() {
                try {
                    return CFG.core.getActiveProvID() >= 0 && CFG.core.getProv(CFG.core.getActiveProvID()).getCivId() == CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId();
                }
                catch (Exception exception) {
                    return false;
                }
            }

            @Override
            public void buildElemHover() {
                ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), 0, CFG.PADD));
                nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("RelocatePopulation"), CFG.COLOR_HOVER_TITLE));
                nData.add(new ME_Hover_2Type_Image_Big(Images.pop, CFG.PADD, 0));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Space());
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_TextDesc(CFG.lang.get("RedistributeYourPopulation")));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Space());
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Province") + ": "));
                nData.add(new ME_Hover_2Type_Text(CFG.core.getProv(BuildingsManager.iBuildInProvinceID).getProvName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nData.add(new ME_Hover_2Type_Flag(CFG.core.getProv(BuildingsManager.iBuildInProvinceID).getCivId(), CFG.PADD, 0));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                this.menuElemHover = new ME_Hover_v2(nElements);
            }
        });
        menuElems.add(new Button_DiplomacyAction(Images.nuke, CFG.lang.get("BuildAnAtomicBomb"), 0, 0, tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE(), menuW - 2, tempElemH, true){

            @Override
            public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElemHover != null) {
                    this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                }
            }

            @Override
            public void actionElem(int iID) {
                CFG.menus.rebuildInGame_Build_Nuke();
            }

            @Override
            public void buildElemHover() {
                this.menuElemHover = NukeManager.getHoverNuke();
            }
        });
        menuElems.add(new Button_DiplomacyAction(Images.globalDebt, CFG.lang.get("GlobalLoans"), 0, 0, tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE(), menuW - 2, tempElemH, true){

            @Override
            public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElemHover != null) {
                    this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                }
            }

            @Override
            public void actionElem(int iID) {
                CFG.menus.rebuildInGame_LoansGlobal(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId());
            }

            @Override
            public void buildElemHover() {
                try {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    int globalLoans = 0;
                    for (int i = 1; i < CFG.core.getCivsSize(); ++i) {
                        if (CFG.core.getCiv(i).getNumOfProvs() <= 0 || CFG.FOG_OF_WAR >= 2 && !CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getMetCiv(i) || CFG.core.getCiv(i).getLoansSize() <= 0) continue;
                        globalLoans += CFG.core.getCiv(i).getLoansSize();
                    }
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("GlobalLoans") + ": ", CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.getNumberWthSpaces("" + globalLoans)));
                    nData.add(new ME_Hover_2Type_Image_Big(Images.globalDebt, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
                catch (Exception ex) {
                    this.menuElemHover = null;
                }
            }
        });
        menuElems.add(new Button_DiplomacyAction(Images.investF1, CFG.lang.get("ForeignInvestments"), 0, 0, tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE(), menuW - 2, tempElemH, true){

            @Override
            public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElemHover != null) {
                    this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                }
            }

            @Override
            public void actionElem(int iID) {
                CFG.menus.rebuildInGame_Build_ForeignInvestments(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId());
            }

            @Override
            public void buildElemHover() {
                ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), 0, CFG.PADD));
                nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("ForeignInvestments"), CFG.COLOR_HOVER_TITLE));
                nData.add(new ME_Hover_2Type_Image_Big(Images.investF1, CFG.PADD, 0));
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
        menuElems.add(new Button_DiplomacyAction(Images.investB1, CFG.lang.get("ForeignConstructions"), 0, 0, tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE(), menuW - 2, tempElemH, true){

            @Override
            public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElemHover != null) {
                    this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                }
            }

            @Override
            public void actionElem(int iID) {
                CFG.menus.rebuildInGame_Build_ForeignInvestmentsBuild(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId());
            }

            @Override
            public void buildElemHover() {
                ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), 0, CFG.PADD));
                nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("ForeignConstructions"), CFG.COLOR_HOVER_TITLE));
                nData.add(new ME_Hover_2Type_Image_Big(Images.investB1, CFG.PADD, 0));
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
        menuElems.add(new Button_DiplomacyAction(Images.diploRevolution, CFG.lang.get("ProvokeRebels"), 0, 0, tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE(), menuW - 2, tempElemH, true){

            @Override
            public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElemHover != null) {
                    this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                }
            }

            @Override
            public void actionElem(int iID) {
                CFG.menus.rebuildInGame_ProvokeRebels(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId());
            }

            @Override
            public void buildElemHover() {
                ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("ProvokeRebels"), CFG.COLOR_HOVER_TITLE));
                nData.add(new ME_Hover_2Type_Image_Big(Images.diploRevolution, CFG.PADD, 0));
                nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.PADD, 0));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Space());
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_TextDesc(CFG.lang.get("RaisesRevolutionaryRiskToItsMaximum")));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                this.menuElemHover = new ME_Hover_v2(nElements);
            }
        });
        menuElems.add(new Button_DiplomacyAction(Images.diploWar, CFG.lang.get("FightTheCoalition"), 0, 0, tY += tempElemH, menuW - 2, tempElemH, true){

            @Override
            public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElemHover != null) {
                    this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                }
            }

            @Override
            public void actionElem(int iID) {
                CFG.menus.rebuildInGame_FightCoalition();
            }

            @Override
            public boolean getIsClickable() {
                return CFG.core.isAtPeace(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getCivId()) && CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getCivId() == CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getPuppetOfCiv();
            }

            @Override
            public void buildElemHover() {
                ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("FightTheCoalition"), CFG.COLOR_HOVER_TITLE));
                nData.add(new ME_Hover_2Type_Image_Big(Images.diploWar, CFG.PADD, 0));
                nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.PADD, 0));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Space());
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Image(CFG.core.isAtPeace(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getCivId()) ? Images.iconTrue : Images.iconFalse));
                nData.add(new ME_Hover_2Type_Text(CFG.lang.get("AtPeace")));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Image(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getCivId() == CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getPuppetOfCiv() ? Images.iconTrue : Images.iconFalse));
                nData.add(new ME_Hover_2Type_Text(CFG.lang.get("IsNotAVassal")));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Space());
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_TextDesc(CFG.lang.get("FightTheCoalitionDesc")));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                this.menuElemHover = new ME_Hover_v2(nElements);
            }
        });
        menuElems.add(new Button_DiplomacyAction(Images.editorLeaders, CFG.lang.get("AppointANewLeader"), 0, 0, tY += tempElemH, menuW - 2, tempElemH, true){

            @Override
            public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElemHover != null) {
                    this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                }
            }

            @Override
            public void actionElem(int iID) {
                Menu_InGame_LeaderC.civID = CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId();
                CFG.menus.rebuildInGame_LeaderC();
            }

            @Override
            public void actionElemPPM() {
                Menu_InGame_LeaderC.civID = CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId();
                Menu_InGame_Civ_Decisions.rebuildLeaderC();
            }

            @Override
            public void buildElemHover() {
                ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("AppointANewLeader"), CFG.COLOR_HOVER_TITLE));
                nData.add(new ME_Hover_2Type_Image_Big(Images.editorLeaders, CFG.PADD, 0));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Space());
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_TextDesc(CFG.lang.get("LeaderDesc")));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                this.menuElemHover = new ME_Hover_v2(nElements);
            }
        });
        menuElems.add(new Button_DiplomacyAction_TextRight(Images.frontline, CFG.lang.get("CivilizationTerritoryView"), 0, 0, tY += ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE(), menuW - 2, tempElemH, true, "" + CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getNumOfProvs(), Images.provinces){

            @Override
            public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElemHover != null) {
                    this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                }
            }

            @Override
            public void actionElem(int iID) {
                try {
                    if (CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getNumOfProvs() > 0) {
                        Menu_InGame_CivilizationView.iCivID = CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId();
                        CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).iBefore_PosX = CFG.map.getMpC().getPX();
                        CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).iBefore_PosY = CFG.map.getMpC().getPY();
                        CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).fBefore_Scale = CFG.map.getMpS().getCurrSc();
                        CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).iBefore_ActiveProvince = CFG.core.getActiveProvID();
                        CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).iACTIVE_VIEW_MODE = CFG.mapModesManager.getActiveMapModeID();
                        CFG.mapModesManager.disableAllViews();
                        CFG.menus.setMenuID(View.eINGAME_CIV_VIEW);
                        if (CFG.FOG_OF_WAR == 2) {
                            CFG.core.enableDrawCivilizationRegions_FogOfWar(Menu_InGame_CivilizationView.iCivID, 0);
                        } else {
                            CFG.core.enableDrawCivilizationRegions(Menu_InGame_CivilizationView.iCivID, 0);
                        }
                        CFG.map.getMpB().updateWorldMap_Shaders();
                        CFG.toastM.addM(CFG.core.getCiv(Menu_InGame_CivilizationView.iCivID).getCivName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                        CFG.toastM.setTimeInView(1500);
                    }
                }
                catch (Exception ex) {
                    Menu_InGame_CivilizationView.iCivID = CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId();
                }
            }

            @Override
            public void buildElemHover() {
                ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("CivilizationTerritoryView"), CFG.COLOR_HOVER_TITLE));
                nData.add(new ME_Hover_2Type_Image_Big(Images.frontline, CFG.PADD, 0));
                nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.PADD, 0));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                this.menuElemHover = new ME_Hover_v2(nElements);
            }
        });
        tY += tempElemH;
        if (CFG.SANDBOX_MODE || CFG.SPECTATOR_MODE || Menu_InitGame.EACDG) {
            menuElems.add(new Button_DiplomacyAction(Images.iconTrue, CFG.lang.get("AddNewCivilization"), 0, 0, tY, menuW - 2, tempElemH, true){

                @Override
                public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    if (this.menuElemHover != null) {
                        this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                    }
                }

                @Override
                public void buildElemHover() {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("AddNewCivilization"), CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(Images.iconTrue, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }

                @Override
                public void actionElem(int iID) {
                    Menu_InGame_AddCiv.provinceID = -1;
                    Menu_InGame_AddCiv.techLevel = CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getTechLevel();
                    Menu_InGame_AddCiv.civTag = null;
                    Menu_InGame_AddCiv.provinces.clear();
                    CFG.menus.setMenuID(View.eINGAME_AC);
                }
            });
            tY += tempElemH;
        }
        if (CFG.core.getPlayersSize() > 1) {
            menuElems.add(new Button_DiplomacyAction(Images.iconFalse, CFG.lang.get("RemovePlayer") + ": " + CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getCivName(), 0, 0, tY, menuW - 2, tempElemH, true){

                @Override
                public void drawMEH2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    if (this.menuElemHover != null) {
                        this.menuElemHover.drawAlwaysBelowMEH(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + this.getWidthE() + Core.PADDING + iTranslateX, Touch.getMousePosY());
                    }
                }

                @Override
                public void buildElemHover() {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("RemovePlayer") + ": "));
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getCivName(), CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }

                @Override
                public void actionElem(int iID) {
                    CFG.setDialogType(DialogType.REMOVE_PLAYER);
                }
            });
            tY += tempElemH;
        }
        LAST_ELEMENT_POSY = ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getPosY() + ((MenuElemUI)menuElems.get(menuElems.size() - 1)).getHeightE();
        this.initMenu(new TitleM_TextSmall(null, Menu_InGame_Civ_DiplomacyORActions.getButtonHeight(), false, false){

            @Override
            public void drawT(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                IMGManager.getIMG(Images.gameTopEdgeLine).draw2O(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + iTranslateX, Menu_InGame_Civ_Decisions.this.getPosY() - IMGManager.getIMG(Images.gameTopEdgeLine).getHeight() - this.getHeightT(), Menu_InGame_Civ_Decisions.this.getWidthM() + Core.PADDING, this.getHeightT(), true, false);
                CFG.drawRectInfoBox_Left_Title(oSB, Menu_InGame_Civ_Decisions.this.getPosX() + iTranslateX, Menu_InGame_Civ_Decisions.this.getPosY() - this.getHeightT(), Menu_InGame_Civ_Decisions.this.getWidthM() - 2, this.getHeightT());
                Renderer.drawTextWithShadow(oSB, CFG.FONT_BOLD_SMALL, this.getText(), nPosX + nWidth / 2 - this.getTextWidth() / 2 + iTranslateX, nPosY - this.getHeightT() + this.getHeightT() / 2 - this.getTextHeight() / 2, CFG.COLOR_TEXT_CIV_INFO_TITLE);
            }
        }, AoCGame.LEFT, IMGManager.getIMG(Images.gameTop).getHeight() + CFG.PADD * 4 + (int)((float)CFG.TEXT_HEIGHT_DEFAULT * 0.6f) + ButtonFlagBig.getButtonH() + CFG.PADD * 4, tempW, Menu_InGame_Civ_Decisions.getButtonH() * 8 + 1, menuElems, false, false);
        this.updateLang();
        for (i = 0; i < this.getMenuElemsSize(); ++i) {
            this.getMenuElem(i).setCurr(i % 2);
        }
        extraPosX = -this.getWidthM();
        try {
            if (Menu_InitGame.ENDA) {
                for (i = 0; i < this.getMenuElemsSize(); ++i) {
                    this.getMenuElem(i).setTextE(i + 1 + ". " + this.getMenuElem(i).getTextE());
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Override
    public void updateLang() {
        this.getTitleM().setText(CFG.lang.get("Decisions"));
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (Menu_InGame_Civ.lTime + (long)GameValues.gvInGame.MENUS_ANIMATION_TIME >= System.currentTimeMillis()) {
            if (Menu_InGame_Civ.hideAnimation) {
                extraPosX = -((int)((float)this.getWidthM() * ((float)(System.currentTimeMillis() - Menu_InGame_Civ.lTime) / (float)GameValues.gvInGame.MENUS_ANIMATION_TIME)));
                iTranslateX += extraPosX;
            } else {
                extraPosX = -this.getWidthM() + (int)((float)this.getWidthM() * ((float)(System.currentTimeMillis() - Menu_InGame_Civ.lTime) / (float)GameValues.gvInGame.MENUS_ANIMATION_TIME));
                iTranslateX += extraPosX;
            }
        } else {
            if (Menu_InGame_Civ.hideAnimation) {
                super.setVisibleM(false);
                extraPosX = 0;
                return;
            }
            extraPosX = 0;
        }
        IMGManager.getIMG(Images.gameTopEdgeLine).draw2O(oSB, this.getPosX() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gameTopEdgeLine).getHeight() + iTranslateY, this.getWidthM() + Core.PADDING, this.getHeightM() + 2, true, false);
        this.beginClipM(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(Color.WHITE);
        this.drawMenuM(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        this.endClipM(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
        IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosX() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.pix255).getHeight() + this.getHeightM() + 1, this.getWidthM() - 2, 1);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
        IMGManager.getIMG(Images.line32Off1).drawO(oSB, this.getPosX() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.line32Off1).getHeight() + 1 + this.getHeightM(), this.getWidthM() - 2, 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosX() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.pix255).getHeight() + 2 + this.getHeightM(), this.getWidthM(), 1);
        oSB.setColor(Color.WHITE);
        if (AoCGame.LEFT != 0) {
            oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
            IMGManager.getIMG(Images.pix255).draw2O(oSB, this.getPosX() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.pix255).getHeight() + iTranslateY, 1, this.getHeightM() + 2, true, false);
            oSB.setColor(Color.WHITE);
        }
    }

    @Override
    public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    public void onHovered() {
        CFG.menus.setOrderOfMenu_InGame_CivInfo();
    }

    @Override
    public void actionEL(int iID) {
        if (CFG.gameAction.getActiveTurnStateID() != GameAction.TurnStates.INPUT_ORDERS) {
            return;
        }
        this.getMenuElem(iID).actionElem(iID);
    }

    @Override
    public void setVisibleM(boolean visible) {
        if (visible) {
            super.setVisibleM(visible);
        }
    }

    @Override
    public void actionCloseMenu() {
        super.setVisibleM(false);
        for (int i = 0; i < this.getMenuElemsSize(); ++i) {
            this.getMenuElem(i).setVisibleE(false);
        }
    }

    public static void rebuildLeaderC() {
        try {
            if (Menu_InGame_LeaderC.civID > 0) {
                if (CFG.ideologiesMgr.getRealTag(CFG.core.getCiv(Menu_InGame_LeaderC.civID).getCivTag()).equals("pol")) {
                    if (CFG.oR.nextInt(100) < 50) {
                        CFG.menus.rebuildInGame_LeaderC("Lukasz Jakowski");
                    } else {
                        CFG.menus.rebuildInGame_LeaderC("Ryniu");
                    }
                } else if (CFG.ideologiesMgr.getRealTag(CFG.core.getCiv(Menu_InGame_LeaderC.civID).getCivTag()).equals("fra")) {
                    CFG.menus.rebuildInGame_LeaderC("BalekduNom");
                } else if (CFG.ideologiesMgr.getRealTag(CFG.core.getCiv(Menu_InGame_LeaderC.civID).getCivTag()).equals("tur")) {
                    CFG.menus.rebuildInGame_LeaderC("Kerem Yilmaz");
                } else if (CFG.ideologiesMgr.getRealTag(CFG.core.getCiv(Menu_InGame_LeaderC.civID).getCivTag()).equals("rus")) {
                    CFG.menus.rebuildInGame_LeaderC("Dimzap");
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Override
    public void setPosY(int iPosY) {
        super.setPosY(iPosY);
        this.setHeight(this.iMaxSliderPosY);
        if (this.getPosY() + this.getHeightM() > CFG.GAMEHEIGHT) {
            this.setHeight(Math.max(CFG.GAMEHEIGHT - this.getPosY(), CFG.BUTTON_H / 2));
        }
        int tempElemH = Menu_InGame_Civ_Decisions.getButtonH();
        this.setHeight(Math.max(CFG.GAMEHEIGHT - this.getPosY() - CFG.PADD, Math.min(this.getHeightM(), tempElemH * (CFG.getIsDesktop() ? 14 : 10))));
        this.updateMenuElements_IsInView();
    }
}
