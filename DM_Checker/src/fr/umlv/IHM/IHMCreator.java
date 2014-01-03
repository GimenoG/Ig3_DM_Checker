package fr.umlv.IHM;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

/**
 * This class is in charge of manage the items of the swing clooection wich look
 * with the criterion of DM Checker.
 * 
 * @author Gimeno and Bourgain
 * 
 */

public class IHMCreator {

	private JLabel titleLabel;
	private JLabel criterionsLabel;
	private ButtonGroup noteGroup;
	private ArrayList<JRadioButton> allButtons;
	private JTextArea textZone;

	public String getNote() {
		for (JRadioButton jrb : allButtons) {
			if (jrb.getMnemonic() == noteGroup.getSelection().getMnemonic()) {
				return Integer.toString(jrb.getMnemonic());
			}
		}
		return "";
	}

	public String getComment() {
		return textZone.getText();
	}

	public void setComment(String c) {
		textZone.setText(c);
	}

	public void setNot(String not) {
		for (JRadioButton jrb : allButtons) {
			if (jrb.getMnemonic() == Integer.parseInt(not)) {
				noteGroup.setSelected(jrb.getModel(), true);
			}
		}
	}

	/**
	 * unset all the swing item of the criterions
	 * 
	 */
	public void clean() {
		textZone.setText("");
	}

	private static JMenuItem getMenuItem(String s, IHM i) {
		return new JMenuItem(new MenuListener(i, s));
	}

	public static JMenu setMenuName(List<String> name, IHM ihm) {
		JMenu m = new JMenu("etudiant");
		for (String s : name) {
			m.add(getMenuItem(s, ihm));
		}
		return m;

	}

	/**
	 * The constructor need - the panel in which he will add the items - the
	 * creterion to add to the label - the title of the criterion
	 * 
	 * 
	 * @param panel
	 * @param title
	 * @param criterions
	 */
	public IHMCreator(JPanel panel, String title, String criterions) {
		allButtons = new ArrayList<>();
		titleLabel = new JLabel(title);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setPreferredSize(new Dimension(585, 20));
		criterionsLabel = new JLabel(criterions);
		criterionsLabel.setPreferredSize(new Dimension(585, 20));

		textZone = new JTextArea(3, 52);

		noteGroup = new ButtonGroup();
		for (int i = 0; i < 10; i++) {
			allButtons.add(new JRadioButton(Integer.toString(i)));
		}
		int j = 0;
		for (JRadioButton jrb : allButtons) {
			jrb.setMnemonic(j);
			j++;
		}
		for (JRadioButton jrb : allButtons) {
			noteGroup.add(jrb);
			panel.add(jrb);
		}
		panel.add(titleLabel);
		panel.add(criterionsLabel);

		panel.add(textZone);
	}
}
