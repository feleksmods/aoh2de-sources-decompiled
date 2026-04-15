package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Civilization;
import age.of.civilizations2.jakowski.lukasz.Civilization_GameData3;
import age.of.civilizations2.jakowski.lukasz.Color_GameData;
import age.of.civilizations2.jakowski.lukasz.Core.Core;
import age.of.civilizations2.jakowski.lukasz.EventsJ;
import age.of.civilizations2.jakowski.lukasz.Events_GameData;
import age.of.civilizations2.jakowski.lukasz.Files.FileManager;
import age.of.civilizations2.jakowski.lukasz.GameCalendar;
import age.of.civilizations2.jakowski.lukasz.GameManager;
import age.of.civilizations2.jakowski.lukasz.GameValues.GameValues;
import age.of.civilizations2.jakowski.lukasz.MapA.CitiesManager;
import age.of.civilizations2.jakowski.lukasz.Menus.Menu_InitGame;
import age.of.civilizations2.jakowski.lukasz.Province;
import age.of.civilizations2.jakowski.lukasz.Province_Cores_GameData;
import age.of.civilizations2.jakowski.lukasz.Province_GameData_Occupied;
import age.of.civilizations2.jakowski.lukasz.Save.Save_Civ_GameData;
import age.of.civilizations2.jakowski.lukasz.Scenario_GameData;
import age.of.civilizations2.jakowski.lukasz.Scenario_GameData_Armies;
import age.of.civilizations2.jakowski.lukasz.Scenario_GameData_Diplomacy2;
import age.of.civilizations2.jakowski.lukasz.Scenario_GameData_Province2;
import age.of.civilizations2.jakowski.lukasz.Scenario_WastelandProvinces_GameData;
import age.of.civilizations2.jakowski.lukasz.Z_Other.ST.sUM;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Game_Scenarios {
    public static int SCENARIOS_SIZE;
    public List<String> lScenarios_TagsList = new ArrayList<String>();
    public List<Boolean> isInternal = new ArrayList<Boolean>();
    private List<String> lScenarios_Names = new ArrayList<String>();
    private List<Integer> lScenarios_CivNum = new ArrayList<Integer>();
    private List<String> lScenarios_Authors = new ArrayList<String>();
    private List<Integer> lScenarios_Age = new ArrayList<Integer>();
    private List<Integer> lScenarios_Year = new ArrayList<Integer>();
    private List<Integer> lScenarios_Month = new ArrayList<Integer>();
    private List<Integer> lScenarios_Day = new ArrayList<Integer>();
    private List<String> lScenarios_Wikis = new ArrayList<String>();
    private int iScenario_StartingArmyInCapitals = 750;
    private int iScenario_NeutralArmy = 150;
    private int iScenario_StartingPopulation = 65000;
    private int iScenario_StartingEconomy = 32000;
    private int iScenario_StartingMoney = 4500;
    private float iScenario_PopulationGrowthRate_Modifier = 0.0f;
    private float iScenario_EconomyGrowthRate_Modifier = 0.0f;
    private float iScenario_DiseasesDeathRate_Modifier = 0.0f;
    private String sScenario_ActivePallet_TAG = null;
    public String sActiveScenarioTag = "";
    public static final float PERC_OF_POPULATION_REQUIRED_TO_GET_A_CORE = 0.18f;

    public int getScenarioIDbyTag(String tag) {
        for (int i = this.lScenarios_TagsList.size() - 1; i >= 0; --i) {
            if (!this.lScenarios_TagsList.get(i).equals(tag)) continue;
            return i;
        }
        return -1;
    }

    public final void loadGame_Scenarios(boolean initMap) {
        int i;
        int i2;
        int i3;
        FileHandle tempFileT;
        if (SCENARIOS_SIZE > 0 || this.lScenarios_TagsList.size() > 0) {
            this.disposeScenarios();
        }
        String defaultScenario = null;
        ArrayList<String> scenarioTags = new ArrayList<String>();
        if (CFG.getIsDesktop()) {
            if (FileManager.IS_MAC) {
                tempFileT = FileManager.loadFile("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + "Age_of_Civilizations");
                String tempT = tempFileT.readString();
                String[] tagsSPLITED = tempT.split(";");
                for (i3 = tagsSPLITED.length - 1; i3 >= 0; --i3) {
                    scenarioTags.add(tagsSPLITED[i3]);
                }
            } else {
                int i4;
                List<String> tempFiles = CFG.getFileNames_O_Classic("map/" + CFG.map.getFileActiveMapPath() + "scenarios/");
                int iSize = tempFiles.size();
                for (i4 = 0; i4 < iSize; ++i4) {
                    if (!tempFiles.get(i4).equals("Age_of_Civilizations")) continue;
                    tempFiles.remove(i4);
                    break;
                }
                iSize = tempFiles.size();
                for (i4 = 0; i4 < iSize; ++i4) {
                    scenarioTags.add(tempFiles.get(i4));
                }
            }
        } else {
            tempFileT = FileManager.loadFile("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + "Age_of_Civilizations");
            String tempT = tempFileT.readString();
            String[] tagsSPLITED = tempT.split(";");
            for (int i5 = tagsSPLITED.length - 1; i5 >= 0; --i5) {
                if (scenarioTags.contains(tagsSPLITED[i5])) continue;
                scenarioTags.add(tagsSPLITED[i5]);
            }
            try {
                FileHandle tempFileT2 = Gdx.files.internal("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + "Age_of_Civilizations");
                String tempT2 = tempFileT2.readString();
                String[] tagsSPLITED2 = tempT2.split(";");
                for (int i6 = tagsSPLITED2.length - 1; i6 >= 0; --i6) {
                    if (scenarioTags.contains(tagsSPLITED2[i6])) continue;
                    scenarioTags.add(tagsSPLITED2[i6]);
                }
            }
            catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
        for (int a = 0; a < sUM.sUFS; ++a) {
            List<String> tempFiles = CFG.getFileNames_O(sUM.sUF.get(a) + "map/" + CFG.map.getFileActiveMapPath() + "scenarios/");
            int iSize = tempFiles.size();
            for (int i7 = 0; i7 < iSize; ++i7) {
                if (scenarioTags.contains(tempFiles.get(i7))) continue;
                scenarioTags.add(tempFiles.get(i7));
            }
        }
        for (i2 = 0; i2 < sUM.sUFS; ++i2) {
            FileHandle[] files = FileManager.IS_MAC ? Gdx.files.external(sUM.sUF.get(i2) + "map/" + CFG.map.getFileActiveMapPath() + "scenarios/").list() : Gdx.files.internal(sUM.sUF.get(i2) + "map/" + CFG.map.getFileActiveMapPath() + "scenarios/").list();
            for (FileHandle file : files) {
                if (scenarioTags.contains(file.name())) continue;
                scenarioTags.add(file.name());
            }
        }
        for (int a = 0; a < sUM.sUIIS; ++a) {
            FileHandle[] tempFiles = CFG.getFileNames_Absolute(sUM.sUII.get(a).getFolder() + "/" + "map/" + CFG.map.getFileActiveMapPath() + "scenarios/");
            int iSize = tempFiles.size();
            for (i3 = 0; i3 < iSize; ++i3) {
                if (scenarioTags.contains(tempFiles.get(i3))) continue;
                scenarioTags.add((String)tempFiles.get(i3));
            }
        }
        for (i2 = 0; i2 < sUM.sUIIS; ++i2) {
            FileHandle[] files;
            for (FileHandle file : files = Gdx.files.absolute(sUM.sUII.get(i2).getFolder() + "/" + "map/" + CFG.map.getFileActiveMapPath() + "scenarios/").list()) {
                if (scenarioTags.contains(file.name())) continue;
                scenarioTags.add(file.name());
            }
        }
        ArrayList<String> tempScenarios_TagsList = new ArrayList<String>();
        ArrayList<Boolean> tempIsInternal = new ArrayList<Boolean>();
        ArrayList<String> tempScenarios_Names = new ArrayList<String>();
        ArrayList<Integer> tempScenarios_CivNum = new ArrayList<Integer>();
        ArrayList<String> tempScenarios_Authors = new ArrayList<String>();
        ArrayList<Integer> tempScenarios_Age = new ArrayList<Integer>();
        ArrayList<Integer> tempScenarios_Year = new ArrayList<Integer>();
        ArrayList<Integer> tempScenarios_Month = new ArrayList<Integer>();
        ArrayList<Integer> tempScenarios_Day = new ArrayList<Integer>();
        ArrayList<String> tempScenarios_Wikis = new ArrayList<String>();
        int iSize = scenarioTags.size();
        for (i = 0; i < iSize; ++i) {
            if (((String)scenarioTags.get(i)).equals("Age_of_Civilizations")) continue;
            tempScenarios_TagsList.add((String)scenarioTags.get(i));
            tempIsInternal.add(true);
        }
        for (i = 0; i < tempScenarios_TagsList.size(); ++i) {
            try {
                CFG.ConfigScenarioInfo data = new CFG.ConfigScenarioInfo();
                Json json = new Json();
                json.setElementType(CFG.ConfigScenarioInfo.class, "Data_Scenario_Info", CFG.Data_Scenario_Info.class);
                data = json.fromJson(CFG.ConfigScenarioInfo.class, FileManager.loadFile("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + (String)tempScenarios_TagsList.get(i) + "/" + (String)tempScenarios_TagsList.get(i) + "_INFO" + ".json").reader("UTF8"));
                Iterator iterator = data.Data_Scenario_Info.iterator();
                if (!iterator.hasNext()) continue;
                Object e = iterator.next();
                CFG.Data_Scenario_Info tempData = (CFG.Data_Scenario_Info)e;
                tempScenarios_CivNum.add(tempData.Civs);
                tempScenarios_Names.add(tempData.Name);
                tempScenarios_Authors.add(tempData.Author);
                tempScenarios_Wikis.add(tempData.Wiki);
                tempScenarios_Age.add(tempData.Age);
                tempScenarios_Year.add(tempData.Year);
                tempScenarios_Month.add(tempData.Month);
                tempScenarios_Day.add(tempData.Day);
                continue;
            }
            catch (GdxRuntimeException ex) {
                if (CFG.LOGs) {
                    CFG.exceptionStack(ex);
                }
                tempScenarios_CivNum.add(0);
                tempScenarios_Names.add("ERROR");
                tempScenarios_Authors.add("ERROR");
                tempScenarios_Wikis.add("");
                tempScenarios_Age.add(0);
                tempScenarios_Year.add(0);
                tempScenarios_Month.add(0);
                tempScenarios_Day.add(0);
            }
        }
        if (CFG.readLocalFiles()) {
            try {
                int i8;
                FileHandle tempFileT2 = Gdx.files.local("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + "Age_of_Civilizations");
                String tempT2 = tempFileT2.readString();
                String[] tagsSPLITED2 = tempT2.split(";");
                int nStart = tempScenarios_TagsList.size();
                int iSize2 = tagsSPLITED2.length;
                for (i8 = 0; i8 < iSize2; ++i8) {
                    if (tempScenarios_TagsList.contains(tagsSPLITED2[i8])) continue;
                    tempScenarios_TagsList.add(tagsSPLITED2[i8]);
                    tempIsInternal.add(false);
                }
                for (i8 = nStart; i8 < tempScenarios_TagsList.size(); ++i8) {
                    FileHandle file = Gdx.files.local("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + (String)tempScenarios_TagsList.get(i8) + "/" + (String)tempScenarios_TagsList.get(i8) + "_INFO" + ".json");
                    String fileContent = file.readString();
                    Json json = new Json();
                    json.setElementType(CFG.ConfigScenarioInfo.class, "Data_Scenario_Info", CFG.Data_Scenario_Info.class);
                    CFG.ConfigScenarioInfo data = new CFG.ConfigScenarioInfo();
                    data = json.fromJson(CFG.ConfigScenarioInfo.class, fileContent);
                    Iterator iterator = data.Data_Scenario_Info.iterator();
                    if (!iterator.hasNext()) continue;
                    Object e = iterator.next();
                    CFG.Data_Scenario_Info tempData = (CFG.Data_Scenario_Info)e;
                    tempScenarios_CivNum.add(tempData.Civs);
                    tempScenarios_Names.add(tempData.Name);
                    tempScenarios_Authors.add(tempData.Author);
                    tempScenarios_Wikis.add(tempData.Wiki);
                    tempScenarios_Age.add(tempData.Age);
                    tempScenarios_Year.add(tempData.Year);
                    tempScenarios_Month.add(tempData.Month);
                    tempScenarios_Day.add(tempData.Day);
                }
            }
            catch (GdxRuntimeException tempFileT2) {
                // empty catch block
            }
        }
        if (CFG.core.getScenarioID() == -1) {
            defaultScenario = (String)tempScenarios_TagsList.get(0);
            CFG.core.setScenarioID(0);
        }
        while (tempScenarios_TagsList.size() > 0) {
            int nAdd = 0;
            for (int i9 = 1; i9 < tempScenarios_TagsList.size(); ++i9) {
                if ((Integer)tempScenarios_Year.get(nAdd) >= (Integer)tempScenarios_Year.get(i9)) continue;
                nAdd = i9;
            }
            this.lScenarios_TagsList.add((String)tempScenarios_TagsList.get(nAdd));
            tempScenarios_TagsList.remove(nAdd);
            this.isInternal.add((Boolean)tempIsInternal.get(nAdd));
            tempIsInternal.remove(nAdd);
            this.lScenarios_CivNum.add((Integer)tempScenarios_CivNum.get(nAdd));
            tempScenarios_CivNum.remove(nAdd);
            this.lScenarios_Names.add((String)tempScenarios_Names.get(nAdd));
            tempScenarios_Names.remove(nAdd);
            this.lScenarios_Authors.add((String)tempScenarios_Authors.get(nAdd));
            tempScenarios_Authors.remove(nAdd);
            this.lScenarios_Wikis.add((String)tempScenarios_Wikis.get(nAdd));
            tempScenarios_Wikis.remove(nAdd);
            this.lScenarios_Age.add((Integer)tempScenarios_Age.get(nAdd));
            tempScenarios_Age.remove(nAdd);
            this.lScenarios_Year.add((Integer)tempScenarios_Year.get(nAdd));
            tempScenarios_Year.remove(nAdd);
            this.lScenarios_Month.add((Integer)tempScenarios_Month.get(nAdd));
            tempScenarios_Month.remove(nAdd);
            this.lScenarios_Day.add((Integer)tempScenarios_Day.get(nAdd));
            tempScenarios_Day.remove(nAdd);
        }
        if (defaultScenario != null) {
            for (int i10 = 0; i10 < this.lScenarios_TagsList.size(); ++i10) {
                if (!defaultScenario.equals(this.lScenarios_TagsList.get(i10))) continue;
                CFG.core.setScenarioID(i10);
                break;
            }
        }
        SCENARIOS_SIZE = this.lScenarios_TagsList.size();
        if (initMap) {
            CFG.core.updateDaultScenarioID_ForMap();
        }
    }

    public final void disposeScenarios() {
        this.lScenarios_TagsList.clear();
        this.lScenarios_TagsList = new ArrayList<String>();
        this.lScenarios_Names.clear();
        this.lScenarios_Names = new ArrayList<String>();
        this.lScenarios_CivNum.clear();
        this.lScenarios_CivNum = new ArrayList<Integer>();
        this.lScenarios_Authors.clear();
        this.lScenarios_Authors = new ArrayList<String>();
        this.lScenarios_Wikis.clear();
        this.lScenarios_Wikis = new ArrayList<String>();
        this.lScenarios_Age.clear();
        this.lScenarios_Age = new ArrayList<Integer>();
        this.lScenarios_Year.clear();
        this.lScenarios_Year = new ArrayList<Integer>();
        this.lScenarios_Month.clear();
        this.lScenarios_Month = new ArrayList<Integer>();
        this.lScenarios_Day.clear();
        this.lScenarios_Day = new ArrayList<Integer>();
        this.isInternal.clear();
        this.isInternal = new ArrayList<Boolean>();
        SCENARIOS_SIZE = 0;
    }

    public final List<Civilization> loadCivilizations_RandomGame() {
        Random oR = new Random();
        ArrayList<Civilization> lCivs = new ArrayList<Civilization>();
        lCivs.add(CFG.core.getNeutralCivilization());
        ((Civilization)lCivs.get(0)).setCivId(0);
        ArrayList<String> lRandomGameCivsTags = new ArrayList<String>();
        if (CFG.RANDOM_PLACEMENT) {
            int nR;
            int i;
            FileHandle tempFileT = FileManager.loadFile("game/civilizations/Age_of_Civilizations");
            String tempT = tempFileT.readString();
            String[] tagsSPLITED = tempT.split(";");
            String[] tagsSPLITED_ED = new String[]{};
            try {
                FileHandle tempFileT_ED = null;
                tempFileT_ED = CFG.isAndroid() ? Gdx.files.local("game/civilizations_editor/Age_of_Civilizations") : FileManager.loadFile("game/civilizations_editor/Age_of_Civilizations");
                String tempT_ED = tempFileT_ED.readString();
                tagsSPLITED_ED = tempT_ED.split(";");
            }
            catch (GdxRuntimeException tempFileT_ED) {
                // empty catch block
            }
            ArrayList<String> nCivsTags = new ArrayList<String>();
            int iSize = tagsSPLITED.length;
            for (i = 0; i < iSize; ++i) {
                if (CFG.randomGameManager.isTagTaken(tagsSPLITED[i])) continue;
                nCivsTags.add(tagsSPLITED[i]);
            }
            iSize = tagsSPLITED_ED.length;
            for (i = 0; i < iSize; ++i) {
                if (CFG.randomGameManager.isTagTaken(tagsSPLITED_ED[i])) continue;
                nCivsTags.add(tagsSPLITED[i]);
            }
            for (i = 0; i < CFG.randomGameManager.getPlayersSize(); ++i) {
                if (CFG.randomGameManager.getPlayer(i).getTag() != null) {
                    lRandomGameCivsTags.add(CFG.randomGameManager.getPlayer(i).getTag());
                    continue;
                }
                nR = oR.nextInt(nCivsTags.size());
                lRandomGameCivsTags.add((String)nCivsTags.get(nR));
                nCivsTags.remove(nR);
            }
            try {
                nR = 0;
                for (i = 0; i < CFG.randomGameManager.getCivilizationsSize(); ++i) {
                    nR = oR.nextInt(nCivsTags.size());
                    lRandomGameCivsTags.add((String)nCivsTags.get(nR));
                    nCivsTags.remove(nR);
                }
            }
            catch (Exception i2) {
                // empty catch block
            }
            try {
                String tempTag = null;
                boolean add = true;
                int iSize2 = lRandomGameCivsTags.size();
                for (int i3 = 0; i3 < iSize2; ++i3) {
                    int j;
                    int nRandIdeology = oR.nextInt(CFG.ideologiesMgr.getIdeologiesSize());
                    int nNumOfTries = 0;
                    while ((CFG.ideologiesMgr.getIdeologyID((int)nRandIdeology).REVOLUTIONARY || CFG.ideologiesMgr.getIdeologyID((int)nRandIdeology).CAN_BECOME_CIVILIZED >= 0) && nNumOfTries++ < 8) {
                        nRandIdeology = oR.nextInt(CFG.ideologiesMgr.getIdeologiesSize());
                    }
                    add = true;
                    tempTag = CFG.ideologiesMgr.getRealTag((String)lRandomGameCivsTags.get(i3)) + CFG.ideologiesMgr.getIdeologyID(nRandIdeology).getExtraTag();
                    for (j = i3 + 1; j < iSize2; ++j) {
                        if (!tempTag.equals(lRandomGameCivsTags.get(j))) continue;
                        add = false;
                        break;
                    }
                    if (add) {
                        for (j = i3 - 1; j >= 0; --j) {
                            if (!tempTag.equals(lRandomGameCivsTags.get(j))) continue;
                            add = false;
                            break;
                        }
                        if (add) {
                            lRandomGameCivsTags.set(i3, tempTag);
                        }
                    }
                    Civilization_GameData3 civData = Core.loadCivilization((String)lRandomGameCivsTags.get(i3));
                    int tCapital = 0;
                    tCapital = i3 < CFG.randomGameManager.getPlayersSize() && CFG.randomGameManager.getPlayer(i3).getCapitalProvinceID() >= 0 ? CFG.randomGameManager.getPlayer(i3).getCapitalProvinceID() : -1;
                    lCivs.add(new Civilization((String)lRandomGameCivsTags.get(i3), civData.getR(), civData.getG(), civData.getB(), tCapital, i3 + 1, civData.iReligionID, civData.iGroupID, true));
                    ((Civilization)lCivs.get(i3 + 1)).setCivId(i3 + 1);
                    ((Civilization)lCivs.get(i3 + 1)).setTechLevel((float)(GameValues.gvInGame.RANDOM_GAME_TECHNOLOGY_MIN + Math.min(GameValues.gvInGame.RANDOM_GAME_TECHNOLOGY_PER_AGE * GameCalendar.CURRENT_AGEID, GameValues.gvInGame.RANDOM_GAME_TECHNOLOGY_PER_AGE_MAX) + oR.nextInt(GameValues.gvInGame.RANDOM_GAME_TECHNOLOGY_RANDOM)) / 100.0f);
                    ((Civilization)lCivs.get(i3 + 1)).setHappiness(68 + oR.nextInt(16));
                    if (tCapital >= 0) {
                        CFG.core.getProv(((Civilization)lCivs.get(i3 + 1)).getCapitalProvID()).setCivId_LoadScenario(i3 + 1);
                    }
                    ((Civilization)lCivs.get(i3 + 1)).setGold(CFG.core.getGameScenars().getScenario_StartingMoney());
                }
            }
            catch (Exception e) {
                CFG.exceptionStack(e);
            }
        } else {
            int i;
            int i4;
            FileHandle tempFileT = FileManager.loadFile("game/civilizations/Age_of_Civilizations");
            String tempT = tempFileT.readString();
            String[] tagsSPLITED = tempT.split(";");
            String[] tagsSPLITED_ED = new String[]{};
            try {
                FileHandle tempFileT_ED = null;
                tempFileT_ED = CFG.isAndroid() ? Gdx.files.local("game/civilizations_editor/Age_of_Civilizations") : FileManager.loadFile("game/civilizations_editor/Age_of_Civilizations");
                String tempT_ED = tempFileT_ED.readString();
                tagsSPLITED_ED = tempT_ED.split(";");
            }
            catch (GdxRuntimeException tempFileT_ED) {
                // empty catch block
            }
            ArrayList<String> nCivsTags = new ArrayList<String>();
            ArrayList<RandomGame_AoCMode> civsToAdd = new ArrayList<RandomGame_AoCMode>();
            int iSize = tagsSPLITED.length;
            for (i4 = 0; i4 < iSize; ++i4) {
                if (CFG.randomGameManager.isTagTaken(tagsSPLITED[i4])) continue;
                nCivsTags.add(tagsSPLITED[i4]);
            }
            iSize = tagsSPLITED_ED.length;
            for (i4 = 0; i4 < iSize; ++i4) {
                if (CFG.randomGameManager.isTagTaken(tagsSPLITED_ED[i4])) continue;
                nCivsTags.add(tagsSPLITED[i4]);
            }
            for (i4 = 0; i4 < CFG.randomGameManager.getPlayersSize(); ++i4) {
                if (CFG.randomGameManager.getPlayer(i4).getTag() != null) {
                    civsToAdd.add(new RandomGame_AoCMode(CFG.randomGameManager.getPlayer(i4).getTag(), CFG.randomGameManager.getPlayer(i4).getCapitalProvinceID()));
                    continue;
                }
                if (CFG.randomGameManager.getPlayer(i4).getCapitalProvinceID() < 0) continue;
                int nR = oR.nextInt(nCivsTags.size());
                civsToAdd.add(new RandomGame_AoCMode((String)nCivsTags.get(nR), CFG.randomGameManager.getPlayer(i4).getCapitalProvinceID()));
                nCivsTags.remove(nR);
            }
            for (int o = 0; o < civsToAdd.size(); ++o) {
                try {
                    Civilization_GameData3 civData = Core.loadCivilization(((RandomGame_AoCMode)civsToAdd.get((int)o)).sTag);
                    int tCapital = ((RandomGame_AoCMode)civsToAdd.get((int)o)).iCapitalID;
                    lCivs.add(new Civilization(((RandomGame_AoCMode)civsToAdd.get((int)o)).sTag, civData.getR(), civData.getG(), civData.getB(), tCapital, o + 1, civData.iReligionID, civData.iGroupID, true));
                    ((Civilization)lCivs.get(o + 1)).setCivId(o + 1);
                    ((Civilization)lCivs.get(o + 1)).setTechLevel((float)(20 + Math.min(5 * GameCalendar.CURRENT_AGEID, 25) + oR.nextInt(10)) / 100.0f);
                    ((Civilization)lCivs.get(o + 1)).setHappiness(68 + oR.nextInt(16));
                    if (tCapital >= 0) {
                        CFG.core.getProv(((Civilization)lCivs.get(o + 1)).getCapitalProvID()).setCivId_LoadScenario(o + 1);
                    }
                    ((Civilization)lCivs.get(o + 1)).setGold(CFG.core.getGameScenars().getScenario_StartingMoney());
                    continue;
                }
                catch (Exception civData) {
                    // empty catch block
                }
            }
            ArrayList<Integer> lPossibleCapitals = new ArrayList<Integer>();
            for (i = 0; i < CFG.core.getProvinSize(); ++i) {
                if (CFG.core.getProv(i).getSeaProv()) continue;
                CFG.core.getProv(i).setIsCapital(false);
            }
            for (i = 0; i < CFG.randomGameManager.getPlayersSize(); ++i) {
                if (CFG.randomGameManager.getPlayer(i).getCapitalProvinceID() < 0) continue;
                CFG.core.getProv(CFG.randomGameManager.getPlayer(i).getCapitalProvinceID()).setIsCapital(true);
            }
            for (i = 0; i < CFG.core.getProvinSize(); ++i) {
                if (CFG.core.getProv(i).getSeaProv() || CFG.core.getProv(i).getWastelandLvl() >= 0 || CFG.core.getProv(i).isCapital()) continue;
                try {
                    if (!FileManager.loadFile("map/" + CFG.map.getFileActiveMapPath() + "suggested_owners/" + i).exists()) continue;
                    lPossibleCapitals.add(i);
                    continue;
                }
                catch (GdxRuntimeException ex) {
                    // empty catch block
                }
            }
            if (lPossibleCapitals.size() < 100) {
                for (i = 0; i < CFG.core.getProvinSize(); ++i) {
                    if (CFG.core.getProv(i).getSeaProv() || CFG.core.getProv(i).getWastelandLvl() >= 0 || CFG.core.getProv(i).isCapital() || lPossibleCapitals.contains(i)) continue;
                    lPossibleCapitals.add(i);
                }
            }
            try {
                int extraToAddForPlayers = civsToAdd.size() - CFG.randomGameManager.getPlayersSize();
                block36: for (int i5 = 0; i5 < CFG.randomGameManager.getCivilizationsSize() + extraToAddForPlayers && !lPossibleCapitals.isEmpty(); ++i5) {
                    try {
                        int tempCapitalID = 0;
                        int iNumOfItterations = 0;
                        while (true) {
                            int tRandID = CFG.oR.nextInt(lPossibleCapitals.size());
                            tempCapitalID = (Integer)lPossibleCapitals.get(tRandID);
                            ++iNumOfItterations;
                            if (!CFG.core.getProv(tempCapitalID).isCapital()) {
                                boolean found = true;
                                for (int o = 0; o < CFG.core.getProv(tempCapitalID).getNeighProvincesSize(); ++o) {
                                    if (!CFG.core.getProv(CFG.core.getProv(tempCapitalID).getNeighProvinces(o)).getIsCapital_Just()) continue;
                                    found = false;
                                    break;
                                }
                                if (!found && iNumOfItterations <= 18) continue;
                                found = false;
                                ArrayList<String> lPossibleCapitals_Tags = new ArrayList<String>();
                                try {
                                    int j;
                                    FileHandle file = FileManager.loadFile("map/" + CFG.map.getFileActiveMapPath() + "suggested_owners/" + lPossibleCapitals.get(tRandID));
                                    String sOwners = file.readString();
                                    String[] sRes = sOwners.split(";");
                                    for (j = 0; j < sRes.length; j += 2) {
                                        int nIdeology = CFG.ideologiesMgr.getIdeologyID(sRes[j]);
                                        if (CFG.ideologiesMgr.getIdeologyID((int)nIdeology).CAN_BECOME_CIVILIZED >= 0) {
                                            lPossibleCapitals_Tags.add(CFG.ideologiesMgr.getRealTag(sRes[j]));
                                            continue;
                                        }
                                        lPossibleCapitals_Tags.add(sRes[j]);
                                    }
                                    block40: for (j = lPossibleCapitals_Tags.size() - 1; j >= 0; --j) {
                                        for (int k = civsToAdd.size() - 1; k >= 0; --k) {
                                            if (!((RandomGame_AoCMode)civsToAdd.get((int)k)).sTag.equals(lPossibleCapitals_Tags.get(j))) continue;
                                            lPossibleCapitals_Tags.remove(j);
                                            continue block40;
                                        }
                                    }
                                    if (lPossibleCapitals_Tags.size() == 0) {
                                        lPossibleCapitals.remove(tRandID);
                                        continue;
                                    }
                                    found = true;
                                }
                                catch (GdxRuntimeException ex) {
                                    lPossibleCapitals.remove(tRandID);
                                    continue;
                                }
                                if (!found) continue block36;
                                try {
                                    int nTagIDToAdd = CFG.oR.nextInt(lPossibleCapitals_Tags.size());
                                    Civilization_GameData3 civData = Core.loadCivilization((String)lPossibleCapitals_Tags.get(nTagIDToAdd));
                                    int tCapital = (Integer)lPossibleCapitals.get(tRandID);
                                    civsToAdd.add(new RandomGame_AoCMode((String)lPossibleCapitals_Tags.get(nTagIDToAdd), tCapital));
                                    int tCivID = lCivs.size();
                                    lCivs.add(new Civilization((String)lPossibleCapitals_Tags.get(nTagIDToAdd), civData.getR(), civData.getG(), civData.getB(), tCapital, tCivID, civData.iReligionID, civData.iGroupID, true));
                                    ((Civilization)lCivs.get(tCivID)).setCivId(tCivID);
                                    ((Civilization)lCivs.get(tCivID)).setTechLevel((float)(GameValues.gvInGame.RANDOM_GAME_TECHNOLOGY_MIN + Math.min(GameValues.gvInGame.RANDOM_GAME_TECHNOLOGY_PER_AGE * GameCalendar.CURRENT_AGEID, GameValues.gvInGame.RANDOM_GAME_TECHNOLOGY_PER_AGE_MAX) + oR.nextInt(GameValues.gvInGame.RANDOM_GAME_TECHNOLOGY_RANDOM)) / 100.0f);
                                    ((Civilization)lCivs.get(tCivID)).setHappiness(68 + oR.nextInt(16));
                                    if (tCapital >= 0) {
                                        CFG.core.getProv(((Civilization)lCivs.get(tCivID)).getCapitalProvID()).setCivId_LoadScenario(tCivID);
                                        CFG.core.getProv(tCapital).setIsCapital(true);
                                    }
                                    ((Civilization)lCivs.get(tCivID)).setGold(CFG.core.getGameScenars().getScenario_StartingMoney());
                                    lPossibleCapitals.remove(tRandID);
                                    continue block36;
                                }
                                catch (Exception e) {
                                    lPossibleCapitals.remove(tRandID);
                                    continue;
                                }
                            }
                            lPossibleCapitals.remove(tRandID);
                        }
                    }
                    catch (StackOverflowError ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
            catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
        return lCivs;
    }

    public final List<Civilization> loadCivilizationsLoadGame(List<Save_Civ_GameData> nCivsData, int startCivID) {
        GameCalendar.updateAge(false);
        ArrayList<Civilization> lCivs = new ArrayList<Civilization>();
        if (startCivID == 0) {
            lCivs.add(CFG.core.getNeutralCivilization());
            ((Civilization)lCivs.get(0)).setCivId(0);
        }
        for (int i = 0; i < nCivsData.size(); ++i) {
            lCivs.add(new Civilization(nCivsData.get(i), startCivID + i + (startCivID == 0 ? 1 : 0)));
        }
        CFG.map.getMpB().disposeMinimapOfCivilizations();
        return lCivs;
    }

    public final List<Civilization> loadCivilizations(boolean nEditor) {
        FileHandle fileProvince;
        FileHandle file;
        CFG.FILL_THE_MAP = true;
        GameCalendar.CURRENT_AGEID = this.getScenarioAgeID(CFG.core.getScenarioID());
        ArrayList<Civilization> lCivs = new ArrayList<Civilization>();
        lCivs.add(CFG.core.getNeutralCivilization());
        ((Civilization)lCivs.get(0)).setCivId(0);
        if (this.isInternal.get(CFG.core.getScenarioID()).booleanValue()) {
            file = FileManager.loadFile("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()));
            fileProvince = FileManager.loadFile("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "_PD");
        } else {
            try {
                file = Gdx.files.local("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()));
                fileProvince = Gdx.files.local("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "_PD");
            }
            catch (Exception ex) {
                file = FileManager.loadFile("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()));
                fileProvince = FileManager.loadFile("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "_PD");
            }
        }
        try {
            int i;
            Scenario_GameData tempScenarioGameData = (Scenario_GameData)CFG.deserialize(file.readBytes());
            this.setScenarioStartingArmyInCapitals(tempScenarioGameData.getStartingArmyInCapitals());
            this.setScenario_NeutralArmy(tempScenarioGameData.getNeutralArmy());
            this.setScenarioStartingPopulation(tempScenarioGameData.getStartingPopulation());
            this.setScenarioStartingEconomy(tempScenarioGameData.getStartingEconomy());
            this.setScenarioStartingMoney(tempScenarioGameData.getStartingMoney());
            this.setScenario_PopulationGrowthRate_Modifier(tempScenarioGameData.getPopulationGrowthRate_Modifier());
            this.setScenario_EconomyGrowthRate_Modifier(tempScenarioGameData.getEconomyGrowthRate_Modifier());
            this.setScenario_DiseasesDeathRate_Modifier(tempScenarioGameData.getDiseasesDeathRate_Modifier());
            this.setScenarioActivePallet_TAG(tempScenarioGameData.getActivePalletOfColors_TAG());
            GameCalendar.ENABLE_COLONIZATION = tempScenarioGameData.getColonization();
            GameCalendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES = tempScenarioGameData.ENABLE_COLONIZATION_NEUTRAL_PROVINCES;
            GameCalendar.COLONIZATION_TECH_LEVEL = tempScenarioGameData.COLONIZATION_TECH_LEVEL;
            for (i = 0; i < tempScenarioGameData.getCivSize(); ++i) {
                Civilization_GameData3 civData = Core.loadCivilization(tempScenarioGameData.getCivTag(i));
                lCivs.add(new Civilization(tempScenarioGameData.getCivTag(i), civData.getR(), civData.getG(), civData.getB(), tempScenarioGameData.getCivCapital(i), i + 1, civData.iReligionID, civData.iGroupID, false));
                ((Civilization)lCivs.get(i + 1)).setCivId(i + 1);
                ((Civilization)lCivs.get(i + 1)).setTechLevel(tempScenarioGameData.getTechnologyLevel(i));
                ((Civilization)lCivs.get(i + 1)).setHappiness(tempScenarioGameData.getHappiness(i));
                if (nEditor) {
                    ((Civilization)lCivs.get(i + 1)).setGold(-999999 == tempScenarioGameData.getStartingMoneyCiv(i) ? -999999 : tempScenarioGameData.getStartingMoneyCiv(i));
                } else {
                    ((Civilization)lCivs.get(i + 1)).setGold(-999999 == tempScenarioGameData.getStartingMoneyCiv(i) ? (CFG.ideologiesMgr.getIdeologyID((int)((Civilization)lCivs.get((int)(i + 1))).getIdeology()).CAN_BECOME_CIVILIZED >= 0 ? tempScenarioGameData.getStartingMoney() / 10 : tempScenarioGameData.getStartingMoney()) : tempScenarioGameData.getStartingMoneyCiv(i));
                }
                if (((Civilization)lCivs.get(i + 1)).getCapitalProvID() < 0) continue;
                CFG.core.getProv(((Civilization)lCivs.get(i + 1)).getCapitalProvID()).setCivId_LoadScenario(i + 1);
            }
            CFG.initCreateScenario_TechnologyLevelsByContinents_Civ();
            for (i = 0; i < tempScenarioGameData.getCivSize(); ++i) {
                CFG.addCreateScenario_TechnologyLevelsByContinents_Civ(tempScenarioGameData.getTechnologyByContinents(i));
            }
            tempScenarioGameData = null;
            Scenario_GameData_Province2 scenario_GameData_Province = (Scenario_GameData_Province2)CFG.deserialize(fileProvince.readBytes());
            if (scenario_GameData_Province.getProvinceOwners() != null) {
                try {
                    int iSize = scenario_GameData_Province.getProvinceOwners().size();
                    for (int i2 = 0; i2 < iSize; ++i2) {
                        CFG.core.getProv(i2).setCivId_LoadScenario(scenario_GameData_Province.getProvinceOwners().get(i2));
                    }
                }
                catch (Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
            scenario_GameData_Province = null;
        }
        catch (Exception e) {
            CFG.exceptionStack(e);
        }
        if (!nEditor) {
            boolean foundRandomCivilization = false;
            int iSize = lCivs.size();
            for (int i = 1; i < iSize; ++i) {
                if (!((Civilization)lCivs.get(i)).getCivTag().equals("ran")) continue;
                foundRandomCivilization = true;
                break;
            }
            if (foundRandomCivilization) {
                FileHandle tempFileT = FileManager.loadFile("game/civilizations/Age_of_Civilizations");
                String tempT = tempFileT.readString();
                String[] tagsSPLITED = tempT.split(";");
                Random oR = new Random();
                int iSize2 = lCivs.size();
                for (int i = 1; i < iSize2; ++i) {
                    int tempTagID;
                    if (!((Civilization)lCivs.get(i)).getCivTag().equals("ran")) continue;
                    while (tagsSPLITED[tempTagID = oR.nextInt(tagsSPLITED.length)].equals("ran") || CFG.isInTheCivGameTag(tagsSPLITED[tempTagID])) {
                    }
                    FileHandle fileCiv = FileManager.loadFile("game/civilizations/" + tagsSPLITED[tempTagID]);
                    try {
                        Civilization_GameData3 tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                        ((Civilization)lCivs.get(i)).setCivTag(tempCivData.getCivTag());
                        ((Civilization)lCivs.get(i)).setCivName(CFG.lang.getCiv(tempCivData.getCivTag()));
                        ((Civilization)lCivs.get(i)).setR(tempCivData.getR());
                        ((Civilization)lCivs.get(i)).setG(tempCivData.getG());
                        ((Civilization)lCivs.get(i)).setB(tempCivData.getB());
                        ((Civilization)lCivs.get(i)).disposeFlag();
                        ((Civilization)lCivs.get(i)).loadFlag();
                        tempCivData = null;
                    }
                    catch (ClassNotFoundException classNotFoundException) {
                    }
                    catch (IOException iOException) {
                    }
                }
            }
        }
        CFG.map.getMpB().disposeMinimapOfCivilizations();
        try {
            this.sActiveScenarioTag = this.getScenarioTagID(CFG.core.getScenarioID());
        }
        catch (IndexOutOfBoundsException ex) {
            this.sActiveScenarioTag = "";
        }
        return lCivs;
    }

    public final void loadProvincesData(boolean nEditor) {
        FileHandle file;
        if (this.isInternal.get(CFG.core.getScenarioID()).booleanValue()) {
            file = FileManager.loadFile("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "_W");
        } else {
            try {
                file = Gdx.files.local("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "_W");
            }
            catch (Exception ex) {
                file = FileManager.loadFile("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "_W");
            }
        }
        try {
            Scenario_WastelandProvinces_GameData scenario_WastelandProvinces_GameData = (Scenario_WastelandProvinces_GameData)CFG.deserialize(file.readBytes());
            int iSize = scenario_WastelandProvinces_GameData.getWastelandProvincesSize();
            for (int i = 0; i < iSize; ++i) {
                CFG.core.getProv(scenario_WastelandProvinces_GameData.getWastelandProvinceID(i)).setWastelandLvl(0);
            }
            Object var3_4 = null;
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.buildProvincePopulationAndEconomy(true, nEditor);
    }

    public final void loadEventsData() {
        block13: {
            try {
                CFG.eventsManager.events = new Events_GameData();
                if (!Menu_InitGame.DJE && EventsJ.loadEventsJ()) {
                    CFG.eventsManager.FXABF();
                    break block13;
                }
                try {
                    FileHandle file = null;
                    try {
                        if (this.isInternal.get(CFG.core.getScenarioID()).booleanValue()) {
                            file = FileManager.loadFile("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "/" + "events/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "_E");
                        } else {
                            try {
                                file = Gdx.files.local("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "/" + "events/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "_E");
                            }
                            catch (Exception ex) {
                                file = FileManager.loadFile("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "/" + "events/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "_E");
                            }
                        }
                    }
                    catch (Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                    try {
                        CFG.eventsManager.events = (Events_GameData)CFG.deserialize(file.readBytes());
                        CFG.eventsManager.FXABF();
                    }
                    catch (Exception e) {
                        CFG.eventsManager.events = new Events_GameData();
                        CFG.eventsManager.FXABF();
                        CFG.exceptionStack(e);
                    }
                }
                catch (Exception ex) {
                    CFG.eventsManager.events = new Events_GameData();
                    CFG.eventsManager.FXABF();
                    CFG.exceptionStack(ex);
                }
            }
            catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void loadCoresData() {
        FileHandle file;
        try {
            if (this.isInternal.get(CFG.core.getScenarioID()).booleanValue()) {
                file = FileManager.loadFile("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "_C");
            } else {
                try {
                    file = Gdx.files.local("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "_C");
                }
                catch (Exception ex) {
                    file = FileManager.loadFile("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "_C");
                }
            }
            try {
                CFG.province_CoresGD = (Province_Cores_GameData)CFG.deserialize(file.readBytes());
            }
            catch (Exception e) {
                CFG.province_CoresGD = new Province_Cores_GameData();
            }
        }
        catch (GdxRuntimeException ex) {
            CFG.province_CoresGD = new Province_Cores_GameData();
        }
        try {
            if (this.isInternal.get(CFG.core.getScenarioID()).booleanValue()) {
                file = FileManager.loadFile("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "_O");
            } else {
                try {
                    file = Gdx.files.local("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "_O");
                }
                catch (Exception ex) {
                    file = FileManager.loadFile("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "_O");
                }
            }
            Province_GameData_Occupied occupied = (Province_GameData_Occupied)CFG.deserialize(file.readBytes());
            for (int i = 0; i < occupied.provinceID.size(); ++i) {
                CFG.core.getProv(occupied.provinceID.get(i)).setTrueOwnerOfProv(occupied.civID.get(i));
                CFG.core.getProv(occupied.provinceID.get(i)).getCores().addNewCore(occupied.civID.get(i), 1);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public final void loadCoresDataEditor() {
        FileHandle file;
        try {
            if (this.isInternal.get(CFG.core.getScenarioID()).booleanValue()) {
                file = FileManager.loadFile("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "_C");
            } else {
                try {
                    file = Gdx.files.local("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "_C");
                }
                catch (Exception ex) {
                    file = FileManager.loadFile("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "_C");
                }
            }
            try {
                CFG.province_CoresGD = (Province_Cores_GameData)CFG.deserialize(file.readBytes());
            }
            catch (Exception e) {
                CFG.province_CoresGD = new Province_Cores_GameData();
            }
            try {
                for (int i = 0; i < CFG.province_CoresGD.getProvincesSize(); ++i) {
                    CFG.core.getProv(CFG.province_CoresGD.lProvinces.get((int)i).iProvinceID).buildProvinceCore();
                    for (int j = 0; j < CFG.province_CoresGD.lProvinces.get((int)i).lCores.size(); ++j) {
                        CFG.core.getProv(CFG.province_CoresGD.lProvinces.get((int)i).iProvinceID).getCores().addNewCore(CFG.province_CoresGD.lProvinces.get((int)i).lCores.get((int)j).iCivID, GameCalendar.TURNID);
                    }
                }
            }
            catch (Exception i) {
            }
        }
        catch (GdxRuntimeException ex) {
            CFG.province_CoresGD = new Province_Cores_GameData();
        }
        try {
            if (this.isInternal.get(CFG.core.getScenarioID()).booleanValue()) {
                file = FileManager.loadFile("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "_O");
            } else {
                try {
                    file = Gdx.files.local("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "_O");
                }
                catch (Exception ex) {
                    file = FileManager.loadFile("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "_O");
                }
            }
            Province_GameData_Occupied occupied = (Province_GameData_Occupied)CFG.deserialize(file.readBytes());
            for (int i = 0; i < occupied.provinceID.size(); ++i) {
                CFG.core.getProv(occupied.provinceID.get(i)).setTrueOwnerOfProv(occupied.civID.get(i));
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public final void buildDiplomacy() {
        CFG.core.buildAlliances();
        CFG.core.buildWars();
        for (int i = 1; i < CFG.core.getCivsSize(); ++i) {
            CFG.core.getCiv(i).buildDiplomacy(true);
        }
    }

    public final void loadDiplomacyData(boolean editor) {
        FileHandle file;
        this.buildDiplomacy();
        if (this.isInternal.get(CFG.core.getScenarioID()).booleanValue()) {
            file = FileManager.loadFile("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "_D");
        } else {
            try {
                file = Gdx.files.local("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "_D");
            }
            catch (Exception ex) {
                file = FileManager.loadFile("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "_D");
            }
        }
        try {
            int j;
            int i;
            Scenario_GameData_Diplomacy2 scenario_GameData_Diplomacy = (Scenario_GameData_Diplomacy2)CFG.deserialize(file.readBytes());
            for (i = 0; i < scenario_GameData_Diplomacy.getVassals().size(); ++i) {
                if (scenario_GameData_Diplomacy.getVassals().get(i).getCivLordID() >= CFG.core.getCivsSize()) continue;
                CFG.core.getCiv(scenario_GameData_Diplomacy.getVassals().get(i).getCivID()).setPuppetOfCivId(scenario_GameData_Diplomacy.getVassals().get(i).getCivLordID());
            }
            for (i = 0; i < scenario_GameData_Diplomacy.getAlliances().size(); ++i) {
                CFG.core.addAlliance(scenario_GameData_Diplomacy.getAlliances().get(i).getName());
                for (j = 0; j < scenario_GameData_Diplomacy.getAlliances().get(i).getCivs().size(); ++j) {
                    CFG.core.getAlliance(i + 1).addCivilization(scenario_GameData_Diplomacy.getAlliances().get(i).getCivs().get(j));
                    CFG.core.getCiv(scenario_GameData_Diplomacy.getAlliances().get(i).getCivs().get(j)).setAlliance(i + 1);
                }
                CFG.core.getAlliance(i + 1).setColorOfAlliance(new Color_GameData(scenario_GameData_Diplomacy.getAlliances().get(i).getColor().getR(), scenario_GameData_Diplomacy.getAlliances().get(i).getColor().getG(), scenario_GameData_Diplomacy.getAlliances().get(i).getColor().getB()));
            }
            if (editor) {
                for (i = 0; i < scenario_GameData_Diplomacy.getRelations().size(); ++i) {
                    CFG.core.setCivRelationOfCivB(scenario_GameData_Diplomacy.getRelations().get(i).getCivA(), scenario_GameData_Diplomacy.getRelations().get(i).getCivB(), scenario_GameData_Diplomacy.getRelations().get(i).getValue());
                }
            } else {
                for (i = 0; i < scenario_GameData_Diplomacy.getRelations().size(); ++i) {
                    CFG.core.setCivRelationOfCivB(scenario_GameData_Diplomacy.getRelations().get(i).getCivA(), scenario_GameData_Diplomacy.getRelations().get(i).getCivB(), scenario_GameData_Diplomacy.getRelations().get(i).getValue());
                }
                if (GameValues.gvDiplomacy.NEW_GAME_SET_RANDOM_RELATIONS) {
                    for (i = 1; i < CFG.core.getCivsSize() - 1; ++i) {
                        for (j = i + 1; j < CFG.core.getCivsSize(); ++j) {
                            if ((int)CFG.core.getCivRelationOfCivB(i, j) == 0) {
                                CFG.core.setCivRelationOfCivB(i, j, CFG.oR.nextInt(GameValues.gvDiplomacy.NEW_GAME_RANDOM_RELATIONS_RANDOM) - GameValues.gvDiplomacy.NEW_GAME_RANDOM_RELATIONS_BASE_MINUS);
                            }
                            if ((int)CFG.core.getCivRelationOfCivB(j, i) != 0) continue;
                            CFG.core.setCivRelationOfCivB(j, i, CFG.oR.nextInt(GameValues.gvDiplomacy.NEW_GAME_RANDOM_RELATIONS_RANDOM) - GameValues.gvDiplomacy.NEW_GAME_RANDOM_RELATIONS_BASE_MINUS);
                        }
                    }
                }
            }
            for (i = 0; i < scenario_GameData_Diplomacy.getMilitaryAccess().size(); ++i) {
                CFG.core.setMilitaryAccess(scenario_GameData_Diplomacy.getMilitaryAccess().get(i).getCivA(), scenario_GameData_Diplomacy.getMilitaryAccess().get(i).getCivB(), scenario_GameData_Diplomacy.getMilitaryAccess().get(i).getValue());
            }
            for (i = 0; i < scenario_GameData_Diplomacy.getGuarantee().size(); ++i) {
                CFG.core.setGuarantee(scenario_GameData_Diplomacy.getGuarantee().get(i).getCivA(), scenario_GameData_Diplomacy.getGuarantee().get(i).getCivB(), scenario_GameData_Diplomacy.getGuarantee().get(i).getValue());
            }
            for (i = 0; i < scenario_GameData_Diplomacy.getPacts().size(); ++i) {
                CFG.core.setCivNonAggressionPact(scenario_GameData_Diplomacy.getPacts().get(i).getCivA(), scenario_GameData_Diplomacy.getPacts().get(i).getCivB(), scenario_GameData_Diplomacy.getPacts().get(i).getValue());
            }
            for (i = 0; i < scenario_GameData_Diplomacy.getDefensivePacts().size(); ++i) {
                CFG.core.setDefensivePact(scenario_GameData_Diplomacy.getDefensivePacts().get(i).getCivA(), scenario_GameData_Diplomacy.getDefensivePacts().get(i).getCivB(), scenario_GameData_Diplomacy.getDefensivePacts().get(i).getValue());
            }
            for (i = 0; i < scenario_GameData_Diplomacy.getTruces().size(); ++i) {
                CFG.core.setCivTruce(scenario_GameData_Diplomacy.getTruces().get(i).getCivA(), scenario_GameData_Diplomacy.getTruces().get(i).getCivB(), scenario_GameData_Diplomacy.getTruces().get(i).getValue());
            }
            scenario_GameData_Diplomacy = null;
        }
        catch (ClassNotFoundException e) {
            CFG.toastM.addM("Error - Diplomacy Data");
        }
        catch (IOException iOException) {
        }
        catch (Exception exception) {
            // empty catch block
        }
        GameManager.buildFriendlyCivs();
    }

    public final void loadArmiesData() {
        int i;
        for (i = 0; i < CFG.core.getProvinSize(); ++i) {
            CFG.core.getProv(i).resetArmiesNewGame(0);
            if (CFG.core.getProv(i).getSeaProv() || CFG.core.getProv(i).getWastelandLvl() >= 0) continue;
            if (CFG.core.getProv(i).getCivId() == 0) {
                CFG.core.getProv(i).updateArmy4(this.getScenario_NeutralArmy());
                continue;
            }
            if (!CFG.core.getProv(i).isCapital()) continue;
            if (CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)CFG.core.getProv((int)i).getCivId()).getIdeology()).CAN_BECOME_CIVILIZED >= 0) {
                CFG.core.getProv(i).updateArmy4(this.getScenario_StartingArmyInCapitals() / 10);
                continue;
            }
            CFG.core.getProv(i).updateArmy4(this.getScenario_StartingArmyInCapitals());
        }
        for (i = 0; i < CFG.core.getProvinSize(); ++i) {
            CFG.core.getProv((int)i).provinceVolunteerArmySent.clear();
        }
        try {
            FileHandle file;
            if (this.isInternal.get(CFG.core.getScenarioID()).booleanValue()) {
                file = FileManager.loadFile("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "_A");
            } else {
                try {
                    file = Gdx.files.local("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "_A");
                }
                catch (Exception ex) {
                    file = FileManager.loadFile("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "_A");
                }
            }
            try {
                Scenario_GameData_Armies scenario_GameData_Armies = (Scenario_GameData_Armies)CFG.deserialize(file.readBytes());
                int iSize = scenario_GameData_Armies.lArmies.size();
                for (int i2 = 0; i2 < iSize; ++i2) {
                    try {
                        if (CFG.core.getProv(scenario_GameData_Armies.lArmies.get(i2).getProvinceID()).getWastelandLvl() >= 0 || CFG.core.getProv(scenario_GameData_Armies.lArmies.get(i2).getProvinceID()).getCivId() != scenario_GameData_Armies.lArmies.get(i2).getCivID() && !CFG.core.getProv(scenario_GameData_Armies.lArmies.get(i2).getProvinceID()).getSeaProv() && (CFG.core.getCiv(scenario_GameData_Armies.lArmies.get(i2).getCivID()).getAlliance() <= 0 || CFG.core.getCiv(scenario_GameData_Armies.lArmies.get(i2).getCivID()).getAlliance() > 0 != CFG.core.getCiv(CFG.core.getProv(scenario_GameData_Armies.lArmies.get(i2).getProvinceID()).getCivId()).getAlliance() > 0) && CFG.core.getCiv(CFG.core.getProv(scenario_GameData_Armies.lArmies.get(i2).getProvinceID()).getCivId()).getPuppetOfCiv() != scenario_GameData_Armies.lArmies.get(i2).getCivID() && CFG.core.getProv(scenario_GameData_Armies.lArmies.get(i2).getProvinceID()).getCivId() != CFG.core.getCiv(scenario_GameData_Armies.lArmies.get(i2).getCivID()).getPuppetOfCiv() && CFG.core.getMilitaryAccess(scenario_GameData_Armies.lArmies.get(i2).getCivID(), CFG.core.getProv(scenario_GameData_Armies.lArmies.get(i2).getProvinceID()).getCivId()) <= 0) continue;
                        CFG.core.getProv(scenario_GameData_Armies.lArmies.get(i2).getProvinceID()).updateArmy4(scenario_GameData_Armies.lArmies.get(i2).getCivID(), scenario_GameData_Armies.lArmies.get(i2).getArmy());
                        continue;
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                }
                Object var2_4 = null;
            }
            catch (Exception exception) {}
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
    }

    public final void buildProvincePopulationAndEconomy(boolean loadCoresData, boolean nEditor) {
        int i;
        Random oR = new Random();
        CFG.core.getCiv(0).setTechLevel(0.1f);
        for (i = 0; i < CFG.core.getProvinSize(); ++i) {
            Province province = CFG.core.getProv(i);
            if (!province.getSeaProv()) {
                province.getPop().clearData();
                province.setEco(0);
                province.incomeTaxation = 1.0f;
                province.incomeProduction = 1.0f;
                province.administrationCost = 0.0f;
            }
            province.setIsPartOfHolyRomanEmpire(false);
            province.provGD.resetData();
        }
        for (i = 0; i < CFG.core.getProvinSize(); ++i) {
            CFG.core.getProv(i).buildProvinceCore();
        }
        if (loadCoresData) {
            CFG.core.getGameScenars().loadCoresData();
            for (i = 0; i < CFG.province_CoresGD.getProvincesSize(); ++i) {
                try {
                    if (CFG.core.getProv(CFG.province_CoresGD.lProvinces.get((int)i).iProvinceID).getSeaProv() || CFG.core.getProv(CFG.province_CoresGD.lProvinces.get((int)i).iProvinceID).getWastelandLvl() >= 0 || CFG.core.getProv(CFG.province_CoresGD.lProvinces.get((int)i).iProvinceID).getCivId() <= 0) continue;
                    for (int j = 0; j < CFG.province_CoresGD.lProvinces.get((int)i).lCores.size(); ++j) {
                        CFG.core.getProv(CFG.province_CoresGD.lProvinces.get((int)i).iProvinceID).getCores().addNewCore(CFG.province_CoresGD.lProvinces.get((int)i).lCores.get((int)j).iCivID, 1);
                    }
                    continue;
                }
                catch (Exception j) {
                    // empty catch block
                }
            }
        }
        if (CFG.province_CoresGD == null) {
            CFG.province_CoresGD = new Province_Cores_GameData();
        }
        for (i = 0; i < CFG.core.getProvinSize(); ++i) {
            Province province = CFG.core.getProv(i);
            if (province.getSeaProv()) continue;
            float tDevelopment = CFG.core.getCiv(province.getCivId()).getTechLevel();
            tDevelopment = tDevelopment * ((1.0f - CFG.gameAges.getAge_StartingDevelopment(GameCalendar.CURRENT_AGEID)) * (province.isCapital() ? 0.7646841f : 0.5746985f)) + tDevelopment * CFG.gameAges.getAge_StartingDevelopment(GameCalendar.CURRENT_AGEID) * province.getGrowthRate_Pop();
            if (province.getCivId() > 0) {
                tDevelopment = tDevelopment * (float)CFG.getCreateScenario_TechnologyLevelsByContinents_Continent(province.getCivId() - 1, province.getRegion()) / 100.0f;
            }
            province.setDevLvl(tDevelopment *= 0.875f + (float)CFG.oR.nextInt(2000) / 10000.0f + CFG.terrainTypesManager.getBaseDevelopmentModifier(province.getTerrainTypeID()));
            if (province.getCivId() == 0) {
                province.getPop().setPopulationOfCivID(province.getCivId(), (int)((float)this.getScenario_StartingPopulation() * 0.18275f * (province.getGrowthRate_Pop() * (1.0f + CFG.terrainTypesManager.getPopulationGrowth(province.getTerrainTypeID())))) + oR.nextInt(1 + (int)Math.ceil((float)this.getScenario_StartingPopulation() * ((float)oR.nextInt(25) / 100.0f) * (province.getGrowthRate_Pop() * (1.0f + CFG.terrainTypesManager.getPopulationGrowth(province.getTerrainTypeID()))))) / 4);
                province.setEco((int)((float)this.getScenario_StartingEconomy() * (0.05275f + (float)province.getNeighSeaProvincesSize() * 0.0015f) * (province.getGrowthRate_Pop() * (1.0f + CFG.terrainTypesManager.getEconomyGrowth(province.getTerrainTypeID())))) + oR.nextInt(1 + (int)Math.ceil((float)this.getScenario_StartingEconomy() * ((float)oR.nextInt(10) / 100.0f) * (province.getGrowthRate_Pop() * (1.0f + CFG.terrainTypesManager.getEconomyGrowth(province.getTerrainTypeID())) * province.getDeveLvl()))));
                province.setHappi(0.48f + (float)oR.nextInt(2400) / 10000.0f);
            } else {
                if (province.getCores().getCivsSize() >= 1) {
                    int j;
                    int tempPop = (int)((float)((int)((float)this.getScenario_StartingPopulation() * (0.85f + (province.isCapital() ? 0.0725f : 0.0f)) * ((province.isCapital() ? Math.max(0.2675f, province.getGrowthRate_Pop()) : province.getGrowthRate_Pop()) * (1.0f + CFG.terrainTypesManager.getPopulationGrowth(province.getTerrainTypeID())))) + oR.nextInt(1 + (int)Math.ceil((float)this.getScenario_StartingPopulation() * 0.15f * (province.getGrowthRate_Pop() * (1.0f + CFG.terrainTypesManager.getPopulationGrowth(province.getTerrainTypeID())))))) * (CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)province.getCivId()).getIdeology()).CAN_BECOME_CIVILIZED >= 0 ? (CFG.core.getCiv(province.getCivId()).getCapitalProvID() == i ? 0.4f : 0.275f) : 1.0f) * (0.725f + 0.275f * (float)CFG.getCreateScenario_TechnologyLevelsByContinents_Continent(province.getCivId() - 1, province.getRegion()) / 100.0f));
                    province.getPop().clearData();
                    for (j = 0; j < province.getCores().getCivsSize(); ++j) {
                        province.getPop().setPopulationOfCivID(province.getCores().getCivID(j), (int)((float)tempPop * CFG.province_CoresGD.getPercOfPop(i, province.getCores().getCivID(j))));
                    }
                    for (j = 0; j < province.getCores().getCivsSize() && j < 1; ++j) {
                        if (!(CFG.province_CoresGD.getPercOfPop(i, province.getCores().getCivID(j)) < 0.18f)) continue;
                        province.getCores().removeCore(province.getCores().getCivID(j));
                    }
                } else {
                    province.getPop().setPopulationOfCivID(province.getCivId(), (int)((float)((int)((float)this.getScenario_StartingPopulation() * (0.85f + (province.isCapital() ? 0.05f : 0.0f)) * (province.getGrowthRate_Pop() * (1.0f + CFG.terrainTypesManager.getPopulationGrowth(province.getTerrainTypeID())))) + oR.nextInt(1 + (int)Math.ceil((float)this.getScenario_StartingPopulation() * 0.15f * (province.getGrowthRate_Pop() * (1.0f + CFG.terrainTypesManager.getPopulationGrowth(province.getTerrainTypeID())))))) * (CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)province.getCivId()).getIdeology()).CAN_BECOME_CIVILIZED >= 0 ? (CFG.core.getCiv(province.getCivId()).getCapitalProvID() == i ? 0.4f : 0.275f) : 1.0f) * (0.725f + 0.275f * (float)CFG.getCreateScenario_TechnologyLevelsByContinents_Continent(province.getCivId() - 1, province.getRegion()) / 100.0f)));
                }
                province.setEco((int)((float)((int)((float)this.getScenario_StartingEconomy() * (province.getDeveLvl() * 1.064498f + (float)province.getNeighSeaProvincesSize() * 0.035f) * (province.getGrowthRate_Pop() * (1.0f + CFG.terrainTypesManager.getEconomyGrowth(province.getTerrainTypeID()))))) + (float)oR.nextInt(1 + Math.max((int)Math.ceil((float)this.getScenario_StartingEconomy() * (1.0f - province.getDeveLvl()) * (province.getGrowthRate_Pop() * (1.0f + CFG.terrainTypesManager.getEconomyGrowth(province.getTerrainTypeID())) * province.getDeveLvl())), 0)) * (CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)province.getCivId()).getIdeology()).CAN_BECOME_CIVILIZED >= 0 ? (CFG.core.getCiv(province.getCivId()).getCapitalProvID() == i ? 0.95f : 0.725f) : 1.0f)));
                province.setHappi((float)(CFG.core.getCiv(province.getCivId()).getHappiness() + oR.nextInt(12) - 6) / 100.0f);
            }
            for (int j = 0; j < province.getNeighProvincesSize(); ++j) {
                if (CFG.core.getProv(province.getNeighProvinces(j)).getCivId() <= 0 || CFG.core.getProv(province.getNeighProvinces(j)).getCivId() == province.getCivId()) continue;
                province.getPop().setPopulationOfCivID(CFG.core.getProv(province.getNeighProvinces(j)).getCivId(), province.getPop().getPopulationOfCivID(CFG.core.getProv(province.getNeighProvinces(j)).getCivId()) + (int)((float)province.getPop().getPops() * (0.00874f + (float)CFG.oR.nextInt(345) / 10000.0f)));
            }
        }
        if (!nEditor) {
            for (i = 1; i < CFG.core.getCivsSize(); ++i) {
                if (CFG.core.getCiv(i).getNumOfProvs() <= 0 || CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)i).getIdeology()).CAN_BECOME_CIVILIZED < 0 || CFG.core.getCiv(i).getCapitalProvID() < 0) continue;
                for (int j = 0; j < CFG.core.getProv(CFG.core.getCiv(i).getCapitalProvID()).getNeighProvincesSize(); ++j) {
                    if (CFG.core.getProv(CFG.core.getProv(CFG.core.getCiv(i).getCapitalProvID()).getNeighProvinces(j)).getWastelandLvl() >= 0 || CFG.core.getProv(CFG.core.getProv(CFG.core.getCiv(i).getCapitalProvID()).getNeighProvinces(j)).getCivId() != 0 && CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)CFG.core.getProv((int)CFG.core.getProv((int)CFG.core.getCiv((int)i).getCapitalProvID()).getNeighProvinces((int)j)).getCivId()).getIdeology()).CAN_BECOME_CIVILIZED < 0) continue;
                    CFG.core.getProv(CFG.core.getProv(CFG.core.getCiv(i).getCapitalProvID()).getNeighProvinces(j)).getCores().addNewCore(i, 1);
                }
                int tRan = CFG.oR.nextInt(GameValues.gvMigrate.CAN_MIGRATE_EVERY_X_TURNS);
                for (int a = 0; a < tRan; ++a) {
                    CFG.core.getProv(CFG.core.getCiv(i).getCapitalProvID()).getCores().increaseOwnership(i, CFG.core.getCiv(i).getCapitalProvID());
                }
            }
        }
        CFG.province_CoresGD = null;
        Core.addSimpleTask(new Core.SimpleTask("updateCitiesAll"){

            @Override
            public void update() {
                CitiesManager.updateCitiesAll();
            }
        });
    }

    public final void disableFillTheMap() {
        int i;
        for (i = 0; i < CFG.core.getProvinSize(); ++i) {
            if (CFG.core.getProv(i).isCapital()) continue;
            CFG.core.getProv(i).setCivId_LoadScenario(0);
            CFG.core.getProv(i).setCivRegionID(-1);
        }
        for (i = 1; i < CFG.core.getCivsSize(); ++i) {
            CFG.core.getCiv(i).clearProvinces_FillTheMap(CFG.core.getCiv(i).getNumOfProvs() > 0);
        }
        for (i = 0; i < CFG.core.getProvinSize(); ++i) {
            for (int j = 0; j < CFG.core.getProv(i).getProvinceBordersLandByLandSize(); ++j) {
                CFG.core.getProv(i).getProvBordersLandByLand().get(j).setIsCivilizationBorder(false, i);
            }
        }
        for (i = 1; i < CFG.core.getCivsSize(); ++i) {
            CFG.core.getProv(CFG.core.getCiv(i).getCapitalProvID()).updateProvinceBorder();
        }
        CFG.core.buildCivilizationsRegions();
        CFG.map.getMpB().disposeMinimapOfCivilizations();
    }

    public final void enableFillTheMap() {
        for (int i = 1; i < CFG.core.getCivsSize(); ++i) {
            CFG.core.getCiv(i).clearProvinces_FillTheMap(false);
        }
        FileHandle file = FileManager.loadFile("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()));
        FileHandle fileProvince = FileManager.loadFile("map/" + CFG.map.getFileActiveMapPath() + "scenarios/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "/" + this.lScenarios_TagsList.get(CFG.core.getScenarioID()) + "_PD");
        try {
            int i;
            Scenario_GameData tempScenarioGameData = (Scenario_GameData)CFG.deserialize(file.readBytes());
            for (int i2 = 0; i2 < tempScenarioGameData.getCivSize(); ++i2) {
                CFG.core.getCiv(i2 + 1).setCapitalProvID(tempScenarioGameData.getCivCapital(i2));
            }
            Scenario_GameData_Province2 scenario_GameData_Province = (Scenario_GameData_Province2)CFG.deserialize(fileProvince.readBytes());
            if (scenario_GameData_Province.getProvinceOwners() != null) {
                int iSize = scenario_GameData_Province.getProvinceOwners().size();
                for (i = 0; i < iSize; ++i) {
                    CFG.core.getProv(i).setCivId_LoadScenario(scenario_GameData_Province.getProvinceOwners().get(i));
                    CFG.core.getCiv(scenario_GameData_Province.getProvinceOwners().get(i)).addProv_Just(i);
                }
            }
            for (i = 0; i < CFG.core.getProvinSize(); ++i) {
                for (int j = 0; j < CFG.core.getProv(i).getProvinceBordersLandByLandSize(); ++j) {
                    CFG.core.getProv(i).getProvBordersLandByLand().get(j).setIsCivilizationBorder(CFG.core.getProv(i).getCivId() != CFG.core.getProv(CFG.core.getProv(i).getProvBordersLandByLand().get(j).getWithProvinceID()).getCivId(), i);
                }
            }
            CFG.core.buildCivilizationsRegions();
        }
        catch (Exception exception) {
            // empty catch block
        }
        CFG.map.getMpB().disposeMinimapOfCivilizations();
    }

    public final void editScenario(int iID) {
        GameCalendar.TURNID = 1;
        CFG.core.setScenarioID(iID);
        CFG.core.loadScenario(true);
        CFG.core.getGameScenars().loadCoresDataEditor();
        CFG.CREATE_SCENARIO_GAME_DATA_TAG = this.lScenarios_TagsList.get(CFG.core.getScenarioID());
        CFG.CREATE_SCENARIO_NAME = this.getScenarioNameID(CFG.core.getScenarioID());
        CFG.CREATE_SCENARIO_AUTHOR = this.getScenarioAuthorID(CFG.core.getScenarioID());
        CFG.CREATE_SCENARIO_AGE = this.getScenarioAgeID(CFG.core.getScenarioID());
        CFG.CREATE_SCENARIO_WIKI = this.getScenarioWiki(CFG.core.getScenarioID());
        GameCalendar.currYear = this.getScenarioYearID(CFG.core.getScenarioID());
        GameCalendar.currMonth = this.getScenarioMonth(CFG.core.getScenarioID());
        GameCalendar.currDay = this.getScenarioDay(CFG.core.getScenarioID());
    }

    public final int getScenarioNumOfCivs(int i) {
        try {
            return this.lScenarios_CivNum.get(i);
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
            return 0;
        }
    }

    public final void setNumOfCivs(int i, int nNumCivs) {
        try {
            this.lScenarios_CivNum.set(i, nNumCivs);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
    }

    public final String getScenarioNameID(int i) {
        return this.lScenarios_Names.get(i);
    }

    public final void setScenarioName(int i, String nName) {
        try {
            this.lScenarios_Names.set(i, nName);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
    }

    public final String getScenarioWiki(int i) {
        return this.lScenarios_Wikis.get(i);
    }

    public final String getScenarioAuthorID(int i) {
        return this.lScenarios_Authors.get(i);
    }

    public final void setScenarioAuthor(int i, String nAuthor) {
        try {
            this.lScenarios_Authors.set(i, nAuthor);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
    }

    public final String getScenarioTagID(int i) {
        return this.lScenarios_TagsList.get(i);
    }

    public final int getScenarioAgeID(int i) {
        return this.lScenarios_Age.get(i);
    }

    public final void setScenarioAge(int i, int nAge) {
        try {
            this.lScenarios_Age.set(i, nAge);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
    }

    public final int getScenarioYearID(int i) {
        return this.lScenarios_Year.get(i);
    }

    public final int getScenarioMonth(int i) {
        return this.lScenarios_Month.get(i);
    }

    public final int getScenarioDay(int i) {
        return this.lScenarios_Day.get(i);
    }

    public final void setScenarioDay(int i, int nDay) {
        try {
            this.lScenarios_Day.set(i, nDay);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
    }

    public final void setScenarioMonth(int i, int nMonth) {
        try {
            this.lScenarios_Month.set(i, nMonth);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
    }

    public final void setScenarioYear(int i, int nYear) {
        try {
            this.lScenarios_Year.set(i, nYear);
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
    }

    public final int getScenario_StartingArmyInCapitals() {
        return this.iScenario_StartingArmyInCapitals;
    }

    public final void setScenarioStartingArmyInCapitals(int iScenario_StartingArmyInCapitals) {
        this.iScenario_StartingArmyInCapitals = iScenario_StartingArmyInCapitals;
    }

    public final float getScenario_PopulationGrowthRate_Modifier() {
        return this.iScenario_PopulationGrowthRate_Modifier;
    }

    public final void setScenario_PopulationGrowthRate_Modifier(float iScenario_PopulationGrowthRate_Modifier) {
        this.iScenario_PopulationGrowthRate_Modifier = iScenario_PopulationGrowthRate_Modifier;
    }

    public final float getScenario_EconomyGrowthRate_Modifier() {
        return this.iScenario_EconomyGrowthRate_Modifier;
    }

    public final void setScenario_EconomyGrowthRate_Modifier(float iScenario_EconomyGrowthRate_Modifier) {
        this.iScenario_EconomyGrowthRate_Modifier = iScenario_EconomyGrowthRate_Modifier;
    }

    public final float getScenario_DiseasesDeathRate_Modifier() {
        return this.iScenario_DiseasesDeathRate_Modifier;
    }

    public final void setScenario_DiseasesDeathRate_Modifier(float iScenario_DiseasesDeathRate_Modifier) {
        this.iScenario_DiseasesDeathRate_Modifier = iScenario_DiseasesDeathRate_Modifier;
    }

    public final int getScenario_NeutralArmy() {
        return this.iScenario_NeutralArmy;
    }

    public final void setScenario_NeutralArmy(int iScenario_NeutralArmy) {
        this.iScenario_NeutralArmy = iScenario_NeutralArmy;
    }

    public final int getScenario_StartingPopulation() {
        return this.iScenario_StartingPopulation;
    }

    public final void setScenarioStartingPopulation(int iScenario_StartingPopulation) {
        this.iScenario_StartingPopulation = iScenario_StartingPopulation;
    }

    public final int getScenario_StartingEconomy() {
        return this.iScenario_StartingEconomy;
    }

    public final void setScenarioStartingEconomy(int iScenario_StartingEconomy) {
        this.iScenario_StartingEconomy = iScenario_StartingEconomy;
    }

    public final int getScenario_StartingMoney() {
        return this.iScenario_StartingMoney;
    }

    public final void setScenarioStartingMoney(int iScenario_StartingMoney) {
        this.iScenario_StartingMoney = iScenario_StartingMoney;
    }

    public final String getScenario_ActivePallet_TAG() {
        return this.sScenario_ActivePallet_TAG;
    }

    public void setScenarioActivePallet_TAG(String sScenario_ActivePallet_TAG) {
        this.sScenario_ActivePallet_TAG = sScenario_ActivePallet_TAG;
    }

    public final boolean getScenarioIsInternal(int i) {
        return this.isInternal.get(i);
    }

    public class RandomGame_AoCMode {
        public String sTag;
        public int iCapitalID = -1;

        public RandomGame_AoCMode(String sTag) {
            this.sTag = sTag;
            this.iCapitalID = -1;
        }

        public RandomGame_AoCMode(String sTag, int iCapitalID) {
            this.sTag = sTag;
            this.iCapitalID = iCapitalID;
        }
    }
}
