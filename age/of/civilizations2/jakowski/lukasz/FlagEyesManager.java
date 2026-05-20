package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.IMGManager;
import age.of.civilizations2.jakowski.lukasz.Image;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class FlagEyesManager {
    public static int RAND = 0;
    public static long RAND_TIME = 0L;
    public static List<Image> neutral = new ArrayList<Image>();
    public static int neutralSize = 0;
    public static List<Image> friendly = new ArrayList<Image>();
    public static int friendlySize = 0;
    public static List<Image> negative = new ArrayList<Image>();
    public static int negativeSize = 0;
    public static List<Image> atWar = new ArrayList<Image>();
    public static int atWarSize = 0;
    public static List<Image> atWarLosing = new ArrayList<Image>();
    public static int atWarLosingSize = 0;
    public static List<Image> lordOfPlayer = new ArrayList<Image>();
    public static int lordOfPlayerSize = 0;
    public static List<Image> vassalOfPlayer = new ArrayList<Image>();
    public static int vassalOfPlayerSize = 0;
    public static List<Image> dead = new ArrayList<Image>();
    public static int deadSize = 0;

    public static void drawEyesBig(SpriteBatch oSB, int posX, int posY, int civID) {
    }

    public static void drawNeutral(SpriteBatch oSB, int posX, int posY, int civID) {
        neutral.get((civID + RAND) % neutralSize).draw(oSB, posX, posY, IMGManager.getIMG(Images.flagBigMask).getWidth(), IMGManager.getIMG(Images.flagBigMask).getHeight());
    }

    public static void drawFriendly(SpriteBatch oSB, int posX, int posY, int civID) {
        friendly.get((civID + RAND) % friendlySize).draw(oSB, posX, posY, IMGManager.getIMG(Images.flagBigMask).getWidth(), IMGManager.getIMG(Images.flagBigMask).getHeight());
    }

    public static void drawNegative(SpriteBatch oSB, int posX, int posY, int civID) {
        negative.get((civID + RAND) % negativeSize).draw(oSB, posX, posY, IMGManager.getIMG(Images.flagBigMask).getWidth(), IMGManager.getIMG(Images.flagBigMask).getHeight());
    }

    public static void drawAtWar(SpriteBatch oSB, int posX, int posY, int civID) {
        atWar.get((civID + RAND) % atWarSize).draw(oSB, posX, posY, IMGManager.getIMG(Images.flagBigMask).getWidth(), IMGManager.getIMG(Images.flagBigMask).getHeight());
    }

    public static void drawAtWarLosing(SpriteBatch oSB, int posX, int posY, int civID) {
        atWarLosing.get((civID + RAND) % atWarLosingSize).draw(oSB, posX, posY, IMGManager.getIMG(Images.flagBigMask).getWidth(), IMGManager.getIMG(Images.flagBigMask).getHeight());
    }

    public static void drawLordOfPlayer(SpriteBatch oSB, int posX, int posY, int civID) {
        lordOfPlayer.get((civID + RAND) % lordOfPlayerSize).draw(oSB, posX, posY, IMGManager.getIMG(Images.flagBigMask).getWidth(), IMGManager.getIMG(Images.flagBigMask).getHeight());
    }

    public static void drawVassalOfPlayer(SpriteBatch oSB, int posX, int posY, int civID) {
        vassalOfPlayer.get((civID + RAND) % vassalOfPlayerSize).draw(oSB, posX, posY, IMGManager.getIMG(Images.flagBigMask).getWidth(), IMGManager.getIMG(Images.flagBigMask).getHeight());
    }

    public static void drawDead(SpriteBatch oSB, int posX, int posY, int civID) {
        dead.get((civID + RAND) % deadSize).draw(oSB, posX, posY, IMGManager.getIMG(Images.flagBigMask).getWidth(), IMGManager.getIMG(Images.flagBigMask).getHeight());
    }

    public static void updateRand() {
        RAND = CFG.oR.nextInt(5);
    }

    public static void updateRandTimer() {
        if (RAND_TIME < System.currentTimeMillis() - 10000L) {
            RAND = CFG.oR.nextInt(5);
            RAND_TIME = System.currentTimeMillis();
        }
    }

    public static void loadImages() {
    }
}
