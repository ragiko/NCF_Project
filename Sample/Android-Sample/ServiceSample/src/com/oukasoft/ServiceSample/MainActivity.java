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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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
		
		TextView textView1 = (TextView)findViewById(R.id.textView1);  
        textView1.setText("GPS情報取得:");  
        
		// /////////////////////////////////
		// GPS
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		// 位置情報の要求条件を指定する
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE); // 精度を指定する
		criteria.setAltitudeRequired(true); // 標高を取得するかどうか
		criteria.setBearingRequired(true); // 進行方向を取得するかどうか
		criteria.setCostAllowed(false); // 取得費用がかかることを許可するかどうか
		criteria.setPowerRequirement(Criteria.POWER_LOW); // 消費電力量を指定する
		criteria.setSpeedRequired(true); // 速度を取得するかどうか

		// 指定する取得条件でプロバイダーを取得
		String provider = locationManager.getBestProvider(criteria, true);

		// ロケーションリスナーを設定
		locationManager.requestLocationUpdates(provider, 1000, // リスナーに通知する最小時間間隔
				1, // リスナーに通知する最小距離間隔
				locationListener); // リスナー
		
	}
    
  //位置情報を画面に表示  
    private void showLocation(Location location) {  
        TextView textView2 = (TextView)findViewById(R.id.textView2);  
          
        StringBuffer sb = new StringBuffer();  
          
        if (location != null) {  
            sb.append("緯度：").append(location.getLatitude());  
            sb.append("緯度：").append(location.getLongitude());  
            sb.append("精度：").append(location.getAccuracy());  
            sb.append("標高：").append(location.getAltitude());  
            sb.append("時間：").append(location.getTime());  
            sb.append("速度：").append(location.getSpeed());  
            sb.append("進行方向：").append(location.getBearing());  
            //sb.append("プロバイダ：").append(location.getProvider());  
        }  
          
        textView2.setText(sb.toString());  
    }  
      
    private LocationListener locationListener = new LocationListener() {  
          
        //プロバイダ・ステータスが変更された時に呼び出される  
        public void onStatusChanged(String provider, int status, Bundle extras) {              
        }  
          
        //プロバイダが利用可能になった時に呼び出される  
        public void onProviderEnabled(String provider) {  
        }  
          
        //プロバイダが利用不可になった時に呼び出される  
        public void onProviderDisabled(String provider) {  
        }  
          
        //位置情報が変更されたときに呼び出される  
        public void onLocationChanged(Location location) {  
            showLocation(location);  
        }  
    };

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
