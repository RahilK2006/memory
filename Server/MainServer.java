import java.io.IOException;
import java.net.DatagramSocket;

public class MainServer {
    public static void main(String[] args) throws IOException {
        Matrice m = new Matrice();
        m.genera();
        m.visualizza();
        

        DatagramSocket serverSocket = new DatagramSocket(1234);

        Clients listaClienti = Connessione.connessione(serverSocket);
    }
}