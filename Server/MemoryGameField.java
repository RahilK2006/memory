import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class MemoryGameField extends JFrame {

    private static int punteggioGiocatore1 = 0; 
    private static int punteggioGiocatore2 = 0;

    public MemoryGameField() {
        // Imposta il titolo della finestra
        super("Memory Multiplayer");

        // Dimensioni della finestra
        setSize(getSize());
        // Chiude il programma quando si chiude la finestra
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crea il pannello principale con uno sfondo
        BackgroundPanel panel = new BackgroundPanel("Immagini/Sfondo.jpg");
        panel.setLayout(new GridLayout(4, 4, 100, 50)); // Griglia 4x4 con spaziatura da 50px

        // Aggiungi un padding esterno al pannello
        panel.setBorder(new EmptyBorder(70, 20, 20, 20)); // Padding (top, left, bottom, right)

        // Etichetta per il punteggio
        JLabel scoreLabel1 = new JLabel("Giocatore 1: " + punteggioGiocatore1, JLabel.LEFT);
        JLabel scoreLabel2 = new JLabel("Giocatore 2: " + punteggioGiocatore2,JLabel.LEFT);

        // Imposta il font e il colore delle etichette
        scoreLabel1.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel2.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel1.setForeground(Color.BLACK);
        scoreLabel2.setForeground(Color.BLACK);

        // Crea un pannello per i punteggi (in alto a sinistra)
        BackgroundPanel scorePanel = new BackgroundPanel("Immagini/Sfondo2.png");
        scorePanel.setLayout(new GridLayout(2, 1));  // Due righe per i punteggi
        scorePanel.add(scoreLabel1);
        scorePanel.add(scoreLabel2);
        
        // Aggiungi il pannello dei punteggi alla parte superiore del pannello principale
        add(scorePanel, BorderLayout.NORTH);

        // Aggiungi i pulsanti (rappresentano le carte)
        for (int i = 0; i < 16; i++) {
            JButton button = new JButton();

            // Retro della carta (immagine visibile inizialmente)
            ImageIcon backIcon = new ImageIcon("Immagini/Retro.png");

            // Imposta il retro come icona iniziale
            button.setIcon(backIcon);

            panel.add(button);
        }

        // Aggiungi il pannello alla finestra
        add(panel);

        // Rendi la finestra visibile
        setVisible(true);
    }

    // Pannello personalizzato per disegnare lo sfondo
    private static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                backgroundImage = new ImageIcon(imagePath).getImage();
            } catch (Exception e) {
                System.err.println("Immagine non trovata: " + imagePath);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public static void main(String[] args) {
        new MemoryGameField();
    }
}