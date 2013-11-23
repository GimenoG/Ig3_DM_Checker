package fr.umlv.IHM;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class MenuListener extends AbstractAction{
	
	private IHM ihm;

	public MenuListener(IHM ihm, String texte) {
		super(texte);
		 
		this.ihm = ihm;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}