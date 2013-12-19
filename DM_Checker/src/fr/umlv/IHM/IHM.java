package fr.umlv.IHM;

import javax.swing.*;

import java.awt.event.*;

import fr.umlv.util.Log;
import fr.umlv.util.Regex;
import fr.umlv.util.TxtExploreur;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class IHM extends JFrame{
	
	private JLabel nameLableTop;
	private JLabel titleIHMComment;
	
	private JButton buttonPrevious;
	private JButton buttonNext;
	private JButton buttonRun;

	private JMenuBar menubar;
	private JMenu menu;
	private JMenuItem openRepository;
	private JMenuItem save;
	private JMenuItem list;
	private JMenuItem quit;
	
	private JPanel panel;
	
	
	private String exec;
	private int indice;
	private Process proc;
	private ArrayList<String []> criterion;
	private final String reportPath;
	private final ArrayList<String []> data;
	private boolean execlaunch=false;
	private ArrayList<IHMCreator> cr;
	
	public IHM(String [] param){
		super("DMChecker");
		indice=0;
		exec=param[0];
		reportPath=param[1];
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		data=(ArrayList<String[]>) TxtExploreur.getDataFile(reportPath+File.separator+param[2]);
		criterion=(ArrayList<String[]>) TxtExploreur.getCriterions(reportPath+File.separator+param[3]);
		cr = new ArrayList<>();
		try {
			Log.writeText(reportPath+File.separator+"nots.txt", "");
		} catch (IOException e) {
			System.err.println("impossible de creer le fichier "+reportPath+File.separator+"nots.txt");
		}
		buildWindow();
		initWindow();
	}

	
	
	
	private void initWindow(){
		editNameLabelTop(Regex.idName(data.get(indice)[2]));
	}
	
	private void buildWindow(){
			WindowListener wl = new WindowAdapter() {
				public void windowClosing(WindowEvent e){
					System.exit(0);
				}
			};
			addWindowListener(wl);
			setSize(600,800);
			setLocationRelativeTo(null);//center the window. Note : may have some probl�me if the user have two monitor.
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close the soft on the event close
			setResizable(false);
			setContentPane(buildContendPanel());
			
			
	}
	private JPanel buildContendPanel(){
		
		//Labels create zone
		nameLableTop = new JLabel("<html><body>name forname<br>name forname</body></html>");
		nameLableTop.setPreferredSize(new Dimension(550, 40));
		
		//Buttons create zone
		buttonPrevious = new JButton(new ButtonListener(this, "<"));
		buttonNext = new JButton(new ButtonListener(this, ">"));
		buttonRun = new JButton(new ButtonListener(this, "Run"));
		buttonPrevious.setPreferredSize(new Dimension(50, 30));
		buttonNext.setPreferredSize(new Dimension(50, 30));
		buttonRun.setPreferredSize(new Dimension(450, 30));
		
		//Menu creation
		menubar = new JMenuBar();
		menu  = new JMenu("Menu");
		openRepository = new JMenuItem("Ouvrir repertoire");
		save = new JMenuItem("Sauvegarder");
		list = new JMenuItem("Change executable");
		quit = new JMenuItem("Quitter");
		menu.add(openRepository);
		menu.add(save);
		menu.add(list);
		//menu.add(exportNote);
		menu.add(quit);
		//menu.add(former);
		menubar.add(menu);
	


		//ligne de l'ihm (en nombre de block)
		//line1 ?
		setJMenuBar(menubar);
		//line2 30
		panel.add(buttonPrevious);
		panel.add(buttonRun);
		panel.add(buttonNext);
		//line3 40
		panel.add(nameLableTop);
		//line4 20
		for(String [] s : criterion){
			cr.add(new IHMCreator(panel, s[1], s[2]));
		}
		
		return panel;
	}
	/**
	 * 
	 * Edit the message at the top of the window
	 * 
	 * @param texte
	 */
	public void editNameLabelTop(){
		nameLableTop.setText(data.get(indice)[2]);
	}
	public void editNameLabelTop(String texte){
		String message = "<html><body>"+texte+"</body></html>";
		nameLableTop.setText(message);
	}

	
	
	
	public void cleanSheet(){
		editNameLabelTop(Regex.idName(getCurrentName()));
		for(IHMCreator c : cr){
			c.clean();
		}
	}
	/**
	 * save the rapport
	 */
	public void saveReport(){
		String [] report = new String[5];
		report[0]=nameLableTop.getText();

		TxtExploreur.saveReport(reportPath, report);
	}
	public void setReport(){
		String [] r= TxtExploreur.getReport(reportPath, nameLableTop.getText());
		if (r!=null){
			//TODO set radiogroup

		}
	}
	public JButton getButtonPrevious() {
		return buttonPrevious;
	}
	
	public JButton getButtonNext() {
		return buttonNext;
	}

	public JButton getButtonRun() {
		return buttonRun;
	}
	public JMenu getMenu() {
		return menu;
	}

	public JMenuItem getOpenRepository() {
		return openRepository;
	}

	public JMenuItem getSave() {
		return save;
	}
	public JMenuItem getListItem() {
		return list;
	}

	public JMenuItem getQuit() {
		return quit;
	}

	public int getIndice(){
		return indice;
	}
	public String getCurrentName(){
		return data.get(indice)[2];
	}
	public void incrementIndice(){
		if(indice<data.size()){
			indice++;
		}
	}
	public void decrementIndice(){
		if(indice>0){
			indice--;
		}
	}
	/**
	 * launch the exec which is currently set (must be a jar)
	 */
	public void launchExe(){
		//TODO test /!\ -> lancement d'un jar.
		execlaunch=true;
		try {
			//proc = Runtime.getRuntime().exec("cmd.exe", "/C", "dir C:\\ >fichier.txt" );
			proc = Runtime.getRuntime().exec(new String("java -jar "+reportPath+File.separator+exec+".jar"));
		} catch (IOException e) {
			System.err.println("Impossible de lancer le programme "+exec+"\n");
		}
	}
	public void stopExe(){
		if(execlaunch){
			proc.destroy();
			execlaunch=false;
		}
	}
	public void setExe(String pathExe){
			exec=pathExe;
	}
}
