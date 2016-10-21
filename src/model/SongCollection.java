/**
 * @author: Justin Benge
 * Date: Oct 6, 2016
 * Compiler: compiled using javac 
 * File Name: SongCollection.java
 * Description:
 */

package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class SongCollection implements TableModel, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static String baseDir = System.getProperty("user.dir") + System.getProperty("file.separator") + "songfiles"
			+ System.getProperty("file.separator");

	private HashMap<String, Song> songLibrary;
	private Song flute = new Song("flute.aif", "N/A", baseDir + "flute.aif", 6);
	private Song spaceMusic = new Song("spacemusic.au", "N/A", baseDir + "spacemusic.au", 6);
	private Song tada = new Song("tada.wav", "N/A", baseDir + "tada.wav", 2);
	//add more songs....

	public SongCollection() {
		songLibrary = new HashMap<String, Song>();

		songLibrary.put("flute.aif", flute);
		songLibrary.put("spacemusic.au", spaceMusic);
		songLibrary.put("tada.wav", tada);

		/*
		 * Set<String> keys = songLibrary.keySet(); for(String key:keys){
		 * songLibrary.get(key); }
		 */
	}

	/**
	 * For persistence
	 */
	public SongCollection(HashMap<String, Song> aFile) {
		songLibrary = aFile;
	}

	/**
	 * Add a song to our songLibrary with the name of the song as the key
	 * 
	 * @param name
	 *            the key
	 * @param song
	 *            the value
	 */
	public void put(String name, Song song) {
		songLibrary.put(name, song);
	}

	/**
	 * Get some specific song
	 * 
	 * @param name
	 *            the name of the song
	 * @return the song
	 */
	public Song getSong(String name) {
		return songLibrary.get(name);
	}

	/**
	 * 
	 */
	public void reset() {
		Set<String> keys = songLibrary.keySet();
		for (String key : keys)
			songLibrary.get(key).reset();
	}

	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Class<?> getColumnClass(int argv) {
		if (argv <= 1)
			return String.class;
		if (argv == 2)
			return double.class;
		else
			return int.class;
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public String getColumnName(int argv) {
		if (argv == 0)
			return "Name";
		if (argv == 1)
			return "Artist";
		if (argv == 2)
			return "Length";
		else
			return "Times Played";
	}

	@Override
	public int getRowCount() {
		return songLibrary.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		Set<String> temp = songLibrary.keySet();
		String key = (String) temp.toArray()[arg0];
		Song song = songLibrary.get(key);

		if (arg1 == 0)
			return song.getName();
		if (arg1 == 1)
			return song.getArtist();
		if (arg1 == 2)
			return song.getSongLength();
		else
			return song.getTimesPlayed();
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
	}

	public String getFileInput() {
		String toFile = "";
		for (int i = 0; i < songLibrary.size() - 1; i++) {
			toFile += songLibrary.get(i).getFileInput() + "\n";
			//toFile += songLibrary.
		}
		return toFile;
	}

}
