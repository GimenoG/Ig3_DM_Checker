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
		setSize(200,100);
		setVisible(true);
	}
	//TODO a mettre dans le main a la fin des tests
	public static void main(String [] args){
		JFrame frame = new IHM();
	}
}
