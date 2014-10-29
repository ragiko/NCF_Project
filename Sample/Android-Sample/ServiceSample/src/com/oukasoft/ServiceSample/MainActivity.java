package com.oukasoft.ServiceSample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT; 

	LinearLayout ll;
    Button btnStart;
    Button btnStop;
    ListView lv;
    ArrayAdapter<String> adapter;;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
		btnStart = (Button)findViewById(R.id.button1);
		btnStop = (Button)findViewById(R.id.button2);

		btnStart.setOnClickListener(new ServiceOnClickListener());
		btnStop.setOnClickListener(new ServiceOnClickListener());
		
		TextView textView1 = (TextView)findViewById(R.id.textView1);  
        textView1.setText("GPS情報取得:");  
        
		/*
		 *  ListView
		 */
		lv = (ListView) findViewById(R.id.listView1);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1);
		adapter.add( "List View");
		lv.setAdapter(adapter);
	}
    
	class ServiceOnClickListener implements OnClickListener {
		@Override
		public void onClick(View view) {
			Log.i("MainActivity", "onClick");
			if( view == btnStart ){
				Log.i("MainActivity", "btnStart");
				startService( new Intent( MainActivity.this, TestService.class ) );
			}else if( view == btnStop ){
				Log.i("MainActivity", "btnStop");
				stopService( new Intent( MainActivity.this, TestService.class ) );
			}
		}

    } 
}
