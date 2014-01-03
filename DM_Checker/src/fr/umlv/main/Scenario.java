package fr.umlv.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import fr.umlv.IHM.IHM;
import fr.umlv.archive.ArchiveOptionChecker;
import fr.umlv.archive.ZipFileFormat;
import fr.umlv.junit.Junit;
import fr.umlv.util.Messages;
import fr.umlv.util.Options;

/**
 * this class launch the good test chose by the user.
 * 
 * @author Gimeno and Bourgain
 * 
 */
public class Scenario {

	private final Options options;
	private final ArchiveOptionChecker optCheck;
	private Junit junit;

	public Scenario(Options opt) {
		options = opt;
		optCheck = new ZipFileFormat();
		junit = new Junit();
	}

	// echec option force : pas extratiction et archive refuse
	private boolean optionForceRefused(String option, String param) {
		System.err.println(Messages.getOutputString(option, param));
		return false;
	}

	private void optionRefused(String option, String param) {
		System.out.println(Messages.getOutputString(option, param));
	}

	/**
	 * Check all the option on an archive. It's return a boolean to know if the
	 * rar can be extract. need the path of the rar
	 * 
	 * @param path
	 */
	public boolean checkOptionsArchive(String path) {
		System.err.println(path);
		// test one top
		if (options.isOneTop() || options.isForceOneTop()) {
			System.out.println(optCheck.oneTop(path));
			if (!optCheck.oneTop(path))
				if (options.isForceOneTop()) {
					return optionForceRefused("onetop", "");
					// sortie car option refuse
				} else {
					optionRefused("onetop", "");
				}
		}
		// check opt I et x

		for (String s : options.getForbidden()) {
			if (optCheck.existe(path, s))
				optionRefused("i", s);
		}

		for (String s : options.getForceinterdit()) {
			if (optCheck.existe(path, s))
				return optionForceRefused("i", s);
		}

		for (String s : options.getBe()) {
			if (!optCheck.existe(path, s))
				optionRefused("i", s);
		}

		for (String s : options.getForceBe()) {
			if (!optCheck.existe(path, s))
				return optionForceRefused("i", s);
		}

		// endswith
		for (String s : options.getEndWith()) {
			if (!optCheck.endsWith(path, s)) {
				optionRefused("e", s);
			}
		}

		for (String s : options.getForceEndsWith()) {
			if (!optCheck.endsWith(path, s)) {
				return optionForceRefused("e", s);
			}
		}

		// startwiths
		for (String s : options.getBeginWith()) {
			if (!optCheck.beginsWith(path, s))
				optionRefused("b", s);
		}

		for (String s : options.getForceBeginsWith()) {
			if (!optCheck.beginsWith(path, s)) {
				return optionForceRefused("b", s);
			}
		}
		if (options.isVerbose()) {
			System.out.println(path + " OK");
		}
		return true;
	}

	/**
	 * check all the archive in the path and extract there if they are OK Need
	 * the path of the rar of rar
	 * 
	 * @param path
	 */
	public void checkArchiveSerial(String path) {
		// init : extrait l'archive d'archive
		ArrayList<String> paths;
		ArrayList<String> tmp = new ArrayList<>();
		Objects.requireNonNull(paths = optCheck.getPathArchive(path));
		if (paths.size() == 0) {
			System.err.println("pas d'archive d'archive !");
			return;
		}
		for (int i = 0; i < paths.size(); i++) {
			tmp.add(paths.get(i).replace("/", File.separator));
			if (options.isVerbose()) {
				System.out.println(tmp.get(i));
			}
		}
		String newpathdestination = optCheck.extract(path,
				options.getDestination());
		;
		// paths.remove(0);
		// lance le traitement
		// System.out.println("lol"+newpathdestination+File.separator+tmp.get(0));
		for (String p : tmp) {
			if ((checkOptionsArchive(newpathdestination + File.separator + p))
					&& (optCheck.isValid(p))) {
				optCheck.extract(newpathdestination + File.separator + p,
						options.getDestination());
			}
		}
	}

	/**
	 * init the parameter which are need to the HMI
	 * 
	 * @return List of the param needed
	 */
	public ArrayList<String> initIHM() {
		String path = options.getSource();
		ArrayList<String> p = optCheck.getPathArchive(path);

		// verif que la liste ne soit pas vide.
		if (p.size() > 0) {
			optCheck.extract(path, p.get(0));
			p.remove(0);
			return p;
		} else {
			// pas top
			return null;
		}
	}

	/**
	 * for the option -3 of DM Checker
	 */
	public void jUnitTesting() {

		// on lance les jUnits
		try {
			junit.execute(options.getJUnitPath(), options.getSource(),
					options.getResultatPath(), true);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * for the option -4 of DM Checker
	 */
	private boolean ihmMode() {

		// String[] param = options.getParam();
		// verfie qu'il y a bien 3 parametres passe en option.
		/*
		 * for(String s : param) System.out.println(s);
		 */
		if (options.getParam().length != 4) {
			if (options.isVerbose()) {
				System.err.println("nombre de parametres invalide");
			}
			return false;
		}
		if (options.isVerbose()) {
			System.out.println("Lancement IHM");
		}
		for (String s : optCheck.getPathArchive(options.getSource())) {
			System.out.println(s);
		}
		// lancement de l'ihm, puissance maximum M. Solo
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame fenetre = new IHM(options.getParam());
				fenetre.setVisible(true);
			}
		});
		return true;
	}

	/**
	 * Entry point of the class launch the good process give with the option
	 */
	public void start() {
		optCheck.setVerbose(options.isVerbose());
		// check si il y a bien une source
		if (options.getSource().compareTo("") == 0) {
			System.err.println("Dossier source invalide");
			return;
		}
		// System.out.println(options.getMode());
		// lance un scenario
		switch (options.getMode()) {
		case 1:
			checkOptionsArchive(options.getSource());
			break;
		case 2:
			checkArchiveSerial(options.getSource());
			break;
		case 3:
			jUnitTesting();
			break;
		case 4:
			ihmMode();
			break;
		default:
			System.err.println("Erreur : pas de scenario associe");
		}
	}
}
