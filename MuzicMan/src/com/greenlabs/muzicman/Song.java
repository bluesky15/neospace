package com.greenlabs.muzicman;

import java.io.Serializable;

public class Song implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1901472635209019198L;
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public String getSongLink() {
		return songLink;
	}
	public void setSongLink(String songLink) {
		this.songLink = songLink;
	}
	private String songName;
	private String songLink;
	

}
