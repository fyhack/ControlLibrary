package com.example.viewpagermask;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {
    private ViewPager viewPager ;
    private List<Fragment> fragments ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }
    
    private void initViews(){
        viewPager = (ViewPager) findViewById(R.id.guide_viewPager);
        fragments = new ArrayList<Fragment>();
        FragmentOne one = new FragmentOne();
        FragmentTwo two = new FragmentTwo();
        fragments.add(one);
        fragments.add(two);
        ViewAdapter adapter = new ViewAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        
    }

    private class ViewAdapter extends FragmentPagerAdapter {

        private List<Fragment> list;

        public ViewAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public Fragment getItem(int arg0) {
            return list.get(arg0);
        }

        @Override
        public int getCount() {
            return list.size();
        }

    }
}
