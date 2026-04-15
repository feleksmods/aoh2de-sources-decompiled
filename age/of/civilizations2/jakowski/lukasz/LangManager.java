package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Files.FileManager;
import age.of.civilizations2.jakowski.lukasz.Z_Other.ST.sUM;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.I18NBundle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;

public class LangManager {
    public static boolean translationsKeysMode = false;
    public int iLNOT = 0;
    private FileHandle fileHandleCivs;
    private Locale localeCivs;
    private I18NBundle bundleCivs;
    private FileHandle fileHandleLoading;
    private Locale localeLoading;
    private I18NBundle bundleLoading;
    private FileHandle fileHandle;
    private Locale locale;
    private I18NBundle bundle;
    private FileHandle fileHandleFormable;
    private Locale localeFormable;
    private I18NBundle bundleFormable;
    private KeyOutput keyOutput;
    public List<I18NBundle> modsBundles = new ArrayList<I18NBundle>();
    public int modsBundlesSize = 0;

    public String get(String key) {
        return this.keyOutput.get(key);
    }

    public String get(String key, int nValue) {
        return this.keyOutput.get(key, nValue);
    }

    public String get(String key, String nValue) {
        return this.keyOutput.get(key, nValue);
    }

    public String get(String key, String nValue, String nValue2) {
        return this.keyOutput.get(key, nValue, nValue2);
    }

    public String getCiv(String key) {
        try {
            if (key != null) {
                int i;
                for (i = 0; i < this.modsBundlesSize; ++i) {
                    try {
                        return this.modsBundles.get(i).get(key);
                    }
                    catch (Exception ex2) {
                        continue;
                    }
                }
                for (i = 0; i < this.modsBundlesSize; ++i) {
                    try {
                        if (key.indexOf(95) <= 0) continue;
                        try {
                            return this.modsBundles.get(i).get(key);
                        }
                        catch (Exception ex2) {
                            try {
                                return this.modsBundles.get(i).get(key.substring(0, key.indexOf(95)));
                            }
                            catch (Exception ex2) {
                                continue;
                            }
                        }
                    }
                    catch (Exception exr) {
                        // empty catch block
                    }
                }
                if (key.indexOf(95) > 0) {
                    try {
                        return this.bundleCivs.get(key);
                    }
                    catch (Exception i22) {
                        try {
                            return this.bundleCivs.get(key.substring(0, key.indexOf(95)));
                        }
                        catch (Exception i22) {
                        }
                    }
                } else {
                    return this.bundleCivs.get(key);
                }
            }
            return this.bundleCivs.get(key);
        }
        catch (MissingResourceException ex) {
            if (key.indexOf(95) > 0) {
                try {
                    return this.bundleCivs.get(key.substring(0, key.indexOf(95)));
                }
                catch (MissingResourceException exr) {
                    // empty catch block
                }
            }
            try {
                if (CFG.isAndroid()) {
                    try {
                        FileHandle file = Gdx.files.local("game/civilizations_editor/" + CFG.ideologiesMgr.getRealTag(key) + "/" + CFG.ideologiesMgr.getRealTag(key) + "_NM");
                        return file.readString();
                    }
                    catch (GdxRuntimeException er) {
                        FileHandle file = FileManager.loadFile("game/civilizations_editor/" + CFG.ideologiesMgr.getRealTag(key) + "/" + CFG.ideologiesMgr.getRealTag(key) + "_NM");
                        return file.readString();
                    }
                }
                FileHandle file = FileManager.loadFile("game/civilizations_editor/" + CFG.ideologiesMgr.getRealTag(key) + "/" + CFG.ideologiesMgr.getRealTag(key) + "_NM");
                return file.readString();
            }
            catch (GdxRuntimeException gdxRuntimeException) {
                return key;
            }
        }
    }

