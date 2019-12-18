package view;

import controller.MagicSquareController;
import model.MagicSquareEvent;
import model.MagicSquareListener;
import model.MagicSquareModel;
import model.Status;

import javax.swing.*;
import java.awt.*;

/**
 * The view of the project as part of the mcv design pattern
 *
 * @author Mohamed Radwan
 */
public class MagicSquareView extends JFrame implements MagicSquareListener {
    /**
     * A model object for the application.
     */
    private MagicSquareModel model;
    /**
     * A controller object for the application.
     */
    private MagicSquareController controller;
    /**
     * The buttons used to represent the board.
     */
    private JButton[][] buttons;


    /**
     * Instantiates a new Magic square view.
     */
    public MagicSquareView() {
        this.setTitle("Magic Square");
        this.model = new MagicSquareModel();
        this.model.addListeners(this);
        this.controller = new MagicSquareController(model);

        this.setLayout(new GridLayout(MagicSquareModel.DEFAULT_SIZE, MagicSquareModel.DEFAULT_SIZE));
        this.buttons = new JButton[MagicSquareModel.DEFAULT_SIZE][MagicSquareModel.DEFAULT_SIZE];

        for (int y = 0; y < MagicSquareModel.DEFAULT_SIZE; y++) {
            for (int x = 0; x < MagicSquareModel.DEFAULT_SIZE; x++) {
                this.buttons[x][y] = new JButton();
                this.buttons[x][y].setOpaque(false);
                this.buttons[x][y].setContentAreaFilled(false);
                this.buttons[x][y].setFocusPainted(false);
                //Set the button size based on the size of the gird
                int size = 700 / MagicSquareModel.DEFAULT_SIZE;
                this.buttons[x][y].setPreferredSize(new Dimension(size, size));
                //Set the font size based on the font of the gird
                this.buttons[x][y].setFont(new Font("Arial", Font.PLAIN, size));
                add(buttons[x][y]);
                final int xCopy = x;
                final int yCopy = y;
                buttons[x][y].addActionListener(e -> {
                    String stringValue = JOptionPane.showInputDialog("Enter your number");
                    try {
                        int value = Integer.parseInt(stringValue);
                        if (value > 0 && value <= MagicSquareModel.DEFAULT_SIZE * MagicSquareModel.DEFAULT_SIZE) {
                            controller.placeNumber(xCopy, yCopy, value);
                        } else {
                            JOptionPane.showMessageDialog(this, "Please Make sure the value you input is between 1 and " + MagicSquareModel.DEFAULT_SIZE * MagicSquareModel.DEFAULT_SIZE, "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException error) {
                        JOptionPane.showMessageDialog(this, "A Problem has occurred. Please make sure the value you inputted was a number.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                });
            }
        }
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MagicSquareView::new);
    }

    public void handleEvent(MagicSquareEvent event) {
        // updates the text on the button.
        buttons[event.getX()][event.getY()].setText(String.valueOf(event.getValue()));
        buttons[event.getX()][event.getY()].setEnabled(false);

        if (event.getStatus() != Status.IN_GAME) {
            Object[] options = {"Play Again!", "Quit"};
            if (JOptionPane.showOptionDialog(this, (event.getStatus() == Status.LOST ? "You Lost !" : "You Won !"), "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]) == 1) {
                System.exit(0);
            } else {
                this.model = controller.reset();
                this.model.addListeners(this);
                configureBoardButtons(true);
            }
        }
    }

    /**
     * Enables or disables the buttons based on the passed condition.
     *
     * @param condition
     */
    private void configureBoardButtons(boolean condition) {
        for (int y = 0; y < MagicSquareModel.DEFAULT_SIZE; y++) {
            for (int x = 0; x < MagicSquareModel.DEFAULT_SIZE; x++) {
                this.buttons[x][y].setEnabled(condition);
                this.buttons[x][y].setText("");
            }
        }
    }

}
