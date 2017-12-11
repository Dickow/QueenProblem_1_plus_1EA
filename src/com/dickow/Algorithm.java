package com.dickow;

import java.util.Random;

public class Algorithm {
    private int maxEpoch;
    private int currentEpoch;
    private Board result;
    private Random rand = new Random();

    public Algorithm(Board initialBoard, int maxEpoch) {
        result = initialBoard;
        this.maxEpoch = maxEpoch;
        this.currentEpoch = 0;
    }

    public Algorithm(int height, int width, int maxEpoch) {
        // Initialise the board with the specified dimensions
        this.result = new Board(height, width);

        // Initialise the board uniformly at random
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                boolean value = rand.nextInt(1) == 1;
                this.result.set(i, j, value);
            }
        }

        this.maxEpoch = maxEpoch;
        this.currentEpoch = 0;
    }

    public Board compute(Callback callback) {
        // First calculate the fitness of the start board
        int currentFitness = this.result.fitness();

        // Run the 1+1 EA loop until the max epoch have been reached, or if the maximal fitness have been reached.
        while (this.currentEpoch <= maxEpoch) {
            Board newValue = this.result.copy();
            for (int i = 0; i < newValue.getWidth(); i++) {
                for (int j = 0; j < newValue.getHeight(); j++) {
                    // Flip the the bit with probability 1/(width+height). Eg if the value is zero in a random from zero to width+height
                    boolean flip = rand.nextInt(newValue.getWidth() + newValue.getHeight()) == 0;
                    if (flip) {
                        newValue.set(i, j, !newValue.get(i, j));
                    }
                }
            }

            // Check if the new fitness is better
            int newFitness = newValue.fitness();
            if (newFitness > currentFitness) {
                currentFitness = newFitness;
                this.result = newValue;
                callback.call(this.result, currentFitness);
            }

            this.currentEpoch++;
        }

        return this.result;
    }
}
