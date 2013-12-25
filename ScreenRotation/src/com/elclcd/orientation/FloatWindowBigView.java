package com.elclcd.orientation;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class FloatWindowBigView extends LinearLayout {

	/**
	 * 记录大悬浮窗的宽度
	 */
	public static int viewWidth;

	/**
	 * 记录大悬浮窗的高度
	 */
	public static int viewHeight;

	public FloatWindowBigView(final Context context) {
		super(context);
		LayoutInflater.from(context).inflate(R.layout.float_window_big, this);
		View view = findViewById(R.id.big_window_layout);
		viewWidth = view.getLayoutParams().width;
		viewHeight = view.getLayoutParams().height;
		Button lan = (Button) findViewById(R.id.f_lan);
		Button por = (Button) findViewById(R.id.f_por);
		Button relan = (Button) findViewById(R.id.f_relan);
		Button repor = (Button) findViewById(R.id.f_repor);
		Button back = (Button) findViewById(R.id.back);
		lan.setOnClickListener(onClickListener);
		por.setOnClickListener(onClickListener);
		relan.setOnClickListener(onClickListener);
		repor.setOnClickListener(onClickListener);
		back.setOnClickListener(onClickListener);
	}
	
	private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.f_lan:
                    handleClick(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    break;
                case R.id.f_por:
                    handleClick(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    break;
                case R.id.f_relan:
                    handleClick(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                    break;
                case R.id.f_repor:
                    handleClick(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
                    break;
                case R.id.back:
                    MyWindowManager.invisibleBigWindow();
                    MyWindowManager.visibleSmallWindow();
                    break;
            }
        }
    };
    
    private void handleClick(int activityInfo){
        MyWindowManager.invisibleBigWindow();
        MyWindowManager.visibleSmallWindow();
        Intent intent = new Intent(getContext(), OrientationService.class);
        intent.putExtra(OrientationService.FLAG_VALUE, activityInfo);
        getContext().startService(intent);
    }
}
