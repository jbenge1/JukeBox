/**
 * @author: Justin Benge
 * Date: Oct 13, 2016
 * Compiler: compiled using javac 
 * File Name: TheDeciderTest.java
 * Description:
 */

package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Song;
import model.TheDecider;
import model.JBAccountCollection;

public class TheDeciderTest {

	TheDecider decider = new TheDecider();
	JBAccountCollection accounts = new JBAccountCollection();
	private static String baseDir = System.getProperty("user.dir") + System.getProperty("file.separator") + "songfiles"
			+ System.getProperty("file.separator");
	private Song flute = new Song("flute.aif", "N/A" , baseDir + "flute.aif",6);
	private Song spaceMusic = new Song("spacemusic.au", "N/A" , baseDir + "spacemusic.au",6);
	private Song tada = new Song("tada.wav", "N/A", baseDir + "tada.wav",2);
	private Song longSongToTestOverTimeLimit = new Song("SomeSong","N/A","N/A",1500);
	
	@Test
	public void testCompareDate(){
		assertTrue(decider.compareDate());
	}
	
	
	@Test
	public void testCanPlaySong(){
		assertTrue(decider.decide(flute,accounts.getAccount("Chris")));
		
		tada.playSong();
		tada.playSong();
		tada.playSong();
		
		assertFalse(decider.decide(tada, accounts.getAccount("Chris")));
		
		accounts.getAccount("Chris").playedSong(spaceMusic);
		accounts.getAccount("Chris").playedSong(spaceMusic);
		accounts.getAccount("Chris").playedSong(spaceMusic);
		
		assertFalse(decider.decide(spaceMusic, accounts.getAccount("Chris")));
		
		accounts.getAccount("Ryan").playedSong(longSongToTestOverTimeLimit);
		assertFalse(decider.decide(flute, accounts.getAccount("Ryan")));
	}
}
