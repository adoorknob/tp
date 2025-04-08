package utils;

public class CasingStandardiser {
    public static String casingStandardise(String input) {
        if (input == null || input.length() == 0) {
            return input;
        }

        if (input.length() == 1) {
            return input.toUpperCase();
        } else {
            return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
        }

    }
}
