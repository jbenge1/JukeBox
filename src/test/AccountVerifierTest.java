/**
 * @author: Justin Benge
 * Date: Oct 13, 2016
 * Compiler: compiled using javac 
 * File Name: AccountVerifierTest.java
 * Description:
 */

package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.AccountVerifier;

public class AccountVerifierTest {

	AccountVerifier verify = new AccountVerifier();
	
	@Test
	public void testAddDupAccounts(){
		assertTrue(verify.addAccount("Chris", "1"));
		assertTrue(verify.addAccount("Ryan", "2"));
		assertTrue(verify.addAccount("Paul", "3"));
		
		assertFalse(verify.addAccount("Chris", "1"));
	}
	
	
	@Test
	public void testVerify(){
		assertFalse(verify.verify("Justin", "not here"));
		assertFalse(verify.verify("Chris", "2"));
		
		assertFalse(verify.verify("Paul", "3"));
	}
}
