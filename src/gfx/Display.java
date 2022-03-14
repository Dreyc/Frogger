package gfx;

import util.Handler;

import javax.swing.*;
import java.awt.*;

public class Display {
    private JFrame frame;
    private Canvas canvas;

    public Display(Handler handler) {
        JFrame frame = new JFrame("Frogger");
        this.frame = frame;
        frame.setSize(handler.getWidth()* handler.getPixelByCase(), handler.getHeight()* handler.getPixelByCase());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(handler.getWidth()* handler.getPixelByCase(), handler.getHeight()* handler.getPixelByCase()));
        canvas.setMaximumSize(new Dimension(handler.getWidth()* handler.getPixelByCase(), handler.getHeight()* handler.getPixelByCase()));
        canvas.setMinimumSize(new Dimension(handler.getWidth()* handler.getPixelByCase(), handler.getHeight()* handler.getPixelByCase()));
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();
    }



    /* GETTERS AND SETTERS */

    public JFrame getFrame(){
        return frame;
    }

    public Canvas getCanvas() {
        return canvas;
    }
}