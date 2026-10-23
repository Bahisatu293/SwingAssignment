package sl.edu.prog209;

import javax.swing.SwingUtilities;
import java.awt.Color;
import java.util.concurrent.atomic.AtomicReference;

/** Lightweight GUI smoke test. Run it in a graphical session (xvfb-run works on Linux). */
public class SwingAssignmentSmokeTest {
    public static void main(String[] args) throws Exception {
        AtomicReference<SwingAssignment> holder = new AtomicReference<>();
        SwingUtilities.invokeAndWait(() -> holder.set(new SwingAssignment()));
        SwingAssignment app = holder.get();
        try {
            require(app.getFrame().getTitle().equals("Java Swing Assignment"), "window title");
            require(app.getFrame().getWidth() == 800 && app.getFrame().getHeight() == 800, "800x800 size");
            require(app.getFrame().getDefaultCloseOperation() == javax.swing.WindowConstants.EXIT_ON_CLOSE, "close operation");
            require(app.getPanel() != null, "JPanel exists");
            require(app.getMessageLabel().getText().contains("Isatu Bah"), "initial welcome label contains student name");
            require(app.hasLoadedIconResource(), "custom icon resource available");
            SwingUtilities.invokeAndWait(() -> app.applyTextChange("Smoke test message"));
            require(app.getMessageLabel().getText().contains("Smoke test message"), "text update");
            boolean initialMode = app.isLightBackground();
            SwingUtilities.invokeAndWait(app::toggleBackground);
            require(app.isLightBackground() != initialMode, "background toggled");
            SwingUtilities.invokeAndWait(app::toggleBackground);
            require(app.isLightBackground() == initialMode, "background toggled back");
            Color testAccent = new Color(237, 75, 155);
            SwingUtilities.invokeAndWait(() -> app.applyAccentColor(testAccent));
            require(app.getAccentColor().equals(testAccent), "accent colour updated");
            System.out.println("PASS: JFrame title = Java Swing Assignment");
            System.out.println("PASS: JFrame dimensions = 800 x 800");
            System.out.println("PASS: close operation = EXIT_ON_CLOSE");
            System.out.println("PASS: JPanel and JLabel initialized");
            System.out.println("PASS: welcome message contains Isatu Bah");
            System.out.println("PASS: packaged IB ImageIcon resource detected");
            System.out.println("PASS: programmatic text change");
            System.out.println("PASS: background state toggles in both directions");
            System.out.println("PASS: accent colour updates");
        } finally {
            SwingUtilities.invokeAndWait(() -> app.getFrame().dispose());
        }
    }
    private static void require(boolean condition, String label) {
        if (!condition) throw new AssertionError("FAIL: " + label);
    }
}
