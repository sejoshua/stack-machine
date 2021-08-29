package org.example;

import org.example.io.InputParser;
import org.example.io.impl.DefaultInputParser;
import org.example.machine.StackMachine;
import org.example.model.Command;
import org.example.renderer.StackRenderer;
import org.example.renderer.impl.ConsoleStackRenderer;
import org.example.shutdown.ShutdownManager;
import org.example.shutdown.impl.SimpleShutdownManager;

import java.util.Scanner;

/**
 * @author Joshua Xing
 */
public class Application {
    public static void main(String[] args) {
        StackRenderer renderer = new ConsoleStackRenderer();
        ShutdownManager manager = new SimpleShutdownManager();
        StackMachine machine = new StackMachine(renderer, manager);
        InputParser parser = new DefaultInputParser();

        Scanner scanner = new Scanner(System.in);
        printWelcome();
        printUsage();
        while (true) {
            System.out.print("\nPlease input your instruction: ");
            Command command = parser.from(scanner.nextLine());
            if (command == null) {
                printUsage();
            } else {
                machine.onCommand(command);
            }
        }
    }

    private static void printWelcome() {
        System.out.println("****************************************");
        System.out.println("*     Welcome to the Stack Machine     *");
        System.out.println("****************************************");
    }

    private static void printUsage() {
        System.out.println("Usage: COMMAND <operand>");
        System.out.println();
        System.out.println("\"COMMAND\" options include:");
        System.out.println("\tPUSH <xyx> or <xyz>:");
        System.out.println("\t       where <xyz> is a valid decimal number.");
        System.out.println("\t       Pushes the numeric value <xyz> to the top of the stack.");
        System.out.println("\t  POP: Removes the top element from the stack.");
        System.out.println("\tCLEAR: Removes all elements from the stack.");
        System.out.println("\t  ADD: Adds the top 2 elements on the stack and pushes the result back to the stack.");
        System.out.println("\t  MUL: Multiplies the top 2 elements on the stack and pushes the result back to the stack.");
        System.out.println("\t  NEG: Negates the top element on the stack and pushes the result back to the stack.");
        System.out.println("\t  INV: Inverts the top element on the stack and pushes the result back to the stack.");
        System.out.println("\t UNDO: The last instruction is undone leaving the stack in the same state as before that instruction.");
        System.out.println("\tPRINT: Prints all elements that are currently on the stack.");
        System.out.println("\t QUIT: Exits the program.");
    }
}
