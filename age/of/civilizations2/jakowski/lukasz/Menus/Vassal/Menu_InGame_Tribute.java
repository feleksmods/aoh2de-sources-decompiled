package age.of.civilizations2.jakowski.lukasz.Menus.Vassal;

import age.of.civilizations2.jakowski.lukasz.Button.Button_Tribute;
import age.of.civilizations2.jakowski.lukasz.Button.Button_Tribute_Vassal;
import age.of.civilizations2.jakowski.lukasz.Button.Diplomacy.Action.Button_DiplomacyAction_XV;
import age.of.civilizations2.jakowski.lukasz.Button.Diplomacy.ButtonDiplomacy;
import age.of.civilizations2.jakowski.lukasz.Button.Flag.Button_InGameAction;
import age.of.civilizations2.jakowski.lukasz.Button.GameN.ButtonN_Civs;
import age.of.civilizations2.jakowski.lukasz.Button.MenuElemUI;
import age.of.civilizations2.jakowski.lukasz.Button2.Text_Desc;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Colors;
import age.of.civilizations2.jakowski.lukasz.Core.Core;
import age.of.civilizations2.jakowski.lukasz.GameValues.GameValues;
import age.of.civilizations2.jakowski.lukasz.IMGManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.Menus.Messages.Diplomacy.Menu_InGame_Message_Alliance;
import age.of.civilizations2.jakowski.lukasz.Menus.Relations.Actions.Menu_InGameOfferAlliance;
import age.of.civilizations2.jakowski.lukasz.Menus.ZRest.Menu_InGame_2;
import age.of.civilizations2.jakowski.lukasz.Renderer;
import age.of.civilizations2.jakowski.lukasz.Sliders.InGame.Clear.Slider_InGame_Clear_Flag_Tribute;
import age.of.civilizations2.jakowski.lukasz.Sliders.InGame.Slider_InGame_Army;
import age.of.civilizations2.jakowski.lukasz.TextB.Texts.TextBuildTitle;
import age.of.civilizations2.jakowski.lukasz.TextB.Texts.TextScale;
import age.of.civilizations2.jakowski.lukasz.Title.TitleM_TextSmall;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

