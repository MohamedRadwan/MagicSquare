package controller;

import model.MagicSquareModel;

/**
 * The type Magic square controller.
 *
 * @author Mohamed Radwan
 */
public class MagicSquareController {
    /**
     * A model object.
     */
    private MagicSquareModel model;

    /**
     * Instantiates a new Magic square controller.
     *
     * @param model the model
     */
    public MagicSquareController(MagicSquareModel model) {
        this.model = model;
    }

    /**
     * Places a number on the board.
     *
     * @param x     the x location
     * @param y     the y location
     * @param value the value to be placed
     */
    public void placeNumber(int x, int y, int value) {
        this.model.placeNumber(x, y, value);
    }

    /**
     * Resets the game.
     *
     * @return the magic square model
     */
    public MagicSquareModel reset() {
        return this.model = new MagicSquareModel();
    }
}
