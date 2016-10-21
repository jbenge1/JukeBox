/**
 * @author: Nick Reppe
 * Date: Oct 9, 2016
 * Compiler: compiled using javac 
 * File Name: JukeboxGUI.java
 * Description:
 */
package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.JBAccount;
import model.JukeBox;
import model.Song;
import model.SongQueue;
import model.TheDecider;
import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;

public class JukeBoxGUI extends JFrame implements Serializable {
	private static final long serialVersionUID = 1L;
	private JPanel songPanel; // holds two song buttons
	private JPanel loginPanel; // holds login/out buttons, status fields
	private JTextField loginText;
	private JPasswordField passwordText;
	private JButton loginButton; // login and display status
	private JButton logoutButton; // logout and remove status
	private JLabel statusLabel; // display student status
	private JButton song1;
	private JButton song2;
	private JukeBox jukebox;
	private TheDecider decide; // used to tell if the date is current
	private SongQueue playList;
	private JBAccount account;

	private boolean loggedIn;
	private static String baseDir = System.getProperty("user.dir") + System.getProperty("file.separator") + "songfiles"
			+ System.getProperty("file.separator");

	public static void main(String[] args) {
		JukeBoxGUI juke = new JukeBoxGUI();
	}

	public JukeBoxGUI() {
		jukebox = new JukeBox();
		loggedIn = false;
		decide = new TheDecider(); // create current local date
		playList = new SongQueue();

		String[] choices = { "Yes", "No" };
		int playerChoice = JOptionPane.showOptionDialog(new JFrame(),
				"Would you like to start from the last saved file?", "Starting up Jukebox!", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
		if (playerChoice == 0) {
			try {
				FileInputStream fis = new FileInputStream(baseDir + "JukeBox.txt");
				ObjectInputStream fromFile = new ObjectInputStream(fis);
				jukebox.readingFile();
				fromFile.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		// what happens if the window was closed when a file was playing?

		setUpGUI();
		addListeners();

		setSize(500, 400);
		setVisible(true);
	}

	public void setUpGUI() {
		this.setTitle("This is the GUI");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // need window listener
		this.setLayout(new GridLayout(2, 1));

		// add panels to frame
		songPanel = new JPanel();
		songPanel.setLayout(new BoxLayout(songPanel, BoxLayout.Y_AXIS));
		loginPanel = new JPanel(new GridLayout(4, 2));

		// add song buttons to JPanel, songPanel
		this.add(songPanel);
		song1 = new JButton("Select song 1");
		song2 = new JButton("Select song 2");
		songPanel.add(song1);
		songPanel.add(song2);

		// add bottom JPanel with login info
		this.add(loginPanel);
		loginText = new JTextField(10);
		passwordText = new JPasswordField(10);
		loginButton = new JButton("Login");
		logoutButton = new JButton("Sign Out");
		statusLabel = new JLabel("");

		// add components to JPanel, loginPanel
		loginPanel.add(new JLabel("Login ID"));
		loginPanel.add(loginText);
		loginPanel.add(new JLabel("Password"));
		loginPanel.add(passwordText);
		loginPanel.add(loginButton);
		loginPanel.add(logoutButton);
		loginPanel.add(new JLabel("Status:"));
		loginPanel.add(statusLabel);
	}

	private void addListeners() {
		song1.addActionListener(new song1Listener());
		song2.addActionListener(new song2Listener());

		loginButton.addActionListener(new loginListener());
		logoutButton.addActionListener(new logoutListener());
		
		super.addWindowListener(new aWindowListener());
	}
	
	private class aWindowListener implements WindowListener {

		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent arg0) {
			String[] choices = { "Yes", "No" };
			int playerChoice = JOptionPane.showOptionDialog(new JFrame(),
					"Would you like to save this session?", "Closing down Jukebox!", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
			if(playerChoice == 0) {
				try {
					FileOutputStream fos = new FileOutputStream(baseDir + "JukeBoxSave.txt");
					ObjectOutputStream outputFile = new ObjectOutputStream(fos);
					jukebox.saveToFile();
					outputFile.close();
					System.exit(0);
				} catch(IOException ioe) {
					ioe.printStackTrace();
				}
			}
			else
				System.exit(0);
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

	// clear login, password fields and update statusLabel
	private class logoutListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			account = null;
			loggedIn = false;
			statusLabel.setText("");
			updateDate();
			JOptionPane.showMessageDialog(null, "Logout Successful");
		}
	}

	// update statusLabel, clear login/password field
	private class loginListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

			char[] temp = passwordText.getPassword();
			String str = "";
			for (char ch : temp)
				str += ch;
			if (jukebox.verify(loginText.getText(), str)) {
				account = jukebox.getAccounts().getAccount(loginText.getText());
				JOptionPane.showMessageDialog(null, "Login Succesful");
				statusLabel.setText(account.getInfo());
				updateDate();
				loggedIn = true;
				loginText.setText("");
				passwordText.setText("");
			}

			else
				JOptionPane.showMessageDialog(null, "Incorrect id or password");
		}
	}

	// play song 2
	private class song2Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!loggedIn)
				JOptionPane.showMessageDialog(null, "Not logged in");
			else {
				Song someSong = jukebox.getSongs().getSong("flute.aif");
				if (decide.decide(someSong, account)) {
					playList.add(someSong);
					account.playedSong(someSong);
					playList.playPlayList();
					updateDate();
					statusLabel.setText(account.getInfo());
				} else
					JOptionPane.showMessageDialog(null, "Cant play song");
			}
		}
	}

	// play song 1
	private class song1Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!loggedIn)
				JOptionPane.showMessageDialog(null, "Not Logged in");
			else {
				Song song2 = jukebox.getSongs().getSong("tada.wav");

				if (decide.decide(song2, account)) {
					playList.add(song2);
					account.playedSong(song2);
					playList.playPlayList();
					updateDate();
					statusLabel.setText(account.getInfo());
				} else
					JOptionPane.showMessageDialog(null, "Cant play song");
			}
		}
	}

	/**
	 * 
	 */
	private void updateDate() {
		if (decide.compareDate()) {
			jukebox.resetDate();
		}
	}

	/**
	 * This inner class allows us to have an callback function that receive a
	 * songFinishedPlaying message whenever an audio file has been played.
	 * 
	 * Note: this is a static class because it is being called from main, which
	 * is a static context. If you are using a GUI, you won't need static
	 */
	@SuppressWarnings("unused")
	private static class ObjectWaitingForSongToEnd implements EndOfSongListener {

		public void songFinishedPlaying(EndOfSongEvent eosEvent) {
			System.out.println("Finished " + eosEvent.fileName() + ", " + eosEvent.finishedDate() + ", "
					+ eosEvent.finishedTime());

		}
	}

}