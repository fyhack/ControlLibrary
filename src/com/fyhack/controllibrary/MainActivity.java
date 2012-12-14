package com.fyhack.controllibrary;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;

public class MainActivity extends Activity implements OnClickListener{
	private LinearLayout mainLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}
	
	private void initView(){
		LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		params.addRule(LinearLayout.VERTICAL);
		mainLayout=new LinearLayout(this);
		setContentView(mainLayout,params);
		params=new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		Button btn1=new Button(this);
		btn1.setText(R.string.btn_dock);
		mainLayout.addView(btn1,params);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		
	}

}
