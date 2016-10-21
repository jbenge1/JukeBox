/**
 * @author: Justin Benge
 * Date: Oct 13, 2016
 * Compiler: compiled using javac 
 * File Name: SongQueueTest.java
 * Description:
 */

package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Song;
import model.SongQueue;

public class SongQueueTest {
	
	SongQueue playList = new SongQueue();
	private Song flute = new Song("flute.aif", "N/A" , baseDir + "flute.aif",6);
	private Song spaceMusic = new Song("spacemusic.au", "N/A" , baseDir + "spacemusic.au",6);
	private Song tada = new Song("tada.wav", "N/A", baseDir + "tada.wav",2);
	private static String baseDir = System.getProperty("user.dir") + System.getProperty("file.separator") + "songfiles"
			+ System.getProperty("file.separator");
	
	@Test
	public void testAddSong(){
		playList.add(flute);
	
		
		assertTrue(flute.equals(playList.getTopSong()));
		
		playList.playPlayList();
		
		playList.add(spaceMusic);
		playList.playPlayList();
	}

}
