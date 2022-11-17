import java.util.*;
import java.util.stream.*;

public class Lines implements LinesInterface{
    private Set<Point> Points;
    private Set<Segment> Segments;

    private Set<Point> visited;
    private List<Segment> path;



    public Lines() {
        Points = new HashSet<>();
        Segments = new HashSet<>();
        visited = new HashSet<>();
        path = new ArrayList<>();
    }

    @Override
    public void addPoints(Set<Point> points) {
        Points = new HashSet<>(points);
    }

    @Override
    public void addSegments(Set<Segment> segments) {
        Segments = new HashSet<>(segments);
    }

    private Set<Segment> getNeighbours(Point v) {
        Set<Segment> S = new HashSet<>();

        for(Segment e : Segments) {
            if(e.getEndpoint1() == v || e.getEndpoint2() == v) {
                S.add(e);
            }
        }

        return S;
    }

    private boolean visitNode(Point v, Point end) {
        visited.add(v);

        if(v.getName().equals(end.getName())) {
            return true;
        }

        Set<Segment> N = getNeighbours(v);
        for (Segment e : N) {
            if(!visited.contains(e.getEndpoint1())) {
                path.add(e);
                if(!visitNode(e.getEndpoint1(), end)) {
                    path.remove(e);
                }
                else {
                    return true;
                }
            }
            if(!visited.contains(e.getEndpoint2())) {
                path.add(e);
                if(!visitNode(e.getEndpoint2(), end)) {
                    path.remove(e);
                }
                else {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public List<Segment> findConnection(Point start, Point end) {
        path.clear();
        visited.clear();

        if(!visitNode(start, end)) {
            path.clear();
        }

        return path;
    }

    @Override
    public Map<Point, Set<Segment>> getMapEndpointToSegments() {
        Map<Point, Set<Segment>> M = new HashMap<>();

        for(Point v : Points) {
            M.put(v, getNeighbours(v));
        }

        return M;
    }

    private void dfs(Point v, Point start, Point prev, int depth, Set<Point> targets) {
        Set<Point> N = new HashSet<>();

        for (Segment e : getNeighbours(v)) {
            N.add(e.getEndpoint1());
            N.add(e.getEndpoint2());
        }

        N.remove(v);
        N.remove(start);
        N.remove(prev);

        if (depth == 1) {
            targets.addAll(N);
        }
        else {
            for (Point i : N) {
                dfs(i, start, v, depth - 1, targets);
            }
        }
    }

    @Override
    public Map<Point, Map<Integer, Set<Point>>> getReachableEndpoints() {
        Map<Point, Map<Integer, Set<Point>>> M = new HashMap<>();

        for (Point v : Points) {
            Map<Integer, Set<Point>> subM = new HashMap<>();

            for (int i = 1; i <= 4; i++) {
                Set<Point> S = new HashSet<>();
                dfs(v, v, null, i , S);
                subM.put(i, S);
            }

            M.put(v, subM);
        }

        return M;
    }
}
