import java.util.HashMap;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Map;
import java.rmi.registry.Registry;
import java.util.List;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements Graph {
    int nextGraphId = 0;
    Map<String, GraphServer> M_GraphId;

    public static void print(String S){
        System.out.println(S);
    }

    protected Server() throws RemoteException {
        super();
        M_GraphId = new HashMap<String, GraphServer>();
    }

    public static void main(String[] args) {
        try {
            int port = Integer.parseInt(args[0]);
            Registry reg =  LocateRegistry.createRegistry(port);
            reg.rebind("regis",new Server());
            print("Server Ready!");
            print("------------------------------------------------------------------------");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String create_graph(String GraphId, int vertex) throws RemoteException {
        if (M_GraphId.containsKey(GraphId))return "Graph Id already exists , choose new one";
        M_GraphId.put(GraphId,new GraphServer(vertex));
        return "Graph created successfully!";
    }
    
    @Override
    public String addEgde(String GraphId, int nodeA, int nodeB, int weight) throws RemoteException {
        if (!M_GraphId.containsKey(GraphId))return "Graph Id Not Found";
        GraphServer graphServer = M_GraphId.get(GraphId);
        graphServer.addEgde(nodeA, nodeB, weight);
        return "Edge added successfully!";
    }

    @Override
    public String getMST(String GraphId) throws RemoteException {
        if (!M_GraphId.containsKey(GraphId)) return "Graph Id Not Found";
        GraphServer graphServer = M_GraphId.get(GraphId);
        return graphServer.getMST();
    }
}
