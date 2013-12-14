package fr.umlv.IHM;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import fr.umlv.util.Regex;

public class ButtonListener extends AbstractAction {

	private IHM ihm;

	public ButtonListener(IHM ihm, String texte) {
		super(texte);

		this.ihm = ihm;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object src = arg0.getSource();

		if (src == ihm.getButtonNext()) {
			// TODO action
			//TODO svg rapport + etat
			ihm.incrementIndice();
			ihm.editNameLabelTop(Regex.idName(ihm.getCurrentName()));
		} else if (src == ihm.getButtonPrevious()) {
			//TODO action
			//TODO svg du rapport +etat
			ihm.incrementIndice();
			ihm.editNameLabelTop(Regex.idName(ihm.getCurrentName()));
		} else if (src == ihm.getButtonRun()) {
			// set the button label
			if (ihm.getButtonRun().getText().compareTo("Run") == 0) {
				ihm.getButtonRun().setText("Stop");
				//on arrete l'executable si il tourne
			} else {
				ihm.getButtonRun().setText("Run");
				//on lance l'executable
				ihm.launchExe();
			}
			
		}

	}
}