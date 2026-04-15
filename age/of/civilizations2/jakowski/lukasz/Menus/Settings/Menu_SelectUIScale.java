package age.of.civilizations2.jakowski.lukasz.Menus.Settings;

import age.of.civilizations2.jakowski.lukasz.Button.Classic.Button_Classic;
import age.of.civilizations2.jakowski.lukasz.Button.MenuElemUI;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Files.FileManager;
import age.of.civilizations2.jakowski.lukasz.Image;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.View;
import age.of.civilizations2.jakowski.lukasz.Z_Other.ConfigINI;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Menu_SelectUIScale
extends Menu {
    private List<Image> lButtons = new ArrayList<Image>();

    public Menu_SelectUIScale() {
        ArrayList<MenuElemUI> menuElements = new ArrayList<MenuElemUI>();
        this.lButtons.add(new Image(new Texture(FileManager.loadFile("UI/interface/H/buttons/menu.png"), Pixmap.Format.RGBA8888, true), Texture.TextureFilter.Linear));
        this.lButtons.add(new Image(new Texture(FileManager.loadFile("UI/interface/XH/buttons/menu.png"), Pixmap.Format.RGBA8888, true), Texture.TextureFilter.Linear));
        this.lButtons.add(new Image(new Texture(FileManager.loadFile("UI/interface/XXH/buttons/menu.png"), Pixmap.Format.RGBA8888, true), Texture.TextureFilter.Linear));
        this.lButtons.add(new Image(new Texture(FileManager.loadFile("UI/interface/XXXH/buttons/menu.png"), Pixmap.Format.RGBA8888, true), Texture.TextureFilter.Linear));
        int tY = CFG.PADD;
        menuElements.add(new Button_Classic(null, -1, 0, tY, CFG.GAMEWIDTH, this.lButtons.get(0).getHeight(), true, CFG.getUIScale() == 0){

            @Override
            public void drawButtonBGE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                ((Image)Menu_SelectUIScale.this.lButtons.get(0)).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() + iTranslateY, this.getWidthE());
            }
        });
        menuElements.add(new Button_Classic(null, -1, 0, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, CFG.GAMEWIDTH, this.lButtons.get(1).getHeight(), true, CFG.getUIScale() == 1){

            @Override
            public void drawButtonBGE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                ((Image)Menu_SelectUIScale.this.lButtons.get(1)).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() + iTranslateY, this.getWidthE());
            }
        });
        menuElements.add(new Button_Classic(null, -1, 0, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, CFG.GAMEWIDTH, this.lButtons.get(2).getHeight(), true, CFG.getUIScale() == 2){

            @Override
            public void drawButtonBGE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                ((Image)Menu_SelectUIScale.this.lButtons.get(2)).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() + iTranslateY, this.getWidthE());
            }
        });
        menuElements.add(new Button_Classic(null, -1, 0, tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD, CFG.GAMEWIDTH, this.lButtons.get(3).getHeight(), true, CFG.getUIScale() == 3){

            @Override
            public void drawButtonBGE(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                ((Image)Menu_SelectUIScale.this.lButtons.get(3)).drawO(oSB, this.getPosXE() + iTranslateX, this.getPosY() + iTranslateY, this.getWidthE());
            }
        });
        tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD;
        this.initMenu(null, 0, CFG.BUTTON_H * 3 / 4, CFG.GAMEWIDTH, CFG.GAMEHEIGHT - CFG.BUTTON_H * 3 / 4 - CFG.BUTTON_H - CFG.PADD, menuElements);
        this.updateLang();
    }

    @Override
    public void updateLang() {
        this.getMenuElem(0).setTextE(CFG.lang.get("1"));
        this.getMenuElem(1).setTextE(CFG.lang.get("2"));
        this.getMenuElem(2).setTextE(CFG.lang.get("3"));
        this.getMenuElem(3).setTextE(CFG.lang.get("4"));
    }

    @Override
    public final void actionEL(int iID) {
        ConfigINI.iUIScale = iID + 1;
        ConfigINI.saveConfig();
        switch (iID) {
            case 0: {
                CFG.settingsGD.FONT_MAIN_SIZEX = 18;
                break;
            }
            case 1: {
                CFG.settingsGD.FONT_MAIN_SIZEX = 24;
                break;
            }
            case 2: {
                CFG.settingsGD.FONT_MAIN_SIZEX = 32;
                break;
            }
            case 3: {
                CFG.settingsGD.FONT_MAIN_SIZEX = 36;
            }
        }
        CFG.saveSettings();
        CFG.menus.setMenuID(View.eSETTINGS);
        CFG.menus.setBackAnimation(true);
        CFG.toastM.addM(CFG.lang.get("GameNeedsToBeRestartedToApplyTheChanges"));
        CFG.toastM.setTimeInView(4500);
    }

    @Override
    public void setVisibleM(boolean visible) {
        if (!visible) {
            try {
                for (int i = 0; i < this.lButtons.size(); ++i) {
                    this.lButtons.get(i).getTexture().dispose();
                }
                this.lButtons.clear();
            }
            catch (NullPointerException nullPointerException) {
                // empty catch block
            }
        }
        super.setVisibleM(visible);
    }
}
