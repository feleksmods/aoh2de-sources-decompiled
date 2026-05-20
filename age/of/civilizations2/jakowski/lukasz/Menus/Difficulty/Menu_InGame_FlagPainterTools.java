package age.of.civilizations2.jakowski.lukasz.Menus.Difficulty;

import age.of.civilizations2.jakowski.lukasz.Button.MenuElemUI;
import age.of.civilizations2.jakowski.lukasz.Button.NewGame.Button_InGameBox;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Core.Core;
import age.of.civilizations2.jakowski.lukasz.IMGManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.MEHover_2E;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Image_Big;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_TextDesc;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Text_Big;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.Menus.Civilization.Menu_InGame_Civ_Decisions;
import age.of.civilizations2.jakowski.lukasz.Menus.Difficulty.Menu_InGame_FlagPainter;
import age.of.civilizations2.jakowski.lukasz.Renderer;
import age.of.civilizations2.jakowski.lukasz.Sliders.InGame.Clear.Slider_InGame_Clear;
import age.of.civilizations2.jakowski.lukasz.TextB.Texts.TextBuildTitle;
import age.of.civilizations2.jakowski.lukasz.Title.TitleM_TextSmall;
import age.of.civilizations2.jakowski.lukasz.Z_Other.ColorPicker.ColorPicker_AoC;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

