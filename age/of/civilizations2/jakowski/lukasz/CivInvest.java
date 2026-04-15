package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class CivInvest
implements Serializable {
    private static final long serialVersionUID = 0L;
    public int provinceID;
    public int turnsLeft;
    public int iEconomyLeft;
    public int iEconomyPerTurn;

    public CivInvest(int provinceID, int turnsLeft, int iEconomyLeft, int iEconomyPerTurn) {
        this.provinceID = provinceID;
        this.turnsLeft = turnsLeft;
        this.iEconomyLeft = iEconomyLeft;
        this.iEconomyPerTurn = iEconomyPerTurn;
    }
}
