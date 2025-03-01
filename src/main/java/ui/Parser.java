package ui;

import java.io.IOException;

public class Parser {

    public Parser() {
    }

    public String parse(String input) throws IOException {
        if (input == null || input.isEmpty()) {
            throw new IOException("Empty input");
        }
        return input.split(" ")[0];
    }
}