public class Menu_InGame_FlagPainterTools
extends Menu {
    public static final int ANIMATION_TIME = 155;
    private long lTime = 0L;

    public Menu_InGame_FlagPainterTools() {
        ArrayList<MenuElemUI> menuElements = new ArrayList<MenuElemUI>();
        int tempW = (int)((float)CFG.CIV_INFO_MENU_WIDTH * 1.25f);
        int tY = CFG.PADD;
        int tempElemH = Menu_InGame_Civ_Decisions.getButtonH();
        menuElements.add(new Slider_InGame_Clear(CFG.lang.get("Scale"), CFG.PADD, tY, tempW - CFG.PADD * 2, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 2 + CFG.PADD * 4, 1, 14, Menu_InGame_FlagPainter.SCALE){

            @Override
            public String getDrawText() {
                return "x" + super.getDrawText() + "";
            }

            @Override
            public int getSliderHeight() {
                return CFG.PADD * 2;
            }

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.SCALE = this.getCurr();
                Menu_InGame_FlagPainter.updateFlagPos();
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("ColorPicker"), -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, (tempW - CFG.PADD * 3) / 2, CFG.BUTTON_H * 3 / 5, true){

            @Override
            public void actionElem(int iID) {
                CFG.menus.getColorPicker().setPosX(CFG.GAMEWIDTH - CFG.menus.getColorPicker().getWidth() - CFG.PADD * 3);
                CFG.menus.getColorPicker().setPosY(CFG.PADD * 3);
                CFG.menus.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.FLAG_PAINTER);
            }

            @Override
            public void buildElemHover() {
                ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("ColorPicker"), CFG.COLOR_HOVER_TITLE));
                nData.add(new ME_Hover_2Type_Image_Big(Images.pickerIcon, CFG.PADD, 0));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Space());
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_TextDesc(CFG.lang.get("ColorPickerDesc")));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                this.menuElemHover = new ME_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("ToggleGrid"), -1, CFG.PADD * 2 + (tempW - CFG.PADD * 3) / 2, tY, tempW - (CFG.PADD + (tempW - CFG.PADD * 3) / 2) - CFG.PADD * 2, CFG.BUTTON_H * 3 / 5, true){

            @Override
            public void actionElem(int iID) {
                if (++Menu_InGame_FlagPainter.GRID >= 3) {
                    Menu_InGame_FlagPainter.GRID = 0;
                }
            }

            @Override
            public void buildElemHover() {
                ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("ToggleGrid"), CFG.COLOR_HOVER_TITLE));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                this.menuElemHover = new ME_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("Undo"), -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, CFG.BUTTON_H * 3 / 5, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.undo();
            }

            @Override
            public String getTextE() {
                return super.getTextE() + " [" + Menu_InGame_FlagPainter.undoStack.size() + "/" + Menu_InGame_FlagPainter.UNDO_LIMIT + "]";
            }
        });
        menuElements.add(new Slider_InGame_Clear(CFG.lang.get("BrushSize"), CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 2 + CFG.PADD * 4, 1, 50, Menu_InGame_FlagPainter.BRUSH_SIZE){

            @Override
            public String getDrawText() {
                return super.getDrawText() + "px";
            }

            @Override
            public int getSliderHeight() {
                return CFG.PADD * 2;
            }

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.BRUSH_SIZE = this.getCurr();
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("SquareBrush"), -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, (tempW - CFG.PADD * 3) / 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.brushType = Menu_InGame_FlagPainter.BrushType.SQUARE;
            }

            @Override
            public Color getColorE(boolean isActive) {
                if (Menu_InGame_FlagPainter.brushType == Menu_InGame_FlagPainter.BrushType.SQUARE) {
                    return isActive || this.getIsHovered() ? CFG.COLOR_HOVER_TITLE : CFG.COLOR_TEXT_NUM_OF_PROVINCES;
                }
                return super.getColorE(isActive);
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("CircleBrush"), -1, CFG.PADD * 2 + (tempW - CFG.PADD * 3) / 2, tY, tempW - (CFG.PADD + (tempW - CFG.PADD * 3) / 2) - CFG.PADD * 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.brushType = Menu_InGame_FlagPainter.BrushType.CIRCLE;
            }

            @Override
            public Color getColorE(boolean isActive) {
                if (Menu_InGame_FlagPainter.brushType == Menu_InGame_FlagPainter.BrushType.CIRCLE) {
                    return isActive || this.getIsHovered() ? CFG.COLOR_HOVER_TITLE : CFG.COLOR_TEXT_NUM_OF_PROVINCES;
                }
                return super.getColorE(isActive);
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("HorizontalLine"), -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, (tempW - CFG.PADD * 3) / 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.brushType = Menu_InGame_FlagPainter.BrushType.LINE_BRUSH_HORIZONTAL;
            }

            @Override
            public Color getColorE(boolean isActive) {
                if (Menu_InGame_FlagPainter.brushType == Menu_InGame_FlagPainter.BrushType.LINE_BRUSH_HORIZONTAL) {
                    return isActive || this.getIsHovered() ? CFG.COLOR_HOVER_TITLE : CFG.COLOR_TEXT_NUM_OF_PROVINCES;
                }
                return super.getColorE(isActive);
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("VerticalLine"), -1, CFG.PADD * 2 + (tempW - CFG.PADD * 3) / 2, tY, tempW - (CFG.PADD + (tempW - CFG.PADD * 3) / 2) - CFG.PADD * 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.brushType = Menu_InGame_FlagPainter.BrushType.LINE_BRUSH_VERTICAL;
            }

            @Override
            public Color getColorE(boolean isActive) {
                if (Menu_InGame_FlagPainter.brushType == Menu_InGame_FlagPainter.BrushType.LINE_BRUSH_VERTICAL) {
                    return isActive || this.getIsHovered() ? CFG.COLOR_HOVER_TITLE : CFG.COLOR_TEXT_NUM_OF_PROVINCES;
                }
                return super.getColorE(isActive);
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("LineBrush"), -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, (tempW - CFG.PADD * 3) / 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.brushType = Menu_InGame_FlagPainter.BrushType.LINE_BRUSH;
            }

            @Override
            public Color getColorE(boolean isActive) {
                if (Menu_InGame_FlagPainter.brushType == Menu_InGame_FlagPainter.BrushType.LINE_BRUSH) {
                    return isActive || this.getIsHovered() ? CFG.COLOR_HOVER_TITLE : CFG.COLOR_TEXT_NUM_OF_PROVINCES;
                }
                return super.getColorE(isActive);
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("StarStamp"), -1, CFG.PADD * 2 + (tempW - CFG.PADD * 3) / 2, tY, tempW - (CFG.PADD + (tempW - CFG.PADD * 3) / 2) - CFG.PADD * 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.brushType = Menu_InGame_FlagPainter.BrushType.STAR;
            }

            @Override
            public Color getColorE(boolean isActive) {
                if (Menu_InGame_FlagPainter.brushType == Menu_InGame_FlagPainter.BrushType.STAR) {
                    return isActive || this.getIsHovered() ? CFG.COLOR_HOVER_TITLE : CFG.COLOR_TEXT_NUM_OF_PROVINCES;
                }
                return super.getColorE(isActive);
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("FillBucket"), -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.brushType = Menu_InGame_FlagPainter.BrushType.FILL_BUCKET;
            }

            @Override
            public Color getColorE(boolean isActive) {
                if (Menu_InGame_FlagPainter.brushType == Menu_InGame_FlagPainter.BrushType.FILL_BUCKET) {
                    return isActive || this.getIsHovered() ? CFG.COLOR_HOVER_TITLE : CFG.COLOR_TEXT_NUM_OF_PROVINCES;
                }
                return super.getColorE(isActive);
            }
        });
        menuElements.add(new Slider_InGame_Clear(CFG.lang.get("FillTolerance"), CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 2 + CFG.PADD * 4, 1, 100, (int)(Menu_InGame_FlagPainter.tolerance * 100.0f)){

            @Override
            public String getDrawText() {
                return super.getDrawText() + "";
            }

            @Override
            public int getSliderHeight() {
                return CFG.PADD * 2;
            }

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.tolerance = (float)this.getCurr() / 100.0f;
            }

            @Override
            public void buildElemHover() {
                ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("FillTolerance"), CFG.COLOR_HOVER_TITLE));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Space());
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_TextDesc(CFG.lang.get("FillToleranceDesc")));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                this.menuElemHover = new ME_Hover_v2(nElements);
            }
        });
        menuElements.add(new TextBuildTitle(CFG.lang.get("BackupOptions"), -1, 2, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - 4, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 4){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_GRAY_NS_HOVER : (this.getIsClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_GRAY_NS : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("SaveBackup"), -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, (tempW - CFG.PADD * 3) / 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.saveBackup();
                CFG.toastM.addM(CFG.lang.get("SaveBackup") + ": " + CFG.lang.get("Done"), CFG.COLOR_POSITIVE);
            }

            @Override
            public void buildElemHover() {
                ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("SaveBackup"), CFG.COLOR_HOVER_TITLE));
                nData.add(new ME_Hover_2Type_Image_Big(Images.icon_save, CFG.PADD, 0));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Space());
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_TextDesc(CFG.lang.get("SaveBackupDesc")));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                this.menuElemHover = new ME_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("LoadBackup"), -1, CFG.PADD * 2 + (tempW - CFG.PADD * 3) / 2, tY, tempW - (CFG.PADD + (tempW - CFG.PADD * 3) / 2) - CFG.PADD * 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.loadBackup();
                CFG.toastM.addM(CFG.lang.get("LoadBackup") + ": " + CFG.lang.get("Done"), CFG.COLOR_POSITIVE);
            }

            @Override
            public boolean getIsClickable() {
                return super.getIsClickable() && Menu_InGame_FlagPainter.savedPixmap != null;
            }
        });
        menuElements.add(new TextBuildTitle(CFG.lang.get("FlagTemplates"), -1, 2, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - 4, CFG.TEXT_HEIGHT_DEFAULT + CFG.PADD * 4){

            @Override
            public Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_GRAY_NS_HOVER : (this.getIsClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_GRAY_NS : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }

            @Override
            public void buildElemHover() {
                ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("FlagTemplates"), CFG.COLOR_HOVER_TITLE));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_Space());
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                nData.add(new ME_Hover_2Type_TextDesc(CFG.lang.get("FlagTemplatesDesc")));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                this.menuElemHover = new ME_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("HorizontalFlag2"), -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.drawHorizontalFlag();
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("HorizontalFlag3"), -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.drawHorizontalTricolor();
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("HorizontalFlag4"), -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.drawHorizontalFlag4();
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("VerticalFlag2"), -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.drawVerticalFlag2();
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("VerticalFlag3"), -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.drawVerticalTricolor();
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("VerticalFlag4"), -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.drawVerticalFlag4();
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("QuarteredFlag"), -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.drawMedievalQuarteredFlag();
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("ChequeredFlag"), -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.drawChequeredFlag();
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("ScotlandFlag"), -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.drawScotlandFlag();
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("EnglandFlag"), -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.drawEnglandFlag();
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("USAFlag"), -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.drawUSAFlag();
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("AragonFlag"), -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.drawAragonFlag();
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("UnionJack"), -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.drawUnionJack();
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("FlagOfNorway"), -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.drawNorwayFlag();
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("FlagOfUtrecht"), -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.drawDiagonalTwoColorFlag();
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("FlagOfUtrechtFlipped"), -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.drawMirroredDiagonalFlag();
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("FlagOfJapan"), -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.drawJapanFlag();
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("FlagOfTurkey"), -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.drawTurkeyFlag();
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("FlagOfTogo"), -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.drawTogoFlag();
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("FlagOfSpain"), -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.drawSpainFlag();
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("FlagOfPortugal"), -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.drawPortugalFlag();
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("FlagOfCzechia"), -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.drawCzechFlag();
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("FlagOfBenin"), -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.drawBeninFlag();
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("NorthKoreaFlag"), -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.drawNorthKoreaFlag();
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("FlagPreset") + ": 1", -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.drawPreset1();
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("FlagPreset") + ": 2", -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.drawPreset2();
            }
        });
        menuElements.add(new Button_InGameBox(CFG.lang.get("FlagPreset") + ": 3", -1, CFG.PADD, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, tempW - CFG.PADD * 2, true){

            @Override
            public void actionElem(int iID) {
                Menu_InGame_FlagPainter.drawPreset3();
            }
        });
        tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD;
        int menuPY = CFG.PADD * 7 + CFG.menus.getColorPicker().getHeight() + CFG.BUTTON_H * 3 / 4;
        this.initMenu(new TitleM_TextSmall(CFG.lang.get("Tools"), CFG.BUTTON_H * 3 / 4, false, false){

            @Override
            public void drawT(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                IMGManager.getIMG(Images.dialog_title).draw2O(oSB, nPosX - Core.PADDING + iTranslateX, nPosY - Core.PADDING - this.getHeightT() - IMGManager.getIMG(Images.dialog_title).getHeight(), nWidth + Core.PADDING * 2 - IMGManager.getIMG(Images.dialog_title).getWidth(), this.getHeightT() + Core.PADDING);
                IMGManager.getIMG(Images.dialog_title).draw2O(oSB, nPosX + Core.PADDING + nWidth - IMGManager.getIMG(Images.dialog_title).getWidth() + iTranslateX, nPosY - Core.PADDING - this.getHeightT() - IMGManager.getIMG(Images.dialog_title).getHeight(), IMGManager.getIMG(Images.dialog_title).getWidth(), this.getHeightT() + Core.PADDING, true, false);
                oSB.setColor(new Color(100.0f, 0.27450982f, 0.49019608f, 0.075f));
                IMGManager.getIMG(Images.line32Off1).drawO(oSB, nPosX + 2 + iTranslateX, nPosY - this.getHeightT() + 2 - IMGManager.getIMG(Images.line32Off1).getHeight(), nWidth - 4, this.getHeightT() - 2, false, true);
                oSB.setColor(new Color(100.0f, 0.27450982f, 0.49019608f, 0.175f));
                IMGManager.getIMG(Images.gradient).drawO(oSB, nPosX + 2 + iTranslateX, nPosY - this.getHeightT() * 2 / 3 - IMGManager.getIMG(Images.gradient).getHeight(), nWidth - 4, this.getHeightT() * 2 / 3, false, true);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
                IMGManager.getIMG(Images.gradient).drawO(oSB, nPosX + 2 + iTranslateX, nPosY - CFG.PADD - IMGManager.getIMG(Images.gradient).getHeight(), nWidth - 4, CFG.PADD, false, true);
                oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
                IMGManager.getIMG(Images.pix255).drawO(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - IMGManager.getIMG(Images.pix255).getHeight(), nWidth - 4, 1);
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45f));
                IMGManager.getIMG(Images.sliderGradient).drawO(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - IMGManager.getIMG(Images.sliderGradient).getHeight(), (nWidth - 4) / 2, 1);
                IMGManager.getIMG(Images.sliderGradient).drawO(oSB, nPosX + 2 + (nWidth - 4) - (nWidth - 4) / 2 + iTranslateX, nPosY - 1 - IMGManager.getIMG(Images.sliderGradient).getHeight(), (nWidth - 4) / 2, 1, true, false);
                oSB.setColor(Color.WHITE);
                Renderer.drawText(oSB, CFG.FONT_BOLD_SMALL, this.getText(), nPosX + nWidth / 2 - this.getTextWidth() / 2 + iTranslateX, nPosY - this.getHeightT() + this.getHeightT() / 2 - this.getTextHeight() / 2, Color.WHITE);
            }
        }, CFG.GAMEWIDTH - tempW - CFG.PADD * 2, menuPY, tempW, Math.min(CFG.GAMEHEIGHT - menuPY, ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD), menuElements, true, false);
        this.updateLang();
    }

    @Override
    public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (this.lTime + 155L >= System.currentTimeMillis()) {
            Rectangle clipBounds = new Rectangle(this.getPosX(), CFG.GAMEHEIGHT - this.getPosY(), this.getWidthM(), -((int)((float)this.getHeightM() * ((float)(System.currentTimeMillis() - this.lTime) / 155.0f))));
            oSB.flush();
            ScissorStack.pushScissors(clipBounds);
            IMGManager.getIMG(Images.gameTopEdge).draw2O(oSB, this.getPosX() - Core.PADDING + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gameTopEdge).getHeight() + iTranslateY, this.getWidthM() + Core.PADDING * 2 - IMGManager.getIMG(Images.gameTopEdge).getWidth(), this.getHeightM() + 2 + Core.PADDING, false, true);
            IMGManager.getIMG(Images.gameTopEdge).draw2O(oSB, this.getPosX() + Core.PADDING + this.getWidthM() - IMGManager.getIMG(Images.gameTopEdge).getWidth() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gameTopEdge).getHeight() + iTranslateY, IMGManager.getIMG(Images.gameTopEdge).getWidth(), this.getHeightM() + 2 + Core.PADDING, true, true);
            super.drawMenuM(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
            CFG.setRenderO(true);
            try {
                oSB.flush();
                ScissorStack.popScissors();
            }
            catch (IllegalStateException illegalStateException) {
                // empty catch block
            }
            super.endClipM(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        } else {
            IMGManager.getIMG(Images.gameTopEdge).draw2O(oSB, this.getPosX() - Core.PADDING + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gameTopEdge).getHeight() + iTranslateY, this.getWidthM() + Core.PADDING * 2 - IMGManager.getIMG(Images.gameTopEdge).getWidth(), this.getHeightM() + 2 + Core.PADDING, false, true);
            IMGManager.getIMG(Images.gameTopEdge).draw2O(oSB, this.getPosX() + Core.PADDING + this.getWidthM() - IMGManager.getIMG(Images.gameTopEdge).getWidth() + iTranslateX, this.getPosY() - IMGManager.getIMG(Images.gameTopEdge).getHeight() + iTranslateY, IMGManager.getIMG(Images.gameTopEdge).getWidth(), this.getHeightM() + 2 + Core.PADDING, true, true);
            super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }
}
