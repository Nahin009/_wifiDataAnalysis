import java.util.List;

public class dot_sub204 {

    private static final List<List<Integer>> refVectorList204 = List.of(
            List.of(-71, -64, -59, -59, -73, -44, -59, -62, -67, -61),
            List.of(-70, -56, -67, -59, -70, -44, -60, -50, -63, -52),
            List.of(-73, -64, -71, -56, -77, -52, -55, -70, -65, -58),
            List.of(-72, -63, -76, -55, -81, -61, -57, -69, -74, -58),
            List.of(-68, -59, -77, -48, -82, -65, -56, -74, -78, -63),
            List.of(-67, -70, -77, -55, -77, -57, -53, -68, -66, -55),
            List.of(-68, -65, -81, -55, -68, -62, -44, -59, -55, -59),
            List.of(-68, -62, -77, -58, -69, -61, -59, -64, -63, -55),
            List.of(-63, -60, -69, -54, -79, -57, -66, -64, -73, -13)
    );

    private static final List<List<Integer>> allVectorList204 = List.of(
            List.of(-71, -64, -59, -59, -73, -44, -59, -62, -67, -61),
            List.of(-70, -56, -67, -59, -70, -44, -60, -50, -63, -52),
            List.of(-73, -64, -71, -56, -77, -52, -55, -70, -65, -58),
            List.of(-72, -63, -76, -55, -81, -61, -57, -69, -74, -58),
            List.of(-68, -59, -77, -48, -82, -65, -56, -74, -78, -63),
            List.of(-67, -70, -77, -55, -77, -57, -53, -68, -66, -55),
            List.of(-68, -65, -81, -55, -68, -62, -44, -59, -55, -59),
            List.of(-68, -62, -77, -58, -69, -61, -59, -64, -63, -55),
            List.of(-63, -60, -69, -54, -79, -57, -66, -64, -73, -13),
            List.of(-75, -53, -64, -66, -61, -54, -68, -52, -71, -62),
            List.of(-75, -53, -64, -66, -61, -54, -68, -52, -71, -62),
            List.of(-74, -54, -77, -61, -57, -69, -58, -50, -63, -66),
            List.of(-62, -54, -78, -58, -58, -62, -46, -51, -59, -64),
            List.of(-59, -56, -79, -57, -61, -70, -59, -56, -57, -74),
            List.of(-77, -54, -73, -64, -72, -53, -66, -64, -74, -69),
            List.of(-73, -47, -77, -64, -72, -60, -63, -73, -67, -64),
            List.of(-83, -43, -77, -73, -63, -64, -74, -70, -77, -68),
            List.of(-85, -62, -76, -70, -55, -61, -71, -55, -78, -70)
    );

    public static void main(String[] args) {
        double modulus = 0.0;
        for (List<Integer> refVector : refVectorList204) {
            for (List<Integer> allVector : allVectorList204) {
                for (int k = 0; k < refVector.size(); k++) {
//                    System.out.println(refVector.get(k) - allVector.get(k));
                    modulus += Math.pow(refVector.get(k) - allVector.get(k), 2);
//                    modulus += refVector.get(k) * allVector.get(k);
                }
//                System.out.println();
                // print Math.sqrt(modulus) upto 2 decimal places
//                modulus /= refVector.size();
                System.out.printf("%.2f ", Math.sqrt(modulus));
                System.out.println();
                modulus = 0.0;
            }
            System.out.println("==========================================");
        }
    }
}
