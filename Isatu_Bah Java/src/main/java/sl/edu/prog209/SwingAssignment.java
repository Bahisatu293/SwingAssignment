package sl.edu.prog209;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class SwingAssignment extends BaseWindow {
    // These three named variables are explicitly required by the assignment brief.
    private JFrame frame;
    private JPanel panel;
    private JLabel label;

    private JLabel statusLabel;
    private JLabel subtitleLabel;
    private JButton changeTextButton;
    private JButton changeBackgroundButton;
    private JButton themeColourButton;
    private final List<JButton> actionButtons = new ArrayList<>();
    private Color accentColor = new Color(245, 91, 173);       // vivid pink
    private Color accentSecondary = new Color(181, 112, 255);  // lavender
    private boolean lightBackground = false;
    private final String initialWelcome = "Welcome to Java Swing First Individual Assignment by Isatu Bah";

    public SwingAssignment() {
        // Constructor initializes and assembles the GUI components.
        frame = new JFrame();
        configureFrame(frame); // inherited configuration from BaseWindow
        createInterface();
    }

    private void createInterface() {
        panel = new GradientPanel();
        panel.setLayout(new BorderLayout(22, 20));
        panel.setBorder(new EmptyBorder(28, 34, 24, 34));

        JPanel header = new JPanel(new BorderLayout(14, 8));
        header.setOpaque(false);
        JLabel smallBrandIcon = new JLabel(loadAppIcon(54));
        smallBrandIcon.setPreferredSize(new Dimension(62, 62));
        JPanel brandWords = new JPanel();
        brandWords.setOpaque(false);
        brandWords.setLayout(new BoxLayout(brandWords, BoxLayout.Y_AXIS));
        JLabel brandName = new JLabel("");
        brandName.setFont(new Font("SansSerif", Font.BOLD, 13));
        brandName.setForeground(accentColor);
        JLabel brandDetail = new JLabel("");
        brandDetail.setFont(new Font("SansSerif", Font.PLAIN, 11));
        brandDetail.setForeground(new Color(229, 215, 238));
        brandWords.add(Box.createVerticalGlue());
        brandWords.add(brandName);
        brandWords.add(Box.createVerticalStrut(5));
        brandWords.add(brandDetail);
        brandWords.add(Box.createVerticalGlue());
        header.add(smallBrandIcon, BorderLayout.WEST);
        header.add(brandWords, BorderLayout.CENTER);
        panel.add(header, BorderLayout.NORTH);

        JPanel centre = new JPanel();
        centre.setOpaque(false);
        centre.setLayout(new BoxLayout(centre, BoxLayout.Y_AXIS));
        JLabel heroIcon = new JLabel(loadAppIcon(174));
        heroIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        heroIcon.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel capsule = new JLabel("  YOUR PERSONAL JAVA SWING SPACE  ");
        capsule.setAlignmentX(Component.CENTER_ALIGNMENT);
        capsule.setFont(new Font("SansSerif", Font.BOLD, 11));
        capsule.setForeground(Color.WHITE);
        capsule.setOpaque(true);
        capsule.setBackground(new Color(208, 55, 139));
        capsule.setBorder(new EmptyBorder(7, 13, 7, 13));

        label = new JLabel();
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 24));
        label.setForeground(Color.WHITE);
        setWelcomeText(initialWelcome);

        subtitleLabel = new JLabel("A small interactive application built with Java Swing");
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(238, 226, 244));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 9));
        buttonPanel.setOpaque(false);
        changeTextButton = makeButton("Change Text");
        changeBackgroundButton = makeButton("Change Background");
        themeColourButton = makeButton("Theme Colour");
        changeTextButton.addActionListener(this::onChangeText);
        changeBackgroundButton.addActionListener(e -> toggleBackground());
        themeColourButton.addActionListener(e -> chooseThemeColour());
        buttonPanel.add(changeTextButton);
        buttonPanel.add(changeBackgroundButton);
        buttonPanel.add(themeColourButton);

        centre.add(Box.createVerticalGlue());
        centre.add(heroIcon);
        centre.add(Box.createVerticalStrut(16));
        centre.add(capsule);
        centre.add(Box.createVerticalStrut(22));
        centre.add(label);
        centre.add(Box.createVerticalStrut(13));
        centre.add(subtitleLabel);
        centre.add(Box.createVerticalStrut(28));
        centre.add(buttonPanel);
        centre.add(Box.createVerticalGlue());
        panel.add(centre, BorderLayout.CENTER);

        JPanel footer = new JPanel(new BorderLayout(10, 8));
        footer.setOpaque(false);
        statusLabel = new JLabel("STATUS  •  Ready");
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 11));
        statusLabel.setForeground(accentColor);
        JLabel footerTip = new JLabel("Pink / Lavender");
        footerTip.setFont(new Font("SansSerif", Font.PLAIN, 11));
        footerTip.setForeground(new Color(210, 192, 222));
        footer.add(statusLabel, BorderLayout.WEST);
        footer.add(footerTip, BorderLayout.EAST);
        panel.add(footer, BorderLayout.SOUTH);

        frame.setContentPane(panel);
        frame.setIconImage(loadAppIcon(64).getImage());
        frame.setVisible(true);
    }

    private ImageIcon loadAppIcon(int size) {
        URL iconUrl = getClass().getResource("/assets/ib-application-icon.jpg");
        if (iconUrl == null) {
            // The fallback keeps the UI usable if the image file is accidentally removed.
            return createLetterIcon(size);
        }
        ImageIcon source = new ImageIcon(iconUrl);
        java.awt.Image scaled = source.getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    private ImageIcon createLetterIcon(int size) {
        java.awt.image.BufferedImage image = new java.awt.image.BufferedImage(size, size, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(new Color(255, 225, 241));
        g.fillOval(2, 2, size - 4, size - 4);
        g.setColor(accentColor);
        g.setFont(new Font("Serif", Font.BOLD, Math.max(18, size / 3)));
        g.drawString("IB", size / 5, (int) (size * 0.62));
        g.dispose();
        return new ImageIcon(image);
    }

    private JButton makeButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(76, 36, 83));
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(accentColor, 2, true),
                new EmptyBorder(11, 15, 11, 15)));
        actionButtons.add(button);
        return button;
    }

    private void onChangeText(ActionEvent event) {
        String typed = JOptionPane.showInputDialog(frame,
                "Enter the message to display:", "Change Welcome Text", JOptionPane.PLAIN_MESSAGE);
        if (typed == null) {
            statusLabel.setText("STATUS  •  Text change cancelled");
            return;
        }
        applyTextChange(typed);
    }

    /** Updates the label safely; exposed package-locally for repeatable smoke testing. */
    void applyTextChange(String typed) {
        if (typed == null || typed.trim().isEmpty()) {
            statusLabel.setText("STATUS  •  No change: enter some text");
            return;
        }
        setWelcomeText(typed.trim());
        statusLabel.setText("STATUS  •  Welcome text updated");
    }

    private void setWelcomeText(String text) {
        label.setText("<html><div style='text-align:center;width:560px'>" + escapeHtml(text) + "</div></html>");
    }

    private String escapeHtml(String input) {
        return input.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }

    /** Switches the root JPanel between a deep-plum night state and a blush day state. */
    void toggleBackground() {
        lightBackground = !lightBackground;
        if (panel instanceof GradientPanel) {
            ((GradientPanel) panel).setLightMode(lightBackground);
        }
        panel.setBackground(lightBackground ? new Color(255, 231, 243) : new Color(35, 19, 48));
        if (lightBackground) {
            label.setForeground(new Color(70, 35, 75));
            subtitleLabel.setForeground(new Color(104, 73, 116));
            statusLabel.setForeground(new Color(173, 35, 116));
        } else {
            label.setForeground(Color.WHITE);
            subtitleLabel.setForeground(new Color(238, 226, 244));
            statusLabel.setForeground(accentColor);
        }
        statusLabel.setText(lightBackground ? "STATUS  •  Blush Daylight background" : "STATUS  •  Rose Twilight background");
        panel.repaint();
    }

    /** Opens the Swing colour chooser and applies the selected accent when confirmed. */
    private void chooseThemeColour() {
        Color chosen = JColorChooser.showDialog(frame, "Choose an accent colour", accentColor);
        if (chosen == null) {
            statusLabel.setText("STATUS  •  Theme colour unchanged");
            return;
        }
        applyAccentColor(chosen);
    }

    void applyAccentColor(Color chosen) {
        if (chosen == null) return;
        accentColor = chosen;
        for (JButton button : actionButtons) {
            button.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(accentColor, 2, true), new EmptyBorder(11, 15, 11, 15)));
        }
        statusLabel.setForeground(lightBackground ? new Color(112, 31, 90) : accentColor);
        statusLabel.setText(String.format("STATUS  •  Accent updated to #%02X%02X%02X", chosen.getRed(), chosen.getGreen(), chosen.getBlue()));
        panel.repaint();
    }

    // Getters are intentionally small and package-visible for an external smoke-test class.
    JFrame getFrame() { return frame; }
    JPanel getPanel() { return panel; }
    JLabel getMessageLabel() { return label; }
    JLabel getStatusLabel() { return statusLabel; }
    boolean isLightBackground() { return lightBackground; }
    Color getAccentColor() { return accentColor; }
    boolean hasLoadedIconResource() { return getClass().getResource("/assets/ib-application-icon.jpg") != null; }

    private class GradientPanel extends JPanel {
        private boolean lightMode;
        GradientPanel() { setOpaque(true); }
        void setLightMode(boolean lightMode) { this.lightMode = lightMode; }
        @Override protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D g = (Graphics2D) graphics.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Color start = lightMode ? new Color(255, 231, 243) : new Color(36, 17, 50);
            Color end = lightMode ? new Color(246, 205, 231) : new Color(83, 32, 88);
            g.setPaint(new GradientPaint(0, 0, start, getWidth(), getHeight(), end));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SwingAssignment::new);
    }
}
