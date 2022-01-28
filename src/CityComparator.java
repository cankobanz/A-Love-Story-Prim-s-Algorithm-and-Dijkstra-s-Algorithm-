import java.util.Comparator;

public class CityComparator implements Comparator<City> {
    @Override
    public int compare(City city1, City city2) {
        if (city1.getLength() < city2.getLength())
            return -1;
        else if (city1.getLength() > city2.getLength())
            return 1;
        else
            return 0;
    }
}