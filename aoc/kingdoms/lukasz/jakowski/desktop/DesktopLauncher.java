package aoc.kingdoms.lukasz.jakowski.desktop;

import age.of.civilizations2.jakowski.lukasz.CFG;
import aoc.kingdoms.lukasz.jakowski.AA_Game;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DesktopLauncher {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Age of History 2: Definitive Edition");
        try {
            config.setWindowIcon(Files.FileType.Internal, "UI/ic_16x16.png");
            config.setWindowIcon(Files.FileType.Internal, "UI/ic_32x32.png");
            config.setWindowIcon(Files.FileType.Internal, "UI/ic_128x128.png");
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
        config.setResizable(false);
        int tWidth = -1;
        int tHeight = -1;
        boolean tFullscreen = true;
        int tSamples = -1;
        boolean tVSync = false;
        FileReader fr = null;
        String sLine = "";
        System.out.println(System.getProperty("file.encoding"));
        try {
            fr = new FileReader("settings/config.txt");
            BufferedReader bfr = new BufferedReader(fr);
            while ((sLine = bfr.readLine()) != null) {
                String[] tempR = sLine.replace(";", "").split("=");
                try {
                    if (tempR[0].equals("FULLSCREEN")) {
                        tFullscreen = Boolean.parseBoolean(tempR[1]);
                        continue;
                    }
                    if (tempR[0].equals("WIDTH")) {
                        tWidth = Integer.parseInt(tempR[1]);
                        continue;
                    }
                    if (tempR[0].equals("HEIGHT")) {
                        tHeight = Integer.parseInt(tempR[1]);
                        continue;
                    }
                    if (tempR[0].equals("ANTIALIASING")) {
                        tSamples = Integer.parseInt(tempR[1]);
                        continue;
                    }
                    if (!tempR[0].equals("VSYNC")) continue;
                    tVSync = Boolean.parseBoolean(tempR[1]);
                }
                catch (IndexOutOfBoundsException ex) {
                    tWidth = -1;
                    tHeight = -1;
                    tFullscreen = true;
                    tSamples = -1;
                    tVSync = false;
                    break;
                }
                catch (IllegalArgumentException ex) {
                    tWidth = -1;
                    tHeight = -1;
                    tFullscreen = true;
                    tSamples = -1;
                    tVSync = false;
                    break;
                }
            }
            fr.close();
        }
        catch (IOException ex) {
            tWidth = -1;
            tHeight = -1;
            tFullscreen = true;
            tSamples = -1;
            tVSync = false;
        }
        if (tWidth < 0 && tHeight < 0) {
            config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
        } else {
            config.setWindowedMode(tWidth, tHeight);
            if (tFullscreen) {
                config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
            }
        }
        config.useVsync(tVSync);
        config.setAudioConfig(32, 512, 18);
        config.setForegroundFPS(60);
        new Lwjgl3Application(new AA_Game(), config);
    }
}
