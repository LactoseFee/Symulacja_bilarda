package pl.edu.pw.fizyka.java.bilard;

import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class MainWindow extends JFrame {

	JMenuBar menuBar = new JMenuBar();
	JMenu menu = new JMenu("Menu");
	JMenuItem item = new JMenuItem();
	JSlider strokePowerRegulation = new JSlider();
	JPanel bottomPanel = new JPanel();
	
	
	MainWindow() throws HeadlessException {
		this.setSize(640, 480);
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
