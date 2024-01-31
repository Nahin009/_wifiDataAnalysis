import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main2 {
    public static void main(String[] args) {
        String JsonFilePath = "src/main/Dataset_2.json";

        // Read JSON file203 and convert to Java object
        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

        RoomPosList AllRoomWithPosList = new RoomPosList();

        try {
            TypeReference<Map<String, Map<String, ScanList>>> typeReference = new TypeReference<>() {};
            Map<String, Map<String, ScanList>> roomPosList = mapper.readValue(new File(JsonFilePath), typeReference);

//            System.out.println("total roomPosNo: " + roomPosList.size());

            for (Map.Entry<String, Map<String, ScanList>> roomPosNo : roomPosList.entrySet()) {
                AllRoomWithPosList.addRoomPosNo(roomPosNo.getKey(), roomPosNo.getValue());
                System.out.println("RoomPosNo: " + roomPosNo.getKey());
                System.out.println("    No of ScanList: " + roomPosNo.getValue().size());
                for (Map.Entry<String, ScanList> scanList : roomPosNo.getValue().entrySet()) {
                    System.out.println("      Details of the scanSet: " + scanList.getKey());
                    System.out.println("          DateTime: " + scanList.getValue().getDatetime());
                    System.out.println("          DeviceModel: " + scanList.getValue().getDeviceModel());
                    System.out.println("          No of scanList: " + scanList.getValue().getScanList().size());
                    for (List<wifiNode> scan : scanList.getValue().getScanList()) {
                        System.out.println("              No of wifiNode: " + scan.size());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        // Get average scanList for a roomPosNo and deviceModel
//        String roomPosNo = "CSE_203_r1c1_i";
//        String deviceModel = "RMX3363";
////        String deviceModel = "SM-M127G";
//        System.out.println("RoomPosNo: " + roomPosNo + " DeviceModel: " + deviceModel);
////        List<wifiNode> averageScanList = AllRoomWithPosList.getAverageScanList(roomPosNo, deviceModel);
//        List<wifiNode> averageScanList = AllRoomWithPosList.getSelectedScanList(roomPosNo, deviceModel);
//        for (wifiNode node : averageScanList) {
//            System.out.println(node.getSSID() + " " + node.getStrength());
//        }
//
//        // Get common average scanList for a list of roomPosNo and deviceModel
//        List<String> roomPosNoList = List.of("DataLab@BUET", "Hall of Fame",
//        "CSE-204", "CSE-205", "CSE-206", "CSE-202", "CSE-104", "CSE-303", "CSE-304","Galaxy M124213");
////        Map<String, List<Pair<String, Integer>>> commonAverageScanList = AllRoomWithPosList.getCommonAverageScanList(roomPosNoList, deviceModel);
////        Map<String, List<Pair<String, Integer>>> commonAverageScanList = AllRoomWithPosList.getCommonSelectedScanList(roomPosNoList, deviceModel);
////        for (Map.Entry<String, List<Pair<String, Integer>>> entry : commonAverageScanList.entrySet()) {
////            System.out.println(entry.getKey());
////            for (Pair<String, Integer> pair : entry.getValue()) {
////                System.out.println("    " + pair.getFirst() + " " + pair.getSecond());
////            }
////        }
//
    }

}

