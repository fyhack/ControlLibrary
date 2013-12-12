package com.fyhack.android.widget.dockedlistview;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

/**
 * 停靠栏示例测试
 *
 * version : 1.0
 * modified by: fyhack
 * email: fyhack.cn@gmail.com
 * last modified: 2013-6-7
 */
public class DockedListView extends RelativeLayout {
	public static final String TAG = DockedListView.class.getSimpleName();
	public final int DOCKED_VIEW_ID=10001;
	public final int DOCKED_TEMPVIEW_ID=10002;
	private Context c;
	private ListView listView;
	public View dockedView,dockedTempView, headView;
	private int dockedHeight;
	
	public DockedListView(Context context){
		super(context);
		c=context;
	}
	
	public DockedListView(Context context, AttributeSet attrs){
		super(context, attrs);
		c=context;
		
		listView=new ListView(c);
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.FILL_PARENT);
		this.addView(listView, lp);
		
		dockedTempView=LayoutInflater.from(c).inflate(
				R.layout.docked_layout, null);
		dockedTempView.setId(DOCKED_TEMPVIEW_ID);
		lp = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		this.addView(dockedTempView,lp);
		
		dockedView=LayoutInflater.from(c).inflate(
				R.layout.docked_layout, null);
		dockedView.setId(DOCKED_VIEW_ID);
		
		headView=LayoutInflater.from(c).inflate(
				R.layout.listview_head_layout, null);
		((ViewGroup)headView).addView(dockedView);
		listView.addHeaderView(headView);
	}
	
	
	public void setAdapter(ListAdapter adapter){
		listView.setAdapter(adapter);
		
		listView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				if (null != dockedTempView) {
					Log.i(TAG, "dockedHeight:"+dockedHeight);
					int[] location = new int[2];
					dockedView.getLocationInWindow(location);
					int x = location[1];
					Log.i(TAG, "x:"+x);
					if (x < dockedHeight) {
						findViewById(DOCKED_TEMPVIEW_ID).setVisibility(View.VISIBLE);
					}else{
						findViewById(DOCKED_TEMPVIEW_ID).setVisibility(View.GONE);
					}
				}
			}

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

			}

		});
	}
	
	public void setDockedHeight(int dockedHeight) {
		this.dockedHeight = dockedHeight;
	}

}
