import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class read_from_csv {

    public static void main(String[] args) {
        String csvFilePath = "dataSet/204_1.csv";

        List<List<Integer>> allVectorList = new ArrayList<>();
        List<List<Integer>> refVectorList = new ArrayList<>();

        // Read and group data
        Map<String, List<Integer>> columnMajorData = readAndGroupData(csvFilePath);

        // Print the grouped data
        for (Map.Entry<String, List<Integer>> entry : columnMajorData.entrySet()) {
            String roomPosNo = entry.getKey();
            List<Integer> columnValues = entry.getValue();

            allVectorList.add(columnValues);

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

    private static void dot_sub(List<List<Integer>> refVectorList, List<List<Integer>> allVectorList) {
        double modulus = 0.0;
        for (List<Integer> refVector : refVectorList) {
            for (List<Integer> allVector : allVectorList) {
                for (int k = 0; k < refVector.size(); k++) {
//                    System.out.println(refVector.get(k) - allVector.get(k));
//                    modulus += Math.pow(refVector.get(k) - allVector.get(k), 2);
                    modulus += refVector.get(k) * allVector.get(k);
                }
//                System.out.println();
                // print Math.sqrt(modulus) upto 2 decimal places
//                modulus /= refVector.size();
                System.out.printf("%.2f", Math.sqrt(modulus));
                System.out.println();
                modulus = 0.0;
            }
            System.out.println("==========================================");
        }
    }
}
