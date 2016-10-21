/**
 * @author: Justin Benge
 * Date: Oct 12, 2016
 * Compiler: compiled using javac 
 * File Name: JukeBox.java
 * Description:
 */

package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class JukeBox implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SongCollection songs;
	// private AccountVerifier verifier;
	private JBAccountCollection accounts;
	private String baseDir = System.getProperty("user.dir") + System.getProperty("file.separator") + "songfiles"
			+ System.getProperty("file.separator");

	/**
	 * 
	 */
	public JukeBox() {
		songs = new SongCollection();
		// verifier = new AccountVerifier();
		accounts = new JBAccountCollection(); // has the verifier

	}

	/**
	 * Reset song.timePlayed to 0 in the song collection
	 */
	public void resetDate() {
		songs.reset();
	}

	/**
	 * 
	 * @return
	 */
	public JBAccountCollection getAccounts() {
		return accounts;
	}

	public boolean verify(String id, String pass) {
		return accounts.verify(id, pass);

	}

	/**
	 * 
	 * @return
	 */
	public SongCollection getSongs() {
		return songs;
	}

	/**
	 * Adding songList, JBAccountCollection and the songQueue info to a
	 * FileInputStream
	 */
	@SuppressWarnings("resource")
	public void readingFile() {
		try {
			FileInputStream fis = new FileInputStream(baseDir + "JukeBoxSave.txt");
			ObjectInputStream inputFile = new ObjectInputStream(fis);

			songs = (SongCollection) inputFile.readObject();
			accounts = (JBAccountCollection) inputFile.readObject();

		} catch (IOException | ClassNotFoundException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public void saveToFile() {
		try {
			FileOutputStream fos = new FileOutputStream(baseDir + "JukeBoxSave.txt");
			ObjectOutputStream outputFile = new ObjectOutputStream(fos);
			outputFile.writeObject(songs);
			outputFile.writeObject(accounts);
			outputFile.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}