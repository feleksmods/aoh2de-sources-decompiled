package age.of.civilizations2.jakowski.lukasz.Menus.Buildings;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.Button.Build.Button_Build3;
import age.of.civilizations2.jakowski.lukasz.Button.Diplomacy.Action.Button_DiplomacyAction;
import age.of.civilizations2.jakowski.lukasz.Button.MenuElemUI;
import age.of.civilizations2.jakowski.lukasz.Button2.Button_NS_Population_Buildings;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Core.Core;
import age.of.civilizations2.jakowski.lukasz.GameValues.GameValues;
import age.of.civilizations2.jakowski.lukasz.IMGManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MapA.BuildingsManager;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.MEHover_2E;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Image_Big;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Text_Big;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.Menus.Civilization.Menu_InGame_Civ_Decisions;
import age.of.civilizations2.jakowski.lukasz.Province;
import age.of.civilizations2.jakowski.lukasz.Renderer;
import age.of.civilizations2.jakowski.lukasz.TextB.Texts.TextBuildTitle;
import age.of.civilizations2.jakowski.lukasz.TextB.Texts.TextScale;
import age.of.civilizations2.jakowski.lukasz.Title.TitleM_TextSmall;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_View_BuildingsTop
extends Menu {
    public static long lTime = 0L;
    public static boolean hideAnimation = true;

    public Menu_InGame_View_BuildingsTop() {
        int a;
        int aSize;
        int bestID;
        int i;
        int tempW = CFG.CIV_INFO_MENU_WIDTH + CFG.BUTTON_W * 3 / 4;
        int tY = 0;
        ArrayList<MenuElemUI> menuElements = new ArrayList<MenuElemUI>();
        ArrayList<BData> castle = new ArrayList<BData>();
        ArrayList<BData> tower = new ArrayList<BData>();
        ArrayList<BData> port = new ArrayList<BData>();
        ArrayList<BData> farm = new ArrayList<BData>();
        ArrayList<BData> workshop = new ArrayList<BData>();
        ArrayList<BData> market = new ArrayList<BData>();
        ArrayList<BData> library = new ArrayList<BData>();
        ArrayList<BData> armoury = new ArrayList<BData>();
        ArrayList<BData> supply = new ArrayList<BData>();
        int castleNum = 0;
        int towerNum = 0;
        int portNum = 0;
        int farmNum = 0;
        int workshopNum = 0;
        int marketNum = 0;
        int libraryNum = 0;
        int armouryNum = 0;
        int supplyNum = 0;
        for (int i2 = 0; i2 < CFG.core.getProvinSize(); ++i2) {
            boolean added;
            Province prov = CFG.core.getProv(i2);
            if (prov.getCivId() <= 0) continue;
            if (prov.getLvlOfFort() > 0) {
                added = false;
                for (BData item : castle) {
                    if (item.getID() != prov.getCivId()) continue;
                    item.setNum(item.getNum() + prov.getLvlOfFort());
                    added = true;
                    break;
                }
                if (!added) {
                    castle.add(new BData(prov.getCivId(), prov.getLvlOfFort()));
                }
                castleNum += prov.getLvlOfFort();
            }
            if (prov.getLvlOfWatchTower() > 0) {
                added = false;
                for (BData item : tower) {
                    if (item.getID() != prov.getCivId()) continue;
                    item.setNum(item.getNum() + prov.getLvlOfWatchTower());
                    added = true;
                    break;
                }
                if (!added) {
                    tower.add(new BData(prov.getCivId(), prov.getLvlOfWatchTower()));
                }
                towerNum += prov.getLvlOfWatchTower();
            }
            if (prov.getLvlOfPort() > 0) {
                added = false;
                for (BData item : port) {
                    if (item.getID() != prov.getCivId()) continue;
                    item.setNum(item.getNum() + prov.getLvlOfPort());
                    added = true;
                    break;
                }
                if (!added) {
                    port.add(new BData(prov.getCivId(), prov.getLvlOfPort()));
                }
                portNum += prov.getLvlOfPort();
            }
            if (prov.getLvlOfFarm() > 0) {
                added = false;
                for (BData item : farm) {
                    if (item.getID() != prov.getCivId()) continue;
                    item.setNum(item.getNum() + prov.getLvlOfFarm());
                    added = true;
                    break;
                }
                if (!added) {
                    farm.add(new BData(prov.getCivId(), prov.getLvlOfFarm()));
                }
                farmNum += prov.getLvlOfFarm();
            }
            if (prov.getLvlOfWorkshop() > 0) {
                added = false;
                for (BData item : workshop) {
                    if (item.getID() != prov.getCivId()) continue;
                    item.setNum(item.getNum() + prov.getLvlOfWorkshop());
                    added = true;
                    break;
                }
                if (!added) {
                    workshop.add(new BData(prov.getCivId(), prov.getLvlOfWorkshop()));
                }
                workshopNum += prov.getLvlOfWorkshop();
            }
            if (prov.getLvlOfMarket() > 0) {
                added = false;
                for (BData item : market) {
                    if (item.getID() != prov.getCivId()) continue;
                    item.setNum(item.getNum() + prov.getLvlOfMarket());
                    added = true;
                    break;
                }
                if (!added) {
                    market.add(new BData(prov.getCivId(), prov.getLvlOfMarket()));
                }
                marketNum += prov.getLvlOfMarket();
            }
            if (prov.getLvlOfLibrary() > 0) {
                added = false;
                for (BData item : library) {
                    if (item.getID() != prov.getCivId()) continue;
                    item.setNum(item.getNum() + prov.getLvlOfLibrary());
                    added = true;
                    break;
                }
                if (!added) {
                    library.add(new BData(prov.getCivId(), prov.getLvlOfLibrary()));
                }
                libraryNum += prov.getLvlOfLibrary();
            }
            if (prov.getLvlOfArmoury() > 0) {
                added = false;
                for (BData item : armoury) {
                    if (item.getID() != prov.getCivId()) continue;
                    item.setNum(item.getNum() + prov.getLvlOfArmoury());
                    added = true;
                    break;
                }
                if (!added) {
                    armoury.add(new BData(prov.getCivId(), prov.getLvlOfArmoury()));
                }
                armouryNum += prov.getLvlOfArmoury();
            }
            if (prov.getLvlOfSupply() <= 0) continue;
            added = false;
            for (BData item : supply) {
                if (item.getID() != prov.getCivId()) continue;
                item.setNum(item.getNum() + prov.getLvlOfSupply());
                added = true;
                break;
            }
            if (!added) {
                supply.add(new BData(prov.getCivId(), prov.getLvlOfSupply()));
            }
            supplyNum += prov.getLvlOfSupply();
        }
        int totalNum = castleNum + towerNum + portNum + farmNum + workshopNum + marketNum + libraryNum + armouryNum + supplyNum;
        menuElements.add(new Button_DiplomacyAction(Images.buildAll, CFG.lang.get("Buildings") + ": " + CFG.getNumberWthSpaces("" + totalNum), 0, 0, tY, tempW, Menu_InGame_Civ_Decisions.getButtonH(), true){

            @Override
            public void actionElem(int iID) {
                CFG.menus.setVisible_InGame_View_Buildings(true);
            }

            @Override
            public void buildElemHover() {
                ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("Buildings") + ": "));
                nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("Civilization"), CFG.COLOR_HOVER_TITLE));
                nData.add(new ME_Hover_2Type_Image_Big(Images.buildAll, CFG.PADD, 0));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                this.menuElemHover = new ME_Hover_v2(nElements);
            }
        });
        ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(0);
        int buttonH = Math.max(CFG.BUTTON_H * 3 / 4, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 4);
        int elemBefore = 0;
        int civID = 0;
        boolean metCiv = true;
        menuElements.add(new TextBuildTitle(CFG.lang.get(BuildingsManager.getFort_Name(1)) + ": " + CFG.lang.get("TopCivilizations"), -1, 0, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE(), tempW, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 4){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_GRAY_NS_HOVER : (this.getIsClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_GRAY_NS : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        menuElements.add(new Button_Build3(BuildingsManager.getFort_Name(1), Images.bFort, 0, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE(), tempW, castleNum){

            @Override
            public void actionElem(int iID) {
            }

            @Override
            public void buildElemHover() {
                try {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.map.getMapName_Just(CFG.map.getActiveMapIDN()) + ": "));
                    nData.add(new ME_Hover_2Type_Text_Big(this.getTextE(), CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(this.iImageID, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Space());
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("BuildingsConstructed") + ": "));
                    nData.add(new ME_Hover_2Type_Text_Big(this.sPop, CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(Images.buildAll, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    // empty catch block
                }
            }
        });
        ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setHeightE(buttonH);
        ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(1);
        elemBefore = menuElements.size();
        tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        for (i = 0; i < GameValues.gvInGame.BUILDINGS_VIEW_TOP_CIVS_LIMIT && !castle.isEmpty(); ++i) {
            bestID = 0;
            aSize = castle.size();
            for (a = 1; a < aSize; ++a) {
                if (((BData)castle.get(bestID)).num >= ((BData)castle.get(a)).num) continue;
                bestID = a;
            }
            civID = ((BData)castle.get(bestID)).id;
            menuElements.add(new Button_NS_Population_Buildings(new Color((float)CFG.core.getCiv(civID).getR() / 255.0f, (float)CFG.core.getCiv(civID).getG() / 255.0f, (float)CFG.core.getCiv(civID).getB() / 255.0f, 1.0f), i + 1 + ". " + (metCiv ? CFG.core.getCiv(civID).getCivName() : CFG.lang.get("Undiscovered")), metCiv ? civID : -1, "" + CFG.getPrecision2((float)((BData)castle.get(bestID)).num / (float)castleNum * 100.0f, 100) + "%", CFG.getNumberWthSpaces("" + ((BData)castle.get(bestID)).num), Images.bFort, CFG.COLOR_TEXT_NUM_OF_PROVINCES, 0, tY, tempW, CFG.core.getCiv(civID).getNumOfProvs()));
            ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(i % 2);
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
            castle.remove(bestID);
        }
        if (elemBefore == menuElements.size()) {
            menuElements.add(new TextScale(CFG.lang.get("NoData"), -1, 0, tY, tempW, CFG.BUTTON_H * 3 / 4, 0.75f));
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        }
        menuElements.add(new TextBuildTitle(CFG.lang.get(BuildingsManager.getTower_Name(1)) + ": " + CFG.lang.get("TopCivilizations"), -1, 0, tY, tempW, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 4){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_GRAY_NS_HOVER : (this.getIsClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_GRAY_NS : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        menuElements.add(new Button_Build3(BuildingsManager.getTower_Name(1), Images.bTower, 0, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE(), tempW, towerNum){

            @Override
            public void actionElem(int iID) {
            }

            @Override
            public void buildElemHover() {
                try {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.map.getMapName_Just(CFG.map.getActiveMapIDN()) + ": "));
                    nData.add(new ME_Hover_2Type_Text_Big(this.getTextE(), CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(this.iImageID, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Space());
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("BuildingsConstructed") + ": "));
                    nData.add(new ME_Hover_2Type_Text_Big(this.sPop, CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(Images.buildAll, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    // empty catch block
                }
            }
        });
        ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setHeightE(buttonH);
        ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(1);
        elemBefore = menuElements.size();
        tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        for (i = 0; i < GameValues.gvInGame.BUILDINGS_VIEW_TOP_CIVS_LIMIT && !tower.isEmpty(); ++i) {
            bestID = 0;
            aSize = tower.size();
            for (a = 1; a < aSize; ++a) {
                if (((BData)tower.get(bestID)).num >= ((BData)tower.get(a)).num) continue;
                bestID = a;
            }
            civID = ((BData)tower.get(bestID)).id;
            menuElements.add(new Button_NS_Population_Buildings(new Color((float)CFG.core.getCiv(civID).getR() / 255.0f, (float)CFG.core.getCiv(civID).getG() / 255.0f, (float)CFG.core.getCiv(civID).getB() / 255.0f, 1.0f), i + 1 + ". " + (metCiv ? CFG.core.getCiv(civID).getCivName() : CFG.lang.get("Undiscovered")), metCiv ? civID : -1, "" + CFG.getPrecision2((float)((BData)tower.get(bestID)).num / (float)towerNum * 100.0f, 100) + "%", CFG.getNumberWthSpaces("" + ((BData)castle.get(bestID)).num), Images.bTower, CFG.COLOR_TEXT_NUM_OF_PROVINCES, 0, tY, tempW, CFG.core.getCiv(civID).getNumOfProvs()));
            ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(i % 2);
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
            tower.remove(bestID);
        }
        if (elemBefore == menuElements.size()) {
            menuElements.add(new TextScale(CFG.lang.get("NoData"), -1, 0, tY, tempW, CFG.BUTTON_H * 3 / 4, 0.75f));
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        }
        menuElements.add(new TextBuildTitle(CFG.lang.get(BuildingsManager.getPort_Name(1)) + ": " + CFG.lang.get("TopCivilizations"), -1, 0, tY, tempW, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 4){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_GRAY_NS_HOVER : (this.getIsClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_GRAY_NS : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        menuElements.add(new Button_Build3(BuildingsManager.getPort_Name(1), Images.bPort, 0, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE(), tempW, portNum){

            @Override
            public void actionElem(int iID) {
            }

            @Override
            public void buildElemHover() {
                try {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.map.getMapName_Just(CFG.map.getActiveMapIDN()) + ": "));
                    nData.add(new ME_Hover_2Type_Text_Big(this.getTextE(), CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(this.iImageID, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Space());
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("BuildingsConstructed") + ": "));
                    nData.add(new ME_Hover_2Type_Text_Big(this.sPop, CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(Images.buildAll, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    // empty catch block
                }
            }
        });
        ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setHeightE(buttonH);
        ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(1);
        elemBefore = menuElements.size();
        tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        for (i = 0; i < GameValues.gvInGame.BUILDINGS_VIEW_TOP_CIVS_LIMIT && !port.isEmpty(); ++i) {
            bestID = 0;
            aSize = port.size();
            for (a = 1; a < aSize; ++a) {
                if (((BData)port.get(bestID)).num >= ((BData)port.get(a)).num) continue;
                bestID = a;
            }
            civID = ((BData)port.get(bestID)).id;
            menuElements.add(new Button_NS_Population_Buildings(new Color((float)CFG.core.getCiv(civID).getR() / 255.0f, (float)CFG.core.getCiv(civID).getG() / 255.0f, (float)CFG.core.getCiv(civID).getB() / 255.0f, 1.0f), i + 1 + ". " + (metCiv ? CFG.core.getCiv(civID).getCivName() : CFG.lang.get("Undiscovered")), metCiv ? civID : -1, "" + CFG.getPrecision2((float)((BData)port.get(bestID)).num / (float)portNum * 100.0f, 100) + "%", CFG.getNumberWthSpaces("" + ((BData)port.get(bestID)).num), Images.bPort, CFG.COLOR_TEXT_NUM_OF_PROVINCES, 0, tY, tempW, CFG.core.getCiv(civID).getNumOfProvs()));
            ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(i % 2);
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
            port.remove(bestID);
        }
        if (elemBefore == menuElements.size()) {
            menuElements.add(new TextScale(CFG.lang.get("NoData"), -1, 0, tY, tempW, CFG.BUTTON_H * 3 / 4, 0.75f));
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        }
        menuElements.add(new TextBuildTitle(CFG.lang.get(BuildingsManager.getFarm_Name(1)) + ": " + CFG.lang.get("TopCivilizations"), -1, 0, tY, tempW, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 4){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_GRAY_NS_HOVER : (this.getIsClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_GRAY_NS : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        menuElements.add(new Button_Build3(BuildingsManager.getFarm_Name(1), Images.bFarm, 0, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE(), tempW, farmNum){

            @Override
            public void actionElem(int iID) {
            }

            @Override
            public void buildElemHover() {
                try {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.map.getMapName_Just(CFG.map.getActiveMapIDN()) + ": "));
                    nData.add(new ME_Hover_2Type_Text_Big(this.getTextE(), CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(this.iImageID, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Space());
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("BuildingsConstructed") + ": "));
                    nData.add(new ME_Hover_2Type_Text_Big(this.sPop, CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(Images.buildAll, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    // empty catch block
                }
            }
        });
        ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setHeightE(buttonH);
        ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(1);
        elemBefore = menuElements.size();
        tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        for (i = 0; i < GameValues.gvInGame.BUILDINGS_VIEW_TOP_CIVS_LIMIT && !farm.isEmpty(); ++i) {
            bestID = 0;
            aSize = farm.size();
            for (a = 1; a < aSize; ++a) {
                if (((BData)farm.get(bestID)).num >= ((BData)farm.get(a)).num) continue;
                bestID = a;
            }
            civID = ((BData)farm.get(bestID)).id;
            menuElements.add(new Button_NS_Population_Buildings(new Color((float)CFG.core.getCiv(civID).getR() / 255.0f, (float)CFG.core.getCiv(civID).getG() / 255.0f, (float)CFG.core.getCiv(civID).getB() / 255.0f, 1.0f), i + 1 + ". " + (metCiv ? CFG.core.getCiv(civID).getCivName() : CFG.lang.get("Undiscovered")), metCiv ? civID : -1, "" + CFG.getPrecision2((float)((BData)farm.get(bestID)).num / (float)farmNum * 100.0f, 100) + "%", CFG.getNumberWthSpaces("" + ((BData)farm.get(bestID)).num), Images.bFarm, CFG.COLOR_TEXT_NUM_OF_PROVINCES, 0, tY, tempW, CFG.core.getCiv(civID).getNumOfProvs()));
            ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(i % 2);
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
            farm.remove(bestID);
        }
        if (elemBefore == menuElements.size()) {
            menuElements.add(new TextScale(CFG.lang.get("NoData"), -1, 0, tY, tempW, CFG.BUTTON_H * 3 / 4, 0.75f));
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        }
        menuElements.add(new TextBuildTitle(CFG.lang.get(BuildingsManager.getWorkshop_Name(1)) + ": " + CFG.lang.get("TopCivilizations"), -1, 0, tY, tempW, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 4){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_GRAY_NS_HOVER : (this.getIsClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_GRAY_NS : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        menuElements.add(new Button_Build3(BuildingsManager.getWorkshop_Name(1), Images.bWorkshop, 0, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE(), tempW, workshopNum){

            @Override
            public void actionElem(int iID) {
            }

            @Override
            public void buildElemHover() {
                try {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.map.getMapName_Just(CFG.map.getActiveMapIDN()) + ": "));
                    nData.add(new ME_Hover_2Type_Text_Big(this.getTextE(), CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(this.iImageID, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Space());
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("BuildingsConstructed") + ": "));
                    nData.add(new ME_Hover_2Type_Text_Big(this.sPop, CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(Images.buildAll, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    // empty catch block
                }
            }
        });
        ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setHeightE(buttonH);
        ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(1);
        elemBefore = menuElements.size();
        tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        for (i = 0; i < GameValues.gvInGame.BUILDINGS_VIEW_TOP_CIVS_LIMIT && !workshop.isEmpty(); ++i) {
            bestID = 0;
            aSize = workshop.size();
            for (a = 1; a < aSize; ++a) {
                if (((BData)workshop.get(bestID)).num >= ((BData)workshop.get(a)).num) continue;
                bestID = a;
            }
            civID = ((BData)workshop.get(bestID)).id;
            menuElements.add(new Button_NS_Population_Buildings(new Color((float)CFG.core.getCiv(civID).getR() / 255.0f, (float)CFG.core.getCiv(civID).getG() / 255.0f, (float)CFG.core.getCiv(civID).getB() / 255.0f, 1.0f), i + 1 + ". " + (metCiv ? CFG.core.getCiv(civID).getCivName() : CFG.lang.get("Undiscovered")), metCiv ? civID : -1, "" + CFG.getPrecision2((float)((BData)workshop.get(bestID)).num / (float)workshopNum * 100.0f, 100) + "%", CFG.getNumberWthSpaces("" + ((BData)workshop.get(bestID)).num), Images.bWorkshop, CFG.COLOR_TEXT_NUM_OF_PROVINCES, 0, tY, tempW, CFG.core.getCiv(civID).getNumOfProvs()));
            ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(i % 2);
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
            workshop.remove(bestID);
        }
        if (elemBefore == menuElements.size()) {
            menuElements.add(new TextScale(CFG.lang.get("NoData"), -1, 0, tY, tempW, CFG.BUTTON_H * 3 / 4, 0.75f));
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        }
        menuElements.add(new TextBuildTitle(CFG.lang.get(BuildingsManager.getMarket_Name(1)) + ": " + CFG.lang.get("TopCivilizations"), -1, 0, tY, tempW, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 4){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_GRAY_NS_HOVER : (this.getIsClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_GRAY_NS : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        menuElements.add(new Button_Build3(BuildingsManager.getMarket_Name(1), Images.bMarket, 0, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE(), tempW, marketNum){

            @Override
            public void actionElem(int iID) {
            }

            @Override
            public void buildElemHover() {
                try {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.map.getMapName_Just(CFG.map.getActiveMapIDN()) + ": "));
                    nData.add(new ME_Hover_2Type_Text_Big(this.getTextE(), CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(this.iImageID, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Space());
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("BuildingsConstructed") + ": "));
                    nData.add(new ME_Hover_2Type_Text_Big(this.sPop, CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(Images.buildAll, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    // empty catch block
                }
            }
        });
        ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setHeightE(buttonH);
        ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(1);
        elemBefore = menuElements.size();
        tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        for (i = 0; i < GameValues.gvInGame.BUILDINGS_VIEW_TOP_CIVS_LIMIT && !market.isEmpty(); ++i) {
            bestID = 0;
            aSize = market.size();
            for (a = 1; a < aSize; ++a) {
                if (((BData)market.get(bestID)).num >= ((BData)market.get(a)).num) continue;
                bestID = a;
            }
            civID = ((BData)market.get(bestID)).id;
            menuElements.add(new Button_NS_Population_Buildings(new Color((float)CFG.core.getCiv(civID).getR() / 255.0f, (float)CFG.core.getCiv(civID).getG() / 255.0f, (float)CFG.core.getCiv(civID).getB() / 255.0f, 1.0f), i + 1 + ". " + (metCiv ? CFG.core.getCiv(civID).getCivName() : CFG.lang.get("Undiscovered")), metCiv ? civID : -1, "" + CFG.getPrecision2((float)((BData)market.get(bestID)).num / (float)marketNum * 100.0f, 100) + "%", CFG.getNumberWthSpaces("" + ((BData)market.get(bestID)).num), Images.bMarket, CFG.COLOR_TEXT_NUM_OF_PROVINCES, 0, tY, tempW, CFG.core.getCiv(civID).getNumOfProvs()));
            ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(i % 2);
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
            market.remove(bestID);
        }
        if (elemBefore == menuElements.size()) {
            menuElements.add(new TextScale(CFG.lang.get("NoData"), -1, 0, tY, tempW, CFG.BUTTON_H * 3 / 4, 0.75f));
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        }
        menuElements.add(new TextBuildTitle(CFG.lang.get(BuildingsManager.getLibrary_Name(1)) + ": " + CFG.lang.get("TopCivilizations"), -1, 0, tY, tempW, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 4){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_GRAY_NS_HOVER : (this.getIsClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_GRAY_NS : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        menuElements.add(new Button_Build3(BuildingsManager.getLibrary_Name(1), Images.bLibrary, 0, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE(), tempW, libraryNum){

            @Override
            public void actionElem(int iID) {
            }

            @Override
            public void buildElemHover() {
                try {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.map.getMapName_Just(CFG.map.getActiveMapIDN()) + ": "));
                    nData.add(new ME_Hover_2Type_Text_Big(this.getTextE(), CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(this.iImageID, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Space());
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("BuildingsConstructed") + ": "));
                    nData.add(new ME_Hover_2Type_Text_Big(this.sPop, CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(Images.buildAll, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    // empty catch block
                }
            }
        });
        ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setHeightE(buttonH);
        ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(1);
        elemBefore = menuElements.size();
        tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        for (i = 0; i < GameValues.gvInGame.BUILDINGS_VIEW_TOP_CIVS_LIMIT && !library.isEmpty(); ++i) {
            bestID = 0;
            aSize = library.size();
            for (a = 1; a < aSize; ++a) {
                if (((BData)library.get(bestID)).num >= ((BData)library.get(a)).num) continue;
                bestID = a;
            }
            civID = ((BData)library.get(bestID)).id;
            menuElements.add(new Button_NS_Population_Buildings(new Color((float)CFG.core.getCiv(civID).getR() / 255.0f, (float)CFG.core.getCiv(civID).getG() / 255.0f, (float)CFG.core.getCiv(civID).getB() / 255.0f, 1.0f), i + 1 + ". " + (metCiv ? CFG.core.getCiv(civID).getCivName() : CFG.lang.get("Undiscovered")), metCiv ? civID : -1, "" + CFG.getPrecision2((float)((BData)library.get(bestID)).num / (float)libraryNum * 100.0f, 100) + "%", CFG.getNumberWthSpaces("" + ((BData)library.get(bestID)).num), Images.bLibrary, CFG.COLOR_TEXT_NUM_OF_PROVINCES, 0, tY, tempW, CFG.core.getCiv(civID).getNumOfProvs()));
            ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(i % 2);
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
            library.remove(bestID);
        }
        if (elemBefore == menuElements.size()) {
            menuElements.add(new TextScale(CFG.lang.get("NoData"), -1, 0, tY, tempW, CFG.BUTTON_H * 3 / 4, 0.75f));
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        }
        menuElements.add(new TextBuildTitle(CFG.lang.get(BuildingsManager.getArmoury_Name(1)) + ": " + CFG.lang.get("TopCivilizations"), -1, 0, tY, tempW, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 4){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_GRAY_NS_HOVER : (this.getIsClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_GRAY_NS : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        menuElements.add(new Button_Build3(BuildingsManager.getArmoury_Name(1), Images.bArmoury, 0, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE(), tempW, armouryNum){

            @Override
            public void actionElem(int iID) {
            }

            @Override
            public void buildElemHover() {
                try {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.map.getMapName_Just(CFG.map.getActiveMapIDN()) + ": "));
                    nData.add(new ME_Hover_2Type_Text_Big(this.getTextE(), CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(this.iImageID, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Space());
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("BuildingsConstructed") + ": "));
                    nData.add(new ME_Hover_2Type_Text_Big(this.sPop, CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(Images.buildAll, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    // empty catch block
                }
            }
        });
        ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setHeightE(buttonH);
        ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(1);
        elemBefore = menuElements.size();
        tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        for (i = 0; i < GameValues.gvInGame.BUILDINGS_VIEW_TOP_CIVS_LIMIT && !armoury.isEmpty(); ++i) {
            bestID = 0;
            aSize = armoury.size();
            for (a = 1; a < aSize; ++a) {
                if (((BData)armoury.get(bestID)).num >= ((BData)armoury.get(a)).num) continue;
                bestID = a;
            }
            civID = ((BData)armoury.get(bestID)).id;
            menuElements.add(new Button_NS_Population_Buildings(new Color((float)CFG.core.getCiv(civID).getR() / 255.0f, (float)CFG.core.getCiv(civID).getG() / 255.0f, (float)CFG.core.getCiv(civID).getB() / 255.0f, 1.0f), i + 1 + ". " + (metCiv ? CFG.core.getCiv(civID).getCivName() : CFG.lang.get("Undiscovered")), metCiv ? civID : -1, "" + CFG.getPrecision2((float)((BData)armoury.get(bestID)).num / (float)armouryNum * 100.0f, 100) + "%", CFG.getNumberWthSpaces("" + ((BData)armoury.get(bestID)).num), Images.bArmoury, CFG.COLOR_TEXT_NUM_OF_PROVINCES, 0, tY, tempW, CFG.core.getCiv(civID).getNumOfProvs()));
            ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(i % 2);
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
            armoury.remove(bestID);
        }
        if (elemBefore == menuElements.size()) {
            menuElements.add(new TextScale(CFG.lang.get("NoData"), -1, 0, tY, tempW, CFG.BUTTON_H * 3 / 4, 0.75f));
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        }
        menuElements.add(new TextBuildTitle(CFG.lang.get(BuildingsManager.getSupply_Name(1)) + ": " + CFG.lang.get("TopCivilizations"), -1, 0, tY, tempW, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 4){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_GRAY_NS_HOVER : (this.getIsClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_GRAY_NS : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        menuElements.add(new Button_Build3(BuildingsManager.getSupply_Name(1), Images.bSupply, 0, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE(), tempW, supplyNum){

            @Override
            public void actionElem(int iID) {
            }

            @Override
            public void buildElemHover() {
                try {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.map.getMapName_Just(CFG.map.getActiveMapIDN()) + ": "));
                    nData.add(new ME_Hover_2Type_Text_Big(this.getTextE(), CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(this.iImageID, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Space());
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("BuildingsConstructed") + ": "));
                    nData.add(new ME_Hover_2Type_Text_Big(this.sPop, CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(Images.buildAll, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    // empty catch block
                }
            }
        });
        ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setHeightE(buttonH);
        ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(1);
        elemBefore = menuElements.size();
        tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        for (i = 0; i < GameValues.gvInGame.BUILDINGS_VIEW_TOP_CIVS_LIMIT && !supply.isEmpty(); ++i) {
            bestID = 0;
            aSize = supply.size();
            for (a = 1; a < aSize; ++a) {
                if (((BData)supply.get(bestID)).num >= ((BData)supply.get(a)).num) continue;
                bestID = a;
            }
            civID = ((BData)supply.get(bestID)).id;
            menuElements.add(new Button_NS_Population_Buildings(new Color((float)CFG.core.getCiv(civID).getR() / 255.0f, (float)CFG.core.getCiv(civID).getG() / 255.0f, (float)CFG.core.getCiv(civID).getB() / 255.0f, 1.0f), i + 1 + ". " + (metCiv ? CFG.core.getCiv(civID).getCivName() : CFG.lang.get("Undiscovered")), metCiv ? civID : -1, "" + CFG.getPrecision2((float)((BData)supply.get(bestID)).num / (float)supplyNum * 100.0f, 100) + "%", CFG.getNumberWthSpaces("" + ((BData)supply.get(bestID)).num), Images.bSupply, CFG.COLOR_TEXT_NUM_OF_PROVINCES, 0, tY, tempW, CFG.core.getCiv(civID).getNumOfProvs()));
            ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(i % 2);
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
            supply.remove(bestID);
        }
        if (elemBefore == menuElements.size()) {
            menuElements.add(new TextScale(CFG.lang.get("NoData"), -1, 0, tY, tempW, CFG.BUTTON_H * 3 / 4, 0.75f));
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        }
        castle.clear();
        this.initMenu(new TitleM_TextSmall(CFG.map.getMapName_Just(CFG.map.getActiveMapIDN()) + ": " + CFG.lang.get("Buildings"), CFG.BUTTON_H * 3 / 5, false, false){

            @Override
            public void drawT(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                IMGManager.getIMG(Images.gameTopEdgeTitle).draw2O(oSB, Menu_InGame_View_BuildingsTop.this.getPosX() + iTranslateX, Menu_InGame_View_BuildingsTop.this.getPosY() - Core.PADDING - IMGManager.getIMG(Images.gameTopEdgeTitle).getHeight() - this.getHeightT(), Menu_InGame_View_BuildingsTop.this.getWidthM() + 2 + Core.PADDING, this.getHeightT() + Core.PADDING, true, false);
                oSB.setColor(new Color(CFG.COLOR_TEXT_NUM_OF_PROVINCES.r, CFG.COLOR_TEXT_NUM_OF_PROVINCES.g, CFG.COLOR_TEXT_NUM_OF_PROVINCES.b, 0.165f));
                IMGManager.getIMG(Images.line32Off1).drawO(oSB, nPosX + iTranslateX, nPosY - this.getHeightT() + 2 - IMGManager.getIMG(Images.line32Off1).getHeight(), nWidth, this.getHeightT() - 2, false, true);
                oSB.setColor(new Color(CFG.COLOR_TEXT_NUM_OF_PROVINCES.r, CFG.COLOR_TEXT_NUM_OF_PROVINCES.g, CFG.COLOR_TEXT_NUM_OF_PROVINCES.b, 0.375f));
                IMGManager.getIMG(Images.gradient).drawO(oSB, nPosX + iTranslateX, nPosY - this.getHeightT() * 2 / 3 - IMGManager.getIMG(Images.gradient).getHeight(), nWidth, this.getHeightT() * 2 / 3, false, true);
                oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
                IMGManager.getIMG(Images.pix255).drawO(oSB, Menu_InGame_View_BuildingsTop.this.getPosX() + iTranslateX, Menu_InGame_View_BuildingsTop.this.getPosY() - IMGManager.getIMG(Images.pix255).getHeight(), Menu_InGame_View_BuildingsTop.this.getWidthM());
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
                IMGManager.getIMG(Images.line32Off1).drawO(oSB, Menu_InGame_View_BuildingsTop.this.getPosX() + iTranslateX, Menu_InGame_View_BuildingsTop.this.getPosY() - IMGManager.getIMG(Images.pix255).getHeight() - IMGManager.getIMG(Images.line32Off1).getHeight(), Menu_InGame_View_BuildingsTop.this.getWidthM(), 1);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.6f));
                IMGManager.getIMG(Images.line32Off1).drawO(oSB, Menu_InGame_View_BuildingsTop.this.getPosX() + iTranslateX, Menu_InGame_View_BuildingsTop.this.getPosY() - IMGManager.getIMG(Images.pix255).getHeight() - IMGManager.getIMG(Images.line32Off1).getHeight() - 1, Menu_InGame_View_BuildingsTop.this.getWidthM(), 1);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.8f));
                IMGManager.getIMG(Images.sliderGradient).drawO(oSB, Menu_InGame_View_BuildingsTop.this.getPosX() + iTranslateX, Menu_InGame_View_BuildingsTop.this.getPosY() - IMGManager.getIMG(Images.pix255).getHeight() - IMGManager.getIMG(Images.sliderGradient).getHeight(), Menu_InGame_View_BuildingsTop.this.getWidthM() / 4, 1);
                IMGManager.getIMG(Images.sliderGradient).drawO(oSB, Menu_InGame_View_BuildingsTop.this.getPosX() + Menu_InGame_View_BuildingsTop.this.getWidthM() - Menu_InGame_View_BuildingsTop.this.getWidthM() / 4 + iTranslateX, Menu_InGame_View_BuildingsTop.this.getPosY() - IMGManager.getIMG(Images.pix255).getHeight() - IMGManager.getIMG(Images.sliderGradient).getHeight(), Menu_InGame_View_BuildingsTop.this.getWidthM() / 4, 1, true, false);
                oSB.setColor(Color.WHITE);
                IMGManager.getIMG(Images.buildAll).drawO(oSB, nPosX + CFG.PADD * 2 + iTranslateX, Menu_InGame_View_BuildingsTop.this.getPosY() - this.getHeightT() / 2 - IMGManager.getIMG(Images.buildAll).getHeight() / 2);
                Renderer.drawText(oSB, CFG.FONT_BOLD_SMALL, this.getText(), nPosX + nWidth / 2 - this.getTextWidth() / 2 + iTranslateX, nPosY - this.getHeightT() + this.getHeightT() / 2 + 1 - this.getTextHeight() / 2, Color.WHITE);
            }
        }, AoCGame.LEFT, IMGManager.getIMG(Images.topFlagBG).getHeight() + CFG.PADD * 3 + CFG.BUTTON_H * 3 / 5, tempW, Math.min(tY + 1, CFG.isAndroid() && !CFG.LANDSCAPE ? (CFG.GAMEHEIGHT - (IMGManager.getIMG(Images.topFlagBG).getHeight() + CFG.PADD * 3 + CFG.BUTTON_H * 3 / 4 + (CFG.PADD * 2 + CFG.BUTTON_H) * 2)) / 2 : CFG.GAMEHEIGHT - (IMGManager.getIMG(Images.topFlagBG).getHeight() + CFG.PADD * 3 + CFG.BUTTON_H * 3 / 4 + (GameValues.gvInGame.MAP_MODES_MENUS_TO_PROVINCE_INFO ? (CFG.PADD * 2 + CFG.BUTTON_H) * 2 : 0))), menuElements, false, true);
        this.updateLang();
    }

    @Override
    public void updateLang() {
    }

    @Override
    public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (lTime + (long)GameValues.gvInGame.MENUS_ANIMATION_TIME >= System.currentTimeMillis()) {
            iTranslateX = hideAnimation ? (iTranslateX -= (int)((float)this.getWidthM() * ((float)(System.currentTimeMillis() - lTime) / (float)GameValues.gvInGame.MENUS_ANIMATION_TIME))) : (iTranslateX += -this.getWidthM() + (int)((float)this.getWidthM() * ((float)(System.currentTimeMillis() - lTime) / (float)GameValues.gvInGame.MENUS_ANIMATION_TIME)));
            CFG.setRenderO(true);
        } else if (hideAnimation) {
            super.setVisibleM(false);
            return;
        }
        IMGManager.getIMG(Images.gameTopEdgeLine).draw2O(oSB, this.getPosX() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gameTopEdgeLine).getHeight() + iTranslateY, this.getWidthM() + 2 + Core.PADDING, this.getHeightM() + CFG.PADD, true, true);
        oSB.setColor(new Color(0.09803922f, 0.05882353f, 0.37254903f, 0.25f));
        IMGManager.getIMG(Images.gradient).drawO(oSB, this.getPosX() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gradient).getHeight() + iTranslateY, this.getWidthM(), CFG.PADD * 4);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        IMGManager.getIMG(Images.gradient).drawO(oSB, this.getPosX() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gradient).getHeight() + iTranslateY, this.getWidthM(), CFG.PADD * 2);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
        IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosX() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.pix255).getHeight() + this.getHeightM() + CFG.PADD, this.getWidthM());
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
        IMGManager.getIMG(Images.sliderGradient).drawO(oSB, this.getPosX() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.pix255).getHeight() - IMGManager.getIMG(Images.sliderGradient).getHeight() + this.getHeightM() + CFG.PADD, this.getWidthM(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeightM() + CFG.PADD, this.getWidthM() + 2);
        oSB.setColor(Color.WHITE);
    }

    @Override
    public void actionEL(int iID) {
        this.getMenuElem(iID).actionElem(iID);
    }

    @Override
    public void setVisibleM(boolean visible) {
        if (visible) {
            super.setVisibleM(visible);
            this.setHideAnimation(false);
        } else {
            this.setHideAnimation(true);
        }
    }

    public final void setHideAnimation(boolean nHideAnimation) {
        if (nHideAnimation != hideAnimation) {
            lTime = lTime > System.currentTimeMillis() - (long)GameValues.gvInGame.MENUS_ANIMATION_TIME ? System.currentTimeMillis() - ((long)GameValues.gvInGame.MENUS_ANIMATION_TIME - (System.currentTimeMillis() - lTime)) : System.currentTimeMillis();
            CFG.setRenderO(true);
        }
        hideAnimation = nHideAnimation;
    }

    public class BData {
        private int id;
        private int num;

        public BData(int id, int number) {
            this.id = id;
            this.num = number;
        }

        public int getID() {
            return this.id;
        }

        public int getNum() {
            return this.num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
