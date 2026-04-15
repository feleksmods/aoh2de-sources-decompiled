package age.of.civilizations2.jakowski.lukasz.Menus.Settings;

import age.of.civilizations2.jakowski.lukasz.Button.Classic.Button_Classic_LR_Line;
import age.of.civilizations2.jakowski.lukasz.Button.MenuElemUI;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.RenderProvince;
import age.of.civilizations2.jakowski.lukasz.Title.TitleM;
import age.of.civilizations2.jakowski.lukasz.View;
import java.util.ArrayList;

public class Menu_Settings
extends Menu {
    public Menu_Settings() {
        ArrayList<MenuElemUI> menuElements = new ArrayList<MenuElemUI>();
        menuElements.add(new Button_Classic_LR_Line(null, -1, 0, CFG.PADD, CFG.GAMEWIDTH, CFG.BUTTON_H, true));
        this.initMenuWithBackButton(new TitleM(null, CFG.BUTTON_H * 3 / 4, false, false), 0, CFG.BUTTON_H * 3 / 4, CFG.GAMEWIDTH, CFG.GAMEHEIGHT - CFG.BUTTON_H * 3 / 4, menuElements);
        this.updateLang();
    }

    @Override
    public void updateLang() {
        this.getMenuElem(0).setTextE(CFG.lang.get("Back"));
        this.getTitleM().setText(CFG.lang.get("Settings"));
    }

    @Override
    public final void actionEL(int iID) {
        CFG.menus.getColorPicker().setVisible(false, null);
        switch (iID) {
            case 0: {
                this.onBackPressed();
            }
        }
        RenderProvince.updateDrawProvinces();
    }

    @Override
    public final void onBackPressed() {
        CFG.menus.setMenuID(CFG.goToMenu2);
        CFG.menus.setBackAnimation(true);
        if (CFG.goToMenu2 == View.eINGAME) {
            CFG.core.checkProvinceActionMenu();
        }
    }
}
