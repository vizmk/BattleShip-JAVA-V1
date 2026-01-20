import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Board board=new Board();

        board.print();
        //lettura coordinate
        System.out.println("Enter the coordinates of the ship:");
        String iCoord=scanner.next();
        String fCoord= scanner.next();
        Logic logic = new Logic();

        int[] start = logic.parseCoordinate(iCoord);
        int[] end   = logic.parseCoordinate(fCoord);

        if (logic.outOfBounds(start) || logic.outOfBounds(end)) {
            System.out.println("Error");
            return;
        }

        if (!logic.aligned(start, end)) {
            System.out.println("Error");
            return;
        }
        int length = logic.lengthBoat(start, end);
        System.out.println("Length: " + length);
        String parts = logic.partsBoat(start, end);
        System.out.println("Parts: " + parts);




    }
}
