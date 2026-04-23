package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Civilization;
import age.of.civilizations2.jakowski.lukasz.Core.Core;
import age.of.civilizations2.jakowski.lukasz.Diplomacy.Festivals.Festival;
import age.of.civilizations2.jakowski.lukasz.GameCalendar;
import age.of.civilizations2.jakowski.lukasz.GameManager;
import age.of.civilizations2.jakowski.lukasz.GameN;
import age.of.civilizations2.jakowski.lukasz.GameValues.GameValues;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MapA.CitiesManager;
import age.of.civilizations2.jakowski.lukasz.Menus.ZRest.Menu_InGame_2;
import age.of.civilizations2.jakowski.lukasz.Messages.Civilization.Message_MilitaryExpPoints;
import age.of.civilizations2.jakowski.lukasz.Messages.DefensivePact.Response.Message_DefensivePact_Expired;
import age.of.civilizations2.jakowski.lukasz.Messages.Guarantee.Message_IndependenceFrom_Expired;
import age.of.civilizations2.jakowski.lukasz.Messages.Guarantee.Message_Independence_Expired;
import age.of.civilizations2.jakowski.lukasz.Messages.Info.Message_GoodsLow;
import age.of.civilizations2.jakowski.lukasz.Messages.Info.Message_InvestmentsLow;
import age.of.civilizations2.jakowski.lukasz.Messages.Info.Message_RelocateCapital;
import age.of.civilizations2.jakowski.lukasz.Messages.Info.Message_TechPoints;
import age.of.civilizations2.jakowski.lukasz.Messages.Invest.Message_InvestBuildDoneForeign;
import age.of.civilizations2.jakowski.lukasz.Messages.Invest.Message_InvestDoneForeign;
import age.of.civilizations2.jakowski.lukasz.Messages.Message_MigrationComplete;
import age.of.civilizations2.jakowski.lukasz.Messages.MilitaryAccess.Message_MilitaryAccess_ExpireSoon;
import age.of.civilizations2.jakowski.lukasz.Messages.MilitaryAccess.Message_MilitaryAccess_Expired;
import age.of.civilizations2.jakowski.lukasz.Messages.NonAggression.Message_NonAggressionPact_Expired;
import age.of.civilizations2.jakowski.lukasz.Messages.Province.Message_ProvincesOccupiedNotAtWar_LostControl;
import age.of.civilizations2.jakowski.lukasz.Messages.Relations.Summit.Message_SummitIsOver;
import age.of.civilizations2.jakowski.lukasz.Messages.Relations.Vassal.Message_VassalHighLiberty;
import age.of.civilizations2.jakowski.lukasz.Messages.Truce.Message_Truce_Expired;
import age.of.civilizations2.jakowski.lukasz.Messages.War.Message_War;
import age.of.civilizations2.jakowski.lukasz.NewGameManager;
import age.of.civilizations2.jakowski.lukasz.PopulationGrowth;
import age.of.civilizations2.jakowski.lukasz.Province;
import age.of.civilizations2.jakowski.lukasz.Save.SaveGameManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class NewTurn
extends Thread {
    private static long tempTime;
    private static long tempTimeTotal;
    public static float ageRiskModifier;
    public static float ageDevMod;
    public static List<PopulationGrowth> tempCivs;
    public static List<Float> happinessChange_ByTaxation;
    public static List<Float> happinessChange_ByTaxation_Occupied;
    public static List<Float> goodsUpdate;
    public static List<Float> devUpdate;
    public static List<Float> ecoUpdate;

    @Override
    public void run() {
        NewTurn.doAction();
    }

    public static void checkOccupiedProvincesIfAreAtWar() {
        if (GameCalendar.TURNID % GameValues.gvProvince.TURNS_BETWEEN_RETURNING_PEACEFUL_OCCUPIED_PROVINCES == 0) {
            for (int i = 0; i < CFG.core.getProvinSize(); ++i) {
                int j;
                if (CFG.core.getProv(i).getSeaProv() || CFG.core.getProv(i).getWastelandLvl() >= 0 || !CFG.core.getProv(i).isOccupied() || CFG.core.getCiv(CFG.core.getProv(i).getCivId()).getIdeology() == CFG.ideologiesMgr.REBELS_ID || CFG.core.getCivsAtWar(CFG.core.getProv(i).getCivId(), CFG.core.getProv(i).getTrueOwnerOfProv()) || CFG.core.getCiv((int)CFG.core.getProv((int)i).getCivId()).civGD.civPlans.isPreparingForTheWar(CFG.core.getProv(i).getTrueOwnerOfProv()) || CFG.core.getCiv((int)CFG.core.getProv((int)i).getTrueOwnerOfProv()).civGD.civPlans.isPreparingForTheWar(CFG.core.getProv(i).getCivId())) continue;
                if (CFG.core.getCiv(CFG.core.getProv(i).getCivId()).getIsPlayer()) {
                    CFG.core.getCiv((int)CFG.core.getProv((int)i).getCivId()).getCivDiploGD().messageBox.addMessage(new Message_ProvincesOccupiedNotAtWar_LostControl(CFG.core.getProv(i).getTrueOwnerOfProv(), i));
                }
                int tempArmy0 = CFG.core.getProv(i).getArmyID(0);
                int tempCiv0 = CFG.core.getProv(i).getCivId();
                int tempArmyNewOwner = CFG.core.getProv(i).getArmyCivID1(CFG.core.getProv(i).getTrueOwnerOfProv());
                CFG.core.getProv(i).updateArmy4(0);
                CFG.core.getProv(i).setCivId(CFG.core.getProv(i).getTrueOwnerOfProv(), false);
                CFG.core.getProv(i).updateArmy4(tempCiv0, tempArmy0);
                CFG.core.getProv(i).updateArmy4(CFG.core.getProv(i).getTrueOwnerOfProv(), tempArmyNewOwner);
                ArrayList<Integer> tempCivsLostAccess = new ArrayList<Integer>();
                for (j = 0; j < CFG.core.getProv(i).getCivsSize(); ++j) {
                    tempCivsLostAccess.add(CFG.core.getProv(i).getCivId(j));
                }
                for (j = 0; j < tempCivsLostAccess.size(); ++j) {
                    if (CFG.core.getCiv((Integer)tempCivsLostAccess.get(j)).getPuppetOfCiv() == CFG.core.getProv(i).getTrueOwnerOfProv() || CFG.core.getCiv(CFG.core.getProv(i).getTrueOwnerOfProv()).getPuppetOfCiv() == ((Integer)tempCivsLostAccess.get(j)).intValue() || CFG.core.getCiv((Integer)tempCivsLostAccess.get(j)).getAlliance() > 0 && CFG.core.getCiv((Integer)tempCivsLostAccess.get(j)).getAlliance() == CFG.core.getCiv(CFG.core.getProv(i).getTrueOwnerOfProv()).getAlliance() || CFG.core.getMilitaryAccess((Integer)tempCivsLostAccess.get(j), CFG.core.getProv(i).getTrueOwnerOfProv()) > 0) continue;
                    CFG.gameAction.accessLost_MoveArmyToClosetsProvince((Integer)tempCivsLostAccess.get(j), i);
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static final void doAction() {
        try {
            int i;
            int i2;
            CFG.gameUpdate.updateCivs_Money();
            for (i2 = 1; i2 < CFG.core.getCivsSize(); ++i2) {
                Civilization civI = CFG.core.getCiv(i2);
                civI.runFestivals();
                civI.runInvests_Development();
                civI.runInvests();
                civI.runAssimilates();
                civI.runWarReparations();
            }
            CFG.plagueManager.runPlagues();
            NewTurn.updateCapitulation();
            NewTurn.checkOccupiedProvincesIfAreAtWar();
            NewTurn.updateGameData();
            for (i2 = 1; i2 < CFG.core.getCivsSize(); ++i2) {
                CFG.core.getCiv((int)i2).getCivDiploGD().messageBox.updateNextTurn(i2);
            }
            CFG.gameAction.updateCivsMovementPoints();
            CFG.gameAction.updateCivsDiploPoints();
            NewTurn.updateDiplomacy();
            if (GameValues.gvUpdate.USE_OLD_CIV_HAPPINESS_UPDATE) {
                CFG.gameAction.updateCivsHappiness_AllCivs();
            } else {
                CFG.gameAction.updateCivsHappiness_New();
            }
            if (GameValues.gvUpdate.USE_OLD_PROVINCE_STABILITY_UPDATE) {
                CFG.gameUpdate.updateProvinceStabilityAllProvinces();
            } else {
                CFG.gameUpdate.updatePrvStability();
            }
            CFG.gameUpdate.updateInflationPeakValueAllCivs();
            GameCalendar.updateDateNextTurn();
            NewTurn.updateBuildingsConstruction();
            NewTurn.updateWarWeariness();
            NewTurn.updateForeignInvests();
            NewTurn.updateForeignBuildInvests();
            NewTurn.updateDiplomaticSummits();
            NewTurn.updatePropaganda();
            NewTurn.updateSanctions();
            NewTurn.updateLibertyDesireMessages();
            for (i2 = 1; i2 < CFG.core.getCivsSize(); ++i2) {
                if (CFG.core.getCiv(i2).getNumOfProvs() <= 0) continue;
                for (int j = CFG.core.getCiv((int)i2).provincesWithLowStability.size() - 1; j >= 0; --j) {
                    if (!(CFG.core.getProv(CFG.core.getCiv((int)i2).provincesWithLowStability.get(j)).getProviStability() < GameValues.gvRebels.RISE_REVOLT_RISK_IN_PROVINCE_IF_STABILITY_BELOW) || CFG.core.getProv(CFG.core.getCiv((int)i2).provincesWithLowStability.get(j)).isOccupied() || !(CFG.core.getProv(CFG.core.getCiv((int)i2).provincesWithLowStability.get(j)).getRevRisk() < 0.55f)) continue;
                    CFG.core.getProv(CFG.core.getCiv((int)i2).provincesWithLowStability.get(j)).setRevRisk(CFG.core.getProv(CFG.core.getCiv((int)i2).provincesWithLowStability.get(j)).getRevRisk() + ageRiskModifier * (GameValues.gvRebels.RISE_REVOLT_RISK_IN_PROVINCE_IF_STABILITY_BELOW - CFG.core.getProv(CFG.core.getCiv((int)i2).provincesWithLowStability.get(j)).getProviStability()) * 0.0155f);
                }
            }
            if (!CFG.SPECTATOR_MODE) {
                for (i2 = 0; i2 < CFG.core.getPlayersSize(); ++i2) {
                    try {
                        if (CFG.core.getCiv(CFG.core.getPlayer(i2).getCivId()).getNumOfProvs() <= 0) continue;
                        CFG.core.getPlayer((int)i2).statsCiv.setTurns(CFG.core.getPlayer((int)i2).statsCiv.getTurns() + 1);
                        continue;
                    }
                    catch (NullPointerException ex) {
                        CFG.core.getPlayer(i2).tryLoadStats();
                    }
                }
            }
            try {
                for (i2 = 1; i2 < CFG.core.getCivsSize(); ++i2) {
                    CFG.core.getCiv(i2).updateBonuses();
                }
            }
            catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
            try {
                for (int i3 = 1; i3 < CFG.core.getCivsSize(); ++i3) {
                    CFG.core.getCiv(i3).updateGift_Received();
                }
            }
            catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
            try {
                GameManager.updateGoldenAge();
                GameManager.sendUncivilizedMessages();
                GameManager.sendLowHappiness();
            }
            catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
            if (!CFG.SPECTATOR_MODE) {
                for (int i4 = 0; i4 < CFG.core.getPlayersSize(); ++i4) {
                    if (CFG.core.getCiv(CFG.core.getPlayer(i4).getCivId()).getNumOfProvs() <= 0) continue;
                    for (int j = CFG.core.getCiv((int)CFG.core.getPlayer((int)i4).getCivId()).civGD.civPlans.iWarPrepsSize - 1; j >= 0; --j) {
                        if (--CFG.core.getCiv((int)CFG.core.getPlayer((int)i4).getCivId()).civGD.civPlans.warPreps.get((int)j).iNumOfTurnsLeft > 0) continue;
                        int tOnCivID = CFG.core.getCiv((int)CFG.core.getPlayer((int)i4).getCivId()).civGD.civPlans.warPreps.get((int)j).onCivID;
                        CFG.core.declareWar(CFG.core.getPlayer(i4).getCivId(), CFG.core.getCiv((int)CFG.core.getPlayer((int)i4).getCivId()).civGD.civPlans.warPreps.get((int)j).onCivID, false);
                        CFG.core.getCiv((int)CFG.core.getPlayer((int)i4).getCivId()).civGD.civDiploGD.messageBox.addMessage(new Message_War(tOnCivID, CFG.core.getPlayer(i4).getCivId()));
                        try {
                            CFG.core.getCiv((int)CFG.core.getPlayer((int)i4).getCivId()).civGD.civPlans.warPreps.remove(j);
                            CFG.core.getCiv((int)CFG.core.getPlayer((int)i4).getCivId()).civGD.civPlans.iWarPrepsSize = CFG.core.getCiv((int)CFG.core.getPlayer((int)i4).getCivId()).civGD.civPlans.warPreps.size();
                            continue;
                        }
                        catch (Exception exception) {
                            // empty catch block
                        }
                    }
                    if (CFG.core.getCiv(CFG.core.getPlayer(i4).getCivId()).getCapitalProvID() >= 0 && (CFG.core.getProv(CFG.core.getCiv(CFG.core.getPlayer(i4).getCivId()).getCapitalProvID()).getCivId() == CFG.core.getPlayer(i4).getCivId() || CFG.core.getProv(CFG.core.getCiv(CFG.core.getPlayer(i4).getCivId()).getCapitalProvID()).isOccupied())) continue;
                    CFG.core.getCiv((int)CFG.core.getPlayer((int)i4).getCivId()).civGD.civDiploGD.messageBox.addMessage(new Message_RelocateCapital(CFG.core.getPlayer(i4).getCivId()));
                }
            }
            CFG.gameAction.updateHRE_Elections();
            if (CFG.SANDBOX_MODE && !CFG.SPECTATOR_MODE) {
                CFG.gameNewGame.sandboxMode();
            }
            if (CFG.SANDBOX_MODE_AI) {
                NewGameManager.sandboxMode_AI();
            }
            for (i = 1; i < CFG.core.getCivsSize(); ++i) {
                CFG.core.getCiv(i).getCivDiploGD().updateEmbassyClosed();
                CFG.core.getCiv(i).getCivDiploGD().runImproveRelations(i);
            }
            CFG.gameAction.updateRelations();
            Core.updateOverInvestment();
            GameManager.checkCivsHatedCivilizations_IfStillExists();
            GameManager.updatePlayersFriendlyCivs();
            for (i = 0; i < CFG.core.getWarsSize(); ++i) {
                ++CFG.core.getWar((int)i).iLastFight_InTurns;
            }
            NewTurn.updateProvinceVolunteerArmySent();
            try {
                NewTurn.migr();
                GameN.updateLeaderDeath();
            }
            catch (Exception i5) {
                // empty catch block
            }
            try {
                NewTurn.updateAlliances();
            }
            catch (Exception i5) {
                // empty catch block
            }
            ++SaveGameManager.iTurnsSinceLastSave;
            if (SaveGameManager.gameWillBeSavedInThisTurn()) {
                SaveGameManager.trySaveGame();
            } else {
                NewTurn.doAction_End();
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
        finally {
            CFG.menus.getInGameProvInfo().getMenuElem(0).setClickable(true);
            Menu_InGame_2.TIME_CONTINUE = System.currentTimeMillis();
        }
    }

    public static final void migr() {
        try {
            for (int i = 0; i < CFG.core.getPlayersSize(); ++i) {
                if (CFG.core.getPlayer((int)i).playerGD.migrationF.isEmpty()) continue;
                for (int j = CFG.core.getPlayer((int)i).playerGD.migrationF.size() - 1; j >= 0; --j) {
                    int civ = CFG.core.getPlayer((int)i).playerGD.migrationF.get(j);
                    boolean remove = true;
                    for (int k = 0; k < CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getNumOfProvs(); ++k) {
                        for (int o = CFG.core.getProv(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getProvID(k)).getPop().getNatsSize() - 1; o >= 0; --o) {
                            int maxPop;
                            if (CFG.core.getProv(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getProvID(k)).getPop().getCivID(o) != civ || (maxPop = CFG.core.getProv(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getProvID(k)).getPop().getPopulationID(o)) <= 0) continue;
                            int popTM = (int)Math.min((float)maxPop, Math.max((float)GameValues.gvPopRelocate.MIGRATE_MIN, (float)maxPop * GameValues.gvPopRelocate.MIGRATE_PERC));
                            float perc = (float)popTM / (float)CFG.core.getProv(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getProvID(k)).getPop().getPops();
                            CFG.core.getProv(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getProvID(k)).setEco((int)((float)CFG.core.getProv(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getProvID(k)).getEco() * (1.0f - perc * GameValues.gvPopRelocate.MIGRATE_ECO_MODIFIER)));
                            CFG.core.getProv(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getProvID(k)).setRevRisk(CFG.core.getProv(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getProvID(k)).getRevRisk() + perc * GameValues.gvPopRelocate.MIGRATE_REV_RISK_MODIFIER);
                            CFG.core.getProv(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getProvID(k)).getPop().setPopulationOfCivID(civ, CFG.core.getProv(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getProvID(k)).getPop().getPopulationID(o) - popTM);
                            if (CFG.core.getCiv(civ).getNumOfProvs() > 0) {
                                int toPr = CFG.core.getCiv(civ).getProvID(CFG.oR.nextInt(CFG.core.getCiv(civ).getNumOfProvs()));
                                CFG.core.getProv(toPr).getPop().setPopulationOfCivID(civ, CFG.core.getProv(toPr).getPop().getPopulationOfCivID(civ) + popTM);
                            } else {
                                for (int y = 0; y < 25; ++y) {
                                    int rand = CFG.oR.nextInt(CFG.core.getProvinSize());
                                    if (CFG.core.getProv(rand).getSeaProv() || CFG.core.getProv(rand).getWastelandLvl() >= 0) continue;
                                    CFG.core.getProv(rand).getPop().setPopulationOfCivID(civ, CFG.core.getProv(rand).getPop().getPopulationOfCivID(civ) + popTM);
                                    break;
                                }
                            }
                            remove = false;
                        }
                    }
                    if (!remove) continue;
                    CFG.core.getCiv((int)CFG.core.getPlayer((int)i).getCivId()).getCivDiploGD().messageBox.addMessage(new Message_MigrationComplete(CFG.core.getPlayer((int)i).playerGD.migrationF.get(j)));
                    CFG.core.getPlayer((int)i).playerGD.migrationF.remove(j);
                }
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    public static final void doAction_End() {
        try {
            int i;
            tempTime = System.nanoTime();
            for (i = 0; i < CFG.core.getPlayersSize(); ++i) {
                CFG.core.getPlayer(CFG.PLAYER_TURN_ID).setNoOrders(true);
            }
            CFG.gameAction.moveRegroupArmy();
            for (i = 1; i < CFG.core.getCivsSize(); ++i) {
                if (!CFG.core.getCiv(i).getUpdateRegions()) continue;
                Core.addSimpleTask(new Core.SimpleTask("buildCivilizationRegions" + i, i){

                    @Override
                    public void update() {
                        try {
                            CFG.core.getCiv(this.id).setUpdateRegions(false);
                            CFG.core.buildCivilizationRegions(this.id);
                        }
                        catch (Exception exception) {
                            // empty catch block
                        }
                    }
                });
                Core.addSimpleTask(new Core.SimpleTask("buildNeighbors" + i, i){

                    @Override
                    public void update() {
                        try {
                            CFG.core.getCiv((int)this.id).civNeighbors.buildNeighbors(this.id);
                        }
                        catch (Exception exception) {
                            // empty catch block
                        }
                    }
                });
            }
            CFG.gameAction.updateIsSupplied();
            CFG.eventsManager.runEvents();
            ++GameCalendar.TURNS_SINCE_LAST_WAR;
            if (GameCalendar.TURNID % GameValues.gvUpdate.REBUILD_CIV_RANK_SCORES_EVERY_X_TURNS == 0) {
                CFG.gameAction.buildRank_Score();
            }
            CFG.historyManager.addNewTurn();
            CFG.timelapseManager.newTurn();
            CitiesManager.updateCities();
            CFG.gameAction.checkGameEnd();
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
        finally {
            CFG.menus.getInGameProvInfo().getMenuElem(0).setClickable(true);
            Menu_InGame_2.TIME_CONTINUE = System.currentTimeMillis();
        }
    }

    public static void updateNukes() {
        try {
            for (int i = 1; i < CFG.core.getCivsSize(); ++i) {
                if (CFG.core.getCiv((int)i).civGD.nukesConstruction.isEmpty()) continue;
                for (int a = CFG.core.getCiv((int)i).civGD.nukesConstruction.size() - 1; a >= 0; --a) {
                    CFG.core.getCiv((int)i).civGD.nukesConstruction.set(a, CFG.core.getCiv((int)i).civGD.nukesConstruction.get(a) - 1);
                    if (CFG.core.getCiv((int)i).civGD.nukesConstruction.get(a) > 0) continue;
                    CFG.core.getCiv((int)i).civGD.nukesConstruction.remove(a);
                    ++CFG.core.getCiv((int)i).civGD.iNukes;
                }
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    public static int getUpdateGameData_PopulationGrowth(int civID) {
        int out = 0;
        try {
            float civGoodsUpdate = NewTurn.getGoodsUpdate(civID);
            float modifiedStartingPop = (float)CFG.core.getGameScenars().getScenario_StartingPopulation() * GameValues.gvEconomy.POPULATION_GROWTH_STARTING_POPULATION_MODIFIER;
            for (int i = 0; i < CFG.core.getCiv(civID).getNumOfProvs(); ++i) {
                Province province = CFG.core.getProv(CFG.core.getCiv(civID).getProvID(i));
                float tempPopGrowth = (float)province.getPop().getPops() * (GameValues.gvPopulationGrowth.POP_GROWTH_BASE_VALUE + (CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(province.getCivId()).getIdeology()).getMin_Goods(province.getCivId()) < CFG.core.getCiv(province.getCivId()).getSpendingGoodsB() ? (float)CFG.oR.nextInt(GameValues.gvPopulationGrowth.POP_GROWTH_BASE_VALUE_GOODS_SPENDING_BELOW_RANDOM_100_MAX) / 100.0f : GameValues.gvPopulationGrowth.POP_GROWTH_BASE_VALUE_GOODS_SPENDING_OVER)) * civGoodsUpdate * ((GameValues.gvPopulationGrowth.POP_GROWTH_PROVINCE_GROWTH_RATE_BASE + province.getGrowthRate_Pop_WithFarm_WithTerrain() + CFG.core.getCiv(province.getCivId()).getModifier_PopGrowth()) * GameValues.gvPopulationGrowth.POP_GROWTH_PROVINCE_GROWTH_RATE_MODIFIER) * (1.0f + province.getDeveLvl() * GameValues.gvPopulationGrowth.POP_GROWTH_PROVINCE_DEVELOPMENT_MODIFIER + CFG.core.getGameScenars().getScenario_PopulationGrowthRate_Modifier()) * GameCalendar.GAME_SPEED;
                if (tempPopGrowth > 0.0f) {
                    if ((float)province.getPop().getPops() < modifiedStartingPop * province.getGrowthRate_Pop()) {
                        tempPopGrowth += (float)CFG.core.getGameScenars().getScenario_StartingPopulation() * (GameValues.gvPopulationGrowth.POP_GROWTH_LOW_POPULATION_BONUS_BASE * (1.0f - (float)province.getPop().getPops() / (float)CFG.core.getGameScenars().getScenario_StartingPopulation())) * province.getGrowthRate_Pop() * Math.min(province.getDeveLvl() * GameValues.gvPopulationGrowth.POP_GROWTH_LOW_POPULATION_BONUS_DEVELOPMENT_MODIFIER, GameValues.gvPopulationGrowth.POP_GROWTH_LOW_POPULATION_BONUS_DEVELOPMENT_MODIFIER_LIMIT);
                    }
                    if ((tempPopGrowth = 1.0f + tempPopGrowth * Math.max(GameValues.gvPopulationGrowth.POP_GROWTH_MODIFIER_STARTING_POPULATION_MIN, 1.0f - 0.4f * (float)province.getPop().getPops() / ((float)CFG.core.getGameScenars().getScenario_StartingPopulation() * GameValues.gvPopulationGrowth.POP_GROWTH_MODIFIER_STARTING_POPULATION_MODIFIER))) > 0.0f) {
                        tempPopGrowth = tempPopGrowth * GameValues.gvPopulationGrowth.POP_GROWTH_MODIFIER_FINAL_BASE_PERC + (float)CFG.oR.nextInt(Math.max((int)(tempPopGrowth * GameValues.gvPopulationGrowth.POP_GROWTH_MODIFIER_FINAL_RANDOM_POSITIVE_PERC * 100.0f), GameValues.gvPopulationGrowth.POP_GROWTH_MODIFIER_FINAL_RANDOM_POSITIVE_MIN_VALUE)) / 100.0f - (float)CFG.oR.nextInt(Math.max((int)(tempPopGrowth * GameValues.gvPopulationGrowth.POP_GROWTH_MODIFIER_FINAL_RANDOM_NEGATIVE_PERC * 100.0f), 1)) / 100.0f;
                    }
                }
                if ((tempPopGrowth *= CFG.POPULATION_GROWTH_RATE) > (float)GameValues.gvPopulationGrowth.POP_GROWTH_LIMIT_PER_TURN) {
                    tempPopGrowth = GameValues.gvPopulationGrowth.POP_GROWTH_LIMIT_PER_TURN;
                }
                out += (int)tempPopGrowth;
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
        return out;
    }

    public static int getUpdateGameData_PopulationGrowth_WithoutRandom(int civID) {
        int out = 0;
        try {
            float civGoodsUpdate = NewTurn.getGoodsUpdate(civID);
            float modifiedStartingPop = (float)CFG.core.getGameScenars().getScenario_StartingPopulation() * GameValues.gvEconomy.POPULATION_GROWTH_STARTING_POPULATION_MODIFIER;
            float randomModifier = GameValues.gvPopulationGrowth.POP_GROWTH_BUDGET_TEXT_RANDOM_MODIFIER;
            for (int i = 0; i < CFG.core.getCiv(civID).getNumOfProvs(); ++i) {
                Province province = CFG.core.getProv(CFG.core.getCiv(civID).getProvID(i));
                float tempPopGrowth = (float)province.getPop().getPops() * (GameValues.gvPopulationGrowth.POP_GROWTH_BASE_VALUE + (CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(province.getCivId()).getIdeology()).getMin_Goods(province.getCivId()) < CFG.core.getCiv(province.getCivId()).getSpendingGoodsB() ? (float)GameValues.gvPopulationGrowth.POP_GROWTH_BASE_VALUE_GOODS_SPENDING_BELOW_RANDOM_100_MAX * randomModifier / 100.0f : GameValues.gvPopulationGrowth.POP_GROWTH_BASE_VALUE_GOODS_SPENDING_OVER)) * civGoodsUpdate * ((GameValues.gvPopulationGrowth.POP_GROWTH_PROVINCE_GROWTH_RATE_BASE + province.getGrowthRate_Pop_WithFarm_WithTerrain() + CFG.core.getCiv(province.getCivId()).getModifier_PopGrowth()) * GameValues.gvPopulationGrowth.POP_GROWTH_PROVINCE_GROWTH_RATE_MODIFIER) * (1.0f + province.getDeveLvl() * GameValues.gvPopulationGrowth.POP_GROWTH_PROVINCE_DEVELOPMENT_MODIFIER + CFG.core.getGameScenars().getScenario_PopulationGrowthRate_Modifier()) * GameCalendar.GAME_SPEED;
                if (tempPopGrowth > 0.0f) {
                    if ((float)province.getPop().getPops() < modifiedStartingPop * province.getGrowthRate_Pop()) {
                        tempPopGrowth += (float)CFG.core.getGameScenars().getScenario_StartingPopulation() * (GameValues.gvPopulationGrowth.POP_GROWTH_LOW_POPULATION_BONUS_BASE * (1.0f - (float)province.getPop().getPops() / (float)CFG.core.getGameScenars().getScenario_StartingPopulation())) * province.getGrowthRate_Pop() * Math.min(province.getDeveLvl() * GameValues.gvPopulationGrowth.POP_GROWTH_LOW_POPULATION_BONUS_DEVELOPMENT_MODIFIER, GameValues.gvPopulationGrowth.POP_GROWTH_LOW_POPULATION_BONUS_DEVELOPMENT_MODIFIER_LIMIT);
                    }
                    if ((tempPopGrowth = 1.0f + tempPopGrowth * Math.max(GameValues.gvPopulationGrowth.POP_GROWTH_MODIFIER_STARTING_POPULATION_MIN, 1.0f - 0.4f * (float)province.getPop().getPops() / ((float)CFG.core.getGameScenars().getScenario_StartingPopulation() * GameValues.gvPopulationGrowth.POP_GROWTH_MODIFIER_STARTING_POPULATION_MODIFIER))) > 0.0f) {
                        tempPopGrowth = tempPopGrowth * GameValues.gvPopulationGrowth.POP_GROWTH_MODIFIER_FINAL_BASE_PERC + (float)Math.max((int)(tempPopGrowth * GameValues.gvPopulationGrowth.POP_GROWTH_MODIFIER_FINAL_RANDOM_POSITIVE_PERC * 100.0f), GameValues.gvPopulationGrowth.POP_GROWTH_MODIFIER_FINAL_RANDOM_POSITIVE_MIN_VALUE) * randomModifier / 100.0f - (float)Math.max((int)(tempPopGrowth * GameValues.gvPopulationGrowth.POP_GROWTH_MODIFIER_FINAL_RANDOM_NEGATIVE_PERC * 100.0f), 1) * randomModifier / 100.0f;
                    }
                }
                if ((tempPopGrowth *= CFG.POPULATION_GROWTH_RATE) > (float)GameValues.gvPopulationGrowth.POP_GROWTH_LIMIT_PER_TURN) {
                    tempPopGrowth = GameValues.gvPopulationGrowth.POP_GROWTH_LIMIT_PER_TURN;
                }
                out += (int)tempPopGrowth;
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
        return out;
    }

    public static int getUpdateGameData_EconomyGrowth_WithoutRandom(int civID) {
        int out = 0;
        try {
            float civInvestsUpdate = NewTurn.getInvestUpdate(civID);
            float modifiedStartingEco = (float)CFG.core.getGameScenars().getScenario_StartingEconomy() * GameValues.gvEconomy.ECONOMY_GROWTH_STARTING_ECONOMY_MODIFIER;
            float randomModifier = GameValues.gvEconomy.ECO_GROWTH_BUDGET_TEXT_RANDOM_MODIFIER;
            for (int i = 0; i < CFG.core.getCiv(civID).getNumOfProvs(); ++i) {
                float tempEco;
                Province province = CFG.core.getProv(CFG.core.getCiv(civID).getProvID(i));
                float tempEcoPop = 0.0f;
                if (CFG.core.getCiv(province.getCivId()).getSpendingGoodsB() < CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(province.getCivId()).getIdeology()).getMin_Goods(province.getCivId())) {
                    tempEcoPop = (float)province.getEco() * GameValues.gvGoods.GOODS_BELOW_MIN_ECONOMY_BASE_MODIFIER * ((CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(province.getCivId()).getIdeology()).getMin_Goods(province.getCivId()) - CFG.core.getCiv(province.getCivId()).getSpendingGoodsB()) / CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(province.getCivId()).getIdeology()).getMin_Goods(province.getCivId())) * (GameValues.gvGoods.GOODS_BELOW_MIN_ECONOMY_BASE_DEV_POP_GROWTH_MODIFIER + province.getDeveLvl() * GameValues.gvGoods.GOODS_BELOW_MIN_ECONOMY_PROVINCE_DEVELOPMENT_MODIFIER + province.getGrowthRate_Pop_WithFarm_WithTerrain() * GameValues.gvGoods.GOODS_BELOW_MIN_ECONOMY_PROVINCE_GROWTH_RATE_MODIFIER);
                }
                if ((tempEco = Math.max((float)province.getEco(), (float)CFG.core.getGameScenars().getScenario_StartingPopulation() * GameValues.gvEconomyGrowth.ECO_GROWTH_STARTING_POPULATION_MODIFIER * province.getGrowthRate_Pop()) * civInvestsUpdate * (GameValues.gvEconomyGrowth.ECO_GROWTH_BASE_VALUE + GameValues.gvEconomyGrowth.ECO_GROWTH_PROV_DEVELOPMENT_PERC_OF_TECH_MODIFIER * (province.getDeveLvl() / CFG.core.getCiv(province.getCivId()).getTechLevel()) + province.getGrowthRate_Pop_WithFarm_WithTerrain() * GameValues.gvEconomyGrowth.ECO_GROWTH_PROV_GROWTH_RATE_MODIFIER) * (1.0f + CFG.core.getCiv(province.getCivId()).getModifier_EconomyGrowth() + CFG.core.getGameScenars().getScenario_EconomyGrowthRate_Modifier()) * GameCalendar.GAME_SPEED) > 0.0f) {
                    if ((float)province.getEco() < modifiedStartingEco * province.getGrowthRate_Pop()) {
                        tempEco += (float)CFG.core.getGameScenars().getScenario_StartingEconomy() * (GameValues.gvEconomyGrowth.ECO_GROWTH_LOW_ECONOMY_BONUS_BASE * (1.0f - (float)province.getPop().getPops() / (float)CFG.core.getGameScenars().getScenario_StartingEconomy())) * province.getGrowthRate_Pop_WithFarm_WithTerrain() * GameValues.gvEconomyGrowth.ECO_GROWTH_LOW_ECONOMY_BONUS_PROV_GROWTH_RATE_MODIFIER * Math.min(province.getDeveLvl() * GameValues.gvEconomyGrowth.ECO_GROWTH_LOW_ECONOMY_BONUS_PROV_DEVELOPMENT_MODIFIER, 1.0f);
                    }
                    if ((tempEco *= Math.max(GameValues.gvEconomyGrowth.ECO_GROWTH_MODIFIER_STARTING_ECONOMY_MIN, 1.0f - GameValues.gvEconomy.ECO_GROWTH_SATURATION_STRENGTH * (float)province.getEco() / ((float)CFG.core.getGameScenars().getScenario_StartingEconomy() * GameValues.gvEconomyGrowth.ECO_GROWTH_MODIFIER_STARTING_ECONOMY_MODIFIER))) > 0.0f) {
                        tempEco = tempEco * GameValues.gvEconomyGrowth.ECO_GROWTH_MODIFIER_FINAL_BASE_PERC + randomModifier * (float)Math.max((int)(tempEco * GameValues.gvEconomyGrowth.ECO_GROWTH_MODIFIER_FINAL_RANDOM_POSITIVE_PERC * 100.0f), GameValues.gvEconomyGrowth.ECO_GROWTH_MODIFIER_FINAL_RANDOM_POSITIVE_MIN_VALUE) / 100.0f - randomModifier * (float)Math.max((int)(tempEco * GameValues.gvEconomyGrowth.ECO_GROWTH_MODIFIER_FINAL_RANDOM_NEGATIVE_PERC * 100.0f), 1) / 100.0f;
                    }
                }
                tempEco = CFG.core.getCiv(civID).getSpendingInvestmentsB() < CFG.ideologiesMgr.getInvestments(CFG.core.getCiv(civID).getIdeology(), civID) ? (tempEco *= CFG.ECONOMY_GROWTH_RATE) : Math.max((float)GameValues.gvEconomy.MIN_ECONOMY_CHANCE, tempEco * CFG.ECONOMY_GROWTH_RATE);
                out += (int)tempEcoPop + (int)tempEco;
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
        return out;
    }

    public static float getGoodsUpdate(int civID) {
        return CFG.core.getCiv(civID).getSpendingGoodsB() < CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(civID).getIdeology()).getMin_Goods(civID) ? GameValues.gvEconomy.GOODS_UNDER_MIN_PENALTY * ((CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(civID).getIdeology()).getMin_Goods(civID) - CFG.core.getCiv(civID).getSpendingGoodsB()) / CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(civID).getIdeology()).getMin_Goods(civID)) : (-CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(civID).getIdeology()).getMin_Goods(civID) + GameValues.gvEconomy.GOODS_OVER_MIN_BASE + CFG.core.getCiv(civID).getSpendingGoodsB()) * CFG.gameAges.getAge_Population_GrowthRate(GameCalendar.CURRENT_AGEID);
    }

    public static float getInvestUpdate(int civID) {
        return CFG.core.getCiv(civID).getSpendingInvestmentsB() < CFG.ideologiesMgr.getInvestments(CFG.core.getCiv(civID).getIdeology(), civID) ? GameValues.gvEconomy.INVEST_UNDER_MIN_ECO_PENALTY * ((CFG.ideologiesMgr.getInvestments(CFG.core.getCiv(civID).getIdeology(), civID) - CFG.core.getCiv(civID).getSpendingInvestmentsB()) / CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)civID).getIdeology()).MIN_INVESTMENTS) : (-CFG.ideologiesMgr.getInvestments(CFG.core.getCiv(civID).getIdeology(), civID) + GameValues.gvEconomy.INVEST_OVER_MIN_ECO_BASE + CFG.core.getCiv(civID).getSpendingInvestmentsB() * GameValues.gvEconomy.INVEST_OVER_INVESTMENTS_MODIFIER) * CFG.gameAges.getAge_Economy_GrowthRate(GameCalendar.CURRENT_AGEID);
    }

    public static final void updateGameData() {
        float tempPopGrowth;
        Province province;
        int i;
        tempCivs = new ArrayList<PopulationGrowth>();
        happinessChange_ByTaxation = new ArrayList<Float>();
        happinessChange_ByTaxation_Occupied = new ArrayList<Float>();
        goodsUpdate = new ArrayList<Float>();
        devUpdate = new ArrayList<Float>();
        ecoUpdate = new ArrayList<Float>();
        ageRiskModifier = CFG.gameAges.getAge_RevolutionaryRiskModifier(GameCalendar.CURRENT_AGEID);
        ageDevMod = CFG.gameAges.getAge_DevelopmentLevel_Increase(GameCalendar.CURRENT_AGEID);
        for (int i2 = 1; i2 < CFG.core.getCivsSize(); ++i2) {
            if (CFG.core.getCiv(i2).getNumOfProvs() > 0) {
                happinessChange_ByTaxation.add(Float.valueOf(CFG.gameUpdate.getHappinessChange_ByTaxation(i2)));
                happinessChange_ByTaxation_Occupied.add(Float.valueOf(CFG.gameUpdate.getHappinessChange_ByTaxation_Occupied(i2)));
                goodsUpdate.add(Float.valueOf(NewTurn.getGoodsUpdate(i2)));
                devUpdate.add(Float.valueOf(CFG.core.getCiv(i2).getSpendingInvestmentsB() < CFG.ideologiesMgr.getInvestments(CFG.core.getCiv(i2).getIdeology(), i2) ? GameValues.gvEconomy.INVEST_UNDER_MIN_DEV_PENALTY * ((CFG.ideologiesMgr.getInvestments(CFG.core.getCiv(i2).getIdeology(), i2) - CFG.core.getCiv(i2).getSpendingInvestmentsB()) / CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)i2).getIdeology()).MIN_INVESTMENTS) : -CFG.ideologiesMgr.getInvestments(CFG.core.getCiv(i2).getIdeology(), i2) + GameValues.gvEconomy.INVEST_OVER_MIN_DEV_BASE + CFG.core.getCiv(i2).getSpendingInvestmentsB()));
                ecoUpdate.add(Float.valueOf(NewTurn.getInvestUpdate(i2)));
            } else {
                happinessChange_ByTaxation.add(Float.valueOf(1.0f));
                happinessChange_ByTaxation_Occupied.add(Float.valueOf(1.0f));
                goodsUpdate.add(Float.valueOf(1.0f));
                devUpdate.add(Float.valueOf(1.0f));
                ecoUpdate.add(Float.valueOf(1.0f));
            }
            CFG.core.getCiv((int)i2).civGD.civAggressionLevel = Math.max(0.0f, CFG.core.getCiv((int)i2).civGD.civAggressionLevel - GameValues.gvDiplomacy.CIV_AGGRESSION_DECAY_PER_TURN);
        }
        NewTurn.updateNukes();
        float modifiedStartingPop = (float)CFG.core.getGameScenars().getScenario_StartingPopulation() * GameValues.gvEconomy.POPULATION_GROWTH_STARTING_POPULATION_MODIFIER;
        float modifiedStartingEco = (float)CFG.core.getGameScenars().getScenario_StartingEconomy() * GameValues.gvEconomy.ECONOMY_GROWTH_STARTING_ECONOMY_MODIFIER;
        if (CFG.getIsDesktop() || GameValues.gvEconomy.POPULATION_GROWTH_USE_SIMPLER_VERSION) {
            for (i = 0; i < CFG.core.getProvinSize(); ++i) {
                province = CFG.core.getProv(i);
                if (province.getSeaProv() || province.getWastelandLvl() >= 0 || province.getCivId() <= 0) continue;
                tempPopGrowth = (float)province.getPop().getPops() * (GameValues.gvPopulationGrowth.POP_GROWTH_BASE_VALUE + (CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(province.getCivId()).getIdeology()).getMin_Goods(province.getCivId()) < CFG.core.getCiv(province.getCivId()).getSpendingGoodsB() ? (float)CFG.oR.nextInt(GameValues.gvPopulationGrowth.POP_GROWTH_BASE_VALUE_GOODS_SPENDING_BELOW_RANDOM_100_MAX) / 100.0f : GameValues.gvPopulationGrowth.POP_GROWTH_BASE_VALUE_GOODS_SPENDING_OVER)) * goodsUpdate.get(province.getCivId() - 1).floatValue() * ((GameValues.gvPopulationGrowth.POP_GROWTH_PROVINCE_GROWTH_RATE_BASE + province.getGrowthRate_Pop_WithFarm_WithTerrain() + CFG.core.getCiv(province.getCivId()).getModifier_PopGrowth()) * GameValues.gvPopulationGrowth.POP_GROWTH_PROVINCE_GROWTH_RATE_MODIFIER) * (1.0f + province.getDeveLvl() * GameValues.gvPopulationGrowth.POP_GROWTH_PROVINCE_DEVELOPMENT_MODIFIER + CFG.core.getGameScenars().getScenario_PopulationGrowthRate_Modifier()) * GameCalendar.GAME_SPEED;
                if (tempPopGrowth > 0.0f) {
                    if ((float)province.getPop().getPops() < modifiedStartingPop * province.getGrowthRate_Pop()) {
                        tempPopGrowth += (float)CFG.core.getGameScenars().getScenario_StartingPopulation() * (GameValues.gvPopulationGrowth.POP_GROWTH_LOW_POPULATION_BONUS_BASE * (1.0f - (float)province.getPop().getPops() / (float)CFG.core.getGameScenars().getScenario_StartingPopulation())) * province.getGrowthRate_Pop() * Math.min(province.getDeveLvl() * GameValues.gvPopulationGrowth.POP_GROWTH_LOW_POPULATION_BONUS_DEVELOPMENT_MODIFIER, GameValues.gvPopulationGrowth.POP_GROWTH_LOW_POPULATION_BONUS_DEVELOPMENT_MODIFIER_LIMIT);
                    }
                    if ((tempPopGrowth = 1.0f + tempPopGrowth * Math.max(GameValues.gvPopulationGrowth.POP_GROWTH_MODIFIER_STARTING_POPULATION_MIN, 1.0f - 0.4f * (float)province.getPop().getPops() / ((float)CFG.core.getGameScenars().getScenario_StartingPopulation() * GameValues.gvPopulationGrowth.POP_GROWTH_MODIFIER_STARTING_POPULATION_MODIFIER))) > 0.0f) {
                        tempPopGrowth = tempPopGrowth * GameValues.gvPopulationGrowth.POP_GROWTH_MODIFIER_FINAL_BASE_PERC + (float)CFG.oR.nextInt(Math.max((int)(tempPopGrowth * GameValues.gvPopulationGrowth.POP_GROWTH_MODIFIER_FINAL_RANDOM_POSITIVE_PERC * 100.0f), GameValues.gvPopulationGrowth.POP_GROWTH_MODIFIER_FINAL_RANDOM_POSITIVE_MIN_VALUE)) / 100.0f - (float)CFG.oR.nextInt(Math.max((int)(tempPopGrowth * GameValues.gvPopulationGrowth.POP_GROWTH_MODIFIER_FINAL_RANDOM_NEGATIVE_PERC * 100.0f), 1)) / 100.0f;
                    }
                }
                if ((tempPopGrowth *= CFG.POPULATION_GROWTH_RATE) > (float)GameValues.gvPopulationGrowth.POP_GROWTH_LIMIT_PER_TURN * CFG.POPULATION_GROWTH_RATE) {
                    tempPopGrowth = (float)GameValues.gvPopulationGrowth.POP_GROWTH_LIMIT_PER_TURN * CFG.POPULATION_GROWTH_RATE;
                }
                if ((int)tempPopGrowth != 0) {
                    if (tempPopGrowth > -10.0f && tempPopGrowth < 16.0f) {
                        province.getPop().setPopulationOfCivID(province.getCivId(), province.getPop().getPopulationOfCivID(province.getCivId()) + (int)tempPopGrowth);
                    } else {
                        int j;
                        int j2;
                        tempCivs.clear();
                        tempCivs.add(new PopulationGrowth(province.getCivId(), GameValues.gvPopulationGrowth.POP_GROWTH_NATIONALITY_OWNER_X_STABILITY * province.getProviStability()));
                        if (province.getCivId() != CFG.core.getCiv(province.getCivId()).getPuppetOfCiv()) {
                            tempCivs.add(new PopulationGrowth(CFG.core.getCiv(province.getCivId()).getPuppetOfCiv(), GameValues.gvPopulationGrowth.POP_GROWTH_NATIONALITY_LORD));
                        }
                        if (province.isOccupied()) {
                            tempCivs.add(new PopulationGrowth(province.getTrueOwnerOfProv(), GameValues.gvPopulationGrowth.POP_GROWTH_NATIONALITY_TRUE_OWNER));
                        }
                        for (int j3 = 0; j3 < province.getCores().getCivsSize(); ++j3) {
                            tempCivs.add(new PopulationGrowth(province.getCores().getCivID(j3), GameValues.gvPopulationGrowth.POP_GROWTH_NATIONALITY_CORE_CIV));
                        }
                        int tempPop = province.getPop().getPops();
                        for (j2 = 0; j2 < province.getPop().getNatsSize(); ++j2) {
                            tempCivs.add(new PopulationGrowth(province.getPop().getCivID(j2), (float)province.getPop().getPopulationID(j2) / (float)tempPop * 100.0f));
                        }
                        for (j2 = 0; j2 < province.getNeighProvincesSize(); ++j2) {
                            if (CFG.core.getProv(province.getNeighProvinces(j2)).getCivId() <= 0) continue;
                            tempCivs.add(new PopulationGrowth(CFG.core.getProv(province.getNeighProvinces(j2)).getCivId(), GameValues.gvPopulationGrowth.POP_GROWTH_NATIONALITY_NEIGHBORING_PROVINCE_CIV));
                        }
                        float tempTotalPoints = 0.0f;
                        for (j = tempCivs.size() - 1; j >= 0; --j) {
                            tempTotalPoints += NewTurn.tempCivs.get((int)j).fPerc;
                        }
                        for (j = tempCivs.size() - 1; j >= 0; --j) {
                            NewTurn.tempCivs.get((int)j).fPerc /= tempTotalPoints;
                            province.getPop().setPopulationOfCivID(NewTurn.tempCivs.get((int)j).iCivID, province.getPop().getPopulationOfCivID(NewTurn.tempCivs.get((int)j).iCivID) + (int)(tempPopGrowth * NewTurn.tempCivs.get((int)j).fPerc));
                        }
                        tempCivs.clear();
                    }
                }
                NewTurn.updateGameData_Province(i, modifiedStartingEco);
            }
        } else {
            for (i = 0; i < CFG.core.getProvinSize(); ++i) {
                province = CFG.core.getProv(i);
                if (province.getSeaProv() || province.getWastelandLvl() >= 0 || province.getCivId() <= 0) continue;
                tempPopGrowth = (float)province.getPop().getPops() * (GameValues.gvPopulationGrowth.POP_GROWTH_BASE_VALUE + (CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(province.getCivId()).getIdeology()).getMin_Goods(province.getCivId()) < CFG.core.getCiv(province.getCivId()).getSpendingGoodsB() ? (float)CFG.oR.nextInt(GameValues.gvPopulationGrowth.POP_GROWTH_BASE_VALUE_GOODS_SPENDING_BELOW_RANDOM_100_MAX) / 100.0f : GameValues.gvPopulationGrowth.POP_GROWTH_BASE_VALUE_GOODS_SPENDING_OVER)) * goodsUpdate.get(province.getCivId() - 1).floatValue() * ((GameValues.gvPopulationGrowth.POP_GROWTH_PROVINCE_GROWTH_RATE_BASE + province.getGrowthRate_Pop_WithFarm_WithTerrain() + CFG.core.getCiv(province.getCivId()).getModifier_PopGrowth()) * GameValues.gvPopulationGrowth.POP_GROWTH_PROVINCE_GROWTH_RATE_MODIFIER) * (1.0f + province.getDeveLvl() * GameValues.gvPopulationGrowth.POP_GROWTH_PROVINCE_DEVELOPMENT_MODIFIER + CFG.core.getGameScenars().getScenario_PopulationGrowthRate_Modifier()) * GameCalendar.GAME_SPEED;
                if (tempPopGrowth > 0.0f) {
                    if ((float)province.getPop().getPops() < modifiedStartingPop * province.getGrowthRate_Pop()) {
                        tempPopGrowth += (float)CFG.core.getGameScenars().getScenario_StartingPopulation() * (GameValues.gvPopulationGrowth.POP_GROWTH_LOW_POPULATION_BONUS_BASE * (1.0f - (float)province.getPop().getPops() / (float)CFG.core.getGameScenars().getScenario_StartingPopulation())) * province.getGrowthRate_Pop() * Math.min(province.getDeveLvl() * GameValues.gvPopulationGrowth.POP_GROWTH_LOW_POPULATION_BONUS_DEVELOPMENT_MODIFIER, GameValues.gvPopulationGrowth.POP_GROWTH_LOW_POPULATION_BONUS_DEVELOPMENT_MODIFIER_LIMIT);
                    }
                    if ((tempPopGrowth = 1.0f + tempPopGrowth * Math.max(GameValues.gvPopulationGrowth.POP_GROWTH_MODIFIER_STARTING_POPULATION_MIN, 1.0f - 0.4f * (float)province.getPop().getPops() / ((float)CFG.core.getGameScenars().getScenario_StartingPopulation() * GameValues.gvPopulationGrowth.POP_GROWTH_MODIFIER_STARTING_POPULATION_MODIFIER))) > 0.0f) {
                        tempPopGrowth = tempPopGrowth * GameValues.gvPopulationGrowth.POP_GROWTH_MODIFIER_FINAL_BASE_PERC + (float)CFG.oR.nextInt(Math.max((int)(tempPopGrowth * GameValues.gvPopulationGrowth.POP_GROWTH_MODIFIER_FINAL_RANDOM_POSITIVE_PERC * 100.0f), GameValues.gvPopulationGrowth.POP_GROWTH_MODIFIER_FINAL_RANDOM_POSITIVE_MIN_VALUE)) / 100.0f - (float)CFG.oR.nextInt(Math.max((int)(tempPopGrowth * GameValues.gvPopulationGrowth.POP_GROWTH_MODIFIER_FINAL_RANDOM_NEGATIVE_PERC * 100.0f), 1)) / 100.0f;
                    }
                }
                if ((tempPopGrowth *= CFG.POPULATION_GROWTH_RATE) > (float)GameValues.gvPopulationGrowth.POP_GROWTH_LIMIT_PER_TURN * CFG.POPULATION_GROWTH_RATE) {
                    tempPopGrowth = (float)GameValues.gvPopulationGrowth.POP_GROWTH_LIMIT_PER_TURN * CFG.POPULATION_GROWTH_RATE;
                }
                if ((int)tempPopGrowth != 0) {
                    province.getPop().setPopulationOfCivID(province.getCivId(), province.getPop().getPopulationOfCivID(province.getCivId()) + (int)tempPopGrowth);
                }
                NewTurn.updateGameData_Province(i, modifiedStartingEco);
            }
        }
        if (GameCalendar.TURNID % GameValues.gvUpdate.UPDATE_NEUTRAL_ARMY == 0) {
            for (i = 0; i < CFG.core.getProvinSize(); ++i) {
                if (CFG.core.getProv(i).getSeaProv() || CFG.core.getProv(i).getWastelandLvl() >= 0 || CFG.core.getProv(i).getCivId() != 0 || CFG.oR.nextInt(100) <= GameValues.gvProvince.NEUTRAL_ARMY_UPDATE_CHANCE_100) continue;
                CFG.core.getProv(i).updateArmy4(0, CFG.core.getProv(i).getArmyCivID1(0) + (GameValues.gvProvince.NEUTRAL_ARMY_UPDATE_BASE + CFG.oR.nextInt(GameValues.gvProvince.NEUTRAL_ARMY_UPDATE_RANDOM)) * GameCalendar.TURNID % GameValues.gvUpdate.UPDATE_NEUTRAL_ARMY);
            }
        }
        tempCivs.clear();
        tempCivs = null;
        happinessChange_ByTaxation.clear();
        happinessChange_ByTaxation_Occupied.clear();
        goodsUpdate.clear();
        devUpdate.clear();
        ecoUpdate.clear();
    }

    public static void updateGameData_Province(int iProvinceID, float modifiedStartingEco) {
        float tempEco;
        Province province = CFG.core.getProv(iProvinceID);
        if (province.getTrueOwnerOfProv() == province.getCivId() && !CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)province.getCivId()).getIdeology()).REVOLUTIONARY) {
            province.getCores().increaseOwnership(province.getCivId(), iProvinceID);
            if (CFG.core.getCiv(province.getCivId()).getPuppetOfCiv() != province.getCivId()) {
                province.getCores().increaseOwnership(CFG.core.getCiv(province.getCivId()).getPuppetOfCiv(), iProvinceID);
            }
        }
        if (province.getDeveLvl() < 1.0f) {
            if (province.getCivId() == province.getTrueOwnerOfProv()) {
                float tempDevelopmentChange = ageDevMod * devUpdate.get(province.getCivId() - 1).floatValue() * Math.min(province.getGrowthRate_Pop_WithFarm_WithTerrain() * GameValues.gvDevelopment.DEV_CHANGE_PROVINCE_GROWTH_RATE_MODIFIER, GameValues.gvDevelopment.DEV_CHANGE_MODIFIER_LIMIT);
                province.setDevLvl(province.getDeveLvl() + tempDevelopmentChange);
            } else {
                province.setDevLvl(province.getDeveLvl() - (float)CFG.oR.nextInt(GameValues.gvDevelopment.DEV_CHANGE_OCCUPIED_RANDOM) / GameValues.gvDevelopment.DEV_CHANGE_OCCUPIED_RANDOM_DIVIDE);
            }
        }
        if (CFG.core.getCiv(province.getCivId()).getSpendingGoodsB() < CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(province.getCivId()).getIdeology()).getMin_Goods(province.getCivId())) {
            float tempHapp = GameValues.gvGoods.GOODS_BELOW_MIN_HAPPINESS_BASE_MODIFIER * ((CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(province.getCivId()).getIdeology()).getMin_Goods(province.getCivId()) - CFG.core.getCiv(province.getCivId()).getSpendingGoodsB()) / CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(province.getCivId()).getIdeology()).getMin_Goods(province.getCivId())) * (GameValues.gvGoods.GOODS_BELOW_MIN_HAPPINESS_BASE_DEV_POP_GROWTH_MODIFIER + province.getDeveLvl() * GameValues.gvGoods.GOODS_BELOW_MIN_HAPPINESS_PROVINCE_DEVELOPMENT_MODIFIER + province.getGrowthRate_Pop_WithFarm() * GameValues.gvGoods.GOODS_BELOW_MIN_HAPPINESS_PROVINCE_GROWTH_RATE_MODIFIER);
            if (tempHapp > 0.0f) {
                tempHapp *= 1.0f + GameValues.gvGoods.GOODS_BELOW_MIN_HAPPINESS_WAR_WEARiNESS_MODIFIER * CFG.core.getCiv((int)province.getCivId()).civGD.warWeariness;
            }
            province.setHappi(province.getHappi() + tempHapp);
            float tempEcoPop = (float)province.getEco() * GameValues.gvGoods.GOODS_BELOW_MIN_ECONOMY_BASE_MODIFIER * ((CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(province.getCivId()).getIdeology()).getMin_Goods(province.getCivId()) - CFG.core.getCiv(province.getCivId()).getSpendingGoodsB()) / CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(province.getCivId()).getIdeology()).getMin_Goods(province.getCivId())) * (GameValues.gvGoods.GOODS_BELOW_MIN_ECONOMY_BASE_DEV_POP_GROWTH_MODIFIER + province.getDeveLvl() * GameValues.gvGoods.GOODS_BELOW_MIN_ECONOMY_PROVINCE_DEVELOPMENT_MODIFIER + province.getGrowthRate_Pop_WithFarm_WithTerrain() * GameValues.gvGoods.GOODS_BELOW_MIN_ECONOMY_PROVINCE_GROWTH_RATE_MODIFIER);
            province.setEco((int)((float)province.getEco() + tempEcoPop));
        }
        if ((tempEco = Math.max((float)province.getEco(), (float)CFG.core.getGameScenars().getScenario_StartingPopulation() * GameValues.gvEconomyGrowth.ECO_GROWTH_STARTING_POPULATION_MODIFIER * province.getGrowthRate_Pop()) * ecoUpdate.get(province.getCivId() - 1).floatValue() * (GameValues.gvEconomyGrowth.ECO_GROWTH_BASE_VALUE + GameValues.gvEconomyGrowth.ECO_GROWTH_PROV_DEVELOPMENT_PERC_OF_TECH_MODIFIER * (province.getDeveLvl() / CFG.core.getCiv(province.getCivId()).getTechLevel()) + province.getGrowthRate_Pop_WithFarm_WithTerrain() * GameValues.gvEconomyGrowth.ECO_GROWTH_PROV_GROWTH_RATE_MODIFIER) * (1.0f + CFG.core.getCiv(province.getCivId()).getModifier_EconomyGrowth() + CFG.core.getGameScenars().getScenario_EconomyGrowthRate_Modifier()) * GameCalendar.GAME_SPEED) > 0.0f) {
            if ((float)province.getEco() < modifiedStartingEco * province.getGrowthRate_Pop()) {
                tempEco += (float)CFG.core.getGameScenars().getScenario_StartingEconomy() * (GameValues.gvEconomyGrowth.ECO_GROWTH_LOW_ECONOMY_BONUS_BASE * (1.0f - (float)province.getPop().getPops() / (float)CFG.core.getGameScenars().getScenario_StartingEconomy())) * province.getGrowthRate_Pop_WithFarm_WithTerrain() * GameValues.gvEconomyGrowth.ECO_GROWTH_LOW_ECONOMY_BONUS_PROV_GROWTH_RATE_MODIFIER * Math.min(province.getDeveLvl() * GameValues.gvEconomyGrowth.ECO_GROWTH_LOW_ECONOMY_BONUS_PROV_DEVELOPMENT_MODIFIER, 1.0f);
            }
            if ((tempEco *= Math.max(GameValues.gvEconomyGrowth.ECO_GROWTH_MODIFIER_STARTING_ECONOMY_MIN, 1.0f - GameValues.gvEconomy.ECO_GROWTH_SATURATION_STRENGTH * (float)province.getEco() / ((float)CFG.core.getGameScenars().getScenario_StartingEconomy() * GameValues.gvEconomyGrowth.ECO_GROWTH_MODIFIER_STARTING_ECONOMY_MODIFIER))) > 0.0f) {
                tempEco = tempEco * GameValues.gvEconomyGrowth.ECO_GROWTH_MODIFIER_FINAL_BASE_PERC + (float)CFG.oR.nextInt(Math.max((int)(tempEco * GameValues.gvEconomyGrowth.ECO_GROWTH_MODIFIER_FINAL_RANDOM_POSITIVE_PERC * 100.0f), GameValues.gvEconomyGrowth.ECO_GROWTH_MODIFIER_FINAL_RANDOM_POSITIVE_MIN_VALUE)) / 100.0f - (float)CFG.oR.nextInt(Math.max((int)(tempEco * GameValues.gvEconomyGrowth.ECO_GROWTH_MODIFIER_FINAL_RANDOM_NEGATIVE_PERC * 100.0f), 1)) / 100.0f;
            }
        }
        tempEco = CFG.core.getCiv(province.getCivId()).getSpendingInvestmentsB() < CFG.ideologiesMgr.getInvestments(CFG.core.getCiv(province.getCivId()).getIdeology(), province.getCivId()) ? (tempEco *= CFG.ECONOMY_GROWTH_RATE) : Math.max((float)GameValues.gvEconomy.MIN_ECONOMY_CHANCE, tempEco * CFG.ECONOMY_GROWTH_RATE);
        province.setEco((int)((float)province.getEco() + tempEco));
        if (province.getCivId() == province.getTrueOwnerOfProv()) {
            if (happinessChange_ByTaxation.get(province.getCivId() - 1).floatValue() > 0.0f) {
                province.setHappi(province.getHappi() + (float)CFG.oR.nextInt((int)(Math.max(happinessChange_ByTaxation.get(province.getCivId() - 1).floatValue(), 0.001f) * 1000.0f)) / 100000.0f);
            } else {
                province.setHappi(province.getHappi() + (happinessChange_ByTaxation.get(province.getCivId() - 1).floatValue() + happinessChange_ByTaxation.get(province.getCivId() - 1).floatValue() * (GameValues.gvHappiness.HAPPINESS_TAXATION_STABILITY_MODIFIER - GameValues.gvHappiness.HAPPINESS_TAXATION_STABILITY_MODIFIER * province.getProviStability())) / 100.0f);
            }
        } else if (happinessChange_ByTaxation_Occupied.get(province.getCivId() - 1).floatValue() > 0.0f) {
            province.setHappi(province.getHappi() + (float)CFG.oR.nextInt(Math.max(1, (int)(happinessChange_ByTaxation_Occupied.get(province.getCivId() - 1).floatValue() * 100.0f))) / 10000.0f);
        } else {
            province.setHappi(province.getHappi() + happinessChange_ByTaxation_Occupied.get(province.getCivId() - 1).floatValue() / 100.0f);
        }
        if (CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)province.getCivId()).getIdeology()).REVOLUTIONARY) {
            province.setRevRisk(0.0f);
        } else {
            float fRisk = province.getRevRisk();
            if (fRisk > GameValues.gvRevolutionaryRisk.REVOLT_RISK_DECAY_THRESHOLD) {
                fRisk -= Math.min(fRisk / GameValues.gvRevolutionaryRisk.REVOLT_RISK_DECAY_DIVISOR, GameValues.gvRevolutionaryRisk.REVOLT_RISK_DECAY_MAX) * (1.0f - CFG.core.getCiv(province.getCivId()).getWarWeariness());
            }
            if (province.getHappi() < GameValues.gvRebels.RISE_REVOLT_RISK_IN_PROVINCE_IF_HAPPINESS_BELOW) {
                float nModifier = CFG.core.getCiv(province.getCivId()).getGold() < (long)GameValues.gvRevolutionaryRisk.REVOLT_RISK_BANKRUPTCY_THRESHOLD ? 1.0f : Math.min(GameValues.gvRevolutionaryRisk.REVOLT_RISK_TAXATION_BASE + CFG.core.getCiv(province.getCivId()).getTaxationLvl() / CFG.ideologiesMgr.getAcceptableTaxation(CFG.core.getCiv(province.getCivId()).getIdeology(), province.getCivId()), 1.0f);
                fRisk += nModifier * ageRiskModifier * (GameValues.gvRebels.RISE_REVOLT_RISK_IN_PROVINCE_IF_HAPPINESS_BELOW - province.getHappi()) / GameValues.gvRevolutionaryRisk.REVOLT_RISK_HAPPINESS_DIVISOR;
            }
            province.setRevRisk(fRisk);
        }
        province.runSupportRebels();
        province.updateNewColony();
    }

    public static void updateDiplomacy() {
        for (int i = 1; i < CFG.core.getCivsSize(); ++i) {
            Civilization.DiplomacyData tData;
            Iterator<Map.Entry<Integer, Civilization.DiplomacyData>> it;
            Civilization civI = CFG.core.getCiv(i);
            try {
                if (!civI.defensivePact.isEmpty()) {
                    it = civI.defensivePact.entrySet().iterator();
                    while (it.hasNext()) {
                        tData = it.next().getValue();
                        civI.setDiploPoints(civI.getDiploPoints() - GameValues.gvDiplomacyPoints.DIPLOMACY_COST_PER_DEFENSIVE_PACT);
                        CFG.core.getCiv(tData.iCivID).setDiploPoints(CFG.core.getCiv(tData.iCivID).getDiploPoints() - GameValues.gvDiplomacyPoints.DIPLOMACY_COST_PER_DEFENSIVE_PACT);
                        if (tData.iTurnID == 1 && civI.getNumOfProvs() > 0 && CFG.core.getCiv(tData.iCivID).getNumOfProvs() > 0) {
                            civI.getCivDiploGD().messageBox.addMessage(new Message_DefensivePact_Expired(tData.iCivID));
                            CFG.core.getCiv((int)tData.iCivID).getCivDiploGD().messageBox.addMessage(new Message_DefensivePact_Expired(i));
                        }
                        if (tData.iTurnID == 1) {
                            civI.setDefensivePact4(tData.iCivID, tData.iTurnID - 1);
                            it.remove();
                            continue;
                        }
                        civI.setDefensivePact4(tData.iCivID, tData.iTurnID - 1);
                    }
                }
            }
            catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
            try {
                if (!civI.nonAggressionPact.isEmpty()) {
                    it = civI.nonAggressionPact.entrySet().iterator();
                    while (it.hasNext()) {
                        tData = it.next().getValue();
                        civI.setDiploPoints(civI.getDiploPoints() - GameValues.gvDiplomacyPoints.DIPLOMACY_COST_PER_NONAGGRESSION);
                        CFG.core.getCiv(tData.iCivID).setDiploPoints(CFG.core.getCiv(tData.iCivID).getDiploPoints() - GameValues.gvDiplomacyPoints.DIPLOMACY_COST_PER_NONAGGRESSION);
                        if (tData.iTurnID == 1 && civI.getNumOfProvs() > 0 && CFG.core.getCiv(tData.iCivID).getNumOfProvs() > 0) {
                            civI.getCivDiploGD().messageBox.addMessage(new Message_NonAggressionPact_Expired(tData.iCivID));
                            CFG.core.getCiv((int)tData.iCivID).getCivDiploGD().messageBox.addMessage(new Message_NonAggressionPact_Expired(i));
                        }
                        if (tData.iTurnID == 1) {
                            civI.setNonAggPact(tData.iCivID, tData.iTurnID - 1);
                            it.remove();
                            continue;
                        }
                        civI.setNonAggPact(tData.iCivID, tData.iTurnID - 1);
                    }
                }
            }
            catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
            try {
                if (!civI.guarantee.isEmpty()) {
                    it = civI.guarantee.entrySet().iterator();
                    while (it.hasNext()) {
                        tData = it.next().getValue();
                        civI.setDiploPoints(civI.getDiploPoints() - GameValues.gvDiplomacyPoints.DIPLOMACY_COST_PER_GUARANTEE);
                        CFG.core.getCiv(tData.iCivID).setDiploPoints(CFG.core.getCiv(tData.iCivID).getDiploPoints() - GameValues.gvDiplomacyPoints.DIPLOMACY_COST_PER_GUARANTEE);
                        if (tData.iTurnID == 1 && civI.getNumOfProvs() > 0 && CFG.core.getCiv(tData.iCivID).getNumOfProvs() > 0) {
                            if (civI.getIsPlayer()) {
                                civI.getCivDiploGD().messageBox.addMessage(new Message_IndependenceFrom_Expired(tData.iCivID));
                            }
                            if (CFG.core.getCiv(tData.iCivID).getIsPlayer()) {
                                CFG.core.getCiv((int)tData.iCivID).getCivDiploGD().messageBox.addMessage(new Message_Independence_Expired(i));
                            }
                        }
                        if (tData.iTurnID == 1) {
                            civI.setGuarantee2(tData.iCivID, tData.iTurnID - 1);
                            it.remove();
                            continue;
                        }
                        civI.setGuarantee2(tData.iCivID, tData.iTurnID - 1);
                    }
                }
            }
            catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
            try {
                if (!civI.militaryAccess.isEmpty()) {
                    it = civI.militaryAccess.entrySet().iterator();
                    while (it.hasNext()) {
                        tData = it.next().getValue();
                        civI.setDiploPoints(civI.getDiploPoints() - GameValues.gvDiplomacyPoints.DIPLOMACY_COST_PER_MILITARY_ACCESS);
                        CFG.core.getCiv(tData.iCivID).setDiploPoints(CFG.core.getCiv(tData.iCivID).getDiploPoints() - GameValues.gvDiplomacyPoints.DIPLOMACY_COST_PER_MILITARY_ACCESS);
                        if (tData.iTurnID == 1) {
                            if (civI.getNumOfProvs() > 0 && CFG.core.getCiv(tData.iCivID).getNumOfProvs() > 0) {
                                if (civI.getIsPlayer()) {
                                    civI.getCivDiploGD().messageBox.addMessage(new Message_MilitaryAccess_Expired(tData.iCivID));
                                }
                                if (CFG.core.getCiv(tData.iCivID).getIsPlayer()) {
                                    CFG.core.getCiv((int)tData.iCivID).getCivDiploGD().messageBox.addMessage(new Message_MilitaryAccess_Expired(i));
                                }
                            }
                        } else if (tData.iTurnID < 4 && civI.getIsPlayer()) {
                            civI.getCivDiploGD().messageBox.addMessage(new Message_MilitaryAccess_ExpireSoon(tData.iCivID, tData.iTurnID - 1));
                        }
                        if (tData.iTurnID == 1) {
                            civI.setMilitaryAccess7(tData.iCivID, tData.iTurnID - 1);
                            it.remove();
                            continue;
                        }
                        civI.setMilitaryAccess7(tData.iCivID, tData.iTurnID - 1);
                    }
                }
            }
            catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
            try {
                if (civI.truce.isEmpty()) continue;
                it = civI.truce.entrySet().iterator();
                while (it.hasNext()) {
                    tData = it.next().getValue();
                    if (tData.iTurnID == 1 && civI.getNumOfProvs() > 0 && CFG.core.getCiv(tData.iCivID).getNumOfProvs() > 0) {
                        if (civI.getIsPlayer()) {
                            civI.getCivDiploGD().messageBox.addMessage(new Message_Truce_Expired(tData.iCivID));
                        }
                        if (CFG.core.getCiv(tData.iCivID).getIsPlayer()) {
                            CFG.core.getCiv((int)tData.iCivID).getCivDiploGD().messageBox.addMessage(new Message_Truce_Expired(i));
                        }
                    }
                    if (tData.iTurnID == 1) {
                        civI.setTruce3(tData.iCivID, tData.iTurnID - 1);
                        it.remove();
                        continue;
                    }
                    civI.setTruce3(tData.iCivID, tData.iTurnID - 1);
                }
                continue;
            }
            catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
    }

    public static void updateBuildingsConstruction() {
        try {
            for (int i = 1; i < CFG.core.getCivsSize(); ++i) {
                CFG.core.getCiv(i).runConstructions();
                if (CFG.core.getCiv(i).getGold() < (long)GameValues.gvTechnology.MIN_MONEY_REQUIRED_TO_ENABLE_RESEARCH) {
                    CFG.core.getCiv(i).setSpendingResearchB(0.0f);
                }
                if (CFG.core.getCiv((int)i).civGD.techPoints.getPointsLeft(i) <= 0) continue;
                CFG.core.getCiv((int)i).getCivDiploGD().messageBox.addMessage(new Message_TechPoints(i));
            }
            for (int a = 0; a < CFG.core.getPlayersSize(); ++a) {
                if (CFG.core.getCiv(CFG.core.getPlayer(a).getCivId()).getNumOfProvs() <= 0) continue;
                if (CFG.core.getCiv(CFG.core.getPlayer(a).getCivId()).getSpendingGoodsB() < CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(CFG.core.getPlayer(a).getCivId()).getIdeology()).getMin_Goods(CFG.core.getPlayer(a).getCivId())) {
                    CFG.core.getCiv((int)CFG.core.getPlayer((int)a).getCivId()).getCivDiploGD().messageBox.addMessage(new Message_GoodsLow(CFG.core.getPlayer(a).getCivId(), (int)(CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(CFG.core.getPlayer(a).getCivId()).getIdeology()).getMin_Goods(CFG.core.getPlayer(a).getCivId()) * 100.0f)));
                }
                if (CFG.core.getCiv(CFG.core.getPlayer(a).getCivId()).getSpendingInvestmentsB() < CFG.ideologiesMgr.getInvestments(CFG.core.getCiv(CFG.core.getPlayer(a).getCivId()).getIdeology(), CFG.core.getPlayer(a).getCivId())) {
                    CFG.core.getCiv((int)CFG.core.getPlayer((int)a).getCivId()).getCivDiploGD().messageBox.addMessage(new Message_InvestmentsLow(CFG.core.getPlayer(a).getCivId(), (int)(CFG.ideologiesMgr.getInvestments(CFG.core.getCiv(CFG.core.getPlayer(a).getCivId()).getIdeology(), CFG.core.getPlayer(a).getCivId()) * 100.0f)));
                }
                if (CFG.core.armyExpertisePointsLeft(CFG.core.getPlayer(a).getCivId()) <= 0) continue;
                CFG.core.getCiv((int)CFG.core.getPlayer((int)a).getCivId()).getCivDiploGD().messageBox.addMessage(new Message_MilitaryExpPoints(CFG.core.getPlayer(a).getCivId()));
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    public static void updateForeignInvests() {
        try {
            for (int i = CFG.core.investForeignGold.size() - 1; i >= 0; --i) {
                if (GameCalendar.TURNID < CFG.core.investForeignGold.get((int)i).returnTurnID) continue;
                CFG.core.getCiv(CFG.core.investForeignGold.get((int)i).civID).setGold(CFG.core.getCiv(CFG.core.investForeignGold.get((int)i).civID).getGold() + (long)CFG.core.investForeignGold.get((int)i).gold);
                if (CFG.core.getCiv(CFG.core.investForeignGold.get((int)i).civID).getIsPlayer()) {
                    CFG.core.getCiv((int)CFG.core.investForeignGold.get((int)i).civID).getCivDiploGD().messageBox.addMessage(new Message_InvestDoneForeign(CFG.core.investForeignGold.get((int)i).inCivID, CFG.core.investForeignGold.get((int)i).provinceID, CFG.core.investForeignGold.get((int)i).gold, CFG.core.investForeignGold.get((int)i).profit));
                }
                CFG.core.investForeignGold.remove(i);
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    public static void updateForeignBuildInvests() {
        try {
            for (int i = CFG.core.buildForeignGold.size() - 1; i >= 0; --i) {
                if (GameCalendar.TURNID < CFG.core.buildForeignGold.get((int)i).returnTurnID) continue;
                CFG.core.getCiv(CFG.core.buildForeignGold.get((int)i).civID).setGold(CFG.core.getCiv(CFG.core.buildForeignGold.get((int)i).civID).getGold() + (long)CFG.core.buildForeignGold.get((int)i).gold);
                if (CFG.core.getCiv(CFG.core.buildForeignGold.get((int)i).civID).getIsPlayer()) {
                    CFG.core.getCiv((int)CFG.core.buildForeignGold.get((int)i).civID).getCivDiploGD().messageBox.addMessage(new Message_InvestBuildDoneForeign(CFG.core.buildForeignGold.get((int)i).inCivID, CFG.core.buildForeignGold.get((int)i).provinceID, CFG.core.buildForeignGold.get((int)i).gold, CFG.core.buildForeignGold.get((int)i).profit));
                }
                CFG.core.buildForeignGold.remove(i);
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    public static void updatePropaganda() {
        try {
            for (int i = CFG.core.propaganda.size() - 1; i >= 0; --i) {
                if (CFG.core.getProv(CFG.core.propaganda.get((int)i).provinceID).getCivId() == CFG.core.propaganda.get((int)i).byCivID) {
                    CFG.core.propaganda.remove(i);
                    continue;
                }
                CFG.core.getProv(CFG.core.propaganda.get((int)i).provinceID).setHappi(CFG.core.getProv(CFG.core.propaganda.get((int)i).provinceID).getHappi() - Festival.festivalHappinessPerTurn(CFG.core.propaganda.get((int)i).provinceID) * GameValues.gvPropaganda.PROPAGANDA_PERC_OF_FESTIVAL_HAPPINESS);
                for (int j = 0; j < CFG.core.getProv(CFG.core.propaganda.get((int)i).provinceID).getNeighProvincesSize(); ++j) {
                    CFG.core.getProv(CFG.core.getProv(CFG.core.propaganda.get((int)i).provinceID).getNeighProvinces(j)).setHappi(CFG.core.getProv(CFG.core.getProv(CFG.core.propaganda.get((int)i).provinceID).getNeighProvinces(j)).getHappi() - Festival.festivalHappinessPerTurn_NeighboringProvinces() * GameValues.gvPropaganda.PROPAGANDA_PERC_OF_FESTIVAL_HAPPINESS_NEIGH_PROVINCES);
                }
                if (CFG.core.getProv(CFG.core.propaganda.get((int)i).provinceID).getHappi() < GameValues.gvPropaganda.INCREASE_REV_RISK_IF_HAPPINESS_BELOW) {
                    CFG.core.getProv(CFG.core.propaganda.get((int)i).provinceID).setRevRisk(CFG.core.getProv(CFG.core.propaganda.get((int)i).provinceID).getRevRisk() + (float)CFG.oR.nextInt(GameValues.gvPropaganda.INCREASE_REV_RISK_IF_HAPPINESS_BELOW_BY_VALUE_PER_TURN_RANDOM_1000) / 1000.0f);
                }
                if (GameCalendar.TURNID < CFG.core.propaganda.get((int)i).endTurnID) continue;
                CFG.core.propaganda.remove(i);
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    public static void updateSanctions() {
        for (int i = 1; i < CFG.core.getCivsSize(); ++i) {
            CFG.core.getCiv(i).updateSanctionsTurns();
        }
    }

    public static void updateDiplomaticSummits() {
        int i;
        try {
            for (i = CFG.core.diplomaticSummits.size() - 1; i >= 0; --i) {
                GameManager.summitImproveRelations(CFG.core.diplomaticSummits.get((int)i).invitedCivs);
                if (CFG.core.diplomaticSummits.get((int)i).endTurnID > GameCalendar.TURNID) continue;
                try {
                    int a;
                    if (CFG.core.getCiv(CFG.core.diplomaticSummits.get((int)i).civHostID).getIsPlayer()) {
                        CFG.menus.rebuildMenu_InGame_Infobox(CFG.lang.get("DiplomaticSummitOver"), CFG.core.getCiv(CFG.core.diplomaticSummits.get((int)i).civHostID).getCivName() + " " + GameCalendar.getCurrDate(), Images.infoDiplomacy);
                    }
                    int civsSize = CFG.core.diplomaticSummits.get((int)i).invitedCivs.size();
                    for (a = 1; a < civsSize; ++a) {
                        Core.addDiplomacyLines(CFG.core.getCapitalOrProvince(CFG.core.diplomaticSummits.get((int)i).invitedCivs.get(0)), CFG.core.getCapitalOrProvince(CFG.core.diplomaticSummits.get((int)i).invitedCivs.get(a)), CFG.COLOR_POSITIVE);
                    }
                    civsSize = CFG.core.diplomaticSummits.get((int)i).invitedCivs.size();
                    for (a = 0; a < civsSize; ++a) {
                        if (!CFG.core.getCiv(CFG.core.diplomaticSummits.get((int)i).invitedCivs.get(a)).getIsPlayer()) continue;
                        CFG.core.getCiv((int)CFG.core.diplomaticSummits.get((int)i).invitedCivs.get((int)a).intValue()).getCivDiploGD().messageBox.addMessage(new Message_SummitIsOver(CFG.core.diplomaticSummits.get((int)i).civHostID));
                    }
                }
                catch (Exception exr) {
                    CFG.exceptionStack(exr);
                }
                CFG.core.diplomaticSummits.remove(i);
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            for (i = CFG.core.diplomaticSummitCooldowns.size() - 1; i >= 0; --i) {
                if (CFG.core.diplomaticSummitCooldowns.get((int)i).turnID > GameCalendar.TURNID) continue;
                CFG.core.diplomaticSummitCooldowns.remove(i);
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static void updateCapitulation() {
        try {
            int i = 0;
            while (i < CFG.core.getWarsSize()) {
                int j;
                if ((float)CFG.core.getWar(i).getWarScore() <= GameValues.gvCapitulation.CAPITULATION_AGGRESSORS_MIN_WAR_SCORE) {
                    for (j = 0; j < CFG.core.getWar(i).getDefendersSize(); ++j) {
                        if (!((float)CFG.core.getWar(i).getProvinces_Defender_OwnTotal(j) / (float)CFG.core.getWar(i).getProvinces_Defender_Own(j) <= CFG.CAPITULATION)) continue;
                        NewTurn.capitulation(CFG.core.getWar(i).getDefenderID(j).getCivID(), CFG.core.getWar(i).getAggressorID(0).getCivID());
                    }
                } else if ((float)CFG.core.getWar(i).getWarScore() >= GameValues.gvCapitulation.CAPITULATION_DEFENDERS_MIN_WAR_SCORE) {
                    for (j = 0; j < CFG.core.getWar(i).getAggressorsSize(); ++j) {
                        if (!((float)CFG.core.getWar(i).getProvinces_Aggressor_OwnTotal(j) / (float)CFG.core.getWar(i).getProvinces_Aggressor_Own(j) <= CFG.CAPITULATION)) continue;
                        NewTurn.capitulation(CFG.core.getWar(i).getAggressorID(j).getCivID(), CFG.core.getWar(i).getDefenderID(0).getCivID());
                    }
                }
                ++i;
            }
            return;
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    public static void capitulation(int civID, int toCivID) {
        block11: {
            try {
                int i;
                if (CFG.core.getCiv(civID).getIsPlayer() && !GameValues.gvCapitulation.PLAYER_CAN_CAPITULATE) {
                    return;
                }
                Civilization civ = CFG.core.getCiv(civID);
                if (civ.getNumOfProvs() <= 0) break block11;
                try {
                    for (i = civ.getArmyInAnotherProvinceSize() - 1; i >= 0; --i) {
                        CFG.core.getProv(civ.getArmyInAnotherProviP(i)).updateArmy4(civID, 0);
                    }
                }
                catch (Exception ex) {
                    CFG.exceptionStack(ex);
                }
                try {
                    for (i = civ.getNumOfProvs() - 1; i >= 0; --i) {
                        CFG.core.getProv(civ.getProvID(i)).updateArmy4(civID, 0);
                    }
                }
                catch (Exception ex) {
                    CFG.exceptionStack(ex);
                }
                for (int i2 = civ.getNumOfProvs() - 1; i2 >= 0; --i2) {
                    CFG.core.getProv(civ.getProvID(i2)).setCivId(toCivID, true);
                }
                if (!CFG.SPECTATOR_MODE && CFG.core.getCiv(toCivID).getIsPlayer()) {
                    CFG.menus.rebuildMenu_InGame_Infobox(CFG.lang.get("Capitulation"), civID, toCivID, Images.infoWar);
                }
            }
            catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
    }

    public static void updateWarWeariness() {
        try {
            for (int i = 1; i < CFG.core.getCivsSize(); ++i) {
                if (CFG.core.getCiv(i).getNumOfProvs() <= 0) continue;
                if (CFG.core.getCiv(i).isAtWarC()) {
                    boolean atWarWithOnlyRebels = true;
                    for (int a = 0; a < CFG.core.getCiv((int)i).isAtWarWithCivs.size(); ++a) {
                        if ((int)CFG.core.getCivRelationOfCivB(i, CFG.core.getCiv((int)i).isAtWarWithCivs.get(a)) != GameValues.gvDiplomacy.RELATION_AT_WAR || CFG.core.getCiv(CFG.core.getCiv((int)i).isAtWarWithCivs.get(a)).getNumOfProvs() <= 0 || CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)CFG.core.getCiv((int)i).isAtWarWithCivs.get((int)a).intValue()).getIdeology()).REVOLUTIONARY) continue;
                        atWarWithOnlyRebels = false;
                        break;
                    }
                    if (atWarWithOnlyRebels) {
                        CFG.core.getCiv(i).setWarWeariness(CFG.core.getCiv(i).getWarWeariness() + GameValues.gvWarWeariness.WAR_WEARINESS_BASE_INCREASE_AT_WAR * Math.min(GameValues.gvWarWeariness.WAR_DURATION_SCALE_LIMIT, (float)CFG.core.getCiv((int)i).civGD.iNumOfTurnsAtWar / (GameValues.gvWarWeariness.WAR_DURATION_SCALE_FACTOR * GameCalendar.GAME_SPEED)) * GameValues.gvWarWeariness.WAR_WEARINESS_AT_WAR_WITH_ONLY_REBELS_MODIFIER);
                        continue;
                    }
                    CFG.core.getCiv(i).setWarWeariness(CFG.core.getCiv(i).getWarWeariness() + GameValues.gvWarWeariness.WAR_WEARINESS_BASE_INCREASE_AT_WAR * Math.min(GameValues.gvWarWeariness.WAR_DURATION_SCALE_LIMIT, (float)CFG.core.getCiv((int)i).civGD.iNumOfTurnsAtWar / (GameValues.gvWarWeariness.WAR_DURATION_SCALE_FACTOR * GameCalendar.GAME_SPEED)));
                    continue;
                }
                CFG.core.getCiv(i).setWarWeariness(CFG.core.getCiv(i).getWarWeariness() - GameValues.gvWarWeariness.WAR_WEARINESS_PEACE_DECREASE);
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    public static void updateLibertyDesireMessages() {
        try {
            if (GameCalendar.TURNID % GameValues.gvVassalLiberty.SEND_VASSALS_HIGH_LIBERTY_MESSAGE_EVERY_X_TURNS == 0) {
                for (int i = 0; i < CFG.core.getPlayersSize(); ++i) {
                    for (int j = 0; j < CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).civGD.iVassalsSize; ++j) {
                        if (!(CFG.core.getCiv(CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).civGD.vassals.get((int)j).iCivID).getVassalLibertyDesire() > GameValues.gvVassalLiberty.MESSAGE_THE_PLAYER_IF_LIBERTY_OVER)) continue;
                        CFG.core.getCiv((int)CFG.core.getPlayer((int)i).getCivId()).civGD.civDiploGD.messageBox.addMessage(new Message_VassalHighLiberty(CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).civGD.vassals.get((int)j).iCivID));
                    }
                }
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    public static void updateProvinceVolunteerArmySent() {
        try {
            for (int i = GameCalendar.TURNID % GameValues.gvArmyRecruit.VOLUNTEER_ARMY_SEND_RESET_AFTER_X_TURNS; i < CFG.core.getProvinSize(); i += GameValues.gvArmyRecruit.VOLUNTEER_ARMY_SEND_RESET_AFTER_X_TURNS) {
                if (CFG.core.getProv((int)i).provinceVolunteerArmySent.isEmpty()) continue;
                for (int j = CFG.core.getProv((int)i).provinceVolunteerArmySent.size() - 1; j >= 0; --j) {
                    if (CFG.core.getProv((int)i).provinceVolunteerArmySent.get((int)j).TURN_ID + GameValues.gvArmyRecruit.VOLUNTEER_ARMY_SEND_RESET_AFTER_X_TURNS > GameCalendar.TURNID) continue;
                    CFG.core.getProv((int)i).provinceVolunteerArmySent.remove(j);
                }
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    public static void updateAlliances() {
        block9: {
            try {
                if (GameCalendar.TURNID % 14 != 0) break block9;
                for (int i = CFG.core.getAlliancesSize() - 1; i > 0; --i) {
                    int j;
                    if (CFG.core.getAlliance(i).getCivilizationsSize() == 0) {
                        CFG.core.lAlliances.remove(i);
                        CFG.core.iAlliancesSize = CFG.core.lAlliances.size();
                        for (int j2 = 1; j2 < CFG.core.getCivsSize(); ++j2) {
                            if (CFG.core.getCiv(j2).getAlliance() < i) continue;
                            CFG.core.getCiv(j2).setAlliance(CFG.core.getCiv(j2).getAlliance() - 1);
                        }
                        continue;
                    }
                    int numOfCivsInAlliance = 0;
                    for (j = CFG.core.getAlliance(i).getCivilizationsSize() - 1; j >= 0; --j) {
                        if (CFG.core.getAlliance(i).getCivilization(j) <= 0 || CFG.core.getAlliance(i).getCivilization(j) >= CFG.core.getCivsSize()) continue;
                        if (CFG.core.getCiv(CFG.core.getAlliance(i).getCivilization(j)).isAtWarC()) {
                            numOfCivsInAlliance += 5;
                            continue;
                        }
                        if (CFG.core.getCiv(CFG.core.getAlliance(i).getCivilization(j)).getNumOfProvs() <= 0) continue;
                        ++numOfCivsInAlliance;
                    }
                    if (numOfCivsInAlliance > true) continue;
                    for (j = CFG.core.getAlliance(i).getCivilizationsSize() - 1; j >= 0; --j) {
                        CFG.core.getCiv(CFG.core.getAlliance(i).getCivilization(j)).setAlliance(0);
                    }
                    CFG.core.lAlliances.remove(i);
                    CFG.core.iAlliancesSize = CFG.core.lAlliances.size();
                    for (j = 1; j < CFG.core.getCivsSize(); ++j) {
                        if (CFG.core.getCiv(j).getAlliance() < i) continue;
                        CFG.core.getCiv(j).setAlliance(CFG.core.getCiv(j).getAlliance() - 1);
                    }
                }
            }
            catch (Exception exr) {
                CFG.exceptionStack(exr);
            }
        }
    }

    static {
        ageRiskModifier = 1.0f;
        ageDevMod = 1.0f;
        tempCivs = new ArrayList<PopulationGrowth>();
        happinessChange_ByTaxation = new ArrayList<Float>();
        happinessChange_ByTaxation_Occupied = new ArrayList<Float>();
        goodsUpdate = new ArrayList<Float>();
        devUpdate = new ArrayList<Float>();
        ecoUpdate = new ArrayList<Float>();
    }
}
