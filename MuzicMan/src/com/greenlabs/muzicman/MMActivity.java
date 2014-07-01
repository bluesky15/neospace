package com.greenlabs.muzicman;

import java.util.ArrayList;

import com.greenlab.mm.asynctask.MyAsyncTask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MMActivity extends Activity implements OnItemClickListener{

	private MyAsyncTask aTask;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Album>album=new ArrayList<Album>();
       
        setContentView(R.layout.activity_mm);
        ListView lv=(ListView)findViewById(R.id.list);
        aTask.execute(lv);
        lv.setOnItemClickListener(this);
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}

}
