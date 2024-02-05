import java.util.HashMap;
import java.util.Map;

class countMap {
    Map<String, Integer> countMap;
}

class avgStrengthMap {
    Map<String, Integer> avgStrengthMap;
}

public class MakeRefSSIDs {
    Map<String, countMap> AllPosCountMap;
    Map<String, avgStrengthMap> AllPosAvgStrengthMap;

    public MakeRefSSIDs() {
        AllPosCountMap = new HashMap<>();
        AllPosAvgStrengthMap = new HashMap<>();
    }

    public void addPosCountMap(String roomPosNo, countMap countMap) {
        AllPosCountMap.put(roomPosNo, countMap);
    }

    public void addPosAvgStrengthMap(String roomPosNo, avgStrengthMap avgStrengthMap) {
        AllPosAvgStrengthMap.put(roomPosNo, avgStrengthMap);
    }

    public Map<String, countMap> getAllPosCountMap() {
        return AllPosCountMap;
    }

    public Map<String, avgStrengthMap> getAllPosAvgStrengthMap() {
        return AllPosAvgStrengthMap;
    }

    public countMap getPosCountMap(String roomPosNo) {
        return AllPosCountMap.get(roomPosNo);
    }

    public avgStrengthMap getPosAvgStrengthMap(String roomPosNo) {
        return AllPosAvgStrengthMap.get(roomPosNo);
    }

}
