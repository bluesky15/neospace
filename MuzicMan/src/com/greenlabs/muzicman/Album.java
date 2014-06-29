package com.greenlabs.muzicman;

import java.util.List;

public class Album {
	
private int id;
private String album_name;
private String albumImage_link;
private List<Song> album_songs;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getAlbum_name() {
	return album_name;
}
public void setAlbum_name(String album_name) {
	this.album_name = album_name;
}
public String getAlbumImage_link() {
	return albumImage_link;
}
public void setAlbumImage_link(String albumImage_link) {
	this.albumImage_link = albumImage_link;
}
public List<Song> getAlbum_songs() {
	return album_songs;
}
public void setAlbum_songs(List<Song> album_songs) {
	this.album_songs = album_songs;
}
public Album() {
	// TODO Auto-generated constructor stub
}

}
