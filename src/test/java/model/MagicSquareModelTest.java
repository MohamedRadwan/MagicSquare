package model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The type Magic square model test.
 *
 * @author Mohamed Radwan
 */
public class MagicSquareModelTest {


    /**
     * Place number.
     */
    @Test
    public void placeNumber() {
        MagicSquareModel model = new MagicSquareModel();
        // place the minimum value
        assertTrue(model.placeNumber(0, 0, 1));
        // place the maximum value
        assertTrue(model.placeNumber(0, 1, MagicSquareModel.DEFAULT_SIZE * MagicSquareModel.DEFAULT_SIZE));
        // place a value in the same position
        assertFalse(model.placeNumber(0, 0, 1));
        // place a value smaller than 1
        assertFalse(model.placeNumber(0, 2, 0));
        // place a value that is larger than n^2
        assertFalse(model.placeNumber(1, 2, MagicSquareModel.DEFAULT_SIZE * MagicSquareModel.DEFAULT_SIZE + 1));
    }


    /**
     * Gets status.
     */
    @Test
    public void getStatus() {
        MagicSquareModel model;
        // Check for puzzle completion
        // winner
        model = new MagicSquareModel();
        for (int y = 0; y < MagicSquareModel.DEFAULT_SIZE; y++) {
            for (int x = 0; x < MagicSquareModel.DEFAULT_SIZE; x++) {
                model.placeNumber(x, y, 1);
            }
        }
        assertEquals(Status.WON, model.getStatus());

        // Lose
        model = new MagicSquareModel();
        for (int y = 0; y < MagicSquareModel.DEFAULT_SIZE; y++) {
            for (int x = 0; x < MagicSquareModel.DEFAULT_SIZE; x++) {
                model.placeNumber(x, y, x + 1);
            }
        }
        assertEquals(Status.LOST, model.getStatus());
    }


}