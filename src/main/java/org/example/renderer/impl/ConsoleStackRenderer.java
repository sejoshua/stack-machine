package org.example.renderer.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.renderer.StackRenderer;

import java.util.Deque;

/**
 * @author Joshua Xing
 */
@Slf4j
public class ConsoleStackRenderer implements StackRenderer {
    @Override
    public void renderStack(Deque<Double> data) {
        log.info("Data in the stack (top->bottom): {}.", data);
    }

    @Override
    public void renderError(String error) {
        log.error("Error occurred: [{}]. Ignore the operation.", error);
    }

    @Override
    public void renderTopElement(Deque<Double> data) {
        log.info("Top element in the stack: {}.", data.getFirst());
    }
}
