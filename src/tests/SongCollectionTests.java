/**
 * @author: Justin Benge
 * Date: Oct 14, 2016
 * Compiler: compiled using javac 
 * File Name: SongCollectionTests.java
 * Description:
 */

package tests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import model.Song;
import model.SongCollection;

public class SongCollectionTests {

	public SongCollection songs = new SongCollection();
	private static String baseDir = System.getProperty("user.dir") + System.getProperty("file.separator") + "songfiles"
			+ System.getProperty("file.separator");
	
	
	private Song flute = new Song("flute.aif", "N/A" , baseDir + "flute.aif",6);
	private Song spaceMusic = new Song("spacemusic.au", "N/A" , baseDir + "spacemusic.au",6);
	private Song tada = new Song("tada.wav", "N/A", baseDir + "tada.wav",2);
	private Song untamableFire = new Song("untamableFire.mp3","N/A", baseDir+"UntameableFire.mp3", 155);
	
	@Test
	public void testPutAndGet(){
		songs.put("UntamableFire", untamableFire);
		
		assertEquals(songs.getSong("UntamableFire").getSongLength(),155,0.001);
		songs.getSong("flute.aif").playSong();
		assertEquals(songs.getSong("flute.aif").getTimesPlayed(),1);
		songs.reset();
		
		assertEquals(songs.getSong("flute.aif").getTimesPlayed(),0);
		
	}
}
