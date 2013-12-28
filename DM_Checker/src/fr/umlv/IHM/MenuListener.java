package fr.umlv.IHM;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;

public class MenuListener extends AbstractAction{
	
	private IHM ihm;

	public MenuListener(IHM ihm, String texte) {
		super(texte);
		 
		this.ihm = ihm;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JMenuItem i = (JMenuItem) arg0.getSource();
		
		//System.out.println("actionPerformed menu "+i.getText());//TODO sysodebug
		
		if (i == ihm.getSave()) {
			ihm.saveReport();
		}
		else if (i == ihm.getQuit()){
			//this is the end my only friend the end ....
			ihm.saveReport();
			System.exit(0);
		}
		else{
			ArrayList<String []> name = (ArrayList<String[]>) ihm.getData();
			int cpt=0;
			for(String [] s : name){
				//System.out.println("actionPerformed menu "+s[2]);//TODO sysodebug
				if(i.getText().compareTo(s[2])==0){
					//System.out.println("actionPerformed menu "+cpt);//TODO sysodebug
					ihm.setIndice(cpt);
					ihm.editNameLabelTop();
					ihm.cleanSheet();
					ihm.getButtonRun().setText("Run");
					ihm.setExe();
					return; //TODO c'est moche non ?
				}
					
				cpt++;
			}
		}
	}
}