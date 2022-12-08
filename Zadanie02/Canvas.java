public class Canvas implements Geometry{

    private static int size;
    private static int first;
    private static int second;

    public Canvas(int a, int b, int c) {
        size = a;
        first = b;
        second = c;
    }

    @Override


    public int getSize() {
        return size;
    }

    @Override
    public int getInitialFirstCoordinate() {
        return first;
    }

    @Override
    public int getInitialSecondCoordinate() {
        return second;
    }
}
