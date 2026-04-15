package age.of.civilizations2.jakowski.lukasz.AI.AI_Playstyle;

import age.of.civilizations2.jakowski.lukasz.AI.AI_ArmyUpkeep;
import age.of.civilizations2.jakowski.lukasz.AI.AI_Assimilate_Data;
import age.of.civilizations2.jakowski.lukasz.AI.AI_Build.AI_Build;
import age.of.civilizations2.jakowski.lukasz.AI.AI_Build.AI_Build_Option;
import age.of.civilizations2.jakowski.lukasz.AI.AI_Build.AI_Build_Option_Armoury;
import age.of.civilizations2.jakowski.lukasz.AI.AI_Build.AI_Build_Option_Fort;
import age.of.civilizations2.jakowski.lukasz.AI.AI_Build.AI_Build_Option_Invest;
import age.of.civilizations2.jakowski.lukasz.AI.AI_Build.AI_Build_Option_Invest2;
import age.of.civilizations2.jakowski.lukasz.AI.AI_Build.AI_Build_Option_Invest_Development;
import age.of.civilizations2.jakowski.lukasz.AI.AI_Build.AI_Build_Option_Invest_Development2;
import age.of.civilizations2.jakowski.lukasz.AI.AI_Build.AI_Build_Option_Library;
import age.of.civilizations2.jakowski.lukasz.AI.AI_Build.AI_Build_Option_Market;
import age.of.civilizations2.jakowski.lukasz.AI.AI_Build.AI_Build_Option_Port;
import age.of.civilizations2.jakowski.lukasz.AI.AI_Build.AI_Build_Option_Supplies;
import age.of.civilizations2.jakowski.lukasz.AI.AI_Build.AI_Build_Option_Tower;
import age.of.civilizations2.jakowski.lukasz.AI.AI_Build.AI_Build_Option_Workshop;
import age.of.civilizations2.jakowski.lukasz.AI.AI_CivsInRange;
import age.of.civilizations2.jakowski.lukasz.AI.AI_ImproveRelations;
import age.of.civilizations2.jakowski.lukasz.AI.AI_NeighProvinces;
import age.of.civilizations2.jakowski.lukasz.AI.AI_RegoupArmyData;
import age.of.civilizations2.jakowski.lukasz.AI.AI_ReleaseVassal;
import age.of.civilizations2.jakowski.lukasz.AI.AI_Rival;
import age.of.civilizations2.jakowski.lukasz.AI.AI_Skills.AI_Skills;
import age.of.civilizations2.jakowski.lukasz.AI.AI_Skills.AI_Skills_Administration;
import age.of.civilizations2.jakowski.lukasz.AI.AI_Skills.AI_Skills_Assimilate;
import age.of.civilizations2.jakowski.lukasz.AI.AI_Skills.AI_Skills_Eco;
import age.of.civilizations2.jakowski.lukasz.AI.AI_Skills.AI_Skills_Military;
import age.of.civilizations2.jakowski.lukasz.AI.AI_Skills.AI_Skills_Movement;
import age.of.civilizations2.jakowski.lukasz.AI.AI_Skills.AI_Skills_Production;
import age.of.civilizations2.jakowski.lukasz.AI.AI_Skills.AI_Skills_Recruitable;
import age.of.civilizations2.jakowski.lukasz.AI.AI_Skills.AI_Skills_Research;
import age.of.civilizations2.jakowski.lukasz.AI.AI_Skills.AI_Skills_Taxation;
import age.of.civilizations2.jakowski.lukasz.AI.FrontLine.AI_Frontline;
import age.of.civilizations2.jakowski.lukasz.AI.Province.AI_ProvinceInfo;
import age.of.civilizations2.jakowski.lukasz.AI.Province.AI_ProvinceInfo_War;
import age.of.civilizations2.jakowski.lukasz.AI.Province.AI_ProvinceValue;
import age.of.civilizations2.jakowski.lukasz.Alliance;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Civilization;
import age.of.civilizations2.jakowski.lukasz.Civilization_SentMessages;
import age.of.civilizations2.jakowski.lukasz.Civilizations.CivArmy_Mission.CivArmyMission_ColonizeProvince;
import age.of.civilizations2.jakowski.lukasz.Civilizations.CivArmy_Mission.CivArmyMission_ColonizeProvince_Just;
import age.of.civilizations2.jakowski.lukasz.Civilizations.CivArmy_Mission.CivArmyMission_NavalInvasion;
import age.of.civilizations2.jakowski.lukasz.Civilizations.CivArmy_Mission.CivArmyMission_RegroupAfterRecruitment;
import age.of.civilizations2.jakowski.lukasz.Civilizations.CivArmy_Mission.CivArmyMission_RegroupAfterRecruitment_War;
import age.of.civilizations2.jakowski.lukasz.Civilizations.CivArmy_Mission.CivArmyMission_RegroupAfterRecruitment_War_Double;
import age.of.civilizations2.jakowski.lukasz.Civilizations.CivArmy_Mission.CivArmyMission_Type;
import age.of.civilizations2.jakowski.lukasz.Civilizations.PeaceT.PeaceTreaty_Data;
import age.of.civilizations2.jakowski.lukasz.Diplomacy.Festivals.Festival;
import age.of.civilizations2.jakowski.lukasz.Diplomacy.Loans;
import age.of.civilizations2.jakowski.lukasz.Diplomacy.Plunder;
import age.of.civilizations2.jakowski.lukasz.GameCalendar;
import age.of.civilizations2.jakowski.lukasz.GameManager;
import age.of.civilizations2.jakowski.lukasz.GameValues.GameValues;
import age.of.civilizations2.jakowski.lukasz.Managers.RivalsManager;
import age.of.civilizations2.jakowski.lukasz.MapA.BuildingsManager;
import age.of.civilizations2.jakowski.lukasz.MapA.Distance;
import age.of.civilizations2.jakowski.lukasz.MapA.Plagues.Nuke.NukeManager;
import age.of.civilizations2.jakowski.lukasz.Menus.PeaceTreaty.Menu_PeaceTreaty;
import age.of.civilizations2.jakowski.lukasz.Messages.MessageType;
import age.of.civilizations2.jakowski.lukasz.Messages.Relations.Message_Rivals;
import age.of.civilizations2.jakowski.lukasz.Province;
import age.of.civilizations2.jakowski.lukasz.RegroupArmy.RegroupArmy;
import age.of.civilizations2.jakowski.lukasz.RegroupArmy.RegroupArmy_AtPeace;
import age.of.civilizations2.jakowski.lukasz.RegroupArmy.RegroupArmy_AtWar;
import age.of.civilizations2.jakowski.lukasz.RegroupArmy.RegroupArmy_ToTheFront_Double;
import age.of.civilizations2.jakowski.lukasz.SkillsManager;
import age.of.civilizations2.jakowski.lukasz.TradeRequest_GameData;
import age.of.civilizations2.jakowski.lukasz.Ultimatum_GameData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AIPlaystyle {
    public String TAG = "DEFAULT";
    public float PERSONALITY_MIN_MILITARY_SPENDINGS_DEFAULT = 0.1f;
    public int PERSONALITY_MIN_MILITARY_SPENDINGS_RANDOM = 13;
    public float PERSONALITY_MIN_DIFFERENCE_IN_DEVELOPMENT_TO_TECHNOLOGY_DEFAULT = 0.6f;
    public int PERSONALITY_MIN_DIFFERENCE_IN_DEVELOPMENT_TO_TECHNOLOGY_RANDOM = 35;
    public int PERSONALITY_MIN_HAPPINESS_DEFAULT = 69;
    public int PERSONALITY_MIN_HAPPINESS_RANDOM = 24;
    public float PERSONALITY_FORGIVENESS_DEFAULT = 1.0f;
    public int PERSONALITY_FORGIVENESS_RANDOM = 50;
    public int USE_OF_BUDGET_FOR_SPENDINGS = 35;
    public int USE_OF_BUDGET_FOR_SPENDINGS_RANDOM = 65;
    public int PERSONALITY_GOODS_RANDOM = 100;
    public int PERSONALITY_INVESTMENTS_RANDOM = 100;
    public int PERSONALITY_RESEARCH_RANDOM = 100;
    public int PERSONALITY_PLUNDER_MIN = 0;
    public int PERSONALITY_PLUNDER_RANDOM = 45;
    public int PERSONALITY_PLUNDER_LOCK = 78;
    public float PERSONALITY_MIN_AGGRESSION_DEFAULT = 0.2475f;
    public int PERSONALITY_MIN_AGGRESSION_RANDOM_100 = 4825;
    public boolean armyOverBudget = false;
    public int MIN_TURNS_TO_ABANDON_USELESS_PROVINCE = 25;

    public float getMinMilitarySpending(int nCivID) {
        return CFG.core.getCiv((int)nCivID).civGD.civPers.MIN_MILITARY_SPENDINGS;
    }

    public void turnOrders(int nCivID) {
        this.armyOverBudget = false;
        this.relocateLostCapital(nCivID);
        this.changeTypeOfIdeology(nCivID);
        if (CFG.core.getCiv((int)nCivID).civGD.civPlans.iWarPrepsSize > 0) {
            CFG.core.getCiv((int)nCivID).civGD.civPlans.checkWarPreparations(nCivID);
        }
        try {
            if (CFG.core.getCiv(nCivID).isAtWarC()) {
                this.takeLoanAtWar(nCivID);
                this.nukeDropBomb(nCivID);
                this.defendFromSeaInvasion(nCivID);
                this.moveAtWar(nCivID);
                this.armyOverBudget = true;
            }
            if (CFG.core.getCiv((int)nCivID).civGD.civPlans.isPreparingForTheWar()) {
                this.prepareForWar2(nCivID);
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
        CFG.oAI.expandNeutral.expandToNeutralProvinces(nCivID);
        if (this.getMinMilitarySpending(nCivID) + 0.025f < CFG.core.getCiv((int)nCivID).iMilitaryUpkeep_PERC) {
            this.armyOverBudget_Disband(nCivID);
            this.armyOverBudget = true;
        }
        if (CFG.core.getCiv(nCivID).getHappiness() < GameValues.gvAiProvince.HAPPINESS_CRISIS_BASE + CFG.oR.nextInt(GameValues.gvAiProvince.HAPPINESS_CRISIS_RANDOM)) {
            this.happinessCrisis(nCivID);
        } else if (!CFG.core.getCiv(nCivID).isAtWarC()) {
            if (!CFG.core.getCiv((int)nCivID).provincesWithLowHappiness.isEmpty() && CFG.core.getCiv(nCivID).getTaxationLvl() <= CFG.ideologiesMgr.getAcceptableTaxation(CFG.core.getCiv(nCivID).getIdeology(), nCivID) && CFG.core.getCiv(nCivID).getSpendingGoodsB() >= CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(nCivID).getIdeology()).getMin_Goods(nCivID)) {
                this.hostFestivals(nCivID, CFG.core.getCiv(nCivID).getNumOfProvs());
            }
        } else if (!CFG.core.getCiv((int)nCivID).provincesWithLowHappiness.isEmpty() && CFG.core.getCiv(nCivID).getTaxationLvl() <= CFG.ideologiesMgr.getAcceptableTaxation(CFG.core.getCiv(nCivID).getIdeology(), nCivID) && CFG.core.getCiv(nCivID).getSpendingGoodsB() >= CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(nCivID).getIdeology()).getMin_Goods(nCivID)) {
            this.hostFestivals(nCivID, 1 + CFG.oR.nextInt(3));
        }
        if (!CFG.core.getCiv((int)nCivID).provincesWithLowStability.isEmpty()) {
            this.assimilateProvinces(nCivID);
        }
        if ((!this.armyOverBudget || CFG.core.getCiv(nCivID).getBordersWithEnemy() == 0) && CFG.core.getCiv(nCivID).getGold() > 0L && this.getMinMilitarySpending(nCivID) > CFG.core.getCiv((int)nCivID).iMilitaryUpkeep_PERC + 0.0275f) {
            this.recruitMilitary_MinSpending(nCivID);
        }
        if (!this.armyOverBudget) {
            this.colonizeProvinces(nCivID);
        }
        if (!CFG.core.getCiv(nCivID).isAtWarC() && !CFG.core.getCiv((int)nCivID).civGD.civPlans.isPreparingForTheWar()) {
            this.regroupArmy_AtPeace(nCivID);
        }
        this.regroupArmyAfterRecruitment(nCivID);
        if (CFG.core.getCiv((int)nCivID).civGD.civPlans.isPreparingForTheWar()) {
            this.prepareForWar_MoveReadyArmies(nCivID);
            for (int i = CFG.core.getCiv((int)nCivID).civGD.civPlans.iWarPrepsSize - 1; i >= 0; --i) {
                if (CFG.core.getCiv((int)nCivID).civGD.civPlans.warPreps.get((int)i).iNumOfTurnsLeft-- > 0) continue;
                int tOnCivID = CFG.core.getCiv((int)nCivID).civGD.civPlans.warPreps.get((int)i).onCivID;
                CFG.core.declareWar(nCivID, CFG.core.getCiv((int)nCivID).civGD.civPlans.warPreps.get((int)i).onCivID, false);
                for (int k = CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.size() - 1; k >= 0; --k) {
                    if (CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.get((int)k).MISSION_TYPE != CivArmyMission_Type.PREAPARE_FOR_WAR || CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.get((int)k).MISSION_ID != tOnCivID) continue;
                    CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.remove(k);
                }
                try {
                    CFG.core.getCiv((int)nCivID).civGD.civPlans.warPreps.remove(i);
                    CFG.core.getCiv((int)nCivID).civGD.civPlans.iWarPrepsSize = CFG.core.getCiv((int)nCivID).civGD.civPlans.warPreps.size();
                    continue;
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
        } else if (!CFG.core.getCiv(nCivID).isAtWarC() && CFG.core.getCiv(nCivID).getMovemPoints() > GameValues.gvAiProvince.BUILD_INVEST_MIN_MOVEMENT_POINTS) {
            if (GameCalendar.TURNID % GameValues.gvAiProvince.EXTRA_INVEST_ECO_EVERY_X_TURN == nCivID % GameValues.gvAiProvince.EXTRA_INVEST_ECO_EVERY_X_TURN) {
                if (CFG.core.getCiv(nCivID).getMovemPoints() >= GameValues.gvInvestEconomy.INVEST_ECO_COST_MOVEMENT_POINTS && CFG.core.getCiv(nCivID).getGold() > (long)GameValues.gvAiProvince.MIN_GOLD_TO_INVEST) {
                    this.buildInvestEco(nCivID);
                }
            } else if (CFG.core.getCiv(nCivID).getMovemPoints() > GameValues.gvAiProvince.BUILD_INVEST_MIN_MOVEMENT_POINTS && CFG.core.getCiv(nCivID).getGold() > (long)GameValues.gvAiProvince.MIN_GOLD_TO_BUILD) {
                this.buildBuildings(nCivID);
            }
            if (GameCalendar.TURNID > GameValues.gvAiProvince.EXTRA_INVEST_DEVELOPMENT_MIN_TURN_ID && CFG.core.getCiv(nCivID).getMovemPoints() >= GameValues.gvInvestEconomy.INVEST_ECO_COST_MOVEMENT_POINTS && CFG.core.getCiv(nCivID).getGold() > (long)GameValues.gvAiProvince.MIN_GOLD_TO_INVEST) {
                this.buildInvestDev(nCivID);
            }
        }
        CFG.core.getCiv((int)nCivID).civGD.moveAtWar_ProvincesLostAndConquered_LastTurn = 0;
    }

    public final void turnOrdersEssential(int nCivID) {
        this.respondToEvents(nCivID);
        this.updateSentMessages(nCivID);
        this.respondToMessages(nCivID);
        this.diplomacyActions(nCivID);
        this.manageBudget(nCivID);
    }

    public final void turnOrdersEssential_respondToEvents(int nCivID) {
        this.respondToEvents(nCivID);
    }

    public final void turnOrdersEssential_updateSentMessages(int nCivID) {
        this.updateSentMessages(nCivID);
    }

    public final void turnOrdersEssential_respondToMessages(int nCivID) {
        this.respondToMessages(nCivID);
    }

    public final void turnOrdersEssential_diplomacyActions(int nCivID) {
        this.diplomacyActions(nCivID);
    }

    public final void turnOrdersEssential_diplomacyActions_diplomacyActions_BuildCivsInRange(int nCivID) {
        this.diplomacyActions_diplomacyActions_BuildCivsInRange(nCivID);
    }

    public final void turnOrdersEssential_diplomacyActions_diplomacyActions_RivalCiv(int nCivID) {
        this.diplomacyActions_diplomacyActions_RivalCiv(nCivID);
    }

    public final void turnOrdersEssential_diplomacyActions_diplomacyActions_FindFriendlyCivs(int nCivID) {
        this.diplomacyActions_diplomacyActions_FindFriendlyCivs(nCivID);
    }

    public final void turnOrdersEssential_diplomacyActions_diplomacyActions_DeclareWar(int nCivID) {
        this.diplomacyActions_diplomacyActions_DeclareWar(nCivID);
    }

    public final void turnOrdersEssential_diplomacyActions_diplomacyActions_Ally(int nCivID) {
        this.diplomacyActions_diplomacyActions_Ally(nCivID);
    }

    public final void turnOrdersEssential_manageBudget(int nCivID) {
        this.manageBudget(nCivID);
    }

    public final void turnOrdersEssential_2(int nCivID) {
        this.updateLibertyDesire(nCivID);
    }

    public void diplomacyActions(int nCivID) {
        this.diplomacyActions_BuildCivsInRange(nCivID);
        if (!CFG.core.getCiv(nCivID).isAtWarC() && !CFG.core.getCiv((int)nCivID).civGD.civPlans.isPreparingForTheWar()) {
            if (GameValues.gvAiRivals.USE_NEW_RIVALS_SYSTEM) {
                this.diplomacyActions_RivalCiv_New(nCivID);
            } else {
                this.diplomacyActions_RivalCiv(nCivID);
            }
            this.diplomacyActions_FormCiv(nCivID);
            this.diplomacyActions_SurroundedVassals(nCivID);
        }
        this.diplomacyActions_FindFriendlyCivs(nCivID);
        if (!CFG.core.getCiv(nCivID).isAtWarC() && !CFG.core.getCiv((int)nCivID).civGD.civPlans.isPreparingForTheWar()) {
            if (CFG.USE_NEW_DECLARE_WAR_SYSTEM && (CFG.USE_OLD_DECLARE_WAR_CHANGE_100 == 0 || CFG.oR.nextInt(100) >= CFG.USE_OLD_DECLARE_WAR_CHANGE_100)) {
                this.diplomacyActions_DeclareWar(nCivID);
            } else {
                this.diplomacyActions_DeclareWar_Old(nCivID);
            }
        }
        this.diplomacyActions_Ally(nCivID);
    }

    public void diplomacyActions_diplomacyActions_BuildCivsInRange(int nCivID) {
        this.diplomacyActions_BuildCivsInRange(nCivID);
    }

    public void diplomacyActions_diplomacyActions_RivalCiv(int nCivID) {
        if (!CFG.core.getCiv(nCivID).isAtWarC() && !CFG.core.getCiv((int)nCivID).civGD.civPlans.isPreparingForTheWar()) {
            if (GameValues.gvAiRivals.USE_NEW_RIVALS_SYSTEM) {
                this.diplomacyActions_RivalCiv_New(nCivID);
            } else {
                this.diplomacyActions_RivalCiv(nCivID);
            }
            this.diplomacyActions_FormCiv(nCivID);
            this.diplomacyActions_SurroundedVassals(nCivID);
        }
    }

    public void diplomacyActions_diplomacyActions_FindFriendlyCivs(int nCivID) {
        this.diplomacyActions_FindFriendlyCivs(nCivID);
    }

    public void diplomacyActions_diplomacyActions_DeclareWar(int nCivID) {
        if (!CFG.core.getCiv(nCivID).isAtWarC() && !CFG.core.getCiv((int)nCivID).civGD.civPlans.isPreparingForTheWar()) {
            if (CFG.USE_NEW_DECLARE_WAR_SYSTEM && (CFG.USE_OLD_DECLARE_WAR_CHANGE_100 == 0 || CFG.oR.nextInt(100) >= CFG.USE_OLD_DECLARE_WAR_CHANGE_100)) {
                this.diplomacyActions_DeclareWar(nCivID);
            } else {
                this.diplomacyActions_DeclareWar_Old(nCivID);
            }
        }
    }

    public void diplomacyActions_diplomacyActions_Ally(int nCivID) {
        this.diplomacyActions_Ally(nCivID);
    }

    public final void diplomacyActions_FormCiv(int nCivID) {
        if (GameCalendar.TURNID >= CFG.core.getCiv((int)nCivID).civGD.checkFormCiv_TurnID) {
            if (CFG.core.getCiv(nCivID).getTagsCanFormCSize() > 0) {
                for (int i = 0; i < CFG.core.getCiv(nCivID).getTagsCanFormCSize(); ++i) {
                    if (!CFG.canFormACiv(nCivID, CFG.core.getCiv(nCivID).getTagsCanFormC(i), true)) continue;
                    CFG.loadFormableCiv_GameData(CFG.core.getCiv(nCivID).getTagsCanFormC(i));
                    CFG.formCiv(nCivID);
                    CFG.core.getCiv((int)nCivID).civGD.checkFormCiv_TurnID = GameCalendar.TURNID + GameValues.gvAiFormCiv.NEXT_FORM_CIV_CHECK_TURN_ID_AFTER_FORMING + CFG.oR.nextInt(GameValues.gvAiFormCiv.NEXT_FORM_CIV_CHECK_TURN_ID_RANDOM_AFTER_FORMING);
                    return;
                }
                CFG.core.getCiv((int)nCivID).civGD.checkFormCiv_TurnID = GameCalendar.TURNID + GameValues.gvAiFormCiv.NEXT_FORM_CIV_CHECK_TURN_ID + CFG.oR.nextInt(GameValues.gvAiFormCiv.NEXT_FORM_CIV_CHECK_TURN_ID_RANDOM);
            } else {
                CFG.core.getCiv((int)nCivID).civGD.checkFormCiv_TurnID = GameCalendar.TURNID + GameValues.gvAiFormCiv.NEXT_FORM_CIV_CHECK_TURN_ID_NONE_TO_FORM + CFG.oR.nextInt(GameValues.gvAiFormCiv.NEXT_FORM_CIV_CHECK_TURN_ID_RANDOM_NONE_TO_FORM);
            }
        }
    }

    public final void diplomacyActions_SurroundedVassals(int nCivID) {
        if (CFG.core.getCiv((int)nCivID).civGD.circledVassals_TurnID <= GameCalendar.TURNID) {
            if (CFG.core.getCiv((int)nCivID).civGD.iVassalsSize > 0) {
                try {
                    for (int z = 0; z < CFG.core.getCiv((int)nCivID).civGD.iVassalsSize; ++z) {
                        if (!CFG.core.getCiv((int)CFG.core.getCiv((int)nCivID).civGD.vassals.get((int)z).iCivID).lFrontLines.isEmpty() || CFG.core.getCiv(CFG.core.getCiv((int)nCivID).civGD.vassals.get((int)z).iCivID).getSeaAccess() > 0) continue;
                        if (CFG.core.getCivRelationOfCivB(nCivID, CFG.core.getCiv((int)nCivID).civGD.vassals.get((int)z).iCivID) > (float)GameValues.gvUltimatum.ULTIMATUM_REQUIRED_RELATIONS) {
                            int randNum = CFG.oR.nextInt(5);
                            for (int a = 0; a < 3 + randNum; ++a) {
                                GameManager.decreaseRelation(nCivID, CFG.core.getCiv((int)nCivID).civGD.vassals.get((int)z).iCivID, 10);
                            }
                        }
                        Ultimatum_GameData nUltimatum = new Ultimatum_GameData();
                        nUltimatum.demandAnexation = true;
                        GameManager.sendUltimatum(CFG.core.getCiv((int)nCivID).civGD.vassals.get((int)z).iCivID, nCivID, nUltimatum, CFG.core.getCiv(nCivID).getNumberOfUnits());
                    }
                }
                catch (Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
            CFG.core.getCiv((int)nCivID).civGD.circledVassals_TurnID = GameCalendar.TURNID + GameValues.gvAiVassals.NEXT_SURROUNDED_VASSALS_CHECK_TURN_ID + CFG.oR.nextInt(GameValues.gvAiVassals.NEXT_SURROUNDED_VASSALS_CHECK_TURN_ID_RANDOM);
        }
    }

    /*
     * Unable to fully structure code
     */
    public final void diplomacyActions_DeclareWar(int nCivID) {
        if (GameCalendar.AI_AGGRESSIVENESS > 0.0f) {
            civ = CFG.core.getCiv(nCivID);
            if (civ.civGD.declareWarCheckNextTurnID <= GameCalendar.TURNID) {
                if ((float)CFG.oR.nextInt(GameCalendar.MAX_AI_AGGRESSIVENESS) > civ.civGD.civPers.AI_CIV_AGGRESSION * 100.0f) {
                    CFG.core.getCiv((int)nCivID).civGD.declareWarCheckNextTurnID = GameCalendar.TURNID + (int)(((float)(GameValues.gvAiDeclareWar.BASE_WAR_CHECK_DELAY_TURNS + CFG.oR.nextInt(GameValues.gvAiDeclareWar.WAR_CHECK_DELAY_RANDOM_TURNS)) + (float)CFG.oR.nextInt(CFG.oAI.NUM_OF_CIVS_IN_THE_GAME + 1) / GameValues.gvAiDeclareWar.WAR_CHECK_CIV_COUNT_RANDOM_DIVISOR) / Math.max(0.01f, GameCalendar.AI_AGGRESSIVENESS));
                    return;
                }
                if (civ.getGold() < (long)GameValues.gvAiDeclareWar.AI_DECLARE_WAR_ONLY_IF_GOLD_OVER) {
                    CFG.core.getCiv((int)nCivID).civGD.declareWarCheckNextTurnID = GameCalendar.TURNID + GameValues.gvAiDeclareWar.BASE_WAR_CHECK_DELAY_TURNS + (int)(((float)(1 + CFG.oR.nextInt(GameValues.gvAiDeclareWar.WAR_CHECK_DELAY_RANDOM_TURNS)) + (float)CFG.oR.nextInt(CFG.oAI.NUM_OF_CIVS_IN_THE_GAME + 1) / GameValues.gvAiDeclareWar.WAR_CHECK_CIV_COUNT_RANDOM_DIVISOR_2) / Math.max(0.01f, GameCalendar.AI_AGGRESSIVENESS));
                    return;
                }
                if (civ.getStabilityCiv() < GameValues.gvAiDeclareWar.AI_DECLARE_WAR_ONLY_IF_STABILITY_OVER) {
                    CFG.core.getCiv((int)nCivID).civGD.declareWarCheckNextTurnID = GameCalendar.TURNID + GameValues.gvAiDeclareWar.BASE_WAR_CHECK_DELAY_TURNS + (int)(((float)(CFG.core.getCiv((int)nCivID).provincesWithLowStability.size() * 2 + CFG.oR.nextInt(GameValues.gvAiDeclareWar.WAR_CHECK_DELAY_RANDOM_TURNS)) + (float)CFG.oR.nextInt(CFG.oAI.NUM_OF_CIVS_IN_THE_GAME + 1) / GameValues.gvAiDeclareWar.WAR_CHECK_CIV_COUNT_RANDOM_DIVISOR_2) / Math.max(0.01f, GameCalendar.AI_AGGRESSIVENESS));
                    return;
                }
                if ((float)civ.getHappiness() < GameValues.gvAiDeclareWar.AI_DECLARE_WAR_ONLY_IF_HAPPINESS_OVER) {
                    CFG.core.getCiv((int)nCivID).civGD.declareWarCheckNextTurnID = GameCalendar.TURNID + GameValues.gvAiDeclareWar.BASE_WAR_CHECK_DELAY_TURNS + (int)(((float)(CFG.core.getCiv((int)nCivID).provincesWithLowHappiness.size() * 2 + CFG.oR.nextInt(GameValues.gvAiDeclareWar.WAR_CHECK_DELAY_RANDOM_TURNS)) + (float)CFG.oR.nextInt(CFG.oAI.NUM_OF_CIVS_IN_THE_GAME + 1) / GameValues.gvAiDeclareWar.WAR_CHECK_CIV_COUNT_RANDOM_DIVISOR_2) / Math.max(0.01f, GameCalendar.AI_AGGRESSIVENESS));
                    return;
                }
                if (CFG.core.getCiv(civ.getPuppetOfCiv()).getIsPlayer() && CFG.core.getCiv(civ.getPuppetOfCiv()).getNumOfProvs() > 0) {
                    pID = CFG.core.getPlayerIDbyCivID(civ.getPuppetOfCiv());
                    if (pID >= 0 && !CFG.core.getPlayer((int)pID).playerGD.VASSALS_CAN_DECLARE_WARS) {
                        CFG.core.getCiv((int)nCivID).civGD.declareWarCheckNextTurnID = GameCalendar.TURNID + GameValues.gvAiDeclareWar.BASE_WAR_CHECK_DELAY_TURNS + (int)(((float)(CFG.core.getCiv((int)nCivID).provincesWithLowHappiness.size() * 2 + CFG.oR.nextInt(GameValues.gvAiDeclareWar.WAR_CHECK_DELAY_RANDOM_TURNS)) + (float)CFG.oR.nextInt(CFG.oAI.NUM_OF_CIVS_IN_THE_GAME + 1) / GameValues.gvAiDeclareWar.WAR_CHECK_CIV_COUNT_RANDOM_DIVISOR_2) / Math.max(0.01f, GameCalendar.AI_AGGRESSIVENESS));
                        return;
                    }
                } else if (civ.getPuppetOfCiv() != nCivID && CFG.core.getCiv(civ.getPuppetOfCiv()).getNumOfProvs() > 0 && !CFG.AI_VASSALS_CAN_DECLARE_WARS) {
                    CFG.core.getCiv((int)nCivID).civGD.declareWarCheckNextTurnID = GameCalendar.TURNID + GameValues.gvAiDeclareWar.BASE_WAR_CHECK_DELAY_TURNS + (int)(((float)(CFG.core.getCiv((int)nCivID).provincesWithLowHappiness.size() * 2 + CFG.oR.nextInt(GameValues.gvAiDeclareWar.WAR_CHECK_DELAY_RANDOM_TURNS)) + (float)CFG.oR.nextInt(CFG.oAI.NUM_OF_CIVS_IN_THE_GAME + 1) / GameValues.gvAiDeclareWar.WAR_CHECK_CIV_COUNT_RANDOM_DIVISOR_2) / Math.max(0.01f, GameCalendar.AI_AGGRESSIVENESS));
                    return;
                }
                possibleCivs = new ArrayList<Integer>();
                if (GameValues.gvAiDeclareWar.AI_PREPARE_FOR_WAR_PRIORITIZE_TRIBAL && CFG.oR.nextInt(100) < GameValues.gvAiDeclareWar.AI_PREPARE_FOR_WAR_PRIORITIZE_NEIGHBORS_TRIBAL_CHANCE && CFG.ideologiesMgr.getIdeologyID((int)civ.getIdeology()).CAN_BECOME_CIVILIZED < 0) {
                    for (a = 0; a < civ.civNeighbors.civsSize; ++a) {
                        if (CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)civ.civNeighbors.civs.get((int)a).civID).getIdeology()).CAN_BECOME_CIVILIZED < 0 || CFG.core.getCiv(civ.civNeighbors.civs.get((int)a).civID).getNumOfProvs() <= 0 || CFG.core.isAlly(nCivID, civ.civNeighbors.civs.get((int)a).civID)) continue;
                        possibleCivs.add(civ.civNeighbors.civs.get((int)a).civID);
                    }
                }
                if (possibleCivs.isEmpty() && civ.getRankPos() < GameValues.gvAiDeclareWar.AI_PREPARE_FOR_WAR_CONQUER_TRIBAL_TOP_RANK_CIVS && civ.getSeaAccess_PortProvinces_Size() > 0 && CFG.oR.nextInt(100) < GameValues.gvAiDeclareWar.AI_PREPARE_FOR_WAR_CONQUER_TRIBAL_TOP_RANK_CIVS_CHANCE) {
                    distance = new ArrayList<CivDistance>();
                    for (i = 1; i < nCivID; ++i) {
                        if (CFG.core.getCiv(i).getNumOfProvs() <= 0 || CFG.core.getCiv(i).getSeaAccess() <= 0 || CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)i).getIdeology()).CAN_BECOME_CIVILIZED < 0) continue;
                        distance.add(new CivDistance(i, Distance.getDistanceFromAToB_PercOfMax(civ.getCapitalProvID(), CFG.core.getCiv(i).getCapitalProvID())));
                    }
                    for (i = nCivID + 1; i < CFG.core.getCivsSize(); ++i) {
                        if (CFG.core.getCiv(i).getNumOfProvs() <= 0 || CFG.core.getCiv(i).getSeaAccess() <= 0 || CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)i).getIdeology()).CAN_BECOME_CIVILIZED < 0) continue;
                        distance.add(new CivDistance(i, Distance.getDistanceFromAToB_PercOfMax(civ.getCapitalProvID(), CFG.core.getCiv(i).getCapitalProvID())));
                    }
                    for (i = 0; !distance.isEmpty() && i < GameValues.gvAiDeclareWar.AI_PREPARE_FOR_WAR_CONQUER_TRIBAL_LIMIT; ++i) {
                        bestID = 0;
                        jSize = distance.size();
                        for (j = 1; j < jSize; ++j) {
                            if (!(((CivDistance)distance.get((int)bestID)).distance > ((CivDistance)distance.get((int)j)).distance)) continue;
                            bestID = j;
                        }
                        if (!CFG.core.isAlly(nCivID, ((CivDistance)distance.get((int)bestID)).civID)) {
                            possibleCivs.add(((CivDistance)distance.get((int)bestID)).civID);
                        } else {
                            --i;
                        }
                        distance.remove(bestID);
                    }
                }
                if (possibleCivs.isEmpty() && CFG.AI_CONQUER_VASSALS) {
                    conquerVassal = false;
                    if (civ.civGD.iVassalsSize > 0 && civ.civGD.iVassalsSize > CFG.AI_CONQUER_OWN_VASSALS_IF_OVER && CFG.oR.nextInt(100) < GameValues.gvAiDeclareWar.AI_CONQUER_OWN_VASSALS_CHANCE) {
                        for (i = 0; i < civ.civNeighbors.civsSize; ++i) {
                            if (CFG.core.getCiv(civ.civNeighbors.civs.get((int)i).civID).getPuppetOfCiv() != nCivID || CFG.core.isAlly(nCivID, civ.civNeighbors.civs.get((int)i).civID)) continue;
                            possibleCivs.add(civ.civNeighbors.civs.get((int)i).civID);
                            conquerVassal = true;
                        }
                    }
                    ** if (conquerVassal || civ.civNeighbors.civsSize <= 0) goto lbl85
                    for (i = 0; i < civ.civNeighbors.civsSize; ++i) {
                        if (CFG.core.getCiv(civ.civNeighbors.civs.get((int)i).civID).getPuppetOfCiv() == nCivID) {
                            if (CFG.oR.nextInt(100) < GameValues.gvAiDeclareWar.AI_PREPARE_FOR_WAR_AGAINST_OWN_VASSAL_CHANCE && !CFG.core.isAlly(nCivID, civ.civNeighbors.civs.get((int)i).civID)) {
                                possibleCivs.add(civ.civNeighbors.civs.get((int)i).civID);
                                continue;
                            }
                            for (j = 0; j < CFG.core.getCiv((int)civ.civNeighbors.civs.get((int)i).civID).civNeighbors.civsSize; ++j) {
                                if (CFG.core.getCiv((int)civ.civNeighbors.civs.get((int)i).civID).civNeighbors.civs.get((int)j).civID == nCivID || CFG.core.isAlly(nCivID, CFG.core.getCiv((int)civ.civNeighbors.civs.get((int)i).civID).civNeighbors.civs.get((int)j).civID) || possibleCivs.contains(CFG.core.getCiv((int)civ.civNeighbors.civs.get((int)i).civID).civNeighbors.civs.get((int)j).civID)) continue;
                                possibleCivs.add(CFG.core.getCiv((int)civ.civNeighbors.civs.get((int)i).civID).civNeighbors.civs.get((int)j).civID);
                            }
                            continue;
                        }
                        if (CFG.core.isAlly(nCivID, civ.civNeighbors.civs.get((int)i).civID) || possibleCivs.contains(civ.civNeighbors.civs.get((int)i).civID)) continue;
                        possibleCivs.add(civ.civNeighbors.civs.get((int)i).civID);
lbl-1000:
                        // 2 sources

                        {
                            continue;
                        }
                    }
                }
lbl85:
                // 4 sources

                if (possibleCivs.isEmpty() && CFG.oR.nextInt(100) < GameValues.gvAiDeclareWar.AI_PREPARE_FOR_WAR_CLOSEST_CIV_CHANCE) {
                    distance = new ArrayList<CivDistance>();
                    for (i = 1; i < nCivID; ++i) {
                        if (CFG.core.getCiv(i).getNumOfProvs() <= 0) continue;
                        distance.add(new CivDistance(i, Distance.getDistanceFromAToB_PercOfMax(civ.getCapitalProvID(), CFG.core.getCiv(i).getCapitalProvID())));
                    }
                    for (i = nCivID + 1; i < CFG.core.getCivsSize(); ++i) {
                        if (CFG.core.getCiv(i).getNumOfProvs() <= 0) continue;
                        distance.add(new CivDistance(i, Distance.getDistanceFromAToB_PercOfMax(civ.getCapitalProvID(), CFG.core.getCiv(i).getCapitalProvID())));
                    }
                    for (i = 0; !distance.isEmpty() && i < GameValues.gvAiDeclareWar.AI_PREPARE_FOR_WAR_CLOSEST_CIV_CIVS_LIMIT; ++i) {
                        bestID = 0;
                        jSize = distance.size();
                        for (j = 1; j < jSize; ++j) {
                            if (!(((CivDistance)distance.get((int)bestID)).distance > ((CivDistance)distance.get((int)j)).distance)) continue;
                            bestID = j;
                        }
                        if (!CFG.core.isAlly(nCivID, ((CivDistance)distance.get((int)bestID)).civID)) {
                            possibleCivs.add(((CivDistance)distance.get((int)bestID)).civID);
                        } else {
                            --i;
                        }
                        distance.remove(bestID);
                    }
                }
                if (!possibleCivs.isEmpty()) {
                    for (a = 0; a < GameValues.gvAiDeclareWar.AI_PREPARE_FOR_WAR_CHECK_LIMIT && !possibleCivs.isEmpty() && !CFG.core.getCiv(nCivID).isAtWarC(); ++a) {
                        bestID = 0;
                        rand = CFG.oR.nextInt(GameValues.gvAiDeclareWar.AI_PREPARE_FOR_WAR_CHOOSE_WEAKEST_RANDOM_NUMBER);
                        if (rand < GameValues.gvAiDeclareWar.AI_PREPARE_FOR_WAR_CHOOSE_WEAKEST_CLOSEST_CIV_ALL_PROVINCES_CHANCE) {
                            distance = new ArrayList<Float>();
                            capitalProvinceID = CFG.core.getCiv(nCivID).getCapitalProvID();
                            for (c = 0; c < possibleCivs.size(); ++c) {
                                provincesDistance = 0.0f;
                                civDistance = CFG.core.getCiv((Integer)possibleCivs.get(c));
                                for (j = 0; j < civDistance.getNumOfProvs(); ++j) {
                                    provincesDistance += Distance.getDistanceFromAToB_PercOfMax(capitalProvinceID, civDistance.getProvID(j));
                                }
                                distance.add(Float.valueOf(provincesDistance / (float)civDistance.getNumOfProvs()));
                            }
                            for (c = 1; c < possibleCivs.size(); ++c) {
                                if (!(((Float)distance.get(bestID)).floatValue() > ((Float)distance.get(c)).floatValue())) continue;
                                bestID = c;
                            }
                        } else if (rand < GameValues.gvAiDeclareWar.AI_PREPARE_FOR_WAR_CHOOSE_WEAKEST_CLOSEST_CIV_CAPITAL_CHANCE) {
                            distance = Distance.getDistanceFromAToB_PercOfMax(civ.getCapitalProvID(), CFG.core.getCiv((Integer)possibleCivs.get(bestID)).getCapitalProvID());
                            for (c = 1; c < possibleCivs.size(); ++c) {
                                if (!(distance > Distance.getDistanceFromAToB_PercOfMax(civ.getCapitalProvID(), CFG.core.getCiv((Integer)possibleCivs.get(c)).getCapitalProvID()))) continue;
                                distance = Distance.getDistanceFromAToB_PercOfMax(civ.getCapitalProvID(), CFG.core.getCiv((Integer)possibleCivs.get(c)).getCapitalProvID());
                                bestID = c;
                            }
                        } else if (rand < GameValues.gvAiDeclareWar.AI_PREPARE_FOR_WAR_CHOOSE_WEAKEST_CIV_PROVINCES_CHANCE) {
                            for (c = 1; c < possibleCivs.size(); ++c) {
                                if (CFG.core.getCiv((Integer)possibleCivs.get(bestID)).getNumOfProvs() + CFG.core.getCiv(CFG.core.getCiv((Integer)possibleCivs.get(bestID)).getPuppetOfCiv()).getNumOfProvs() <= CFG.core.getCiv(CFG.core.getCiv((Integer)possibleCivs.get(c)).getPuppetOfCiv()).getNumOfProvs() + CFG.core.getCiv((Integer)possibleCivs.get(c)).getNumOfProvs()) continue;
                                bestID = c;
                            }
                        } else if (rand < GameValues.gvAiDeclareWar.AI_PREPARE_FOR_WAR_CHOOSE_WEAKEST_CIV_ARMY_MAX_CHANCE) {
                            for (c = 1; c < possibleCivs.size(); ++c) {
                                if (CFG.core.getCiv((Integer)possibleCivs.get(bestID)).getNumberOfUnits() + CFG.core.getCiv(CFG.core.getCiv((Integer)possibleCivs.get(bestID)).getPuppetOfCiv()).getNumberOfUnits() <= CFG.core.getCiv(CFG.core.getCiv((Integer)possibleCivs.get(c)).getPuppetOfCiv()).getNumberOfUnits() + CFG.core.getCiv((Integer)possibleCivs.get(c)).getNumberOfUnits()) continue;
                                bestID = c;
                            }
                        } else {
                            bestID = CFG.oR.nextInt(possibleCivs.size());
                        }
                        if (CFG.core.getCiv(onCivID = ((Integer)possibleCivs.get(bestID)).intValue()).getIsPlayer() != false && CFG.core.getCiv(nCivID).getRelationD(onCivID) > GameValues.gvAiDeclareWar.AI_MAX_RELATION_TO_DECLARE_WAR_WITH_PLAYER || CFG.core.getCiv(nCivID).getRelationD(onCivID) > GameValues.gvAiDeclareWar.AI_MAX_RELATION_TO_DECLARE_WAR) {
                            randNum = CFG.oR.nextInt(4);
                            for (z = 0; z < 2 + randNum; ++z) {
                                GameManager.decreaseRelation(nCivID, onCivID, 10);
                            }
                            possibleCivs.remove(bestID);
                            continue;
                        }
                        if (CFG.core.getCiv(nCivID).isFriendlyCiv(onCivID) >= 0) {
                            possibleCivs.remove(bestID);
                            continue;
                        }
                        if (CFG.core.isAlly(nCivID, onCivID)) {
                            possibleCivs.remove(bestID);
                            continue;
                        }
                        if (CFG.core.getGuarantee(nCivID, onCivID) > 0 || CFG.core.getGuarantee(onCivID, nCivID) > 0) {
                            possibleCivs.remove(bestID);
                            continue;
                        }
                        if (CFG.core.getCivNonAggressionPact(nCivID, onCivID) > 0) {
                            possibleCivs.remove(bestID);
                            continue;
                        }
                        if (CFG.core.getCivTruce(nCivID, onCivID) > 0) {
                            possibleCivs.remove(bestID);
                            continue;
                        }
                        if (CFG.core.getCivNonAggressionPact(nCivID, onCivID) > 0) {
                            randNum = CFG.oR.nextInt(2);
                            for (z = 0; z < 1 + randNum; ++z) {
                                GameManager.decreaseRelation(nCivID, onCivID, 10);
                            }
                            possibleCivs.remove(bestID);
                            continue;
                        }
                        if (!AIPlaystyle.checkArmy_ForWar(nCivID, onCivID)) {
                            possibleCivs.remove(bestID);
                            continue;
                        }
                        ownBudget = this.diplomacyActions_DeclareWar_Budgets(nCivID, false);
                        if ((float)ownBudget > (float)(theirBudget = this.diplomacyActions_DeclareWar_Budgets(onCivID, true)) * GameValues.gvAiDeclareWar.WAR_PREPARATION_MIN_BUDGET_RATIO) {
                            turns = GameValues.gvAiDeclareWar.WAR_PREPARATION_MIN_TURNS + CFG.oR.nextInt(GameValues.gvAiDeclareWar.WAR_PREPARATION_RANDOM_TURNS);
                            CFG.core.getCiv((int)nCivID).civGD.civPlans.addNewWarPreps(nCivID, nCivID, onCivID, turns);
                            toCall = GameManager.callToArmsListOfCivs(nCivID, onCivID);
                            for (z = 0; z < toCall.size(); ++z) {
                                GameManager.sendPrepareForWar(toCall.get(z), nCivID, onCivID, turns, nCivID);
                            }
                        } else {
                            possibleToJoin = new ArrayList<Integer>();
                            for (z = 0; z < CFG.core.getCiv(onCivID).getHatedCivs_BySize(); ++z) {
                                if (CFG.core.getCiv(nCivID).isHatedCiv(CFG.core.getCiv(onCivID).getHatedCiv_By(z)) || CFG.core.getCiv(CFG.core.getCiv(onCivID).getHatedCiv_By(z)).getNumOfProvs() <= 0) continue;
                                possibleToJoin.add(CFG.core.getCiv(onCivID).getHatedCiv_By(z));
                            }
                            for (z = 0; z < possibleToJoin.size(); ++z) {
                                ownBudget += this.diplomacyActions_DeclareWar_Budgets((Integer)possibleToJoin.get(z), false);
                            }
                            if ((float)ownBudget > (float)theirBudget * GameValues.gvAiDeclareWar.TRADE_RQ_COALITION_MIN_BUDGET_RATIO) {
                                for (z = 0; z < possibleToJoin.size(); ++z) {
                                    if (Objects.equals(onCivID, possibleToJoin.get(z))) continue;
                                    tradeRequest = new TradeRequest_GameData();
                                    tradeRequest.iCivLEFT = nCivID;
                                    tradeRequest.iCivRIGHT = (Integer)possibleToJoin.get(z);
                                    tradeRequest.listRight.iFormCoalitionAgainst = onCivID;
                                    tradeRequest.listLEFT.iGold = GameValues.gvAiDeclareWar.TRADE_RQ_COALITION_BRIBE_GOLD_MIN + CFG.oR.nextInt(GameValues.gvAiDeclareWar.TRADE_RQ_COALITION_BRIBE_GOLD_RANDOM) + (int)Math.max(0.0f, Math.min((float)CFG.core.getCiv(nCivID).getGold(), (float)CFG.core.getCiv((Integer)possibleToJoin.get(z)).getNumberOfUnits() * (GameValues.gvAiDeclareWar.TRADE_RQ_COALITION_BRIBE_GOLD_PERC_OF_ARMY_MIN + (float)CFG.oR.nextInt(GameValues.gvAiDeclareWar.TRADE_RQ_COALITION_BRIBE_GOLD_PERC_OF_ARMY_RANDOM_100) / 100.0f)));
                                    GameManager.sendTradeRequest((Integer)possibleToJoin.get(z), nCivID, tradeRequest);
                                }
                                turns = GameValues.gvAiDeclareWar.WAR_PREPARATION_MIN_TURNS + CFG.oR.nextInt(GameValues.gvAiDeclareWar.WAR_PREPARATION_RANDOM_TURNS);
                                CFG.core.getCiv((int)nCivID).civGD.civPlans.addNewWarPreps(nCivID, nCivID, onCivID, turns);
                                toCall = GameManager.callToArmsListOfCivs(nCivID, onCivID);
                                for (z = 0; z < toCall.size(); ++z) {
                                    GameManager.sendPrepareForWar(toCall.get(z), nCivID, onCivID, turns, nCivID);
                                }
                            } else {
                                GameManager.sendNonAggressionProposal(onCivID, nCivID, GameValues.gvDipNonAggression.DIPLOMACY_MAX_NUMBER_OF_TURNS_NON_AGGRESSION_PACT);
                            }
                        }
                        if (civ.civGD.civPlans.iWarPrepsSize >= GameValues.gvAiDeclareWar.AI_PREPARE_FOR_WAR_CIVS_LIMIT) break;
                    }
                }
                CFG.core.getCiv((int)nCivID).civGD.declareWarCheckNextTurnID = GameCalendar.TURNID + GameValues.gvAiDeclareWar.BASE_WAR_CHECK_DELAY_TURNS + (int)(((float)CFG.oR.nextInt(GameValues.gvAiDeclareWar.WAR_CHECK_DELAY_RANDOM_TURNS_AFTER_PREPARATION) + (float)CFG.oR.nextInt(CFG.oAI.NUM_OF_CIVS_IN_THE_GAME + 1) / GameValues.gvAiDeclareWar.WAR_CHECK_CIV_COUNT_RANDOM_DIVISOR_2) / Math.max(0.01f, GameCalendar.AI_AGGRESSIVENESS));
                possibleCivs.clear();
            }
        } else {
            CFG.core.getCiv((int)nCivID).civGD.declareWarCheckNextTurnID = GameCalendar.TURNID + GameValues.gvAiDeclareWar.BASE_WAR_CHECK_DELAY_TURNS + (int)(((float)CFG.oR.nextInt(GameValues.gvAiDeclareWar.WAR_CHECK_DELAY_RANDOM_TURNS) + (float)CFG.oR.nextInt(CFG.oAI.NUM_OF_CIVS_IN_THE_GAME + 1) / GameValues.gvAiDeclareWar.WAR_CHECK_CIV_COUNT_RANDOM_DIVISOR) / Math.max(0.01f, GameCalendar.AI_AGGRESSIVENESS));
            return;
        }
    }

    public static final boolean checkArmy_ForWar(int civID, int onCivID) {
        int i;
        List<Integer> alliesCiv = AIPlaystyle.declareWar_AlliesAttacker(civID, onCivID);
        List<Integer> alliesDefenders = AIPlaystyle.declareWar_AlliesDefender(onCivID, civID);
        int armyA = 0;
        int armyB = 0;
        try {
            for (i = alliesCiv.size() - 1; i >= 0; --i) {
                armyA += CFG.core.getCiv(alliesCiv.get(i)).getNumberOfUnits();
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            for (i = alliesDefenders.size() - 1; i >= 0; --i) {
                armyB += CFG.core.getCiv(alliesDefenders.get(i)).getNumberOfUnits();
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
        armyA = (int)((float)armyA * GameValues.gvAiDeclareWar.AI_DECLARE_WAR_ALLIES_ARMY_MODIFIER);
        armyB = (int)((float)armyB * GameValues.gvAiDeclareWar.AI_DECLARE_WAR_ALLIES_ARMY_MODIFIER_DEFENDERS);
        alliesCiv.clear();
        alliesDefenders.clear();
        return (armyA += CFG.core.getCiv(civID).getNumberOfUnits()) >= (armyB += (int)Math.max(0.0f, (float)CFG.core.getCiv(onCivID).getNumberOfUnits() * GameValues.gvAiDeclareWar.AI_DECLARE_WAR_DEFENDER_ARMY_MODIFIER));
    }

    public static final List<Integer> declareWar_AlliesAttacker(int civAllies, int civEnemy) {
        int i;
        ArrayList<Integer> out = new ArrayList<Integer>();
        if (CFG.core.getCiv(civAllies).getAlliance() > 0) {
            for (i = 0; i < CFG.core.getAlliance(CFG.core.getCiv(civAllies).getAlliance()).getCivilizationsSize(); ++i) {
                if (civAllies == CFG.core.getAlliance(CFG.core.getCiv(civAllies).getAlliance()).getCivilization(i) || civAllies == CFG.core.getCiv(CFG.core.getAlliance(CFG.core.getCiv(civAllies).getAlliance()).getCivilization(i)).getPuppetOfCiv() || CFG.core.getCiv(civAllies).getPuppetOfCiv() == CFG.core.getAlliance(CFG.core.getCiv(civAllies).getAlliance()).getCivilization(i) || out.contains(CFG.core.getAlliance(CFG.core.getCiv(civAllies).getAlliance()).getCivilization(i))) continue;
                out.add(CFG.core.getAlliance(CFG.core.getCiv(civAllies).getAlliance()).getCivilization(i));
            }
        }
        for (i = 0; i < CFG.core.getCiv((int)civAllies).civGD.iVassalsSize; ++i) {
            if (CFG.core.getCiv((int)civAllies).civGD.vassals.get((int)i).iCivID == civAllies || CFG.core.getCiv((int)civAllies).civGD.vassals.get((int)i).iCivID == civEnemy || out.contains(CFG.core.getCiv((int)civAllies).civGD.vassals.get((int)i).iCivID)) continue;
            out.add(CFG.core.getCiv((int)civAllies).civGD.vassals.get((int)i).iCivID);
        }
        for (i = out.size() - 1; i >= 0; --i) {
            if ((Integer)out.get(i) < 1 || CFG.core.getCiv((Integer)out.get(i)).getNumOfProvs() <= 0) {
                out.remove(i);
                continue;
            }
            if ((Integer)out.get(i) != civAllies && (Integer)out.get(i) != civEnemy) continue;
            out.remove(i);
        }
        return out;
    }

    public static final List<Integer> declareWar_AlliesDefender(int findCivAllies, int enemyCiv) {
        int i;
        ArrayList<Integer> out = new ArrayList<Integer>();
        Civilization civAllies = CFG.core.getCiv(findCivAllies);
        if (civAllies.getAlliance() > 0) {
            for (i = 0; i < CFG.core.getAlliance(civAllies.getAlliance()).getCivilizationsSize(); ++i) {
                if (findCivAllies == CFG.core.getAlliance(civAllies.getAlliance()).getCivilization(i) || out.contains(CFG.core.getAlliance(civAllies.getAlliance()).getCivilization(i))) continue;
                out.add(CFG.core.getAlliance(civAllies.getAlliance()).getCivilization(i));
            }
        }
        for (i = 1; i < findCivAllies; ++i) {
            if (CFG.core.getDefensivePact(findCivAllies, i) > 0) {
                if (CFG.core.getCiv(i).getNumOfProvs() <= 0 || out.contains(i)) continue;
                out.add(i);
                continue;
            }
            if (CFG.core.getGuarantee(i, findCivAllies) <= 0 || CFG.core.getCiv(i).getNumOfProvs() <= 0 || out.contains(i)) continue;
            out.add(i);
        }
        for (i = findCivAllies; i < CFG.core.getCivsSize(); ++i) {
            if (CFG.core.getDefensivePact(findCivAllies, i) > 0) {
                if (CFG.core.getCiv(i).getNumOfProvs() <= 0 || out.contains(i)) continue;
                out.add(i);
                continue;
            }
            if (CFG.core.getGuarantee(findCivAllies, i) <= 0 || CFG.core.getCiv(i).getNumOfProvs() <= 0 || out.contains(i)) continue;
            out.add(i);
        }
        for (i = 0; i < civAllies.civGD.iVassalsSize; ++i) {
            if (civAllies.civGD.vassals.get((int)i).iCivID == findCivAllies || civAllies.civGD.vassals.get((int)i).iCivID == enemyCiv || out.contains(civAllies.civGD.vassals.get((int)i).iCivID)) continue;
            out.add(civAllies.civGD.vassals.get((int)i).iCivID);
        }
        if (civAllies.getPuppetOfCiv() != findCivAllies && CFG.core.getCiv(enemyCiv).getPuppetOfCiv() != civAllies.getPuppetOfCiv() && !out.contains(civAllies.getPuppetOfCiv())) {
            out.add(civAllies.getPuppetOfCiv());
        }
        for (i = out.size() - 1; i >= 0; --i) {
            if ((Integer)out.get(i) < 1 || CFG.core.getCiv((Integer)out.get(i)).getNumOfProvs() <= 0) {
                out.remove(i);
                continue;
            }
            if ((Integer)out.get(i) != enemyCiv && (Integer)out.get(i) != findCivAllies) continue;
            out.remove(i);
        }
        return out;
    }

    public static final List<Integer> declareWar_AlliesDefender2(int findCivAllies) {
        int i;
        ArrayList<Integer> out = new ArrayList<Integer>();
        Civilization civAllies = CFG.core.getCiv(findCivAllies);
        if (civAllies.getAlliance() > 0) {
            for (i = 0; i < CFG.core.getAlliance(civAllies.getAlliance()).getCivilizationsSize(); ++i) {
                if (findCivAllies == CFG.core.getAlliance(civAllies.getAlliance()).getCivilization(i) || out.contains(CFG.core.getAlliance(civAllies.getAlliance()).getCivilization(i))) continue;
                out.add(CFG.core.getAlliance(civAllies.getAlliance()).getCivilization(i));
            }
        }
        for (i = 1; i < findCivAllies; ++i) {
            if (CFG.core.getDefensivePact(findCivAllies, i) > 0) {
                if (CFG.core.getCiv(i).getNumOfProvs() <= 0 || out.contains(i)) continue;
                out.add(i);
                continue;
            }
            if (CFG.core.getGuarantee(i, findCivAllies) <= 0 || CFG.core.getCiv(i).getNumOfProvs() <= 0 || out.contains(i)) continue;
            out.add(i);
        }
        for (i = findCivAllies; i < CFG.core.getCivsSize(); ++i) {
            if (CFG.core.getDefensivePact(findCivAllies, i) > 0) {
                if (CFG.core.getCiv(i).getNumOfProvs() <= 0 || out.contains(i)) continue;
                out.add(i);
                continue;
            }
            if (CFG.core.getGuarantee(findCivAllies, i) <= 0 || CFG.core.getCiv(i).getNumOfProvs() <= 0 || out.contains(i)) continue;
            out.add(i);
        }
        for (i = 0; i < civAllies.civGD.iVassalsSize; ++i) {
            if (civAllies.civGD.vassals.get((int)i).iCivID == findCivAllies || out.contains(civAllies.civGD.vassals.get((int)i).iCivID)) continue;
            out.add(civAllies.civGD.vassals.get((int)i).iCivID);
        }
        for (i = out.size() - 1; i >= 0; --i) {
            if ((Integer)out.get(i) < 1 || CFG.core.getCiv((Integer)out.get(i)).getNumOfProvs() <= 0) {
                out.remove(i);
                continue;
            }
            if ((Integer)out.get(i) != findCivAllies) continue;
            out.remove(i);
        }
        return out;
    }

    public final void diplomacyActions_DeclareWar_Old(int nCivID) {
        if (CFG.core.getCiv((int)nCivID).civGD.declareWarCheckNextTurnID <= GameCalendar.TURNID && GameCalendar.AI_AGGRESSIVENESS > 0.0f) {
            int pID;
            if (CFG.core.getCiv(CFG.core.getCiv(nCivID).getPuppetOfCiv()).getIsPlayer() && CFG.core.getCiv(CFG.core.getCiv(nCivID).getPuppetOfCiv()).getNumOfProvs() > 0 && (pID = CFG.core.getPlayerIDbyCivID(CFG.core.getCiv(nCivID).getPuppetOfCiv())) >= 0 && !CFG.core.getPlayer((int)pID).playerGD.VASSALS_CAN_DECLARE_WARS) {
                return;
            }
            if (CFG.core.getCiv(nCivID).getPuppetOfCiv() != nCivID && CFG.core.getCiv(CFG.core.getCiv(nCivID).getPuppetOfCiv()).getNumOfProvs() > 0 && !CFG.AI_VASSALS_CAN_DECLARE_WARS) {
                CFG.core.getCiv((int)nCivID).civGD.declareWarCheckNextTurnID = GameCalendar.TURNID + GameValues.gvAiDeclareWar.BASE_WAR_CHECK_DELAY_TURNS + (int)(((float)(CFG.core.getCiv((int)nCivID).provincesWithLowHappiness.size() * 2 + CFG.oR.nextInt(GameValues.gvAiDeclareWar.WAR_CHECK_DELAY_RANDOM_TURNS)) + (float)CFG.oR.nextInt(CFG.oAI.NUM_OF_CIVS_IN_THE_GAME + 1) / GameValues.gvAiDeclareWar.WAR_CHECK_CIV_COUNT_RANDOM_DIVISOR_2) / Math.max(0.01f, GameCalendar.AI_AGGRESSIVENESS));
                return;
            }
            if (CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).CAN_BECOME_CIVILIZED >= 0) {
                CFG.core.getCiv((int)nCivID).civGD.declareWarCheckNextTurnID = GameCalendar.TURNID + (int)(((float)(GameValues.gvAiDeclareWar.BASE_WAR_CHECK_DELAY_TURNS + CFG.oR.nextInt(GameValues.gvAiDeclareWar.WAR_CHECK_DELAY_RANDOM_TURNS_TRIBAL)) + (float)CFG.oR.nextInt(CFG.oAI.NUM_OF_CIVS_IN_THE_GAME + 1) / GameValues.gvAiDeclareWar.WAR_CHECK_CIV_COUNT_RANDOM_DIVISOR) / Math.max(0.01f, GameCalendar.AI_AGGRESSIVENESS));
            } else {
                float f = CFG.core.getCiv(nCivID).getPuppetOfCiv() == nCivID ? CFG.oAI.getAIStyle((int)CFG.core.getCiv((int)nCivID).getAIStyleID()).PERSONALITY_MIN_AGGRESSION_DEFAULT + (float)CFG.oR.nextInt(CFG.oAI.getAIStyle((int)CFG.core.getCiv((int)nCivID).getAIStyleID()).PERSONALITY_MIN_AGGRESSION_RANDOM_100) / 100.0f : (CFG.oAI.getAIStyle((int)CFG.core.getCiv((int)nCivID).getAIStyleID()).PERSONALITY_MIN_AGGRESSION_DEFAULT + (float)CFG.oR.nextInt(CFG.oAI.getAIStyle((int)CFG.core.getCiv((int)nCivID).getAIStyleID()).PERSONALITY_MIN_AGGRESSION_RANDOM_100) / 100.0f) / 8.0f;
                if (f * GameCalendar.AI_AGGRESSIVENESS >= (float)CFG.oR.nextInt(10000) / 100.0f) {
                    int z;
                    int o;
                    boolean wasAdded;
                    int i;
                    ArrayList<Integer> possibleCivs = new ArrayList<Integer>();
                    if (!CFG.core.getCiv((int)nCivID).provincesWithLowStability.isEmpty()) {
                        CFG.core.getCiv((int)nCivID).civGD.declareWarCheckNextTurnID = GameCalendar.TURNID + GameValues.gvAiDeclareWar.BASE_WAR_CHECK_DELAY_TURNS + (int)(((float)(CFG.core.getCiv((int)nCivID).provincesWithLowStability.size() * 2 + CFG.oR.nextInt(GameValues.gvAiDeclareWar.WAR_CHECK_DELAY_RANDOM_TURNS)) + (float)CFG.oR.nextInt(CFG.oAI.NUM_OF_CIVS_IN_THE_GAME + 1) / GameValues.gvAiDeclareWar.WAR_CHECK_CIV_COUNT_RANDOM_DIVISOR_2) / Math.max(0.01f, GameCalendar.AI_AGGRESSIVENESS));
                        return;
                    }
                    if (!CFG.core.getCiv((int)nCivID).provincesWithLowHappiness.isEmpty()) {
                        CFG.core.getCiv((int)nCivID).civGD.declareWarCheckNextTurnID = GameCalendar.TURNID + GameValues.gvAiDeclareWar.BASE_WAR_CHECK_DELAY_TURNS + (int)(((float)(CFG.core.getCiv((int)nCivID).provincesWithLowHappiness.size() * 2 + CFG.oR.nextInt(GameValues.gvAiDeclareWar.WAR_CHECK_DELAY_RANDOM_TURNS)) + (float)CFG.oR.nextInt(CFG.oAI.NUM_OF_CIVS_IN_THE_GAME + 1) / GameValues.gvAiDeclareWar.WAR_CHECK_CIV_COUNT_RANDOM_DIVISOR_2) / Math.max(0.01f, GameCalendar.AI_AGGRESSIVENESS));
                        return;
                    }
                    for (i = CFG.core.getCiv((int)nCivID).lFrontLines.size() - 1; i >= 0; --i) {
                        wasAdded = false;
                        for (o = possibleCivs.size() - 1; o >= 0; --o) {
                            if ((Integer)possibleCivs.get(o) != CFG.core.getCiv((int)nCivID).lFrontLines.get((int)i).iWithCivID) continue;
                            wasAdded = true;
                        }
                        if (wasAdded) continue;
                        possibleCivs.add(CFG.core.getCiv((int)nCivID).lFrontLines.get((int)i).iWithCivID);
                    }
                    for (i = 0; i < CFG.core.getCiv(nCivID).getSeaAccess_Provinces_Size(); ++i) {
                        for (int j = 0; j < CFG.core.getProv(CFG.core.getCiv(nCivID).getSeaAccessProvinces().get(i)).getNeighSeaProvincesSize(); ++j) {
                            for (int k = 0; k < CFG.core.getProv(CFG.core.getProv(CFG.core.getCiv(nCivID).getSeaAccessProvinces().get(i)).getNeighSeaProvinces(j)).getNeighProvincesSize(); ++k) {
                                if (!CFG.core.getProv(CFG.core.getProv(CFG.core.getProv(CFG.core.getCiv(nCivID).getSeaAccessProvinces().get(i)).getNeighSeaProvinces(j)).getNeighProvinces(k)).getSeaProv()) {
                                    if (CFG.core.getProv(CFG.core.getProv(CFG.core.getProv(CFG.core.getCiv(nCivID).getSeaAccessProvinces().get(i)).getNeighSeaProvinces(j)).getNeighProvinces(k)).getCivId() <= 0) continue;
                                    boolean wasAdded2 = false;
                                    for (int o2 = possibleCivs.size() - 1; o2 >= 0; --o2) {
                                        if (((Integer)possibleCivs.get(o2)).intValue() != CFG.core.getProv(CFG.core.getProv(CFG.core.getProv(CFG.core.getCiv(nCivID).getSeaAccessProvinces().get(i)).getNeighSeaProvinces(j)).getNeighProvinces(k)).getCivId()) continue;
                                        wasAdded2 = true;
                                    }
                                    if (wasAdded2) continue;
                                    possibleCivs.add(CFG.core.getProv(CFG.core.getProv(CFG.core.getProv(CFG.core.getCiv(nCivID).getSeaAccessProvinces().get(i)).getNeighSeaProvinces(j)).getNeighProvinces(k)).getCivId());
                                    continue;
                                }
                                for (int z2 = 0; z2 < CFG.core.getProv(CFG.core.getProv(CFG.core.getProv(CFG.core.getCiv(nCivID).getSeaAccessProvinces().get(i)).getNeighSeaProvinces(j)).getNeighProvinces(k)).getNeighProvincesSize(); ++z2) {
                                    if (CFG.core.getProv(CFG.core.getProv(CFG.core.getProv(CFG.core.getProv(CFG.core.getCiv(nCivID).getSeaAccessProvinces().get(i)).getNeighSeaProvinces(j)).getNeighProvinces(k)).getNeighProvinces(z2)).getCivId() <= 0) continue;
                                    boolean wasAdded3 = false;
                                    for (int o3 = possibleCivs.size() - 1; o3 >= 0; --o3) {
                                        if (((Integer)possibleCivs.get(o3)).intValue() != CFG.core.getProv(CFG.core.getProv(CFG.core.getProv(CFG.core.getProv(CFG.core.getCiv(nCivID).getSeaAccessProvinces().get(i)).getNeighSeaProvinces(j)).getNeighProvinces(k)).getNeighProvinces(z2)).getCivId()) continue;
                                        wasAdded3 = true;
                                    }
                                    if (wasAdded3) continue;
                                    possibleCivs.add(CFG.core.getProv(CFG.core.getProv(CFG.core.getProv(CFG.core.getProv(CFG.core.getCiv(nCivID).getSeaAccessProvinces().get(i)).getNeighSeaProvinces(j)).getNeighProvinces(k)).getNeighProvinces(z2)).getCivId());
                                }
                            }
                        }
                    }
                    if ((possibleCivs.isEmpty() || CFG.oR.nextInt(100) < GameValues.gvAiDeclareWar.NAVAL_EXPANSION_RANDOM_CHANCE_100) && CFG.core.getCiv(nCivID).getSeaAccess_PortProvinces_Size() > 0 && CFG.core.getCiv(nCivID).getNumOfProvs() > GameValues.gvAiDeclareWar.MIN_PROVINCES_FOR_NAVAL_ACTIONS) {
                        for (i = CFG.core.getCiv((int)nCivID).civsInRange.size() - 1; i >= 0; --i) {
                            possibleCivs.add(CFG.core.getCiv((int)nCivID).civsInRange.get((int)i).iCivID);
                        }
                    }
                    if (possibleCivs.isEmpty()) {
                        for (i = 0; i < CFG.core.getCiv((int)nCivID).civGD.civRivalsSize; ++i) {
                            wasAdded = false;
                            for (o = possibleCivs.size() - 1; o >= 0; --o) {
                                if ((Integer)possibleCivs.get(o) != CFG.core.getCiv((int)nCivID).civGD.civRivals.get((int)i).iCivID) continue;
                                wasAdded = true;
                            }
                            if (wasAdded) continue;
                            possibleCivs.add(CFG.core.getCiv((int)nCivID).civGD.civRivals.get((int)i).iCivID);
                        }
                    }
                    for (i = possibleCivs.size() - 1; i >= 0; --i) {
                        if (CFG.core.getCiv((Integer)possibleCivs.get(i)).getPuppetOfCiv() != ((Integer)possibleCivs.get(i)).intValue()) {
                            possibleCivs.remove(i);
                            continue;
                        }
                        if (CFG.core.getCiv(nCivID).isFriendlyCiv((Integer)possibleCivs.get(i)) >= 0) {
                            possibleCivs.remove(i);
                            continue;
                        }
                        if (CFG.core.isAlly(nCivID, (Integer)possibleCivs.get(i))) {
                            possibleCivs.remove(i);
                            continue;
                        }
                        if (CFG.core.getGuarantee(nCivID, (Integer)possibleCivs.get(i)) > 0 || CFG.core.getGuarantee((Integer)possibleCivs.get(i), nCivID) > 0) {
                            possibleCivs.remove(i);
                            continue;
                        }
                        if (CFG.core.getCivNonAggressionPact(nCivID, (Integer)possibleCivs.get(i)) > 0) {
                            possibleCivs.remove(i);
                            continue;
                        }
                        if (CFG.core.getCivTruce(nCivID, (Integer)possibleCivs.get(i)) > 0) {
                            possibleCivs.remove(i);
                            continue;
                        }
                        float f2 = CFG.core.getCivRelationOfCivB(nCivID, (Integer)possibleCivs.get(i));
                        float f3 = CFG.oAI.NUM_OF_CIVS_IN_THE_GAME < 10 ? 10.0f : Math.max((float)GameValues.gvAiDeclareWar.DECLARE_WAR_TARGET_RELATION, (float)GameValues.gvAiDeclareWar.DECLARE_WAR_TARGET_RELATION / Math.max(0.01f, GameCalendar.AI_AGGRESSIVENESS));
                        if (f2 > f3) {
                            possibleCivs.remove(i);
                            continue;
                        }
                        if (!(CFG.core.getCiv((Integer)possibleCivs.get(i)).getIsPlayer() && CFG.core.getCiv(nCivID).getRelationD((Integer)possibleCivs.get(i)) > GameValues.gvAiDeclareWar.AI_MAX_RELATION_TO_DECLARE_WAR_WITH_PLAYER) && !(CFG.core.getCiv(nCivID).getRelationD((Integer)possibleCivs.get(i)) > GameValues.gvAiDeclareWar.AI_MAX_RELATION_TO_DECLARE_WAR)) continue;
                        int randNum = CFG.oR.nextInt(4);
                        for (z = 0; z < 2 + randNum; ++z) {
                            GameManager.decreaseRelation(nCivID, (Integer)possibleCivs.get(i), 10);
                        }
                        possibleCivs.remove(i);
                    }
                    if (!possibleCivs.isEmpty()) {
                        boolean done = false;
                        if (!CFG.core.getCiv((int)nCivID).civGD.coloniesFounded.isEmpty() && CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).CAN_BECOME_CIVILIZED < 0) {
                            ArrayList<Integer> tribalPossible = new ArrayList<Integer>();
                            for (z = 0; z < possibleCivs.size(); ++z) {
                                if (CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)((Integer)possibleCivs.get((int)z)).intValue()).getIdeology()).CAN_BECOME_CIVILIZED < 0) continue;
                                tribalPossible.add((Integer)possibleCivs.get(z));
                            }
                            if (!tribalPossible.isEmpty()) {
                                CFG.core.declareWar(nCivID, (Integer)tribalPossible.get(CFG.oR.nextInt(tribalPossible.size())), false);
                                done = true;
                            }
                        }
                        if (!done) {
                            int theirBudget;
                            ArrayList<Float> lScores = new ArrayList<Float>();
                            float modifier_Relation = GameValues.gvAiDeclareWar.RELATION_MODIFIER_BASE + (float)CFG.oR.nextInt(GameValues.gvAiDeclareWar.RELATION_MODIFIER_RANDOM_1000) / 1000.0f;
                            float modifier_Budget = GameValues.gvAiDeclareWar.BUDGET_MODIFIER_BASE + (float)CFG.oR.nextInt(GameValues.gvAiDeclareWar.BUDGET_MODIFIER_RANDOM_1000) / 1000.0f;
                            float modifier_CivsSize = GameValues.gvAiDeclareWar.CIV_SIZE_MODIFIER;
                            for (int z3 = 0; z3 < possibleCivs.size(); ++z3) {
                                lScores.add(Float.valueOf(this.diplomacyActions_DeclareWar_Score(nCivID, (Integer)possibleCivs.get(z3), modifier_Budget, modifier_CivsSize, modifier_Relation)));
                            }
                            int tBest = 0;
                            for (int z4 = 1; z4 < possibleCivs.size(); ++z4) {
                                if (!(((Float)lScores.get(tBest)).floatValue() < ((Float)lScores.get(z4)).floatValue())) continue;
                                tBest = z4;
                            }
                            int ownBudget = this.diplomacyActions_DeclareWar_Budgets(nCivID, false);
                            if ((float)ownBudget > (float)(theirBudget = this.diplomacyActions_DeclareWar_Budgets((Integer)possibleCivs.get(tBest), true)) * GameValues.gvAiDeclareWar.WAR_PREPARATION_MIN_BUDGET_RATIO) {
                                int turns = GameValues.gvAiDeclareWar.WAR_PREPARATION_MIN_TURNS + CFG.oR.nextInt(GameValues.gvAiDeclareWar.WAR_PREPARATION_RANDOM_TURNS);
                                CFG.core.getCiv((int)nCivID).civGD.civPlans.addNewWarPreps(nCivID, nCivID, (Integer)possibleCivs.get(tBest), turns);
                                List<Integer> toCall = GameManager.callToArmsListOfCivs(nCivID, (Integer)possibleCivs.get(tBest));
                                for (int z5 = 0; z5 < toCall.size(); ++z5) {
                                    GameManager.sendPrepareForWar(toCall.get(z5), nCivID, (Integer)possibleCivs.get(tBest), turns, nCivID);
                                }
                            } else {
                                int z6;
                                ArrayList<Integer> possibleToJoin = new ArrayList<Integer>();
                                for (int a = 0; a < CFG.core.getCiv((Integer)possibleCivs.get(tBest)).getHatedCivs_BySize(); ++a) {
                                    if (CFG.core.getCiv(nCivID).isHatedCiv(CFG.core.getCiv((Integer)possibleCivs.get(tBest)).getHatedCiv_By(a)) || CFG.core.getCiv(CFG.core.getCiv((Integer)possibleCivs.get(tBest)).getHatedCiv_By(a)).getNumOfProvs() <= 0) continue;
                                    possibleToJoin.add(CFG.core.getCiv((Integer)possibleCivs.get(tBest)).getHatedCiv_By(a));
                                }
                                for (z6 = 0; z6 < possibleToJoin.size(); ++z6) {
                                    ownBudget += this.diplomacyActions_DeclareWar_Budgets((Integer)possibleToJoin.get(z6), false);
                                }
                                if ((float)ownBudget > (float)theirBudget * GameValues.gvAiDeclareWar.TRADE_RQ_COALITION_MIN_BUDGET_RATIO) {
                                    for (z6 = 0; z6 < possibleToJoin.size(); ++z6) {
                                        if (Objects.equals(possibleCivs.get(tBest), possibleToJoin.get(z6))) continue;
                                        TradeRequest_GameData tradeRequest = new TradeRequest_GameData();
                                        tradeRequest.iCivLEFT = nCivID;
                                        tradeRequest.iCivRIGHT = (Integer)possibleToJoin.get(z6);
                                        tradeRequest.listRight.iFormCoalitionAgainst = (Integer)possibleCivs.get(tBest);
                                        tradeRequest.listLEFT.iGold = GameValues.gvAiDeclareWar.TRADE_RQ_COALITION_BRIBE_GOLD_MIN + CFG.oR.nextInt(GameValues.gvAiDeclareWar.TRADE_RQ_COALITION_BRIBE_GOLD_RANDOM) + (int)Math.max(0.0f, Math.min((float)CFG.core.getCiv(nCivID).getGold(), (float)CFG.core.getCiv((Integer)possibleToJoin.get(z6)).getNumberOfUnits() * (GameValues.gvAiDeclareWar.TRADE_RQ_COALITION_BRIBE_GOLD_PERC_OF_ARMY_MIN + (float)CFG.oR.nextInt(GameValues.gvAiDeclareWar.TRADE_RQ_COALITION_BRIBE_GOLD_PERC_OF_ARMY_RANDOM_100) / 100.0f)));
                                        GameManager.sendTradeRequest((Integer)possibleToJoin.get(z6), nCivID, tradeRequest);
                                    }
                                    int turns = GameValues.gvAiDeclareWar.WAR_PREPARATION_MIN_TURNS + CFG.oR.nextInt(GameValues.gvAiDeclareWar.WAR_PREPARATION_RANDOM_TURNS);
                                    CFG.core.getCiv((int)nCivID).civGD.civPlans.addNewWarPreps(nCivID, nCivID, (Integer)possibleCivs.get(tBest), turns);
                                    List<Integer> toCall = GameManager.callToArmsListOfCivs(nCivID, (Integer)possibleCivs.get(tBest));
                                    for (int z7 = 0; z7 < toCall.size(); ++z7) {
                                        GameManager.sendPrepareForWar(toCall.get(z7), nCivID, (Integer)possibleCivs.get(tBest), turns, nCivID);
                                    }
                                } else {
                                    GameManager.sendNonAggressionProposal((Integer)possibleCivs.get(tBest), nCivID, GameValues.gvDipNonAggression.DIPLOMACY_MAX_NUMBER_OF_TURNS_NON_AGGRESSION_PACT);
                                }
                            }
                        }
                        CFG.core.getCiv((int)nCivID).civGD.declareWarCheckNextTurnID = GameCalendar.TURNID + (int)(((float)(GameValues.gvAiDeclareWar.BASE_WAR_CHECK_DELAY_TURNS + CFG.core.getCiv((Integer)possibleCivs.get(CFG.oR.nextInt(possibleCivs.size()))).getNumOfProvs() + CFG.oR.nextInt(GameValues.gvAiDeclareWar.WAR_CHECK_DELAY_RANDOM_TURNS_AFTER_PREPARATION)) + (float)CFG.oR.nextInt(CFG.oAI.NUM_OF_CIVS_IN_THE_GAME + 1) / GameValues.gvAiDeclareWar.WAR_CHECK_CIV_COUNT_RANDOM_DIVISOR_2) / Math.max(0.01f, GameCalendar.AI_AGGRESSIVENESS));
                    } else {
                        CFG.core.getCiv((int)nCivID).civGD.declareWarCheckNextTurnID = GameCalendar.TURNID + (int)(((float)(GameValues.gvAiDeclareWar.BASE_WAR_CHECK_DELAY_TURNS + CFG.oR.nextInt(GameValues.gvAiDeclareWar.WAR_CHECK_DELAY_RANDOM_TURNS)) + (float)CFG.oR.nextInt(CFG.oAI.NUM_OF_CIVS_IN_THE_GAME + 1) / GameValues.gvAiDeclareWar.WAR_CHECK_CIV_COUNT_RANDOM_DIVISOR_2) / Math.max(0.01f, GameCalendar.AI_AGGRESSIVENESS));
                    }
                } else {
                    CFG.core.getCiv((int)nCivID).civGD.declareWarCheckNextTurnID = GameCalendar.TURNID + (int)(((float)(GameValues.gvAiDeclareWar.BASE_WAR_CHECK_DELAY_TURNS + CFG.oR.nextInt(GameValues.gvAiDeclareWar.WAR_CHECK_DELAY_RANDOM_TURNS)) + (float)CFG.oR.nextInt(CFG.oAI.NUM_OF_CIVS_IN_THE_GAME + 1) / GameValues.gvAiDeclareWar.WAR_CHECK_CIV_COUNT_RANDOM_DIVISOR) / Math.max(0.01f, GameCalendar.AI_AGGRESSIVENESS));
                }
            }
        }
    }

    public final float diplomacyActions_DeclareWar_Score(int nCivID, int onCivID, float modifier_Budget, float modifier_CivsSize, float modifier_Relation) {
        return modifier_Budget * (1.0f - Math.min((float)CFG.core.getCiv((int)onCivID).iBudget / (float)CFG.core.getCiv((int)nCivID).iBudget, GameValues.gvAiDeclareWar.SCORE_MAX_BUDGET_RATIO)) + modifier_Relation * (1.0f + CFG.core.getCiv((int)onCivID).civGD.civAggressionLevel / GameValues.gvAiDeclareWar.SCORE_CIV_AGGRESSION_DIVISOR) * (1.0f - Math.min(CFG.core.getCivRelationOfCivB(nCivID, onCivID) + 100.0f, 200.0f) / 200.0f);
    }

    public final int diplomacyActions_DeclareWar_Budgets(int nCivID, boolean defensivePacts) {
        int i;
        int out = CFG.core.getCiv((int)nCivID).iBudget;
        if (CFG.core.getCiv(nCivID).getPuppetOfCiv() != nCivID) {
            out += CFG.core.getCiv((int)CFG.core.getCiv((int)nCivID).getPuppetOfCiv()).iBudget;
        }
        for (i = 0; i < CFG.core.getCiv((int)nCivID).civGD.iVassalsSize; ++i) {
            out += CFG.core.getCiv((int)CFG.core.getCiv((int)nCivID).civGD.vassals.get((int)i).iCivID).iBudget;
        }
        if (CFG.core.getCiv(nCivID).getAlliance() > 0) {
            for (i = 0; i < CFG.core.getAlliance(CFG.core.getCiv(nCivID).getAlliance()).getCivilizationsSize(); ++i) {
                if (nCivID == CFG.core.getAlliance(CFG.core.getCiv(nCivID).getAlliance()).getCivilization(i) || nCivID == CFG.core.getCiv(CFG.core.getAlliance(CFG.core.getCiv(nCivID).getAlliance()).getCivilization(i)).getPuppetOfCiv() || CFG.core.getCiv(nCivID).getPuppetOfCiv() == CFG.core.getAlliance(CFG.core.getCiv(nCivID).getAlliance()).getCivilization(i)) continue;
                out += CFG.core.getCiv((int)CFG.core.getAlliance((int)CFG.core.getCiv((int)nCivID).getAlliance()).getCivilization((int)i)).iBudget;
            }
        }
        try {
            if (defensivePacts) {
                for (i = 1; i < nCivID; ++i) {
                    if (CFG.core.getDefensivePact(nCivID, i) > 0) {
                        if (CFG.core.getCiv(i).getNumOfProvs() <= 0) continue;
                        out += CFG.core.getCiv((int)i).iBudget;
                        continue;
                    }
                    if (CFG.core.getGuarantee(i, nCivID) <= 0 || CFG.core.getCiv(i).getNumOfProvs() <= 0) continue;
                    out += CFG.core.getCiv((int)i).iBudget;
                }
                for (i = nCivID; i < CFG.core.getCivsSize(); ++i) {
                    if (CFG.core.getDefensivePact(nCivID, i) > 0) {
                        if (CFG.core.getCiv(i).getNumOfProvs() <= 0) continue;
                        out += CFG.core.getCiv((int)i).iBudget;
                        continue;
                    }
                    if (CFG.core.getGuarantee(nCivID, i) <= 0 || CFG.core.getCiv(i).getNumOfProvs() <= 0) continue;
                    out += CFG.core.getCiv((int)i).iBudget;
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return out;
    }

    public final void diplomacyActions_BuildCivsInRange(int nCivID) {
        if (GameCalendar.TURNID >= CFG.core.getCiv((int)nCivID).civGD.nextBuildCivsInRange_TurnID) {
            if (CFG.core.getCiv(nCivID).getCapitalProvID() >= 0) {
                CFG.core.getCiv((int)nCivID).civsInRange.clear();
                CFG.core.getCiv((int)nCivID).civsInRange = this.diplomacyActions_CivsInRange(nCivID);
                CFG.core.getCiv((int)nCivID).civGD.nextBuildCivsInRange_TurnID = !CFG.core.getCiv((int)nCivID).civsInRange.isEmpty() ? GameCalendar.TURNID + GameValues.gvAiCivsInRange.REBUILD_CIVS_IN_RANGE_AFTER_X_TURNS_EMPTY + CFG.oR.nextInt(GameValues.gvAiCivsInRange.REBUILD_CIVS_IN_RANGE_AFTER_X_TURNS_RANDOM_EMPTY) : GameCalendar.TURNID + GameValues.gvAiCivsInRange.REBUILD_CIVS_IN_RANGE_AFTER_X_TURNS + CFG.oR.nextInt(Math.max(GameValues.gvAiCivsInRange.REBUILD_CIVS_IN_RANGE_AFTER_X_TURNS_RANDOM, CFG.core.getCivsSize() / 4));
            } else {
                CFG.core.getCiv((int)nCivID).civGD.nextBuildCivsInRange_TurnID = GameCalendar.TURNID + GameValues.gvAiCivsInRange.REBUILD_CIVS_IN_RANGE_AFTER_X_TURNS_NO_CAPITAL + CFG.oR.nextInt(GameValues.gvAiCivsInRange.REBUILD_CIVS_IN_RANGE_AFTER_X_TURNS_NO_CAPITAL_RANDOM);
            }
        }
    }

    public final void diplomacyActions_Ally(int nCivID) {
        if (CFG.core.getCiv((int)nCivID).civGD.resumeAllianceCheckAtTurnID <= GameCalendar.TURNID) {
            this.diplomacyActions_Union(nCivID);
            if (CFG.core.getCiv(nCivID).getPuppetOfCiv() == nCivID && (CFG.MAX_PROVINCES_FOR_ALLIANCE_PROPOSAL == 0 || CFG.core.getCiv(nCivID).getNumOfProvs() < CFG.MAX_PROVINCES_FOR_ALLIANCE_PROPOSAL) && CFG.core.getCiv(nCivID).getAlliance() == 0 && CFG.oR.nextInt(100) < CFG.PROPOSE_ALLIANCE_CHANCE_100 && CFG.core.getCiv(nCivID).getFriendlyCivsSize() > 0) {
                ArrayList<Integer> possibleCivs = new ArrayList<Integer>();
                for (int a = 0; a < CFG.core.getCiv(nCivID).getFriendlyCivsSize(); ++a) {
                    if (CFG.core.getCiv(CFG.core.getCiv((int)nCivID).getFriendlyCiv((int)a).iCivID).getPuppetOfCiv() != CFG.core.getCiv((int)nCivID).getFriendlyCiv((int)a).iCivID || CFG.core.getCiv(CFG.core.getCiv((int)nCivID).getFriendlyCiv((int)a).iCivID).isAtWarC() || CFG.core.getCiv(CFG.core.getCiv((int)nCivID).getFriendlyCiv((int)a).iCivID).getCapitalProvID() < 0 || CFG.core.getCiv(CFG.core.getCiv((int)nCivID).getFriendlyCiv((int)a).iCivID).getAlliance() != 0) continue;
                    possibleCivs.add(CFG.core.getCiv((int)nCivID).getFriendlyCiv((int)a).iCivID);
                }
                if (!possibleCivs.isEmpty()) {
                    if (CFG.oR.nextInt(100) < GameValues.gvAiDiplomacy.ALLY_CHOOSE_RANDOM_CHANCE_100) {
                        int tRandCiv = CFG.oR.nextInt(CFG.core.getCiv(nCivID).getFriendlyCivsSize());
                        if (CFG.core.getCiv(CFG.core.getCiv((int)nCivID).getFriendlyCiv((int)tRandCiv).iCivID).getPuppetOfCiv() == CFG.core.getCiv((int)nCivID).getFriendlyCiv((int)tRandCiv).iCivID && CFG.core.getCiv(CFG.core.getCiv((int)nCivID).getFriendlyCiv((int)tRandCiv).iCivID).getAlliance() == 0) {
                            GameManager.sendAllianceProposal(CFG.core.getCiv((int)nCivID).getFriendlyCiv((int)tRandCiv).iCivID, nCivID);
                            GameManager.improveRelation(nCivID, CFG.core.getCiv((int)nCivID).getFriendlyCiv((int)tRandCiv).iCivID);
                        }
                    } else {
                        ArrayList<Float> distance = new ArrayList<Float>();
                        for (int a = 0; a < possibleCivs.size(); ++a) {
                            distance.add(Float.valueOf(Distance.getDistanceFromAToB_PercOfMax(CFG.core.getCiv(nCivID).getCapitalProvID(), CFG.core.getCiv((Integer)possibleCivs.get(a)).getCapitalProvID())));
                        }
                        int bestID = 0;
                        for (int a = 1; a < possibleCivs.size(); ++a) {
                            if (!(((Float)distance.get(bestID)).floatValue() > ((Float)distance.get(a)).floatValue())) continue;
                            bestID = a;
                        }
                        int bestCivID = (Integer)possibleCivs.get(bestID);
                        if (CFG.core.getCiv(bestCivID).getPuppetOfCiv() == bestCivID && CFG.core.getCiv(bestCivID).getAlliance() == 0) {
                            GameManager.sendAllianceProposal(bestCivID, nCivID);
                            GameManager.improveRelation(nCivID, bestCivID);
                        }
                    }
                }
            }
            CFG.core.getCiv((int)nCivID).civGD.resumeAllianceCheckAtTurnID = GameCalendar.TURNID + GameValues.gvAiAlliance.NEXT_ALLIANCE_CHECK_TURN_ID + CFG.oR.nextInt(GameValues.gvAiAlliance.NEXT_ALLIANCE_CHECK_TURN_ID_RANDOM);
        }
    }

    public final void diplomacyActions_Union(int nCivID) {
        if (CFG.AI_UNIONS_ENABLED && CFG.core.getCiv(nCivID).getPuppetOfCiv() == nCivID && CFG.core.getCiv(nCivID).getAlliance() > 0 && CFG.core.getAlliance(CFG.core.getCiv(nCivID).getAlliance()).getCivilizationsSize() == 2) {
            int allyCivID = -1;
            for (int a = 0; a < CFG.core.getAlliance(CFG.core.getCiv(nCivID).getAlliance()).getCivilizationsSize(); ++a) {
                if (nCivID == CFG.core.getAlliance(CFG.core.getCiv(nCivID).getAlliance()).getCivilization(a)) continue;
                allyCivID = CFG.core.getAlliance(CFG.core.getCiv(nCivID).getAlliance()).getCivilization(a);
                break;
            }
            if (!GameValues.gvAiDiplomacy.ENABLE_AI_UNIONS_DIFFERENT_RELIGION && CFG.core.getCiv(nCivID).getReligionID() != CFG.core.getCiv(allyCivID).getReligionID()) {
                return;
            }
            if (allyCivID > 0 && CFG.core.getCiv(allyCivID).getPuppetOfCiv() == allyCivID) {
                if (CFG.core.getCivRelationOfCivB(nCivID, allyCivID) > (float)GameValues.gvAiDiplomacy.UNION_ALLY_MIN_RELATION) {
                    GameManager.sendUnionProposal(allyCivID, nCivID);
                } else {
                    GameManager.improveRelation(nCivID, allyCivID);
                    CFG.core.getCiv(nCivID).getCivDiploGD().addImproveRelations(nCivID, allyCivID, GameValues.gvRelationImprove.IMPROVE_RELATIONS_MAX_NUM_OF_TURNS);
                    if (!CFG.core.getCiv(allyCivID).getIsPlayer()) {
                        CFG.core.getCiv(allyCivID).getCivDiploGD().addImproveRelations(allyCivID, nCivID, GameValues.gvRelationImprove.IMPROVE_RELATIONS_MAX_NUM_OF_TURNS);
                    }
                }
            }
        }
    }

    public final void diplomacyActions_FindFriendlyCivs(int nCivID) {
        this.diplomacyActions_InfluencedCiv_Update(nCivID);
        if (GameCalendar.TURNID >= CFG.core.getCiv((int)nCivID).civGD.resumeLookingForFriendsAtTurnID) {
            try {
                if (CFG.core.getCiv((int)nCivID).civGD.iVassalsSize > 0) {
                    for (int i = 0; i < CFG.core.getCiv((int)nCivID).civGD.iVassalsSize; ++i) {
                        if (!(CFG.core.getCiv(CFG.core.getCiv((int)nCivID).civGD.vassals.get((int)i).iCivID).getRelationD(nCivID) < 0.0f) || CFG.core.getCiv(nCivID).getCivDiploGD().getIsImprovingRelations(CFG.core.getCiv((int)nCivID).civGD.vassals.get((int)i).iCivID)) continue;
                        CFG.core.getCiv(nCivID).getCivDiploGD().addImproveRelations(nCivID, CFG.core.getCiv((int)nCivID).civGD.vassals.get((int)i).iCivID, (int)Math.min((float)GameValues.gvRelationImprove.IMPROVE_RELATIONS_MAX_NUM_OF_TURNS, Math.max(GameValues.gvRelationImprove.IMPROVE_RELATIONS_WITH_VASSAL_TURNS_MIN, GameValues.gvRelationImprove.IMPROVE_RELATIONS_WITH_VASSAL_TURNSLIMIT - CFG.core.getCiv(CFG.core.getCiv((int)nCivID).civGD.vassals.get((int)i).iCivID).getRelationD(nCivID)) / GameValues.gvRelationImprove.IMPROVE_RELATIONS_BASE));
                    }
                }
            }
            catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
            int numOfCivsToAdd = Math.min(CFG.oAI.MIN_NUM_OF_RIVALS, CFG.core.getCiv(nCivID).getNumOfProvs()) - CFG.core.getCiv((int)nCivID).civGD.civsToImproveRelationsWithSize;
            if (numOfCivsToAdd > 0) {
                if (CFG.gameAction.getUpdateCivsDiploPoints(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) <= GameValues.gvRelationImprove.COST_OFFER_IMPROVE_RELATIONS_DIPLOMACY_POINTS / 2) {
                    CFG.core.getCiv((int)nCivID).civGD.resumeLookingForFriendsAtTurnID = GameCalendar.TURNID + GameValues.gvAiRelations.RESUME_LOOKING_FOR_FRIENDS_AFTER_X_TURNS + CFG.oR.nextInt(GameValues.gvAiRelations.RESUME_LOOKING_FOR_FRIENDS_AFTER_X_TURNS_RANDOM);
                } else if (!CFG.core.getCiv((int)nCivID).civsInRange.isEmpty()) {
                    int i;
                    ArrayList<AI_CivsInRange> possibleCivs = new ArrayList<AI_CivsInRange>();
                    for (i = CFG.core.getCiv((int)nCivID).civsInRange.size() - 1; i >= 0; --i) {
                        possibleCivs.add(CFG.core.getCiv((int)nCivID).civsInRange.get(i));
                    }
                    for (i = possibleCivs.size() - 1; i >= 0; --i) {
                        if (AIPlaystyle.diplomacyActions_RivalCiv_IsRival(nCivID, ((AI_CivsInRange)possibleCivs.get((int)i)).iCivID)) {
                            possibleCivs.remove(i);
                            continue;
                        }
                        if (this.diplomacyActions_IsInfluenced(nCivID, ((AI_CivsInRange)possibleCivs.get((int)i)).iCivID)) {
                            possibleCivs.remove(i);
                            continue;
                        }
                        if (this.diplomacyActions_IsInfluenced(((AI_CivsInRange)possibleCivs.get((int)i)).iCivID, nCivID) && CFG.oR.nextInt(100) < GameValues.gvAiRelations.IMPROVE_RELATIONS_SKIP_IF_CIV_IMPROVES_RELATIONS_CHANCE_100) {
                            possibleCivs.remove(i);
                            continue;
                        }
                        if (!(CFG.core.getCivRelationOfCivB(nCivID, ((AI_CivsInRange)possibleCivs.get((int)i)).iCivID) > (float)GameValues.gvAiRelations.IMPROVE_RELATIONS_SKIP_IF_RELATIONS_OVER) && !(CFG.core.getCivRelationOfCivB(((AI_CivsInRange)possibleCivs.get((int)i)).iCivID, nCivID) > (float)GameValues.gvAiRelations.IMPROVE_RELATIONS_SKIP_IF_RELATIONS_OVER)) continue;
                        possibleCivs.remove(i);
                    }
                    if (!possibleCivs.isEmpty()) {
                        int i2;
                        ArrayList<Float> lScores = new ArrayList<Float>();
                        ArrayList<Integer> tempD = new ArrayList<Integer>();
                        float modifier_Budget = GameValues.gvAiRelations.IMPROVE_RELATIONS_BASE_BUDGET_MODIFIER + (float)CFG.oR.nextInt(GameValues.gvAiRelations.IMPROVE_RELATIONS_BUDGET_MODIFIER_RANDOM_RANGE_1000) / 1000.0f;
                        float modifier_CivsSize = GameValues.gvAiRelations.IMPROVE_RELATIONS_BASE_CIV_SIZE_MODIFIER + (float)CFG.oR.nextInt(GameValues.gvAiRelations.IMPROVE_RELATIONS_CIV_SIZE_RANDOM_RANGE_1000) / 1000.0f;
                        float modifier_Range = GameValues.gvAiRelations.IMPROVE_RELATIONS_BASE_RANGE_MODIFIER + GameValues.gvAiRelations.IMPROVE_RELATIONS_RANGE_RANK_SCALING * ((float)CFG.core.getCiv(nCivID).getRankPos() / (float)CFG.core.getCivsSize());
                        int civBudget = (int)((float)CFG.core.getCiv((int)nCivID).iBudget * (GameValues.gvAiRelations.IMPROVE_RELATIONS_BASE_BUDGET_SCALE + (float)CFG.oR.nextInt(GameValues.gvAiRelations.IMPROVE_RELATIONS_BUDGET_SCALE_RANDOM_RANGE_100) / 100.0f));
                        int iSize = possibleCivs.size();
                        for (int i3 = 0; i3 < iSize; ++i3) {
                            lScores.add(Float.valueOf(this.diplomacyActions_FriendlyCiv_Score(civBudget, nCivID, (AI_CivsInRange)possibleCivs.get(i3), modifier_Budget, modifier_CivsSize)));
                            tempD.add(i3);
                        }
                        float tempDis = CFG.gameAges.getAge_FogOfWarDiscovery_MetProvinces(GameCalendar.CURRENT_AGEID);
                        for (int i4 = possibleCivs.size() - 1; i4 >= 0; --i4) {
                            lScores.set(i4, Float.valueOf((float)CFG.oR.nextInt(GameValues.gvAiRelations.IMPROVE_RELATIONS_SCORE_RANDOM_RANGE_100) / 100.0f + ((Float)lScores.get(i4)).floatValue() * (1.0f - modifier_Range * ((AI_CivsInRange)possibleCivs.get((int)i4)).fDistance / ((tempDis + tempDis * GameValues.gvAiRelations.IMPROVE_RELATIONS_DISTANCE_MULTIPLIER) * CFG.core.getCiv(nCivID).getTechLevel()) + GameValues.gvAiRelations.IMPROVE_RELATIONS_RELATIONS_MODIFIER * (Math.min(CFG.core.getCivRelationOfCivB(nCivID, ((AI_CivsInRange)possibleCivs.get((int)i4)).iCivID), 0.0f) / 100.0f))));
                        }
                        ArrayList<Integer> sortedIDs = new ArrayList<Integer>();
                        while (!tempD.isEmpty() && sortedIDs.size() < numOfCivsToAdd) {
                            int tBest = 0;
                            for (i2 = tempD.size() - 1; i2 > 0; --i2) {
                                if (!(((Float)lScores.get((Integer)tempD.get(i2))).floatValue() > ((Float)lScores.get((Integer)tempD.get(tBest))).floatValue())) continue;
                                tBest = i2;
                            }
                            sortedIDs.add((Integer)tempD.get(tBest));
                            tempD.remove(tBest);
                        }
                        int maxCivsInThisTurn = Math.min(1 + CFG.oR.nextInt(2), numOfCivsToAdd);
                        for (i2 = 0; i2 < possibleCivs.size() && i2 < maxCivsInThisTurn; ++i2) {
                            int improveRelationsToValue = GameValues.gvAiRelations.IMPROVE_RELATIONS_MIN_VALUE + CFG.oR.nextInt(GameValues.gvAiRelations.IMPROVE_RELATIONS_RANDOM);
                            CFG.core.getCiv(nCivID).getCivDiploGD().addImproveRelations(nCivID, ((AI_CivsInRange)possibleCivs.get((int)((Integer)sortedIDs.get((int)i2)).intValue())).iCivID, (int)Math.min((float)GameValues.gvRelationImprove.IMPROVE_RELATIONS_MAX_NUM_OF_TURNS, Math.max(10.0f, (float)improveRelationsToValue - CFG.core.getCiv(((AI_CivsInRange)possibleCivs.get((int)((Integer)sortedIDs.get((int)i2)).intValue())).iCivID).getRelationD(nCivID)) / GameValues.gvRelationImprove.IMPROVE_RELATIONS_BASE));
                            CFG.core.getCiv((int)nCivID).civGD.civsToImproveRelationsWith.add(new AI_ImproveRelations(((AI_CivsInRange)possibleCivs.get((int)((Integer)sortedIDs.get((int)i2)).intValue())).iCivID, improveRelationsToValue, GameCalendar.TURNID + GameValues.gvAiRelations.IMPROVE_RELATIONS_MIN_NUM_OF_TURNS + CFG.oR.nextInt(GameValues.gvAiRelations.IMPROVE_RELATIONS_MIN_NUM_OF_TURNS_RANDOM)));
                            --numOfCivsToAdd;
                        }
                        CFG.core.getCiv((int)nCivID).civGD.civsToImproveRelationsWithSize = CFG.core.getCiv((int)nCivID).civGD.civsToImproveRelationsWith.size();
                        if (numOfCivsToAdd <= 0) {
                            CFG.core.getCiv((int)nCivID).civGD.resumeLookingForFriendsAtTurnID = GameCalendar.TURNID + GameValues.gvAiRelations.RESUME_LOOKING_FOR_FRIENDS_AFTER_X_TURNS + CFG.oR.nextInt(GameValues.gvAiRelations.RESUME_LOOKING_FOR_FRIENDS_AFTER_X_TURNS_RANDOM);
                        } else if (possibleCivs.size() - maxCivsInThisTurn > Math.min(CFG.oAI.MIN_NUM_OF_RIVALS, CFG.core.getCiv(nCivID).getNumOfProvs()) - CFG.core.getCiv((int)nCivID).civGD.civsToImproveRelationsWithSize) {
                            CFG.core.getCiv((int)nCivID).civGD.resumeLookingForFriendsAtTurnID = GameCalendar.TURNID + GameValues.gvAiRelations.RESUME_LOOKING_FOR_FRIENDS_AFTER_X_TURNS + CFG.oR.nextInt(GameValues.gvAiRelations.RESUME_LOOKING_FOR_FRIENDS_AFTER_X_TURNS_RANDOM);
                        }
                    } else {
                        CFG.core.getCiv((int)nCivID).civGD.resumeLookingForFriendsAtTurnID = GameCalendar.TURNID + GameValues.gvAiRelations.RESUME_LOOKING_FOR_FRIENDS_AFTER_X_TURNS + CFG.oR.nextInt(GameValues.gvAiRelations.RESUME_LOOKING_FOR_FRIENDS_AFTER_X_TURNS_RANDOM);
                    }
                    CFG.core.getCiv((int)nCivID).civGD.resumeLookingForFriendsAtTurnID = GameCalendar.TURNID + GameValues.gvAiRelations.RESUME_LOOKING_FOR_FRIENDS_AFTER_X_TURNS + CFG.oR.nextInt(GameValues.gvAiRelations.RESUME_LOOKING_FOR_FRIENDS_AFTER_X_TURNS_RANDOM);
                } else {
                    CFG.core.getCiv((int)nCivID).civGD.resumeLookingForFriendsAtTurnID = GameCalendar.TURNID + GameValues.gvAiRelations.RESUME_LOOKING_FOR_FRIENDS_AFTER_X_TURNS + CFG.oR.nextInt(GameValues.gvAiRelations.RESUME_LOOKING_FOR_FRIENDS_AFTER_X_TURNS_RANDOM);
                }
            } else {
                CFG.core.getCiv((int)nCivID).civGD.resumeLookingForFriendsAtTurnID = GameCalendar.TURNID + GameValues.gvAiRelations.RESUME_LOOKING_FOR_FRIENDS_AFTER_X_TURNS + CFG.oR.nextInt(GameValues.gvAiRelations.RESUME_LOOKING_FOR_FRIENDS_AFTER_X_TURNS_RANDOM);
            }
        }
    }

    public final float diplomacyActions_FriendlyCiv_Score(int civBudget, int nCivID, AI_CivsInRange withCiv, float modifier_Budget, float modifier_CivsSize) {
        return modifier_Budget * (float)Math.min(civBudget, CFG.core.getCiv((int)withCiv.iCivID).iBudget) / (float)Math.max(civBudget, CFG.core.getCiv((int)withCiv.iCivID).iBudget) + modifier_CivsSize * (float)Math.min(CFG.core.getCiv(nCivID).getNumOfProvs(), CFG.core.getCiv(withCiv.iCivID).getNumOfProvs()) / (float)Math.max(CFG.core.getCiv(nCivID).getNumOfProvs(), CFG.core.getCiv(withCiv.iCivID).getNumOfProvs()) * (this.isRivalOfMyRival(nCivID, withCiv.iCivID) ? GameValues.gvAiRelations.IMPROVE_RELATIONS_SCORE_RIVAL_OF_RIVAL_BONUS : 1.0f) * (CFG.core.getCivRelationOfCivB(nCivID, withCiv.iCivID) > (float)GameValues.gvAiRelations.IMPROVE_RELATIONS_SCORE_RELATION_THRESHOLD ? GameValues.gvAiRelations.IMPROVE_RELATIONS_SCORE_HIGH_RELATION_PENALTY : 1.0f);
    }

    public final boolean isRivalOfMyRival(int nCivID, int withCiv) {
        for (int i = 0; i < CFG.core.getCiv((int)nCivID).civGD.civRivalsSize; ++i) {
            if (!AIPlaystyle.diplomacyActions_RivalCiv_IsRival(CFG.core.getCiv((int)nCivID).civGD.civRivals.get((int)i).iCivID, withCiv)) continue;
            return true;
        }
        return false;
    }

    public final void diplomacyActions_RivalCiv_New(int nCivID) {
        if (GameCalendar.TURNID >= CFG.core.getCiv((int)nCivID).civGD.resumeLookingForRivalAtTurnID) {
            int numOfCivsToAdd = Math.min(CFG.oAI.MIN_NUM_OF_RIVALS, CFG.core.getCiv(nCivID).getNumOfProvs()) - CFG.core.getCiv((int)nCivID).civGD.civRivalsSize;
            if (numOfCivsToAdd > 0) {
                try {
                    List<Integer> rivals = RivalsManager.buildRivals(nCivID, GameValues.gvAiRivals.NUM_OF_RIVALS_TO_CHOOSE_FROM);
                    List<Float> score = RivalsManager.chooseRivals_BuildScore(nCivID, rivals);
                    int rivalsSize = rivals.size();
                    for (int a = 0; a < numOfCivsToAdd && !rivals.isEmpty(); ++a) {
                        int bestID = 0;
                        for (int i = 1; i < rivalsSize; ++i) {
                            if (!(score.get(bestID).floatValue() > score.get(i).floatValue())) continue;
                            bestID = i;
                        }
                        int turns = GameValues.gvRelationDecrease.SUSPEND_DIPLOMATIC_RELATIONS_MIN + CFG.oR.nextInt(GameValues.gvRelationDecrease.SUSPEND_DIPLOMATIC_RELATIONS_MAX - GameValues.gvRelationDecrease.SUSPEND_DIPLOMATIC_RELATIONS_MIN);
                        GameManager.decreaseRelation(nCivID, rivals.get(bestID), turns);
                        if (CFG.oR.nextInt(1000) < GameValues.gvAiDiplomacy.ADD_RIVAL_IMPOSE_SANCTIONS_CHANCE_1000) {
                            GameManager.imposeSanctions(nCivID, rivals.get(bestID), GameValues.gvSanctions.SANCTIONS_MIN_TURNS + CFG.oR.nextInt(Math.max(1, GameValues.gvSanctions.SANCTIONS_MAX_TURNS - GameValues.gvSanctions.SANCTIONS_MIN_TURNS)));
                        }
                        CFG.core.getCiv((int)nCivID).civGD.civRivals.add(new AI_Rival(rivals.get(bestID), GameCalendar.TURNID + GameValues.gvAiRivals.END_OF_RIVALRY_AFTER_EXTRA_TURNS_BASE + CFG.oR.nextInt(GameValues.gvAiRivals.END_OF_RIVALRY_AFTER_EXTRA_TURNS_RANDOM)));
                        CFG.core.getCiv((int)nCivID).civGD.civRivalsSize = CFG.core.getCiv((int)nCivID).civGD.civRivals.size();
                        if (CFG.core.getCiv(rivals.get(bestID)).getIsPlayer()) {
                            CFG.core.getCiv((int)rivals.get((int)bestID).intValue()).getCivDiploGD().messageBox.addMessage(new Message_Rivals(nCivID));
                        }
                        rivals.remove(bestID);
                        score.remove(bestID);
                        rivalsSize = rivals.size();
                    }
                    rivals.clear();
                    score.clear();
                    CFG.core.getCiv((int)nCivID).civGD.resumeLookingForRivalAtTurnID = GameCalendar.TURNID + GameValues.gvAiRivals.RESUME_LOOKING_FOR_ENEMY_AT_TURN_ID + CFG.oR.nextInt(GameValues.gvAiRivals.RESUME_LOOKING_FOR_ENEMY_AT_TURN_ID_RANDOM);
                }
                catch (Exception ex) {
                    CFG.exceptionStack(ex);
                    CFG.core.getCiv((int)nCivID).civGD.resumeLookingForRivalAtTurnID = GameCalendar.TURNID + GameValues.gvAiRivals.RESUME_LOOKING_FOR_ENEMY_AT_TURN_ID + CFG.oR.nextInt(GameValues.gvAiRivals.RESUME_LOOKING_FOR_ENEMY_AT_TURN_ID_RANDOM);
                }
            } else {
                CFG.core.getCiv((int)nCivID).civGD.resumeLookingForRivalAtTurnID = GameCalendar.TURNID + GameValues.gvAiRivals.RESUME_LOOKING_FOR_ENEMY_AT_TURN_ID + CFG.oR.nextInt(GameValues.gvAiRivals.RESUME_LOOKING_FOR_ENEMY_AT_TURN_ID_RANDOM);
            }
        }
    }

    public final void diplomacyActions_RivalCiv(int nCivID) {
        if (GameCalendar.TURNID >= CFG.core.getCiv((int)nCivID).civGD.resumeLookingForRivalAtTurnID) {
            int numOfCivsToAdd = Math.min(CFG.oAI.MIN_NUM_OF_RIVALS, CFG.core.getCiv(nCivID).getNumOfProvs()) - CFG.core.getCiv((int)nCivID).civGD.civRivalsSize;
            if (numOfCivsToAdd > 0) {
                if (!CFG.core.getCiv((int)nCivID).civsInRange.isEmpty()) {
                    int i;
                    ArrayList<AI_CivsInRange> possibleCivs = new ArrayList<AI_CivsInRange>();
                    for (i = CFG.core.getCiv((int)nCivID).civsInRange.size() - 1; i >= 0; --i) {
                        possibleCivs.add(CFG.core.getCiv((int)nCivID).civsInRange.get(i));
                    }
                    for (i = possibleCivs.size() - 1; i >= 0; --i) {
                        if (AIPlaystyle.diplomacyActions_RivalCiv_IsRival(nCivID, ((AI_CivsInRange)possibleCivs.get((int)i)).iCivID)) {
                            possibleCivs.remove(i);
                            continue;
                        }
                        if (!this.diplomacyActions_IsInfluenced(nCivID, ((AI_CivsInRange)possibleCivs.get((int)i)).iCivID)) continue;
                        possibleCivs.remove(i);
                    }
                    if (!possibleCivs.isEmpty()) {
                        int i2;
                        ArrayList<Float> lScores = new ArrayList<Float>();
                        ArrayList<Integer> tempD = new ArrayList<Integer>();
                        float modifier_Budget = GameValues.gvAiRivals.OLD_RIVALS_BUDGET_MODIFIER + (float)CFG.oR.nextInt(GameValues.gvAiRivals.OLD_RIVALS_BUDGET_MODIFIER_RANDOM_1000) / 1000.0f;
                        float modifier_CivsSize = GameValues.gvAiRivals.OLD_RIVALS_CIV_SIZE_MODIFIER + (float)CFG.oR.nextInt(GameValues.gvAiRivals.OLD_RIVALS_CIV_SIZE_MODIFIER_RANDOM_1000) / 1000.0f;
                        float modifier_Range = GameValues.gvAiRivals.OLD_RIVALS_DISTANCE_MODIFIER + GameValues.gvAiRivals.OLD_RIVALS_DISTANCE_RANK_MODIFIER * ((float)CFG.core.getCiv(nCivID).getRankPos() / (float)CFG.core.getCivsSize());
                        int civBudget = 0;
                        civBudget = CFG.core.getCiv(nCivID).getNumOfProvs() < GameValues.gvAiRivals.OLD_RIVALS_BUDGET_MODIFIER_2_SMALL_CIV_PROVINCES_BELOW || CFG.core.getCiv((int)nCivID).iLeague > 6 ? (int)((float)CFG.core.getCiv((int)nCivID).iBudget * (GameValues.gvAiRivals.OLD_RIVALS_BUDGET_MODIFIER_2_SMALL_CIV + (float)CFG.oR.nextInt(GameValues.gvAiRivals.OLD_RIVALS_BUDGET_MODIFIER_2_SMALL_CIV_RANDOM_100) / 100.0f)) : (int)((float)CFG.core.getCiv((int)nCivID).iBudget * (GameValues.gvAiRivals.OLD_RIVALS_BUDGET_MODIFIER_2 + (float)CFG.oR.nextInt(GameValues.gvAiRivals.OLD_RIVALS_BUDGET_MODIFIER_2_RANDOM_100) / 100.0f));
                        int iSize = possibleCivs.size();
                        for (int i3 = 0; i3 < iSize; ++i3) {
                            lScores.add(Float.valueOf(this.diplomacyActions_RivalCiv_Score(civBudget, nCivID, (AI_CivsInRange)possibleCivs.get(i3), modifier_Budget, modifier_CivsSize)));
                            tempD.add(i3);
                        }
                        float tempDis = CFG.gameAges.getAge_FogOfWarDiscovery_MetProvinces(GameCalendar.CURRENT_AGEID);
                        for (int i4 = possibleCivs.size() - 1; i4 >= 0; --i4) {
                            lScores.set(i4, Float.valueOf(((Float)lScores.get(i4)).floatValue() * (1.0f + (-modifier_Range + (GameValues.gvAiRivals.OLD_RIVALS_RANGE_AGGRESSION_BASE + (float)CFG.oR.nextInt(GameValues.gvAiRivals.OLD_RIVALS_RANGE_AGGRESSION_RANDOM_100) / 100.0f) * CFG.core.getCiv((int)((AI_CivsInRange)possibleCivs.get((int)i4)).iCivID).civGD.civAggressionLevel) * ((AI_CivsInRange)possibleCivs.get((int)i4)).fDistance / ((tempDis + tempDis * GameValues.gvAiRivals.OLD_RIVALS_DISTANCE_BONUS) * CFG.core.getCiv(nCivID).getTechLevel()) + GameValues.gvAiRivals.OLD_RIVALS_RELATIONS_MODIFIER * (Math.min(CFG.core.getCivRelationOfCivB(nCivID, ((AI_CivsInRange)possibleCivs.get((int)i4)).iCivID), 0.0f) / 100.0f))));
                        }
                        ArrayList<Integer> sortedIDs = new ArrayList<Integer>();
                        while (!tempD.isEmpty() && sortedIDs.size() < numOfCivsToAdd) {
                            int tBest = 0;
                            for (i2 = tempD.size() - 1; i2 > 0; --i2) {
                                if (!(((Float)lScores.get((Integer)tempD.get(i2))).floatValue() > ((Float)lScores.get((Integer)tempD.get(tBest))).floatValue())) continue;
                                tBest = i2;
                            }
                            sortedIDs.add((Integer)tempD.get(tBest));
                            tempD.remove(tBest);
                        }
                        int maxCivsInThisTurn = Math.min(CFG.oAI.MIN_NUM_OF_RIVALS + CFG.oR.nextInt(3), numOfCivsToAdd);
                        for (i2 = 0; i2 < possibleCivs.size() && i2 < maxCivsInThisTurn; ++i2) {
                            int turns = GameValues.gvRelationDecrease.SUSPEND_DIPLOMATIC_RELATIONS_MIN + CFG.oR.nextInt(GameValues.gvRelationDecrease.SUSPEND_DIPLOMATIC_RELATIONS_MAX - GameValues.gvRelationDecrease.SUSPEND_DIPLOMATIC_RELATIONS_MIN);
                            GameManager.decreaseRelation(nCivID, ((AI_CivsInRange)possibleCivs.get((int)((Integer)sortedIDs.get((int)i2)).intValue())).iCivID, turns);
                            CFG.core.getCiv((int)nCivID).civGD.civRivals.add(new AI_Rival(((AI_CivsInRange)possibleCivs.get((int)((Integer)sortedIDs.get((int)i2)).intValue())).iCivID, GameCalendar.TURNID + GameValues.gvAiRivals.END_OF_RIVALRY_AFTER_EXTRA_TURNS_BASE + CFG.oR.nextInt(GameValues.gvAiRivals.END_OF_RIVALRY_AFTER_EXTRA_TURNS_RANDOM)));
                            if (CFG.core.getCiv(((AI_CivsInRange)possibleCivs.get((int)((Integer)sortedIDs.get((int)i2)).intValue())).iCivID).getIsPlayer()) {
                                CFG.core.getCiv((int)((AI_CivsInRange)possibleCivs.get((int)((Integer)sortedIDs.get((int)i2)).intValue())).iCivID).getCivDiploGD().messageBox.addMessage(new Message_Rivals(nCivID));
                            }
                            --numOfCivsToAdd;
                        }
                        CFG.core.getCiv((int)nCivID).civGD.civRivalsSize = CFG.core.getCiv((int)nCivID).civGD.civRivals.size();
                        if (numOfCivsToAdd <= 0) {
                            CFG.core.getCiv((int)nCivID).civGD.resumeLookingForRivalAtTurnID = GameCalendar.TURNID + GameValues.gvAiRivals.RESUME_LOOKING_FOR_ENEMY_AT_TURN_ID + CFG.oR.nextInt(GameValues.gvAiRivals.RESUME_LOOKING_FOR_ENEMY_AT_TURN_ID_RANDOM);
                        } else if (possibleCivs.size() - maxCivsInThisTurn > Math.min(CFG.oAI.MIN_NUM_OF_RIVALS, CFG.core.getCiv(nCivID).getNumOfProvs()) - CFG.core.getCiv((int)nCivID).civGD.civRivalsSize) {
                            CFG.core.getCiv((int)nCivID).civGD.resumeLookingForRivalAtTurnID = GameCalendar.TURNID + GameValues.gvAiRivals.RESUME_LOOKING_FOR_ENEMY_AT_TURN_ID + CFG.oR.nextInt(GameValues.gvAiRivals.RESUME_LOOKING_FOR_ENEMY_AT_TURN_ID_RANDOM);
                        }
                    } else {
                        CFG.core.getCiv((int)nCivID).civGD.resumeLookingForRivalAtTurnID = GameCalendar.TURNID + GameValues.gvAiRivals.RESUME_LOOKING_FOR_ENEMY_AT_TURN_ID + CFG.oR.nextInt(GameValues.gvAiRivals.RESUME_LOOKING_FOR_ENEMY_AT_TURN_ID_RANDOM);
                    }
                } else {
                    CFG.core.getCiv((int)nCivID).civGD.resumeLookingForRivalAtTurnID = GameCalendar.TURNID + GameValues.gvAiRivals.RESUME_LOOKING_FOR_ENEMY_AT_TURN_ID + CFG.oR.nextInt(GameValues.gvAiRivals.RESUME_LOOKING_FOR_ENEMY_AT_TURN_ID_RANDOM);
                }
            } else {
                CFG.core.getCiv((int)nCivID).civGD.resumeLookingForRivalAtTurnID = GameCalendar.TURNID + GameValues.gvAiRivals.RESUME_LOOKING_FOR_ENEMY_AT_TURN_ID + CFG.oR.nextInt(GameValues.gvAiRivals.RESUME_LOOKING_FOR_ENEMY_AT_TURN_ID_RANDOM);
            }
        }
    }

    public static final boolean diplomacyActions_RivalCiv_IsRival(int nCivID, int nRivalID) {
        for (int z = 0; z < CFG.core.getCiv((int)nCivID).civGD.civRivalsSize; ++z) {
            if (CFG.core.getCiv((int)nCivID).civGD.civRivals.get((int)z).iCivID != nRivalID) continue;
            return true;
        }
        return false;
    }

    public final boolean diplomacyActions_IsInfluenced(int nCivID, int nInfluenced) {
        for (int z = 0; z < CFG.core.getCiv((int)nCivID).civGD.civsToImproveRelationsWithSize; ++z) {
            if (CFG.core.getCiv((int)nCivID).civGD.civsToImproveRelationsWith.get((int)z).iCivID != nInfluenced) continue;
            return true;
        }
        return false;
    }

    public static void diplomacyActions_RivalCiv_Update() {
        for (int i = 1 + GameCalendar.TURNID % GameValues.gvAiRivals.UPDATE_RIVALRY_END_EVERY_X_TURNS; i < CFG.core.getCivsSize(); i += GameValues.gvAiRivals.UPDATE_RIVALRY_END_EVERY_X_TURNS) {
            AIPlaystyle.diplomacyActions_RivalCiv_Update(i);
        }
    }

    public static void diplomacyActions_RivalCiv_Update(int nCivID) {
        for (int z = CFG.core.getCiv((int)nCivID).civGD.civRivalsSize - 1; z >= 0; --z) {
            if (CFG.core.getCiv((int)nCivID).civGD.civRivals.get((int)z).iUntilTurnID > GameCalendar.TURNID) continue;
            CFG.core.getCiv((int)nCivID).civGD.civRivals.remove(z);
            CFG.core.getCiv((int)nCivID).civGD.civRivalsSize = CFG.core.getCiv((int)nCivID).civGD.civRivals.size();
            CFG.core.getCiv((int)nCivID).civGD.resumeLookingForRivalAtTurnID = GameCalendar.TURNID;
        }
    }

    public final void diplomacyActions_InfluencedCiv_Update(int nCivID) {
        for (int z = CFG.core.getCiv((int)nCivID).civGD.civsToImproveRelationsWithSize - 1; z >= 0; --z) {
            if (CFG.core.getCiv((int)nCivID).civGD.civsToImproveRelationsWith.get((int)z).iUntilTurnID > GameCalendar.TURNID) continue;
            CFG.core.getCiv((int)nCivID).civGD.civsToImproveRelationsWith.remove(z);
            CFG.core.getCiv((int)nCivID).civGD.civsToImproveRelationsWithSize = CFG.core.getCiv((int)nCivID).civGD.civsToImproveRelationsWith.size();
            CFG.core.getCiv((int)nCivID).civGD.resumeLookingForFriendsAtTurnID = GameCalendar.TURNID;
        }
    }

    public final float diplomacyActions_RivalCiv_Score(int civBudget, int nCivID, AI_CivsInRange withCiv, float modifier_Budget, float modifier_CivsSize) {
        return modifier_Budget * (float)Math.min(civBudget, CFG.core.getCiv((int)withCiv.iCivID).iBudget) / (float)Math.max(civBudget, CFG.core.getCiv((int)withCiv.iCivID).iBudget) + modifier_CivsSize * (float)Math.min(CFG.core.getCiv(nCivID).getNumOfProvs(), CFG.core.getCiv(withCiv.iCivID).getNumOfProvs()) / (float)Math.max(CFG.core.getCiv(nCivID).getNumOfProvs(), CFG.core.getCiv(withCiv.iCivID).getNumOfProvs());
    }

    public final List<AI_CivsInRange> diplomacyActions_CivsInRange(int nCivID) {
        int i;
        ArrayList<AI_CivsInRange> possibleCivs = new ArrayList<AI_CivsInRange>();
        float tDistanceBetweenCivs = 1.0f;
        float tempDis = CFG.gameAges.getAge_FogOfWarDiscovery_MetProvinces(GameCalendar.CURRENT_AGEID);
        for (i = 1; i < nCivID; ++i) {
            if (CFG.core.getCiv(i).getNumOfProvs() <= 0 || CFG.core.getCiv(i).getCapitalProvID() <= 0 || !((tDistanceBetweenCivs = Distance.getDistanceFromAToB_PercOfMax(CFG.core.getCiv(nCivID).getCapitalProvID(), CFG.core.getCiv(i).getCapitalProvID())) * GameValues.gvAiCivsInRange.CIVS_IN_RANGE_DISTANCE_MODIFIER < (tempDis + tempDis * GameValues.gvAiCivsInRange.CIVS_IN_RANGE_DISTANCE_EXTRA_MODIFIER) * CFG.core.getCiv(nCivID).getTechLevel())) continue;
            possibleCivs.add(new AI_CivsInRange(i, tDistanceBetweenCivs));
        }
        for (i = nCivID + 1; i < CFG.core.getCivsSize(); ++i) {
            if (CFG.core.getCiv(i).getNumOfProvs() <= 0 || CFG.core.getCiv(i).getCapitalProvID() <= 0 || !((tDistanceBetweenCivs = Distance.getDistanceFromAToB_PercOfMax(CFG.core.getCiv(nCivID).getCapitalProvID(), CFG.core.getCiv(i).getCapitalProvID())) * GameValues.gvAiCivsInRange.CIVS_IN_RANGE_DISTANCE_MODIFIER < (tempDis + tempDis * GameValues.gvAiCivsInRange.CIVS_IN_RANGE_DISTANCE_EXTRA_MODIFIER) * CFG.core.getCiv(nCivID).getTechLevel())) continue;
            possibleCivs.add(new AI_CivsInRange(i, tDistanceBetweenCivs));
        }
        return possibleCivs;
    }

    public final void colonizeProvinces(int nCivID) {
        block17: {
            if (GameCalendar.getColonizationOfWastelandIsEnabled() || GameCalendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES) {
                boolean isColonizing = false;
                for (int k = CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.size() - 1; k >= 0; --k) {
                    if (CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.get((int)k).MISSION_TYPE != CivArmyMission_Type.COLONIZE_PROVINCE) continue;
                    isColonizing = true;
                    if (!CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.get(k).canMakeAction(nCivID, 0)) continue;
                    if (CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.get(k).action(nCivID)) {
                        CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.remove(k);
                        CFG.core.getCiv((int)nCivID).civGD.resumeColonizationCheckAtTurnID = Math.max(GameCalendar.TURNID + GameValues.gvAiColonization.RESUME_COLONIZATION_AFTER_X_TURNS_IN_PROGRESS + CFG.oR.nextInt(GameValues.gvAiColonization.RESUME_COLONIZATION_AFTER_X_TURNS_IN_PROGRESS_RANDOM), CFG.core.getCiv((int)nCivID).civGD.resumeColonizationCheckAtTurnID);
                        continue;
                    }
                    if (CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.get((int)k).iObsolete > 0) continue;
                    CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.remove(k);
                    CFG.core.getCiv((int)nCivID).civGD.resumeColonizationCheckAtTurnID = Math.max(GameCalendar.TURNID + GameValues.gvAiColonization.RESUME_COLONIZATION_AFTER_X_TURNS_IN_PROGRESS + CFG.oR.nextInt(GameValues.gvAiColonization.RESUME_COLONIZATION_AFTER_X_TURNS_IN_PROGRESS_RANDOM), CFG.core.getCiv((int)nCivID).civGD.resumeColonizationCheckAtTurnID);
                }
                if (isColonizing) {
                    return;
                }
                CFG.core.getCiv((int)nCivID).civGD.iLockTreasury = 1;
                if (CFG.core.getCiv((int)nCivID).civGD.resumeColonizationCheckAtTurnID > GameCalendar.TURNID) {
                    return;
                }
                if (!GameCalendar.getCanColonize_TechLevel(nCivID) && CFG.core.getCiv(nCivID).getTechLevel() / GameCalendar.COLONIZATION_TECH_LEVEL < 1.0f - GameValues.gvAiColonization.TECH_GAP_REDUCTION_FACTOR * Math.min((float)CFG.oAI.iNumOfColonizedProvcs / Math.min((float)(GameValues.gvAiColonization.BASE_COLONIZATION_DIVISOR + Math.min((CFG.core.getCiv(nCivID).getRankPos() - 1) * GameValues.gvAiColonization.RANK_COLONIZATION_MULTIPLIER, GameValues.gvAiColonization.MAX_RANK_COLONIZATION_BONUS)), (float)CFG.core.getProvinSize() * GameValues.gvAiColonization.PROVINCE_SCALING_FACTOR), 1.0f)) {
                    CFG.core.getCiv((int)nCivID).civGD.resumeColonizationCheckAtTurnID = Math.max(GameCalendar.TURNID + GameValues.gvAiColonization.RESUME_COLONIZATION_AFTER_X_TURNS_INSUFFICIENT_TECH + CFG.oR.nextInt(GameValues.gvAiColonization.RESUME_COLONIZATION_AFTER_X_TURNS_INSUFFICIENT_TECH_RANDOM), CFG.core.getCiv((int)nCivID).civGD.resumeColonizationCheckAtTurnID);
                    return;
                }
                if (CFG.core.getCiv((int)nCivID).iBudget < 1) {
                    CFG.core.getCiv((int)nCivID).civGD.resumeColonizationCheckAtTurnID = Math.max(GameCalendar.TURNID + GameValues.gvAiColonization.RESUME_COLONIZATION_AFTER_X_TURNS_INSUFFICIENT_GOLD + CFG.oR.nextInt(GameValues.gvAiColonization.RESUME_COLONIZATION_AFTER_X_TURNS_INSUFFICIENT_GOLD_RANDOM), CFG.core.getCiv((int)nCivID).civGD.resumeColonizationCheckAtTurnID);
                    return;
                }
                if ((float)GameManager.getColonizeCost_AI(nCivID) / (float)CFG.core.getCiv((int)nCivID).iBudget > GameValues.gvAiColonization.MAX_COLONIZATION_COST_TO_BUDGET_RATIO) {
                    CFG.core.getCiv((int)nCivID).civGD.resumeColonizationCheckAtTurnID = Math.max(GameCalendar.TURNID + GameValues.gvAiColonization.RESUME_COLONIZATION_AFTER_TURNS_TOO_EXPENSIVE + CFG.oR.nextInt(GameValues.gvAiColonization.RESUME_COLONIZATION_AFTER_TURNS_TOO_EXPENSIVE_RANDOM), CFG.core.getCiv((int)nCivID).civGD.resumeColonizationCheckAtTurnID);
                    return;
                }
                if ((float)CFG.core.getCiv(nCivID).getRankPos() < Math.max((float)CFG.core.getCivsSize() * GameValues.gvAiColonization.CAN_COLONIZE_TOP_CIVS_PERCENT, (float)GameValues.gvAiColonization.CAN_COLONIZE_TOP_CIVS_LIMIT)) {
                    try {
                        if (CFG.core.getCiv(nCivID).isAtWarC()) {
                            CFG.core.getCiv((int)nCivID).civGD.resumeColonizationCheckAtTurnID = Math.max(GameCalendar.TURNID + GameValues.gvAiColonization.RESUME_COLONIZATION_AFTER_X_TURNS_AT_WAR + CFG.oR.nextInt(GameValues.gvAiColonization.RESUME_COLONIZATION_AFTER_X_TURNS_AT_WAR_RANDOM), CFG.core.getCiv((int)nCivID).civGD.resumeColonizationCheckAtTurnID);
                            return;
                        }
                        int numOfProvincesAbleToColonize = 0;
                        if (GameCalendar.getColonizationOfWastelandIsEnabled()) {
                            numOfProvincesAbleToColonize += CFG.oAI.lWastelandProvincesWithSeaAccess.size();
                            numOfProvincesAbleToColonize += CFG.core.getCiv((int)nCivID).bordersWithWastelandProvsID.size();
                        }
                        if (GameCalendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES) {
                            numOfProvincesAbleToColonize += CFG.oAI.lNeutralProvincesWithSeaAccess.size();
                            numOfProvincesAbleToColonize += CFG.core.getCiv((int)nCivID).bordersWithNeutralProvcsID.size();
                        }
                        if (numOfProvincesAbleToColonize <= 0) break block17;
                        boolean tryFoundNewColony = true;
                        if (!CFG.core.getCiv((int)nCivID).civGD.coloniesFounded.isEmpty()) {
                            boolean bl = tryFoundNewColony = !this.colonizeProvinces_ExtendColony(nCivID);
                        }
                        if (tryFoundNewColony) {
                            this.colonizeProvinces_FoundNewColony(nCivID);
                        }
                    }
                    catch (Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                } else {
                    CFG.core.getCiv((int)nCivID).civGD.resumeColonizationCheckAtTurnID = Math.max(GameCalendar.TURNID + GameValues.gvAiColonization.RESUME_COLONIZATION_AFTER_X_TURNS_INSUFFICIENT_TECH + CFG.oR.nextInt(GameValues.gvAiColonization.RESUME_COLONIZATION_AFTER_X_TURNS_INSUFFICIENT_TECH_RANDOM), CFG.core.getCiv((int)nCivID).civGD.resumeColonizationCheckAtTurnID);
                }
            }
        }
    }

    public final void colonizeProvinces_FoundNewColony(int nCivID) {
        int colonizeProvinceID;
        int j;
        boolean canColonizeThisProvince;
        int i;
        ArrayList<AI_ProvinceValue> possibleProvinces = new ArrayList<AI_ProvinceValue>();
        ArrayList<Boolean> haveAccessToBasins = new ArrayList<Boolean>();
        for (i = 0; i < CFG.map.numOfBasins; ++i) {
            haveAccessToBasins.add(false);
        }
        for (i = CFG.core.getCiv(nCivID).getSeaAccess_Provinces_Size() - 1; i >= 0; --i) {
            if (CFG.core.getProv(CFG.core.getCiv(nCivID).getSeaAccessProvinces().get(i)).isOccupied()) continue;
            for (int j2 = 0; j2 < CFG.core.getProv(CFG.core.getCiv(nCivID).getSeaAccessProvinces().get(i)).getNeighSeaProvincesSize(); ++j2) {
                haveAccessToBasins.set(CFG.core.getProv(CFG.core.getProv(CFG.core.getCiv(nCivID).getSeaAccessProvinces().get(i)).getNeighSeaProvinces(j2)).getBasinID(), true);
            }
        }
        for (i = CFG.oAI.lNeutralProvincesWithSeaAccess.size() - 1; i >= 0; --i) {
            canColonizeThisProvince = false;
            for (j = 0; j < CFG.core.getProv(CFG.oAI.lNeutralProvincesWithSeaAccess.get(i)).getNeighSeaProvincesSize(); ++j) {
                if (!((Boolean)haveAccessToBasins.get(CFG.core.getProv(CFG.core.getProv(CFG.oAI.lNeutralProvincesWithSeaAccess.get(i)).getNeighSeaProvinces(j)).getBasinID())).booleanValue()) continue;
                canColonizeThisProvince = true;
                break;
            }
            if (!canColonizeThisProvince) continue;
            possibleProvinces.add(new AI_ProvinceValue(CFG.oAI.lNeutralProvincesWithSeaAccess.get(i)));
        }
        if (possibleProvinces.isEmpty() && GameCalendar.getColonizationOfWastelandIsEnabled()) {
            for (i = CFG.oAI.lWastelandProvincesWithSeaAccess.size() - 1; i >= 0; --i) {
                canColonizeThisProvince = false;
                for (j = 0; j < CFG.core.getProv(CFG.oAI.lWastelandProvincesWithSeaAccess.get(i)).getNeighSeaProvincesSize(); ++j) {
                    if (!((Boolean)haveAccessToBasins.get(CFG.core.getProv(CFG.core.getProv(CFG.oAI.lWastelandProvincesWithSeaAccess.get(i)).getNeighSeaProvinces(j)).getBasinID())).booleanValue()) continue;
                    canColonizeThisProvince = true;
                    break;
                }
                if (!canColonizeThisProvince) continue;
                possibleProvinces.add(new AI_ProvinceValue(CFG.oAI.lWastelandProvincesWithSeaAccess.get(i)));
            }
        }
        if (!possibleProvinces.isEmpty() && CFG.core.getProv(colonizeProvinceID = ((AI_ProvinceValue)possibleProvinces.get((int)CFG.oR.nextInt((int)possibleProvinces.size()))).iProvinceID).getCivId() == 0) {
            CFG.core.getCiv((int)nCivID).civGD.civPlans.addNewArmyMission(colonizeProvinceID, new CivArmyMission_ColonizeProvince(nCivID, colonizeProvinceID));
            CFG.core.getCiv((int)nCivID).civGD.declareWarCheckNextTurnID = Math.max(CFG.core.getCiv((int)nCivID).civGD.declareWarCheckNextTurnID, GameCalendar.TURNID + (int)(((float)(GameValues.gvAiColonization.RESUME_COLONIZATION_AFTER_X_TURNS_AFTER_COLONIZATION + CFG.oR.nextInt(GameValues.gvAiColonization.RESUME_COLONIZATION_AFTER_X_TURNS_AFTER_COLONIZATION_RANDOM)) + (float)CFG.oR.nextInt(CFG.core.getCivsSize() + 1) / GameValues.gvAiColonization.RESUME_COLONIZATION_AFTER_X_TURNS_AFTER_COLONIZATION_SIZE_DIVISOR) / Math.max(0.01f, GameCalendar.AI_AGGRESSIVENESS)));
        }
    }

    public final boolean colonizeProvinces_ExtendColony(int nCivID) {
        try {
            int k;
            int j;
            int i;
            ArrayList<AI_ProvinceValue> possibleProvinces = new ArrayList<AI_ProvinceValue>();
            for (i = CFG.core.getCiv((int)nCivID).bordersWithNeutralProvcsID.size() - 1; i >= 0; --i) {
                if (CFG.core.getProv(CFG.core.getCiv((int)nCivID).bordersWithNeutralProvcsID.get(i)).getCivId() != 0) continue;
                possibleProvinces.add(new AI_ProvinceValue(CFG.core.getCiv((int)nCivID).bordersWithNeutralProvcsID.get(i), this.colonizeProvinces_ExtendColony_Score(nCivID, CFG.core.getCiv((int)nCivID).bordersWithNeutralProvcsID.get(i))));
            }
            for (i = CFG.core.getCiv((int)nCivID).bordersWithWastelandProvsID.size() - 1; i >= 0; --i) {
                if (CFG.core.getProv(CFG.core.getCiv((int)nCivID).bordersWithWastelandProvsID.get(i)).getCivId() != 0) continue;
                possibleProvinces.add(new AI_ProvinceValue(CFG.core.getCiv((int)nCivID).bordersWithWastelandProvsID.get(i), this.colonizeProvinces_ExtendColony_Score(nCivID, CFG.core.getCiv((int)nCivID).bordersWithWastelandProvsID.get(i))));
            }
            for (i = CFG.core.getCiv((int)nCivID).civGD.coloniesFounded.size() - 1; i >= 0; --i) {
                for (j = 0; j < CFG.core.getProv(CFG.core.getCiv((int)nCivID).civGD.coloniesFounded.get((int)i).iProvinceID).getNeighProvincesSize(); ++j) {
                    if (CFG.core.getProv(CFG.core.getProv(CFG.core.getCiv((int)nCivID).civGD.coloniesFounded.get((int)i).iProvinceID).getNeighProvinces(j)).getCivId() != 0 && CFG.core.getProv(CFG.core.getProv(CFG.core.getCiv((int)nCivID).civGD.coloniesFounded.get((int)i).iProvinceID).getNeighProvinces(j)).getWastelandLvl() < 0) continue;
                    possibleProvinces.add(new AI_ProvinceValue(CFG.core.getProv(CFG.core.getCiv((int)nCivID).civGD.coloniesFounded.get((int)i).iProvinceID).getNeighProvinces(j), this.colonizeProvinces_ExtendColony_Score(nCivID, CFG.core.getProv(CFG.core.getCiv((int)nCivID).civGD.coloniesFounded.get((int)i).iProvinceID).getNeighProvinces(j))));
                }
            }
            for (i = 0; i < CFG.core.getCiv(nCivID).getNumOfProvs(); ++i) {
                for (j = 0; j < CFG.core.getProv(CFG.core.getCiv(nCivID).getProvID(i)).getNeighSeaProvincesSize(); ++j) {
                    for (k = 0; k < CFG.core.getProv(CFG.core.getProv(CFG.core.getCiv(nCivID).getProvID(i)).getNeighSeaProvinces(j)).getNeighProvincesSize(); ++k) {
                        if (CFG.core.getProv(CFG.core.getProv(CFG.core.getProv(CFG.core.getCiv(nCivID).getProvID(i)).getNeighSeaProvinces(j)).getNeighProvinces(k)).getSeaProv() || CFG.core.getProv(CFG.core.getProv(CFG.core.getProv(CFG.core.getCiv(nCivID).getProvID(i)).getNeighSeaProvinces(j)).getNeighProvinces(k)).getCivId() != 0) continue;
                        possibleProvinces.add(new AI_ProvinceValue(CFG.core.getProv(CFG.core.getProv(CFG.core.getCiv(nCivID).getProvID(i)).getNeighSeaProvinces(j)).getNeighProvinces(k), (int)((float)this.colonizeProvinces_ExtendColony_Score(nCivID, CFG.core.getProv(CFG.core.getProv(CFG.core.getCiv(nCivID).getProvID(i)).getNeighSeaProvinces(j)).getNeighProvinces(k)) * (CFG.core.getProv(CFG.core.getProv(CFG.core.getProv(CFG.core.getCiv(nCivID).getProvID(i)).getNeighSeaProvinces(j)).getNeighProvinces(k)).getNeighProvincesSize() == 0 ? 1.0f : 0.625f))));
                    }
                }
            }
            if (!possibleProvinces.isEmpty()) {
                for (i = possibleProvinces.size() - 1; i >= 0; --i) {
                    for (j = 0; j < CFG.core.getProv(((AI_ProvinceValue)possibleProvinces.get((int)i)).iProvinceID).getNeighSeaProvincesSize(); ++j) {
                        for (k = 0; k < CFG.core.getProv(CFG.core.getProv(((AI_ProvinceValue)possibleProvinces.get((int)i)).iProvinceID).getNeighSeaProvinces(j)).getNeighProvincesSize(); ++k) {
                            if (CFG.core.getProv(CFG.core.getProv(CFG.core.getProv(((AI_ProvinceValue)possibleProvinces.get((int)i)).iProvinceID).getNeighSeaProvinces(j)).getNeighProvinces(k)).getSeaProv() || CFG.core.getProv(CFG.core.getProv(CFG.core.getProv(((AI_ProvinceValue)possibleProvinces.get((int)i)).iProvinceID).getNeighSeaProvinces(j)).getNeighProvinces(k)).getNeighProvincesSize() != 0 || CFG.core.getProv(CFG.core.getProv(CFG.core.getProv(((AI_ProvinceValue)possibleProvinces.get((int)i)).iProvinceID).getNeighSeaProvinces(j)).getNeighProvinces(k)).getCivId() != 0) continue;
                            possibleProvinces.add(new AI_ProvinceValue(CFG.core.getProv(CFG.core.getProv(((AI_ProvinceValue)possibleProvinces.get((int)i)).iProvinceID).getNeighSeaProvinces(j)).getNeighProvinces(k), this.colonizeProvinces_ExtendColony_Score(nCivID, CFG.core.getProv(CFG.core.getProv(((AI_ProvinceValue)possibleProvinces.get((int)i)).iProvinceID).getNeighSeaProvinces(j)).getNeighProvinces(k))));
                        }
                    }
                }
                int colonizeProvinceID = 0;
                for (int i2 = possibleProvinces.size() - 1; i2 > 0; --i2) {
                    if (((AI_ProvinceValue)possibleProvinces.get((int)colonizeProvinceID)).iValue < ((AI_ProvinceValue)possibleProvinces.get((int)i2)).iValue) {
                        colonizeProvinceID = i2;
                        continue;
                    }
                    if (((AI_ProvinceValue)possibleProvinces.get((int)colonizeProvinceID)).iValue != ((AI_ProvinceValue)possibleProvinces.get((int)i2)).iValue || CFG.oR.nextInt(100) >= 50) continue;
                    colonizeProvinceID = i2;
                }
                if (CFG.core.getProv(((AI_ProvinceValue)possibleProvinces.get((int)colonizeProvinceID)).iProvinceID).getCivId() == 0) {
                    if (CFG.gameAction.canColonizieWasteland_BorderOrArmy(((AI_ProvinceValue)possibleProvinces.get((int)colonizeProvinceID)).iProvinceID, nCivID)) {
                        CFG.core.getCiv((int)nCivID).civGD.civPlans.addNewArmyMission(((AI_ProvinceValue)possibleProvinces.get((int)colonizeProvinceID)).iProvinceID, new CivArmyMission_ColonizeProvince_Just(nCivID, ((AI_ProvinceValue)possibleProvinces.get((int)colonizeProvinceID)).iProvinceID));
                        CFG.core.getCiv((int)nCivID).civGD.declareWarCheckNextTurnID = Math.max(CFG.core.getCiv((int)nCivID).civGD.declareWarCheckNextTurnID, GameCalendar.TURNID + (int)(((float)(GameValues.gvAiColonization.RESUME_COLONIZATION_AFTER_X_TURNS_AFTER_COLONIZATION + CFG.oR.nextInt(GameValues.gvAiColonization.RESUME_COLONIZATION_AFTER_X_TURNS_AFTER_COLONIZATION_RANDOM)) + (float)CFG.oR.nextInt(CFG.core.getCivsSize() + 1) / GameValues.gvAiColonization.RESUME_COLONIZATION_AFTER_X_TURNS_AFTER_COLONIZATION_SIZE_DIVISOR) / Math.max(0.01f, GameCalendar.AI_AGGRESSIVENESS)));
                    } else {
                        CFG.core.getCiv((int)nCivID).civGD.civPlans.addNewArmyMission(((AI_ProvinceValue)possibleProvinces.get((int)colonizeProvinceID)).iProvinceID, new CivArmyMission_ColonizeProvince(nCivID, ((AI_ProvinceValue)possibleProvinces.get((int)colonizeProvinceID)).iProvinceID));
                        CFG.core.getCiv((int)nCivID).civGD.declareWarCheckNextTurnID = Math.max(CFG.core.getCiv((int)nCivID).civGD.declareWarCheckNextTurnID, GameCalendar.TURNID + (int)(((float)(GameValues.gvAiColonization.RESUME_COLONIZATION_AFTER_X_TURNS_AFTER_COLONIZATION + CFG.oR.nextInt(GameValues.gvAiColonization.RESUME_COLONIZATION_AFTER_X_TURNS_AFTER_COLONIZATION_RANDOM)) + (float)CFG.oR.nextInt(CFG.core.getCivsSize() + 1) / GameValues.gvAiColonization.RESUME_COLONIZATION_AFTER_X_TURNS_AFTER_COLONIZATION_SIZE_DIVISOR) / Math.max(0.01f, GameCalendar.AI_AGGRESSIVENESS)));
                    }
                    return true;
                }
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
        return false;
    }

    public final int colonizeProvinces_ExtendColony_Score(int nCivID, int nProvinceID) {
        float out = 1.0f;
        if (CFG.core.getProv(nProvinceID).getNeighProvincesSize() > 0) {
            int ownProvinces = 0;
            for (int i = 0; i < CFG.core.getProv(nProvinceID).getNeighProvincesSize(); ++i) {
                if (CFG.core.getProv(CFG.core.getProv(nProvinceID).getNeighProvinces(i)).getCivId() != nCivID) continue;
                ++ownProvinces;
                out += GameValues.gvAiColonization.COLONIZATION_SCORE_PER_OWN_NEIGH_PROVINCE;
            }
            out += CFG.core.getCiv((int)nCivID).civGD.civPers.COLONIZATION_OWN_PROVINCES * ((float)ownProvinces / (float)Math.max(CFG.core.getProv(nProvinceID).getNeighProvincesSize(), 1));
        }
        out += CFG.core.getCiv((int)nCivID).civGD.civPers.COLONIZATION_DISTANCE * (1.0f - Distance.getDistanceFromAToB_PercOfMax(CFG.core.getCiv(nCivID).getCapitalProvID(), nProvinceID));
        if (CFG.core.getProv(nProvinceID).getNeighSeaProvincesSize() > 0) {
            out += CFG.core.getCiv((int)nCivID).civGD.civPers.COLONIZATION_SEA;
        }
        return (int)(out += CFG.core.getCiv((int)nCivID).civGD.civPers.COLONIZATION_GROWTH_RATE * CFG.core.getProv(nProvinceID).getGrowthRate_Pop());
    }

    public final void recruitMilitary_MinSpending(int nCivID) {
        try {
            int nUpkeepLeft = (int)((float)CFG.core.getCiv((int)nCivID).iBudget * this.getMinMilitarySpending(nCivID) - (float)CFG.core.getCiv((int)nCivID).iBudget * CFG.core.getCiv((int)nCivID).iMilitaryUpkeep_PERC);
            if (nUpkeepLeft > 0 && !CFG.core.getCiv((int)nCivID).lFrontLines.isEmpty()) {
                int tMaxDL = 1;
                float tMaxPotential = 1.0f;
                ArrayList<AI_ProvinceInfo> tempFrontProvinces = new ArrayList<AI_ProvinceInfo>();
                for (int i = CFG.core.getCiv((int)nCivID).lFrontLines.size() - 1; i >= 0; --i) {
                    for (int j = CFG.core.getCiv((int)nCivID).lFrontLines.get((int)i).lProvinces.size() - 1; j >= 0; --j) {
                        boolean wasAdded = false;
                        for (int k = tempFrontProvinces.size() - 1; k >= 0; --k) {
                            if (((AI_ProvinceInfo)tempFrontProvinces.get((int)k)).iProvinceID != CFG.core.getCiv((int)nCivID).lFrontLines.get((int)i).lProvinces.get(j)) continue;
                            wasAdded = true;
                            break;
                        }
                        if (wasAdded) continue;
                        tempFrontProvinces.add(new AI_ProvinceInfo(CFG.core.getCiv((int)nCivID).lFrontLines.get((int)i).lProvinces.get(j), this.getPotential_BasedOnNeighboringProvs(CFG.core.getCiv((int)nCivID).lFrontLines.get((int)i).lProvinces.get(j), nCivID), CFG.gameAction.gMARY(CFG.core.getCiv((int)nCivID).lFrontLines.get((int)i).lProvinces.get(j))));
                    }
                }
                if (CFG.core.getCiv(nCivID).getCapitalProvID() >= 0 && CFG.core.getProv(CFG.core.getCiv(nCivID).getCapitalProvID()).getNeighSeaProvincesSize() > 0 && CFG.core.getProv(CFG.core.getCiv(nCivID).getCapitalProvID()).getCivId() == nCivID) {
                    boolean aldAdded = false;
                    for (int k = tempFrontProvinces.size() - 1; k >= 0; --k) {
                        if (((AI_ProvinceInfo)tempFrontProvinces.get((int)k)).iProvinceID != CFG.core.getCiv(nCivID).getCapitalProvID()) continue;
                        aldAdded = true;
                        break;
                    }
                    if (!aldAdded) {
                        tempFrontProvinces.add(new AI_ProvinceInfo(CFG.core.getCiv(nCivID).getCapitalProvID(), this.getPotential_BasedOnNeighboringProvs(CFG.core.getCiv(nCivID).getCapitalProvID(), nCivID), CFG.gameAction.gMARY(CFG.core.getCiv(nCivID).getCapitalProvID())));
                    }
                }
                if (!tempFrontProvinces.isEmpty()) {
                    int tMaxArmy = 1;
                    float tMaxRegion_NumOfProvinces = 1.0f;
                    float tMaxRegion_Potential = 1.0f;
                    ArrayList<Integer> tMovingArmy = new ArrayList<Integer>();
                    int iSize = tempFrontProvinces.size();
                    int tempMovingArmy = 0;
                    for (int i = 0; i < iSize; ++i) {
                        if (((AI_ProvinceInfo)tempFrontProvinces.get((int)i)).iValue > tMaxPotential) {
                            tMaxPotential = ((AI_ProvinceInfo)tempFrontProvinces.get((int)i)).iValue;
                        }
                        if (CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i)).iProvinceID).getDangerLevel_WithArmy() > tMaxDL) {
                            tMaxDL = CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i)).iProvinceID).getDangerLevel_WithArmy();
                        }
                        if ((float)CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i)).iProvinceID).getRegion_NumOfProvinces() > tMaxRegion_NumOfProvinces) {
                            tMaxRegion_NumOfProvinces = CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i)).iProvinceID).getRegion_NumOfProvinces();
                        }
                        if ((float)CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i)).iProvinceID).getPotentialRegion() > tMaxRegion_Potential) {
                            tMaxRegion_Potential = CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i)).iProvinceID).getPotentialRegion();
                        }
                        tMovingArmy.add(tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, ((AI_ProvinceInfo)tempFrontProvinces.get((int)i)).iProvinceID));
                        if (CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i)).iProvinceID).getArmyID(0) + tempMovingArmy <= tMaxArmy) continue;
                        tMaxArmy = CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i)).iProvinceID).getArmyID(0) + tempMovingArmy;
                    }
                    int numOfUnitsToRecruit_MAX = (int)((float)nUpkeepLeft / (CFG.gameUpdate.getMilitaryUpkeep_WithoutDefensivePosition(((AI_ProvinceInfo)tempFrontProvinces.get((int)0)).iProvinceID, 1000, nCivID) / 1000.0f));
                    int iSize2 = tempFrontProvinces.size();
                    for (int i = 0; i < iSize2; ++i) {
                        ((AI_ProvinceInfo)tempFrontProvinces.get((int)i)).iValue = this.getValue_PositionOfArmy(nCivID, tempFrontProvinces, i, (Integer)tMovingArmy.get(i), tMaxPotential, tMaxRegion_Potential, tMaxDL, tMaxArmy, numOfUnitsToRecruit_MAX, tMaxRegion_NumOfProvinces);
                    }
                    ArrayList<AI_ProvinceInfo> sortedFrontProvinces = new ArrayList<AI_ProvinceInfo>();
                    while (!tempFrontProvinces.isEmpty()) {
                        int tBest = 0;
                        int iSize3 = tempFrontProvinces.size();
                        for (int i = 1; i < iSize3; ++i) {
                            if (!(((AI_ProvinceInfo)tempFrontProvinces.get((int)tBest)).iValue < ((AI_ProvinceInfo)tempFrontProvinces.get((int)i)).iValue)) continue;
                            tBest = i;
                        }
                        sortedFrontProvinces.add((AI_ProvinceInfo)tempFrontProvinces.get(tBest));
                        tempFrontProvinces.remove(tBest);
                    }
                    int iNumOfMaxRecruitments = Math.max(1, Math.min((CFG.core.getCiv(nCivID).getMovemPoints() - CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_MOVE_OWN_PROVINCE) / CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_RECRUIT, CFG.core.getCiv(nCivID).getNumOfProvs()));
                    ArrayList<AI_ProvinceInfo> tRecruitArmiesForProvinces = new ArrayList<AI_ProvinceInfo>();
                    float totalValues = 0.0f;
                    for (int i = 0; i < iNumOfMaxRecruitments && i < sortedFrontProvinces.size(); ++i) {
                        tRecruitArmiesForProvinces.add((AI_ProvinceInfo)sortedFrontProvinces.get(i));
                        totalValues += ((AI_ProvinceInfo)sortedFrontProvinces.get((int)i)).iValue;
                    }
                    int tempMoneyPre = (int)CFG.core.getCiv(nCivID).getGold();
                    for (int i = 0; i < tRecruitArmiesForProvinces.size(); ++i) {
                        int tArmyToRecruit_PRE = (int)((float)Math.min(numOfUnitsToRecruit_MAX, tempMoneyPre / (CFG.core.getProv(((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i)).iProvinceID).getLvlOfArmoury() > 0 ? GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT - 1 : GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT)) * ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i)).iValue / totalValues);
                        boolean notEnoughRecruits = false;
                        if (((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i)).iRecruitable < tArmyToRecruit_PRE) {
                            notEnoughRecruits = true;
                        }
                        if (CFG.core.getProv(((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i)).iProvinceID).isOccupied() || notEnoughRecruits || CFG.oR.nextInt(100) < CFG.core.getCiv((int)nCivID).civGD.civPers.VALUABLE_RECRUIT_FROM_FAR_AWAY_CHANCE) {
                            List<AI_NeighProvinces> listOfPossibleProvincesToRecruit = CFG.oAI.getAllNeighboringProvincesInRange_Recruit(((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i)).iProvinceID, nCivID, 3, true, false, new ArrayList<AI_NeighProvinces>(), new ArrayList<Integer>());
                            if (listOfPossibleProvincesToRecruit.isEmpty()) continue;
                            int tempRand = 0;
                            if (notEnoughRecruits) {
                                int tBest = 0;
                                int tBestArmy = CFG.gameAction.gMARY(listOfPossibleProvincesToRecruit.get((int)tBest).iProvinceID);
                                for (int k = 1; k < listOfPossibleProvincesToRecruit.size(); ++k) {
                                    if (tBestArmy >= CFG.gameAction.gMARY(listOfPossibleProvincesToRecruit.get((int)k).iProvinceID)) continue;
                                    tBest = k;
                                    tBestArmy = CFG.gameAction.gMARY(listOfPossibleProvincesToRecruit.get((int)k).iProvinceID);
                                }
                                tempRand = tBest;
                            } else {
                                tempRand = CFG.oR.nextInt(listOfPossibleProvincesToRecruit.size());
                            }
                            int tArmyToRecruit = (int)((float)Math.min(numOfUnitsToRecruit_MAX, Math.min(CFG.gameAction.gMARY(listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID), tempMoneyPre / (CFG.core.getProv(listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID).getLvlOfArmoury() > 0 ? GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT - 1 : GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT))) * ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i)).iValue / totalValues);
                            CFG.core.getCiv(nCivID).recruitArmy_AI(listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID, tArmyToRecruit);
                            int tempArmy = CFG.core.getCiv(nCivID).getRecruitArmy_BasedOnProvinceID(listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID);
                            if (tempArmy <= 0) continue;
                            CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.add(new CivArmyMission_RegroupAfterRecruitment(nCivID, listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID, ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i)).iProvinceID, tempArmy));
                            continue;
                        }
                        int tArmyToRecruit = (int)((float)Math.min(numOfUnitsToRecruit_MAX, Math.min(CFG.gameAction.gMARY(((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i)).iProvinceID), tempMoneyPre / (CFG.core.getProv(((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i)).iProvinceID).getLvlOfArmoury() > 0 ? GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT - 1 : GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT))) * ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i)).iValue / totalValues);
                        CFG.core.getCiv(nCivID).recruitArmy_AI(((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i)).iProvinceID, tArmyToRecruit);
                    }
                }
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    public final void regroupArmy_AtPeace(int nCivID) {
        try {
            if (CFG.core.getCiv((int)nCivID).civGD.iRegroupArmyAtPeace_CheckTurnID <= GameCalendar.TURNID) {
                int tArmyToRegroup;
                int i;
                ArrayList<AI_RegoupArmyData> armiesWithoutDanger = new ArrayList<AI_RegoupArmyData>();
                ArrayList<AI_RegoupArmyData> armiesInAnotherTerritory = new ArrayList<AI_RegoupArmyData>();
                ArrayList<AI_RegoupArmyData> armiesAtSea = new ArrayList<AI_RegoupArmyData>();
                ArrayList<AI_RegoupArmyData> rest = new ArrayList<AI_RegoupArmyData>();
                int numOfUnitsToRegoup = 0;
                for (i = 0; i < CFG.core.getCiv((int)nCivID).armiesPositionSize; ++i) {
                    tArmyToRegroup = this.getRegroupArmy_NumOfUnits(nCivID, CFG.core.getCiv((int)nCivID).armiesPosition.get(i));
                    if (tArmyToRegroup <= 0) continue;
                    if (CFG.core.getProv(CFG.core.getCiv((int)nCivID).armiesPosition.get(i)).getSeaProv()) {
                        armiesAtSea.add(new AI_RegoupArmyData(CFG.core.getCiv((int)nCivID).armiesPosition.get(i), tArmyToRegroup));
                        continue;
                    }
                    if (CFG.core.getProv(CFG.core.getCiv((int)nCivID).armiesPosition.get(i)).getCivId() != nCivID) {
                        armiesInAnotherTerritory.add(new AI_RegoupArmyData(CFG.core.getCiv((int)nCivID).armiesPosition.get(i), tArmyToRegroup));
                        continue;
                    }
                    if (CFG.core.getProv(CFG.core.getCiv((int)nCivID).armiesPosition.get(i)).getDangerLvl() == 0) {
                        armiesWithoutDanger.add(new AI_RegoupArmyData(CFG.core.getCiv((int)nCivID).armiesPosition.get(i), tArmyToRegroup));
                        numOfUnitsToRegoup += tArmyToRegroup;
                        continue;
                    }
                    rest.add(new AI_RegoupArmyData(CFG.core.getCiv((int)nCivID).armiesPosition.get(i), tArmyToRegroup));
                }
                for (i = 0; i < CFG.core.getCiv(nCivID).getArmyInAnotherProvinceSize(); ++i) {
                    tArmyToRegroup = this.getRegroupArmy_NumOfUnits(nCivID, CFG.core.getCiv(nCivID).getArmyInAnotherProviP(i));
                    if (tArmyToRegroup <= 0) continue;
                    if (CFG.core.getProv(CFG.core.getCiv(nCivID).getArmyInAnotherProviP(i)).getSeaProv()) {
                        armiesAtSea.add(new AI_RegoupArmyData(CFG.core.getCiv(nCivID).getArmyInAnotherProviP(i), tArmyToRegroup));
                        continue;
                    }
                    if (CFG.core.getProv(CFG.core.getCiv(nCivID).getArmyInAnotherProviP(i)).getCivId() != nCivID) {
                        armiesInAnotherTerritory.add(new AI_RegoupArmyData(CFG.core.getCiv(nCivID).getArmyInAnotherProviP(i), tArmyToRegroup));
                        continue;
                    }
                    if (CFG.core.getProv(CFG.core.getCiv(nCivID).getArmyInAnotherProviP(i)).getDangerLvl() == 0) {
                        armiesWithoutDanger.add(new AI_RegoupArmyData(CFG.core.getCiv(nCivID).getArmyInAnotherProviP(i), tArmyToRegroup));
                        numOfUnitsToRegoup += tArmyToRegroup;
                        continue;
                    }
                    rest.add(new AI_RegoupArmyData(CFG.core.getCiv((int)nCivID).armiesPosition.get(i), tArmyToRegroup));
                }
                if (armiesWithoutDanger.size() == CFG.core.getCiv(nCivID).getNumOfProvs()) {
                    armiesWithoutDanger.clear();
                }
                while (!(armiesWithoutDanger.isEmpty() && armiesAtSea.isEmpty() && armiesInAnotherTerritory.isEmpty())) {
                    int i2;
                    int highestArmyID = -1;
                    int highestArmy_Num = 0;
                    int highestArmy_ListID = -1;
                    for (i2 = armiesWithoutDanger.size() - 1; i2 >= 0; --i2) {
                        if (highestArmyID >= 0 && highestArmy_Num >= ((AI_RegoupArmyData)armiesWithoutDanger.get((int)i2)).iArmy) continue;
                        highestArmyID = i2;
                        highestArmy_Num = ((AI_RegoupArmyData)armiesWithoutDanger.get((int)i2)).iArmy;
                        highestArmy_ListID = 0;
                    }
                    for (i2 = armiesAtSea.size() - 1; i2 >= 0; --i2) {
                        if (highestArmyID >= 0 && highestArmy_Num >= ((AI_RegoupArmyData)armiesAtSea.get((int)i2)).iArmy) continue;
                        highestArmyID = i2;
                        highestArmy_Num = ((AI_RegoupArmyData)armiesAtSea.get((int)i2)).iArmy;
                        highestArmy_ListID = 1;
                    }
                    for (i2 = armiesInAnotherTerritory.size() - 1; i2 >= 0; --i2) {
                        if (highestArmyID >= 0 && highestArmy_Num >= ((AI_RegoupArmyData)armiesInAnotherTerritory.get((int)i2)).iArmy) continue;
                        highestArmyID = i2;
                        highestArmy_Num = ((AI_RegoupArmyData)armiesInAnotherTerritory.get((int)i2)).iArmy;
                        highestArmy_ListID = 2;
                    }
                    if (GameCalendar.TURNID >= CFG.core.getCiv((int)nCivID).civGD.nextArmyRestRegroupment_TurnID) {
                        for (i2 = rest.size() - 1; i2 >= 0; --i2) {
                            if (highestArmyID >= 0 && highestArmy_Num >= ((AI_RegoupArmyData)rest.get((int)i2)).iArmy) continue;
                            highestArmyID = i2;
                            highestArmy_Num = ((AI_RegoupArmyData)rest.get((int)i2)).iArmy;
                            highestArmy_ListID = 3;
                        }
                    }
                    if (highestArmyID >= 0 && highestArmy_ListID >= 0 && highestArmy_Num > 0) {
                        switch (highestArmy_ListID) {
                            case 0: {
                                this.regroupArmy_AtPeace_InOwnTerritory_WithoutDanger(nCivID, (AI_RegoupArmyData)armiesWithoutDanger.get(highestArmyID), false);
                                armiesWithoutDanger.remove(highestArmyID);
                                break;
                            }
                            case 1: {
                                this.regroupArmy_AtPeace_AtSea(nCivID, (AI_RegoupArmyData)armiesAtSea.get(highestArmyID));
                                armiesAtSea.remove(highestArmyID);
                                break;
                            }
                            case 2: {
                                this.regroupArmy_AtPeace_InAnotherTerritory(nCivID, (AI_RegoupArmyData)armiesInAnotherTerritory.get(highestArmyID));
                                armiesInAnotherTerritory.remove(highestArmyID);
                                break;
                            }
                            case 3: {
                                this.regroupArmy_AtPeace_InOwnTerritory_WithoutDanger(nCivID, (AI_RegoupArmyData)rest.get(highestArmyID), true);
                                rest.remove(highestArmyID);
                                CFG.core.getCiv((int)nCivID).civGD.nextArmyRestRegroupment_TurnID = Math.max(CFG.core.getCiv((int)nCivID).civGD.nextArmyRestRegroupment_TurnID, GameCalendar.TURNID + 3 + CFG.oR.nextInt(9));
                            }
                        }
                    }
                    if (CFG.core.getCiv(nCivID).getMovemPoints() >= CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_MOVE && CFG.core.getCiv(nCivID).getMovemPoints() >= CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_MOVE_OWN_PROVINCE) continue;
                    break;
                }
                if (armiesWithoutDanger.size() == CFG.core.getCiv(nCivID).getNumOfProvs() || !armiesAtSea.isEmpty()) {
                    CFG.core.getCiv((int)nCivID).civGD.iRegroupArmyAtPeace_CheckTurnID = GameCalendar.TURNID + 4 + CFG.oR.nextInt(4);
                }
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
        catch (StackOverflowError ex) {
            CFG.exceptionStack(ex);
        }
    }

    public final boolean regroupArmy_AtPeace_AtSea(int nCivID, AI_RegoupArmyData nArmy) {
        int tMaxArmy;
        int i;
        ArrayList<AI_ProvinceInfo> possibleMoveTo = new ArrayList<AI_ProvinceInfo>();
        for (i = 0; i < CFG.core.getProv(nArmy.iProvinceID).getNeighProvincesSize(); ++i) {
            if (CFG.core.getProv(CFG.core.getProv(nArmy.iProvinceID).getNeighProvinces(i)).getCivId() != nCivID) continue;
            possibleMoveTo.add(new AI_ProvinceInfo(CFG.core.getProv(nArmy.iProvinceID).getNeighProvinces(i), this.getPotential_BasedOnNeighboringProvs(CFG.core.getProv(nArmy.iProvinceID).getNeighProvinces(i), nCivID), 1));
        }
        if (!possibleMoveTo.isEmpty()) {
            int tArmyToRecruit_PRE;
            int i2;
            int i3;
            tMaxArmy = 1;
            float tMaxPotential = 1.0f;
            float tMaxRegion_NumOfProvinces = 1.0f;
            float tMaxRegion_Potential = 1.0f;
            int tMaxDL = 1;
            ArrayList<Integer> tMovingArmy = new ArrayList<Integer>();
            int iSize = possibleMoveTo.size();
            int tempMovingArmy = 0;
            for (i3 = 0; i3 < iSize; ++i3) {
                if (((AI_ProvinceInfo)possibleMoveTo.get((int)i3)).iValue > tMaxPotential) {
                    tMaxPotential = ((AI_ProvinceInfo)possibleMoveTo.get((int)i3)).iValue;
                }
                if (CFG.core.getProv(((AI_ProvinceInfo)possibleMoveTo.get((int)i3)).iProvinceID).getDangerLevel_WithArmy() > tMaxDL) {
                    tMaxDL = CFG.core.getProv(((AI_ProvinceInfo)possibleMoveTo.get((int)i3)).iProvinceID).getDangerLevel_WithArmy();
                }
                if ((float)CFG.core.getProv(((AI_ProvinceInfo)possibleMoveTo.get((int)i3)).iProvinceID).getRegion_NumOfProvinces() > tMaxRegion_NumOfProvinces) {
                    tMaxRegion_NumOfProvinces = CFG.core.getProv(((AI_ProvinceInfo)possibleMoveTo.get((int)i3)).iProvinceID).getRegion_NumOfProvinces();
                }
                if ((float)CFG.core.getProv(((AI_ProvinceInfo)possibleMoveTo.get((int)i3)).iProvinceID).getPotentialRegion() > tMaxRegion_Potential) {
                    tMaxRegion_Potential = CFG.core.getProv(((AI_ProvinceInfo)possibleMoveTo.get((int)i3)).iProvinceID).getPotentialRegion();
                }
                tMovingArmy.add(tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, ((AI_ProvinceInfo)possibleMoveTo.get((int)i3)).iProvinceID));
                if (CFG.core.getProv(((AI_ProvinceInfo)possibleMoveTo.get((int)i3)).iProvinceID).getArmyID(0) + tempMovingArmy <= tMaxArmy) continue;
                tMaxArmy = CFG.core.getProv(((AI_ProvinceInfo)possibleMoveTo.get((int)i3)).iProvinceID).getArmyID(0) + tempMovingArmy;
            }
            iSize = possibleMoveTo.size();
            for (i3 = 0; i3 < iSize; ++i3) {
                ((AI_ProvinceInfo)possibleMoveTo.get((int)i3)).iValue = this.getValue_PositionOfArmy(nCivID, possibleMoveTo, i3, (Integer)tMovingArmy.get(i3), tMaxPotential, tMaxRegion_Potential, tMaxDL, tMaxArmy, nArmy.iArmy, nArmy.iArmy);
            }
            ArrayList<AI_ProvinceInfo> sortedFrontProvinces = new ArrayList<AI_ProvinceInfo>();
            while (!possibleMoveTo.isEmpty()) {
                int tBest = 0;
                int iSize2 = possibleMoveTo.size();
                for (int i4 = 1; i4 < iSize2; ++i4) {
                    if (!(((AI_ProvinceInfo)possibleMoveTo.get((int)tBest)).iValue < ((AI_ProvinceInfo)possibleMoveTo.get((int)i4)).iValue)) continue;
                    tBest = i4;
                }
                sortedFrontProvinces.add((AI_ProvinceInfo)possibleMoveTo.get(tBest));
                possibleMoveTo.remove(tBest);
            }
            float percOfArmyToRegroup = Math.max((float)nArmy.iArmy / (float)CFG.core.getCiv(nCivID).getNumberOfUnits(), 0.01f);
            int iNumOfMaxMovements = 1;
            if (GameValues.gvAiArmy.REGROUP_AT_PEACE_MAX_ONE_MOVE_IF_PERC_OF_ARMY > percOfArmyToRegroup) {
                iNumOfMaxMovements = 1;
            } else {
                iNumOfMaxMovements = Math.max(1, Math.min((CFG.core.getCiv(nCivID).getMovemPoints() - CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_MOVE) / (CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_MOVE * 2), 1 + CFG.oR.nextInt(3)));
                iNumOfMaxMovements = percOfArmyToRegroup > 0.54f ? Math.min(iNumOfMaxMovements, 4) : (percOfArmyToRegroup > 0.34f ? Math.min(iNumOfMaxMovements, 3) : (percOfArmyToRegroup > 0.14f ? Math.min(iNumOfMaxMovements, 2) : Math.min(iNumOfMaxMovements, 1)));
            }
            ArrayList<AI_ProvinceInfo> tRecruitArmiesForProvinces = new ArrayList<AI_ProvinceInfo>();
            float totalValues = 0.0f;
            for (i2 = 0; i2 < iNumOfMaxMovements && i2 < sortedFrontProvinces.size(); ++i2) {
                tRecruitArmiesForProvinces.add((AI_ProvinceInfo)sortedFrontProvinces.get(i2));
                totalValues += ((AI_ProvinceInfo)sortedFrontProvinces.get((int)i2)).iValue;
            }
            for (i2 = 0; i2 < tRecruitArmiesForProvinces.size() && (tArmyToRecruit_PRE = (int)Math.ceil((float)nArmy.iArmy * ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i2)).iValue / totalValues)) > 0; ++i2) {
                RegroupArmy_AtPeace tryRegroupArmy = new RegroupArmy_AtPeace(nCivID, nArmy.iProvinceID, ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i2)).iProvinceID);
                if (tryRegroupArmy.getRouteSize() <= 0) continue;
                if (tryRegroupArmy.getRouteSize() == 1) {
                    if (CFG.gameAction.moveArmyAction(nArmy.iProvinceID, ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i2)).iProvinceID, tArmyToRecruit_PRE, nCivID, true, false)) continue;
                    continue;
                }
                if (!CFG.gameAction.moveArmyAction(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), tArmyToRecruit_PRE, nCivID, true, false)) continue;
                tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
                tryRegroupArmy.removeRoute(0);
                tryRegroupArmy.setNumOfUnits(tArmyToRecruit_PRE);
                CFG.core.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
            }
            return true;
        }
        block7: for (i = CFG.core.getCiv(nCivID).getSeaAccess_Provinces_Size() - 1; i >= 0; --i) {
            for (int j = 0; j < CFG.core.getProv(CFG.core.getCiv(nCivID).getSeaAccessProvinces().get(i)).getNeighSeaProvincesSize(); ++j) {
                if (CFG.core.getProv(CFG.core.getProv(CFG.core.getCiv(nCivID).getSeaAccessProvinces().get(i)).getNeighSeaProvinces(j)).getBasinID() != CFG.core.getProv(nArmy.iProvinceID).getBasinID()) continue;
                possibleMoveTo.add(new AI_ProvinceInfo(CFG.core.getCiv(nCivID).getSeaAccessProvinces().get(i), this.getPotential_BasedOnNeighboringProvs(CFG.core.getCiv(nCivID).getSeaAccessProvinces().get(i), nCivID), 1));
                continue block7;
            }
        }
        if (!possibleMoveTo.isEmpty()) {
            RegroupArmy_AtPeace tryRegroupArmy;
            int i5;
            tMaxArmy = 1;
            float tMaxPotential = 1.0f;
            float tMaxRegion_NumOfProvinces = 1.0f;
            float tMaxRegion_Potential = 1.0f;
            int tMaxDL = 1;
            ArrayList<Integer> tMovingArmy = new ArrayList<Integer>();
            int iSize = possibleMoveTo.size();
            int tempMovingArmy = 0;
            for (i5 = 0; i5 < iSize; ++i5) {
                if (((AI_ProvinceInfo)possibleMoveTo.get((int)i5)).iValue > tMaxPotential) {
                    tMaxPotential = ((AI_ProvinceInfo)possibleMoveTo.get((int)i5)).iValue;
                }
                if (CFG.core.getProv(((AI_ProvinceInfo)possibleMoveTo.get((int)i5)).iProvinceID).getDangerLevel_WithArmy() > tMaxDL) {
                    tMaxDL = CFG.core.getProv(((AI_ProvinceInfo)possibleMoveTo.get((int)i5)).iProvinceID).getDangerLevel_WithArmy();
                }
                if ((float)CFG.core.getProv(((AI_ProvinceInfo)possibleMoveTo.get((int)i5)).iProvinceID).getRegion_NumOfProvinces() > tMaxRegion_NumOfProvinces) {
                    tMaxRegion_NumOfProvinces = CFG.core.getProv(((AI_ProvinceInfo)possibleMoveTo.get((int)i5)).iProvinceID).getRegion_NumOfProvinces();
                }
                if ((float)CFG.core.getProv(((AI_ProvinceInfo)possibleMoveTo.get((int)i5)).iProvinceID).getPotentialRegion() > tMaxRegion_Potential) {
                    tMaxRegion_Potential = CFG.core.getProv(((AI_ProvinceInfo)possibleMoveTo.get((int)i5)).iProvinceID).getPotentialRegion();
                }
                tMovingArmy.add(tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, ((AI_ProvinceInfo)possibleMoveTo.get((int)i5)).iProvinceID));
                if (CFG.core.getProv(((AI_ProvinceInfo)possibleMoveTo.get((int)i5)).iProvinceID).getArmyID(0) + tempMovingArmy <= tMaxArmy) continue;
                tMaxArmy = CFG.core.getProv(((AI_ProvinceInfo)possibleMoveTo.get((int)i5)).iProvinceID).getArmyID(0) + tempMovingArmy;
            }
            iSize = possibleMoveTo.size();
            for (i5 = 0; i5 < iSize; ++i5) {
                ((AI_ProvinceInfo)possibleMoveTo.get((int)i5)).iValue = this.getValue_PositionOfArmy(nCivID, possibleMoveTo, i5, (Integer)tMovingArmy.get(i5), tMaxPotential, tMaxRegion_Potential, tMaxDL, tMaxArmy, nArmy.iArmy, nArmy.iArmy);
            }
            ArrayList<AI_ProvinceInfo> sortedFrontProvinces = new ArrayList<AI_ProvinceInfo>();
            if (!possibleMoveTo.isEmpty()) {
                int tBest = 0;
                int iSize3 = possibleMoveTo.size();
                for (int i6 = 1; i6 < iSize3; ++i6) {
                    if (!(((AI_ProvinceInfo)possibleMoveTo.get((int)tBest)).iValue < ((AI_ProvinceInfo)possibleMoveTo.get((int)i6)).iValue)) continue;
                    tBest = i6;
                }
                sortedFrontProvinces.add((AI_ProvinceInfo)possibleMoveTo.get(tBest));
                possibleMoveTo.remove(tBest);
            }
            if ((tryRegroupArmy = new RegroupArmy_AtPeace(nCivID, nArmy.iProvinceID, ((AI_ProvinceInfo)sortedFrontProvinces.get((int)0)).iProvinceID)).getRouteSize() > 0) {
                if (tryRegroupArmy.getRouteSize() == 1) {
                    if (!CFG.gameAction.moveArmyAction(nArmy.iProvinceID, ((AI_ProvinceInfo)sortedFrontProvinces.get((int)0)).iProvinceID, nArmy.iArmy, nCivID, true, false)) {
                        // empty if block
                    }
                } else if (CFG.gameAction.moveArmyAction(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), nArmy.iArmy, nCivID, true, false)) {
                    tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
                    tryRegroupArmy.removeRoute(0);
                    tryRegroupArmy.setNumOfUnits(nArmy.iArmy);
                    CFG.core.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
                }
            }
            return true;
        }
        if (CFG.core.getCiv(nCivID).getCapitalProvID() >= 0 && CFG.core.getProv(CFG.core.getCiv(nCivID).getCapitalProvID()).getCivId() == nCivID) {
            RegroupArmy_AtPeace tryRegroupArmy = new RegroupArmy_AtPeace(nCivID, nArmy.iProvinceID, CFG.core.getCiv(nCivID).getCapitalProvID());
            if (tryRegroupArmy.getRouteSize() > 0) {
                if (tryRegroupArmy.getRouteSize() == 1) {
                    if (!CFG.gameAction.moveArmyAction(nArmy.iProvinceID, CFG.core.getCiv(nCivID).getCapitalProvID(), nArmy.iArmy, nCivID, true, false)) {
                        // empty if block
                    }
                } else if (CFG.gameAction.moveArmyAction(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), nArmy.iArmy, nCivID, true, false)) {
                    tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
                    tryRegroupArmy.removeRoute(0);
                    tryRegroupArmy.setNumOfUnits(nArmy.iArmy);
                    CFG.core.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
                } else {
                    CFG.gameAction.disbandArmy(nArmy.iProvinceID, nArmy.iArmy, nCivID);
                }
            } else {
                CFG.gameAction.disbandArmy(nArmy.iProvinceID, nArmy.iArmy, nCivID);
            }
        } else {
            CFG.gameAction.disbandArmy(nArmy.iProvinceID, nArmy.iArmy, nCivID);
        }
        return true;
    }

    public final boolean regroupArmy_AtPeace_InAnotherTerritory(int nCivID, AI_RegoupArmyData nArmy) {
        try {
            float percOfArmyToRegroup = Math.max((float)nArmy.iArmy / (float)CFG.core.getCiv(nCivID).getNumberOfUnits(), 0.01f);
            List<AI_NeighProvinces> listOfPossibleProvinces = CFG.oAI.getAllNeighboringProvincesInRange_OnlyOwn_Clear(nArmy.iProvinceID, nCivID, CFG.core.getCiv((int)nCivID).civGD.civPers.REGROUP_AT_PEACE_MAX_PROVINCES + CFG.core.getCiv(nCivID).getNumOfProvs() / 15, false, false, new ArrayList<AI_NeighProvinces>(), new ArrayList<Integer>());
            if (!listOfPossibleProvinces.isEmpty()) {
                int i;
                int nNumOfPossibleMovements = CFG.core.getCiv(nCivID).getMovemPoints() / CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_MOVE;
                nNumOfPossibleMovements = percOfArmyToRegroup > 0.275f ? Math.min(nNumOfPossibleMovements, 2) : Math.min(nNumOfPossibleMovements, 1);
                ArrayList<Integer> tSortedIDs = new ArrayList<Integer>();
                ArrayList<Integer> tData = new ArrayList<Integer>();
                for (int i2 = listOfPossibleProvinces.size() - 1; i2 >= 0; --i2) {
                    tData.add(i2);
                }
                while (!tData.isEmpty()) {
                    int tBest = 0;
                    for (i = tData.size() - 1; i > 0; --i) {
                        if (CFG.core.getProv(listOfPossibleProvinces.get((int)((Integer)tData.get((int)tBest)).intValue()).iProvinceID).getPotential() >= CFG.core.getProv(listOfPossibleProvinces.get((int)((Integer)tData.get((int)i)).intValue()).iProvinceID).getPotential()) continue;
                        tBest = i;
                    }
                    tSortedIDs.add((Integer)tData.get(tBest));
                    tData.remove(tBest);
                }
                int nDangerTotal = 0;
                for (i = 0; i < nNumOfPossibleMovements && i < tSortedIDs.size(); ++i) {
                    nDangerTotal += CFG.core.getProv(listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)i)).intValue()).iProvinceID).getPotential();
                }
                int tIDOfFisrttSuccesfulMovement = -1;
                for (int i3 = 0; i3 < nNumOfPossibleMovements && i3 < tSortedIDs.size() && nArmy.iArmy > 0; ++i3) {
                    RegroupArmy_AtPeace tryRegroupArmy = new RegroupArmy_AtPeace(nCivID, nArmy.iProvinceID, listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)i3)).intValue()).iProvinceID);
                    if (tryRegroupArmy.getRouteSize() > 0) {
                        int tArmyToMove = i3 == nNumOfPossibleMovements || i3 == tSortedIDs.size() - 1 ? nArmy.iArmy : (int)Math.ceil((float)nArmy.iArmy * ((float)CFG.core.getProv(listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)i3)).intValue()).iProvinceID).getPotential() / (float)nDangerTotal));
                        nArmy.iArmy -= tArmyToMove;
                        if (tArmyToMove <= 0) break;
                        if (!CFG.gameAction.moveArmyAction(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), tArmyToMove, nCivID, true, false)) continue;
                        if (tryRegroupArmy.getRouteSize() > 1) {
                            CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.add(new CivArmyMission_RegroupAfterRecruitment(nCivID, tryRegroupArmy.getRoute(0), listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)i3)).intValue()).iProvinceID, tArmyToMove));
                        }
                        tIDOfFisrttSuccesfulMovement = i3;
                        continue;
                    }
                    if (tIDOfFisrttSuccesfulMovement < 0 || (tryRegroupArmy = new RegroupArmy_AtPeace(nCivID, nArmy.iProvinceID, listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)tIDOfFisrttSuccesfulMovement)).intValue()).iProvinceID)).getRouteSize() <= 0 || !CFG.gameAction.moveArmyAction(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), nArmy.iArmy, nCivID, true, false)) continue;
                    if (tryRegroupArmy.getRouteSize() > 1) {
                        CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.add(new CivArmyMission_RegroupAfterRecruitment(nCivID, tryRegroupArmy.getRoute(0), listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)tIDOfFisrttSuccesfulMovement)).intValue()).iProvinceID, nArmy.iArmy));
                    }
                    return true;
                }
                if (tIDOfFisrttSuccesfulMovement >= 0) {
                    return true;
                }
            } else if (CFG.core.getCiv(nCivID).getCapitalProvID() >= 0 && CFG.core.getProv(CFG.core.getCiv(nCivID).getCapitalProvID()).getCivId() == nCivID) {
                RegroupArmy_AtPeace tryRegroupArmy = new RegroupArmy_AtPeace(nCivID, nArmy.iProvinceID, CFG.core.getCiv(nCivID).getCapitalProvID());
                if (tryRegroupArmy.getRouteSize() > 0) {
                    if (tryRegroupArmy.getRouteSize() == 1) {
                        if (!CFG.gameAction.moveArmyAction(nArmy.iProvinceID, CFG.core.getCiv(nCivID).getCapitalProvID(), nArmy.iArmy, nCivID, true, false)) {
                            // empty if block
                        }
                    } else if (CFG.gameAction.moveArmyAction(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), nArmy.iArmy, nCivID, true, false)) {
                        tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
                        tryRegroupArmy.removeRoute(0);
                        tryRegroupArmy.setNumOfUnits(nArmy.iArmy);
                        CFG.core.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
                    }
                } else {
                    CFG.gameAction.disbandArmy(nArmy.iProvinceID, nArmy.iArmy, nCivID);
                }
            } else {
                CFG.gameAction.disbandArmy(nArmy.iProvinceID, nArmy.iArmy, nCivID);
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
        catch (StackOverflowError ex) {
            CFG.exceptionStack(ex);
        }
        return false;
    }

    public final boolean regroupArmy_AtPeace_InOwnTerritory_WithoutDanger(int nCivID, AI_RegoupArmyData nArmy, boolean rebuildLine) {
        try {
            int i;
            float percOfArmyToRegroup = Math.max((float)nArmy.iArmy / (float)CFG.core.getCiv(nCivID).getNumberOfUnits(), 0.01f);
            try {
                if (CFG.core.getCiv(nCivID).getCivRegion(CFG.core.getProv(nArmy.iProvinceID).getCivRegionID()).getProvincesSize() > 1) {
                    int tMaxDL = 1;
                    float tMaxPotential = 1.0f;
                    ArrayList<AI_ProvinceInfo> tempFrontProvinces = new ArrayList<AI_ProvinceInfo>();
                    for (i = CFG.core.getCiv((int)nCivID).lFrontLines.size() - 1; i >= 0; --i) {
                        try {
                            if (CFG.core.getProv(CFG.core.getCiv((int)nCivID).lFrontLines.get((int)i).lProvinces.get(0)).getCivRegionID() != CFG.core.getProv(nArmy.iProvinceID).getCivRegionID()) continue;
                            for (int j = CFG.core.getCiv((int)nCivID).lFrontLines.get((int)i).lProvinces.size() - 1; j >= 0; --j) {
                                boolean wasAdded = false;
                                for (int k = tempFrontProvinces.size() - 1; k >= 0; --k) {
                                    if (((AI_ProvinceInfo)tempFrontProvinces.get((int)k)).iProvinceID != CFG.core.getCiv((int)nCivID).lFrontLines.get((int)i).lProvinces.get(j)) continue;
                                    wasAdded = true;
                                    break;
                                }
                                if (wasAdded) continue;
                                tempFrontProvinces.add(new AI_ProvinceInfo(CFG.core.getCiv((int)nCivID).lFrontLines.get((int)i).lProvinces.get(j), this.getPotential_BasedOnNeighboringProvs(CFG.core.getCiv((int)nCivID).lFrontLines.get((int)i).lProvinces.get(j), nCivID), 1));
                            }
                            continue;
                        }
                        catch (Exception j) {
                            // empty catch block
                        }
                    }
                    if (!tempFrontProvinces.isEmpty()) {
                        int tArmyToRecruit_PRE;
                        int i2;
                        int i3;
                        int tMaxArmy = 1;
                        float tMaxRegion_NumOfProvinces = 1.0f;
                        float tMaxRegion_Potential = 1.0f;
                        ArrayList<Integer> tMovingArmy = new ArrayList<Integer>();
                        int iSize = tempFrontProvinces.size();
                        int tempMovingArmy = 0;
                        for (i3 = 0; i3 < iSize; ++i3) {
                            if (((AI_ProvinceInfo)tempFrontProvinces.get((int)i3)).iValue > tMaxPotential) {
                                tMaxPotential = ((AI_ProvinceInfo)tempFrontProvinces.get((int)i3)).iValue;
                            }
                            if (CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i3)).iProvinceID).getDangerLevel_WithArmy() > tMaxDL) {
                                tMaxDL = CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i3)).iProvinceID).getDangerLevel_WithArmy();
                            }
                            if ((float)CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i3)).iProvinceID).getRegion_NumOfProvinces() > tMaxRegion_NumOfProvinces) {
                                tMaxRegion_NumOfProvinces = CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i3)).iProvinceID).getRegion_NumOfProvinces();
                            }
                            if ((float)CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i3)).iProvinceID).getPotentialRegion() > tMaxRegion_Potential) {
                                tMaxRegion_Potential = CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i3)).iProvinceID).getPotentialRegion();
                            }
                            tMovingArmy.add(tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, ((AI_ProvinceInfo)tempFrontProvinces.get((int)i3)).iProvinceID));
                            if (CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i3)).iProvinceID).getArmyID(0) + tempMovingArmy <= tMaxArmy) continue;
                            tMaxArmy = CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i3)).iProvinceID).getArmyID(0) + tempMovingArmy;
                        }
                        iSize = tempFrontProvinces.size();
                        for (i3 = 0; i3 < iSize; ++i3) {
                            ((AI_ProvinceInfo)tempFrontProvinces.get((int)i3)).iValue = this.getValue_PositionOfArmy(nCivID, tempFrontProvinces, i3, (Integer)tMovingArmy.get(i3), tMaxPotential, tMaxRegion_Potential, tMaxDL, tMaxArmy, nArmy.iArmy, tMaxRegion_NumOfProvinces);
                        }
                        ArrayList<AI_ProvinceInfo> sortedFrontProvinces = new ArrayList<AI_ProvinceInfo>();
                        while (!tempFrontProvinces.isEmpty()) {
                            int tBest = 0;
                            int iSize2 = tempFrontProvinces.size();
                            for (int i4 = 1; i4 < iSize2; ++i4) {
                                if (!(((AI_ProvinceInfo)tempFrontProvinces.get((int)tBest)).iValue < ((AI_ProvinceInfo)tempFrontProvinces.get((int)i4)).iValue)) continue;
                                tBest = i4;
                            }
                            sortedFrontProvinces.add((AI_ProvinceInfo)tempFrontProvinces.get(tBest));
                            tempFrontProvinces.remove(tBest);
                        }
                        int iNumOfMaxMovements = 1;
                        iNumOfMaxMovements = rebuildLine ? Math.max(1, Math.min((CFG.core.getCiv(nCivID).getMovemPoints() - CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_MOVE) / CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_MOVE_OWN_PROVINCE, Math.min(CFG.core.getCiv(nCivID).getNumOfProvs(), 2 + CFG.oR.nextInt(3)))) : (GameValues.gvAiArmy.REGROUP_AT_PEACE_MAX_ONE_MOVE_IF_PERC_OF_ARMY > percOfArmyToRegroup ? 1 : Math.max(1, Math.min((CFG.core.getCiv(nCivID).getMovemPoints() - CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_MOVE) / CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_MOVE_OWN_PROVINCE, Math.min(CFG.core.getCiv(nCivID).getNumOfProvs(), 2 + CFG.oR.nextInt(3)))));
                        ArrayList<AI_ProvinceInfo> tRecruitArmiesForProvinces = new ArrayList<AI_ProvinceInfo>();
                        float totalValues = 0.0f;
                        for (i2 = 0; i2 < iNumOfMaxMovements && i2 < sortedFrontProvinces.size(); ++i2) {
                            tRecruitArmiesForProvinces.add((AI_ProvinceInfo)sortedFrontProvinces.get(i2));
                            totalValues += ((AI_ProvinceInfo)sortedFrontProvinces.get((int)i2)).iValue;
                        }
                        for (i2 = 0; i2 < tRecruitArmiesForProvinces.size() && (tArmyToRecruit_PRE = (int)Math.ceil((float)nArmy.iArmy * ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i2)).iValue / totalValues)) > 0; ++i2) {
                            RegroupArmy_AtPeace tryRegroupArmy = new RegroupArmy_AtPeace(nCivID, nArmy.iProvinceID, ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i2)).iProvinceID);
                            if (tryRegroupArmy.getRouteSize() <= 0) continue;
                            if (tryRegroupArmy.getRouteSize() == 1) {
                                if (CFG.gameAction.moveArmyAction(nArmy.iProvinceID, ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i2)).iProvinceID, tArmyToRecruit_PRE, nCivID, true, false)) continue;
                                continue;
                            }
                            if (!CFG.gameAction.moveArmyAction(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), tArmyToRecruit_PRE, nCivID, true, false)) continue;
                            tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
                            tryRegroupArmy.removeRoute(0);
                            tryRegroupArmy.setNumOfUnits(tArmyToRecruit_PRE);
                            CFG.core.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
                        }
                        return true;
                    }
                }
            }
            catch (NullPointerException tMaxDL) {
                // empty catch block
            }
            List<AI_NeighProvinces> listOfPossibleProvinces = CFG.oAI.getAllNeighboringProvincesInRange_OnlyOwn_Clear(nArmy.iProvinceID, nCivID, Math.max(CFG.core.getCiv((int)nCivID).civGD.civPers.REGROUP_AT_PEACE_MAX_PROVINCES, CFG.core.getCiv(nCivID).getNumOfProvs() / 10), false, false, new ArrayList<AI_NeighProvinces>(), new ArrayList<Integer>());
            if (!listOfPossibleProvinces.isEmpty()) {
                int nNumOfPossibleMovements = CFG.core.getCiv(nCivID).getMovemPoints() / CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_MOVE;
                nNumOfPossibleMovements = percOfArmyToRegroup > 0.375f ? Math.min(nNumOfPossibleMovements, 4) : (percOfArmyToRegroup > 0.25f ? Math.min(nNumOfPossibleMovements, 3) : (percOfArmyToRegroup > 0.1f ? Math.min(nNumOfPossibleMovements, 2) : Math.min(nNumOfPossibleMovements, 1)));
                boolean provincesWithDanger = false;
                for (i = listOfPossibleProvinces.size() - 1; i >= 0; --i) {
                    if (CFG.core.getProv(listOfPossibleProvinces.get((int)i).iProvinceID).getDangerLvl() <= 0) continue;
                    provincesWithDanger = true;
                    break;
                }
                if (provincesWithDanger) {
                    int i5;
                    ArrayList<Integer> tSortedIDs = new ArrayList<Integer>();
                    ArrayList<Integer> tData = new ArrayList<Integer>();
                    for (int i6 = listOfPossibleProvinces.size() - 1; i6 >= 0; --i6) {
                        tData.add(i6);
                    }
                    while (!tData.isEmpty()) {
                        int tBest = 0;
                        for (i5 = tData.size() - 1; i5 > 0; --i5) {
                            if (CFG.core.getProv(listOfPossibleProvinces.get((int)((Integer)tData.get((int)tBest)).intValue()).iProvinceID).getDangerLevel_WithArmy() >= CFG.core.getProv(listOfPossibleProvinces.get((int)((Integer)tData.get((int)i5)).intValue()).iProvinceID).getDangerLevel_WithArmy()) continue;
                            tBest = i5;
                        }
                        tSortedIDs.add((Integer)tData.get(tBest));
                        tData.remove(tBest);
                    }
                    int nDangerTotal = 0;
                    for (i5 = 0; i5 < nNumOfPossibleMovements && i5 < tSortedIDs.size(); ++i5) {
                        nDangerTotal += CFG.core.getProv(listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)i5)).intValue()).iProvinceID).getDangerLevel_WithArmy();
                    }
                    int tIDOfFisrttSuccesfulMovement = -1;
                    for (int i7 = 0; i7 < nNumOfPossibleMovements && i7 < tSortedIDs.size() && nArmy.iArmy > 0; ++i7) {
                        RegroupArmy_AtPeace tryRegroupArmy = new RegroupArmy_AtPeace(nCivID, nArmy.iProvinceID, listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)i7)).intValue()).iProvinceID);
                        if (tryRegroupArmy.getRouteSize() > 0) {
                            int tArmyToMove = i7 == nNumOfPossibleMovements || i7 == tSortedIDs.size() - 1 ? nArmy.iArmy : (int)Math.ceil((float)nArmy.iArmy * ((float)CFG.core.getProv(listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)i7)).intValue()).iProvinceID).getDangerLevel_WithArmy() / (float)nDangerTotal));
                            nArmy.iArmy -= tArmyToMove;
                            if (tArmyToMove <= 0) break;
                            if (!CFG.gameAction.moveArmyAction(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), tArmyToMove, nCivID, true, false)) continue;
                            if (tryRegroupArmy.getRouteSize() > 1) {
                                CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.add(new CivArmyMission_RegroupAfterRecruitment(nCivID, tryRegroupArmy.getRoute(0), listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)i7)).intValue()).iProvinceID, tArmyToMove));
                            }
                            tIDOfFisrttSuccesfulMovement = i7;
                            continue;
                        }
                        if (tIDOfFisrttSuccesfulMovement < 0 || (tryRegroupArmy = new RegroupArmy_AtPeace(nCivID, nArmy.iProvinceID, listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)tIDOfFisrttSuccesfulMovement)).intValue()).iProvinceID)).getRouteSize() <= 0 || !CFG.gameAction.moveArmyAction(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), nArmy.iArmy, nCivID, true, false)) continue;
                        if (tryRegroupArmy.getRouteSize() > 1) {
                            CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.add(new CivArmyMission_RegroupAfterRecruitment(nCivID, tryRegroupArmy.getRoute(0), listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)tIDOfFisrttSuccesfulMovement)).intValue()).iProvinceID, nArmy.iArmy));
                        }
                        return true;
                    }
                    if (tIDOfFisrttSuccesfulMovement >= 0) {
                        return true;
                    }
                } else if (CFG.core.getProv(nArmy.iProvinceID).getCivId() != nCivID) {
                    CFG.gameAction.disbandArmy(nArmy.iProvinceID, nArmy.iArmy, nCivID);
                } else if (!CFG.core.getCiv((int)nCivID).getCivRegion((int)CFG.core.getProv((int)nArmy.iProvinceID).getCivRegionID()).isKeyRegion) {
                    CFG.gameAction.disbandArmy(nArmy.iProvinceID, nArmy.iArmy, nCivID);
                }
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
        catch (StackOverflowError ex) {
            CFG.exceptionStack(ex);
        }
        return false;
    }

    public final int getRegroupArmy_NumOfUnits(int nCivID, int nProvinceID) {
        int out = CFG.core.getProv(nProvinceID).getArmyCivID1(nCivID);
        for (int k = CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.size() - 1; k >= 0; --k) {
            if (CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.get((int)k).iProvinceID != nProvinceID) continue;
            out -= CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.get((int)k).iArmy;
        }
        return out;
    }

    public final float getValue_PositionOfArmy(int nCivID, List<AI_ProvinceInfo> tempFrontProvinces, int i, int tMovingArmy, float tMaxPotential, float tMaxRegion_Potential, int tMaxDL, int tMaxArmy, int numOfUnitsToRecruit_MAX, float tMaxRegion_NumOfProvinces) {
        return CFG.core.getCiv((int)nCivID).civGD.civPers.VALUABLE_POTENTIAL * (tempFrontProvinces.get((int)i).iValue / tMaxPotential) + CFG.core.getCiv((int)nCivID).civGD.civPers.VALUABLE_DANGER * ((float)CFG.core.getProv(tempFrontProvinces.get((int)i).iProvinceID).getDangerLevel_WithArmy() / (float)tMaxDL) * (1.0f - CFG.core.getCiv((int)nCivID).civGD.civPers.VALUABLE_NUM_OF_UNITS + CFG.core.getCiv((int)nCivID).civGD.civPers.VALUABLE_NUM_OF_UNITS * (1.0f - (float)(CFG.core.getProv(tempFrontProvinces.get((int)i).iProvinceID).getArmyID(0) + tMovingArmy) / ((float)tMaxArmy + (float)numOfUnitsToRecruit_MAX * CFG.core.getCiv((int)nCivID).civGD.civPers.VALUABLE_NUM_OF_UNITS_RECRUITMENT))) * (1.0f - CFG.core.getCiv((int)nCivID).civGD.civPers.VALUABLE_REGION_NUM_OF_PROVINCES + CFG.core.getCiv((int)nCivID).civGD.civPers.VALUABLE_REGION_NUM_OF_PROVINCES * (float)CFG.core.getProv(tempFrontProvinces.get((int)i).iProvinceID).getRegion_NumOfProvinces() / tMaxRegion_NumOfProvinces - CFG.core.getCiv((int)nCivID).civGD.civPers.VALUABLE_REGION_POTENTIAL + CFG.core.getCiv((int)nCivID).civGD.civPers.VALUABLE_REGION_POTENTIAL * (float)CFG.core.getProv(tempFrontProvinces.get((int)i).iProvinceID).getPotentialRegion() / tMaxRegion_Potential);
    }

    public final void nukeDropBomb(int civID) {
        if (CFG.core.getCiv((int)civID).civGD.iNukes > 0) {
            try {
                ArrayList<Integer> provinces = new ArrayList<Integer>();
                ArrayList<Float> score = new ArrayList<Float>();
                for (int i = CFG.core.getCiv((int)civID).isAtWarWithCivs.size() - 1; i >= 0; --i) {
                    Civilization atWarCivID = CFG.core.getCiv(CFG.core.getCiv((int)civID).isAtWarWithCivs.get(i));
                    if (CFG.core.getCiv(civID).getNumOfProvs() >= GameValues.gvAiNuke.NUKE_OR_IF_NUM_OF_PROVINCES_BELOW && !((float)atWarCivID.getNumOfProvs() / (float)CFG.core.getCiv(civID).getNumOfProvs() > GameValues.gvAiNuke.NUKE_ONLY_IF_PROVINCE_RATIO_OVER)) continue;
                    for (int j = 0; j < atWarCivID.getNumOfProvs(); ++j) {
                        Province province = CFG.core.getProv(atWarCivID.getProvID(j));
                        provinces.add(province.getProvID());
                        score.add(Float.valueOf((float)province.getPop().getPops() * GameValues.gvAiNuke.NUKE_SCORE_POPULATION_MODIFIER + (float)province.getEco() * GameValues.gvAiNuke.NUKE_SCORE_ECONOMY_MODIFIER));
                    }
                }
                while (!provinces.isEmpty() && CFG.core.getCiv((int)civID).civGD.iNukes > 0) {
                    int bestID = 0;
                    for (int a = provinces.size() - 1; a > 0; --a) {
                        if (!(((Float)score.get(bestID)).floatValue() < ((Float)score.get(a)).floatValue())) continue;
                        bestID = a;
                    }
                    NukeManager.dropNuke(civID, (Integer)provinces.get(bestID));
                    provinces.remove(bestID);
                    score.remove(bestID);
                }
            }
            catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
    }

    public final void takeLoanAtWar(int civID) {
        try {
            if (CFG.core.getCiv(civID).getLoansSize() < GameValues.gvLoan.LOAN_MAX_NUM_OF_LOANS && ((float)CFG.core.getCiv(civID).getGold() < (float)Loans.takeLoan_MaxValue(civID) * GameValues.gvAiLoan.LOW_MONEY_RELATIVE_TO_LOAN_MULTIPLIER || CFG.core.getCiv(civID).getGold() < (long)GameValues.gvAiLoan.LOW_MONEY_THRESHOLD)) {
                for (int i = CFG.core.getCiv((int)civID).isAtWarWithCivs.size() - 1; i >= 0; --i) {
                    if (!((float)CFG.core.getCiv(CFG.core.getCiv((int)civID).isAtWarWithCivs.get(i)).getNumberOfUnits() > (float)CFG.core.getCiv(civID).getNumberOfUnits() * GameValues.gvAiLoan.ENEMY_ARMY_MODIFIER)) continue;
                    Loans.takeLoan(civID, Loans.takeLoan_MaxValue(civID), GameValues.gvLoan.LOAN_MIN_DURATION + CFG.oR.nextInt(Math.max(1, GameValues.gvLoan.LOAN_MAX_DURATION - GameValues.gvLoan.LOAN_MIN_DURATION)));
                }
            }
            if (CFG.core.getCiv(civID).getLoansFromCivSize() < GameValues.gvLoan.REQUEST_LOAN_MAX_NUM_OF_LOANS && ((float)CFG.core.getCiv(civID).getGold() < (float)Loans.takeLoan_MaxValue(civID) * GameValues.gvAiLoan.LOW_MONEY_RELATIVE_TO_LOAN_MULTIPLIER || CFG.core.getCiv(civID).getGold() < (long)GameValues.gvAiLoan.LOW_MONEY_THRESHOLD)) {
                ArrayList<Integer> possibleCivs = new ArrayList<Integer>();
                for (int i = CFG.core.getCiv((int)civID).civsInRange.size() - 1; i >= 0; --i) {
                    if (!(CFG.core.getCiv(CFG.core.getCiv((int)civID).civsInRange.get((int)i).iCivID).getRelationD(civID) > (float)GameValues.gvLoan.REQUEST_LOAN_REQUIRED_RELATION)) continue;
                    possibleCivs.add(CFG.core.getCiv((int)civID).civsInRange.get((int)i).iCivID);
                }
                if (!possibleCivs.isEmpty()) {
                    for (int a = CFG.core.getCiv(civID).getLoansFromCivSize(); a < GameValues.gvLoan.REQUEST_LOAN_MAX_NUM_OF_LOANS; ++a) {
                        int randID = CFG.oR.nextInt(possibleCivs.size());
                        GameManager.sendLoanRequest((Integer)possibleCivs.get(randID), civID, Loans.takeLoan_MaxValue(civID), GameValues.gvLoan.REQUEST_LOAN_MIN_DURATION + CFG.oR.nextInt(Math.max(1, GameValues.gvLoan.REQUEST_LOAN_MAX_DURATION - GameValues.gvLoan.REQUEST_LOAN_MIN_DURATION)));
                    }
                }
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    public final void regroupArmyAfterRecruitment(int nCivID) {
        for (int k = CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.size() - 1; k >= 0; --k) {
            if (CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.get((int)k).MISSION_TYPE != CivArmyMission_Type.REGRUOP_AFTER_RECRUIT || !CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.get(k).canMakeAction(nCivID, 0) || !CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.get(k).action(nCivID)) continue;
            CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.remove(k);
        }
    }

    public final void defendFromSeaInvasion(int nCivID) {
        int j;
        ArrayList<Integer> provincesToDefend = new ArrayList<Integer>();
        ArrayList<Integer> toArmies = new ArrayList<Integer>();
        for (int i = CFG.core.getCiv((int)nCivID).isAtWarWithCivs.size() - 1; i >= 0; --i) {
            for (j = 0; j < CFG.core.getCiv(CFG.core.getCiv((int)nCivID).isAtWarWithCivs.get(i)).moveUnitsSize(); ++j) {
                if (CFG.core.getProv(CFG.core.getCiv(CFG.core.getCiv((int)nCivID).isAtWarWithCivs.get(i)).getMoveUnits(j).getToProvID()).getCivId() != nCivID || !CFG.core.getProv(CFG.core.getCiv(CFG.core.getCiv((int)nCivID).isAtWarWithCivs.get(i)).getMoveUnits(j).getFromProviID()).getSeaProv() || CFG.core.getProv(CFG.core.getCiv(CFG.core.getCiv((int)nCivID).isAtWarWithCivs.get(i)).getMoveUnits(j).getToProvID()).isOccupied()) continue;
                provincesToDefend.add(CFG.core.getCiv(CFG.core.getCiv((int)nCivID).isAtWarWithCivs.get(i)).getMoveUnits(j).getToProvID());
                toArmies.add(CFG.core.getCiv(CFG.core.getCiv((int)nCivID).isAtWarWithCivs.get(i)).getMoveUnits(j).getNumberOfUnits());
            }
        }
        while (!provincesToDefend.isEmpty()) {
            int tBest = 0;
            for (j = 1; j < provincesToDefend.size(); ++j) {
                if (CFG.core.getProv((Integer)provincesToDefend.get(tBest)).getPotential() >= CFG.core.getProv((Integer)provincesToDefend.get(j)).getPotential()) continue;
                tBest = j;
            }
            if (CFG.core.getProv((Integer)provincesToDefend.get(tBest)).getArmyCivID1(nCivID) < (Integer)toArmies.get(tBest)) {
                int requiredArmy = (Integer)toArmies.get(tBest) - CFG.core.getProv((Integer)provincesToDefend.get(tBest)).getArmyCivID1(nCivID);
                requiredArmy = (int)Math.ceil((float)requiredArmy * (GameValues.gvAiArmy.DEFEND_FROM_SEA_INVASION_REQUIRED_ARMY_MODIFIER + (float)CFG.oR.nextInt(GameValues.gvAiArmy.DEFEND_FROM_SEA_INVASION_REQUIRED_ARMY_MODIFIER_RANDOM_1000) / 1000.0f));
                if (CFG.core.getCiv(nCivID).getMovemPoints() >= CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_RECRUIT) {
                    if (CFG.core.getCiv(nCivID).getGold() < (long)(GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT * requiredArmy) && CFG.core.getCiv(nCivID).getMovemPoints() >= CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_RECRUIT + GameValues.gvLoan.COST_TAKE_LOAN) {
                        int toTake = (int)((long)(GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT * requiredArmy) - CFG.core.getCiv(nCivID).getGold());
                        if (CFG.core.getCiv(nCivID).getGold() + (long)toTake > (long)GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT) {
                            Loans.takeLoan(nCivID, toTake, GameValues.gvLoan.LOAN_MIN_DURATION);
                        }
                    }
                    if (CFG.core.getCiv(nCivID).getGold() <= (long)GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT || CFG.core.getCiv(nCivID).recruitArmy_AI((Integer)provincesToDefend.get(tBest), requiredArmy)) {
                        // empty if block
                    }
                }
            }
            provincesToDefend.remove(tBest);
            toArmies.remove(tBest);
            if (CFG.core.getCiv(nCivID).getMovemPoints() >= CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_MOVE) continue;
            break;
        }
    }

    public final void moveAtWar(int nCivID) {
        Civilization civ = CFG.core.getCiv(nCivID);
        try {
            block97: {
                int o;
                block98: {
                    int numOfPossibleMoves;
                    ArrayList<AI_ProvinceInfo_War> sortedFrontProvinces;
                    ArrayList<Integer> lFrontIDsWithArmies;
                    block99: {
                        block101: {
                            block100: {
                                boolean canRecruitAndMove;
                                int i;
                                boolean add;
                                ArrayList<AI_ProvinceInfo_War> tempFrontProvinces = new ArrayList<AI_ProvinceInfo_War>();
                                for (int a = 0; a < civ.isAtWarWithCivs.size(); ++a) {
                                }
                                for (int i2 = civ.lFrontLines.size() - 1; i2 >= 0; --i2) {
                                    AI_Frontline frontLineI = civ.lFrontLines.get(i2);
                                    if (!CFG.core.getCivsAtWar(nCivID, frontLineI.iWithCivID)) continue;
                                    for (int k = frontLineI.lProvinces.size() - 1; k >= 0; --k) {
                                        boolean add2 = true;
                                        for (int o2 = tempFrontProvinces.size() - 1; o2 >= 0; --o2) {
                                            if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)o2)).iProvinceID != frontLineI.lProvinces.get(k)) continue;
                                            add2 = false;
                                            break;
                                        }
                                        if (!add2) continue;
                                        tempFrontProvinces.add(new AI_ProvinceInfo_War(frontLineI.lProvinces.get(k), this.getPotential_BasedOnNeighboringProvs(frontLineI.lProvinces.get(k), nCivID), true));
                                    }
                                }
                                for (int o3 = 0; o3 < civ.civGD.iVassalsSize; ++o3) {
                                    Civilization civVassalO = CFG.core.getCiv(civ.civGD.vassals.get((int)o3).iCivID);
                                    for (int i3 = civVassalO.lFrontLines.size() - 1; i3 >= 0; --i3) {
                                        AI_Frontline frontlineVassalOI = civVassalO.lFrontLines.get(i3);
                                        if (!CFG.core.getCivsAtWar(nCivID, frontlineVassalOI.iWithCivID)) continue;
                                        for (int k = frontlineVassalOI.lProvinces.size() - 1; k >= 0; --k) {
                                            add = true;
                                            for (int u = tempFrontProvinces.size() - 1; u >= 0; --u) {
                                                if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)u)).iProvinceID != frontlineVassalOI.lProvinces.get(k)) continue;
                                                add = false;
                                                break;
                                            }
                                            if (!add) continue;
                                            tempFrontProvinces.add(new AI_ProvinceInfo_War(frontlineVassalOI.lProvinces.get(k), this.getPotential_BasedOnNeighboringProvs(frontlineVassalOI.lProvinces.get(k), civ.civGD.vassals.get((int)o3).iCivID), false));
                                        }
                                    }
                                }
                                if (civ.getPuppetOfCiv() != nCivID) {
                                    Civilization civLord = CFG.core.getCiv(civ.getPuppetOfCiv());
                                    for (int i4 = civLord.lFrontLines.size() - 1; i4 >= 0; --i4) {
                                        AI_Frontline frontLineLordI = civLord.lFrontLines.get(i4);
                                        if (!CFG.core.getCivsAtWar(nCivID, frontLineLordI.iWithCivID)) continue;
                                        for (int k = frontLineLordI.lProvinces.size() - 1; k >= 0; --k) {
                                            boolean add3 = true;
                                            for (o = tempFrontProvinces.size() - 1; o >= 0; --o) {
                                                if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)o)).iProvinceID != frontLineLordI.lProvinces.get(k)) continue;
                                                add3 = false;
                                                break;
                                            }
                                            if (!add3) continue;
                                            tempFrontProvinces.add(new AI_ProvinceInfo_War(frontLineLordI.lProvinces.get(k), this.getPotential_BasedOnNeighboringProvs(frontLineLordI.lProvinces.get(k), civ.getPuppetOfCiv()), false));
                                        }
                                    }
                                }
                                if (civ.getAlliance() > 0) {
                                    Alliance civAlliance = CFG.core.getAlliance(civ.getAlliance());
                                    for (int o4 = 0; o4 < civAlliance.getCivilizationsSize(); ++o4) {
                                        Civilization civAllyO = CFG.core.getCiv(civAlliance.getCivilization(o4));
                                        if (civAlliance.getCivilization(o4) == nCivID) continue;
                                        for (int i5 = civAllyO.lFrontLines.size() - 1; i5 >= 0; --i5) {
                                            AI_Frontline frontlineAllyOI = civAllyO.lFrontLines.get(i5);
                                            if (!CFG.core.getCivsAtWar(nCivID, frontlineAllyOI.iWithCivID)) continue;
                                            for (int k = frontlineAllyOI.lProvinces.size() - 1; k >= 0; --k) {
                                                boolean add4 = true;
                                                for (int u = tempFrontProvinces.size() - 1; u >= 0; --u) {
                                                    if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)u)).iProvinceID != frontlineAllyOI.lProvinces.get(k)) continue;
                                                    add4 = false;
                                                    break;
                                                }
                                                if (!add4) continue;
                                                tempFrontProvinces.add(new AI_ProvinceInfo_War(frontlineAllyOI.lProvinces.get(k), this.getPotential_BasedOnNeighboringProvs(frontlineAllyOI.lProvinces.get(k), civAlliance.getCivilization(o4)), false));
                                            }
                                        }
                                    }
                                }
                                try {
                                    for (Map.Entry<Integer, Civilization.DiplomacyData> entry : civ.militaryAccess.entrySet()) {
                                        for (int i6 = CFG.core.getCiv((int)entry.getKey().intValue()).lFrontLines.size() - 1; i6 >= 0; --i6) {
                                            AI_Frontline frontlineMilitaryAccessI = CFG.core.getCiv((int)entry.getKey().intValue()).lFrontLines.get(i6);
                                            if (!CFG.core.getCivsAtWar(nCivID, frontlineMilitaryAccessI.iWithCivID)) continue;
                                            for (int k = frontlineMilitaryAccessI.lProvinces.size() - 1; k >= 0; --k) {
                                                add = true;
                                                for (int o5 = tempFrontProvinces.size() - 1; o5 >= 0; --o5) {
                                                    if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)o5)).iProvinceID != frontlineMilitaryAccessI.lProvinces.get(k)) continue;
                                                    add = false;
                                                    break;
                                                }
                                                if (!add) continue;
                                                tempFrontProvinces.add(new AI_ProvinceInfo_War(frontlineMilitaryAccessI.lProvinces.get(k), this.getPotential_BasedOnNeighboringProvs(frontlineMilitaryAccessI.lProvinces.get(k), entry.getKey()), false));
                                            }
                                        }
                                    }
                                }
                                catch (Exception ex) {
                                    CFG.exceptionStack(ex);
                                }
                                if (tempFrontProvinces.isEmpty()) break block98;
                                int tMaxDL = 1;
                                float tMaxPotential = 1.0f;
                                ArrayList<Integer> tMovingArmy_toFrontProvince = new ArrayList<Integer>();
                                int tMaxArmy = 1;
                                float tMaxRegion_NumOfProvinces = 1.0f;
                                float tMaxRegion_Potential = 1.0f;
                                lFrontIDsWithArmies = new ArrayList<Integer>();
                                int tempMovingArmy = 0;
                                for (i = tempFrontProvinces.size() - 1; i >= 0; --i) {
                                    Province provinceI = CFG.core.getProv(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i)).iProvinceID);
                                    if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i)).iValue > tMaxPotential) {
                                        tMaxPotential = ((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i)).iValue;
                                    }
                                    if (provinceI.getDangerLevel_WithArmy() > tMaxDL) {
                                        tMaxDL = provinceI.getDangerLevel_WithArmy();
                                    }
                                    if ((float)provinceI.getRegion_NumOfProvinces() > tMaxRegion_NumOfProvinces) {
                                        tMaxRegion_NumOfProvinces = provinceI.getRegion_NumOfProvinces();
                                    }
                                    if ((float)provinceI.getPotentialRegion() > tMaxRegion_Potential) {
                                        tMaxRegion_Potential = provinceI.getPotentialRegion();
                                    }
                                    tMovingArmy_toFrontProvince.add(tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, ((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i)).iProvinceID));
                                    int provinceArmy = CFG.core.getProvinceArmy(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i)).iProvinceID);
                                    if (provinceArmy + tempMovingArmy <= tMaxArmy) continue;
                                    tMaxArmy = provinceArmy + tempMovingArmy;
                                }
                                for (i = tempFrontProvinces.size() - 1; i >= 0; --i) {
                                    Province provinceI = CFG.core.getProv(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i)).iProvinceID);
                                    ((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i)).iValue = (civ.civGD.civPers.WAR_POTENTIAL * (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i)).iValue / tMaxPotential) + civ.civGD.civPers.WAR_DANGER * ((float)provinceI.getDangerLevel_WithArmy() / (float)tMaxDL) + (1.0f - civ.civGD.civPers.WAR_REGION_NUM_OF_PROVINCES + civ.civGD.civPers.WAR_REGION_NUM_OF_PROVINCES * (float)provinceI.getRegion_NumOfProvinces() / tMaxRegion_NumOfProvinces - civ.civGD.civPers.WAR_REGION_POTENTIAL + civ.civGD.civPers.WAR_REGION_POTENTIAL * (float)provinceI.getPotentialRegion() / tMaxRegion_Potential)) * (1.0f - civ.civGD.civPers.WAR_ATTACK_DISTANCE * Distance.getDistanceFromAToB_PercOfMax(CFG.gameUpdate.getAdministration_Capital(nCivID), ((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i)).iProvinceID)) + (1.0f - civ.civGD.civPers.WAR_NUM_OF_UNITS + civ.civGD.civPers.WAR_NUM_OF_UNITS * (1.0f - (float)(CFG.core.getProvinceArmy(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i)).iProvinceID) + (Integer)tMovingArmy_toFrontProvince.get(i)) / (float)tMaxArmy) * (provinceI.getNeighProvinceOfCivWasLost() > 0 ? 0.55f + (float)CFG.oR.nextInt(30) / 100.0f : 1.0f));
                                }
                                sortedFrontProvinces = new ArrayList<AI_ProvinceInfo_War>();
                                int tID = 0;
                                while (!tempFrontProvinces.isEmpty()) {
                                    int tBest = 0;
                                    int iSize = tempFrontProvinces.size();
                                    for (int i7 = 1; i7 < iSize; ++i7) {
                                        if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)tBest)).iValue < ((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i7)).iValue) {
                                            tBest = i7;
                                            continue;
                                        }
                                        if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)tBest)).iValue != ((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i7)).iValue || CFG.oR.nextInt(100) >= 50) continue;
                                        tBest = i7;
                                    }
                                    if (CFG.core.getProv(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)tBest)).iProvinceID).getArmyCivID1(nCivID) > 0) {
                                        lFrontIDsWithArmies.add(tID);
                                    }
                                    sortedFrontProvinces.add((AI_ProvinceInfo_War)tempFrontProvinces.get(tBest));
                                    tempFrontProvinces.remove(tBest);
                                    ++tID;
                                }
                                this.moveAtWar_Regroup(nCivID, sortedFrontProvinces, lFrontIDsWithArmies);
                                if (civ.getGold() <= (long)GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT || civ.iBudget <= 0) break block99;
                                boolean bl = canRecruitAndMove = (float)lFrontIDsWithArmies.size() * 1.75f * (float)CFG.ideologiesMgr.getIdeologyID((int)civ.getIdeology()).COST_OF_MOVE <= (float)(civ.getMovemPoints() - CFG.ideologiesMgr.getIdeologyID((int)civ.getIdeology()).COST_OF_RECRUIT);
                                if (canRecruitAndMove) break block100;
                                float f = civ.getGold() / (long)GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT;
                                float f2 = civ.civGD.moveAtWar_ProvincesLostAndConquered_LastTurn < 0 ? 0.16f + 0.03f * (float)civ.civGD.moveAtWar_ProvincesLostAndConquered_LastTurn : (civ.civGD.moveAtWar_ArmyFullyRecruitedLastTurn ? 0.6f : 0.75f);
                                if (!(f * f2 > (float)civ.getNumberOfUnits()) && civ.civGD.moveAtWar_ProvincesLostAndConquered_LastTurn >= -3 && civ.getNumOfProvs() >= 3 && CFG.oR.nextInt(100) >= 6) break block101;
                            }
                            this.moveAtWar_Recruit(nCivID, sortedFrontProvinces, lFrontIDsWithArmies, false);
                        }
                        civ.civGD.moveAtWar_ArmyFullyRecruitedLastTurn = false;
                    }
                    if ((numOfPossibleMoves = civ.getMovemPoints() / CFG.ideologiesMgr.getIdeologyID((int)civ.getIdeology()).COST_OF_MOVE) > 0) {
                        int i;
                        Province provinceSortedI;
                        int i8;
                        ArrayList<Float> lScores = new ArrayList<Float>();
                        float score_MaxArmy = 1.0f;
                        float score_MaxPotenialProvinces = 1.0f;
                        for (i8 = lFrontIDsWithArmies.size() - 1; i8 >= 0; --i8) {
                            if (CFG.core.getProv(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)i8)).intValue())).iProvinceID).getArmyCivID1(nCivID) >= CFG.MIN_ARMY_REQUIRED_TO_ATTACK) continue;
                            lFrontIDsWithArmies.remove(i8);
                        }
                        for (i8 = lFrontIDsWithArmies.size() - 1; i8 >= 0; --i8) {
                            provinceSortedI = CFG.core.getProv(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)i8)).intValue())).iProvinceID);
                            if (score_MaxArmy < (float)provinceSortedI.getArmyCivID1(nCivID)) {
                                score_MaxArmy = provinceSortedI.getArmyCivID1(nCivID);
                            }
                            if (!(score_MaxPotenialProvinces < (float)provinceSortedI.getPotentialModified_WAR_MoveFrom(nCivID))) continue;
                            score_MaxPotenialProvinces = provinceSortedI.getPotentialModified_WAR_MoveFrom(nCivID);
                        }
                        for (i8 = 0; i8 < lFrontIDsWithArmies.size(); ++i8) {
                            provinceSortedI = CFG.core.getProv(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)i8)).intValue())).iProvinceID);
                            lScores.add(Float.valueOf(civ.civGD.civPers.WAR_ATTACK_SCORE_ARMY * (float)provinceSortedI.getArmyCivID1(nCivID) / score_MaxArmy + civ.civGD.civPers.WAR_ATTACK_SCORE_POTENTIAL * (float)provinceSortedI.getPotentialModified_WAR_MoveFrom(nCivID) / score_MaxPotenialProvinces + (provinceSortedI.getWasConquered() > 0 ? civ.civGD.civPers.WAR_ATTACK_SCORE_WAS_CONQUERED : 0.0f) + (provinceSortedI.getIsNotSuppliedForXTurns() > 0 ? 0.275f + 2.5f * (float)provinceSortedI.getArmyCivID1(nCivID) / score_MaxArmy : 0.0f)));
                        }
                        ArrayList<Integer> tSorted = new ArrayList<Integer>();
                        ArrayList<Integer> tempIDs = new ArrayList<Integer>();
                        for (i = lFrontIDsWithArmies.size() - 1; i >= 0; --i) {
                            tempIDs.add(i);
                        }
                        while (!tempIDs.isEmpty()) {
                            int tBest = 0;
                            for (int i9 = tempIDs.size() - 1; i9 > 0; --i9) {
                                if (!(((Float)lScores.get((Integer)tempIDs.get(tBest))).floatValue() < ((Float)lScores.get((Integer)tempIDs.get(i9))).floatValue())) continue;
                                tBest = i9;
                            }
                            tSorted.add((Integer)tempIDs.get(tBest));
                            tempIDs.remove(tBest);
                        }
                        int iSize = tSorted.size();
                        for (i = 0; i < iSize; ++i) {
                            int enemyArmy;
                            int neighID;
                            int j;
                            Province provinceFrontArmy;
                            lScores.clear();
                            tempIDs.clear();
                            if (CFG.oR.nextInt(100) < 65) {
                                provinceFrontArmy = CFG.core.getProv(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i)).intValue())).intValue())).iProvinceID);
                                for (j = 0; j < provinceFrontArmy.getNeighProvincesSize(); ++j) {
                                    neighID = provinceFrontArmy.getNeighProvinces(j);
                                    if (!CFG.core.getCivsAtWar(nCivID, CFG.core.getProv(neighID).getCivId()) || civ.isMovingUnitsToProvID(neighID)) continue;
                                    tempIDs.add(neighID);
                                    lScores.add(Float.valueOf(this.moveAtWar_AttackTo_Score(nCivID, neighID)));
                                }
                            }
                            if (tempIDs.isEmpty()) {
                                provinceFrontArmy = CFG.core.getProv(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i)).intValue())).intValue())).iProvinceID);
                                for (j = 0; j < provinceFrontArmy.getNeighProvincesSize(); ++j) {
                                    neighID = provinceFrontArmy.getNeighProvinces(j);
                                    if (!CFG.core.getCivsAtWar(nCivID, CFG.core.getProv(neighID).getCivId())) continue;
                                    tempIDs.add(neighID);
                                    lScores.add(Float.valueOf(this.moveAtWar_AttackTo_Score(nCivID, neighID) * (civ.isMovingUnitsToProvID(neighID) ? 0.625f : 1.0f)));
                                }
                            }
                            if (CFG.AI_PLUNDER_ENABLED && !CFG.core.getCiv(nCivID).getIsPlayer() && CFG.oR.nextInt(100) < GameValues.gvAiWar.PLUNDER_AT_WAR_CHANCE_100 && this.plunderProvince(nCivID, ((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i)).intValue())).intValue())).iProvinceID)) {
                                int maxArmy = this.getRegroupArmy_NumOfUnits(nCivID, ((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i)).intValue())).intValue())).iProvinceID);
                                maxArmy = (int)Math.max(Plunder.plunderEfficiency_RequiredMAX(nCivID, ((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i)).intValue())).intValue())).iProvinceID), (float)maxArmy);
                                Plunder.plunderProvince(nCivID, ((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i)).intValue())).intValue())).iProvinceID, maxArmy);
                            }
                            if (tempIDs.isEmpty()) continue;
                            if (tempIDs.size() > 1 && civ.getMovemPoints() >= CFG.ideologiesMgr.getIdeologyID((int)civ.getIdeology()).COST_OF_MOVE * 2) {
                                int o6;
                                int tArmyToMove = this.getRegroupArmy_NumOfUnits(nCivID, ((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i)).intValue())).intValue())).iProvinceID);
                                if (tArmyToMove <= 0) continue;
                                int numOfMoves = civ.getMovemPoints() / CFG.ideologiesMgr.getIdeologyID((int)civ.getIdeology()).COST_OF_MOVE;
                                ArrayList<Integer> sortedMoveTo = new ArrayList<Integer>();
                                ArrayList<Integer> tData = new ArrayList<Integer>();
                                for (int o7 = lScores.size() - 1; o7 >= 0; --o7) {
                                    tData.add(o7);
                                }
                                while (!tData.isEmpty()) {
                                    int tBest = 0;
                                    for (o6 = tData.size() - 1; o6 > 0; --o6) {
                                        if (!(((Float)lScores.get((Integer)tData.get(tBest))).floatValue() < ((Float)lScores.get((Integer)tData.get(o6))).floatValue())) continue;
                                        tBest = o6;
                                    }
                                    sortedMoveTo.add((Integer)tData.get(tBest));
                                    tData.remove(tBest);
                                }
                                numOfMoves = Math.min(numOfMoves, tempIDs.size());
                                float totalScore = 0.0f;
                                for (o6 = 0; o6 < sortedMoveTo.size(); ++o6) {
                                    totalScore += ((Float)lScores.get((Integer)sortedMoveTo.get(o6))).floatValue();
                                }
                                ArrayList<Boolean> checkJoinProvinces = new ArrayList<Boolean>();
                                for (int o8 = 0; o8 < numOfMoves; ++o8) {
                                    int enemyArmy2;
                                    int armyToMove_PRE = (int)Math.ceil((float)tArmyToMove * ((Float)lScores.get(o8)).floatValue() / totalScore);
                                    if ((CFG.core.getProv((Integer)tempIDs.get((Integer)sortedMoveTo.get(o8))).getWasAttacked() > 0 || CFG.core.getProv(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i)).intValue())).intValue())).iProvinceID).getLvlOfWatchTower() > 0 && CFG.core.getProv((Integer)tempIDs.get((Integer)sortedMoveTo.get(o8))).getLvlOfFort() <= 0) && armyToMove_PRE < (enemyArmy2 = (int)((float)(CFG.core.getProvinceArmy((Integer)tempIDs.get((Integer)sortedMoveTo.get(o8))) + this.getEnemyArmy_ExtraMovedArmy((Integer)tempIDs.get((Integer)sortedMoveTo.get(o8)))) * 1.05f))) {
                                        if (CFG.core.getProv(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i)).intValue())).intValue())).iProvinceID).getArmyCivID1(nCivID) > enemyArmy2) {
                                            armyToMove_PRE = (int)Math.min((float)CFG.core.getProv(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i)).intValue())).intValue())).iProvinceID).getArmyCivID1(nCivID), (float)CFG.core.getProvinceArmy((Integer)tempIDs.get((Integer)sortedMoveTo.get(o8))) * (1.04f + (float)CFG.oR.nextInt(20) / 100.0f));
                                            tArmyToMove -= armyToMove_PRE;
                                            totalScore = Math.max(1.0f, totalScore - ((Float)lScores.get(o8)).floatValue());
                                        } else if (enemyArmy2 >= CFG.core.getProvinceArmy(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i)).intValue())).intValue())).iProvinceID)) {
                                            int armyJoinProvinces = 0;
                                            for (int m = 0; m < CFG.core.getProv((Integer)tempIDs.get((Integer)sortedMoveTo.get(o8))).getNeighProvincesSize(); ++m) {
                                                if (CFG.core.getProv((Integer)tempIDs.get((Integer)sortedMoveTo.get(o8))).getNeighProvinces(m) == ((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i)).intValue())).intValue())).iProvinceID) continue;
                                                armyJoinProvinces += CFG.core.getProv(CFG.core.getProv((Integer)tempIDs.get((Integer)sortedMoveTo.get(o8))).getNeighProvinces(m)).getArmyCivID1(nCivID);
                                            }
                                            if (enemyArmy2 >= armyToMove_PRE + armyJoinProvinces) {
                                                checkJoinProvinces.add(false);
                                                continue;
                                            }
                                        }
                                    }
                                    checkJoinProvinces.add(true);
                                    if (!CFG.gameAction.moveArmyAction(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i)).intValue())).intValue())).iProvinceID, (Integer)tempIDs.get((Integer)sortedMoveTo.get(o8)), armyToMove_PRE, nCivID, true, false)) break;
                                }
                                if (CFG.ideologiesMgr.getIdeologyID((int)civ.getIdeology()).COST_OF_MOVE_SAME_PROVINCE > civ.getMovemPoints()) continue;
                                for (int k = 0; k < checkJoinProvinces.size(); ++k) {
                                    if (!((Boolean)checkJoinProvinces.get(k)).booleanValue()) continue;
                                    for (int o9 = 0; o9 < numOfMoves; ++o9) {
                                        Province provinceNeighM;
                                        Province provinceSortedO = CFG.core.getProv((Integer)tempIDs.get((Integer)sortedMoveTo.get(o9)));
                                        for (int m = 0; m < provinceSortedO.getNeighProvincesSize() && ((provinceNeighM = CFG.core.getProv(provinceSortedO.getNeighProvinces(m))).getArmyCivID1(nCivID) <= 0 || provinceNeighM.getCivId() == nCivID && this.moveAtWar_NumOfNotCoveredNeighEnemyProvinces(nCivID, provinceSortedO.getNeighProvinces(m)) > 1 || CFG.gameAction.moveArmyAction(provinceSortedO.getNeighProvinces(m), (Integer)tempIDs.get((Integer)sortedMoveTo.get(o9)), provinceNeighM.getArmyCivID1(nCivID), nCivID, true, false) || civ.getMovemPoints() >= CFG.ideologiesMgr.getIdeologyID((int)civ.getIdeology()).COST_OF_MOVE_SAME_PROVINCE); ++m) {
                                        }
                                    }
                                }
                                continue;
                            }
                            int tBestMoveTo = 0;
                            for (int o10 = lScores.size() - 1; o10 > 0; --o10) {
                                if (!(((Float)lScores.get(tBestMoveTo)).floatValue() < ((Float)lScores.get(o10)).floatValue())) continue;
                                tBestMoveTo = o10;
                            }
                            float totalScore = 0.0f;
                            for (int k = tempIDs.size() - 1; k >= 0; --k) {
                                if (civ.isMovingUnitsToProvID((Integer)tempIDs.get(k))) continue;
                                totalScore += ((Float)lScores.get(k)).floatValue();
                            }
                            int armyToMove_PRE = totalScore > 0.0f && CFG.oR.nextInt(100) < 90 ? (int)Math.ceil((float)CFG.core.getProv(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i)).intValue())).intValue())).iProvinceID).getArmyCivID1(nCivID) * ((Float)lScores.get(tBestMoveTo)).floatValue() / totalScore) : CFG.core.getProv(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i)).intValue())).intValue())).iProvinceID).getArmyCivID1(nCivID);
                            Province provinceTempBestMoveTo = CFG.core.getProv((Integer)tempIDs.get(tBestMoveTo));
                            if ((provinceTempBestMoveTo.getWasAttacked() > 0 || CFG.core.getProv(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i)).intValue())).intValue())).iProvinceID).getLvlOfWatchTower() > 0 && provinceTempBestMoveTo.getLvlOfFort() <= 0) && armyToMove_PRE < (enemyArmy = (int)((float)(CFG.core.getProvinceArmy((Integer)tempIDs.get(tBestMoveTo)) + this.getEnemyArmy_ExtraMovedArmy((Integer)tempIDs.get(tBestMoveTo))) * 1.05f))) {
                                if (CFG.core.getProv(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i)).intValue())).intValue())).iProvinceID).getArmyCivID1(nCivID) > enemyArmy) {
                                    armyToMove_PRE = (int)Math.min((float)CFG.core.getProv(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i)).intValue())).intValue())).iProvinceID).getArmyCivID1(nCivID), (float)CFG.core.getProvinceArmy((Integer)tempIDs.get(tBestMoveTo)) * (1.04f + (float)CFG.oR.nextInt(20) / 100.0f));
                                } else if (enemyArmy >= CFG.core.getProvinceArmy(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i)).intValue())).intValue())).iProvinceID)) {
                                    int armyJoinProvinces = 0;
                                    for (int m = 0; m < provinceTempBestMoveTo.getNeighProvincesSize(); ++m) {
                                        if (provinceTempBestMoveTo.getNeighProvinces(m) == ((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i)).intValue())).intValue())).iProvinceID) continue;
                                        armyJoinProvinces += CFG.core.getProv(provinceTempBestMoveTo.getNeighProvinces(m)).getArmyCivID1(nCivID);
                                    }
                                    if (enemyArmy >= armyToMove_PRE + armyJoinProvinces) continue;
                                }
                            }
                            if (CFG.gameAction.moveArmyAction(((AI_ProvinceInfo_War)sortedFrontProvinces.get((int)((Integer)lFrontIDsWithArmies.get((int)((Integer)tSorted.get((int)i)).intValue())).intValue())).iProvinceID, (Integer)tempIDs.get(tBestMoveTo), armyToMove_PRE, nCivID, true, false)) {
                                Province provinceNeighM;
                                Province provinceBestMoveTo = CFG.core.getProv((Integer)tempIDs.get(tBestMoveTo));
                                for (int m = 0; m < provinceBestMoveTo.getNeighProvincesSize() && ((provinceNeighM = CFG.core.getProv(provinceBestMoveTo.getNeighProvinces(m))).getArmyCivID1(nCivID) <= 0 || provinceNeighM.getCivId() == nCivID && this.moveAtWar_NumOfNotCoveredNeighEnemyProvinces(nCivID, provinceBestMoveTo.getNeighProvinces(m)) > 1 || CFG.gameAction.moveArmyAction(provinceBestMoveTo.getNeighProvinces(m), (Integer)tempIDs.get(tBestMoveTo), provinceNeighM.getArmyCivID1(nCivID), nCivID, true, false) || civ.getMovemPoints() >= CFG.ideologiesMgr.getIdeologyID((int)civ.getIdeology()).COST_OF_MOVE); ++m) {
                                }
                                continue;
                            }
                            if (civ.getMovemPoints() >= CFG.ideologiesMgr.getIdeologyID((int)civ.getIdeology()).COST_OF_MOVE) {
                                continue;
                            }
                            break block97;
                        }
                    }
                    break block97;
                }
                if (civ.civGD.iNextCheckMilitaryAccessTurnID <= GameCalendar.TURNID && CFG.oR.nextInt(100) < 72) {
                    ArrayList<Integer> askForAccess = new ArrayList<Integer>();
                    for (int i = civ.lFrontLines.size() - 1; i >= 0; --i) {
                        AI_Frontline frontlineAskI = civ.lFrontLines.get(i);
                        if (CFG.core.getCivsAtWar(nCivID, frontlineAskI.iWithCivID)) continue;
                        for (int j = CFG.core.getCiv((int)frontlineAskI.iWithCivID).lFrontLines.size() - 1; j >= 0; --j) {
                            if (!CFG.core.getCivsAtWar(nCivID, CFG.core.getCiv((int)frontlineAskI.iWithCivID).lFrontLines.get((int)j).iWithCivID) || civ.iBudget <= CFG.core.getCiv((int)CFG.core.getCiv((int)frontlineAskI.iWithCivID).lFrontLines.get((int)j).iWithCivID).iBudget && CFG.oR.nextInt(100) >= 6) continue;
                            boolean wasAdded = false;
                            for (int z = askForAccess.size() - 1; z >= 0; --z) {
                                if ((Integer)askForAccess.get(z) != frontlineAskI.iWithCivID) continue;
                                wasAdded = true;
                                break;
                            }
                            if (wasAdded) continue;
                            askForAccess.add(frontlineAskI.iWithCivID);
                        }
                    }
                    if (!askForAccess.isEmpty()) {
                        while (!askForAccess.isEmpty() && civ.getDiploPoints() >= GameValues.gvDipMilitaryAccess.COST_OFFER_MILITARY_ACCESS_ASK_DIPLOMACY_POINTS) {
                            int tRand = CFG.oR.nextInt(askForAccess.size());
                            if (CFG.core.getMilitaryAccess(nCivID, (Integer)askForAccess.get(tRand)) <= 10 && !civ.messageWasSent((Integer)askForAccess.get(tRand), MessageType.MILITARY_ACCESS_ASK)) {
                                GameManager.sendMilitaryAccess_AskProposal((Integer)askForAccess.get(tRand), nCivID, GameValues.gvDipMilitaryAccess.DIPLOMACY_MAX_NUMBER_OF_TURNS_FOR_MILITARY_ACCESS);
                            }
                            askForAccess.remove(tRand);
                        }
                        civ.civGD.iNextCheckMilitaryAccessTurnID = GameCalendar.TURNID + 6 + CFG.oR.nextInt(20);
                    }
                } else if (civ.civGD.iNextCheckMilitaryAccessSeaTurnID <= GameCalendar.TURNID) {
                    ArrayList<Integer> askForAccess2 = new ArrayList<Integer>();
                    for (int i = civ.isAtWarWithCivs.size() - 1; i >= 0; --i) {
                        Civilization civAtWarI = CFG.core.getCiv(civ.isAtWarWithCivs.get(i));
                        if (civ.iBudget <= civAtWarI.iBudget || civAtWarI.getSeaAccess() != 0) continue;
                        for (int z = civAtWarI.lFrontLines.size() - 1; z >= 0; --z) {
                            if (CFG.core.getCivsAtWar(nCivID, civAtWarI.lFrontLines.get((int)z).iWithCivID) || CFG.core.getCiv(civAtWarI.lFrontLines.get((int)z).iWithCivID).getSeaAccess() <= 0) continue;
                            boolean wasAdded = false;
                            for (o = askForAccess2.size() - 1; o >= 0; --o) {
                                if ((Integer)askForAccess2.get(o) != civAtWarI.lFrontLines.get((int)z).iWithCivID) continue;
                                wasAdded = true;
                                break;
                            }
                            if (wasAdded) continue;
                            askForAccess2.add(civAtWarI.lFrontLines.get((int)z).iWithCivID);
                        }
                    }
                    if (!askForAccess2.isEmpty()) {
                        while (!askForAccess2.isEmpty() && civ.getDiploPoints() >= GameValues.gvDipMilitaryAccess.COST_OFFER_MILITARY_ACCESS_ASK_DIPLOMACY_POINTS) {
                            int tRand = CFG.oR.nextInt(askForAccess2.size());
                            if (CFG.core.getMilitaryAccess(nCivID, (Integer)askForAccess2.get(tRand)) <= 10 && !civ.messageWasSent((Integer)askForAccess2.get(tRand), MessageType.MILITARY_ACCESS_ASK)) {
                                GameManager.sendMilitaryAccess_AskProposal((Integer)askForAccess2.get(tRand), nCivID, GameValues.gvDipMilitaryAccess.DIPLOMACY_MAX_NUMBER_OF_TURNS_FOR_MILITARY_ACCESS);
                            }
                            askForAccess2.remove(tRand);
                        }
                    }
                    civ.civGD.iNextCheckMilitaryAccessSeaTurnID = GameCalendar.TURNID + 6 + CFG.oR.nextInt(20);
                }
            }
            if (GameValues.gvAiWar.USE_NEW_NAVAL_INVASION) {
                this.moveAtWar_AtSea_New(nCivID);
            } else {
                this.moveAtWar_AtSea(nCivID);
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    public final boolean plunderProvince(int nCivID, int nProvinceID) {
        int possibleArmy;
        if (CFG.core.getProv(nProvinceID).isOccupied() && !CFG.core.getProv(nProvinceID).getCores().getHaveACore(nCivID) && (float)CFG.core.getProv(nProvinceID).getArmyCivID1(nCivID) < (float)CFG.core.getCiv(nCivID).getNumberOfUnits() * 0.235f && CFG.core.getCiv((int)nCivID).civGD.iPlunder_LastTurnID <= GameCalendar.TURNID && (float)(possibleArmy = this.getRegroupArmy_NumOfUnits(nCivID, nProvinceID)) / Plunder.plunderEfficiency_RequiredMAX(nCivID, nProvinceID) > 0.45f && CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_PLUNDER <= CFG.core.getCiv(nCivID).getMovemPoints()) {
            if ((float)CFG.oAI.getAIStyle((int)CFG.core.getCiv((int)nCivID).getAIStyleID()).PERSONALITY_PLUNDER_MIN + (float)CFG.oR.nextInt(CFG.oAI.getAIStyle((int)CFG.core.getCiv((int)nCivID).getAIStyleID()).PERSONALITY_PLUNDER_RANDOM) / 1000.0f > (float)CFG.oR.nextInt(1000) / 1000.0f) {
                CFG.core.getCiv((int)nCivID).civGD.iPlunder_LastTurnID = CFG.oR.nextInt(100) < this.PERSONALITY_PLUNDER_LOCK ? GameCalendar.TURNID + 3 + CFG.oR.nextInt(5) : GameCalendar.TURNID + 3 + CFG.oR.nextInt(5);
                return true;
            }
            return true;
        }
        return false;
    }

    public final int getEnemyArmy_ExtraMovedArmy(int nProvinceID) {
        int out = 0;
        Province province = CFG.core.getProv(nProvinceID);
        for (int i = 0; i < province.getCivsSize(); ++i) {
            for (int j = 0; j < CFG.core.getCiv(province.getCivId(i)).moveUnitsSize(); ++j) {
                if (CFG.core.getCiv(province.getCivId(i)).getMoveUnits(j).getFromProviID() != nProvinceID) continue;
                out += CFG.core.getCiv(province.getCivId(i)).getMoveUnits(j).getNumberOfUnits();
            }
        }
        return out;
    }

    public final int moveAtWar_AtSea_RunMissions(int nCivID) {
        int outActiveMissions = 0;
        Civilization civ = CFG.core.getCiv(nCivID);
        for (int k = civ.civGD.civPlans.armiesMissions.size() - 1; k >= 0; --k) {
            if (civ.civGD.civPlans.armiesMissions.get((int)k).MISSION_TYPE != CivArmyMission_Type.NAVAL_INVASION || !civ.civGD.civPlans.armiesMissions.get(k).canMakeAction(nCivID, 0)) continue;
            if (civ.civGD.civPlans.armiesMissions.get(k).action(nCivID)) {
                civ.civGD.civPlans.armiesMissions.remove(k);
                continue;
            }
            if (civ.civGD.civPlans.armiesMissions.get((int)k).iObsolete <= 0) {
                civ.civGD.civPlans.armiesMissions.remove(k);
                continue;
            }
            ++outActiveMissions;
        }
        return outActiveMissions;
    }

    public final void moveAtWar_AtSea_New(int nCivID) {
        block29: {
            Civilization civ = CFG.core.getCiv(nCivID);
            for (int k = civ.civGD.civPlans.armiesMissions.size() - 1; k >= 0; --k) {
                if (civ.civGD.civPlans.armiesMissions.get((int)k).MISSION_TYPE != CivArmyMission_Type.NAVAL_INVASION) continue;
            }
            try {
                int j;
                int i;
                if (civ.getMovemPoints() < CFG.ideologiesMgr.getIdeologyID((int)civ.getIdeology()).COST_OF_MOVE) break block29;
                int numOfCurrentInvasions = this.moveAtWar_AtSea_RunMissions(nCivID);
                if (GameCalendar.TURNID <= civ.civGD.iNextPossibleNavalInvasionTurnID) {
                    return;
                }
                if ((civ.getBordersWithEnemy() == 1 || civ.getBordersWithEnemy() == 2 && CFG.oR.nextInt(100) < 75) && numOfCurrentInvasions >= GameValues.gvAiWar.NAVAL_INVASION_LIMIT) {
                    return;
                }
                if ((float)numOfCurrentInvasions >= Math.max(1.0f, (float)civ.getNumOfProvs() / 10.0f)) {
                    return;
                }
                if (civ.getBordersWithEnemy() != 0) break block29;
                boolean canBuildPort = BuildingsManager.canBuildPort(civ.getProvID(0));
                if (civ.getSeaAccess_PortProvinces_Size() == 0 && !canBuildPort) {
                    return;
                }
                if (civ.getSeaAccess() <= 0) break block29;
                ArrayList<Integer> civsAtWarWithSeaAccessToo = new ArrayList<Integer>();
                for (int i2 = civ.isAtWarWithCivs.size() - 1; i2 >= 0; --i2) {
                    if (CFG.core.getCiv(civ.isAtWarWithCivs.get(i2)).getSeaAccess() <= 0) continue;
                    civsAtWarWithSeaAccessToo.add(civ.isAtWarWithCivs.get(i2));
                }
                if (civsAtWarWithSeaAccessToo.isEmpty()) break block29;
                ArrayList<Boolean> haveAccessToBasins = new ArrayList<Boolean>();
                ArrayList possibleProvinceMoveTo_OwnProvinces = new ArrayList();
                ArrayList possibleProvinceMoveTo = new ArrayList();
                for (i = 0; i < CFG.map.numOfBasins; ++i) {
                    haveAccessToBasins.add(false);
                    possibleProvinceMoveTo_OwnProvinces.add(new ArrayList());
                    possibleProvinceMoveTo.add(new ArrayList());
                }
                if (!canBuildPort) {
                    for (i = civ.getSeaAccess_PortProvinces_Size() - 1; i >= 0; --i) {
                        for (j = 0; j < CFG.core.getProv(civ.getSeaAccess_PortProvinces().get(i)).getNeighSeaProvincesSize(); ++j) {
                            haveAccessToBasins.set(CFG.core.getProv(CFG.core.getProv(civ.getSeaAccess_PortProvinces().get(i)).getNeighSeaProvinces(j)).getBasinID(), true);
                        }
                    }
                } else {
                    for (i = civ.getSeaAccess_Provinces_Size() - 1; i >= 0; --i) {
                        for (j = 0; j < CFG.core.getProv(civ.getSeaAccessProvinces().get(i)).getNeighSeaProvincesSize(); ++j) {
                            haveAccessToBasins.set(CFG.core.getProv(CFG.core.getProv(civ.getSeaAccessProvinces().get(i)).getNeighSeaProvinces(j)).getBasinID(), true);
                        }
                    }
                }
                int possibleMoveTo_OwnProvinces = 0;
                int possibleMoveTo = 0;
                for (int i3 = civsAtWarWithSeaAccessToo.size() - 1; i3 >= 0; --i3) {
                    Civilization civAtWarSeaAccessI = CFG.core.getCiv((Integer)civsAtWarWithSeaAccessToo.get(i3));
                    for (int j2 = civAtWarSeaAccessI.getSeaAccess_Provinces_Size() - 1; j2 >= 0; --j2) {
                        for (int k = 0; k < CFG.core.getProv(civAtWarSeaAccessI.getSeaAccessProvinces().get(j2)).getNeighSeaProvincesSize(); ++k) {
                            if (!((Boolean)haveAccessToBasins.get(CFG.core.getProv(CFG.core.getProv(civAtWarSeaAccessI.getSeaAccessProvinces().get(j2)).getNeighSeaProvinces(k)).getBasinID())).booleanValue()) continue;
                            if (CFG.core.getProv(civAtWarSeaAccessI.getSeaAccessProvinces().get(j2)).getTrueOwnerOfProv() == nCivID) {
                                ((List)possibleProvinceMoveTo_OwnProvinces.get(CFG.core.getProv(CFG.core.getProv(civAtWarSeaAccessI.getSeaAccessProvinces().get(j2)).getNeighSeaProvinces(k)).getBasinID())).add(civAtWarSeaAccessI.getSeaAccessProvinces().get(j2));
                                ++possibleMoveTo_OwnProvinces;
                                continue;
                            }
                            ((List)possibleProvinceMoveTo.get(CFG.core.getProv(CFG.core.getProv(civAtWarSeaAccessI.getSeaAccessProvinces().get(j2)).getNeighSeaProvinces(k)).getBasinID())).add(civAtWarSeaAccessI.getSeaAccessProvinces().get(j2));
                            ++possibleMoveTo;
                        }
                    }
                }
                if (possibleMoveTo <= 0 && possibleMoveTo_OwnProvinces <= 0) break block29;
                for (int z = 0; z < 5; ++z) {
                    int j3;
                    int i4;
                    int iBestProvinceID_MoveTo = -1;
                    float fBestProvinceID_MoveTo_Score = -1.0f;
                    int best_I = -1;
                    int best_J = -1;
                    boolean best_OwnProvince = true;
                    float tempScore = 0.0f;
                    for (i4 = possibleProvinceMoveTo_OwnProvinces.size() - 1; i4 >= 0; --i4) {
                        for (j3 = ((List)possibleProvinceMoveTo_OwnProvinces.get(i4)).size() - 1; j3 >= 0; --j3) {
                            tempScore = this.moveAtWar_AtSea_ToProvinceID_Score_New(nCivID, (Integer)((List)possibleProvinceMoveTo_OwnProvinces.get(i4)).get(j3), true);
                            if (!(tempScore > fBestProvinceID_MoveTo_Score)) continue;
                            iBestProvinceID_MoveTo = (Integer)((List)possibleProvinceMoveTo_OwnProvinces.get(i4)).get(j3);
                            fBestProvinceID_MoveTo_Score = tempScore;
                            best_I = i4;
                            best_J = j3;
                            best_OwnProvince = true;
                        }
                    }
                    for (i4 = possibleProvinceMoveTo.size() - 1; i4 >= 0; --i4) {
                        for (j3 = ((List)possibleProvinceMoveTo.get(i4)).size() - 1; j3 >= 0; --j3) {
                            tempScore = this.moveAtWar_AtSea_ToProvinceID_Score_New(nCivID, (Integer)((List)possibleProvinceMoveTo.get(i4)).get(j3), false);
                            if (!(tempScore > fBestProvinceID_MoveTo_Score)) continue;
                            iBestProvinceID_MoveTo = (Integer)((List)possibleProvinceMoveTo.get(i4)).get(j3);
                            fBestProvinceID_MoveTo_Score = tempScore;
                            best_I = i4;
                            best_J = j3;
                            best_OwnProvince = false;
                        }
                    }
                    if (iBestProvinceID_MoveTo >= 0) {
                        if (!(CFG.core.isAlly(nCivID, CFG.core.getProv(iBestProvinceID_MoveTo).getCivId()) || CFG.core.getProv(iBestProvinceID_MoveTo).getTrueOwnerOfProv() == nCivID && CFG.core.getProv(iBestProvinceID_MoveTo).isOccupied() && CFG.oR.nextInt(100) < GameValues.gvAiWar.NAVAL_INVASION_RETAKE_OCCUPIED_PROVINCE_CHANCE_100 || civ.getRankPos() <= CFG.core.getCiv(CFG.core.getProv(iBestProvinceID_MoveTo).getCivId()).getRankPos() || CFG.oR.nextInt(100) >= GameValues.gvAiWar.NAVAL_INVASION_DELAY_HIGHER_RANK_CHANCE)) {
                            civ.civGD.iNextPossibleNavalInvasionTurnID = GameCalendar.TURNID + GameValues.gvAiWar.NAVAL_INVASION_DELAY_HIGHER_RANK_MIN_TURNS + CFG.oR.nextInt(GameValues.gvAiWar.NAVAL_INVASION_DELAY_HIGHER_RANK_RANDOM_TURNS);
                        } else if (this.moveAtWar_AtSea_ToProvinceID_New(nCivID, iBestProvinceID_MoveTo)) {
                            if (best_OwnProvince) {
                                ((List)possibleProvinceMoveTo_OwnProvinces.get(best_I)).remove(best_J);
                                if (!((List)possibleProvinceMoveTo_OwnProvinces.get(best_I)).isEmpty()) continue;
                                possibleProvinceMoveTo_OwnProvinces.remove(best_I);
                                continue;
                            }
                            ((List)possibleProvinceMoveTo.get(best_I)).remove(best_J);
                            if (!((List)possibleProvinceMoveTo.get(best_I)).isEmpty()) continue;
                            possibleProvinceMoveTo.remove(best_I);
                            continue;
                        }
                    }
                    break;
                }
            }
            catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
    }

    public final float moveAtWar_AtSea_ToProvinceID_Score_New(int nCivID, int nProvince, boolean ownProvince) {
        return ((float)CFG.core.getProv(nProvince).getPotential() + (float)(CFG.core.getProv(nProvince).getPotentialRegion() * CFG.core.getCiv(CFG.core.getProv(nProvince).getCivId()).getCivRegion(CFG.core.getProv(nProvince).getCivRegionID()).getProvincesSize()) / (float)CFG.core.getCiv(CFG.core.getProv(nProvince).getCivId()).getNumOfProvs()) * (ownProvince ? 2.5f : (CFG.core.getProv(nProvince).isOccupied() ? 0.625f : 1.0f)) * (CFG.core.getProv(nProvince).isCapital() && CFG.core.getProv(nProvince).getCivId() == nCivID ? 1.15f : 1.0f) * (CFG.core.getProv(nProvince).getLvlOfPort() > 0 ? 1.5f : 1.0f) * (1.0f - CFG.core.getCiv((int)nCivID).civGD.civPers.WAR_ATTACK_NAVAL_DISTANCE_NEW * Distance.getDistanceFromAToB_PercOfMax(CFG.gameUpdate.getAdministration_Capital(nCivID), nProvince));
    }

    public final float moveAtWar_AtSea_FromProvinceID_Score_New(int nCivID, int nProvince, int toProvinceID, boolean ownProvince, int tempPossibleToRecruit) {
        return ((float)CFG.core.getProv(nProvince).getPotential() * 0.2f + 500.0f) * (0.625f * ((float)CFG.core.getProv(nProvince).getArmyCivID1(nCivID) + (float)Math.min(CFG.gameAction.gMARY(nProvince, nCivID), tempPossibleToRecruit) * 0.1f)) * (CFG.core.getProv(nProvince).getLvlOfPort() > 0 ? 2.0f : 1.0f) * (1.0f - Distance.getDistanceFromAToB_PercOfMax(nProvince, toProvinceID) / 2.0f);
    }

    public final boolean moveAtWar_AtSea_ToProvinceID_New(int nCivID, int iBestProvinceID_MoveTo) {
        try {
            int i;
            ArrayList<Boolean> haveAccessToBasins = new ArrayList<Boolean>();
            for (i = 0; i < CFG.map.numOfBasins; ++i) {
                haveAccessToBasins.add(false);
            }
            for (i = 0; i < CFG.core.getProv(iBestProvinceID_MoveTo).getNeighSeaProvincesSize(); ++i) {
                haveAccessToBasins.set(CFG.core.getProv(CFG.core.getProv(iBestProvinceID_MoveTo).getNeighSeaProvinces(i)).getBasinID(), true);
            }
            int iBestProvinceID_MoveFrom = -1;
            float fBestProvinceID_MoveTo_Score = -1.0f;
            int tempPossibleToRecruit = 0;
            if (CFG.core.getCiv(nCivID).getGold() > (long)(GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT * CFG.MIN_ARMY_REQUIRED_TO_ATTACK * 2)) {
                tempPossibleToRecruit = (int)(CFG.core.getCiv(nCivID).getGold() / (long)GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT);
            }
            block4: for (int i2 = CFG.core.getCiv(nCivID).getSeaAccess_Provinces_Size() - 1; i2 >= 0; --i2) {
                if (CFG.core.getProv(CFG.core.getCiv(nCivID).getSeaAccessProvinces().get(i2)).isOccupied() && GameCalendar.TURNID % GameValues.gvAiWar.NAVAL_INVASION_FROM_OCCUPIED_ONLY_EVERY_X_TURNS != 0) continue;
                for (int j = 0; j < CFG.core.getProv(CFG.core.getCiv(nCivID).getSeaAccessProvinces().get(i2)).getNeighSeaProvincesSize(); ++j) {
                    float tempScore;
                    if (!((Boolean)haveAccessToBasins.get(CFG.core.getProv(CFG.core.getProv(CFG.core.getCiv(nCivID).getSeaAccessProvinces().get(i2)).getNeighSeaProvinces(j)).getBasinID())).booleanValue()) continue;
                    if (CFG.core.getCiv(nCivID).getCivRegion(CFG.core.getProv(CFG.core.getCiv(nCivID).getSeaAccessProvinces().get(i2)).getCivRegionID()).checkRegionBordersWithEnemy(nCivID) || !((tempScore = this.moveAtWar_AtSea_FromProvinceID_Score_New(nCivID, CFG.core.getCiv(nCivID).getSeaAccessProvinces().get(i2), iBestProvinceID_MoveTo, false, tempPossibleToRecruit)) > fBestProvinceID_MoveTo_Score)) continue block4;
                    iBestProvinceID_MoveFrom = CFG.core.getCiv(nCivID).getSeaAccessProvinces().get(i2);
                    fBestProvinceID_MoveTo_Score = tempScore;
                    continue block4;
                }
            }
            if (iBestProvinceID_MoveFrom >= 0) {
                if (CFG.core.getProv(iBestProvinceID_MoveFrom).getLvlOfPort() <= 0) {
                    int i3;
                    boolean newFound = false;
                    for (i3 = 0; i3 < CFG.core.getProv(iBestProvinceID_MoveFrom).getNeighProvincesSize(); ++i3) {
                        if (CFG.core.getProv(CFG.core.getProv(iBestProvinceID_MoveFrom).getNeighProvinces(i3)).getCivId() != nCivID || CFG.core.getProv(CFG.core.getProv(iBestProvinceID_MoveFrom).getNeighProvinces(i3)).getLvlOfPort() <= 0) continue;
                        iBestProvinceID_MoveFrom = CFG.core.getProv(iBestProvinceID_MoveFrom).getNeighProvinces(i3);
                        newFound = true;
                        break;
                    }
                    if (!newFound) {
                        block7: for (i3 = 0; i3 < CFG.core.getProv(iBestProvinceID_MoveFrom).getNeighProvincesSize(); ++i3) {
                            if (CFG.core.getProv(CFG.core.getProv(iBestProvinceID_MoveFrom).getNeighProvinces(i3)).getCivId() != nCivID || CFG.core.getProv(CFG.core.getProv(iBestProvinceID_MoveFrom).getNeighProvinces(i3)).getLvlOfPort() <= 0) continue;
                            for (int j = 0; j < CFG.core.getProv(CFG.core.getProv(iBestProvinceID_MoveFrom).getNeighProvinces(i3)).getNeighProvincesSize(); ++j) {
                                if (CFG.core.getProv(CFG.core.getProv(CFG.core.getProv(iBestProvinceID_MoveFrom).getNeighProvinces(i3)).getNeighProvinces(j)).getCivId() != nCivID || CFG.core.getProv(CFG.core.getProv(CFG.core.getProv(iBestProvinceID_MoveFrom).getNeighProvinces(i3)).getNeighProvinces(j)).getLvlOfPort() <= 0) continue;
                                iBestProvinceID_MoveFrom = CFG.core.getProv(CFG.core.getProv(iBestProvinceID_MoveFrom).getNeighProvinces(i3)).getNeighProvinces(j);
                                i3 = CFG.core.getProv(iBestProvinceID_MoveFrom).getNeighProvincesSize();
                                continue block7;
                            }
                        }
                    }
                    if (CFG.core.getProv(iBestProvinceID_MoveFrom).getLvlOfPort() > 0 || BuildingsManager.constructPort(iBestProvinceID_MoveFrom, nCivID)) {
                        // empty if block
                    }
                }
                for (int a = CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.size() - 1; a >= 0; --a) {
                    if (CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.get((int)a).MISSION_TYPE != CivArmyMission_Type.NAVAL_INVASION || CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.get((int)a).iProvinceID != iBestProvinceID_MoveFrom) continue;
                    return true;
                }
                CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.add(new CivArmyMission_NavalInvasion(nCivID, iBestProvinceID_MoveFrom, iBestProvinceID_MoveTo));
                CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.get(CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.size() - 1).action(nCivID);
                return true;
            }
            return false;
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
            return false;
        }
    }

    public final void moveAtWar_AtSea(int nCivID) {
        Civilization civ = CFG.core.getCiv(nCivID);
        if (civ.getMovemPoints() >= CFG.ideologiesMgr.getIdeologyID((int)civ.getIdeology()).COST_OF_MOVE) {
            int numOfCurrentInvasions = this.moveAtWar_AtSea_RunMissions(nCivID);
            if (GameCalendar.TURNID <= civ.civGD.iNextPossibleNavalInvasionTurnID) {
                return;
            }
            if (civ.getBordersWithEnemy() == 0 && numOfCurrentInvasions > 0) {
                return;
            }
            if ((float)numOfCurrentInvasions >= Math.max(1.0f, (float)civ.getNumOfProvs() / 10.0f)) {
                return;
            }
            if (civ.getBordersWithEnemy() == 0) {
                boolean canBuildPort = BuildingsManager.canBuildPort(civ.getProvID(0));
                if (civ.getSeaAccess_PortProvinces_Size() == 0 && !canBuildPort) {
                    return;
                }
                if (civ.getSeaAccess() > 0) {
                    ArrayList<Integer> civsAtWarWithSeaAccessToo = new ArrayList<Integer>();
                    for (int i = civ.isAtWarWithCivs.size() - 1; i >= 0; --i) {
                        if (CFG.core.getCiv(civ.isAtWarWithCivs.get(i)).getSeaAccess() <= 0) continue;
                        civsAtWarWithSeaAccessToo.add(civ.isAtWarWithCivs.get(i));
                    }
                    if (!civsAtWarWithSeaAccessToo.isEmpty()) {
                        int j;
                        int i;
                        ArrayList<Boolean> haveAccessToBasins = new ArrayList<Boolean>();
                        ArrayList possibleProvinceMoveTo_OwnProvinces = new ArrayList();
                        ArrayList possibleProvinceMoveTo = new ArrayList();
                        for (i = 0; i < CFG.map.numOfBasins; ++i) {
                            haveAccessToBasins.add(false);
                            possibleProvinceMoveTo_OwnProvinces.add(new ArrayList());
                            possibleProvinceMoveTo.add(new ArrayList());
                        }
                        if (!canBuildPort) {
                            for (i = civ.getSeaAccess_PortProvinces_Size() - 1; i >= 0; --i) {
                                for (j = 0; j < CFG.core.getProv(civ.getSeaAccess_PortProvinces().get(i)).getNeighSeaProvincesSize(); ++j) {
                                    haveAccessToBasins.set(CFG.core.getProv(CFG.core.getProv(civ.getSeaAccess_PortProvinces().get(i)).getNeighSeaProvinces(j)).getBasinID(), true);
                                }
                            }
                        } else {
                            for (i = civ.getSeaAccess_Provinces_Size() - 1; i >= 0; --i) {
                                for (j = 0; j < CFG.core.getProv(civ.getSeaAccessProvinces().get(i)).getNeighSeaProvincesSize(); ++j) {
                                    haveAccessToBasins.set(CFG.core.getProv(CFG.core.getProv(civ.getSeaAccessProvinces().get(i)).getNeighSeaProvinces(j)).getBasinID(), true);
                                }
                            }
                        }
                        int possibleMoveTo_OwnProvinces = 0;
                        int possibleMoveTo = 0;
                        for (int i2 = civsAtWarWithSeaAccessToo.size() - 1; i2 >= 0; --i2) {
                            Civilization civAtWarSeaAccessI = CFG.core.getCiv((Integer)civsAtWarWithSeaAccessToo.get(i2));
                            for (int j2 = civAtWarSeaAccessI.getSeaAccess_Provinces_Size() - 1; j2 >= 0; --j2) {
                                for (int k = 0; k < CFG.core.getProv(civAtWarSeaAccessI.getSeaAccessProvinces().get(j2)).getNeighSeaProvincesSize(); ++k) {
                                    if (!((Boolean)haveAccessToBasins.get(CFG.core.getProv(CFG.core.getProv(civAtWarSeaAccessI.getSeaAccessProvinces().get(j2)).getNeighSeaProvinces(k)).getBasinID())).booleanValue()) continue;
                                    if (CFG.core.getProv(civAtWarSeaAccessI.getSeaAccessProvinces().get(j2)).getTrueOwnerOfProv() == nCivID) {
                                        ((List)possibleProvinceMoveTo_OwnProvinces.get(CFG.core.getProv(CFG.core.getProv(civAtWarSeaAccessI.getSeaAccessProvinces().get(j2)).getNeighSeaProvinces(k)).getBasinID())).add(civAtWarSeaAccessI.getSeaAccessProvinces().get(j2));
                                        ++possibleMoveTo_OwnProvinces;
                                        continue;
                                    }
                                    ((List)possibleProvinceMoveTo.get(CFG.core.getProv(CFG.core.getProv(civAtWarSeaAccessI.getSeaAccessProvinces().get(j2)).getNeighSeaProvinces(k)).getBasinID())).add(civAtWarSeaAccessI.getSeaAccessProvinces().get(j2));
                                    ++possibleMoveTo;
                                }
                            }
                        }
                        if (possibleMoveTo + possibleMoveTo_OwnProvinces == 0) {
                            return;
                        }
                        int iBestProvinceID_MoveTo = -1;
                        float fBestProvinceID_MoveTo_Score = -1.0f;
                        if (possibleMoveTo > 0 || possibleMoveTo_OwnProvinces > 0) {
                            int j3;
                            int i3;
                            float tempScore = 0.0f;
                            for (i3 = possibleProvinceMoveTo_OwnProvinces.size() - 1; i3 >= 0; --i3) {
                                for (j3 = ((List)possibleProvinceMoveTo_OwnProvinces.get(i3)).size() - 1; j3 >= 0; --j3) {
                                    tempScore = this.moveAtWar_AtSea_ToProvinceID_Score(nCivID, (Integer)((List)possibleProvinceMoveTo_OwnProvinces.get(i3)).get(j3), true);
                                    if (!(tempScore > fBestProvinceID_MoveTo_Score)) continue;
                                    iBestProvinceID_MoveTo = (Integer)((List)possibleProvinceMoveTo_OwnProvinces.get(i3)).get(j3);
                                    fBestProvinceID_MoveTo_Score = tempScore;
                                }
                            }
                            for (i3 = possibleProvinceMoveTo.size() - 1; i3 >= 0; --i3) {
                                for (j3 = ((List)possibleProvinceMoveTo.get(i3)).size() - 1; j3 >= 0; --j3) {
                                    tempScore = this.moveAtWar_AtSea_ToProvinceID_Score(nCivID, (Integer)((List)possibleProvinceMoveTo.get(i3)).get(j3), false);
                                    if (!(tempScore > fBestProvinceID_MoveTo_Score)) continue;
                                    iBestProvinceID_MoveTo = (Integer)((List)possibleProvinceMoveTo.get(i3)).get(j3);
                                    fBestProvinceID_MoveTo_Score = tempScore;
                                }
                            }
                            if (iBestProvinceID_MoveTo >= 0) {
                                if (!CFG.core.isAlly(nCivID, CFG.core.getProv(iBestProvinceID_MoveTo).getCivId()) && civ.getRankPos() > CFG.core.getCiv(CFG.core.getProv(iBestProvinceID_MoveTo).getCivId()).getRankPos() && CFG.oR.nextInt(100) < 62) {
                                    civ.civGD.iNextPossibleNavalInvasionTurnID = GameCalendar.TURNID + 3 + CFG.oR.nextInt(4);
                                    return;
                                }
                                this.moveAtWar_AtSea_ToProvinceID(nCivID, iBestProvinceID_MoveTo);
                            }
                        }
                    }
                }
            }
        }
    }

    public final void moveAtWar_AtSea_ToProvinceID(int nCivID, int iBestProvinceID_MoveTo) {
        int i;
        ArrayList<Boolean> haveAccessToBasins = new ArrayList<Boolean>();
        for (i = 0; i < CFG.map.numOfBasins; ++i) {
            haveAccessToBasins.add(false);
        }
        for (i = 0; i < CFG.core.getProv(iBestProvinceID_MoveTo).getNeighSeaProvincesSize(); ++i) {
            haveAccessToBasins.set(CFG.core.getProv(CFG.core.getProv(iBestProvinceID_MoveTo).getNeighSeaProvinces(i)).getBasinID(), true);
        }
        int iBestProvinceID_MoveFrom = -1;
        float fBestProvinceID_MoveTo_Score = -1.0f;
        int tempPossibleToRecruit = (int)(CFG.core.getCiv(nCivID).getGold() > (long)GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT && CFG.core.getCiv((int)nCivID).iBudget > 0 ? (float)(CFG.core.getCiv(nCivID).getGold() / (long)GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT) * 0.8f : 0.0f);
        block2: for (int i2 = CFG.core.getCiv(nCivID).getSeaAccess_Provinces_Size() - 1; i2 >= 0; --i2) {
            for (int j = 0; j < CFG.core.getProv(CFG.core.getCiv(nCivID).getSeaAccessProvinces().get(i2)).getNeighSeaProvincesSize(); ++j) {
                float tempScore;
                if (!((Boolean)haveAccessToBasins.get(CFG.core.getProv(CFG.core.getProv(CFG.core.getCiv(nCivID).getSeaAccessProvinces().get(i2)).getNeighSeaProvinces(j)).getBasinID())).booleanValue()) continue;
                if (CFG.core.getCiv(nCivID).getCivRegion(CFG.core.getProv(CFG.core.getCiv(nCivID).getSeaAccessProvinces().get(i2)).getCivRegionID()).checkRegionBordersWithEnemy(nCivID) || !((tempScore = this.moveAtWar_AtSea_FromProvinceID_Score(nCivID, CFG.core.getCiv(nCivID).getSeaAccessProvinces().get(i2), iBestProvinceID_MoveTo, false, tempPossibleToRecruit)) > fBestProvinceID_MoveTo_Score)) continue block2;
                iBestProvinceID_MoveFrom = CFG.core.getCiv(nCivID).getSeaAccessProvinces().get(i2);
                fBestProvinceID_MoveTo_Score = tempScore;
                continue block2;
            }
        }
        if (iBestProvinceID_MoveFrom >= 0) {
            if (CFG.core.getProv(iBestProvinceID_MoveFrom).getLvlOfPort() <= 0) {
                int i3;
                boolean newFound = false;
                for (i3 = 0; i3 < CFG.core.getProv(iBestProvinceID_MoveFrom).getNeighProvincesSize(); ++i3) {
                    if (CFG.core.getProv(CFG.core.getProv(iBestProvinceID_MoveFrom).getNeighProvinces(i3)).getCivId() != nCivID || CFG.core.getProv(CFG.core.getProv(iBestProvinceID_MoveFrom).getNeighProvinces(i3)).getLvlOfPort() <= 0) continue;
                    iBestProvinceID_MoveFrom = CFG.core.getProv(iBestProvinceID_MoveFrom).getNeighProvinces(i3);
                    newFound = true;
                    break;
                }
                if (!newFound) {
                    block5: for (i3 = 0; i3 < CFG.core.getProv(iBestProvinceID_MoveFrom).getNeighProvincesSize(); ++i3) {
                        if (CFG.core.getProv(CFG.core.getProv(iBestProvinceID_MoveFrom).getNeighProvinces(i3)).getCivId() != nCivID || CFG.core.getProv(CFG.core.getProv(iBestProvinceID_MoveFrom).getNeighProvinces(i3)).getLvlOfPort() <= 0) continue;
                        for (int j = 0; j < CFG.core.getProv(CFG.core.getProv(iBestProvinceID_MoveFrom).getNeighProvinces(i3)).getNeighProvincesSize(); ++j) {
                            if (CFG.core.getProv(CFG.core.getProv(CFG.core.getProv(iBestProvinceID_MoveFrom).getNeighProvinces(i3)).getNeighProvinces(j)).getCivId() != nCivID || CFG.core.getProv(CFG.core.getProv(CFG.core.getProv(iBestProvinceID_MoveFrom).getNeighProvinces(i3)).getNeighProvinces(j)).getLvlOfPort() <= 0) continue;
                            iBestProvinceID_MoveFrom = CFG.core.getProv(CFG.core.getProv(iBestProvinceID_MoveFrom).getNeighProvinces(i3)).getNeighProvinces(j);
                            i3 = CFG.core.getProv(iBestProvinceID_MoveFrom).getNeighProvincesSize();
                            continue block5;
                        }
                    }
                }
            }
            CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.add(new CivArmyMission_NavalInvasion(nCivID, iBestProvinceID_MoveFrom, iBestProvinceID_MoveTo));
            CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.get(CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.size() - 1).action(nCivID);
        }
    }

    public final float moveAtWar_AtSea_ToProvinceID_Score(int nCivID, int nProvince, boolean ownProvince) {
        return ((float)CFG.core.getProv(nProvince).getPotential() + (float)(CFG.core.getProv(nProvince).getPotentialRegion() * CFG.core.getCiv(CFG.core.getProv(nProvince).getCivId()).getCivRegion(CFG.core.getProv(nProvince).getCivRegionID()).getProvincesSize()) / (float)CFG.core.getCiv(CFG.core.getProv(nProvince).getCivId()).getNumOfProvs()) * (ownProvince ? 1.625f : (CFG.core.getProv(nProvince).isOccupied() ? 0.725f : 1.0f)) * (CFG.core.getProv(nProvince).isCapital() ? (CFG.core.getProv(nProvince).getCivId() != nCivID ? 0.725f : 1.45f) : 1.0f) * (1.0f - CFG.core.getCiv((int)nCivID).civGD.civPers.WAR_ATTACK_NAVAL_DISTANCE * Distance.getDistanceFromAToB_PercOfMax(CFG.gameUpdate.getAdministration_Capital(nCivID), nProvince));
    }

    public final float moveAtWar_AtSea_FromProvinceID_Score(int nCivID, int nProvince, int toProvinceID, boolean ownProvince, int tempPossibleToRecruit) {
        return (float)CFG.core.getProv(nProvince).getPotential() * (0.375f + 0.625f * (float)((CFG.core.getProv(nProvince).getArmyCivID1(nCivID) + (CFG.core.getProv(nProvince).isOccupied() ? 0 : tempPossibleToRecruit)) / Math.max(1, CFG.core.getCiv(nCivID).getNumberOfUnits() + tempPossibleToRecruit))) * (CFG.core.getProv(nProvince).getLvlOfPort() > 0 ? 1.5f : 1.0f) * (1.0f - Distance.getDistanceFromAToB_PercOfMax(nProvince, toProvinceID) / 2.0f);
    }

    public final int moveAtWar_NumOfNotCoveredNeighEnemyProvinces(int nCivID, int nProvinceID) {
        int out = 0;
        for (int j = 0; j < CFG.core.getProv(nProvinceID).getNeighProvincesSize(); ++j) {
            if (!CFG.core.getCivsAtWar(nCivID, CFG.core.getProv(CFG.core.getProv(nProvinceID).getNeighProvinces(j)).getCivId()) || CFG.core.getCiv(nCivID).isMovingUnitsToProvID(CFG.core.getProv(nProvinceID).getNeighProvinces(j))) continue;
            ++out;
        }
        return out;
    }

    public final float moveAtWar_AttackTo_Score(int nCivID, int toProvinceID) {
        return CFG.core.getProv(toProvinceID).getPotentialModified_WAR_MoveTo(nCivID);
    }

    public final void prepareForWar_Regroup(int nCivID, List<AI_ProvinceInfo_War> sortedFrontProvinces, List<Integer> lFrontIDsWithArmies) {
        try {
            if (CFG.core.getCiv((int)nCivID).civGD.iRegroupArmyAtPeace_CheckTurnID <= GameCalendar.TURNID) {
                int tArmyToRegroup;
                int i;
                ArrayList<AI_RegoupArmyData> armiesWithoutDanger = new ArrayList<AI_RegoupArmyData>();
                ArrayList armiesInAnotherTerritory = new ArrayList();
                for (i = 0; i < CFG.core.getCiv((int)nCivID).armiesPositionSize; ++i) {
                    tArmyToRegroup = this.getRegroupArmy_NumOfUnits(nCivID, CFG.core.getCiv((int)nCivID).armiesPosition.get(i));
                    if (tArmyToRegroup <= 0 || CFG.oAI.prepareForWar_BordersWithEnemy(nCivID, CFG.core.getCiv((int)nCivID).armiesPosition.get(i))) continue;
                    armiesWithoutDanger.add(new AI_RegoupArmyData(CFG.core.getCiv((int)nCivID).armiesPosition.get(i), tArmyToRegroup));
                }
                for (i = 0; i < CFG.core.getCiv(nCivID).getArmyInAnotherProvinceSize(); ++i) {
                    tArmyToRegroup = this.getRegroupArmy_NumOfUnits(nCivID, CFG.core.getCiv(nCivID).getArmyInAnotherProviP(i));
                    if (tArmyToRegroup <= 0 || CFG.oAI.prepareForWar_BordersWithEnemy(nCivID, CFG.core.getCiv(nCivID).getArmyInAnotherProviP(i))) continue;
                    boolean addN = true;
                    for (int z = armiesWithoutDanger.size() - 1; z >= 0; --z) {
                        if (((AI_RegoupArmyData)armiesWithoutDanger.get((int)z)).iProvinceID != CFG.core.getCiv(nCivID).getArmyInAnotherProviP(i)) continue;
                        addN = false;
                    }
                    if (!addN) continue;
                    armiesWithoutDanger.add(new AI_RegoupArmyData(CFG.core.getCiv(nCivID).getArmyInAnotherProviP(i), tArmyToRegroup));
                }
                if (armiesWithoutDanger.size() == CFG.core.getCiv(nCivID).getNumOfProvs()) {
                    armiesWithoutDanger.clear();
                }
                while (armiesWithoutDanger.size() > 0 || armiesInAnotherTerritory.size() > 0) {
                    int i2;
                    int highestArmyID = -1;
                    int highestArmy_Num = 0;
                    int highestArmy_ListID = -1;
                    for (i2 = armiesWithoutDanger.size() - 1; i2 >= 0; --i2) {
                        if (highestArmyID >= 0 && highestArmy_Num >= ((AI_RegoupArmyData)armiesWithoutDanger.get((int)i2)).iArmy) continue;
                        highestArmyID = i2;
                        highestArmy_Num = ((AI_RegoupArmyData)armiesWithoutDanger.get((int)i2)).iArmy;
                        highestArmy_ListID = 0;
                    }
                    for (i2 = armiesInAnotherTerritory.size() - 1; i2 >= 0; --i2) {
                        if (highestArmyID >= 0 && highestArmy_Num >= ((AI_RegoupArmyData)armiesInAnotherTerritory.get((int)i2)).iArmy) continue;
                        highestArmyID = i2;
                        highestArmy_Num = ((AI_RegoupArmyData)armiesInAnotherTerritory.get((int)i2)).iArmy;
                        highestArmy_ListID = 2;
                    }
                    if (highestArmyID >= 0 && highestArmy_ListID >= 0 && highestArmy_Num > 0) {
                        switch (highestArmy_ListID) {
                            case 0: {
                                this.regroupArmy_PrepareForWar_WithoutDanger(nCivID, (AI_RegoupArmyData)armiesWithoutDanger.get(highestArmyID));
                                armiesWithoutDanger.remove(highestArmyID);
                                break;
                            }
                            case 2: {
                                this.regroupArmy_PrepareForWar_WithoutDanger(nCivID, (AI_RegoupArmyData)armiesInAnotherTerritory.get(highestArmyID));
                                armiesInAnotherTerritory.remove(highestArmyID);
                            }
                        }
                    }
                    if (CFG.core.getCiv(nCivID).getMovemPoints() >= CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_MOVE && CFG.core.getCiv(nCivID).getMovemPoints() >= CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_MOVE_OWN_PROVINCE) continue;
                    return;
                }
                CFG.core.getCiv((int)nCivID).civGD.iRegroupArmyAtPeace_CheckTurnID = 0;
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    public final boolean moveAtWar_BordersWithEnemyCheck(int nCivID, int nProvinceID) {
        Province province = CFG.core.getProv(nProvinceID);
        for (int i = 0; i < province.getNeighProvincesSize(); ++i) {
            if (!CFG.core.getCivsAtWar(nCivID, CFG.core.getProv(province.getNeighProvinces(i)).getCivId())) continue;
            return true;
        }
        return false;
    }

    public final void moveAtWar_Regroup(int nCivID, List<AI_ProvinceInfo_War> sortedFrontProvinces, List<Integer> lFrontIDsWithArmies) {
        try {
            if (CFG.core.getCiv((int)nCivID).civGD.iRegroupArmyAtPeace_CheckTurnID <= GameCalendar.TURNID) {
                int i;
                int highestArmy_ListID;
                int highestArmy_Num;
                int highestArmyID;
                int tArmyToRegroup;
                int i2;
                ArrayList<AI_RegoupArmyData> armiesWithoutDanger = new ArrayList<AI_RegoupArmyData>();
                ArrayList<AI_RegoupArmyData> armiesInAnotherTerritory = new ArrayList<AI_RegoupArmyData>();
                ArrayList<AI_RegoupArmyData> armiesAtSea = new ArrayList<AI_RegoupArmyData>();
                for (i2 = 0; i2 < CFG.core.getCiv((int)nCivID).armiesPositionSize; ++i2) {
                    tArmyToRegroup = this.getRegroupArmy_NumOfUnits(nCivID, CFG.core.getCiv((int)nCivID).armiesPosition.get(i2));
                    if (tArmyToRegroup <= 0) continue;
                    if (CFG.core.getProv(CFG.core.getCiv((int)nCivID).armiesPosition.get(i2)).getSeaProv()) {
                        armiesAtSea.add(new AI_RegoupArmyData(CFG.core.getCiv((int)nCivID).armiesPosition.get(i2), tArmyToRegroup));
                        continue;
                    }
                    if (CFG.core.getProv(CFG.core.getCiv((int)nCivID).armiesPosition.get(i2)).getCivId() != nCivID && !CFG.core.getProv(CFG.core.getCiv((int)nCivID).armiesPosition.get(i2)).getBordersWithEnemy() && !this.moveAtWar_BordersWithEnemyCheck(nCivID, CFG.core.getCiv((int)nCivID).armiesPosition.get(i2))) {
                        armiesInAnotherTerritory.add(new AI_RegoupArmyData(CFG.core.getCiv((int)nCivID).armiesPosition.get(i2), tArmyToRegroup));
                        continue;
                    }
                    if (CFG.core.getProv(CFG.core.getCiv((int)nCivID).armiesPosition.get(i2)).getBordersWithEnemy() || this.moveAtWar_BordersWithEnemyCheck(nCivID, CFG.core.getCiv((int)nCivID).armiesPosition.get(i2))) continue;
                    armiesWithoutDanger.add(new AI_RegoupArmyData(CFG.core.getCiv((int)nCivID).armiesPosition.get(i2), tArmyToRegroup));
                }
                for (i2 = 0; i2 < CFG.core.getCiv(nCivID).getArmyInAnotherProvinceSize(); ++i2) {
                    tArmyToRegroup = this.getRegroupArmy_NumOfUnits(nCivID, CFG.core.getCiv(nCivID).getArmyInAnotherProviP(i2));
                    if (tArmyToRegroup <= 0) continue;
                    if (CFG.core.getProv(CFG.core.getCiv(nCivID).getArmyInAnotherProviP(i2)).getSeaProv()) {
                        armiesAtSea.add(new AI_RegoupArmyData(CFG.core.getCiv(nCivID).getArmyInAnotherProviP(i2), tArmyToRegroup));
                        continue;
                    }
                    if (CFG.core.getProv(CFG.core.getCiv(nCivID).getArmyInAnotherProviP(i2)).getCivId() != nCivID && !CFG.core.getProv(CFG.core.getCiv(nCivID).getArmyInAnotherProviP(i2)).getBordersWithEnemy() && !this.moveAtWar_BordersWithEnemyCheck(nCivID, CFG.core.getCiv(nCivID).getArmyInAnotherProviP(i2))) {
                        armiesInAnotherTerritory.add(new AI_RegoupArmyData(CFG.core.getCiv(nCivID).getArmyInAnotherProviP(i2), tArmyToRegroup));
                        continue;
                    }
                    if (CFG.core.getProv(CFG.core.getCiv(nCivID).getArmyInAnotherProviP(i2)).getDangerLvl() != 0 || CFG.core.getProv(CFG.core.getCiv(nCivID).getArmyInAnotherProviP(i2)).getBordersWithEnemy() || this.moveAtWar_BordersWithEnemyCheck(nCivID, CFG.core.getCiv(nCivID).getArmyInAnotherProviP(i2))) continue;
                    armiesWithoutDanger.add(new AI_RegoupArmyData(CFG.core.getCiv(nCivID).getArmyInAnotherProviP(i2), tArmyToRegroup));
                }
                if (armiesWithoutDanger.size() == CFG.core.getCiv(nCivID).getNumOfProvs()) {
                    armiesWithoutDanger.clear();
                }
                while (!armiesAtSea.isEmpty()) {
                    highestArmyID = -1;
                    highestArmy_Num = 0;
                    highestArmy_ListID = -1;
                    for (i = armiesAtSea.size() - 1; i >= 0; --i) {
                        if (highestArmyID >= 0 && highestArmy_Num >= ((AI_RegoupArmyData)armiesAtSea.get((int)i)).iArmy) continue;
                        highestArmyID = i;
                        highestArmy_Num = ((AI_RegoupArmyData)armiesAtSea.get((int)i)).iArmy;
                        highestArmy_ListID = 1;
                    }
                    if (highestArmyID >= 0 && highestArmy_ListID >= 0 && highestArmy_Num > 0) {
                        switch (highestArmy_ListID) {
                            case 1: {
                                this.regroupArmy_AtWar_AtSea(nCivID, (AI_RegoupArmyData)armiesAtSea.get(highestArmyID));
                                armiesAtSea.remove(highestArmyID);
                            }
                        }
                    }
                    if (CFG.core.getCiv(nCivID).getMovemPoints() >= CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_MOVE && CFG.core.getCiv(nCivID).getMovemPoints() >= CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_MOVE_OWN_PROVINCE) continue;
                    return;
                }
                while (!armiesWithoutDanger.isEmpty() || !armiesInAnotherTerritory.isEmpty()) {
                    highestArmyID = -1;
                    highestArmy_Num = 0;
                    highestArmy_ListID = -1;
                    for (i = armiesWithoutDanger.size() - 1; i >= 0; --i) {
                        if (highestArmyID >= 0 && highestArmy_Num >= ((AI_RegoupArmyData)armiesWithoutDanger.get((int)i)).iArmy) continue;
                        highestArmyID = i;
                        highestArmy_Num = ((AI_RegoupArmyData)armiesWithoutDanger.get((int)i)).iArmy;
                        highestArmy_ListID = 0;
                    }
                    for (i = armiesInAnotherTerritory.size() - 1; i >= 0; --i) {
                        if (highestArmyID >= 0 && highestArmy_Num >= ((AI_RegoupArmyData)armiesInAnotherTerritory.get((int)i)).iArmy) continue;
                        highestArmyID = i;
                        highestArmy_Num = ((AI_RegoupArmyData)armiesInAnotherTerritory.get((int)i)).iArmy;
                        highestArmy_ListID = 2;
                    }
                    if (highestArmyID >= 0 && highestArmy_ListID >= 0 && highestArmy_Num > 0) {
                        switch (highestArmy_ListID) {
                            case 0: {
                                this.regroupArmy_AtWar_WithoutDanger(nCivID, (AI_RegoupArmyData)armiesWithoutDanger.get(highestArmyID));
                                armiesWithoutDanger.remove(highestArmyID);
                                break;
                            }
                            case 2: {
                                this.regroupArmy_AtWar_WithoutDanger(nCivID, (AI_RegoupArmyData)armiesInAnotherTerritory.get(highestArmyID));
                                armiesInAnotherTerritory.remove(highestArmyID);
                            }
                        }
                    }
                    if (CFG.core.getCiv(nCivID).getMovemPoints() >= CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_MOVE && CFG.core.getCiv(nCivID).getMovemPoints() >= CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_MOVE_OWN_PROVINCE) continue;
                    return;
                }
                CFG.core.getCiv((int)nCivID).civGD.iRegroupArmyAtPeace_CheckTurnID = 0;
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    public final boolean regroupArmy_AtWar_AtSea(int nCivID, AI_RegoupArmyData nArmy) {
        int tMaxArmy;
        int i;
        ArrayList<AI_ProvinceInfo> possibleMoveTo = new ArrayList<AI_ProvinceInfo>();
        Province provinceArmy = CFG.core.getProv(nArmy.iProvinceID);
        for (i = 0; i < provinceArmy.getNeighProvincesSize(); ++i) {
            Province provinceArmyNeighI = CFG.core.getProv(provinceArmy.getNeighProvinces(i));
            if (provinceArmyNeighI.getCivId() != nCivID && !CFG.core.getCivsAtWar(nCivID, provinceArmyNeighI.getCivId())) continue;
            possibleMoveTo.add(new AI_ProvinceInfo(provinceArmy.getNeighProvinces(i), this.getPotential_BasedOnNeighboringProvs(provinceArmy.getNeighProvinces(i), nCivID), 1));
        }
        if (!possibleMoveTo.isEmpty()) {
            int tArmyToRecruit_PRE;
            int i2;
            int i3;
            tMaxArmy = 1;
            float tMaxPotential = 1.0f;
            float tMaxRegion_NumOfProvinces = 1.0f;
            float tMaxRegion_Potential = 1.0f;
            int tMaxDL = 1;
            ArrayList<Integer> tMovingArmy = new ArrayList<Integer>();
            int iSize = possibleMoveTo.size();
            int tempMovingArmy = 0;
            for (i3 = 0; i3 < iSize; ++i3) {
                if (((AI_ProvinceInfo)possibleMoveTo.get((int)i3)).iValue > tMaxPotential) {
                    tMaxPotential = ((AI_ProvinceInfo)possibleMoveTo.get((int)i3)).iValue;
                }
                if (CFG.core.getProv(((AI_ProvinceInfo)possibleMoveTo.get((int)i3)).iProvinceID).getDangerLevel_WithArmy() > tMaxDL) {
                    tMaxDL = CFG.core.getProv(((AI_ProvinceInfo)possibleMoveTo.get((int)i3)).iProvinceID).getDangerLevel_WithArmy();
                }
                if ((float)CFG.core.getProv(((AI_ProvinceInfo)possibleMoveTo.get((int)i3)).iProvinceID).getRegion_NumOfProvinces() > tMaxRegion_NumOfProvinces) {
                    tMaxRegion_NumOfProvinces = CFG.core.getProv(((AI_ProvinceInfo)possibleMoveTo.get((int)i3)).iProvinceID).getRegion_NumOfProvinces();
                }
                if ((float)CFG.core.getProv(((AI_ProvinceInfo)possibleMoveTo.get((int)i3)).iProvinceID).getPotentialRegion() > tMaxRegion_Potential) {
                    tMaxRegion_Potential = CFG.core.getProv(((AI_ProvinceInfo)possibleMoveTo.get((int)i3)).iProvinceID).getPotentialRegion();
                }
                tMovingArmy.add(tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, ((AI_ProvinceInfo)possibleMoveTo.get((int)i3)).iProvinceID));
                if (CFG.core.getProv(((AI_ProvinceInfo)possibleMoveTo.get((int)i3)).iProvinceID).getArmyID(0) + tempMovingArmy <= tMaxArmy) continue;
                tMaxArmy = CFG.core.getProv(((AI_ProvinceInfo)possibleMoveTo.get((int)i3)).iProvinceID).getArmyID(0) + tempMovingArmy;
            }
            iSize = possibleMoveTo.size();
            for (i3 = 0; i3 < iSize; ++i3) {
                ((AI_ProvinceInfo)possibleMoveTo.get((int)i3)).iValue = this.getValue_PositionOfArmy(nCivID, possibleMoveTo, i3, (Integer)tMovingArmy.get(i3), tMaxPotential, tMaxRegion_Potential, tMaxDL, tMaxArmy, nArmy.iArmy, nArmy.iArmy);
            }
            ArrayList<AI_ProvinceInfo> sortedFrontProvinces = new ArrayList<AI_ProvinceInfo>();
            while (!possibleMoveTo.isEmpty()) {
                int tBest = 0;
                int iSize2 = possibleMoveTo.size();
                for (int i4 = 1; i4 < iSize2; ++i4) {
                    if (!(((AI_ProvinceInfo)possibleMoveTo.get((int)tBest)).iValue < ((AI_ProvinceInfo)possibleMoveTo.get((int)i4)).iValue)) continue;
                    tBest = i4;
                }
                sortedFrontProvinces.add((AI_ProvinceInfo)possibleMoveTo.get(tBest));
                possibleMoveTo.remove(tBest);
            }
            float percOfArmyToRegroup = Math.max((float)nArmy.iArmy / (float)CFG.core.getCiv(nCivID).getNumberOfUnits(), 0.01f);
            int iNumOfMaxMovements = 1;
            if (GameValues.gvAiArmy.REGROUP_AT_PEACE_MAX_ONE_MOVE_IF_PERC_OF_ARMY > percOfArmyToRegroup) {
                iNumOfMaxMovements = 1;
            } else {
                iNumOfMaxMovements = Math.max(1, Math.min((CFG.core.getCiv(nCivID).getMovemPoints() - CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_MOVE) / (CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_MOVE * 2), 1 + CFG.oR.nextInt(3)));
                iNumOfMaxMovements = percOfArmyToRegroup > 0.34f ? Math.min(iNumOfMaxMovements, 4) : (percOfArmyToRegroup > 0.24f ? Math.min(iNumOfMaxMovements, 3) : (percOfArmyToRegroup > 0.1f ? Math.min(iNumOfMaxMovements, 2) : Math.min(iNumOfMaxMovements, 1)));
            }
            ArrayList<AI_ProvinceInfo> tRecruitArmiesForProvinces = new ArrayList<AI_ProvinceInfo>();
            float totalValues = 0.0f;
            for (i2 = 0; i2 < iNumOfMaxMovements && i2 < sortedFrontProvinces.size(); ++i2) {
                tRecruitArmiesForProvinces.add((AI_ProvinceInfo)sortedFrontProvinces.get(i2));
                totalValues += ((AI_ProvinceInfo)sortedFrontProvinces.get((int)i2)).iValue;
            }
            for (i2 = 0; i2 < tRecruitArmiesForProvinces.size() && (tArmyToRecruit_PRE = (int)Math.ceil((float)nArmy.iArmy * ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i2)).iValue / totalValues)) > 0; ++i2) {
                RegroupArmy_AtWar tryRegroupArmy = new RegroupArmy_AtWar(nCivID, nArmy.iProvinceID, ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i2)).iProvinceID);
                if (tryRegroupArmy.getRouteSize() <= 0) continue;
                if (tryRegroupArmy.getRouteSize() == 1) {
                    if (CFG.gameAction.moveArmyAction(nArmy.iProvinceID, ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i2)).iProvinceID, tArmyToRecruit_PRE, nCivID, true, false)) continue;
                    continue;
                }
                if (!CFG.gameAction.moveArmyAction(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), tArmyToRecruit_PRE, nCivID, true, false)) continue;
                tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
                tryRegroupArmy.removeRoute(0);
                tryRegroupArmy.setNumOfUnits(tArmyToRecruit_PRE);
                CFG.core.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
            }
            return true;
        }
        block7: for (i = CFG.core.getCiv(nCivID).getSeaAccess_Provinces_Size() - 1; i >= 0; --i) {
            Province provinceSeaAccessI = CFG.core.getProv(CFG.core.getCiv(nCivID).getSeaAccessProvinces().get(i));
            for (int j = 0; j < provinceSeaAccessI.getNeighSeaProvincesSize(); ++j) {
                if (CFG.core.getProv(provinceSeaAccessI.getNeighSeaProvinces(j)).getBasinID() != CFG.core.getProv(nArmy.iProvinceID).getBasinID()) continue;
                possibleMoveTo.add(new AI_ProvinceInfo(CFG.core.getCiv(nCivID).getSeaAccessProvinces().get(i), this.getPotential_BasedOnNeighboringProvs(CFG.core.getCiv(nCivID).getSeaAccessProvinces().get(i), nCivID), 1));
                continue block7;
            }
        }
        if (!possibleMoveTo.isEmpty()) {
            RegroupArmy_AtWar tryRegroupArmy;
            int i5;
            tMaxArmy = 1;
            float tMaxPotential = 1.0f;
            float tMaxRegion_NumOfProvinces = 1.0f;
            float tMaxRegion_Potential = 1.0f;
            int tMaxDL = 1;
            ArrayList<Integer> tMovingArmy = new ArrayList<Integer>();
            int iSize = possibleMoveTo.size();
            int tempMovingArmy = 0;
            for (i5 = 0; i5 < iSize; ++i5) {
                Province provincePossibleMoveToI;
                if (((AI_ProvinceInfo)possibleMoveTo.get((int)i5)).iValue > tMaxPotential) {
                    tMaxPotential = ((AI_ProvinceInfo)possibleMoveTo.get((int)i5)).iValue;
                }
                if ((provincePossibleMoveToI = CFG.core.getProv(((AI_ProvinceInfo)possibleMoveTo.get((int)i5)).iProvinceID)).getDangerLevel_WithArmy() > tMaxDL) {
                    tMaxDL = provincePossibleMoveToI.getDangerLevel_WithArmy();
                }
                if ((float)provincePossibleMoveToI.getRegion_NumOfProvinces() > tMaxRegion_NumOfProvinces) {
                    tMaxRegion_NumOfProvinces = provincePossibleMoveToI.getRegion_NumOfProvinces();
                }
                if ((float)provincePossibleMoveToI.getPotentialRegion() > tMaxRegion_Potential) {
                    tMaxRegion_Potential = provincePossibleMoveToI.getPotentialRegion();
                }
                tMovingArmy.add(tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, ((AI_ProvinceInfo)possibleMoveTo.get((int)i5)).iProvinceID));
                if (provincePossibleMoveToI.getArmyID(0) + tempMovingArmy <= tMaxArmy) continue;
                tMaxArmy = provincePossibleMoveToI.getArmyID(0) + tempMovingArmy;
            }
            iSize = possibleMoveTo.size();
            for (i5 = 0; i5 < iSize; ++i5) {
                ((AI_ProvinceInfo)possibleMoveTo.get((int)i5)).iValue = this.getValue_PositionOfArmy(nCivID, possibleMoveTo, i5, (Integer)tMovingArmy.get(i5), tMaxPotential, tMaxRegion_Potential, tMaxDL, tMaxArmy, nArmy.iArmy, nArmy.iArmy);
            }
            ArrayList<AI_ProvinceInfo> sortedFrontProvinces = new ArrayList<AI_ProvinceInfo>();
            if (!possibleMoveTo.isEmpty()) {
                int tBest = 0;
                int iSize3 = possibleMoveTo.size();
                for (int i6 = 1; i6 < iSize3; ++i6) {
                    if (!(((AI_ProvinceInfo)possibleMoveTo.get((int)tBest)).iValue < ((AI_ProvinceInfo)possibleMoveTo.get((int)i6)).iValue)) continue;
                    tBest = i6;
                }
                sortedFrontProvinces.add((AI_ProvinceInfo)possibleMoveTo.get(tBest));
                possibleMoveTo.remove(tBest);
            }
            if ((tryRegroupArmy = new RegroupArmy_AtWar(nCivID, nArmy.iProvinceID, ((AI_ProvinceInfo)sortedFrontProvinces.get((int)0)).iProvinceID)).getRouteSize() > 0) {
                if (tryRegroupArmy.getRouteSize() == 1) {
                    if (!CFG.gameAction.moveArmyAction(nArmy.iProvinceID, ((AI_ProvinceInfo)sortedFrontProvinces.get((int)0)).iProvinceID, nArmy.iArmy, nCivID, true, false)) {
                        // empty if block
                    }
                } else if (CFG.gameAction.moveArmyAction(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), nArmy.iArmy, nCivID, true, false)) {
                    tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
                    tryRegroupArmy.removeRoute(0);
                    tryRegroupArmy.setNumOfUnits(nArmy.iArmy);
                    CFG.core.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
                }
            }
            return true;
        }
        if (CFG.core.getCiv(nCivID).getCapitalProvID() >= 0 && CFG.core.getProv(CFG.core.getCiv(nCivID).getCapitalProvID()).getCivId() == nCivID) {
            RegroupArmy_AtWar tryRegroupArmy = new RegroupArmy_AtWar(nCivID, nArmy.iProvinceID, CFG.core.getCiv(nCivID).getCapitalProvID());
            if (tryRegroupArmy.getRouteSize() > 0) {
                if (tryRegroupArmy.getRouteSize() == 1) {
                    if (!CFG.gameAction.moveArmyAction(nArmy.iProvinceID, CFG.core.getCiv(nCivID).getCapitalProvID(), nArmy.iArmy, nCivID, true, false)) {
                        // empty if block
                    }
                } else if (CFG.gameAction.moveArmyAction(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), nArmy.iArmy, nCivID, true, false)) {
                    tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
                    tryRegroupArmy.removeRoute(0);
                    tryRegroupArmy.setNumOfUnits(nArmy.iArmy);
                    CFG.core.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
                }
            } else {
                CFG.gameAction.disbandArmy(nArmy.iProvinceID, nArmy.iArmy, nCivID);
            }
        } else {
            CFG.gameAction.disbandArmy(nArmy.iProvinceID, nArmy.iArmy, nCivID);
        }
        return true;
    }

    public final boolean regroupArmy_PrepareForWar_WithoutDanger(int nCivID, AI_RegoupArmyData nArmy) {
        try {
            int i;
            int i2;
            int i3;
            float percOfArmyToRegroup = Math.max((float)nArmy.iArmy / (float)CFG.core.getCiv(nCivID).getNumberOfUnits(), 0.01f);
            try {
                if (CFG.core.getCiv(nCivID).getCivRegion(CFG.core.getProv(nArmy.iProvinceID).getCivRegionID()).getProvincesSize() > 1) {
                    int tMaxDL = 1;
                    float tMaxPotential = 1.0f;
                    ArrayList<AI_ProvinceInfo> tempFrontProvinces = new ArrayList<AI_ProvinceInfo>();
                    for (i3 = CFG.core.getCiv((int)CFG.core.getProv((int)nArmy.iProvinceID).getCivId()).lFrontLines.size() - 1; i3 >= 0; --i3) {
                        for (int u = 0; u < CFG.core.getCiv((int)nCivID).civGD.civPlans.iWarPrepsSize; ++u) {
                            if (CFG.core.getCiv((int)CFG.core.getProv((int)nArmy.iProvinceID).getCivId()).lFrontLines.get((int)i3).iWithCivID != CFG.core.getCiv((int)nCivID).civGD.civPlans.warPreps.get((int)u).onCivID) continue;
                            try {
                                if (CFG.core.getProv(CFG.core.getCiv((int)CFG.core.getProv((int)nArmy.iProvinceID).getCivId()).lFrontLines.get((int)i3).lProvinces.get(0)).getCivRegionID() != CFG.core.getProv(nArmy.iProvinceID).getCivRegionID()) continue;
                                for (int j = CFG.core.getCiv((int)CFG.core.getProv((int)nArmy.iProvinceID).getCivId()).lFrontLines.get((int)i3).lProvinces.size() - 1; j >= 0; --j) {
                                    boolean wasAdded = false;
                                    for (int k = tempFrontProvinces.size() - 1; k >= 0; --k) {
                                        if (((AI_ProvinceInfo)tempFrontProvinces.get((int)k)).iProvinceID != CFG.core.getCiv((int)CFG.core.getProv((int)nArmy.iProvinceID).getCivId()).lFrontLines.get((int)i3).lProvinces.get(j)) continue;
                                        wasAdded = true;
                                        break;
                                    }
                                    if (wasAdded) continue;
                                    tempFrontProvinces.add(new AI_ProvinceInfo(CFG.core.getCiv((int)CFG.core.getProv((int)nArmy.iProvinceID).getCivId()).lFrontLines.get((int)i3).lProvinces.get(j), 1, 1));
                                }
                                continue;
                            }
                            catch (IndexOutOfBoundsException j) {
                                // empty catch block
                            }
                        }
                    }
                    if (tempFrontProvinces.size() > 0) {
                        int tArmyToRecruit_PRE;
                        int i4;
                        int tMaxArmy = 1;
                        ArrayList<Integer> tMovingArmy = new ArrayList<Integer>();
                        int iSize = tempFrontProvinces.size();
                        int tempMovingArmy = 0;
                        for (i2 = 0; i2 < iSize; ++i2) {
                            if (((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iValue > tMaxPotential) {
                                tMaxPotential = ((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iValue;
                            }
                            if (CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iProvinceID).getDangerLevel_WithArmy() > tMaxDL) {
                                tMaxDL = CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iProvinceID).getDangerLevel_WithArmy();
                            }
                            tMovingArmy.add(tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, ((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iProvinceID));
                            if (CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iProvinceID).getArmyID(0) + tempMovingArmy <= tMaxArmy) continue;
                            tMaxArmy = CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iProvinceID).getArmyID(0) + tempMovingArmy;
                        }
                        iSize = tempFrontProvinces.size();
                        for (i2 = 0; i2 < iSize; ++i2) {
                            ((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iValue = (1.0f - (float)(CFG.core.getProvinceArmy(((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iProvinceID) + (Integer)tMovingArmy.get(i2)) / (float)tMaxArmy + 0.2f * ((float)CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iProvinceID).getDangerLvl() / (float)tMaxDL) + 0.2f * ((float)CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iProvinceID).getPotentialModified_WAR_MoveFrom(nCivID) / tMaxPotential) + 0.2f * (float)CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iProvinceID).getNeighProvinceOfCivWasLost()) * (float)((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iRecruitable == 0.0f ? 0.725f : 1.0f;
                        }
                        ArrayList<AI_ProvinceInfo> sortedFrontProvinces = new ArrayList<AI_ProvinceInfo>();
                        while (tempFrontProvinces.size() > 0) {
                            int tBest = 0;
                            int iSize2 = tempFrontProvinces.size();
                            for (i = 1; i < iSize2; ++i) {
                                if (!(((AI_ProvinceInfo)tempFrontProvinces.get((int)tBest)).iValue < ((AI_ProvinceInfo)tempFrontProvinces.get((int)i)).iValue)) continue;
                                tBest = i;
                            }
                            sortedFrontProvinces.add((AI_ProvinceInfo)tempFrontProvinces.get(tBest));
                            tempFrontProvinces.remove(tBest);
                        }
                        int iNumOfMaxMovements = 1;
                        if (GameValues.gvAiArmy.REGROUP_AT_PEACE_MAX_ONE_MOVE_IF_PERC_OF_ARMY > percOfArmyToRegroup) {
                            iNumOfMaxMovements = 1;
                        } else {
                            iNumOfMaxMovements = Math.max(1, Math.min((CFG.core.getCiv(nCivID).getMovemPoints() - CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_MOVE) / CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_MOVE_OWN_PROVINCE, Math.min(CFG.core.getCiv(nCivID).getNumOfProvs(), 2 + CFG.oR.nextInt(3))));
                            iNumOfMaxMovements = percOfArmyToRegroup > 0.4f ? Math.min(iNumOfMaxMovements, 4) : (percOfArmyToRegroup > 0.3f ? Math.min(iNumOfMaxMovements, 3) : (percOfArmyToRegroup > 0.2f ? Math.min(iNumOfMaxMovements, 2) : Math.min(iNumOfMaxMovements, 1)));
                        }
                        ArrayList<AI_ProvinceInfo> tRecruitArmiesForProvinces = new ArrayList<AI_ProvinceInfo>();
                        float totalValues = 0.0f;
                        for (i4 = 0; i4 < iNumOfMaxMovements && i4 < sortedFrontProvinces.size(); ++i4) {
                            tRecruitArmiesForProvinces.add((AI_ProvinceInfo)sortedFrontProvinces.get(i4));
                            totalValues += ((AI_ProvinceInfo)sortedFrontProvinces.get((int)i4)).iValue;
                        }
                        for (i4 = 0; i4 < tRecruitArmiesForProvinces.size() && (tArmyToRecruit_PRE = (int)Math.ceil((float)nArmy.iArmy * ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i4)).iValue / totalValues)) > 0; ++i4) {
                            RegroupArmy tryRegroupArmy = new RegroupArmy(nCivID, nArmy.iProvinceID, ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i4)).iProvinceID);
                            if (tryRegroupArmy.getRouteSize() <= 0) continue;
                            if (tryRegroupArmy.getRouteSize() == 1) {
                                if (CFG.gameAction.moveArmyAction(nArmy.iProvinceID, ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i4)).iProvinceID, tArmyToRecruit_PRE, nCivID, true, false)) continue;
                                continue;
                            }
                            if (!CFG.gameAction.moveArmyAction(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), tArmyToRecruit_PRE, nCivID, true, false)) continue;
                            tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
                            tryRegroupArmy.removeRoute(0);
                            tryRegroupArmy.setNumOfUnits(tArmyToRecruit_PRE);
                            CFG.core.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
                        }
                        return true;
                    }
                }
            }
            catch (NullPointerException tMaxDL) {
                // empty catch block
            }
            List<AI_NeighProvinces> listOfPossibleProvinces = this.getAllNeighboringProvincesInRange_RegroupPrepareForWAr(nArmy.iProvinceID, nCivID, CFG.core.getCiv((int)nCivID).civGD.civPers.REGROUP_AT_PEACE_MAX_PROVINCES + CFG.core.getCiv(nCivID).getNumOfProvs() / 15, new ArrayList<AI_NeighProvinces>(), new ArrayList<Integer>());
            if (listOfPossibleProvinces.size() > 0) {
                int nNumOfPossibleMovements = CFG.core.getCiv(nCivID).getMovemPoints() / CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_MOVE;
                nNumOfPossibleMovements = percOfArmyToRegroup > 0.54f ? Math.min(nNumOfPossibleMovements, 4) : (percOfArmyToRegroup > 0.35f ? Math.min(nNumOfPossibleMovements, 3) : (percOfArmyToRegroup > 0.25f ? Math.min(nNumOfPossibleMovements, 2) : Math.min(nNumOfPossibleMovements, 1)));
                boolean provincesWithDanger = false;
                for (i3 = listOfPossibleProvinces.size() - 1; i3 >= 0; --i3) {
                    if (CFG.core.getProv(listOfPossibleProvinces.get((int)i3).iProvinceID).getDangerLvl() <= 0) continue;
                    provincesWithDanger = true;
                    break;
                }
                if (provincesWithDanger) {
                    int i5;
                    ArrayList<Integer> tSortedIDs = new ArrayList<Integer>();
                    ArrayList<Integer> tData = new ArrayList<Integer>();
                    for (i2 = listOfPossibleProvinces.size() - 1; i2 >= 0; --i2) {
                        tData.add(i2);
                    }
                    while (tData.size() > 0) {
                        int tBest = 0;
                        for (i5 = tData.size() - 1; i5 > 0; --i5) {
                            if (CFG.core.getProv(listOfPossibleProvinces.get((int)((Integer)tData.get((int)tBest)).intValue()).iProvinceID).getDangerLevel_WithArmy() >= CFG.core.getProv(listOfPossibleProvinces.get((int)((Integer)tData.get((int)i5)).intValue()).iProvinceID).getDangerLevel_WithArmy()) continue;
                            tBest = i5;
                        }
                        tSortedIDs.add((Integer)tData.get(tBest));
                        tData.remove(tBest);
                    }
                    int nDangerTotal = 0;
                    for (i5 = 0; i5 < nNumOfPossibleMovements && i5 < tSortedIDs.size(); ++i5) {
                        nDangerTotal += CFG.core.getProv(listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)i5)).intValue()).iProvinceID).getDangerLevel_WithArmy();
                    }
                    int tIDOfFisrttSuccesfulMovement = -1;
                    for (i = 0; i < nNumOfPossibleMovements && i < tSortedIDs.size() && nArmy.iArmy > 0; ++i) {
                        RegroupArmy tryRegroupArmy = new RegroupArmy(nCivID, nArmy.iProvinceID, listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)i)).intValue()).iProvinceID);
                        if (tryRegroupArmy.getRouteSize() > 0) {
                            int tArmyToMove = i == nNumOfPossibleMovements || i == tSortedIDs.size() - 1 ? nArmy.iArmy : (int)Math.ceil((float)nArmy.iArmy * ((float)CFG.core.getProv(listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)i)).intValue()).iProvinceID).getDangerLevel_WithArmy() / (float)nDangerTotal));
                            nArmy.iArmy -= tArmyToMove;
                            if (tArmyToMove <= 0) break;
                            if (!CFG.gameAction.moveArmyAction(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), tArmyToMove, nCivID, true, false)) continue;
                            if (tryRegroupArmy.getRouteSize() > 1) {
                                CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.add(new CivArmyMission_RegroupAfterRecruitment(nCivID, tryRegroupArmy.getRoute(0), listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)i)).intValue()).iProvinceID, tArmyToMove));
                            }
                            tIDOfFisrttSuccesfulMovement = i;
                            continue;
                        }
                        if (tIDOfFisrttSuccesfulMovement < 0 || (tryRegroupArmy = new RegroupArmy(nCivID, nArmy.iProvinceID, listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)tIDOfFisrttSuccesfulMovement)).intValue()).iProvinceID)).getRouteSize() <= 0 || !CFG.gameAction.moveArmyAction(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), nArmy.iArmy, nCivID, true, false)) continue;
                        if (tryRegroupArmy.getRouteSize() > 1) {
                            CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.add(new CivArmyMission_RegroupAfterRecruitment(nCivID, tryRegroupArmy.getRoute(0), listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)tIDOfFisrttSuccesfulMovement)).intValue()).iProvinceID, nArmy.iArmy));
                        }
                        return true;
                    }
                    if (tIDOfFisrttSuccesfulMovement >= 0) {
                        return true;
                    }
                }
            }
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (StackOverflowError ex) {
            CFG.exceptionStack(ex);
        }
        return false;
    }

    public final boolean regroupArmy_AtWar_WithoutDanger(int nCivID, AI_RegoupArmyData nArmy) {
        try {
            int i;
            int i2;
            float percOfArmyToRegroup = Math.max((float)nArmy.iArmy / (float)CFG.core.getCiv(nCivID).getNumberOfUnits(), 0.01f);
            try {
                if (CFG.core.getCiv(nCivID).getCivRegion(CFG.core.getProv(nArmy.iProvinceID).getCivRegionID()).getProvincesSize() > 1) {
                    int tMaxDL = 1;
                    float tMaxPotential = 1.0f;
                    ArrayList<AI_ProvinceInfo> tempFrontProvinces = new ArrayList<AI_ProvinceInfo>();
                    for (i2 = CFG.core.getCiv((int)CFG.core.getProv((int)nArmy.iProvinceID).getCivId()).lFrontLines.size() - 1; i2 >= 0; --i2) {
                        if (!CFG.core.getCivsAtWar(nCivID, CFG.core.getCiv((int)CFG.core.getProv((int)nArmy.iProvinceID).getCivId()).lFrontLines.get((int)i2).iWithCivID)) continue;
                        try {
                            if (CFG.core.getProv(CFG.core.getCiv((int)CFG.core.getProv((int)nArmy.iProvinceID).getCivId()).lFrontLines.get((int)i2).lProvinces.get(0)).getCivRegionID() != CFG.core.getProv(nArmy.iProvinceID).getCivRegionID()) continue;
                            for (int j = CFG.core.getCiv((int)CFG.core.getProv((int)nArmy.iProvinceID).getCivId()).lFrontLines.get((int)i2).lProvinces.size() - 1; j >= 0; --j) {
                                boolean wasAdded = false;
                                for (int k = tempFrontProvinces.size() - 1; k >= 0; --k) {
                                    if (((AI_ProvinceInfo)tempFrontProvinces.get((int)k)).iProvinceID != CFG.core.getCiv((int)CFG.core.getProv((int)nArmy.iProvinceID).getCivId()).lFrontLines.get((int)i2).lProvinces.get(j)) continue;
                                    wasAdded = true;
                                    break;
                                }
                                if (wasAdded) continue;
                                tempFrontProvinces.add(new AI_ProvinceInfo(CFG.core.getCiv((int)CFG.core.getProv((int)nArmy.iProvinceID).getCivId()).lFrontLines.get((int)i2).lProvinces.get(j), 1, 1));
                            }
                            continue;
                        }
                        catch (IndexOutOfBoundsException j) {
                            // empty catch block
                        }
                    }
                    if (tempFrontProvinces.size() > 0) {
                        int i3;
                        int tMaxArmy = 1;
                        ArrayList<Integer> tMovingArmy = new ArrayList<Integer>();
                        int iSize = tempFrontProvinces.size();
                        int tempMovingArmy = 0;
                        for (i = 0; i < iSize; ++i) {
                            if (((AI_ProvinceInfo)tempFrontProvinces.get((int)i)).iValue > tMaxPotential) {
                                tMaxPotential = ((AI_ProvinceInfo)tempFrontProvinces.get((int)i)).iValue;
                            }
                            if (CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i)).iProvinceID).getDangerLevel_WithArmy() > tMaxDL) {
                                tMaxDL = CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i)).iProvinceID).getDangerLevel_WithArmy();
                            }
                            tMovingArmy.add(tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, ((AI_ProvinceInfo)tempFrontProvinces.get((int)i)).iProvinceID));
                            if (CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i)).iProvinceID).getArmyID(0) + tempMovingArmy <= tMaxArmy) continue;
                            tMaxArmy = CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i)).iProvinceID).getArmyID(0) + tempMovingArmy;
                        }
                        iSize = tempFrontProvinces.size();
                        for (i = 0; i < iSize; ++i) {
                            ((AI_ProvinceInfo)tempFrontProvinces.get((int)i)).iValue = (1.0f - (float)(CFG.core.getProvinceArmy(((AI_ProvinceInfo)tempFrontProvinces.get((int)i)).iProvinceID) + (Integer)tMovingArmy.get(i)) / (float)tMaxArmy + 0.2f * ((float)CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i)).iProvinceID).getDangerLvl() / (float)tMaxDL) + 0.2f * ((float)CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i)).iProvinceID).getPotentialModified_WAR_MoveFrom(nCivID) / tMaxPotential) + 0.2f * (float)CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i)).iProvinceID).getNeighProvinceOfCivWasLost()) * (float)((AI_ProvinceInfo)tempFrontProvinces.get((int)i)).iRecruitable == 0.0f ? 0.725f : 1.0f;
                        }
                        ArrayList<AI_ProvinceInfo> sortedFrontProvinces = new ArrayList<AI_ProvinceInfo>();
                        while (tempFrontProvinces.size() > 0) {
                            int tBest = 0;
                            int iSize2 = tempFrontProvinces.size();
                            for (int i4 = 1; i4 < iSize2; ++i4) {
                                if (!(((AI_ProvinceInfo)tempFrontProvinces.get((int)tBest)).iValue < ((AI_ProvinceInfo)tempFrontProvinces.get((int)i4)).iValue)) continue;
                                tBest = i4;
                            }
                            sortedFrontProvinces.add((AI_ProvinceInfo)tempFrontProvinces.get(tBest));
                            tempFrontProvinces.remove(tBest);
                        }
                        int iNumOfMaxMovements = 1;
                        if (GameValues.gvAiArmy.REGROUP_AT_PEACE_MAX_ONE_MOVE_IF_PERC_OF_ARMY > percOfArmyToRegroup) {
                            iNumOfMaxMovements = 1;
                        } else {
                            iNumOfMaxMovements = Math.max(1, Math.min((CFG.core.getCiv(nCivID).getMovemPoints() - CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_MOVE) / CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_MOVE_OWN_PROVINCE, Math.min(CFG.core.getCiv(nCivID).getNumOfProvs(), 2 + CFG.oR.nextInt(3))));
                            iNumOfMaxMovements = percOfArmyToRegroup > 0.34f ? Math.min(iNumOfMaxMovements, 4) : (percOfArmyToRegroup > 0.24f ? Math.min(iNumOfMaxMovements, 3) : (percOfArmyToRegroup > 0.1f ? Math.min(iNumOfMaxMovements, 2) : Math.min(iNumOfMaxMovements, 1)));
                        }
                        ArrayList<AI_ProvinceInfo> tRecruitArmiesForProvinces = new ArrayList<AI_ProvinceInfo>();
                        float totalValues = 0.0f;
                        for (i3 = 0; i3 < iNumOfMaxMovements && i3 < sortedFrontProvinces.size(); ++i3) {
                            tRecruitArmiesForProvinces.add((AI_ProvinceInfo)sortedFrontProvinces.get(i3));
                            totalValues += ((AI_ProvinceInfo)sortedFrontProvinces.get((int)i3)).iValue;
                        }
                        for (i3 = 0; i3 < tRecruitArmiesForProvinces.size(); ++i3) {
                            int tArmyToRecruit_PRE;
                            int tempArmyInThisMove = nArmy.iArmy;
                            if (CFG.core.getCiv(nCivID).getBordersWithEnemy() == 0 && CFG.core.getProv(nArmy.iProvinceID).getCivId() == nCivID && CFG.core.getProv(((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i3)).iProvinceID).getCivId() != nCivID) {
                                tempArmyInThisMove = (int)Math.ceil((float)tempArmyInThisMove * (0.72f + (float)CFG.oR.nextInt(12) / 100.0f));
                            }
                            if ((tArmyToRecruit_PRE = (int)Math.ceil((float)tempArmyInThisMove * ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i3)).iValue / totalValues)) <= 0) break;
                            RegroupArmy_ToTheFront_Double tryRegroupArmy = new RegroupArmy_ToTheFront_Double(nCivID, nArmy.iProvinceID, ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i3)).iProvinceID);
                            if (tryRegroupArmy.getRouteSize() <= 0) continue;
                            if (tryRegroupArmy.getRouteSize() == 1) {
                                if (CFG.gameAction.moveArmyAction(nArmy.iProvinceID, ((AI_ProvinceInfo)tRecruitArmiesForProvinces.get((int)i3)).iProvinceID, tArmyToRecruit_PRE, nCivID, true, false)) continue;
                                continue;
                            }
                            if (!CFG.gameAction.moveArmyAction(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), tArmyToRecruit_PRE, nCivID, true, false)) continue;
                            tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
                            tryRegroupArmy.removeRoute(0);
                            tryRegroupArmy.setNumOfUnits(tArmyToRecruit_PRE);
                            CFG.core.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
                        }
                        return true;
                    }
                }
            }
            catch (NullPointerException tMaxDL) {
                // empty catch block
            }
            List<AI_NeighProvinces> listOfPossibleProvinces = this.getAllNeighboringProvincesInRange_RegroupAtWar(nArmy.iProvinceID, nCivID, CFG.core.getCiv((int)nCivID).civGD.civPers.REGROUP_AT_PEACE_MAX_PROVINCES + CFG.core.getCiv(nCivID).getNumOfProvs() / 15, new ArrayList<AI_NeighProvinces>(), new ArrayList<Integer>());
            if (listOfPossibleProvinces.size() > 0) {
                int nNumOfPossibleMovements = CFG.core.getCiv(nCivID).getMovemPoints() / CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_MOVE;
                nNumOfPossibleMovements = percOfArmyToRegroup > 0.54f ? Math.min(nNumOfPossibleMovements, 4) : (percOfArmyToRegroup > 0.34f ? Math.min(nNumOfPossibleMovements, 3) : (percOfArmyToRegroup > 0.19f ? Math.min(nNumOfPossibleMovements, 2) : Math.min(nNumOfPossibleMovements, 1)));
                boolean provincesWithDanger = false;
                for (i2 = listOfPossibleProvinces.size() - 1; i2 >= 0; --i2) {
                    if (CFG.core.getProv(listOfPossibleProvinces.get((int)i2).iProvinceID).getDangerLvl() <= 0) continue;
                    provincesWithDanger = true;
                    break;
                }
                if (provincesWithDanger) {
                    int i5;
                    ArrayList<Integer> tSortedIDs = new ArrayList<Integer>();
                    ArrayList<Integer> tData = new ArrayList<Integer>();
                    for (i = listOfPossibleProvinces.size() - 1; i >= 0; --i) {
                        tData.add(i);
                    }
                    while (tData.size() > 0) {
                        int tBest = 0;
                        for (i5 = tData.size() - 1; i5 > 0; --i5) {
                            if (CFG.core.getProv(listOfPossibleProvinces.get((int)((Integer)tData.get((int)tBest)).intValue()).iProvinceID).getDangerLevel_WithArmy() >= CFG.core.getProv(listOfPossibleProvinces.get((int)((Integer)tData.get((int)i5)).intValue()).iProvinceID).getDangerLevel_WithArmy()) continue;
                            tBest = i5;
                        }
                        tSortedIDs.add((Integer)tData.get(tBest));
                        tData.remove(tBest);
                    }
                    int nDangerTotal = 0;
                    for (i5 = 0; i5 < nNumOfPossibleMovements && i5 < tSortedIDs.size(); ++i5) {
                        nDangerTotal += CFG.core.getProv(listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)i5)).intValue()).iProvinceID).getDangerLevel_WithArmy();
                    }
                    int tIDOfFisrttSuccesfulMovement = -1;
                    for (int i6 = 0; i6 < nNumOfPossibleMovements && i6 < tSortedIDs.size() && nArmy.iArmy > 0; ++i6) {
                        RegroupArmy_AtWar tryRegroupArmy = new RegroupArmy_AtWar(nCivID, nArmy.iProvinceID, listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)i6)).intValue()).iProvinceID);
                        if (tryRegroupArmy.getRouteSize() <= 0) continue;
                        int tempArmyInThisMove = nArmy.iArmy;
                        if (CFG.core.getCiv(nCivID).getBordersWithEnemy() == 0 && CFG.core.getProv(nArmy.iProvinceID).getCivId() == nCivID && CFG.core.getProv(listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)i6)).intValue()).iProvinceID).getCivId() != nCivID) {
                            tempArmyInThisMove = (int)Math.ceil((float)tempArmyInThisMove * (0.72f + (float)CFG.oR.nextInt(12) / 100.0f));
                        }
                        int tArmyToMove = i6 == nNumOfPossibleMovements || i6 == tSortedIDs.size() - 1 ? tempArmyInThisMove : (int)Math.ceil((float)tempArmyInThisMove * ((float)CFG.core.getProv(listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)i6)).intValue()).iProvinceID).getDangerLevel_WithArmy() / (float)nDangerTotal));
                        nArmy.iArmy -= tArmyToMove;
                        if (tArmyToMove <= 0) break;
                        if (!CFG.gameAction.moveArmyAction(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), tArmyToMove, nCivID, true, false)) continue;
                        if (tryRegroupArmy.getRouteSize() > 1) {
                            CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.add(new CivArmyMission_RegroupAfterRecruitment_War_Double(nCivID, tryRegroupArmy.getRoute(0), listOfPossibleProvinces.get((int)((Integer)tSortedIDs.get((int)i6)).intValue()).iProvinceID, tArmyToMove));
                        }
                        tIDOfFisrttSuccesfulMovement = i6;
                    }
                    if (tIDOfFisrttSuccesfulMovement >= 0) {
                        return true;
                    }
                }
            }
            if (CFG.core.getCiv(nCivID).getCapitalProvID() >= 0 && CFG.core.getCiv(nCivID).getCapitalProvID() != nArmy.iProvinceID && (CFG.core.getCiv(nCivID).getBordersWithEnemy() > 0 || CFG.core.getProv(nArmy.iProvinceID).getNeighSeaProvincesSize() <= 0 || CFG.oR.nextInt(100) >= 80) && CFG.oR.nextInt(100) < 15) {
                if (percOfArmyToRegroup < 0.01f) {
                    CFG.gameAction.disbandArmy(nArmy.iProvinceID, nArmy.iArmy, nCivID);
                } else {
                    RegroupArmy_AtWar tryRegroupArmy = new RegroupArmy_AtWar(nCivID, nArmy.iProvinceID, CFG.core.getCiv(nCivID).getCapitalProvID());
                    if (tryRegroupArmy.getRouteSize() > 0) {
                        if (tryRegroupArmy.getRouteSize() == 1) {
                            if (!CFG.gameAction.moveArmyAction(nArmy.iProvinceID, CFG.core.getCiv(nCivID).getCapitalProvID(), nArmy.iArmy, nCivID, true, false)) {
                                // empty if block
                            }
                        } else if (CFG.gameAction.moveArmyAction(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), nArmy.iArmy, nCivID, true, false)) {
                            tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
                            tryRegroupArmy.removeRoute(0);
                            tryRegroupArmy.setNumOfUnits(nArmy.iArmy);
                            CFG.core.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
                        }
                    } else if (!CFG.core.getCiv((int)CFG.core.getProv((int)nArmy.iProvinceID).getCivId()).getCivRegion((int)CFG.core.getProv((int)nArmy.iProvinceID).getCivRegionID()).isKeyRegion) {
                        CFG.gameAction.disbandArmy(nArmy.iProvinceID, nArmy.iArmy, nCivID);
                    }
                }
            }
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (StackOverflowError ex) {
            CFG.exceptionStack(ex);
        }
        catch (NullPointerException ex) {
            CFG.exceptionStack(ex);
        }
        return false;
    }

    public final List<AI_NeighProvinces> getAllNeighboringProvincesInRange_RegroupAtWar(int nProvinceID, int nCivID, int iRange, List<AI_NeighProvinces> out, List<Integer> was) {
        ArrayList<Integer> recentlyAdded = new ArrayList<Integer>();
        recentlyAdded.add(nProvinceID);
        was.add(nProvinceID);
        CFG.core.getProv((int)nProvinceID).wasInProv = true;
        ArrayList<Integer> currProvinces = new ArrayList<Integer>();
        int nIteration_Distance = 0;
        int iFirstFoundRange = -1;
        while ((nIteration_Distance < iRange || out.size() == 0) && recentlyAdded.size() > 0) {
            int a;
            currProvinces.clear();
            ++nIteration_Distance;
            for (a = recentlyAdded.size() - 1; a >= 0; --a) {
                boolean wasntAdded = true;
                for (int j = currProvinces.size() - 1; j >= 0; --j) {
                    if (currProvinces.get(j) != recentlyAdded.get(a)) continue;
                    wasntAdded = false;
                    break;
                }
                if (!wasntAdded) continue;
                currProvinces.add((Integer)recentlyAdded.get(a));
            }
            recentlyAdded.clear();
            for (a = currProvinces.size() - 1; a >= 0; --a) {
                for (int i = 0; i < CFG.core.getProv((Integer)currProvinces.get(a)).getNeighProvincesSize(); ++i) {
                    if (CFG.core.getProv((int)CFG.core.getProv((int)((Integer)currProvinces.get((int)a)).intValue()).getNeighProvinces((int)i)).wasInProv) continue;
                    was.add(CFG.core.getProv((Integer)currProvinces.get(a)).getNeighProvinces(i));
                    CFG.core.getProv((int)CFG.core.getProv((int)((Integer)currProvinces.get((int)a)).intValue()).getNeighProvinces((int)i)).wasInProv = true;
                    if (!CFG.core.isAlly(nCivID, CFG.core.getProv(CFG.core.getProv((Integer)currProvinces.get(a)).getNeighProvinces(i)).getCivId()) && CFG.core.getMilitaryAccess(nCivID, CFG.core.getProv(CFG.core.getProv((Integer)currProvinces.get(a)).getNeighProvinces(i)).getCivId()) <= 0) continue;
                    if (CFG.core.getProv(CFG.core.getProv((Integer)currProvinces.get(a)).getNeighProvinces(i)).getBordersWithEnemy()) {
                        boolean bordersWithOurEnemy = false;
                        for (int z = 0; z < CFG.core.getProv(CFG.core.getProv((Integer)currProvinces.get(a)).getNeighProvinces(i)).getNeighProvincesSize(); ++z) {
                            if (!CFG.core.getCivsAtWar(nCivID, CFG.core.getProv(CFG.core.getProv(CFG.core.getProv((Integer)currProvinces.get(a)).getNeighProvinces(i)).getNeighProvinces(z)).getCivId())) continue;
                            bordersWithOurEnemy = true;
                            break;
                        }
                        if (bordersWithOurEnemy) {
                            out.add(new AI_NeighProvinces(CFG.core.getProv((Integer)currProvinces.get(a)).getNeighProvinces(i), nIteration_Distance));
                            if (iFirstFoundRange < 0) {
                                iFirstFoundRange = nIteration_Distance;
                            }
                        }
                    } else if (this.moveAtWar_BordersWithEnemyCheck(nCivID, CFG.core.getProv((Integer)currProvinces.get(a)).getNeighProvinces(i))) {
                        out.add(new AI_NeighProvinces(CFG.core.getProv((Integer)currProvinces.get(a)).getNeighProvinces(i), nIteration_Distance));
                        if (iFirstFoundRange < 0) {
                            iFirstFoundRange = nIteration_Distance;
                        }
                    }
                    recentlyAdded.add(CFG.core.getProv((Integer)currProvinces.get(a)).getNeighProvinces(i));
                }
            }
            if (iFirstFoundRange <= 0 || iFirstFoundRange + 2 >= nIteration_Distance) continue;
        }
        for (int j = was.size() - 1; j >= 0; --j) {
            CFG.core.getProv((int)was.get((int)j).intValue()).wasInProv = false;
        }
        recentlyAdded.clear();
        recentlyAdded = null;
        was.clear();
        was = null;
        return out;
    }

    public final List<AI_NeighProvinces> getAllNeighboringProvincesInRange_RegroupPrepareForWAr(int nProvinceID, int nCivID, int iRange, List<AI_NeighProvinces> out, List<Integer> was) {
        ArrayList<Integer> recentlyAdded = new ArrayList<Integer>();
        recentlyAdded.add(nProvinceID);
        was.add(nProvinceID);
        CFG.core.getProv((int)nProvinceID).wasInProv = true;
        ArrayList<Integer> currProvinces = new ArrayList<Integer>();
        int nIteration_Distance = 0;
        int iFirstFoundRange = -1;
        while ((nIteration_Distance < iRange || out.size() == 0) && recentlyAdded.size() > 0) {
            int a;
            currProvinces.clear();
            ++nIteration_Distance;
            for (a = recentlyAdded.size() - 1; a >= 0; --a) {
                boolean wasntAdded = true;
                for (int j = currProvinces.size() - 1; j >= 0; --j) {
                    if (currProvinces.get(j) != recentlyAdded.get(a)) continue;
                    wasntAdded = false;
                    break;
                }
                if (!wasntAdded) continue;
                currProvinces.add((Integer)recentlyAdded.get(a));
            }
            recentlyAdded.clear();
            for (a = currProvinces.size() - 1; a >= 0; --a) {
                for (int i = 0; i < CFG.core.getProv((Integer)currProvinces.get(a)).getNeighProvincesSize(); ++i) {
                    if (CFG.core.getProv((int)CFG.core.getProv((int)((Integer)currProvinces.get((int)a)).intValue()).getNeighProvinces((int)i)).wasInProv) continue;
                    was.add(CFG.core.getProv((Integer)currProvinces.get(a)).getNeighProvinces(i));
                    CFG.core.getProv((int)CFG.core.getProv((int)((Integer)currProvinces.get((int)a)).intValue()).getNeighProvinces((int)i)).wasInProv = true;
                    if (!CFG.core.isAlly(nCivID, CFG.core.getProv(CFG.core.getProv((Integer)currProvinces.get(a)).getNeighProvinces(i)).getCivId()) && CFG.core.getMilitaryAccess(nCivID, CFG.core.getProv(CFG.core.getProv((Integer)currProvinces.get(a)).getNeighProvinces(i)).getCivId()) <= 0) continue;
                    if (CFG.oAI.prepareForWar_BordersWithEnemy(nCivID, CFG.core.getProv((Integer)currProvinces.get(a)).getNeighProvinces(i))) {
                        out.add(new AI_NeighProvinces(CFG.core.getProv((Integer)currProvinces.get(a)).getNeighProvinces(i), nIteration_Distance));
                        if (iFirstFoundRange < 0) {
                            iFirstFoundRange = nIteration_Distance;
                        }
                    }
                    recentlyAdded.add(CFG.core.getProv((Integer)currProvinces.get(a)).getNeighProvinces(i));
                }
            }
            if (iFirstFoundRange <= 0 || iFirstFoundRange + 2 >= nIteration_Distance) continue;
        }
        for (int j = was.size() - 1; j >= 0; --j) {
            CFG.core.getProv((int)was.get((int)j).intValue()).wasInProv = false;
        }
        recentlyAdded.clear();
        recentlyAdded = null;
        was.clear();
        was = null;
        return out;
    }

    public final void prepareForWar_Recruit(int nCivID, List<AI_ProvinceInfo_War> sortedFrontProvinces, List<Integer> lFrontIDsWithArmies, boolean forSeaInvasion) {
        if (CFG.core.getCiv(nCivID).getMovemPoints() < CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_RECRUIT) {
            return;
        }
        if (lFrontIDsWithArmies.size() * CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_MOVE > CFG.core.getCiv(nCivID).getMovemPoints() && Math.max((float)(CFG.core.getCiv(nCivID).getGold() / (long)GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT) / (float)CFG.core.getCiv(nCivID).getNumberOfUnits(), 0.001f) < 0.048f && CFG.oR.nextInt(100) < 85) {
            return;
        }
        int nUpkeepLeft = (int)((float)CFG.core.getCiv((int)nCivID).iBudget * (0.8f - CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(nCivID).getIdeology()).getMin_Goods(nCivID) - CFG.ideologiesMgr.getInvestments(CFG.core.getCiv(nCivID).getIdeology(), nCivID)) - (float)CFG.core.getCiv((int)nCivID).iBudget * CFG.core.getCiv((int)nCivID).iMilitaryUpkeep_PERC);
        if (nUpkeepLeft < 0) {
            return;
        }
        if (!forSeaInvasion && CFG.core.getCiv(nCivID).getCapitalProvID() >= 0 && CFG.core.getProv(CFG.core.getCiv(nCivID).getCapitalProvID()).getCivId() == nCivID && CFG.core.getProv(CFG.core.getCiv(nCivID).getCapitalProvID()).getNeighSeaProvincesSize() > 0) {
            boolean aldAdded = false;
            for (int k = sortedFrontProvinces.size() - 1; k >= 0; --k) {
                if (sortedFrontProvinces.get((int)k).iProvinceID != CFG.core.getCiv(nCivID).getCapitalProvID()) continue;
                aldAdded = true;
                break;
            }
            if (!aldAdded) {
                sortedFrontProvinces.add(new AI_ProvinceInfo_War(CFG.core.getCiv(nCivID).getCapitalProvID(), this.getPotential_BasedOnNeighboringProvs(CFG.core.getCiv(nCivID).getCapitalProvID(), nCivID), true));
            }
        }
        int numOfUnitsToRecruit_MAX = (int)((float)nUpkeepLeft / (CFG.gameUpdate.getMilitaryUpkeep_WithoutDefensivePosition(sortedFrontProvinces.get((int)0).iProvinceID, 1000, nCivID) / 1000.0f));
        int iNumOfMaxRecruitments = Math.max(1, Math.min((CFG.core.getCiv(nCivID).getMovemPoints() - (lFrontIDsWithArmies.size() > 0 ? CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_MOVE * lFrontIDsWithArmies.size() : CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_MOVE_OWN_PROVINCE)) / CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_RECRUIT, CFG.core.getCiv(nCivID).getNumOfProvs()));
        if (lFrontIDsWithArmies.size() > 1 && iNumOfMaxRecruitments > 1 && Math.min(CFG.core.getCiv(nCivID).getGold() / (long)GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT, (long)numOfUnitsToRecruit_MAX) <= (long)sortedFrontProvinces.get(0).getRecruitableArmy(nCivID) && (float)Math.min(CFG.core.getCiv(nCivID).getGold() / (long)GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT, (long)numOfUnitsToRecruit_MAX) < (float)CFG.core.getCiv(nCivID).getNumberOfUnits() * 0.35f && CFG.oR.nextInt(100) < 95) {
            iNumOfMaxRecruitments = 1;
        }
        ArrayList<AI_ProvinceInfo_War> tRecruitArmiesForProvinces = new ArrayList<AI_ProvinceInfo_War>();
        float totalValues = 0.0f;
        for (int i = 0; i < iNumOfMaxRecruitments && i < sortedFrontProvinces.size(); ++i) {
            tRecruitArmiesForProvinces.add(sortedFrontProvinces.get(i));
            totalValues += sortedFrontProvinces.get((int)i).iValue;
        }
        int tempMoneyPre = (int)CFG.core.getCiv(nCivID).getGold();
        boolean armyRecruited = false;
        for (int i = 0; i < tRecruitArmiesForProvinces.size(); ++i) {
            int tArmyToRecruit_PRE = (int)((float)Math.min(numOfUnitsToRecruit_MAX, tempMoneyPre / (CFG.core.getProv(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iProvinceID).getLvlOfArmoury() > 0 ? GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT - 1 : GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT)) * ((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iValue / totalValues);
            boolean notEnoughRecruits = false;
            if (((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get(i)).getRecruitableArmy(nCivID) < tArmyToRecruit_PRE) {
                notEnoughRecruits = true;
            }
            if (CFG.core.getProv(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iProvinceID).isOccupied() || CFG.core.getProv(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iProvinceID).getCivId() != nCivID || notEnoughRecruits) {
                int tempArmy;
                int tArmyToRecruit;
                List<AI_NeighProvinces> listOfPossibleProvincesToRecruit = CFG.oAI.getAllNeighboringProvincesInRange_RecruitAtWAr(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iProvinceID, nCivID, Math.max(10, CFG.core.getCiv(nCivID).getNumOfProvs() / 8), true, false, new ArrayList<AI_NeighProvinces>(), new ArrayList<Integer>());
                if (listOfPossibleProvincesToRecruit.size() <= 0) continue;
                int tempRand = 0;
                if (notEnoughRecruits || CFG.oR.nextInt(100) < 90) {
                    int tBest = 0;
                    int tBestArmy = CFG.gameAction.gMARY(listOfPossibleProvincesToRecruit.get((int)tBest).iProvinceID);
                    for (int k = 1; k < listOfPossibleProvincesToRecruit.size(); ++k) {
                        if (tBestArmy >= CFG.gameAction.gMARY(listOfPossibleProvincesToRecruit.get((int)k).iProvinceID)) continue;
                        tBest = k;
                        tBestArmy = CFG.gameAction.gMARY(listOfPossibleProvincesToRecruit.get((int)k).iProvinceID);
                    }
                    tempRand = tBest;
                } else {
                    tempRand = CFG.oR.nextInt(listOfPossibleProvincesToRecruit.size());
                }
                if ((float)CFG.gameAction.gMARY(listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID, nCivID) < (float)((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get(i)).getRecruitableArmy(nCivID) * 1.2f) {
                    tArmyToRecruit = (int)((float)Math.min(numOfUnitsToRecruit_MAX, Math.min(numOfUnitsToRecruit_MAX, tempMoneyPre / (CFG.core.getProv(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iProvinceID).getLvlOfArmoury() > 0 ? GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT - 1 : GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT))) * ((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iValue / totalValues);
                    if (!CFG.core.getCiv(nCivID).recruitArmy_AI(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iProvinceID, tArmyToRecruit)) continue;
                    armyRecruited = true;
                    continue;
                }
                tArmyToRecruit = (int)((float)Math.min(numOfUnitsToRecruit_MAX, Math.min(numOfUnitsToRecruit_MAX, tempMoneyPre / (CFG.core.getProv(listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID).getLvlOfArmoury() > 0 ? GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT - 1 : GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT))) * ((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iValue / totalValues);
                if (CFG.core.getCiv(nCivID).recruitArmy_AI(listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID, tArmyToRecruit)) {
                    armyRecruited = true;
                }
                if ((tempArmy = CFG.core.getCiv(nCivID).getRecruitArmy_BasedOnProvinceID(listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID)) <= 0) continue;
                CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.add(new CivArmyMission_RegroupAfterRecruitment(nCivID, listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID, ((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iProvinceID, tempArmy));
                continue;
            }
            int tArmyToRecruit = (int)((float)Math.min(numOfUnitsToRecruit_MAX, Math.min(numOfUnitsToRecruit_MAX, tempMoneyPre / (CFG.core.getProv(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iProvinceID).getLvlOfArmoury() > 0 ? GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT - 1 : GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT))) * ((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iValue / totalValues);
            if (!CFG.core.getCiv(nCivID).recruitArmy_AI(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iProvinceID, tArmyToRecruit)) continue;
            armyRecruited = true;
        }
        if (armyRecruited && CFG.core.getCiv(nCivID).getGold() < (long)(GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT * 5)) {
            CFG.core.getCiv((int)nCivID).civGD.moveAtWar_ArmyFullyRecruitedLastTurn = true;
        }
    }

    public final void moveAtWar_Recruit(int nCivID, List<AI_ProvinceInfo_War> sortedFrontProvinces, List<Integer> lFrontIDsWithArmies, boolean forSeaInvasion) {
        Civilization civ = CFG.core.getCiv(nCivID);
        if (civ.getMovemPoints() < CFG.ideologiesMgr.getIdeologyID((int)civ.getIdeology()).COST_OF_RECRUIT) {
            return;
        }
        if (lFrontIDsWithArmies.size() * CFG.ideologiesMgr.getIdeologyID((int)civ.getIdeology()).COST_OF_MOVE > civ.getMovemPoints() && Math.max((float)(civ.getGold() / (long)GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT) / (float)civ.getNumberOfUnits(), 0.001f) < 0.048f && CFG.oR.nextInt(100) < 85) {
            return;
        }
        int nUpkeepLeft = (int)((float)civ.iBudget * ((forSeaInvasion ? civ.civGD.civPers.MIN_MILITARY_SPENDINGS_NOT_BORDERING_WITH_ENEMY : (civ.getBordersWithEnemy() == 0 ? civ.civGD.civPers.MIN_MILITARY_SPENDINGS_NOT_BORDERING_WITH_ENEMY : civ.civGD.civPers.MIN_MILITARY_SPENDINGS_RECRUIT_AT_WAR)) - CFG.ideologiesMgr.getIdeologyID(civ.getIdeology()).getMin_Goods(nCivID) - CFG.ideologiesMgr.getInvestments(civ.getIdeology(), nCivID)) - (float)civ.iBudget * civ.iMilitaryUpkeep_PERC);
        if (nUpkeepLeft < 0) {
            return;
        }
        if (!forSeaInvasion && civ.getCapitalProvID() >= 0 && CFG.core.getProv(civ.getCapitalProvID()).getCivId() == nCivID && CFG.core.getProv(civ.getCapitalProvID()).getNeighSeaProvincesSize() > 0) {
            boolean aldAdded = false;
            for (int k = sortedFrontProvinces.size() - 1; k >= 0; --k) {
                if (sortedFrontProvinces.get((int)k).iProvinceID != civ.getCapitalProvID()) continue;
                aldAdded = true;
                break;
            }
            if (!aldAdded) {
                sortedFrontProvinces.add(new AI_ProvinceInfo_War(civ.getCapitalProvID(), this.getPotential_BasedOnNeighboringProvs(civ.getCapitalProvID(), nCivID), true));
            }
        }
        int numOfUnitsToRecruit_MAX = (int)((float)nUpkeepLeft / (CFG.gameUpdate.getMilitaryUpkeep_WithoutDefensivePosition(sortedFrontProvinces.get((int)0).iProvinceID, 1000, nCivID) / 1000.0f));
        int iNumOfMaxRecruitments = Math.max(1, Math.min((civ.getMovemPoints() - (lFrontIDsWithArmies.size() > 0 ? CFG.ideologiesMgr.getIdeologyID((int)civ.getIdeology()).COST_OF_MOVE * lFrontIDsWithArmies.size() : CFG.ideologiesMgr.getIdeologyID((int)civ.getIdeology()).COST_OF_MOVE_OWN_PROVINCE)) / CFG.ideologiesMgr.getIdeologyID((int)civ.getIdeology()).COST_OF_RECRUIT, civ.getNumOfProvs()));
        if (lFrontIDsWithArmies.size() > 1 && iNumOfMaxRecruitments > 1 && Math.min(civ.getGold() / (long)GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT, (long)numOfUnitsToRecruit_MAX) <= (long)sortedFrontProvinces.get(0).getRecruitableArmy(nCivID) && (float)Math.min(civ.getGold() / (long)GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT, (long)numOfUnitsToRecruit_MAX) < (float)civ.getNumberOfUnits() * 0.35f && CFG.oR.nextInt(100) < 95) {
            iNumOfMaxRecruitments = 1;
        }
        ArrayList<AI_ProvinceInfo_War> tRecruitArmiesForProvinces = new ArrayList<AI_ProvinceInfo_War>();
        float totalValues = 0.0f;
        for (int i = 0; i < iNumOfMaxRecruitments && i < sortedFrontProvinces.size(); ++i) {
            tRecruitArmiesForProvinces.add(sortedFrontProvinces.get(i));
            totalValues += sortedFrontProvinces.get((int)i).iValue;
        }
        int tempMoneyPre = (int)civ.getGold();
        boolean armyRecruited = false;
        for (int i = 0; i < tRecruitArmiesForProvinces.size(); ++i) {
            int tArmyToRecruit_PRE = (int)((float)Math.min(numOfUnitsToRecruit_MAX, tempMoneyPre / (CFG.core.getProv(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iProvinceID).getLvlOfArmoury() > 0 ? GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT - 1 : GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT)) * ((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iValue / totalValues);
            boolean notEnoughRecruits = false;
            if (((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get(i)).getRecruitableArmy(nCivID) < tArmyToRecruit_PRE) {
                notEnoughRecruits = true;
            }
            if (CFG.core.getProv(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iProvinceID).isOccupied() || CFG.core.getProv(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iProvinceID).getCivId() != nCivID || notEnoughRecruits) {
                List<AI_NeighProvinces> listOfPossibleProvincesToRecruit = CFG.oAI.getAllNeighboringProvincesInRange_RecruitAtWAr(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iProvinceID, nCivID, Math.max(10, civ.getNumOfProvs() / 8), true, false, new ArrayList<AI_NeighProvinces>(), new ArrayList<Integer>());
                if (!listOfPossibleProvincesToRecruit.isEmpty()) {
                    int tempArmy;
                    int tArmyToRecruit;
                    int tempRand = 0;
                    if (notEnoughRecruits || CFG.oR.nextInt(100) < 90) {
                        int tBest = 0;
                        int tBestArmy = CFG.gameAction.gMARY(listOfPossibleProvincesToRecruit.get((int)tBest).iProvinceID);
                        for (int k = 1; k < listOfPossibleProvincesToRecruit.size(); ++k) {
                            if (tBestArmy >= CFG.gameAction.gMARY(listOfPossibleProvincesToRecruit.get((int)k).iProvinceID)) continue;
                            tBest = k;
                            tBestArmy = CFG.gameAction.gMARY(listOfPossibleProvincesToRecruit.get((int)k).iProvinceID);
                        }
                        tempRand = tBest;
                    } else {
                        tempRand = CFG.oR.nextInt(listOfPossibleProvincesToRecruit.size());
                    }
                    if ((float)CFG.gameAction.gMARY(listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID, nCivID) < (float)((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get(i)).getRecruitableArmy(nCivID) * 1.2f) {
                        tArmyToRecruit = (int)((float)Math.min(numOfUnitsToRecruit_MAX, Math.min(numOfUnitsToRecruit_MAX, tempMoneyPre / (CFG.core.getProv(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iProvinceID).getLvlOfArmoury() > 0 ? GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT - 1 : GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT))) * ((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iValue / totalValues);
                        if (!civ.recruitArmy_AI(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iProvinceID, tArmyToRecruit)) continue;
                        armyRecruited = true;
                        continue;
                    }
                    tArmyToRecruit = (int)((float)Math.min(numOfUnitsToRecruit_MAX, Math.min(numOfUnitsToRecruit_MAX, tempMoneyPre / (CFG.core.getProv(listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID).getLvlOfArmoury() > 0 ? GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT - 1 : GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT))) * ((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iValue / totalValues);
                    if (civ.recruitArmy_AI(listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID, tArmyToRecruit)) {
                        armyRecruited = true;
                    }
                    if ((tempArmy = civ.getRecruitArmy_BasedOnProvinceID(listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID)) <= 0) continue;
                    civ.civGD.civPlans.armiesMissions.add(new CivArmyMission_RegroupAfterRecruitment_War(nCivID, listOfPossibleProvincesToRecruit.get((int)tempRand).iProvinceID, ((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iProvinceID, tempArmy));
                    continue;
                }
                if (forSeaInvasion) continue;
                boolean addMission = true;
                for (int k = civ.civGD.civPlans.armiesMissions.size() - 1; k >= 0; --k) {
                    if (civ.civGD.civPlans.armiesMissions.get((int)k).MISSION_TYPE != CivArmyMission_Type.NAVAL_INVASION || civ.civGD.civPlans.armiesMissions.get((int)k).toProvinceID != ((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iProvinceID) continue;
                    addMission = false;
                    break;
                }
                if (!addMission) continue;
                int tMoveTo = ((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iProvinceID;
                if (CFG.core.getProv(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iProvinceID).getNeighSeaProvincesSize() == 0) {
                    boolean provinceUpdated = false;
                    for (int z = 0; z < CFG.core.getProv(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iProvinceID).getNeighSeaProvincesSize(); ++z) {
                        if (CFG.core.getProv(CFG.core.getProv(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iProvinceID).getNeighProvinces(z)).getLvlOfPort() < 0 || CFG.core.getProv(CFG.core.getProv(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iProvinceID).getNeighProvinces(z)).getCivId() != nCivID && !CFG.core.getCivsAtWar(nCivID, CFG.core.getProv(CFG.core.getProv(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iProvinceID).getNeighProvinces(z)).getCivId())) continue;
                        if (provinceUpdated) {
                            if (CFG.oR.nextInt(100) >= 50) continue;
                            tMoveTo = CFG.core.getProv(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iProvinceID).getNeighProvinces(z);
                            continue;
                        }
                        tMoveTo = CFG.core.getProv(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iProvinceID).getNeighProvinces(z);
                        provinceUpdated = true;
                    }
                }
                if (GameValues.gvAiWar.USE_NEW_NAVAL_INVASION) {
                    this.moveAtWar_AtSea_ToProvinceID_New(nCivID, tMoveTo);
                    continue;
                }
                this.moveAtWar_AtSea_ToProvinceID(nCivID, tMoveTo);
                continue;
            }
            int tArmyToRecruit = (int)((float)Math.min(numOfUnitsToRecruit_MAX, Math.min(numOfUnitsToRecruit_MAX, tempMoneyPre / (CFG.core.getProv(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iProvinceID).getLvlOfArmoury() > 0 ? GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT - 1 : GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT))) * ((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iValue / totalValues);
            if (!civ.recruitArmy_AI(((AI_ProvinceInfo_War)tRecruitArmiesForProvinces.get((int)i)).iProvinceID, tArmyToRecruit)) continue;
            armyRecruited = true;
        }
        if (armyRecruited && civ.getGold() < (long)(GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT * 5)) {
            civ.civGD.moveAtWar_ArmyFullyRecruitedLastTurn = true;
        }
    }

    public void buildStartingBuildings(int nCivID) {
        try {
            if (CFG.core.getCiv(nCivID).getCapitalProvID() >= 0) {
                if (CFG.core.getCiv(nCivID).getTechLevel() >= BuildingsManager.getTower_TechLevel(1) * GameValues.gvProvince.STARTING_BUILDING_WATCHTOWER_TECH_REQUIRED) {
                    CFG.core.getProv(CFG.core.getCiv(nCivID).getCapitalProvID()).setLvlOfWatchTower(1);
                }
                if (CFG.core.getCiv(nCivID).getTechLevel() >= BuildingsManager.getFort_TechLevel(1) * GameValues.gvProvince.STARTING_BUILDING_FORT_TECH_REQUIRED) {
                    CFG.core.getProv(CFG.core.getCiv(nCivID).getCapitalProvID()).setLvlOfFort(1);
                }
                if (CFG.core.getCiv(nCivID).getTechLevel() >= BuildingsManager.getPort_TechLevel(1) * GameValues.gvProvince.STARTING_BUILDING_PORT_TECH_REQUIRED) {
                    this.buildStartingBuildings_Port(nCivID);
                }
                if (CFG.core.getCiv(nCivID).getTechLevel() >= BuildingsManager.getFarm_TechLevel(1) && CFG.oR.nextInt(1000) < GameValues.gvProvince.STARTING_BUILDING_FARM_RANDOM_1000) {
                    CFG.core.getProv(CFG.core.getCiv(nCivID).getCapitalProvID()).setLvlOfFarm(1);
                }
                if (CFG.core.getCiv(nCivID).getTechLevel() >= BuildingsManager.getMarket_TechLevel(1) && CFG.oR.nextInt(1000) < GameValues.gvProvince.STARTING_BUILDING_MARKET_RANDOM_1000) {
                    CFG.core.getProv(CFG.core.getCiv(nCivID).getCapitalProvID()).setLvlOfMarket(1);
                }
                if (CFG.core.getCiv(nCivID).getTechLevel() >= BuildingsManager.getWorkshop_TechLevel(1) && CFG.oR.nextInt(1000) < GameValues.gvProvince.STARTING_BUILDING_WORKSHOP_RANDOM_1000) {
                    CFG.core.getProv(CFG.core.getCiv(nCivID).getCapitalProvID()).setLvlOfWorkshop(1);
                }
                if (CFG.core.getCiv(nCivID).getTechLevel() >= BuildingsManager.getLibrary_TechLevel(1) && CFG.oR.nextInt(1000) < GameValues.gvProvince.STARTING_BUILDING_LIBRARY_RANDOM_1000) {
                    CFG.core.getProv(CFG.core.getCiv(nCivID).getCapitalProvID()).setLvlOfLibrary(1);
                }
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    public final void buildStartingBuildings_Port(int nCivID) {
        int buildPortInProvinceID = -1;
        if (CFG.core.getProv(CFG.core.getCiv(nCivID).getCapitalProvID()).getLvlOfPort() >= 0) {
            buildPortInProvinceID = CFG.core.getCiv(nCivID).getCapitalProvID();
        } else {
            for (int j = 0; j < CFG.core.getCiv(nCivID).getNumOfProvs(); ++j) {
                if (CFG.core.getProv(CFG.core.getCiv(nCivID).getProvID(j)).getLvlOfPort() != 0) continue;
                if (buildPortInProvinceID < 0) {
                    buildPortInProvinceID = CFG.core.getCiv(nCivID).getProvID(j);
                    continue;
                }
                if (CFG.core.getProv(buildPortInProvinceID).getPop().getPops() >= CFG.core.getProv(CFG.core.getCiv(nCivID).getProvID(j)).getPop().getPops()) continue;
                buildPortInProvinceID = CFG.core.getCiv(nCivID).getProvID(j);
            }
        }
        if (buildPortInProvinceID >= 0 && CFG.core.getProv(buildPortInProvinceID).getLvlOfPort() >= 0) {
            CFG.core.getProv(buildPortInProvinceID).setLvlOfPort(1);
        }
    }

    public static final long getMoney_MinReserve_LockTreasury(int nCivID) {
        if (CFG.core.getCiv((int)nCivID).civGD.changeTypeOfGovernment != null) {
            return Math.max(CFG.core.getCiv((int)nCivID).civGD.changeTypeOfGovernment.iCost, CFG.core.getCiv((int)nCivID).civGD.iLockTreasury);
        }
        return CFG.core.getCiv((int)nCivID).civGD.iLockTreasury;
    }

    public static final long getMoney_MinReserve(int nCivID) {
        return (long)Math.max((float)AIPlaystyle.getMoney_MinReserve_LockTreasury(nCivID), (float)CFG.core.getCiv((int)nCivID).iBudget * CFG.core.getCiv((int)nCivID).civGD.civPers.TREASURY_RESERVE);
    }

    public void manageBudget(int nCivID) {
        CFG.core.getCiv(nCivID).setSpendingGoodsB(Math.max(CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(nCivID).getIdeology()).getMin_Goods(nCivID) + 0.01f, CFG.core.getCiv(nCivID).getSpendingGoodsB()));
        CFG.core.getCiv(nCivID).setSpendingInvestmentsB(Math.max(CFG.ideologiesMgr.getInvestments(CFG.core.getCiv(nCivID).getIdeology(), nCivID) + 0.01f, CFG.core.getCiv(nCivID).getSpendingInvestmentsB()));
        if (CFG.core.getCiv(nCivID).isAtWarC() || CFG.core.getCiv((int)nCivID).civGD.civPlans.isPreparingForTheWar()) {
            float happinessDiff;
            if (!CFG.core.getCiv((int)nCivID).isAtWarWithCivs.isEmpty()) {
                int iBudgetOfEnemies = 0;
                for (int i = CFG.core.getCiv((int)nCivID).isAtWarWithCivs.size() - 1; i >= 0; --i) {
                    iBudgetOfEnemies += (int)Math.max(1.0f, (float)CFG.core.getCiv((int)CFG.core.getCiv((int)nCivID).isAtWarWithCivs.get((int)i).intValue()).iBudget * CFG.core.getCiv((int)nCivID).civGD.civPers.MIN_MILITARY_SPENDINGS_WAR_MODIFIER);
                }
                CFG.core.getCiv((int)nCivID).civGD.civPers.MIN_MILITARY_SPENDINGS_WAR = Math.max(Math.min(2.0f, (float)iBudgetOfEnemies / (float)CFG.core.getCiv((int)nCivID).iBudget), CFG.core.getCiv((int)nCivID).civGD.civPers.MIN_MILITARY_SPENDINGS);
            } else {
                CFG.core.getCiv((int)nCivID).civGD.civPers.MIN_MILITARY_SPENDINGS_WAR = CFG.core.getCiv((int)nCivID).civGD.civPers.MIN_MILITARY_SPENDINGS;
            }
            float fHappinessLeft = GameValues.gvAiBudget.WAR_BASE_HAPPINESS;
            if (CFG.core.getCiv(nCivID).getHappiness() - CFG.core.getCiv((int)nCivID).civGD.civPers.MIN_HAPPINESS_FOR_CIV < 0) {
                if (CFG.core.getCiv(nCivID).getHappiness() < GameValues.gvAiBudget.WAR_HAPPINESS_THRESHOLD_VERY_LOW) {
                    fHappinessLeft = (float)CFG.core.getCiv((int)nCivID).civGD.civPers.MIN_HAPPINESS_FOR_CIV / 100.0f / GameValues.gvAiBudget.WAR_HAPPINESS_DIVISOR_VERY_LOW;
                    happinessDiff = 0.0f;
                } else if (CFG.core.getCiv(nCivID).getHappiness() < GameValues.gvAiBudget.WAR_HAPPINESS_THRESHOLD_LOW) {
                    fHappinessLeft = (float)CFG.core.getCiv((int)nCivID).civGD.civPers.MIN_HAPPINESS_FOR_CIV / 100.0f / GameValues.gvAiBudget.WAR_HAPPINESS_DIVISOR_LOW;
                    happinessDiff = 0.0f;
                } else if (CFG.core.getCiv(nCivID).getHappiness() < GameValues.gvAiBudget.WAR_HAPPINESS_THRESHOLD_MEDIUM) {
                    fHappinessLeft = (float)CFG.core.getCiv((int)nCivID).civGD.civPers.MIN_HAPPINESS_FOR_CIV / 100.0f / GameValues.gvAiBudget.WAR_HAPPINESS_DIVISOR_MEDIUM;
                    happinessDiff = 0.0f;
                } else {
                    happinessDiff = (1.0f - fHappinessLeft) * ((float)CFG.core.getCiv(nCivID).getHappiness() / (float)CFG.core.getCiv((int)nCivID).civGD.civPers.MIN_HAPPINESS_FOR_CIV);
                }
            } else {
                happinessDiff = 1.0f - fHappinessLeft;
                if (CFG.core.getCiv(nCivID).getHappiness() > GameValues.gvAiBudget.WAR_HAPPINESS_THRESHOLD_HIGH && (float)CFG.core.getCiv(nCivID).getHappiness() > CFG.core.getCiv((int)nCivID).civGD.civPers.MIN_PROVINCE_HAPPINESS_RUN_FESTIVAL * GameValues.gvAiBudget.WAR_MIN_PROVINCE_HAPPINESS_RUN_FESTIVAL_MODIFIER) {
                    happinessDiff = 1.0f - fHappinessLeft + (float)CFG.oR.nextInt(GameValues.gvAiBudget.WAR_HAPPINESS_HIGH_RANDOM_1000) / 1000.0f;
                }
            }
            CFG.core.getCiv(nCivID).setTaxationLvl((CFG.ideologiesMgr.getAcceptableTaxation(CFG.core.getCiv(nCivID).getIdeology(), nCivID) * fHappinessLeft + CFG.ideologiesMgr.getAcceptableTaxation(CFG.core.getCiv(nCivID).getIdeology(), nCivID) * happinessDiff) * CFG.core.getCiv((int)nCivID).civGD.civPers.TAXATION_LEVEL);
            this.updateMilitarySpending(nCivID);
            float reserveModifier = GameValues.gvAiBudget.WAR_GOLD_RESERVE_BASE;
            float nSpendingsLeft = GameValues.gvAiBudget.WAR_TOTAL_BUDGET_BASE - CFG.core.getCiv((int)nCivID).iMilitaryUpkeep_PERC - CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(nCivID).getIdeology()).getMin_Goods(nCivID) - CFG.ideologiesMgr.getInvestments(CFG.core.getCiv(nCivID).getIdeology(), nCivID);
            reserveModifier = CFG.core.getCiv((int)nCivID).civGD.civPers.MIN_MILITARY_SPENDINGS_WAR > CFG.core.getCiv((int)nCivID).iMilitaryUpkeep_PERC ? reserveModifier - GameValues.gvAiBudget.WAR_GOLD_RESERVE_REDUCTION_BASE - GameValues.gvAiBudget.WAR_GOLD_RESERVE_REDUCTION_SCALE * (1.0f - CFG.core.getCiv((int)nCivID).iMilitaryUpkeep_PERC / Math.min(1.0f, CFG.core.getCiv((int)nCivID).civGD.civPers.MIN_MILITARY_SPENDINGS_WAR)) : GameValues.gvAiBudget.WAR_GOLD_RESERVE_LOW;
            if (CFG.gameUpdate.getInflationPerc(nCivID) * 100.0f > 0.0f) {
                reserveModifier = 1.0f + CFG.gameUpdate.getInflationPerc(nCivID) * 100.0f;
            }
            if (nSpendingsLeft > 0.0f) {
                if (CFG.core.getCiv(nCivID).getGold() < 0L) {
                    float tTotal = CFG.core.getCiv((int)nCivID).civGD.civPers.GOODS_EXTRA_PERC_OF_BUDGET + CFG.core.getCiv((int)nCivID).civGD.civPers.INVESTMENTS_EXTRA_PERC_OF_BUDGET + CFG.core.getCiv((int)nCivID).civGD.civPers.RESEARCH_PERC_OF_BUDGET;
                    CFG.core.getCiv(nCivID).setSpendingGoodsB(Math.max(CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(nCivID).getIdeology()).getMin_Goods(nCivID) + CFG.core.getCiv((int)nCivID).civGD.civPers.GOODS_EXTRA_PERC_OF_BUDGET / tTotal * ((nSpendingsLeft *= GameValues.gvAiBudget.WAR_NO_MONEY_SPENDING_MIN + (float)CFG.oR.nextInt(GameValues.gvAiBudget.WAR_NO_MONEY_SPENDING_RAND_100) / 100.0f) * CFG.core.getCiv((int)nCivID).civGD.civPers.USE_OF_BUDGET_FOR_SPENDINGS), CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(nCivID).getIdeology()).getMin_Goods(nCivID)));
                    CFG.core.getCiv(nCivID).setSpendingInvestmentsB(Math.max(CFG.ideologiesMgr.getInvestments(CFG.core.getCiv(nCivID).getIdeology(), nCivID) + CFG.core.getCiv((int)nCivID).civGD.civPers.INVESTMENTS_EXTRA_PERC_OF_BUDGET / tTotal * (nSpendingsLeft * CFG.core.getCiv((int)nCivID).civGD.civPers.USE_OF_BUDGET_FOR_SPENDINGS), CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).MIN_INVESTMENTS));
                    CFG.core.getCiv(nCivID).setSpendingResearchB(0.0f);
                } else {
                    float extraDevelopment = 1.0f;
                    if (CFG.core.getCiv((int)nCivID).fAverageDevelopment / CFG.core.getCiv(nCivID).getTechLevel() < CFG.core.getCiv((int)nCivID).civGD.civPers.MIN_DIFFERENCE_IN_DEVELOPMENT_TO_TECHNOLOGY) {
                        extraDevelopment = 1.0f - CFG.core.getCiv((int)nCivID).fAverageDevelopment / CFG.core.getCiv(nCivID).getTechLevel();
                    }
                    float tTotal = CFG.core.getCiv((int)nCivID).civGD.civPers.GOODS_EXTRA_PERC_OF_BUDGET + CFG.core.getCiv((int)nCivID).civGD.civPers.INVESTMENTS_EXTRA_PERC_OF_BUDGET + (CFG.ideologiesMgr.getInvestments(CFG.core.getCiv(nCivID).getIdeology(), nCivID) + CFG.core.getCiv((int)nCivID).civGD.civPers.INVESTMENTS_EXTRA_PERC_OF_BUDGET) * extraDevelopment + CFG.core.getCiv((int)nCivID).civGD.civPers.RESEARCH_PERC_OF_BUDGET;
                    CFG.core.getCiv(nCivID).setSpendingGoodsB(Math.max(CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(nCivID).getIdeology()).getMin_Goods(nCivID) + CFG.core.getCiv((int)nCivID).civGD.civPers.GOODS_EXTRA_PERC_OF_BUDGET / tTotal * (nSpendingsLeft * CFG.core.getCiv((int)nCivID).civGD.civPers.USE_OF_BUDGET_FOR_SPENDINGS) * reserveModifier, CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(nCivID).getIdeology()).getMin_Goods(nCivID)));
                    CFG.core.getCiv(nCivID).setSpendingInvestmentsB(Math.max((CFG.ideologiesMgr.getInvestments(CFG.core.getCiv(nCivID).getIdeology(), nCivID) + CFG.core.getCiv((int)nCivID).civGD.civPers.INVESTMENTS_EXTRA_PERC_OF_BUDGET + (CFG.ideologiesMgr.getInvestments(CFG.core.getCiv(nCivID).getIdeology(), nCivID) + CFG.core.getCiv((int)nCivID).civGD.civPers.INVESTMENTS_EXTRA_PERC_OF_BUDGET) * extraDevelopment) / tTotal * (nSpendingsLeft * CFG.core.getCiv((int)nCivID).civGD.civPers.USE_OF_BUDGET_FOR_SPENDINGS) * reserveModifier, CFG.ideologiesMgr.getInvestments(CFG.core.getCiv(nCivID).getIdeology(), nCivID)));
                    CFG.core.getCiv(nCivID).setSpendingResearchB(CFG.core.getCiv((int)nCivID).civGD.civPers.RESEARCH_PERC_OF_BUDGET / tTotal * (nSpendingsLeft * CFG.core.getCiv((int)nCivID).civGD.civPers.USE_OF_BUDGET_FOR_SPENDINGS) * reserveModifier);
                }
            } else {
                CFG.core.getCiv(nCivID).setSpendingResearchB(0.0f);
            }
        } else {
            float happinessDiff;
            float reserveModifier = GameValues.gvAiBudget.GOLD_RESERVE_BASE;
            if (CFG.core.getCiv(nCivID).getGold() < AIPlaystyle.getMoney_MinReserve_LockTreasury(nCivID)) {
                reserveModifier = GameValues.gvAiBudget.GOLD_RESERVE_LOCKED;
                if (CFG.core.getCiv(nCivID).getGold() > 0L) {
                    reserveModifier += GameValues.gvAiBudget.GOLD_RESERVE_LOCKED_BONUS * ((float)CFG.core.getCiv(nCivID).getGold() / (float)AIPlaystyle.getMoney_MinReserve_LockTreasury(nCivID));
                }
            } else {
                if (CFG.core.getCiv(nCivID).getGold() < AIPlaystyle.getMoney_MinReserve(nCivID)) {
                    if (CFG.gameUpdate.getInflationPerc(nCivID) * 100.0f > 0.0f) {
                        reserveModifier = 1.0f + CFG.gameUpdate.getInflationPerc(nCivID) * 100.0f;
                        CFG.core.getCiv((int)nCivID).civGD.civPers.TREASURY_RESERVE = Math.max(GameValues.gvAiBudget.GOLD_RESERVE_TREASURY_MAX, CFG.core.getCiv((int)nCivID).civGD.civPers.TREASURY_RESERVE - GameValues.gvAiBudget.GOLD_RESERVE_TREASURY_DECREASE);
                    } else {
                        reserveModifier = CFG.core.getCiv((int)nCivID).civGD.civPers.TREASURY_RESERVE_MODIFIER + (1.0f - CFG.core.getCiv((int)nCivID).civGD.civPers.TREASURY_RESERVE_MODIFIER) * (float)CFG.core.getCiv(nCivID).getGold() / ((float)CFG.core.getCiv((int)nCivID).iBudget * CFG.core.getCiv((int)nCivID).civGD.civPers.TREASURY_RESERVE);
                    }
                } else if (CFG.gameUpdate.getInflationPerc(nCivID) * 100.0f > 0.0f) {
                    reserveModifier = 1.0f + CFG.gameUpdate.getInflationPerc(nCivID) * 100.0f;
                }
                if (!CFG.core.getCiv((int)nCivID).provincesWithLowStability.isEmpty()) {
                    int tAssimilateCost = GameManager.assimilateCost(CFG.core.getCiv((int)nCivID).provincesWithLowStability.get(0), GameValues.gvAssimilate.ASSIMILATE_NUM_OF_TURNS_MAX / 2) * CFG.core.getCiv((int)nCivID).provincesWithLowStability.size();
                    reserveModifier = Math.min(reserveModifier, GameValues.gvAiBudget.GOLD_RESERVE_ASSIMILATION_MIN + GameValues.gvAiBudget.GOLD_RESERVE_ASSIMILATION_SCALE * (float)CFG.core.getCiv(nCivID).getGold() / (float)tAssimilateCost);
                }
            }
            float fHappinessLeft = GameValues.gvAiBudget.BASE_HAPPINESS;
            if (CFG.core.getCiv(nCivID).getHappiness() - CFG.core.getCiv((int)nCivID).civGD.civPers.MIN_HAPPINESS_FOR_CIV < 0) {
                if (CFG.core.getCiv(nCivID).getHappiness() < GameValues.gvAiBudget.HAPPINESS_THRESHOLD_VERY_LOW) {
                    fHappinessLeft = (float)CFG.core.getCiv((int)nCivID).civGD.civPers.MIN_HAPPINESS_FOR_CIV / 100.0f / GameValues.gvAiBudget.HAPPINESS_DIVISOR_VERY_LOW;
                    happinessDiff = 0.0f;
                } else if (CFG.core.getCiv(nCivID).getHappiness() < GameValues.gvAiBudget.HAPPINESS_THRESHOLD_LOW) {
                    fHappinessLeft = (float)CFG.core.getCiv((int)nCivID).civGD.civPers.MIN_HAPPINESS_FOR_CIV / 100.0f / GameValues.gvAiBudget.HAPPINESS_DIVISOR_LOW;
                    happinessDiff = 0.0f;
                } else if (CFG.core.getCiv(nCivID).getHappiness() < GameValues.gvAiBudget.HAPPINESS_THRESHOLD_MEDIUM) {
                    fHappinessLeft = (float)CFG.core.getCiv((int)nCivID).civGD.civPers.MIN_HAPPINESS_FOR_CIV / 100.0f / GameValues.gvAiBudget.HAPPINESS_DIVISOR_MEDIUM;
                    happinessDiff = 0.0f;
                } else {
                    happinessDiff = (1.0f - fHappinessLeft) * ((float)CFG.core.getCiv(nCivID).getHappiness() / (float)CFG.core.getCiv((int)nCivID).civGD.civPers.MIN_HAPPINESS_FOR_CIV);
                }
            } else {
                happinessDiff = 1.0f - fHappinessLeft;
                if (CFG.core.getCiv(nCivID).getHappiness() > GameValues.gvAiBudget.HAPPINESS_THRESHOLD_HIGH && (float)CFG.core.getCiv(nCivID).getHappiness() > CFG.core.getCiv((int)nCivID).civGD.civPers.MIN_PROVINCE_HAPPINESS_RUN_FESTIVAL * GameValues.gvAiBudget.MIN_PROVINCE_HAPPINESS_RUN_FESTIVAL_MODIFIER) {
                    happinessDiff = 1.0f - fHappinessLeft + (float)CFG.oR.nextInt(GameValues.gvAiBudget.HAPPINESS_HIGH_RANDOM_1000) / 1000.0f;
                }
            }
            CFG.core.getCiv(nCivID).setTaxationLvl((CFG.ideologiesMgr.getAcceptableTaxation(CFG.core.getCiv(nCivID).getIdeology(), nCivID) * fHappinessLeft + CFG.ideologiesMgr.getAcceptableTaxation(CFG.core.getCiv(nCivID).getIdeology(), nCivID) * happinessDiff) * CFG.core.getCiv((int)nCivID).civGD.civPers.TAXATION_LEVEL);
            this.updateMilitarySpending(nCivID);
            float nSpendingsLeft = GameValues.gvAiBudget.TOTAL_BUDGET_BASE - CFG.core.getCiv((int)nCivID).iMilitaryUpkeep_PERC - CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(nCivID).getIdeology()).getMin_Goods(nCivID) - CFG.ideologiesMgr.getInvestments(CFG.core.getCiv(nCivID).getIdeology(), nCivID);
            if (nSpendingsLeft > 0.0f) {
                if (CFG.core.getCiv(nCivID).getGold() < 0L) {
                    float tTotal = CFG.core.getCiv((int)nCivID).civGD.civPers.GOODS_EXTRA_PERC_OF_BUDGET + CFG.core.getCiv((int)nCivID).civGD.civPers.INVESTMENTS_EXTRA_PERC_OF_BUDGET + CFG.core.getCiv((int)nCivID).civGD.civPers.RESEARCH_PERC_OF_BUDGET;
                    CFG.core.getCiv(nCivID).setSpendingGoodsB(Math.max(CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(nCivID).getIdeology()).getMin_Goods(nCivID) + CFG.core.getCiv((int)nCivID).civGD.civPers.GOODS_EXTRA_PERC_OF_BUDGET / tTotal * ((nSpendingsLeft *= GameValues.gvAiBudget.NO_MONEY_SPENDING_MIN + (float)CFG.oR.nextInt(GameValues.gvAiBudget.NO_MONEY_SPENDING_RAND_100) / 100.0f) * CFG.core.getCiv((int)nCivID).civGD.civPers.USE_OF_BUDGET_FOR_SPENDINGS), CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(nCivID).getIdeology()).getMin_Goods(nCivID)));
                    CFG.core.getCiv(nCivID).setSpendingInvestmentsB(Math.max(CFG.ideologiesMgr.getInvestments(CFG.core.getCiv(nCivID).getIdeology(), nCivID) + CFG.core.getCiv((int)nCivID).civGD.civPers.INVESTMENTS_EXTRA_PERC_OF_BUDGET / tTotal * (nSpendingsLeft * CFG.core.getCiv((int)nCivID).civGD.civPers.USE_OF_BUDGET_FOR_SPENDINGS), CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).MIN_INVESTMENTS));
                    CFG.core.getCiv(nCivID).setSpendingResearchB(0.0f);
                } else {
                    float extraDevelopment = 1.0f;
                    if (CFG.core.getCiv((int)nCivID).fAverageDevelopment / CFG.core.getCiv(nCivID).getTechLevel() < CFG.core.getCiv((int)nCivID).civGD.civPers.MIN_DIFFERENCE_IN_DEVELOPMENT_TO_TECHNOLOGY) {
                        extraDevelopment = 1.0f + (CFG.core.getCiv((int)nCivID).civGD.civPers.MIN_DIFFERENCE_IN_DEVELOPMENT_TO_TECHNOLOGY - CFG.core.getCiv((int)nCivID).fAverageDevelopment / CFG.core.getCiv(nCivID).getTechLevel()) / CFG.core.getCiv(nCivID).getTechLevel();
                    }
                    float tTotal = CFG.core.getCiv((int)nCivID).civGD.civPers.GOODS_EXTRA_PERC_OF_BUDGET + CFG.core.getCiv((int)nCivID).civGD.civPers.INVESTMENTS_EXTRA_PERC_OF_BUDGET * extraDevelopment + CFG.core.getCiv((int)nCivID).civGD.civPers.RESEARCH_PERC_OF_BUDGET;
                    CFG.core.getCiv(nCivID).setSpendingGoodsB(Math.max(CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(nCivID).getIdeology()).getMin_Goods(nCivID) + CFG.core.getCiv((int)nCivID).civGD.civPers.GOODS_EXTRA_PERC_OF_BUDGET / tTotal * (nSpendingsLeft * CFG.core.getCiv((int)nCivID).civGD.civPers.USE_OF_BUDGET_FOR_SPENDINGS) * reserveModifier, CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(nCivID).getIdeology()).getMin_Goods(nCivID)));
                    CFG.core.getCiv(nCivID).setSpendingInvestmentsB(Math.max(CFG.ideologiesMgr.getInvestments(CFG.core.getCiv(nCivID).getIdeology(), nCivID) + CFG.core.getCiv((int)nCivID).civGD.civPers.INVESTMENTS_EXTRA_PERC_OF_BUDGET * extraDevelopment / tTotal * (nSpendingsLeft * CFG.core.getCiv((int)nCivID).civGD.civPers.USE_OF_BUDGET_FOR_SPENDINGS) * reserveModifier, CFG.ideologiesMgr.getInvestments(CFG.core.getCiv(nCivID).getIdeology(), nCivID)));
                    CFG.core.getCiv(nCivID).setSpendingResearchB(CFG.core.getCiv((int)nCivID).civGD.civPers.RESEARCH_PERC_OF_BUDGET / tTotal * (nSpendingsLeft * CFG.core.getCiv((int)nCivID).civGD.civPers.USE_OF_BUDGET_FOR_SPENDINGS) * reserveModifier);
                }
            } else {
                CFG.core.getCiv(nCivID).setSpendingResearchB(0.0f);
            }
            if (CFG.core.getCiv(nCivID).getGold() > (long)GameValues.gvAiBudget.MAX_RESEARCH_IF_GOLD_OVER && CFG.core.getCiv(nCivID).getTechLevel() < GameValues.gvTechnology.MAX_TECHNOLOGY_LEVEL) {
                CFG.core.getCiv(nCivID).setSpendingResearchB(1.0f);
            }
        }
    }

    public final void manageVassalsTribute(int nCivID) {
        try {
            for (int i = 0; i < CFG.core.getCiv((int)nCivID).civGD.vassals.size(); ++i) {
                CFG.core.getCiv((int)nCivID).civGD.vassals.get(i).setTribute(Math.min((int)((float)GameValues.gvVassal.PERCENTAGE_OF_INCOME_FOR_LORD_MAX * GameValues.gvAiVassals.PERCENTAGE_OF_INCOME_FOR_LORD_MAX_PERC_MAX), (int)((float)GameValues.gvVassal.PERCENTAGE_OF_INCOME_FOR_LORD_MAX * (CFG.core.getCiv((int)nCivID).VASSALS_TRIBUTE_PERC - (CFG.core.getCivRelationOfCivB(nCivID, CFG.core.getCiv((int)nCivID).civGD.vassals.get((int)i).iCivID) > 0.0f ? CFG.core.getCiv((int)nCivID).VASSALS_TRIBUTE_PERC * CFG.core.getCiv((int)nCivID).VASSALS_TRIBUTE_PERC_FRIENDLY * CFG.core.getCivRelationOfCivB(nCivID, CFG.core.getCiv((int)nCivID).civGD.vassals.get((int)i).iCivID) / 100.0f : 0.0f) + (float)CFG.oR.nextInt((int)(CFG.core.getCiv((int)nCivID).VASSALS_TRIBUTE_PERC_RAND * 100.0f)) / 100.0f))));
                if (!(CFG.core.getCiv(CFG.core.getCiv((int)nCivID).civGD.vassals.get((int)i).iCivID).getRelationD(nCivID) < 0.0f)) continue;
                CFG.core.getCiv(nCivID).getCivDiploGD().addImproveRelations(nCivID, CFG.core.getCiv((int)nCivID).civGD.vassals.get((int)i).iCivID, Math.min(GameValues.gvAiVassals.UPDATE_VASSALS_TRIBUTE, 5 + Math.abs((int)(CFG.core.getCiv(CFG.core.getCiv((int)nCivID).civGD.vassals.get((int)i).iCivID).getRelationD(nCivID) - 1.0f))));
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    public final void happinessCrisis(int nCivID) {
        try {
            if (!CFG.core.getCiv((int)nCivID).provincesWithLowHappiness.isEmpty() && CFG.core.getCiv(nCivID).getMovemPoints() >= GameValues.gvFestival.COST_FESTIVAL_MOVEMENT_POINTS && (float)CFG.core.getCiv(nCivID).getGold() >= 0.5f * (float)Festival.festivalCost(CFG.core.getCiv((int)nCivID).provincesWithLowHappiness.get(0))) {
                ArrayList<AI_Assimilate_Data> tempProvincesScore = new ArrayList<AI_Assimilate_Data>();
                for (int i = CFG.core.getCiv((int)nCivID).provincesWithLowHappiness.size() - 1; i >= 0; --i) {
                    if (CFG.core.getProv(CFG.core.getCiv((int)nCivID).provincesWithLowHappiness.get(i)).getHappi() < GameValues.gvRebels.RISE_REVOLT_RISK_IN_PROVINCE_IF_HAPPINESS_BELOW) {
                        tempProvincesScore.add(new AI_Assimilate_Data(CFG.core.getCiv((int)nCivID).provincesWithLowHappiness.get(i), (float)CFG.core.getProv(CFG.core.getCiv((int)nCivID).provincesWithLowHappiness.get(i)).getPop().getPops() * (1.0f - CFG.core.getProv(CFG.core.getCiv((int)nCivID).provincesWithLowHappiness.get(i)).getHappi() / 4.0f)));
                        continue;
                    }
                    tempProvincesScore.add(new AI_Assimilate_Data(CFG.core.getCiv((int)nCivID).provincesWithLowHappiness.get(i), (float)CFG.core.getProv(CFG.core.getCiv((int)nCivID).provincesWithLowHappiness.get(i)).getPop().getPops() * (1.0f - CFG.core.getProv(CFG.core.getCiv((int)nCivID).provincesWithLowHappiness.get(i)).getHappi())));
                }
                ArrayList<AI_Assimilate_Data> tempSorted = new ArrayList<AI_Assimilate_Data>();
                while (!tempProvincesScore.isEmpty()) {
                    int tBest = 0;
                    for (int i = tBest + 1; i < tempProvincesScore.size(); ++i) {
                        if (!(((AI_Assimilate_Data)tempProvincesScore.get((int)i)).fScore > ((AI_Assimilate_Data)tempProvincesScore.get((int)tBest)).fScore)) continue;
                        tBest = i;
                    }
                    tempSorted.add((AI_Assimilate_Data)tempProvincesScore.get(tBest));
                    tempProvincesScore.remove(tBest);
                }
                while (CFG.core.getCiv(nCivID).getMovemPoints() >= GameValues.gvFestival.COST_FESTIVAL_MOVEMENT_POINTS && !tempSorted.isEmpty() && Festival.addFestival(nCivID, ((AI_Assimilate_Data)tempSorted.get((int)0)).iProvinceID)) {
                    tempSorted.remove(0);
                }
                tempSorted.clear();
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    public void updateMilitarySpending(int nCivID) {
        CFG.core.getCiv((int)nCivID).iMilitaryUpkeep_Total = (int)CFG.gameUpdate.getMilitaryUpkeep_Total(nCivID);
        CFG.core.getCiv((int)nCivID).iMilitaryUpkeep_PERC = CFG.core.getCiv((int)nCivID).iBudget <= 0 && CFG.core.getCiv(nCivID).getNumberOfUnits() > 0 ? 100.0f : Math.max(0.0f, (float)CFG.core.getCiv((int)nCivID).iMilitaryUpkeep_Total / (float)CFG.core.getCiv((int)nCivID).iBudget);
    }

    public long build_GetMoney(int nCivID) {
        if (CFG.core.getCiv(nCivID).getGold() < AIPlaystyle.getMoney_MinReserve(nCivID)) {
            return 0L;
        }
        return CFG.core.getCiv(nCivID).getGold() - AIPlaystyle.getMoney_MinReserve(nCivID);
    }

    public void buildBuildings(int nCivID) {
        if (this.build_GetMoney(nCivID) > 0L) {
            ArrayList<AI_Build> buildingsScore = new ArrayList<AI_Build>();
            ArrayList<AI_Build_Option> buildingsOptions = new ArrayList<AI_Build_Option>();
            Civilization civ = CFG.core.getCiv(nCivID);
            try {
                if (civ.getTechLevel() >= BuildingsManager.getFarm_TechLevel(1) && civ.numOf_Farms_ProvincesPossibleToBuild * BuildingsManager.getWorkshop_MaxLevel_CanBuild(nCivID) > civ.numOf_Farms) {
                    buildingsOptions.add(new AI_Build_Option());
                }
                if (civ.getTechLevel() >= BuildingsManager.getWorkshop_TechLevel(1) && civ.getNumOfProvs() * BuildingsManager.getWorkshop_MaxLevel_CanBuild(nCivID) > civ.numOf_Workshops) {
                    buildingsOptions.add(new AI_Build_Option_Workshop());
                }
                if (civ.getTechLevel() >= BuildingsManager.getMarket_TechLevel(1) && civ.getNumOfProvs() * BuildingsManager.getMarket_MaxLevel_CanBuild(nCivID) > civ.numOf_Markets) {
                    buildingsOptions.add(new AI_Build_Option_Market());
                }
                if (civ.getTechLevel() >= BuildingsManager.getLibrary_TechLevel(1) && civ.getNumOfProvs() * BuildingsManager.getLibrary_MaxLevel_CanBuild(nCivID) > civ.numOf_Libraries) {
                    buildingsOptions.add(new AI_Build_Option_Library());
                }
                if (civ.getSeaAccess() > 0 && civ.getTechLevel() >= BuildingsManager.getPort_TechLevel(1) && civ.getNumOfProvs() > civ.numOf_Ports) {
                    buildingsOptions.add(new AI_Build_Option_Port());
                }
                if (civ.getTechLevel() >= BuildingsManager.getArmoury_TechLevel(1) && civ.getNumOfProvs() > civ.numOf_Armories) {
                    buildingsOptions.add(new AI_Build_Option_Armoury());
                }
                if (civ.getTechLevel() >= BuildingsManager.getSupply_TechLevel(1) && civ.getNumOfProvs() > civ.numOf_SuppliesCamp) {
                    buildingsOptions.add(new AI_Build_Option_Supplies());
                }
                if (civ.getTechLevel() >= BuildingsManager.getFort_TechLevel(1) && civ.getNumOfProvs() * BuildingsManager.getFort_MaxLevel_CanBuild(nCivID) > civ.numOf_Forts) {
                    buildingsOptions.add(new AI_Build_Option_Fort());
                }
                if (civ.getTechLevel() >= BuildingsManager.getTower_TechLevel(1) && civ.getNumOfProvs() * BuildingsManager.getTower_MaxLevel_CanBuild(nCivID) > civ.numOf_Towers) {
                    buildingsOptions.add(new AI_Build_Option_Tower());
                }
                buildingsOptions.add(new AI_Build_Option_Invest());
                if (civ.fAverageDevelopment / civ.getTechLevel() < GameValues.gvAiInvest.INVEST_DEV_DEVELOPMENT_TO_TECH_RATIO) {
                    buildingsOptions.add(new AI_Build_Option_Invest_Development());
                }
                buildingsOptions.add(new AI_Build_Option_Invest());
                if (!buildingsOptions.isEmpty()) {
                    int tBestScore = 0;
                    for (int i = tBestScore + 1; i < buildingsOptions.size(); ++i) {
                        if (!(((AI_Build_Option)buildingsOptions.get(i)).getScore(nCivID) > ((AI_Build_Option)buildingsOptions.get(tBestScore)).getScore(nCivID))) continue;
                        tBestScore = i;
                    }
                    buildingsScore.add(((AI_Build_Option)buildingsOptions.get(tBestScore)).getData(nCivID));
                    if (((AI_Build)buildingsScore.get(0)).build(nCivID, 0, false)) {
                        civ.buildCivPersonality_Buildings();
                    }
                }
            }
            catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
            buildingsOptions.clear();
            buildingsOptions = null;
            buildingsScore.clear();
            Object var2_2 = null;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void buildInvestEco(int nCivID) {
        if (this.build_GetMoney(nCivID) <= 0L) return;
        try {
            AI_Build_Option_Invest2 option = new AI_Build_Option_Invest2();
            AI_Build buildData = ((AI_Build_Option)option).getData(nCivID);
            if (!buildData.build(nCivID, 0, false)) return;
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void buildInvestDev(int nCivID) {
        if (this.build_GetMoney(nCivID) <= 0L) return;
        try {
            AI_Build_Option_Invest_Development2 option = new AI_Build_Option_Invest_Development2();
            AI_Build buildData = ((AI_Build_Option)option).getData(nCivID);
            if (!buildData.build(nCivID, 0, false)) return;
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    public final void prepareArmyForRevolution(int nCivID) {
    }

    public final void assimilateProvinces(int nCivID) {
        try {
            if (CFG.core.getCiv(nCivID).getDiploPoints() >= GameValues.gvAssimilate.COST_ASSIMILATE_MOVEMENT && (float)CFG.core.getCiv(nCivID).getGold() >= GameValues.gvAssimilate.AI_ASSIMILATE_MIN_GOLD_MODIFIER * (float)GameManager.assimilateCost(CFG.core.getCiv((int)nCivID).provincesWithLowStability.get(0), GameValues.gvAssimilate.ASSIMILATE_NUM_OF_TURNS_MIN)) {
                ArrayList<AI_Assimilate_Data> tempAssimilateProvinces = new ArrayList<AI_Assimilate_Data>();
                int tempCapital = CFG.core.getCiv(nCivID).getCapitalProvID() >= 0 ? CFG.core.getCiv(nCivID).getCapitalProvID() : CFG.core.getCiv(nCivID).getProvID(0);
                for (int i = CFG.core.getCiv((int)nCivID).provincesWithLowStability.size() - 1; i >= 0; --i) {
                    tempAssimilateProvinces.add(new AI_Assimilate_Data(CFG.core.getCiv((int)nCivID).provincesWithLowStability.get(i), CFG.core.getCiv((int)nCivID).civGD.civPers.ASSIMILATE_PERC_POPULATION_SCORE * Math.min((float)CFG.core.getProv(CFG.core.getCiv((int)nCivID).provincesWithLowStability.get(i)).getPop().getPops() / (float)CFG.core.getGameScenars().getScenario_StartingPopulation(), 1.0f) + CFG.core.getCiv((int)nCivID).civGD.civPers.ASSIMILATE_PERC_DISTANCE_SCORE * Distance.getDistanceFromCapital_PercOfMax(tempCapital, CFG.core.getCiv((int)nCivID).provincesWithLowStability.get(i)) + CFG.core.getCiv((int)nCivID).civGD.civPers.ASSIMILATE_PERC_LOW_STABILITY_SCORE * (1.0f - CFG.core.getProv(CFG.core.getCiv((int)nCivID).provincesWithLowStability.get(i)).getProviStability())));
                }
                ArrayList<AI_Assimilate_Data> tempSortedAssimilate = new ArrayList<AI_Assimilate_Data>();
                while (!tempAssimilateProvinces.isEmpty()) {
                    int tBest = 0;
                    for (int i = tBest + 1; i < tempAssimilateProvinces.size(); ++i) {
                        if (!(((AI_Assimilate_Data)tempAssimilateProvinces.get((int)i)).fScore > ((AI_Assimilate_Data)tempAssimilateProvinces.get((int)tBest)).fScore)) continue;
                        tBest = i;
                    }
                    tempSortedAssimilate.add((AI_Assimilate_Data)tempAssimilateProvinces.get(tBest));
                    tempAssimilateProvinces.remove(tBest);
                }
                while (CFG.core.getCiv(nCivID).getDiploPoints() >= GameValues.gvAssimilate.COST_ASSIMILATE_MOVEMENT && !tempSortedAssimilate.isEmpty() && (float)CFG.core.getCiv(nCivID).getGold() >= GameValues.gvAssimilate.AI_ASSIMILATE_MIN_GOLD_MODIFIER * (float)GameManager.assimilateCost(((AI_Assimilate_Data)tempSortedAssimilate.get((int)0)).iProvinceID, GameValues.gvAssimilate.ASSIMILATE_NUM_OF_TURNS_MIN) && GameManager.addAssi(nCivID, ((AI_Assimilate_Data)tempSortedAssimilate.get((int)0)).iProvinceID, (int)Math.min(Math.min((100.0f - CFG.core.getProv(((AI_Assimilate_Data)tempSortedAssimilate.get((int)0)).iProvinceID).getProviStability() * 100.0f) / GameValues.gvAiProvince.ASSIMILATE_STABILITY_TO_TURNS_DIVISOR, (float)(CFG.core.getCiv(nCivID).getGold() / (long)GameManager.assimilateCost(((AI_Assimilate_Data)tempSortedAssimilate.get((int)0)).iProvinceID, 1))), (float)GameValues.gvAssimilate.ASSIMILATE_NUM_OF_TURNS_MAX))) {
                    tempSortedAssimilate.remove(0);
                }
                tempSortedAssimilate.clear();
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    public final void hostFestivals(int nCivID, int iLimit) {
        try {
            if (CFG.core.getCiv(nCivID).getMovemPoints() >= GameValues.gvFestival.COST_FESTIVAL_MOVEMENT_POINTS && (float)CFG.core.getCiv(nCivID).getGold() >= GameValues.gvFestival.AI_FESTIVAL_MIN_GOLD_MODIFIER * (float)Festival.festivalCost(CFG.core.getCiv((int)nCivID).provincesWithLowHappiness.get(0))) {
                ArrayList<AI_Assimilate_Data> tempProvincesScore = new ArrayList<AI_Assimilate_Data>();
                int tempCapital = CFG.core.getCiv(nCivID).getCapitalProvID() >= 0 ? CFG.core.getCiv(nCivID).getCapitalProvID() : CFG.core.getCiv(nCivID).getProvID(0);
                for (int i = CFG.core.getCiv((int)nCivID).provincesWithLowHappiness.size() - 1; i >= 0; --i) {
                    tempProvincesScore.add(new AI_Assimilate_Data(CFG.core.getCiv((int)nCivID).provincesWithLowHappiness.get(i), (CFG.core.getCiv((int)nCivID).civGD.civPers.ASSIMILATE_PERC_POPULATION_SCORE * Math.min((float)CFG.core.getProv(CFG.core.getCiv((int)nCivID).provincesWithLowHappiness.get(i)).getPop().getPops() / (float)CFG.core.getGameScenars().getScenario_StartingPopulation(), 1.0f) + CFG.core.getCiv((int)nCivID).civGD.civPers.ASSIMILATE_PERC_DISTANCE_SCORE * Distance.getDistanceFromCapital_PercOfMax(tempCapital, CFG.core.getCiv((int)nCivID).provincesWithLowHappiness.get(i))) * CFG.core.getProv(CFG.core.getCiv((int)nCivID).provincesWithLowHappiness.get(i)).getProviStability()));
                }
                ArrayList<AI_Assimilate_Data> tempSorted = new ArrayList<AI_Assimilate_Data>();
                while (!tempProvincesScore.isEmpty()) {
                    int tBest = 0;
                    for (int i = tBest + 1; i < tempProvincesScore.size(); ++i) {
                        if (!(((AI_Assimilate_Data)tempProvincesScore.get((int)i)).fScore > ((AI_Assimilate_Data)tempProvincesScore.get((int)tBest)).fScore)) continue;
                        tBest = i;
                    }
                    tempSorted.add((AI_Assimilate_Data)tempProvincesScore.get(tBest));
                    tempProvincesScore.remove(tBest);
                }
                while (CFG.core.getCiv(nCivID).getMovemPoints() >= GameValues.gvFestival.COST_FESTIVAL_MOVEMENT_POINTS && !tempSorted.isEmpty() && (float)CFG.core.getCiv(nCivID).getGold() >= GameValues.gvFestival.AI_FESTIVAL_MIN_GOLD_MODIFIER * (float)Festival.festivalCost(((AI_Assimilate_Data)tempSorted.get((int)0)).iProvinceID) && Festival.addFestival(nCivID, ((AI_Assimilate_Data)tempSorted.get((int)0)).iProvinceID)) {
                    tempSorted.remove(0);
                    if (iLimit-- > 0) continue;
                    return;
                }
                tempSorted.clear();
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    public final void changeTypeOfIdeology(int nCivID) {
        if (CFG.core.getCiv((int)nCivID).civGD.changeTypeOfGovernment != null) {
            if (CFG.core.getCiv(nCivID).isAtWarC()) {
                CFG.core.getCiv((int)nCivID).civGD.changeTypeOfGovernment = null;
            } else if (CFG.core.getCiv((int)nCivID).civGD.changeTypeOfGovernment.action(nCivID)) {
                CFG.core.getCiv((int)nCivID).civGD.changeTypeOfGovernment = null;
            }
        }
    }

    public final void relocateLostCapital(int nCivID) {
        try {
            if (!(CFG.core.getCiv(nCivID).getCapitalProvID() == CFG.core.getCiv(nCivID).getCoreCapitalProvID() || CFG.core.getCiv(nCivID).getCoreCapitalProvID() < 0 || CFG.core.getProv(CFG.core.getCiv(nCivID).getCoreCapitalProvID()).getCivId() != nCivID || CFG.core.getProv(CFG.core.getCiv(nCivID).getCoreCapitalProvID()).isOccupied() || CFG.core.getCiv(nCivID).getCapitalProvID() >= 0 && CFG.core.getProv(CFG.core.getCiv(nCivID).getCapitalProvID()).getCivId() != nCivID)) {
                if ((float)CFG.core.getCiv(nCivID).getGold() > (float)CFG.gameAction.moveCapital_Cost(nCivID) * 4.76124f) {
                    CFG.gameAction.moveCapital(nCivID, CFG.core.getCiv(nCivID).getCoreCapitalProvID());
                }
            } else if (CFG.core.getCiv(nCivID).getCapitalProvID() < 0 || CFG.core.getProv(CFG.core.getCiv(nCivID).getCapitalProvID()).getCivId() != nCivID && (!CFG.core.getProv(CFG.core.getCiv(nCivID).getCapitalProvID()).isOccupied() || !CFG.core.getCivsAtWar(nCivID, CFG.core.getProv(CFG.core.getCiv(nCivID).getCapitalProvID()).getCivId()))) {
                int bestProvinceID = CFG.core.getCiv(nCivID).getProvID(0);
                int bestScore = this.relocateLostCapital_ProvinceScore(nCivID, CFG.core.getCiv(nCivID).getProvID(0));
                for (int j = 1; j < CFG.core.getCiv(nCivID).getNumOfProvs(); ++j) {
                    int tempScore = this.relocateLostCapital_ProvinceScore(nCivID, CFG.core.getCiv(nCivID).getProvID(j));
                    if (bestScore >= tempScore) continue;
                    bestScore = tempScore;
                    bestProvinceID = CFG.core.getCiv(nCivID).getProvID(j);
                }
                if (!CFG.core.getProv(bestProvinceID).isOccupied()) {
                    CFG.gameAction.moveCapital(nCivID, bestProvinceID);
                }
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    public final int relocateLostCapital_ProvinceScore(int nCivID, int nProvinceID) {
        return CFG.core.getProv(nProvinceID).isOccupied() ? -1 : (int)((float)CFG.core.getProv(nCivID).getPop().getPopulationOfCivID(nCivID) + (float)CFG.core.getProv(nCivID).getPop().getPops() / GameValues.gvAiProvince.RELOCATE_CAPITAL_TOTAL_POPULATION_DIVISOR + (float)CFG.core.getProv(nCivID).getEco() / GameValues.gvAiProvince.RELOCATE_CAPITAL_ECONOMY_DIVISOR);
    }

    public final void respondToEvents(int nCivID) {
        CFG.core.getCiv(nCivID).runNextEvent2();
    }

    /*
     * Unable to fully structure code
     */
    public final void respondToMessages(int nCivID) {
        try {
            civ = CFG.core.getCiv(nCivID);
            messageBox = civ.getCivDiploGD().messageBox;
            if (messageBox.lMessages.isEmpty()) {
                return;
            }
            i = messageBox.getMessagesSize() - 1;
            block55: for (tLimit = 0; i >= 0 && tLimit < 100; --i, ++tLimit) {
                message = messageBox.getMessage(i);
                switch (1.$SwitchMap$age$of$civilizations2$jakowski$lukasz$Messages$MessageType[message.messageType.ordinal()]) {
                    case 1: {
                        try {
                            if (CFG.ideologiesMgr.getIdeologyID((int)civ.getIdeology()).REVOLUTIONARY) ** GOTO lbl120
                            peaceID = CFG.core.getPeaceTreaty_GameDataID(message.TAG);
                            if (peaceID < 0) ** GOTO lbl118
                            tempData = new PeaceTreaty_Data(CFG.core.lPeaceTreaties.get((int)peaceID).peaceTreaty_GameData);
                            if (tempData.peaceTreatyGD.civsDataDefenders.isEmpty() || tempData.peaceTreatyGD.civsDataAggressors.isEmpty()) ** GOTO lbl113
                            warID = CFG.core.getWarID(tempData.peaceTreatyGD.civsDataDefenders.get((int)0).iCivID, tempData.peaceTreatyGD.civsDataAggressors.get((int)0).iCivID);
                            if (warID < 0) ** GOTO lbl107
                            v0 = canEnd = civ.getNumOfProvs() == 0;
                            if (!canEnd) {
                                try {
                                    canEnd = GameCalendar.TURNID > CFG.core.getWar(warID).getWarTurnID() + GameValues.gvPeaceTreaty.AI_PEACE_TREATY_ACCEPTED_WAR_TURNS;
                                }
                                catch (Exception ex) {
                                    CFG.exceptionStack(ex);
                                }
                            }
                            if (!canEnd) {
                                try {
                                    trueOwnerProvinces = 0;
                                    for (a = 0; a < CFG.core.getProvinSize(); ++a) {
                                        if (CFG.core.getProv(a).getTrueOwnerOfProv() != nCivID) continue;
                                        ++trueOwnerProvinces;
                                    }
                                    if (trueOwnerProvinces > 0 && (float)civ.getNumOfProvs() / (float)trueOwnerProvinces <= 1.0f - GameValues.gvCapitulation.ACCEPT_PEACE_OFFER_IF_WAR_SCORE_OVER) {
                                        canEnd = true;
                                    }
                                }
                                catch (Exception ex) {
                                    CFG.exceptionStack(ex);
                                }
                            }
                            if (!canEnd) {
                                try {
                                    trueOwnerProvinces = 0;
                                    if (CFG.core.getWar(warID).getWarScore() < -25) {
                                        for (a = 0; a < CFG.core.getProvinSize(); ++a) {
                                            if (CFG.core.getProv(a).getTrueOwnerOfProv() != CFG.core.getWar(warID).getDefenderID(0).getCivID()) continue;
                                            ++trueOwnerProvinces;
                                        }
                                        if (trueOwnerProvinces > 0 && (float)CFG.core.getCiv(CFG.core.getWar(warID).getDefenderID(0).getCivID()).getNumOfProvs() / (float)trueOwnerProvinces <= 1.0f - GameValues.gvCapitulation.ACCEPT_PEACE_OFFER_IF_WAR_SCORE_OVER) {
                                            canEnd = true;
                                        }
                                    } else if (CFG.core.getWar(warID).getWarScore() > 25) {
                                        for (a = 0; a < CFG.core.getProvinSize(); ++a) {
                                            if (CFG.core.getProv(a).getTrueOwnerOfProv() != CFG.core.getWar(warID).getAggressorID(0).getCivID()) continue;
                                            ++trueOwnerProvinces;
                                        }
                                        if (trueOwnerProvinces > 0 && (float)CFG.core.getCiv(CFG.core.getWar(warID).getAggressorID(0).getCivID()).getNumOfProvs() / (float)trueOwnerProvinces <= 1.0f - GameValues.gvCapitulation.ACCEPT_PEACE_OFFER_IF_WAR_SCORE_OVER) {
                                            canEnd = true;
                                        }
                                    }
                                }
                                catch (Exception ex) {
                                    CFG.exceptionStack(ex);
                                }
                            }
                            if (!canEnd) {
                                try {
                                    for (a = 0; a < CFG.core.getPlayersSize(); ++a) {
                                        if (civ.getPuppetOfCiv() != CFG.core.getPlayer(a).getCivId()) continue;
                                        canEnd = true;
                                        break;
                                    }
                                }
                                catch (Exception ex) {
                                    CFG.exceptionStack(ex);
                                }
                            }
                            if (canEnd) {
                                GameManager.acceptPeaceTreaty(nCivID, message.TAG);
                            } else if (!CFG.core.lPeaceTreaties.isEmpty()) {
                                powerLeft = 0;
                                powerRight = 0;
                                canEnd_V2 = false;
                                try {
                                    for (o = 0; o < tempData.peaceTreatyGD.civsDataDefenders.size(); ++o) {
                                        if (CFG.core.getCiv(tempData.peaceTreatyGD.civsDataDefenders.get((int)o).iCivID).getNumOfProvs() > 0) {
                                            powerLeft = (int)((long)powerLeft + (Math.max(CFG.core.getCiv(tempData.peaceTreatyGD.civsDataDefenders.get((int)o).iCivID).getGold(), 0L) / (long)GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT + (long)CFG.core.getCiv(tempData.peaceTreatyGD.civsDataDefenders.get((int)o).iCivID).getNumberOfUnits() + (long)CFG.core.getCiv(tempData.peaceTreatyGD.civsDataDefenders.get((int)o).iCivID).getNumOfProvs()));
                                            continue;
                                        }
                                        canEnd_V2 = true;
                                    }
                                    for (o = 0; o < tempData.peaceTreatyGD.civsDataAggressors.size(); ++o) {
                                        if (CFG.core.getCiv(tempData.peaceTreatyGD.civsDataAggressors.get((int)o).iCivID).getNumOfProvs() > 0) {
                                            powerRight = (int)((long)powerRight + (Math.max(CFG.core.getCiv(tempData.peaceTreatyGD.civsDataAggressors.get((int)o).iCivID).getGold(), 0L) / (long)GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT + (long)CFG.core.getCiv(tempData.peaceTreatyGD.civsDataAggressors.get((int)o).iCivID).getNumberOfUnits() + (long)CFG.core.getCiv(tempData.peaceTreatyGD.civsDataAggressors.get((int)o).iCivID).getNumOfProvs()));
                                            continue;
                                        }
                                        canEnd_V2 = true;
                                    }
                                }
                                catch (Exception ex) {
                                    canEnd_V2 = true;
                                }
                                if (canEnd_V2) {
                                    message.onAccept(nCivID);
                                } else if (CFG.core.getWar(warID).getIsDefender(nCivID)) {
                                    if ((float)powerLeft > (float)powerRight * GameValues.gvPeaceTreaty.AI_PEACE_TREATY_POWER_RIGHT_MODIFIER) {
                                        message.onDecline(nCivID);
                                    } else {
                                        message.onAccept(nCivID);
                                    }
                                } else if ((float)powerRight > (float)powerLeft * GameValues.gvPeaceTreaty.AI_PEACE_TREATY_POWER_RIGHT_MODIFIER) {
                                    message.onDecline(nCivID);
                                } else {
                                    message.onAccept(nCivID);
                                }
                            } else {
                                message.onDecline(nCivID);
                            }
                            ** GOTO lbl118
lbl107:
                            // 1 sources

                            try {
                                message.onAccept(nCivID);
                            }
                            catch (Exception ex) {
                                CFG.exceptionStack(ex);
                            }
                            ** GOTO lbl118
lbl113:
                            // 1 sources

                            try {
                                message.onAccept(nCivID);
                            }
                            catch (Exception ex) {
                                CFG.exceptionStack(ex);
                            }
lbl118:
                            // 12 sources

                            messageBox.removeMessage(i);
                            continue block55;
lbl120:
                            // 1 sources

                            messageBox.removeMessage(i);
                        }
                        catch (Exception ex) {
                            GameManager.acceptPeaceTreaty(nCivID, message.TAG);
                            messageBox.removeMessage(i);
                            CFG.exceptionStack(ex);
                        }
                        continue block55;
                    }
                    case 2: 
                    case 3: {
                        nWarID = CFG.core.getWarID(nCivID, message.fromCivID);
                        playerTakesPartInPeaceTreaty = false;
                        playerID_InPeaceTreaty = -1;
                        if (nWarID >= 0) {
                            if (CFG.core.getPeaceTreaty_GameData_AlreadySent(nCivID, message.fromCivID)) {
                                messageBox.removeMessage(i);
                                continue block55;
                            }
                            lDefenders = new ArrayList<Boolean>();
                            lAggressors = new ArrayList<Boolean>();
                            if (CFG.core.getWar(nWarID).getIsAggressor(nCivID)) {
                                for (o = 0; o < CFG.core.getWar(nWarID).getAggressorsSize(); ++o) {
                                    lAggressors.add(true);
                                    if (!CFG.core.getCiv(CFG.core.getWar(nWarID).getAggressorID(o).getCivID()).getIsPlayer()) continue;
                                    playerOccupiedProvincesInThisPeace = false;
                                    block63: for (z = 0; z < CFG.core.getCiv(CFG.core.getWar(nWarID).getAggressorID(o).getCivID()).getNumOfProvs(); ++z) {
                                        if (!CFG.core.getProv(CFG.core.getCiv(CFG.core.getWar(nWarID).getAggressorID(o).getCivID()).getProvID(z)).isOccupied()) continue;
                                        for (p = 0; p < CFG.core.getWar(nWarID).getDefendersSize(); ++p) {
                                            if (CFG.core.getWar(nWarID).getDefenderID(p).getCivID() != CFG.core.getProv(CFG.core.getCiv(CFG.core.getWar(nWarID).getAggressorID(o).getCivID()).getProvID(z)).getTrueOwnerOfProv()) continue;
                                            playerOccupiedProvincesInThisPeace = true;
                                            z = CFG.core.getCiv(CFG.core.getWar(nWarID).getAggressorID(o).getCivID()).getNumOfProvs();
                                            continue block63;
                                        }
                                    }
                                    if (!playerOccupiedProvincesInThisPeace) continue;
                                    playerTakesPartInPeaceTreaty = true;
                                }
                                for (o = 0; o < CFG.core.getWar(nWarID).getDefendersSize(); ++o) {
                                    lDefenders.add(CFG.core.getWar(nWarID).getDefenderID(o).getCivID() == message.fromCivID || CFG.core.getCiv(CFG.core.getWar(nWarID).getDefenderID(o).getCivID()).getNumOfProvs() == 0);
                                }
                            } else {
                                for (o = 0; o < CFG.core.getWar(nWarID).getAggressorsSize(); ++o) {
                                    lAggressors.add(CFG.core.getWar(nWarID).getAggressorID(o).getCivID() == message.fromCivID || CFG.core.getCiv(CFG.core.getWar(nWarID).getAggressorID(o).getCivID()).getNumOfProvs() == 0);
                                }
                                for (o = 0; o < CFG.core.getWar(nWarID).getDefendersSize(); ++o) {
                                    lDefenders.add(true);
                                    if (!CFG.core.getCiv(CFG.core.getWar(nWarID).getDefenderID(o).getCivID()).getIsPlayer()) continue;
                                    playerTakesPartInPeaceTreaty = true;
                                    playerOccupiedProvincesInThisPeace = false;
                                    block68: for (z = 0; z < CFG.core.getCiv(CFG.core.getWar(nWarID).getDefenderID(o).getCivID()).getNumOfProvs(); ++z) {
                                        if (!CFG.core.getProv(CFG.core.getCiv(CFG.core.getWar(nWarID).getDefenderID(o).getCivID()).getProvID(z)).isOccupied()) continue;
                                        for (p = 0; p < CFG.core.getWar(nWarID).getAggressorsSize(); ++p) {
                                            if (CFG.core.getWar(nWarID).getAggressorID(p).getCivID() != CFG.core.getProv(CFG.core.getCiv(CFG.core.getWar(nWarID).getDefenderID(o).getCivID()).getProvID(z)).getTrueOwnerOfProv()) continue;
                                            playerOccupiedProvincesInThisPeace = true;
                                            z = CFG.core.getCiv(CFG.core.getWar(nWarID).getDefenderID(o).getCivID()).getNumOfProvs();
                                            continue block68;
                                        }
                                    }
                                    if (!playerOccupiedProvincesInThisPeace) continue;
                                    playerTakesPartInPeaceTreaty = true;
                                }
                            }
                            if (playerTakesPartInPeaceTreaty) {
                                messageBox.removeMessage(i);
                                continue block55;
                            }
                            if (!CFG.SPECTATOR_MODE && GameValues.gvInGame.ENABLE_PLAYER_AI_PEACE_PROPOSITION_RETRY) {
                                try {
                                    for (o = 0; o < CFG.core.getWar(nWarID).getAggressorsSize(); ++o) {
                                        if (!CFG.core.getCiv(CFG.core.getWar(nWarID).getAggressorID(o).getCivID()).getIsPlayer()) continue;
                                        playerID_InPeaceTreaty = CFG.core.getPlayerIDbyCivID(CFG.core.getWar(nWarID).getAggressorID(o).getCivID());
                                    }
                                    for (o = 0; o < CFG.core.getWar(nWarID).getDefendersSize(); ++o) {
                                        if (!CFG.core.getCiv(CFG.core.getWar(nWarID).getDefenderID(o).getCivID()).getIsPlayer()) continue;
                                        playerID_InPeaceTreaty = CFG.core.getPlayerIDbyCivID(CFG.core.getWar(nWarID).getDefenderID(o).getCivID());
                                    }
                                    if (playerID_InPeaceTreaty >= 0) {
                                        if (GameManager.playerAIPeace_WasSent(nCivID, CFG.core.getPlayer(playerID_InPeaceTreaty).getCivId())) {
                                            messageBox.removeMessage(i);
                                            continue block55;
                                        }
                                        GameManager.playerAIPeace_AddCiv(nCivID, CFG.core.getPlayer(playerID_InPeaceTreaty).getCivId());
                                    }
                                }
                                catch (Exception ex) {
                                    CFG.exceptionStack(ex);
                                }
                            }
                            Menu_PeaceTreaty.WAR_ID = nWarID;
                            CFG.peaceTreatyData = new PeaceTreaty_Data(Menu_PeaceTreaty.WAR_ID, lDefenders, lAggressors, CFG.core.getWar(nWarID).getIsAggressor(nCivID));
                            CFG.peaceTreatyData.AIUseVictoryPoints();
                            GameManager.sendPeaceTreaty(CFG.core.getWar(CFG.peaceTreatyData.peaceTreatyGD.iWarID).getIsAggressor(nCivID), nCivID, CFG.peaceTreatyData.peaceTreatyGD);
                        }
                        messageBox.removeMessage(i);
                        continue block55;
                    }
                    case 4: {
                        if (CFG.core.getCiv(message.fromCivID).getCivDiploGD().getIsEmbassyClosed(nCivID)) {
                            message.onDecline(nCivID);
                        } else if (civ.civGD.civPlans.isPreparingForTheWar(message.fromCivID)) {
                            message.onDecline(nCivID);
                        } else if (GameManager.getAllianceProposal_Positive(message.fromCivID, nCivID) + GameManager.getAllianceProposal_Negative(message.fromCivID, nCivID) > 0 || CFG.core.getCivRelationOfCivB(nCivID, message.fromCivID) > 0.0f && CFG.oR.nextInt(1000) < GameValues.gvAiDiplomacy.ALLIANCE_RANDOM_ACCEPT_CHANCE_PER_1000) {
                            message.onAccept(nCivID);
                        } else {
                            message.onDecline(nCivID);
                        }
                        messageBox.removeMessage(i);
                        continue block55;
                    }
                    case 5: {
                        if (civ.civGD.civPlans.isPreparingForTheWar(message.fromCivID)) {
                            message.onDecline(nCivID);
                        } else if (CFG.core.getCivRelationOfCivB(nCivID, message.fromCivID) > (float)GameValues.gvAiDiplomacy.NON_AGGRESSION_PACT_ACCEPT_MIN_RELATION) {
                            message.onAccept(nCivID);
                        } else {
                            message.onDecline(nCivID);
                        }
                        messageBox.removeMessage(i);
                        continue block55;
                    }
                    case 6: {
                        if (CFG.core.getCiv(message.fromCivID).getCivDiploGD().getIsEmbassyClosed(nCivID)) {
                            message.onDecline(nCivID);
                        } else if (CFG.core.getCivRelationOfCivB(nCivID, message.fromCivID) > (float)GameValues.gvAiDiplomacy.PREPARE_FOR_WAR_ACCEPT_MIN_RELATION) {
                            message.onAccept(nCivID);
                        } else {
                            message.onDecline(nCivID);
                        }
                        messageBox.removeMessage(i);
                        continue block55;
                    }
                    case 7: {
                        if (civ.civGD.civPlans.isPreparingForTheWar(message.fromCivID)) {
                            message.onDecline(nCivID);
                        } else if (CFG.core.getCivRelationOfCivB(nCivID, message.fromCivID) > (float)GameValues.gvAiDiplomacy.DEFENSIVE_PACT_ACCEPT_MIN_RELATION) {
                            sameRivals = false;
                            for (a = 0; a < civ.getHatedCivsSize(); ++a) {
                                for (b = 0; b < CFG.core.getCiv(message.fromCivID).getHatedCivsSize(); ++b) {
                                    if (!CFG.core.getCiv(message.fromCivID).isHatedCiv(civ.getHatedCiv((int)a).iCivID)) continue;
                                    sameRivals = true;
                                    break;
                                }
                                if (sameRivals) break;
                            }
                            if (sameRivals || !GameValues.gvAiDiplomacy.DEFENSIVE_PACT_ACCEPT_SAME_RIVALS_REQUIRED) {
                                message.onAccept(nCivID);
                            } else {
                                message.onDecline(nCivID);
                            }
                        } else {
                            message.onDecline(nCivID);
                        }
                        messageBox.removeMessage(i);
                        continue block55;
                    }
                    case 8: {
                        if (!civ.isAtWarC() && !CFG.core.getCiv(message.fromCivID).isAtWarC() && civ.isFriendlyCiv(message.fromCivID) > 0 && CFG.oR.nextInt(100) < GameValues.gvAiDiplomacy.DEFENSIVE_PACT_RENEW_CHANCE_100) {
                            GameManager.sendDefensivePactProposal(message.fromCivID, nCivID, GameValues.gvDipNonAggression.DIPLOMACY_MAX_NUMBER_OF_TURNS_NON_AGGRESSION_PACT);
                        }
                        messageBox.removeMessage(i);
                        continue block55;
                    }
                    case 9: {
                        if (CFG.core.getCivsAtWar(nCivID, message.fromCivID) || civ.isFriendlyCiv(message.fromCivID) <= 0 || CFG.oR.nextInt(100) >= GameValues.gvAiDiplomacy.NONAGGRESSION_PACT_RENEW_CHANCE_100) continue block55;
                        GameManager.sendNonAggressionProposal(message.fromCivID, nCivID, GameValues.gvDipNonAggression.DIPLOMACY_MAX_NUMBER_OF_TURNS_NON_AGGRESSION_PACT);
                        continue block55;
                    }
                    case 10: {
                        nPercOfBudget = (float)message.iValue / (float)civ.iBudget;
                        if (civ.civGD.civPlans.isPreparingForTheWar(message.fromCivID)) {
                            message.onAccept(nCivID);
                            if (civ.getPuppetOfCiv() == message.fromCivID) {
                                civ.setVassalLibertyDesire(civ.getVassalLibertyDesire() - Math.max(GameValues.gvGift.GIFT_LIBERTY_DESIRE_DECREASE_MIN, Math.min(GameValues.gvGift.GIFT_LIBERTY_DESIRE_DECREASE_MAX, GameValues.gvGift.GIFT_LIBERTY_DESIRE_DECREASE_MAX * ((float)message.iValue / (float)(civ.incomeTaxation + civ.incomeProduction)))));
                            }
                        } else if (civ.isHatedCiv(message.fromCivID) || CFG.core.getCiv(message.fromCivID).isHatedCiv(nCivID)) {
                            if (nPercOfBudget > GameValues.gvAiDiplomacy.GIFT_HATED_CIV_THRESHOLD + (float)CFG.oR.nextInt(GameValues.gvAiDiplomacy.GIFT_HATED_CIV_THRESHOLD_RANDOM_1000) / 1000.0f) {
                                civ.getCivDiploGD().addImproveRelations(nCivID, message.fromCivID, GameValues.gvAiDiplomacy.GIFT_IMPROVE_RELATIONS_HATED_TURNS_MIN + CFG.oR.nextInt(GameValues.gvAiDiplomacy.GIFT_IMPROVE_RELATIONS_HATED_TURNS_RANDOM));
                                message.onAccept(nCivID);
                                if (civ.getPuppetOfCiv() == message.fromCivID) {
                                    civ.setVassalLibertyDesire(civ.getVassalLibertyDesire() - Math.max(GameValues.gvGift.GIFT_LIBERTY_DESIRE_DECREASE_MIN, Math.min(GameValues.gvGift.GIFT_LIBERTY_DESIRE_DECREASE_MAX, GameValues.gvGift.GIFT_LIBERTY_DESIRE_DECREASE_MAX * ((float)message.iValue / (float)(civ.incomeTaxation + civ.incomeProduction)))));
                                }
                            } else if (nPercOfBudget < GameValues.gvAiDiplomacy.GIFT_LOW_BUDGET_DECLINE_THRESHOLD + (float)CFG.oR.nextInt(GameValues.gvAiDiplomacy.GIFT_LOW_BUDGET_DECLINE_THRESHOLD_RANDOM_1000) / 1000.0f) {
                                message.onDecline(nCivID);
                            } else {
                                message.onAccept(nCivID);
                                if (civ.getPuppetOfCiv() == message.fromCivID) {
                                    civ.setVassalLibertyDesire(civ.getVassalLibertyDesire() - Math.max(GameValues.gvGift.GIFT_LIBERTY_DESIRE_DECREASE_MIN, Math.min(GameValues.gvGift.GIFT_LIBERTY_DESIRE_DECREASE_MAX, GameValues.gvGift.GIFT_LIBERTY_DESIRE_DECREASE_MAX * ((float)message.iValue / (float)(civ.incomeTaxation + civ.incomeProduction)))));
                                }
                            }
                        } else {
                            message.onAccept(nCivID);
                            if (civ.getPuppetOfCiv() == message.fromCivID) {
                                civ.setVassalLibertyDesire(civ.getVassalLibertyDesire() - Math.max(GameValues.gvGift.GIFT_LIBERTY_DESIRE_DECREASE_MIN, Math.min(GameValues.gvGift.GIFT_LIBERTY_DESIRE_DECREASE_MAX, GameValues.gvGift.GIFT_LIBERTY_DESIRE_DECREASE_MAX * ((float)message.iValue / (float)(civ.incomeTaxation + civ.incomeProduction)))));
                            }
                            if (nPercOfBudget > GameValues.gvAiDiplomacy.GIFT_RELATION_IMPROVE_THRESHOLD + (float)CFG.oR.nextInt(GameValues.gvAiDiplomacy.GIFT_RELATION_IMPROVE_THRESHOLD_RANDOM_1000) / 1000.0f) {
                                civ.getCivDiploGD().addImproveRelations(nCivID, message.fromCivID, GameValues.gvAiDiplomacy.GIFT_IMPROVE_RELATIONS_TURNS_MIN + CFG.oR.nextInt(GameValues.gvAiDiplomacy.GIFT_IMPROVE_RELATIONS_TURNS_RANDOM));
                            }
                            if ((civ.isFriendlyCiv(message.fromCivID) >= 0 || CFG.core.getCiv(message.fromCivID).isFriendlyCiv(nCivID) >= 0) && civ.getNumOfProvs() > GameValues.gvAiDiplomacy.GIFT_GUARANTEE_MIN_OWN_PROVINCES && civ.getNumOfProvs() > CFG.core.getCiv(message.fromCivID).getNumOfProvs() && CFG.core.getCiv(message.fromCivID).getNumOfProvs() < GameValues.gvAiDiplomacy.GIFT_GUARANTEE_MAX_FROM_CIV_PROVINCES) {
                                alreadyGuaratneed = false;
                                for (z = 1; z < CFG.core.getCivsSize(); ++z) {
                                    if (CFG.core.getGuarantee(z, message.fromCivID) <= 0) continue;
                                    alreadyGuaratneed = true;
                                    break;
                                }
                                if (!alreadyGuaratneed) {
                                    GameManager.sendGuaranteeIndependence_AskProposal(message.fromCivID, nCivID, GameValues.gvDipNonAggression.DIPLOMACY_MAX_NUMBER_OF_TURNS_NON_AGGRESSION_PACT);
                                }
                            }
                        }
                        messageBox.removeMessage(i);
                        continue block55;
                    }
                    case 11: {
                        if (civ.civGD.civPlans.isPreparingForTheWar(message.fromCivID)) {
                            message.onDecline(nCivID);
                        } else if (civ.isHatedCiv(message.fromCivID)) {
                            message.onDecline(nCivID);
                        } else if ((float)civ.iBudget > (float)CFG.core.getCiv((int)message.fromCivID).iBudget * GameValues.gvAiDiplomacy.GUARANTEE_DECLINE_BUDGET_MODIFIER_FROM_CIV) {
                            message.onDecline(nCivID);
                        } else {
                            message.onAccept(nCivID);
                        }
                        messageBox.removeMessage(i);
                        continue block55;
                    }
                    case 12: {
                        if (CFG.core.getCiv(message.fromCivID).getCivDiploGD().getIsEmbassyClosed(nCivID)) {
                            message.onDecline(nCivID);
                        } else {
                            nScore = GameValues.gvAiDiplomacy.MILITARY_ACCESS_BASE_SCORE;
                            if (!civ.civGD.civPlans.isPreparingForTheWar(message.fromCivID)) {
                                minDistance = 1.0f;
                                for (k = 0; k < civ.getNumOfProvs(); ++k) {
                                    for (j = 0; j < CFG.core.getCiv(message.fromCivID).getNumOfProvs(); ++j) {
                                        minDistance = Math.min(minDistance, Distance.getDistanceFromAToB_PercOfMax(civ.getProvID(k), CFG.core.getCiv(message.fromCivID).getProvID(j)));
                                    }
                                }
                                nScore -= civ.RESPONSE_MILITARY_ACCESS_DISTANCE_SCORE * minDistance;
                                nScore += civ.RESPONSE_MILITARY_ACCESS_RELATION_SCORE * CFG.core.getCivRelationOfCivB(nCivID, message.fromCivID) / 100.0f;
                                nScore += civ.RESPONSE_MILITARY_ACCESS_RANK_SCORE * ((float)CFG.core.getCiv(message.fromCivID).getRankScore() / (float)CFG.core.getCivsSize());
                                nScore -= civ.RESPONSE_MILITARY_ACCESS_RANK_OWN_SCORE * ((float)civ.getRankScore() / (float)CFG.core.getCivsSize());
                                if (CFG.core.getMilitaryAccess(nCivID, message.fromCivID) > 0) {
                                    nScore += GameValues.gvAiDiplomacy.MILITARY_ACCESS_SCORE_EXISTING_OUTGOING;
                                }
                                if (CFG.core.getMilitaryAccess(message.fromCivID, nCivID) > 0) {
                                    nScore += GameValues.gvAiDiplomacy.MILITARY_ACCESS_SCORE_EXISTING_INCOMING;
                                }
                                if (CFG.core.getGuarantee(message.fromCivID, nCivID) > 0) {
                                    nScore += GameValues.gvAiDiplomacy.MILITARY_ACCESS_SCORE_GUARANTEE;
                                }
                                if (civ.getIsPartOfHolyRomanEmpire() || CFG.core.getCiv(message.fromCivID).getIsPartOfHolyRomanEmpire()) {
                                    if (civ.getIsPartOfHolyRomanEmpire() && CFG.core.getCiv(message.fromCivID).getIsPartOfHolyRomanEmpire()) {
                                        if (CFG.core.getCivRelationOfCivB(nCivID, message.fromCivID) > GameValues.gvAiDiplomacy.MILITARY_ACCESS_SCORE_HRE_FRIENDLY_MIN_RELATION) {
                                            nScore += GameValues.gvAiDiplomacy.MILITARY_ACCESS_SCORE_HRE_FRIENDLY;
                                            if (CFG.hreMgr.getHRE().getIsEmperor(message.fromCivID)) {
                                                nScore += GameValues.gvAiDiplomacy.MILITARY_ACCESS_SCORE_HRE_EMPEROR;
                                            }
                                        }
                                    } else if (!CFG.core.getCiv(message.fromCivID).getIsPartOfHolyRomanEmpire()) {
                                        nScore += GameValues.gvAiDiplomacy.MILITARY_ACCESS_SCORE_HRE_CIV_FROM_IS_NOT_IN_HRE;
                                    }
                                }
                                if (civ.getAlliance() > 0) {
                                    for (j = 0; j < CFG.core.getAlliance(civ.getAlliance()).getCivilizationsSize(); ++j) {
                                        if (CFG.core.getAlliance(civ.getAlliance()).getCivilization(j) != nCivID && CFG.core.getCivRelationOfCivB(CFG.core.getAlliance(civ.getAlliance()).getCivilization(j), message.fromCivID) < 0.0f) {
                                            nScore -= GameValues.gvAiDiplomacy.MILITARY_ACCESS_ALLIANCE_PENALTY_MULT * CFG.core.getCivRelationOfCivB(CFG.core.getAlliance(civ.getAlliance()).getCivilization(j), message.fromCivID) / 100.0f;
                                        }
                                        if (!CFG.core.getCivsAtWar(CFG.core.getAlliance(civ.getAlliance()).getCivilization(j), message.fromCivID)) continue;
                                        nScore = GameValues.gvAiDiplomacy.MILITARY_ACCESS_SCORE_ALLY_AT_WAR;
                                    }
                                }
                                if (civ.isAtWarC()) {
                                    for (j = 0; j < CFG.core.getWarsSize(); ++j) {
                                        if (CFG.core.getWar(j).getIsDefender(nCivID) && CFG.core.getWar(j).getIsDefender(message.fromCivID)) {
                                            nScore += GameValues.gvAiDiplomacy.MILITARY_ACCESS_SCORE_SHARED_WAR;
                                            break;
                                        }
                                        if (!CFG.core.getWar(j).getIsAggressor(nCivID) || !CFG.core.getWar(j).getIsAggressor(message.fromCivID)) continue;
                                        nScore += GameValues.gvAiDiplomacy.MILITARY_ACCESS_SCORE_SHARED_WAR;
                                        break;
                                    }
                                }
                                try {
                                    if ((float)civ.getDefensivePact8(message.fromCivID) > GameValues.gvAiDiplomacy.MILITARY_ACCESS_ACCEPT_MIN_SCORE) {
                                        nScore += civ.RESPONSE_MILITARY_ACCESS_DEFENSIVE_PACT_SCORE;
                                    }
                                }
                                catch (Exception j) {
                                    // empty catch block
                                }
                                if (nScore > 0.0f) {
                                    message.onAccept(nCivID);
                                } else {
                                    message.onDecline(nCivID);
                                }
                            }
                        }
                        messageBox.removeMessage(i);
                        continue block55;
                    }
                    case 13: {
                        if (CFG.core.getMilitaryAccess(nCivID, message.fromCivID) > 0 || civ.messageWasSent(message.fromCivID, MessageType.TRADE_REQUEST)) continue block55;
                        tradeRequest = new TradeRequest_GameData();
                        tradeRequest.iCivLEFT = nCivID;
                        tradeRequest.iCivRIGHT = message.fromCivID;
                        tradeRequest.listRight.militaryAccess = true;
                        tradeRequest.listLEFT.iGold = (int)(Math.min((float)civ.iBudget, (float)CFG.core.getCiv((int)message.fromCivID).iBudget * GameValues.gvAiDiplomacy.MILITARY_ACCESS_DENIED_GOLD_OFFER_BUDGET_MULT) * (GameValues.gvAiDiplomacy.MILITARY_ACCESS_DENIED_GOLD_OFFER_BASE + (float)CFG.oR.nextInt(GameValues.gvAiDiplomacy.MILITARY_ACCESS_DENIED_GOLD_OFFER_RANDOM_1000) / 1000.0f));
                        GameManager.sendTradeRequest(message.fromCivID, nCivID, tradeRequest);
                        continue block55;
                    }
                    case 14: {
                        message.onAccept(nCivID);
                        messageBox.removeMessage(i);
                        continue block55;
                    }
                    case 15: {
                        if (GameValues.gvAiWar.ALWAYS_JOIN_WAR) {
                            GameManager.DECLINE_CALL_TO_ARMS_REASON = -1;
                            message.onAccept(nCivID);
                            messageBox.removeMessage(i);
                            continue block55;
                        }
                        if (CFG.core.getCiv(nCivID).getGold() < (long)GameValues.gvAiWar.DENY_JOIN_WAR_IF_GOLD_BELOW) {
                            GameManager.DECLINE_CALL_TO_ARMS_REASON = 1;
                            message.onDecline(nCivID);
                            messageBox.removeMessage(i);
                            continue block55;
                        }
                        if (CFG.core.getCiv(nCivID).getCivPlans().isPreparingForTheWar(nCivID)) {
                            GameManager.DECLINE_CALL_TO_ARMS_REASON = 2;
                            message.onDecline(nCivID);
                            messageBox.removeMessage(i);
                            continue block55;
                        }
                        if (GameValues.gvAiWar.DENY_JOIN_WAR_IF_IS_ALREADY_AT_WAR && CFG.core.getCiv(nCivID).isAtWarC()) {
                            GameManager.DECLINE_CALL_TO_ARMS_REASON = 3;
                            message.onDecline(nCivID);
                            messageBox.removeMessage(i);
                            continue block55;
                        }
                        if (CFG.core.getCiv(nCivID).getRelationD(message.fromCivID) < (float)GameValues.gvAiWar.DENY_JOIN_WAR_IF_RELATIONS_BELOW) {
                            GameManager.DECLINE_CALL_TO_ARMS_REASON = 4;
                            message.onDecline(nCivID);
                            messageBox.removeMessage(i);
                            continue block55;
                        }
                        if (GameValues.gvAiWar.DENY_JOIN_WAR_IF_AGAINST_FRIENDLY_CIV && CFG.core.getCiv(nCivID).isFriendlyCiv(message.iValue) >= 0) {
                            GameManager.DECLINE_CALL_TO_ARMS_REASON = 5;
                            message.onDecline(nCivID);
                            messageBox.removeMessage(i);
                            continue block55;
                        }
                        if (CFG.core.getCiv(message.fromCivID).getIsPlayer() && CFG.core.getCiv(message.fromCivID).getPuppetOfCiv() == nCivID && !CFG.SPECTATOR_MODE && !CFG.SANDBOX_MODE && (tPlayerID = CFG.core.getPlayerIDbyCivID(message.fromCivID)) >= 0 && tPlayerID < CFG.core.getPlayersSize()) {
                            ++CFG.core.getPlayer((int)tPlayerID).playerGD.WARS_DECLARED_AS_VASSAL_AND_LORD_JOINED_WAR;
                            if (CFG.core.getPlayer((int)tPlayerID).playerGD.WARS_DECLARED_AS_VASSAL_AND_LORD_JOINED_WAR > GameValues.gvAiWar.AI_LORD_MAX_WARS_JOINED_WHEN_PLAYER_IS_VASSAL) {
                                GameManager.DECLINE_CALL_TO_ARMS_REASON = 6;
                                message.onDecline(nCivID);
                                messageBox.removeMessage(i);
                                continue block55;
                            }
                        }
                        GameManager.DECLINE_CALL_TO_ARMS_REASON = -1;
                        message.onAccept(nCivID);
                        messageBox.removeMessage(i);
                        continue block55;
                    }
                    case 16: {
                        if (CFG.SANDBOX_MODE) {
                            message.onAccept(nCivID);
                        } else if (message.tradeRequest.listRight.iDeclareWarOnCivID > 0) {
                            if (message.tradeRequest.listLEFT.iGold == GameValues.gvTrade.DECLARE_WAR_MAGIC_NUM_ALWAYS_ACCEPT) {
                                message.onAccept(nCivID);
                            } else if (civ.isHatedCiv(message.fromCivID)) {
                                message.onDecline(nCivID);
                            } else if (civ.isFriendlyCiv(message.tradeRequest.listRight.iDeclareWarOnCivID) >= 0) {
                                message.onDecline(nCivID);
                            } else if ((float)civ.countPop() * GameValues.gvTrade.DECLARE_WAR_CIV_POP_MODIFIER < (float)CFG.core.getCiv(message.tradeRequest.listRight.iDeclareWarOnCivID).countPop()) {
                                message.onDecline(nCivID);
                            } else if (message.tradeRequest.listLEFT.iGold == GameValues.gvTrade.DECLARE_WAR_MAGIC_NUM_ALWAYS_ACCEPT) {
                                message.onAccept(nCivID);
                            } else if (message.tradeRequest.listRight.iGold > 0) {
                                message.onDecline(nCivID);
                            } else {
                                civIncome = Math.max(CFG.core.getCiv((int)nCivID).incomeTaxation + CFG.core.getCiv((int)nCivID).incomeProduction, CFG.core.getCiv((int)message.tradeRequest.listRight.iDeclareWarOnCivID).incomeTaxation + CFG.core.getCiv((int)message.tradeRequest.listRight.iDeclareWarOnCivID).incomeProduction);
                                civIncome += (int)Math.max(1.0f, (float)CFG.core.getCiv(message.tradeRequest.listRight.iDeclareWarOnCivID).getNumberOfUnits() * GameValues.gvTrade.DECLARE_WAR_CIV_GOLD_PER_ENEMY_UNIT);
                                if (message.tradeRequest.listLEFT.iGold >= (civIncome = (int)((float)civIncome * GameValues.gvTrade.DECLARE_WAR_CIV_INCOME_MULTIPLIER))) {
                                    message.onAccept(nCivID);
                                } else if (!message.tradeRequest.listLEFT.lProvinces.isEmpty()) {
                                    totalGold = message.tradeRequest.listLEFT.iGold;
                                    for (a = 0; a < message.tradeRequest.listLEFT.lProvinces.size(); ++a) {
                                        totalGold = (long)((float)totalGold + Math.max(GameValues.gvTrade.AI_TRADE_PROVINCE_MIN_COST, CFG.core.getProv((int)message.tradeRequest.listLEFT.lProvinces.get((int)a).intValue()).incomeTaxation * GameValues.gvTrade.AI_TRADE_PROVINCE_INCOME_TAXATION_WEIGHT + CFG.core.getProv((int)message.tradeRequest.listLEFT.lProvinces.get((int)a).intValue()).incomeProduction * GameValues.gvTrade.AI_TRADE_PROVINCE_INCOME_PRODUCTION_WEIGHT));
                                    }
                                    if (totalGold >= (long)civIncome) {
                                        message.onAccept(nCivID);
                                    } else {
                                        message.onDecline(nCivID);
                                    }
                                } else {
                                    message.onDecline(nCivID);
                                }
                            }
                        } else if (message.tradeRequest.listRight.iFormCoalitionAgainst > 0 || message.tradeRequest.listLEFT.iFormCoalitionAgainst > 0) {
                            if (civ.isHatedCiv(message.fromCivID)) {
                                message.onDecline(nCivID);
                            } else if (!message.tradeRequest.listRight.lProvinces.isEmpty()) {
                                message.onDecline(nCivID);
                            } else if (message.tradeRequest.listRight.iGold > 0) {
                                message.onDecline(nCivID);
                            } else if (civ.isAtWarC()) {
                                message.onDecline(nCivID);
                            } else if (civ.isHatedCiv(message.tradeRequest.listLEFT.iFormCoalitionAgainst) || civ.isHatedCiv(message.tradeRequest.listRight.iFormCoalitionAgainst)) {
                                if (civ.isFriendlyCiv(message.fromCivID) >= 0) {
                                    message.onAccept(nCivID);
                                } else {
                                    message.onDecline(nCivID);
                                }
                            } else {
                                message.onDecline(nCivID);
                            }
                        } else if (message.tradeRequest.listRight.lProvinces.size() > 0) {
                            if ((float)message.tradeRequest.listRight.lProvinces.size() / (float)civ.getNumOfProvs() > GameValues.gvTrade.AI_TRADE_MAX_PROVINCE_SHARE_TO_ACCEPT) {
                                message.onDecline(nCivID);
                            } else {
                                haveACore = false;
                                for (z = 0; z < message.tradeRequest.listRight.lProvinces.size(); ++z) {
                                    if (!CFG.core.getProv(message.tradeRequest.listRight.lProvinces.get(z)).getCores().getHaveACore(nCivID)) continue;
                                    haveACore = true;
                                    break;
                                }
                                if (!haveACore) {
                                    totalCost = 0;
                                    for (z = 0; z < message.tradeRequest.listRight.lProvinces.size(); ++z) {
                                        totalCost = (int)((float)totalCost + Math.max(GameValues.gvTrade.AI_TRADE_PROVINCE_MIN_COST, CFG.core.getProv((int)message.tradeRequest.listRight.lProvinces.get((int)z).intValue()).incomeTaxation * GameValues.gvTrade.AI_TRADE_PROVINCE_INCOME_TAXATION_WEIGHT + CFG.core.getProv((int)message.tradeRequest.listRight.lProvinces.get((int)z).intValue()).incomeProduction * GameValues.gvTrade.AI_TRADE_PROVINCE_INCOME_PRODUCTION_WEIGHT));
                                    }
                                    if (message.tradeRequest.listLEFT.iGold > (totalCost = (int)Math.ceil((float)totalCost * GameValues.gvTrade.AI_TRADE_PROVINCE_COST_MULTIPLIER))) {
                                        message.onAccept(nCivID);
                                    } else {
                                        message.onDecline(nCivID);
                                    }
                                } else {
                                    message.onDecline(nCivID);
                                }
                            }
                        } else if (!message.tradeRequest.listLEFT.lProvinces.isEmpty() && message.tradeRequest.listRight.iGold > 0) {
                            maxGold = (int)Math.ceil((float)message.tradeRequest.listLEFT.lProvinces.size() * GameValues.gvTrade.AI_TRADE_ACCEPT_PROVINCES_MAX_GOLD_PER_PROVINCE);
                            if (maxGold >= message.tradeRequest.listRight.iGold) {
                                if ((float)(civ.getGold() - (long)message.tradeRequest.listRight.iGold) > GameValues.gvTrade.AI_TRADE_ACCEPT_PROVINCES_ONLY_IF_TREASURY_AFTER_PAYING_IS_OVER) {
                                    message.onAccept(nCivID);
                                } else {
                                    message.onDecline(nCivID);
                                }
                            } else {
                                message.onDecline(nCivID);
                            }
                        } else if (message.tradeRequest.listRight.militaryAccess && !message.tradeRequest.listRight.proclaimIndependence && !message.tradeRequest.listRight.nonAggressionPact && !message.tradeRequest.listRight.defensivePact && message.tradeRequest.listRight.iGold <= 0 && message.tradeRequest.listRight.lProvinces.isEmpty()) {
                            warAgainstFriendlyCiv = false;
                            for (z = 0; z < CFG.core.getCiv((int)message.fromCivID).isAtWarWithCivs.size(); ++z) {
                                if (civ.isFriendlyCiv(CFG.core.getCiv((int)message.fromCivID).isAtWarWithCivs.get(z)) < 0) continue;
                                warAgainstFriendlyCiv = true;
                                break;
                            }
                            if (warAgainstFriendlyCiv) {
                                if (civ.isHatedCiv(message.fromCivID)) {
                                    if ((float)message.tradeRequest.listLEFT.iGold > (float)civ.iBudget * GameValues.gvTrade.AI_TRADE_MILITARY_ACCESS_BUDGET_MULTIPLIER_AT_WAR_WITH_FRIENDLY) {
                                        message.onAccept(nCivID);
                                    } else {
                                        message.onDecline(nCivID);
                                    }
                                }
                            } else if (civ.isHatedCiv(message.fromCivID)) {
                                if ((float)message.tradeRequest.listLEFT.iGold > (float)civ.iBudget * GameValues.gvTrade.AI_TRADE_MILITARY_ACCESS_BUDGET_MULTIPLIER_FROM_HATED_CIV) {
                                    message.onAccept(nCivID);
                                } else {
                                    message.onDecline(nCivID);
                                }
                            } else if (message.tradeRequest.listLEFT.iGold > 0) {
                                if (civ.iBudget > 0) {
                                    if ((float)message.tradeRequest.listLEFT.iGold > (float)civ.iBudget * GameValues.gvTrade.AI_TRADE_MILITARY_ACCESS_BUDGET_MULTIPLIER) {
                                        message.onAccept(nCivID);
                                    } else {
                                        message.onDecline(nCivID);
                                    }
                                } else {
                                    message.onAccept(nCivID);
                                }
                            }
                        } else if (message.tradeRequest.listRight.defensivePact || message.tradeRequest.listLEFT.defensivePact) {
                            if (civ.isHatedCiv(message.fromCivID)) {
                                message.onDecline(nCivID);
                            } else {
                                civIncome = Math.max(CFG.core.getCiv((int)nCivID).incomeTaxation + CFG.core.getCiv((int)nCivID).incomeProduction, CFG.core.getCiv((int)message.tradeRequest.listRight.iDeclareWarOnCivID).incomeTaxation + CFG.core.getCiv((int)message.tradeRequest.listRight.iDeclareWarOnCivID).incomeProduction);
                                civIncome = (int)((float)Math.max(1, civIncome) * GameValues.gvTrade.AI_TRADE_DEFENSIVE_INCOME_MULTIPLIER);
                                relationModifier = (float)message.tradeRequest.listLEFT.iGold / (float)civIncome;
                                if (message.tradeRequest.listRight.iGold > 0) {
                                    message.onDecline(nCivID);
                                } else if ((float)CFG.oR.nextInt(100) < GameValues.gvTrade.AI_TRADE_DEFENSIVE_RELATION_GOLD_MAX * Math.min(1.0f, relationModifier) + CFG.core.getCivRelationOfCivB(nCivID, message.fromCivID)) {
                                    message.onAccept(nCivID);
                                } else {
                                    message.onDecline(nCivID);
                                }
                            }
                        } else if (message.tradeRequest.listRight.proclaimIndependence || message.tradeRequest.listLEFT.proclaimIndependence) {
                            if (civ.isHatedCiv(message.fromCivID)) {
                                message.onDecline(nCivID);
                            } else if (message.tradeRequest.listRight.proclaimIndependence && CFG.core.getCiv(nCivID).getNumOfProvs() > CFG.core.getCiv(message.fromCivID).getNumOfProvs()) {
                                if (CFG.core.getCiv(message.fromCivID).getPuppetOfCiv() != message.fromCivID) {
                                    message.onDecline(nCivID);
                                } else if (GameManager.getGuaranteeTheirIndependenceSize(message.fromCivID) >= GameValues.gvTrade.PROCLAIM_THEIR_INDEPENDENCE_CIVS_LIMIT) {
                                    message.onDecline(nCivID);
                                } else if (CFG.core.getCiv((int)nCivID).civNeighbors.isNeighbor(message.fromCivID) && CFG.core.getCivRelationOfCivB(nCivID, message.fromCivID) > 0.0f && CFG.core.getCiv(message.fromCivID).getNumOfProvs() < GameValues.gvTrade.PROCLAIM_INDEPENDENCE_MAX_PROVINCES) {
                                    if ((float)CFG.oR.nextInt(100) < CFG.core.getCivRelationOfCivB(nCivID, message.fromCivID) * 2.0f) {
                                        message.onAccept(nCivID);
                                    }
                                } else if ((float)CFG.oR.nextInt(100) < CFG.core.getCivRelationOfCivB(nCivID, message.fromCivID)) {
                                    message.onAccept(nCivID);
                                } else {
                                    message.onDecline(nCivID);
                                }
                            } else if (message.tradeRequest.listLEFT.proclaimIndependence && CFG.core.getCiv(nCivID).getNumOfProvs() < CFG.core.getCiv(message.fromCivID).getNumOfProvs() && CFG.core.getCiv(nCivID).getNumOfProvs() < GameValues.gvTrade.PROCLAIM_INDEPENDENCE_MAX_PROVINCES) {
                                if (CFG.core.getCiv(nCivID).getPuppetOfCiv() != nCivID) {
                                    message.onDecline(nCivID);
                                } else if (CFG.core.getCiv((int)nCivID).civNeighbors.isNeighbor(message.fromCivID) && CFG.core.getCivRelationOfCivB(nCivID, message.fromCivID) > 0.0f) {
                                    if ((float)CFG.oR.nextInt(100) < CFG.core.getCivRelationOfCivB(nCivID, message.fromCivID) * 2.0f) {
                                        message.onAccept(nCivID);
                                    }
                                } else if ((float)CFG.oR.nextInt(100) < CFG.core.getCivRelationOfCivB(nCivID, message.fromCivID)) {
                                    message.onAccept(nCivID);
                                } else {
                                    message.onDecline(nCivID);
                                }
                            } else if (CFG.core.getCiv(nCivID).getPuppetOfCiv() != nCivID || CFG.core.getCiv(message.fromCivID).getPuppetOfCiv() != message.fromCivID) {
                                message.onDecline(nCivID);
                            } else if ((float)CFG.oR.nextInt(100) < CFG.core.getCivRelationOfCivB(nCivID, message.fromCivID)) {
                                if (message.tradeRequest.listRight.proclaimIndependence) {
                                    if (GameManager.getGuaranteeTheirIndependenceSize(message.fromCivID) >= GameValues.gvTrade.PROCLAIM_THEIR_INDEPENDENCE_CIVS_LIMIT) {
                                        message.onAccept(nCivID);
                                    } else {
                                        message.onDecline(nCivID);
                                    }
                                } else {
                                    message.onAccept(nCivID);
                                }
                            } else {
                                message.onDecline(nCivID);
                            }
                        } else if (message.tradeRequest.listRight.nonAggressionPact || message.tradeRequest.listLEFT.nonAggressionPact) {
                            if (civ.isHatedCiv(message.fromCivID)) {
                                message.onDecline(nCivID);
                            } else if (message.tradeRequest.listRight.iGold > 0) {
                                message.onDecline(nCivID);
                            } else if ((float)CFG.oR.nextInt(100) < CFG.core.getCivRelationOfCivB(nCivID, message.fromCivID)) {
                                message.onAccept(nCivID);
                            } else {
                                message.onDecline(nCivID);
                            }
                        } else if (message.tradeRequest.listRight.militaryAccess || message.tradeRequest.listLEFT.militaryAccess) {
                            if (civ.isHatedCiv(message.fromCivID)) {
                                message.onDecline(nCivID);
                            } else if (message.tradeRequest.listRight.iGold > 0) {
                                message.onDecline(nCivID);
                            } else if ((float)CFG.oR.nextInt(100) < CFG.core.getCivRelationOfCivB(nCivID, message.fromCivID)) {
                                message.onAccept(nCivID);
                            } else {
                                message.onDecline(nCivID);
                            }
                        } else if (message.tradeRequest.listRight.iGold > 0 || message.tradeRequest.listLEFT.iGold > 0) {
                            if (message.tradeRequest.listRight.iGold > message.tradeRequest.listLEFT.iGold) {
                                message.onDecline(nCivID);
                            } else {
                                message.onAccept(nCivID);
                            }
                        } else {
                            message.onAccept(nCivID);
                        }
                        messageBox.removeMessage(i);
                        continue block55;
                    }
                    case 17: {
                        if ((float)CFG.core.getCiv(message.fromCivID).getNumberOfUnits() < (float)civ.getNumberOfUnits() * GameValues.gvAiDiplomacy.ULTIMATUM_REFUSED_RESPONSE_ARMY_CIV_MODIFIER) {
                            message.onAccept(nCivID);
                        } else {
                            message.onDecline(nCivID);
                        }
                        messageBox.removeMessage(i);
                        continue block55;
                    }
                    case 18: {
                        if (message.ultimatum.demandAnexation) {
                            try {
                                if (CFG.core.getCiv(message.fromCivID).getIsPlayer()) {
                                    nPlayerID = CFG.core.getPlayerIDbyCivID(message.fromCivID);
                                    if (CFG.core.getPlayer((int)nPlayerID).playerGD.ULTIMATUMS_SENT > GameValues.gvAiDiplomacy.ULTIMATUM_ANNEXATION_MAX_NUM_OF_ULTIMATUMS_SENT_BY_PLAYER) {
                                        message.onDecline(nCivID);
                                        messageBox.removeMessage(i);
                                        continue block55;
                                    }
                                }
                            }
                            catch (Exception exr) {
                                CFG.exceptionStack(exr);
                            }
                            if ((long)message.ultimatum.numOfUntis < (long)civ.getNumberOfUnits() + Math.max(((long)(civ.incomeTaxation + civ.incomeProduction) + Math.max(0L, civ.getGold())) / (long)GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT, 0L)) {
                                message.onDecline(nCivID);
                                if (CFG.oR.nextInt(1000) < GameValues.gvAiDiplomacy.ULTIMATUM_REFUSE_SANCTIONS_CHANCE_1000) {
                                    GameManager.imposeSanctions(nCivID, message.fromCivID, GameValues.gvSanctions.SANCTIONS_MIN_TURNS + CFG.oR.nextInt(Math.max(1, GameValues.gvSanctions.SANCTIONS_MAX_TURNS - GameValues.gvSanctions.SANCTIONS_MIN_TURNS)));
                                }
                                GameManager.declarationOfIndependenceByVassal(civ.getPuppetOfCiv(), nCivID);
                            } else if (civ.getRelationD(message.fromCivID) > (float)GameValues.gvAiDiplomacy.ULTIMATUM_ANNEXATION_MIN_RELATION_TO_ACCEPT && civ.getRankPos() >= GameValues.gvAiDiplomacy.ULTIMATUM_ANNEXATION_MIN_RANK_TO_ACCEPT && (float)message.ultimatum.numOfUntis * GameValues.gvAiDiplomacy.ULTIMATUM_ANNEXATION_UNITS_FROM_MODIFIER > (float)civ.getNumberOfUnits() * GameValues.gvAiDiplomacy.ULTIMATUM_ANNEXATION_UNITS_TO_MODIFIER + Math.max((float)((long)(civ.incomeTaxation + civ.incomeProduction) + Math.max(0L, civ.getGold())) * GameValues.gvAiDiplomacy.ULTIMATUM_ANNEXATION_GOLD_TO_MODIFIER / (float)GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT, 0.0f)) {
                                message.onAccept(nCivID);
                            } else {
                                message.onDecline(nCivID);
                            }
                        } else if (!message.ultimatum.demandProvinces.isEmpty()) {
                            try {
                                if (CFG.core.getCiv(message.fromCivID).getIsPlayer()) {
                                    nPlayerID = CFG.core.getPlayerIDbyCivID(message.fromCivID);
                                    if (CFG.core.getPlayer((int)nPlayerID).playerGD.ULTIMATUMS_SENT > GameValues.gvAiDiplomacy.ULTIMATUM_PROVINCES_MAX_NUM_OF_ULTIMATUMS_SENT_BY_PLAYER) {
                                        message.onDecline(nCivID);
                                        messageBox.removeMessage(i);
                                        continue block55;
                                    }
                                }
                            }
                            catch (Exception exr) {
                                CFG.exceptionStack(exr);
                            }
                            if ((float)message.ultimatum.demandProvinces.size() >= (float)CFG.core.getCiv(nCivID).getNumOfProvs() * GameValues.gvAiDiplomacy.ULTIMATUM_PROVINCES_REFUSE_DEMAND_VS_NUM_OF_PROVINCES_MODIFIER) {
                                GameManager.decreaseRelation(nCivID, message.fromCivID, GameValues.gvRelationDecrease.SUSPEND_DIPLOMATIC_RELATIONS_MAX);
                                message.onDecline(nCivID);
                            } else if (civ.getRelationD(message.fromCivID) > (float)GameValues.gvAiDiplomacy.ULTIMATUM_PROVINCES_MIN_RELATION_TO_ACCEPT && civ.getRankPos() >= GameValues.gvAiDiplomacy.ULTIMATUM_PROVINCES_MIN_RANK_TO_ACCEPT && (float)message.ultimatum.numOfUntis * GameValues.gvAiDiplomacy.ULTIMATUM_PROVINCES_UNITS_FROM_MODIFIER > (float)civ.getNumberOfUnits() * GameValues.gvAiDiplomacy.ULTIMATUM_PROVINCES_UNITS_TO_MODIFIER + Math.max((float)((long)(civ.incomeTaxation + civ.incomeProduction) + Math.max(0L, civ.getGold())) * GameValues.gvAiDiplomacy.ULTIMATUM_PROVINCES_GOLD_TO_MODIFIER / (float)GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT, 0.0f)) {
                                GameManager.decreaseRelation(nCivID, message.fromCivID, GameValues.gvRelationDecrease.SUSPEND_DIPLOMATIC_RELATIONS_MAX);
                                message.onAccept(nCivID);
                            } else {
                                message.onDecline(nCivID);
                                if (CFG.oR.nextInt(1000) < GameValues.gvAiDiplomacy.ULTIMATUM_REFUSE_SANCTIONS_CHANCE_1000) {
                                    GameManager.imposeSanctions(nCivID, message.fromCivID, GameValues.gvSanctions.SANCTIONS_MIN_TURNS + CFG.oR.nextInt(Math.max(1, GameValues.gvSanctions.SANCTIONS_MAX_TURNS - GameValues.gvSanctions.SANCTIONS_MIN_TURNS)));
                                }
                                totalBudget = CFG.core.getCiv((int)message.fromCivID).iBudget;
                                if (CFG.core.getCiv(message.fromCivID).getAlliance() > 0) {
                                    for (a = 0; a < CFG.core.getAlliance(CFG.core.getCiv(message.fromCivID).getAlliance()).getCivilizationsSize(); ++a) {
                                        if (CFG.core.getAlliance(CFG.core.getCiv(message.fromCivID).getAlliance()).getCivilization(a) == message.fromCivID) continue;
                                        totalBudget += CFG.core.getCiv((int)CFG.core.getAlliance((int)CFG.core.getCiv((int)message.fromCivID).getAlliance()).getCivilization((int)a)).iBudget;
                                    }
                                }
                                if (CFG.core.getCiv((int)message.fromCivID).civGD.iVassalsSize > 0) {
                                    for (a = 0; a < CFG.core.getCiv((int)message.fromCivID).civGD.iVassalsSize; ++a) {
                                        totalBudget += CFG.core.getCiv((int)CFG.core.getCiv((int)message.fromCivID).civGD.vassals.get((int)a).iCivID).iBudget;
                                    }
                                }
                                if (CFG.core.getCiv(message.fromCivID).getCivId() != CFG.core.getCiv(message.fromCivID).getPuppetOfCiv()) {
                                    totalBudget += CFG.core.getCiv((int)CFG.core.getCiv((int)message.fromCivID).getPuppetOfCiv()).iBudget;
                                }
                                if ((float)((long)(civ.incomeTaxation + civ.incomeProduction) + Math.max(0L, civ.getGold())) * GameValues.gvAiDiplomacy.ULTIMATUM_PROVINCES_REFUSE_DECLARE_WAR_BUDGET_MODIFIER > (float)totalBudget) {
                                    CFG.core.declareWar(nCivID, message.fromCivID, false);
                                }
                            }
                        } else if (message.ultimatum.demandVasalization) {
                            if (civ.getRelationD(message.fromCivID) > (float)GameValues.gvAiDiplomacy.ULTIMATUM_VASSALIZATION_MIN_RELATION_TO_ACCEPT && civ.getRankPos() >= GameValues.gvAiDiplomacy.ULTIMATUM_VASSALIZATION_MIN_RANK_TO_ACCEPT && (float)message.ultimatum.numOfUntis * GameValues.gvAiDiplomacy.ULTIMATUM_VASSALIZATION_UNITS_FROM_MODIFIER > (float)civ.getNumberOfUnits() * GameValues.gvAiDiplomacy.ULTIMATUM_VASSALIZATION_UNITS_TO_MODIFIER + Math.max((float)((long)(civ.incomeTaxation + civ.incomeProduction) + Math.max(0L, civ.getGold())) * GameValues.gvAiDiplomacy.ULTIMATUM_VASSALIZATION_GOLD_TO_MODIFIER / (float)GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT, 0.0f)) {
                                message.onAccept(nCivID);
                            } else {
                                message.onDecline(nCivID);
                                if (CFG.oR.nextInt(1000) < GameValues.gvAiDiplomacy.ULTIMATUM_REFUSE_SANCTIONS_CHANCE_1000) {
                                    GameManager.imposeSanctions(nCivID, message.fromCivID, GameValues.gvSanctions.SANCTIONS_MIN_TURNS + CFG.oR.nextInt(Math.max(1, GameValues.gvSanctions.SANCTIONS_MAX_TURNS - GameValues.gvSanctions.SANCTIONS_MIN_TURNS)));
                                }
                                totalBudget = CFG.core.getCiv((int)message.fromCivID).iBudget;
                                if (CFG.core.getCiv(message.fromCivID).getAlliance() > 0) {
                                    for (a = 0; a < CFG.core.getAlliance(CFG.core.getCiv(message.fromCivID).getAlliance()).getCivilizationsSize(); ++a) {
                                        if (CFG.core.getAlliance(CFG.core.getCiv(message.fromCivID).getAlliance()).getCivilization(a) == message.fromCivID) continue;
                                        totalBudget += CFG.core.getCiv((int)CFG.core.getAlliance((int)CFG.core.getCiv((int)message.fromCivID).getAlliance()).getCivilization((int)a)).iBudget;
                                    }
                                }
                                if (CFG.core.getCiv((int)message.fromCivID).civGD.iVassalsSize > 0) {
                                    for (a = 0; a < CFG.core.getCiv((int)message.fromCivID).civGD.iVassalsSize; ++a) {
                                        totalBudget += CFG.core.getCiv((int)CFG.core.getCiv((int)message.fromCivID).civGD.vassals.get((int)a).iCivID).iBudget;
                                    }
                                }
                                if (CFG.core.getCiv(message.fromCivID).getCivId() != CFG.core.getCiv(message.fromCivID).getPuppetOfCiv()) {
                                    totalBudget += CFG.core.getCiv((int)CFG.core.getCiv((int)message.fromCivID).getPuppetOfCiv()).iBudget;
                                }
                                if ((float)((long)(civ.incomeTaxation + civ.incomeProduction) + Math.max(0L, civ.getGold())) * GameValues.gvAiDiplomacy.ULTIMATUM_VASSALIZATION_REFUSE_DECLARE_WAR_BUDGET_MODIFIER > (float)totalBudget) {
                                    CFG.core.declareWar(nCivID, message.fromCivID, false);
                                }
                            }
                        } else if (message.ultimatum.demandChangeOfGovernment) {
                            if ((float)message.ultimatum.numOfUntis * GameValues.gvAiDiplomacy.ULTIMATUM_CHANGE_GOV_UNITS_FROM_MODIFIER > (float)civ.getNumberOfUnits() * GameValues.gvAiDiplomacy.ULTIMATUM_CHANGE_GOV_UNITS_TO_MODIFIER + Math.max((float)((long)(civ.incomeTaxation + civ.incomeProduction) + Math.max(0L, civ.getGold())) * GameValues.gvAiDiplomacy.ULTIMATUM_CHANGE_GOV_GOLD_TO_MODIFIER / (float)GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT, 0.0f)) {
                                GameManager.decreaseRelation(nCivID, message.fromCivID, GameValues.gvRelationDecrease.SUSPEND_DIPLOMATIC_RELATIONS_MIN);
                                message.onAccept(nCivID);
                            } else {
                                message.onDecline(nCivID);
                                if (CFG.oR.nextInt(1000) < GameValues.gvAiDiplomacy.ULTIMATUM_REFUSE_SANCTIONS_CHANCE_1000) {
                                    GameManager.imposeSanctions(nCivID, message.fromCivID, GameValues.gvSanctions.SANCTIONS_MIN_TURNS + CFG.oR.nextInt(Math.max(1, GameValues.gvSanctions.SANCTIONS_MAX_TURNS - GameValues.gvSanctions.SANCTIONS_MIN_TURNS)));
                                }
                            }
                        } else if (message.ultimatum.demandMilitaryAccess) {
                            if ((float)message.ultimatum.numOfUntis * GameValues.gvAiDiplomacy.ULTIMATUM_MILITARY_ACCESS_UNITS_FROM_MODIFIER > (float)civ.getNumberOfUnits() * GameValues.gvAiDiplomacy.ULTIMATUM_MILITARY_ACCESS_UNITS_TO_MODIFIER + Math.max((float)((long)(civ.incomeTaxation + civ.incomeProduction) + Math.max(0L, civ.getGold())) * GameValues.gvAiDiplomacy.ULTIMATUM_MILITARY_ACCESS_GOLD_TO_MODIFIER / (float)GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT, 0.0f)) {
                                GameManager.decreaseRelation(nCivID, message.fromCivID, GameValues.gvRelationDecrease.SUSPEND_DIPLOMATIC_RELATIONS_MIN);
                                message.onAccept(nCivID);
                            } else {
                                message.onDecline(nCivID);
                            }
                        } else if (message.ultimatum.demandLiberation.size() > 0) {
                            if ((float)message.ultimatum.numOfUntis * GameValues.gvAiDiplomacy.ULTIMATUM_LIBERATION_UNITS_FROM_MODIFIER / (float)message.ultimatum.demandLiberation.size() > (float)civ.getNumberOfUnits() * GameValues.gvAiDiplomacy.ULTIMATUM_LIBERATION_UNITS_TO_MODIFIER + Math.max((float)((long)(civ.incomeTaxation + civ.incomeProduction) + Math.max(0L, civ.getGold())) * GameValues.gvAiDiplomacy.ULTIMATUM_LIBERATION_GOLD_TO_MODIFIER / (float)GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT, 0.0f)) {
                                message.onAccept(nCivID);
                            } else {
                                message.onDecline(nCivID);
                            }
                        } else {
                            message.onAccept(nCivID);
                        }
                        messageBox.removeMessage(i);
                        continue block55;
                    }
                    case 19: {
                        if (CFG.core.getCiv(message.fromCivID).getCivDiploGD().getIsEmbassyClosed(nCivID)) {
                            message.onDecline(nCivID);
                        } else if (civ.civGD.civPlans.isPreparingForTheWar(message.fromCivID)) {
                            message.onDecline(nCivID);
                        } else if (civ.iLeague < GameValues.gvAiDiplomacy.UNION_MIN_LEAGUE && CFG.core.getCiv((int)message.fromCivID).iLeague < GameValues.gvAiDiplomacy.UNION_MIN_LEAGUE) {
                            message.onDecline(nCivID);
                        } else if (CFG.core.isAlly(message.fromCivID, nCivID)) {
                            if (CFG.core.getCiv((int)message.fromCivID).civGD.numOfUnions == 0 && civ.civGD.numOfUnions == 0) {
                                sameRivals = false;
                                for (a = 0; a < civ.getHatedCivsSize(); ++a) {
                                    for (b = 0; b < CFG.core.getCiv(message.fromCivID).getHatedCivsSize(); ++b) {
                                        if (!CFG.core.getCiv(message.fromCivID).isHatedCiv(civ.getHatedCiv((int)a).iCivID)) continue;
                                        sameRivals = true;
                                        break;
                                    }
                                    if (sameRivals) break;
                                }
                                if (sameRivals) {
                                    message.onAccept(nCivID);
                                } else if (CFG.core.getCiv(nCivID).getRelationD(message.fromCivID) > (float)GameValues.gvAiDiplomacy.UNION_ALLY_MIN_RELATION && (float)CFG.core.getCiv(message.fromCivID).getNumOfProvs() >= (float)CFG.core.getCiv(nCivID).getNumOfProvs() * GameValues.gvAiDiplomacy.UNION_ALLY_NUM_OF_PROVINCES_MODIFIER) {
                                    message.onAccept(nCivID);
                                } else {
                                    message.onDecline(nCivID);
                                }
                            } else if (civ.getNumOfProvs() <= GameValues.gvAiDiplomacy.UNION_SECOND_UNION_MAX_PROVINCES) {
                                if (CFG.core.getCiv((int)message.fromCivID).civGD.numOfUnions < GameValues.gvAiDiplomacy.UNION_MAX_NUM_OF_UNIONS && civ.civGD.numOfUnions < GameValues.gvAiDiplomacy.UNION_MAX_NUM_OF_UNIONS) {
                                    if (CFG.core.getCiv(nCivID).getRelationD(message.fromCivID) > (float)GameValues.gvAiDiplomacy.UNION_ALLY_MIN_RELATION && (float)CFG.core.getCiv(message.fromCivID).getNumOfProvs() >= (float)CFG.core.getCiv(nCivID).getNumOfProvs() * GameValues.gvAiDiplomacy.UNION_ALLY_NUM_OF_PROVINCES_MODIFIER) {
                                        message.onAccept(nCivID);
                                    } else {
                                        message.onDecline(nCivID);
                                    }
                                } else {
                                    message.onDecline(nCivID);
                                }
                            } else {
                                message.onDecline(nCivID);
                            }
                        } else if (CFG.core.getCiv(nCivID).getRelationD(message.fromCivID) > (float)GameValues.gvAiDiplomacy.UNION_MIN_RELATION && (float)CFG.core.getCiv(message.fromCivID).getNumOfProvs() >= (float)CFG.core.getCiv(nCivID).getNumOfProvs() * GameValues.gvAiDiplomacy.UNION_NUM_OF_PROVINCES_MODIFIER) {
                            message.onAccept(nCivID);
                        } else {
                            message.onDecline(nCivID);
                        }
                        messageBox.removeMessage(i);
                        continue block55;
                    }
                    case 20: {
                        if (CFG.core.getCiv(message.fromCivID).getCivDiploGD().getIsEmbassyClosed(nCivID)) {
                            message.onDecline(nCivID);
                        } else if (CFG.core.getCiv(message.fromCivID).getPuppetOfCiv() == nCivID) {
                            message.onDecline(nCivID);
                        } else if ((float)civ.iBudget * (GameValues.gvAiDiplomacy.OFFER_VASSALIZATION_BUDGET_RATIO_TO_ACCEPT + (float)CFG.core.getCiv((int)message.fromCivID).civGD.iVassalsSize * GameValues.gvAiDiplomacy.OFFER_VASSALIZATION_BUDGET_RATIO_TO_ACCEPT_EXTRA_PER_VASSAL) < (float)CFG.core.getCiv((int)message.fromCivID).iBudget && !civ.isHatedCiv(message.fromCivID) && civ.getRelationD(message.fromCivID) >= GameValues.gvAiDiplomacy.OFFER_VASSALIZATION_MIN_RELATION) {
                            message.onAccept(nCivID);
                        } else {
                            CFG.core.getCiv(message.fromCivID).setRelationD(nCivID, CFG.core.getCiv(message.fromCivID).getRelationD(nCivID) + (float)GameValues.gvDiplomacy.OFFER_VASSALIZATION_REJECT_RELATION_CHANGE);
                            CFG.core.getCiv(nCivID).setRelationD(message.fromCivID, CFG.core.getCiv(nCivID).getRelationD(message.fromCivID) + (float)GameValues.gvDiplomacy.OFFER_VASSALIZATION_REJECT_RELATION_CHANGE);
                            message.onDecline(nCivID);
                        }
                        messageBox.removeMessage(i);
                        continue block55;
                    }
                    case 21: {
                        message.onAccept(nCivID);
                        messageBox.removeMessage(i);
                        continue block55;
                    }
                    case 22: {
                        if (CFG.core.getCivsAtWar(nCivID, message.fromCivID)) {
                            message.onDecline(nCivID);
                        } else if (CFG.core.getCiv(message.fromCivID).getLoansFromCivSize() >= GameValues.gvLoan.REQUEST_LOAN_MAX_NUM_OF_LOANS) {
                            message.onDecline(nCivID);
                        } else if (civ.getCivPlans().isPreparingForTheWar(message.fromCivID)) {
                            message.onDecline(nCivID);
                        } else if (civ.isRival(message.fromCivID)) {
                            message.onDecline(nCivID);
                        } else if ((float)civ.getGold() >= (float)message.iValue * GameValues.gvLoan.AI_RESPONSE_REQUEST_LOAN_ACCEPT_MIN_TREASURY_RATIO) {
                            message.onAccept(nCivID);
                        } else if (civ.getGold() < 0L) {
                            message.onDecline(nCivID);
                        } else {
                            message.iValue = (int)((float)message.iValue * (GameValues.gvLoan.AI_RESPONSE_REQUEST_LOAN_GOLD_MODIFIER_BASE + (float)CFG.oR.nextInt(GameValues.gvLoan.AI_RESPONSE_REQUEST_LOAN_GOLD_MODIFIER_RANDOM_100) / 100.0f));
                            message.onAccept(nCivID);
                        }
                        messageBox.removeMessage(i);
                        continue block55;
                    }
                    case 23: {
                        CFG.core.getCiv(nCivID).setSpendingInvestmentsB(Math.max(2.0f, CFG.core.getCiv(nCivID).getSpendingInvestmentsB()));
                        messageBox.removeMessage(i);
                        continue block55;
                    }
                    case 24: {
                        if ((float)civ.iBudget > (float)CFG.core.getCiv((int)message.fromCivID).iBudget * GameValues.gvAiDiplomacy.DECLARATION_OF_INDEPENDENCE_BUDGET_MODIFIER) {
                            message.onAccept(nCivID);
                        } else {
                            message.onDecline(nCivID);
                        }
                        messageBox.removeMessage(i);
                        continue block55;
                    }
                    case 25: {
                        if (CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)message.fromCivID).getIdeology()).REVOLUTIONARY) {
                            messageBox.removeMessage(i);
                            continue block55;
                        }
                        callToArms = GameManager.callToArmsListOfCivs(nCivID, message.fromCivID);
                        for (j = 0; j < callToArms.size(); ++j) {
                            GameManager.sendCallToArms(callToArms.get(j), nCivID, message.fromCivID);
                        }
                        messageBox.removeMessage(i);
                        continue block55;
                    }
                    case 26: {
                        if ((float)civ.iBudget > (float)CFG.core.getCiv((int)message.fromCivID).iBudget * GameValues.gvAiDiplomacy.DECLARATION_OF_INDEPENDENCE_BY_VASSAL_BUDGET_MODIFIER) {
                            message.onAccept(nCivID);
                        } else {
                            message.onDecline(nCivID);
                        }
                        messageBox.removeMessage(i);
                        continue block55;
                    }
                    case 27: {
                        this.useTechnologyPoints(nCivID);
                        messageBox.removeMessage(i);
                        continue block55;
                    }
                    case 28: {
                        messageBox.removeMessage(i);
                    }
                }
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    public final void updateSentMessages(int nCivID) {
        try {
            block5: for (int i = CFG.core.getCiv(nCivID).getSentMessagesSize() - 1; i >= 0; --i) {
                switch (CFG.core.getCiv((int)nCivID).getSentMessage((int)i).messageType) {
                    case GIFT: {
                        continue block5;
                    }
                    default: {
                        if (GameCalendar.TURNID - CFG.core.getCiv((int)nCivID).getSentMessage((int)i).iSentInTurnID <= GameValues.gvAiDiplomacy.REMOVE_MESSAGES_TURNS) continue block5;
                        CFG.core.getCiv(nCivID).removeSentMessage(i);
                    }
                }
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    public final int getPrepareForWar_TurnsLeft(int nCivID, int onCivID) {
        for (int j = 0; j < CFG.core.getCiv((int)nCivID).civGD.civPlans.iWarPrepsSize; ++j) {
            if (CFG.core.getCiv((int)nCivID).civGD.civPlans.warPreps.get((int)j).onCivID != onCivID) continue;
            return CFG.core.getCiv((int)nCivID).civGD.civPlans.warPreps.get((int)j).iNumOfTurnsLeft;
        }
        return -1;
    }

    public final int getPrepareForWar_TurnsLeft_BasedOnNeighboors(int nCivID, int nProvinceID) {
        int out = 8;
        for (int i = 0; i < CFG.core.getProv(nProvinceID).getNeighProvincesSize(); ++i) {
            if (CFG.core.getProv(CFG.core.getProv(nProvinceID).getNeighProvinces(i)).getCivId() <= 0 || CFG.core.getProv(CFG.core.getProv(nProvinceID).getNeighProvinces(i)).getCivId() == nCivID) continue;
            for (int j = 0; j < CFG.core.getCiv((int)nCivID).civGD.civPlans.iWarPrepsSize; ++j) {
                if (CFG.core.getCiv((int)nCivID).civGD.civPlans.warPreps.get((int)j).onCivID != CFG.core.getProv(CFG.core.getProv(nProvinceID).getNeighProvinces(i)).getCivId()) continue;
                out = Math.max(CFG.core.getCiv((int)nCivID).civGD.civPlans.warPreps.get((int)j).iNumOfTurnsLeft, out);
            }
        }
        return out;
    }

    public final void prepareForWar_MoveReadyArmies(int nCivID) {
        for (int i = CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.size() - 1; i >= 0; --i) {
            if (CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.get((int)i).MISSION_TYPE != CivArmyMission_Type.PREAPARE_FOR_WAR) continue;
            int tempTurnsLeft = CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.get((int)i).MISSION_ID < 0 ? 0 : this.getPrepareForWar_TurnsLeft(nCivID, CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.get((int)i).MISSION_ID);
            if (tempTurnsLeft < 0) {
                CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.remove(i);
                continue;
            }
            if (!CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.get(i).canMakeAction(nCivID, tempTurnsLeft) || !CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.get(i).action(nCivID)) continue;
            CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.remove(i);
        }
    }

    public final void prepareForWar2(int nCivID) {
        try {
            block38: {
                block40: {
                    ArrayList<AI_ProvinceInfo_War> sortedFrontProvinces;
                    ArrayList<Integer> lFrontIDsWithArmies;
                    block39: {
                        boolean canRecruitAndMove;
                        int i;
                        int z;
                        boolean add;
                        int k;
                        int u;
                        int i2;
                        int o;
                        int o2;
                        boolean add2;
                        int k2;
                        int u2;
                        int i3;
                        if (CFG.core.getCiv((int)nCivID).civGD.civPlans.iWarPrepsSize == 0) {
                            return;
                        }
                        boolean haveOwnFront = false;
                        ArrayList<AI_ProvinceInfo_War> tempFrontProvinces = new ArrayList<AI_ProvinceInfo_War>();
                        ArrayList tempFrontlinesIDs = new ArrayList();
                        for (i3 = CFG.core.getCiv((int)nCivID).lFrontLines.size() - 1; i3 >= 0; --i3) {
                            for (u2 = 0; u2 < CFG.core.getCiv((int)nCivID).civGD.civPlans.iWarPrepsSize; ++u2) {
                                if (CFG.core.getCiv((int)nCivID).lFrontLines.get((int)i3).iWithCivID != CFG.core.getCiv((int)nCivID).civGD.civPlans.warPreps.get((int)u2).onCivID) continue;
                                haveOwnFront = true;
                                for (k2 = CFG.core.getCiv((int)nCivID).lFrontLines.get((int)i3).lProvinces.size() - 1; k2 >= 0; --k2) {
                                    add2 = true;
                                    for (o2 = tempFrontProvinces.size() - 1; o2 >= 0; --o2) {
                                        if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)o2)).iProvinceID != CFG.core.getCiv((int)nCivID).lFrontLines.get((int)i3).lProvinces.get(k2)) continue;
                                        add2 = false;
                                        break;
                                    }
                                    if (!add2) continue;
                                    tempFrontProvinces.add(new AI_ProvinceInfo_War(CFG.core.getCiv((int)nCivID).lFrontLines.get((int)i3).lProvinces.get(k2), this.getPotential_BasedOnNeighboringProvs(CFG.core.getCiv((int)nCivID).lFrontLines.get((int)i3).lProvinces.get(k2), nCivID), true));
                                }
                            }
                        }
                        for (o = 0; o < CFG.core.getCiv((int)nCivID).civGD.iVassalsSize; ++o) {
                            for (i2 = CFG.core.getCiv((int)CFG.core.getCiv((int)nCivID).civGD.vassals.get((int)o).iCivID).lFrontLines.size() - 1; i2 >= 0; --i2) {
                                for (u = 0; u < CFG.core.getCiv((int)nCivID).civGD.civPlans.iWarPrepsSize; ++u) {
                                    if (CFG.core.getCiv((int)CFG.core.getCiv((int)nCivID).civGD.vassals.get((int)o).iCivID).lFrontLines.get((int)i2).iWithCivID != CFG.core.getCiv((int)nCivID).civGD.civPlans.warPreps.get((int)u).onCivID) continue;
                                    for (k = CFG.core.getCiv((int)CFG.core.getCiv((int)nCivID).civGD.vassals.get((int)o).iCivID).lFrontLines.get((int)i2).lProvinces.size() - 1; k >= 0; --k) {
                                        add = true;
                                        for (z = tempFrontProvinces.size() - 1; z >= 0; --z) {
                                            if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)z)).iProvinceID != CFG.core.getCiv((int)CFG.core.getCiv((int)nCivID).civGD.vassals.get((int)o).iCivID).lFrontLines.get((int)i2).lProvinces.get(k)) continue;
                                            add = false;
                                            break;
                                        }
                                        if (!add) continue;
                                        tempFrontProvinces.add(new AI_ProvinceInfo_War(CFG.core.getCiv((int)CFG.core.getCiv((int)nCivID).civGD.vassals.get((int)o).iCivID).lFrontLines.get((int)i2).lProvinces.get(k), this.getPotential_BasedOnNeighboringProvs(CFG.core.getCiv((int)CFG.core.getCiv((int)nCivID).civGD.vassals.get((int)o).iCivID).lFrontLines.get((int)i2).lProvinces.get(k), CFG.core.getCiv((int)nCivID).civGD.vassals.get((int)o).iCivID), false));
                                    }
                                }
                            }
                        }
                        if (CFG.core.getCiv(nCivID).getPuppetOfCiv() != nCivID) {
                            for (i3 = CFG.core.getCiv((int)CFG.core.getCiv((int)nCivID).getPuppetOfCiv()).lFrontLines.size() - 1; i3 >= 0; --i3) {
                                for (u2 = 0; u2 < CFG.core.getCiv((int)nCivID).civGD.civPlans.iWarPrepsSize; ++u2) {
                                    if (CFG.core.getCiv((int)CFG.core.getCiv((int)nCivID).getPuppetOfCiv()).lFrontLines.get((int)i3).iWithCivID != CFG.core.getCiv((int)nCivID).civGD.civPlans.warPreps.get((int)u2).onCivID) continue;
                                    for (k2 = CFG.core.getCiv((int)CFG.core.getCiv((int)nCivID).getPuppetOfCiv()).lFrontLines.get((int)i3).lProvinces.size() - 1; k2 >= 0; --k2) {
                                        add2 = true;
                                        for (o2 = tempFrontProvinces.size() - 1; o2 >= 0; --o2) {
                                            if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)o2)).iProvinceID != CFG.core.getCiv((int)CFG.core.getCiv((int)nCivID).getPuppetOfCiv()).lFrontLines.get((int)i3).lProvinces.get(k2)) continue;
                                            add2 = false;
                                            break;
                                        }
                                        if (!add2) continue;
                                        tempFrontProvinces.add(new AI_ProvinceInfo_War(CFG.core.getCiv((int)CFG.core.getCiv((int)nCivID).getPuppetOfCiv()).lFrontLines.get((int)i3).lProvinces.get(k2), this.getPotential_BasedOnNeighboringProvs(CFG.core.getCiv((int)CFG.core.getCiv((int)nCivID).getPuppetOfCiv()).lFrontLines.get((int)i3).lProvinces.get(k2), CFG.core.getCiv(nCivID).getPuppetOfCiv()), false));
                                    }
                                }
                            }
                        }
                        if (CFG.core.getCiv(nCivID).getAlliance() > 0) {
                            for (o = 0; o < CFG.core.getAlliance(CFG.core.getCiv(nCivID).getAlliance()).getCivilizationsSize(); ++o) {
                                if (CFG.core.getAlliance(CFG.core.getCiv(nCivID).getAlliance()).getCivilization(o) == nCivID) continue;
                                for (i2 = CFG.core.getCiv((int)CFG.core.getAlliance((int)CFG.core.getCiv((int)nCivID).getAlliance()).getCivilization((int)o)).lFrontLines.size() - 1; i2 >= 0; --i2) {
                                    for (u = 0; u < CFG.core.getCiv((int)nCivID).civGD.civPlans.iWarPrepsSize; ++u) {
                                        if (CFG.core.getCiv((int)CFG.core.getAlliance((int)CFG.core.getCiv((int)nCivID).getAlliance()).getCivilization((int)o)).lFrontLines.get((int)i2).iWithCivID != CFG.core.getCiv((int)nCivID).civGD.civPlans.warPreps.get((int)u).onCivID) continue;
                                        for (k = CFG.core.getCiv((int)CFG.core.getAlliance((int)CFG.core.getCiv((int)nCivID).getAlliance()).getCivilization((int)o)).lFrontLines.get((int)i2).lProvinces.size() - 1; k >= 0; --k) {
                                            add = true;
                                            for (z = tempFrontProvinces.size() - 1; z >= 0; --z) {
                                                if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)z)).iProvinceID != CFG.core.getCiv((int)CFG.core.getAlliance((int)CFG.core.getCiv((int)nCivID).getAlliance()).getCivilization((int)o)).lFrontLines.get((int)i2).lProvinces.get(k)) continue;
                                                add = false;
                                                break;
                                            }
                                            if (!add) continue;
                                            tempFrontProvinces.add(new AI_ProvinceInfo_War(CFG.core.getCiv((int)CFG.core.getAlliance((int)CFG.core.getCiv((int)nCivID).getAlliance()).getCivilization((int)o)).lFrontLines.get((int)i2).lProvinces.get(k), this.getPotential_BasedOnNeighboringProvs(CFG.core.getCiv((int)CFG.core.getAlliance((int)CFG.core.getCiv((int)nCivID).getAlliance()).getCivilization((int)o)).lFrontLines.get((int)i2).lProvinces.get(k), CFG.core.getAlliance(CFG.core.getCiv(nCivID).getAlliance()).getCivilization(o)), false));
                                        }
                                    }
                                }
                            }
                        }
                        if (tempFrontProvinces.isEmpty()) break block38;
                        int tMaxDL = 1;
                        float tMaxPotential = 1.0f;
                        ArrayList<Integer> tMovingArmy_toFrontProvince = new ArrayList<Integer>();
                        int tMaxArmy = 1;
                        float tMaxRegion_NumOfProvinces = 1.0f;
                        float tMaxRegion_Potential = 1.0f;
                        lFrontIDsWithArmies = new ArrayList<Integer>();
                        int tempMovingArmy = 0;
                        for (i = tempFrontProvinces.size() - 1; i >= 0; --i) {
                            if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i)).iValue > tMaxPotential) {
                                tMaxPotential = ((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i)).iValue;
                            }
                            if (CFG.core.getProv(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i)).iProvinceID).getDangerLevel_WithArmy() > tMaxDL) {
                                tMaxDL = CFG.core.getProv(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i)).iProvinceID).getDangerLevel_WithArmy();
                            }
                            if ((float)CFG.core.getProv(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i)).iProvinceID).getRegion_NumOfProvinces() > tMaxRegion_NumOfProvinces) {
                                tMaxRegion_NumOfProvinces = CFG.core.getProv(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i)).iProvinceID).getRegion_NumOfProvinces();
                            }
                            if ((float)CFG.core.getProv(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i)).iProvinceID).getPotentialRegion() > tMaxRegion_Potential) {
                                tMaxRegion_Potential = CFG.core.getProv(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i)).iProvinceID).getPotentialRegion();
                            }
                            tMovingArmy_toFrontProvince.add(tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, ((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i)).iProvinceID));
                            if (CFG.core.getProvinceArmy(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i)).iProvinceID) + tempMovingArmy <= tMaxArmy) continue;
                            tMaxArmy = CFG.core.getProvinceArmy(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i)).iProvinceID) + tempMovingArmy;
                        }
                        for (i = tempFrontProvinces.size() - 1; i >= 0; --i) {
                            ((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i)).iValue = CFG.core.getCiv((int)nCivID).civGD.civPers.WAR_POTENTIAL * (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i)).iValue / tMaxPotential) + CFG.core.getCiv((int)nCivID).civGD.civPers.WAR_DANGER * ((float)CFG.core.getProv(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i)).iProvinceID).getDangerLevel_WithArmy() / (float)tMaxDL) + (1.0f - CFG.core.getCiv((int)nCivID).civGD.civPers.WAR_NUM_OF_UNITS + CFG.core.getCiv((int)nCivID).civGD.civPers.WAR_NUM_OF_UNITS * (1.0f - (float)(CFG.core.getProvinceArmy(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i)).iProvinceID) + (Integer)tMovingArmy_toFrontProvince.get(i)) / (float)tMaxArmy)) + (1.0f - CFG.core.getCiv((int)nCivID).civGD.civPers.WAR_REGION_NUM_OF_PROVINCES + CFG.core.getCiv((int)nCivID).civGD.civPers.WAR_REGION_NUM_OF_PROVINCES * (float)CFG.core.getProv(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i)).iProvinceID).getRegion_NumOfProvinces() / tMaxRegion_NumOfProvinces - CFG.core.getCiv((int)nCivID).civGD.civPers.WAR_REGION_POTENTIAL + CFG.core.getCiv((int)nCivID).civGD.civPers.WAR_REGION_POTENTIAL * (float)CFG.core.getProv(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i)).iProvinceID).getPotentialRegion() / tMaxRegion_Potential);
                        }
                        sortedFrontProvinces = new ArrayList<AI_ProvinceInfo_War>();
                        int tID = 0;
                        while (!tempFrontProvinces.isEmpty()) {
                            int tBest = 0;
                            int iSize = tempFrontProvinces.size();
                            for (int i4 = 1; i4 < iSize; ++i4) {
                                if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)tBest)).iValue < ((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i4)).iValue) {
                                    tBest = i4;
                                    continue;
                                }
                                if (((AI_ProvinceInfo_War)tempFrontProvinces.get((int)tBest)).iValue != ((AI_ProvinceInfo_War)tempFrontProvinces.get((int)i4)).iValue || CFG.oR.nextInt(100) >= 50) continue;
                                tBest = i4;
                            }
                            if (CFG.core.getProv(((AI_ProvinceInfo_War)tempFrontProvinces.get((int)tBest)).iProvinceID).getArmyCivID1(nCivID) > 0) {
                                lFrontIDsWithArmies.add(tID);
                            }
                            sortedFrontProvinces.add((AI_ProvinceInfo_War)tempFrontProvinces.get(tBest));
                            tempFrontProvinces.remove(tBest);
                            ++tID;
                        }
                        this.prepareForWar_Regroup(nCivID, sortedFrontProvinces, lFrontIDsWithArmies);
                        if (CFG.core.getCiv(nCivID).getGold() <= (long)GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT || CFG.core.getCiv((int)nCivID).iBudget <= 0) break block38;
                        boolean bl = canRecruitAndMove = (float)lFrontIDsWithArmies.size() * 1.75f * (float)CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_MOVE <= (float)(CFG.core.getCiv(nCivID).getMovemPoints() - CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_RECRUIT);
                        if (canRecruitAndMove) break block39;
                        float f = CFG.core.getCiv(nCivID).getGold() / (long)GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT;
                        float f2 = CFG.core.getCiv((int)nCivID).civGD.moveAtWar_ProvincesLostAndConquered_LastTurn < 0 ? 0.16f + 0.03f * (float)CFG.core.getCiv((int)nCivID).civGD.moveAtWar_ProvincesLostAndConquered_LastTurn : (CFG.core.getCiv((int)nCivID).civGD.moveAtWar_ArmyFullyRecruitedLastTurn ? 0.6f : 0.75f);
                        if (!(f * f2 > (float)CFG.core.getCiv(nCivID).getNumberOfUnits()) && CFG.core.getCiv((int)nCivID).civGD.moveAtWar_ProvincesLostAndConquered_LastTurn >= -3 && CFG.core.getCiv(nCivID).getNumOfProvs() >= 3) break block40;
                    }
                    this.prepareForWar_Recruit(nCivID, sortedFrontProvinces, lFrontIDsWithArmies, false);
                }
                CFG.core.getCiv((int)nCivID).civGD.moveAtWar_ArmyFullyRecruitedLastTurn = false;
            }
            if (GameValues.gvAiWar.USE_NEW_NAVAL_INVASION) {
                this.moveAtWar_AtSea_New(nCivID);
            } else {
                this.moveAtWar_AtSea(nCivID);
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    public final void prepareForWar(int nCivID, float fMovemnetPointsToUse) {
        if (CFG.core.getCiv((int)nCivID).civGD.civPlans.iWarPrepsSize > 0) {
            ArrayList<Integer> tempFrontlinesIDs = new ArrayList<Integer>();
            block0: for (int i = 0; i < CFG.core.getCiv((int)nCivID).lFrontLines.size(); ++i) {
                for (int j = 0; j < CFG.core.getCiv((int)nCivID).civGD.civPlans.iWarPrepsSize; ++j) {
                    if (CFG.core.getCiv((int)nCivID).lFrontLines.get((int)i).iWithCivID != CFG.core.getCiv((int)nCivID).civGD.civPlans.warPreps.get((int)j).onCivID) continue;
                    tempFrontlinesIDs.add(i);
                    continue block0;
                }
            }
            if (!tempFrontlinesIDs.isEmpty()) {
                ArrayList<AI_ProvinceInfo> tempFrontProvinces = new ArrayList<AI_ProvinceInfo>();
                int tMaxDL = 1;
                float tMaxPotential = 1.0f;
                ArrayList<Integer> tempWithCivs = new ArrayList<Integer>();
                for (int j = 0; j < CFG.core.getCiv((int)nCivID).civGD.civPlans.iWarPrepsSize; ++j) {
                    tempWithCivs.add(CFG.core.getCiv((int)nCivID).civGD.civPlans.warPreps.get((int)j).onCivID);
                }
                for (int i = 0; i < tempFrontlinesIDs.size(); ++i) {
                    int jSize = CFG.core.getCiv((int)nCivID).lFrontLines.get((int)((Integer)tempFrontlinesIDs.get((int)i)).intValue()).lProvinces.size();
                    for (int j = 0; j < jSize; ++j) {
                        boolean wasAdded = false;
                        for (int k = 0; k < tempFrontProvinces.size(); ++k) {
                            if (((AI_ProvinceInfo)tempFrontProvinces.get((int)k)).iProvinceID != CFG.core.getCiv((int)nCivID).lFrontLines.get((int)((Integer)tempFrontlinesIDs.get((int)i)).intValue()).lProvinces.get(j)) continue;
                            wasAdded = true;
                            break;
                        }
                        if (wasAdded) continue;
                        tempFrontProvinces.add(new AI_ProvinceInfo(CFG.core.getCiv((int)nCivID).lFrontLines.get((int)((Integer)tempFrontlinesIDs.get((int)i)).intValue()).lProvinces.get(j), this.getPotential_BasedOnNeighboringProvs((int)CFG.core.getCiv((int)nCivID).lFrontLines.get((int)((Integer)tempFrontlinesIDs.get((int)i)).intValue()).lProvinces.get(j), nCivID, tempWithCivs), CFG.gameAction.gMARY(CFG.core.getCiv((int)nCivID).lFrontLines.get((int)((Integer)tempFrontlinesIDs.get((int)i)).intValue()).lProvinces.get(j))));
                    }
                }
                tempWithCivs.clear();
                tempWithCivs = null;
                if (!tempFrontProvinces.isEmpty()) {
                    int i;
                    int i2;
                    int tMaxArmy = 1;
                    ArrayList<Integer> tMovingArmy = new ArrayList<Integer>();
                    int iSize = tempFrontProvinces.size();
                    int tempMovingArmy = 0;
                    for (i2 = 0; i2 < iSize; ++i2) {
                        if (((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iValue > tMaxPotential) {
                            tMaxPotential = ((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iValue;
                        }
                        if (CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iProvinceID).getDangerLvl() > tMaxDL) {
                            tMaxDL = CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iProvinceID).getDangerLvl();
                        }
                        tMovingArmy.add(tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, ((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iProvinceID));
                        if (CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iProvinceID).getArmyID(0) + tempMovingArmy <= tMaxArmy) continue;
                        tMaxArmy = CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iProvinceID).getArmyID(0) + tempMovingArmy;
                    }
                    iSize = tempFrontProvinces.size();
                    for (i2 = 0; i2 < iSize; ++i2) {
                        ((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iValue = CFG.core.getCiv((int)nCivID).civGD.civPers.VALUABLE_POTENTIAL * (((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iValue / tMaxPotential) + CFG.core.getCiv((int)nCivID).civGD.civPers.VALUABLE_DANGER * ((float)CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iProvinceID).getDangerLvl() / (float)tMaxDL) * (1.0f - CFG.core.getCiv((int)nCivID).civGD.civPers.VALUABLE_NUM_OF_UNITS + CFG.core.getCiv((int)nCivID).civGD.civPers.VALUABLE_NUM_OF_UNITS * (1.0f - (float)(CFG.core.getProv(((AI_ProvinceInfo)tempFrontProvinces.get((int)i2)).iProvinceID).getArmyID(0) + (Integer)tMovingArmy.get(i2)) / (float)tMaxArmy));
                    }
                    ArrayList<AI_ProvinceInfo> sortedFrontProvinces = new ArrayList<AI_ProvinceInfo>();
                    while (!tempFrontProvinces.isEmpty()) {
                        int tBest = 0;
                        int iSize2 = tempFrontProvinces.size();
                        for (i = 1; i < iSize2; ++i) {
                            if (!(((AI_ProvinceInfo)tempFrontProvinces.get((int)tBest)).iValue < ((AI_ProvinceInfo)tempFrontProvinces.get((int)i)).iValue)) continue;
                            tBest = i;
                        }
                        sortedFrontProvinces.add((AI_ProvinceInfo)tempFrontProvinces.get(tBest));
                        tempFrontProvinces.remove(tBest);
                    }
                    ArrayList<Integer> lArmiesToRegoup = new ArrayList<Integer>();
                    for (i = 0; i < CFG.core.getCiv((int)nCivID).armiesPositionSize; ++i) {
                        if (CFG.core.getCiv((int)nCivID).civGD.civPlans.haveMission(CFG.core.getCiv((int)nCivID).armiesPosition.get(i))) continue;
                        lArmiesToRegoup.add(CFG.core.getCiv((int)nCivID).armiesPosition.get(i));
                    }
                    List<AI_NeighProvinces> ab = CFG.oAI.getAllNeighboringProvincesInRange_Recruit(((AI_ProvinceInfo)sortedFrontProvinces.get((int)0)).iProvinceID, nCivID, 3, true, false, new ArrayList<AI_NeighProvinces>(), new ArrayList<Integer>());
                    if (!ab.isEmpty()) {
                        int tempRand = CFG.oR.nextInt(ab.size());
                        CFG.core.getCiv(nCivID).recruitArmy_AI(ab.get((int)tempRand).iProvinceID, CFG.gameAction.gMARY(ab.get((int)tempRand).iProvinceID));
                        int tempArmy = CFG.core.getCiv(nCivID).getRecruitArmy_BasedOnProvinceID(ab.get((int)tempRand).iProvinceID);
                        if (tempArmy > 0) {
                            CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.add(new CivArmyMission_RegroupAfterRecruitment(nCivID, ab.get((int)tempRand).iProvinceID, ((AI_ProvinceInfo)sortedFrontProvinces.get((int)0)).iProvinceID, tempArmy));
                        }
                    }
                }
            }
        }
    }

    public final int getMovingArmyToProvinceID(int nCivID, int nProvinceID) {
        int i;
        int out = 0;
        for (i = 0; i < CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.size(); ++i) {
            if (CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.get((int)i).toProvinceID != nProvinceID) continue;
            out += CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.get((int)i).iArmy;
        }
        for (i = 0; i < CFG.core.getCiv(nCivID).getRegroupArmySize(); ++i) {
            if (CFG.core.getCiv(nCivID).getRegroupArmy(i).getToProvinceID() != nProvinceID) continue;
            out += CFG.core.getCiv(nCivID).getRegroupArmy(i).getNumOfUnits();
        }
        for (i = 0; i < CFG.core.getCiv(nCivID).moveUnitsSize(); ++i) {
            if (CFG.core.getCiv(nCivID).getMoveUnits(i).getToProvID() != nProvinceID) continue;
            out += CFG.core.getCiv(nCivID).getMoveUnits(i).getNumberOfUnits();
        }
        return out;
    }

    public final int getPotential_BasedOnNeighboringProvs(int nProvinceID, int nCivID) {
        int out = CFG.core.getProv(nProvinceID).getPotential();
        int tSize = 1;
        for (int i = 0; i < CFG.core.getProv(nProvinceID).getNeighProvincesSize(); ++i) {
            if (CFG.core.getProv(CFG.core.getProv(nProvinceID).getNeighProvinces(i)).getCivId() == nCivID) continue;
            out += CFG.core.getProv(CFG.core.getProv(nProvinceID).getNeighProvinces(i)).getPotentialModified(nCivID);
            ++tSize;
        }
        return out / tSize;
    }

    public final int getPotential_BasedOnNeighboringProvs(int nProvinceID, int nCivID, int withCivID) {
        int out = CFG.core.getProv(nProvinceID).getPotential();
        int tSize = 1;
        for (int i = 0; i < CFG.core.getProv(nProvinceID).getNeighProvincesSize(); ++i) {
            if (CFG.core.getProv(CFG.core.getProv(nProvinceID).getNeighProvinces(i)).getCivId() != withCivID) continue;
            out += CFG.core.getProv(CFG.core.getProv(nProvinceID).getNeighProvinces(i)).getPotentialModified(nCivID);
            ++tSize;
        }
        return out / tSize;
    }

    public final int getPotential_BasedOnNeighboringProvs(int nProvinceID, int nCivID, List<Integer> withCivID) {
        int out = CFG.core.getProv(nProvinceID).getPotential();
        int tSize = 1;
        int jSize = withCivID.size();
        for (int i = 0; i < CFG.core.getProv(nProvinceID).getNeighProvincesSize(); ++i) {
            for (int j = 0; j < jSize; ++j) {
                if (CFG.core.getProv(CFG.core.getProv(nProvinceID).getNeighProvinces(i)).getCivId() != withCivID.get(j).intValue()) continue;
                out += CFG.core.getProv(CFG.core.getProv(nProvinceID).getNeighProvinces(i)).getPotentialModified(nCivID);
                ++tSize;
            }
        }
        return out / tSize;
    }

    public boolean canMove(int nCivID) {
        return CFG.core.getCiv(nCivID).getMovemPoints() >= CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_MOVE;
    }

    public boolean canMoveAndRecruit(int nCivID) {
        return CFG.core.getCiv(nCivID).getMovemPoints() >= CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_MOVE + CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_RECRUIT;
    }

    public boolean canMoveArmyToProvinceID(int nProvinceID, int nCivID) {
        return CFG.core.getProv(nProvinceID).getCivId() == nCivID || CFG.core.getCivsAreAllied(nCivID, CFG.core.getProv(nProvinceID).getCivId()) || CFG.core.getCiv(CFG.core.getProv(nProvinceID).getCivId()).getPuppetOfCiv() == nCivID || CFG.core.getCiv(nCivID).getPuppetOfCiv() == CFG.core.getProv(nProvinceID).getCivId() || CFG.core.getMilitaryAccess(nCivID, CFG.core.getProv(nProvinceID).getCivId()) > 0;
    }

    public boolean alliesAtWar(int nCivID) {
        for (int i = 1; i < CFG.core.getCivsSize(); ++i) {
            if (i == nCivID || !CFG.core.isAlly(nCivID, i) || !CFG.core.getCiv(i).isAtWarC()) continue;
            return true;
        }
        return false;
    }

    public boolean canRecruit(int nCivID, int nProvinceID) {
        return CFG.core.getCiv(nCivID).getMovemPoints() >= CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_RECRUIT && CFG.core.getCiv(nCivID).getGold() >= (long)CFG.gCARR(nProvinceID);
    }

    public final int getRecruitableArmy(int nProvinceID, int nCivID) {
        return Math.min(CFG.gameAction.gMARY(nProvinceID, nCivID), (int)(CFG.core.getCiv(nCivID).getGold() / (long)CFG.gCARR(nProvinceID)));
    }

    public final boolean doHaveAVisionInProvince(int nProvinceID, int nCivID) {
        if (CFG.FOG_OF_WAR == 0) {
            return true;
        }
        if (CFG.core.getProv(nProvinceID).getLvlOfFort() == 0) {
            for (int i = 0; i < CFG.core.getProv(nProvinceID).getNeighProvincesSize(); ++i) {
                if (CFG.core.getProv(CFG.core.getProv(nProvinceID).getNeighProvinces(i)).getLvlOfWatchTower() <= 0 || !CFG.core.isAlly(CFG.core.getProv(CFG.core.getProv(nProvinceID).getNeighProvinces(i)).getCivId(), nCivID)) continue;
                return true;
            }
        }
        return false;
    }

    public final int getEnemyArmyInNeighbooringProvinces_ArmyOnlyAtWar(int nProvinceID, int nCivID) {
        int nOut = 0;
        for (int i = 0; i < CFG.core.getProv(nProvinceID).getNeighProvincesSize(); ++i) {
            if (CFG.core.getProv(CFG.core.getProv(nProvinceID).getNeighProvinces(i)).getCivId() <= 0) continue;
            for (int j = 0; j < CFG.core.getProv(CFG.core.getProv(nProvinceID).getNeighProvinces(i)).getCivsSize(); ++j) {
                if (!CFG.core.getCivsAtWar(CFG.core.getProv(CFG.core.getProv(nProvinceID).getNeighProvinces(i)).getCivId(j), nCivID)) continue;
                nOut += CFG.core.getProv(CFG.core.getProv(nProvinceID).getNeighProvinces(i)).getArmyCivID1(CFG.core.getProv(CFG.core.getProv(nProvinceID).getNeighProvinces(i)).getCivId(j));
            }
        }
        return nOut;
    }

    public final int getEnemyArmyInNeighbooringProvinces_Total(int nProvinceID, int nCivID) {
        int nOut = 0;
        block0: for (int i = 0; i < CFG.core.getProv(nProvinceID).getNeighProvincesSize(); ++i) {
            if (CFG.core.getProv(CFG.core.getProv(nProvinceID).getNeighProvinces(i)).getCivId() <= 0) continue;
            for (int j = 0; j < CFG.core.getProv(CFG.core.getProv(nProvinceID).getNeighProvinces(i)).getCivsSize(); ++j) {
                if (!CFG.core.getCivsAtWar(CFG.core.getProv(CFG.core.getProv(nProvinceID).getNeighProvinces(i)).getCivId(j), nCivID)) continue;
                nOut += CFG.core.getProvinceArmy(CFG.core.getProv(nProvinceID).getNeighProvinces(i));
                continue block0;
            }
        }
        return nOut;
    }

    public final int getEnemyArmyInNeighbooringSeaProvinces_Total(int nProvinceID, int nCivID) {
        int nOut = 0;
        for (int i = 0; i < CFG.core.getProv(nProvinceID).getNeighSeaProvincesSize(); ++i) {
            for (int j = 1; j < CFG.core.getProv(CFG.core.getProv(nProvinceID).getNeighSeaProvinces(i)).getCivsSize(); ++j) {
                if (!CFG.core.getCivsAtWar(CFG.core.getProv(CFG.core.getProv(nProvinceID).getNeighSeaProvinces(i)).getCivId(j), nCivID)) continue;
                nOut += CFG.core.getProv(CFG.core.getProv(nProvinceID).getNeighSeaProvinces(i)).getArmyID(j);
            }
        }
        return nOut;
    }

    public final boolean isUncivilzed(int nCivID) {
        return CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).CAN_BECOME_CIVILIZED >= 0;
    }

    public final boolean canCivlize(int nCivID) {
        return CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).CIVILIZE_TECH_LEVEL <= CFG.core.getCiv(nCivID).getTechLevel();
    }

    public final boolean civilize(int nCivID) {
        if (this.isUncivilzed(nCivID) && this.canCivlize(nCivID)) {
            if (GameCalendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES && this.tryToExpandBeforeCivilize(nCivID) && CFG.oR.nextInt(100) > 2) {
                return false;
            }
            if (GameManager.civilizeCiv(nCivID)) {
                return true;
            }
        }
        return false;
    }

    public final boolean tryToExpandBeforeCivilize(int nCivID) {
        if (CFG.core.getCiv(nCivID).getBordersWithEnemy() > 0) {
            return false;
        }
        if (CFG.core.getCiv(nCivID).getGold() + (long)CFG.core.getCiv((int)nCivID).iBudget > -1000L && CFG.core.getCiv(nCivID).getNumOfNeighboringNeutralProvinces() > 0) {
            for (int k = CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.size() - 1; k >= 0; --k) {
                if (CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.get((int)k).MISSION_TYPE != CivArmyMission_Type.EXPAND_NETURAL_PROVINCE) continue;
                if (CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.get(k).action(nCivID)) {
                    CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.get(k).onRemove();
                    CFG.core.getCiv((int)nCivID).civGD.civPlans.armiesMissions.remove(k);
                    continue;
                }
                return true;
            }
            if (CFG.core.getCiv(nCivID).getNumOfProvs() < 6 + nCivID % 2) {
                int minArmy = -1;
                for (int i = 0; i < CFG.core.getCiv(nCivID).getNumOfProvs(); ++i) {
                    for (int j = 0; j < CFG.core.getProv(CFG.core.getCiv(nCivID).getProvID(i)).getNeighProvincesSize(); ++j) {
                        if (CFG.core.getProv(CFG.core.getProv(CFG.core.getCiv(nCivID).getProvID(i)).getNeighProvinces(j)).getCivId() != 0) continue;
                        minArmy = minArmy < 0 ? CFG.core.getProv(CFG.core.getProv(CFG.core.getCiv(nCivID).getProvID(i)).getNeighProvinces(j)).getArmyID(0) : Math.min(minArmy, CFG.core.getProv(CFG.core.getProv(CFG.core.getCiv(nCivID).getProvID(i)).getNeighProvinces(j)).getArmyID(0));
                    }
                }
                if (minArmy < 0) {
                    return false;
                }
                if ((minArmy = (int)((long)minArmy - ((long)CFG.core.getCiv(nCivID).getNumberOfUnits() + Math.max(CFG.core.getCiv(nCivID).getGold(), 0L) / (long)GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT))) <= 0) {
                    CFG.oAI.expandToNeutralProvinces_Out(nCivID, false);
                    return true;
                }
                int willTakeNumOfTurns = (int)Math.ceil((float)minArmy / (float)(CFG.core.getCiv((int)nCivID).iBudget / GameValues.gvArmyRecruit.COST_OF_RECRUIT_ARMY_GOLD_PER_UNIT));
                if (willTakeNumOfTurns < 50) {
                    CFG.oAI.expandToNeutralProvinces_Out(nCivID, false);
                    return true;
                }
            }
        }
        return false;
    }

    public final void checkBalanceOfProvinces_Tribal(int nCivID) {
        try {
            ArrayList<Integer> lProvincesWithDeficit_ALL = new ArrayList<Integer>();
            int totalBalanceOnMinus = 0;
            int totalBalancePositive = 0;
            for (int i = 0; i < CFG.core.getCiv(nCivID).getNumOfProvs(); ++i) {
                if (CFG.core.getProv(CFG.core.getCiv(nCivID).getProvID(i)).isOccupied()) continue;
                if (CFG.core.getProv(CFG.core.getCiv(nCivID).getProvID(i)).getBalance_LastTurn() < 0) {
                    if (CFG.core.getCiv(nCivID).getProvID(i) != CFG.core.getCiv(nCivID).getCapitalProvID()) {
                        totalBalanceOnMinus += CFG.core.getProv(CFG.core.getCiv(nCivID).getProvID(i)).getBalance_LastTurn();
                        lProvincesWithDeficit_ALL.add(CFG.core.getCiv(nCivID).getProvID(i));
                        continue;
                    }
                    totalBalanceOnMinus += CFG.core.getProv(CFG.core.getCiv(nCivID).getProvID(i)).getBalance_LastTurn();
                    continue;
                }
                totalBalancePositive += CFG.core.getProv(CFG.core.getCiv(nCivID).getProvID(i)).getBalance_LastTurn();
            }
            if (!lProvincesWithDeficit_ALL.isEmpty() && (float)totalBalancePositive * 0.65f < (float)Math.abs(totalBalanceOnMinus)) {
                float fAverage = 0.0f;
                for (int i = lProvincesWithDeficit_ALL.size() - 1; i >= 0; --i) {
                    fAverage += (float)CFG.core.getProv((Integer)lProvincesWithDeficit_ALL.get(i)).getBalance_LastTurn();
                }
                fAverage /= (float)lProvincesWithDeficit_ALL.size();
                ArrayList<Integer> lProvincesToDoSomething = new ArrayList<Integer>();
                for (int i = lProvincesWithDeficit_ALL.size() - 1; i >= 0; --i) {
                    if (!(fAverage * 0.375f > (float)CFG.core.getProv((Integer)lProvincesWithDeficit_ALL.get(i)).getBalance_LastTurn())) continue;
                    lProvincesToDoSomething.add((Integer)lProvincesWithDeficit_ALL.get(i));
                }
                this.abandonOrReleaseAsVassalProvinces(nCivID, lProvincesToDoSomething, true);
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    public static void sendUltimatumToPlayer() {
        if (GameCalendar.TURNID % GameValues.gvAiDiplomacy.DEMAND_VASSALIZATION_MODULO_TURN == GameValues.gvAiDiplomacy.DEMAND_VASSALIZATION_MODULO_TURN_CHECK_IF) {
            for (int i = 0; i < CFG.core.getPlayersSize(); ++i) {
                if (CFG.core.getCiv((int)CFG.core.getPlayer((int)i).getCivId()).civNeighbors.civsSize <= 0 || CFG.core.getCiv(CFG.core.getPlayer(i).getCivId()).getPuppetOfCiv() != CFG.core.getPlayer(i).getCivId() || CFG.oR.nextInt(100) >= GameValues.gvAiDiplomacy.DEMAND_VASSALIZATION_CHECK_CHANCE_100) continue;
                int bestID = 0;
                for (int a = 0; a < CFG.core.getCiv((int)CFG.core.getPlayer((int)i).getCivId()).civNeighbors.civsSize; ++a) {
                    if (CFG.core.getCiv(CFG.core.getCiv((int)CFG.core.getPlayer((int)i).getCivId()).civNeighbors.civs.get((int)a).civID).getNumOfProvs() <= 0 || CFG.core.getCiv(CFG.core.getCiv((int)CFG.core.getPlayer((int)i).getCivId()).civNeighbors.civs.get((int)a).civID).getPuppetOfCiv() != CFG.core.getCiv((int)CFG.core.getPlayer((int)i).getCivId()).civNeighbors.civs.get((int)a).civID || CFG.core.getCiv(CFG.core.getCiv((int)CFG.core.getPlayer((int)i).getCivId()).civNeighbors.civs.get((int)a).civID).isAtWarC() || CFG.core.getCiv((int)CFG.core.getCiv((int)CFG.core.getPlayer((int)i).getCivId()).civNeighbors.civs.get((int)a).civID).civGD.civPlans.isPreparingForTheWar() || CFG.core.getCiv(CFG.core.getCiv((int)CFG.core.getPlayer((int)i).getCivId()).civNeighbors.civs.get((int)a).civID).getNumberOfUnits() <= CFG.core.getCiv(CFG.core.getCiv((int)CFG.core.getPlayer((int)i).getCivId()).civNeighbors.civs.get((int)bestID).civID).getNumberOfUnits()) continue;
                    bestID = a;
                }
                int fromCivID = CFG.core.getCiv((int)CFG.core.getPlayer((int)i).getCivId()).civNeighbors.civs.get((int)bestID).civID;
                if (fromCivID != CFG.core.getCiv(fromCivID).getPuppetOfCiv() || CFG.core.getCiv(fromCivID).isAtWarC() || CFG.core.getCiv((int)fromCivID).civGD.civPlans.isPreparingForTheWar() || CFG.core.getCiv(fromCivID).getNumberOfUnits() < CFG.core.getCiv(CFG.core.getPlayer(i).getCivId()).getNumberOfUnits() || !((float)CFG.core.getCiv((int)fromCivID).iBudget * GameValues.gvAiDiplomacy.DEMAND_VASSALIZATION_CIV_FROM_BUDGET_MODIFIER > (float)CFG.core.getCiv((int)CFG.core.getPlayer((int)i).getCivId()).iBudget)) continue;
                GameManager.decreaseRelation(fromCivID, CFG.core.getPlayer(i).getCivId(), GameValues.gvRelationDecrease.SUSPEND_DIPLOMATIC_RELATIONS_MAX);
                GameManager.decreaseRelation(fromCivID, CFG.core.getPlayer(i).getCivId(), GameValues.gvRelationDecrease.SUSPEND_DIPLOMATIC_RELATIONS_MAX);
                if (CFG.core.getCiv(fromCivID).getNumOfProvs() <= 0) continue;
                Ultimatum_GameData ultimatumGameData = new Ultimatum_GameData();
                ultimatumGameData.demandVasalization = true;
                GameManager.sendUltimatumFree(CFG.core.getPlayer(i).getCivId(), fromCivID, ultimatumGameData, CFG.core.getCiv(fromCivID).getNumberOfUnits());
            }
        }
    }

    public final boolean abandonOrReleaseAsVassalProvinces(int nCivID, List<Integer> tProvinces, boolean canAbandon) {
        int i;
        int nNewVassalID;
        int k;
        boolean addCiv;
        int j;
        int i2;
        ArrayList<AI_ReleaseVassal> lCivsToRelease = new ArrayList<AI_ReleaseVassal>();
        for (i2 = tProvinces.size() - 1; i2 >= 0; --i2) {
            for (j = 0; j < CFG.core.getProv(tProvinces.get(i2)).getCores().getCivsSize(); ++j) {
                if (CFG.core.getCiv(CFG.core.getProv(tProvinces.get(i2)).getCores().getCivID(j)).getNumOfProvs() != 0) continue;
                addCiv = true;
                for (k = lCivsToRelease.size() - 1; k >= 0; --k) {
                    if (((AI_ReleaseVassal)lCivsToRelease.get((int)k)).iCivID != CFG.core.getProv(tProvinces.get(i2)).getCores().getCivID(j)) continue;
                    addCiv = false;
                    ((AI_ReleaseVassal)lCivsToRelease.get(k)).addProvince(tProvinces.get(i2));
                    break;
                }
                if (!addCiv) continue;
                lCivsToRelease.add(new AI_ReleaseVassal(CFG.core.getProv(tProvinces.get(i2)).getCores().getCivID(j), tProvinces.get(i2)));
            }
        }
        if (!lCivsToRelease.isEmpty() && (nNewVassalID = this.abandonOrReleaseAsVassalProvinces_ReleaseVassal(lCivsToRelease, tProvinces, nCivID)) >= 0) {
            for (i = tProvinces.size() - 1; i >= 0; --i) {
                if (!CFG.core.getCiv(nNewVassalID).controlsProvince(tProvinces.get(i))) continue;
                tProvinces.remove(i);
            }
            return this.abandonOrReleaseAsVassalProvinces(nCivID, tProvinces, canAbandon);
        }
        lCivsToRelease.clear();
        for (i2 = tProvinces.size() - 1; i2 >= 0; --i2) {
            for (j = 0; j < CFG.core.getProv(tProvinces.get(i2)).getNeighProvincesSize(); ++j) {
                if (CFG.core.getProv(CFG.core.getProv(tProvinces.get(i2)).getNeighProvinces(j)).getCivId() <= 0 || CFG.core.getProv(CFG.core.getProv(tProvinces.get(i2)).getNeighProvinces(j)).getCivId() == nCivID) continue;
                addCiv = true;
                for (k = lCivsToRelease.size() - 1; k >= 0; --k) {
                    if (((AI_ReleaseVassal)lCivsToRelease.get((int)k)).iCivID != CFG.core.getProv(CFG.core.getProv(tProvinces.get(i2)).getNeighProvinces(j)).getCivId()) continue;
                    addCiv = false;
                    ((AI_ReleaseVassal)lCivsToRelease.get(k)).addProvince(tProvinces.get(i2));
                    break;
                }
                if (!addCiv) continue;
                lCivsToRelease.add(new AI_ReleaseVassal(CFG.core.getProv(CFG.core.getProv(tProvinces.get(i2)).getNeighProvinces(j)).getCivId(), tProvinces.get(i2)));
            }
            for (j = 0; j < CFG.core.getProv(tProvinces.get(i2)).getCores().getCivsSize(); ++j) {
                if (CFG.core.getProv(tProvinces.get(i2)).getCores().getCivID(j) == nCivID) continue;
                addCiv = true;
                for (k = lCivsToRelease.size() - 1; k >= 0; --k) {
                    if (((AI_ReleaseVassal)lCivsToRelease.get((int)k)).iCivID != CFG.core.getProv(tProvinces.get(i2)).getCores().getCivID(j)) continue;
                    addCiv = false;
                    if (((AI_ReleaseVassal)lCivsToRelease.get(k)).haveProvince(tProvinces.get(i2))) break;
                    ((AI_ReleaseVassal)lCivsToRelease.get(k)).addProvince(tProvinces.get(i2));
                    break;
                }
                if (!addCiv) continue;
                lCivsToRelease.add(new AI_ReleaseVassal(CFG.core.getProv(tProvinces.get(i2)).getCores().getCivID(j), tProvinces.get(i2)));
            }
        }
        ArrayList<AI_ReleaseVassal> lAllies = new ArrayList<AI_ReleaseVassal>();
        for (i = lCivsToRelease.size() - 1; i >= 0; --i) {
            if (!CFG.core.isAlly(nCivID, ((AI_ReleaseVassal)lCivsToRelease.get((int)i)).iCivID)) continue;
            lAllies.add((AI_ReleaseVassal)lCivsToRelease.get(i));
        }
        for (j = lAllies.size() - 1; j >= 0; --j) {
            for (int i3 = CFG.core.getCiv(nCivID).getSentMessagesSize() - 1; i3 >= 0; --i3) {
                if (CFG.core.getCiv((int)nCivID).getSentMessage((int)i3).messageType != MessageType.TRADE_REQUEST_GIVE_PROVINCES || CFG.core.getCiv((int)nCivID).getSentMessage((int)i3).iToCivID != ((AI_ReleaseVassal)lAllies.get((int)j)).iCivID) continue;
                lAllies.remove(j);
            }
        }
        while (!lAllies.isEmpty()) {
            int i4;
            int tBest = 0;
            for (int i5 = lAllies.size() - 1; i5 > 0; --i5) {
                if (((AI_ReleaseVassal)lAllies.get((int)tBest)).lProvinces.size() >= ((AI_ReleaseVassal)lAllies.get((int)i5)).lProvinces.size() && CFG.oR.nextInt(100) >= 10) continue;
                tBest = i5;
            }
            TradeRequest_GameData nTD = new TradeRequest_GameData();
            for (int i6 = ((AI_ReleaseVassal)lAllies.get((int)tBest)).lProvinces.size() - 1; i6 >= 0; --i6) {
                nTD.listLEFT.lProvinces.add(((AI_ReleaseVassal)lAllies.get((int)tBest)).lProvinces.get(i6));
            }
            boolean messageSent = GameManager.sendTradeRequest(((AI_ReleaseVassal)lAllies.get((int)tBest)).iCivID, nCivID, nTD);
            if (!messageSent) break;
            CFG.core.getCiv((int)nCivID).civGD.sentMessages.add(new Civilization_SentMessages(((AI_ReleaseVassal)lAllies.get((int)tBest)).iCivID, MessageType.TRADE_REQUEST_GIVE_PROVINCES));
            CFG.core.getCiv((int)((AI_ReleaseVassal)lAllies.get((int)tBest)).iCivID).civGD.sentMessages.add(new Civilization_SentMessages(nCivID, MessageType.TRADE_REQUEST_GIVE_PROVINCES));
            block15: for (int j2 = nTD.listLEFT.lProvinces.size() - 1; j2 >= 0; --j2) {
                for (int i7 = tProvinces.size() - 1; i7 >= 0; --i7) {
                    if (!tProvinces.get(i7).equals(nTD.listLEFT.lProvinces.get(j2))) continue;
                    tProvinces.remove(i7);
                    continue block15;
                }
            }
            for (i4 = lAllies.size() - 1; i4 >= 0; --i4) {
                if (i4 == tBest) continue;
                for (int j3 = ((AI_ReleaseVassal)lAllies.get((int)tBest)).lProvinces.size() - 1; j3 >= 0; --j3) {
                    ((AI_ReleaseVassal)lAllies.get(i4)).removeProvinceID(((AI_ReleaseVassal)lAllies.get((int)tBest)).lProvinces.get(j3));
                }
            }
            lAllies.remove(tBest);
            for (i4 = lAllies.size() - 1; i4 >= 0; --i4) {
                if (((AI_ReleaseVassal)lAllies.get((int)i4)).lProvinces.size() != 0) continue;
                lAllies.remove(i4);
            }
        }
        for (i = tProvinces.size() - 1; i >= 0; --i) {
            CFG.gameAction.abandonProvince(tProvinces.get(i), nCivID);
        }
        return true;
    }

    public final int abandonOrReleaseAsVassalProvinces_ReleaseVassal(List<AI_ReleaseVassal> lCivsToRelease, List<Integer> tProvinces, int nCivID) {
        int i;
        int tBest = 0;
        for (i = lCivsToRelease.size() - 1; i > 0; --i) {
            if (lCivsToRelease.get((int)tBest).lProvinces.size() < lCivsToRelease.get((int)i).lProvinces.size()) {
                tBest = i;
                continue;
            }
            if (lCivsToRelease.get((int)tBest).lProvinces.size() != lCivsToRelease.get((int)i).lProvinces.size() || CFG.oR.nextInt(100) >= 50) continue;
            tBest = i;
        }
        for (i = tProvinces.size() - 1; i >= 0; --i) {
            CFG.core.getProv((int)tProvinces.get((int)i).intValue()).wasInProv = true;
        }
        for (i = lCivsToRelease.get((int)tBest).lProvinces.size() - 1; i >= 0; --i) {
            CFG.core.getProv((int)lCivsToRelease.get((int)tBest).lProvinces.get((int)i).intValue()).wasInProv = false;
        }
        for (i = 0; i < lCivsToRelease.get((int)tBest).lProvinces.size(); ++i) {
            int j;
            for (j = 0; j < CFG.core.getProv(lCivsToRelease.get((int)tBest).lProvinces.get(i)).getNeighProvincesSize(); ++j) {
                if (!CFG.core.getProv((int)CFG.core.getProv((int)lCivsToRelease.get((int)tBest).lProvinces.get((int)i).intValue()).getNeighProvinces((int)j)).wasInProv) continue;
                boolean canBeAdded = true;
                for (int o = lCivsToRelease.size() - 1; o >= 0; --o) {
                    if (!CFG.core.getProv(CFG.core.getProv(lCivsToRelease.get((int)tBest).lProvinces.get(i)).getNeighProvinces(j)).getCores().getHaveACore(lCivsToRelease.get((int)o).iCivID)) continue;
                    canBeAdded = false;
                    break;
                }
                block6: for (int m = 0; m < CFG.core.getProv(CFG.core.getProv(lCivsToRelease.get((int)tBest).lProvinces.get(i)).getNeighProvinces(j)).getNeighProvincesSize(); ++m) {
                    for (int u = lCivsToRelease.size() - 1; u >= 0; --u) {
                        if (u == tBest || !lCivsToRelease.get(u).haveProvince(CFG.core.getProv(CFG.core.getProv(lCivsToRelease.get((int)tBest).lProvinces.get(i)).getNeighProvinces(j)).getNeighProvinces(m))) continue;
                        canBeAdded = false;
                        continue block6;
                    }
                }
                if (!canBeAdded) continue;
                lCivsToRelease.get(tBest).addProvince(CFG.core.getProv(lCivsToRelease.get((int)tBest).lProvinces.get(i)).getNeighProvinces(j));
                CFG.core.getProv((int)CFG.core.getProv((int)lCivsToRelease.get((int)tBest).lProvinces.get((int)i).intValue()).getNeighProvinces((int)j)).wasInProv = false;
            }
            for (j = 0; j < CFG.core.getProv(lCivsToRelease.get((int)tBest).lProvinces.get(i)).getNeighSeaProvincesSize(); ++j) {
                for (int k = 0; k < CFG.core.getProv(CFG.core.getProv(lCivsToRelease.get((int)tBest).lProvinces.get(i)).getNeighSeaProvinces(j)).getNeighProvincesSize(); ++k) {
                    if (CFG.core.getProv(CFG.core.getProv(CFG.core.getProv(lCivsToRelease.get((int)tBest).lProvinces.get(i)).getNeighSeaProvinces(j)).getNeighProvinces(k)).getSeaProv() || !CFG.core.getProv((int)CFG.core.getProv((int)CFG.core.getProv((int)lCivsToRelease.get((int)tBest).lProvinces.get((int)i).intValue()).getNeighSeaProvinces((int)j)).getNeighProvinces((int)k)).wasInProv) continue;
                    boolean canBeAdded = true;
                    for (int o = lCivsToRelease.size() - 1; o >= 0; --o) {
                        if (!CFG.core.getProv(CFG.core.getProv(CFG.core.getProv(lCivsToRelease.get((int)tBest).lProvinces.get(i)).getNeighSeaProvinces(j)).getNeighProvinces(k)).getCores().getHaveACore(lCivsToRelease.get((int)o).iCivID)) continue;
                        canBeAdded = false;
                        break;
                    }
                    block11: for (int m = 0; m < CFG.core.getProv(CFG.core.getProv(CFG.core.getProv(lCivsToRelease.get((int)tBest).lProvinces.get(i)).getNeighSeaProvinces(j)).getNeighProvinces(k)).getNeighProvincesSize(); ++m) {
                        for (int u = lCivsToRelease.size() - 1; u >= 0; --u) {
                            if (u == tBest || !lCivsToRelease.get(u).haveProvince(CFG.core.getProv(CFG.core.getProv(CFG.core.getProv(lCivsToRelease.get((int)tBest).lProvinces.get(i)).getNeighSeaProvinces(j)).getNeighProvinces(k)).getNeighProvinces(m))) continue;
                            canBeAdded = false;
                            continue block11;
                        }
                    }
                    if (!canBeAdded) continue;
                    lCivsToRelease.get(tBest).addProvince(CFG.core.getProv(CFG.core.getProv(lCivsToRelease.get((int)tBest).lProvinces.get(i)).getNeighSeaProvinces(j)).getNeighProvinces(k));
                    CFG.core.getProv((int)CFG.core.getProv((int)CFG.core.getProv((int)lCivsToRelease.get((int)tBest).lProvinces.get((int)i).intValue()).getNeighSeaProvinces((int)j)).getNeighProvinces((int)k)).wasInProv = false;
                }
            }
        }
        this.clearWas(tProvinces);
        return CFG.core.releaseVassal(CFG.core.getCiv(lCivsToRelease.get((int)tBest).iCivID).getCivTag(), lCivsToRelease.get((int)tBest).lProvinces, -1, nCivID, true);
    }

    public final void clearWas(List<Integer> was) {
        for (int i = was.size() - 1; i >= 0; --i) {
            CFG.core.getProv((int)was.get((int)i).intValue()).wasInProv = false;
        }
    }

    public final void clearWas() {
        for (int i = 0; i < CFG.core.getProvinSize(); ++i) {
            CFG.core.getProv((int)i).wasInProv = false;
        }
    }

    public final float armyOverBudget_Disband_AtWar(int nCivID) {
        return 0.9f - CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(nCivID).getIdeology()).getMin_Goods(nCivID) - CFG.ideologiesMgr.getInvestments(CFG.core.getCiv(nCivID).getIdeology(), nCivID);
    }

    public void armyOverBudget_Disband(int nCivID) {
        if (CFG.core.getCiv(nCivID).getMovemPoints() >= CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_DISBAND) {
            boolean atWar = false;
            if ((CFG.core.getCiv(nCivID).isAtWarC() || CFG.core.getCiv((int)nCivID).civGD.civPlans.isPreparingForTheWar()) && CFG.core.getCiv((int)nCivID).iBudget > 0 && CFG.core.getCiv(nCivID).getGold() + (long)(CFG.core.getCiv((int)nCivID).iBudget * 3) > 0L) {
                atWar = true;
                return;
            }
            ArrayList<AI_ArmyUpkeep> armyUpkeep = new ArrayList<AI_ArmyUpkeep>();
            int spendingsOnArmy = (int)((float)CFG.core.getCiv((int)nCivID).iBudget * (atWar ? this.armyOverBudget_Disband_AtWar(nCivID) : this.getMinMilitarySpending(nCivID)));
            int budgetForArmyisOver = (int)Math.abs((float)CFG.core.getCiv((int)nCivID).iBudget * (atWar ? this.armyOverBudget_Disband_AtWar(nCivID) : this.getMinMilitarySpending(nCivID)) - (float)CFG.core.getCiv((int)nCivID).iBudget * CFG.core.getCiv((int)nCivID).iMilitaryUpkeep_PERC);
            if (CFG.core.getCiv((int)nCivID).iMilitaryUpkeep_Total > spendingsOnArmy) {
                int i;
                for (int i2 = 0; i2 < CFG.core.getCiv((int)nCivID).armiesPositionSize; ++i2) {
                    armyUpkeep.add(new AI_ArmyUpkeep(nCivID, CFG.core.getCiv((int)nCivID).armiesPosition.get(i2)));
                }
                ArrayList<AI_ArmyUpkeep> armiesOver = new ArrayList<AI_ArmyUpkeep>();
                for (i = armyUpkeep.size() - 1; i >= 0; --i) {
                    if (((AI_ArmyUpkeep)armyUpkeep.get((int)i)).iCost < budgetForArmyisOver) continue;
                    armiesOver.add((AI_ArmyUpkeep)armyUpkeep.get(i));
                }
                if (armiesOver.size() > 0) {
                    int tBestID = 0;
                    for (int i3 = tBestID + 1; i3 < armiesOver.size(); ++i3) {
                        if (CFG.core.getProv(((AI_ArmyUpkeep)armiesOver.get((int)tBestID)).iProvinceID).getDangerLvl() <= CFG.core.getProv(((AI_ArmyUpkeep)armiesOver.get((int)i3)).iProvinceID).getDangerLvl()) continue;
                        tBestID = i3;
                    }
                    float costPerUnit = CFG.gameUpdate.getMilitaryUpkeepP(((AI_ArmyUpkeep)armiesOver.get((int)tBestID)).iProvinceID, 1000, nCivID) / 1000.0f * 1.05f;
                    int maxDisbandArmy = CFG.core.getProv(((AI_ArmyUpkeep)armiesOver.get((int)tBestID)).iProvinceID).getArmyCivID1(nCivID);
                    if (maxDisbandArmy > 0) {
                        CFG.gameAction.disbandArmy(((AI_ArmyUpkeep)armiesOver.get((int)tBestID)).iProvinceID, (int)Math.min(Math.ceil((float)budgetForArmyisOver / costPerUnit), (double)maxDisbandArmy), nCivID);
                    }
                } else {
                    armiesOver.clear();
                    for (i = armyUpkeep.size() - 1; i >= 0; --i) {
                        if (CFG.core.getProv(((AI_ArmyUpkeep)armyUpkeep.get((int)i)).iProvinceID).getDangerLvl() != 0) continue;
                        armiesOver.add((AI_ArmyUpkeep)armyUpkeep.get(i));
                    }
                    if (armiesOver.size() > 0) {
                        int tTotalCost = 0;
                        for (int i4 = armiesOver.size() - 1; i4 >= 0; --i4) {
                            tTotalCost += ((AI_ArmyUpkeep)armiesOver.get((int)i4)).iCost;
                        }
                        if (tTotalCost >= budgetForArmyisOver) {
                            while (CFG.core.getCiv(nCivID).getMovemPoints() >= CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_DISBAND && armiesOver.size() > 0) {
                                if (budgetForArmyisOver <= 0) {
                                    return;
                                }
                                int tBest = 0;
                                for (int i5 = armiesOver.size() - 1; i5 > 0; --i5) {
                                    if (((AI_ArmyUpkeep)armiesOver.get((int)tBest)).iCost >= ((AI_ArmyUpkeep)armiesOver.get((int)i5)).iCost) continue;
                                    tBest = i5;
                                }
                                float costPerUnit = CFG.gameUpdate.getMilitaryUpkeepP(((AI_ArmyUpkeep)armiesOver.get((int)tBest)).iProvinceID, 1000, nCivID) / 1000.0f * 1.05f;
                                CFG.gameAction.disbandArmy(((AI_ArmyUpkeep)armiesOver.get((int)tBest)).iProvinceID, (int)Math.ceil((float)budgetForArmyisOver / costPerUnit), nCivID);
                                budgetForArmyisOver -= ((AI_ArmyUpkeep)armiesOver.get((int)tBest)).iCost;
                                armiesOver.remove(tBest);
                            }
                        }
                    }
                    while (CFG.core.getCiv(nCivID).getMovemPoints() >= CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)nCivID).getIdeology()).COST_OF_DISBAND && armyUpkeep.size() > 0) {
                        if (budgetForArmyisOver <= 0) {
                            return;
                        }
                        int tBest = 0;
                        for (int i6 = armyUpkeep.size() - 1; i6 > 0; --i6) {
                            if (((AI_ArmyUpkeep)armyUpkeep.get((int)tBest)).iCost >= ((AI_ArmyUpkeep)armyUpkeep.get((int)i6)).iCost) continue;
                            tBest = i6;
                        }
                        float costPerUnit = CFG.gameUpdate.getMilitaryUpkeepP(((AI_ArmyUpkeep)armyUpkeep.get((int)tBest)).iProvinceID, 1000, nCivID) / 1000.0f * 1.05f;
                        CFG.gameAction.disbandArmy(((AI_ArmyUpkeep)armyUpkeep.get((int)tBest)).iProvinceID, (int)Math.ceil((float)budgetForArmyisOver / costPerUnit), nCivID);
                        budgetForArmyisOver -= ((AI_ArmyUpkeep)armyUpkeep.get((int)tBest)).iCost;
                        armyUpkeep.remove(tBest);
                    }
                }
            }
        }
    }

    public final void useTechnologyPoints(int nCivID) {
        if (CFG.core.getCiv((int)nCivID).civGD.techPoints.getPointsLeft(nCivID) > 0) {
            ArrayList<AI_Skills> nSkills = new ArrayList<AI_Skills>();
            nSkills.add(new AI_Skills_Movement(CFG.core.getCiv((int)nCivID).civGD.techPoints.POINTS_MOVEMENT, GameValues.gvTechnology.MAX_POINTS_MOVEMENT));
            nSkills.add(new AI_Skills(CFG.core.getCiv((int)nCivID).civGD.techPoints.POINTS_POP_GROWTH, GameValues.gvTechnology.MAX_POINTS_POP_GROWTH));
            nSkills.add(new AI_Skills_Eco(CFG.core.getCiv((int)nCivID).civGD.techPoints.POINTS_ECONOMY_GROWTH, GameValues.gvTechnology.MAX_POINTS_ECONOMY_GROWTH));
            nSkills.add(new AI_Skills_Taxation(CFG.core.getCiv((int)nCivID).civGD.techPoints.POINTS_INCOME_TAXATION, GameValues.gvTechnology.MAX_POINTS_INCOME_TAXATION));
            nSkills.add(new AI_Skills_Production(CFG.core.getCiv((int)nCivID).civGD.techPoints.POINTS_INCOME_PRODUCTION, GameValues.gvTechnology.MAX_POINTS_INCOME_PRODUCTION));
            nSkills.add(new AI_Skills_Administration(CFG.core.getCiv((int)nCivID).civGD.techPoints.POINTS_ADMINISTRATION, GameValues.gvTechnology.MAX_POINTS_ADMINISTRATION));
            nSkills.add(new AI_Skills_Military(CFG.core.getCiv((int)nCivID).civGD.techPoints.POINTS_MILITARY_UPKEEP, GameValues.gvTechnology.MAX_POINTS_MILITARY_UPKEEP));
            nSkills.add(new AI_Skills_Assimilate(CFG.core.getCiv((int)nCivID).civGD.techPoints.POINTS_ASSIMILATE, GameValues.gvTechnology.MAX_POINTS_ASSIMILATE));
            nSkills.add(new AI_Skills_Research(CFG.core.getCiv((int)nCivID).civGD.techPoints.POINTS_RESEARCH, GameValues.gvTechnology.MAX_POINTS_RESEARCH));
            nSkills.add(new AI_Skills_Recruitable(CFG.core.getCiv((int)nCivID).civGD.techPoints.POINTS_RECRUITABLE, GameValues.gvTechnology.MAX_POINTS_RECRUITABLE));
            int pointsToUse = CFG.core.getCiv((int)nCivID).civGD.techPoints.getPointsLeft(nCivID);
            int nSkillsSize = nSkills.size();
            if (!CFG.core.getCiv((int)nCivID).civGD.coloniesFounded.isEmpty() && CFG.core.getCiv((int)nCivID).civGD.techPoints.POINTS_COLONIZATION < GameValues.gvTechnology.MAX_POINTS_COLONIZATION && CFG.core.getCiv((int)nCivID).civGD.techPoints.POINTS_COLONIZATION < CFG.core.getCiv((int)nCivID).civGD.coloniesFounded.size()) {
                SkillsManager.add_Colonization(nCivID);
            }
            while (true) {
                int n = --pointsToUse;
                --pointsToUse;
                if (n <= 0) break;
                int tBestID = 0;
                for (int i = tBestID + 1; i < nSkillsSize; ++i) {
                    if (!(((AI_Skills)nSkills.get(tBestID)).getScore(nCivID) < ((AI_Skills)nSkills.get(i)).getScore(nCivID))) continue;
                    tBestID = i;
                }
                ((AI_Skills)nSkills.get(tBestID)).addPoint_CivID(nCivID);
            }
        }
    }

    public final void updateLibertyDesire(int nCivID) {
        if (CFG.core.getCiv(nCivID).getPuppetOfCiv() != nCivID) {
            if (!CFG.VASSALS_CAN_DECLARE_INDEPENDENCE) {
                CFG.core.getCiv(nCivID).setVassalLibertyDesire(0.0f);
                return;
            }
            boolean updateLiberity = true;
            Civilization civ = CFG.core.getCiv(nCivID);
            try {
                if ((float)civ.getNumOfProvs() > (float)CFG.core.getCiv(civ.getPuppetOfCiv()).getNumOfProvs() * GameValues.gvVassalLiberty.PROVINCE_RATIO_THRESHOLD) {
                    civ.setVassalLibertyDesire(civ.getVassalLibertyDesire() + (GameValues.gvVassalLiberty.BASE_PROVINCE_LIBERTY_INCREASE + (float)CFG.oR.nextInt(GameValues.gvVassalLiberty.RANDOM_PROVINCE_LIBERTY_MAX_1000) / 1000.0f) * ((float)civ.getNumOfProvs() / (float)CFG.core.getCiv(civ.getPuppetOfCiv()).getNumOfProvs() * GameValues.gvVassalLiberty.PROVINCE_RATIO_MULTIPLIER) * (float)GameValues.gvUpdate.AI_TURN_ESSENTIALS_2);
                    updateLiberity = false;
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
            try {
                if ((float)CFG.core.getCiv(civ.getPuppetOfCiv()).getVassal_Tribute(nCivID) > (float)GameValues.gvVassal.PERCENTAGE_OF_INCOME_FOR_LORD_MAX * civ.LIBERTY_ACCEPTABLE_TRIBUTE) {
                    civ.setVassalLibertyDesire(civ.getVassalLibertyDesire() + (civ.getVassalLibertyDesire() * GameValues.gvVassalLiberty.BASE_LIBERTY_HIGH_MULTIPLIER_PERC + (GameValues.gvVassalLiberty.BASE_TRIBUTE_HIGH_LIBERTY_INCREASE + (float)CFG.oR.nextInt(GameValues.gvVassalLiberty.RANDOM_TRIBUTE_HIGH_LIBERTY_100) / 100.0f) * ((float)CFG.core.getCiv(civ.getPuppetOfCiv()).getVassal_Tribute(nCivID) / (float)GameValues.gvVassal.PERCENTAGE_OF_INCOME_FOR_LORD_MAX)) * (float)GameValues.gvUpdate.AI_TURN_ESSENTIALS_2);
                    updateLiberity = false;
                } else if ((float)CFG.core.getCiv(civ.getPuppetOfCiv()).getVassal_Tribute(nCivID) < (float)GameValues.gvVassal.PERCENTAGE_OF_INCOME_FOR_LORD_MAX * civ.LIBERTY_ACCEPTABLE_TRIBUTE * GameValues.gvVassalLiberty.TRIBUTE_LOW_THRESHOLD_MULTIPLIER) {
                    civ.setVassalLibertyDesire(civ.getVassalLibertyDesire() - (GameValues.gvVassalLiberty.BASE_TRIBUTE_LOW_LIBERTY_DECREASE + (float)CFG.oR.nextInt(GameValues.gvVassalLiberty.RANDOM_TRIBUTE_LOW_LIBERTY_DECREASE_100) / 100.0f) * (1.0f - (float)CFG.core.getCiv(civ.getPuppetOfCiv()).getVassal_Tribute(nCivID) / (float)GameValues.gvVassal.PERCENTAGE_OF_INCOME_FOR_LORD_MAX) * (float)GameValues.gvUpdate.AI_TURN_ESSENTIALS_2);
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
            try {
                if (CFG.core.getCivRelationOfCivB(nCivID, civ.getPuppetOfCiv()) < (float)GameValues.gvVassalLiberty.RELATION_NEGATIVE_THRESHOLD) {
                    civ.setVassalLibertyDesire(civ.getVassalLibertyDesire() + GameValues.gvVassalLiberty.BASE_RELATION_LIBERTY_LOW_INCREASE * Math.abs(CFG.core.getCivRelationOfCivB(nCivID, civ.getPuppetOfCiv()) / 100.0f) * (float)GameValues.gvUpdate.AI_TURN_ESSENTIALS_2);
                    updateLiberity = false;
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
            if (updateLiberity) {
                civ.setVassalLibertyDesire(civ.getVassalLibertyDesire() - civ.getVassalLibertyDesire() * GameValues.gvVassalLiberty.PASSIVE_LIBERTY_DECAY_PERC * (float)GameValues.gvUpdate.AI_TURN_ESSENTIALS_2);
            }
            if (civ.getVassalLibertyDesire() > civ.LIBERTY_DECLARATION) {
                GameManager.declarationOfIndependenceByVassal(civ.getPuppetOfCiv(), nCivID);
            }
        }
    }

    public static float getLibertyDesireChange_JustInfo(int nCivID) {
        if (CFG.core.getCiv(nCivID).getPuppetOfCiv() != nCivID) {
            float out = 0.0f;
            boolean updateLiberity = true;
            Civilization civ = CFG.core.getCiv(nCivID);
            try {
                if ((float)civ.getNumOfProvs() > (float)CFG.core.getCiv(civ.getPuppetOfCiv()).getNumOfProvs() * GameValues.gvVassalLiberty.PROVINCE_RATIO_THRESHOLD) {
                    out += (GameValues.gvVassalLiberty.BASE_PROVINCE_LIBERTY_INCREASE + (float)GameValues.gvVassalLiberty.RANDOM_PROVINCE_LIBERTY_MAX_1000 * GameValues.gvVassalLiberty.LIBERTY_CHANGE_JUST_INFO_RANDOM_MODIFIER / 1000.0f) * ((float)civ.getNumOfProvs() / (float)CFG.core.getCiv(civ.getPuppetOfCiv()).getNumOfProvs() * GameValues.gvVassalLiberty.PROVINCE_RATIO_MULTIPLIER);
                    updateLiberity = false;
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
            try {
                if ((float)CFG.core.getCiv(civ.getPuppetOfCiv()).getVassal_Tribute(nCivID) > (float)GameValues.gvVassal.PERCENTAGE_OF_INCOME_FOR_LORD_MAX * civ.LIBERTY_ACCEPTABLE_TRIBUTE) {
                    out += civ.getVassalLibertyDesire() * GameValues.gvVassalLiberty.BASE_LIBERTY_HIGH_MULTIPLIER_PERC + (GameValues.gvVassalLiberty.BASE_TRIBUTE_HIGH_LIBERTY_INCREASE + (float)GameValues.gvVassalLiberty.RANDOM_TRIBUTE_HIGH_LIBERTY_100 * GameValues.gvVassalLiberty.LIBERTY_CHANGE_JUST_INFO_RANDOM_MODIFIER / 100.0f) * ((float)CFG.core.getCiv(civ.getPuppetOfCiv()).getVassal_Tribute(nCivID) / (float)GameValues.gvVassal.PERCENTAGE_OF_INCOME_FOR_LORD_MAX);
                    updateLiberity = false;
                } else if ((float)CFG.core.getCiv(civ.getPuppetOfCiv()).getVassal_Tribute(nCivID) < (float)GameValues.gvVassal.PERCENTAGE_OF_INCOME_FOR_LORD_MAX * civ.LIBERTY_ACCEPTABLE_TRIBUTE * GameValues.gvVassalLiberty.TRIBUTE_LOW_THRESHOLD_MULTIPLIER) {
                    out -= (GameValues.gvVassalLiberty.BASE_TRIBUTE_LOW_LIBERTY_DECREASE + (float)GameValues.gvVassalLiberty.RANDOM_TRIBUTE_LOW_LIBERTY_DECREASE_100 * GameValues.gvVassalLiberty.LIBERTY_CHANGE_JUST_INFO_RANDOM_MODIFIER / 100.0f) * (1.0f - (float)CFG.core.getCiv(civ.getPuppetOfCiv()).getVassal_Tribute(nCivID) / (float)GameValues.gvVassal.PERCENTAGE_OF_INCOME_FOR_LORD_MAX);
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
            try {
                if (CFG.core.getCivRelationOfCivB(nCivID, civ.getPuppetOfCiv()) < (float)GameValues.gvVassalLiberty.RELATION_NEGATIVE_THRESHOLD) {
                    out += GameValues.gvVassalLiberty.BASE_RELATION_LIBERTY_LOW_INCREASE * Math.abs(CFG.core.getCivRelationOfCivB(nCivID, civ.getPuppetOfCiv()) / 100.0f);
                    updateLiberity = false;
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
            if (updateLiberity) {
                out -= civ.getVassalLibertyDesire() * GameValues.gvVassalLiberty.PASSIVE_LIBERTY_DECAY_PERC;
            }
            return out;
        }
        return 0.0f;
    }

    public final void investForeign(int civID) {
        try {
            if (!CFG.core.getCiv(civID).isAtWarC() && !CFG.core.getCiv((int)civID).civGD.civPlans.isPreparingForTheWar() && CFG.core.getCiv(civID).getGold() > (long)GameValues.gvAiInvest.INVEST_FOREIGN_MIN_GOLD && CFG.oR.nextInt(1000) < GameValues.gvAiInvest.INVEST_FOREIGN_RAND_CHANCE_1000) {
                int randValue = CFG.oR.nextInt(100);
                if (randValue < GameValues.gvAiInvest.INVEST_FOREIGN_FRIENDLY_CIV) {
                    if (CFG.core.getCiv(civID).getFriendlyCivsSize() > 0) {
                        int randProvince;
                        int randFriendly = CFG.oR.nextInt(CFG.core.getCiv(civID).getFriendlyCivsSize());
                        if (CFG.core.getCiv(CFG.core.getCiv((int)civID).getFriendlyCiv((int)randFriendly).iCivID).getNumOfProvs() > 0 && !CFG.core.getProv(randProvince = CFG.core.getCiv(CFG.core.getCiv((int)civID).getFriendlyCiv((int)randFriendly).iCivID).getProvID(CFG.oR.nextInt(CFG.core.getCiv(CFG.core.getCiv((int)civID).getFriendlyCiv((int)randFriendly).iCivID).getNumOfProvs()))).getSeaProv() && CFG.core.getProv(randProvince).getWastelandLvl() < 0 && CFG.core.getProv(randProvince).getCivId() != civID && CFG.core.getProv(randProvince).getCivId() > 0) {
                            int maxInvestGold = (int)Math.min(CFG.core.getCiv(civID).getGold(), (long)GameManager.invest_MaxEconomy_Gold(randProvince, civID));
                            GameManager.investForeignEconomy(civID, randProvince, (int)((float)maxInvestGold * GameValues.gvAiInvest.INVEST_FOREIGN_MAX_GOLD_MIN + (float)CFG.oR.nextInt((int)Math.max(1.0, Math.ceil((float)maxInvestGold * GameValues.gvAiInvest.INVEST_FOREIGN_MAX_GOLD_RAND)))));
                        }
                    }
                } else if (randValue < GameValues.gvAiInvest.INVEST_FOREIGN_NEIGHBOURING_CIV) {
                    if (CFG.core.getCiv((int)civID).civNeighbors.civsSize > 0) {
                        int randProvince;
                        int randCiv;
                        ArrayList<Integer> possibleCivs = new ArrayList<Integer>();
                        for (int i = 0; i < CFG.core.getCiv((int)civID).civNeighbors.civsSize; ++i) {
                            if (!(CFG.core.getCiv(civID).getRelationD(CFG.core.getCiv((int)civID).civNeighbors.civs.get((int)i).civID) >= (float)GameValues.gvAiInvest.INVEST_FOREIGN_MIN_RELATION) || CFG.core.getCiv(civID).areSanctionsAdded(civID, CFG.core.getCiv((int)civID).civNeighbors.civs.get((int)i).civID) || CFG.core.getCiv(CFG.core.getCiv((int)civID).civNeighbors.civs.get((int)i).civID).areSanctionsAdded(CFG.core.getCiv((int)civID).civNeighbors.civs.get((int)i).civID, civID)) continue;
                            possibleCivs.add(CFG.core.getCiv((int)civID).civNeighbors.civs.get((int)i).civID);
                        }
                        if (!possibleCivs.isEmpty() && CFG.core.getCiv((Integer)possibleCivs.get(randCiv = CFG.oR.nextInt(possibleCivs.size()))).getNumOfProvs() > 0 && !CFG.core.getProv(randProvince = CFG.core.getCiv((Integer)possibleCivs.get(randCiv)).getProvID(CFG.oR.nextInt(CFG.core.getCiv((Integer)possibleCivs.get(randCiv)).getNumOfProvs()))).getSeaProv() && CFG.core.getProv(randProvince).getWastelandLvl() < 0 && CFG.core.getProv(randProvince).getCivId() != civID && CFG.core.getProv(randProvince).getCivId() > 0) {
                            int maxInvestGold = (int)Math.min(CFG.core.getCiv(civID).getGold(), (long)GameManager.invest_MaxEconomy_Gold(randProvince, civID));
                            GameManager.investForeignEconomy(civID, randProvince, (int)((float)maxInvestGold * GameValues.gvAiInvest.INVEST_FOREIGN_MAX_GOLD_MIN + (float)CFG.oR.nextInt((int)Math.max(1.0, Math.ceil((float)maxInvestGold * GameValues.gvAiInvest.INVEST_FOREIGN_MAX_GOLD_RAND)))));
                        }
                        possibleCivs.clear();
                    }
                } else {
                    int randProvince = CFG.oR.nextInt(CFG.core.getProvinSize());
                    if (!CFG.core.getProv(randProvince).getSeaProv() && CFG.core.getProv(randProvince).getWastelandLvl() < 0 && CFG.core.getProv(randProvince).getCivId() != civID && CFG.core.getProv(randProvince).getCivId() > 0) {
                        int maxInvestGold = (int)Math.min(CFG.core.getCiv(civID).getGold(), (long)GameManager.invest_MaxEconomy_Gold(randProvince, civID));
                        GameManager.investForeignEconomy(civID, randProvince, (int)((float)maxInvestGold * GameValues.gvAiInvest.INVEST_FOREIGN_MAX_GOLD_MIN + (float)CFG.oR.nextInt((int)Math.max(1.0, Math.ceil((float)maxInvestGold * GameValues.gvAiInvest.INVEST_FOREIGN_MAX_GOLD_RAND)))));
                    }
                }
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    public static boolean unionResponseAI(int fromCivID, int nCivID) {
        if (CFG.core.getCiv(fromCivID).getCivDiploGD().getIsEmbassyClosed(nCivID)) {
            return false;
        }
        if (CFG.core.getCiv((int)nCivID).civGD.civPlans.isPreparingForTheWar(fromCivID)) {
            return false;
        }
        if (CFG.core.getCiv((int)nCivID).iLeague < GameValues.gvAiDiplomacy.UNION_MIN_LEAGUE && CFG.core.getCiv((int)fromCivID).iLeague < GameValues.gvAiDiplomacy.UNION_MIN_LEAGUE) {
            return false;
        }
        if (CFG.core.isAlly(fromCivID, nCivID)) {
            if (CFG.core.getCiv((int)fromCivID).civGD.numOfUnions == 0 && CFG.core.getCiv((int)nCivID).civGD.numOfUnions == 0) {
                boolean sameRivals = false;
                for (int a = 0; a < CFG.core.getCiv(nCivID).getHatedCivsSize(); ++a) {
                    for (int b = 0; b < CFG.core.getCiv(fromCivID).getHatedCivsSize(); ++b) {
                        if (!CFG.core.getCiv(fromCivID).isHatedCiv(CFG.core.getCiv((int)nCivID).getHatedCiv((int)a).iCivID)) continue;
                        sameRivals = true;
                        break;
                    }
                    if (sameRivals) break;
                }
                if (sameRivals) {
                    return true;
                }
                return CFG.core.getCiv(nCivID).getRelationD(fromCivID) > (float)GameValues.gvAiDiplomacy.UNION_ALLY_MIN_RELATION && (float)CFG.core.getCiv(fromCivID).getNumOfProvs() >= (float)CFG.core.getCiv(nCivID).getNumOfProvs() * GameValues.gvAiDiplomacy.UNION_ALLY_NUM_OF_PROVINCES_MODIFIER;
            }
            if (CFG.core.getCiv(nCivID).getNumOfProvs() <= GameValues.gvAiDiplomacy.UNION_SECOND_UNION_MAX_PROVINCES) {
                if (CFG.core.getCiv((int)fromCivID).civGD.numOfUnions < GameValues.gvAiDiplomacy.UNION_MAX_NUM_OF_UNIONS && CFG.core.getCiv((int)nCivID).civGD.numOfUnions < GameValues.gvAiDiplomacy.UNION_MAX_NUM_OF_UNIONS) {
                    return CFG.core.getCiv(nCivID).getRelationD(fromCivID) > (float)GameValues.gvAiDiplomacy.UNION_ALLY_MIN_RELATION && (float)CFG.core.getCiv(fromCivID).getNumOfProvs() >= (float)CFG.core.getCiv(nCivID).getNumOfProvs() * GameValues.gvAiDiplomacy.UNION_ALLY_NUM_OF_PROVINCES_MODIFIER;
                }
                return false;
            }
            return false;
        }
        return CFG.core.getCiv(nCivID).getRelationD(fromCivID) > (float)GameValues.gvAiDiplomacy.UNION_MIN_RELATION && (float)CFG.core.getCiv(fromCivID).getNumOfProvs() >= (float)CFG.core.getCiv(nCivID).getNumOfProvs() * GameValues.gvAiDiplomacy.UNION_NUM_OF_PROVINCES_MODIFIER;
    }

    public static boolean offerVassalization_AIResponse(int fromCivID, int toCivID) {
        return (float)CFG.core.getCiv((int)toCivID).iBudget * (GameValues.gvAiDiplomacy.OFFER_VASSALIZATION_BUDGET_RATIO_TO_ACCEPT + (float)CFG.core.getCiv((int)fromCivID).civGD.iVassalsSize * GameValues.gvAiDiplomacy.OFFER_VASSALIZATION_BUDGET_RATIO_TO_ACCEPT_EXTRA_PER_VASSAL) < (float)CFG.core.getCiv((int)fromCivID).iBudget && !CFG.core.getCiv(toCivID).isHatedCiv(fromCivID) && CFG.core.getCiv(toCivID).getRelationD(fromCivID) >= GameValues.gvAiDiplomacy.OFFER_VASSALIZATION_MIN_RELATION;
    }

    public static float offerVassalization_BudgetToAccept(int byCivID) {
        return GameValues.gvAiDiplomacy.OFFER_VASSALIZATION_BUDGET_RATIO_TO_ACCEPT + (float)CFG.core.getCiv((int)byCivID).civGD.iVassalsSize * GameValues.gvAiDiplomacy.OFFER_VASSALIZATION_BUDGET_RATIO_TO_ACCEPT_EXTRA_PER_VASSAL;
    }

    public static int tradeDealAI_ResponseInfo(TradeRequest_GameData tradeRequest, int fromCivID, int nCivID) {
        if (CFG.SANDBOX_MODE) {
            return 2;
        }
        Civilization civ = CFG.core.getCiv(nCivID);
        if (tradeRequest.listRight.iDeclareWarOnCivID > 0) {
            if (tradeRequest.listLEFT.iGold == GameValues.gvTrade.DECLARE_WAR_MAGIC_NUM_ALWAYS_ACCEPT) {
                return 2;
            }
            if (civ.isHatedCiv(fromCivID)) {
                return -1;
            }
            if (civ.isFriendlyCiv(tradeRequest.listRight.iDeclareWarOnCivID) >= 0) {
                return -1;
            }
            if ((float)civ.countPop() * GameValues.gvTrade.DECLARE_WAR_CIV_POP_MODIFIER < (float)CFG.core.getCiv(tradeRequest.listRight.iDeclareWarOnCivID).countPop()) {
                return -1;
            }
            if (tradeRequest.listLEFT.iGold == GameValues.gvTrade.DECLARE_WAR_MAGIC_NUM_ALWAYS_ACCEPT) {
                return 2;
            }
            if (tradeRequest.listRight.iGold > 0) {
                return -1;
            }
            int civIncome = Math.max(CFG.core.getCiv((int)nCivID).incomeTaxation + CFG.core.getCiv((int)nCivID).incomeProduction, CFG.core.getCiv((int)tradeRequest.listRight.iDeclareWarOnCivID).incomeTaxation + CFG.core.getCiv((int)tradeRequest.listRight.iDeclareWarOnCivID).incomeProduction);
            civIncome += (int)Math.max(1.0f, (float)CFG.core.getCiv(tradeRequest.listRight.iDeclareWarOnCivID).getNumberOfUnits() * GameValues.gvTrade.DECLARE_WAR_CIV_GOLD_PER_ENEMY_UNIT);
            if (tradeRequest.listLEFT.iGold >= (civIncome = (int)((float)civIncome * GameValues.gvTrade.DECLARE_WAR_CIV_INCOME_MULTIPLIER))) {
                return 2;
            }
            if (!tradeRequest.listLEFT.lProvinces.isEmpty()) {
                long totalGold = tradeRequest.listLEFT.iGold;
                for (int a = 0; a < tradeRequest.listLEFT.lProvinces.size(); ++a) {
                    totalGold = (long)((float)totalGold + Math.max(GameValues.gvTrade.AI_TRADE_PROVINCE_MIN_COST, CFG.core.getProv((int)tradeRequest.listLEFT.lProvinces.get((int)a).intValue()).incomeTaxation * GameValues.gvTrade.AI_TRADE_PROVINCE_INCOME_TAXATION_WEIGHT + CFG.core.getProv((int)tradeRequest.listLEFT.lProvinces.get((int)a).intValue()).incomeProduction * GameValues.gvTrade.AI_TRADE_PROVINCE_INCOME_PRODUCTION_WEIGHT));
                }
                if (totalGold >= (long)civIncome) {
                    return 2;
                }
                return -1;
            }
            return -1;
        }
        if (tradeRequest.listRight.iFormCoalitionAgainst > 0 || tradeRequest.listLEFT.iFormCoalitionAgainst > 0) {
            if (civ.isHatedCiv(fromCivID)) {
                return -1;
            }
            if (!tradeRequest.listRight.lProvinces.isEmpty()) {
                return -1;
            }
            if (tradeRequest.listRight.iGold > 0) {
                return -1;
            }
            if (civ.isAtWarC()) {
                return -1;
            }
            if (civ.isHatedCiv(tradeRequest.listLEFT.iFormCoalitionAgainst) || civ.isHatedCiv(tradeRequest.listRight.iFormCoalitionAgainst)) {
                if (civ.isFriendlyCiv(fromCivID) >= 0) {
                    return 2;
                }
                return -1;
            }
            return -1;
        }
        if (tradeRequest.listRight.lProvinces.size() > 0) {
            if ((float)tradeRequest.listRight.lProvinces.size() / (float)civ.getNumOfProvs() > GameValues.gvTrade.AI_TRADE_MAX_PROVINCE_SHARE_TO_ACCEPT) {
                return -1;
            }
            boolean haveACore = false;
            for (int z = 0; z < tradeRequest.listRight.lProvinces.size(); ++z) {
                if (!CFG.core.getProv(tradeRequest.listRight.lProvinces.get(z)).getCores().getHaveACore(nCivID)) continue;
                haveACore = true;
                break;
            }
            if (!haveACore) {
                int totalCost = 0;
                for (int z = 0; z < tradeRequest.listRight.lProvinces.size(); ++z) {
                    totalCost = (int)((float)totalCost + Math.max(GameValues.gvTrade.AI_TRADE_PROVINCE_MIN_COST, CFG.core.getProv((int)tradeRequest.listRight.lProvinces.get((int)z).intValue()).incomeTaxation * GameValues.gvTrade.AI_TRADE_PROVINCE_INCOME_TAXATION_WEIGHT + CFG.core.getProv((int)tradeRequest.listRight.lProvinces.get((int)z).intValue()).incomeProduction * GameValues.gvTrade.AI_TRADE_PROVINCE_INCOME_PRODUCTION_WEIGHT));
                }
                if (tradeRequest.listLEFT.iGold > (totalCost = (int)Math.ceil((float)totalCost * GameValues.gvTrade.AI_TRADE_PROVINCE_COST_MULTIPLIER))) {
                    return 2;
                }
                return -1;
            }
            return -1;
        }
        if (!tradeRequest.listLEFT.lProvinces.isEmpty() && tradeRequest.listRight.iGold > 0) {
            int maxGold = (int)Math.ceil((float)tradeRequest.listLEFT.lProvinces.size() * GameValues.gvTrade.AI_TRADE_ACCEPT_PROVINCES_MAX_GOLD_PER_PROVINCE);
            if (maxGold >= tradeRequest.listRight.iGold) {
                if ((float)(civ.getGold() - (long)tradeRequest.listRight.iGold) > GameValues.gvTrade.AI_TRADE_ACCEPT_PROVINCES_ONLY_IF_TREASURY_AFTER_PAYING_IS_OVER) {
                    return 2;
                }
                return -1;
            }
            return -1;
        }
        if (tradeRequest.listRight.militaryAccess && !tradeRequest.listRight.proclaimIndependence && !tradeRequest.listRight.nonAggressionPact && !tradeRequest.listRight.defensivePact && tradeRequest.listRight.iGold <= 0 && tradeRequest.listRight.lProvinces.isEmpty()) {
            boolean warAgainstFriendlyCiv = false;
            for (int z = 0; z < CFG.core.getCiv((int)fromCivID).isAtWarWithCivs.size(); ++z) {
                if (civ.isFriendlyCiv(CFG.core.getCiv((int)fromCivID).isAtWarWithCivs.get(z)) < 0) continue;
                warAgainstFriendlyCiv = true;
                break;
            }
            if (warAgainstFriendlyCiv) {
                if (civ.isHatedCiv(fromCivID)) {
                    if ((float)tradeRequest.listLEFT.iGold > (float)civ.iBudget * GameValues.gvTrade.AI_TRADE_MILITARY_ACCESS_BUDGET_MULTIPLIER_AT_WAR_WITH_FRIENDLY) {
                        return 2;
                    }
                    return -1;
                }
            } else {
                if (civ.isHatedCiv(fromCivID)) {
                    if ((float)tradeRequest.listLEFT.iGold > (float)civ.iBudget * GameValues.gvTrade.AI_TRADE_MILITARY_ACCESS_BUDGET_MULTIPLIER_FROM_HATED_CIV) {
                        return 2;
                    }
                    return -1;
                }
                if (tradeRequest.listLEFT.iGold > 0) {
                    if (civ.iBudget > 0) {
                        if ((float)tradeRequest.listLEFT.iGold > (float)civ.iBudget * GameValues.gvTrade.AI_TRADE_MILITARY_ACCESS_BUDGET_MULTIPLIER) {
                            return 2;
                        }
                        return -1;
                    }
                    return 2;
                }
            }
        } else {
            if (tradeRequest.listRight.defensivePact || tradeRequest.listLEFT.defensivePact) {
                if (civ.isHatedCiv(fromCivID)) {
                    return -1;
                }
                if (tradeRequest.listRight.iGold > 0) {
                    return -1;
                }
                return 0;
            }
            if (tradeRequest.listRight.proclaimIndependence || tradeRequest.listLEFT.proclaimIndependence) {
                if (civ.isHatedCiv(fromCivID)) {
                    return -1;
                }
                if (tradeRequest.listRight.proclaimIndependence && CFG.core.getCiv(nCivID).getNumOfProvs() > CFG.core.getCiv(fromCivID).getNumOfProvs()) {
                    if (CFG.core.getCiv(fromCivID).getPuppetOfCiv() != fromCivID) {
                        return -1;
                    }
                    if (GameManager.getGuaranteeTheirIndependenceSize(fromCivID) >= GameValues.gvTrade.PROCLAIM_THEIR_INDEPENDENCE_CIVS_LIMIT) {
                        return -1;
                    }
                    return 0;
                }
                if (tradeRequest.listLEFT.proclaimIndependence && CFG.core.getCiv(nCivID).getNumOfProvs() < CFG.core.getCiv(fromCivID).getNumOfProvs() && CFG.core.getCiv(nCivID).getNumOfProvs() < GameValues.gvTrade.PROCLAIM_INDEPENDENCE_MAX_PROVINCES) {
                    if (CFG.core.getCiv(nCivID).getPuppetOfCiv() != nCivID) {
                        return -1;
                    }
                    return 0;
                }
                if (CFG.core.getCiv(nCivID).getPuppetOfCiv() != nCivID || CFG.core.getCiv(fromCivID).getPuppetOfCiv() != fromCivID) {
                    return -1;
                }
                return 0;
            }
            if (tradeRequest.listRight.nonAggressionPact || tradeRequest.listLEFT.nonAggressionPact) {
                if (civ.isHatedCiv(fromCivID)) {
                    return -1;
                }
                if (tradeRequest.listRight.iGold > 0) {
                    return -1;
                }
                return 0;
            }
            if (tradeRequest.listRight.militaryAccess || tradeRequest.listLEFT.militaryAccess) {
                if (civ.isHatedCiv(fromCivID)) {
                    return -1;
                }
                if (tradeRequest.listRight.iGold > 0) {
                    return -1;
                }
                return 0;
            }
            if (tradeRequest.listRight.iGold > 0 || tradeRequest.listLEFT.iGold > 0) {
                if (tradeRequest.listRight.iGold > tradeRequest.listLEFT.iGold) {
                    return -1;
                }
                return 2;
            }
            return 2;
        }
        return -1;
    }

    public static class CivDistance {
        public int civID;
        public float distance;

        public CivDistance(int civID, float distance) {
            this.civID = civID;
            this.distance = distance;
        }
    }
}
