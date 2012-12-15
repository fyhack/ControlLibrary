/*
* Copyright (C) Fyhack
*
* File: DockedListViewActivity.java
*
* Author: fyhack
* Email: fyhack.cn@gmail.com
* CreateDate: 2012-12-14
*/
package com.fyhack.dockedlistview;

import java.util.ArrayList;
import java.util.List;

import com.fyhack.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.AbsListView.OnScrollListener;

public class DockedListViewActivity extends Activity {
	public static final String TAG = DockedListViewActivity.class.getSimpleName();
	private ListView listView;
	View listViewHead, dockedView, headView,dock;
	RelativeLayout mainLayout;
	int dockedHeight;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dockedlistview_activity_main);

		listView = (ListView) this.findViewById(R.id.listView);

		mainLayout=(RelativeLayout) this.findViewById(R.id.mainLayout);

		listViewHead = LayoutInflater.from(this).inflate(
				R.layout.dockedlistview_listview_head, listView, false);

		dockedView = listViewHead.findViewById(R.id.docked_head);

		headView = listViewHead.findViewById(R.id.listview_head);
		
		headView.post(new Runnable() {
			
			@Override
			public void run() {
				dockedHeight=getDockedHeight();
			}
		});

		listView.addHeaderView(listViewHead);
		
		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, getData()));
		
		listView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				if (null != dockedView) {
					int[] location = new int[2];
					dockedView.getLocationInWindow(location);
					int x = location[1];
					
					if (x < dockedHeight) {
						mainLayout.findViewById(R.id.docked_head_1).setVisibility(View.VISIBLE);
					}else{
						mainLayout.findViewById(R.id.docked_head_1).setVisibility(View.GONE);
					}
				}
			}

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

			}

		});
	}
	
	private int getDockedHeight(){
		int[] location = new int[2];
		headView.getLocationInWindow(location);
		return location[1];
	}

	private List<String> getData() {

		List<String> data = new ArrayList<String>();

		for (int i = 1; i < 20; i++) {
			data.add("测试数据" + i);
		}

		return data;
	}
}