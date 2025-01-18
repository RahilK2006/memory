import java.util.ArrayList;
import java.util.Collections;

public class Matrice {

    private int righe;
    private int colonne;
    private int[][] matrix;

    public Matrice() {
        this.righe = 4;
        this.colonne = 5;
        this.matrix = new int[this.righe][this.colonne];
    }

    public void genera() {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        for (int i = 0; i < (this.righe * this.colonne); i++) {
            numbers.add(i / 2);
        }
        Collections.shuffle(numbers);

        int conta = 0;
        for (int r = 0; r < righe; r++) {
            for (int c = 0; c< colonne; c++) {
                matrix[r][c] = numbers.get(conta);
                conta++;
            }
            
        }
    }

    public void visualizza() {
        for (int[] riga : matrix) {
            for (int colonna : riga) {
                System.out.print(colonna + " ");
            }
            System.out.println();
        }
    }
    
}
