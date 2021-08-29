package org.example.io.impl;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.example.io.InputParser;
import org.example.model.Command;
import org.example.model.CommandType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Joshua Xing
 */
@AllArgsConstructor
public class DefaultInputParser implements InputParser {
    private static final String OPERATOR_PATTERN_STR = "[A-Z]{3,}";
    private static final String DOUBLE_PATTERN_STR = "(-?)(0|[1-9][0-9]*)(\\.[0-9]+)?";
    private static final Pattern OPERATOR_PATTERN = Pattern.compile(String.format("^(%s)$", OPERATOR_PATTERN_STR));
    private static final Pattern OPERAND_PATTERN = Pattern.compile(String.format("^(%s)$", DOUBLE_PATTERN_STR));
    private static final Pattern OPERATOR_AND_OPERAND_PATTERN =
            Pattern.compile(String.format("^(%s) (%s)$", OPERATOR_PATTERN_STR, DOUBLE_PATTERN_STR));


    @Override
    public Command from(String input) {
        if (StringUtils.isEmpty(input)) {
            return null;
        }
        Matcher operatorMatcher = OPERATOR_PATTERN.matcher(input);
        if (operatorMatcher.matches()) {
            try {
                return new Command(CommandType.valueOf(operatorMatcher.group()), null);
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
        Matcher operandMatcher = OPERAND_PATTERN.matcher(input);
        if (operandMatcher.matches()) {
            CommandType type = CommandType.PUSH;
            Double value = Double.parseDouble(operandMatcher.group());
            return new Command(type, value);
        }
        Matcher operatorAndOperandMatcher = OPERATOR_AND_OPERAND_PATTERN.matcher(input);
        if (operatorAndOperandMatcher.matches()) {
            try {
                CommandType type = CommandType.valueOf(operatorAndOperandMatcher.group(1));
                Double value = Double.parseDouble(operatorAndOperandMatcher.group(2));
                return new Command(type, value);
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
        return null;
    }
}
