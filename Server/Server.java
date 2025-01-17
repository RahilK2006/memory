import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(2); // Pool di thread per gestire i client

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server in ascolto sulla porta " + PORT + "...");
            MemoryGameField n = new MemoryGameField();
            while (true) {
                // Aspetta una nuova connessione
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connessione stabilita con un nuovo client.");

                // Assegna la gestione del client a un nuovo thread
                threadPool.execute(new ClientHandler(clientSocket));
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally { // Se ci dovesse essere un errore o un qualunque imprevisto questo pezzo di codice sarà sempre eseguito
            threadPool.shutdown(); //Garantisce che tutti i thread vengano terminati in modo controllato, evitando sprechi.
        }
    }

    // Classe per gestire la comunicazione con ogni client
    private static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
            ) {
                out.println("Benvenuto sul server!");
                String receivedMessage;

                // Gestione della comunicazione con il client
                while ((receivedMessage = in.readLine()) != null) {
                    System.out.println("Client dice: " + receivedMessage);
                    out.println("Echo: " + receivedMessage); // Risponde al client
                }
            } catch (IOException e) {
                System.err.println("Errore di comunicazione con il client: " + e.getMessage());
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    System.err.println("Errore durante la chiusura della connessione: " + e.getMessage());
                }
                System.out.println("Connessione chiusa con il client.");
            }
        }
    }
}