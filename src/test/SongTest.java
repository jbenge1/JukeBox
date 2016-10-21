/**
 * @author: Justin Benge
 * Date: Oct 11, 2016
 * Compiler: compiled using javac 
 * File Name: SongTest.java
 * Description:
 */

package test;


import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import model.Song;
import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;

public class SongTest {


	public static String baseDir = System.getProperty("user.dir") + System.getProperty("file.separator") + "songfiles"
			+ System.getProperty("file.separator");
	
	
	Song flute = new Song("flute.aif", "N/A" , baseDir + "flute.aif",6);
	Song spaceMusic = new Song("SpaceMusic.au", "N/A" , baseDir + "spacemusic.au",6);
	Song tada = new Song("Tada.wav", "N/A", baseDir + "tada.wav",2);
	
	@Test
	public void testGetFileAsString(){
		assertEquals(baseDir + "flute.aif", flute.getFileAsString());
		assertEquals(baseDir + "spacemusic.au" , spaceMusic.getFileAsString());
		assertEquals(baseDir + "tada.wav" , tada.getFileAsString());
				
	}
	
	
	@Test
	public void testCanPlaySong3Times(){
		assertTrue(flute.playSong());
		assertTrue(flute.playSong());
		assertTrue(flute.playSong());
		
		assertFalse(flute.playSong());
		assertEquals(3,flute.getTimesPlayed());
		flute.reset();
		
		assertTrue(flute.playSong());
	}
	
	
	@Test
	public void testToString(){
		assertEquals("Name: flute.aif\nAritst: N/A" , flute.toString());
	}

	
	@Test
	public void testGetLength(){
		assertEquals(6, flute.getSongLength(), 0.001);
		assertEquals(6, spaceMusic.getSongLength(), 0.001);
		assertEquals(2, tada.getSongLength(), 0.001);
		
	}
}
