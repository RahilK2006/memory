import java.io.IOException;
import java.net.DatagramSocket;

public class MainServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket serverSocket = new DatagramSocket(1234);

        Clients listaClienti = Connessione.connessione(serverSocket);
    }
}