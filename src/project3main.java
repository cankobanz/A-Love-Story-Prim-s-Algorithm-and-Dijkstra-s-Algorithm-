import java.io.*;
import java.util.*;

public class project3main {
    public static void main(String[] args) throws FileNotFoundException {
        /*
        * Following four hashmap is for constructing mapping system between city names and their given IDs.
        * By doing this, program can work with any type of "City Names" such as Istanbul, Bursa etc.
        * In this implementation there are two graphs. One for Mecnun's Path (Left side of the country, LSC)
        * and other one is for Honeymoon Path (Right side of the country, RSC).
        * cityNameToId1 and cityIdtoName1 is for the "Left side of the country".
        * cityNameToId2 and cityIdtoName2 is for the "Right side of the country".
        * */
        Map<String,Integer> cityNameToId1=new HashMap<>();
        Map<Integer,String> cityIdtoName1=new HashMap<>();
        Map<String,Integer> cityNameToId2=new HashMap<>();
        Map<Integer,String> cityIdtoName2=new HashMap<>();


        File inputFile = new File("D:\\Kullanıcılar\\canko\\OneDrive\\Masaüstü\\test_cases\\test_cases\\input_8.txt");
        File outputFile=new File("D:\\Kullanıcılar\\canko\\OneDrive\\Masaüstü\\test_cases\\test_cases\\output.txt");

        Scanner scan = new Scanner(inputFile);

        final int timeLimit = scan.nextInt();
        final int n=scan.nextInt();
        scan.nextLine();

        String mecnunCity= scan.next();
        String leylaCity= scan.next();

        scan.nextLine();


        /*Following while loop is the where ID-City name matching is done.
        * id1 and id2 stand for id numbers of the cities, started from 0
        * but first city in the Honeymoon path is leyla's city, with id 0.
        * n1 and n2 are the number of cities in LSC and RSC.
        * */
        int id1=-1;
        int id2=0;
        int n1=0;
        cityNameToId2.put(leylaCity,0);
        cityIdtoName2.put(0,leylaCity);

        while (scan.hasNextLine()) {

            String readLine = scan.nextLine();
            String[] cityLine = readLine.split(" ");

            try {
                if (cityLine[0].startsWith("c")) {
                    id1++;
                    n1++;

                    String cityName = cityLine[0];
                    cityNameToId1.put(cityName, id1);
                    cityIdtoName1.put(id1, cityName);
                }
                else if(cityLine[0].startsWith("d")){
                    id2++;
                    String cityName = cityLine[0];
                    cityNameToId2.put(cityName, id2);
                    cityIdtoName2.put(id2, cityName);
                }
            }catch (Exception ignored){}
        }
        scan.close();

        int n2=n-n1+1; //+1 is for Leyla's city

        /*
         * mecnunsPath and honeymoonPath are the graphs in this project.
         * mecnunsPath is directed graph and honeymoonPath is undirected path.
         */
        MecnunsPath mecnunsPath=new MecnunsPath(n1);
        HoneymoonPath honeymoonPath=new HoneymoonPath(n2);

        /* Input is scanned again and first three lines are skipped since they are already stored.
         * Then, edges are distributed according to their matching and sent to the related graphs
         * with their ID not the "City Names".
         */
        Scanner scan2=new Scanner(inputFile);

        for (int i = 0; i < 3; i++) {
            scan2.nextLine();
        }

        while (scan2.hasNextLine()) {
            String readLine= scan2.nextLine();
            String[] cityLine = readLine.split(" ");

            for (int i = 1; i < cityLine.length; i += 2) {
                String fromName = cityLine[0];
                String toName = cityLine[i];

                int length = Integer.parseInt(cityLine[i + 1]);

                try {
                    if(fromName.startsWith("c") && toName.startsWith("c")) {
                        int from = cityNameToId1.get(fromName);
                        int to = cityNameToId1.get(toName);
                        mecnunsPath.addRoad(from, to, length);
                    }
                    else if(fromName.startsWith("c") && toName.startsWith("d")) {
                        int from = cityNameToId2.get(fromName);
                        int to = cityNameToId2.get(toName);
                        honeymoonPath.addUndirectedRoad(from, to, length);
                    }
                    else if(fromName.startsWith("d") && toName.startsWith("d")) {
                        int from = cityNameToId2.get(fromName);
                        int to = cityNameToId2.get(toName);
                        honeymoonPath.addUndirectedRoad(from, to, length);
                    }
                }catch (Exception ignored){}

            }
        }
        scan2.close();

        //Applying algorithms to the graphs.
        int startLeft=cityNameToId1.get(mecnunCity);
        int endLeft=cityNameToId1.get(leylaCity);
        List<Integer> mecnunResult =mecnunsPath.dijkstraAlgorithm(startLeft,endLeft,timeLimit);

        int startRight=cityNameToId2.get(leylaCity);
        int honeymoonResult = honeymoonPath.primsAlgorithm(startRight);

        //Following try and catch stands for outputting part.
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            if (mecnunResult == null) { //Mecnun doesn't reach to Leyla
                writer.write("-1");
                honeymoonResult=-1;
                writer.write("\n"+honeymoonResult);
            } else { //Mecnun reaches to Leyla
                for (Object o : mecnunResult) {
                    writer.write(cityIdtoName1.get(o) + " ");
                }
                if(mecnunsPath.isMarried()){
                    writer.write("\n"+ honeymoonResult);
                }
                else{
                    honeymoonResult=-1;
                    writer.write("\n"+honeymoonResult);
                }
            }
            writer.close();
        } catch (IOException e){
        System.out.println("Catch - An error occurred.");
        e.printStackTrace();
    }
    }
}