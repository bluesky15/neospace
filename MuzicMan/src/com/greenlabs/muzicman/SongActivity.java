package com.greenlabs.muzicman;

import java.util.ArrayList;

import com.greenlabs.muzicman.adapter.SongAdapter;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

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
	  DownloadManager  mgr=(DownloadManager)getSystemService(DOWNLOAD_SERVICE);
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
	    
	}
}
