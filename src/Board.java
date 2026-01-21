public class Board {

    private final char[][] grid;

    public Board() {
        grid = new char[10][10];
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                grid[r][c] = '~';
            }
        }
    }

    public void print() {
        System.out.print("  ");
        for (int i = 1; i <= 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int r = 0; r < 10; r++) {
            System.out.print((char) ('A' + r) + " ");
            for (int c = 0; c < 10; c++) {
                System.out.print(grid[r][c] + " ");
            }
            System.out.println();
        }
    }

    // Controlla sovrapposizione o adiacenza (anche diagonale)
    public boolean isTooCloseOrOverlaps(int[] start, int[] end) {
        int r1 = start[0], c1 = start[1];
        int r2 = end[0],   c2 = end[1];

        int rFrom = Math.min(r1, r2);
        int rTo   = Math.max(r1, r2);
        int cFrom = Math.min(c1, c2);
        int cTo   = Math.max(c1, c2);

        for (int r = rFrom; r <= rTo; r++) {
            for (int c = cFrom; c <= cTo; c++) {

                // intorno 3x3
                for (int rr = r - 1; rr <= r + 1; rr++) {
                    for (int cc = c - 1; cc <= c + 1; cc++) {
                        if (rr < 0 || rr > 9 || cc < 0 || cc > 9) continue;
                        if (grid[rr][cc] == 'O') return true;
                    }
                }
            }
        }
        return false;
    }

    public void placeShip(int[] start, int[] end) {
        int r1 = start[0], c1 = start[1];
        int r2 = end[0],   c2 = end[1];

        int rFrom = Math.min(r1, r2);
        int rTo   = Math.max(r1, r2);
        int cFrom = Math.min(c1, c2);
        int cTo   = Math.max(c1, c2);

        for (int r = rFrom; r <= rTo; r++) {
            for (int c = cFrom; c <= cTo; c++) {
                grid[r][c] = 'O';
            }
        }
    }
}
