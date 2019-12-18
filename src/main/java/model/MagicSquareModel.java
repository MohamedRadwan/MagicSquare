package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The type Magic square model.
 */
public class MagicSquareModel {

    /**
     * The constant DEFAULT_SIZE.
     */
    public static final int DEFAULT_SIZE = 3;
    /**
     * List of listeners to the model.
     */
    private List<MagicSquareListener> listeners;
    /**
     * A grid used to represent the board.
     */
    private int[][] grid;
    /**
     * Represents the game status
     */
    private Status status;
    /**
     * The number of turns a user have taken
     */
    private int numberOfTurns;

    /**
     * Instantiates a new Magic square model.
     */
    public MagicSquareModel() {
        this.status = Status.IN_GAME;
        this.listeners = new ArrayList<>();
        this.numberOfTurns = 0;
        this.grid = new int[DEFAULT_SIZE][DEFAULT_SIZE];
        for (int y = 0; y < DEFAULT_SIZE; y++) {
            for (int x = 0; x < DEFAULT_SIZE; x++) {
                grid[x][y] = 0;
            }
        }
    }

    /**
     * Place number boolean.
     *
     * @param x     the x
     * @param y     the y
     * @param value the value
     * @return the boolean
     */
    public boolean placeNumber(int x, int y, int value) {
        if (this.grid[x][y] == 0 && value > 0 && value <= DEFAULT_SIZE * DEFAULT_SIZE) {
            this.grid[x][y] = value;
            numberOfTurns++;
            updateStatus();
            notifyListeners(x, y, value);
            return true;
        }
        return false;
    }

    private void updateStatus() {
        // if the grid is full
        if (numberOfTurns == DEFAULT_SIZE * DEFAULT_SIZE) {
            Set<Integer> sum = new HashSet<>();
            // Check rows
            int rowSum = 0;
            int colSum = 0;
            int sumDiagonalLeft = 0;
            int sumDiagonalRight = 0;

            // check the sum of the rows and columns
            for (int i = 0; i < DEFAULT_SIZE; i++) {
                for (int j = 0; j < DEFAULT_SIZE; j++) {
                    rowSum += this.grid[i][j];
                    colSum += this.grid[j][i];
                }
                sum.add(colSum);
                sum.add(rowSum);
                colSum = 0;
                rowSum = 0;
            }
            // check the diagonal sum starting from the left [0][0]
            for (int i = 0; i < DEFAULT_SIZE; i++) {
                sumDiagonalLeft += grid[i][i];
            }
            sum.add(sumDiagonalLeft);

            // check the diagonal sum starting from the right side [DEFAULT_SIZE-1][0]
            for (int i = 0; i < DEFAULT_SIZE; i++) {
                // Since one side x is decreasing by 1 and y is increasing by 1. I have implemented the logic bellow.
                sumDiagonalRight += grid[DEFAULT_SIZE - 1 - i][i];
            }
            sum.add(sumDiagonalRight);

            if (sum.size() == 1) {
                status = Status.WON;
            } else {
                status = Status.LOST;
            }
        }

    }

    /**
     * Adds listeners.
     *
     * @param listener the listener to the model
     */
    public void addListeners(MagicSquareListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    /**
     * notifies all the listeners.
     *
     * @param x - location on the board
     * @param y - location on the board
     */
    private void notifyListeners(int x, int y, int value) {
        for (MagicSquareListener listener : listeners) {
            listener.handleEvent(new MagicSquareEvent(this, x, y, value, status));
        }
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public Status getStatus() {
        return status;
    }
}
