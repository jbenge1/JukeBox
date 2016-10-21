/**
 * @author: Justin Benge
 * Date: Oct 6, 2016
 * Compiler: compiled using javac 
 * File Name: AccountVerifier.java
 * Description: Holds a list of the valid accounts for the jukebox so we can 
 * easily verify if the user has a valid account
 */

package model;

import java.io.Serializable;
import java.util.HashMap;

public class AccountVerifier implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String, String> accounts;
	
	/**
	 * 
	 */
	public AccountVerifier(){
		accounts = new HashMap<String, String>();
	}
	
	/**
	 * Add an account to the list of existing accounts
	 * 
	 * @param id the id of the account
	 * @param password the password
	 * @return true if the id did not exist, false otherwise
	 */
	public boolean addAccount(String id, String password){
		if(accounts.containsKey(id))
			return false;
		accounts.put(id, password);
		return true;
	}
	
	/**
	 * Check to see if we have a valid id and if the password for the id is also
	 * valid
	 * 
	 * @param id the login name or id
	 * @param password the password
	 * @return true if the pair is valid false otherwise
	 */
	public boolean verify(String id, String password){
		if(!accounts.containsKey(id))
			return false;
		if(accounts.get(id).equals(password))
			return true;
		return false;
	}
	
	/**
	 * Remove an account from the list of valid accounts
	 * (do we need this?)
	 * 
	 * @param id the id to remove
	 *//*
	public void remove(String id){
		accounts.remove(id);
	}*/
}
