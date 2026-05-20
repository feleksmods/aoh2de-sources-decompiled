package age.of.civilizations2.jakowski.lukasz.Menus.Build;

import age.of.civilizations2.jakowski.lukasz.Button.Build.Button_BuildForeign;
import age.of.civilizations2.jakowski.lukasz.Button.Build.Button_Build_LevelForeign;
import age.of.civilizations2.jakowski.lukasz.Button.Diplomacy.Button_Diplomacy_InvestReturn;
import age.of.civilizations2.jakowski.lukasz.Button.Flag.Button_InGameAction;
import age.of.civilizations2.jakowski.lukasz.Button.GameN.ButtonN_Civs;
import age.of.civilizations2.jakowski.lukasz.Button.GameN.Population.ButtonN_Pop_TextRight;
import age.of.civilizations2.jakowski.lukasz.Button.MenuElemUI;
import age.of.civilizations2.jakowski.lukasz.Button2.Text_Desc;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Civilizations.Construction.ConstructionType;
import age.of.civilizations2.jakowski.lukasz.Core.Core;
import age.of.civilizations2.jakowski.lukasz.GameCalendar;
import age.of.civilizations2.jakowski.lukasz.GameManager;
import age.of.civilizations2.jakowski.lukasz.GameValues.GameValues;
import age.of.civilizations2.jakowski.lukasz.IMGManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MapA.BuildingsManager;
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
import age.of.civilizations2.jakowski.lukasz.Menus.Messages.Diplomacy.Menu_InGame_Message_Alliance;
import age.of.civilizations2.jakowski.lukasz.Menus.Relations.Actions.Menu_InGameOfferAlliance;
import age.of.civilizations2.jakowski.lukasz.Renderer;
import age.of.civilizations2.jakowski.lukasz.TextB.Texts.TextBuildTitle;
import age.of.civilizations2.jakowski.lukasz.TextB.Texts.TextScale;
import age.of.civilizations2.jakowski.lukasz.Title.TitleM_TextSmall;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;
import java.util.List;

