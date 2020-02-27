// @Giridhari Lal Gupta #2018201019
import java.util.Scanner;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;


public class Client {

    public static void main(String[] args) throws RemoteException {
        try{
            int port = Integer.parseInt(args[1]);
            Registry reg = LocateRegistry.getRegistry(args[0], port);
            Graph graph = (Graph) reg.lookup("regis");
            print("Create new graph or Add edges in old graphs or Check MST's Total weight of Graphs:");
            while(true){
                Scanner scanner = new Scanner(System.in);
                while(scanner.hasNextLine()){
                    String query = scanner.nextLine();
                    String[] query_parts = query.split(" ");
                    if(query_parts.length == 0)
                        break;
                    if(query_parts[0].equals("add_graph")){
                        if(query_parts.length < 3){
                            print("Invalid Arguments! Number of parameters should be 3");
                            continue;
                        }
                        print(graph.create_graph(query_parts[1], Integer.parseInt(query_parts[2])));
                    }
                    else if(query_parts[0].equals("add_edge")){
                        if(query_parts.length < 5){
                            print("Invalid Arguments! Number of parameters should be 5");
                            continue;
                        }
                        
                        int u = Integer.parseInt(query_parts[2]);
                        int v = Integer.parseInt(query_parts[3]);
                        int w = Integer.parseInt(query_parts[4]);
                        print(graph.addEgde(query_parts[1], u, v, w));
                    }
                    else if(query_parts[0].equals("get_mst")){
                        if(query_parts.length < 2){
                            print("Invalid Arguments! Number of parameters should be 2");
                            continue;
                        }
                        print(graph.getMST(query_parts[1]));
                    }
                    else if(query_parts[0].equals("exit") || query_parts[0].equals("quit")){
                        break;
                    }
                    else {
                        print("No Command Found! Please enter any valid Command");
                    }
                }
                print("----------------------------------------------------------------------------------------------");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void print(String S){
        System.out.println(S);
    }
}
