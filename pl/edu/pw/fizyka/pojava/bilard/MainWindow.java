package pl.edu.pw.fizyka.pojava.bilard;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{

    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("Menu");
    JMenuItem item = new JMenuItem();
    JSlider strokePowerRegulation = new JSlider();
    JPanel bottomPanel = new JPanel();
    public MainWindow() throws HeadlessException {
        this.setSize(640, 360);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.setJMenuBar(menuBar);
        menuBar.add(menu);
        menu.add(item);
    }

    public static void main(String[] args) {
        MainWindow frame = new MainWindow();
        frame.setVisible(true);
    }

}
