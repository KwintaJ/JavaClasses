public class Line implements Segment{

    private int d;
    private int l;
    private int c;

    public Line(int d, int l, int c) {
        this.d = d;
        this.l = l;
        this.c = c;
    }

    @Override
    public int getDirection() {
        return d;
    }

    @Override
    public int getLength() {
        return l;
    }

    @Override
    public int getColor() {
        return c;
    }
}
