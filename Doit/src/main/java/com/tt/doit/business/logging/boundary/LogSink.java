package com.tt.doit.business.logging.boundary;

@FunctionalInterface
public interface LogSink {
    void log(String message);
}
