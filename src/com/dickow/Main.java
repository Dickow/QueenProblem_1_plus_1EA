package com.dickow;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Algorithm algo = new Algorithm(12, 12, 1000000);
        Board bestSolution = algo.compute((board, fitness) -> {
            System.out.println(String.format("Fitness: %d", fitness));
            System.out.println(board.toString());
        });
        System.out.println("Best solution found after max epoch reached");
        System.out.println(String.format("Fitness: %d", bestSolution.fitness()));
        System.out.println(bestSolution.toString());
        input.next();
    }
}
