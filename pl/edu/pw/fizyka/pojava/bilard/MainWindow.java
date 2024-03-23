package pl.edu.pw.fizyka.pojava.bilard;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageProducer;
import java.io.InputStream;

public class MainWindow extends JFrame{

    //Panels
    PoolTablePanel poolPanel = new PoolTablePanel();
    JPanel sliderPanel = new JPanel(new BorderLayout(10, 10));
    JPanel bottomPanel = new JPanel();

    //Menu
    JMenuBar menuBar = new JMenuBar();
    JMenu optionsMenu = new JMenu("Menu opcji");
    JMenu gameMenu = new JMenu("Menu gry");

    JMenuItem itemPreferences = new JMenuItem("Preferencje wyglądu gry");
    JMenuItem itemLanguageVer = new JMenuItem("Wersja językowa");
    JMenuItem itemInfo = new JMenuItem("Informacje o autorach");
    JMenuItem itemSave = new JMenuItem("Zapisz grę");
    JMenuItem itemLoad = new JMenuItem("Wczytaj poprzednią grę");
    JMenuItem itemNewGame = new JMenuItem("Rozpocznij grę od początku");

    //Cue stroke power slider
    JSlider strokePowerRegulation = new JSlider(0,100);
    JButton cueRelease = new JButton("Uderz");
    JLabel strokePowerRegulationLabel = new JLabel("Siła uderzenia");

    //Bottom labels
    JLabel firstPlayerPoints = new JLabel("Bile zdobyte przez Player1:\n");
    JLabel secondPlayerPoints = new JLabel("Bile zdobyte przez Player2:\n");

    public MainWindow() throws HeadlessException {
        this.setMinimumSize(new Dimension(800,600));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //Panels
        this.add(poolPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(sliderPanel, BorderLayout.WEST);

        //Menu
        this.setJMenuBar(menuBar);
        menuBar.add(optionsMenu);
        menuBar.add(gameMenu);

        optionsMenu.add(itemPreferences);
        optionsMenu.add(itemLanguageVer);
        optionsMenu.add(itemInfo);
        gameMenu.add(itemSave);
        gameMenu.add(itemLoad);
        gameMenu.add(itemNewGame);

        //Cue stroke power slider
        sliderPanel.add(strokePowerRegulation, BorderLayout.CENTER);
        sliderPanel.add(strokePowerRegulationLabel, BorderLayout.NORTH);
        sliderPanel.add(cueRelease, BorderLayout.SOUTH);
        strokePowerRegulation.setOrientation(SwingConstants.VERTICAL);
        strokePowerRegulation.setMajorTickSpacing(20);
        strokePowerRegulation.setMinorTickSpacing(5);
        strokePowerRegulation.setPaintTicks(true);
        strokePowerRegulation.setPaintLabels(true);
    }

    public static void main(String[] args) {
        MainWindow frame = new MainWindow();
        frame.setVisible(true);
    }

}
