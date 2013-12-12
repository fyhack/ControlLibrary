/*
* Copyright (C) Fyhack
*
* File: DockedListViewActivity.java
*
* Author: fyhack
* Email: fyhack.cn@gmail.com
* CreateDate: 2012-12-14
*/
package com.fyhack.android.widget.dockedlistview;

import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.fyhack.android.widget.dockedlistview.DockedListView;

public class DockedListViewActivity extends Activity {
	public static final String TAG = DockedListViewActivity.class.getSimpleName();
	
	private DockedListView dockedListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dockedlistview_activity_layout);
		dockedListView=(DockedListView) findViewById(R.id.dockedListView);
		initData();
		dockedListView.post(new Runnable() {
			@Override
			public void run() {
				dockedListView.setDockedHeight(getDockedHeight());
			}
		});
	}
	
	private void initData(){
		List<String> data = new ArrayList<String>();

		for (int i = 1; i < 20; i++) {
			data.add("测试数据" + i);
		}
		
		dockedListView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, data));
		
	}
	
	private int getDockedHeight(){
		int[] location = new int[2];
		dockedListView.headView.getLocationInWindow(location);
		return location[1];
	}
	
}