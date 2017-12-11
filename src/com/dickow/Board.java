package com.dickow;

import java.util.Arrays;

public class Board {
    private boolean[][] values;

    /**
     * Instantiates the board with the specified size
     * The dimensions of the board is width * height
     *
     * @param height The height of the board
     * @param width  The width of the board
     */
    public Board(int height, int width) {
        this.values = new boolean[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.values[i][j] = false;
            }
        }
    }

    /**
     * Private constructor used when copying the underlying values into a new Board reference.
     *
     * @param values
     */
    private Board(boolean[][] values) {
        this.values = values;
    }

    /**
     * Get the value of the specified index on the board
     *
     * @param x
     * @param y
     * @return
     */
    public boolean get(int x, int y) {
        return this.values[x][y];
    }

    /**
     * Sets the index in the board to the supplied value
     *
     * @param x
     * @param y
     * @param value
     */
    public void set(int x, int y, boolean value) {
        this.values[x][y] = value;
    }

    /**
     * Calculates the fitness value of the current positions on the board
     *
     * @return @int value representing the current fitness of the board
     */
    public int fitness() {
        int fitness = 0;
        for (int i = 0; i < this.getWidth(); i++) {
            for (int j = 0; j < this.getHeight(); j++) {
                if (this.values[i][j]) {
                    if (this.hasCollision(i, j)) {
                        fitness -= this.getWidth() * this.getHeight();
                    } else {
                        fitness++;
                    }
                }
            }
        }

        return fitness;
    }

    private boolean hasCollision(int x, int y) {
        // Check from left to right
        for (int i = 0; i < this.getWidth(); i++) {
            if (i != x && this.values[i][y]) {
                return true;
            }
        }

        // Check from top to bottom
        for (int i = 0; i < this.getHeight(); i++) {
            if (i != y && this.values[x][i]) {
                return true;
            }
        }

        // Check in the diagonal direction
        // Calculate the start diagonal
        int xIter = x;
        int yIter = y;
        while (xIter > 0 && yIter > 0) {
            xIter--;
            yIter--;
        }
        while (xIter < this.getWidth() && yIter < this.getHeight()) {
            if (xIter != x && yIter != y && this.values[xIter][yIter]) {
                return true;
            }
            xIter++;
            yIter++;
        }


        // Check in other diagonal direction
        // Calculate the start diagonal
        xIter = x;
        yIter = y;
        while (xIter > 0 && yIter < this.getHeight()) {
            xIter--;
            yIter++;
        }
        while (xIter < this.getWidth() && yIter > 0 && yIter < this.getHeight()) {
            if (xIter != x && yIter != y && this.values[xIter][yIter]) {
                return true;
            }
            xIter++;
            yIter--;
        }

        return false;
    }

    /**
     * Gets the height of the board
     *
     * @return
     */
    public int getHeight() {
        return this.values[0].length;
    }

    /**
     * Gets the width of the board
     *
     * @return
     */
    public int getWidth() {
        return this.values.length;
    }

    /**
     * Creates a deep copy of the current board using the Arrays.copyOf method
     *
     * @return
     */
    public Board copy() {
        boolean[][] copy = new boolean[this.getWidth()][this.getHeight()];
        for (int i = 0; i < this.getWidth(); i++) {
            copy[i] = Arrays.copyOf(this.values[i], this.values[i].length);
        }

        return new Board(copy);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < this.getWidth(); i++) {
            for (int j = 0; j < this.getHeight(); j++) {
                string.append((this.values[i][j] ? "X" : "O"));
            }
            string.append(System.lineSeparator());
        }

        return string.toString();
    }
}
