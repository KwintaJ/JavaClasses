import java.util.*;

public class Meeting implements MeetingInterface{

    List<PawnPosition> allPawns;
    List<PawnPosition> movedPawns;
    Set<Integer> movedPawnsIds;
    Position meetingPoint;
    int moveCounter = 0;

    public Meeting() {
        allPawns = new ArrayList<>();
        meetingPoint = new Position2D(0, 0);
    }

    @Override
    public void addPawns(List<PawnPosition> positions) {
        allPawns.addAll(positions);
    }

    @Override
    public void addMeetingPoint(Position meetingPointPosition) {
        meetingPoint = meetingPointPosition;
    }

    public boolean isOneOff(Position A, Position B) {
        if (A.equals(B))
            return false;
        return Math.abs(A.x() - B.x()) <= 1 & Math.abs(A.y() - B.y()) <= 1;
    }

    @Override
    public Set<PawnPosition> getNeighbours(int pawnId) {
        PawnPosition xPawn = new PawnPosition2D(-1, 0, 0);
        Set<PawnPosition> xsNeighbours = new HashSet<>();

        for (PawnPosition X : allPawns) {
            if (X.pawnId() == pawnId) {
                xPawn = X;
            }
        }

        for (PawnPosition Y : allPawns) {
            if (!movedPawnsIds.contains(Y.pawnId()) & isOneOff(xPawn, Y)) {
                xsNeighbours.add(Y);
            }
        }

        for (PawnPosition Y : movedPawns) {
            if (isOneOff(xPawn, Y)) {
                xsNeighbours.add(Y);
            }
        }

        return xsNeighbours;
    }

    boolean notBlocking(PawnPosition P, Set<PawnPosition> N) {
        for (PawnPosition neighbour : N) {
            if(P.x() == neighbour.x() & P.y() == neighbour.y())
                return false;
        }
        return true;
    }

    PawnPosition movePawn(PawnPosition pawnToMove) {
        int dx = meetingPoint.x() - pawnToMove.x();
        int dy = meetingPoint.y() - pawnToMove.y();

        if (Math.abs(dx) == 0 & Math.abs(dy) == 0) {
            return pawnToMove;
        }

        Set<PawnPosition> theNeighbours = getNeighbours(pawnToMove.pawnId());

        if (Math.abs(dx) > Math.abs(dy)) {
            PawnPosition pawnToMoveNewPosition = pawnToMove;

            if (dx < 0)
                pawnToMoveNewPosition = new PawnPosition2D(pawnToMove.pawnId(), pawnToMove.x() - 1, pawnToMove.y());

            if (dx > 0)
                pawnToMoveNewPosition = new PawnPosition2D(pawnToMove.pawnId(), pawnToMove.x() + 1, pawnToMove.y());

            if (notBlocking(pawnToMoveNewPosition, theNeighbours)) {
                moveCounter++;
                return pawnToMoveNewPosition;
            }
        }

        if (Math.abs(dx) <= Math.abs(dy)) {
            PawnPosition pawnToMoveNewPosition = pawnToMove;

            if (dy < 0)
                pawnToMoveNewPosition = new PawnPosition2D(pawnToMove.pawnId(), pawnToMove.x(), pawnToMove.y() - 1);

            if (dy > 0)
                pawnToMoveNewPosition = new PawnPosition2D(pawnToMove.pawnId(), pawnToMove.x(), pawnToMove.y() + 1);

            if (notBlocking(pawnToMoveNewPosition, theNeighbours)) {
                moveCounter++;
                return pawnToMoveNewPosition;
            }
        }
        return pawnToMove;
    }

    @Override
    public void move() {
        while (true) {
            oneMove();

            if(moveCounter == 0)
                return;
        }
    }

    List<PawnPosition> reverse(List<PawnPosition> L) {
        List<PawnPosition> G = new ArrayList<>();
        for (int i = (L.size() - 1); i >= 0; i--) {
            G.add(L.get(i));
        }
        return G;
    }

    void oneMove() {
        moveCounter = 0;
        movedPawns = new ArrayList<>();
        movedPawnsIds = new HashSet<>();

        for (PawnPosition pawnToMove : allPawns) {
            movedPawns.add(movePawn(pawnToMove));
            movedPawnsIds.add(pawnToMove.pawnId());
        }
        allPawns = new ArrayList<>(reverse(movedPawns));
    }

    @Override
    public Set<PawnPosition> getAllPawns() {
        return new HashSet<>(allPawns);
    }
}
