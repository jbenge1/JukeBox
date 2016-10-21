/**
 * @author: Justin Benge
 * Date: Oct 12, 2016
 * Compiler: compiled using javac 
 * File Name: TheDecider.java
 * Description: decides if a song can be played on this date
 */

package model;

import java.io.Serializable;
import java.time.LocalDate;

public class TheDecider implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LocalDate today;
	
	public TheDecider(){
		today = LocalDate.now();
	}
	
	/**
	 * 
	 * @param temp
	 * @return
	 */
	public boolean compareDate(){
		if(LocalDate.now() != today){
			today = LocalDate.now();
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param song
	 * @param account
	 * @return
	 */
	public boolean decide(Song song, JBAccount account){
		if(song.getTimesPlayed() >= 3)
			return false;
		if(account.getTimeLeft()<song.getSongLength())
			return false;
		if(account.getSongsPlayed()>=3)
			return false;
		return true;
	}
}
