package age.of.civilizations2.jakowski.lukasz.Menus.Settings;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.Button.Button_Transparent;
import age.of.civilizations2.jakowski.lukasz.Button.Flag.Button_Flag_JustFrame;
import age.of.civilizations2.jakowski.lukasz.Button.MenuElemUI;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Files.FileManager;
import age.of.civilizations2.jakowski.lukasz.IMGManager;
import age.of.civilizations2.jakowski.lukasz.Image;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.LangManager;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.Menus.Editors.Menu_Editor;
import age.of.civilizations2.jakowski.lukasz.SFXManager;
import age.of.civilizations2.jakowski.lukasz.View;
import age.of.civilizations2.jakowski.lukasz.Z_Other.DialogType;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

public class Menu_SelectLanguage
extends Menu {
    private List<Image> lFlags = null;
    private String sText = null;
    private int iTextWidth = 0;

    public Menu_SelectLanguage() {
        ArrayList<MenuElemUI> menuElements = new ArrayList<MenuElemUI>();
        this.lFlags = new ArrayList<Image>();
        try {
            int i;
            FileHandle tempFileT = FileManager.loadFile("game/languages/Age_of_Civilizations");
            String tempT = tempFileT.readString();
            String[] tagsSPLITED = tempT.split(";");
            int tPosX = (CFG.GAMEWIDTH - IMGManager.getIMG(Images.topFlagFrame).getWidth() * (tagsSPLITED.length / 2) - CFG.PADD * tagsSPLITED.length) / 2;
            int tPosY = CFG.GAMEHEIGHT * 3 / 5 - IMGManager.getIMG(Images.topFlagFrame).getHeight() / 2;
            int tX = tPosX + CFG.PADD;
            for (i = 0; i < tagsSPLITED.length; i += 2) {
                menuElements.add(new Button_Flag_JustFrame(tX, tPosY, true){

                    @Override
                    public int getSFXElem() {
                        return SFXManager.SFX_CLICK3;
                    }
                });
                tX += IMGManager.getIMG(Images.topFlagFrame).getWidth() + CFG.PADD * 2;
                try {
                    this.lFlags.add(new Image(new Texture(FileManager.loadFile("game/flagsH/" + tagsSPLITED[i + 1] + ".png")), Texture.TextureFilter.Nearest));
                    continue;
                }
                catch (GdxRuntimeException ex) {
                    try {
                        this.lFlags.add(new Image(new Texture(FileManager.loadFile("game/flags/" + tagsSPLITED[i + 1] + ".png")), Texture.TextureFilter.Nearest));
                    }
                    catch (GdxRuntimeException e) {
                        this.lFlags.add(new Image(new Texture(FileManager.loadFile("game/flags/ran.png")), Texture.TextureFilter.Nearest));
                    }
                    catch (OutOfMemoryError outOfMemoryError) {}
                    continue;
                }
                catch (OutOfMemoryError outOfMemoryError) {
                    // empty catch block
                }
            }
            menuElements.add(new Button_Transparent(tX, tPosY, CFG.BUTTON_W / 2, IMGManager.getIMG(Images.topFlagFrame).getHeight(), true));
            tX = CFG.BUTTON_W / 2;
            if (((MenuElemUI)menuElements.get(0)).getPosXE() <= 0) {
                for (i = 0; i < menuElements.size(); ++i) {
                    ((MenuElemUI)menuElements.get(i)).setPosX(tX);
                    tX += IMGManager.getIMG(Images.topFlagFrame).getWidth() + CFG.PADD * 2;
                }
            }
        }
        catch (IndexOutOfBoundsException ex) {
            menuElements.add(new Button_Flag_JustFrame(CFG.GAMEWIDTH / 2 - IMGManager.getIMG(Images.topFlagFrame).getWidth() / 2, CFG.GAMEHEIGHT / 2 - IMGManager.getIMG(Images.topFlagFrame).getHeight() / 2, true));
        }
        catch (GdxRuntimeException ex) {
            menuElements.add(new Button_Flag_JustFrame(CFG.GAMEWIDTH / 2 - IMGManager.getIMG(Images.topFlagFrame).getWidth() / 2, CFG.GAMEHEIGHT / 2 - IMGManager.getIMG(Images.topFlagFrame).getHeight() / 2, true));
        }
        this.updateLang();
        this.initMenu(null, 0 + AoCGame.LEFT, 0, CFG.GAMEWIDTH - AoCGame.LEFT, CFG.GAMEHEIGHT, menuElements);
        this.updateLang();
    }

    @Override
    public void updateLang() {
        this.sText = CFG.lang.get("SelectLanguage");
        CFG.glyphLay.setText(CFG.fontMain.get(0), this.sText);
        this.iTextWidth = (int)CFG.glyphLay.width;
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
        IMGManager.getIMG(Images.gradient).drawO(oSB, iTranslateX, -IMGManager.getIMG(Images.gradient).getHeight() + iTranslateY, CFG.GAMEWIDTH, CFG.BUTTON_H / 2);
        IMGManager.getIMG(Images.gradient).drawO(oSB, iTranslateX, CFG.GAMEHEIGHT - IMGManager.getIMG(Images.gradient).getHeight() + iTranslateY, CFG.GAMEWIDTH, CFG.BUTTON_H / 2, false, true);
        oSB.setColor(CFG.COLOR_GRADIENT_DARK_BLUE);
        IMGManager.getIMG(Images.line32Off1).drawO(oSB, this.getPosX() + iTranslateX, this.getMenuElem(0).getPosY() + this.getMenuElem(0).getHeightE() / 2 - IMGManager.getIMG(Images.topFlagFrame).getHeight() - IMGManager.getIMG(Images.line32Off1).getHeight() + iTranslateY, CFG.GAMEWIDTH, IMGManager.getIMG(Images.topFlagFrame).getHeight() * 2);
        oSB.setColor(CFG.COLOR_FLAG_FRAME);
        IMGManager.getIMG(Images.line32Off1).drawO(oSB, this.getPosX() + iTranslateX, this.getMenuElem(0).getPosY() + this.getMenuElem(0).getHeightE() / 2 - IMGManager.getIMG(Images.topFlagFrame).getHeight() - IMGManager.getIMG(Images.line32Off1).getHeight() + iTranslateY, CFG.GAMEWIDTH, 1);
        IMGManager.getIMG(Images.line32Off1).drawO(oSB, this.getPosX() + iTranslateX, this.getMenuElem(0).getPosY() + this.getMenuElem(0).getHeightE() / 2 + IMGManager.getIMG(Images.topFlagFrame).getHeight() - IMGManager.getIMG(Images.line32Off1).getHeight() + iTranslateY, CFG.GAMEWIDTH, 1);
        CFG.drawTextDefaultWithShadow(oSB, this.sText, this.getWidthM() / 2 - this.iTextWidth / 2 + iTranslateX, this.getMenuElem(0).getPosY() + this.getMenuElem(0).getHeightE() / 2 - IMGManager.getIMG(Images.topFlagFrame).getHeight() - CFG.PADD - CFG.PADD / 2 - CFG.TEXT_HEIGHT_DEFAULT + iTranslateY, CFG.COLOR_HOVER_TITLE);
        oSB.setColor(Color.WHITE);
        IMGManager.getIMG(Images.gameLogo).drawO(oSB, CFG.PADD + iTranslateX, CFG.GAMEHEIGHT - IMGManager.getIMG(Images.gameLogo).getHeight() - CFG.PADD);
        try {
            for (int i = 0; i < this.lFlags.size(); ++i) {
                if (!this.getMenuElem(i).getIsInView()) continue;
                this.lFlags.get(i).drawO(oSB, this.getMenuElem(i).getPosXE() + this.getMenuPosX() + iTranslateX, this.getMenuElem(i).getPosY() - this.lFlags.get(i).getHeight() + this.getMenuPosY() + iTranslateY, IMGManager.getIMG(Images.topFlagFrame).getWidth(), IMGManager.getIMG(Images.topFlagFrame).getHeight());
            }
        }
        catch (NullPointerException ex) {
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.35f));
            for (int i = 0; i < this.getMenuElemsSize(); ++i) {
                IMGManager.getIMG(Images.pix255).drawO(oSB, this.getMenuElem(i).getPosXE() + this.getMenuPosX() + iTranslateX, this.getMenuElem(i).getPosY() - IMGManager.getIMG(Images.pix255).getHeight() + this.getMenuPosY() + iTranslateY, IMGManager.getIMG(Images.topFlagFrame).getWidth(), IMGManager.getIMG(Images.topFlagFrame).getHeight());
            }
            oSB.setColor(Color.WHITE);
        }
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    public final void actionEL(int iID) {
        if (iID == this.getMenuElemsSize() - 1) {
            return;
        }
        try {
            FileHandle tempFileT = FileManager.loadFile("game/languages/Age_of_Civilizations");
            String tempT = tempFileT.readString();
            String[] tagsSPLITED = tempT.split(";");
            CFG.lang.dispose();
            CFG.lang = null;
            CFG.lang = new LangManager(tagsSPLITED[iID * 2]);
            CFG.settingsGD.LANG_TAG = tagsSPLITED[iID * 2];
            CFG.loadFontMain();
            CFG.loadFontBorder();
            CFG.RANDOM_CIVILIZATION = CFG.lang.get("RandomCivilization");
            CFG.saveSettings();
            if (CFG.VIEW_SHOW_VALUES) {
                CFG.menus.updateLang();
                CFG.toastM.addM(CFG.lang.get("LANGUAGENAME"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            }
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        if (CFG.backToMenu == View.eMAINMENU) {
            CFG.setDialogType(DialogType.CONFIRM_LANGUAGE);
        } else {
            this.onBackPressed();
            Menu_Editor.reloadScenario();
        }
    }

    @Override
    public final void onBackPressed() {
        int i;
        for (i = 0; i < CFG.core.getCivsSize(); ++i) {
            CFG.core.getCiv(i).setCivName(CFG.lang.getCiv(CFG.core.getCiv(i).getCivTag()));
        }
        try {
            for (i = 0; i < this.lFlags.size(); ++i) {
                this.lFlags.get(i).getTexture().dispose();
            }
            this.lFlags.clear();
            this.lFlags = null;
        }
        catch (NullPointerException ex) {
            CFG.exceptionStack(ex);
        }
        CFG.map.getMpC().centerToRandomMapPos();
        CFG.menus.setMenuID(CFG.backToMenu);
        CFG.menus.setBackAnimation(true);
        CFG.map.getMpB().updateWorldMap_Shaders();
    }
}
