package age.of.civilizations2.jakowski.lukasz.Z_Other;

import age.of.civilizations2.jakowski.lukasz.CFG;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class GlyphLayout_Game {
    public float width = 1.0f;
    public float height = 1.0f;

    public boolean setText(BitmapFont font, CharSequence str) {
        try {
            if (str == null || str.length() <= 0) {
                this.width = 1.0f;
                this.height = CFG.TEXT_HEIGHT_DEFAULT;
                return false;
            }
            GlyphLayout glyphLayout = new GlyphLayout();
            glyphLayout.setText(font, str);
            this.width = glyphLayout.width;
            this.height = glyphLayout.height;
            return true;
        }
        catch (Exception exception) {
            return false;
        }
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }
}
