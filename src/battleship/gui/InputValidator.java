package battleship.gui;

public class InputValidator {


    public boolean isCordinateValid(String input) {
        return isLengthValid(input) && isNumberFormatValid(input) && isLetterValid(input) && isNumberValid(input);
    }

    private boolean isLengthValid(String input) {
        return input.length() >= 2 && input.length() <= 3;
    }

    private boolean isLetterValid(String input) {
        return input.charAt(0) >= 'A' && input.charAt(0) <= 'J';
    }

    private boolean isNumberFormatValid(String input) {
        return input.substring(1).chars().allMatch(Character::isDigit);
    }

    private boolean isNumberValid(String input) {

        int number = Integer.parseInt(input.substring(1));

        return number >= 1 && number <= 10;
    }

}
