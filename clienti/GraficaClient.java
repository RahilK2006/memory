import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramSocket;
import java.net.Socket;

public class GraficaClient {
    private JTextField textboxName;
    private JTextField textboxIP;
    private JTextField textboPort;
    private JButton connessioneBotton;

    public GraficaClient() {
        // Creazione del frame principale
        JFrame frame = new JFrame("MEMORY");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());


        
        // Pannello superiore con sfondo e titolo
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(255, 223, 186)); // Colore giallo chiaro
        JLabel titleLabel = new JLabel("BENVENUTO NEL MEMORY!");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        titleLabel.setForeground(Color.RED);
        titlePanel.add(titleLabel);
        frame.add(titlePanel, BorderLayout.NORTH);


        JPanel inputPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/immagini/Sfondo2.png"));
                Image img = backgroundImage.getImage();

                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        inputPanel.setLayout(new GridLayout(3, 3, 50, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        inputPanel.add(new JLabel());
        inputPanel.add(new JLabel("Nome giocatore:"));

        textboxName = new JTextField();
        inputPanel.add(textboxName);


        inputPanel.add(new JLabel());

        inputPanel.add(new JLabel("IP del server:"));
        textboxIP = new JTextField("127.0.0.1");
        inputPanel.add(textboxIP);

        inputPanel.add(new JLabel());

        inputPanel.add(new JLabel("Porta:"));
        textboPort = new JTextField("1234");
        inputPanel.add(textboPort);

        frame.add(inputPanel, BorderLayout.CENTER);


        

        // Bottone per connettersi
        connessioneBotton = new JButton("Connetti al Server");
        connessioneBotton.setFont(new Font("Verdana", Font.BOLD, 14));
        connessioneBotton.setBackground(new Color(144, 238, 144));
        connessioneBotton.setForeground(Color.BLUE);
        connessioneBotton.setEnabled(false);
        connessioneBotton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectToServer(frame);
            }
        });
        frame.add(connessioneBotton, BorderLayout.SOUTH);

        // Aggiunta del DocumentListener per abilitare/disabilitare il bottone
        DocumentListener fieldListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validateFields();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateFields();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateFields();
            }
        };

        textboxName.getDocument().addDocumentListener(fieldListener);
        textboxIP.getDocument().addDocumentListener(fieldListener);
        textboPort.getDocument().addDocumentListener(fieldListener);

        frame.setVisible(true);
    }

    // Verifica la validità dei campi e abilita/disabilita il bottone di connessione
    private void validateFields() {
        String name = textboxName.getText().trim();
        String ip = textboxIP.getText().trim();
        String portText = textboPort.getText().trim();

        // Verifica che tutti i campi siano riempiti e validi
        boolean isNameValid = !name.isEmpty();
        boolean isIpValid = ip.matches("\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b"); // IP v4 regex
        boolean isPortValid;
        try {
            int port = Integer.parseInt(portText);
            isPortValid = port > 0 && port <= 65535;
        } catch (NumberFormatException e) {
            isPortValid = false;
        }

        connessioneBotton.setEnabled(isNameValid && isIpValid && isPortValid);
    }

    // Metodo per connettersi al server
    private void connectToServer(JFrame frame) {
        try {
            int port = Integer.parseInt(textboPort.getText());
            // Simulazione della connessione al server
            DatagramSocket socket= Connessione.connessioneServer(textboxName.getText(), textboxIP.getText(), port);


            JOptionPane.showMessageDialog(frame, "Connessione riuscita! Proseguendo alla schermata successiva...");
            AttesaRispostaGUI attesaRispostaGUI=new AttesaRispostaGUI(textboxName.getText(),socket);

            frame.dispose();


                        

        }  catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Connessione non riuscita: " + ex.getMessage());
        }
    }

}
