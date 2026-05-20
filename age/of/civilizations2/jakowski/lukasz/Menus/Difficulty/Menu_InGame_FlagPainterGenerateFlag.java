package age.of.civilizations2.jakowski.lukasz.Menus.Difficulty;

import age.of.civilizations2.jakowski.lukasz.Button.Button_Transparent;
import age.of.civilizations2.jakowski.lukasz.Button.MenuElemUI;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.View;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_FlagPainterGenerateFlag
extends Menu {
    public Menu_InGame_FlagPainterGenerateFlag() {
        ArrayList<MenuElemUI> menuElements = new ArrayList<MenuElemUI>();
        menuElements.add(new Button_Transparent(0, 0, CFG.GAMEWIDTH, CFG.GAMEHEIGHT - CFG.BUTTON_H - CFG.PADD, true));
        this.initMenu(null, 0, 0, CFG.GAMEWIDTH, CFG.GAMEHEIGHT, menuElements);
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        CFG.flagManager.saveFlagTextureFlagPainter(oSB);
        CFG.toastM.addM(CFG.lang.get("Saved"), CFG.COLOR_HOVER_TITLE);
        this.onBackPressed();
    }

    @Override
    public void actionEL(int nMenuElementID) {
    }

    @Override
    public final void onBackPressed() {
        CFG.menus.setMenuIDWithoutAnim(View.eINGAME);
    }
}
