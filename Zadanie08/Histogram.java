import java.util.Map;
import java.util.function.Consumer;

public interface Histogram {
    
    public record HistogramResult(int vectorID, Map<Integer, Integer> histogram) {
    }

    public void setup(int bins, Consumer<HistogramResult> histogramConsumer);

    public void addVector(int vectorID, Vector vector);
}