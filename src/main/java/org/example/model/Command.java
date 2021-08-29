package org.example.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author Joshua Xing
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Command {
    private final CommandType type;
    private final Double value;
}