    public String getForm(String key) {
        try {
            if (key != null) {
                int i;
                for (i = 0; i < this.modsBundlesSize; ++i) {
                    try {
                        return this.modsBundles.get(i).get(key);
                    }
                    catch (Exception ex2) {
                        continue;
                    }
                }
                for (i = 0; i < this.modsBundlesSize; ++i) {
                    try {
                        if (key.indexOf(95) <= 0) continue;
                        try {
                            return this.modsBundles.get(i).get(key);
                        }
                        catch (Exception ex2) {
                            try {
                                return this.modsBundles.get(i).get(key.substring(0, key.indexOf(95)));
                            }
                            catch (Exception ex2) {
                                continue;
                            }
                        }
                    }
                    catch (Exception exr) {
                        // empty catch block
                    }
                }
                if (key.indexOf(95) > 0) {
                    try {
                        return this.bundleFormable.get(key);
                    }
                    catch (Exception i22) {
                        try {
                            return this.bundleFormable.get(key.substring(0, key.indexOf(95)));
                        }
                        catch (Exception i22) {
                        }
                    }
                } else {
                    return this.bundleFormable.get(key);
                }
            }
            return this.bundleFormable.get(key);
        }
        catch (MissingResourceException ex) {
            if (key.indexOf(95) > 0) {
                try {
                    return this.bundleFormable.get(key.substring(0, key.indexOf(95)));
                }
                catch (MissingResourceException missingResourceException) {
                    // empty catch block
                }
            }
            return key;
        }
    }

    public String getLOA(String key) {
        try {
            return this.bundleLoading.get(key);
        }
        catch (MissingResourceException ex) {
            return "";
        }
        catch (NullPointerException ex) {
            CFG.loaTM = 0L;
            return "";
        }
    }

    public final void updateKeyOutput() {
        this.keyOutput = translationsKeysMode ? new KeyOutput(){

            @Override
            public String get(String key) {
                return "[" + key + "]";
            }

            @Override
            public String get(String key, int iValue) {
                return "[" + key + "]";
            }

            @Override
            public String get(String key, String sValue) {
                return "[" + key + "]";
            }

            @Override
            public String get(String key, String sValue, String sValue2) {
                return "[" + key + "]";
            }
        } : new KeyOutput(){

            @Override
            public String get(String key) {
                try {
                    for (int i = 0; i < LangManager.this.modsBundlesSize; ++i) {
                        try {
                            return LangManager.this.modsBundles.get(i).get(key);
                        }
                        catch (Exception ex) {
                            continue;
                        }
                    }
                    return LangManager.this.bundle.get(key);
                }
                catch (Exception ex) {
                    return key;
                }
            }

            @Override
            public String get(String key, int iValue) {
                try {
                    for (int i = 0; i < LangManager.this.modsBundlesSize; ++i) {
                        try {
                            return LangManager.this.modsBundles.get(i).format(key, iValue);
                        }
                        catch (Exception ex) {
                            continue;
                        }
                    }
                    return LangManager.this.bundle.format(key, iValue);
                }
                catch (Exception ex) {
                    return key;
                }
            }

            @Override
            public String get(String key, String sValue) {
                try {
                    for (int i = 0; i < LangManager.this.modsBundlesSize; ++i) {
                        try {
                            return LangManager.this.modsBundles.get(i).format(key, sValue);
                        }
                        catch (Exception ex) {
                            continue;
                        }
                    }
                    return LangManager.this.bundle.format(key, sValue);
                }
                catch (Exception ex) {
                    return key;
                }
            }

            @Override
            public String get(String key, String sValue, String sValue2) {
                try {
                    for (int i = 0; i < LangManager.this.modsBundlesSize; ++i) {
                        try {
                            return LangManager.this.modsBundles.get(i).format(key, sValue, sValue2);
                        }
                        catch (Exception ex) {
                            continue;
                        }
                    }
                    return LangManager.this.bundle.format(key, sValue, sValue2);
                }
                catch (Exception ex) {
                    return key;
                }
            }
        };
    }

    public LangManager(String nTag) {
        this.fileHandle = FileManager.loadFile("game/languages/Bundle");
        this.locale = new Locale(nTag);
        this.bundle = I18NBundle.createBundle(this.fileHandle, this.locale);
        this.initCivsBundle(nTag);
        this.initLoadingBundle(nTag);
        this.loadModsLanguages(nTag);
        this.updateKeyOutput();
    }

