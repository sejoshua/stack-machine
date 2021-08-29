package org.example.model;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Joshua Xing
 */
public class CommandHistory {
    private final Deque<CommandExecution> history;

    public CommandHistory() {
        this.history = new LinkedList<>();
    }

    public void recordCommand(CommandType type, List<Double> values, boolean shouldPop) {
        if (type.isImpactingStack()) {
            history.push(new CommandExecution(type, values, shouldPop));
        }
    }

    public CommandExecution undoCommand() {
        return history.isEmpty() ? null : history.pop();
    }
}
