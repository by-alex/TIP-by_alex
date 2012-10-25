package com.by_alex.ti_a;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;
import android.widget.Toast;
import java.nio.charset.Charset;

public class mai extends Activity implements CreateNdefMessageCallback{
	
	NfcAdapter mNfcAdapter;
	TextView textView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
		mNfcAdapter	= NfcAdapter.getDefaultAdapter(this);
		
		if(mNfcAdapter == null){
			Toast.makeText(this, "!NFC", Toast.LENGTH_LONG).show();
			return;
		}
		else{
			mNfcAdapter.setNdefPushMessageCallback(this, this);
		}
    }

	/** Called when the user clicks the Send button */
	public void sendMessage(View view){
		//EditText editText = (EditText) findViewById(R.id.edit_message);
		//String message = editText.getText().toString();
		
		
		/*NdefMessage msg = new NdefMessage(
        new NdefRecord[] {
            NdefRecord.createApplicationRecord(message)
		}*/
		
	}
	
	@Override
	public NdefMessage createNdefMessage(NfcEvent event){
			String text = "HHH_"+System.currentTimeMillis();
			
			NdefMessage msg = new NdefMessage(
				new NdefRecord[]{
					createMimeRecord("application/com.by_alex.ti_a", text.getBytes())
				}
			);
		return msg;
	}
	
	@Override
	public void onResume(){
		super.onResume();
		if(NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())){
			processIntent(getIntent());
		}			
	}
	
	@Override
	public void onNewIntent(Intent intent){
		setIntent(intent);
	}
	
	void processIntent(Intent intent){
		textView = (TextView) findViewById(R.id.textView);
	//	String message = editText.getText().toString();
		
		Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
		
		NdefMessage msg = (NdefMessage)rawMsgs[0];
		
		textView.setText(new String(msg.getRecords()[0].getPayload()));
	}
	
	public NdefRecord createMimeRecord(String mimeType, byte[] payload){
		byte[] mimeBytes = mimeType.getBytes(Charset.forName("US-ASCII"));
		NdefRecord mimeRecord = new NdefRecord(NdefRecord.TNF_MIME_MEDIA, mimeBytes, new byte[0], payload);
		return mimeRecord;
	}
	
}
