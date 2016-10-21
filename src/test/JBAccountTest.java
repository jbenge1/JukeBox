/**
 * @author: Justin Benge
 * Date: Oct 13, 2016
 * Compiler: compiled using javac 
 * File Name: JBAccountTest.java
 * Description:
 */

package test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import model.JBAccountCollection;
import model.Song;

public class JBAccountTest {

	JBAccountCollection accounts = new JBAccountCollection();

	private static String baseDir = System.getProperty("user.dir") + System.getProperty("file.separator") + "songfiles"
			+ System.getProperty("file.separator");
	private Song flute = new Song("flute.aif", "N/A" , baseDir + "flute.aif",6);
	private Song spaceMusic = new Song("spacemusic.au", "N/A" , baseDir + "spacemusic.au",6);
	private Song tada = new Song("tada.wav", "N/A", baseDir + "tada.wav",2);
	
	
	@Test
	public void testGetPassword(){
		assertEquals("1", accounts.getAccount("Chris").getPassword());
		assertEquals("22", accounts.getAccount("Devon").getPassword());
		assertEquals("333", accounts.getAccount("River").getPassword());
		assertEquals("4444", accounts.getAccount("Ryan").getPassword());
	}
	
	@Test 
	public void testPlaySongsandReset(){
		accounts.getAccount("Chris").playedSong(flute);
		
		assertEquals(1,accounts.getAccount("Chris").getSongsPlayed());
		accounts.getAccount("Chris").reset();
		
		assertEquals(0, accounts.getAccount("Chris").getSongsPlayed());
	}
	
	
	@Test
	public void testGetInfo(){
		assertEquals("Chris: 0 played, 25:0:0 time left"  , accounts.getAccount("Chris").getInfo());
	}
}