    public final void initCivsBundle(String nTag) {
        this.fileHandleCivs = FileManager.loadFile("game/languages/civilizations/Bundle");
        this.localeCivs = new Locale(nTag);
        this.bundleCivs = I18NBundle.createBundle(this.fileHandleCivs, this.localeCivs);
        this.fileHandleFormable = FileManager.loadFile("game/languages/formable/Bundle");
        this.localeFormable = new Locale(nTag);
        this.bundleFormable = I18NBundle.createBundle(this.fileHandleFormable, this.localeFormable);
    }

    public final void initLoadingBundle(String nTag) {
        this.fileHandleLoading = FileManager.loadFile("game/languages/loading/Bundle");
        this.localeLoading = new Locale(nTag);
        this.bundleLoading = I18NBundle.createBundle(this.fileHandleLoading, this.localeLoading);
        try {
            this.iLNOT = Integer.parseInt(this.getLOA("NumOfTexts"));
        }
        catch (IllegalArgumentException ex) {
            this.iLNOT = 0;
        }
    }

    public final void dispose() {
        this.fileHandle = null;
        this.locale = null;
        this.bundle = null;
        this.fileHandleCivs = null;
        this.localeCivs = null;
        this.bundleCivs = null;
        this.fileHandleLoading = null;
        this.localeLoading = null;
        this.bundleLoading = null;
        this.fileHandleFormable = null;
        this.localeFormable = null;
        this.bundleFormable = null;
    }

    public void loadModsLanguages(String nTag) {
        try {
            Locale localeCivs;
            FileHandle fileHandleCivs;
            int i;
            this.modsBundles.clear();
            for (i = 0; i < sUM.sUFS; ++i) {
                if (Gdx.files.external(sUM.sUF.get(i) + "languages/Bundle" + "_" + nTag + ".properties").exists()) {
                    fileHandleCivs = Gdx.files.external(sUM.sUF.get(i) + "languages/Bundle");
                    localeCivs = new Locale(nTag);
                    this.modsBundles.add(I18NBundle.createBundle(fileHandleCivs, localeCivs));
                    continue;
                }
                if (Gdx.files.external(sUM.sUF.get(i) + "languages/Bundle" + ".properties").exists()) {
                    fileHandleCivs = Gdx.files.external(sUM.sUF.get(i) + "languages/Bundle");
                    localeCivs = new Locale("");
                    this.modsBundles.add(I18NBundle.createBundle(fileHandleCivs, localeCivs));
                    continue;
                }
                if (Gdx.files.internal(sUM.sUF.get(i) + "languages/Bundle" + "_" + nTag + ".properties").exists()) {
                    fileHandleCivs = Gdx.files.internal(sUM.sUF.get(i) + "languages/Bundle");
                    localeCivs = new Locale(nTag);
                    this.modsBundles.add(I18NBundle.createBundle(fileHandleCivs, localeCivs));
                    continue;
                }
                if (!Gdx.files.internal(sUM.sUF.get(i) + "languages/Bundle" + ".properties").exists()) continue;
                fileHandleCivs = Gdx.files.internal(sUM.sUF.get(i) + "languages/Bundle");
                localeCivs = new Locale("");
                this.modsBundles.add(I18NBundle.createBundle(fileHandleCivs, localeCivs));
            }
            for (i = 0; i < sUM.sUIIS; ++i) {
                if (Gdx.files.absolute(sUM.sUII.get(i).getFolder() + "/" + "languages/Bundle" + "_" + nTag + ".properties").exists()) {
                    fileHandleCivs = Gdx.files.absolute(sUM.sUII.get(i).getFolder() + "/" + "languages/Bundle");
                    localeCivs = new Locale(nTag);
                    this.modsBundles.add(I18NBundle.createBundle(fileHandleCivs, localeCivs));
                    continue;
                }
                if (!Gdx.files.absolute(sUM.sUII.get(i).getFolder() + "/" + "languages/Bundle" + ".properties").exists()) continue;
                fileHandleCivs = Gdx.files.absolute(sUM.sUII.get(i).getFolder() + "/" + "languages/Bundle");
                localeCivs = new Locale("");
                this.modsBundles.add(I18NBundle.createBundle(fileHandleCivs, localeCivs));
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
        this.modsBundlesSize = this.modsBundles.size();
    }

    static interface KeyOutput {
        public String get(String var1);

        public String get(String var1, int var2);

        public String get(String var1, String var2);

        public String get(String var1, String var2, String var3);
    }
}
