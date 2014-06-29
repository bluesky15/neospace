package com.greenlabs.muzicman;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MMActivity extends Activity implements OnItemClickListener{

	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Album>album=new ArrayList<Album>();
        Album al = new Album();
        al.setId(1);
        al.setAlbum_name("ek villan");
        al.setAlbumImage_link(null);
        al.setAlbum_songs(null);
        album.add(al);
        setContentView(R.layout.activity_mm);
        ListView lv=(ListView)findViewById(R.id.list);
        MySimpleAdapter adapter = new MySimpleAdapter(album, this);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}

}
