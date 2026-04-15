package age.of.civilizations2.jakowski.lukasz.Menus.Wars.Details;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.Button.Button_Transparent;
import age.of.civilizations2.jakowski.lukasz.Button.Flag.Button_InGameAction;
import age.of.civilizations2.jakowski.lukasz.Button.Flag.Button_InGameAction_Animated;
import age.of.civilizations2.jakowski.lukasz.Button.MenuElemUI;
import age.of.civilizations2.jakowski.lukasz.Button.Stats.Button_Stats_CallAlly;
import age.of.civilizations2.jakowski.lukasz.Button.Stats.Button_Stats_CallAlly_Right;
import age.of.civilizations2.jakowski.lukasz.Button.Stats.Button_Stats_Title;
import age.of.civilizations2.jakowski.lukasz.Button.Stats.Button_Stats_Title_Right;
import age.of.civilizations2.jakowski.lukasz.Button.Stats.Button_Stats_WarDetails;
import age.of.civilizations2.jakowski.lukasz.Button.Stats.Button_Stats_WarDetails_Right;
import age.of.civilizations2.jakowski.lukasz.Button.Stats.Button_Stats_WarDetails_WarResult;
import age.of.civilizations2.jakowski.lukasz.Button.Stats.Button_Stats_War_Casualties;
import age.of.civilizations2.jakowski.lukasz.Button.Stats.Button_Stats_War_Casualties_Right;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Civilizations.PeaceT.PeaceTreaty_Data;
import age.of.civilizations2.jakowski.lukasz.Core.Core;
import age.of.civilizations2.jakowski.lukasz.GameCalendar;
import age.of.civilizations2.jakowski.lukasz.IMGManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MapA.Mode.MapModesManager;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.MEHover_2E;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Image_Big;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Text;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Text_Big;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.Menus.PeaceTreaty.Menu_PeaceTreaty;
import age.of.civilizations2.jakowski.lukasz.Renderer;
import age.of.civilizations2.jakowski.lukasz.SFXManager;
import age.of.civilizations2.jakowski.lukasz.TextB.Texts.TextAlliesNotInWar;
import age.of.civilizations2.jakowski.lukasz.TextB.Texts.TextBuildTitle;
import age.of.civilizations2.jakowski.lukasz.Title.TitleM_TextSmall;
import age.of.civilizations2.jakowski.lukasz.View;
import age.of.civilizations2.jakowski.lukasz.Z_Other.DialogType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_WarDetails
extends Menu {
    public static int WAR_ID = 0;
    public static int iSort = 0;
    public String sDefender;
    public String sWarDate;
    public int iWarDateWidth;
    public int civLeft = -1;
    public int civRight = -1;

    public Menu_InGame_WarDetails(int tInit) {
        ArrayList<MenuElemUI> menuElements = new ArrayList<MenuElemUI>();
        int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 3;
        int tempMenuPosY = IMGManager.getIMG(Images.topFlagFrame).getHeight() + CFG.PADD * 4 + CFG.BUTTON_H * 3 / 5 + CFG.PADD * 2 + CFG.BUTTON_H / 2;
        this.initMenu(null, CFG.GAMEWIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, 5, menuElements, false, false);
    }

    public Menu_InGame_WarDetails() {
        int i;
        int j;
        int i2;
        int i3;
        int i4;
        ArrayList<MenuElemUI> menuElements = new ArrayList<MenuElemUI>();
        int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 3;
        if (WAR_ID >= CFG.core.getWarsSize()) {
            WAR_ID = 0;
        }
        try {
            this.sWarDate = GameCalendar.getNumOfDatesByTurnID(CFG.core.getWar(WAR_ID).getWarTurnID());
            CFG.glyphLay.setText(CFG.fontMain.get(CFG.FONT_BOLD_SMALL), this.sWarDate);
            this.iWarDateWidth = (int)CFG.glyphLay.width;
        }
        catch (Exception ex) {
            this.sWarDate = "";
            this.iWarDateWidth = 0;
        }
        int titleH = CFG.BUTTON_H / 2;
        menuElements.add(new Button_Stats_WarDetails_WarResult(CFG.core.getWar(WAR_ID).getAggressorID(0).getCivID(), CFG.core.getWar(WAR_ID).getDefenderID(0).getCivID(), WAR_ID, 2, 0, tempWidth - 4){

            @Override
            public int getWidthE() {
                return Menu_InGame_WarDetails.this.getW();
            }
        });
        int tY = ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        try {
            this.civRight = CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getMetCiv(CFG.core.getWar(WAR_ID).getDefenderID(0).getCivID()) ? CFG.core.getWar(WAR_ID).getDefenderID(0).getCivID() : -1;
            this.civLeft = CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getMetCiv(CFG.core.getWar(WAR_ID).getAggressorID(0).getCivID()) ? CFG.core.getWar(WAR_ID).getAggressorID(0).getCivID() : -1;
        }
        catch (Exception exception) {
            // empty catch block
        }
        menuElements.add(new Button_Stats_Title(CFG.lang.get("Aggressors"), CFG.PADD * 2, 2, tY, CFG.BUTTON_W * 2, titleH){

            @Override
            public int getWidthE() {
                return Menu_InGame_WarDetails.this.getElementW() * 4;
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

            @Override
            public void actionElem(int iID) {
                if (iSort != 0) {
                    iSort = 0;
                    CFG.menus.rebuildInGame_WarDetails();
                }
            }
        });
        menuElements.add(new Button_Stats_Title(CFG.lang.get("Casualties"), -1, CFG.PADD * 2 + CFG.BUTTON_W * 2, tY, CFG.BUTTON_W, titleH){

            @Override
            public int getPosXE() {
                return Menu_InGame_WarDetails.this.getElementW() * 4 + 2;
            }

            @Override
            public int getWidthE() {
                return Menu_InGame_WarDetails.this.getElementW() + 2;
            }

            @Override
            public Color getColorE(boolean isActive) {
                return iSort == 1 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColorE(isActive);
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

            @Override
            public void actionElem(int iID) {
                if (iSort != 1) {
                    iSort = 1;
                    CFG.menus.rebuildInGame_WarDetails();
                }
            }
        });
        menuElements.add(new Button_Stats_Title(CFG.lang.get("Casualties"), -1, CFG.PADD * 2 + CFG.BUTTON_W * 2, tY, CFG.BUTTON_W, titleH){

            @Override
            public int getPosXE() {
                return Menu_InGame_WarDetails.this.getElementW() * 5 + 4;
            }

            @Override
            public int getWidthE() {
                return Menu_InGame_WarDetails.this.getElementW() + 2;
            }

            @Override
            public Color getColorE(boolean isActive) {
                return iSort == 1 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColorE(isActive);
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

            @Override
            public void actionElem(int iID) {
                if (iSort != 1) {
                    iSort = 1;
                    CFG.menus.rebuildInGame_WarDetails();
                }
            }
        });
        menuElements.add(new Button_Stats_Title_Right(CFG.lang.get("Defenders"), CFG.PADD, CFG.PADD * 2 + CFG.BUTTON_W * 5, tY, CFG.BUTTON_W, titleH){

            @Override
            public int getPosXE() {
                return Menu_InGame_WarDetails.this.getElementW() * 6 + 6;
            }

            @Override
            public int getWidthE() {
                return Menu_InGame_WarDetails.this.getW() - Menu_InGame_WarDetails.this.getElementW() * 6 - 4;
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

            @Override
            public void actionElem(int iID) {
                if (iSort != 0) {
                    iSort = 0;
                    CFG.menus.rebuildInGame_WarDetails();
                }
            }
        });
        tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        for (i4 = 0; i4 < CFG.core.getWar(WAR_ID).getAggressorsSize(); ++i4) {
            menuElements.add(new Button_Stats_War_Casualties(CFG.core.getWar(WAR_ID).getAggressorID(i4).getCasualties() + CFG.core.getWar(WAR_ID).getAggressorID(i4).getCivilianDeaths(), -1, tY, CFG.BUTTON_W * 2){

                @Override
                public int getPosXE() {
                    return Menu_InGame_WarDetails.this.getElementW() * 4 + 2;
                }

                @Override
                public int getWidthE() {
                    return Menu_InGame_WarDetails.this.getElementW() + 2;
                }
            });
            menuElements.add(new Button_Stats_WarDetails(CFG.core.getWar(WAR_ID).getAggressorID(i4).getCivID(), CFG.core.getWar(WAR_ID).getAggressorID(i4).getCivilianDeaths(), CFG.core.getWar(WAR_ID).getAggressorID(i4).getEconomicLosses(), CFG.core.getWar(WAR_ID).getParticipation_AggressorID(i4), CFG.core.getWar(WAR_ID).getProvinces_Aggressor_OwnTotal(i4), CFG.core.getWar(WAR_ID).getProvinces_Aggressor_Own(i4), 2, tY, CFG.BUTTON_W * 2, !CFG.SPECTATOR_MODE && CFG.core.getWar(WAR_ID).getIsDefender(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())){

                @Override
                public int getWidthE() {
                    return Menu_InGame_WarDetails.this.getElementW() * 4;
                }
            });
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        }
        tY = ((MenuElemUI)menuElements.get(1)).getPosY() + ((MenuElemUI)menuElements.get(1)).getHeightE();
        for (i4 = 0; i4 < CFG.core.getWar(WAR_ID).getDefendersSize(); ++i4) {
            menuElements.add(new Button_Stats_War_Casualties_Right(CFG.core.getWar(WAR_ID).getDefenderID(i4).getCasualties() + CFG.core.getWar(WAR_ID).getDefenderID(i4).getCivilianDeaths(), -1, tY, CFG.BUTTON_W * 2){

                @Override
                public int getPosXE() {
                    return Menu_InGame_WarDetails.this.getElementW() * 5 + 4;
                }

                @Override
                public int getWidthE() {
                    return Menu_InGame_WarDetails.this.getElementW() + 2;
                }
            });
            menuElements.add(new Button_Stats_WarDetails_Right(CFG.core.getWar(WAR_ID).getDefenderID(i4).getCivID(), CFG.core.getWar(WAR_ID).getDefenderID(i4).getCivilianDeaths(), CFG.core.getWar(WAR_ID).getDefenderID(i4).getEconomicLosses(), CFG.core.getWar(WAR_ID).getParticipation_DefenderID(i4), CFG.core.getWar(WAR_ID).getProvinces_Defender_OwnTotal(i4), CFG.core.getWar(WAR_ID).getProvinces_Defender_Own(i4), CFG.PADD * 2, tY, CFG.BUTTON_W * 2, !CFG.SPECTATOR_MODE && CFG.core.getWar(WAR_ID).getIsAggressor(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())){

                @Override
                public int getPosXE() {
                    return Menu_InGame_WarDetails.this.getElementW() * 6 + 6;
                }

                @Override
                public int getWidthE() {
                    return Menu_InGame_WarDetails.this.getW() - Menu_InGame_WarDetails.this.getElementW() * 6 - 4;
                }
            });
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        }
        this.sDefender = CFG.FOG_OF_WAR == 2 ? (CFG.core.getCiv(CFG.core.getWar(WAR_ID).getDefenderID(0).getCivID()).getAlliance() > 0 ? (CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getMetAlliance(CFG.core.getCiv(CFG.core.getWar(WAR_ID).getDefenderID(0).getCivID()).getAlliance()) ? CFG.core.getAlliance(CFG.core.getCiv(CFG.core.getWar(WAR_ID).getDefenderID(0).getCivID()).getAlliance()).getAllianceName() : CFG.lang.get("Undiscovered")) : (CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getMetCiv(CFG.core.getWar(WAR_ID).getDefenderID(0).getCivID()) ? CFG.core.getCiv(CFG.core.getWar(WAR_ID).getDefenderID(0).getCivID()).getCivName() : CFG.lang.get("Undiscovered"))) : (CFG.core.getCiv(CFG.core.getWar(WAR_ID).getDefenderID(0).getCivID()).getAlliance() > 0 ? CFG.core.getAlliance(CFG.core.getCiv(CFG.core.getWar(WAR_ID).getDefenderID(0).getCivID()).getAlliance()).getAllianceName() : CFG.core.getCiv(CFG.core.getWar(WAR_ID).getDefenderID(0).getCivID()).getCivName());
        int tempMaxY = 0;
        int iSize = menuElements.size();
        for (i3 = 0; i3 < iSize; ++i3) {
            if (((MenuElemUI)menuElements.get(i3)).getPosY() + ((MenuElemUI)menuElements.get(i3)).getHeightE() <= tempMaxY) continue;
            tempMaxY = ((MenuElemUI)menuElements.get(i3)).getPosY() + ((MenuElemUI)menuElements.get(i3)).getHeightE();
        }
        if (!CFG.core.getWar(WAR_ID).getIsInAggressors(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) && !CFG.core.getWar(WAR_ID).getIsInDefenders(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())) {
            tY = tempMaxY + CFG.PADD;
            menuElements.add(new TextBuildTitle(CFG.lang.get("InterveneInWar"), -1, 2, tY, CFG.BUTTON_W, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 4){

                @Override
                public Color getColor(boolean isActive) {
                    return isActive ? CFG.COLOR_TEXT_GRAY_NS_HOVER : (this.getIsClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_GRAY_NS : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
                }

                @Override
                public int getWidthE() {
                    return Menu_InGame_WarDetails.this.getW();
                }
            });
            menuElements.add(new Button_InGameAction(CFG.lang.get("JoinAWar"), -1, 2, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE(), CFG.BUTTON_W, CFG.FOG_OF_WAR < 2 || CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getMetCiv(CFG.core.getWar(WAR_ID).getDefenderID(0).getCivID())){

                @Override
                public int getWidthE() {
                    return Menu_InGame_WarDetails.this.getElementW() * 5 + 2;
                }

                @Override
                public void drawTextE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    IMGManager.getIMG(Images.diploIntervene).draw(oSB, this.getPosXE() + this.getWidthE() / 2 - CFG.PADD - IMGManager.getIMG(Images.diploIntervene).getWidth() / 2 - this.getTextWidthU() / 2 + iTranslateX, this.getPosY() + this.getHeightE() / 2 - IMGManager.getIMG(Images.diploIntervene).getHeight() / 2 + iTranslateY);
                    Renderer.drawText(oSB, this.fontID, this.getTextE(), this.getPosXE() + (this.getTextPosElem() < 0 ? this.getWidthE() / 2 - (this.getTextWidthU() + IMGManager.getIMG(Images.diploIntervene).getWidth() + CFG.PADD) / 2 + IMGManager.getIMG(Images.diploIntervene).getWidth() + CFG.PADD : this.getTextPosElem()) + iTranslateX, this.getPosY() + this.getHeightE() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColorE(isActive));
                }

                @Override
                public void actionElem(int iID) {
                    CFG.setDialogType(DialogType.JOIN_A_WAR_AGGRESSORS);
                }

                @Override
                public void buildElemHover() {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    if (this.getIsClickable()) {
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("InterveneInWar"), CFG.COLOR_HOVER_TITLE));
                        nData.add(new ME_Hover_2Type_Image_Big(Images.diploIntervene, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("JoinAWar") + ": "));
                        nData.add(new ME_Hover_2Type_Text(CFG.core.getCiv(CFG.core.getWar(WAR_ID).getAggressorID(0).getCivID()).getCivName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nData.add(new ME_Hover_2Type_Flag(CFG.core.getWar(WAR_ID).getAggressorID(0).getCivID(), CFG.PADD));
                        nData.add(new ME_Hover_2Type_Image(Images.diploWar, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    } else {
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Undiscovered"), CFG.COLOR_HOVER_TITLE));
                        nData.add(new ME_Hover_2Type_Flag(-1, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    }
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
            });
            menuElements.add(new Button_InGameAction(CFG.lang.get("JoinAWar"), -1, 2, tY, CFG.BUTTON_W, CFG.FOG_OF_WAR < 2 || CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getMetCiv(CFG.core.getWar(WAR_ID).getDefenderID(0).getCivID())){

                @Override
                public int getPosXE() {
                    return Menu_InGame_WarDetails.this.getElementW() * 5 + 4;
                }

                @Override
                public int getWidthE() {
                    return Menu_InGame_WarDetails.this.getW() - Menu_InGame_WarDetails.this.getElementW() * 6 - 4 + Menu_InGame_WarDetails.this.getElementW() + 2;
                }

                @Override
                public void drawTextE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    IMGManager.getIMG(Images.diploIntervene).draw(oSB, this.getPosXE() + this.getWidthE() / 2 - CFG.PADD - IMGManager.getIMG(Images.diploIntervene).getWidth() / 2 - this.getTextWidthU() / 2 + iTranslateX, this.getPosY() + this.getHeightE() / 2 - IMGManager.getIMG(Images.diploIntervene).getHeight() / 2 + iTranslateY);
                    Renderer.drawText(oSB, this.fontID, this.getTextE(), this.getPosXE() + (this.getTextPosElem() < 0 ? this.getWidthE() / 2 - (this.getTextWidthU() + IMGManager.getIMG(Images.diploIntervene).getWidth() + CFG.PADD) / 2 + IMGManager.getIMG(Images.diploIntervene).getWidth() + CFG.PADD : this.getTextPosElem()) + iTranslateX, this.getPosY() + this.getHeightE() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColorE(isActive));
                }

                @Override
                public void actionElem(int iID) {
                    CFG.setDialogType(DialogType.JOIN_A_WAR_DEFENDERS);
                }

                @Override
                public void buildElemHover() {
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    if (this.getIsClickable()) {
                        nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("InterveneInWar"), CFG.COLOR_HOVER_TITLE));
                        nData.add(new ME_Hover_2Type_Image_Big(Images.diploIntervene, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("JoinAWar") + ": "));
                        nData.add(new ME_Hover_2Type_Text(CFG.core.getCiv(CFG.core.getWar(WAR_ID).getDefenderID(0).getCivID()).getCivName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nData.add(new ME_Hover_2Type_Flag(CFG.core.getWar(WAR_ID).getDefenderID(0).getCivID(), CFG.PADD));
                        nData.add(new ME_Hover_2Type_Image(Images.diploWar, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    } else {
                        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Undiscovered"), CFG.COLOR_HOVER_TITLE));
                        nData.add(new ME_Hover_2Type_Flag(-1, CFG.PADD, 0));
                        nElements.add(new MEHover_2E(nData));
                        nData.clear();
                    }
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
            });
        }
        tempMaxY = 0;
        iSize = menuElements.size();
        for (i3 = 0; i3 < iSize; ++i3) {
            if (((MenuElemUI)menuElements.get(i3)).getPosY() + ((MenuElemUI)menuElements.get(i3)).getHeightE() <= tempMaxY) continue;
            tempMaxY = ((MenuElemUI)menuElements.get(i3)).getPosY() + ((MenuElemUI)menuElements.get(i3)).getHeightE();
        }
        boolean addAlliesNotInWar = false;
        if (CFG.core.getCiv(CFG.core.getWar(WAR_ID).getAggressorID(0).getCivID()).getAlliance() > 0) {
            for (i2 = 0; i2 < CFG.core.getAlliance(CFG.core.getCiv(CFG.core.getWar(WAR_ID).getAggressorID(0).getCivID()).getAlliance()).getCivilizationsSize(); ++i2) {
                if (CFG.core.getWar(WAR_ID).getIsInAggressors(CFG.core.getAlliance(CFG.core.getCiv(CFG.core.getWar(WAR_ID).getAggressorID(0).getCivID()).getAlliance()).getCivilization(i2))) continue;
                addAlliesNotInWar = true;
                break;
            }
        }
        if (!addAlliesNotInWar && CFG.core.getCiv(CFG.core.getWar(WAR_ID).getDefenderID(0).getCivID()).getAlliance() > 0) {
            for (i2 = 0; i2 < CFG.core.getAlliance(CFG.core.getCiv(CFG.core.getWar(WAR_ID).getDefenderID(0).getCivID()).getAlliance()).getCivilizationsSize(); ++i2) {
                if (CFG.core.getWar(WAR_ID).getIsInDefenders(CFG.core.getAlliance(CFG.core.getCiv(CFG.core.getWar(WAR_ID).getDefenderID(0).getCivID()).getAlliance()).getCivilization(i2))) continue;
                addAlliesNotInWar = true;
                break;
            }
        }
        if (!addAlliesNotInWar) {
            block10: for (i2 = 0; i2 < CFG.core.getWar(WAR_ID).getAggressorsSize(); ++i2) {
                if (CFG.core.getCiv(CFG.core.getWar(WAR_ID).getAggressorID(i2).getCivID()).getNumOfProvs() <= 0) continue;
                for (j = 1; j < CFG.core.getCivsSize(); ++j) {
                    if (j == CFG.core.getWar(WAR_ID).getAggressorID(i2).getCivID() || CFG.core.getCiv(j).getPuppetOfCiv() != CFG.core.getWar(WAR_ID).getAggressorID(i2).getCivID() && CFG.core.getCiv(CFG.core.getWar(WAR_ID).getAggressorID(i2).getCivID()).getPuppetOfCiv() != j || CFG.core.getWar(WAR_ID).getIsAggressor(j) || CFG.core.getCivsAreAllied(j, CFG.core.getWar(WAR_ID).getAggressorID(i2).getCivID())) continue;
                    addAlliesNotInWar = true;
                    continue block10;
                }
            }
        }
        if (!addAlliesNotInWar) {
            block12: for (i2 = 0; i2 < CFG.core.getWar(WAR_ID).getDefendersSize(); ++i2) {
                if (CFG.core.getCiv(CFG.core.getWar(WAR_ID).getDefenderID(i2).getCivID()).getNumOfProvs() <= 0) continue;
                for (j = 1; j < CFG.core.getCivsSize(); ++j) {
                    if (j == CFG.core.getWar(WAR_ID).getDefenderID(i2).getCivID() || CFG.core.getCiv(j).getPuppetOfCiv() != CFG.core.getWar(WAR_ID).getDefenderID(i2).getCivID() && CFG.core.getCiv(CFG.core.getWar(WAR_ID).getDefenderID(i2).getCivID()).getPuppetOfCiv() != j || CFG.core.getWar(WAR_ID).getIsDefender(j) || CFG.core.getCivsAreAllied(j, CFG.core.getWar(WAR_ID).getDefenderID(i2).getCivID())) continue;
                    addAlliesNotInWar = true;
                    continue block12;
                }
            }
        }
        if (addAlliesNotInWar) {
            int j2;
            tY = tempMaxY + CFG.PADD;
            menuElements.add(new TextAlliesNotInWar(CFG.lang.get("AlliesNotInWar"), -1, CFG.PADD, tY, tempWidth - CFG.PADD * 2, CFG.BUTTON_H / 2){

                @Override
                public int getPosXE() {
                    return 2;
                }

                @Override
                public int getWidthE() {
                    return Menu_InGame_WarDetails.this.getW();
                }
            });
            tempMaxY = tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
            int tempAdded = 0;
            if (CFG.core.getCiv(CFG.core.getWar(WAR_ID).getAggressorID(0).getCivID()).getAlliance() > 0) {
                for (i = 0; i < CFG.core.getAlliance(CFG.core.getCiv(CFG.core.getWar(WAR_ID).getAggressorID(0).getCivID()).getAlliance()).getCivilizationsSize(); ++i) {
                    if (CFG.core.getWar(WAR_ID).getIsInAggressors(CFG.core.getAlliance(CFG.core.getCiv(CFG.core.getWar(WAR_ID).getAggressorID(0).getCivID()).getAlliance()).getCivilization(i))) continue;
                    menuElements.add(new Button_Stats_CallAlly(CFG.FOG_OF_WAR != 2 || CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getMetCiv(CFG.core.getAlliance(CFG.core.getCiv(CFG.core.getWar(WAR_ID).getAggressorID(0).getCivID()).getAlliance()).getCivilization(i)) ? CFG.core.getAlliance(CFG.core.getCiv(CFG.core.getWar(WAR_ID).getAggressorID(0).getCivID()).getAlliance()).getCivilization(i) : -1, 2, tY, CFG.BUTTON_W * 2, CFG.core.getWar(WAR_ID).getIsDefender(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())){

                        @Override
                        public int getWidthE() {
                            return Menu_InGame_WarDetails.this.getElementW() * 5 + 2;
                        }

                        @Override
                        public void actionElem(int iID) {
                            if (WAR_ID >= 0 && WAR_ID < CFG.core.getWarsSize()) {
                                if (this.getCurr() == CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) {
                                    CFG.menus.rebuildInGame_JoinAWar(CFG.core.getWar(WAR_ID).getAggressorID(0).getCivID(), CFG.core.getWar(WAR_ID).getDefenderID(0).getCivID());
                                } else if (CFG.core.getWar(WAR_ID).getIsAggressor(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())) {
                                    CFG.menus.rebuildInGame_CallToArms(CFG.core.getWar(WAR_ID).getDefenderID(0).getCivID());
                                } else if (CFG.core.getWar(WAR_ID).getIsDefender(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())) {
                                    CFG.menus.rebuildInGame_DeclareWar(this.getCurr());
                                }
                            }
                        }
                    });
                    ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(tempAdded++ % 2);
                    ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setClickable(CFG.core.getWar(WAR_ID).getIsAggressor(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) || CFG.core.getAlliance(CFG.core.getCiv(CFG.core.getWar(WAR_ID).getAggressorID(0).getCivID()).getAlliance()).getCivilization(i) == CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId() || CFG.core.getWar(WAR_ID).getIsDefender(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()));
                    tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
                }
            }
            for (i = 0; i < CFG.core.getWar(WAR_ID).getAggressorsSize(); ++i) {
                if (CFG.core.getCiv(CFG.core.getWar(WAR_ID).getAggressorID(i).getCivID()).getNumOfProvs() <= 0) continue;
                for (j2 = 1; j2 < CFG.core.getCivsSize(); ++j2) {
                    if (j2 == CFG.core.getWar(WAR_ID).getAggressorID(i).getCivID() || CFG.core.getCiv(j2).getPuppetOfCiv() != CFG.core.getWar(WAR_ID).getAggressorID(i).getCivID() || CFG.core.getCivsAreAllied(j2, CFG.core.getWar(WAR_ID).getAggressorID(i).getCivID()) || CFG.core.getWar(WAR_ID).getIsInAggressors(j2)) continue;
                    menuElements.add(new Button_Stats_CallAlly(CFG.FOG_OF_WAR != 2 || CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getMetCiv(j2) ? j2 : -1, 2, tY, CFG.BUTTON_W * 2, CFG.core.getWar(WAR_ID).getIsDefender(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())){

                        @Override
                        public int getWidthE() {
                            return Menu_InGame_WarDetails.this.getElementW() * 5 + 2;
                        }

                        @Override
                        public void actionElem(int iID) {
                            if (WAR_ID >= 0 && WAR_ID < CFG.core.getWarsSize()) {
                                if (this.getCurr() == CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) {
                                    CFG.menus.rebuildInGame_JoinAWar(CFG.core.getWar(WAR_ID).getAggressorID(0).getCivID(), CFG.core.getWar(WAR_ID).getDefenderID(0).getCivID());
                                } else if (CFG.core.getWar(WAR_ID).getIsAggressor(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())) {
                                    CFG.menus.rebuildInGame_CallToArms(CFG.core.getWar(WAR_ID).getDefenderID(0).getCivID());
                                } else if (CFG.core.getWar(WAR_ID).getIsDefender(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())) {
                                    CFG.menus.rebuildInGame_DeclareWar(this.getCurr());
                                }
                            }
                        }
                    });
                    ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(tempAdded++ % 2);
                    ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setClickable(CFG.core.getWar(WAR_ID).getIsAggressor(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) || j2 == CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId() || CFG.core.getWar(WAR_ID).getIsDefender(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()));
                    tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
                }
            }
            tempAdded = 0;
            tY = tempMaxY;
            if (CFG.core.getCiv(CFG.core.getWar(WAR_ID).getDefenderID(0).getCivID()).getAlliance() > 0) {
                for (i = 0; i < CFG.core.getAlliance(CFG.core.getCiv(CFG.core.getWar(WAR_ID).getDefenderID(0).getCivID()).getAlliance()).getCivilizationsSize(); ++i) {
                    if (CFG.core.getWar(WAR_ID).getIsInDefenders(CFG.core.getAlliance(CFG.core.getCiv(CFG.core.getWar(WAR_ID).getDefenderID(0).getCivID()).getAlliance()).getCivilization(i))) continue;
                    menuElements.add(new Button_Stats_CallAlly_Right(CFG.FOG_OF_WAR != 2 || CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getMetCiv(CFG.core.getAlliance(CFG.core.getCiv(CFG.core.getWar(WAR_ID).getDefenderID(0).getCivID()).getAlliance()).getCivilization(i)) ? CFG.core.getAlliance(CFG.core.getCiv(CFG.core.getWar(WAR_ID).getDefenderID(0).getCivID()).getAlliance()).getCivilization(i) : -1, 2, tY, CFG.BUTTON_W * 2, CFG.core.getWar(WAR_ID).getIsAggressor(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())){

                        @Override
                        public int getPosXE() {
                            return Menu_InGame_WarDetails.this.getElementW() * 5 + 4;
                        }

                        @Override
                        public int getWidthE() {
                            return Menu_InGame_WarDetails.this.getW() - Menu_InGame_WarDetails.this.getElementW() * 6 - 4 + Menu_InGame_WarDetails.this.getElementW() + 2;
                        }

                        @Override
                        public void actionElem(int iID) {
                            if (WAR_ID >= 0 && WAR_ID < CFG.core.getWarsSize()) {
                                if (this.getCurr() == CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) {
                                    CFG.menus.rebuildInGame_JoinAWar(CFG.core.getWar(WAR_ID).getDefenderID(0).getCivID(), CFG.core.getWar(WAR_ID).getAggressorID(0).getCivID());
                                } else if (CFG.core.getWar(WAR_ID).getIsAggressor(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())) {
                                    CFG.menus.rebuildInGame_DeclareWar(this.getCurr());
                                } else if (CFG.core.getWar(WAR_ID).getIsDefender(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())) {
                                    CFG.menus.rebuildInGame_CallToArms(CFG.core.getWar(WAR_ID).getAggressorID(0).getCivID());
                                }
                            }
                        }
                    });
                    ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(tempAdded++ % 2);
                    ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setClickable(CFG.core.getWar(WAR_ID).getIsDefender(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) || CFG.core.getAlliance(CFG.core.getCiv(CFG.core.getWar(WAR_ID).getDefenderID(0).getCivID()).getAlliance()).getCivilization(i) == CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId() || CFG.core.getWar(WAR_ID).getIsAggressor(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()));
                    tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
                }
            }
            for (i = 0; i < CFG.core.getWar(WAR_ID).getDefendersSize(); ++i) {
                if (CFG.core.getCiv(CFG.core.getWar(WAR_ID).getDefenderID(i).getCivID()).getNumOfProvs() <= 0) continue;
                for (j2 = 1; j2 < CFG.core.getCivsSize(); ++j2) {
                    if (j2 == CFG.core.getWar(WAR_ID).getDefenderID(i).getCivID() || CFG.core.getCiv(j2).getPuppetOfCiv() != CFG.core.getWar(WAR_ID).getDefenderID(i).getCivID() || CFG.core.getCivsAreAllied(j2, CFG.core.getWar(WAR_ID).getDefenderID(i).getCivID()) || CFG.core.getWar(WAR_ID).getIsInDefenders(j2)) continue;
                    menuElements.add(new Button_Stats_CallAlly_Right(CFG.FOG_OF_WAR != 2 || CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getMetCiv(j2) ? j2 : -1, 2, tY, CFG.BUTTON_W * 2, CFG.core.getWar(WAR_ID).getIsAggressor(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())){

                        @Override
                        public int getPosXE() {
                            return Menu_InGame_WarDetails.this.getElementW() * 5 + 4;
                        }

                        @Override
                        public int getWidthE() {
                            return Menu_InGame_WarDetails.this.getW() - Menu_InGame_WarDetails.this.getElementW() * 6 - 4 + Menu_InGame_WarDetails.this.getElementW() + 2;
                        }

                        @Override
                        public void actionElem(int iID) {
                            if (WAR_ID >= 0 && WAR_ID < CFG.core.getWarsSize()) {
                                if (this.getCurr() == CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) {
                                    CFG.menus.rebuildInGame_JoinAWar(CFG.core.getWar(WAR_ID).getDefenderID(0).getCivID(), CFG.core.getWar(WAR_ID).getAggressorID(0).getCivID());
                                } else if (CFG.core.getWar(WAR_ID).getIsAggressor(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())) {
                                    CFG.menus.rebuildInGame_DeclareWar(this.getCurr());
                                } else if (CFG.core.getWar(WAR_ID).getIsDefender(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())) {
                                    CFG.menus.rebuildInGame_CallToArms(CFG.core.getWar(WAR_ID).getAggressorID(0).getCivID());
                                }
                            }
                        }
                    });
                    ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(tempAdded++ % 2);
                    ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setClickable(CFG.core.getWar(WAR_ID).getIsDefender(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) || j2 == CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId() || CFG.core.getWar(WAR_ID).getIsAggressor(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()));
                    tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
                }
            }
        }
        tempMaxY = 0;
        int iSize2 = menuElements.size();
        for (i2 = 0; i2 < iSize2; ++i2) {
            if (((MenuElemUI)menuElements.get(i2)).getPosY() + ((MenuElemUI)menuElements.get(i2)).getHeightE() <= tempMaxY) continue;
            tempMaxY = ((MenuElemUI)menuElements.get(i2)).getPosY() + ((MenuElemUI)menuElements.get(i2)).getHeightE();
        }
        if (CFG.core.getWar(WAR_ID).getIsAggressor(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) || CFG.core.getWar(WAR_ID).getIsDefender(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId())) {
            menuElements.add(new Button_InGameAction_Animated(CFG.lang.get("PeaceNegotiations"), -1, 2, tempMaxY += CFG.PADD, CFG.BUTTON_W, true){

                @Override
                public int getPosXE() {
                    return 2 + CFG.PADD;
                }

                @Override
                public int getWidthE() {
                    return Menu_InGame_WarDetails.this.getW() - CFG.PADD * 2;
                }

                @Override
                public void drawTextE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    IMGManager.getIMG(Images.diploTruce).draw(oSB, this.getPosXE() + this.getWidthE() / 2 - CFG.PADD - IMGManager.getIMG(Images.diploTruce).getWidth() / 2 - this.getTextWidthU() / 2 + iTranslateX, this.getPosY() + this.getHeightE() / 2 - IMGManager.getIMG(Images.diploTruce).getHeight() / 2 + iTranslateY);
                    Renderer.drawText(oSB, this.fontID, this.getTextE(), this.getPosXE() + (this.getTextPosElem() < 0 ? this.getWidthE() / 2 - (this.getTextWidthU() + IMGManager.getIMG(Images.diploTruce).getWidth() + CFG.PADD) / 2 + IMGManager.getIMG(Images.diploTruce).getWidth() + CFG.PADD : this.getTextPosElem()) + iTranslateX, this.getPosY() + this.getHeightE() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColorE(isActive));
                }

                @Override
                public void actionElem(int iID) {
                    CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).iBefore_ActiveProvince = CFG.core.getActiveProvID();
                    CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).iACTIVE_VIEW_MODE = CFG.mapModesManager.getActiveMapModeID();
                    CFG.mapModesManager.disableAllViews();
                    Menu_PeaceTreaty.WAR_ID = WAR_ID;
                    CFG.peaceTreatyData = new PeaceTreaty_Data(Menu_PeaceTreaty.WAR_ID, CFG.core.getWar(WAR_ID).getIsAggressor(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()));
                    CFG.core.resetChooseProvinceData_Immediately();
                    CFG.core.resetRegroupArmy_Data();
                    CFG.menus.setMenuID(View.eINGAME_PEACE_TREATY);
                }

                @Override
                public int getSFXElem() {
                    return SFXManager.SFX_CLICK2;
                }

                @Override
                public void buildElemHover() {
                    int i;
                    ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                    ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                    nData.add(new ME_Hover_2Type_Text(CFG.lang.get("PeaceNegotiations"), CFG.COLOR_HOVER_TITLE));
                    for (i = 0; i < CFG.core.getWar(WAR_ID).getAggressorsSize() && i < 5; ++i) {
                        nData.add(new ME_Hover_2Type_Flag(CFG.core.getWar(WAR_ID).getAggressorID(i).getCivID(), i == 0 ? CFG.PADD : 0, 0));
                    }
                    nData.add(new ME_Hover_2Type_Image(Images.diploTruce, CFG.PADD, 0));
                    for (i = 0; i < CFG.core.getWar(WAR_ID).getDefendersSize() && i < 5; ++i) {
                        nData.add(new ME_Hover_2Type_Flag(CFG.core.getWar(WAR_ID).getDefenderID(i).getCivID(), i == 0 ? CFG.PADD : 0, 0));
                    }
                    nElements.add(new MEHover_2E(nData));
                    nData.clear();
                    this.menuElemHover = new ME_Hover_v2(nElements);
                }
            });
            tempMaxY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        }
        menuElements.add(new Button_Transparent(0, 0, tempWidth, tempMaxY, true){

            @Override
            public int getPosXE() {
                return 2;
            }

            @Override
            public int getWidthE() {
                return Menu_InGame_WarDetails.this.getW();
            }
        });
        int tempMenuPosY = IMGManager.getIMG(Images.topFlagFrame).getHeight() + CFG.PADD * 4 + CFG.BUTTON_H * 3 / 4 + CFG.PADD * 2 + CFG.BUTTON_H / 2;
        this.initMenu(new TitleM_TextSmall(CFG.FOG_OF_WAR == 2 ? (CFG.core.getCiv(CFG.core.getWar(WAR_ID).getAggressorID(0).getCivID()).getAlliance() > 0 ? (CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getMetAlliance(CFG.core.getCiv(CFG.core.getWar(WAR_ID).getAggressorID(0).getCivID()).getAlliance()) ? CFG.core.getAlliance(CFG.core.getCiv(CFG.core.getWar(WAR_ID).getAggressorID(0).getCivID()).getAlliance()).getAllianceName() : CFG.lang.get("Undiscovered")) : (CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getMetCiv(CFG.core.getWar(WAR_ID).getAggressorID(0).getCivID()) ? CFG.core.getCiv(CFG.core.getWar(WAR_ID).getAggressorID(0).getCivID()).getCivName() : CFG.lang.get("Undiscovered"))) : (CFG.core.getCiv(CFG.core.getWar(WAR_ID).getAggressorID(0).getCivID()).getAlliance() > 0 ? CFG.core.getAlliance(CFG.core.getCiv(CFG.core.getWar(WAR_ID).getAggressorID(0).getCivID()).getAlliance()).getAllianceName() : CFG.core.getCiv(CFG.core.getWar(WAR_ID).getAggressorID(0).getCivID()).getCivName()), CFG.BUTTON_H * 3 / 4, true, true){

            @Override
            public void drawT(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                IMGManager.getIMG(Images.gameTopEdge).draw2O(oSB, nPosX - Core.PADDING + iTranslateX, nPosY - this.getHeightT() - Core.PADDING - IMGManager.getIMG(Images.gameTopEdge).getHeight(), nWidth - IMGManager.getIMG(Images.gameTopEdge).getWidth() + Core.PADDING * 2, this.getHeightT() + Core.PADDING);
                IMGManager.getIMG(Images.gameTopEdge).draw2O(oSB, nPosX + Core.PADDING + nWidth - IMGManager.getIMG(Images.gameTopEdge).getWidth() + iTranslateX, nPosY - Core.PADDING - this.getHeightT() - IMGManager.getIMG(Images.gameTopEdge).getHeight(), IMGManager.getIMG(Images.gameTopEdge).getWidth(), this.getHeightT() + Core.PADDING, true, false);
                oSB.setColor(new Color(0.5411765f, 0.050980393f, 0.050980393f, 0.165f));
                IMGManager.getIMG(Images.line32Off1).drawO(oSB, nPosX + 2 + iTranslateX, nPosY - this.getHeightT() + 2 - IMGManager.getIMG(Images.line32Off1).getHeight(), nWidth - 4, this.getHeightT() - 2, false, true);
                oSB.setColor(new Color(0.5411765f, 0.050980393f, 0.050980393f, 0.375f));
                IMGManager.getIMG(Images.gradient).drawO(oSB, nPosX + 2 + iTranslateX, nPosY - this.getHeightT() * 2 / 3 - IMGManager.getIMG(Images.gradient).getHeight(), nWidth - 4, this.getHeightT() * 2 / 3, false, true);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
                IMGManager.getIMG(Images.gradient).drawO(oSB, nPosX + 2 + iTranslateX, nPosY - CFG.PADD - IMGManager.getIMG(Images.gradient).getHeight(), nWidth - 4, CFG.PADD, false, true);
                oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
                IMGManager.getIMG(Images.pix255).drawO(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - IMGManager.getIMG(Images.pix255).getHeight(), nWidth - 4, 1);
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45f));
                IMGManager.getIMG(Images.sliderGradient).drawO(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - IMGManager.getIMG(Images.sliderGradient).getHeight(), (nWidth - 4) / 2, 1);
                IMGManager.getIMG(Images.sliderGradient).drawO(oSB, nPosX + 2 + (nWidth - 4) - (nWidth - 4) / 2 + iTranslateX, nPosY - 1 - IMGManager.getIMG(Images.sliderGradient).getHeight(), (nWidth - 4) / 2, 1, true, false);
                oSB.setColor(Color.WHITE);
                IMGManager.getIMG(Images.diploRivals).drawO(oSB, nPosX + nWidth / 2 - IMGManager.getIMG(Images.diploRivals).getWidth() / 2 + iTranslateX, 2 + nPosY - this.getHeightT() + this.getHeightT() / 2 - IMGManager.getIMG(Images.diploRivals).getHeight() / 2);
                try {
                    int posX = nPosX + nWidth / 2 - IMGManager.getIMG(Images.diploRivals).getWidth() / 2 - CFG.PADD - IMGManager.getIMG(Images.flagRect2).getWidth() + iTranslateX;
                    int posY = 2 + nPosY - this.getHeightT() + this.getHeightT() / 2 - IMGManager.getIMG(Images.flagRect2).getHeight() / 2;
                    oSB.setShader(Renderer.shaderAlpha);
                    if (Menu_InGame_WarDetails.this.civLeft >= 0 && Menu_InGame_WarDetails.this.civLeft < CFG.core.getCivsSize()) {
                        CFG.core.getCiv(Menu_InGame_WarDetails.this.civLeft).getFlagC().getTexture().bind(1);
                    } else {
                        IMGManager.getIMG(Images.randomCivilizationFlag).getTexture().bind(1);
                    }
                    Gdx.gl.glActiveTexture(33984);
                    IMGManager.getIMG(Images.flagRect2Mask).draw(oSB, posX, posY, IMGManager.getIMG(Images.flagRect2Mask).getWidth(), IMGManager.getIMG(Images.flagRect2Mask).getHeight());
                    oSB.flush();
                    oSB.setShader(AoCGame.shaderDef);
                    IMGManager.getIMG(Images.flagRect2).draw(oSB, posX, posY, IMGManager.getIMG(Images.flagRect2).getWidth(), IMGManager.getIMG(Images.flagRect2).getHeight());
                    posX = nPosX + nWidth / 2 + IMGManager.getIMG(Images.diploRivals).getWidth() / 2 + CFG.PADD + iTranslateX;
                    oSB.setShader(Renderer.shaderAlpha);
                    if (Menu_InGame_WarDetails.this.civRight >= 0 && Menu_InGame_WarDetails.this.civRight < CFG.core.getCivsSize()) {
                        CFG.core.getCiv(Menu_InGame_WarDetails.this.civRight).getFlagC().getTexture().bind(1);
                    } else {
                        IMGManager.getIMG(Images.randomCivilizationFlag).getTexture().bind(1);
                    }
                    Gdx.gl.glActiveTexture(33984);
                    IMGManager.getIMG(Images.flagRect2Mask).draw(oSB, posX, posY, IMGManager.getIMG(Images.flagRect2Mask).getWidth(), IMGManager.getIMG(Images.flagRect2Mask).getHeight());
                    oSB.flush();
                    oSB.setShader(AoCGame.shaderDef);
                    IMGManager.getIMG(Images.flagRect2).draw(oSB, posX, posY, IMGManager.getIMG(Images.flagRect2).getWidth(), IMGManager.getIMG(Images.flagRect2).getHeight());
                }
                catch (Exception exception) {
                    // empty catch block
                }
                Renderer.drawText(oSB, CFG.FONT_BOLD_SMALL, this.getText(), nPosX + nWidth / 2 - this.getTextWidth() - IMGManager.getIMG(Images.diploRivals).getWidth() / 2 - CFG.PADD * 2 - IMGManager.getIMG(Images.flagRect2).getWidth() + iTranslateX, 2 + nPosY - this.getHeightT() + (this.getHeightT() - this.getTextHeight()) / 2, Color.WHITE);
                Renderer.drawText(oSB, CFG.FONT_BOLD_SMALL, Menu_InGame_WarDetails.this.sDefender, nPosX + nWidth / 2 + IMGManager.getIMG(Images.diploRivals).getWidth() / 2 + CFG.PADD * 2 + IMGManager.getIMG(Images.flagRect2).getWidth() + iTranslateX, 2 + nPosY - this.getHeightT() + (this.getHeightT() - this.getTextHeight()) / 2, Color.WHITE);
                IMGManager.getIMG(Images.time).drawO(oSB, nPosX + nWidth - CFG.PADD - 2 - (int)((float)IMGManager.getIMG(Images.time).getWidth() * Menu_InGame_WarDetails.this.getImageScale2(Images.time)) + iTranslateX, nPosY - CFG.PADD - (int)((float)IMGManager.getIMG(Images.time).getHeight() * Menu_InGame_WarDetails.this.getImageScale2(Images.time)) - IMGManager.getIMG(Images.time).getHeight(), (int)((float)IMGManager.getIMG(Images.time).getWidth() * Menu_InGame_WarDetails.this.getImageScale2(Images.time)), (int)((float)IMGManager.getIMG(Images.time).getHeight() * Menu_InGame_WarDetails.this.getImageScale2(Images.time)));
                Renderer.drawText(oSB, CFG.FONT_BOLD_SMALL, Menu_InGame_WarDetails.this.sWarDate, nPosX + nWidth - Menu_InGame_WarDetails.this.iWarDateWidth - CFG.PADD * 2 - (int)((float)IMGManager.getIMG(Images.time).getWidth() * Menu_InGame_WarDetails.this.getImageScale2(Images.time)) - 2 + iTranslateX, nPosY - CFG.PADD - CFG.TEXT_HEIGHT_DEFAULT_SMALL, CFG.COLOR_NEUTRAL);
            }
        }, CFG.GAMEWIDTH / 2 - tempWidth * 3 / 4, tempMenuPosY, tempWidth, ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD + tempMenuPosY > CFG.GAMEHEIGHT - CFG.BUTTON_H - CFG.PADD * 2 ? Math.max(CFG.GAMEHEIGHT - CFG.BUTTON_H - CFG.PADD * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 2) * 6) : ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, menuElements, true, true);
        this.updateLang();
        for (i = 0; i < this.getMenuElemsSize() && i < CFG.core.getWar(WAR_ID).getAggressorsSize() * 2; ++i) {
            this.getMenuElem(i).setCurr(i / 2 % 2);
        }
        for (i = 4 + CFG.core.getWar(WAR_ID).getAggressorsSize() * 2; i < this.getMenuElemsSize() && i < 4 + CFG.core.getWar(WAR_ID).getAggressorsSize() * 2 + CFG.core.getWar(WAR_ID).getDefendersSize(); ++i) {
            this.getMenuElem(i).setCurr((i / 2 + (CFG.core.getWar(WAR_ID).getAggressorsSize() + 1) % 2) % 2);
        }
    }

    @Override
    public void updateLang() {
    }

    private final float getImageScale3(int nImageID) {
        return (float)CFG.TEXT_HEIGHT_DEFAULT * 1.0f / (float)IMGManager.getIMG(nImageID).getHeight();
    }

    private final float getImageScale2(int nImageID) {
        return (float)CFG.TEXT_HEIGHT_DEFAULT / (float)IMGManager.getIMG(nImageID).getHeight() < 1.0f ? (float)CFG.TEXT_HEIGHT_DEFAULT / (float)IMGManager.getIMG(nImageID).getHeight() : 1.0f;
    }

    private final void clickFlag(int iID) {
        try {
            CFG.toastM.addM(CFG.core.getCiv(this.getMenuElem(iID).getCurr()).getCivName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            if (CFG.FOG_OF_WAR == 2) {
                if (CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getMetCiv(this.getMenuElem(iID).getCurr()) && CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getMetProv(CFG.core.getCiv(this.getMenuElem(iID).getCurr()).getCapitalProvID())) {
                    CFG.core.setActiveProvID(CFG.core.getCiv(this.getMenuElem(iID).getCurr()).getCapitalProvID());
                    CFG.map.getMpC().centerToProvID(CFG.core.getActiveProvID());
                }
            } else {
                CFG.core.setActiveProvID(CFG.core.getCiv(this.getMenuElem(iID).getCurr()).getCapitalProvID());
                CFG.map.getMpC().centerToProvID(CFG.core.getActiveProvID());
            }
            if (CFG.mapModesManager.getActiveMapModeID() == MapModesManager.VIEW_DIPLOMACY_MODE) {
                CFG.core.disableDrawCivilizationRegions_Active();
                CFG.core.enableDrawCivilizationRegions_ActiveProvince();
            }
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        oSB.setColor(Color.WHITE);
        IMGManager.getIMG(Images.gameTopEdge).draw2O(oSB, this.getPosX() - Core.PADDING + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gameTopEdge).getHeight() + iTranslateY, this.getWidthM() + Core.PADDING * 2 - IMGManager.getIMG(Images.gameTopEdge).getWidth(), this.getHeightM() + CFG.PADD, false, true);
        IMGManager.getIMG(Images.gameTopEdge).draw2O(oSB, this.getPosX() + this.getWidthM() + Core.PADDING - IMGManager.getIMG(Images.gameTopEdge).getWidth() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gameTopEdge).getHeight() + iTranslateY, IMGManager.getIMG(Images.gameTopEdge).getWidth(), this.getHeightM() + CFG.PADD, true, true);
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
    }

    @Override
    public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    public void drawCloseButton(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        this.getCloseButtonImage(sliderMenuIsActive).drawO(oSB, this.getPosX() + this.getWidthM() - IMGManager.getIMG(Images.btnClose).getWidth() * 3 / 5 + iTranslateX, this.getPosY() - this.getTitleM().getHeightT() - IMGManager.getIMG(Images.btnClose).getHeight() + iTranslateY, IMGManager.getIMG(Images.btnClose).getWidth() * 3 / 5, IMGManager.getIMG(Images.btnClose).getHeight() * 3 / 5);
    }

    @Override
    public final void actionEL(int iID) {
        if (iID == this.getMenuElemsSize() - 1) {
            return;
        }
        this.getMenuElem(iID).actionElem(iID);
    }

    public final int getW() {
        return this.getWidthM() - 4;
    }

    public final int getElementW() {
        return this.getW() / 10;
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
