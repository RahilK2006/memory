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

            DatagramPacket pacchetto = new DatagramPacket(bufferRisposta, bufferRisposta.length); // creazione dei pacchetto che riceve la risposta

            serverSocket.receive(pacchetto); // richiesta bloccante che attende la recezione di una risposta

            String mess = new String(pacchetto.getData()).trim(); //toglie gli spazi null alla fine del messaggio
            System.out.println(mess); // prende il testo del messaggio dalla risposta e lo stamp a schermo

            if (mess.equals("connettiti")) {
                //individuo chi ha mandato il messaggio per salvarlo 
                InetAddress indirizzoClient = pacchetto.getAddress();
                lista.add(new Client(indirizzoClient, pacchetto.getPort()));
                i++;

                String messaggio = "ok"; //crea messaggio da mandare
                byte[] buffer = messaggio.getBytes(); //trasformare il messaggio in array di buffer
                DatagramPacket pacchettoRisposta = new DatagramPacket(buffer, buffer.length, indirizzoClient, pacchetto.getPort()); //creazione del pacchetto da mandare
                serverSocket.send(pacchettoRisposta); //invio del pacchetto;
            }
            // NB: la richiesta receive è bloccante
        }

        return lista;
    }
}