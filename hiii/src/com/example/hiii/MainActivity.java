package com.example.hiii;


import org.opencv.android.OpenCVLoader;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

@TargetApi(12)
public class MainActivity extends Activity {
	


	private static final String TAG = "Scope.java";
	static {
		if (!OpenCVLoader.initDebug()) {
			Log.e(TAG, "Some Error!");
		}
	}
	public double alpha = 2.0;
	public double beta = 0;
	public Bitmap myimage;
	public Bitmap ppimage;
	public Uri image_uri;
	public String filepath;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v(TAG, "hellooo...");
		Log.v(TAG, "Preprocess...");

		setContentView(R.layout.activity_main);
		
		String path = "file://mnt/sdcard/Pictures/pic2.jpg";
		//Brightness brightener = new Brightness(this.getApplicationContext(),Uri.parse(path));
		//Uri ppimage = brightener.Brighten(80);
		
		Smoothing smoother = new Smoothing(this.getApplicationContext(), Uri.parse(path));
		Uri ppimage = smoother.BilateralFilter();
		
		ImageView imageView2 = (ImageView) findViewById(R.id.imgView2);
		imageView2.setImageURI(Uri.parse(path));
		
		ImageView imageView = (ImageView) findViewById(R.id.imgView);
		imageView.setImageURI(ppimage);
	}
	
}
