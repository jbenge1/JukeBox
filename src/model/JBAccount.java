/**
 * @author: Justin Benge
 * Date: Oct 6, 2016
 * Compiler: compiled using javac 
 * File Name: JBAccounts.java
 * Description:
 */

package model;

import java.io.Serializable;

public class JBAccount implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id, password;
	private double timeLeft;
	private AccountVerifier verifer;
	private int songsPlayed;

	// added variable for songsPlayed and timeLeft for persistence
	public JBAccount(String id, String password, int count, int time) {
		this.id = id;
		this.songsPlayed = count;
		this.password = password;
		this.timeLeft = time; // i think we want our time in seconds...

	}

	/**
	 * 
	 * @param song
	 */
	public void playedSong(Song song) {
		songsPlayed++;
		timeLeft -= song.getSongLength();
	}

	/**
	 * Used to get id as a string for the verifier
	 */
	public String getID() {
		return this.id;
	}

	/**
	 * 
	 * @return
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * 
	 * @return
	 */
	public int getSongsPlayed() {
		return songsPlayed;
	}

	/**
	 * 
	 */
	public void reset() {
		songsPlayed = 0;
	}

	/**
	 * 
	 * @return
	 */
	public double getTimeLeft() {
		return timeLeft;
	}

	/**
	 * Used to display current user info
	 * @return
	 */
	public String getInfo() {
		int hour = (int) timeLeft / 60;
		int min = (int) timeLeft % 60;
		int sec = min % 60;
		return id + ": " + songsPlayed + " played, " + hour + ":" + min + ":" + sec + " time left";
	}

	public String getFileInput() {
		return this.id + "_" + this.password + "_" + this.songsPlayed + "_" + this.timeLeft + "_";
	}
}
