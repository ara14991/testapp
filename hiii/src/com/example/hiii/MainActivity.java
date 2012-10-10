package com.example.hiii;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
		
		
		Mat src = new Mat();
		Mat dst = new Mat();
		

		try {
			myimage = MediaStore.Images.Media.getBitmap(
					this.getContentResolver(),Uri.parse("file://mnt/sdcard/Pictures/card2new.jpg"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			Log.v(TAG, "NULL");
			e.printStackTrace();
		}
		
		
		Log.v(TAG, "not screwed");
		Log.v(TAG, "Myimage Size:" + myimage.getByteCount());
		
		ppimage=myimage;
		
		Utils.bitmapToMat(myimage, src);
		Log.v(TAG, "not screwed1");
		Imgproc.cvtColor(src, dst, Imgproc.COLOR_RGB2GRAY, 0);
		Mat dst1 = Mat.zeros(dst.size(), dst.type());
		dst.convertTo(dst1, -1, alpha, beta);
		//Imgproc.equalizeHist(dst1, dst1);
		Log.v(TAG, "not screwed2");
		Utils.matToBitmap(dst1, ppimage);
		
		Log.v(TAG, "PPimage Size:" + ppimage.getByteCount());
		
		ImageView imageView = (ImageView) findViewById(R.id.imgView);
		imageView.setImageBitmap(ppimage);
	}
	
}
