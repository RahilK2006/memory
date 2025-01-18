import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Connessione {

    public static void connessioneServer(String name, String serverIP, int port) throws Exception 
    {
        DatagramSocket clientSocket = new DatagramSocket();
        try {
            InetAddress serverAddress = InetAddress.getByName(serverIP);

            String message = "connettiti";
            byte[] buffer = message.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, port);
            clientSocket.send(packet);

            byte[] responseBuffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
            clientSocket.receive(responsePacket);
            String mess = new String(responsePacket.getData()).trim();
            System.out.println(mess);

        } catch (Exception ex) {
            clientSocket.close();
            throw new Exception("Errore durante la connessione: " + ex.getMessage());
        }
    }
}