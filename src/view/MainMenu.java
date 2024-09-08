package view;

import controller.Controller;

import javax.swing.*;
import javax.xml.transform.Source;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;



public class MainMenu extends JFrame implements ActionListener {
    private final JButton playButton;
    private final JButton settingsButton;
//    private final JButton statsButton;
    private final JButton exitButton;

    private int[] settings;
    /**
     * Creates the main menu
     *
     * @post Main menu/starting window is created
     */
    public MainMenu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("FruitClash");
        setBounds(200, 100, 800, 600);

//        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
//                "/resources/background.png"))); // TODO
//        Image image;
//        image = icon.getImage();
//        image = image.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
//        icon = new ImageIcon(image);
//        setContentPane(new JLabel(icon));

        setLayout(new GridLayout(10, 1));
        setPreferredSize(new Dimension(800, 600));

        add(new JLabel());

        JLabel title1 = new JLabel("<html><font color=lime>Fruit</font></html>", SwingConstants.CENTER);
        title1.setPreferredSize(new Dimension(60, 60));
        title1.setFont(new Font("Serif", Font.BOLD, 60));
        add(title1);
        JLabel title2 = new JLabel("<html><font color=  red>Clash</font></html>", SwingConstants.CENTER);
        title2.setPreferredSize(new Dimension(60, 60));
        title2.setFont(new Font("Serif", Font.BOLD, 60));
        add(title2);

        for (int i = 0; i < 2; i++) {
            JLabel empty = new JLabel();
            empty.setPreferredSize(new Dimension(150, 450));
            add(empty);
        }

        playButton = new JButton();
//        playButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(
//                "/resources/start.png"))));
        playButton.setText("Play");
//        playButton.setBorder(null);
//        playButton.setFocusPainted(false);
//        playButton.setContentAreaFilled(false);
//        playButton.setBorderPainted(false);
//        playButton.setBackground(new Color(0, 0, 0, 0));
        playButton.setPreferredSize(new Dimension(200, 200));
        playButton.setFont(new Font("Serif", Font.BOLD, 30));
        playButton.addActionListener(this);
        add(playButton);

        settingsButton = new JButton();
//        settingsButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(
//                "/resources/settings.png"))));
        settingsButton.setText("Settings");
//        settingsButton.setBorder(null);
//        settingsButton.setFocusPainted(false);
//        settingsButton.setContentAreaFilled(false);
//        settingsButton.setBorderPainted(false);
//        settingsButton.setBackground(new Color(0, 0, 0, 0));
        settingsButton.setPreferredSize(new Dimension(200, 200));
        settingsButton.setFont(new Font("Serif", Font.BOLD, 30));
        settingsButton.addActionListener(this);
        add(settingsButton);

//        statsButton = new JButton();
////        statsButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(
////                "/resources/stats.png"))));
//        statsButton.setText("Stats");
//        statsButton.setBorder(null);
//        statsButton.setFocusPainted(false);
//        statsButton.setContentAreaFilled(false);
//        statsButton.setBorderPainted(false);
//        statsButton.setBackground(new Color(0, 0, 0, 0));
//        statsButton.setPreferredSize(new Dimension(200, 200));
//        statsButton.setFont(new Font("Serif", Font.BOLD, 30));
//        statsButton.addActionListener(this);
//        add(statsButton);

        add(new JLabel());

        exitButton = new JButton();
//        exitButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(
//                "/resources/exit.png"))));
        exitButton.setText("Exit");
//        exitButton.setBorder(null);
//        exitButton.setFocusPainted(false);
//        exitButton.setContentAreaFilled(false);
//        exitButton.setBorderPainted(false);
//        exitButton.setBackground(new Color(0, 0, 0, 0));
        exitButton.setPreferredSize(new Dimension(200, 200));
        exitButton.setFont(new Font("Serif", Font.BOLD, 30));
        exitButton.addActionListener(this);
        add(exitButton);

        settings = new int[]{0};

        setVisible(true);
    }

    public void activateFrame() {
        setEnabled(true);
    }

    public void deactivateFrame() {
        setEnabled(false);
    }

    /**
     * Button click event handler
     *
     * @param e Button click event
     * @post If reducedArmy or noRetreat button is clicked then the setting is toggle, or play is clicked so it starts the game
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(this.playButton)) {
            Controller c = new Controller(settings);
            dispose();
        } else if (source.equals(this.settingsButton)) {
            Settings s = new Settings(this);
            deactivateFrame();
//        } else if (source.equals(this.statsButton)) {

        } else {
            System.exit(0);
        }
    }
}
