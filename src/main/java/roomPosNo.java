import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class roomPosNo {
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String roomPosNo;
    private Map<String, ScanList> scanListMap;

    public roomPosNo() {
        scanListMap = new HashMap<>();
    }

    public roomPosNo(String roomPosNo, Map<String, ScanList> scanListMap) {
        this.roomPosNo = roomPosNo;
        this.scanListMap = scanListMap;
    }

    public String getRoomPosNo() {
        return roomPosNo;
    }

    public Map<String, ScanList> getScanListMap() {
        return scanListMap;
    }

    public void setRoomPosNo(String roomPosNo) {
        this.roomPosNo = roomPosNo;
    }

    public void setScanListMap(Map<String, ScanList> scanListMap) {
        this.scanListMap = scanListMap;
    }

    public void addScanList(String datetime, ScanList scanList) {
        scanListMap.put(datetime, scanList);
    }

}
