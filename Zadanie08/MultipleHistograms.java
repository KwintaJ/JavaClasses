import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class MultipleHistograms implements Histogram {

    private int numOfBuckets;
    public static Consumer<Histogram.HistogramResult> histCons;

    class Calculate implements Runnable {
        int vID;
        Vector V;
        Map<Integer, Integer> H = new HashMap<>();

        public Calculate(int id, Vector in) {
            vID = id;
            V = in;
        }

        @Override
        public void run() {
            for(int i = 0; i < V.getSize(); i++) {
                int val = V.getValue(i);
                incrementHistogram(val);
            }

            Runnable t = new Deliver(new Histogram.HistogramResult(vID, H));
            new Thread(t).start();
        }

        void incrementHistogram(int num) {
            if(H.containsKey(num)) {
                int toInc = H.get(num);
                H.replace(num, toInc + 1);
            } else {
                H.put(num, 1);
            }
        }
    }

    class Deliver implements Runnable {
        Histogram.HistogramResult histogramToDeliver;

        public Deliver(Histogram.HistogramResult H) {
            histogramToDeliver = H;
        }

        @Override
        public void run() {
            histCons.accept(histogramToDeliver);
        }
    }

    public void setup(int bins, Consumer<Histogram.HistogramResult> histogramConsumer) {
        numOfBuckets = bins;
        histCons = histogramConsumer;
    }

    public void addVector(int vectorID, Vector vector) {
        Runnable r = new Calculate(vectorID, vector);
        new Thread(r).start();
    }
}
