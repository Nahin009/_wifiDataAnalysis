import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomPosList {
    Map<String, roomPosNo> roomPosList;

    public RoomPosList() {
        roomPosList = new HashMap<>();
    }

    public RoomPosList(Map<String, roomPosNo> roomPosList) {
        this.roomPosList = roomPosList;
    }

    public Map<String, roomPosNo> getRoomPosList() {
        return roomPosList;
    }

    public void setRoomPosList(Map<String, roomPosNo> roomPosList) {
        this.roomPosList = roomPosList;
    }

    public void addRoomPosNo(String roomPosNo, Map<String, ScanList> scanListMap) {
        roomPosList.put(roomPosNo, new roomPosNo(roomPosNo, scanListMap));
    }

    public List<wifiNode> getAverageScanList(String roomPosNo, String deviceModel) {
        Map<String, ScanList> scanListMap = roomPosList.get(roomPosNo).getScanListMap();
        for (String key : scanListMap.keySet()) {
           if(scanListMap.get(key).getDeviceModel().equals(deviceModel)) {
               return scanListMap.get(key).getAverageScanList();
           }
        }
        return null;
    }

    public List<wifiNode> getSelectedScanList(String roomPosNo, String deviceModel) {
        Map<String, ScanList> scanListMap = roomPosList.get(roomPosNo).getScanListMap();
        for (String key : scanListMap.keySet()) {
            if(scanListMap.get(key).getDeviceModel().equals(deviceModel)) {
                return scanListMap.get(key).getSelectedScanList();
            }
        }
        System.out.println("No such device model");
        return null;
    }

    public Map<String, List< Pair<String, Integer>>> getCommonAverageScanList(List<String > roomPosList, String deviceModel) {
        // return a common Map<SSID, List<Pair<roomPosNo, Strength>>> format
        Map<String, List< Pair<String, Integer>>> commonAverageScanList = new HashMap<>();
        for (String roomPosNo : roomPosList) {
            List<wifiNode> averageScanList = getAverageScanList(roomPosNo, deviceModel);
            for (wifiNode wifiNode : averageScanList) {
                if (commonAverageScanList.containsKey(wifiNode.getSSID())) {
                    commonAverageScanList.get(wifiNode.getSSID()).add(new Pair<>(roomPosNo, wifiNode.getStrength()));
                } else {
                    List<Pair<String, Integer>> list = new ArrayList<>();
                    list.add(new Pair<>(roomPosNo, wifiNode.getStrength()));
                    commonAverageScanList.put(wifiNode.getSSID(), list);
                }
            }
        }
        return commonAverageScanList;
    }

    public Map<String, List< Pair<String, Integer>>> getCommonSelectedScanList(List<String > roomPosList, String deviceModel) {
        // return a common Map<SSID, List<Pair<roomPosNo, Strength>>> format
        Map<String, List< Pair<String, Integer>>> commonSelectedScanList = new HashMap<>();
        for (String roomPosNo : roomPosList) {
            List<wifiNode> selectedScanList = getSelectedScanList(roomPosNo, deviceModel);
            for (wifiNode wifiNode : selectedScanList) {
                if (commonSelectedScanList.containsKey(wifiNode.getSSID())) {
                    commonSelectedScanList.get(wifiNode.getSSID()).add(new Pair<>(roomPosNo, wifiNode.getStrength()));
                } else {
                    List<Pair<String, Integer>> list = new ArrayList<>();
                    list.add(new Pair<>(roomPosNo, wifiNode.getStrength()));
                    commonSelectedScanList.put(wifiNode.getSSID(), list);
                }
            }
        }
        return commonSelectedScanList;
    }


    public Map<String, List<wifiNode>> getTabularData(List<String > roomPosList, List<String > ssidList, String deviceModel) {
        Map<String, List<wifiNode>> tabularData = new HashMap<>();
        for (String roomPosNo : roomPosList) {
            List<wifiNode> averageScanList = getSelectedScanList(roomPosNo, deviceModel);
            for (wifiNode wifiNode : averageScanList) {
                if (ssidList.contains(wifiNode.getSSID())) {
                    if (tabularData.containsKey(wifiNode.getSSID())) {
                        tabularData.get(wifiNode.getSSID()).add(wifiNode);
                    } else {
                        List<wifiNode> list = new ArrayList<>();
                        list.add(wifiNode);
                        tabularData.put(wifiNode.getSSID(), list);
                    }
                }
                else {
                    System.out.println("No such SSID" + wifiNode.getSSID());
                }
            }
        }
        return tabularData;
    }
}
