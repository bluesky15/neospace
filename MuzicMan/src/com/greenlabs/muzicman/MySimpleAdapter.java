package com.greenlabs.muzicman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MySimpleAdapter extends BaseAdapter{
	private List<Album> album;
	private Context context;

	public MySimpleAdapter(List<Album> album,Context context) {
		
		this.album=album;
		this.context = context;
		
		
	}
	@Override
	public int getCount() {
		
		return album.size();
	}

	@Override
	public Object getItem(int position) {
		
		return album.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return album.get(position).getId();
	}
private class ViewHolder{
	ImageView albumArt;
	TextView albumName;
}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Bitmap alArt;
		 ViewHolder holder = null;
		 LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		 
		 if(convertView==null){
			 convertView = mInflater.inflate(R.layout.custum_view, null);
			 holder = new ViewHolder();
			 holder.albumArt=(ImageView)convertView.findViewById(R.id.album_art);
			 holder.albumName= (TextView)convertView.findViewById(R.id.album_name);
			 convertView.setTag(holder);
			 
		 }else{
			 holder = (ViewHolder)convertView.getTag();
		 }
		   
		 Album newalbum=album.get(position);
		    Long ll=newalbum.getId();
		    String filename=ll.toString();
		    filename =filename+".png";
		    FileInputStream fi; 
		    try {
			 File myfile = context.getFileStreamPath(filename);
			 
			 fi = new FileInputStream(myfile);
			 alArt = BitmapFactory.decodeStream(fi);
			 holder.albumArt.setImageBitmap(alArt);;
			 holder.albumName.setText(newalbum.getAlbumName());
		} catch (FileNotFoundException e) {
			 holder.albumArt.setImageResource(R.drawable.villan);
			 holder.albumName.setText(newalbum.getAlbumName());
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		 
		
		return convertView;
	}

	

}
