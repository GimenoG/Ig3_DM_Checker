package fr.umlv.IHM;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class IHMCreator {
	
	private JLabel titleLabel;
	private JLabel criterionsLabel;
	private ButtonGroup noteGroup;
	private ArrayList<JRadioButton> allButtons;
	private JTextArea textZone;
	
	public String getNote(){
		for (JRadioButton jrb : allButtons){
			if (jrb.getMnemonic() == noteGroup.getSelection().getMnemonic()){
				return Integer.toString(jrb.getMnemonic());
			}
		}
		return "";
	}
	
	public String getComment(){
		return textZone.getText();
	}
	public void setComment(String c){
		textZone.setText(c);
	}
	public void setNot(String not){
		for (JRadioButton jrb : allButtons){
			if (jrb.getMnemonic() == Integer.parseInt(not)){
				noteGroup.setSelected(jrb.getModel(), true);
			}
		}
	}
	
	public void clean(){
		textZone.setText("");
	}
	private static JMenuItem getMenuItem(String s, IHM i){
		return new JMenuItem(new MenuListener(i, s));
	}
	
	public static JMenu  setMenuName(List<String> name, IHM ihm){
		JMenu m = new JMenu("etudiant");
		for(String s : name){
			m.add(getMenuItem(s, ihm));
		}
		return m;
		
	}
	
	public IHMCreator(JPanel panel, String title, String criterions){
		allButtons=new ArrayList<>();
		titleLabel = new JLabel(title);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setPreferredSize(new Dimension(585, 20));
		criterionsLabel = new JLabel(criterions);
		criterionsLabel.setPreferredSize(new Dimension(585, 20));
		
		textZone = new JTextArea(3, 52);
		
		noteGroup = new ButtonGroup();
		//creation de tout les bouton
		for(int i = 0; i<10; i++){
			allButtons.add(new JRadioButton(Integer.toString(i)));
		}
		int j = 0;
		for(JRadioButton jrb : allButtons){
			jrb.setMnemonic(j);
			j++;
		}
		//creation des lien avec le boutton groupe et le panel
		for(JRadioButton jrb : allButtons){
			noteGroup.add(jrb);
			panel.add(jrb);
		}
		panel.add(titleLabel);
		panel.add(criterionsLabel);
		
		panel.add(textZone);
	}
}
