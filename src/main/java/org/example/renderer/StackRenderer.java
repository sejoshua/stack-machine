package org.example.renderer;

import java.util.Deque;

/**
 * @author Joshua Xing
 */
public interface StackRenderer {
    void renderStack(Deque<Double> data);

    void renderError(String error);

    void renderTopElement(Deque<Double> data);
}
