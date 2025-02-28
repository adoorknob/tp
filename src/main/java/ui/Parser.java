package ui;

import java.io.IOException;

public class Parser {

    public Parser() {
    }

    public String parse(String input) throws IOException {
        try {
            return (input.split(" ")[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
