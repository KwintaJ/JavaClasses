import java.util.Map;
import java.util.Set;

public class Main {
    static Lines lines = new Lines();

    public static void main(String[] args) {
        LinesInterface.Point A = () -> "A";
        LinesInterface.Point B = () -> "B";
        LinesInterface.Point C = () -> "C";
        LinesInterface.Point D = () -> "D";
        LinesInterface.Point E = () -> "E";
        LinesInterface.Point F = () -> "F";
        LinesInterface.Point G = () -> "G";
        LinesInterface.Point H = () -> "H";
        LinesInterface.Point I = () -> "I";
        LinesInterface.Point J = () -> "J";


        LinesInterface.Segment AB = produceSegment(A, B);
        LinesInterface.Segment AD = produceSegment(A, D);
        LinesInterface.Segment BC = produceSegment(B, C);
        LinesInterface.Segment EF = produceSegment(E, F);
        LinesInterface.Segment AE = produceSegment(A, E);
        LinesInterface.Segment CG = produceSegment(C, G);
        LinesInterface.Segment GH = produceSegment(G, H);
        LinesInterface.Segment HI = produceSegment(H, I);
        LinesInterface.Segment IJ = produceSegment(I, J);
        LinesInterface.Segment DE = produceSegment(D, E);
        LinesInterface.Segment CI = produceSegment(C, I);


        lines.addPoints(Set.of(A, B, C, D, E, F, G, H, I, J));
        lines.addSegments(Set.of(AB, AD, BC, EF, AE, CG, GH, HI, IJ, DE, CI));

        Map<LinesInterface.Point, Map<Integer, Set<LinesInterface.Point>>> M1 = lines.getReachableEndpoints();

        for (var i : M1.entrySet()) {
            System.out.println(i.getKey().getName() + ": ");
            Map<Integer, Set<LinesInterface.Point>> sM1 = i.getValue();
            for (var j : sM1.entrySet()) {
                System.out.println("    " + j.getKey() + ": ");
                for (var e : j.getValue()) {
                    System.out.println("        " + e.getName());
                }
            }
            System.out.println();
        }

    }

    static LinesInterface.Segment produceSegment(LinesInterface.Point X, LinesInterface.Point Y) {
        return new LinesInterface.Segment() {
            @Override
            public LinesInterface.Point getEndpoint1() {
                return X;
            }

            @Override
            public LinesInterface.Point getEndpoint2() {
                return Y;
            }
        };
    }
}
