package age.of.civilizations2.jakowski.lukasz.Timelapse;

import age.of.civilizations2.jakowski.lukasz.GameValues.GameValues;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Timelapse_Stats_Rank_GameData
implements Serializable {
    private static final long serialVersionUID = 0L;
    public List<List<Integer>> lRank = new ArrayList<List<Integer>>();

    public final void addRank(List<Integer> tData) {
        this.lRank.add(tData);
        if (GameValues.gvTimelapse.GRAPH_DATA_LIMIT_RANK > 0 && this.lRank.size() > GameValues.gvTimelapse.GRAPH_DATA_LIMIT_RANK) {
            this.lRank.remove(0);
        }
    }
}
