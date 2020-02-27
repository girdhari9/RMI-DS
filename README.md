<!-- Name: Giridhari Lal Gutpa -->
<!-- Roll No.: 2018201019-->
### Instructions:
Open 3 Terminals and run the following commands

#### Terminal 1
Run the following commands:
- javac *.java
- rmiregistry

#### Terminal 2
Run the following commands:
- java Server <PORT>

#### Terminal 3
Run the following commands:
- java Client <IP> <PORT>

Commands:

1. add_graph <graphName> <vertices>
2. add_edge <graphName> <source> <destination> <weight>
3. get_mst <graphName>
4. exit or quit

### Architecture
- Client runs the command at its end to add_graph, add_edge, get_mst at its end and using the rmi the values are passed at the server (using stub and skeleton methods) where given above functions are implemented at the server, like,
	1. add_graph - create graph at the server.
	2. add_edge - added edge in the given graph (identified by GraphID).
	3. get_mst - using Kruskal's algorithm calcuates the MST total weights and sends the result back to client.
- The server can handle multiple clients.

### Algorithm implementation
- MST is created by Kruskal's ALgorithm for any given graph.

### Results and Observations
- The program is taking O(ElogE) time for calculating the MST using Kruskal's algorithm at server side.
- If two clients are updating same graph. Server will create only one copy of that graph and will update in same graph.
- If No edges or disconnected graph is provided in given graph then server will return -1.
