import java.util.*;

public class TestingData {
    private static final int K = 3;


    List<String> refPointsInside, refPointsOutside, testPointsInside, testPointsOutside;
    List<String> AllRefPoints, AllTestPoints;

    Map<String, AvgStrengthMap> AllPosAvgStrengthMapOfSelectedSSIDs;
    Map<String, AvgStrengthMap> refPoints, testPoints;

    public TestingData(List<String> refPointsInside, List<String> refPointsOutside, List<String> testPointsInside, List<String> testPointsOutside, Map<String, AvgStrengthMap> AllPosAvgStrengthMapOfSelectedSSIDs) {
        this.refPointsInside = refPointsInside;
        this.refPointsOutside = refPointsOutside;
        this.testPointsInside = testPointsInside;
        this.testPointsOutside = testPointsOutside;
        AllRefPoints = this.refPointsInside;
        AllRefPoints.addAll(this.refPointsOutside);
        AllTestPoints = this.testPointsInside;
        AllTestPoints.addAll(this.testPointsOutside);

        this.AllPosAvgStrengthMapOfSelectedSSIDs = AllPosAvgStrengthMapOfSelectedSSIDs;

        refPoints = new HashMap<>();
        testPoints = new HashMap<>();
        for (String refPoint : AllRefPoints) {
            refPoints.put(refPoint, AllPosAvgStrengthMapOfSelectedSSIDs.get(refPoint));
        }
        for (String testPoint : AllTestPoints) {
            testPoints.put(testPoint, AllPosAvgStrengthMapOfSelectedSSIDs.get(testPoint));
        }
    }

    private Double EuclideanDistance(AvgStrengthMap refPoint, AvgStrengthMap testPoint) {
        double distance = 0.0;
        for (Map.Entry<String, Double> entry : refPoint.map.entrySet()) {
            distance += Math.pow(entry.getValue() - testPoint.map.get(entry.getKey()), 2);
        }
        return Math.sqrt(distance);
    }

    private Double DotProduct(AvgStrengthMap refPoint, AvgStrengthMap testPoint) {
        double product = 0.0;
        for (Map.Entry<String, Double> entry : refPoint.map.entrySet()) {
            product += entry.getValue() * testPoint.map.get(entry.getKey());
        }
        return Math.sqrt(product);
    }

    public void KNN_With_MajorityVoting() {
        int matchedCount = 0;
        int unmatchedCount = 0;
        // For every test Points, find the K nearest ref points
        for (Map.Entry<String, AvgStrengthMap> testPoint : testPoints.entrySet()) {
            Map<String, Double> distanceMap = new HashMap<>();
            for (Map.Entry<String, AvgStrengthMap> refPoint : refPoints.entrySet()) {
                distanceMap.put(refPoint.getKey(), EuclideanDistance(refPoint.getValue(), testPoint.getValue()));
            }
            List<Map.Entry<String, Double>> distanceList = new ArrayList<>(distanceMap.entrySet());
            distanceList.sort(Map.Entry.comparingByValue());
            Map<String, Integer> countMap = new HashMap<>();
            for (int i = 0; i < K; i++) {
                String refPoint = distanceList.get(i).getKey();
                if (refPoint.endsWith("_i")) {
                    countMap.put("inside", countMap.getOrDefault("inside", 0) + 1);
                } else {
                    countMap.put("outside", countMap.getOrDefault("outside", 0) + 1);
                }
            }
            if (countMap.getOrDefault("inside", 0) > countMap.getOrDefault("outside", 0)) {
                // Test the result is true or false
                if (testPoint.getKey().endsWith("_i")) {
                    System.out.println(testPoint.getKey() + " -> true");
                    matchedCount++;
                }
                else {
                    System.out.println(testPoint.getKey() + " -> false");
                    unmatchedCount++;
                }
            } else {
                // Test the result is true or false
                if (testPoint.getKey().endsWith("_i")) {
                    System.out.println(testPoint.getKey() + " -> false");
                    unmatchedCount++;
                }
                else {
                    System.out.println(testPoint.getKey() + " -> true");
                    matchedCount++;
                }
            }
        }
        System.out.println("Matched: " + matchedCount);
        System.out.println("Unmatched: " + unmatchedCount);
    }
}
