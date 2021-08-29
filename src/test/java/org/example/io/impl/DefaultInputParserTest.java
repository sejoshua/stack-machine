package org.example.io.impl;

import org.example.model.Command;
import org.example.model.CommandType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultInputParserTest {

    @Test
    void fromTest() {
        DefaultInputParser parser = new DefaultInputParser();

//        assertNull(parser.from(null));
//        assertNull(parser.from(""));
//        assertNull(parser.from("123UNKNOWN"));
//        assertNull(parser.from("UNKNOWN"));
//        assertEquals(new Command(CommandType.ADD, null), parser.from("ADD"));
//        assertEquals(new Command(CommandType.PUSH, 1.0), parser.from("1"));
        assertEquals(new Command(CommandType.PUSH, 0.12), parser.from("0.12"));
//        assertEquals(new Command(CommandType.PUSH, -12.5), parser.from("PUSH -12.5"));
    }
}