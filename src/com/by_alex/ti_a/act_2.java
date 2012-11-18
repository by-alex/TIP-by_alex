package com.by_alex.ti_a;

import java.io.File;
import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import android.app.AlertDialog;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcEvent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;
import java.nio.charset.Charset;
import java.io.IOException;
import android.widget.Toast;

public class act_2 extends Activity {    
	
	TextView textView;
	static Context context;
	
	sqlLiteHelper db; //=new sqlLiteHelper(this);
	
	@Override   
	public void onCreate(Bundle savedInstanceState) {         
		super.onCreate(savedInstanceState);         
		setContentView(R.layout.layout_a1);
		Log.i("hhh","start");
		context = this;
		 db = new sqlLiteHelper(this);
	} 
	
	
	public static void toaster(String str){
		Toast toast = Toast.makeText(context, str, 2000);
		toast.show();
	}
	
	public void setTextView(String str){
		textView = (TextView) findViewById(R.id.textView);
		textView.setText(str);
	}
	
	@Override
	public void onNewIntent(Intent intent){
	context = this;
		//toaster("NewIntent - "+intent.getExtras().toString());
			NdefMessage msgs[];
			if(NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())){
					Parcelable[] rawMsgs = getIntent().getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
					if(rawMsgs != null){
						msgs = new NdefMessage[rawMsgs.length];
						for(int i = 0; i < rawMsgs.length; i++)
							msgs[i] = (NdefMessage)rawMsgs[i];
						String str = "";
						//for(int i = 0; i < msgs.length; i++)
						//	str += msgs.length+"_"+msgs[i]
						
						//
						
						for(int i = 0; i < msgs.length; i++){
							for(int i2 = 0; i2 < msgs[i].getRecords().length; i2++){
								//byte[] value = msgs[i].getRecords()[i2].getPayload();
								str += new String(msgs[i].getRecords()[i2].getPayload());
							}
						}
				
						//Tag tag = getIntent().getParcelableExtra(NfcAdapter.EXTRA_TAG);
						//str += tag.toString();
						str.trim();
						str = str.substring(7);
						Log.i("HHH","TAG = [ "+str+" ]");
						try{
							toaster("TAG = [ "+str+" ] "+getExternalFilesDir(null).toString()+" "+io_manager.fileExists(this, "/objects", "HHH"));
						}catch(Exception e){
								toaster("TAG = "+ e.toString());
						}
						//io_manager.createFolder(this, "/objects");
						io_manager.writeFile(this, "/objects", "HHH");
						
						request_manager.MakeRequest(str,config.ACTIVITY_TWO);
					}
					
			}
			
	}
	
	@Override
	public void onResume(){
			super.onResume();
			
			
	}
	
}
