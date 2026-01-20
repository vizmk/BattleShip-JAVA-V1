public class Logic {
    // Converte "A1" -> {0,0}, "J10" -> {9,9}
    public int[] parseCoordinate(String coord) {

        char rowChar = coord.charAt(0);          // 'A'..'J'
        int row = rowChar - 'A';                 // 0..9

        int colNumber = Integer.parseInt(coord.substring(1)); // 1..10
        int col = colNumber - 1;                 // 0..9

        return new int[]{row, col};
    }

    // True se fuori griglia
    public boolean outOfBounds(int[] pos) {
        int r = pos[0];
        int c = pos[1];
        return r < 0 || r > 9 || c < 0 || c > 9;
    }

    // True se allineate (stessa riga o stessa colonna)
    public boolean aligned(int[] start, int[] end) {
        return start[0] == end[0] || start[1] == end[1];


    }
    public int lengthBoat(int[] start, int[] end) {

        if (start[0] == end[0]) { // orizzontale
            return Math.abs(end[1] - start[1]) + 1;
        } else { // verticale
            return Math.abs(end[0] - start[0]) + 1;
        }
    }

    //calcolo parti
    public String partsBoat(int[] start, int[] end) {
        StringBuilder parts = new StringBuilder();

        if (start[0] == end[0]) { // orizzontale
            int row = start[0];
            int from = Math.min(start[1], end[1]);
            int to   = Math.max(start[1], end[1]);

            for (int c = from; c <= to; c++) {
                char letter = (char) ('A' + row);
                int number = c + 1;
                parts.append(letter).append(number).append(" ");
            }
        } else { // verticale
            int col = start[1];
            int from = Math.min(start[0], end[0]);
            int to   = Math.max(start[0], end[0]);

            for (int r = from; r <= to; r++) {
                char letter = (char) ('A' + r);
                int number = col + 1;
                parts.append(letter).append(number).append(" ");
            }
        }

        return parts.toString().trim();
    }




}
