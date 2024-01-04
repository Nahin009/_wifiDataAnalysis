import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class read_from_csv {

    public static void main(String[] args) {
        String csvFilePath = "dataSet/203_1.csv";

        // Read and group data
        Map<String, List<Integer>> columnMajorData = readAndGroupData(csvFilePath);

        // Print the grouped data
        for (Map.Entry<String, List<Integer>> entry : columnMajorData.entrySet()) {
            String roomPosNo = entry.getKey();
            List<Integer> columnValues = entry.getValue();

            System.out.println("Room Position No: " + roomPosNo);
            System.out.println("Column Values: " + columnValues);
            System.out.println();
        }
    }

    private static Map<String, List<Integer>> readAndGroupData(String filePath) {
        Map<String, List<Integer>> columnMajorData = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Read header to get room positions
            String[] roomPositions = reader.readLine().split(",");

            // Initialize lists for each room position
            for (String roomPos : roomPositions) {
                columnMajorData.put(roomPos, new ArrayList<>());
            }

            // Read data rows and populate lists
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                for (int i = 1; i < values.length; i++) { // Start from index 1 to skip the first value (room position)
                    int value = Integer.parseInt(values[i].trim());
                    columnMajorData.get(roomPositions[i]).add(value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return columnMajorData;
    }
}
