package age.of.civilizations2.jakowski.lukasz.Menus.Provinces;

import age.of.civilizations2.jakowski.lukasz.Button.MenuElemUI;
import age.of.civilizations2.jakowski.lukasz.Button.Stats.ButtonStats;
import age.of.civilizations2.jakowski.lukasz.Button.Stats.Button_Stats_Flag_Clip;
import age.of.civilizations2.jakowski.lukasz.Button.Stats.Button_Stats_Title;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Core.Core;
import age.of.civilizations2.jakowski.lukasz.IMGManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.MEHover_2E;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Text;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.Menus.Info.Menu_InGame_ProvInfo;
import age.of.civilizations2.jakowski.lukasz.Renderer;
import age.of.civilizations2.jakowski.lukasz.TextB.Texts.TextScale;
import age.of.civilizations2.jakowski.lukasz.Title.TitleM_TextSmall;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_CivProvinces
extends Menu {
    public static int iSort = 1;
    public static int civID = 0;
    public static int LIMIT = 50;
    public static int ACTIVE_PAGE = 0;
    public static int PAGES = 1;

    public Menu_InGame_CivProvinces() {
        int i;
        ArrayList<MenuElemUI> menuElements = new ArrayList<MenuElemUI>();
        int tPosY = CFG.PADD + CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 4;
        int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
        if (tempWidth > CFG.GAMEWIDTH) {
            tempWidth = CFG.GAMEWIDTH - CFG.PADD * 2;
        }
        if (civID < 0 || civID >= CFG.core.getCivsSize()) {
            civID = 0;
            ACTIVE_PAGE = 0;
            PAGES = 1;
        }
        int tElemHeight = CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 4;
        int tElemHeight2 = Math.max(CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 4, IMGManager.getIMG(Images.flagRect2).getHeight() + CFG.PADD * 4);
        tPosY = tElemHeight;
        menuElements.add(new Button_Stats_Title(CFG.lang.get("Province"), CFG.PADD * 2, 2, 0, CFG.BUTTON_W * 2, tElemHeight){

            @Override
            public int getWidthE() {
                return Menu_InGame_CivProvinces.this.getElementW() * 2;
            }

            @Override
            public Color getColorE(boolean isActive) {
                return iSort == 0 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColorE(isActive);
            }

            @Override
            public void buildElemHover() {
                ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                nData.add(new ME_Hover_2Type_Text(CFG.lang.get("SortBy") + ": "));
                nData.add(new ME_Hover_2Type_Text(this.getTextE(), CFG.COLOR_HOVER_TITLE));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                this.menuElemHover = new ME_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Stats_Title(CFG.lang.get("Population"), CFG.PADD, CFG.PADD * 2 + CFG.BUTTON_W * 5, 0, CFG.BUTTON_W, tElemHeight){

            @Override
            public int getPosXE() {
                return Menu_InGame_CivProvinces.this.getElementW() * 2;
            }

            @Override
            public int getWidthE() {
                return Menu_InGame_CivProvinces.this.getElementW();
            }

            @Override
            public Color getColorE(boolean isActive) {
                return iSort == 1 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColorE(isActive);
            }

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
            }

            @Override
            public void buildElemHover() {
                ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                nData.add(new ME_Hover_2Type_Text(CFG.lang.get("SortBy") + ": "));
                nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Population"), CFG.COLOR_HOVER_TITLE));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                this.menuElemHover = new ME_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Stats_Title(CFG.lang.get("Economy"), CFG.PADD, CFG.PADD * 2 + CFG.BUTTON_W * 5, 0, CFG.BUTTON_W, tElemHeight){

            @Override
            public int getPosXE() {
                return Menu_InGame_CivProvinces.this.getElementW() * 3;
            }

            @Override
            public int getWidthE() {
                return Menu_InGame_CivProvinces.this.getW() - Menu_InGame_CivProvinces.this.getElementW() * 3;
            }

            @Override
            public Color getColorE(boolean isActive) {
                return iSort == 2 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColorE(isActive);
            }

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
            }

            @Override
            public void buildElemHover() {
                ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                nData.add(new ME_Hover_2Type_Text(CFG.lang.get("SortBy") + ": "));
                nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Economy"), CFG.COLOR_HOVER_TITLE));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                this.menuElemHover = new ME_Hover_v2(nElements);
            }
        });
        try {
            int i2;
            int tAddID;
            int i3;
            ArrayList<Integer> tSorted = new ArrayList<Integer>();
            if (iSort == 0 && CFG.getIsDesktop()) {
                int i4;
                for (i4 = 0; i4 < CFG.core.getCiv(civID).getNumOfProvs(); ++i4) {
                    tSorted.add(CFG.core.getCiv(civID).getProvID(i4));
                }
                for (i4 = 0; i4 < tSorted.size() - 1; ++i4) {
                    for (int j = 0; j < tSorted.size() - i4 - 1; ++j) {
                        String name2;
                        int id1 = (Integer)tSorted.get(j);
                        int id2 = (Integer)tSorted.get(j + 1);
                        String name1 = CFG.core.getProv(id1).getProvName();
                        if (name1.compareTo(name2 = CFG.core.getProv(id2).getProvName()) <= 0) continue;
                        tSorted.set(j, id2);
                        tSorted.set(j + 1, id1);
                    }
                }
            } else if (iSort == 2) {
                ArrayList<Integer> tempIDS = new ArrayList<Integer>();
                ArrayList<Integer> tempScore = new ArrayList<Integer>();
                for (i3 = 0; i3 < CFG.core.getCiv(civID).getNumOfProvs(); ++i3) {
                    tempIDS.add(CFG.core.getCiv(civID).getProvID(i3));
                    tempScore.add(CFG.core.getProv(CFG.core.getCiv(civID).getProvID(i3)).getEco());
                }
                tAddID = 0;
                while (!tempIDS.isEmpty()) {
                    tAddID = 0;
                    for (i2 = 1; i2 < tempIDS.size(); ++i2) {
                        if ((Integer)tempScore.get(tAddID) >= (Integer)tempScore.get(i2)) continue;
                        tAddID = i2;
                    }
                    tSorted.add((Integer)tempIDS.get(tAddID));
                    tempIDS.remove(tAddID);
                    tempScore.remove(tAddID);
                }
            } else {
                ArrayList<Integer> tempIDS = new ArrayList<Integer>();
                ArrayList<Integer> tempScore = new ArrayList<Integer>();
                for (i3 = 0; i3 < CFG.core.getCiv(civID).getNumOfProvs(); ++i3) {
                    tempIDS.add(CFG.core.getCiv(civID).getProvID(i3));
                    tempScore.add(CFG.core.getProv(CFG.core.getCiv(civID).getProvID(i3)).getPop().getPops());
                }
                tAddID = 0;
                while (!tempIDS.isEmpty()) {
                    tAddID = 0;
                    for (i2 = 1; i2 < tempIDS.size(); ++i2) {
                        if ((Integer)tempScore.get(tAddID) >= (Integer)tempScore.get(i2)) continue;
                        tAddID = i2;
                    }
                    tSorted.add((Integer)tempIDS.get(tAddID));
                    tempIDS.remove(tAddID);
                    tempScore.remove(tAddID);
                }
            }
            if (tSorted.size() > LIMIT) {
                PAGES = (int)Math.ceil((float)tSorted.size() / (float)LIMIT);
                menuElements.add(new ButtonStats("<<", -1, 0, tPosY, CFG.BUTTON_W, tElemHeight2){

                    @Override
                    public int getWidthE() {
                        return Menu_InGame_CivProvinces.this.getElementW();
                    }

                    @Override
                    public void actionElem(int iID) {
                        ACTIVE_PAGE = ACTIVE_PAGE == 0 ? Math.max(0, PAGES - 1) : --ACTIVE_PAGE;
                        CFG.menus.rebuildInGame_CivProvinces();
                    }
                });
                menuElements.add(new ButtonStats(ACTIVE_PAGE + 1 + " / " + PAGES, -1, 0, tPosY, CFG.BUTTON_W, tElemHeight2){

                    @Override
                    public int getPosXE() {
                        return Menu_InGame_CivProvinces.this.getElementW();
                    }

                    @Override
                    public int getWidthE() {
                        return Menu_InGame_CivProvinces.this.getElementW() * 2;
                    }
                });
                menuElements.add(new ButtonStats(">>", -1, 0, tPosY, CFG.BUTTON_W, tElemHeight2){

                    @Override
                    public void actionElem(int iID) {
                        ACTIVE_PAGE = ACTIVE_PAGE >= PAGES - 1 ? 0 : ++ACTIVE_PAGE;
                        CFG.menus.rebuildInGame_CivProvinces();
                    }

                    @Override
                    public int getPosXE() {
                        return Menu_InGame_CivProvinces.this.getElementW() * 3;
                    }

                    @Override
                    public int getWidthE() {
                        return Menu_InGame_CivProvinces.this.getW() - Menu_InGame_CivProvinces.this.getElementW() * 3;
                    }
                });
                tPosY += tElemHeight2;
            } else {
                ACTIVE_PAGE = 0;
                PAGES = 1;
            }
            int limit2 = LIMIT * ACTIVE_PAGE + LIMIT;
            int iSize = tSorted.size();
            for (i = LIMIT * ACTIVE_PAGE; i < iSize && i < limit2; ++i) {
                if (CFG.FOG_OF_WAR == 2) {
                    if (CFG.getMetProv((Integer)tSorted.get(i))) {
                        menuElements.add(new Button_Stats_Flag_Clip(civID, "" + (i + 1) + ". " + CFG.core.getProv((Integer)tSorted.get(i)).getProvName(), CFG.PADD, 0, tPosY, CFG.BUTTON_W * 2, tElemHeight2){
                            int id;
                            {
                                this.id = 0;
                            }

                            @Override
                            public void actionElem(int iID) {
                                if (this.getCurr() > 0) {
                                    CFG.map.getMpC().centerToProvID(this.getCurr());
                                    CFG.core.setActiveProvID(this.getCurr());
                                } else {
                                    CFG.toastM.addM(CFG.lang.get("Undiscovered"), CFG.COLOR_HOVER_TITLE);
                                }
                            }

                            @Override
                            public int getWidthE() {
                                return Menu_InGame_CivProvinces.this.getElementW() * 2;
                            }

                            @Override
                            public void buildElemHover() {
                                this.menuElemHover = Menu_InGame_ProvInfo.getHoverProvince(this.getCurr());
                            }

                            @Override
                            public Color getColorE(boolean isActive) {
                                return isActive ? CFG.COLOR_TEXT_GRAY_LEFT_NS_ACTIVE : (this.getIsClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_GRAY_LEFT_NS_HOVER : CFG.COLOR_TEXT_GRAY_LEFT_NS) : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE);
                            }

                            @Override
                            public void setMax(int iMax) {
                                this.id = iMax;
                            }

                            @Override
                            public int getCurr() {
                                return this.id;
                            }
                        });
                        ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setMax((Integer)tSorted.get(i));
                    } else {
                        menuElements.add(new Button_Stats_Flag_Clip(-1, "" + (i + 1) + ". " + CFG.lang.get("Undiscovered"), CFG.PADD, 0, tPosY, CFG.BUTTON_W * 2, tElemHeight2){

                            @Override
                            public void actionElem(int iID) {
                                if (this.getCurr() > 0) {
                                    CFG.map.getMpC().centerToProvID(this.getCurr());
                                    CFG.core.setActiveProvID(this.getCurr());
                                } else {
                                    CFG.toastM.addM(CFG.lang.get("Undiscovered"), CFG.COLOR_HOVER_TITLE);
                                }
                            }

                            @Override
                            public int getWidthE() {
                                return Menu_InGame_CivProvinces.this.getElementW() * 2;
                            }

                            @Override
                            public Color getColorE(boolean isActive) {
                                return isActive ? CFG.COLOR_TEXT_GRAY_LEFT_NS_ACTIVE : (this.getIsClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_GRAY_LEFT_NS_HOVER : CFG.COLOR_TEXT_GRAY_LEFT_NS) : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE);
                            }
                        });
                    }
                } else {
                    menuElements.add(new Button_Stats_Flag_Clip(civID, "" + (i + 1) + ". " + CFG.core.getProv((Integer)tSorted.get(i)).getProvName(), CFG.PADD, 0, tPosY, CFG.BUTTON_W * 2, tElemHeight2){

                        @Override
                        public void actionElem(int iID) {
                            if (this.getCurr() > 0) {
                                CFG.map.getMpC().centerToProvID(this.getCurr());
                                CFG.core.setActiveProvID(this.getCurr());
                            } else {
                                CFG.toastM.addM(CFG.lang.get("Undiscovered"), CFG.COLOR_HOVER_TITLE);
                            }
                        }

                        @Override
                        public int getWidthE() {
                            return Menu_InGame_CivProvinces.this.getElementW() * 2;
                        }

                        @Override
                        public void buildElemHover() {
                            this.menuElemHover = Menu_InGame_ProvInfo.getHoverProvince(this.getCurr());
                        }

                        @Override
                        public Color getColorE(boolean isActive) {
                            return isActive ? CFG.COLOR_TEXT_GRAY_LEFT_NS_ACTIVE : (this.getIsClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_GRAY_LEFT_NS_HOVER : CFG.COLOR_TEXT_GRAY_LEFT_NS) : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE);
                        }

                        @Override
                        public void setMax(int iMax) {
                            this.id = iMax;
                        }

                        @Override
                        public int getCurr() {
                            return this.id;
                        }
                    });
                    ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setMax((Integer)tSorted.get(i));
                }
                menuElements.add(new ButtonStats(CFG.getNumberWthSpaces("" + CFG.core.getProv((Integer)tSorted.get(i)).getPop().getPops()), CFG.PADD, CFG.PADD * 2 + CFG.BUTTON_W * 5, tPosY, CFG.BUTTON_W, tElemHeight2){

                    @Override
                    public int getPosXE() {
                        return Menu_InGame_CivProvinces.this.getElementW() * 2;
                    }

                    @Override
                    public int getWidthE() {
                        return Menu_InGame_CivProvinces.this.getElementW();
                    }

                    @Override
                    public void buildElemHover() {
                        this.menuElemHover = Menu_InGame_ProvInfo.getHoverPopulation(this.getCurr());
                    }

                    @Override
                    public Color getColorE(boolean isActive) {
                        return isActive ? CFG.COLOR_POPULATION_ACTIVE : (this.getIsHovered() ? CFG.COLOR_POPULATION_HOVER : CFG.COLOR_POPULATION);
                    }

                    @Override
                    public void setMax(int iMax) {
                        this.id = iMax;
                    }
                });
                ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setMax((Integer)tSorted.get(i));
                menuElements.add(new ButtonStats(CFG.getNumberWthSpaces("" + CFG.core.getProv((Integer)tSorted.get(i)).getEco()), CFG.PADD, CFG.PADD * 2 + CFG.BUTTON_W * 5, tPosY, CFG.BUTTON_W, tElemHeight2){

                    @Override
                    public int getPosXE() {
                        return Menu_InGame_CivProvinces.this.getElementW() * 3;
                    }

                    @Override
                    public int getWidthE() {
                        return Menu_InGame_CivProvinces.this.getW() - Menu_InGame_CivProvinces.this.getElementW() * 3;
                    }

                    @Override
                    public void buildElemHover() {
                        this.menuElemHover = Menu_InGame_ProvInfo.getHoverEconomy(this.getCurr());
                    }

                    @Override
                    public Color getColorE(boolean isActive) {
                        return isActive ? CFG.COLOR_ECONOMY_ACTIVE : (this.getIsHovered() ? CFG.COLOR_ECONOMY_HOVER : CFG.COLOR_ECONOMY);
                    }

                    @Override
                    public void setMax(int iMax) {
                        this.id = iMax;
                    }
                });
                ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setMax((Integer)tSorted.get(i));
                tPosY += tElemHeight2;
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
        if (menuElements.size() == 3) {
            menuElements.add(new TextScale(CFG.lang.get("None"), -1, CFG.PADD, tPosY, tempWidth - CFG.PADD * 2, CFG.BUTTON_H * 3 / 4, 0.75f){

                @Override
                public int getWidthE() {
                    return Menu_InGame_CivProvinces.this.getW() - CFG.PADD * 2;
                }
            });
            ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setClickable(false);
            tPosY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        }
        int tempMenuPosY = IMGManager.getIMG(Images.topFlagFrame).getHeight() + CFG.PADD * 4 + CFG.BUTTON_H * 3 / 4 + CFG.PADD * 2;
        this.initMenu(new TitleM_TextSmall(null, CFG.BUTTON_H * 3 / 4, true, true){

            @Override
            public void drawT(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                IMGManager.getIMG(Images.gameTopEdge).draw2O(oSB, nPosX - Core.PADDING + iTranslateX, nPosY - this.getHeightT() - Core.PADDING - IMGManager.getIMG(Images.gameTopEdge).getHeight(), nWidth - IMGManager.getIMG(Images.gameTopEdge).getWidth() + Core.PADDING * 2, this.getHeightT() + Core.PADDING);
                IMGManager.getIMG(Images.gameTopEdge).draw2O(oSB, nPosX + Core.PADDING + nWidth - IMGManager.getIMG(Images.gameTopEdge).getWidth() + iTranslateX, nPosY - Core.PADDING - this.getHeightT() - IMGManager.getIMG(Images.gameTopEdge).getHeight(), IMGManager.getIMG(Images.gameTopEdge).getWidth(), this.getHeightT() + Core.PADDING, true, false);
                oSB.setColor(new Color((float)CFG.core.getCiv(civID).getR() / 255.0f, (float)CFG.core.getCiv(civID).getG() / 255.0f, (float)CFG.core.getCiv(civID).getB() / 255.0f, 0.165f));
                IMGManager.getIMG(Images.line32Off1).drawO(oSB, nPosX + 2 + iTranslateX, nPosY - this.getHeightT() + 2 - IMGManager.getIMG(Images.line32Off1).getHeight(), nWidth - 4, this.getHeightT() - 2, false, true);
                oSB.setColor(new Color((float)CFG.core.getCiv(civID).getR() / 255.0f, (float)CFG.core.getCiv(civID).getG() / 255.0f, (float)CFG.core.getCiv(civID).getB() / 255.0f, 0.375f));
                IMGManager.getIMG(Images.gradient).drawO(oSB, nPosX + 2 + iTranslateX, nPosY - this.getHeightT() * 2 / 3 - IMGManager.getIMG(Images.gradient).getHeight(), nWidth - 4, this.getHeightT() * 2 / 3, false, true);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
                IMGManager.getIMG(Images.gradient).drawO(oSB, nPosX + 2 + iTranslateX, nPosY - CFG.PADD - IMGManager.getIMG(Images.gradient).getHeight(), nWidth - 4, CFG.PADD, false, true);
                oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
                IMGManager.getIMG(Images.pix255).drawO(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - IMGManager.getIMG(Images.pix255).getHeight(), nWidth - 4, 1);
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45f));
                IMGManager.getIMG(Images.sliderGradient).drawO(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - IMGManager.getIMG(Images.sliderGradient).getHeight(), (nWidth - 4) / 2, 1);
                IMGManager.getIMG(Images.sliderGradient).drawO(oSB, nPosX + 2 + (nWidth - 4) - (nWidth - 4) / 2 + iTranslateX, nPosY - 1 - IMGManager.getIMG(Images.sliderGradient).getHeight(), (nWidth - 4) / 2, 1, true, false);
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.425f));
                IMGManager.getIMG(Images.sliderGradient).drawO(oSB, nPosX + CFG.PADD * 2 + iTranslateX, nPosY + 1 - this.getHeightT() / 2 - IMGManager.getIMG(Images.sliderGradient).getHeight(), (nWidth - CFG.PADD * 6 - this.getTextWidth()) / 2, 1, true, false);
                IMGManager.getIMG(Images.sliderGradient).drawO(oSB, nPosX + nWidth / 2 + CFG.PADD + this.getTextWidth() / 2 + iTranslateX, nPosY + 1 - this.getHeightT() / 2 - IMGManager.getIMG(Images.sliderGradient).getHeight(), (nWidth - CFG.PADD * 6 - this.getTextWidth()) / 2, 1);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.325f));
                IMGManager.getIMG(Images.sliderGradient).drawO(oSB, nPosX + CFG.PADD * 2 + iTranslateX, nPosY + 2 - this.getHeightT() / 2 - IMGManager.getIMG(Images.sliderGradient).getHeight(), (nWidth - CFG.PADD * 6 - this.getTextWidth()) / 2, 1, true, false);
                IMGManager.getIMG(Images.sliderGradient).drawO(oSB, nPosX + nWidth / 2 + CFG.PADD + this.getTextWidth() / 2 + iTranslateX, nPosY + 2 - this.getHeightT() / 2 - IMGManager.getIMG(Images.sliderGradient).getHeight(), (nWidth - CFG.PADD * 6 - this.getTextWidth()) / 2, 1);
                IMGManager.getIMG(Images.sliderGradient).drawO(oSB, nPosX + CFG.PADD * 2 + iTranslateX, nPosY - this.getHeightT() / 2 - IMGManager.getIMG(Images.sliderGradient).getHeight(), (nWidth - CFG.PADD * 6 - this.getTextWidth()) / 2, 1, true, false);
                IMGManager.getIMG(Images.sliderGradient).drawO(oSB, nPosX + nWidth / 2 + CFG.PADD + this.getTextWidth() / 2 + iTranslateX, nPosY - this.getHeightT() / 2 - IMGManager.getIMG(Images.sliderGradient).getHeight(), (nWidth - CFG.PADD * 6 - this.getTextWidth()) / 2, 1);
                oSB.setColor(Color.WHITE);
                Renderer.drawText(oSB, CFG.FONT_BOLD_SMALL, this.getText(), nPosX + (nWidth - this.getTextWidth()) / 2 + iTranslateX, 2 + nPosY - this.getHeightT() + (this.getHeightT() - this.getTextHeight()) / 2, Color.WHITE);
            }
        }, CFG.GAMEWIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD + tempMenuPosY > CFG.GAMEHEIGHT - CFG.BUTTON_H - CFG.PADD * 2 ? Math.max(CFG.GAMEHEIGHT - CFG.BUTTON_H - CFG.PADD * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 2) * 6) : ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, menuElements, true, true);
        this.updateLang();
        for (i = 0; i < this.getMenuElemsSize(); ++i) {
            this.getMenuElem(i).setCurr(i / 3 % 2);
        }
    }

    @Override
    public void updateLang() {
        try {
            this.getTitleM().setText(CFG.core.getCiv(civID).getCivName() + " - " + CFG.lang.get("Provinces") + ": " + CFG.core.getCiv(civID).getNumOfProvs() + "");
            this.getMenuElem(0).setTextE(CFG.lang.get("Province"));
            this.getMenuElem(1).setTextE(CFG.lang.get("Population"));
            this.getMenuElem(2).setTextE(CFG.lang.get("Economy"));
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        oSB.setColor(Color.WHITE);
        IMGManager.getIMG(Images.gameTopEdge).draw2O(oSB, this.getPosX() - Core.PADDING + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gameTopEdge).getHeight() + iTranslateY, this.getWidthM() - IMGManager.getIMG(Images.gameTopEdge).getWidth() + Core.PADDING * 2, this.getHeightM() + 2 + Core.PADDING, false, true);
        IMGManager.getIMG(Images.gameTopEdge).draw2O(oSB, this.getPosX() + Core.PADDING + this.getWidthM() - IMGManager.getIMG(Images.gameTopEdge).getWidth() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gameTopEdge).getHeight() + iTranslateY, IMGManager.getIMG(Images.gameTopEdge).getWidth(), this.getHeightM() + 2 + Core.PADDING, true, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
        IMGManager.getIMG(Images.gradient).drawO(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gradient).getHeight() + iTranslateY, this.getWidthM() - 4, this.getHeightM() / 4);
        IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.pix255).getHeight() + iTranslateY, this.getWidthM() - 4, 1);
        oSB.setColor(Color.WHITE);
        this.beginClipM(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        this.drawMenuM(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.65f));
        IMGManager.getIMG(Images.line32Off1).drawO(oSB, this.getPosX() + this.getMenuElem(0).getPosXE() + iTranslateX, this.getMenuPosY() - 1 + this.getMenuElem(0).getPosY() - IMGManager.getIMG(Images.line32Off1).getHeight() + this.getMenuElem(0).getHeightE() + iTranslateY, this.getWidthM() - 4, 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
        IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosX() + this.getMenuElem(0).getPosXE() + iTranslateX, this.getMenuPosY() + this.getMenuElem(0).getPosY() - IMGManager.getIMG(Images.pix255).getHeight() + this.getMenuElem(0).getHeightE() + iTranslateY, this.getWidthM() - 4, 1);
        oSB.setColor(Color.WHITE);
        this.endClipM(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        try {
            if (CFG.core.getActiveProvID() >= 0 && civID != CFG.core.getProv(CFG.core.getActiveProvID()).getCivId()) {
                PAGES = 1;
                ACTIVE_PAGE = 0;
                civID = CFG.core.getProv(CFG.core.getActiveProvID()).getCivId();
                CFG.menus.rebuildInGame_CivProvinces();
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

    @Override
    public final void actionEL(int iID) {
        switch (iID) {
            case 0: {
                if (iSort != iID) {
                    iSort = iID;
                    CFG.menus.rebuildInGame_CivProvinces();
                }
                return;
            }
            case 1: {
                if (iSort != iID) {
                    iSort = iID;
                    CFG.menus.rebuildInGame_CivProvinces();
                }
                return;
            }
            case 2: {
                if (iSort != iID) {
                    iSort = iID;
                    CFG.menus.rebuildInGame_CivProvinces();
                }
                return;
            }
        }
        super.actionEL(iID);
    }

    public final int getW() {
        return this.getWidthM();
    }

    public final int getElementW() {
        return this.getW() / 4;
    }
}
