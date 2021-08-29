package org.example.shutdown.impl;

import org.example.shutdown.ShutdownManager;

/**
 * @author Joshua Xing
 */
public class SimpleShutdownManager implements ShutdownManager {
    public void shutdown() {
        System.exit(0);
    }
}
