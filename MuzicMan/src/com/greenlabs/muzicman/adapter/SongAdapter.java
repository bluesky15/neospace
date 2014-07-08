package com.greenlabs.muzicman.adapter;


import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.greenlabs.muzicman.R;
import com.greenlabs.muzicman.Song;

public class SongAdapter extends BaseAdapter{
	static final String LOAD="Load";
	static final String RETRY="Retry";
	static final String DONE="Done";
    ArrayList<Song> mm_song;
    ArrayList<Integer> ss_status;
    Context context;
    public SongAdapter(ArrayList<Song> song,ArrayList<Integer> status,Context context) {
		mm_song=song;
		ss_status=status;
		this.context=context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mm_song.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mm_song.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return (long)position;
	}
	private class ViewHolder{
		TextView song_name;
		TextView song_status;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		
		LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if(convertView==null){
			convertView = mInflater.inflate(R.layout.ss_custum_list,null);
			holder = new ViewHolder();
			holder.song_name=(TextView)convertView.findViewById(R.id.ss_name);
			holder.song_status=(TextView)convertView.findViewById(R.id.ss_status);
			convertView.setTag(holder);
			convertView.setBackgroundResource(R.drawable.rounder_corner);
			
		}
		else{
			holder = (ViewHolder)convertView.getTag();
		}
		Song ad_song= new Song();
		ad_song=mm_song.get(position);
		holder.song_name.setText(ad_song.getSongName());
		holder.song_status.setText(LOAD);
		switch(ss_status.get(position)){
		case 0:
			holder.song_status.setText(LOAD);
			break;
		case 1:
			holder.song_status.setText(DONE);
			break;
		case-1:
			holder.song_status.setText(RETRY);
			break;
		}
		
		return convertView;
	}

}
