import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Pair<A, B> {
    private A first;
    private B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public void setFirst(A first) {
        this.first = first;
    }

    public void setSecond(B second) {
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }
}

public class ScanList {
    private static final Integer THRESHHOLD = 5;
    private String roomPosNo;
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("DateTime")
    private String DateTime;
    @JsonProperty("DeviceModel")
    private String DeviceModel;
    @JsonProperty("ScanList")
    private List<List<wifiNode>> scanList;

    Map<String, Pair<Integer, Integer>> countWithAvgMap;

    List<String> SSIDList; //All Captured SSID
    
    Map<String, Integer> countMap; //All Captured SSID vs their count out of 18 (As more than one arrived SSID in a single scan is removed)
    
    Map<String, List<Integer>> sortedStrengthMap; //All Captured SSID vs their strength in ascending order
    
    Map<String, Integer> avgStrengthMapAfterRemovingOutliers; //All Captured SSID vs their average strength after removing outliers
    
    Map<String, Integer> standardDeviationMap;

    List<wifiNode> averageScanList;
    List<wifiNode> selectedScanList;

    List<String> selectedSSID;

    public ScanList() {
        scanList = new ArrayList<>();
        averageScanList = new ArrayList<>();
        selectedScanList = new ArrayList<>();
        selectedSSID = new ArrayList<>();
        selectedSSID.add("DataLab@BUET");
        selectedSSID.add("Hall of Fame");
//        selectedSSID.add("CSE OFFICE");
        selectedSSID.add("dlink");
        selectedSSID.add("CSE-204");
        selectedSSID.add("CSE-205");
        selectedSSID.add("CSE-206");
        selectedSSID.add("CSE-202");
        selectedSSID.add("CSE-104");
        selectedSSID.add("CSE-303");
        selectedSSID.add("CSE-304");

        selectedSSID.add("Redmi Note 8 Pro");
        selectedSSID.add("Galaxy M124213");
        countWithAvgMap = new HashMap<>();
    }

    public ScanList(String Datetime, String DeviceModel, List<List<wifiNode>> scanList) {
        this.DateTime = Datetime;
        this.DeviceModel = DeviceModel;
        this.scanList = scanList;
    }


    public String getDatetime() {
        return DateTime;
    }

    public String getDeviceModel() {
        return DeviceModel;
    }

    public List<List<wifiNode>> getScanList() {
        return scanList;
    }

    public String getRoomPosNo() {
        return roomPosNo;
    }

    public void setRoomPosNo(String roomPosNo) {
        this.roomPosNo = roomPosNo;
    }

    public void setDatetime(String Datetime) {
        this.DateTime = Datetime;
    }

    public void setDeviceModel(String DeviceModel) {
        this.DeviceModel = DeviceModel;
    }

    public void setScanList(List<List<wifiNode>> scanList) {
        this.scanList = scanList;
    }

    public void addScanList(List<wifiNode> scanList) {
        this.scanList.add(scanList);
    }

    public void addcountWithAvgMap() {
        for(int i = 0; i<scanList.size(); i++){
            int size = scanList.get(i).size();
            for (int j = 0; j<size; j++){
                if(scanList.get(i).get(j).getSSID().equals("")){
                    scanList.get(i).remove(j);
                    j--;
                    size--;
                    continue;
                }
                if(countWithAvgMap.containsKey(scanList.get(i).get(j).getSSID())){
                    Pair<Integer, Integer> pair = countWithAvgMap.get(scanList.get(i).get(j).getSSID());
                    pair.setFirst(pair.getFirst() + scanList.get(i).get(j).getStrength());
                    pair.setSecond(pair.getSecond() + 1);
                    countWithAvgMap.put(scanList.get(i).get(j).getSSID(), pair);
                }
                else{
                    Pair<Integer, Integer> pair = new Pair<>(scanList.get(i).get(j).getStrength(), 1);
                    countWithAvgMap.put(scanList.get(i).get(j).getSSID(), pair);
                }
            }
        }
    }

    public List<wifiNode> getAverageScanList() {

        addcountWithAvgMap();

        for(Map.Entry<String, Pair<Integer, Integer>> entry : countWithAvgMap.entrySet()){
            Pair<Integer, Integer> pair = entry.getValue();
            if (pair.getSecond() >= THRESHHOLD)
                averageScanList.add(new wifiNode(entry.getKey(), pair.getFirst()/pair.getSecond()));
        }

        return averageScanList;
    }

    public List<wifiNode> getSelectedScanList() {

        addcountWithAvgMap();

        for(Map.Entry<String, Pair<Integer, Integer>> entry : countWithAvgMap.entrySet()){
            if(selectedSSID.contains(entry.getKey())){
                Pair<Integer, Integer> pair = entry.getValue();
                selectedScanList.add(new wifiNode(entry.getKey(), pair.getFirst()/pair.getSecond()));
            }
        }

        return selectedScanList;
    }




