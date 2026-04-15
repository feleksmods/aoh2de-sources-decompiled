package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Province_Population_Nationalities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Province_Population
implements Serializable {
    private static final long serialVersionUID = 0L;
    private int iPopulation = 0;
    private List<Province_Population_Nationalities> lNationalities = new ArrayList<Province_Population_Nationalities>();
    private int iNationalitiesSize = 0;

    public final void updatePopulationOfProvince() {
        for (int i = 0; i < this.iNationalitiesSize; ++i) {
            this.iPopulation += this.lNationalities.get(i).getPopulation();
        }
    }

    public final int getPopulationID(int nID) {
        return this.lNationalities.get(nID).getPopulation();
    }

    public final int getPopulationOfCivID(int nCivID) {
        for (int i = 0; i < this.iNationalitiesSize; ++i) {
            if (this.lNationalities.get(i).getCivID() != nCivID) continue;
            return this.lNationalities.get(i).getPopulation();
        }
        return 0;
    }

    public final boolean setPopulationOfCivID(int nCivID, int nPopulation) {
        for (int i = 0; i < this.iNationalitiesSize; ++i) {
            if (this.lNationalities.get(i).getCivID() != nCivID) continue;
            if (nPopulation <= 0) {
                if (this.lNationalities.size() > 1) {
                    this.iPopulation -= this.lNationalities.get(i).getPopulation();
                    this.lNationalities.remove(i);
                    this.iNationalitiesSize = this.lNationalities.size();
                    return true;
                }
                this.lNationalities.get(i).setPopulaton(10);
                this.iPopulation = 10;
            } else {
                this.iPopulation -= this.lNationalities.get(i).getPopulation();
                this.iPopulation += nPopulation;
                this.lNationalities.get(i).setPopulaton(nPopulation);
            }
            return false;
        }
        if (nPopulation > 0) {
            this.lNationalities.add(new Province_Population_Nationalities(nCivID, nPopulation));
            this.iPopulation += nPopulation;
            this.iNationalitiesSize = this.lNationalities.size();
        }
        return false;
    }

    public final void clearData() {
        this.iPopulation = 0;
        this.lNationalities.clear();
        this.iNationalitiesSize = 0;
    }

    public final int getPops() {
        return this.iPopulation;
    }

    public final int getNatsSize() {
        return this.iNationalitiesSize;
    }

    public final int getCivID(int i) {
        return this.lNationalities.get(i).getCivID();
    }
}
