package ui;

import java.io.IOException;

public class Parser {

    public Parser() {
    }

    public String[] separateNMY(String input) throws IOException {
        if (input == null || input.isEmpty()) {
            throw new IOException("Input is Empty");
        }
        String[] split = input.split("\\|");
        if (split.length != 3) {
            throw new IOException("Input instrument is invalid");
        }
        try {
            Integer.parseInt(split[2]);
            return split;
        } catch (NumberFormatException e) {
            throw new IOException("Input year is invalid");
        }
    }
}
