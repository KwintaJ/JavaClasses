import java.util.List;
import java.util.Objects;

public record PawnPosition2D(int pawnId, int x, int y) implements PawnPosition {

    public static void main(String[] args) {
        Position pos = new PawnPosition2D(1, 3, 3);
        System.out.println(pos);

        List<Position> positions = List.of(new PawnPosition2D(2, 1, 1), new PawnPosition2D(3, 3, 3));

        System.out.println(positions);
    }
}