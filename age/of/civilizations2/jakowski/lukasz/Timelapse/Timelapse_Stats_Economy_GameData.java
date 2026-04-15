package age.of.civilizations2.jakowski.lukasz.Timelapse;

import age.of.civilizations2.jakowski.lukasz.GameValues.GameValues;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Timelapse_Stats_Economy_GameData
implements Serializable {
    private static final long serialVersionUID = 0L;
    public List<List<Integer>> lEconomy = new ArrayList<List<Integer>>();

    public final void addData(List<Integer> tData) {
        this.lEconomy.add(tData);
        if (GameValues.gvTimelapse.GRAPH_DATA_LIMIT_ECONOMY > 0 && this.lEconomy.size() > GameValues.gvTimelapse.GRAPH_DATA_LIMIT_ECONOMY) {
            this.lEconomy.remove(0);
        }
    }
}
