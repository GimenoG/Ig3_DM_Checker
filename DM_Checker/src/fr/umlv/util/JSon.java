package fr.umlv.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JSon {

	/**
	 * 
	 * @param nameTeam
	 *            Nom de l'équipe du tu évalues
	 * @param nameCritere
	 *            Nom du critère
	 * @param note
	 *            La note que tu mets au critère
	 * @param path
	 *            Le chemin du fichier
	 * @param end
	 *            Si c'est le dernier critère que tu enregistres
	 */

	public void write(String nameTeam, String nameCritere, String note,
			String path, boolean end) {

		String cheminDuFichier = path;

		String jsonContentFirstWrite = "\"Rating projects\":[";
		String jsonContentAddWrite = "{\"Binomial name\": \"" + nameTeam
				+ "\",\"Criterion\": \"" + nameCritere + "\",\"Score\": \""
				+ note + "\"},";

		String jsonContentEndWrite = "{\"Binomial name\": \"" + nameTeam
				+ "\",\"Criterion\": \"" + nameCritere + "\",\"Score\": \""
				+ note + "\"}]";

		File file = new File(cheminDuFichier);

		try {
			FileWriter writer = null;

			if (!file.exists()) {
				file.createNewFile();
				writer = new FileWriter(file);
				writer.write(jsonContentFirstWrite);
				writer.flush();
				writer.close();
			}
			if (!end) {
				writer = new FileWriter(file, true);
				writer.write(jsonContentAddWrite);
			} else {
				writer = new FileWriter(file, true);
				writer.write(jsonContentEndWrite);
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			System.out.println("Erreur: impossible de créer le fichier '"
					+ cheminDuFichier + "'");
		}
	}

	public static void main(String[] args) {
		JSon j = new JSon();
		j.write("Gimeno", "Critere de la mort", "15/20",
				"/Users/Gui/Downloads/jsondelamort", false);
		j.write("Gimeno", "Critere de la mortadel", "18/20",
				"/Users/Gui/Downloads/jsondelamort", false);
		j.write("Gimeno", "Critere de la fin", "13/20",
				"/Users/Gui/Downloads/jsondelamort", true);
	}

}