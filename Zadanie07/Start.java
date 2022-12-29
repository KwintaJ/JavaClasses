import java.util.*;

public class Start {
    static FastHistogram H = new FastHistogram();

    public static void main(String[] args) {
        Vector V = new Vector() {
            @Override
            public int getValue(int idx) {
                List<Integer> L = List.of(1, 2, 4, 2, 4, 1, 1, 6, 1, 5, 1, 2, 3, 2, 4, 3, 1, 6, 1, 6);
                return L.get(idx);
            }

            @Override
            public int getSize() {
                return 20;
            }
        };

        H.setup(5, 6);
        H.setVector(V);

        while(!H.isReady()) {
            long msec = 150 + System.currentTimeMillis();
            do {
            } while (msec > System.currentTimeMillis());
        }

        Map<Integer, Integer> M = H.histogram();
        System.out.println(M);
    }
}
