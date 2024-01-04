import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.security.KeyPair;
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

    Map<String, Pair<Integer, Integer>> countMap;

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
        countMap = new HashMap<>();
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

    public void addCountMap() {
        for(int i = 0; i<scanList.size(); i++){
            int size = scanList.get(i).size();
            for (int j = 0; j<size; j++){
                if(scanList.get(i).get(j).getSSID().equals("")){
                    scanList.get(i).remove(j);
                    j--;
                    size--;
                    continue;
                }
                if(countMap.containsKey(scanList.get(i).get(j).getSSID())){
                    Pair<Integer, Integer> pair = countMap.get(scanList.get(i).get(j).getSSID());
                    pair.setFirst(pair.getFirst() + scanList.get(i).get(j).getStrength());
                    pair.setSecond(pair.getSecond() + 1);
                    countMap.put(scanList.get(i).get(j).getSSID(), pair);
                }
                else{
                    Pair<Integer, Integer> pair = new Pair<>(scanList.get(i).get(j).getStrength(), 1);
                    countMap.put(scanList.get(i).get(j).getSSID(), pair);
                }
            }
        }
    }

    public List<wifiNode> getAverageScanList() {

        addCountMap();

        for(Map.Entry<String, Pair<Integer, Integer>> entry : countMap.entrySet()){
            Pair<Integer, Integer> pair = entry.getValue();
            if (pair.getSecond() >= THRESHHOLD)
                averageScanList.add(new wifiNode(entry.getKey(), pair.getFirst()/pair.getSecond()));
        }

        return averageScanList;
    }

    public List<wifiNode> getSelectedScanList() {

        addCountMap();

        for(Map.Entry<String, Pair<Integer, Integer>> entry : countMap.entrySet()){
            if(selectedSSID.contains(entry.getKey())){
                Pair<Integer, Integer> pair = entry.getValue();
                selectedScanList.add(new wifiNode(entry.getKey(), pair.getFirst()/pair.getSecond()));
            }
        }

        return selectedScanList;
    }

}
