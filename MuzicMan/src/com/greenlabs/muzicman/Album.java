package com.greenlabs.muzicman;

import java.io.Serializable;
import java.util.List;

import android.graphics.Bitmap;

public class Album implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = -254787372183330788L;
private Long id;
private String albumName;
private String albumArt;
private List<Song> song;
private Bitmap bitmap=null;

public Bitmap getBitmap() {
	return bitmap;
}
public void setBitmap(Bitmap bitmap) {
	this.bitmap = bitmap;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id =  id;
}
public String getAlbumNname() {
	return albumName;
}
public void setAlbumNname(String albumNname) {
	this.albumName = albumNname;
}
public String getAlbumArt() {
	return albumArt;
}
public void setAlbumArt(String albumArt) {
	this.albumArt = albumArt;
}
public List<Song> getSong() {
	return song;
}
public void setSong(List<Song> song) {
	this.song = song;
}

public String getAlbumName() {
	// TODO Auto-generated method stub
	return albumName;
}

}
