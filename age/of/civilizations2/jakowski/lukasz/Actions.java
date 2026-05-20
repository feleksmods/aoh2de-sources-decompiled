package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.GameAction;
import age.of.civilizations2.jakowski.lukasz.GameValues.GameValues;
import age.of.civilizations2.jakowski.lukasz.Menus.RTO.Menu_InGame_RTO2;
import age.of.civilizations2.jakowski.lukasz.RTS;
import age.of.civilizations2.jakowski.lukasz.Render;
import age.of.civilizations2.jakowski.lukasz.TechManager;

public class Actions
extends Thread {
    @Override
    public void run() {
        Actions.doActions();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void doActions() {
        long time = System.nanoTime();
        time = System.nanoTime();
        try {
            int i;
            CFG.menus.getInGameProvInfo().getMenuElem(0).setTextE(CFG.lang.get("Next"));
            try {
                CFG.gameAction.battleReports.clear();
            }
            catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
            if (CFG.getIsDesktop()) {
                Actions.runRevolts();
            }
            time = System.nanoTime();
            time = System.nanoTime();
            time = System.nanoTime();
            time = System.nanoTime();
            time = System.nanoTime();
            CFG.menus.updateBuildProvinceHoverInformation();
            CFG.gameAction.resetCurrentMoveUnits();
            time = System.nanoTime();
            CFG.menus.setVisibleInGame_Messages(false);
            time = System.nanoTime();
            if ((!RTS.isEnabled() || RTS.PAUSE) && CFG.gameAction.showNextPlayerTurnView()) {
                CFG.map.getMpB().updateWorldMap_Shaders();
                Render.updateRenderer();
                if (CFG.FOG_OF_WAR == 2) {
                    CFG.core.enableDrawCivlizationsRegions_Player(CFG.PLAYER_TURN_ID);
                } else {
                    CFG.core.enableDrawCivlizationsRegions_Players();
                }
            }
            try {
                if (!CFG.SPECTATOR_MODE) {
                    for (int i2 = 0; i2 < CFG.core.getPlayersSize(); ++i2) {
                        if (CFG.core.getCiv(CFG.core.getPlayer(i2).getCivId()).getGold() >= (long)GameValues.gvInvestEconomy.BUDGET_LOCK_INVESTMENTS_IF_GOLD_BELOW) continue;
                        CFG.core.getCiv(CFG.core.getPlayer(i2).getCivId()).setSpendingInvestmentsB(Math.min(CFG.core.getCiv(CFG.core.getPlayer(i2).getCivId()).getSpendingInvestmentsB(), CFG.ideologiesMgr.getInvestments(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getIdeology(), CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())));
                        CFG.core.getCiv(CFG.core.getPlayer(i2).getCivId()).setSpendingGoodsB(Math.min(CFG.core.getCiv(CFG.core.getPlayer(i2).getCivId()).getSpendingGoodsB(), CFG.ideologiesMgr.getIdeologyID(CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getIdeology()).getMin_Goods(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())));
                    }
                }
            }
            catch (Exception i2) {
                // empty catch block
            }
            time = System.nanoTime();
            CFG.core.clearMoveUnits_JustDraw_AnotherArmies();
            time = System.nanoTime();
            CFG.map.getTouchMgr().ueExA();
            time = System.nanoTime();
            TechManager.updateCivs_ResearchProgress();
            time = System.nanoTime();
            CFG.oAI.setLoadingTurnActionsOfCivID(0);
            time = System.nanoTime();
            CFG.oAI.buildAI_Data();
            time = System.nanoTime();
            try {
                CFG.oAI.turnOrders_0();
            }
            catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
            try {
                CFG.oAI.turnOrders_1();
            }
            catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
            try {
                CFG.oAI.turnOrders_2();
            }
            catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
            try {
                CFG.oAI.turnOrders();
            }
            catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
            try {
                CFG.oAI.turnOrders_InvestForeign();
            }
            catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
            try {
                CFG.oAI.turnOrders_End();
            }
            catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
            time = System.nanoTime();
            for (i = 1; i < CFG.core.getCivsSize(); ++i) {
                try {
                    int j;
                    for (j = 0; j < CFG.core.getCiv(i).moveUnitsSize(); ++j) {
                        CFG.core.getProv(CFG.core.getCiv(i).getMoveUnits(j).getFromProviID()).updateArmy4(i, CFG.core.getProv(CFG.core.getCiv(i).getMoveUnits(j).getFromProviID()).getArmyCivID1(i) + CFG.core.getCiv(i).getMoveUnits(j).getNumberOfUnits());
                    }
                    for (j = 0; j < CFG.core.getCiv(i).getMoveUnitsPlunderSize(); ++j) {
                        CFG.core.getProv(CFG.core.getCiv(i).getMoveUnitsPlunder(j).getFromProvinceID()).updateArmy4(i, CFG.core.getProv(CFG.core.getCiv(i).getMoveUnitsPlunder(j).getFromProvinceID()).getArmyCivID1(i) + CFG.core.getCiv(i).getMoveUnitsPlunder(j).getNumOfUnits());
                    }
                    continue;
                }
                catch (Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
            time = System.nanoTime();
            time = System.nanoTime();
            time = System.nanoTime();
            CFG.gameAction.setActiveTurnState(GameAction.TurnStates.TURN_ACTIONS);
            time = System.nanoTime();
            CFG.core.disableDrawCivlizationsRegions_Players();
            CFG.map.getTouchMgr().ueExA();
            time = System.nanoTime();
            CFG.map.getMpB().updateWorldMap_Shaders();
            Render.updateRenderer();
            Render.updateDrawMoveUnits();
            CFG.core.updateDrawMoveUnitsArmy();
            time = System.nanoTime();
            CFG.gameAction.SHOW_REPORT = false;
            for (i = 1; i < CFG.core.getCivsSize(); ++i) {
                CFG.core.getCiv(i).runRecruitArmyNT();
            }
            CFG.menus.updateBuildProvinceHoverInformation();
            time = System.nanoTime();
        }
        catch (StackOverflowError ex) {
            CFG.exceptionStack(ex);
            try {
                CFG.menus.getInGameProvInfo().getMenuElem(0).setClickable(true);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        finally {
            time = System.nanoTime();
            try {
                CFG.menus.getInGameProvInfo().getMenuElem(0).setClickable(true);
            }
            catch (Exception exception) {}
        }
        CFG.setRenderO(true);
        Menu_InGame_RTO2.TIME_CONTINUE = System.currentTimeMillis();
    }

    public static final void runRevolts() {
        long time = System.nanoTime();
        try {
            CFG.core.revoltDeclareIndependence();
            time = System.nanoTime();
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
        catch (StackOverflowError exr) {
            CFG.exceptionStack(exr);
        }
        try {
            CFG.gameAction.beginUprising();
            time = System.nanoTime();
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
        catch (StackOverflowError exr) {
            CFG.exceptionStack(exr);
        }
    }
}
