package age.of.civilizations2.jakowski.lukasz.Save;

import age.of.civilizations2.jakowski.lukasz.MapA.Plagues.PlagueProvince_GameData;
import age.of.civilizations2.jakowski.lukasz.Province_Army;
import age.of.civilizations2.jakowski.lukasz.Province_Core;
import age.of.civilizations2.jakowski.lukasz.Province_Population;
import age.of.civilizations2.jakowski.lukasz.Province_SupportRebels;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Province_Save_GD
implements Serializable {
    private static final long serialVersionUID = 0L;
    public int trueOwnerOfProvince = 0;
    public boolean isCapital = false;
    public int iPlaguesDeaths = 0;
    public int iNumOfRecruitedArmyTotal = 0;
    public int iLastPlagueTurnID = 0;
    public boolean isPartOfHolyRomaEmpire = false;
    public PlagueProvince_GameData provincePlague = null;
    public int iNewColonyBonus = 0;
    public List<Province_SupportRebels> lSupportRebels = new ArrayList<Province_SupportRebels>();
    public int iSupportRebelsSize = 0;
    public int startingPopulation = 0;
    public int startingEconomy = 0;
    public CopyOnWriteArrayList<Province_Army> armiesC = new CopyOnWriteArrayList();
    public int civsSize;
    public int totalCasualtiesInProvince = 0;
    public Province_Population pops;
    public int economy;
    public float fDevLevel;
    public float fHappiness = 0.85f;
    public float fRevolutionaryRisk = 0.0f;
    public Province_Core oProvinceCore = null;
    public int isNotSuppliedForYTurns = -1;
    public int defPositionTurns = 0;
    public int iWatchTower;
    public int iFort;
    public int iPort;
    public int iFarm;
    public int iLibrary;
    public int iArmoury;
    public int iWorkshop;
    public int iSupply;
    public int iMarket = 0;
    public byte wasConquered = 0;
    public byte wasAttacked = 0;
    public byte neighProvinceOfCivicWasLost = 0;
    public int wastelandLevel = -1;
    public boolean wonderBuilt = false;

    public final void resetData() {
        this.iLastPlagueTurnID = -19;
        this.iPlaguesDeaths = 0;
        this.provincePlague = null;
        this.iNumOfRecruitedArmyTotal = 0;
        this.iNewColonyBonus = 0;
        this.lSupportRebels = new ArrayList<Province_SupportRebels>();
        this.iSupportRebelsSize = 0;
        this.startingPopulation = 0;
        this.startingEconomy = 0;
        this.totalCasualtiesInProvince = 0;
        this.wonderBuilt = false;
    }
}
