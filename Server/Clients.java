import java.util.ArrayList;

public class Clients {
    ArrayList <Client> listaClients;

    public Clients() {
        this.listaClients = new ArrayList<Client>();
    }

    public void add(Client client){
        this.listaClients.add(client);
    }

    public int size(){
        return this.listaClients.size();
    }

    public Client get(int i)
    {
        return this.listaClients.get(i);
    }
}
