package software.ulpgc.ImageViewer.view;

import software.ulpgc.ImageViewer.presenter.Command;
import software.ulpgc.ImageViewer.presenter.ImageDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private ImageDisplay imageDisplay;
    private final Map<String, Command> commands;

    public MainFrame() {
        this.commands = new HashMap<>();
        setTitle("Image Viewer");
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        add(createImageDisplay());
        add(createLeftToolbar(), BorderLayout.WEST);
        add(createRightToolbar(), BorderLayout.EAST);

        double velocity = 0.4;

        addMouseMotionListener(new MouseAdapter() {
            private int lastMousePosition;

            @Override
            public void mousePressed(MouseEvent e) {
                lastMousePosition = e.getX();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int current = e.getX();
                int movement = (int) ((current-lastMousePosition) * velocity);

                if (movement > 0) { commands.get(">").execute(); }
                else if (movement < 0) { commands.get("<").execute(); }

                lastMousePosition = current;
            }
        });
    }

    private Component createLeftToolbar() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        Box vertical = Box.createVerticalBox();
        vertical.add(Box.createVerticalGlue());
        vertical.add(createArrowButton("◄"));
        vertical.add(Box.createVerticalGlue());
        panel.add(vertical);
        return panel;
    }

    private Component createRightToolbar() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        Box vertical = Box.createVerticalBox();
        vertical.add(Box.createVerticalGlue());
        vertical.add(createArrowButton("►"));
        vertical.add(Box.createVerticalGlue());
        panel.add(vertical);
        return panel;
    }

    private Component createArrowButton(String label) {
        JButton button = new JButton(label);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.addActionListener(e -> {
            if ("◄".equals(label)) { commands.get("<").execute(); }
            else if ("►".equals(label)) { commands.get(">").execute(); }
        });
        return button;
    }

    private Component createImageDisplay() {
        SwingImageDisplay display = new SwingImageDisplay();
        this.imageDisplay = display;
        return display;
    }

    public ImageDisplay imageDisplay() {
        return imageDisplay;
    }

    public void add(String name, Command command) {
        commands.put(name, command);
    }
}