package com.oukasoft.ServiceSample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT; 

	LinearLayout ll;
    Button btnStart;
    Button btnStop;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
		btnStart = (Button)findViewById(R.id.button1);
		btnStop = (Button)findViewById(R.id.button2);

		btnStart.setOnClickListener(new ServiceOnClickListener());
		btnStop.setOnClickListener(new ServiceOnClickListener());
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