public class Menu_InGame_BuildForeign
extends Menu {
    public static int civID = 0;
    public static int provinceID = -1;
    public static List<Boolean> build = new ArrayList<Boolean>();
    public static int buildCost = 0;
    public int totalReturnButtonID = -1;

    public static void buildBuildList() {
        build.clear();
        buildCost = 0;
        for (int i = 0; i < 20; ++i) {
            build.add(false);
        }
    }

    public void updateBuild(int id) {
        build.set(id, build.get(id) == false);
        Menu_InGame_BuildForeign.updateBuildCost();
        if (build.get(id).booleanValue() && (long)buildCost > CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getGold()) {
            build.set(id, false);
            Menu_InGame_BuildForeign.updateBuildCost();
            CFG.toastM.addM(CFG.lang.get("InsufficientGold"), CFG.COLOR_NEGATIVE_1);
        }
        if (this.totalReturnButtonID > 0) {
            this.getMenuElem(this.totalReturnButtonID).setMin(GameManager.buildForeignEconomy_Return(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), provinceID, buildCost));
            this.getMenuElem(this.totalReturnButtonID + 1).setMin(GameManager.buildForeignEconomy_Return(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), provinceID, buildCost) - buildCost);
        }
    }

    public static void updateBuildCost() {
        buildCost = 0;
        if (build.get(0).booleanValue()) {
            buildCost += BuildingsManager.getFort_BuildCost(CFG.core.getProv(provinceID).getLvlOfFort() + 1, provinceID);
        }
        if (build.get(1).booleanValue()) {
            buildCost += BuildingsManager.getTower_BuildCost(CFG.core.getProv(provinceID).getLvlOfWatchTower() + 1, provinceID);
        }
        if (build.get(2).booleanValue()) {
            buildCost += BuildingsManager.getPort_BuildCost(CFG.core.getProv(provinceID).getLvlOfPort() + 1, provinceID);
        }
        if (build.get(3).booleanValue()) {
            buildCost += BuildingsManager.getFarm_BuildCost(CFG.core.getProv(provinceID).getLvlOfFarm() + 1, provinceID);
        }
        if (build.get(4).booleanValue()) {
            buildCost += BuildingsManager.getWorkshop_BuildCost(CFG.core.getProv(provinceID).getLvlOfWorkshop() + 1, provinceID);
        }
        if (build.get(5).booleanValue()) {
            buildCost += BuildingsManager.getMarket_BuildCost(CFG.core.getProv(provinceID).getLvlOfMarket() + 1, provinceID);
        }
        if (build.get(6).booleanValue()) {
            buildCost += BuildingsManager.getLibrary_BuildCost(CFG.core.getProv(provinceID).getLvlOfLibrary() + 1, provinceID);
        }
        if (build.get(7).booleanValue()) {
            buildCost += BuildingsManager.getArmoury_BuildCost(CFG.core.getProv(provinceID).getLvlOfArmoury() + 1, provinceID);
        }
        if (build.get(8).booleanValue()) {
            buildCost += BuildingsManager.getSupply_BuildCost(CFG.core.getProv(provinceID).getLvlOfSupply() + 1, provinceID);
        }
    }

    public final int getElementW2() {
        return this.getWidthM();
    }

    public Menu_InGame_BuildForeign(int nCivID, int nProvinceID) {
        ArrayList<MenuElemUI> menuElements = new ArrayList<MenuElemUI>();
        civID = nCivID;
        provinceID = nProvinceID;
        int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
        int tY = 0;
        menuElements.add(new ButtonN_Civs(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), civID, 2, tY, tempWidth - 4){

            @Override
            public int getWidthE() {
                return Menu_InGame_BuildForeign.this.getElementW() * 2;
            }
        });
        ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(1);
        menuElements.add(new TextBuildTitle(CFG.lang.get("SelectTheBuildingsToConstruct"), -1, 0, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE(), CFG.BUTTON_W, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 4){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_GRAY_NS_HOVER : (this.getIsClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_GRAY_NS : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }

            @Override
            public int getWidthE() {
                return Menu_InGame_BuildForeign.this.getElementW2();
            }
        });
        tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        if (provinceID >= 0) {
            int buttonH = CFG.BUTTON_H * 4 / 5;
            int tRow = 0;
            menuElements.add(new Button_Build_LevelForeign(CFG.lang.get(BuildingsManager.getFort_Name(CFG.core.getProv(provinceID).getLvlOfFort() + 1)), Images.bFort, "" + CFG.core.getProv(provinceID).getLvlOfFort(), BuildingsManager.getFort_BuildCost(CFG.core.getProv(provinceID).getLvlOfFort() + 1, provinceID), BuildingsManager.getFort_BuildMovementCost(CFG.core.getProv(provinceID).getLvlOfFort() + 1), 0, tY, tempWidth / 2, true, CFG.core.getProv(provinceID).getLvlOfFort() == BuildingsManager.getFort_MaxLevel(), CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.FORT), BuildingsManager.getFort_TechLevel(CFG.core.getProv(provinceID).getLvlOfFort() + 1)){

                @Override
                public void actionElem(int iID) {
                    if (CFG.core.getProv(provinceID).getLvlOfFort() == BuildingsManager.getFort_MaxLevel()) {
                        CFG.toastM.addM(CFG.lang.get("Built"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    } else if (CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.FORT) > 0) {
                        CFG.toastM.addM(CFG.lang.get("InConstruction"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    } else if (CFG.core.getCiv(civID).getTechLevel() < BuildingsManager.getFort_TechLevel(CFG.core.getProv(provinceID).getLvlOfFort() + 1)) {
                        CFG.toastM.addM(CFG.lang.get("RequiredTechnologyLevel") + ": " + BuildingsManager.getFort_TechLevel(CFG.core.getProv(provinceID).getLvlOfFort() + 1), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    } else {
                        Menu_InGame_BuildForeign.this.updateBuild(0);
                    }
                }

                @Override
                public int getCurr() {
                    return build.get(0) != false ? 1 : 0;
                }

                @Override
                public void buildElemHover() {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    if (CFG.core.getProv(provinceID).getLvlOfFort() == BuildingsManager.getFort_MaxLevel()) {
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("Fortress") + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.core.getProv(provinceID).getName().length() > 0 ? CFG.core.getProv(provinceID).getName() : CFG.lang.get("Province")));
                        nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getProv(provinceID).getCivId(), CFG.PADD, 0));
                        nData.add(new ME_Hover_2Type_Image_Big(Images.iconTrue, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(" - "));
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("HidesTheArmyFromTheSightOfViewOfWatchTower"), CFG.COLOR_HOVER_TITLE));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(" - "));
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("DefenseBonus") + ": "));
                        nData.add(new ME_Hover_2Type_Text("+" + BuildingsManager.getFort_DefenseBonus(CFG.core.getProv(provinceID).getLvlOfFort()) + "%", CFG.COLOR_POSITIVE));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    } else if (CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.FORT) > 0) {
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("InConstruction"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(GameCalendar.getDate_ByTurnID(GameCalendar.TURNID + CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.FORT))));
                        nData.add(new ME_Hover_2Type_Text(" [" + CFG.lang.get("TurnsX", CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.FORT)) + "]", CFG.COLOR_NEUTRAL));
                        nData.add(new ME_Hover_2Type_Image(Images.time, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Space());
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get(CFG.core.getProv(provinceID).getLvlOfFort() == 0 ? "Castle" : "Fortress") + ": ", CFG.COLOR_HOVER_TITLE));
                        nData.add(new ME_Hover_2Type_Text(CFG.core.getProv(provinceID).getName().length() > 0 ? CFG.core.getProv(provinceID).getName() : CFG.lang.get("Province")));
                        nData.add(new ME_Hover_2Type_Flag(CFG.core.getProv(provinceID).getCivId(), CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    } else {
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get(CFG.core.getProv(provinceID).getLvlOfFort() == 0 ? "BuildCastleIn" : "BuildFortressIn") + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.core.getProv(provinceID).getName().length() > 0 ? CFG.core.getProv(provinceID).getName() : CFG.lang.get("Province")));
                        nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getProv(provinceID).getCivId(), CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(" - "));
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("HidesTheArmyFromTheSightOfViewOfWatchTower"), CFG.COLOR_HOVER_TITLE));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(" - "));
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("DefenseBonus") + ": "));
                        nData.add(new ME_Hover_2Type_Text("+" + BuildingsManager.getFort_DefenseBonus(CFG.core.getProv(provinceID).getLvlOfFort() + 1) + "%", CFG.COLOR_POSITIVE));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Space());
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Cost") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + BuildingsManager.getFort_BuildCost(CFG.core.getProv(provinceID).getLvlOfFort() + 1, provinceID), CFG.core.getCiv(civID).getGold() >= (long)BuildingsManager.getFort_BuildCost(CFG.core.getProv(provinceID).getLvlOfFort() + 1, provinceID) ? CFG.COLOR_GOLD : CFG.COLOR_NEGATIVE_2));
                        nData.add(new ME_Hover_2Type_Image(Images.topGold(), CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("MovementPoints") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + (float)BuildingsManager.getFort_BuildMovementCost(CFG.core.getProv(provinceID).getLvlOfFort() + 1) / 10.0f, CFG.core.getCiv(civID).getMovemPoints() >= BuildingsManager.getFort_BuildMovementCost(CFG.core.getProv(provinceID).getLvlOfFort() + 1) ? CFG.COLOR_MOVEMENT : CFG.COLOR_NEGATIVE_2));
                        nData.add(new ME_Hover_2Type_Image(Images.topMovementPoints, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("ConstructionWillTakeXurns", BuildingsManager.getFort_Construction(CFG.core.getProv(provinceID).getLvlOfFort() + 1))));
                        nData.add(new ME_Hover_2Type_Image(Images.time, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("RequiredTechnologyLevel") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + (float)((int)(BuildingsManager.getFort_TechLevel(CFG.core.getProv(provinceID).getLvlOfFort() + 1) * 100.0f)) / 100.0f, CFG.core.getCiv(civID).getTechLevel() >= BuildingsManager.getFort_TechLevel(CFG.core.getProv(provinceID).getLvlOfFort() + 1) ? CFG.COLOR_TECHNOLOGY : CFG.COLOR_NEGATIVE_2));
                        nData.add(new ME_Hover_2Type_Image(Images.technology, CFG.PADD, 0));
                        nData.add(new ME_Hover_2Type_Image(CFG.core.getCiv(civID).getTechLevel() >= BuildingsManager.getFort_TechLevel(CFG.core.getProv(provinceID).getLvlOfFort() + 1) ? Images.iconTrue : Images.iconFalse, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    }
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
            });
            ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setHeightE(buttonH);
            ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(tRow);
            menuElements.add(new Button_BuildForeign(BuildingsManager.getTower_Name(CFG.core.getProv(provinceID).getLvlOfWatchTower() + 1), Images.bTower, BuildingsManager.getTower_BuildCost(CFG.core.getProv(provinceID).getLvlOfWatchTower() + 1, provinceID), BuildingsManager.getTower_BuildMovementCost(CFG.core.getProv(provinceID).getLvlOfWatchTower() + 1), tempWidth / 2, tY, tempWidth / 2, true, CFG.core.getProv(provinceID).getLvlOfWatchTower() == BuildingsManager.getTower_MaxLevel(), CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.TOWER), BuildingsManager.getTower_TechLevel(CFG.core.getProv(provinceID).getLvlOfWatchTower() + 1)){

                @Override
                public void actionElem(int iID) {
                    if (CFG.core.getProv(provinceID).getLvlOfWatchTower() == BuildingsManager.getTower_MaxLevel()) {
                        CFG.toastM.addM(CFG.lang.get("Built"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    } else if (CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.TOWER) > 0) {
                        CFG.toastM.addM(CFG.lang.get("InConstruction"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    } else if (CFG.core.getCiv(civID).getTechLevel() < BuildingsManager.getTower_TechLevel(CFG.core.getProv(provinceID).getLvlOfWatchTower() + 1)) {
                        CFG.toastM.addM(CFG.lang.get("RequiredTechnologyLevel") + ": " + BuildingsManager.getTower_TechLevel(CFG.core.getProv(provinceID).getLvlOfWatchTower() + 1), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    } else {
                        Menu_InGame_BuildForeign.this.updateBuild(1);
                    }
                }

                @Override
                public int getCurr() {
                    return build.get(1) != false ? 1 : 0;
                }

                @Override
                public void buildElemHover() {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    if (CFG.core.getProv(provinceID).getLvlOfWatchTower() == BuildingsManager.getTower_MaxLevel()) {
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("WatchTower") + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nData.add(new ME_Hover_2Type_Text(CFG.core.getProv(provinceID).getName().length() > 0 ? CFG.core.getProv(provinceID).getName() : CFG.lang.get("Province")));
                        nData.add(new ME_Hover_2Type_Flag(CFG.core.getProv(provinceID).getCivId(), CFG.PADD, 0));
                        nData.add(new ME_Hover_2Type_Image(Images.iconTrue, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(" - "));
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("AllowsToSeeTheArmyInNeighboringProvinces"), CFG.COLOR_HOVER_TITLE));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(" - "));
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("DefenseBonus") + ": "));
                        nData.add(new ME_Hover_2Type_Text("+" + BuildingsManager.getTower_DefenseBonus(CFG.core.getProv(provinceID).getLvlOfWatchTower()) + "%", CFG.COLOR_POSITIVE));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    } else if (CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.TOWER) > 0) {
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("InConstruction"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(GameCalendar.getDate_ByTurnID(GameCalendar.TURNID + CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.TOWER))));
                        nData.add(new ME_Hover_2Type_Text(" [" + CFG.lang.get("TurnsX", CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.TOWER)) + "]", CFG.COLOR_NEUTRAL));
                        nData.add(new ME_Hover_2Type_Image(Images.time, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Space());
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("WatchTower") + ": ", CFG.COLOR_HOVER_TITLE));
                        nData.add(new ME_Hover_2Type_Text(CFG.core.getProv(provinceID).getName().length() > 0 ? CFG.core.getProv(provinceID).getName() : CFG.lang.get("Province")));
                        nData.add(new ME_Hover_2Type_Flag(CFG.core.getProv(provinceID).getCivId(), CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    } else {
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("BuildWatchTowerIn") + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.core.getProv(provinceID).getName().length() > 0 ? CFG.core.getProv(provinceID).getName() : CFG.lang.get("Province")));
                        nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getProv(provinceID).getCivId(), CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(" - "));
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("AllowsToSeeTheArmyInNeighboringProvinces"), CFG.COLOR_HOVER_TITLE));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(" - "));
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("DefenseBonus") + ": "));
                        nData.add(new ME_Hover_2Type_Text("+" + BuildingsManager.getTower_DefenseBonus(CFG.core.getProv(provinceID).getLvlOfWatchTower() + 1) + "%", CFG.COLOR_POSITIVE));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Space());
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Cost") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + BuildingsManager.getTower_BuildCost(CFG.core.getProv(provinceID).getLvlOfWatchTower() + 1, provinceID), CFG.core.getCiv(civID).getGold() >= (long)BuildingsManager.getTower_BuildCost(CFG.core.getProv(provinceID).getLvlOfWatchTower() + 1, provinceID) ? CFG.COLOR_GOLD : CFG.COLOR_NEGATIVE_2));
                        nData.add(new ME_Hover_2Type_Image(Images.topGold(), CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("MovementPoints") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + (float)BuildingsManager.getTower_BuildMovementCost(CFG.core.getProv(provinceID).getLvlOfWatchTower() + 1) / 10.0f, CFG.core.getCiv(civID).getMovemPoints() >= BuildingsManager.getTower_BuildMovementCost(CFG.core.getProv(provinceID).getLvlOfWatchTower() + 1) ? CFG.COLOR_MOVEMENT : CFG.COLOR_NEGATIVE_2));
                        nData.add(new ME_Hover_2Type_Image(Images.topMovementPoints, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("ConstructionWillTakeXurns", BuildingsManager.getTower_Construction(CFG.core.getProv(provinceID).getLvlOfWatchTower() + 1))));
                        nData.add(new ME_Hover_2Type_Image(Images.time, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("RequiredTechnologyLevel") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + (float)((int)(BuildingsManager.getTower_TechLevel(CFG.core.getProv(provinceID).getLvlOfWatchTower() + 1) * 100.0f)) / 100.0f, CFG.core.getCiv(civID).getTechLevel() >= BuildingsManager.getTower_TechLevel(CFG.core.getProv(provinceID).getLvlOfWatchTower() + 1) ? CFG.COLOR_TECHNOLOGY : CFG.COLOR_NEGATIVE_2));
                        nData.add(new ME_Hover_2Type_Image(Images.technology, CFG.PADD, 0));
                        nData.add(new ME_Hover_2Type_Image(CFG.core.getCiv(civID).getTechLevel() >= BuildingsManager.getTower_TechLevel(CFG.core.getProv(provinceID).getLvlOfWatchTower() + 1) ? Images.iconTrue : Images.iconFalse, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    }
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
            });
            ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setHeightE(buttonH);
            ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(tRow);
            tRow = (tRow + 1) % 2;
            String portName = BuildingsManager.getPort_Name(CFG.core.getProv(provinceID).getLvlOfPort() + 1);
            menuElements.add(new Button_BuildForeign(portName.length() == 0 ? CFG.lang.get("NotAvailable") : portName, Images.bPort, BuildingsManager.getPort_BuildCost(CFG.core.getProv(provinceID).getLvlOfPort() + 1, provinceID), BuildingsManager.getPort_BuildMovementCost(CFG.core.getProv(provinceID).getLvlOfPort() + 1), 0, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE(), tempWidth / 2, CFG.core.getProv(provinceID).getLvlOfPort() >= 0, CFG.core.getProv(provinceID).getLvlOfPort() == BuildingsManager.getPort_MaxLevel(), CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.PORT), BuildingsManager.getPort_TechLevel(CFG.core.getProv(provinceID).getLvlOfPort() + 1)){

                @Override
                public void actionElem(int iID) {
                    if (CFG.core.getProv(provinceID).getLvlOfPort() == BuildingsManager.getPort_MaxLevel()) {
                        CFG.toastM.addM(CFG.lang.get("Built"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    } else if (CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.PORT) > 0) {
                        CFG.toastM.addM(CFG.lang.get("InConstruction"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    } else if (CFG.core.getCiv(civID).getTechLevel() < BuildingsManager.getPort_TechLevel(CFG.core.getProv(provinceID).getLvlOfPort() + 1)) {
                        CFG.toastM.addM(CFG.lang.get("RequiredTechnologyLevel") + ": " + BuildingsManager.getPort_TechLevel(CFG.core.getProv(provinceID).getLvlOfPort() + 1), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    } else {
                        Menu_InGame_BuildForeign.this.updateBuild(2);
                    }
                }

                @Override
                public int getCurr() {
                    return build.get(2) != false ? 1 : 0;
                }

                @Override
                public void buildElemHover() {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    if (CFG.core.getProv(provinceID).getLvlOfPort() == BuildingsManager.getPort_MaxLevel()) {
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("Port") + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.core.getProv(provinceID).getName().length() > 0 ? CFG.core.getProv(provinceID).getName() : CFG.lang.get("Province")));
                        nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getProv(provinceID).getCivId(), CFG.PADD, 0));
                        nData.add(new ME_Hover_2Type_Image_Big(Images.iconTrue, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(" - "));
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("AllowsYourArmyGoToTheSea"), CFG.COLOR_HOVER_TITLE));
                        nData.add(new ME_Hover_2Type_Image(Images.icon_move_sea, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(" - "));
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("IncomeProduction") + ": "));
                        nData.add(new ME_Hover_2Type_Text("+" + (int)(BuildingsManager.getPort_IncomeProduction(CFG.core.getProv(provinceID).getLvlOfPort()) * 100.0f) + "%", CFG.COLOR_POSITIVE));
                        nData.add(new ME_Hover_2Type_Image(Images.economy, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    } else if (CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.PORT) > 0) {
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("InConstruction"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(GameCalendar.getDate_ByTurnID(GameCalendar.TURNID + CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.PORT))));
                        nData.add(new ME_Hover_2Type_Text(" [" + CFG.lang.get("TurnsX", CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.PORT)) + "]", CFG.COLOR_NEUTRAL));
                        nData.add(new ME_Hover_2Type_Image(Images.time, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Space());
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Port") + ": ", CFG.COLOR_HOVER_TITLE));
                        nData.add(new ME_Hover_2Type_Text(CFG.core.getProv(provinceID).getName().length() > 0 ? CFG.core.getProv(provinceID).getName() : CFG.lang.get("Province")));
                        nData.add(new ME_Hover_2Type_Flag(CFG.core.getProv(provinceID).getCivId(), CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    } else {
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("BuildPortIn") + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.core.getProv(provinceID).getName().length() > 0 ? CFG.core.getProv(provinceID).getName() : CFG.lang.get("Province")));
                        nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getProv(provinceID).getCivId(), CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(" - "));
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("AllowsYourArmyGoToTheSea"), CFG.COLOR_HOVER_TITLE));
                        nData.add(new ME_Hover_2Type_Image(Images.icon_move_sea, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(" - "));
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("IncomeProduction") + ": "));
                        nData.add(new ME_Hover_2Type_Text("+" + (int)(BuildingsManager.getPort_IncomeProduction(CFG.core.getProv(provinceID).getLvlOfPort() + 1) * 100.0f) + "%", CFG.COLOR_POSITIVE));
                        nData.add(new ME_Hover_2Type_Image(Images.economy, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Space());
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Cost") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + BuildingsManager.getPort_BuildCost(CFG.core.getProv(provinceID).getLvlOfPort() + 1, provinceID), CFG.core.getCiv(civID).getGold() >= (long)BuildingsManager.getPort_BuildCost(CFG.core.getProv(provinceID).getLvlOfPort() + 1, provinceID) ? CFG.COLOR_GOLD : CFG.COLOR_NEGATIVE_2));
                        nData.add(new ME_Hover_2Type_Image(Images.topGold(), CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("MovementPoints") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + (float)BuildingsManager.getPort_BuildMovementCost(CFG.core.getProv(provinceID).getLvlOfPort() + 1) / 10.0f, CFG.core.getCiv(civID).getMovemPoints() >= BuildingsManager.getPort_BuildMovementCost(CFG.core.getProv(provinceID).getLvlOfPort() + 1) ? CFG.COLOR_MOVEMENT : CFG.COLOR_NEGATIVE_2));
                        nData.add(new ME_Hover_2Type_Image(Images.topMovementPoints, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("ConstructionWillTakeXurns", BuildingsManager.getPort_Construction(CFG.core.getProv(provinceID).getLvlOfPort() + 1))));
                        nData.add(new ME_Hover_2Type_Image(Images.time, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("RequiredTechnologyLevel") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + (float)((int)(BuildingsManager.getPort_TechLevel(CFG.core.getProv(provinceID).getLvlOfPort() + 1) * 100.0f)) / 100.0f, CFG.core.getCiv(civID).getTechLevel() >= BuildingsManager.getPort_TechLevel(CFG.core.getProv(provinceID).getLvlOfPort() + 1) ? CFG.COLOR_TECHNOLOGY : CFG.COLOR_NEGATIVE_2));
                        nData.add(new ME_Hover_2Type_Image(Images.technology, CFG.PADD, 0));
                        nData.add(new ME_Hover_2Type_Image(CFG.core.getCiv(civID).getTechLevel() >= BuildingsManager.getPort_TechLevel(CFG.core.getProv(provinceID).getLvlOfPort() + 1) ? Images.iconTrue : Images.iconFalse, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    }
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
            });
            ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setHeightE(buttonH);
            ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(tRow);
            menuElements.add(new Button_Build_LevelForeign(CFG.lang.get(BuildingsManager.getFarm_Name(CFG.core.getProv(provinceID).getLvlOfFarm() + 1)), Images.bFarm, "" + CFG.core.getProv(provinceID).getLvlOfFarm(), BuildingsManager.getFarm_BuildCost(CFG.core.getProv(provinceID).getLvlOfFarm() + 1, provinceID), BuildingsManager.getFarm_BuildMovementCost(CFG.core.getProv(provinceID).getLvlOfFarm() + 1), tempWidth / 2, tY, tempWidth / 2, true, CFG.core.getProv(provinceID).getLvlOfFarm() == BuildingsManager.getFarm_MaxLevel(), CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.FARM), BuildingsManager.getFarm_TechLevel(CFG.core.getProv(provinceID).getLvlOfFarm() + 1)){

                @Override
                public void actionElem(int iID) {
                    if (CFG.core.getProv(provinceID).getLvlOfFarm() == BuildingsManager.getFarm_MaxLevel()) {
                        CFG.toastM.addM(CFG.lang.get("Built"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    } else if (CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.FARM) > 0) {
                        CFG.toastM.addM(CFG.lang.get("InConstruction"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    } else if (CFG.core.getCiv(civID).getTechLevel() < BuildingsManager.getFarm_TechLevel(CFG.core.getProv(provinceID).getLvlOfFarm() + 1)) {
                        CFG.toastM.addM(CFG.lang.get("RequiredTechnologyLevel") + ": " + BuildingsManager.getFarm_TechLevel(CFG.core.getProv(provinceID).getLvlOfFarm() + 1), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    } else {
                        Menu_InGame_BuildForeign.this.updateBuild(3);
                    }
                }

                @Override
                public int getCurr() {
                    return build.get(3) != false ? 1 : 0;
                }

                @Override
                public void buildElemHover() {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    if (CFG.core.getProv(provinceID).getLvlOfFarm() == BuildingsManager.getFarm_MaxLevel()) {
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("Farm") + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.core.getProv(provinceID).getName().length() > 0 ? CFG.core.getProv(provinceID).getName() : CFG.lang.get("Province")));
                        nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getProv(provinceID).getCivId(), CFG.PADD, 0));
                        nData.add(new ME_Hover_2Type_Image_Big(Images.iconTrue, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(" - "));
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("GrowthRate") + ": "));
                        nData.add(new ME_Hover_2Type_Text("+" + (int)(BuildingsManager.getFarm_GrowthRateBonus(CFG.core.getProv(provinceID).getLvlOfFarm()) * 100.0f) + "%", CFG.COLOR_POSITIVE));
                        nData.add(new ME_Hover_2Type_Image(Images.popGrowth, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    } else if (CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.FARM) > 0) {
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("InConstruction"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(GameCalendar.getDate_ByTurnID(GameCalendar.TURNID + CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.FARM))));
                        nData.add(new ME_Hover_2Type_Text(" [" + CFG.lang.get("TurnsX", CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.FARM)) + "]", CFG.COLOR_NEUTRAL));
                        nData.add(new ME_Hover_2Type_Image(Images.time, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Space());
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Farm") + ": ", CFG.COLOR_HOVER_TITLE));
                        nData.add(new ME_Hover_2Type_Text(CFG.core.getProv(provinceID).getName().length() > 0 ? CFG.core.getProv(provinceID).getName() : CFG.lang.get("Province")));
                        nData.add(new ME_Hover_2Type_Flag(CFG.core.getProv(provinceID).getCivId(), CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    } else {
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("BuildFarmIn") + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.core.getProv(provinceID).getName().length() > 0 ? CFG.core.getProv(provinceID).getName() : CFG.lang.get("Province")));
                        nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getProv(provinceID).getCivId(), CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(" - "));
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("GrowthRate") + ": "));
                        nData.add(new ME_Hover_2Type_Text("+" + (int)(BuildingsManager.getFarm_GrowthRateBonus(CFG.core.getProv(provinceID).getLvlOfFarm() + 1) * 100.0f) + "%", CFG.COLOR_POSITIVE));
                        nData.add(new ME_Hover_2Type_Image(Images.popGrowth, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Space());
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Cost") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + BuildingsManager.getFarm_BuildCost(CFG.core.getProv(provinceID).getLvlOfFarm() + 1, provinceID), CFG.core.getCiv(civID).getGold() >= (long)BuildingsManager.getFarm_BuildCost(CFG.core.getProv(provinceID).getLvlOfFarm() + 1, provinceID) ? CFG.COLOR_GOLD : CFG.COLOR_NEGATIVE_2));
                        nData.add(new ME_Hover_2Type_Image(Images.topGold(), CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("MovementPoints") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + (float)BuildingsManager.getFarm_BuildMovementCost(CFG.core.getProv(provinceID).getLvlOfFarm() + 1) / 10.0f, CFG.core.getCiv(civID).getMovemPoints() >= BuildingsManager.getFarm_BuildMovementCost(CFG.core.getProv(provinceID).getLvlOfFarm() + 1) ? CFG.COLOR_MOVEMENT : CFG.COLOR_NEGATIVE_2));
                        nData.add(new ME_Hover_2Type_Image(Images.topMovementPoints, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("ConstructionWillTakeXurns", BuildingsManager.getFarm_Construction(CFG.core.getProv(provinceID).getLvlOfFarm() + 1))));
                        nData.add(new ME_Hover_2Type_Image(Images.time, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("RequiredTechnologyLevel") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + (float)((int)(BuildingsManager.getFarm_TechLevel(CFG.core.getProv(provinceID).getLvlOfFarm() + 1) * 100.0f)) / 100.0f, CFG.core.getCiv(civID).getTechLevel() >= BuildingsManager.getFarm_TechLevel(CFG.core.getProv(provinceID).getLvlOfFarm() + 1) ? CFG.COLOR_TECHNOLOGY : CFG.COLOR_NEGATIVE_2));
                        nData.add(new ME_Hover_2Type_Image(Images.technology, CFG.PADD, 0));
                        nData.add(new ME_Hover_2Type_Image(CFG.core.getCiv(civID).getTechLevel() >= BuildingsManager.getFarm_TechLevel(CFG.core.getProv(provinceID).getLvlOfFarm() + 1) ? Images.iconTrue : Images.iconFalse, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    }
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
            });
            ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setHeightE(buttonH);
            ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(tRow);
            tRow = (tRow + 1) % 2;
            menuElements.add(new Button_Build_LevelForeign(CFG.lang.get(BuildingsManager.getWorkshop_Name(CFG.core.getProv(provinceID).getLvlOfWorkshop() + 1)), Images.bWorkshop, "" + CFG.core.getProv(provinceID).getLvlOfWorkshop(), BuildingsManager.getWorkshop_BuildCost(CFG.core.getProv(provinceID).getLvlOfWorkshop() + 1, provinceID), BuildingsManager.getWorkshop_BuildMovementCost(CFG.core.getProv(provinceID).getLvlOfWorkshop() + 1), 0, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE(), tempWidth / 2, true, CFG.core.getProv(provinceID).getLvlOfWorkshop() == BuildingsManager.getWorkshop_MaxLevel(), CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.WORKSHOP), BuildingsManager.getWorkshop_TechLevel(CFG.core.getProv(provinceID).getLvlOfWorkshop() + 1)){

                @Override
                public void actionElem(int iID) {
                    if (CFG.core.getProv(provinceID).getLvlOfWorkshop() == BuildingsManager.getWorkshop_MaxLevel()) {
                        CFG.toastM.addM(CFG.lang.get("Built"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    } else if (CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.WORKSHOP) > 0) {
                        CFG.toastM.addM(CFG.lang.get("InConstruction"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    } else if (CFG.core.getCiv(civID).getTechLevel() < BuildingsManager.getWorkshop_TechLevel(CFG.core.getProv(provinceID).getLvlOfWorkshop() + 1)) {
                        CFG.toastM.addM(CFG.lang.get("RequiredTechnologyLevel") + ": " + BuildingsManager.getWorkshop_TechLevel(CFG.core.getProv(provinceID).getLvlOfWorkshop() + 1), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    } else {
                        Menu_InGame_BuildForeign.this.updateBuild(4);
                    }
                }

                @Override
                public int getCurr() {
                    return build.get(4) != false ? 1 : 0;
                }

                @Override
                public void buildElemHover() {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    if (CFG.core.getProv(provinceID).getLvlOfWorkshop() == BuildingsManager.getWorkshop_MaxLevel()) {
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get(BuildingsManager.getWorkshop_Name(CFG.core.getProv(provinceID).getLvlOfWorkshop())) + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.core.getProv(provinceID).getName().length() > 0 ? CFG.core.getProv(provinceID).getName() : CFG.lang.get("Province")));
                        nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getProv(provinceID).getCivId(), CFG.PADD, 0));
                        nData.add(new ME_Hover_2Type_Image_Big(Images.iconTrue, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(" - "));
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("IncomeProduction") + ": "));
                        nData.add(new ME_Hover_2Type_Text("+" + (int)(BuildingsManager.getWorkshop_IncomeProduction(CFG.core.getProv(provinceID).getLvlOfWorkshop()) * 100.0f) + "%", CFG.COLOR_POSITIVE));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    } else if (CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.WORKSHOP) > 0) {
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("InConstruction"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(GameCalendar.getDate_ByTurnID(GameCalendar.TURNID + CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.WORKSHOP))));
                        nData.add(new ME_Hover_2Type_Text(" [" + CFG.lang.get("TurnsX", CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.WORKSHOP)) + "]", CFG.COLOR_NEUTRAL));
                        nData.add(new ME_Hover_2Type_Image(Images.time, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Space());
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get(BuildingsManager.getWorkshop_Name(CFG.core.getProv(provinceID).getLvlOfWorkshop() + 1)) + ": ", CFG.COLOR_HOVER_TITLE));
                        nData.add(new ME_Hover_2Type_Text(CFG.core.getProv(provinceID).getName().length() > 0 ? CFG.core.getProv(provinceID).getName() : CFG.lang.get("Province")));
                        nData.add(new ME_Hover_2Type_Flag(CFG.core.getProv(provinceID).getCivId(), CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    } else {
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("BuildWorkshopIn") + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.core.getProv(provinceID).getName().length() > 0 ? CFG.core.getProv(provinceID).getName() : CFG.lang.get("Province")));
                        nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getProv(provinceID).getCivId(), CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(" - "));
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("IncomeProduction") + ": "));
                        nData.add(new ME_Hover_2Type_Text("+" + (int)(BuildingsManager.getWorkshop_IncomeProduction(CFG.core.getProv(provinceID).getLvlOfWorkshop() + 1) * 100.0f) + "%", CFG.COLOR_POSITIVE));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Space());
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Cost") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + BuildingsManager.getWorkshop_BuildCost(CFG.core.getProv(provinceID).getLvlOfWorkshop() + 1, provinceID), CFG.core.getCiv(civID).getGold() >= (long)BuildingsManager.getWorkshop_BuildCost(CFG.core.getProv(provinceID).getLvlOfWorkshop() + 1, provinceID) ? CFG.COLOR_GOLD : CFG.COLOR_NEGATIVE_2));
                        nData.add(new ME_Hover_2Type_Image(Images.topGold(), CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("MovementPoints") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + (float)BuildingsManager.getWorkshop_BuildMovementCost(CFG.core.getProv(provinceID).getLvlOfWorkshop() + 1) / 10.0f, CFG.core.getCiv(civID).getMovemPoints() >= BuildingsManager.getWorkshop_BuildMovementCost(CFG.core.getProv(provinceID).getLvlOfWorkshop() + 1) ? CFG.COLOR_MOVEMENT : CFG.COLOR_NEGATIVE_2));
                        nData.add(new ME_Hover_2Type_Image(Images.topMovementPoints, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("ConstructionWillTakeXurns", BuildingsManager.getWorkshop_Construction(CFG.core.getProv(provinceID).getLvlOfWorkshop() + 1))));
                        nData.add(new ME_Hover_2Type_Image(Images.time, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("RequiredTechnologyLevel") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + (float)((int)(BuildingsManager.getWorkshop_TechLevel(CFG.core.getProv(provinceID).getLvlOfWorkshop() + 1) * 100.0f)) / 100.0f, CFG.core.getCiv(civID).getTechLevel() >= BuildingsManager.getWorkshop_TechLevel(CFG.core.getProv(provinceID).getLvlOfWorkshop() + 1) ? CFG.COLOR_TECHNOLOGY : CFG.COLOR_NEGATIVE_2));
                        nData.add(new ME_Hover_2Type_Image(Images.technology, CFG.PADD, 0));
                        nData.add(new ME_Hover_2Type_Image(CFG.core.getCiv(civID).getTechLevel() >= BuildingsManager.getWorkshop_TechLevel(CFG.core.getProv(provinceID).getLvlOfWorkshop() + 1) ? Images.iconTrue : Images.iconFalse, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    }
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
            });
            ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setHeightE(buttonH);
            ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(tRow);
            menuElements.add(new Button_Build_LevelForeign(CFG.lang.get(BuildingsManager.getMarket_Name(CFG.core.getProv(provinceID).getLvlOfMarket() + 1)), Images.bMarket, "" + CFG.core.getProv(provinceID).getLvlOfMarket(), BuildingsManager.getMarket_BuildCost(CFG.core.getProv(provinceID).getLvlOfMarket() + 1, provinceID), BuildingsManager.getMarket_BuildMovementCost(CFG.core.getProv(provinceID).getLvlOfMarket() + 1), tempWidth / 2, tY, tempWidth / 2, true, CFG.core.getProv(provinceID).getLvlOfMarket() == BuildingsManager.getMarket_MaxLevel(), CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.MARKET), BuildingsManager.getMarket_TechLevel(CFG.core.getProv(provinceID).getLvlOfMarket() + 1)){

                @Override
                public void actionElem(int iID) {
                    if (CFG.core.getProv(provinceID).getLvlOfMarket() == BuildingsManager.getMarket_MaxLevel()) {
                        CFG.toastM.addM(CFG.lang.get("Built"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    } else if (CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.MARKET) > 0) {
                        CFG.toastM.addM(CFG.lang.get("InConstruction"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    } else if (CFG.core.getCiv(civID).getTechLevel() < BuildingsManager.getMarket_TechLevel(CFG.core.getProv(provinceID).getLvlOfMarket() + 1)) {
                        CFG.toastM.addM(CFG.lang.get("RequiredTechnologyLevel") + ": " + BuildingsManager.getMarket_TechLevel(CFG.core.getProv(provinceID).getLvlOfMarket() + 1), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    } else {
                        Menu_InGame_BuildForeign.this.updateBuild(5);
                    }
                }

                @Override
                public int getCurr() {
                    return build.get(5) != false ? 1 : 0;
                }

                @Override
                public void buildElemHover() {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    if (CFG.core.getProv(provinceID).getLvlOfMarket() == BuildingsManager.getMarket_MaxLevel()) {
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get(BuildingsManager.getMarket_Name(CFG.core.getProv(provinceID).getLvlOfMarket())) + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.core.getProv(provinceID).getName().length() > 0 ? CFG.core.getProv(provinceID).getName() : CFG.lang.get("Province")));
                        nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getProv(provinceID).getCivId(), CFG.PADD, 0));
                        nData.add(new ME_Hover_2Type_Image_Big(Images.iconTrue, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(" - "));
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("IncomeTaxation") + ": "));
                        nData.add(new ME_Hover_2Type_Text("+" + (int)(BuildingsManager.getMarket_IncomeTaxation(CFG.core.getProv(provinceID).getLvlOfMarket()) * 100.0f) + "%", CFG.COLOR_POSITIVE));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    } else if (CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.MARKET) > 0) {
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("InConstruction"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(GameCalendar.getDate_ByTurnID(GameCalendar.TURNID + CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.MARKET))));
                        nData.add(new ME_Hover_2Type_Text(" [" + CFG.lang.get("TurnsX", CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.MARKET)) + "]", CFG.COLOR_NEUTRAL));
                        nData.add(new ME_Hover_2Type_Image(Images.time, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Space());
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get(BuildingsManager.getMarket_Name(CFG.core.getProv(provinceID).getLvlOfMarket() + 1)) + ": ", CFG.COLOR_HOVER_TITLE));
                        nData.add(new ME_Hover_2Type_Text(CFG.core.getProv(provinceID).getName().length() > 0 ? CFG.core.getProv(provinceID).getName() : CFG.lang.get("Province")));
                        nData.add(new ME_Hover_2Type_Flag(CFG.core.getProv(provinceID).getCivId(), CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    } else {
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("BuildMarketIn") + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.core.getProv(provinceID).getName().length() > 0 ? CFG.core.getProv(provinceID).getName() : CFG.lang.get("Province")));
                        nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getProv(provinceID).getCivId(), CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(" - "));
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("IncomeTaxation") + ": "));
                        nData.add(new ME_Hover_2Type_Text("+" + (int)(BuildingsManager.getMarket_IncomeTaxation(CFG.core.getProv(provinceID).getLvlOfMarket() + 1) * 100.0f) + "%", CFG.COLOR_POSITIVE));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Space());
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Cost") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + BuildingsManager.getMarket_BuildCost(CFG.core.getProv(provinceID).getLvlOfMarket() + 1, provinceID), CFG.core.getCiv(civID).getGold() >= (long)BuildingsManager.getMarket_BuildCost(CFG.core.getProv(provinceID).getLvlOfMarket() + 1, provinceID) ? CFG.COLOR_GOLD : CFG.COLOR_NEGATIVE_2));
                        nData.add(new ME_Hover_2Type_Image(Images.topGold(), CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("MovementPoints") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + (float)BuildingsManager.getMarket_BuildMovementCost(CFG.core.getProv(provinceID).getLvlOfMarket() + 1) / 10.0f, CFG.core.getCiv(civID).getMovemPoints() >= BuildingsManager.getMarket_BuildMovementCost(CFG.core.getProv(provinceID).getLvlOfMarket() + 1) ? CFG.COLOR_MOVEMENT : CFG.COLOR_NEGATIVE_2));
                        nData.add(new ME_Hover_2Type_Image(Images.topMovementPoints, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("ConstructionWillTakeXurns", BuildingsManager.getMarket_Construction(CFG.core.getProv(provinceID).getLvlOfMarket() + 1))));
                        nData.add(new ME_Hover_2Type_Image(Images.time, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("RequiredTechnologyLevel") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + (float)((int)(BuildingsManager.getMarket_TechLevel(CFG.core.getProv(provinceID).getLvlOfMarket() + 1) * 100.0f)) / 100.0f, CFG.core.getCiv(civID).getTechLevel() >= BuildingsManager.getMarket_TechLevel(CFG.core.getProv(provinceID).getLvlOfMarket() + 1) ? CFG.COLOR_TECHNOLOGY : CFG.COLOR_NEGATIVE_2));
                        nData.add(new ME_Hover_2Type_Image(Images.technology, CFG.PADD, 0));
                        nData.add(new ME_Hover_2Type_Image(CFG.core.getCiv(civID).getTechLevel() >= BuildingsManager.getMarket_TechLevel(CFG.core.getProv(provinceID).getLvlOfMarket() + 1) ? Images.iconTrue : Images.iconFalse, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    }
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
            });
            ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setHeightE(buttonH);
            ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(tRow);
            tRow = (tRow + 1) % 2;
            menuElements.add(new Button_Build_LevelForeign(CFG.lang.get(BuildingsManager.getLibrary_Name(CFG.core.getProv(provinceID).getLvlOfLibrary() + 1)), Images.bLibrary, "" + CFG.core.getProv(provinceID).getLvlOfLibrary(), BuildingsManager.getLibrary_BuildCost(CFG.core.getProv(provinceID).getLvlOfLibrary() + 1, provinceID), BuildingsManager.getLibrary_BuildMovementCost(CFG.core.getProv(provinceID).getLvlOfLibrary() + 1), 0, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE(), tempWidth / 2, true, CFG.core.getProv(provinceID).getLvlOfLibrary() == BuildingsManager.getLibrary_MaxLevel(), CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.LIBRARY), BuildingsManager.getLibrary_TechLevel(CFG.core.getProv(provinceID).getLvlOfLibrary() + 1)){

                @Override
                public void actionElem(int iID) {
                    if (CFG.core.getProv(provinceID).getLvlOfLibrary() == BuildingsManager.getLibrary_MaxLevel()) {
                        CFG.toastM.addM(CFG.lang.get("Built"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    } else if (CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.LIBRARY) > 0) {
                        CFG.toastM.addM(CFG.lang.get("InConstruction"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    } else if (CFG.core.getCiv(civID).getTechLevel() < BuildingsManager.getLibrary_TechLevel(CFG.core.getProv(provinceID).getLvlOfLibrary() + 1)) {
                        CFG.toastM.addM(CFG.lang.get("RequiredTechnologyLevel") + ": " + BuildingsManager.getLibrary_TechLevel(CFG.core.getProv(provinceID).getLvlOfLibrary() + 1), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    } else {
                        Menu_InGame_BuildForeign.this.updateBuild(6);
                    }
                }

                @Override
                public int getCurr() {
                    return build.get(6) != false ? 1 : 0;
                }

                @Override
                public void buildElemHover() {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    if (CFG.core.getProv(provinceID).getLvlOfLibrary() == BuildingsManager.getLibrary_MaxLevel()) {
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get(BuildingsManager.getLibrary_Name(CFG.core.getProv(provinceID).getLvlOfLibrary())) + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.core.getProv(provinceID).getName().length() > 0 ? CFG.core.getProv(provinceID).getName() : CFG.lang.get("Province")));
                        nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getProv(provinceID).getCivId(), CFG.PADD, 0));
                        nData.add(new ME_Hover_2Type_Image_Big(Images.iconTrue, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(" - "));
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("+1"), CFG.COLOR_RESEARCH));
                        nData.add(new ME_Hover_2Type_Image(Images.research, CFG.PADD, CFG.PADD));
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("ResearchPerTurnForEveryXPeopleInProvince", BuildingsManager.getLibrary_ResearchPerPopulation(CFG.core.getProv(provinceID).getLvlOfLibrary())), CFG.COLOR_HOVER_TITLE));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    } else if (CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.LIBRARY) > 0) {
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("InConstruction"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(GameCalendar.getDate_ByTurnID(GameCalendar.TURNID + CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.LIBRARY))));
                        nData.add(new ME_Hover_2Type_Text(" [" + CFG.lang.get("TurnsX", CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.LIBRARY)) + "]", CFG.COLOR_NEUTRAL));
                        nData.add(new ME_Hover_2Type_Image(Images.time, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Space());
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get(BuildingsManager.getLibrary_Name(CFG.core.getProv(provinceID).getLvlOfLibrary() + 1)) + ": ", CFG.COLOR_HOVER_TITLE));
                        nData.add(new ME_Hover_2Type_Text(CFG.core.getProv(provinceID).getName().length() > 0 ? CFG.core.getProv(provinceID).getName() : CFG.lang.get("Province")));
                        nData.add(new ME_Hover_2Type_Flag(CFG.core.getProv(provinceID).getCivId(), CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    } else {
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get(CFG.core.getProv(provinceID).getLvlOfLibrary() == 0 ? "BuildLibraryIn" : (CFG.core.getProv(provinceID).getLvlOfLibrary() == 1 ? "BuildUniversityIn" : "BuildResearchLabIn")) + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.core.getProv(provinceID).getName().length() > 0 ? CFG.core.getProv(provinceID).getName() : CFG.lang.get("Province")));
                        nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getProv(provinceID).getCivId(), CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(" - "));
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("+1"), CFG.COLOR_RESEARCH));
                        nData.add(new ME_Hover_2Type_Image(Images.research, CFG.PADD, CFG.PADD));
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("ResearchPerTurnForEveryXPeopleInProvince", BuildingsManager.getLibrary_ResearchPerPopulation(CFG.core.getProv(provinceID).getLvlOfLibrary() + 1)), CFG.COLOR_HOVER_TITLE));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Space());
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Cost") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + BuildingsManager.getLibrary_BuildCost(CFG.core.getProv(provinceID).getLvlOfLibrary() + 1, provinceID), CFG.core.getCiv(civID).getGold() >= (long)BuildingsManager.getLibrary_BuildCost(CFG.core.getProv(provinceID).getLvlOfLibrary() + 1, provinceID) ? CFG.COLOR_GOLD : CFG.COLOR_NEGATIVE_2));
                        nData.add(new ME_Hover_2Type_Image(Images.topGold(), CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("MovementPoints") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + (float)BuildingsManager.getLibrary_BuildMovementCost(CFG.core.getProv(provinceID).getLvlOfLibrary() + 1) / 10.0f, CFG.core.getCiv(civID).getMovemPoints() >= BuildingsManager.getLibrary_BuildMovementCost(CFG.core.getProv(provinceID).getLvlOfLibrary() + 1) ? CFG.COLOR_MOVEMENT : CFG.COLOR_NEGATIVE_2));
                        nData.add(new ME_Hover_2Type_Image(Images.topMovementPoints, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("ConstructionWillTakeXurns", BuildingsManager.getLibrary_Construction(CFG.core.getProv(provinceID).getLvlOfLibrary() + 1))));
                        nData.add(new ME_Hover_2Type_Image(Images.time, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("RequiredTechnologyLevel") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + (float)((int)(BuildingsManager.getLibrary_TechLevel(CFG.core.getProv(provinceID).getLvlOfLibrary() + 1) * 100.0f)) / 100.0f, CFG.core.getCiv(civID).getTechLevel() >= BuildingsManager.getLibrary_TechLevel(CFG.core.getProv(provinceID).getLvlOfLibrary() + 1) ? CFG.COLOR_TECHNOLOGY : CFG.COLOR_NEGATIVE_2));
                        nData.add(new ME_Hover_2Type_Image(Images.technology, CFG.PADD, 0));
                        nData.add(new ME_Hover_2Type_Image(CFG.core.getCiv(civID).getTechLevel() >= BuildingsManager.getLibrary_TechLevel(CFG.core.getProv(provinceID).getLvlOfLibrary() + 1) ? Images.iconTrue : Images.iconFalse, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    }
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
            });
            ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setHeightE(buttonH);
            ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(tRow);
            menuElements.add(new Button_BuildForeign(BuildingsManager.getArmoury_Name(CFG.core.getProv(provinceID).getLvlOfArmoury() + 1), Images.bArmoury, BuildingsManager.getArmoury_BuildCost(CFG.core.getProv(provinceID).getLvlOfArmoury() + 1, provinceID), BuildingsManager.getArmoury_BuildMovementCost(CFG.core.getProv(provinceID).getLvlOfArmoury() + 1), tempWidth / 2, tY, tempWidth / 2, true, CFG.core.getProv(provinceID).getLvlOfArmoury() == BuildingsManager.getArmoury_MaxLevel(), CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.ARMOURY), BuildingsManager.getArmoury_TechLevel(CFG.core.getProv(provinceID).getLvlOfArmoury() + 1)){

                @Override
                public void actionElem(int iID) {
                    if (CFG.core.getProv(provinceID).getLvlOfArmoury() == BuildingsManager.getArmoury_MaxLevel()) {
                        CFG.toastM.addM(CFG.lang.get("Built"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    } else if (CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.ARMOURY) > 0) {
                        CFG.toastM.addM(CFG.lang.get("InConstruction"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    } else if (CFG.core.getCiv(civID).getTechLevel() < BuildingsManager.getArmoury_TechLevel(CFG.core.getProv(provinceID).getLvlOfArmoury() + 1)) {
                        CFG.toastM.addM(CFG.lang.get("RequiredTechnologyLevel") + ": " + BuildingsManager.getArmoury_TechLevel(CFG.core.getProv(provinceID).getLvlOfArmoury() + 1), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    } else {
                        Menu_InGame_BuildForeign.this.updateBuild(7);
                    }
                }

                @Override
                public int getCurr() {
                    return build.get(7) != false ? 1 : 0;
                }

                @Override
                public void buildElemHover() {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    if (CFG.core.getProv(provinceID).getLvlOfArmoury() == BuildingsManager.getArmoury_MaxLevel()) {
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get(BuildingsManager.getArmoury_Name(CFG.core.getProv(provinceID).getLvlOfArmoury())) + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.core.getProv(provinceID).getName().length() > 0 ? CFG.core.getProv(provinceID).getName() : CFG.lang.get("Province")));
                        nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getProv(provinceID).getCivId(), CFG.PADD, 0));
                        nData.add(new ME_Hover_2Type_Image_Big(Images.iconTrue, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(" - "));
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("ReducesTheCostOfRecruitmentPerUnitByOneGold"), CFG.COLOR_HOVER_TITLE));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    } else if (CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.ARMOURY) > 0) {
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("InConstruction"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(GameCalendar.getDate_ByTurnID(GameCalendar.TURNID + CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.ARMOURY))));
                        nData.add(new ME_Hover_2Type_Text(" [" + CFG.lang.get("TurnsX", CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.ARMOURY)) + "]", CFG.COLOR_NEUTRAL));
                        nData.add(new ME_Hover_2Type_Image(Images.time, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Space());
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get(BuildingsManager.getArmoury_Name(CFG.core.getProv(provinceID).getLvlOfArmoury() + 1)) + ": ", CFG.COLOR_HOVER_TITLE));
                        nData.add(new ME_Hover_2Type_Text(CFG.core.getProv(provinceID).getName().length() > 0 ? CFG.core.getProv(provinceID).getName() : CFG.lang.get("Province")));
                        nData.add(new ME_Hover_2Type_Flag(CFG.core.getProv(provinceID).getCivId(), CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    } else {
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("BuildArmouryIn") + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.core.getProv(provinceID).getName().length() > 0 ? CFG.core.getProv(provinceID).getName() : CFG.lang.get("Province")));
                        nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getProv(provinceID).getCivId(), CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(" - "));
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("ReducesTheCostOfRecruitmentPerUnitByOneGold"), CFG.COLOR_HOVER_TITLE));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Space());
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Cost") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + BuildingsManager.getArmoury_BuildCost(CFG.core.getProv(provinceID).getLvlOfArmoury() + 1, provinceID), CFG.core.getCiv(civID).getGold() >= (long)BuildingsManager.getArmoury_BuildCost(CFG.core.getProv(provinceID).getLvlOfArmoury() + 1, provinceID) ? CFG.COLOR_GOLD : CFG.COLOR_NEGATIVE_2));
                        nData.add(new ME_Hover_2Type_Image(Images.topGold(), CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("MovementPoints") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + (float)BuildingsManager.getArmoury_BuildMovementCost(CFG.core.getProv(provinceID).getLvlOfArmoury() + 1) / 10.0f, CFG.core.getCiv(civID).getMovemPoints() >= BuildingsManager.getArmoury_BuildMovementCost(CFG.core.getProv(provinceID).getLvlOfArmoury() + 1) ? CFG.COLOR_MOVEMENT : CFG.COLOR_NEGATIVE_2));
                        nData.add(new ME_Hover_2Type_Image(Images.topMovementPoints, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("ConstructionWillTakeXurns", BuildingsManager.getArmoury_Construction(CFG.core.getProv(provinceID).getLvlOfArmoury() + 1))));
                        nData.add(new ME_Hover_2Type_Image(Images.time, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("RequiredTechnologyLevel") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + (float)((int)(BuildingsManager.getArmoury_TechLevel(CFG.core.getProv(provinceID).getLvlOfArmoury() + 1) * 100.0f)) / 100.0f, CFG.core.getCiv(civID).getTechLevel() >= BuildingsManager.getArmoury_TechLevel(CFG.core.getProv(provinceID).getLvlOfArmoury() + 1) ? CFG.COLOR_TECHNOLOGY : CFG.COLOR_NEGATIVE_2));
                        nData.add(new ME_Hover_2Type_Image(Images.technology, CFG.PADD, 0));
                        nData.add(new ME_Hover_2Type_Image(CFG.core.getCiv(civID).getTechLevel() >= BuildingsManager.getArmoury_TechLevel(CFG.core.getProv(provinceID).getLvlOfArmoury() + 1) ? Images.iconTrue : Images.iconFalse, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    }
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
            });
            ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setHeightE(buttonH);
            ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(tRow);
            tRow = (tRow + 1) % 2;
            menuElements.add(new Button_BuildForeign(BuildingsManager.getSupply_Name(CFG.core.getProv(provinceID).getLvlOfSupply() + 1), Images.bSupply, BuildingsManager.getSupply_BuildCost(CFG.core.getProv(provinceID).getLvlOfSupply() + 1, provinceID), BuildingsManager.getSupply_BuildMovementCost(CFG.core.getProv(provinceID).getLvlOfSupply() + 1), 0, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE(), tempWidth, true, CFG.core.getProv(provinceID).getLvlOfSupply() == BuildingsManager.getSupply_MaxLevel(), CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.SUPPLY), BuildingsManager.getSupply_TechLevel(CFG.core.getProv(provinceID).getLvlOfSupply() + 1)){

                @Override
                public void actionElem(int iID) {
                    if (CFG.core.getProv(provinceID).getLvlOfSupply() == BuildingsManager.getSupply_MaxLevel()) {
                        CFG.toastM.addM(CFG.lang.get("Built"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    } else if (CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.SUPPLY) > 0) {
                        CFG.toastM.addM(CFG.lang.get("InConstruction"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    } else if (CFG.core.getCiv(civID).getTechLevel() < BuildingsManager.getSupply_TechLevel(CFG.core.getProv(provinceID).getLvlOfSupply() + 1)) {
                        CFG.toastM.addM(CFG.lang.get("RequiredTechnologyLevel") + ": " + BuildingsManager.getSupply_TechLevel(CFG.core.getProv(provinceID).getLvlOfSupply() + 1), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    } else {
                        Menu_InGame_BuildForeign.this.updateBuild(8);
                    }
                }

                @Override
                public int getCurr() {
                    return build.get(8) != false ? 1 : 0;
                }

                @Override
                public void buildElemHover() {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    if (CFG.core.getProv(provinceID).getLvlOfSupply() == BuildingsManager.getSupply_MaxLevel()) {
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get(BuildingsManager.getSupply_Name(CFG.core.getProv(provinceID).getLvlOfSupply())) + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.core.getProv(provinceID).getName().length() > 0 ? CFG.core.getProv(provinceID).getName() : CFG.lang.get("Province")));
                        nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getProv(provinceID).getCivId(), CFG.PADD, 0));
                        nData.add(new ME_Hover_2Type_Image_Big(Images.iconTrue, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(" - "));
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("MilitaryUpkeep") + ": "));
                        nData.add(new ME_Hover_2Type_Text("-" + (int)(BuildingsManager.getSupply_Bonus(CFG.core.getProv(provinceID).getLvlOfSupply()) * 100.0f) + "%", CFG.COLOR_POSITIVE));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    } else if (CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.SUPPLY) > 0) {
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("InConstruction"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(GameCalendar.getDate_ByTurnID(GameCalendar.TURNID + CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.SUPPLY))));
                        nData.add(new ME_Hover_2Type_Text(" [" + CFG.lang.get("TurnsX", CFG.core.getCiv(civID).isInConstruction(provinceID, ConstructionType.SUPPLY)) + "]", CFG.COLOR_NEUTRAL));
                        nData.add(new ME_Hover_2Type_Image(Images.time, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Space());
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get(BuildingsManager.getSupply_Name(CFG.core.getProv(provinceID).getLvlOfSupply() + 1)) + ": ", CFG.COLOR_HOVER_TITLE));
                        nData.add(new ME_Hover_2Type_Text(CFG.core.getProv(provinceID).getName().length() > 0 ? CFG.core.getProv(provinceID).getName() : CFG.lang.get("Province")));
                        nData.add(new ME_Hover_2Type_Flag(CFG.core.getProv(provinceID).getCivId(), CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    } else {
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("BuildSupplyCampIn") + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.core.getProv(provinceID).getName().length() > 0 ? CFG.core.getProv(provinceID).getName() : CFG.lang.get("Province")));
                        nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getProv(provinceID).getCivId(), CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(" - "));
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("MilitaryUpkeep") + ": "));
                        nData.add(new ME_Hover_2Type_Text("-" + (int)(BuildingsManager.getSupply_Bonus(CFG.core.getProv(provinceID).getLvlOfSupply() + 1) * 100.0f) + "%", CFG.COLOR_POSITIVE));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Space());
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Cost") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + BuildingsManager.getSupply_BuildCost(CFG.core.getProv(provinceID).getLvlOfSupply() + 1, provinceID), CFG.core.getCiv(civID).getGold() >= (long)BuildingsManager.getSupply_BuildCost(CFG.core.getProv(provinceID).getLvlOfSupply() + 1, provinceID) ? CFG.COLOR_GOLD : CFG.COLOR_NEGATIVE_2));
                        nData.add(new ME_Hover_2Type_Image(Images.topGold(), CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("MovementPoints") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + (float)BuildingsManager.getSupply_BuildMovementCost(CFG.core.getProv(provinceID).getLvlOfSupply() + 1) / 10.0f, CFG.core.getCiv(civID).getMovemPoints() >= BuildingsManager.getSupply_BuildMovementCost(CFG.core.getProv(provinceID).getLvlOfSupply() + 1) ? CFG.COLOR_MOVEMENT : CFG.COLOR_NEGATIVE_2));
                        nData.add(new ME_Hover_2Type_Image(Images.topMovementPoints, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("ConstructionWillTakeXurns", BuildingsManager.getSupply_Construction(CFG.core.getProv(provinceID).getLvlOfSupply() + 1))));
                        nData.add(new ME_Hover_2Type_Image(Images.time, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("RequiredTechnologyLevel") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + (float)((int)(BuildingsManager.getSupply_TechLevel(CFG.core.getProv(provinceID).getLvlOfSupply() + 1) * 100.0f)) / 100.0f, CFG.core.getCiv(civID).getTechLevel() >= BuildingsManager.getSupply_TechLevel(CFG.core.getProv(provinceID).getLvlOfSupply() + 1) ? CFG.COLOR_TECHNOLOGY : CFG.COLOR_NEGATIVE_2));
                        nData.add(new ME_Hover_2Type_Image(Images.technology, CFG.PADD, 0));
                        nData.add(new ME_Hover_2Type_Image(CFG.core.getCiv(civID).getTechLevel() >= BuildingsManager.getSupply_TechLevel(CFG.core.getProv(provinceID).getLvlOfSupply() + 1) ? Images.iconTrue : Images.iconFalse, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    }
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
            });
            ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setHeightE(buttonH);
            ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(tRow);
            menuElements.add(new TextBuildTitle(CFG.lang.get("MakingForeignConstruction") + (provinceID >= 0 ? ": " + CFG.core.getProv(provinceID).getProvName() : ""), -1, 0, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE(), CFG.BUTTON_W, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 4){

                @Override
                public Color getColor(boolean isActive) {
                    return isActive ? CFG.COLOR_TEXT_GRAY_NS_HOVER : (this.getIsClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_GRAY_NS : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
                }

                @Override
                public int getWidthE() {
                    return Menu_InGame_BuildForeign.this.getElementW2();
                }
            });
            this.totalReturnButtonID = menuElements.size();
            menuElements.add(new ButtonN_Pop_TextRight(new Color((float)CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getR() / 255.0f, (float)CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getG() / 255.0f, (float)CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getB() / 255.0f, 1.0f), CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getCivName(), CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.lang.get("TotalReturn") + ": ", "0", Images.topGold(), CFG.COLOR_GOLD, 0, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE(), CFG.BUTTON_W, CFG.lang.get("TurnsX", GameValues.gvInvestForeign.BUILD_RETURN_TURNS), Images.time){

                @Override
                public void buildElemHover() {
                }

                @Override
                public int getWidthE() {
                    return Menu_InGame_BuildForeign.this.getElementW2();
                }

                @Override
                public void actionElem(int iID) {
                }
            });
            menuElements.add(new Button_Diplomacy_InvestReturn(CFG.lang.get("EstimatedReturnRate") + ": ", 2, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE(), CFG.BUTTON_W * 2){

                @Override
                public int getWidthE() {
                    return Menu_InGame_BuildForeign.this.getElementW() * 2;
                }
            });
            if (provinceID >= 0) {
                ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr((int)(GameManager.buildForeignEconomy_ReturnRate(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), provinceID) * 10000.0f));
            }
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
            if (CFG.core.getProv(provinceID).isOccupied()) {
                menuElements.add(new Text_Desc(CFG.lang.get("InvestingInAnOccupiedProvinceIsNotPossible"), 2, tY += CFG.PADD, tempWidth - 4){

                    @Override
                    protected Color getColor(boolean isActive) {
                        return this.getIsHovered() || isActive ? CFG.COLOR_NEGATIVE_1 : CFG.COLOR_NEGATIVE_2;
                    }

                    @Override
                    public int getWidthE() {
                        return Menu_InGame_BuildForeign.this.getElementW() * 2;
                    }
                });
                tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD;
            }
        } else {
            menuElements.add(new TextScale(CFG.lang.get("ChooseAProvince"), -1, 0, tY, CFG.BUTTON_W, CFG.BUTTON_H * 3 / 4, 0.75f){

                @Override
                public int getWidthE() {
                    return Menu_InGame_BuildForeign.this.getElementW2();
                }

                @Override
                public void actionElem(int iID) {
                    CFG.toastM.addM(CFG.lang.get("ChooseAProvince"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                }
            });
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        }
        menuElements.add(new Button_InGameAction(CFG.lang.get("Cancel"), -1, 2 + CFG.PADD, tY += CFG.PADD, CFG.BUTTON_W, true){

            @Override
            public int getWidthE() {
                return Menu_InGame_BuildForeign.this.getElementW() - CFG.PADD - CFG.PADD / 2;
            }

            @Override
            public void actionElem(int iID) {
                Menu_InGame_BuildForeign.this.setVisibleM(false);
            }
        });
        menuElements.add(new Button_InGameAction(CFG.lang.get("Confirm"), -1, 2, tY, CFG.BUTTON_W, true){

            @Override
            public int getPosXE() {
                return Menu_InGame_BuildForeign.this.getElementW() + CFG.PADD / 2;
            }

            @Override
            public int getWidthE() {
                return Menu_InGame_BuildForeign.this.getElementW() - CFG.PADD - CFG.PADD / 2;
            }

            @Override
            public void actionElem(int iID) {
                try {
                    if (CFG.core.getProv(provinceID).getCivId() <= 0) {
                        CFG.toastM.addM(CFG.lang.get("Civilization") + ": " + CFG.lang.get("Neutral"), CFG.COLOR_NEGATIVE_1);
                    } else if (CFG.core.getProv(provinceID).isOccupied()) {
                        CFG.toastM.addM(CFG.lang.get("InvestingInAnOccupiedProvinceIsNotPossible"), CFG.COLOR_NEGATIVE_1);
                    } else if (CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).areSanctionsAdded(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.core.getProv(provinceID).getCivId()) || CFG.core.getCiv(CFG.core.getProv(provinceID).getCivId()).areSanctionsAdded(CFG.core.getProv(provinceID).getCivId(), CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())) {
                        CFG.toastM.addM(CFG.lang.get("SanctionsBox1"), CFG.COLOR_NEGATIVE_1);
                    } else {
                        int buildingsNum = 0;
                        for (int a = 0; a < build.size(); ++a) {
                            if (!build.get(a).booleanValue()) continue;
                            ++buildingsNum;
                        }
                        if (buildingsNum == 0) {
                            CFG.toastM.addM(CFG.lang.get("Buildings") + ": " + buildingsNum, CFG.COLOR_NEGATIVE_1);
                        } else {
                            Menu_InGame_BuildForeign.this.setVisibleM(false);
                            GameManager.buildForeignProvince(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), provinceID, build, buildCost);
                            CFG.menus.updateInGameTopAll(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId());
                            CFG.gameAction.updateInGame_ProvinceInfo();
                            CFG.toastM.addM(CFG.lang.get("Ok") + "!", CFG.COLOR_POSITIVE);
                            CFG.toastM.setTimeInView(3500);
                            CFG.menus.rebuildMenu_InGame_Infobox(CFG.lang.get("BuildInForeignProvince"), CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), civID, Images.infoBuild);
                        }
                    }
                }
                catch (Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }

            @Override
            public void buildElemHover() {
                if (provinceID >= 0) {
                    try {
                        ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                        ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("BuildInForeignProvince") + ": "));
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.core.getProv(provinceID).getProvName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getProv(provinceID).getCivId(), CFG.PADD, 0));
                        nData.add(new ME_Hover_2Type_Image_Big(Images.investB1, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Gold") + ": "));
                        nData.add(new ME_Hover_2Type_Text(CFG.getNumberWthSpaces("" + buildCost), CFG.COLOR_GOLD));
                        nData.add(new ME_Hover_2Type_Image(Images.topGold(), CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        int buildingsNum = 0;
                        for (int a = 0; a < build.size(); ++a) {
                            if (!build.get(a).booleanValue()) continue;
                            ++buildingsNum;
                        }
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Buildings") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + buildingsNum, CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nData.add(new ME_Hover_2Type_Image(Images.investB1, CFG.PADD, 0));
                        nData.add(new ME_Hover_2Type_Flag(CFG.core.getProv(provinceID).getCivId(), CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Space());
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("TotalReturn") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + CFG.getNumberWthSpaces("" + GameManager.buildForeignEconomy_Return(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), provinceID, buildCost)), CFG.COLOR_GOLD));
                        nData.add(new ME_Hover_2Type_Image(Images.topGold(), CFG.PADD, 0));
                        nData.add(new ME_Hover_2Type_Flag(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Profit") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + CFG.getNumberWthSpaces("" + (GameManager.buildForeignEconomy_Return(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), provinceID, buildCost) - buildCost)), CFG.COLOR_GOLD));
                        nData.add(new ME_Hover_2Type_Image(Images.topGold(), CFG.PADD, 0));
                        nData.add(new ME_Hover_2Type_Flag(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Duration") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + CFG.lang.get("TurnsX", GameValues.gvInvestForeign.BUILD_RETURN_TURNS), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nData.add(new ME_Hover_2Type_Image(Images.time, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Space());
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_TextDesc(CFG.lang.get("ForeignInvestYourGoldDirectlyDesc")));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Space());
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_TextDesc(CFG.lang.get("InvestingInAnOccupiedProvinceIsNotPossible")));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        this.menuElemHover = new ME_Hover_v2(nElements);
                    }
                    catch (Exception ex) {
                        this.menuElemHover = null;
                    }
                } else {
                    this.menuElemHover = null;
                }
            }

            @Override
            public void drawTextE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                IMGManager.getIMG(Images.investB1).draw(oSB, this.getPosXE() + this.getWidthE() / 2 - (this.getTextWidthU() + IMGManager.getIMG(Images.investB1).getWidth() + CFG.PADD) / 2 + iTranslateX, this.getPosY() + this.getHeightE() / 2 - IMGManager.getIMG(Images.investB1).getHeight() / 2 + iTranslateY);
                Renderer.drawText(oSB, this.fontID, this.getTextE(), this.getPosXE() + this.getWidthE() / 2 - (this.getTextWidthU() + IMGManager.getIMG(Images.investB1).getWidth() + CFG.PADD) / 2 + CFG.PADD + IMGManager.getIMG(Images.investB1).getWidth() + iTranslateX, this.getPosY() + this.getHeightE() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColorE(isActive));
            }

            @Override
            public boolean getIsClickable() {
                return provinceID >= 0 && CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getMovemPoints() >= GameValues.gvInvestForeign.INVEST_ECO_COST_MOVEMENT_POINTS && !CFG.core.getProv(provinceID).isOccupied();
            }
        });
        int tempMenuPosY = IMGManager.getIMG(Images.topBar).getHeight() + CFG.PADD * 2 + CFG.BUTTON_H * 3 / 4;
        this.initMenu(new TitleM_TextSmall(CFG.lang.get("BuildInForeignProvince"), CFG.BUTTON_H * 3 / 4, true, false){

            @Override
            public void drawT(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                IMGManager.getIMG(Images.dialog_title).draw2O(oSB, nPosX - 2 - Core.PADDING + iTranslateX, nPosY - this.getHeightT() - IMGManager.getIMG(Images.dialog_title).getHeight() - Core.PADDING, nWidth + Core.PADDING * 2 + 4 - IMGManager.getIMG(Images.dialog_title).getWidth(), this.getHeightT() + Core.PADDING);
                IMGManager.getIMG(Images.dialog_title).draw2O(oSB, nPosX + nWidth + Core.PADDING + 2 - IMGManager.getIMG(Images.dialog_title).getWidth() + iTranslateX, nPosY - this.getHeightT() - Core.PADDING - IMGManager.getIMG(Images.dialog_title).getHeight(), IMGManager.getIMG(Images.dialog_title).getWidth(), this.getHeightT() + Core.PADDING, true, false);
                oSB.setColor(new Color(0.0f, 0.5019608f, 0.5019608f, 0.165f));
                IMGManager.getIMG(Images.line32Off1).drawO(oSB, nPosX + iTranslateX, nPosY - this.getHeightT() + 2 - IMGManager.getIMG(Images.line32Off1).getHeight(), nWidth, this.getHeightT() - 2, false, true);
                oSB.setColor(new Color(0.0f, 0.5019608f, 0.5019608f, 0.375f));
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
                CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getFlagC().draw(oSB, Menu_InGame_BuildForeign.this.getPosX() + CFG.PADD * 2 + iTranslateX, Menu_InGame_BuildForeign.this.getPosY() - this.getHeightT() / 2 - CFG.CIV_FLAG_HEIGHT / 2, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                IMGManager.getIMG(Images.flagRectSmall).drawO(oSB, Menu_InGame_BuildForeign.this.getPosX() + CFG.PADD * 2 + iTranslateX, Menu_InGame_BuildForeign.this.getPosY() - this.getHeightT() / 2 - CFG.CIV_FLAG_HEIGHT / 2);
                CFG.core.getCiv(civID).getFlagC().draw(oSB, Menu_InGame_BuildForeign.this.getPosX() + CFG.PADD * 2 + CFG.CIV_FLAG_WIDTH + 2 + iTranslateX, Menu_InGame_BuildForeign.this.getPosY() - this.getHeightT() / 2 - CFG.CIV_FLAG_HEIGHT / 2, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                IMGManager.getIMG(Images.flagRectSmall).drawO(oSB, Menu_InGame_BuildForeign.this.getPosX() + CFG.PADD * 2 + CFG.CIV_FLAG_WIDTH + 2 + iTranslateX, Menu_InGame_BuildForeign.this.getPosY() - this.getHeightT() / 2 - CFG.CIV_FLAG_HEIGHT / 2);
                IMGManager.getIMG(Images.investB1).drawO(oSB, nPosX + (nWidth - this.getTextWidth()) / 2 - CFG.PADD - IMGManager.getIMG(Images.investB1).getWidth() + iTranslateX, 2 + nPosY - this.getHeightT() + this.getHeightT() / 2 - IMGManager.getIMG(Images.investB1).getHeight() / 2);
                Renderer.drawText(oSB, CFG.FONT_BOLD_SMALL, this.getText(), nPosX + (nWidth - this.getTextWidth()) / 2 + iTranslateX, 2 + nPosY - this.getHeightT() + (this.getHeightT() - this.getTextHeight()) / 2, Color.WHITE);
            }
        }, CFG.GAMEWIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD + tempMenuPosY > CFG.GAMEHEIGHT - CFG.BUTTON_H - CFG.PADD * 2 ? Math.max(CFG.GAMEHEIGHT - CFG.BUTTON_H - CFG.PADD * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 2) * 6) : ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, menuElements, true, true);
        this.updateLang();
        Menu_InGameOfferAlliance.lTime = System.currentTimeMillis();
    }

    @Override
    public void updateLang() {
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (Menu_InGameOfferAlliance.lTime + (long)Menu_InGame_Message_Alliance.ANIMATION_TIME >= System.currentTimeMillis()) {
            Rectangle clipBounds = new Rectangle(this.getPosX() - 2 - Core.PADDING, CFG.GAMEHEIGHT - this.getPosY(), this.getWidthM() + 4 + Core.PADDING * 2, -((int)((float)(this.getHeightM() + CFG.PADD) * ((float)(System.currentTimeMillis() - Menu_InGameOfferAlliance.lTime) / (float)Menu_InGame_Message_Alliance.ANIMATION_TIME))));
            oSB.flush();
            ScissorStack.pushScissors(clipBounds);
            oSB.setColor(Color.WHITE);
            IMGManager.getIMG(Images.gameTopEdge).draw2O(oSB, this.getPosX() - 2 - Core.PADDING + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gameTopEdge).getHeight() + iTranslateY, this.getWidthM() + Core.PADDING * 2 - IMGManager.getIMG(Images.gameTopEdge).getWidth() + 4, this.getHeightM() + CFG.PADD + Core.PADDING, false, true);
            IMGManager.getIMG(Images.gameTopEdge).draw2O(oSB, this.getPosX() + 2 + Core.PADDING + this.getWidthM() - IMGManager.getIMG(Images.gameTopEdge).getWidth() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gameTopEdge).getHeight() + iTranslateY, IMGManager.getIMG(Images.gameTopEdge).getWidth(), this.getHeightM() + CFG.PADD + Core.PADDING, true, true);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
            IMGManager.getIMG(Images.gradient).drawO(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gradient).getHeight() + iTranslateY, this.getWidthM() - 4, this.getHeightM() / 4);
            IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.pix255).getHeight() + iTranslateY, this.getWidthM() - 4, 1);
            oSB.setColor(Color.WHITE);
            this.drawMenuM(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
            oSB.setColor(Color.WHITE);
            CFG.setRenderO(true);
            this.endClipM(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        } else {
            oSB.setColor(Color.WHITE);
            IMGManager.getIMG(Images.gameTopEdge).draw2O(oSB, this.getPosX() - 2 - Core.PADDING + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gameTopEdge).getHeight() + iTranslateY, this.getWidthM() + Core.PADDING * 2 - IMGManager.getIMG(Images.gameTopEdge).getWidth() + 4, this.getHeightM() + CFG.PADD + Core.PADDING, false, true);
            IMGManager.getIMG(Images.gameTopEdge).draw2O(oSB, this.getPosX() + 2 + Core.PADDING + this.getWidthM() - IMGManager.getIMG(Images.gameTopEdge).getWidth() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gameTopEdge).getHeight() + iTranslateY, IMGManager.getIMG(Images.gameTopEdge).getWidth(), this.getHeightM() + CFG.PADD + Core.PADDING, true, true);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
            IMGManager.getIMG(Images.gradient).drawO(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gradient).getHeight() + iTranslateY, this.getWidthM() - 4, this.getHeightM() / 4);
            IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.pix255).getHeight() + iTranslateY, this.getWidthM() - 4, 1);
            oSB.setColor(Color.WHITE);
            this.beginClipM(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
            this.drawMenuM(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
            oSB.setColor(Color.WHITE);
            this.endClipM(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
        try {
            if (CFG.core.getActiveProvID() >= 0 && CFG.core.getActiveProvID() != provinceID && CFG.core.getProv(CFG.core.getActiveProvID()).getCivId() == civID) {
                provinceID = CFG.core.getActiveProvID();
                Menu_InGame_BuildForeign.buildBuildList();
                CFG.menus.rebuildInGame_BuildForeign(civID, provinceID);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Override
    public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    public final int getW() {
        return this.getWidthM() - 4;
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
        }
    }
}
