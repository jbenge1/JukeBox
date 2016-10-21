/**
 * @author: Justin Benge
 * Date: Oct 14, 2016
 * Compiler: compiled using javac 
 * File Name: JBAccountCollectionTest.java
 * Description:
 */

package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.JBAccount;
import model.JBAccountCollection;

public class JBAccountCollectionTest {

	public JBAccountCollection accounts = new JBAccountCollection();
	
	@Test
	public void testAdd(){
		assertTrue(accounts.add("Justin", new JBAccount("Justin","1234", 0, 1500)));
		
		assertFalse(accounts.add("Justin", new JBAccount("Justin","1234", 0, 1500)));
	}
	
	
	@Test
	public void testGetAccount(){
		assertEquals(accounts.getAccount("Chris").getPassword(), "1");
		assertEquals(accounts.getAccount("River").getPassword(), "333");
		assertEquals(accounts.getAccount("Ryan").getPassword(), "4444");
	}
	
	
	@Test
	public void testVerify(){
		assertTrue(accounts.verify("Chris", "1"));
		assertTrue(accounts.verify("Ryan", "4444"));
		assertTrue(accounts.verify("Devon", "22"));
		
		assertFalse(accounts.verify("Justin", "123"));
		assertFalse(accounts.verify("Chris", "11"));
	}
}
