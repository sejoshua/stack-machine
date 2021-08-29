package org.example.machine;

import org.example.model.Command;
import org.example.model.CommandExecution;
import org.example.model.CommandHistory;
import org.example.model.CommandType;
import org.example.renderer.StackRenderer;
import org.example.shutdown.ShutdownManager;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Joshua Xing
 */
public class StackMachine {
    private final String NOT_ENOUGH_ELE_ERROR_TEMPLATE = "Not enough element in the stack. Cannot execute %s command";

    private final Deque<Double> data;
    private final StackRenderer renderer;
    private final CommandHistory history;
    private final ShutdownManager shutdownManager;

    public StackMachine(StackRenderer renderer, ShutdownManager shutdownManager) {
        this.renderer = renderer;
        this.shutdownManager = shutdownManager;
        this.data = new LinkedList<>();
        this.history = new CommandHistory();
    }

    public void onCommand(Command command) {
        boolean result = false;
        switch (command.getType()) {
            case PUSH:
                result = push(command);
                break;
            case POP:
                result = pop(command);
                break;
            case CLEAR:
                result = clear(command);
                break;
            case ADD:
                result = add(command);
                break;
            case MUL:
                result = mul(command);
                break;
            case NEG:
                result = neg(command);
                break;
            case INV:
                result = inv(command);
                break;
            case UNDO:
                result = undo();
                break;
            case PRINT:
                renderer.renderStack(data);
                break;
            case QUIT:
                shutdownManager.shutdown();
                break;
            default:
                renderer.renderError(String.format("Operation not supported yet: %s", command.getType().name()));
                break;
        }

        if (result) {
            renderer.renderTopElement(data);
        }
    }

    private boolean push(Command command) {
        data.addFirst(command.getValue());
        history.recordCommand(command.getType(), List.of(), true);
        return true;
    }

    private boolean pop(Command command) {
        Double popped = data.removeFirst();
        if (popped == null) {
            renderer.renderError("Stack is empty. Cannot execute POP command.");
            return false;
        } else {
            history.recordCommand(command.getType(), List.of(popped), false);
            return true;
        }
    }

    private boolean clear(Command command) {
        List<Double> popped = new ArrayList<>(data);
        data.clear();
        history.recordCommand(command.getType(), popped, false);
        return true;
    }

    private boolean add(Command command) {
        return biOperandOperation(command.getType(), Double::sum, "ADD");
    }

    private boolean mul(Command command) {
        return biOperandOperation(command.getType(), (d1, d2) -> d1 * d2, "MUL");
    }

    private boolean neg(Command command) {
        return singleOperandOperation(command.getType(), d -> -d, "NEG");
    }

    private boolean inv(Command command) {
        return singleOperandOperation(command.getType(), d -> 1.0 / d, "INV");
    }

    private boolean undo() {
        CommandExecution commandExecution = history.undoCommand();
        if (commandExecution.isShouldPop()) {
            data.pollFirst();
        }
        commandExecution.getPoppedValues().forEach(data::addFirst);
        return true;
    }

    private boolean singleOperandOperation(CommandType type, Function<Double, Double> function, String operation) {
        Double d = data.pollFirst();
        if (d == null) {
            renderer.renderError(String.format(NOT_ENOUGH_ELE_ERROR_TEMPLATE, operation));
            return false;
        }
        data.addFirst(function.apply(d));
        history.recordCommand(type, List.of(d), true);
        return true;
    }

    private boolean biOperandOperation(CommandType type, BiFunction<Double, Double, Double> biFunction, String operation) {
        Double d1 = data.pollFirst();
        if (d1 == null) {
            renderer.renderError(String.format(NOT_ENOUGH_ELE_ERROR_TEMPLATE, operation));
            return false;
        }
        Double d2 = data.pollFirst();
        if (d2 == null) {
            renderer.renderError(String.format(NOT_ENOUGH_ELE_ERROR_TEMPLATE, operation));
            // need to push d1 back
            data.addFirst(d1);
            return false;
        }
        data.addFirst(biFunction.apply(d1, d2));
        history.recordCommand(type, List.of(d1, d2), true);
        return true;
    }
}
