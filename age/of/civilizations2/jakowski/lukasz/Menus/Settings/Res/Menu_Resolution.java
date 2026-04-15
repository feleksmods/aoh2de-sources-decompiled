package age.of.civilizations2.jakowski.lukasz.Menus.Settings.Res;

import age.of.civilizations2.jakowski.lukasz.Button.Classic.Button_Classic;
import age.of.civilizations2.jakowski.lukasz.Button.Classic.Button_Classic_LR_Line;
import age.of.civilizations2.jakowski.lukasz.Button.MenuElemUI;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.Point_XY2;
import age.of.civilizations2.jakowski.lukasz.View;
import age.of.civilizations2.jakowski.lukasz.Z_Other.ConfigINI;
import java.util.ArrayList;
import java.util.List;

public class Menu_Resolution
extends Menu {
    private List<Point_XY2> lResolution = new ArrayList<Point_XY2>();

    public Menu_Resolution() {
        ArrayList<MenuElemUI> menuElements = new ArrayList<MenuElemUI>();
        int tY = CFG.PADD;
        menuElements.add(new Button_Classic_LR_Line(null, -1, 0, tY, CFG.GAMEWIDTH, CFG.BUTTON_H, true));
        tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD;
        this.lResolution.clear();
        this.lResolution.add(new Point_XY2(7680, 4320));
        this.lResolution.add(new Point_XY2(5120, 2880));
        this.lResolution.add(new Point_XY2(5120, 2160));
        this.lResolution.add(new Point_XY2(4096, 2160));
        this.lResolution.add(new Point_XY2(3840, 2160));
        this.lResolution.add(new Point_XY2(3440, 1440));
        this.lResolution.add(new Point_XY2(2560, 2048));
        this.lResolution.add(new Point_XY2(2560, 1920));
        this.lResolution.add(new Point_XY2(2560, 1600));
        this.lResolution.add(new Point_XY2(2560, 1440));
        this.lResolution.add(new Point_XY2(2560, 1080));
        this.lResolution.add(new Point_XY2(2048, 1536));
        this.lResolution.add(new Point_XY2(2048, 1152));
        this.lResolution.add(new Point_XY2(1920, 1440));
        this.lResolution.add(new Point_XY2(1920, 1200));
        this.lResolution.add(new Point_XY2(1920, 1080));
        this.lResolution.add(new Point_XY2(1080, 1920));
        this.lResolution.add(new Point_XY2(1856, 1392));
        this.lResolution.add(new Point_XY2(1792, 1344));
        this.lResolution.add(new Point_XY2(1680, 1050));
        this.lResolution.add(new Point_XY2(1600, 1200));
        this.lResolution.add(new Point_XY2(1600, 900));
        this.lResolution.add(new Point_XY2(1536, 864));
        this.lResolution.add(new Point_XY2(1440, 900));
        this.lResolution.add(new Point_XY2(1400, 1050));
        this.lResolution.add(new Point_XY2(1366, 768));
        this.lResolution.add(new Point_XY2(1360, 768));
        this.lResolution.add(new Point_XY2(1280, 1024));
        this.lResolution.add(new Point_XY2(1280, 960));
        this.lResolution.add(new Point_XY2(1280, 800));
        this.lResolution.add(new Point_XY2(1280, 768));
        this.lResolution.add(new Point_XY2(1280, 720));
        this.lResolution.add(new Point_XY2(1024, 768));
        this.lResolution.add(new Point_XY2(1024, 600));
        this.lResolution.add(new Point_XY2(800, 600));
        for (int i = 0; i < this.lResolution.size(); ++i) {
            if (CFG.GAMEWIDTH == this.lResolution.get(i).getPX() && CFG.GAMEHEIGHT == this.lResolution.get(i).getPY()) {
                menuElements.add(new Button_Classic("" + this.lResolution.get(i).getPX() + "x" + this.lResolution.get(i).getPY(), (int)(50.0f * CFG.GUI_SCALE), 0, tY, CFG.GAMEWIDTH, CFG.BUTTON_H, true, true));
                tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD;
                continue;
            }
            menuElements.add(new Button_Classic("" + this.lResolution.get(i).getPX() + "x" + this.lResolution.get(i).getPY(), (int)(50.0f * CFG.GUI_SCALE), 0, tY, CFG.GAMEWIDTH, CFG.BUTTON_H, true));
            tY += ((MenuElemUI)menuElements.get(menuElements.size() - 1)).getHeightE() + CFG.PADD;
        }
        this.initMenu(null, 0, CFG.BUTTON_H * 3 / 4, CFG.GAMEWIDTH, CFG.GAMEHEIGHT - CFG.BUTTON_H * 3 / 4 - CFG.BUTTON_H - CFG.PADD, menuElements);
        this.updateLang();
    }

    @Override
    public void updateLang() {
        this.getMenuElem(0).setTextE(CFG.lang.get("Max"));
    }

    @Override
    public final void actionEL(int iID) {
        if (iID == 0) {
            ConfigINI.iWidth = -1;
            ConfigINI.iHeight = -1;
        } else {
            ConfigINI.iWidth = this.lResolution.get(iID - 1).getPX();
            ConfigINI.iHeight = this.lResolution.get(iID - 1).getPY();
        }
        ConfigINI.saveConfig();
        CFG.menus.setMenuID(View.eSETTINGS_GRAPHICS);
        CFG.menus.setBackAnimation(true);
        CFG.toastM.addM(CFG.lang.get("GameNeedsToBeRestartedToApplyTheChanges"));
        CFG.toastM.setTimeInView(4500);
    }
}
