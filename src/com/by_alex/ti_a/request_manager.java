package com.by_alex.ti_a;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.BufferedReader;

import android.os.AsyncTask;
import android.util.Log;

class request_manager{
	
	public request_manager(){}
	
	
	public static void MakeRequest(String data){
		Log.i("hhh","request_manager_start");
		new make_request().execute(data);
		Log.i("hhh","request_manager_end");
	}
	
	 private static class make_request extends AsyncTask<String, Integer, String> {
     protected String doInBackground(String... urls) {
		 try{
			return prep(urls[0]);
		}catch(Exception e){return "";}
     }

     protected void onProgressUpdate(Integer... progress) {
       //  setProgressPercent(progress[0]);
     }

     protected void onPostExecute(String result) {
         act_1.toaster(result);
     }
     
    public String prep(String object) throws Exception{
			
        BufferedReader bufferReader = null;
		
		try{
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet();
			request.setURI(new URI("http://pacific-castle-7864.herokuapp.com/nfcobjects/"+object+".json"));
			HttpResponse response = client.execute(request);
			
			bufferReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			
			StringBuffer strBuffer = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			
			while((line = bufferReader.readLine())!=null){
					strBuffer.append(line+NL);
			}
			bufferReader.close();
			String page = strBuffer.toString();
			return page;
			
			}finally{
				if(bufferReader!=null)
				try{
						bufferReader.close();
				}catch(Exception e){}
			}
	}
     
 }


}
