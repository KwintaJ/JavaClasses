public class Drawing implements SimpleDrawing {

    private static int size;
    private static int currentFirstCoordinate;
    private static int currentSecondCoordinate;
    private static int[][] theDrawing;

    public Drawing() {
        size = -1;
        currentFirstCoordinate = 0;
        currentSecondCoordinate = 0;
    }

    @Override
    public void setCanvasGeometry(Geometry input) {
        size = input.getSize();
        currentFirstCoordinate = input.getInitialFirstCoordinate();
        currentSecondCoordinate = input.getInitialSecondCoordinate();
        theDrawing = new int[size][size];
    }

    @Override
    public void draw(Segment segment) {
        int i = segment.getLength();

        switch (segment.getDirection()) {
            case 1:
                while (currentFirstCoordinate < size && i > 0) {
                    theDrawing[currentFirstCoordinate][currentSecondCoordinate] = segment.getColor();
                    i--;
                    if(i == 0 || currentFirstCoordinate == size - 1)
                        break;
                    currentFirstCoordinate++;
                }
                break;

            case 2:
                while (currentSecondCoordinate < size && i > 0) {
                    theDrawing[currentFirstCoordinate][currentSecondCoordinate] = segment.getColor();
                    i--;
                    if(i == 0 || currentSecondCoordinate == size - 1)
                        break;
                    currentSecondCoordinate++;
                }
                break;

            case -1:
                while (currentFirstCoordinate >= 0 && i > 0) {
                    theDrawing[currentFirstCoordinate][currentSecondCoordinate] = segment.getColor();
                    i--;
                    if(i == 0 || currentFirstCoordinate == 0)
                        break;
                    currentFirstCoordinate--;
                }
                break;

            case -2:
                while (currentSecondCoordinate >= 0 && i > 0) {
                    theDrawing[currentFirstCoordinate][currentSecondCoordinate] = segment.getColor();
                    i--;
                    if(i == 0 || currentSecondCoordinate == 0)
                        break;
                    currentSecondCoordinate--;
                }
                break;
        }
    }

    @Override
    public int[][] getPainting() {
        if(size < 0)
            return null;
        return theDrawing;
    }

    @Override
    public void clear() {
        if(size >= 0)
            theDrawing = new int[size][size];
    }
}
