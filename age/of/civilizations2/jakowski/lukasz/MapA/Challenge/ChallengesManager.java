package age.of.civilizations2.jakowski.lukasz.MapA.Challenge;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Core.Core;
import age.of.civilizations2.jakowski.lukasz.Files.FileManager;
import age.of.civilizations2.jakowski.lukasz.GameCalendar;
import age.of.civilizations2.jakowski.lukasz.MapA.Challenge.Challenge;
import age.of.civilizations2.jakowski.lukasz.Z_Other.ST.sUM;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import java.util.ArrayList;
import java.util.List;

public class ChallengesManager {
    public static int START_CHALLENGE_ID = 0;
    public static int STARTED_CHALLENGE_ID = -1;
    public static List<Challenge> challengeList = new ArrayList<Challenge>();
    public static List<String> challengesCompleted = new ArrayList<String>();

    /*
     * Unable to fully structure code
     */
    public static void loadChallenges() {
        if (Gdx.files.internal("game/ChallengesD.txt").exists()) {
            return;
        }
        try {
            ChallengesManager.clearChallenges();
            if (Gdx.files.internal("map/" + CFG.map.getFileActiveMapPath() + "Challenges.json").exists()) {
                file = Gdx.files.internal("map/" + CFG.map.getFileActiveMapPath() + "Challenges.json");
                fileContent = file.readString();
                json = new Json();
                json.setElementType(ConfigChallengesData.class, "Challenge", Data_Challenges.class);
                data = new ConfigChallengesData();
                data = json.fromJson(ConfigChallengesData.class, fileContent);
                for (E e : data.Challenge) {
                    tempData = (Data_Challenges)e;
                    nChallenge = new Challenge();
                    nChallenge.ID = tempData.ID;
                    nChallenge.PLAY_AS = tempData.PLAY_AS;
                    nChallenge.FORM_TAG = tempData.FORM_TAG;
                    nChallenge.DESC = tempData.DESC;
                    nChallenge.SCENARIO_TAG = tempData.SCENARIO_TAG;
                    nChallenge.PROVINCES = tempData.PROVINCES;
                    nChallenge.PROVINCES_FORM = tempData.PROVINCES_FORM;
                    nChallenge.ADD_CIV_PROVINCES = tempData.ADD_CIV_PROVINCES;
                    if (CFG.core.getGameScenars().getScenarioIDbyTag(nChallenge.SCENARIO_TAG) >= 0) {
                        ChallengesManager.challengeList.add(nChallenge);
                        continue;
                    }
                    CFG.LOG(CFG.lang.get("Challenge") + ": ID: " + nChallenge.ID + ", PLAY_AS: " + nChallenge.PLAY_AS + ", FORM_TAG: " + nChallenge.FORM_TAG + " ### NOT ADDED!");
                }
            }
        }
        catch (Exception exr) {
            CFG.exceptionStack(exr);
        }
lbl32:
        // 4 sources

        try {
            try {
                for (i = 0; i < sUM.sUFS; ++i) {
                    block24: {
                        if (!FileManager.IS_MAC) break block24;
                        if (!Gdx.files.external(sUM.sUF.get(i) + "map/" + CFG.map.getFileActiveMapPath() + "Challenges.json").exists()) continue;
                        fileList = null;
                        fileList = Gdx.files.external(sUM.sUF.get(i) + "map/" + CFG.map.getFileActiveMapPath() + "Challenges.json");
                        fileContent = fileList.readString();
                        json = new Json();
                        json.setElementType(ConfigChallengesData.class, "Challenge", Data_Challenges.class);
                        data = new ConfigChallengesData();
                        data = json.fromJson(ConfigChallengesData.class, fileContent);
                        for (E e : data.Challenge) {
                            tempData = (Data_Challenges)e;
                            nChallenge = new Challenge();
                            nChallenge.ID = tempData.ID;
                            nChallenge.PLAY_AS = tempData.PLAY_AS;
                            nChallenge.FORM_TAG = tempData.FORM_TAG;
                            nChallenge.DESC = tempData.DESC;
                            nChallenge.SCENARIO_TAG = tempData.SCENARIO_TAG;
                            nChallenge.PROVINCES = tempData.PROVINCES;
                            nChallenge.PROVINCES_FORM = tempData.PROVINCES_FORM;
                            nChallenge.ADD_CIV_PROVINCES = tempData.ADD_CIV_PROVINCES;
                            if (CFG.core.getGameScenars().getScenarioIDbyTag(nChallenge.SCENARIO_TAG) >= 0) {
                                addC = true;
                                for (z = ChallengesManager.challengeList.size() - 1; z >= 0; --z) {
                                    if (!ChallengesManager.challengeList.get((int)z).FORM_TAG.equals(nChallenge.FORM_TAG)) continue;
                                    addC = false;
                                }
                                if (!addC) continue;
                                ChallengesManager.challengeList.add(nChallenge);
                                continue;
                            }
                            CFG.LOG(CFG.lang.get("Challenge") + ": ID: " + nChallenge.ID + ", PLAY_AS: " + nChallenge.PLAY_AS + ", FORM_TAG: " + nChallenge.FORM_TAG + " ### NOT ADDED!");
                        }
                        ** GOTO lbl32
                    }
                    if (!Gdx.files.internal(sUM.sUF.get(i) + "map/" + CFG.map.getFileActiveMapPath() + "Challenges.json").exists()) continue;
                    fileList = null;
                    fileList = Gdx.files.internal(sUM.sUF.get(i) + "map/" + CFG.map.getFileActiveMapPath() + "Challenges.json");
                    fileContent = fileList.readString();
                    json = new Json();
                    json.setElementType(ConfigChallengesData.class, "Challenge", Data_Challenges.class);
                    data = new ConfigChallengesData();
                    data = json.fromJson(ConfigChallengesData.class, fileContent);
                    for (E e : data.Challenge) {
                        tempData = (Data_Challenges)e;
                        nChallenge = new Challenge();
                        nChallenge.ID = tempData.ID;
                        nChallenge.PLAY_AS = tempData.PLAY_AS;
                        nChallenge.FORM_TAG = tempData.FORM_TAG;
                        nChallenge.DESC = tempData.DESC;
                        nChallenge.SCENARIO_TAG = tempData.SCENARIO_TAG;
                        nChallenge.PROVINCES = tempData.PROVINCES;
                        nChallenge.PROVINCES_FORM = tempData.PROVINCES_FORM;
                        nChallenge.ADD_CIV_PROVINCES = tempData.ADD_CIV_PROVINCES;
                        if (CFG.core.getGameScenars().getScenarioIDbyTag(nChallenge.SCENARIO_TAG) >= 0) {
                            addC = true;
                            for (z = ChallengesManager.challengeList.size() - 1; z >= 0; --z) {
                                if (!ChallengesManager.challengeList.get((int)z).FORM_TAG.equals(nChallenge.FORM_TAG)) continue;
                                addC = false;
                            }
                            if (!addC) continue;
                            ChallengesManager.challengeList.add(nChallenge);
                            continue;
                        }
                        CFG.LOG(CFG.lang.get("Challenge") + ": ID: " + nChallenge.ID + ", PLAY_AS: " + nChallenge.PLAY_AS + ", FORM_TAG: " + nChallenge.FORM_TAG + " ### NOT ADDED!");
                    }
                    ** GOTO lbl32
                }
            }
            catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
            try {
                for (i = 0; i < sUM.sUII.size(); ++i) {
                    if (!Gdx.files.absolute(sUM.sUII.get(i).getFolder() + "/" + "map/" + CFG.map.getFileActiveMapPath() + "Challenges.json").exists()) continue;
                    fileList = null;
                    fileList = Gdx.files.absolute(sUM.sUII.get(i).getFolder() + "/" + "map/" + CFG.map.getFileActiveMapPath() + "Challenges.json");
                    fileContent = fileList.readString();
                    json = new Json();
                    json.setElementType(ConfigChallengesData.class, "Challenge", Data_Challenges.class);
                    data = new ConfigChallengesData();
                    data = json.fromJson(ConfigChallengesData.class, fileContent);
                    for (E e : data.Challenge) {
                        tempData = (Data_Challenges)e;
                        nChallenge = new Challenge();
                        nChallenge.ID = tempData.ID;
                        nChallenge.PLAY_AS = tempData.PLAY_AS;
                        nChallenge.FORM_TAG = tempData.FORM_TAG;
                        nChallenge.DESC = tempData.DESC;
                        nChallenge.SCENARIO_TAG = tempData.SCENARIO_TAG;
                        nChallenge.PROVINCES = tempData.PROVINCES;
                        nChallenge.PROVINCES_FORM = tempData.PROVINCES_FORM;
                        nChallenge.ADD_CIV_PROVINCES = tempData.ADD_CIV_PROVINCES;
                        if (CFG.core.getGameScenars().getScenarioIDbyTag(nChallenge.SCENARIO_TAG) >= 0) {
                            addC = true;
                            for (z = ChallengesManager.challengeList.size() - 1; z >= 0; --z) {
                                if (!ChallengesManager.challengeList.get((int)z).FORM_TAG.equals(nChallenge.FORM_TAG)) continue;
                                addC = false;
                            }
                            if (!addC) continue;
                            ChallengesManager.challengeList.add(nChallenge);
                            continue;
                        }
                        CFG.LOG(CFG.lang.get("Challenge") + ": ID: " + nChallenge.ID + ", PLAY_AS: " + nChallenge.PLAY_AS + ", FORM_TAG: " + nChallenge.FORM_TAG + " ### NOT ADDED!");
                    }
                }
            }
            catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
        catch (Exception var0_6) {
            // empty catch block
        }
        ChallengesManager.readChallengesCompleted();
    }

    public static void readChallengesCompleted() {
        try {
            challengesCompleted.clear();
            FileHandle fileCC = null;
            fileCC = CFG.readLocalFiles() ? Gdx.files.local("saves/games/" + CFG.map.getFileActiveMapPath() + "ChallengesCompleted.txt") : FileManager.loadFile("saves/games/" + CFG.map.getFileActiveMapPath() + "ChallengesCompleted.txt");
            if (fileCC.exists()) {
                int i;
                String tempT = fileCC.readString();
                String[] tagsSPLITED = tempT.split(";");
                for (i = tagsSPLITED.length - 1; i >= 0; --i) {
                    challengesCompleted.add(tagsSPLITED[i]);
                }
                block3: for (i = challengesCompleted.size() - 1; i >= 0; --i) {
                    for (int j = challengeList.size() - 1; j >= 0; --j) {
                        if (!challengesCompleted.get(i).equals(ChallengesManager.challengeList.get((int)j).FORM_TAG)) continue;
                        ChallengesManager.challengeList.get((int)j).COMPLETED = true;
                        continue block3;
                    }
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static void addChallengeCompleted(String tag) {
        try {
            boolean added = false;
            FileHandle fileCC = null;
            fileCC = CFG.readLocalFiles() ? Gdx.files.local("saves/games/" + CFG.map.getFileActiveMapPath() + "ChallengesCompleted.txt") : FileManager.loadFile("saves/games/" + CFG.map.getFileActiveMapPath() + "ChallengesCompleted.txt");
            if (fileCC.exists()) {
                String tempT = fileCC.readString();
                String[] tagsSPLITED = tempT.split(";");
                for (int i = tagsSPLITED.length - 1; i >= 0; --i) {
                    if (!tagsSPLITED[i].equals(tag)) continue;
                    added = true;
                }
            }
            if (!added) {
                FileHandle fileSave = FileManager.getSaveType("saves/games/" + CFG.map.getFileActiveMapPath() + "ChallengesCompleted.txt");
                fileSave.writeString(tag + ";", true);
            }
            ChallengesManager.readChallengesCompleted();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static void clearChallenges() {
        challengeList.clear();
        challengesCompleted.clear();
    }

    public static int getChallengeCivID() {
        int challengeCivID = 0;
        for (int a = 1; a < CFG.core.getCivsSize(); ++a) {
            if (!CFG.core.getCiv(a).getCivTag().equals(ChallengesManager.challengeList.get((int)ChallengesManager.START_CHALLENGE_ID).PLAY_AS)) continue;
            challengeCivID = a;
            break;
        }
        if (challengeCivID == 0) {
            String tReal = CFG.ideologiesMgr.getRealTag(ChallengesManager.challengeList.get((int)ChallengesManager.START_CHALLENGE_ID).PLAY_AS);
            for (int a = 1; a < CFG.core.getCivsSize(); ++a) {
                if (!CFG.core.getCiv(a).getCivTag().equals(tReal)) continue;
                challengeCivID = a;
                break;
            }
        }
        return challengeCivID;
    }

    public static void addCivilization() {
        if (ChallengesManager.challengeList.get((int)ChallengesManager.START_CHALLENGE_ID).ADD_CIV_PROVINCES == null || ChallengesManager.challengeList.get((int)ChallengesManager.START_CHALLENGE_ID).ADD_CIV_PROVINCES.length == 0) {
            return;
        }
        try {
            ArrayList<Integer> rebuildRegionsCivs = new ArrayList<Integer>();
            String civTag = ChallengesManager.challengeList.get((int)ChallengesManager.START_CHALLENGE_ID).PLAY_AS;
            int provinceID = ChallengesManager.challengeList.get((int)ChallengesManager.START_CHALLENGE_ID).ADD_CIV_PROVINCES[0];
            if (CFG.core.getProv(provinceID).getCivId() > 0) {
                rebuildRegionsCivs.add(CFG.core.getProv(provinceID).getCivId());
            }
            if (CFG.core.createScenarioAddCivilization(civTag, provinceID, false, true, false)) {
                int civID = -1;
                for (int a = CFG.core.getCivsSize() - 1; a >= 0; --a) {
                    if (!CFG.core.getCiv(a).getCivTag().equals(civTag)) continue;
                    civID = a;
                    break;
                }
                if (civID >= 0) {
                    int i;
                    float averageTech = 0.0f;
                    for (i = 1; i < CFG.core.getCivsSize(); ++i) {
                        averageTech += CFG.core.getCiv(i).getTechLevel();
                    }
                    CFG.core.getCiv(civID).setTechLevel(Math.max(0.1f, averageTech /= (float)(CFG.core.getCivsSize() - 1)));
                    CFG.core.getCiv(civID).setGold(CFG.core.getGameScenars().getScenario_StartingMoney());
                    CFG.core.getProv(provinceID).removeArmies();
                    for (int a = CFG.core.getProv(provinceID).getPop().getNatsSize() - 1; a >= 0; --a) {
                        CFG.core.getProv(provinceID).getPop().setPopulationOfCivID(civID, CFG.core.getProv(provinceID).getPop().getPopulationOfCivID(civID) + CFG.core.getProv(provinceID).getPop().getPopulationID(a));
                        if (CFG.core.getProv(provinceID).getPop().getCivID(a) == civID) continue;
                        CFG.core.getProv(provinceID).getPop().setPopulationOfCivID(CFG.core.getProv(provinceID).getPop().getCivID(a), 0);
                    }
                    try {
                        for (i = ChallengesManager.challengeList.get((int)ChallengesManager.START_CHALLENGE_ID).ADD_CIV_PROVINCES.length - 1; i >= 0; --i) {
                            CFG.core.getProv(ChallengesManager.challengeList.get((int)ChallengesManager.START_CHALLENGE_ID).ADD_CIV_PROVINCES[i]).setTrueOwnerOfProv(civID);
                            CFG.core.getProv(ChallengesManager.challengeList.get((int)ChallengesManager.START_CHALLENGE_ID).ADD_CIV_PROVINCES[i]).setCivId(civID, false, false);
                            CFG.core.getProv(ChallengesManager.challengeList.get((int)ChallengesManager.START_CHALLENGE_ID).ADD_CIV_PROVINCES[i]).getCores().addNewCore(civID, GameCalendar.TURNID);
                            CFG.core.getProv(ChallengesManager.challengeList.get((int)ChallengesManager.START_CHALLENGE_ID).ADD_CIV_PROVINCES[i]).removeArmies();
                            for (int a = CFG.core.getProv(ChallengesManager.challengeList.get((int)ChallengesManager.START_CHALLENGE_ID).ADD_CIV_PROVINCES[i]).getPop().getNatsSize() - 1; a >= 0; --a) {
                                CFG.core.getProv(ChallengesManager.challengeList.get((int)ChallengesManager.START_CHALLENGE_ID).ADD_CIV_PROVINCES[i]).getPop().setPopulationOfCivID(civID, CFG.core.getProv(ChallengesManager.challengeList.get((int)ChallengesManager.START_CHALLENGE_ID).ADD_CIV_PROVINCES[i]).getPop().getPopulationOfCivID(civID) + CFG.core.getProv(ChallengesManager.challengeList.get((int)ChallengesManager.START_CHALLENGE_ID).ADD_CIV_PROVINCES[i]).getPop().getPopulationID(a));
                                if (CFG.core.getProv(ChallengesManager.challengeList.get((int)ChallengesManager.START_CHALLENGE_ID).ADD_CIV_PROVINCES[i]).getPop().getCivID(a) == civID) continue;
                                CFG.core.getProv(ChallengesManager.challengeList.get((int)ChallengesManager.START_CHALLENGE_ID).ADD_CIV_PROVINCES[i]).getPop().setPopulationOfCivID(CFG.core.getProv(ChallengesManager.challengeList.get((int)ChallengesManager.START_CHALLENGE_ID).ADD_CIV_PROVINCES[i]).getPop().getCivID(a), 0);
                            }
                        }
                    }
                    catch (Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                    try {
                        Core.addSimpleTask(new Core.SimpleTask("rebuildRegionsCivs" + civID, civID){

                            @Override
                            public void update() {
                                CFG.core.buildCivilizationRegions(this.id);
                            }
                        });
                        for (int a = 0; a < rebuildRegionsCivs.size(); ++a) {
                            Core.addSimpleTask(new Core.SimpleTask("rebuildRegionsCivs" + a, (Integer)rebuildRegionsCivs.get(a)){

                                @Override
                                public void update() {
                                    CFG.core.buildCivilizationRegions(this.id);
                                }
                            });
                        }
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                    CFG.toastM.addM(CFG.lang.get("Added") + ": " + CFG.core.getCiv(CFG.core.getProv(provinceID).getCivId()).getCivName());
                }
            }
            return;
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
            return;
        }
    }

    public static class ConfigChallengesData {
        public String Age_of_Civilizations;
        public ArrayList Challenge;
    }

    public static class Data_Challenges {
        public String ID;
        public String PLAY_AS;
        public String FORM_TAG;
        public String DESC;
        public String SCENARIO_TAG;
        public int PROVINCES;
        public int PROVINCES_FORM;
        public int[] ADD_CIV_PROVINCES;
    }
}
