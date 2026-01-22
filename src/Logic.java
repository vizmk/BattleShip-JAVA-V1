public class Logic {

    /**
     * Converts coordinates like "A1" or "J10" to [row, col].
     * Returns null if the format is invalid.
     */
    public int[] parseCoordinateSafe(String coord) {
        if (coord == null) return null;
        coord = coord.trim();
        if (coord.length() < 2 || coord.length() > 3) return null;

        char rowChar = coord.charAt(0);
        if (rowChar < 'A' || rowChar > 'J') return null;

        String numPart = coord.substring(1);
        int colNumber;
        try {
            colNumber = Integer.parseInt(numPart);
        } catch (NumberFormatException e) {
            return null;
        }

        int row = rowChar - 'A';
        int col = colNumber - 1;
        return new int[]{row, col};
    }

    public boolean outOfBounds(int[] pos) {
        int r = pos[0], c = pos[1];
        return r < 0 || r > 9 || c < 0 || c > 9;
    }

    public boolean aligned(int[] a, int[] b) {
        return a[0] == b[0] || a[1] == b[1];
    }

    public int shipLength(int[] a, int[] b) {
        if (a[0] == b[0]) { // horizontal
            return Math.abs(b[1] - a[1]) + 1;
        }
        // vertical
        return Math.abs(b[0] - a[0]) + 1;
    }
}
