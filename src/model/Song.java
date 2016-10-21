/**
 * @author: Justin Benge
 * Date: Oct 6, 2016
 * Compiler: compiled using javac 
 * File Name: Song.java
 * Description:
 */

package model;

import java.io.File;
import java.io.Serializable;

import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;

public class Song implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name, artist;
	private double length;
	private int timesPlayed;
	private File file;
	private static boolean songPlaying;

	public Song(String name, String artist, String fileName, double length) {
		this.name = name;
		this.artist = artist;
		this.file = new File(fileName);
		this.length = length;
		timesPlayed = 0;
		songPlaying = false;
	}

	public double getSongLength() {
		return this.length;
	}

	/**
	 * 
	 * @return
	 */
	public String getFileAsString() {
		return this.file + "";
	}

	/**
	 * We will need to use this method when we actually play a song to increment
	 * the times it as been played
	 * 
	 * @param waiter2
	 * 
	 * @return true if we can play the song, false otherwise
	 */
	public boolean playSong() {
		if (timesPlayed > 2)
			return false;

		//songPlaying = true;
		//ObjectWaitingForSongToEnd waiter = new ObjectWaitingForSongToEnd();
		timesPlayed++;
		//SongPlayer.playFile(waiter, this.getFileAsString());

		return true;

	}

	/**
	 * 
	 * @return
	 */
	public boolean isPlaying() {
		return songPlaying;
	}

	/**
	 * When a new day starts, we need to reset all the songs so we can play them
	 * again
	 */
	public void reset() {
		timesPlayed = 0;
	}

	@Override
	public String toString() {
		return "Name: " + name + "\nAritst: " + artist;
	}

	public int getTimesPlayed() {
		return timesPlayed;
	}

	/**
	 * This inner class allows us to have an callback function that receive a
	 * songFinishedPlaying message whenever an audio file has been played.
	 * 
	 * Note: this is a static class because it is being called from main, which
	 * is a static context. If you are using a GUI, you won't need static
	 */
	private class ObjectWaitingForSongToEnd implements EndOfSongListener {

		public void songFinishedPlaying(EndOfSongEvent eosEvent) {
			System.out.println("Finished " + eosEvent.fileName() + ", " + eosEvent.finishedDate() + ", "
					+ eosEvent.finishedTime());
			songPlaying = false;

		}
	}

	/**
	 * 
	 * @return
	 */
	public String getArtist() {
		return this.artist;
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	public String getFileInput() {
		return this.name + "_" + this.artist + "_" + this.file + "_"
				+ this.length + "_";
	}

}
