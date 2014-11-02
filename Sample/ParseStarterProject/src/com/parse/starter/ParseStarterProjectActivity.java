package com.parse.starter;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ParseStarterProjectActivity extends Activity {
	
	private Button btnStart;
    private Button btnStop;
    
    
    
    
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		btnStart = (Button)findViewById(R.id.button1);
		btnStop = (Button)findViewById(R.id.button2);

		btnStart.setOnClickListener(new ServiceOnClickListener());
		btnStop.setOnClickListener(new ServiceOnClickListener());
		
		

		ParseAnalytics.trackAppOpened(getIntent());
	}
	
	class ServiceOnClickListener implements OnClickListener {
		@Override
		public void onClick(View view) {
			Log.i("MainActivity", "onClick");
			if( view == btnStart ){
				Log.i("MainActivity", "btnStart");
				startService( new Intent( ParseStarterProjectActivity.this, GpsService.class ) );
			}else if( view == btnStop ){
				Log.i("MainActivity", "btnStop");
				stopService( new Intent( ParseStarterProjectActivity.this, GpsService.class ) );
			}
		}

    } 
}
