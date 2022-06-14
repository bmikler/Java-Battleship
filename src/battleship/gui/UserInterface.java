package battleship.gui;

import battleship.model.Coordinate;

import java.awt.print.Pageable;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserInterface {

    private final Scanner scanner = new Scanner(System.in);
    private final InputValidator validator = new InputValidator();

    public void getEnter() {
        scanner.nextLine();
    }

    public Coordinate getTargetCoordinate() {
        String input = scanner.nextLine();

        if (!validator.isCordinateValid(input)) {
            throw new IllegalArgumentException("Error! You entered the wrong coordinates! Try again:");
        }

        return getFieldFromInput(input);

    }

    public Coordinate[] getNewShipCoordinatesFromUser() {
        String input = scanner.nextLine();

        String[] coordinates = input.split(" ");

        if (!validator.isCordinateValid(coordinates[0]) || !validator.isCordinateValid(coordinates[1])) {
            throw new IllegalArgumentException("Wrong input!");
        }

        Coordinate x = getFieldFromInput(coordinates[0]);
        Coordinate y = getFieldFromInput(coordinates[1]);

        return new Coordinate[]{x,y};
    }


    private Coordinate getFieldFromInput(String input) {
        int x = input.charAt(0) - 65;
        int y = Integer.parseInt(input.substring(1)) - 1;
        return new Coordinate(x, y);

    }


}
