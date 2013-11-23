package fr.umlv.IHM;

import javax.swing.*;

import java.awt.event.*;

public class IHM extends JFrame{
	public IHM(){
		super("DMChecker");
		WindowListener wl = new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		};
		
		addWindowListener(wl);
		setSize(400,800);
		setVisible(true);
	}
	/**
	 * 
	 * Add all the buttons of the interface
	 * 
	 * @param frame
	 * @param panel
	 * @return
	 */
	public JFrame addBouttons(JFrame frame, JPanel panel){
		JButton buttonPrevious = new JButton("Previous");
		JButton buttonNext = new JButton("Next");
		panel.add(buttonPrevious);
		panel.add(buttonNext);
		setContentPane(panel);
		return frame;
	}
	
	
	public JFrame addMenu(JFrame frame){
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu menu = new JMenu("Fichier");
		menu.add(new JMenuItem("Nouveau", 'N'));
		menuBar.add(menu);
		
		
		
		return frame;
	}
	
	
	public void launch(){
		JFrame frame = new IHM();
		JPanel panel = new JPanel();
		addBouttons(frame, panel);
		addMenu(frame);
	}
	//TODO a mettre dans le main a la fin des tests
}
