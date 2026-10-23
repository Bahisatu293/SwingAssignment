package sl.edu.prog209;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Dimension;

/** Shared frame-configuration behaviour inherited by the application. */
public class BaseWindow {
    protected static final Dimension REQUIRED_WINDOW_SIZE = new Dimension(800, 800);

    /** Applies the window settings stated in the PROG209 assignment brief. */
    protected void configureFrame(JFrame frame) {
        frame.setTitle("Java Swing Assignment");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(REQUIRED_WINDOW_SIZE));
        frame.setMinimumSize(new Dimension(700, 700));
        frame.setLocationRelativeTo(null);
    }
}
