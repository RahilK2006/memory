import java.net.DatagramSocket;
import java.util.ArrayList;

public class Clients {
    ArrayList <DatagramSocket> listaClients;

    public Clients() {
        this.listaClients = new ArrayList<DatagramSocket>();
    }

    public void add(DatagramSocket client){
        this.listaClients.add(client);
    }

    public int size(){
        return this.listaClients.size();
    }

    
}
