import java.rmi.RemoteException;
import java.rmi.Remote;

public interface Graph extends Remote {
    String create_graph(String GraphId, int vertex) throws RemoteException;
    String getMST(String GraphId) throws RemoteException;
    String addEgde(String GraphId, int nodeA, int nodeB, int weight) throws RemoteException;
}
