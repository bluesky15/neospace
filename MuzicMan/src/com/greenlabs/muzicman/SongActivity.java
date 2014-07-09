package com.greenlabs.muzicman;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import com.greenlabs.muzicman.adapter.SongAdapter;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class SongActivity extends Activity implements OnItemClickListener{
	Album al_new = new Album();
	//Song sg_new  = new Song();
	ArrayList<Song> songs= new ArrayList<Song>();
	ArrayList<String> ss= new ArrayList<String>();
	ArrayList<Integer> load_status=new ArrayList<Integer>();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	setContentView(R.layout.song_list_activity);
	ListView lv= (ListView)findViewById(R.id.song_list) ;
		Intent i = getIntent();
		al_new = (Album) i.getSerializableExtra("albumf");
		songs=(ArrayList<Song>) al_new.getSong();
		for(Song s:songs){
			load_status.add(0);
		}
		SongAdapter adapter=new SongAdapter(songs, load_status,SongActivity.this);
		
		
		       
		 lv.setAdapter(adapter);
		 lv.setOnItemClickListener(this);
		    
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Song sss= new Song();
		sss=songs.get(position);
		String url = sss.getSongLink();
		String sn = sss.getSongName();
		String song_n=sn+".mp3";
		Uri uri=Uri.parse(url);
		DownloadTask dt = new DownloadTask();
		dt.execute();
	  /*DownloadManager  mgr=(DownloadManager)getSystemService(DOWNLOAD_SERVICE);
	    Environment
	      .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
	      .mkdirs();
	    
	    long lastDownload = mgr.enqueue(new DownloadManager.Request(uri)
	                  .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI |
	                                          DownloadManager.Request.NETWORK_MOBILE)
	                  .setAllowedOverRoaming(false)
	                  .setTitle(sn)
	                  .setDescription("Powered by greenLab...")
	                  .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
	                                                     song_n));
	    
	
	    Toast.makeText(getApplicationContext(), "Song is downloading...",
	    		   Toast.LENGTH_LONG).show();*/
	
	
	}
	private class DownloadTask extends AsyncTask<Void, Void, Void>{
		@Override
		protected void onPostExecute(Void result) {
			Toast.makeText(getApplicationContext(), "download complete...",
					   Toast.LENGTH_LONG).show();
			super.onPostExecute(result);
		}
		//String url="";
		 URLConnection conn;
		@Override
		protected Void doInBackground(Void... params) {
			
			try{
				Environment
			      .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
			      .mkdirs();
				File path=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
				
				File file=new File(path, "newsong.mp3");
				conn = new URL("http://songs.djmazadownload.com/music/indian_movies/Kick%20(2014)/01%20-%20Kick%20-%20Jumme%20Ki%20Raat%20[DJMaza.Info].mp3").openConnection();
				InputStream is = conn.getInputStream();

			    OutputStream outstream = new FileOutputStream(file);
			    byte[] buffer = new byte[8192];
			    int len;
			    while ((len = is.read(buffer)) > 0) {
			        outstream.write(buffer, 0, len);
			    }
			    outstream.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}
		
	}
}
