import java.util.*;

public class HoneymoonPath {

    private final List<LinkedList<Road>> otherSide; //Undirected Graph for "Right Side of the Country"
    private final int n;                            //Number of elements in the Right Country

    private boolean[] isVisited;    //Default value is false for all. Any city is visited yet.

    private final PriorityQueue<Road> roadPQ=new PriorityQueue<>(new RoadComparator());

    //Creating empty graph.
    public HoneymoonPath(int n) {
        this.n = n;
        otherSide = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            otherSide.add(new LinkedList<>());
        }
    }

    //Add undirected edges. Directed to two ways road equal to undirected edges.
    public void addUndirectedRoad(int from, int to, int tax) {
        otherSide.get(from).add(new Road(from, to, tax));
        otherSide.get(to).add(new Road(to, from, tax));
    }

    /*
     * Following method is for the Prim's Algorithm to find the minimum cost to visit all cities.
     * Note that: I did not track of the path since it is not asked. Algorithm only returns the cost.
     * */
    public int primsAlgorithm(int start) {
        int taxPaid=0;
        isVisited = new boolean[n];

        int checker = n - 1; //To check Prim's algorithm was successful
        int roadCount = 0; //Number of connected roads.(Number of elements in the spanning tree)


        isVisited[start] = true;

        List<Road> startRoads = otherSide.get(start);   //Roads outgoing from current city.
        //If the departure city not connected to current city, add road to priority queue.
        for (Road road : startRoads)
            if (!isVisited[road.getEnd()]) {
                roadPQ.add(road);
            }

        while (!roadPQ.isEmpty() && roadCount != checker) {
            Road pulledRoad = roadPQ.poll();
            int cityId = pulledRoad.getEnd();

            if (isVisited[cityId]) continue; //If already connected, skip it.

            roadCount++; //If not connected, increase number of connected roads one.
            taxPaid += pulledRoad.getLength();//If not connected, add cost of the road to total tax paid.

            //Add all the outgoing roads from departure city to priority queue to choose the best choice in the next iteration.

            isVisited[cityId] = true;

            List<Road> Roads = otherSide.get(cityId);   //Roads outgoing from current city.
            //If the departure city not connected to current city, add road to priority queue.
            for (Road road : Roads)
                if (!isVisited[road.getEnd()]) {
                    roadPQ.add(road);
                }
        }

        if(roadCount != checker) return -2;
        else return taxPaid*2; //Multiply by two since Leyla and Mecnun are two person.
    }
}