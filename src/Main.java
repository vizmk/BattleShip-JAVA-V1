import java.util.Scanner;

public class Main {

    private static final Ship[] FLEET = {
            new Ship("Aircraft Carrier", 5),
            new Ship("Battleship", 4),
            new Ship("Submarine", 3),
            new Ship("Cruiser", 3),
            new Ship("Destroyer", 2)
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Logic logic = new Logic();

        Board p1Board = new Board();
        Board p2Board = new Board();

        // --- Placement phase ---
        setupPlayer(scanner, logic, p1Board, "Player 1, place your ships on the game field");
        passMove(scanner);

        setupPlayer(scanner, logic, p2Board, "Player 2, place your ships to the game field");
        passMove(scanner);

        // --- Battle phase ---
        int currentPlayer = 1;

        while (true) {
            Board myBoard = (currentPlayer == 1) ? p1Board : p2Board;
            Board enemyBoard = (currentPlayer == 1) ? p2Board : p1Board;

            // Show enemy (fog) on top, my real field on bottom
            enemyBoard.printFog();
            System.out.println("---------------------");
            myBoard.printUncovered();
            System.out.println();

            System.out.println("Player " + currentPlayer + ", it's your turn:");
            System.out.println();

            int[] shot = readValidShot(scanner, logic);
            Board.ShotResult result = enemyBoard.shoot(shot[0], shot[1]);

            System.out.println();
            if (result == Board.ShotResult.LAST_SUNK) {
                System.out.println("You sank the last ship. You won. Congratulations!");
                break;
            } else if (result == Board.ShotResult.SUNK) {
                System.out.println("You sank a ship!");
            } else if (result == Board.ShotResult.HIT) {
                System.out.println("You hit a ship!");
            } else {
                System.out.println("You missed!");
            }

            passMove(scanner);
            currentPlayer = (currentPlayer == 1) ? 2 : 1;
        }
    }

    private static void setupPlayer(Scanner scanner, Logic logic, Board board, String header) {
        System.out.println(header);
        System.out.println();
        board.printUncovered();
        System.out.println();

        for (Ship ship : FLEET) {
            placeSingleShip(scanner, logic, board, ship);
        }
    }

    private static void placeSingleShip(Scanner scanner, Logic logic, Board board, Ship ship) {
        while (true) {
            System.out.println("Enter the coordinates of the " + ship.getName() + " (" + ship.getLength() + " cells):");
            System.out.println();

            String line = readNonEmptyLine(scanner);
            String[] parts = line.trim().split("\\s+");
            if (parts.length != 2) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                System.out.println();
                continue;
            }

            int[] start = logic.parseCoordinateSafe(parts[0]);
            int[] end = logic.parseCoordinateSafe(parts[1]);

            if (start == null || end == null || logic.outOfBounds(start) || logic.outOfBounds(end)) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                System.out.println();
                continue;
            }

            if (!logic.aligned(start, end)) {
                System.out.println("Error! Wrong ship location! Try again:");
                System.out.println();
                continue;
            }

            int len = logic.shipLength(start, end);
            if (len != ship.getLength()) {
                System.out.println("Error! Wrong length of the " + ship.getName() + "! Try again:");
                System.out.println();
                continue;
            }

            if (board.isTooCloseOrOverlaps(start, end)) {
                System.out.println("Error! You placed it too close to another one. Try again:");
                System.out.println();
                continue;
            }

            board.placeShip(start, end);
            board.printUncovered();
            System.out.println();
            break;
        }
    }

    private static int[] readValidShot(Scanner scanner, Logic logic) {
        while (true) {
            String token = readNonEmptyLine(scanner).trim(); // user enters a single coord like A1
            int[] pos = logic.parseCoordinateSafe(token);

            if (pos == null || logic.outOfBounds(pos)) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                System.out.println();
                continue;
            }

            return pos;
        }
    }

    private static void passMove(Scanner scanner) {
        System.out.println("Press Enter and pass the move to another player");
        readNonEmptyLineAllowEmpty(scanner); // user just presses Enter
        clearScreen();
    }

    // Small helpers to make Scanner behave (mixing nextLine and empty inputs is annoying)
    private static String readNonEmptyLine(Scanner scanner) {
        while (true) {
            String line = scanner.nextLine();
            if (!line.trim().isEmpty()) return line;
        }
    }

    private static void readNonEmptyLineAllowEmpty(Scanner scanner) {
        scanner.nextLine(); // accept even empty line
    }

    private static void clearScreen() {
        // Not "real" clear, but enough for console and for Hyperskill output style.
        for (int i = 0; i < 50; i++) System.out.println();
    }
}
