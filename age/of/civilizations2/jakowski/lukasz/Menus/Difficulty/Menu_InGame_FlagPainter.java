package age.of.civilizations2.jakowski.lukasz.Menus.Difficulty;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.Button.Game.Button_Game;
import age.of.civilizations2.jakowski.lukasz.Button.MenuElemUI;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Core.Core;
import age.of.civilizations2.jakowski.lukasz.Files.FileManager;
import age.of.civilizations2.jakowski.lukasz.IMGManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.MEHover_2E;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Text;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.Renderer;
import age.of.civilizations2.jakowski.lukasz.Touch;
import age.of.civilizations2.jakowski.lukasz.View;
import age.of.civilizations2.jakowski.lukasz.Z_Other.ColorPicker.ColorPicker_AoC;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class Menu_InGame_FlagPainter
extends Menu {
    public static int GRID = 0;
    public static float tolerance = 0.1f;
    public static BrushType brushType = BrushType.SQUARE;
    public static int START_X = 0;
    public static int START_Y = 0;
    public static int flagPosX = 100;
    public static int flagPosY = 100;
    public static int civID = 1;
    public static int FLAG_W = 154;
    public static int FLAG_H = 100;
    public static int SCALE = 6;
    public static int BRUSH_SIZE = 15;
    public static boolean updateTexture = false;
    public static Pixmap pixmap;
    public static Texture texture;
    public static Pixmap savedPixmap;
    public int[] majorX = new int[]{FLAG_W / 3, FLAG_W / 2, FLAG_W * 2 / 3};
    public int[] majorY = new int[]{FLAG_H / 3, FLAG_H / 2, FLAG_H * 2 / 3};
    public static Color brushColor;
    public static Deque<Pixmap> undoStack;
    public static Deque<Pixmap> redoStack;
    public static int UNDO_LIMIT;
    private static boolean[][] visited;
    private String flagPainterTitle;
    private int iFlagPainterW;
    private String flagPainterTitleName;
    private int iFlagPainterNameW;
    public static final Color[] FLAG_COLORS;

    public static void addUndo() {
        Pixmap copy = new Pixmap(pixmap.getWidth(), pixmap.getHeight(), pixmap.getFormat());
        copy.drawPixmap(pixmap, 0, 0);
        undoStack.push(copy);
        if (undoStack.size() > UNDO_LIMIT) {
            Pixmap old = undoStack.removeLast();
            old.dispose();
        }
        redoStack.clear();
    }

    public static void undo() {
        Pixmap prev;
        if (undoStack.isEmpty()) {
            return;
        }
        redoStack.push(pixmap);
        pixmap = prev = undoStack.pop();
        updateTexture = true;
    }

    public void loadFlag(String civTag, int civId, String realTag, boolean isRevolutionary) {
        String baseXH = "game/flagsXH/";
        String baseH = "game/flagsH/";
        String baseL = "game/flags/";
        Pixmap loaded = null;
        boolean nearest = false;
        try {
            if (isRevolutionary) {
                String path = baseXH + "rb" + (civId + civTag.charAt(0)) % 6 + ".png";
                loaded = new Pixmap(FileManager.loadFile(path));
            }
            if (loaded == null) {
                loaded = this.tryLoad(baseXH + civTag + ".png");
            }
            if (loaded == null) {
                loaded = this.tryLoad(baseXH + realTag + ".png");
            }
            if (loaded == null) {
                loaded = this.tryLoad(baseH + civTag + ".png");
            }
            if (loaded == null) {
                loaded = this.tryLoad(baseH + realTag + ".png");
            }
            if (loaded == null) {
                loaded = this.tryLoad(baseL + civTag + ".png");
                boolean bl = nearest = loaded != null;
            }
            if (loaded == null) {
                loaded = this.tryLoad(baseL + realTag + ".png");
                boolean bl = nearest = loaded != null;
            }
            if (loaded == null) {
                loaded = new Pixmap(FileManager.loadFile(baseXH + "ran.png"));
                nearest = true;
            }
            pixmap = loaded = this.resizeNearest(loaded, FLAG_W, FLAG_H);
            if (texture != null) {
                texture.dispose();
            }
            texture = new Texture(pixmap);
            texture.setFilter(nearest ? Texture.TextureFilter.Nearest : Texture.TextureFilter.Nearest, nearest ? Texture.TextureFilter.Nearest : Texture.TextureFilter.Nearest);
        }
        catch (Exception e) {
            pixmap = new Pixmap(FileManager.loadFile(baseXH + "ran.png"));
            if (texture != null) {
                texture.dispose();
            }
            texture = new Texture(pixmap);
            texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
            CFG.exceptionStack(e);
        }
    }

    public static void drawPixel(int x, int y) {
        if (BRUSH_SIZE > 1) {
            switch (brushType) {
                case LINE_BRUSH: {
                    Menu_InGame_FlagPainter.drawLineBrush(START_X, START_Y, x, y);
                    return;
                }
                case LINE_BRUSH_HORIZONTAL: {
                    Menu_InGame_FlagPainter.drawHorizontalLineBrush(START_X, START_Y, x);
                    return;
                }
                case LINE_BRUSH_VERTICAL: {
                    Menu_InGame_FlagPainter.drawVerticalLineBrush(START_X, START_Y, y);
                    return;
                }
                case SQUARE: {
                    Menu_InGame_FlagPainter.drawBrush(x, y);
                    return;
                }
                case CIRCLE: {
                    Menu_InGame_FlagPainter.drawBrushCircle(x, y);
                    return;
                }
                case STAR: {
                    Menu_InGame_FlagPainter.drawStar(x, y);
                    return;
                }
            }
            return;
        }
        if (x < 0 || x >= FLAG_W || y < 0 || y >= FLAG_H) {
            return;
        }
        pixmap.setColor(brushColor);
        pixmap.drawPixel(x, y);
        updateTexture = true;
    }

    public static void drawBrush(int cx, int cy) {
        int r = BRUSH_SIZE / 2;
        for (int y = -r; y <= r; ++y) {
            for (int x = -r; x <= r; ++x) {
                int px = cx + x;
                int py = cy + y;
                if (px < 0 || px >= FLAG_W || py < 0 || py >= FLAG_H) continue;
                pixmap.setColor(brushColor);
                pixmap.drawPixel(px, py);
            }
        }
        updateTexture = true;
    }

    public static void drawBrushCircle(int cx, int cy) {
        int r = Math.max(2, BRUSH_SIZE / 2);
        int r2 = r * r;
        pixmap.setColor(brushColor);
        for (int y = -r; y <= r; ++y) {
            for (int x = -r; x <= r; ++x) {
                int px = cx + x;
                int py = cy + y;
                if (px < 0 || px >= FLAG_W || py < 0 || py >= FLAG_H || x * x + y * y > r2) continue;
                pixmap.drawPixel(px, py);
            }
        }
        updateTexture = true;
    }

    public static void drawHorizontalLineBrush(int x0, int y, int x1) {
        pixmap.setColor(brushColor);
        int r = Math.max(1, BRUSH_SIZE / 2);
        if (x0 > x1) {
            int t = x0;
            x0 = x1;
            x1 = t;
        }
        for (int x = x0; x <= x1; ++x) {
            for (int oy = -r; oy <= r; ++oy) {
                int py = y + oy;
                if (x < 0 || x >= FLAG_W || py < 0 || py >= FLAG_H) continue;
                pixmap.drawPixel(x, py);
            }
        }
        updateTexture = true;
    }

    public static void drawVerticalLineBrush(int x, int y0, int y1) {
        pixmap.setColor(brushColor);
        int r = Math.max(1, BRUSH_SIZE / 2);
        if (y0 > y1) {
            int t = y0;
            y0 = y1;
            y1 = t;
        }
        for (int y = y0; y <= y1; ++y) {
            for (int ox = -r; ox <= r; ++ox) {
                int px = x + ox;
                if (px < 0 || px >= FLAG_W || y < 0 || y >= FLAG_H) continue;
                pixmap.drawPixel(px, y);
            }
        }
        updateTexture = true;
    }

    public static void drawStar(int cx, int cy) {
        pixmap.setColor(brushColor);
        float[] px = new float[10];
        float[] py = new float[10];
        int r = BRUSH_SIZE;
        double outerR = r;
        double innerR = (double)r * 0.382;
        double angle = -1.5707963267948966;
        for (int i = 0; i < 10; ++i) {
            double rad = i % 2 == 0 ? outerR : innerR;
            px[i] = (float)cx + (float)(Math.cos(angle) * rad);
            py[i] = (float)cy + (float)(Math.sin(angle) * rad);
            angle += 0.6283185307179586;
        }
        int minY = (int)Math.max(0.0f, Math.min(py[0], py[1]));
        int maxY = (int)Math.min((float)(FLAG_H - 1), Math.max(py[0], py[1]));
        for (int y = -r; y <= r; ++y) {
            for (int x = -r; x <= r; ++x) {
                int ix = cx + x;
                int iy = cy + y;
                if (ix < 0 || ix >= FLAG_W || iy < 0 || iy >= FLAG_H || !Menu_InGame_FlagPainter.pointInStar(ix, iy, px, py)) continue;
                pixmap.drawPixel(ix, iy);
            }
        }
        updateTexture = true;
    }

    public static void fillBucket(int startX, int startY) {
        if (startX < 0 || startX >= FLAG_W || startY < 0 || startY >= FLAG_H) {
            return;
        }
        for (int x = 0; x < FLAG_W; ++x) {
            for (int y = 0; y < FLAG_H; ++y) {
                Menu_InGame_FlagPainter.visited[x][y] = false;
            }
        }
        Color target = new Color(pixmap.getPixel(startX, startY));
        if (Menu_InGame_FlagPainter.colorMatch(target, brushColor, tolerance)) {
            return;
        }
        ArrayDeque<int[]> stack = new ArrayDeque<int[]>();
        stack.push(new int[]{startX, startY});
        while (!stack.isEmpty()) {
            int[] p = (int[])stack.pop();
            int x = p[0];
            int y = p[1];
            if (x < 0 || x >= FLAG_W || y < 0 || y >= FLAG_H || visited[x][y]) continue;
            Menu_InGame_FlagPainter.visited[x][y] = true;
            Color current = new Color(pixmap.getPixel(x, y));
            if (!Menu_InGame_FlagPainter.colorMatch(current, target, tolerance)) continue;
            pixmap.drawPixel(x, y, Color.rgba8888(brushColor));
            stack.push(new int[]{x + 1, y});
            stack.push(new int[]{x - 1, y});
            stack.push(new int[]{x, y + 1});
            stack.push(new int[]{x, y - 1});
        }
        updateTexture = true;
    }

    private static boolean colorMatch(Color a, Color b, float tolerance) {
        float dr = a.r - b.r;
        float dg = a.g - b.g;
        float db = a.b - b.b;
        float dist = dr * dr + dg * dg + db * db;
        return dist <= tolerance * tolerance;
    }

    private static boolean colorsEqual(Color a, Color b) {
        return a.r == b.r && a.g == b.g && a.b == b.b && a.a == b.a;
    }

    public static void pickColorFromImage(int x, int y) {
        if (x < 0 || x >= FLAG_W || y < 0 || y >= FLAG_H) {
            return;
        }
        int pixel = pixmap.getPixel(x, y);
        brushColor = new Color(pixel);
        CFG.toastM.addM(CFG.lang.get("ColorPicker"), brushColor);
    }

    private static boolean pointInStar(float x, float y, float[] px, float[] py) {
        boolean inside = false;
        int i = 0;
        int j = 9;
        while (i < 10) {
            boolean intersect;
            boolean bl = intersect = py[i] > y != py[j] > y && x < (px[j] - px[i]) * (y - py[i]) / (py[j] - py[i] + 1.0E-4f) + px[i];
            if (intersect) {
                inside = !inside;
            }
            j = i++;
        }
        return inside;
    }

    public static void drawLineBrush(int x0, int y0, int x1, int y1) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;
        int err = dx - dy;
        while (true) {
            Menu_InGame_FlagPainter.drawBrush(x0, y0);
            if (x0 == x1 && y0 == y1) break;
            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x0 += sx;
            }
            if (e2 >= dx) continue;
            err += dx;
            y0 += sy;
        }
    }

    private void updateTexture() {
        updateTexture = false;
        texture.draw(pixmap, 0, 0);
    }

    private Pixmap tryLoad(String path) {
        FileHandle f = FileManager.loadFile(path);
        if (!f.exists()) {
            return null;
        }
        return new Pixmap(f);
    }

    private Pixmap resizeNearest(Pixmap src, int tw, int th) {
        Pixmap dst = new Pixmap(tw, th, Pixmap.Format.RGBA8888);
        float sx = (float)src.getWidth() / (float)tw;
        float sy = (float)src.getHeight() / (float)th;
        for (int y = 0; y < th; ++y) {
            for (int x = 0; x < tw; ++x) {
                int px = (int)((float)x * sx);
                int py = (int)((float)y * sy);
                dst.drawPixel(x, y, src.getPixel(px, py));
            }
        }
        return dst;
    }

    public static void dispose() {
        try {
            pixmap.dispose();
            texture.dispose();
            for (Pixmap p : undoStack) {
                if (p == null) continue;
                p.dispose();
            }
            undoStack.clear();
            for (Pixmap p : redoStack) {
                if (p == null) continue;
                p.dispose();
            }
            redoStack.clear();
            if (savedPixmap != null) {
                savedPixmap.dispose();
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public Menu_InGame_FlagPainter() {
        ArrayList<MenuElemUI> menuElements = new ArrayList<MenuElemUI>();
        menuElements.add(new Button_Game(null, -1, CFG.PADD * 2 + CFG.BUTTON_W, CFG.PADD, CFG.BUTTON_W * 2, true){

            @Override
            public void buildElemHover() {
                ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Save"), CFG.COLOR_HOVER_TITLE));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                this.menuElemHover = new ME_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.PADD, CFG.PADD, true){

            @Override
            public void buildElemHover() {
                ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
                ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
                nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Cancel"), CFG.COLOR_HOVER_TITLE));
                nElements.add(new MEHover_2E(nData));
                nData.clear();
                this.menuElemHover = new ME_Hover_v2(nElements);
            }

            @Override
            public Color getColorE(boolean isActive) {
                return isActive ? CFG.COLOR_NEGATIVE_ACTIVE : (this.getIsHovered() ? CFG.COLOR_NEGATIVE_HOVER : CFG.COLOR_NEGATIVE_2);
            }
        });
        Core.addSimpleTask(new Core.SimpleTask("loadFlagPainter"){

            @Override
            public void update() {
                Menu_InGame_FlagPainter.this.loadFlag(CFG.core.getCiv(civID).getCivTag(), civID, CFG.ideologiesMgr.getRealTag(CFG.core.getCiv(civID).getCivTag()), CFG.ideologiesMgr.getIdeologyID((int)CFG.core.getCiv((int)Menu_InGame_FlagPainter.civID).getIdeology()).REVOLUTIONARY);
            }
        });
        this.initMenu(null, 0, 0, CFG.GAMEWIDTH, CFG.GAMEHEIGHT, menuElements);
        this.updateLang();
        Menu_InGame_FlagPainter.updateFlagPos();
        CFG.menus.getColorPicker().updateColors2();
    }

    public static void updateFlagPos() {
        flagPosY = IMGManager.getIMG(Images.editor_top).getHeight() + (CFG.GAMEHEIGHT - IMGManager.getIMG(Images.editor_top).getHeight()) / 2 - FLAG_H * SCALE / 2;
        flagPosX = Math.max(CFG.PADD * 4, (CFG.GAMEWIDTH - Math.max(CFG.menus.getColorPicker().getWidth(), (int)((float)CFG.CIV_INFO_MENU_WIDTH * 1.25f)) - CFG.PADD * 2) / 2 - FLAG_W * SCALE / 2);
    }

    @Override
    public void updateLang() {
        this.getMenuElem(0).setTextE(CFG.lang.get("Save"));
        this.getMenuElem(1).setTextE(CFG.lang.get("Cancel"));
        this.flagPainterTitle = CFG.lang.get("FlagPainter") + " [AoH2: Definitive Edition]";
        CFG.glyphLay.setText(CFG.fontMain.get(0), this.flagPainterTitle);
        this.iFlagPainterW = (int)CFG.glyphLay.width;
        this.flagPainterTitleName = CFG.core.getCiv(0).getCivName();
        CFG.glyphLay.setText(CFG.fontMain.get(0), this.flagPainterTitleName);
        this.iFlagPainterNameW = (int)CFG.glyphLay.width;
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        IMGManager.getIMG(Images.pix255).draw(oSB, 0, 0, CFG.GAMEWIDTH, CFG.GAMEHEIGHT);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.25f));
        IMGManager.getIMG(Images.gameLogo).draw(oSB, CFG.PADD * 2, CFG.GAMEHEIGHT - CFG.PADD * 2 - IMGManager.getIMG(Images.gameLogo).getHeight());
        oSB.setColor(Color.WHITE);
        CFG.drawEditorTitle_Edge_R_Reflected(oSB, iTranslateX, this.getMenuPosY() + iTranslateY, CFG.GAMEWIDTH, CFG.BUTTON_H + CFG.PADD * 2);
        CFG.drawTextDefaultWithShadow(oSB, this.flagPainterTitle, CFG.GAMEWIDTH / 2 - (this.iFlagPainterW + CFG.CIV_FLAG_WIDTH + CFG.PADD) / 2 + CFG.PADD + CFG.CIV_FLAG_WIDTH + iTranslateX, CFG.PADD + CFG.BUTTON_H / 2 - CFG.TEXT_HEIGHT_DEFAULT - CFG.PADD / 2 + this.getMenuPosY() + iTranslateY, new Color(CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.r, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.g, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.b, 0.95f));
        CFG.fontMain.get(0).getData().setScale(0.8f);
        CFG.drawTextDefaultWithShadow(oSB, this.flagPainterTitleName, CFG.GAMEWIDTH / 2 - (int)((float)this.iFlagPainterNameW * 0.8f / 2.0f) + iTranslateX, CFG.PADD + CFG.BUTTON_H / 2 + CFG.PADD + this.getMenuPosY() + iTranslateY, new Color(CFG.COLOR_TEXT_CNG_TOP_SCENARIO_INFO.r, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_INFO.g, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_INFO.b, 0.75f));
        CFG.fontMain.get(0).getData().setScale(1.0f);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        if (updateTexture) {
            this.updateTexture();
        }
        try {
            if (texture != null) {
                oSB.draw(texture, (float)(CFG.BUTTON_W * 3 + CFG.PADD * 6), (float)(-(CFG.PADD * 2 + FLAG_H)));
                oSB.setShader(Renderer.shaderAlpha);
                texture.bind(1);
                Gdx.gl.glActiveTexture(33984);
                IMGManager.getIMG(Images.flagBigMask).draw(oSB, CFG.BUTTON_W * 3 + CFG.PADD * 9 + FLAG_W, CFG.PADD * 2, IMGManager.getIMG(Images.flagBigMask).getWidth(), IMGManager.getIMG(Images.flagBigMask).getHeight());
                oSB.flush();
                oSB.setShader(AoCGame.shaderDef);
                IMGManager.getIMG(Images.flagBigOver).draw(oSB, CFG.BUTTON_W * 3 + CFG.PADD * 9 + FLAG_W + (IMGManager.getIMG(Images.flagBigMask).getWidth() - IMGManager.getIMG(Images.flagBigOver).getWidth()) / 2, CFG.PADD * 2);
                oSB.setColor(new Color(0.11764706f, 0.11764706f, 0.11764706f, 1.0f));
                IMGManager.getIMG(Images.pix255).draw(oSB, flagPosX - CFG.PADD * 4, flagPosY - CFG.PADD * 4, FLAG_W * SCALE + CFG.PADD * 8, FLAG_H * SCALE + CFG.PADD * 8);
                oSB.setColor(Color.WHITE);
                oSB.draw(texture, (float)flagPosX, (float)(-(flagPosY + FLAG_H * SCALE)), (float)(FLAG_W * SCALE), (float)(FLAG_H * SCALE));
                if (GRID > 0) {
                    int sy;
                    int i;
                    int sy2;
                    int sx;
                    if (GRID == 1) {
                        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.25f));
                    } else {
                        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
                    }
                    for (int x = 0; x <= FLAG_W; ++x) {
                        sx = flagPosX + x * SCALE;
                        sy2 = flagPosY;
                        IMGManager.getIMG(Images.pix255).draw2(oSB, sx, sy2, 1, FLAG_H * SCALE);
                    }
                    for (int y = 0; y <= FLAG_H; ++y) {
                        sx = flagPosX;
                        sy2 = flagPosY + y * SCALE;
                        IMGManager.getIMG(Images.pix255).draw2(oSB, sx, sy2, FLAG_W * SCALE, 1);
                    }
                    if (GRID == 1) {
                        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.35f));
                    } else {
                        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.35f));
                    }
                    for (i = 0; i < this.majorX.length; ++i) {
                        sx = flagPosX + this.majorX[i] * SCALE;
                        IMGManager.getIMG(Images.pix255).draw2(oSB, sx, flagPosY, 2, FLAG_H * SCALE);
                    }
                    for (i = 0; i < this.majorY.length; ++i) {
                        sy = flagPosY + this.majorY[i] * SCALE;
                        IMGManager.getIMG(Images.pix255).draw2(oSB, flagPosX, sy, FLAG_W * SCALE, 2);
                    }
                    if (BRUSH_SIZE > 1) {
                        if (GRID == 1) {
                            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.175f));
                        } else {
                            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.175f));
                        }
                        int sx2 = flagPosX + FLAG_W / 2 * SCALE - BRUSH_SIZE * SCALE / 2;
                        IMGManager.getIMG(Images.pix255).draw2(oSB, sx2, flagPosY, 2, FLAG_H * SCALE);
                        sx2 = flagPosX + FLAG_W / 2 * SCALE + BRUSH_SIZE * SCALE / 2;
                        IMGManager.getIMG(Images.pix255).draw2(oSB, sx2, flagPosY, 2, FLAG_H * SCALE);
                        sy = flagPosY + FLAG_H / 2 * SCALE - BRUSH_SIZE * SCALE / 2;
                        IMGManager.getIMG(Images.pix255).draw2(oSB, flagPosX, sy, FLAG_W * SCALE, 2);
                        sy = flagPosY + FLAG_H / 2 * SCALE + BRUSH_SIZE * SCALE / 2;
                        IMGManager.getIMG(Images.pix255).draw2(oSB, flagPosX, sy, FLAG_W * SCALE, 2);
                    }
                    oSB.setColor(Color.WHITE);
                }
                int size = BRUSH_SIZE * SCALE;
                if (brushType == BrushType.FILL_BUCKET) {
                    size = 1 * SCALE;
                }
                int x = Touch.getMousePosX() - size / 2;
                int y = Touch.getMousePosY() - size / 2;
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.15f));
                IMGManager.getIMG(Images.pix255).draw(oSB, x, y, size, size);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.3f));
                IMGManager.getIMG(Images.pix255).draw(oSB, x - 1, y + size, size + 2, 1);
                IMGManager.getIMG(Images.pix255).draw(oSB, x - 1, y - 1, size + 2, 1);
                IMGManager.getIMG(Images.pix255).draw(oSB, x - 1, y - 1, 1, size + 2);
                IMGManager.getIMG(Images.pix255).draw(oSB, x + size, y - 1, 1, size + 2);
                oSB.setColor(Color.WHITE);
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    @Override
    public void onHovered() {
        CFG.menus.setOrderOfMenu_FlagPainter();
        super.onHovered();
    }

    @Override
    public final void actionEL(int iID) {
        CFG.menus.setOrderOfMenu_FlagPainter();
        switch (iID) {
            case 0: {
                CFG.brushMode = false;
                CFG.core.setActiveProvID(-1);
                CFG.menus.getColorPicker().setVisible(false, ColorPicker_AoC.PickerAction.FLAG_PAINTER);
                CFG.menus.setMenuIDWithoutAnim(View.eFLAG_PAINTER_GENERATE_FLAG);
                return;
            }
            case 1: {
                this.onBackPressed();
                return;
            }
        }
        super.actionEL(iID);
    }

    @Override
    public void onBackPressed() {
        CFG.brushMode = false;
        CFG.menus.setMenuIDWithoutAnim(View.eINGAME);
        CFG.core.setActiveProvID(-1);
        CFG.menus.getColorPicker().setVisible(false, ColorPicker_AoC.PickerAction.FLAG_PAINTER);
    }

    public static void saveBackup() {
        savedPixmap = new Pixmap(pixmap.getWidth(), pixmap.getHeight(), pixmap.getFormat());
        savedPixmap.drawPixmap(pixmap, 0, 0);
    }

    public static void loadBackup() {
        if (savedPixmap != null) {
            pixmap.drawPixmap(savedPixmap, 0, 0);
            updateTexture = true;
        }
    }

    public static void drawHorizontalFlag() {
        int w = pixmap.getWidth();
        int h = pixmap.getHeight();
        pixmap.setColor(Color.WHITE);
        pixmap.fillRectangle(0, 0, w, h / 2);
        pixmap.setColor(Color.RED);
        pixmap.fillRectangle(0, h / 2, w, h / 2);
        updateTexture = true;
    }

    public static void drawHorizontalTricolor() {
        int w = pixmap.getWidth();
        int h = pixmap.getHeight();
        int stripe = h / 3;
        pixmap.setColor(Color.BLACK);
        pixmap.fillRectangle(0, 0, w, stripe);
        pixmap.setColor(Color.RED);
        pixmap.fillRectangle(0, stripe, w, stripe);
        pixmap.setColor(Color.YELLOW);
        pixmap.fillRectangle(0, stripe * 2, w, h - stripe * 2);
        updateTexture = true;
    }

    public static void drawHorizontalFlag4() {
        int w = pixmap.getWidth();
        int h = pixmap.getHeight();
        int stripe = h / 4;
        pixmap.setColor(new Color(0.85f, 0.7f, 0.2f, 1.0f));
        pixmap.fillRectangle(0, 0, w, stripe);
        pixmap.setColor(new Color(0.7f, 0.15f, 0.15f, 1.0f));
        pixmap.fillRectangle(0, stripe, w, stripe);
        pixmap.setColor(new Color(0.1f, 0.2f, 0.45f, 1.0f));
        pixmap.fillRectangle(0, stripe * 2, w, stripe);
        pixmap.setColor(new Color(0.95f, 0.95f, 0.9f, 1.0f));
        pixmap.fillRectangle(0, stripe * 3, w, h - stripe * 3);
        updateTexture = true;
    }

    public static void drawVerticalFlag2() {
        int w = pixmap.getWidth();
        int h = pixmap.getHeight();
        pixmap.setColor(Color.BLUE);
        pixmap.fillRectangle(0, 0, w / 2, h);
        pixmap.setColor(Color.WHITE);
        pixmap.fillRectangle(w / 2, 0, w / 2, h);
        updateTexture = true;
    }

    public static void drawVerticalTricolor() {
        int w = pixmap.getWidth();
        int h = pixmap.getHeight();
        int stripe = w / 3;
        pixmap.setColor(Color.BLUE);
        pixmap.fillRectangle(0, 0, stripe, h);
        pixmap.setColor(Color.WHITE);
        pixmap.fillRectangle(stripe, 0, stripe, h);
        pixmap.setColor(Color.RED);
        pixmap.fillRectangle(stripe * 2, 0, w - stripe * 2, h);
        updateTexture = true;
    }

    public static void drawVerticalFlag4() {
        int w = pixmap.getWidth();
        int h = pixmap.getHeight();
        int stripe = w / 4;
        pixmap.setColor(new Color(0.1f, 0.1f, 0.12f, 1.0f));
        pixmap.fillRectangle(0, 0, stripe, h);
        pixmap.setColor(new Color(0.75f, 0.2f, 0.2f, 1.0f));
        pixmap.fillRectangle(stripe, 0, stripe, h);
        pixmap.setColor(new Color(0.15f, 0.35f, 0.65f, 1.0f));
        pixmap.fillRectangle(stripe * 2, 0, stripe, h);
        pixmap.setColor(new Color(0.9f, 0.8f, 0.3f, 1.0f));
        pixmap.fillRectangle(stripe * 3, 0, w - stripe * 3, h);
        updateTexture = true;
    }

    public static void drawPreset1() {
        int i;
        int w = pixmap.getWidth();
        int h = pixmap.getHeight();
        pixmap.setColor(Color.BLUE);
        pixmap.fillRectangle(0, 0, w, h);
        pixmap.setColor(Color.WHITE);
        for (i = 0; i < h; ++i) {
            pixmap.drawLine(0, i, w, i - w);
        }
        for (i = 0; i < h; ++i) {
            pixmap.drawLine(0, h - i, w, h - i + w);
        }
        updateTexture = true;
    }

    public static void drawScotlandFlag() {
        int y;
        int x;
        int i;
        int w = FLAG_W;
        int h = FLAG_H;
        for (int x2 = 0; x2 < w; ++x2) {
            for (int y2 = 0; y2 < h; ++y2) {
                pixmap.drawPixel(x2, y2, Color.rgba8888(new Color(0.1f, 0.3f, 0.8f, 1.0f)));
            }
        }
        int thickness = 10;
        for (i = -thickness; i <= thickness; ++i) {
            for (x = 0; x < w; ++x) {
                y = (int)((float)h * (float)x / (float)w) + i;
                if (y < 0 || y >= h) continue;
                pixmap.drawPixel(x, y, Color.rgba8888(Color.WHITE));
            }
        }
        for (i = -thickness; i <= thickness; ++i) {
            for (x = 0; x < w; ++x) {
                y = h - (int)((float)h * (float)x / (float)w) + i;
                if (y < 0 || y >= h) continue;
                pixmap.drawPixel(x, y, Color.rgba8888(Color.WHITE));
            }
        }
        updateTexture = true;
    }

    public static void drawEnglandFlag() {
        int w = pixmap.getWidth();
        int h = pixmap.getHeight();
        pixmap.setColor(Color.WHITE);
        pixmap.fillRectangle(0, 0, w, h);
        pixmap.setColor(Color.RED);
        int crossWidth = w / 5;
        pixmap.fillRectangle(w / 2 - crossWidth / 2, 0, crossWidth, h);
        pixmap.fillRectangle(0, h / 2 - crossWidth / 2, w, crossWidth);
        updateTexture = true;
    }

    public static void drawUSAFlag() {
        int w = pixmap.getWidth();
        int h = pixmap.getHeight();
        int stripes = 13;
        int baseH = h / stripes;
        int remainder = h % stripes;
        int y = 0;
        for (int i = 0; i < stripes; ++i) {
            int currentH = baseH;
            if (i < remainder) {
                ++currentH;
            }
            if (i % 2 == 0) {
                pixmap.setColor(Color.RED);
            } else {
                pixmap.setColor(Color.WHITE);
            }
            pixmap.fillRectangle(0, y, w, currentH);
            y += currentH;
        }
        int cantonW = (int)((float)w * 0.4f);
        int cantonH = baseH * 7 + Math.min(remainder, 7);
        pixmap.setColor(Color.BLUE);
        pixmap.fillRectangle(0, 0, cantonW, cantonH);
        updateTexture = true;
    }

    public static void drawAragonFlag() {
        int w = pixmap.getWidth();
        int h = pixmap.getHeight();
        int stripes = 8;
        int stripeH = h / stripes;
        int remainder = h % stripes;
        int y = 0;
        for (int i = 0; i < stripes; ++i) {
            int currentH = stripeH;
            if (i < remainder) {
                ++currentH;
            }
            if (i % 2 == 0) {
                pixmap.setColor(Color.YELLOW);
            } else {
                pixmap.setColor(Color.RED);
            }
            pixmap.fillRectangle(0, y, w, currentH);
            y += currentH;
        }
        updateTexture = true;
    }

    public static void drawUnionJack() {
        int x;
        int y;
        int y2;
        int x2;
        int i;
        int w = FLAG_W;
        int h = FLAG_H;
        for (int x3 = 0; x3 < w; ++x3) {
            for (int y3 = 0; y3 < h; ++y3) {
                pixmap.drawPixel(x3, y3, Color.rgba8888(new Color(0.05f, 0.15f, 0.6f, 1.0f)));
            }
        }
        int midX = w / 2;
        int midY = h / 2;
        int whiteCross = 13;
        int redCross = 8;
        int whiteDiag = 9;
        int redDiag = 3;
        float ratio = (float)h / (float)w;
        Menu_InGame_FlagPainter.drawDiagonalCentered(w, h, Color.WHITE, 0, whiteDiag, ratio, false);
        Menu_InGame_FlagPainter.drawDiagonalCentered(w, h, Color.WHITE, 0, whiteDiag, ratio, true);
        int offset = 3;
        Menu_InGame_FlagPainter.drawDiagonalCentered(w, h, Color.RED, offset, redDiag, ratio, false);
        Menu_InGame_FlagPainter.drawDiagonalCentered(w, h, Color.RED, -offset, redDiag, ratio, true);
        for (i = -whiteCross; i <= whiteCross; ++i) {
            for (x2 = 0; x2 < w; ++x2) {
                y2 = midY + i;
                if (y2 < 0 || y2 >= h) continue;
                pixmap.drawPixel(x2, y2, Color.rgba8888(Color.WHITE));
            }
            for (y = 0; y < h; ++y) {
                x = midX + i;
                if (x < 0 || x >= w) continue;
                pixmap.drawPixel(x, y, Color.rgba8888(Color.WHITE));
            }
        }
        for (i = -redCross; i <= redCross; ++i) {
            for (x2 = 0; x2 < w; ++x2) {
                y2 = midY + i;
                if (y2 < 0 || y2 >= h) continue;
                pixmap.drawPixel(x2, y2, Color.rgba8888(Color.RED));
            }
            for (y = 0; y < h; ++y) {
                x = midX + i;
                if (x < 0 || x >= w) continue;
                pixmap.drawPixel(x, y, Color.rgba8888(Color.RED));
            }
        }
        updateTexture = true;
    }

    private static void drawDiagonalCentered(int w, int h, Color color, int offset, int thickness, float ratio, boolean reverse) {
        float cx = (float)w / 2.0f;
        float cy = (float)h / 2.0f;
        for (int x = 0; x < w; ++x) {
            float dx = (float)x - cx;
            float dy = dx * ratio;
            if (reverse) {
                dy = -dy;
            }
            int y = (int)(cy + dy) + offset;
            for (int i = -thickness; i <= thickness; ++i) {
                int yy = y + i;
                if (yy < 0 || yy >= h) continue;
                pixmap.drawPixel(x, yy, Color.rgba8888(color));
            }
        }
    }

    public static void drawNorthKoreaFlag() {
        int w = FLAG_W;
        int h = FLAG_H;
        int blueH = h / 6;
        int whiteH = h / 12;
        int redH = h - 2 * (blueH + whiteH);
        int y = 0;
        Menu_InGame_FlagPainter.fillRect(0, y, w, blueH, new Color(0.05f, 0.2f, 0.7f, 1.0f));
        Menu_InGame_FlagPainter.fillRect(0, y += blueH, w, whiteH, Color.WHITE);
        Menu_InGame_FlagPainter.fillRect(0, y += whiteH, w, redH, Color.RED);
        int centerY = y + redH / 2;
        int centerX = w / 3;
        Menu_InGame_FlagPainter.drawCircle(centerX, centerY, redH / 3, Color.WHITE);
        Menu_InGame_FlagPainter.fillRect(0, y += redH, w, whiteH, Color.WHITE);
        Menu_InGame_FlagPainter.fillRect(0, y += whiteH, w, h - y, new Color(0.05f, 0.2f, 0.7f, 1.0f));
        updateTexture = true;
    }

    private static void drawCircle(int cx, int cy, int r, Color color) {
        int col = Color.rgba8888(color);
        for (int y = -r; y <= r; ++y) {
            for (int x = -r; x <= r; ++x) {
                if (x * x + y * y > r * r) continue;
                int px = cx + x;
                int py = cy + y;
                if (px < 0 || px >= FLAG_W || py < 0 || py >= FLAG_H) continue;
                pixmap.drawPixel(px, py, col);
            }
        }
    }

    private static void fillRect(int x, int y, int w, int h, Color c) {
        int col = Color.rgba8888(c);
        for (int i = 0; i < w; ++i) {
            for (int j = 0; j < h; ++j) {
                pixmap.drawPixel(x + i, y + j, col);
            }
        }
    }

    public static void drawDiagonalTwoColorFlag() {
        int w = FLAG_W;
        int h = FLAG_H;
        for (int x = 0; x < w; ++x) {
            int yLine = (int)((float)x * (float)h / (float)w);
            for (int y = 0; y < h; ++y) {
                if (y < yLine) {
                    pixmap.drawPixel(x, y, Color.rgba8888(new Color(1.0f, 1.0f, 1.0f, 1.0f)));
                    continue;
                }
                pixmap.drawPixel(x, y, Color.rgba8888(new Color(0.8f, 0.1f, 0.1f, 1.0f)));
            }
        }
        updateTexture = true;
    }

    public static void drawMirroredDiagonalFlag() {
        int w = FLAG_W;
        int h = FLAG_H;
        for (int x = 0; x < w; ++x) {
            int yLine = h - (int)((float)x * (float)h / (float)w);
            for (int y = 0; y < h; ++y) {
                if (y < yLine) {
                    pixmap.drawPixel(x, y, Color.rgba8888(new Color(1.0f, 1.0f, 1.0f, 1.0f)));
                    continue;
                }
                pixmap.drawPixel(x, y, Color.rgba8888(new Color(0.8f, 0.1f, 0.1f, 1.0f)));
            }
        }
        updateTexture = true;
    }

    public static void drawPreset2() {
        int w = pixmap.getWidth();
        int h = pixmap.getHeight();
        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                if (x + y < w) {
                    pixmap.setColor(Color.WHITE);
                } else {
                    pixmap.setColor(Color.RED);
                }
                pixmap.drawPixel(x, y);
            }
        }
        updateTexture = true;
    }

    public static void drawPreset3() {
        int w = pixmap.getWidth();
        int h = pixmap.getHeight();
        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                if (x > y) {
                    pixmap.setColor(Color.WHITE);
                } else {
                    pixmap.setColor(Color.RED);
                }
                pixmap.drawPixel(x, y);
            }
        }
        updateTexture = true;
    }

    public static void drawTogoFlag() {
        int w = FLAG_W;
        int h = FLAG_H;
        Color green = new Color(0.05f, 0.6f, 0.2f, 1.0f);
        Color yellow = new Color(0.95f, 0.8f, 0.1f, 1.0f);
        int stripeH = h / 5;
        for (int i = 0; i < 5; ++i) {
            Color c = i % 2 == 0 ? green : yellow;
            int col = Color.rgba8888(c);
            int yStart = i * stripeH;
            int yEnd = i == 4 ? h : (i + 1) * stripeH;
            for (int y = yStart; y < yEnd; ++y) {
                for (int x = 0; x < w; ++x) {
                    pixmap.drawPixel(x, y, col);
                }
            }
        }
        int cantonSize = stripeH * 3;
        for (int x = 0; x < cantonSize; ++x) {
            for (int y = 0; y < cantonSize; ++y) {
                pixmap.drawPixel(x, y, Color.rgba8888(Color.RED));
            }
        }
        updateTexture = true;
    }

    public static void drawNorwayFlag() {
        int w = FLAG_W;
        int h = FLAG_H;
        Color red = new Color(0.8f, 0.05f, 0.1f, 1.0f);
        for (int x = 0; x < w; ++x) {
            for (int y = 0; y < h; ++y) {
                pixmap.drawPixel(x, y, Color.rgba8888(red));
            }
        }
        int crossX = w / 3;
        int crossY = h / 2;
        int whiteT = 14;
        for (int i = -whiteT; i <= whiteT; ++i) {
            for (int y = 0; y < h; ++y) {
                int x = crossX + i;
                if (x < 0 || x >= w) continue;
                pixmap.drawPixel(x, y, Color.rgba8888(Color.WHITE));
            }
            for (int x = 0; x < w; ++x) {
                int y = crossY + i;
                if (y < 0 || y >= h) continue;
                pixmap.drawPixel(x, y, Color.rgba8888(Color.WHITE));
            }
        }
        Color blue = new Color(0.05f, 0.2f, 0.7f, 1.0f);
        int blueT = 7;
        for (int i = -blueT; i <= blueT; ++i) {
            for (int y = 0; y < h; ++y) {
                int x = crossX + i;
                if (x < 0 || x >= w) continue;
                pixmap.drawPixel(x, y, Color.rgba8888(blue));
            }
            for (int x = 0; x < w; ++x) {
                int y = crossY + i;
                if (y < 0 || y >= h) continue;
                pixmap.drawPixel(x, y, Color.rgba8888(blue));
            }
        }
        updateTexture = true;
    }

    public static void drawJapanFlag() {
        int w = FLAG_W;
        int h = FLAG_H;
        for (int x = 0; x < w; ++x) {
            for (int y = 0; y < h; ++y) {
                pixmap.drawPixel(x, y, Color.rgba8888(Color.WHITE));
            }
        }
        int cx = w / 2;
        int cy = h / 2;
        int r = h * 3 / 10;
        Color red = new Color(0.85f, 0.05f, 0.1f, 1.0f);
        int col = Color.rgba8888(red);
        for (int y = -r; y <= r; ++y) {
            for (int x = -r; x <= r; ++x) {
                if (x * x + y * y > r * r) continue;
                int px = cx + x;
                int py = cy + y;
                if (px < 0 || px >= w || py < 0 || py >= h) continue;
                pixmap.drawPixel(px, py, col);
            }
        }
        updateTexture = true;
    }

    public static void drawPortugalFlag() {
        int y;
        int x;
        int w = FLAG_W;
        int h = FLAG_H;
        int greenW = w * 2 / 5;
        Color green = new Color(0.05f, 0.55f, 0.2f, 1.0f);
        Color red = new Color(0.85f, 0.1f, 0.1f, 1.0f);
        int greenCol = Color.rgba8888(green);
        int redCol = Color.rgba8888(red);
        for (x = 0; x < greenW; ++x) {
            for (y = 0; y < h; ++y) {
                pixmap.drawPixel(x, y, greenCol);
            }
        }
        for (x = greenW; x < w; ++x) {
            for (y = 0; y < h; ++y) {
                pixmap.drawPixel(x, y, redCol);
            }
        }
        updateTexture = true;
    }

    public static void drawCzechFlag() {
        int w = FLAG_W;
        int h = FLAG_H;
        int midY = h / 2;
        int midX = w / 2;
        Color white = Color.WHITE;
        Color red = new Color(0.85f, 0.1f, 0.1f, 1.0f);
        Color blue = new Color(0.05f, 0.2f, 0.7f, 1.0f);
        int whiteCol = Color.rgba8888(white);
        int redCol = Color.rgba8888(red);
        int blueCol = Color.rgba8888(blue);
        for (int y = 0; y < h; ++y) {
            int col = y < midY ? whiteCol : redCol;
            for (int x = 0; x < w; ++x) {
                pixmap.drawPixel(x, y, col);
            }
        }
        for (int x = 0; x < midX; ++x) {
            float t = (float)x / (float)midX;
            int yTop = (int)((float)midY * t);
            int yBottom = h - yTop;
            for (int y = yTop; y < yBottom; ++y) {
                pixmap.drawPixel(x, y, blueCol);
            }
        }
        updateTexture = true;
    }

    public static void drawBeninFlag() {
        int y;
        int x;
        int w = FLAG_W;
        int h = FLAG_H;
        Color green = new Color(0.05f, 0.6f, 0.2f, 1.0f);
        Color yellow = new Color(0.95f, 0.8f, 0.1f, 1.0f);
        Color red = new Color(0.85f, 0.1f, 0.1f, 1.0f);
        int greenCol = Color.rgba8888(green);
        int yellowCol = Color.rgba8888(yellow);
        int redCol = Color.rgba8888(red);
        int leftW = w * 2 / 5;
        for (x = 0; x < leftW; ++x) {
            for (y = 0; y < h; ++y) {
                pixmap.drawPixel(x, y, greenCol);
            }
        }
        for (x = leftW; x < w; ++x) {
            for (y = 0; y < h / 2; ++y) {
                pixmap.drawPixel(x, y, yellowCol);
            }
            for (y = h / 2; y < h; ++y) {
                pixmap.drawPixel(x, y, redCol);
            }
        }
        updateTexture = true;
    }

    public static void drawChequeredFlag() {
        int i;
        int w = FLAG_W;
        int h = FLAG_H;
        int cellsX = 8;
        int cellsY = 8;
        int black = Color.rgba8888(Color.BLACK);
        int white = Color.rgba8888(Color.WHITE);
        int[] xPos = new int[cellsX + 1];
        int[] yPos = new int[cellsY + 1];
        for (i = 0; i <= cellsX; ++i) {
            xPos[i] = i * w / cellsX;
        }
        for (i = 0; i <= cellsY; ++i) {
            yPos[i] = i * h / cellsY;
        }
        for (int cy = 0; cy < cellsY; ++cy) {
            for (int cx = 0; cx < cellsX; ++cx) {
                int col = (cx + cy) % 2 == 0 ? black : white;
                for (int x = xPos[cx]; x < xPos[cx + 1]; ++x) {
                    for (int y = yPos[cy]; y < yPos[cy + 1]; ++y) {
                        pixmap.drawPixel(x, y, col);
                    }
                }
            }
        }
        updateTexture = true;
    }

    public static void drawSpainFlag() {
        int x;
        int yy;
        int w = FLAG_W;
        int h = FLAG_H;
        Color red = new Color(0.85f, 0.1f, 0.1f, 1.0f);
        Color yellow = new Color(0.95f, 0.8f, 0.1f, 1.0f);
        int redCol = Color.rgba8888(red);
        int yellowCol = Color.rgba8888(yellow);
        int redH = h / 4;
        int yellowH = h - 2 * redH;
        int y = 0;
        for (yy = 0; yy < redH; ++yy) {
            for (x = 0; x < w; ++x) {
                pixmap.drawPixel(x, y + yy, redCol);
            }
        }
        y += redH;
        for (yy = 0; yy < yellowH; ++yy) {
            for (x = 0; x < w; ++x) {
                pixmap.drawPixel(x, y + yy, yellowCol);
            }
        }
        y += yellowH;
        for (yy = 0; yy < redH; ++yy) {
            for (x = 0; x < w; ++x) {
                pixmap.drawPixel(x, y + yy, redCol);
            }
        }
        updateTexture = true;
    }

    public static void drawMedievalQuarteredFlag() {
        int y;
        int x;
        int w = FLAG_W;
        int h = FLAG_H;
        Color red = new Color(0.85f, 0.1f, 0.1f, 1.0f);
        Color white = Color.WHITE;
        int redCol = Color.rgba8888(red);
        int whiteCol = Color.rgba8888(white);
        int midX = w / 2;
        int midY = h / 2;
        for (x = 0; x < midX; ++x) {
            for (y = 0; y < midY; ++y) {
                pixmap.drawPixel(x, y, redCol);
            }
        }
        for (x = midX; x < w; ++x) {
            for (y = 0; y < midY; ++y) {
                pixmap.drawPixel(x, y, whiteCol);
            }
        }
        for (x = 0; x < midX; ++x) {
            for (y = midY; y < h; ++y) {
                pixmap.drawPixel(x, y, whiteCol);
            }
        }
        for (x = midX; x < w; ++x) {
            for (y = midY; y < h; ++y) {
                pixmap.drawPixel(x, y, redCol);
            }
        }
        updateTexture = true;
    }

    public static void drawTurkeyFlag() {
        int w = FLAG_W;
        int h = FLAG_H;
        Color red = new Color(0.85f, 0.1f, 0.1f, 1.0f);
        Color white = Color.WHITE;
        int redCol = Color.rgba8888(red);
        int whiteCol = Color.rgba8888(white);
        for (int x = 0; x < w; ++x) {
            for (int y = 0; y < h; ++y) {
                pixmap.drawPixel(x, y, redCol);
            }
        }
        int cx = w * 2 / 5;
        int cy = h / 2;
        int rOuter = h * 3 / 10;
        int rInner = h * 2 / 10;
        for (int y = -rOuter; y <= rOuter; ++y) {
            for (int x = -rOuter; x <= rOuter; ++x) {
                int ox = x - rInner / 2;
                int dOuter = x * x + y * y;
                int dInner = ox * ox + y * y;
                if (dOuter > rOuter * rOuter || dInner <= rInner * rInner) continue;
                int px = cx + x;
                int py = cy + y;
                if (px < 0 || px >= w || py < 0 || py >= h) continue;
                pixmap.drawPixel(px, py, whiteCol);
            }
        }
        int sx = w * 3 / 5 + 15;
        int sy = h / 2;
        float R = (float)h / 8.0f;
        float r = R * 0.4f;
        float[] px = new float[10];
        float[] py = new float[10];
        for (int i = 0; i < 10; ++i) {
            float angle = (float)(1.5707963267948966 + (double)i * Math.PI / 5.0);
            float radius = i % 2 == 0 ? R : r;
            px[i] = (float)sx + (float)Math.cos(angle) * radius;
            py[i] = (float)sy + (float)Math.sin(angle) * radius;
        }
        for (int y = (int)((float)sy - R); y <= (int)((float)sy + R); ++y) {
            for (int x = (int)((float)sx - R); x <= (int)((float)sx + R); ++x) {
                boolean inside = false;
                int i = 0;
                int j = 9;
                while (i < 10) {
                    boolean intersect;
                    float xi = px[i];
                    float yi = py[i];
                    float xj = px[j];
                    float yj = py[j];
                    boolean bl = intersect = yi > (float)y != yj > (float)y && (float)x < (xj - xi) * ((float)y - yi) / (yj - yi + 1.0E-5f) + xi;
                    if (intersect) {
                        inside = !inside;
                    }
                    j = i++;
                }
                if (!inside || x < 0 || x >= w || y < 0 || y >= h) continue;
                pixmap.drawPixel(x, y, whiteCol);
            }
        }
        updateTexture = true;
    }

    static {
        savedPixmap = null;
        brushColor = new Color(0.85f, 0.1f, 0.1f, 1.0f);
        undoStack = new ArrayDeque<Pixmap>();
        redoStack = new ArrayDeque<Pixmap>();
        UNDO_LIMIT = 5;
        visited = new boolean[FLAG_W][FLAG_H];
        FLAG_COLORS = new Color[]{new Color(0.75f, 0.0f, 0.0f, 1.0f), new Color(0.85f, 0.1f, 0.1f, 1.0f), new Color(0.6f, 0.0f, 0.1f, 1.0f), new Color(0.0f, 0.2f, 0.6f, 1.0f), new Color(0.1f, 0.3f, 0.8f, 1.0f), new Color(0.0f, 0.5f, 0.7f, 1.0f), new Color(0.0f, 0.5f, 0.2f, 1.0f), new Color(0.1f, 0.6f, 0.3f, 1.0f), new Color(0.0f, 0.4f, 0.1f, 1.0f), new Color(0.95f, 0.8f, 0.1f, 1.0f), new Color(1.0f, 0.9f, 0.2f, 1.0f), Color.WHITE, new Color(0.95f, 0.95f, 0.95f, 1.0f), Color.BLACK, new Color(0.2f, 0.2f, 0.2f, 1.0f), new Color(0.9f, 0.4f, 0.0f, 1.0f), new Color(0.8f, 0.5f, 0.1f, 1.0f), new Color(0.4f, 0.0f, 0.5f, 1.0f), new Color(0.4f, 0.2f, 0.1f, 1.0f), new Color(0.3f, 0.7f, 0.9f, 1.0f)};
    }

    public static enum BrushType {
        SQUARE,
        CIRCLE,
        LINE_BRUSH,
        LINE_BRUSH_HORIZONTAL,
        LINE_BRUSH_VERTICAL,
        STAR,
        FILL_BUCKET;

    }
}
