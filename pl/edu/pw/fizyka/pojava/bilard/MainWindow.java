package pl.edu.pw.fizyka.pojava.bilard;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class MainWindow extends JFrame{

    //Declarations of variables that need to be globally accessible
    //Menu
    JMenuBar menuBar;
    JMenu optionsMenu;
    JMenu gameMenu;
    JMenuItem itemPreferences;
    JMenuItem itemLanguageVer;
    JMenuItem itemInfo;
    JMenuItem itemSave;
    JMenuItem itemLoad;
    JMenuItem itemNewGame;
    JMenuItem itemFullscreen;

    //Access to language libraries
    Locale locale = Locale.forLanguageTag("en"); //default language
    ResourceBundle bundle = ResourceBundle.getBundle("pl.edu.pw.fizyka.pojava.bilard.messages", locale);
    JFrame languageFrame;

    //Cue power regulation panel
    int cuePower = 100; //0=<cuePower=<100
    JButton cueRelease;
    JLabel strokePowerRegulationLabel;

    public void setCuePower(int cueP){cuePower = cueP;}

    //Bottom panel
    JLabel firstPlayerPoints;
    JLabel secondPlayerPoints;

    //Panels
    JPanel languagePanel = new JPanel();
    PoolTablePanel poolPanel = new PoolTablePanel();
    JPanel sliderPanel = new JPanel(new BorderLayout(5, 5));
    JPanel bottomPanel = new JPanel(new GridLayout(2, 2, 0, 5));

    public MainWindow() throws HeadlessException {
        this.setSize(1200, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Panels
        this.add(poolPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(sliderPanel, BorderLayout.WEST);

        createMenu();
        createCuePowerSlider();
        createBottomLabels();
        createLanguageMenu();
        makeItInternational();

        poolPanel.panelWidth = poolPanel.getWidth();
        poolPanel.panelWidth = poolPanel.getHeight();
        this.setLocationRelativeTo(null);
        this.setResizable(true);
    }

    public void createMenu(){
        //Menu
        menuBar = new JMenuBar();
        optionsMenu = new JMenu(bundle.getString("menu.preferences"));
        gameMenu = new JMenu(bundle.getString("menu.gameplay"));

        itemPreferences = new JMenuItem(bundle.getString("menu.theme"));
        itemLanguageVer = new JMenuItem(bundle.getString("menu.language"));
        itemInfo = new JMenuItem(bundle.getString("menu.info"));
        itemSave = new JMenuItem(bundle.getString("menu.save"));
        itemLoad = new JMenuItem(bundle.getString("menu.load"));
        itemNewGame = new JMenuItem(bundle.getString("menu.start"));
        itemFullscreen = new JMenuItem(bundle.getString("menu.fullscreen"));

        //Menu Easter Egg
        ImageIcon billardIcon = new ImageIcon(Objects.requireNonNull(MainWindow.class.getResource("1674_illustration-The_Billiard_Table.png")));

        //Menu
        this.setJMenuBar(menuBar);
        menuBar.add(optionsMenu);
        menuBar.add(gameMenu);

        //Menu items
        optionsMenu.add(itemPreferences);
        optionsMenu.add(itemLanguageVer);
        optionsMenu.add(itemInfo);
        optionsMenu.add(itemFullscreen);
        gameMenu.add(itemSave);
        gameMenu.add(itemLoad);
        gameMenu.add(itemNewGame);

        //Menu items listeners
        itemInfo.addActionListener(e -> JOptionPane.showMessageDialog(null, bundle.getString("menu.easteregg"), "Easter egg ;)", JOptionPane.PLAIN_MESSAGE, billardIcon));

        itemFullscreen.addActionListener(e -> {
            JMenuItem menuItem = (JMenuItem) e.getSource();
            JPopupMenu popupMenu = (JPopupMenu) menuItem.getParent();
            Component invoker = popupMenu.getInvoker();
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(invoker);
            parentFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        });

        itemLanguageVer.addActionListener(e -> {
            if(e.getSource()==itemLanguageVer){
                languageFrame = new JFrame(bundle.getString("menu.language.select"));
                languageFrame.add(languagePanel);
                languageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                languagePanel.setLayout(new GridLayout(1,2));
                languageFrame.setSize(500,200);
                languageFrame.setLocation(poolPanel.getWidth()/2, poolPanel.getHeight()/2);
                languageFrame.setVisible(true);
            }

        });
    }

    public void createCuePowerSlider(){
        sliderPanel.setPreferredSize(new Dimension(100,PoolTablePanel.HEIGHT));

        //Cue stroke power slider
        JSlider strokePowerRegulation = new JSlider(0,100);
        cueRelease = new JButton(bundle.getString("cue.stroke"));
        strokePowerRegulationLabel = new JLabel();

        strokePowerRegulationLabel.setPreferredSize(new Dimension(sliderPanel.getSize().width, sliderPanel.getSize().height + 50));

        //Cue stroke power slider
        sliderPanel.add(strokePowerRegulation, BorderLayout.CENTER);
        sliderPanel.add(strokePowerRegulationLabel, BorderLayout.PAGE_START);
        sliderPanel.add(cueRelease, BorderLayout.PAGE_END);

        //Appearance of a slider
        strokePowerRegulation.setOrientation(SwingConstants.VERTICAL);
        strokePowerRegulation.setMajorTickSpacing(20);
        strokePowerRegulation.setMinorTickSpacing(5);
        strokePowerRegulation.setPaintTicks(true);
        strokePowerRegulation.setPaintLabels(true);

        //Slider listener
        strokePowerRegulation.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                setCuePower(strokePowerRegulation.getValue());
                strokePowerRegulationLabel.setText("<html>"+ bundle.getString("cue.power.label") + "\n" + cuePower + "</html>");
            }
        });
    }

    public void createBottomLabels(){
        //Bottom labels
        firstPlayerPoints = new JLabel(bundle.getString("bottomlabel.p1"));
        secondPlayerPoints = new JLabel(bundle.getString("bottomlabel.p2"));
        JLabel fPP = new JLabel("1 2 3");
        JLabel sPP = new JLabel("4 5 6");

        //Bottom panel
        bottomPanel.setPreferredSize(new Dimension(MainWindow.WIDTH,70));
        bottomPanel.add(firstPlayerPoints);
        bottomPanel.add(secondPlayerPoints);
        bottomPanel.add(fPP);
        bottomPanel.add(sPP);
    }

    public void createLanguageMenu(){
        //Language changes
        enum Language {POLISH, ENGLISH}
        AtomicReference<Language> language = new AtomicReference<>(Language.POLISH); //by default

        JButton polishButton = new JButton("Polski");
        JButton englishButton = new JButton("English");

        //Icons
        ImageIcon polishIcon = new ImageIcon(Objects.requireNonNull(MainWindow.class.getResource("polish.png")));
        ImageIcon englishIcon = new ImageIcon(Objects.requireNonNull(MainWindow.class.getResource("english.png")));
        polishButton.setIcon(polishIcon);
        englishButton.setIcon(englishIcon);

        languagePanel.add(polishButton);
        languagePanel.add(englishButton);

        //Listeners
        polishButton.addActionListener(e->{
            if(e.getSource()==polishButton){
                locale = Locale.forLanguageTag("pl");
                language.set(Language.POLISH);
                bundle = ResourceBundle.getBundle("pl.edu.pw.fizyka.pojava.bilard.messages", locale);
                makeItInternational();
            }
        });
        englishButton.addActionListener(e->{
            if(e.getSource()==englishButton){
                locale = Locale.forLanguageTag("en");
                language.set(Language.ENGLISH);
                bundle = ResourceBundle.getBundle("pl.edu.pw.fizyka.pojava.bilard.messages", locale);
                makeItInternational();
            }
        });
    }

    public void makeItInternational(){
        //To ma robić .setText(bundle.cośtam...);
        //Jak się dobrać do wartości zainicjalizowanych gdzieśtam?
        optionsMenu.setText(bundle.getString("menu.preferences"));
        gameMenu.setText(bundle.getString("menu.gameplay"));
        itemPreferences.setText(bundle.getString("menu.theme"));
        itemLanguageVer.setText(bundle.getString("menu.language"));
        itemInfo.setText(bundle.getString("menu.info"));
        itemSave.setText(bundle.getString("menu.save"));
        itemLoad.setText(bundle.getString("menu.load"));
        itemNewGame.setText(bundle.getString("menu.start"));
        itemFullscreen.setText(bundle.getString("menu.fullscreen"));
        //languageFrame.setTitle(bundle.getString("menu.language.select"));
        cueRelease.setText(bundle.getString("cue.stroke"));
        strokePowerRegulationLabel.setText("<html>"+ bundle.getString("cue.power.label") + "\n" + cuePower + "</html>");
        firstPlayerPoints.setText(bundle.getString("bottomlabel.p1"));
        secondPlayerPoints.setText(bundle.getString("bottomlabel.p2"));
    }

    public static void main(String[] args) {
        MainWindow frame = new MainWindow();
        frame.setVisible(true);
    }

}
