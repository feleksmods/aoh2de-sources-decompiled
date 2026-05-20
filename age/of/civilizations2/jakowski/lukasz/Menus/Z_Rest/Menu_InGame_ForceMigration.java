package age.of.civilizations2.jakowski.lukasz.Menus.Z_Rest;

import age.of.civilizations2.jakowski.lukasz.Button.Button_RelocatePop;
import age.of.civilizations2.jakowski.lukasz.Button.MenuElemUI;
import age.of.civilizations2.jakowski.lukasz.Button2.Text_Desc;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Colors;
import age.of.civilizations2.jakowski.lukasz.Core.Core;
import age.of.civilizations2.jakowski.lukasz.IMGManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.Menus.Relations.Actions.Menu_InGameOfferAlliance;
import age.of.civilizations2.jakowski.lukasz.Renderer;
import age.of.civilizations2.jakowski.lukasz.TextB.Texts.TextBuildTitle;
import age.of.civilizations2.jakowski.lukasz.TextB.Texts.TextScale;
import age.of.civilizations2.jakowski.lukasz.Title.TitleM_TextSmall;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Menu_InGame_ForceMigration
extends Menu {
    public final int getElementW2() {
        return this.getWidthM();
    }

    public Menu_InGame_ForceMigration(int civID) {
        int i;
        int i2;
        ArrayList<MenuElemUI> menuElements = new ArrayList<MenuElemUI>();
        int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
        int tY = CFG.PADD;
        menuElements.add(new Text_Desc(CFG.lang.get("PopulationTransferDesc"), CFG.PADD, tY, tempWidth - CFG.PADD * 2){

            @Override
            protected Color getColor(boolean isActive) {
                return Colors.getColorButtonHover2(isActive, this.getIsHovered());
            }

            @Override
            public int getWidthE() {
                return Menu_InGame_ForceMigration.this.getElementW2() - CFG.PADD * 2;
            }
        });
        tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD;
        HashMap<Integer, Integer> natioMap = new HashMap<Integer, Integer>();
        for (i2 = 0; i2 < CFG.core.getCiv(civID).getNumOfProvs(); ++i2) {
            if (CFG.core.getProv(CFG.core.getCiv(civID).getProvID(i2)).isOccupied()) continue;
            for (int j = 0; j < CFG.core.getProv(CFG.core.getCiv(civID).getProvID(i2)).getPop().getNatsSize(); ++j) {
                int otherCivID = CFG.core.getProv(CFG.core.getCiv(civID).getProvID(i2)).getPop().getCivID(j);
                if (civID == otherCivID) continue;
                int pop = CFG.core.getProv(CFG.core.getCiv(civID).getProvID(i2)).getPop().getPopulationID(j);
                natioMap.put(otherCivID, natioMap.getOrDefault(otherCivID, 0) + pop);
            }
        }
        menuElements.add(new TextBuildTitle(CFG.lang.get("OngoingMigrations") + ": " + CFG.core.getCiv(civID).getCivName(), -1, 0, tY, CFG.BUTTON_W, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 4){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_GRAY_NS_HOVER : (this.getIsClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_GRAY_NS : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }

            @Override
            public int getWidthE() {
                return Menu_InGame_ForceMigration.this.getElementW2();
            }
        });
        tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        if (CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).playerGD.migrationF.isEmpty()) {
            menuElements.add(new TextScale(CFG.lang.get("None"), -1, 0, tY, CFG.BUTTON_W, CFG.BUTTON_H * 3 / 4, 0.75f){

                @Override
                public int getWidthE() {
                    return Menu_InGame_ForceMigration.this.getElementW2();
                }

                @Override
                public void actionElem(int iID) {
                    CFG.toastM.addM(CFG.lang.get("None"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                }
            });
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        } else {
            for (i2 = 0; i2 < CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).playerGD.migrationF.size(); ++i2) {
                int pop = natioMap.getOrDefault(CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).playerGD.migrationF.get(i2), 0);
                menuElements.add(new Button_RelocatePop(i2, (int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).playerGD.migrationF.get(i2), pop, 0, tY, CFG.BUTTON_W * 2){

                    @Override
                    public int getWidthE() {
                        return Menu_InGame_ForceMigration.this.getElementW2();
                    }

                    @Override
                    public void actionElem(int iID) {
                        for (int a = 0; a < CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).playerGD.migrationF.size(); ++a) {
                            if (CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).playerGD.migrationF.get(a).intValue() != this.getCurr()) continue;
                            CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).playerGD.migrationF.remove(a);
                            CFG.menus.rebuildInGameForceMigration(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId());
                            CFG.toastM.addM(CFG.lang.get("StopMigration"), CFG.COLOR_HOVER_TITLE);
                            break;
                        }
                    }

                    @Override
                    public boolean getCheckboxSt() {
                        return true;
                    }
                });
                tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
            }
        }
        menuElements.add(new TextBuildTitle(CFG.lang.get("AvailableNationalities") + ": " + CFG.core.getCiv(civID).getCivName(), -1, 0, tY, CFG.BUTTON_W, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 4){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_GRAY_NS_HOVER : (this.getIsClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_GRAY_NS : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }

            @Override
            public int getWidthE() {
                return Menu_InGame_ForceMigration.this.getElementW2();
            }
        });
        tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        ArrayList<Map.Entry> list = new ArrayList<Map.Entry>();
        if (!natioMap.isEmpty()) {
            for (Map.Entry entry : natioMap.entrySet()) {
                list.add(entry);
            }
            for (i = 0; i < list.size() - 1; ++i) {
                int maxIndex = i;
                for (int j = i + 1; j < list.size(); ++j) {
                    if ((Integer)((Map.Entry)list.get(j)).getValue() <= (Integer)((Map.Entry)list.get(maxIndex)).getValue()) continue;
                    maxIndex = j;
                }
                Map.Entry temp = (Map.Entry)list.get(i);
                list.set(i, (Map.Entry)list.get(maxIndex));
                list.set(maxIndex, temp);
            }
        }
        if (!list.isEmpty()) {
            for (i = 0; i < list.size(); ++i) {
                Map.Entry entry = (Map.Entry)list.get(i);
                int civId = (Integer)entry.getKey();
                if (CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).playerGD.migrationF.contains(civId)) continue;
                int population = (Integer)entry.getValue();
                menuElements.add(new Button_RelocatePop(i, civId, population, 0, tY, CFG.BUTTON_W * 2){

                    @Override
                    public int getWidthE() {
                        return Menu_InGame_ForceMigration.this.getElementW2();
                    }

                    @Override
                    public void actionElem(int iID) {
                        block4: {
                            try {
                                if (CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).playerGD.migrationF.contains(this.getCurr())) {
                                    for (int a = 0; a < CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).playerGD.migrationF.size(); ++a) {
                                        if (CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).playerGD.migrationF.get(a).intValue() != this.getCurr()) continue;
                                        CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).playerGD.migrationF.remove(a);
                                        CFG.menus.rebuildInGameForceMigration(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId());
                                        CFG.toastM.addM(CFG.lang.get("StopMigration"), CFG.COLOR_HOVER_TITLE);
                                        break block4;
                                    }
                                    break block4;
                                }
                                CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).playerGD.migrationF.add(this.getCurr());
                                CFG.menus.rebuildInGameForceMigration(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId());
                                CFG.toastM.addM(CFG.lang.get("PopulationTransferStatus"), CFG.COLOR_HOVER_TITLE);
                            }
                            catch (Exception exception) {
                                // empty catch block
                            }
                        }
                    }

                    @Override
                    public boolean getCheckboxSt() {
                        return false;
                    }
                });
                tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
            }
        } else {
            menuElements.add(new TextScale(CFG.lang.get("None"), -1, 0, tY, CFG.BUTTON_W, CFG.BUTTON_H * 3 / 4, 0.75f){

                @Override
                public int getWidthE() {
                    return Menu_InGame_ForceMigration.this.getElementW2();
                }

                @Override
                public void actionElem(int iID) {
                    CFG.toastM.addM(CFG.lang.get("None"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                }
            });
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        }
        int tempMenuPosY = IMGManager.getIMG(Images.topBar).getHeight() + CFG.PADD * 2 + CFG.BUTTON_H * 3 / 4;
        this.initMenu(new TitleM_TextSmall(CFG.lang.get("PopulationTransfer"), CFG.BUTTON_H * 3 / 4, true, true){

            @Override
            public void drawT(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                IMGManager.getIMG(Images.dialog_title).draw2O(oSB, nPosX - 2 - Core.PADDING + iTranslateX, nPosY - this.getHeightT() - IMGManager.getIMG(Images.dialog_title).getHeight() - Core.PADDING, nWidth + Core.PADDING * 2 + 4 - IMGManager.getIMG(Images.dialog_title).getWidth(), this.getHeightT() + Core.PADDING);
                IMGManager.getIMG(Images.dialog_title).draw2O(oSB, nPosX + nWidth + Core.PADDING + 2 - IMGManager.getIMG(Images.dialog_title).getWidth() + iTranslateX, nPosY - this.getHeightT() - Core.PADDING - IMGManager.getIMG(Images.dialog_title).getHeight(), IMGManager.getIMG(Images.dialog_title).getWidth(), this.getHeightT() + Core.PADDING, true, false);
                oSB.setColor(new Color(CFG.COLOR_NEGATIVE_2.r, CFG.COLOR_NEGATIVE_2.g, CFG.COLOR_NEGATIVE_2.b, 0.165f));
                IMGManager.getIMG(Images.line32Off1).drawO(oSB, nPosX + iTranslateX, nPosY - this.getHeightT() + 2 - IMGManager.getIMG(Images.line32Off1).getHeight(), nWidth, this.getHeightT() - 2, false, true);
                oSB.setColor(new Color(CFG.COLOR_NEGATIVE_2.r, CFG.COLOR_NEGATIVE_2.g, CFG.COLOR_NEGATIVE_2.b, 0.375f));
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
                CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getFlagC().drawO(oSB, Menu_InGame_ForceMigration.this.getPosX() + CFG.PADD * 2 + iTranslateX, Menu_InGame_ForceMigration.this.getPosY() - this.getHeightT() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getFlagC().getHeight(), CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                IMGManager.getIMG(Images.flagRectSmall).drawO(oSB, Menu_InGame_ForceMigration.this.getPosX() + CFG.PADD * 2 + iTranslateX, Menu_InGame_ForceMigration.this.getPosY() - this.getHeightT() / 2 - CFG.CIV_FLAG_HEIGHT / 2);
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
        oSB.setColor(Color.WHITE);
        IMGManager.getIMG(Images.gameTopEdge).draw2O(oSB, this.getPosX() - 2 - Core.PADDING + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gameTopEdge).getHeight() + iTranslateY, this.getWidthM() - IMGManager.getIMG(Images.gameTopEdge).getWidth() + Core.PADDING * 2 + 4, this.getHeightM() + CFG.PADD + Core.PADDING, false, true);
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

    @Override
    public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    public final void actionEL(int iID) {
        this.getMenuElem(iID).actionElem(iID);
    }

    public final int getW() {
        return this.getWidthM() - 4;
    }

    public final int getElementW() {
        return this.getW() / 2;
    }
}
