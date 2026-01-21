public class Logic {

    // "A1" -> [0,0], "J10" -> [9,9]; ritorna null se formato invalido
    public int[] parseCoordinateSafe(String coord) {
        if (coord == null || coord.length() < 2 || coord.length() > 3) return null;

        char rowChar = coord.charAt(0);
        if (rowChar < 'A' || rowChar > 'Z') return null;

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
        if (a[0] == b[0]) { // orizzontale
            return Math.abs(b[1] - a[1]) + 1;
        } else { // verticale
            return Math.abs(b[0] - a[0]) + 1;
        }
    }
    public String partsBoat(int[] start, int[] end) {
        StringBuilder parts = new StringBuilder();

        if (start[0] == end[0]) { // orizzontale
            int row = start[0];
            int from = Math.min(start[1], end[1]);
            int to = Math.max(start[1], end[1]);

            for (int c = from; c <= to; c++) {
                char letter = (char) ('A' + row);
                int number = c + 1;
                parts.append(letter).append(number).append(" ");
            }
        } else { // verticale
            int col = start[1];
            int from = Math.min(start[0], end[0]);
            int to = Math.max(start[0], end[0]);

            for (int r = from; r <= to; r++) {
                char letter = (char) ('A' + r);
                int number = col + 1;
                parts.append(letter).append(number).append(" ");
            }
        }

        return parts.toString().trim();
    }

}

