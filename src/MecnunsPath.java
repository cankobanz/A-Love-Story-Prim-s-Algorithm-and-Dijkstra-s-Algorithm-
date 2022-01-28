import java.util.*;
public class MecnunsPath {

    private final List<LinkedList<Road>> anatolian; //Anatolian stands for the Directed Graph which is the "Left Side of the Country"
    private final int n;                            //Number of elements in the LSC
    private boolean isMarried=false;
    private int pathLength=-1;

    //Creating empty graph.
    public MecnunsPath(int n) {
        this.n = n;

        anatolian = new ArrayList<>(n);
        for (int i = 0; i < n; i++){
            anatolian.add(new LinkedList<>());
        }
    }

    //Adding directed roads.
    public void addRoad(int start, int end, int length) {
        anatolian.get(start).add(new Road(start, end, length));
    }

    /*
    * Following method is for the Dijkstra's Algorithm to find the minimum path from the departure city
    * to arrival city which are Mecnun's City to Leyla's City. Knowing the target city is
    * abused by ending the loop early when the Mecnun arrive to Leyla's City. Also, it is checked that
    * does Mecnun arrived to the Leyla on time if not algorithm returns null.
    * Variables and steps are explained one by one.
    * */
    public List<Integer> dijkstraAlgorithm(int departure, int arrival,int timeLimit){
        boolean[] isVisited=new boolean[n];     //To keep track of the visited state of the cities. Default value is false for all.
        Integer[] pastCity = new Integer[n];    //To keep track of the passed cities.
        double[] distance = new double[n];      //Shortest path to the cities. Initial value for all arranged as INFINITY.There is no infinity for integers so it is double.

        Arrays.fill(distance,Double.POSITIVE_INFINITY);
        distance[departure]=0;      //Set distance of starting point 0.

        PriorityQueue<City> cityPQ =new PriorityQueue<>(new CityComparator()); //Designed according to pull with the least arrival distance.
        cityPQ.add(new City(departure,0));  //Distance of the starting city to itself is zero.

        //Loop continue until either priority queue is empty or the end point is arrived
        while (!cityPQ.isEmpty()){
            City city=cityPQ.poll();
            isVisited[city.getId()]=true; //Set pulled the as visited.

            //If it is longer path, skip it.
            if(distance[city.getId()]<city.getLength()){
                continue;
            }

            LinkedList<Road> roads = anatolian.get(city.getId()); //Roads that are outgoing from the city. All of them are checked.

            for (Road road : roads) {
                //If the road arrive to visited city, it is skipped.
                if(isVisited[road.getEnd()]){
                    continue;
                }

                //Offered distance equals to distance calculated until the current city + the length of the road to arrival city.
                double offeredDistance= distance[road.getStart()]+ road.getLength();
                // If the offered distance lower than the calculated distance to arrival city update step occurs.
                // Attention: if no path is calculated to the arrival city, updating will occur since initially
                // all distances is Infinity.
                if(offeredDistance< distance[road.getEnd()]){
                    pastCity[road.getEnd()]=city.getId();
                    distance[road.getEnd()]=offeredDistance;
                    cityPQ.add(new City(road.getEnd(),offeredDistance));
                }
            }
            //Stopping criteria is checked for every city.
            if (city.getId()==arrival){
                if(distance[arrival]<=timeLimit){
                    isMarried=true;
                }

                List<Integer> path=new LinkedList<>();
                //By using past city array, we go back from the arrival point to the departure point.Then, reverse it.
                for (Integer current = arrival; current!=null ; current= pastCity[current]) {//pastCity[current]== City connected to the current city with the lowest length (parent).
                    path.add(current);
                }
                Collections.reverse(path);

                return path;
            }
        }
        return null;
    }

    public boolean isMarried() {
        return isMarried;
    }
}