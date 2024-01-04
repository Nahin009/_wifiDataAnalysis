import com.fasterxml.jackson.annotation.JsonProperty;

public class wifiNode {
    @JsonProperty("SSID")
    private String SSID;
    @JsonProperty("Strength")
    private int strength;

    public wifiNode() {

    }

    public wifiNode(String SSID, int strength) {
        this.SSID = SSID;
        this.strength = strength;
    }

    public String getSSID() {
        return SSID;
    }

    public int getStrength() {
        return strength;
    }

    public void setSSID(String SSID) {
        this.SSID = SSID;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
