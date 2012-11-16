package com.by_alex.ti_a;

import android.content.Context;
import 	android.os.Environment;

import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

public class io_manager{


	public static String getExternalStorageState(){
		return Environment.getExternalStorageState();
	}
	
	public boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		}
		return false;
	}

	public boolean isExternalStorageReadable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			return true;
		}
		return false;
	}

	public static String getDir(Context context){
		 return context.getExternalFilesDir(null).toString();
	}
		
	public static void createFolder(Context context, String relative_path){
		File dir = new File(context.getExternalFilesDir(null).getAbsolutePath() + relative_path);
		dir.mkdirs();
	}

	public static void writeFile(Context context, String path, String file){
		
		File dir = new File(context.getExternalFilesDir(null).getAbsolutePath() + path);
		dir.mkdirs();
		
		File fileN = new File(dir, file);
		try {
			FileOutputStream fos = new FileOutputStream(fileN);
			fos.write("HHHHHHHHHHHHHHHH".getBytes());
			fos.close();
 
		}catch (Exception e) {
			Log.i("HHH", e.toString());
		}
	}

}
