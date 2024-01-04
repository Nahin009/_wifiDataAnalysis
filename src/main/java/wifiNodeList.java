import java.util.List;
import java.util.ArrayList;

public class wifiNodeList {
    private List<wifiNode> wifiNodeList;

    public wifiNodeList() {
        wifiNodeList = new ArrayList<>();
    }

    public wifiNodeList(List<wifiNode> wifiNodeList) {
        this.wifiNodeList = wifiNodeList;
    }

    public List<wifiNode> getWifiNodeList() {
        return wifiNodeList;
    }

    public void setWifiNodeList(List<wifiNode> wifiNodeList) {
        this.wifiNodeList = wifiNodeList;
    }
}
