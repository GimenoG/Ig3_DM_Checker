package fr.umlv.IHM;

import java.awt.Dimension;
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
	private JRadioButton radioButton0;
	private JRadioButton radioButton1;
	private JRadioButton radioButton2;
	private JRadioButton radioButton3;
	private JRadioButton radioButton4;
	private JRadioButton radioButton5;
	private JRadioButton radioButton6;
	private JRadioButton radioButton7;
	private JRadioButton radioButton8;
	private JRadioButton radioButton9;
	private JRadioButton radioButton10;
	private JTextArea textZone;
	
	public int getNote(){
		return noteGroup.getButtonCount();
	}
	
	public String getComment(){
		return textZone.getText();
	}
	public void setComment(String c){
		textZone.setText(c);
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
		titleLabel = new JLabel(title);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setPreferredSize(new Dimension(585, 20));
		criterionsLabel = new JLabel(criterions);
		criterionsLabel.setPreferredSize(new Dimension(585, 20));
		
		textZone = new JTextArea(3, 52);
		
		noteGroup = new ButtonGroup();
		radioButton0 = new JRadioButton("0");
		radioButton1 = new JRadioButton("1");
		radioButton2 = new JRadioButton("2");
		radioButton3 = new JRadioButton("3");
		radioButton4 = new JRadioButton("4");
		radioButton5 = new JRadioButton("5");
		radioButton6 = new JRadioButton("6");
		radioButton7 = new JRadioButton("7");
		radioButton8 = new JRadioButton("8");
		radioButton9 = new JRadioButton("9");
		radioButton10 = new JRadioButton("10");
		noteGroup.add(radioButton0);
		noteGroup.add(radioButton1);
		noteGroup.add(radioButton2);
		noteGroup.add(radioButton3);
		noteGroup.add(radioButton4);
		noteGroup.add(radioButton5);
		noteGroup.add(radioButton6);
		noteGroup.add(radioButton7);
		noteGroup.add(radioButton8);
		noteGroup.add(radioButton9);
		noteGroup.add(radioButton10);
		
		panel.add(titleLabel);
		
		panel.add(radioButton0);
		panel.add(radioButton1);
		panel.add(radioButton2);
		panel.add(radioButton3);
		panel.add(radioButton4);
		panel.add(radioButton5);
		panel.add(radioButton6);
		panel.add(radioButton7);
		panel.add(radioButton8);
		panel.add(radioButton9);
		panel.add(radioButton10);
		
		panel.add(criterionsLabel);
		
		panel.add(textZone);
	}
}
