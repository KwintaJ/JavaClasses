import java.util.Map;

public interface Histogram {
    public void setup(int threads, int bins);

    public void setVector(Vector vector);

    public boolean isReady();

    public Map<Integer, Integer> histogram();
}