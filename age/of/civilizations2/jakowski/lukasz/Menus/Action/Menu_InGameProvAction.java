package age.of.civilizations2.jakowski.lukasz.Menus.Action;

import age.of.civilizations2.jakowski.lukasz.Button.Game.Button_Game;
import age.of.civilizations2.jakowski.lukasz.Button.Game.Button_Game_ExtraText;
import age.of.civilizations2.jakowski.lukasz.Button.MenuElemUI;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Civilization;
import age.of.civilizations2.jakowski.lukasz.Core.Core;
import age.of.civilizations2.jakowski.lukasz.GameCalendar;
import age.of.civilizations2.jakowski.lukasz.GameValues.GameValues;
import age.of.civilizations2.jakowski.lukasz.IMGManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MapA.BuildingsManager;
import age.of.civilizations2.jakowski.lukasz.MapA.Mode.MapModesManager;
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
import age.of.civilizations2.jakowski.lukasz.Menus.Civilization.Menu_InGame_Civ;
import age.of.civilizations2.jakowski.lukasz.Menus.Civilization.Menu_InGame_Civ_Decisions;
import age.of.civilizations2.jakowski.lukasz.Menus.ProvinceM.More.Menu_InGame_Province_More;
import age.of.civilizations2.jakowski.lukasz.Menus.ZRest.Menu_InGame_2;
import age.of.civilizations2.jakowski.lukasz.Menus.Z_Rest.Menu_InGame_Plunder;
import age.of.civilizations2.jakowski.lukasz.RTS;
import age.of.civilizations2.jakowski.lukasz.Renderer;
import age.of.civilizations2.jakowski.lukasz.SFXManager;
import age.of.civilizations2.jakowski.lukasz.TouchManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGameProvAction
extends Menu {
    private String sMigration = "";
    private int iMigrationWidth = -1;
    public static int numOfUnits = 0;
    public static String sUnits = "";
    public static int textHeight = CFG.TEXT_HEIGHT_DEFAULT;
    public static int textWidth = 10;
    public static int armyIconWidth = 0;

    @Override
    public int getPosX() {
        if (CFG.menus.getInGame_ProvincemMore_Visible() && Menu_InGame_Province_More.toTheBottom) {
            return super.getPosX() + CFG.CIV_INFO_MENU_WIDTH + Menu_InGame_Province_More.getExtraW() + Menu_InGame_Province_More.extraPosX;
        }
        if (CFG.menus.getInGame_Civ_Info().getVisibleM() && CFG.menus.getInGame_Civ_Info_Decisions().getVisibleM() && Menu_InGame_Civ_Decisions.toTheBottom) {
            return super.getPosX() + Menu_InGame_Civ.getMenuCivInfoWidth() + Menu_InGame_Civ_Decisions.extraPosX;
        }
        return super.getPosX();
    }

    @Override
    public int getMenuPosX() {
        if (CFG.menus.getInGame_ProvincemMore_Visible() && Menu_InGame_Province_More.toTheBottom) {
            return super.getMenuPosX() + CFG.CIV_INFO_MENU_WIDTH + Menu_InGame_Province_More.getExtraW() + Menu_InGame_Province_More.extraPosX;
        }
        if (CFG.menus.getInGame_Civ_Info().getVisibleM() && CFG.menus.getInGame_Civ_Info_Decisions().getVisibleM() && Menu_InGame_Civ_Decisions.toTheBottom) {
            return super.getMenuPosX() + Menu_InGame_Civ.getMenuCivInfoWidth() + Menu_InGame_Civ_Decisions.extraPosX;
        }
        return super.getMenuPosX();
    }

    public Menu_InGameProvAction() {
        ArrayList<MenuElemUI> menuElements = new ArrayList<MenuElemUI>();
        menuElements.add(new Button_Game(null, -1, CFG.PADD, CFG.PADD, true){
            public String sKey;
            public int iKeyWidth;

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
                this.sKey = "Q";
                CFG.glyphLay.setText(CFG.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sKey);
                this.iKeyWidth = (int)CFG.glyphLay.width;
            }

            @Override
            public void drawTextE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                Renderer.drawTextWithShadowAlpha(oSB, CFG.FONT_REGULAR_SMALL, this.sKey, this.getPosXE() + this.getWidthE() - CFG.PADD - this.iKeyWidth + iTranslateX, this.getPosY() + CFG.PADD + iTranslateY, this.getColorE(isActive));
                super.drawTextE(oSB, iTranslateX, iTranslateY, isActive);
            }

            @Override
            public int getSFXElem() {
                return SFXManager.SFX_ACTION_MOVE;
            }

            @Override
            public boolean getVisibleE() {
                try {
                    return Menu_InGameProvAction.canMove();
                }
                catch (Exception ex) {
                    return super.getVisibleE();
                }
            }

            @Override
            public void buildElemHover() {
                if (CFG.getIsDesktop()) {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("MoveArmy"), CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(Images.diploArmy, CFG.PADD, 0));
                    nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Space());
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Shortcut") + ": "));
                    nData.add(new ME_Hover_2Type_Text("Q", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nData.add(new ME_Hover_2Type_Image(Images.key, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Text("U", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nData.add(new ME_Hover_2Type_Image(Images.key, CFG.PADD, CFG.PADD));
                    nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Cancel") + ": " + CFG.lang.get("MoveUnits")));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    this.menuElemHover = new ME_Hover_v2(nElements);
                } else {
                    this.menuElemHover = null;
                }
            }

            @Override
            public void drawButtonBGE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawButtonBGE(oSB, iTranslateX, iTranslateY, isActive);
                if (isActive) {
                    oSB.setColor(Color.WHITE);
                } else if (this.getIsHovered()) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.75f));
                } else {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.275f));
                }
                IMGManager.getIMG(Images.actMove).drawO(oSB, this.getPosXE() + this.getWidthE() - IMGManager.getIMG(Images.actMove).getWidth() - CFG.PADD + iTranslateX, this.getPosY() + this.getHeightE() - IMGManager.getIMG(Images.actMove).getHeight() - CFG.PADD + iTranslateY);
                oSB.setColor(Color.WHITE);
            }
        });
        menuElements.add(new Button_Game_ExtraText(null, -1, CFG.PADD * 2 + CFG.BUTTON_W, CFG.PADD, true){
            public String sKey;
            public int iKeyWidth;

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
                this.sKey = "W";
                CFG.glyphLay.setText(CFG.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sKey);
                this.iKeyWidth = (int)CFG.glyphLay.width;
            }

            @Override
            public void drawTextE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                Renderer.drawTextWithShadowAlpha(oSB, CFG.FONT_REGULAR_SMALL, this.sKey, this.getPosXE() + this.getWidthE() - CFG.PADD - this.iKeyWidth + iTranslateX, this.getPosY() + CFG.PADD + iTranslateY, this.getColorE(isActive));
                super.drawTextE(oSB, iTranslateX, iTranslateY, isActive);
            }

            @Override
            public void updateText() {
                if (this.lastActiveProvinceID != CFG.core.getActiveProvID()) {
                    this.lastActiveProvinceID = CFG.core.getActiveProvID();
                    if (CFG.core.getActiveProvID() < 0) {
                        this.textB = "";
                        this.iTextBWidth = 0;
                    } else {
                        this.textB = CFG.getNumber_SHORT(CFG.gameAction.gMARY(CFG.core.getActiveProvID(), CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()));
                        CFG.glyphLay.setText(CFG.fontMain.get(this.fontID2), this.textB);
                        this.iTextBWidth = (int)CFG.glyphLay.width;
                    }
                }
            }

            @Override
            public void actionElemPPM() {
                if (Menu_InGameProvAction.canRecruit() && CFG.core.getActiveProvID() >= 0) {
                    if (CFG.core.getProv(CFG.core.getActiveProvID()).getTrueOwnerOfProv() == CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) {
                        if (CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getMovemPoints() < CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).getIdeology()).COST_OF_RECRUIT) {
                            CFG.menus.setVisible_InGame_ActionInfo_NoMovementPoints();
                        } else if (CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getGold() < (long)CFG.getCostOfRecruitArmyMoney_Instantly(CFG.core.getActiveProvID())) {
                            CFG.menus.setVisible_InGame_ActionInfo_TreasuryIsEmpty();
                        } else {
                            CFG.core.resetChooseProvinceData();
                            CFG.menus.setVisible_InGame_ProvinceAction(false);
                            Menu_InGameProvAction.hideMenus();
                            CFG.gameAction.updateRecruitSlider_Instantly();
                            CFG.menus.setVisible_InGame_ProvinceRecruitInstantly(true);
                            CFG.menus.setVisible_InGame_ActionInfo_RecruitInstantly();
                        }
                    } else {
                        CFG.menus.setVisible_InGame_ActionInfo_RecruitOccupied();
                    }
                }
            }

            @Override
            public void buildElemHover() {
                ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("RecruitArmy"), CFG.COLOR_HOVER_TITLE));
                nData.add(new ME_Hover_2Type_Image_Big(Images.diploArmy, CFG.PADD, 0));
                nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.PADD, 0));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Text(CFG.lang.get("RecruitablePopulation") + ": "));
                nData.add(new ME_Hover_2Type_Text("" + CFG.getNumberWthSpaces("" + CFG.gameAction.gMARY(CFG.core.getActiveProvID(), CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nData.add(new ME_Hover_2Type_Image(Images.diploArmy, CFG.PADD, 0));
                nData.add(new ME_Hover_2Type_Flag(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.PADD, 0));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Space());
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                if (CFG.core.getActiveProvID() >= 0 && CFG.core.getProv(CFG.core.getActiveProvID()).getTrueOwnerOfProv() != CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) {
                    nData.add(new ME_Hover_2Type_TextDesc(CFG.lang.get("RecruitOccupiedDesc"), CFG.COLOR_NEGATIVE_2));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Space());
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                }
                if (CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getMovemPoints() < CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).getIdeology()).COST_OF_RECRUIT) {
                    nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Cost") + ": "));
                    nData.add(new ME_Hover_2Type_Text("" + (float)CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).getIdeology()).COST_OF_RECRUIT / 10.0f, CFG.COLOR_NEGATIVE_2));
                    nData.add(new ME_Hover_2Type_Image(Images.topMovementPoints, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                }
                if (CFG.getIsDesktop()) {
                    nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Shortcut") + ": "));
                    nData.add(new ME_Hover_2Type_Text("W", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nData.add(new ME_Hover_2Type_Image(Images.key, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Text("A", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nData.add(new ME_Hover_2Type_Image(Images.key, CFG.PADD, CFG.PADD));
                    nData.add(new ME_Hover_2Type_Text("25%"));
                    nData.add(new ME_Hover_2Type_Image(Images.diploArmy, CFG.PADD, CFG.PADD));
                    nData.add(new ME_Hover_2Type_Text("S", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nData.add(new ME_Hover_2Type_Image(Images.key, CFG.PADD, CFG.PADD));
                    nData.add(new ME_Hover_2Type_Text("50%"));
                    nData.add(new ME_Hover_2Type_Image(Images.diploArmy, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Text("D", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nData.add(new ME_Hover_2Type_Image(Images.key, CFG.PADD, CFG.PADD));
                    nData.add(new ME_Hover_2Type_Text("75%"));
                    nData.add(new ME_Hover_2Type_Image(Images.diploArmy, CFG.PADD, CFG.PADD));
                    nData.add(new ME_Hover_2Type_Text("F", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nData.add(new ME_Hover_2Type_Image(Images.key, CFG.PADD, CFG.PADD));
                    nData.add(new ME_Hover_2Type_Text("100%"));
                    nData.add(new ME_Hover_2Type_Image(Images.diploArmy, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Text("G", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nData.add(new ME_Hover_2Type_Image(Images.key, CFG.PADD, CFG.PADD));
                    nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Cancel")));
                    nData.add(new ME_Hover_2Type_Image(Images.diploArmy, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    this.menuElemHover = new ME_Hover_v2(nElements);
                } else {
                    this.menuElemHover = nElements.size() > 0 ? new ME_Hover_v2(nElements) : null;
                }
            }

            @Override
            public void drawButtonBGE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawButtonBGE(oSB, iTranslateX, iTranslateY, isActive);
                if (isActive) {
                    oSB.setColor(Color.WHITE);
                } else if (this.getIsHovered()) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.75f));
                } else {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.575f));
                }
                IMGManager.getIMG(Images.actRecruit).drawO(oSB, this.getPosXE() + this.getWidthE() - IMGManager.getIMG(Images.actRecruit).getWidth() - CFG.PADD + iTranslateX, this.getPosY() + this.getHeightE() - IMGManager.getIMG(Images.actRecruit).getHeight() - CFG.PADD + iTranslateY);
                oSB.setColor(Color.WHITE);
            }

            @Override
            public int getSFXElem() {
                try {
                    return this.getIsClickable() ? SFXManager.SFX_RECRUIT : super.getSFXElem();
                }
                catch (Exception ex) {
                    return super.getSFXElem();
                }
            }

            @Override
            public boolean getIsClickable() {
                try {
                    if (CFG.core.getActiveProvID() >= 0) {
                        return CFG.core.getProv(CFG.core.getActiveProvID()).getTrueOwnerOfProv() == CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId() || CFG.core.getProv(CFG.core.getActiveProvID()).getPop().getPopulationOfCivID(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) > 1;
                    }
                    return super.getIsClickable();
                }
                catch (Exception ex) {
                    return super.getIsClickable();
                }
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.PADD * 3 + CFG.BUTTON_W * 2, CFG.PADD, true){
            public String sKey;
            public int iKeyWidth;

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
                this.sKey = "E";
                CFG.glyphLay.setText(CFG.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sKey);
                this.iKeyWidth = (int)CFG.glyphLay.width;
            }

            @Override
            public void drawTextE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                Renderer.drawTextWithShadowAlpha(oSB, CFG.FONT_REGULAR_SMALL, this.sKey, this.getPosXE() + this.getWidthE() - CFG.PADD - this.iKeyWidth + iTranslateX, this.getPosY() + CFG.PADD + iTranslateY, this.getColorE(isActive));
                super.drawTextE(oSB, iTranslateX, iTranslateY, isActive);
            }

            @Override
            public boolean getVisibleE() {
                try {
                    return Menu_InGameProvAction.canMore();
                }
                catch (Exception ex) {
                    return super.getVisibleE();
                }
            }

            @Override
            public void drawButtonBGE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawButtonBGE(oSB, iTranslateX, iTranslateY, isActive);
                if (isActive) {
                    oSB.setColor(Color.WHITE);
                } else if (this.getIsHovered()) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.75f));
                } else {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.575f));
                }
                IMGManager.getIMG(Images.actMore).drawO(oSB, this.getPosXE() + this.getWidthE() - IMGManager.getIMG(Images.actMore).getWidth() - CFG.PADD + iTranslateX, this.getPosY() + this.getHeightE() - IMGManager.getIMG(Images.actMore).getHeight() - CFG.PADD + iTranslateY);
                oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElemHover() {
                if (CFG.getIsDesktop()) {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("MoreActions"), CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(Images.buildAll, CFG.PADD, 0));
                    nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Space());
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Shortcut") + ": "));
                    nData.add(new ME_Hover_2Type_Text("E", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nData.add(new ME_Hover_2Type_Image(Images.key, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    this.menuElemHover = new ME_Hover_v2(nElements);
                } else {
                    this.menuElemHover = null;
                }
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.PADD * 4 + CFG.BUTTON_W * 3, CFG.PADD, true){
            public String sKey;
            public int iKeyWidth;

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
                this.sKey = "R";
                CFG.glyphLay.setText(CFG.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sKey);
                this.iKeyWidth = (int)CFG.glyphLay.width;
            }

            @Override
            public void drawTextE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                Renderer.drawTextWithShadowAlpha(oSB, CFG.FONT_REGULAR_SMALL, this.sKey, this.getPosXE() + this.getWidthE() - CFG.PADD - this.iKeyWidth + iTranslateX, this.getPosY() + CFG.PADD + iTranslateY, this.getColorE(isActive));
                super.drawTextE(oSB, iTranslateX, iTranslateY, isActive);
            }

            @Override
            public int getPosXE() {
                return Menu_InGameProvAction.this.getMenuElem(1).getVisibleE() ? (Menu_InGameProvAction.this.getMenuElem(2).getVisibleE() ? super.getPosXE() : Menu_InGameProvAction.this.getMenuElem(1).getPosXE() + Menu_InGameProvAction.this.getMenuElem(1).getWidthE() + CFG.PADD) : Menu_InGameProvAction.this.getMenuElem(0).getPosXE() + Menu_InGameProvAction.this.getMenuElem(0).getWidthE() + CFG.PADD;
            }

            @Override
            public void buildElemHover() {
                if (CFG.getIsDesktop()) {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("DisbandArmy"), CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(Images.diploArmy, CFG.PADD, 0));
                    nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Space());
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Shortcut") + ": "));
                    nData.add(new ME_Hover_2Type_Text("R", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nData.add(new ME_Hover_2Type_Image(Images.key, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    this.menuElemHover = new ME_Hover_v2(nElements);
                } else {
                    this.menuElemHover = null;
                }
            }

            @Override
            public void drawButtonBGE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawButtonBGE(oSB, iTranslateX, iTranslateY, isActive);
                if (isActive) {
                    oSB.setColor(Color.WHITE);
                } else if (this.getIsHovered()) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.75f));
                } else {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.575f));
                }
                IMGManager.getIMG(Images.actRecruit).drawO(oSB, this.getPosXE() + this.getWidthE() - IMGManager.getIMG(Images.actRecruit).getWidth() - CFG.PADD + iTranslateX, this.getPosY() + this.getHeightE() - IMGManager.getIMG(Images.actRecruit).getHeight() - CFG.PADD + iTranslateY);
                oSB.setColor(Color.WHITE);
            }

            @Override
            public boolean getIsClickable() {
                return CFG.core.getActiveProvID() >= 0 && CFG.core.getProv(CFG.core.getActiveProvID()).getArmyCivID1(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) > 0;
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.PADD * 5 + CFG.BUTTON_W * 4, CFG.PADD, true){
            public String sKey;
            public int iKeyWidth;

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
                this.sKey = "T";
                CFG.glyphLay.setText(CFG.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sKey);
                this.iKeyWidth = (int)CFG.glyphLay.width;
            }

            @Override
            public void drawTextE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                Renderer.drawTextWithShadowAlpha(oSB, CFG.FONT_REGULAR_SMALL, this.sKey, this.getPosXE() + this.getWidthE() - CFG.PADD - this.iKeyWidth + iTranslateX, this.getPosY() + CFG.PADD + iTranslateY, this.getColorE(isActive));
                super.drawTextE(oSB, iTranslateX, iTranslateY, isActive);
            }

            @Override
            public int getSFXElem() {
                return SFXManager.SFX_ACTION_MOVE;
            }

            @Override
            public int getPosXE() {
                return Menu_InGameProvAction.this.getMenuElem(3).getVisibleE() ? Menu_InGameProvAction.this.getMenuElem(3).getPosXE() + Menu_InGameProvAction.this.getMenuElem(3).getWidthE() + CFG.PADD : (Menu_InGameProvAction.this.getMenuElem(2).getVisibleE() ? Menu_InGameProvAction.this.getMenuElem(2).getPosXE() + Menu_InGameProvAction.this.getMenuElem(2).getWidthE() + CFG.PADD : Menu_InGameProvAction.this.getMenuElem(0).getPosXE() + Menu_InGameProvAction.this.getMenuElem(0).getWidthE() + CFG.PADD);
            }

            @Override
            public boolean getVisibleE() {
                try {
                    return Menu_InGameProvAction.canMove();
                }
                catch (Exception ex) {
                    return super.getVisibleE();
                }
            }

            @Override
            public void drawButtonBGE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawButtonBGE(oSB, iTranslateX, iTranslateY, isActive);
                if (isActive) {
                    oSB.setColor(Color.WHITE);
                } else if (this.getIsHovered()) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.75f));
                } else {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.275f));
                }
                IMGManager.getIMG(Images.actMoveTo).drawO(oSB, this.getPosXE() + this.getWidthE() - IMGManager.getIMG(Images.actMoveTo).getWidth() - CFG.PADD + iTranslateX, this.getPosY() + this.getHeightE() - IMGManager.getIMG(Images.actMoveTo).getHeight() - CFG.PADD + iTranslateY);
                oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElemHover() {
                if (CFG.getIsDesktop()) {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("MoveTo"), CFG.COLOR_HOVER_TITLE));
                    nData.add(new ME_Hover_2Type_Image_Big(Images.diploArmyMove, CFG.PADD, 0));
                    nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Space());
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Shortcut") + ": "));
                    nData.add(new ME_Hover_2Type_Text("T", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nData.add(new ME_Hover_2Type_Image(Images.key, CFG.PADD, 0));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    nData.add(new ME_Hover_2Type_Text("U", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nData.add(new ME_Hover_2Type_Image(Images.key, CFG.PADD, CFG.PADD));
                    nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Cancel") + ": " + CFG.lang.get("MoveUnits")));
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    this.menuElemHover = new ME_Hover_v2(nElements);
                } else {
                    this.menuElemHover = null;
                }
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.PADD * 5 + CFG.BUTTON_W * 4, CFG.PADD, true){

            @Override
            public int getSFXElem() {
                return SFXManager.SFX_ACTION_MOVE;
            }

            @Override
            public int getPosXE() {
                return Menu_InGameProvAction.this.getMenuElem(4).getPosXE() + Menu_InGameProvAction.this.getMenuElem(4).getWidthE() + CFG.PADD;
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
            public int getTextWidthU() {
                return this.getIsClickable() ? super.getTextWidthU() + CFG.PADD + CFG.CIV_FLAG_WIDTH : Math.max(super.getTextWidthU() + CFG.PADD + CFG.CIV_FLAG_WIDTH, Menu_InGameProvAction.this.iMigrationWidth);
            }

            @Override
            public void drawTextE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.getIsClickable()) {
                    CFG.core.getCiv(CFG.core.getActiveCivID()).getFlagC().drawO(oSB, this.getPosXE() + this.getWidthE() / 2 - this.getTextWidthU() / 2 + iTranslateX, this.getPosY() + this.getHeightE() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.core.getCiv(CFG.core.getActiveCivID()).getFlagC().getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                    IMGManager.getIMG(Images.flagRectSmall).drawO(oSB, this.getPosXE() + this.getWidthE() / 2 - this.getTextWidthU() / 2 + iTranslateX, this.getPosY() + this.getHeightE() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
                    if (isActive) {
                        Renderer.drawTextWithShadowAlpha(oSB, this.getTextToDrawElem(), this.getPosXE() + this.getWidthE() / 2 - this.getTextWidthU() / 2 + CFG.PADD + CFG.CIV_FLAG_WIDTH + iTranslateX, this.getPosY() + this.getHeightE() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColorE(isActive));
                    } else {
                        Renderer.drawTextWithShadowAlpha(oSB, this.getTextToDrawElem(), this.getPosXE() + this.getWidthE() / 2 - this.getTextWidthU() / 2 + CFG.PADD + CFG.CIV_FLAG_WIDTH + iTranslateX, this.getPosY() + this.getHeightE() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColorE(isActive));
                    }
                } else {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.575f));
                    CFG.core.getCiv(CFG.core.getActiveCivID()).getFlagC().drawO(oSB, this.getPosXE() + this.getWidthE() / 2 - this.getTextWidthU() / 2 + iTranslateX, this.getPosY() + this.getHeightE() / 2 - CFG.PADD / 2 - this.getTextHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.core.getCiv(CFG.core.getActiveCivID()).getFlagC().getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                    IMGManager.getIMG(Images.flagRectSmall).drawO(oSB, this.getPosXE() + this.getWidthE() / 2 - this.getTextWidthU() / 2 + iTranslateX, this.getPosY() + this.getHeightE() / 2 - CFG.PADD / 2 - this.getTextHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
                    oSB.setColor(Color.WHITE);
                    if (isActive) {
                        Renderer.drawTextWithShadowAlpha(oSB, this.getTextToDrawElem(), this.getPosXE() + this.getWidthE() / 2 - this.getTextWidthU() / 2 + CFG.PADD + CFG.CIV_FLAG_WIDTH + iTranslateX, this.getPosY() + this.getHeightE() / 2 - CFG.PADD / 2 - this.getTextHeight() + iTranslateY, this.getColorE(isActive));
                    } else {
                        Renderer.drawTextWithShadowAlpha(oSB, this.getTextToDrawElem(), this.getPosXE() + this.getWidthE() / 2 - this.getTextWidthU() / 2 + CFG.PADD + CFG.CIV_FLAG_WIDTH + iTranslateX, this.getPosY() + this.getHeightE() / 2 - CFG.PADD / 2 - this.getTextHeight() + iTranslateY, this.getColorE(isActive));
                    }
                    Renderer.drawTextWithShadowAlpha(oSB, CFG.FONT_BOLD_SMALL, Menu_InGameProvAction.this.sMigration, this.getPosXE() + (this.getWidthE() - Menu_InGameProvAction.this.iMigrationWidth) / 2 + iTranslateX, this.getPosY() + this.getHeightE() / 2 + CFG.PADD + (CFG.TEXT_HEIGHT_DEFAULT - CFG.TEXT_HEIGHT_DEFAULT) / 2 + iTranslateY, new Color(0.46f, 0.46f, 0.46f, 0.65f));
                }
            }

            @Override
            public void drawButtonBGE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawButtonBGE(oSB, iTranslateX, iTranslateY, isActive);
                if (isActive) {
                    oSB.setColor(Color.WHITE);
                } else if (this.getIsHovered()) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.75f));
                } else {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.275f));
                }
                IMGManager.getIMG(Images.actMigrate).drawO(oSB, this.getPosXE() + this.getWidthE() - IMGManager.getIMG(Images.actMigrate).getWidth() - CFG.PADD + iTranslateX, this.getPosY() + this.getHeightE() - IMGManager.getIMG(Images.actMigrate).getHeight() - CFG.PADD + iTranslateY);
                oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElemHover() {
                try {
                    if (!this.getIsClickable() && CFG.core.getActiveProvID() >= 0) {
                        ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                        ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                        nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()));
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("YouCantMigrateUntilX", GameCalendar.getDate_ByTurnID(GameCalendar.TURNID + CFG.core.uncivilizedCanMigrate_FromProvince_NumOfTurns(CFG.core.getActiveProvID(), CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()))), CFG.COLOR_NEGATIVE_2));
                        nData.add(new ME_Hover_2Type_Text_Big(" [" + CFG.lang.get("TurnsX", CFG.core.uncivilizedCanMigrate_FromProvince_NumOfTurns(CFG.core.getActiveProvID(), CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())) + "]", CFG.COLOR_NEUTRAL));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        this.menuElemHover = new ME_Hover_v2(nElements);
                    } else {
                        ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                        ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                        nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()));
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("MigrateToAnotherProvince"), CFG.COLOR_HOVER_TITLE));
                        nData.add(new ME_Hover_2Type_Image_Big(Images.pop, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Space());
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Research") + ": "));
                        nData.add(new ME_Hover_2Type_Text("+" + (int)((CFG.core.getProv(CFG.core.getActiveProvID()).isCapital() ? GameValues.gvMigrate.MIGRATE_RESEARCH_PROGRESS : GameValues.gvMigrate.MIGRATE_RESEARCH_PROGRESS_NOT_CAPITAL) * 100.0f) + "%", CFG.COLOR_RESEARCH));
                        nData.add(new ME_Hover_2Type_Image(Images.research, CFG.PADD, 0));
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
        menuElements.add(new Button_Game(null, -1, CFG.PADD * 5 + CFG.BUTTON_W * 4, CFG.PADD, true){
            public String sKey;
            public int iKeyWidth;

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
                this.sKey = "Y";
                CFG.glyphLay.setText(CFG.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sKey);
                this.iKeyWidth = (int)CFG.glyphLay.width;
            }

            @Override
            public void drawTextE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                Renderer.drawTextWithShadowAlpha(oSB, CFG.FONT_REGULAR_SMALL, this.sKey, this.getPosXE() + this.getWidthE() - CFG.PADD - this.iKeyWidth + iTranslateX, this.getPosY() + CFG.PADD + iTranslateY, this.getColorE(isActive));
                super.drawTextE(oSB, iTranslateX, iTranslateY, isActive);
            }

            @Override
            public int getPosXE() {
                return Menu_InGameProvAction.this.getMenuElem(5).getVisibleE() ? Menu_InGameProvAction.this.getMenuElem(5).getPosXE() + Menu_InGameProvAction.this.getMenuElem(5).getWidthE() + CFG.PADD : Menu_InGameProvAction.this.getMenuElem(4).getPosXE() + Menu_InGameProvAction.this.getMenuElem(4).getWidthE() + CFG.PADD;
            }

            @Override
            public boolean getVisibleE() {
                try {
                    if (TouchManager.lMABX.size() > 1) {
                        return true;
                    }
                    if (CFG.core.getProv(CFG.core.getActiveProvID()).getArmyCivID1(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) > 0) {
                        for (int a = 0; a < CFG.core.getProv(CFG.core.getActiveProvID()).getNeighProvincesSize(); ++a) {
                            if (!CFG.core.getCivsAtWar(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.core.getProv(CFG.core.getProv(CFG.core.getActiveProvID()).getNeighProvinces(a)).getCivId())) continue;
                            return true;
                        }
                    }
                    return false;
                }
                catch (Exception ex) {
                    return false;
                }
            }

            @Override
            public void drawButtonBGE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawButtonBGE(oSB, iTranslateX, iTranslateY, isActive);
                if (isActive) {
                    oSB.setColor(Color.WHITE);
                } else if (this.getIsHovered()) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.75f));
                } else {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.575f));
                }
                IMGManager.getIMG(Images.actAttack).drawO(oSB, this.getPosXE() + this.getWidthE() - IMGManager.getIMG(Images.actAttack).getWidth() - CFG.PADD + iTranslateX, this.getPosY() + this.getHeightE() - IMGManager.getIMG(Images.actAttack).getHeight() - CFG.PADD + iTranslateY);
                oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElemHover() {
                try {
                    if (CFG.getIsDesktop()) {
                        ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                        ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                        nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()));
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("Offensive"), CFG.COLOR_HOVER_TITLE));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Space());
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Shortcut") + ": "));
                        nData.add(new ME_Hover_2Type_Text("Y", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nData.add(new ME_Hover_2Type_Image(Images.key, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Space());
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_TextDesc(CFG.lang.get("AttackAllNeighboringEnemyProvinces")));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        this.menuElemHover = new ME_Hover_v2(nElements);
                    } else {
                        this.menuElemHover = null;
                    }
                }
                catch (Exception ex) {
                    this.menuElemHover = null;
                }
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.PADD * 5 + CFG.BUTTON_W * 4, CFG.PADD, true){

            @Override
            public int getPosXE() {
                return Menu_InGameProvAction.this.getMenuElem(6).getVisibleE() ? Menu_InGameProvAction.this.getMenuElem(6).getPosXE() + Menu_InGameProvAction.this.getMenuElem(6).getWidthE() + CFG.PADD : (Menu_InGameProvAction.this.getMenuElem(5).getVisibleE() ? Menu_InGameProvAction.this.getMenuElem(5).getPosXE() + Menu_InGameProvAction.this.getMenuElem(5).getWidthE() + CFG.PADD : Menu_InGameProvAction.this.getMenuElem(4).getPosXE() + Menu_InGameProvAction.this.getMenuElem(4).getWidthE() + CFG.PADD);
            }

            @Override
            public boolean getVisibleE() {
                try {
                    return CFG.core.getProv(CFG.core.getActiveProvID()).isOccupied();
                }
                catch (Exception ex) {
                    return false;
                }
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
            public void drawButtonBGE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawButtonBGE(oSB, iTranslateX, iTranslateY, isActive);
                if (isActive) {
                    oSB.setColor(Color.WHITE);
                } else if (this.getIsHovered()) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.75f));
                } else {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.575f));
                }
                IMGManager.getIMG(Images.actPlunder).drawO(oSB, this.getPosXE() + this.getWidthE() - IMGManager.getIMG(Images.actPlunder).getWidth() - CFG.PADD + iTranslateX, this.getPosY() + this.getHeightE() - IMGManager.getIMG(Images.actPlunder).getHeight() - CFG.PADD + iTranslateY);
                oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElemHover() {
                try {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    if (CFG.core.getProv(CFG.core.getActiveProvID()).isOccupied()) {
                        if (CFG.core.getProv(CFG.core.getActiveProvID()).getName().length() > 0) {
                            nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getProv(CFG.core.getActiveProvID()).getTrueOwnerOfProv()));
                            nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("Plunder") + ": ", CFG.COLOR_HOVER_TITLE));
                            nData.add(new ME_Hover_2Type_Text_Big(CFG.core.getProv(CFG.core.getActiveProvID()).getName()));
                            nElements.add(new MEHover_2E(nData));
                            nData.clear();
                        } else {
                            nData.add(new ME_Hover_2Type_Flag_Big(CFG.core.getProv(CFG.core.getActiveProvID()).getTrueOwnerOfProv()));
                            nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("Plunder"), CFG.COLOR_HOVER_TITLE));
                            nElements.add(new MEHover_2E(nData));
                            nData.clear();
                        }
                        nData.add(new ME_Hover_2Type_Space());
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("OccupiedProvince")));
                        nData.add(new ME_Hover_2Type_Image(CFG.core.getProv(CFG.core.getActiveProvID()).isOccupied() ? Images.iconTrue : Images.iconFalse, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Cost") + ": "));
                        nData.add(new ME_Hover_2Type_Text("" + (float)CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).getIdeology()).COST_OF_PLUNDER / 10.0f, CFG.COLOR_MOVEMENT));
                        nData.add(new ME_Hover_2Type_Image(Images.topMovementPoints, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    } else {
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("OnlyOccupiedProvinceCanBePlundered"), CFG.COLOR_NEGATIVE_2));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    }
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
                catch (Exception ex) {
                    this.menuElemHover = null;
                }
            }
        });
        this.initMenu(null, 0, CFG.GAMEHEIGHT - CFG.map.getMpB().getMinimapHeight() - CFG.BUTTON_H - CFG.PADD * 2, CFG.GAMEWIDTH, CFG.BUTTON_H + CFG.PADD * 2, menuElements, true, false);
        this.updateLang();
        CFG.fMOVE_MENU_PERCENTAGE = 5.0f;
        CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
    }

    @Override
    public void updateLang() {
        this.getMenuElem(0).setTextE(CFG.lang.get("Move"));
        this.getMenuElem(1).setTextE(CFG.lang.get("Recruit"));
        this.getMenuElem(2).setTextE(CFG.lang.get("More"));
        this.getMenuElem(3).setTextE(CFG.lang.get("Disband"));
        this.getMenuElem(4).setTextE(CFG.lang.get("MoveTo"));
        this.getMenuElem(5).setTextE(CFG.lang.get("Migrate"));
        this.getMenuElem(6).setTextE(CFG.lang.get("Attack"));
        this.getMenuElem(7).setTextE(CFG.lang.get("Plunder"));
        this.updateButtonWidth(5, CFG.PADD, CFG.BUTTON_W);
        this.updatedButtonsWidth(CFG.PADD, CFG.BUTTON_W);
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if ((CFG.fMOVE_MENU_PERCENTAGE += (float)(System.currentTimeMillis() - CFG.lMOVE_MENU_TIME) / 300.0f * 95.0f) > 100.0f) {
            CFG.fMOVE_MENU_PERCENTAGE = 100.0f;
        }
        CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
        int tWidth = 0;
        for (int a = 0; a < this.getMenuElemsSize(); ++a) {
            if (!this.getMenuElem(a).getVisibleE() || this.getMenuElem(a).getPosXE() + this.getMenuElem(a).getWidthE() <= tWidth) continue;
            tWidth = this.getMenuElem(a).getPosXE() + this.getMenuElem(a).getWidthE();
        }
        IMGManager.getIMG(Images.bgGameAction).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - 1 + iTranslateY, tWidth += CFG.PADD + 1, this.getHeightM() + 1, true, false);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        Menu_InGameProvAction.draw4(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, sliderMenuIsActive);
    }

    public static void draw4(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        block8: {
            if (!TouchManager.lMABX.isEmpty()) {
                try {
                    int units = 0;
                    for (int i = 0; i < TouchManager.lMABX.size(); ++i) {
                        units += CFG.core.getProv(TouchManager.lMABX.get(i)).getArmyCivID1(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId());
                    }
                    if (units <= 0) break block8;
                    if (units != numOfUnits) {
                        numOfUnits = units;
                        sUnits = CFG.getNumberWthSpaces("" + units);
                        try {
                            if (sUnits != null && sUnits.length() > 0) {
                                CFG.glyphLay.setText(CFG.fontMain.get(CFG.FONT_BOLD), sUnits);
                                textWidth = (int)CFG.glyphLay.width;
                                textHeight = (int)CFG.glyphLay.height;
                            }
                        }
                        catch (Exception i) {
                            // empty catch block
                        }
                        armyIconWidth = (int)((float)IMGManager.getIMG(Images.diploArmy).getWidth() * Menu_InGameProvAction.getImageScale(Images.diploArmy));
                    }
                    int width = textWidth + CFG.PADD * 7 + IMGManager.getIMG(Images.flagRect2).getWidth() + armyIconWidth;
                    int height = Math.max(textHeight, IMGManager.getIMG(Images.flagRect2).getHeight()) + CFG.PADD * 4;
                    int posX = CFG.PADD * 2 + iTranslateX;
                    int posY = -CFG.PADD * 2 - height + iTranslateY;
                    oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.925f));
                    Renderer.drawBox2(oSB, Images.statsRectBG, posX, posY, width, height, 1.0f);
                    oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.25f));
                    IMGManager.getIMG(Images.gradientFull).draw(oSB, posX, posY, width, height);
                    oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.25f));
                    IMGManager.getIMG(Images.gradientXY).draw(oSB, posX, posY, width, height);
                    oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.25f));
                    Renderer.drawBox2(oSB, Images.statsRectBGBorder, posX + 1, posY + 1, width - 2, height - 2, 1.0f);
                    oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
                    Renderer.drawBox2(oSB, Images.statsRectBGBorder, posX, posY, width, height, 1.0f);
                    Core.drawFlagRect(oSB, posX + CFG.PADD * 2, posY + height / 2 - IMGManager.getIMG(Images.flagRect2).getHeight() / 2, CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId());
                    Renderer.drawTextWithShadow(oSB, CFG.FONT_BOLD, sUnits, posX + CFG.PADD * 4 + IMGManager.getIMG(Images.flagRect2).getWidth(), posY + height / 2 - textHeight / 2, CFG.COLOR_NEUTRAL2);
                    IMGManager.getIMG(Images.diploArmy).draw(oSB, posX + CFG.PADD * 5 + IMGManager.getIMG(Images.flagRect2).getWidth() + textWidth, posY + height / 2 - (int)((float)IMGManager.getIMG(Images.diploArmy).getHeight() * Menu_InGameProvAction.getImageScale(Images.diploArmy)) / 2, armyIconWidth, (int)((float)IMGManager.getIMG(Images.diploArmy).getHeight() * Menu_InGameProvAction.getImageScale(Images.diploArmy)));
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
        }
    }

    public static final float getImageScale(int iImageID) {
        return Math.min(1.0f, (float)CFG.TEXT_HEIGHT_DEFAULT / (float)IMGManager.getIMG(iImageID).getHeight());
    }

    @Override
    public void beginClipM(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
    }

    public static final void clickMove() {
        Core.LYC();
        if (CFG.gameAction.isMovingArmyFromProvince(CFG.core.getActiveProvID(), CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())) {
            CFG.core.chooseProvinceMode();
            CFG.menus.setVisible_InGame_ProvinceAction(false);
            Menu_InGameProvAction.hideMenus();
        } else if (CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getMovemPoints() < CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).getIdeology()).COST_OF_MOVE_OWN_PROVINCE) {
            CFG.menus.setVisible_InGame_ActionInfo_NoMovementPoints();
        } else if (CFG.core.getProv(CFG.core.getActiveProvID()).getArmyCivID1(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) <= 0) {
            CFG.menus.setVisible_InGame_ActionInfo_NoUnits();
        } else {
            CFG.core.chooseProvinceMode();
            CFG.menus.setVisible_InGame_ProvinceAction(false);
            Menu_InGameProvAction.hideMenus();
        }
        if (CFG.menus.getInGame_Plunder().getVisibleM()) {
            CFG.menus.getInGame_Plunder().setVisibleM(false);
        }
    }

    public static final boolean canMore() {
        return !CFG.core.getProv(CFG.core.getActiveProvID()).getSeaProv() && (CFG.core.getProv(CFG.core.getActiveProvID()).getCivId() == CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId() || CFG.core.getCiv(CFG.core.getProv(CFG.core.getActiveProvID()).getCivId()).getPuppetOfCiv() == CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId());
    }

    public static boolean canMove() {
        return CFG.core.getProv(CFG.core.getActiveProvID()).getCivId() == CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId() || CFG.core.getCiv(CFG.core.getProv(CFG.core.getActiveProvID()).getCivId()).getPuppetOfCiv() == CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId() || CFG.gameAction.controlsArmyInProvince(CFG.core.getActiveProvID()) || CFG.gameAction.isMovingArmyFromProvince(CFG.core.getActiveProvID());
    }

    public static final boolean canRecruit() {
        return !CFG.core.getProv(CFG.core.getActiveProvID()).getSeaProv() && CFG.core.getProv(CFG.core.getActiveProvID()).getCivId() == CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId();
    }

    public static final void clickRecruit() {
        if (CFG.core.getActiveProvID() >= 0) {
            if (CFG.core.getProv(CFG.core.getActiveProvID()).getTrueOwnerOfProv() == CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId() || CFG.core.getProv(CFG.core.getActiveProvID()).getPop().getPopulationOfCivID(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) > 0) {
                if (CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).isRAIP(CFG.core.getActiveProvID()) < 0) {
                    if (CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getMovemPoints() < CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).getIdeology()).COST_OF_RECRUIT) {
                        CFG.menus.setVisible_InGame_ActionInfo_NoMovementPoints();
                    } else if (CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getGold() < (long)CFG.gCARR(CFG.core.getActiveProvID())) {
                        CFG.menus.setVisible_InGame_ActionInfo_TreasuryIsEmpty();
                    } else {
                        CFG.core.resetChooseProvinceData();
                        CFG.menus.setVisible_InGame_ProvinceAction(false);
                        Menu_InGameProvAction.hideMenus();
                        Core.LYC();
                        Core.MRPRV(CFG.core.getActiveProvID());
                        CFG.gameAction.IEU();
                        Core.dARA(CFG.menus.getInGame_ProvRecruitSlider().getCurr());
                        CFG.menus.setVisible_InGame_ProviRecruit(true);
                        CFG.menus.setVisible_InGame_ActionInfo_Recruit();
                    }
                } else {
                    CFG.core.resetChooseProvinceData();
                    CFG.menus.setVisible_InGame_ProvinceAction(false);
                    Menu_InGameProvAction.hideMenus();
                    Core.LYC();
                    Core.MRPRV(CFG.core.getActiveProvID());
                    CFG.gameAction.IEU();
                    Core.dARA(CFG.menus.getInGame_ProvRecruitSlider().getCurr());
                    CFG.menus.setVisible_InGame_ProviRecruit(true);
                    CFG.menus.setVisible_InGame_ActionInfo_Recruit();
                }
            } else {
                CFG.menus.setVisible_InGame_ActionInfo_RecruitOccupied();
            }
        }
        if (CFG.menus.getInGame_Plunder().getVisibleM()) {
            CFG.menus.getInGame_Plunder().setVisibleM(false);
        }
    }

    public static final void recruit(float perc) {
        if (CFG.core.getActiveProvID() >= 0) {
            if (CFG.core.getProv(CFG.core.getActiveProvID()).getTrueOwnerOfProv() == CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) {
                if (CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).isRAIP(CFG.core.getActiveProvID()) < 0) {
                    if (CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getMovemPoints() < CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).getIdeology()).COST_OF_RECRUIT) {
                        CFG.menus.setVisible_InGame_ActionInfo_NoMovementPoints();
                    } else if (CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getGold() < (long)CFG.gCARR(CFG.core.getActiveProvID())) {
                        CFG.menus.setVisible_InGame_ActionInfo_TreasuryIsEmpty();
                    } else {
                        int maxArmy = CFG.gameAction.getMaxRecruit();
                        if (maxArmy > 0) {
                            CFG.core.resetChooseProvinceData();
                            CFG.menus.setVisible_InGame_ProvinceAction(false);
                            Menu_InGameProvAction.recruitUnits_HotKey((int)Math.max(0.0f, (float)maxArmy * perc));
                        }
                    }
                } else {
                    int maxArmy = CFG.gameAction.getMaxRecruit();
                    if (maxArmy > 0) {
                        CFG.core.resetChooseProvinceData();
                        CFG.menus.setVisible_InGame_ProvinceAction(false);
                        Menu_InGameProvAction.recruitUnits_HotKey((int)Math.max(0.0f, (float)maxArmy * perc));
                    }
                }
            } else {
                CFG.menus.setVisible_InGame_ActionInfo_RecruitOccupied();
            }
        }
        if (CFG.menus.getInGame_Plunder().getVisibleM()) {
            CFG.menus.getInGame_Plunder().setVisibleM(false);
        }
    }

    public static void recruitUnits_HotKey(int units) {
        try {
            CFG.menus.setVisible_InGame_ProviRecruit(false);
            CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).recruitArmy(CFG.core.getActiveProvID(), units);
            CFG.core.checkProvinceActionMenu();
            CFG.menus.updateInGameTopAll(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId());
            CFG.gameAction.updateInGame_ProvinceInfo();
            if (CFG.menus.getVisibleInGame_CensusOfProvince()) {
                CFG.menus.rebuildInGame_CensusOfProvince(CFG.core.getActiveProvID());
            }
            if (CFG.mapModesManager.getActiveMapModeID() == MapModesManager.VIEW_ARMY_MODE) {
                CFG.updateMAX_Army();
            }
            CFG.core.getPlayer(CFG.PLAYER_TURN_ID).setNoOrders(false);
            Menu_InGame_2.updateOverBudget();
            if (RTS.isEnabled() && !RTS.PAUSE) {
                RTS.updateTimePast_AfterAction(1.0f);
            }
            CFG.menus.resetHoverActive();
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    public static final void clickBuild() {
        BuildingsManager.iBuildInProvinceID = CFG.core.getActiveProvID();
        if (BuildingsManager.iBuildInProvinceID >= 0) {
            CFG.core.resetChooseProvinceData();
            CFG.menus.setVisible_InGame_ProvinceMore(!CFG.menus.getInGame_ProvincemMore_Visible(), false);
            if (CFG.menus.getInGame_ProvincemMore_Visible()) {
                if (CFG.mapModesManager.getActiveMapModeID() == MapModesManager.VIEW_DIPLOMACY_MODE) {
                    CFG.mapModesManager.setActiveMapModeID(-1);
                }
                if (CFG.menus.getVisible_InGame_CivInfo()) {
                    CFG.menus.setVisible_InGame_CivInfo(false);
                }
            }
        } else {
            BuildingsManager.iBuildInProvinceID = 0;
        }
        if (CFG.menus.getInGame_Plunder().getVisibleM()) {
            CFG.menus.getInGame_Plunder().setVisibleM(false);
        }
    }

    public static final void clickDisband() {
        if (CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getMovemPoints() < CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).getIdeology()).COST_OF_DISBAND) {
            CFG.menus.setVisible_InGame_ActionInfo_NoMovementPoints();
        } else {
            CFG.core.resetChooseProvinceData();
            CFG.menus.setVisible_InGame_ProvinceAction(false);
            CFG.activeCivilizationArmyID = 0;
            CFG.gameAction.updateDisbandSlider();
            CFG.menus.setVisible_InGame_ProvinceDisband(true);
            CFG.menus.setVisible_InGame_ActionInfo_Disband();
        }
        if (CFG.menus.getInGame_Plunder().getVisibleM()) {
            CFG.menus.getInGame_Plunder().setVisibleM(false);
        }
    }

    public static final void clickMoveTo() {
        if (CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getMovemPoints() < CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).getIdeology()).COST_OF_MOVE_OWN_PROVINCE) {
            CFG.menus.setVisible_InGame_ActionInfo_NoMovementPoints();
        } else if (CFG.core.getProv(CFG.core.getActiveProvID()).getArmyCivID1(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) <= 0) {
            CFG.menus.setVisible_InGame_ActionInfo_NoUnits();
        } else {
            CFG.core.resetChooseProvinceData();
            CFG.core.regroupArmyMode();
            CFG.menus.setVisible_InGame_ProvinceAction(false);
            Menu_InGameProvAction.hideMenus();
        }
        if (CFG.menus.getInGame_Plunder().getVisibleM()) {
            CFG.menus.getInGame_Plunder().setVisibleM(false);
        }
    }

    @Override
    public final void actionEL(int iID) {
        switch (iID) {
            case 0: {
                Menu_InGameProvAction.clickMove();
                break;
            }
            case 1: {
                Menu_InGameProvAction.clickRecruit();
                break;
            }
            case 2: {
                Menu_InGameProvAction.clickBuild();
                break;
            }
            case 3: {
                Menu_InGameProvAction.clickDisband();
                break;
            }
            case 4: {
                Menu_InGameProvAction.clickMoveTo();
                break;
            }
            case 5: {
                if (CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getMovemPoints() < CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).getIdeology()).COST_OF_MOVE) {
                    CFG.menus.setVisible_InGame_ActionInfo_NoMovementPoints();
                    break;
                }
                CFG.migrateMode = true;
                CFG.core.chooseProvinceMode();
                CFG.menus.setVisible_InGame_ProvinceAction(false);
                Menu_InGameProvAction.hideMenus();
                break;
            }
            case 6: {
                Menu_InGameProvAction.clickOffensive();
                break;
            }
            case 7: {
                if (CFG.core.getActiveProvID() < 0) break;
                if (Menu_InGame_Plunder.iProvinceID == CFG.core.getActiveProvID() && CFG.menus.getInGame_Plunder().getVisibleM()) {
                    CFG.menus.getInGame_Plunder().actionEL(CFG.menus.getInGame_Plunder().getMenuElemsSize() - 1);
                    break;
                }
                CFG.menus.rebuildInGame_Plunder(CFG.core.getActiveProvID());
            }
        }
    }

    public static void clickCancelMove() {
        try {
            try {
                CFG.core.resetChooseProvinceData();
                CFG.core.resetRegroupArmy_Data();
            }
            catch (Exception exception) {
                // empty catch block
            }
            Civilization civ = CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId());
            try {
                if (!TouchManager.lMABX.isEmpty()) {
                    for (int a = 0; a < TouchManager.lMABX.size(); ++a) {
                        for (int i = civ.moveUnitsSize() - 1; i >= 0; --i) {
                            if (civ.getMoveUnits(i).getFromProviID() != TouchManager.lMABX.get(a).intValue()) continue;
                            CFG.gameAction.moveArmyAction(civ.getMoveUnits(i).getFromProviID(), civ.getMoveUnits(i).getToProvID(), 0, civ.getCivId(), true, true);
                        }
                    }
                }
                if (CFG.core.getActiveProvID() >= 0) {
                    for (int i = civ.moveUnitsSize() - 1; i >= 0; --i) {
                        if (civ.getMoveUnits(i).getFromProviID() != CFG.core.getActiveProvID()) continue;
                        CFG.gameAction.moveArmyAction(civ.getMoveUnits(i).getFromProviID(), civ.getMoveUnits(i).getToProvID(), 0, civ.getCivId(), true, true);
                    }
                }
            }
            catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
            CFG.toastM.addM(CFG.lang.get("Cancel") + ": " + CFG.lang.get("MoveTheArmies"), CFG.COLOR_POSITIVE);
            CFG.menus.updateInGameTopAll(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId());
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    public static void clickOffensive() {
        try {
            if (!TouchManager.lMABX.isEmpty()) {
                for (int i = 0; i < TouchManager.lMABX.size(); ++i) {
                    Menu_InGameProvAction.clickOffensive(TouchManager.lMABX.get(i));
                }
                return;
            }
            if (CFG.core.getProv(CFG.core.getActiveProvID()).getArmyCivID1(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) > 0) {
                ArrayList<Integer> provinces = new ArrayList<Integer>();
                for (int a = 0; a < CFG.core.getProv(CFG.core.getActiveProvID()).getNeighProvincesSize(); ++a) {
                    if (!CFG.core.getCivsAtWar(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.core.getProv(CFG.core.getProv(CFG.core.getActiveProvID()).getNeighProvinces(a)).getCivId())) continue;
                    provinces.add(CFG.core.getProv(CFG.core.getActiveProvID()).getNeighProvinces(a));
                }
                if (!provinces.isEmpty()) {
                    int armyPerMove = Math.min(Math.max(CFG.MIN_ARMY_REQUIRED_TO_ATTACK, CFG.core.getProv(CFG.core.getActiveProvID()).getArmyCivID1(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) / provinces.size()), CFG.core.getProv(CFG.core.getActiveProvID()).getArmyCivID1(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()));
                    for (int i = 0; i < provinces.size(); ++i) {
                        int armyToMove = Math.min(armyPerMove, CFG.core.getProv(CFG.core.getActiveProvID()).getArmyCivID1(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()));
                        if (i == provinces.size() - 1) {
                            armyToMove = CFG.core.getProv(CFG.core.getActiveProvID()).getArmyCivID1(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId());
                        }
                        CFG.gameAction.moveArmyAction(CFG.core.getActiveProvID(), (Integer)provinces.get(i), armyToMove, CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), false, true);
                        if (CFG.core.getProv(CFG.core.getActiveProvID()).getArmyCivID1(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) == 0) break;
                    }
                    CFG.core.resetChooseProvinceData();
                    CFG.core.resetRegroupArmy_Data();
                    CFG.menus.updateInGameTopAll(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId());
                }
            } else {
                CFG.toastM.addM(CFG.lang.get("Army") + ": " + CFG.getNumberWthSpaces("" + CFG.core.getProv(CFG.core.getActiveProvID()).getArmyCivID1(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())), CFG.COLOR_NEGATIVE_2);
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    public static void clickOffensive(int provinceID) {
        try {
            if (CFG.core.getProv(provinceID).getArmyCivID1(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) > 0) {
                ArrayList<Integer> provinces = new ArrayList<Integer>();
                for (int a = 0; a < CFG.core.getProv(provinceID).getNeighProvincesSize(); ++a) {
                    if (!CFG.core.getCivsAtWar(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.core.getProv(CFG.core.getProv(provinceID).getNeighProvinces(a)).getCivId())) continue;
                    provinces.add(CFG.core.getProv(provinceID).getNeighProvinces(a));
                }
                if (!provinces.isEmpty()) {
                    int armyPerMove = Math.min(Math.max(CFG.MIN_ARMY_REQUIRED_TO_ATTACK, CFG.core.getProv(provinceID).getArmyCivID1(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) / provinces.size()), CFG.core.getProv(provinceID).getArmyCivID1(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()));
                    for (int i = 0; i < provinces.size(); ++i) {
                        int armyToMove = Math.min(armyPerMove, CFG.core.getProv(provinceID).getArmyCivID1(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()));
                        if (i == provinces.size() - 1) {
                            armyToMove = CFG.core.getProv(provinceID).getArmyCivID1(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId());
                        }
                        CFG.gameAction.moveArmyAction(provinceID, (Integer)provinces.get(i), armyToMove, CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), false, true);
                        if (CFG.core.getProv(provinceID).getArmyCivID1(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) == 0) break;
                    }
                    CFG.core.resetChooseProvinceData();
                    CFG.core.resetRegroupArmy_Data();
                    CFG.menus.updateInGameTopAll(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId());
                }
            } else {
                CFG.toastM.addM(CFG.lang.get("Army") + ": " + CFG.getNumberWthSpaces("" + CFG.core.getProv(provinceID).getArmyCivID1(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())), CFG.COLOR_NEGATIVE_2);
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    @Override
    public void setVisibleM(boolean visible) {
        if (visible && this.getVisibleM() != visible) {
            CFG.fMOVE_MENU_PERCENTAGE = 5.0f;
            CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
        }
        super.setVisibleM(visible);
        if (this.getVisibleM()) {
            this.getMenuElem(5).setVisibleE(CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).getIdeology()).CAN_BECOME_CIVILIZED >= 0);
            if (this.getMenuElem(5).getVisibleE()) {
                this.getMenuElem(5).setClickable(Core.uncivilizedCanMigrate_FromProv(CFG.core.getActiveProvID(), CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()));
                if (!this.getMenuElem(5).getIsClickable()) {
                    this.sMigration = CFG.lang.get("TurnsX", CFG.core.uncivilizedCanMigrate_FromProvince_NumOfTurns(CFG.core.getActiveProvID(), CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()));
                    CFG.glyphLay.setText(CFG.fontMain.get(CFG.FONT_BOLD_SMALL), this.sMigration);
                    this.iMigrationWidth = (int)CFG.glyphLay.width;
                }
            }
        }
    }

    @Override
    public void onHovered() {
        CFG.menus.setOrderOfMenu_InGame();
    }

    public static void hideMenus() {
        if (CFG.menus.getInGame_ProvincemMore_Visible()) {
            CFG.menus.setVisible_InGame_ProvinceMore(false, false);
        }
        if (CFG.mapModesManager.getActiveMapModeID() == MapModesManager.VIEW_DIPLOMACY_MODE) {
            CFG.mapModesManager.setActiveMapModeID(-1);
        }
        if (CFG.menus.getVisible_InGame_CivInfo()) {
            CFG.menus.setVisible_InGame_CivInfo(false);
        }
    }
}
