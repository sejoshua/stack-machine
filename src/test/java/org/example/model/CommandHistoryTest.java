package org.example.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Joshua Xing
 */
class CommandHistoryTest {

    @Test
    void undoCommandTest() {
        CommandHistory history = new CommandHistory();

        assertNull(history.undoCommand());

        CommandType nonImpactingType = CommandType.PRINT;
        history.recordCommand(nonImpactingType, List.of(), false);
        assertNull(history.undoCommand());

        CommandType impactingType = CommandType.ADD;
        history.recordCommand(impactingType, List.of(1.0, 2.0), true);
        CommandExecution execution = history.undoCommand();
        assertEquals(impactingType, execution.getType());
        assertEquals(List.of(1.0, 2.0), execution.getPoppedValues());
        assertTrue(execution.isShouldPop());

        assertNull(history.undoCommand());
    }
}