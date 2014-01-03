package fr.umlv.IHM;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;

/**
 * this class extends AbstractAction. She manage the menu of the windows
 * 
 * @author Gimeno & Bourgain
 * 
 */
public class MenuListener extends AbstractAction {

	private IHM ihm;

	public MenuListener(IHM ihm, String texte) {
		super(texte);

		this.ihm = ihm;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JMenuItem i = (JMenuItem) arg0.getSource();

		if (i == ihm.getSave()) {
			ihm.saveReport();
		} else if (i == ihm.getQuit()) {
			ihm.saveReport();
			System.exit(0);
		} else {
			ArrayList<String[]> name = (ArrayList<String[]>) ihm.getData();
			int cpt = 0;
			for (String[] s : name) {
				if (i.getText().compareTo(s[2]) == 0) {
					ihm.setIndice(cpt);
					ihm.editNameLabelTop();
					ihm.cleanSheet();
					ihm.getButtonRun().setText("Run");
					ihm.setExe();
					ihm.setReport();
					return;
				}

				cpt++;
			}
		}
	}
}