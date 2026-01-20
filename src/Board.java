public class Board {

    char[][] grid;

    // costruttore
    public Board() {
        grid = new char[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = '~';
            }
        }
    }

    // stampa del campo
    public void print() {

        // intestazione colonne
        System.out.print("  ");
        for (int i = 1; i <= 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // righe del campo
        for (int i = 0; i < 10; i++) {
            // lettera riga
            System.out.print((char) ('A' + i) + " ");

            // celle
            for (int j = 0; j < 10; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
