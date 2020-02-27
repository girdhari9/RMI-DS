// @Giridhari Lal Gupta #2018201019
import java.util.*;

public class GraphServer {
    int vertices;
    ArrayList<Edge> allEdges;
    static class Edge {
        int source, destination, weight;
        public Edge(int s, int d, int w) {
            weight = w;
            destination = d-1;
            source = s-1;
        }
    }

    GraphServer(int v) {
        allEdges = new ArrayList<>();
        vertices = v;
    }

    public void addEgde(int source, int destination, int weight) {
        Edge edge;
        edge = new Edge(source, destination, weight);
        allEdges.add(edge);
    }

    public static void print(String S){
        System.out.println(S);
    }
    // @2018201019
    public String getMST(){

        if(allEdges.size() == 0)
            return "-1";

        PriorityQueue<Edge> pq = new PriorityQueue<>(allEdges.size(), Comparator.comparingInt(o -> o.weight));

        insertEdge(allEdges, pq);
        
        int [] parent = new int[vertices];

        makeSet(parent);

        int index = 0;
        ArrayList<Edge> mst = new ArrayList<>();

        while(index < vertices-1 && pq.size() > 0){
            Edge edge = pq.peek();
            pq.remove();
            int x_set = find(parent, edge.source), y_set = find(parent, edge.destination);

            if(x_set != y_set){
                mst.add(edge);
                union(parent, x_set, y_set);
                index++;
            }
        }
        int p = 0;
        for(int j = 0; j < vertices; j++){
            if(j == parent[j])
                p++;
        }
        if(p > 1)
            return "-1";
        return printGraph(mst);
    }

    public void makeSet(int [] parent){
        for (int i = 0; i < vertices ; i++) 
            parent[i] = i;
    }

    public void insertEdge(ArrayList<Edge> allEdges, PriorityQueue<Edge> pq){
        for (int i = 0; i < allEdges.size() ; i++)
            pq.add(allEdges.get(i));
    }

    public int find(int [] parent, int vertex){
        if(parent[vertex] != vertex)
            return find(parent, parent[vertex]);;
        return vertex;
    }

    public void union(int [] parent, int x, int y){
        int x_set_parent = find(parent, x);
        int y_set_parent = find(parent, y);
        parent[y_set_parent] = x_set_parent;
    }

    public String printGraph(ArrayList<Edge> edgeList){
        String result = "MST Total Weight: ";
        int totalWeight = 0;
        for (int i = 0; i <edgeList.size() ; i++) {
            Edge edge = edgeList.get(i);
            totalWeight += edge.weight;
        }
        result += String.valueOf(totalWeight);
        return result;
    }

    public static void main(String[] args) {
        //Test
    }
}
