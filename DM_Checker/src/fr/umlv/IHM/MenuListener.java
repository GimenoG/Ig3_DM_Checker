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
		Object src = arg0.getSource();
		
		if (src == ihm.getSave()) {
			System.out.println("ici");
			ihm.saveReport();
		}
		if (src == ihm.getQuit()){
			//this is the end my only friend the end ....
			ihm.saveReport();
			System.exit(0);
		}
		if(src == ihm.getOpenRepository()){
			//TODO heu on le fait ?
		}
		if (src == ihm.getListItem()){
			//TODO pop up pour changer l'executable
		}
	}
}