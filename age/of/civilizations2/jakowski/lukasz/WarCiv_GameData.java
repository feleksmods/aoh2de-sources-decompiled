package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class WarCiv_GameData
implements Serializable {
    private static final long serialVersionUID = 0L;
    private int iCivID = 0;
    private int iCasualties = 0;
    private int iCivilianDeaths = 0;
    private int iEconomicLosses = 0;
    private int iConqueredProvinces = 0;

    public WarCiv_GameData(int nCivID) {
        this.iCivID = nCivID;
    }

    public final int getCivID() {
        return this.iCivID;
    }

    public final void setCivID(int iCivID) {
        this.iCivID = iCivID;
    }

    public final int getCasualties() {
        return this.iCasualties;
    }

    public final void addCasualties(int nCasualties) {
        this.iCasualties += nCasualties;
    }

    public final int getCivilianDeaths() {
        return this.iCivilianDeaths;
    }

    public final void addCivilianDeaths(int nCivilianDeaths) {
        this.iCivilianDeaths += nCivilianDeaths;
    }

    public final int getEconomicLosses() {
        return this.iEconomicLosses;
    }

    public final void addEconomicLosses(int nEconomicLosses) {
        this.iEconomicLosses += nEconomicLosses;
    }

    public final int getConqueredProvinces() {
        return this.iConqueredProvinces;
    }

    public final void setConqueredProvinces(int iConqueredProvinces) {
        this.iConqueredProvinces = iConqueredProvinces;
    }

    public final void addConqueredProvinces() {
        ++this.iConqueredProvinces;
    }
}
