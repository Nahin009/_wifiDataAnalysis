import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class file204 {
    public final static List<String> refPointList = List.of("CSE204_1", "CSE204_2", "CSE204_3", "CSE204_4",
            "CSE204_5", "CSE204_6", "CSE204_7", "CSE204_8", "CSE204_9");
    public final static List<String> allPointList = List.of("CSE204_1", "CSE204_2", "CSE204_3", "CSE204_4",
            "CSE204_5", "CSE204_6", "CSE204_7", "CSE204_8", "CSE204_9",
            "CSE204_10", "CSE204_11", "CSE204_12", "CSE204_13",
            "CSE204_14", "CSE204_15", "CSE204_16", "CSE204_17", "CSE204_18");

    public final static List<String> deviceList = List.of("RMX3363", "SM-M127G");
    public final static List<String> deviceModel = List.of("Galaxy M124213", "Redmi Note 8 Pro");

    public final static List<List<String >> commonAPList = List.of(List.of("DataLab@BUET", "Hall of Fame",
            "CSE-204", "CSE-205", "CSE-206", "CSE-202", "CSE-104", "CSE-303", "CSE-304", deviceModel.get(0)),
            List.of("DataLab@BUET", "Hall of Fame",
                    "CSE-204", "CSE-205", "CSE-206", "CSE-202", "CSE-104", "CSE-303", "CSE-304", deviceModel.get(1)));




    public static void main(String[] args) throws IOException {
        String JsonFilePath = "src/main/JsonData.json";

        String filePath = "dataSet/204_ALL_DATA.csv";

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

        // Read JSON file204 and convert to Java object
        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

        RoomPosList AllRoomWithPosList = new RoomPosList();

        try {
            TypeReference<Map<String, Map<String, ScanList>>> typeReference = new TypeReference<>() {};
            Map<String, Map<String, ScanList>> roomPosList = mapper.readValue(new File(JsonFilePath), typeReference);

            for (Map.Entry<String, Map<String, ScanList>> roomPosNo : roomPosList.entrySet()) {
                AllRoomWithPosList.addRoomPosNo(roomPosNo.getKey(), roomPosNo.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // write in the csv file204 as tabular form

        for(int i = 0; i < deviceList.size(); i++) {
            writer.write("Device Name: " + deviceList.get(i) + "\n");

            writer.write("roomPosNo\\commonAPlist");

            // write roomPosNo's
            for (String roomPosNo : allPointList) {
                writer.write("," + roomPosNo);
            }
            writer.write("\n");

            // write roomPosNo's and strength
            Map<String, List<wifiNode>> tabularList = AllRoomWithPosList.getTabularData(allPointList, commonAPList.get(i), deviceList.get(i));
            for (Map.Entry<String, List<wifiNode>> roomPosNo : tabularList.entrySet()) {
                writer.write(roomPosNo.getKey());
                for (wifiNode node : roomPosNo.getValue()) {
                    writer.write("," + node.getStrength());
                }
                writer.write("\n");
            }

            writer.write("\n\n\n\n");
        }

        writer.close();
    }

}

