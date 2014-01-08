package com.elclcd.orientation;

import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;

public class MyLayoutParams extends WindowManager.LayoutParams{

	public MyLayoutParams(int rotation){
		super(0, 0, 
		        WindowManager.LayoutParams.TYPE_TOAST, 
		        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
		        PixelFormat.TRANSLUCENT);
		this.gravity = Gravity.TOP;
		this.screenOrientation = rotation;
	}
}
