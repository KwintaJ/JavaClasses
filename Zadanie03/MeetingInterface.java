import java.util.List;
import java.util.Set;

public interface MeetingInterface {

    public void addPawns(List<PawnPosition> positions);

    public void addMeetingPoint(Position meetingPointPosition);

    public void move();

    public Set<PawnPosition> getAllPawns();

    public Set<PawnPosition> getNeighbours(int pawnId);
}