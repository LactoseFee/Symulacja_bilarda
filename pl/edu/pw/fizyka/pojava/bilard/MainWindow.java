package pl.edu.pw.fizyka.pojava.bilard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class MainWindow extends JFrame {
    // Lock
    private final ReentrantLock lock = new ReentrantLock();

    //Declarations of variables that need to be globally accessible

    //Menu
    JMenuBar menuBar;
    JMenu optionsMenu;
    JMenu gameMenu;
    JMenuItem itemLanguageVer;
    JMenuItem itemInfo;
    JMenuItem itemSave;
    JMenuItem itemLoad;
    JMenuItem itemNewGame;
    JMenuItem itemFullscreen;

    JTextField whiteBallVX;
    JTextField whiteBallVY;

    JMenu subMenuPreferences;
    JMenuItem themeDefault;
    JMenuItem themeDark;
    JMenuItem themeMiami;

    User playerOne = new User("", User.nowTurning.YES);
    User playerTwo = new User("", User.nowTurning.NO);
    JLabel playerOneNowTurningLabel = new JLabel("********", SwingConstants.CENTER);
    JLabel playerTwoNowTurningLabel = new JLabel("", SwingConstants.CENTER);

    public JSlider ballSliderInputVX;
    public JSlider ballSliderInputVY;

    //Theme
    ThemeManager themeManager = new ThemeManager();

    //Access to language libraries/components
    Locale locale = Locale.forLanguageTag("pl"); //default language
    ResourceBundle bundle = ResourceBundle.getBundle("pl.edu.pw.fizyka.pojava.bilard.messages", locale);
    JFrame languageFrame;

    //Cue power regulation
    int cuePower = 100; //0=<cuePower=<100
    JButton cueRelease;
    JLabel strokePowerRegulationLabel;

    public void setCuePower(int cueP) {
        cuePower = cueP;
    }

    //Points for players
    JLabel firstPlayerPoints;
    JLabel secondPlayerPoints;

    //Panels
    JPanel languagePanel = new JPanel();
    PoolTablePanel poolPanel = new PoolTablePanel(this, lock, playerOne, playerTwo);
    JPanel sliderPanel = new JPanel(new BorderLayout(5, 5));
    JPanel bottomPanel = new JPanel(new GridLayout(2, 2, 0, 5));

    JLabel fPP;
    JLabel sPP;

    //Icons
    ImageIcon billardIcon;//= new ImageIcon(Objects.requireNonNull(MainWindow.class.getResource("1674_illustration-The_Billiard_Table.png")));

    public MainWindow() throws HeadlessException {
        this.setSize(1200, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Panels
        add(poolPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        add(sliderPanel, BorderLayout.WEST);
//        JTextField player1Field = new JTextField();
//        JTextField player2Field = new JTextField();
//        Object[] message = {
//                "Player 1:", player1Field,
//                "Player 2:", player2Field
//        };
//
//        int option = JOptionPane.showConfirmDialog(null, message, "Enter Player Names", JOptionPane.OK_CANCEL_OPTION);
//
//        if (option == JOptionPane.OK_OPTION) {
//            String player1 = player1Field.getText();
//            String player2 = player2Field.getText();
//
//            if (!player1.isEmpty() && !player2.isEmpty()) {
//                System.out.println("Player 1: " + player1);
//                System.out.println("Player 2: " + player2);
//            } else {
//                JOptionPane.showMessageDialog(null, "Both player names must be entered", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        } else {
//            System.out.println("User cancelled the dialog");
//        }
        themeManager.setTheme("default");
        poolPanel.setAllColors(themeManager.poolPanelBackgroundColor, themeManager.poolColorGreen, themeManager.sidePoolColor, themeManager.cornerColor);
        createMenu();
        addPreferencesListeners();
        createCuePowerSlider();
        createBottomLabels();
        createLanguageMenu();
        makeItInternational();
        addGameplayListeners();

        this.setLocationRelativeTo(null);
        this.setResizable(true);

    }

    public void createMenu() {
        //Menu
        menuBar = new JMenuBar();
        optionsMenu = new JMenu(bundle.getString("menu.preferences"));
        gameMenu = new JMenu(bundle.getString("menu.gameplay"));

        subMenuPreferences = new JMenu(bundle.getString("menu.theme"));

        itemLanguageVer = new JMenuItem(bundle.getString("menu.language"));
        itemInfo = new JMenuItem(bundle.getString("menu.info"));
        itemSave = new JMenuItem(bundle.getString("menu.save"));
        itemLoad = new JMenuItem(bundle.getString("menu.load"));
        itemNewGame = new JMenuItem(bundle.getString("menu.start"));
        itemFullscreen = new JMenuItem(bundle.getString("menu.fullscreen"));

        //Preferences menu
        themeDefault = new JMenuItem();
        themeDark = new JMenuItem();
        themeMiami = new JMenuItem();

        //Menu
        this.setJMenuBar(menuBar);
        menuBar.add(optionsMenu);
        menuBar.add(gameMenu);

        //Submenu
        optionsMenu.add(subMenuPreferences);
        subMenuPreferences.add(themeDefault);
        subMenuPreferences.add(themeDark);
        subMenuPreferences.add(themeMiami);

        //Adding menu items
        optionsMenu.add(itemLanguageVer);
        optionsMenu.add(itemInfo);
        optionsMenu.add(itemFullscreen);
        gameMenu.add(itemSave);
        gameMenu.add(itemLoad);
        gameMenu.add(itemNewGame);
    }

    public void addGameplayListeners() {
        itemNewGame.addActionListener(e -> {
            poolPanel.moveBallsToStartingPosition();
            poolPanel.moveWhiteBallToStartingPosition();
            System.out.println("Nowa gra");
            poolPanel.repaint();

        });
        itemSave.addActionListener(e -> {

        });
        itemLoad.addActionListener(e -> {

        });
    }

    public void addPreferencesListeners() {
        //Listeners
        themeDefault.addActionListener(e -> {
            themeManager.setTheme("default");
            poolPanel.setAllColors(themeManager.poolPanelBackgroundColor, themeManager.poolColorGreen, themeManager.sidePoolColor, themeManager.cornerColor);
            SwingUtilities.updateComponentTreeUI(this);
        });
        themeDark.addActionListener(e -> {
            themeManager.setTheme("dark");
            poolPanel.setAllColors(themeManager.poolPanelBackgroundColor, themeManager.poolColorGreen, themeManager.sidePoolColor, themeManager.cornerColor);
            SwingUtilities.updateComponentTreeUI(this);
        });
        themeMiami.addActionListener(e -> {
            themeManager.setTheme("miami");
            //poolPanel.setPanelBackgroundColor(themeManager.poolPanelBackgroundColor);
            poolPanel.setAllColors(themeManager.poolPanelBackgroundColor, themeManager.poolColorGreen, themeManager.sidePoolColor, themeManager.cornerColor);
            SwingUtilities.updateComponentTreeUI(this);
        });

        itemInfo.addActionListener(e -> {
            billardIcon = new ImageIcon(Objects.requireNonNull(MainWindow.class.getResource("1674_illustration-The_Billiard_Table.png")));
            JOptionPane.showMessageDialog(null, bundle.getString("menu.easteregg"), "Easter egg ;)", JOptionPane.PLAIN_MESSAGE, billardIcon);
        });

        itemFullscreen.addActionListener(e -> {
            JMenuItem menuItem = (JMenuItem) e.getSource();
            JPopupMenu popupMenu = (JPopupMenu) menuItem.getParent();
            Component invoker = popupMenu.getInvoker();
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(invoker);
            parentFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        });

        itemLanguageVer.addActionListener(e -> {
            if (e.getSource() == itemLanguageVer) {
                languageFrame = new JFrame(bundle.getString("menu.language.select"));
                languageFrame.add(languagePanel);
                languageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                languagePanel.setLayout(new GridLayout(1, 2));
                languageFrame.setSize(500, 200);
                languageFrame.setLocation(poolPanel.getWidth() / 2, poolPanel.getHeight() / 2);
                languageFrame.setVisible(true);
            }

        });
    }

    public void createCuePowerSlider() {
        sliderPanel.setPreferredSize(new Dimension(100, PoolTablePanel.HEIGHT));
        JPanel upperSliderPanel = new JPanel();
        JLabel sliderLabelVX = new JLabel("Vx:", SwingConstants.CENTER);
        JLabel sliderLabelVY = new JLabel("Vy:", SwingConstants.CENTER);

        sliderPanel.add(upperSliderPanel, BorderLayout.PAGE_START);
        upperSliderPanel.setLayout(new GridLayout(1,2));
        upperSliderPanel.add(sliderLabelVX);
        upperSliderPanel.add(sliderLabelVY);

        ballSliderInputVX = new JSlider(-10,10);
        ballSliderInputVY = new JSlider(-10,10);
        sliderPanel.add(ballSliderInputVX, BorderLayout.WEST);
        sliderPanel.add(ballSliderInputVY, BorderLayout.EAST);
        ballSliderInputVX.setOrientation(SwingConstants.VERTICAL);
        ballSliderInputVX.setMajorTickSpacing(20);
        ballSliderInputVX.setMinorTickSpacing(5);
        ballSliderInputVX.setPaintTicks(true);
        ballSliderInputVX.setPaintLabels(true);

        ballSliderInputVY.setOrientation(SwingConstants.VERTICAL);
        ballSliderInputVY.setMajorTickSpacing(20);
        ballSliderInputVY.setMinorTickSpacing(5);
        ballSliderInputVY.setPaintTicks(true);
        ballSliderInputVY.setPaintLabels(true);

        cueRelease = new JButton(bundle.getString("cue.stroke"));
        //        strokePowerRegulationLabel = new JLabel();
//
//        strokePowerRegulationLabel.setPreferredSize(new Dimension(sliderPanel.getSize().width, sliderPanel.getSize().height + 50));

        //Cue stroke power slider
//        sliderPanel.add(strokePowerRegulation, BorderLayout.CENTER);
//        sliderPanel.add(strokePowerRegulationLabel, BorderLayout.PAGE_START);


        //Appearance of a slider
//        strokePowerRegulation.setOrientation(SwingConstants.VERTICAL);
//        strokePowerRegulation.setMajorTickSpacing(20);
//        strokePowerRegulation.setMinorTickSpacing(5);
//        strokePowerRegulation.setPaintTicks(true);
//        strokePowerRegulation.setPaintLabels(true);

        //Slider listener
//        strokePowerRegulation.addChangeListener(e -> {
//            setCuePower(strokePowerRegulation.getValue());
//            strokePowerRegulationLabel.setText("<html>"+ bundle.getString("cue.power.label") + "\n" + cuePower + "</html>");
//        });

        whiteBallVX = new JTextField("");
        whiteBallVY = new JTextField("");
        sliderPanel.add(cueRelease, BorderLayout.PAGE_END);

        cueRelease.addActionListener(e -> {
            poolPanel.ballAnimation();
        });
    }

    public void uploadPlayerScore(int playerOneScore, int playerTwoScore) {
        fPP.setText(String.valueOf(playerOne.currentScore));
        sPP.setText(String.valueOf(playerTwo.currentScore));
    }

    public void createBottomLabels() {
        //Bottom labels
        firstPlayerPoints = new JLabel(bundle.getString("bottomlabel.p1"), SwingConstants.CENTER);
        secondPlayerPoints = new JLabel(bundle.getString("bottomlabel.p2"), SwingConstants.CENTER);


        //Bottom panel
        ImageIcon originalRedBallIcon = new ImageIcon(MainWindow.class.getResource("redBall2.png"));
        Image scaledRedBallIcon = originalRedBallIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon redBallIcon = new ImageIcon(scaledRedBallIcon);
        fPP = new JLabel(String.valueOf(playerOne.currentScore), SwingConstants.CENTER);
        sPP = new JLabel(String.valueOf(playerTwo.currentScore), SwingConstants.CENTER);
        bottomPanel.setPreferredSize(new Dimension(MainWindow.WIDTH, 70));
        bottomPanel.setLayout(new GridLayout(1,4));
        bottomPanel.add(firstPlayerPoints);
        bottomPanel.add(fPP);
        bottomPanel.add(playerOneNowTurningLabel);
        bottomPanel.add(secondPlayerPoints);
        bottomPanel.add(sPP);
        bottomPanel.add(playerTwoNowTurningLabel);
    }

    public void createLanguageMenu() {
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
        polishButton.addActionListener(e -> {
            if (e.getSource() == polishButton) {
                locale = Locale.forLanguageTag("pl");
                language.set(Language.POLISH);
                bundle = ResourceBundle.getBundle("pl.edu.pw.fizyka.pojava.bilard.messages", locale);
                makeItInternational();
            }
        });
        englishButton.addActionListener(e -> {
            if (e.getSource() == englishButton) {
                locale = Locale.forLanguageTag("en");
                language.set(Language.ENGLISH);
                bundle = ResourceBundle.getBundle("pl.edu.pw.fizyka.pojava.bilard.messages", locale);
                makeItInternational();
            }
        });
    }

    public void makeItInternational() {

        optionsMenu.setText(bundle.getString("menu.preferences"));
        gameMenu.setText(bundle.getString("menu.gameplay"));
        subMenuPreferences.setText(bundle.getString("menu.theme"));
        itemLanguageVer.setText(bundle.getString("menu.language"));
        itemInfo.setText(bundle.getString("menu.info"));
        itemSave.setText(bundle.getString("menu.save"));
        itemLoad.setText(bundle.getString("menu.load"));
        itemNewGame.setText(bundle.getString("menu.start"));
        itemFullscreen.setText(bundle.getString("menu.fullscreen"));
        //languageFrame.setTitle(bundle.getString("menu.language.select"));
        cueRelease.setText(bundle.getString("cue.stroke"));
        //strokePowerRegulationLabel.setText("<html>"+ bundle.getString("cue.power.label") + "\n" + cuePower + "</html>");
        firstPlayerPoints.setText(bundle.getString("bottomlabel.p1"));
        secondPlayerPoints.setText(bundle.getString("bottomlabel.p2"));

        themeDefault.setText("Default");
        themeDark.setText("Dark");
        themeMiami.setText("MiamiVice");
    }


    public static void main(String[] args) {
        DataBase database = new DataBase();
        MainWindow frame = new MainWindow();
        frame.setVisible(true);
        SwingUtilities.updateComponentTreeUI(frame);
    }

}
