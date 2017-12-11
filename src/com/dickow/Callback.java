package com.dickow;

@FunctionalInterface
public interface Callback {
    void call(Board board, int fitness);
}
