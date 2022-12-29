import java.util.HashMap;
import java.util.Map;

public class FastHistogram implements Histogram{
    private int readiness;
    private Map<Integer, Integer> H;
    private int numOfThreads;
    private int numOfBuckets;
    private int oneThreadSize = 0;
    private int vSize = 0;
    private Vector theVector;

    synchronized void incrementHistogram(int num) {
        if(H.containsKey(num)) {
            int toInc = H.get(num);
            H.replace(num, toInc + 1);
        } else {
            H.put(num, 1);
        }
    }

    synchronized void flagReady() {
        readiness++;
    }

    @Override
    public void setup(int threads, int bins) {
        H = new HashMap<>();
        numOfThreads = threads;
        numOfBuckets = bins;
    }

    @Override
    public void setVector(Vector vector) {
        readiness = 0;

        theVector = vector;
        vSize = vector.getSize();
        if(vSize % numOfThreads == 0)
            oneThreadSize = vSize / numOfThreads;
        else
            oneThreadSize = (vSize / numOfThreads) + 1;

        for(int i = 0; i < numOfThreads; i++) {
            Runnable r = new Calculate(i);
            new Thread(r).start();
        }
    }

    class Calculate implements Runnable {
        int threadNum;

        public Calculate(int num) {
            threadNum = num;
        }        

        @Override
        public void run() {
            int left = threadNum * oneThreadSize;
            int right = Math.min(left + oneThreadSize, vSize);
            // System.out.println("thread " + threadNum + " works from " + left + " to " + right);
        
            for(int i = left; i < right; i++) {
                int val = theVector.getValue(i);
                incrementHistogram(val);
            }

            flagReady();
        }
    }

    @Override
    public boolean isReady() {
        return readiness == numOfThreads;
    }

    @Override
    public Map<Integer, Integer> histogram() {
        return H;
    }
}
