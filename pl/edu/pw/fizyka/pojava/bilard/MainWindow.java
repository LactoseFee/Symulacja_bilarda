package pl.edu.pw.fizyka.pojava.bilard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame{

    //Panels
    PoolTablePanel poolPanel = new PoolTablePanel();
    JPanel sliderPanel = new JPanel(new BorderLayout(10, 10));
    JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 0, 5));
   
    
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
    JMenuItem itemFullscreen = new JMenuItem("Tryb pełnoekranowy");
    
    //Menu Easter Egg
    ImageIcon billardIcon = new ImageIcon(MainWindow.class.getResource("1674_illustration-The_Billiard_Table.png"));
    //JLabel iconLabel = new JLabel(billardIcon);

    //Cue stroke power slider
    JSlider strokePowerRegulation = new JSlider(0,100);
    JButton cueRelease = new JButton("Uderz");
    JLabel strokePowerRegulationLabel = new JLabel("Siła uderzenia");
    
    //Bottom labels
    JLabel firstPlayerPoints = new JLabel("Bile zdobyte przez Player1:\n");
    JLabel secondPlayerPoints = new JLabel("Bile zdobyte przez Player2:\n");

    public MainWindow() throws HeadlessException {
        this.setSize(1200, 750);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //Panels
        this.add(poolPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(sliderPanel, BorderLayout.WEST);
        
        
        
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
        itemInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainWindow.this, "Program został napisany przez Rafała Nowakowskiego oraz Magdalenę Paździorę w ramach projektu z zajęć Programowania Obiektowego.\n" +
                        "W ramach ciekawostki - to rycina z XVII wieku obrazująca dwóch arystokratów grających w jedną z najwcześniejszych wersji bilarda.\nMiłej gry!", "Informacja", JOptionPane.PLAIN_MESSAGE, billardIcon);

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

        //Cue stroke power slider
        sliderPanel.add(strokePowerRegulation, BorderLayout.CENTER);
        sliderPanel.add(strokePowerRegulationLabel, BorderLayout.NORTH);
        sliderPanel.add(cueRelease, BorderLayout.SOUTH);
        strokePowerRegulation.setOrientation(SwingConstants.VERTICAL);
        strokePowerRegulation.setMajorTickSpacing(20);
        strokePowerRegulation.setMinorTickSpacing(5);
        strokePowerRegulation.setPaintTicks(true);
        strokePowerRegulation.setPaintLabels(true);

        //Bottom panel
        bottomPanel.setPreferredSize(new Dimension(MainWindow.WIDTH,70));
        bottomPanel.add(firstPlayerPoints);
        bottomPanel.add(secondPlayerPoints);
        
        
        
        poolPanel.panelWidth = poolPanel.getWidth();
        poolPanel.panelWidth = poolPanel.getHeight();
        this.setLocationRelativeTo(null);
        this.setResizable(true);
    }

    public static void main(String[] args) {
        MainWindow frame = new MainWindow();
        frame.setVisible(true);
        frame.poolPanel.panelWidth = frame.poolPanel.getWidth();
        frame.poolPanel.panelHeight = frame.poolPanel.getHeight();
        System.out.println(frame.poolPanel.panelWidth+" "+frame.poolPanel.panelHeight);
    }

}
