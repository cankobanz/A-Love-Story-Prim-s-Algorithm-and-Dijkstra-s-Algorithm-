# A Love Story (Prim's Algorithm and Dijkstra's Algorithm)
This is a school project from Data Structures and Algorithms course where graphs, **Prim's Algorithm and Dijkstra's Algorithm** are used effectively.  It is an application of finding shortest path and minimum spanning tree on graphs. The project is thematized with legendary love story of Leyla and Mecnun. The aim of the project is brought together Leyla and Mecnun. And then, taking them to their honeymoon if it is possible.

**Following Parts are project descriptions that are written by assistants of the course CmpE250 (Data Structures and Algorithms) in Bogazici University.**

## 1 Introduction
As Shakespeare once said “i am to wait, though waiting so be hell...”, it is an obvious fact that
waiting is the worst thing in the world. Do not worry!!! Our hard-working team works really
hard and saves you the burden of waiting for the next project. No thanks needed, we are here
for you.
Mecnun and Leyla are two lovers who live in the Anatolian country. They try to come
together but Leyla’s father is preventing them from doing so. Recently Leyla has tried to convince
her father and she eventually manages to do. But her father offers a condition for Mecnun
that he has to reach their town in time less than or equal to a threshold. The father sets the
threshold and writes it to a piece of paper and hides the paper. Mecnun will be informed about
the challenge but he will not be provided any information about the time that was set. The
countdown for Mecnun will start at the moment he departs from his city and if he can arrive
in time Leyla’s father will let them marry. Mecnun has a map of the Anatolian country which
demonstrates all land routes over the country and their lengths. The country is divided into
two regions by a river. Leyla and Mecnun are in the same region of the country but in different
cities. Leyla’s city is the only city in the country where there are bridges for crossing to the
other side of the country. For this reason, anyone who wants to cross over to the other side
should visit this city.
Two cities can be considered neighboring cities if there is a road between them. The roads
of the country are one-way for the vehicles and two-ways for the pedestrians. Mecnun will take
a bus while going to Leyla to be able to reach fast. Each road has a bus service of its own
and there is always a bus service available at any given time. Every bus has the same constant
velocity.
Mecnun and Leyla are planning a honeymoon in which they would be traveling on foot to
all the cities of the other side of the country. But each pedestrian has to pay a sidewalk-tax
which is equal to the length of the road. If they can’t find a decent travel route for their plan, they will have to stay in the city where Leyla lives.
According to the legend, if they can’t get together, Mecnun will disappear.

## 2 Details
• For each case output consists of a list of integers and one integer, list1 and int1 . List1
indicates the output generated for Mecnun’s path to reach Leyla. int1 indicates the output
generated for the Honeymoon path.  
• Mecnun does not know the time set by the father, so he must take the shortest possible
route. The shortest route to be used in list1 should be containing the IDs of the cities
passed starting from Mecnun’s city to Leyla’s city. If there is no route from Mecnun’s
city to Leyla’s city, list1 should contain only -1.  
• When Mecnun tries to reach Leyla’s city, he cannot get over the bridge. On the honeymoon,
they can only visit Leyla’s city + cities of other side of the country.(For the
sample map illustrated in Schema 1, only c7, d1, d2, d3, and d4 cities can be visited on
the honeymoon.)  
• If Mecnun can reach Leyla, Leyla’s father will compare the time he arrived with the time
on the paper and come to a decision. Since the speed of the buses is 1br/second, the
arrival time is equal to the total length of the path.  
• Mecnun and Leyla are planning to walk around all the cities of the other side of the
country and have the walkthrough honeymoon with the lowest sidewalk-tax. The roads
traveled are subject to a sidewalk-tax proportional to their length, and after the tax is
paid once, subsequent uses of the road are free of charge. In this case, the length of the
path they walk does not matter. The sidewalk-tax paid must be the lowest and the route
must include all cities of the other side of the country. The tax paid by Mecnun will be
the int1 part of the output.  
• If there is no such honeymoon route, Mecnun stays in Leyla’s city and int1 should be -2.
• If they cannot get married, Mecnun disappears as per the legend. In this case int1 should
be -1.  

## 3 Input & Output
### 3.1 Input
• The first line represents the time set by Leyla’s father.  
• The second line represents the total number of cities in the Anatolian country.  
• The third line represents the ID of the city where Mecnun lives and the ID of the city
where Leyla lives, respectively.  
• Each next line will give the ID of a city of Anatolian country and the IDs and lengths of
the cities that can be reached by vehicle from that city in pairs. For example:  
If there is a way from c1 city to c2, c3, and c4 cities of length 1, 2, and 3 respectively.  
Input line will be c1 c2 1 c3 2 c4 3  
If there is no way from c2, input line for c2 will be c2  
![1](https://user-images.githubusercontent.com/81170575/151528147-89a2b6eb-423c-482c-aa0e-948618130617.png)  

![2](https://user-images.githubusercontent.com/81170575/151528183-1f259fa1-d6df-47f4-af96-263550dd9b82.png)

### 3.2 Output
1. For each case there will be two lines of output. First line will be list1 and second line will
be int1.  
2. List1, Mecnun’s path of reaching Leyla (if he cannot reach -1 should be written) should
be written.  
3. Int1, the tax paid on the Honeymoon route if it is possible. (If Leyla and Mecnun don’t
marry -1 should be written but if they did not find a suitable honeymoon route -2 should
be written.)  
4. If there is more than one possible output for a test case, you can use any. The program
used for grading will test their accuracy.  

#### Output File Explanation
**c1 c2 c5 c7 or c1 c3 c6 c7** The path that Mecnun used to reach Leyla. Since there are two paths with the
same minimum length of 6, one of them should be on the output file.  
**16** The side-walk tax paid on the Honeymoon route. (c7,d1), (d1,d4), (d1,d3) and
(d2,d3) ways are used so (2+3+2+1)*2=16.  

