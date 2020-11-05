# Course: Object Oriented Programming - Ariel University
#### Assignment: ex0
#### Student: Alon Firestein



For this assignment, I implemented the three classes (NodeData, Graph_DS, Graph_Algo) using the their respective interfaces that was provided to me in order to create a fully functional graph.

------------------------------------

*****NodeData*****

Each node has its own unique identifier (Key), its value (NodeInfo), and a Tag (to mark it).
In order to keep track of which node is connected to who, I used the ArrayList data structure with node_data in it to keep track of each nodes adjacent nodes (neighbours). 
Using this information and the variables, I was able to fulfill the node_data interface function requirements.

Additional information on each function is located as Javadoc comments ABOVE each function.

------------------------------------

*****Graph_DS*****

To start creating a graph, I used the the NodeData class to control and modify the nodes in the graph, and used the HashMap data structure to keep track of the them. (Key: Node ID, Value: Node Data).
I chose to use HashMap because it helped me to easily store the nodes and their information using only their ID(key).
Therefore easily and quickly fetching any information needed by calling the particular key.
Using the get and put functions in HashMap, I was able to do this with O(1) complexity.

Additional information on each function is located as Javadoc comments ABOVE each function.

------------------------------------

*****Graph_Algo***** 

This class was built in order to properly utilize a couple algorithms in the graph, using the Graph_DS class.
Algorithms such as (isConnected, shortestPathDist, shortestPath) were built using the idea of the BFS algorithm method in order for traversing and searching throughout the graph.
I used each nodes Tag method in order to check and mark which node has been visited in the traversal.
I used the Queue data structure in order to properly travel across the graph and knowing which path to go and which node was already visited.
Depending on the function, I used different structures in order to return the result in the most efficient way possible.
Furthermore, I also used a HashMap in the shortestPath function in order to store each nodes parent node so once the function reaches the destination node, it knows how to get back to the original source node and therefore quickly retracing the shortest path possible between the two nodes.

### Run time for functions: Worst case scenarios (Asymptotic Upper Bounds):
|Function Name | Time Complexity | Extra Info |
|--------------|-----------------|------------|
|isConnected | O(\|V\|+\|E\|) | The function goes through every node in the graph to find out if it's connected. |
|shortestPath | O(\|V\|+\|E\|) | The function goes through almost every node in the graph to find the shortest path. |
|shortestPathDist | O(\|V\|+\|E\|) | Using shortestPath to find the distance (# of edges it had to cross). |

V - vertices   ,     E - edges


Additional information on each function is located as Javadoc comments ABOVE each function.


------------------------------------


***Sources for extra help:***
- https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
- https://en.wikipedia.org/wiki/Shortest_path_problem
- https://en.wikipedia.org/wiki/Breadth-first_search
- https://youtu.be/s-CYnVz-uh4

