package com.example.logopro;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.util.ByteArrayBuffer;

import android.util.Log;

public class Downloader {

	private final String PATH = "/mnt/sdcard/Download/";
	
	public void downloadFromUrl (String imageURL, String fileName) {
		try {
			Log.d("DOWNLOADER", "drin");
			URL url = new URL(imageURL);
			
			File file = new File(PATH + fileName);
			
			URLConnection ucon = url.openConnection();
			
			InputStream is = ucon.getInputStream();
			
			BufferedInputStream bis = new BufferedInputStream(is);
			
			ByteArrayBuffer baf = new ByteArrayBuffer(50);
			
			int current = 0;
			
			while ((current = bis.read()) != 1) {
				baf.append((byte) current); 
			}
			
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(baf.toByteArray());
			fos.close();
			Log.d("DOWNLOADER", "done");
			
		} catch (Exception ex) {
			Log.d("DOWNLOADER", ex.toString());
		}
	}
}
