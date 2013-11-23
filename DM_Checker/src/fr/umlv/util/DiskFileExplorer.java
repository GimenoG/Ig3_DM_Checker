package fr.umlv.util;

import java.io.File;

public class DiskFileExplorer {

    private String initialpath = "";
    private Boolean recursivePath = false;
    public int filecount = 0;
    public int dircount = 0;

/**
 * Constructeur
 * @param path chemin du répertoire
 * @param subFolder analyse des sous dossiers
 */
    public DiskFileExplorer(String path, Boolean subFolder) {
        super();
        this.initialpath = path;
        this.recursivePath = subFolder;
    }

    public void list() {
        this.listDirectory(this.initialpath);
    }

    private void listDirectory(String dir) {
        File file = new File(dir);
        File[] files = file.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory() == true) {
                    System.out.println("Dossier" + files[i].getAbsolutePath());
                    this.dircount++;
                } else {
                    System.out.println("Fichier" + files[i].getName());
                    this.filecount++;
                }
                if (files[i].isDirectory() == true && this.recursivePath == true) {
                    this.listDirectory(files[i].getAbsolutePath());
                }
            }
        }
    }

    /**
     * Exemple : lister les fichiers dans tous les sous-dossiers
     * @param args
     */
   /* public static void main(String[] args) {
        String pathToExplore = "/Users/Gui/Documents/Lambda/";
        DiskFileExplorer diskFileExplorer = new DiskFileExplorer(pathToExplore, true);
        Long start = System.currentTimeMillis();
        diskFileExplorer.list();
        System.out.println("----------");
        System.out.println("Analyse de " + pathToExplore + " en " + (System.currentTimeMillis() - start) + " mses");
        System.out.println(diskFileExplorer.dircount + " dossiers");
        System.out.println(diskFileExplorer.filecount + " fichiers");
    }*/
}