package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Joshua Xing
 */
@Data
@AllArgsConstructor
public class CommandExecution {
    private CommandType type;
    private List<Double> poppedValues;
    private boolean shouldPop;
}
