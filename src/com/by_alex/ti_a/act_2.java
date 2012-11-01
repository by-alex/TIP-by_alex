package com.by_alex.ti_a;


import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import android.app.AlertDialog;

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

public class act_2 extends Activity {    
	
	TextView textView;
	
	@Override   
	public void onCreate(Bundle savedInstanceState) {         
		super.onCreate(savedInstanceState);         
		setContentView(R.layout.layout_a1);
	} 
	
	@Override
	public void onResume(){
			super.onResume();
			
			NdefMessage msgs[];
			if(NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())){
					Parcelable[] rawMsgs = getIntent().getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
					if(rawMsgs != null){
						msgs = new NdefMessage[rawMsgs.length];
						for(int i = 0; i < rawMsgs.length; i++)
							msgs[i] = (NdefMessage)rawMsgs[i];
						String str = "from uri - ";
						for(int i = 0; i < msgs.length; i++)
							str += msgs[i]+"|/|";
						str += "|&|";
						for(int i = 0; i < msgs.length; i++)
							str += msgs[i].getRecords()[0].getPayload().toString()+"|/|";	
				
					Tag tag = getIntent().getParcelableExtra(NfcAdapter.EXTRA_TAG);
						str += tag.toString();
						
						textView = (TextView) findViewById(R.id.textView);
						textView.setText(str);
					}
					
			}
	}
	
}
