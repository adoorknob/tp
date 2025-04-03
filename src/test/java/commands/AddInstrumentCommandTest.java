package commands;

import commands.instrument.AddInstrumentCommand;
import instrument.InstrumentList;
import instrument.Instrument;
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
        addInstrumentCommand = new AddInstrumentCommand("Guitar|Fender|2023", false);

        // Execute the command
        Instrument output = addInstrumentCommand.addInstrument(instrumentList, ui);
        instrumentList.addInstrument(output);

        // Verify that the instrument was added
        assertEquals(1, instrumentList.getList().size(), "Instrument list size should increase by 1.");
    }

    @Test
    void testExecutePrintsInstrumentList() {
        // Given an AddInstrumentCommand
        addInstrumentCommand = new AddInstrumentCommand("Piano|Yamaha|2022", false);

        // Execute the command
        Instrument instrument = addInstrumentCommand.addInstrument(instrumentList, ui);
        instrumentList.addInstrument(instrument);
        ui.printInstrumentList(instrumentList.getList());

        String output = outputStreamCaptor.toString().trim();

        assertTrue(output.contains("Piano"), "Printed list should contain the newly added instrument.");
    }

    @Test
    void testIsExitReturnsFalse() {
        addInstrumentCommand = new AddInstrumentCommand("add Violin|Stradivarius|1700", false);
        assertFalse(addInstrumentCommand.isExit(), "AddInstrumentCommand should not trigger exit.");
    }
}
