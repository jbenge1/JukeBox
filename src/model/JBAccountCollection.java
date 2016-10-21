/**
 * @author: Justin Benge
 * Date: Oct 6, 2016
 * Compiler: compiled using javac 
 * File Name: JBAccountCollection.java
 * Description:
 */

package model;

import java.io.Serializable;
import java.util.HashMap;

public class JBAccountCollection implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String, JBAccount> accounts;
	private AccountVerifier theVerifier;
	
	
	private JBAccount Chris = new JBAccount("Chris", "1", 0, 1500);
	private JBAccount Devon = new JBAccount("Devon", "22", 0, 1500);
	private JBAccount River = new JBAccount("River", "333", 0, 1500);
	private JBAccount Ryan = new JBAccount("Ryan", "4444", 0, 1500);
	
	/**
	 * 
	 */
	public JBAccountCollection(){
		accounts = new HashMap<String, JBAccount>();
		theVerifier = new AccountVerifier();
		accounts.put("Chris", Chris);
		accounts.put("Devon", Devon);
		accounts.put("River", River);
		accounts.put("Ryan", Ryan);
		
		theVerifier.addAccount("Chris", "1");
		theVerifier.addAccount("Devon", "22");
		theVerifier.addAccount("River", "333");
		theVerifier.addAccount("Ryan", "4444");
	}
	/**
	 * For persistence
	 */
	public JBAccountCollection(HashMap<String, JBAccount> aFile) {
		accounts = aFile;
		theVerifier = new AccountVerifier();
		// in order to add each account to the verifier
		for(String key : accounts.keySet()) {
			JBAccount val = accounts.get(key);
			String name = val.getID();
			String pass = val.getPassword();
			theVerifier.addAccount(name, pass);
		}
	}
	
	/**
	 * 
	 * @param accountName
	 * @param account
	 * @return
	 */
	public boolean add(String accountName, JBAccount account){
		if(!theVerifier.addAccount(accountName, account.getPassword()))
			return false;
		accounts.put(accountName, account);
		return true;
	}
	
	
	public JBAccount getAccount(String id){
		return accounts.get(id);
	}
	
	/**
	 * 
	 * @param name
	 * @param pass
	 * @return
	 */
	public boolean verify(String name, String pass){
		return theVerifier.verify(name, pass);
	}
	
	public Object getFileInput() {
		String toFile = "";
		for(int i = 0; i < accounts.size() - 1; i++)
			toFile += accounts.get(i).getFileInput() + "\n";
		return toFile;
	}
}//end class
