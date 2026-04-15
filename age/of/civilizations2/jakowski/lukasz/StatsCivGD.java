package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class StatsCivGD
implements Serializable {
    private static final long serialVersionUID = 0L;
    public String sTag = "";
    private int iGamesWon = 0;
    private int iConqueredProvinces = 0;
    private int iTurns = 0;
    private int iRecruitedArmy = 0;
    private int iLargestArmy = 0;
    private int iLargestPopulation = 0;
    private int iBiggestEconomy = 0;
    private int iBuiltForts = 0;
    private int iBuiltTowers = 0;
    private int iBuiltPorts = 0;
    private int iBuiltLibraries = 0;
    private int iBuiltSupplies = 0;
    private int iBuiltArmories = 0;
    private int iBuiltFarms = 0;
    private int iBuiltWorkshops = 0;

    public StatsCivGD() {
    }

    public StatsCivGD(String nTag) {
        this.sTag = nTag;
    }

    public final int getConqueredProvs() {
        return this.iConqueredProvinces;
    }

    public final void setConqueredProvinces(int iConqueredProvinces) {
        this.iConqueredProvinces = iConqueredProvinces;
    }

    public final int getTurns() {
        return this.iTurns;
    }

    public final void setTurns(int iTurns) {
        this.iTurns = iTurns;
    }

    public final int getRecruitedArmy() {
        return this.iRecruitedArmy;
    }

    public final void setRecruitedArmy(int iRecruitedArmy) {
        this.iRecruitedArmy = iRecruitedArmy;
    }

    public final int getGamesWon() {
        return this.iGamesWon;
    }

    public final void setGamesWon(int iGamesWon) {
        this.iGamesWon = iGamesWon;
    }

    public final int getBiggestEconomy() {
        return this.iBiggestEconomy;
    }

    public final void setBiggestEconomy(int iBiggestEconomy) {
        this.iBiggestEconomy = iBiggestEconomy;
    }

    public final int getLargestPopulation() {
        return this.iLargestPopulation;
    }

    public final void setLargestPopulation(int iLargestPopulation) {
        this.iLargestPopulation = iLargestPopulation;
    }

    public final int getLargestArmy() {
        return this.iLargestArmy;
    }

    public final void setLargestArmy(int iLargestArmy) {
        this.iLargestArmy = iLargestArmy;
    }

    public final int getiBuiltArmories() {
        return this.iBuiltArmories;
    }

    public final void setiBuiltArmories(int iBuiltArmories) {
        this.iBuiltArmories = iBuiltArmories;
    }

    public final int getiBuiltFarms() {
        return this.iBuiltFarms;
    }

    public final void setiBuiltFarms(int iBuiltFarms) {
        this.iBuiltFarms = iBuiltFarms;
    }

    public final int getiBuiltWorkshops() {
        return this.iBuiltWorkshops;
    }

    public final void setiBuiltWorkshops(int iBuiltWorkshops) {
        this.iBuiltWorkshops = iBuiltWorkshops;
    }

    public final int getiBuiltSupplies() {
        return this.iBuiltSupplies;
    }

    public final void setiBuiltSupplies(int iBuiltSupplies) {
        this.iBuiltSupplies = iBuiltSupplies;
    }

    public final int getiBuiltPorts() {
        return this.iBuiltPorts;
    }

    public final void setiBuiltPorts(int iBuiltPorts) {
        this.iBuiltPorts = iBuiltPorts;
    }

    public final int getiBuiltTowers() {
        return this.iBuiltTowers;
    }

    public final void setiBuiltTowers(int iBuiltTowers) {
        this.iBuiltTowers = iBuiltTowers;
    }

    public final int getiBuiltForts() {
        return this.iBuiltForts;
    }

    public final void setiBuiltForts(int iBuiltForts) {
        this.iBuiltForts = iBuiltForts;
    }

    public final int getiBuiltLibraries() {
        return this.iBuiltLibraries;
    }

    public final void setiBuiltLibraries(int iBuiltLibraries) {
        this.iBuiltLibraries = iBuiltLibraries;
    }
}
