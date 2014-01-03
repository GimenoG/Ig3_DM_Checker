package fr.umlv.IHM;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import fr.umlv.util.Log;
import fr.umlv.util.Regex;
import fr.umlv.util.TxtExploreur;

/**
 * This class is the interface human/ machine of the project DM Checker (option
 * -4). She use swing.
 * 
 * @author Gimeno & Bourgain
 * 
 */

public class IHM extends JFrame {

	private JLabel nameLableTop;

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
	private ArrayList<String[]> criterion;
	private final String reportPath;
	private final ArrayList<String[]> data;
	private boolean execlaunch = false;
	private String exename;
	private ArrayList<IHMCreator> cr;
	private ArrayList<String> existingReport;

	/**
	 * The constructor need the param give with the option. First is the exe
	 * name Second is the path of the report Third is the data of the student
	 * 
	 * @param param
	 *            String[3]
	 */
	public IHM(String[] param) {
		super("DMChecker");
		indice = 0;
		exename = param[0];
		reportPath = param[1];
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		data = (ArrayList<String[]>) TxtExploreur.getDataFile(reportPath
				+ File.separator + param[2]);
		criterion = (ArrayList<String[]>) TxtExploreur.getCriterions(reportPath
				+ File.separator + param[3]);
		cr = new ArrayList<>();
		if ((existingReport = (ArrayList<String>) TxtExploreur
				.getReportStored(reportPath + File.separator + "nots.txt"))
				.isEmpty()) {
			try {
				Log.writeText(reportPath + File.separator + "nots.txt", "");
			} catch (IOException e) {
				System.err.println("impossible de creer le fichier "
						+ reportPath + File.separator + "nots.txt");
			}
		}

		buildWindow();
		editNameLabelTop(Regex.idName(data.get(indice)[2]));
		setExe();
		setReport();
	}

	private void buildWindow() {
		WindowListener wl = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		addWindowListener(wl);
		setSize(600, 800);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setContentPane(buildContendPanel());

	}

	private JPanel buildContendPanel() {

		nameLableTop = new JLabel(
				"<html><body>name forname<br>name forname</body></html>");
		nameLableTop.setPreferredSize(new Dimension(560, 40));

		buttonPrevious = new JButton(new ButtonListener(this, "<"));
		buttonNext = new JButton(new ButtonListener(this, ">"));
		buttonRun = new JButton(new ButtonListener(this, "Run"));
		buttonPrevious.setPreferredSize(new Dimension(50, 30));
		buttonNext.setPreferredSize(new Dimension(50, 30));
		buttonRun.setPreferredSize(new Dimension(450, 30));

		menubar = new JMenuBar();
		menu = new JMenu("Menu");
		save = new JMenuItem(new MenuListener(this, "Sauvegarder"));
		quit = new JMenuItem(new MenuListener(this, "Quitter"));
		menu.add(save);
		ArrayList<String> name = new ArrayList<>();
		for (int i = 0; i < data.size(); i++) {
			name.add(data.get(i)[2]);
		}
		menu.add(IHMCreator.setMenuName(name, this));

		menu.add(quit);

		menubar.add(menu);

		setJMenuBar(menubar);
		panel.add(buttonPrevious);
		panel.add(buttonRun);
		panel.add(buttonNext);
		panel.add(nameLableTop);
		for (String[] s : criterion) {
			cr.add(new IHMCreator(panel, s[1], s[2]));
		}
		return panel;
	}

	/**
	 * 
	 * Edit the message at the top of the window with the name of the student
	 * curentlly set
	 * 
	 */
	public void editNameLabelTop() {
		nameLableTop.setText(data.get(indice)[2]);
	}

	/**
	 * Edit the message at the top of the window with a specific text
	 * 
	 * @param texte
	 *            String
	 */
	public void editNameLabelTop(String texte) {
		nameLableTop.setText(texte);
	}

	/**
	 * unset all the criterions (nots and comments) of the project
	 */

	public void cleanSheet() {
		editNameLabelTop(Regex.idName(getCurrentName()));
		for (IHMCreator c : cr) {
			c.clean();
		}
	}

	/**
	 * save the report in the file nots.
	 * 
	 * @see TxtExploreur, IHMCreator
	 */
	public void saveReport() {
		StringBuilder sb = new StringBuilder();
		sb.append(data.get(indice)[0] + ":");
		sb.append(data.get(indice)[2] + ":");
		for (IHMCreator c : cr) {
			sb.append(c.getNote() + ":" + c.getComment() + ":");
		}
		TxtExploreur.saveReport(reportPath + File.separator + "nots.txt", sb
				.toString().substring(0, sb.toString().length() - 2), data
				.get(indice)[0]);
	}

	/**
	 * Set a report with the good criterion if he exsit
	 * 
	 * @see TxtExploreur, IHMCreator
	 */
	public void setReport() {
		ArrayList<String> r = (ArrayList<String>) TxtExploreur.getReport(
				data.get(indice)[0], reportPath + File.separator + "nots.txt");
		if (r != null) {
			int i = 0;

			for (IHMCreator c : cr) {
				c.setNot(r.get(i));
				i++;
				c.setComment(r.get(i));
				i++;
			}

		}
	}

	/**
	 * return the button
	 * 
	 * @see JButton
	 * @return JButton
	 */
	public JButton getButtonPrevious() {
		return buttonPrevious;
	}

	public ArrayList<String[]> getCriterions() {
		return criterion;
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

	public List<String[]> getData() {
		return data;
	}

	public JMenuItem getQuit() {
		return quit;
	}

	public void setIndice(int i) {
		indice = i;
	}

	public int getIndice() {
		return indice;
	}

	public String getCurrentName() {
		return data.get(indice)[2];
	}

	/**
	 * update the current student
	 */
	public void incrementIndice() {
		if (indice < data.size()) {
			indice++;
		}
	}

	/**
	 * update the current student
	 */
	public void decrementIndice() {
		if (indice > 0) {
			indice--;
		}
	}

	/**
	 * launch the exec which is currently set (must be a jar)
	 */
	public void launchExe() {
		execlaunch = true;
		try {
			proc = Runtime.getRuntime().exec(new String("java -jar " + exec));
		} catch (IOException e) {
			System.err.println("Impossible de lancer le programme " + exec
					+ "\n");
		}
	}

	/**
	 * destroy the exe with is currently run
	 */
	public void stopExe() {
		if (execlaunch) {
			proc.destroy();
			execlaunch = false;
		}
	}

	/**
	 * set the exe of a student
	 */
	public void setExe() {
		exec = reportPath + File.separator + data.get(indice)[1]
				+ File.separator + exename + ".jar";
	}
}
