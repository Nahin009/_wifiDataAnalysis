import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class BoundaryPoints {
    int StartX;
    int StartY;
    int EndX;
    int EndY;

    BoundaryPoints(int StartX, int StartY, int EndX, int EndY) {
        this.StartX = StartX;
        this.StartY = StartY;
        this.EndX = EndX;
        this.EndY = EndY;
    }

    public int getStartX() {
        return StartX;
    }

    public int getStartY() {
        return StartY;
    }

    public int getEndX() {
        return EndX;
    }

    public int getEndY() {
        return EndY;
    }

    public boolean isInside(double x, double y) {
        // return true if the point is inside the boundary
        return x >= StartX && x <= EndX && y >= StartY && y <= EndY;
    }
}

public class Coordinates {
    Map<String, Pair<Integer, Integer>> posGridMapping;
    BoundaryPoints boundaryPoints;

    Coordinates(){
        posGridMapping = new HashMap<>();
        posGridMapping.put("CSE_203_r1c1_i", new Pair<>(1, 1));
        posGridMapping.put("CSE_203_r1c2_i", new Pair<>(1, 2));
        posGridMapping.put("CSE_203_r1c3_i", new Pair<>(1, 3));
        posGridMapping.put("CSE_203_r1c4_i", new Pair<>(1, 4));
        posGridMapping.put("CSE_203_r1c5_o", new Pair<>(1, 5));
        posGridMapping.put("CSE_203_r2c1_i", new Pair<>(2, 1));
        posGridMapping.put("CSE_203_r2c2_i", new Pair<>(2, 2));
        posGridMapping.put("CSE_203_r2c3_i", new Pair<>(2, 3));
        posGridMapping.put("CSE_203_r2c4_i", new Pair<>(2, 4));
        posGridMapping.put("CSE_203_r2c5_o", new Pair<>(2, 5));
        posGridMapping.put("CSE_203_r3c1_i", new Pair<>(3, 1));
        posGridMapping.put("CSE_203_r3c2_i", new Pair<>(3, 2));
        posGridMapping.put("CSE_203_r3c3_i", new Pair<>(3, 3));
        posGridMapping.put("CSE_203_r3c4_i", new Pair<>(3, 4));
        posGridMapping.put("CSE_203_r3c5_o", new Pair<>(3, 5));
        posGridMapping.put("CSE_203_r4c1_i", new Pair<>(4, 1));
        posGridMapping.put("CSE_203_r4c2_i", new Pair<>(4, 2));
        posGridMapping.put("CSE_203_r4c3_i", new Pair<>(4, 3));
        posGridMapping.put("CSE_203_r4c4_i", new Pair<>(4, 4));
        posGridMapping.put("CSE_203_r0c0_o", new Pair<>(0, 0));
        posGridMapping.put("CSE_203_r0c1_o", new Pair<>(0, 1));
        posGridMapping.put("CSE_203_r0c2_o", new Pair<>(0, 2));
        posGridMapping.put("CSE_203_r0c3_o", new Pair<>(0, 3));
        posGridMapping.put("CSE_203_r0c4_o", new Pair<>(0, 4));
        posGridMapping.put("CSE_203_r0c5_o", new Pair<>(0, 5));

        // multiply the row and column by 2 to get the actual position
        for (Map.Entry<String, Pair<Integer, Integer>> entry : posGridMapping.entrySet()) {
            Pair<Integer, Integer> pair = entry.getValue();
            pair.setFirst(pair.getFirst() * 2 + 1);
            pair.setSecond(pair.getSecond() * 2 + 1);
            entry.setValue(pair);
        }

        boundaryPoints = new BoundaryPoints(2, 2, 10, 10);
    }

    public Map<String, Pair<Integer, Integer>> getPosGridMapping() {
        return posGridMapping;
    }

    public BoundaryPoints getBoundaryPoints() {
        return boundaryPoints;
    }
}
