package seedu.duke;

import ui.Ui;
import instrument.InstrumentList;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private Ui ui;
    private InstrumentList instrumentList;

    public Duke() {
        ui = new Ui();
        instrumentList = new InstrumentList();
    }

    public void runDuke() {

        assert ui != null;

        ui.printStartMessage();

        while (true) {
            try {
                String userInput = ui.readUserInput();
                String command = ui.getCommand(userInput);
                String input = ui.getRemainingWords(userInput);

                assert userInput != null;
                assert command != null;
                assert input != null;
                
                switch (command) {
                case "help":
                    ui.printCommandList();
                    break;
                case "list":
                    ui.printInstrumentList(instrumentList.getList());
                    break;
                case "add":
                    instrumentList.addInstrument(ui.getNameModelYear(input));
                    ui.printInstrumentList(instrumentList.getList());
                    break;
                case "delete":
                    instrumentList.deleteInstrument(Integer.parseInt(input));
                    ui.printInstrumentList(instrumentList.getList());
                    break;
                case "reserve":
                    instrumentList.reserveInstrument(Integer.parseInt(input));
                    ui.printInstrumentList(instrumentList.getList());
                    break;
                case "return":
                    instrumentList.returnInstrument(Integer.parseInt(input));
                    ui.printInstrumentList(instrumentList.getList());
                    break;
                case "exit":
                    ui.printGoodbye();
                    return;
                default:
                    ui.printNoMatchingCommandError();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static void main(String[] args) {
        new Duke().runDuke();
    }
}
