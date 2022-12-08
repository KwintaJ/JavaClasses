public class Main {

    public static void main(String[] args) {
        Canvas C = new Canvas(13, 3, 6);
        Drawing D = new Drawing();


        D.setCanvasGeometry(C);
        D.draw(new Line(1, 3, 1));
        D.draw(new Line(1, 3, 2));
        D.draw(new Line(2, 4, 3));
        D.draw(new Line(-1, 40, 4));
        D.draw(new Line(-2, 40, 5));
        int[][] painting = D.getPainting();
        String output = "";

        for (int i = 0; i < C.getSize(); i++) {
            output = "";
            for (int j = 0; j < C.getSize(); j++) {
                output += (Integer.toString(painting[j][i]) + " ");
            }
            System.out.println(output);
        }
    }
}