    public void RemoveUnknownSSIDs() {
        for (List<wifiNode> wifiNodes : scanList) {
            int size = wifiNodes.size();
            for (int j = 0; j < size; j++) {
                if (wifiNodes.get(j).getSSID().equals("")) {
                    wifiNodes.remove(j);
                    j--;
                    size--;
                }
            }
        }
    }

    public void removeDuplicateSSIDs() {
        //if a SSID is found more than once anywhere in a scanList, remove all the SSID with that name from the scanList
        List<String> duplicateSSID = new ArrayList<>();
        for (List<wifiNode> wifiNodes : scanList) {
            int size = wifiNodes.size();
            for (int j = 0; j < size; j++) {
                for (int k = j + 1; k < size; k++) {
                    if (wifiNodes.get(j).getSSID().equals(wifiNodes.get(k).getSSID())) {
                        duplicateSSID.add(wifiNodes.get(j).getSSID());
                    }
                }
            }
        }

        for (List<wifiNode> wifiNodes : scanList) {
            int size = wifiNodes.size();
            for (int j = 0; j < size; j++) {
                if (duplicateSSID.contains(wifiNodes.get(j).getSSID())) {
                    wifiNodes.remove(j);
                    j--;
                    size--;
                }
            }
        }
    }


    public List<String> getSSIDList() {
        RemoveUnknownSSIDs();
        removeDuplicateSSIDs();

        SSIDList = new ArrayList<>();
        for (List<wifiNode> wifiNodes : scanList) {
            for (wifiNode wifiNode : wifiNodes) {
                if (!SSIDList.contains(wifiNode.getSSID())) {
                    SSIDList.add(wifiNode.getSSID());
                }
            }
        }
        return SSIDList;
    }


    public Map<String, Integer> getCountMap() {
        RemoveUnknownSSIDs();
        removeDuplicateSSIDs();
        
        countMap = new HashMap<>();
        for (List<wifiNode> wifiNodes : scanList) {
            for (wifiNode wifiNode : wifiNodes) {
                if (countMap.containsKey(wifiNode.getSSID())) {
                    countMap.put(wifiNode.getSSID(), countMap.get(wifiNode.getSSID()) + 1);
                } else {
                    countMap.put(wifiNode.getSSID(), 1);
                }
            }
        }
        return countMap;
    }
    
    public Map<String, List<Integer>> getSortedStrengthMap() {
        RemoveUnknownSSIDs();
        removeDuplicateSSIDs();
        
        sortedStrengthMap = new HashMap<>();
        for (List<wifiNode> wifiNodes : scanList) {
            for (wifiNode wifiNode : wifiNodes) {
                if (sortedStrengthMap.containsKey(wifiNode.getSSID())) {
                    sortedStrengthMap.get(wifiNode.getSSID()).add(wifiNode.getStrength());
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(wifiNode.getStrength());
                    sortedStrengthMap.put(wifiNode.getSSID(), list);
                }
            }
        }
        for (Map.Entry<String, List<Integer>> entry : sortedStrengthMap.entrySet()) {
            entry.getValue().sort(Integer::compareTo);
        }
        return sortedStrengthMap;
    }
    
    public Map<String, Integer> getAvgStrengthMapAfterRemovingOutliers() {
        RemoveUnknownSSIDs();
        removeDuplicateSSIDs();
        getSortedStrengthMap();
        
        avgStrengthMapAfterRemovingOutliers = new HashMap<>();

        for (Map.Entry<String, List<Integer>> entry : sortedStrengthMap.entrySet()) {
            int size = entry.getValue().size();
            if(size  <= 5) {
                int sum = 0;
                for (int i = 0; i < size; i++) {
                    sum += entry.getValue().get(i);
                }
                avgStrengthMapAfterRemovingOutliers.put(entry.getKey(), sum / size);
            }
            else if (size > 15) {
                int sum = 0;
                for (int i = 3; i < size - 3; i++) {
                    sum += entry.getValue().get(i);
                }
                avgStrengthMapAfterRemovingOutliers.put(entry.getKey(), sum / (size - 6));
            }
            else if(size > 10) {
                int sum = 0;
                for (int i = 2; i < size - 2; i++) {
                    sum += entry.getValue().get(i);
                }
                avgStrengthMapAfterRemovingOutliers.put(entry.getKey(), sum / (size - 4));
            }
            else {
                int sum = 0;
                for (int i = 1; i < size - 1; i++) {
                    sum += entry.getValue().get(i);
                }
                avgStrengthMapAfterRemovingOutliers.put(entry.getKey(), sum / (size - 2));
            }
        }

        return avgStrengthMapAfterRemovingOutliers;
    }


}
