import java.util.Comparator;

public class RoadComparator implements Comparator<Road> {
    @Override
    public int compare(Road road1, Road road2) {
        if (road1.getLength() < road2.getLength())
            return -1;
        else if (road1.getLength() > road2.getLength())
            return 1;
        else
            return 0;
    }
}