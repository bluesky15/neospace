package com.greenlab.mm.asynctask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.greenlab.muzicman.jsonreader.JsonReader;
import com.greenlabs.muzicman.Album;
import com.greenlabs.muzicman.MySimpleAdapter;
import com.greenlabs.muzicman.R;
import com.greenlabs.muzicman.net.MConnetion;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MyAsyncTask extends AsyncTask<View,Void, MySimpleAdapter>{
private ArrayList<Album> album= new ArrayList<Album>();
private MConnetion mConnction = new MConnetion();
private JsonReader jsonReader = new JsonReader();
private MySimpleAdapter adapter;
private Context context;
private ListView listview;
 
	@Override
	protected MySimpleAdapter doInBackground(View... params) {
		context=params[0].getContext();
		listview =(ListView)params[1];
		String str=mConnction.connect();
		try {
			album=jsonReader.reader(str);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 adapter = new MySimpleAdapter(album, context);
		return adapter;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected void onPostExecute(MySimpleAdapter result) {
		listview.setAdapter(result);
		listview.setOnItemClickListener();
	}

	
}
