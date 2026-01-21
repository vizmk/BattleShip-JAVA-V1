import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Board board = new Board();
        Logic logic = new Logic();

        // 1) stampa campo vuoto
        board.print();

        // 2) prompt esatto richiesto dai test
        System.out.println("Enter the coordinates of the ship:");

        // 3) leggi coordinate
        String a = scanner.next();
        String b = scanner.next();

        int[] start = logic.parseCoordinateSafe(a);
        int[] end   = logic.parseCoordinateSafe(b);

        // 4) controlli richiesti: fuori griglia o non allineate
        if (start == null || end == null ||
                logic.outOfBounds(start) || logic.outOfBounds(end) ||
                !logic.aligned(start, end)) {
            System.out.println("Error!");
            return;
        }

        // 5) stampa Length e Parts
        int len = logic.shipLength(start, end);
        System.out.println("Length: " + len);
        System.out.println("Parts: " + logic.partsBoat(start, end));
    }
}
