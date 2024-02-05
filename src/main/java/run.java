import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class run {
    public static void main(String[] args) {
        String JsonFilePath = "src/main/203_2ndDay.json";

        // Read JSON file203 and convert to Java object
        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

        RoomPosList AllRoomWithPosList = new RoomPosList();

        try {
            TypeReference<Map<String, Map<String, ScanList>>> typeReference = new TypeReference<>() {};
            Map<String, Map<String, ScanList>> roomPosList = mapper.readValue(new File(JsonFilePath), typeReference);

//            System.out.println("total roomPosNo: " + roomPosList.size());

            for (Map.Entry<String, Map<String, ScanList>> roomPosNo : roomPosList.entrySet()) {
//                AllRoomWithPosList.addRoomPosNo(roomPosNo.getKey(), roomPosNo.getValue());
//                System.out.println("RoomPosNo: " + roomPosNo.getKey());
//                System.out.println("    No of ScanList: " + roomPosNo.getValue().size());
//                for (Map.Entry<String, ScanList> scanList : roomPosNo.getValue().entrySet()) {
//                    System.out.println("      Details of the scanSet: " + scanList.getKey());
//                    System.out.println("          DateTime: " + scanList.getValue().getDatetime());
//                    System.out.println("          DeviceModel: " + scanList.getValue().getDeviceModel());
//                    System.out.println("          No of scanList: " + scanList.getValue().getScanList().size());
//                    for (List<wifiNode> scan : scanList.getValue().getScanList()) {
//                        System.out.println("              No of wifiNode: " + scan.size());
//                    }
//                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

