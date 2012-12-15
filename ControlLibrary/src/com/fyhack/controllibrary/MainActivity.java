package com.fyhack.controllibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.fyhack.R;

public class MainActivity extends Activity implements OnClickListener{
	private LinearLayout mainLayout;
	
	public final int DockedListViewId=1;

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
		btn1.setId(DockedListViewId);
		btn1.setOnClickListener(this);
		mainLayout.addView(btn1,params);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case DockedListViewId :
				this.startActivity(new Intent(this,org.fyhack.dockedlistview.MainActivity.class));
				break;
		}
	}

}
