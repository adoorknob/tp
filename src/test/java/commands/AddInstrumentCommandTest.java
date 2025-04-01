package commands;

import instrument.InstrumentList;
import ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


class AddInstrumentCommandTest {
    private AddInstrumentCommand addInstrumentCommand;
    private InstrumentList instrumentList;
    private Ui ui;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        instrumentList = new InstrumentList();
        ui = new Ui();

        // Redirect System.out to capture output
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testExecuteAddsInstrumentToList() {
        // Given an AddInstrumentCommand with a valid instrument input
        addInstrumentCommand = new AddInstrumentCommand("Guitar|Fender|2023");

        // Execute the command
        addInstrumentCommand.addInstrument(instrumentList, ui);

        // Verify that the instrument was added
        assertEquals(1, instrumentList.getList().size(), "Instrument list size should increase by 1.");
    }

    @Test
    void testExecutePrintsInstrumentList() {
        // Given an AddInstrumentCommand
        addInstrumentCommand = new AddInstrumentCommand("Piano|Yamaha|2022");

        // Execute the command
        addInstrumentCommand.addInstrument(instrumentList, ui);

        // Capture output and check if the instrument list was printed
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("Piano"), "Printed list should contain the newly added instrument.");
    }

    @Test
    void testIsExitReturnsFalse() {
        addInstrumentCommand = new AddInstrumentCommand("add Violin|Stradivarius|1700");
        assertFalse(addInstrumentCommand.isExit(), "AddInstrumentCommand should not trigger exit.");
    }
}
