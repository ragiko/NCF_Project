package com.oukasoft.ServiceSample;

import java.util.Timer;
import java.util.TimerTask;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class TestService extends Service {
	
	private LocationManager locationManager;

	@Override
	public void onCreate() {
		Log.i("TestService", "onCreate");
	}
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("TestService", "onStartCommand");

        /*
		 * GPS
		 */
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

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
				locationListener); // リスナーØ
        
        return START_STICKY;
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
    
    /*
     * LOGにGPSを表示
     */
    private void showLocation(Location location) {  
          
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
        
        Log.d("gps", sb.toString());
    }  

    @Override
    public void onDestroy() {
        Log.i("TestService", "onDestroy");
        
        // http://stackoverflow.com/questions/6894234/stop-location-listener-in-android
		locationManager.removeUpdates(locationListener);
		locationManager = null;
        
        Toast.makeText(this, "MyService�@onDestroy", Toast.LENGTH_SHORT).show();
    }

	@Override
	public IBinder onBind(Intent arg0) {
		Log.i("TestService", "onBind");
		return null;
	}
}
