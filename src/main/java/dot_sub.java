import java.util.List;

public class dot_sub {

    private static final List<List<Integer>> refVectorList = List.of(
            List.of(-73, -71, -70, -64, -51, -61, -63, -69, -66, -61),
            List.of(-78, -68, -75, -70, -60, -59, -54, -66, -59, -53),
            List.of(-77, -67, -76, -71, -62, -66, -61, -72, -67, -57),
            List.of(-78, -73, -82, -76, -64, -70, -57, -75, -64, -52),
            List.of(-80, -75, -84, -75, -63, -75, -61, -71, -61, -55),
            List.of(-78, -75, -83, -73, -65, -72, -71, -71, -57, -48),
            List.of(-74, -63, -81, -66, -53, -68, -66, -61, -54, -60),
            List.of(-79, -67, -79, -64, -58, -58, -60, -59, -54, -53),
            List.of(-75, -70, -79, -71, -59, -68, -61, -68, -57, -21)
    );

    private static final List<List<Integer>> allVectorList = List.of(
            List.of(-73, -71, -70, -64, -51, -61, -63, -69, -66, -61),
            List.of(-78, -68, -75, -70, -60, -59, -54, -66, -59, -53),
            List.of(-77, -67, -76, -71, -62, -66, -61, -72, -67, -57),
            List.of(-78, -73, -82, -76, -64, -70, -57, -75, -64, -52),
            List.of(-80, -75, -84, -75, -63, -75, -61, -71, -61, -55),
            List.of(-78, -75, -83, -73, -65, -72, -71, -71, -57, -48),
            List.of(-74, -63, -81, -66, -53, -68, -66, -61, -54, -60),
            List.of(-79, -67, -79, -64, -58, -58, -60, -59, -54, -53),
            List.of(-75, -70, -79, -71, -59, -68, -61, -68, -57, -21),
            List.of(-74, -54, -70, -67, -56, -59, -56, -65, -67, -71),
            List.of(-72, -55, -60, -60, -64, -55, -54, -67, -59, -68),
            List.of(-63, -64, -75, -61, -64, -58, -60, -60, -64, -64),
            List.of(-61, -63, -82, -57, -69, -67, -58, -51, -51, -65),
            List.of(-64, -63, -81, -52, -68, -66, -56, -57, -58, -76),
            List.of(-74, -69, -85, -57, -63, -71, -64, -62, -48, -64),
            List.of(-70, -78, -85, -60, -61, -76, -70, -67, -51, -67),
            List.of(-75, -81, -84, -60, -67, -69, -67, -68, -37, -58)
    );

    public static void main(String[] args) {
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