public class Menu_InGame_Tribute
extends Menu {
    public Menu_InGame_Tribute() {
        ArrayList<MenuElemUI> menuElements = new ArrayList<MenuElemUI>();
        int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
        int tempMenuPosY = IMGManager.getIMG(Images.topBar).getHeight() + CFG.PADD * 2 + CFG.BUTTON_H * 3 / 5 + CFG.PADD * 2;
        if (tempWidth > CFG.GAMEWIDTH) {
            tempWidth = CFG.GAMEWIDTH - CFG.PADD * 2;
        }
        this.initMenu(null, CFG.GAMEWIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, CFG.GAMEHEIGHT * 3 / 5, menuElements, false, false);
    }

    public Menu_InGame_Tribute(int onCivID) {
        ArrayList<MenuElemUI> menuElements = new ArrayList<MenuElemUI>();
        int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
        int tY = 0;
        menuElements.add(new Button_Tribute(CFG.lang.get("Vassals") + ": ", CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).getIdeology(), 2, tY, CFG.BUTTON_W * 2){

            @Override
            public int getWidthE() {
                return Menu_InGame_Tribute.this.getElementW() * 2;
            }
        });
        tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        menuElements.add(new Text_Desc(CFG.lang.get("VassalsLibertyDesireDesc"), 2, tY += CFG.PADD, tempWidth - 4){

            @Override
            protected Color getColor(boolean isActive) {
                return Colors.getColorButtonHover2(isActive, this.getIsHovered());
            }

            @Override
            public int getWidthE() {
                return Menu_InGame_Tribute.this.getElementW() * 2;
            }
        });
        menuElements.add(new TextBuildTitle(CFG.lang.get("Wars"), -1, 2, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, CFG.BUTTON_W, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 4){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_GRAY_NS_HOVER : (this.getIsClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_GRAY_NS : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }

            @Override
            public int getWidthE() {
                return Menu_InGame_Tribute.this.getElementW() * 2;
            }
        });
        int tempElemH = CFG.BUTTON_H * 3 / 4;
        menuElements.add(new Button_DiplomacyAction_XV(Images.diploWar, CFG.lang.get("Vassals") + ": " + CFG.lang.get("CanDeclareWars"), 0, 2, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE(), CFG.BUTTON_W, tempElemH, true, CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).playerGD.VASSALS_CAN_DECLARE_WARS){

            @Override
            public void actionElem(int iID) {
                CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).playerGD.VASSALS_CAN_DECLARE_WARS = !CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).playerGD.VASSALS_CAN_DECLARE_WARS;
                CFG.menus.rebuildInGame_Tribute();
            }

            @Override
            public int getWidthE() {
                return Menu_InGame_Tribute.this.getElementW() * 2;
            }
        });
        tY += tempElemH;
        if (!CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).civGD.vassals.isEmpty()) {
            menuElements.add(new TextBuildTitle(CFG.lang.get("Vassals") + ": " + CFG.lang.get("MilitarySpendings"), -1, 2, tY, CFG.BUTTON_W, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 4){

                @Override
                public Color getColor(boolean isActive) {
                    return isActive ? CFG.COLOR_TEXT_GRAY_NS_HOVER : (this.getIsClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_GRAY_NS : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
                }

                @Override
                public int getWidthE() {
                    return Menu_InGame_Tribute.this.getElementW() * 2;
                }
            });
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
            menuElements.add(new Text_Desc(CFG.lang.get("VassalMilitaryDesc"), 2, tY += CFG.PADD, tempWidth - 4){

                @Override
                protected Color getColor(boolean isActive) {
                    return Colors.getColorButtonHover2(isActive, this.getIsHovered());
                }

                @Override
                public int getWidthE() {
                    return Menu_InGame_Tribute.this.getElementW() * 2;
                }
            });
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD;
            menuElements.add(new Slider_InGame_Army(CFG.lang.get("MilitarySpendings"), CFG.PADD * 2, ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE(), tempWidth - CFG.PADD * 3 - CFG.BUTTON_W, Math.max(CFG.BUTTON_H * 3 / 4, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 6), -1, 100, CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).playerGD.VASSALS_MILITARY_SPENDINGS < 0.0f ? -1 : (int)(CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).playerGD.VASSALS_MILITARY_SPENDINGS * 100.0f), 0.65f){

                @Override
                public void actionElem(int iID) {
                    CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).playerGD.VASSALS_MILITARY_SPENDINGS = (float)this.getCurr() / 100.0f;
                    Menu_InGame_Tribute.updateVassalsSpendings();
                }

                @Override
                public String getDrawText() {
                    return this.getCurr() < 0 ? CFG.lang.get("Default") : this.getCurr() + "%";
                }

                @Override
                public int getWidthE() {
                    return Math.max(Menu_InGame_Tribute.this.getElementW() * 2 - CFG.PADD * 2, 0);
                }

                @Override
                public int getPosXE() {
                    return CFG.PADD;
                }

                @Override
                public int getSliderHeight() {
                    return CFG.PADD * 2;
                }

                @Override
                public Color getColorLEFT() {
                    return new Color(CFG.COLOR_ARMY_TEXT_ALLIANCE.r, CFG.COLOR_ARMY_TEXT_ALLIANCE.g, CFG.COLOR_ARMY_TEXT_ALLIANCE.b, 0.65f);
                }
            });
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD;
        }
        menuElements.add(new TextBuildTitle(CFG.lang.get("Tribute"), -1, 2, tY, CFG.BUTTON_W, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 4){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_GRAY_NS_HOVER : (this.getIsClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_GRAY_NS : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }

            @Override
            public int getWidthE() {
                return Menu_InGame_Tribute.this.getElementW() * 2;
            }
        });
        tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        menuElements.add(new Text_Desc(CFG.lang.get("VassalsTributeDesc"), 2, tY += CFG.PADD, tempWidth - 4){

            @Override
            protected Color getColor(boolean isActive) {
                return Colors.getColorButtonHover2(isActive, this.getIsHovered());
            }

            @Override
            public int getWidthE() {
                return Menu_InGame_Tribute.this.getElementW() * 2;
            }
        });
        tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD;
        if (CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).civGD.vassals.isEmpty()) {
            menuElements.add(new TextScale(CFG.lang.get("-----"), -1, 2, tY, tempWidth - 4, CFG.BUTTON_H * 3 / 4, 0.75f){

                @Override
                public int getWidthE() {
                    return Menu_InGame_Tribute.this.getElementW() * 2;
                }
            });
            ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setClickable(false);
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
        } else {
            for (int i = 0; i < CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).civGD.vassals.size(); ++i) {
                if (GameValues.gvInGame.VASSALS_TRIBUTE_MENU_SHOWS_CIVS_STATS) {
                    menuElements.add(new ButtonN_Civs(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).civGD.vassals.get((int)i).iCivID, 2, tY, tempWidth){

                        @Override
                        public int getWidthE() {
                            return Menu_InGame_Tribute.this.getElementW() * 2;
                        }
                    });
                    ((MenuElemUI)menuElements.get(menuElements.size() - 1)).setCurr(1);
                    tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE();
                }
                menuElements.add(new Button_Tribute_Vassal("", CFG.core.getCiv(CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).civGD.vassals.get((int)i).iCivID).getIdeology(), 2, tY, CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).civGD.vassals.get((int)i).iCivID));
                menuElements.add(new Slider_InGame_Clear_Flag_Tribute(CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).civGD.vassals.get((int)i).iCivID, CFG.core.getCiv(CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).civGD.vassals.get((int)i).iCivID).getCivName(), CFG.PADD * 2, tY, tempWidth - CFG.PADD * 3 - CFG.BUTTON_W, CFG.BUTTON_H * 3 / 4, GameValues.gvVassal.PERCENTAGE_OF_INCOME_FOR_LORD_MIN, GameValues.gvVassal.PERCENTAGE_OF_INCOME_FOR_LORD_MAX, CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).civGD.vassals.get((int)i).iTribute){

                    @Override
                    public int getWidthE() {
                        return Math.max(Menu_InGame_Tribute.this.getElementW() * 2 - ButtonDiplomacy.iDiploWidth - CFG.PADD * 2, 0);
                    }

                    @Override
                    public int getPosXE() {
                        return 2 + ButtonDiplomacy.iDiploWidth + CFG.PADD;
                    }

                    @Override
                    public int getSliderHeight() {
                        return CFG.PADD + CFG.PADD / 2;
                    }

                    @Override
                    public Color getColorLEFT() {
                        return new Color((float)CFG.core.getCiv(this.iCivID).getR() / 255.0f, (float)CFG.core.getCiv(this.iCivID).getG() / 255.0f, (float)CFG.core.getCiv(this.iCivID).getB() / 255.0f, 0.65f);
                    }

                    @Override
                    public void actionElem(int iID) {
                        super.actionElem(iID);
                        CFG.core.getCiv(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()).setVassal_Tribute(this.iCivID, this.getCurr());
                        Menu_InGame_Tribute.this.updateIncomeFromVassals();
                        Menu_InGame_2.updateOverBudget();
                    }

                    @Override
                    public String getDrawText() {
                        return super.getDrawText() + "%";
                    }
                });
                tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD;
            }
        }
        menuElements.add(new Button_InGameAction(CFG.lang.get("Close"), -1, CFG.PADD, tY += CFG.PADD, CFG.BUTTON_W, true){

            @Override
            public int getWidthE() {
                return Menu_InGame_Tribute.this.getElementW() * 2 - CFG.PADD * 2;
            }

            @Override
            public void actionElem(int iID) {
                CFG.menus.setVisibleInGame_Tribute(false);
            }
        });
        int tempMenuPosY = IMGManager.getIMG(Images.topBar).getHeight() + CFG.PADD * 2 + CFG.BUTTON_H * 3 / 4;
        this.initMenu(new TitleM_TextSmall(CFG.lang.get("Vassals"), CFG.BUTTON_H * 3 / 4, true, true){

            @Override
            public void drawT(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                IMGManager.getIMG(Images.dialog_title).draw2O(oSB, nPosX - 2 - Core.PADDING + iTranslateX, nPosY - this.getHeightT() - IMGManager.getIMG(Images.dialog_title).getHeight() - Core.PADDING, nWidth + Core.PADDING * 2 + 4 - IMGManager.getIMG(Images.dialog_title).getWidth(), this.getHeightT() + Core.PADDING);
                IMGManager.getIMG(Images.dialog_title).draw2O(oSB, nPosX + nWidth + Core.PADDING + 2 - IMGManager.getIMG(Images.dialog_title).getWidth() + iTranslateX, nPosY - this.getHeightT() - Core.PADDING - IMGManager.getIMG(Images.dialog_title).getHeight(), IMGManager.getIMG(Images.dialog_title).getWidth(), this.getHeightT() + Core.PADDING, true, false);
                oSB.setColor(new Color(CFG.COLOR_POSITIVE_HOVER.r, CFG.COLOR_POSITIVE_HOVER.g, CFG.COLOR_POSITIVE_HOVER.b, 0.165f));
                IMGManager.getIMG(Images.line32Off1).drawO(oSB, nPosX + iTranslateX, nPosY - this.getHeightT() + 2 - IMGManager.getIMG(Images.line32Off1).getHeight(), nWidth, this.getHeightT() - 2, false, true);
                oSB.setColor(new Color(CFG.COLOR_POSITIVE_HOVER.r, CFG.COLOR_POSITIVE_HOVER.g, CFG.COLOR_POSITIVE_HOVER.b, 0.375f));
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
                int imgID = Images.diploVassal;
                IMGManager.getIMG(imgID).drawO(oSB, nPosX + (nWidth - (this.getTextWidth() + IMGManager.getIMG(imgID).getWidth() + CFG.PADD)) / 2 + iTranslateX, Menu_InGame_Tribute.this.getPosY() - this.getHeightT() / 2 - IMGManager.getIMG(imgID).getHeight() / 2);
                Renderer.drawText(oSB, CFG.FONT_BOLD_SMALL, this.getText(), nPosX + (nWidth - (this.getTextWidth() + IMGManager.getIMG(imgID).getWidth() + CFG.PADD)) / 2 + IMGManager.getIMG(imgID).getWidth() + CFG.PADD + iTranslateX, 2 + nPosY - this.getHeightT() + (this.getHeightT() - this.getTextHeight()) / 2, Color.WHITE);
            }
        }, CFG.GAMEWIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD + tempMenuPosY > CFG.GAMEHEIGHT - CFG.BUTTON_H - CFG.PADD * 2 ? Math.max(CFG.GAMEHEIGHT - CFG.BUTTON_H - CFG.PADD * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 2) * 6) : ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, menuElements, true, true);
        this.updateLang();
        for (int i = 1; i < this.getMenuElemsSize() - 1; ++i) {
            this.getMenuElem(i).setCurr(this.getMenuElem(i).getCurr());
        }
        Menu_InGameOfferAlliance.lTime = System.currentTimeMillis();
        this.updateIncomeFromVassals();
    }

    @Override
    public void updateLang() {
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (Menu_InGameOfferAlliance.lTime + (long)Menu_InGame_Message_Alliance.ANIMATION_TIME >= System.currentTimeMillis()) {
            Rectangle clipBounds = new Rectangle(this.getPosX() - 2 - Core.PADDING, CFG.GAMEHEIGHT - this.getPosY(), this.getWidthM() + 4 + Core.PADDING * 2, -((int)((float)(this.getHeightM() + CFG.PADD) * ((float)(System.currentTimeMillis() - Menu_InGameOfferAlliance.lTime) / (float)Menu_InGame_Message_Alliance.ANIMATION_TIME))));
            oSB.flush();
            ScissorStack.pushScissors(clipBounds);
            oSB.setColor(Color.WHITE);
            IMGManager.getIMG(Images.gameTopEdge).draw2O(oSB, this.getPosX() - 2 - Core.PADDING + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gameTopEdge).getHeight() + iTranslateY, this.getWidthM() + Core.PADDING * 2 - IMGManager.getIMG(Images.gameTopEdge).getWidth() + 4, this.getHeightM() + CFG.PADD + Core.PADDING, false, true);
            IMGManager.getIMG(Images.gameTopEdge).draw2O(oSB, this.getPosX() + 2 + Core.PADDING + this.getWidthM() - IMGManager.getIMG(Images.gameTopEdge).getWidth() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gameTopEdge).getHeight() + iTranslateY, IMGManager.getIMG(Images.gameTopEdge).getWidth(), this.getHeightM() + CFG.PADD + Core.PADDING, true, true);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
            IMGManager.getIMG(Images.gradient).drawO(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gradient).getHeight() + iTranslateY, this.getWidthM() - 4, this.getHeightM() / 4);
            IMGManager.getIMG(Images.pix255).drawO(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.pix255).getHeight() + iTranslateY, this.getWidthM() - 4, 1);
            oSB.setColor(Color.WHITE);
            this.drawMenuM(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
            oSB.setColor(Color.WHITE);
            oSB.setColor(Color.WHITE);
            CFG.setRenderO(true);
            this.endClipM(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        } else {
            oSB.setColor(Color.WHITE);
            IMGManager.getIMG(Images.gameTopEdge).draw2O(oSB, this.getPosX() - 2 - Core.PADDING + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gameTopEdge).getHeight() + iTranslateY, this.getWidthM() + Core.PADDING * 2 - IMGManager.getIMG(Images.gameTopEdge).getWidth() + 4, this.getHeightM() + CFG.PADD + Core.PADDING, false, true);
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
    }

    @Override
    public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    public final void updateIncomeFromVassals() {
        int tIncome = 0;
        for (int i = 0; i < CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).civGD.vassals.size(); ++i) {
            tIncome = (int)((float)tIncome + CFG.gameUpdate.getIncomeVassals(CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId(), CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).civGD.vassals.get((int)i).iCivID));
        }
        this.getMenuElem(0).setMin(tIncome);
    }

    public static void updateVassalsSpendings() {
        try {
            for (int i = 0; i < CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).civGD.vassals.size(); ++i) {
                int vassalID = CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).civGD.vassals.get((int)i).iCivID;
                if (CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).playerGD.VASSALS_MILITARY_SPENDINGS < 0.0f) {
                    CFG.core.getCiv((int)vassalID).civGD.civPers.MIN_MILITARY_SPENDINGS = CFG.oAI.getAIStyle((int)CFG.core.getCiv((int)vassalID).getAIStyleID()).PERSONALITY_MIN_MILITARY_SPENDINGS_DEFAULT + (float)CFG.oR.nextInt(CFG.oAI.getAIStyle((int)CFG.core.getCiv((int)vassalID).getAIStyleID()).PERSONALITY_MIN_MILITARY_SPENDINGS_RANDOM) / 100.0f;
                    continue;
                }
                CFG.core.getCiv((int)vassalID).civGD.civPers.MIN_MILITARY_SPENDINGS = CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).playerGD.VASSALS_MILITARY_SPENDINGS;
                CFG.core.getCiv((int)vassalID).civGD.civPers.MIN_MILITARY_SPENDINGS_WAR = Math.max(CFG.core.getCiv((int)vassalID).civGD.civPers.MIN_MILITARY_SPENDINGS_WAR, CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).playerGD.VASSALS_MILITARY_SPENDINGS);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static void updateVassalsSpendings(int civID) {
        try {
            for (int i = 0; i < CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).civGD.vassals.size(); ++i) {
                int vassalID = CFG.core.getCiv((int)CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).getCivId()).civGD.vassals.get((int)i).iCivID;
                if (vassalID != civID) continue;
                if (CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).playerGD.VASSALS_MILITARY_SPENDINGS < 0.0f) {
                    CFG.core.getCiv((int)vassalID).civGD.civPers.MIN_MILITARY_SPENDINGS = CFG.oAI.getAIStyle((int)CFG.core.getCiv((int)vassalID).getAIStyleID()).PERSONALITY_MIN_MILITARY_SPENDINGS_DEFAULT + (float)CFG.oR.nextInt(CFG.oAI.getAIStyle((int)CFG.core.getCiv((int)vassalID).getAIStyleID()).PERSONALITY_MIN_MILITARY_SPENDINGS_RANDOM) / 100.0f;
                    continue;
                }
                CFG.core.getCiv((int)vassalID).civGD.civPers.MIN_MILITARY_SPENDINGS = CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).playerGD.VASSALS_MILITARY_SPENDINGS;
                CFG.core.getCiv((int)vassalID).civGD.civPers.MIN_MILITARY_SPENDINGS_WAR = Math.max(CFG.core.getCiv((int)vassalID).civGD.civPers.MIN_MILITARY_SPENDINGS_WAR, CFG.core.getPlayer((int)CFG.PLAYER_TURN_ID).playerGD.VASSALS_MILITARY_SPENDINGS);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Override
    public final void actionEL(int iID) {
        try {
            this.getMenuElem(iID).actionElem(iID);
        }
        catch (Exception ex) {
            this.setVisibleM(false);
        }
    }

    public final int getW() {
        return this.getWidthM() - 4;
    }

    public final int getElementW() {
        return this.getW() / 2;
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
