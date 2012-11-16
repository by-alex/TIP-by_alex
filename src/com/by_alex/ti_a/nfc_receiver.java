package com.by_alex.ti_a;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;


public class nfc_receiver extends BroadcastReceiver{
	
	static int id = 0;
	
	//Broadcast receiver, gets the Wi-Fi state change 
	
	//indication if Wi-Fi was turned On or Off
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.i("nfc",""+intent.getExtras().toString());

		//Wi-Fi manager to check/change the Wi-Fi state
		//in this class is just checking the new state
	//	WifiManager wfm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		
		//Decide which Icon and text to show deppending
		//on Wi-Fi new state
		//mai.onChangeWIFI(wfm.isWifiEnabled());
		Toast.makeText(context, "nfc_object", 1000).show();
		
	}
	
	

}
