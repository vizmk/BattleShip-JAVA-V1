public class Board {

    public enum ShotResult {
        MISS,
        HIT,
        SUNK,
        LAST_SUNK
    }

    private final char[][] grid;

    // shipId[r][c] = 0 means water; (1..5) identifies the ship occupying the cell
    private final int[][] shipId;

    // Remaining un-hit cells per ship ID
    private final int[] remainingByShip;

    // Total remaining ship cells (win condition when it reaches 0)
    private int remainingTotal;

    // Ships are placed one by one, so we can assign incremental IDs
    private int nextShipId = 1;

    public Board() {
        grid = new char[10][10];
        shipId = new int[10][10];
        remainingByShip = new int[6]; // we use 1..5

        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                grid[r][c] = '~';
                shipId[r][c] = 0;
            }
        }
    }

    // Visible board (placement + own field during battle)
    public void printUncovered() {
        printInternal(false);
    }

    // Fog-of-war: hide ships ('O') as water ('~'), but keep X/M visible
    public void printFog() {
        printInternal(true);
    }

    private void printInternal(boolean fog) {
        System.out.print("  ");
        for (int i = 1; i <= 10; i++) System.out.print(i + " ");
        System.out.println();

        for (int r = 0; r < 10; r++) {
            System.out.print((char) ('A' + r) + " ");
            for (int c = 0; c < 10; c++) {
                char cell = grid[r][c];
                if (fog && cell == 'O') cell = '~';
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    /**
     * The project forbids ships that touch (including diagonals).
     * This checks the "halo" around the requested segment.
     */
    public boolean isTooCloseOrOverlaps(int[] start, int[] end) {
        int r1 = start[0], c1 = start[1];
        int r2 = end[0], c2 = end[1];

        int rFrom = Math.min(r1, r2);
        int rTo = Math.max(r1, r2);
        int cFrom = Math.min(c1, c2);
        int cTo = Math.max(c1, c2);

        for (int r = rFrom; r <= rTo; r++) {
            for (int c = cFrom; c <= cTo; c++) {
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

    /**
     * Places a ship and registers it for "sunk" detection.
     * Caller is responsible for validation (alignment, length, spacing).
     */
    public void placeShip(int[] start, int[] end) {
        int r1 = start[0], c1 = start[1];
        int r2 = end[0], c2 = end[1];

        int rFrom = Math.min(r1, r2);
        int rTo = Math.max(r1, r2);
        int cFrom = Math.min(c1, c2);
        int cTo = Math.max(c1, c2);

        int id = nextShipId++;
        int placed = 0;

        for (int r = rFrom; r <= rTo; r++) {
            for (int c = cFrom; c <= cTo; c++) {
                grid[r][c] = 'O';
                shipId[r][c] = id;
                placed++;
            }
        }

        remainingByShip[id] = placed;
        remainingTotal += placed;
    }

    /**
     * Shoots at a cell.
     * <p>
     * Important rule from the statement:
     * - If already shot, treat it the same (X => hit, M => miss).
     */
    public ShotResult shoot(int r, int c) {
        char cell = grid[r][c];

        // Repeated shots: do not change counters, only repeat the message logic.
        if (cell == 'X') return ShotResult.HIT;
        if (cell == 'M') return ShotResult.MISS;

        if (cell == 'O') {
            grid[r][c] = 'X';

            int id = shipId[r][c];
            if (id > 0) remainingByShip[id]--;

            remainingTotal--;

            // If that ship has no remaining cells, it is sunk.
            if (id > 0 && remainingByShip[id] == 0) {
                return (remainingTotal == 0) ? ShotResult.LAST_SUNK : ShotResult.SUNK;
            }

            return ShotResult.HIT;
        }

        // Water
        grid[r][c] = 'M';
        return ShotResult.MISS;
    }
}
