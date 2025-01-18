import javax.swing.*;
import java.awt.*;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.io.IOException;

public class AttesaRispostaGUI {
    private JFrame frame;
    private JLabel messageLabel;
    private JLabel loadingLabel;
    private String nome;
    private DatagramSocket socket;

    public AttesaRispostaGUI(String nome, DatagramSocket socket) {
        this.nome = nome;
        this.socket = socket;

        frame = new JFrame("Attesa Risposta");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(200, 230, 255));
        JLabel welcomeLabel = new JLabel("Benvenuto, " + nome + "!");
        welcomeLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        welcomeLabel.setForeground(Color.BLUE);
        topPanel.add(welcomeLabel);
        frame.add(topPanel, BorderLayout.NORTH);


        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));

        messageLabel = new JLabel("Attendi la risposta del server...");
        messageLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        loadingLabel = new JLabel();
        loadingLabel.setIcon(new ImageIcon(getClass().getResource("/immagini/loading.gif"))); 
        
        loadingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(messageLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20))); 
        centerPanel.add(loadingLabel);
        frame.add(centerPanel, BorderLayout.CENTER);


        frame.setVisible(true);
    }

  
}
