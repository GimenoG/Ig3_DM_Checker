package fr.umlv.IHM;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class ButtonListener extends AbstractAction{
	
	private IHM ihm;
	
	public ButtonListener(IHM ihm, String texte){
		super(texte);
 
		this.ihm = ihm;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object src = arg0.getSource();
		
		if (src == ihm.getButtonNext()){
			//TODO action
			ihm.editNameLabelTop("next");
		}
		else if (src == ihm.getButtonPrevious()){
			//TODO action
			ihm.editNameLabelTop("previous");
		}	
		else if (src == ihm.getButtonRun()){
			//set the button lable
			if (ihm.getButtonRun().getText().compareTo("Run")==0){
				ihm.getButtonRun().setText("Stop");
				ihm.editNameLabelTop("stop");
			}
			else{
				ihm.getButtonRun().setText("Run");
				ihm.editNameLabelTop("run");
			}
		}
		
	}