package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Joshua Xing
 */
@AllArgsConstructor
@Getter
public enum CommandType {
    PUSH(true),
    POP(true),
    CLEAR(true),
    ADD(true),
    MUL(true),
    NEG(true),
    INV(true),
    UNDO(false),
    PRINT(false),
    QUIT(false);

    private boolean impactingStack;
}
