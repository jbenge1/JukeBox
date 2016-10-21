/**
 * @author: Justin Benge
 * Date: Oct 10, 2016
 * Compiler: compiled using javac 
 * File Name: SongQueue.java
 * Description:
 */

package model;

import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class SongQueue {
	private ArrayBlockingQueue<Song> playList;

	public SongQueue() {
		playList = new ArrayBlockingQueue<Song>(100);
	}

	/**
	 * 
	 * @param song
	 */
	public void add(Song song) {
		playList.add(song);
	}

	/**
	 * For testing purposes only
	 * 
	 * @return
	 */
	public Song getTopSong() {
		return playList.poll();
	}

	public void playPlayList() {
		while (!playList.isEmpty()) {
			Song curSong = playList.poll();
			curSong.playSong();
			try {
				Thread.sleep(10000);
			} catch (Exception e) {
			}
		}
	}

	// poll = remove from front of queue
	public Song poll() {
		return playList.poll();
	}

	public boolean isEmpty() {
		return playList.isEmpty();
	}

}