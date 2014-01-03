package fr.umlv.IHM;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * this class extends AbstractAction. She manage the button of the windows
 * 
 * @author Gimeno and Bourgain
 * 
 */

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
			ihm.incrementIndice();
			ihm.editNameLabelTop();
			ihm.cleanSheet();
			ihm.setReport();
			ihm.stopExe();
			ihm.getButtonRun().setText("Run");
			ihm.setExe();
		} else if (src == ihm.getButtonPrevious()) {
			ihm.decrementIndice();
			ihm.editNameLabelTop();
			ihm.cleanSheet();
			ihm.setReport();
			ihm.stopExe();
			ihm.setExe();
		} else if (src == ihm.getButtonRun()) {
			if (ihm.getButtonRun().getText().compareTo("Run") == 0) {
				ihm.getButtonRun().setText("Stop");
				ihm.launchExe();
			} else {
				ihm.getButtonRun().setText("Run");
				ihm.stopExe();
			}

		}

	}
}