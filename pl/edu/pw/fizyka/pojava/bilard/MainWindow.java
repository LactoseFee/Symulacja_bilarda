package pl.edu.pw.fizyka.pojava.bilard;


import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MainWindow extends JFrame{

    //Language
    public enum Language {POLISH, ENGLISH}
    Language language = Language.POLISH; //by default
    JButton polish = new JButton("Polski");
    JButton english = new JButton("English");
    void setLanguage(Language lang){language = lang;}
    ImageIcon polishIcon = new ImageIcon(Objects.requireNonNull(MainWindow.class.getResource("polish.png")));
    ImageIcon englishIcon = new ImageIcon(Objects.requireNonNull(MainWindow.class.getResource("english.png")));


    //Panels
    JPanel languagePanel = new JPanel();
    PoolTablePanel poolPanel = new PoolTablePanel();
    JPanel sliderPanel = new JPanel(new BorderLayout(5, 5));
    JPanel bottomPanel = new JPanel(new GridLayout(2, 2, 0, 5));
    
    //Bottom labels
    JLabel firstPlayerPoints = new JLabel("Bile zdobyte przez Player1:\n");
    JLabel secondPlayerPoints = new JLabel("Bile zdobyte przez Player2:\n");
    JLabel fPP = new JLabel("1 2 3");
    JLabel sPP = new JLabel("4 5 6");


    public MainWindow() throws HeadlessException {
        this.setSize(1200, 750);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //Panels
        this.add(poolPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(sliderPanel, BorderLayout.WEST);

        createMenu();
        createCuePowerSlider();

        //Bottom panel
        bottomPanel.setPreferredSize(new Dimension(MainWindow.WIDTH,70));
        bottomPanel.add(firstPlayerPoints);
        bottomPanel.add(secondPlayerPoints);
        bottomPanel.add(fPP);
        bottomPanel.add(sPP);

        
        poolPanel.panelWidth = poolPanel.getWidth();
        poolPanel.panelWidth = poolPanel.getHeight();
        this.setLocationRelativeTo(null);
        this.setResizable(true);
    }

    void createMenu(){
        //Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu optionsMenu = new JMenu("Opcje");
        JMenu gameMenu = new JMenu("Menu gry");

        JMenuItem itemPreferences = new JMenuItem("Preferencje wyglądu gry");
        JMenuItem itemLanguageVer = new JMenuItem("Wersja językowa");
        JMenuItem itemInfo = new JMenuItem("Informacje o autorach");
        JMenuItem itemSave = new JMenuItem("Zapisz grę");
        JMenuItem itemLoad = new JMenuItem("Wczytaj poprzednią grę");
        JMenuItem itemNewGame = new JMenuItem("Rozpocznij grę od początku");
        JMenuItem itemFullscreen = new JMenuItem("Tryb pełnoekranowy");

        //Menu Easter Egg
        ImageIcon billardIcon = new ImageIcon(Objects.requireNonNull(MainWindow.class.getResource("1674_illustration-The_Billiard_Table.png")));
        //JLabel iconLabel = new JLabel(billardIcon);

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

        //Language
        polish.setIcon(polishIcon);
        english.setIcon(englishIcon);
        polish.addActionListener(new LanguageAction("pl"));
        english.addActionListener(new LanguageAction("en"));

        //Menu items listeners
        itemInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "<html><body><p style='width: 200px;'>" +" Program został napisany przez Rafała Nowakowskiego oraz Magdalenę Paździorę w ramach projektu z zajęć Programowania Obiektowego." +
                        "W ramach ciekawostki - to rycina z XVII wieku obrazująca dwóch arystokratów grających w jedną z najwcześniejszych wersji bilarda.\nMiłej gry!" + "</p></body></html>", "Informacja", JOptionPane.PLAIN_MESSAGE, billardIcon);
            }
        });

        itemFullscreen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JMenuItem menuItem = (JMenuItem) e.getSource();
                JPopupMenu popupMenu = (JPopupMenu) menuItem.getParent();
                Component invoker = popupMenu.getInvoker();
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(invoker);
                parentFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });

        itemLanguageVer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==itemLanguageVer){
                    JFrame languageFrame = new JFrame("Wybór języka");
                    languageFrame.add(languagePanel);
                    languageFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    languagePanel.setLayout(new GridLayout(1,2));
                    languagePanel.add(polish);
                    languagePanel.add(english);
                    languageFrame.setSize(500,200);
                    languageFrame.setLocation(poolPanel.getWidth()/2, poolPanel.getHeight()/2);
                    languageFrame.setVisible(true);
                }

            }
        });
    }

    void createCuePowerSlider(){
        sliderPanel.setPreferredSize(new Dimension(100,PoolTablePanel.HEIGHT));

        //Cue stroke power slider
        JSlider strokePowerRegulation = new JSlider(0,100);
        JButton cueRelease = new JButton("Uderz");
        JLabel strokePowerRegulationLabel = new JLabel();

        strokePowerRegulationLabel.setText("Moc uderzenia:\n" + strokePowerRegulation.getValue());
        strokePowerRegulationLabel.setText("<html>"+ "Moc uderzenia [%]:\n"+strokePowerRegulation.getValue()+"</html>");
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
    }

    class LanguageAction implements ActionListener{
        String codeLanguage;

        public LanguageAction(String codeLang){
            this.codeLanguage = codeLang;
        }

        @Override
        public void actionPerformed(ActionEvent e){
        }
    }

    public static void main(String[] args) {
        MainWindow frame = new MainWindow();
        frame.setVisible(true);
        frame.poolPanel.panelWidth = frame.poolPanel.getWidth();
        frame.poolPanel.panelHeight = frame.poolPanel.getHeight();
        System.out.println(frame.poolPanel.panelWidth+" "+frame.poolPanel.panelHeight);
    }

}
