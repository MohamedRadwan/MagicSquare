package model;

import java.util.EventObject;

/**
 * The type Project name event.
 *
 * @author Mohamed Radwan
 */
public class MagicSquareEvent extends EventObject {

    /**
     * X location on the board.
     */
    private int x;
    /**
     * Y location on the board.
     */
    private int y;
    /**
     * The value to be placed on the board.
     */
    private int value;
    /**
     * Current status of the game represented in an enum object.
     */
    private Status status;

    /**
     * Instantiates a new Magic square event.
     *
     * @param source the source
     * @param x      the x
     * @param y      the y
     * @param value  the value
     * @param status the status
     */
    public MagicSquareEvent(MagicSquareModel source, int x, int y, int value, Status status) {
        super(source);
        this.x = x;
        this.y = y;
        this.status = status;
        this.value = value;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }
}
