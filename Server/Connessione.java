import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Connessione {

    public static Clients connessione(DatagramSocket serverSocket) throws IOException {
        final int MAXCLIENTS = 2;

        Clients lista = new Clients();

        int i = 0;

        while (i < MAXCLIENTS) {

            byte[] bufferRisposta = new byte[1024]; // creazione del buffer dove salvare il messaggio ricevuto

            DatagramPacket pacchettoRisposta = new DatagramPacket(bufferRisposta, bufferRisposta.length); // creazione dei pacchetto che riceve la risposta

            serverSocket.receive(pacchettoRisposta); // richiesta bloccante che attende la recezione di una risposta

            System.out.println(pacchettoRisposta.getData()); // prende il testo del messaggio dalla risposta e lo stamp a schermo

            if (pacchettoRisposta.getData().equals("connettiti")) {
                //individuo chi ha mandato il messaggio per salvarlo 
                InetAddress indirizzoClient = pacchettoRisposta.getAddress();
                DatagramSocket client = new DatagramSocket(pacchettoRisposta.getPort(), indirizzoClient);
                lista.add(client);
                i++;
                
            }
            // NB: la richiesta receive è bloccante
        }

        return lista;
    }
}