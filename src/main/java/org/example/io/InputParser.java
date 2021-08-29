package org.example.io;

import org.example.model.Command;

/**
 * @author Joshua Xing
 */
public interface InputParser {
    Command from(String input);
}
