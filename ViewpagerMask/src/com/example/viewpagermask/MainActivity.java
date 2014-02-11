package com.example.viewpagermask;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends FragmentActivity {
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        startAnim();
    }

    private void initViews() {
        viewPager = (ViewPager) findViewById(R.id.guide_viewPager);
        btn = (Button) findViewById(R.id.btn_guide_enter);
        fragments = new ArrayList<Fragment>();
        FragmentOne one = new FragmentOne();
        FragmentTwo two = new FragmentTwo();
        fragments.add(one);
        fragments.add(two);
        ViewAdapter adapter = new ViewAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                changeDot(arg0);
                if (arg0 == viewPager.getAdapter().getCount()-1) {
                    btn.setVisibility(View.VISIBLE);
                }else{
                    btn.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

        changeDot(0);
    }

    private void changeDot(int page) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.ll_guide_point_icon);
        for (int i = 0; i < layout.getChildCount(); i++) {
            if (i == page) {
                layout.getChildAt(i).setBackgroundResource(R.drawable.dot_guide_s);
            } else {
                layout.getChildAt(i).setBackgroundResource(R.drawable.dot_guide_n);
            }
        }
    }
    
    private void startAnim(){
        startAnimHorseView(new OnAfterStart() {
            
            @Override
            public void todo() {
                startAnimTopView();                
            }
        });
    }
    
    private void startAnimHorseView(final OnAfterStart afterStart){
        final View horse = findViewById(R.id.iv_guide_horse);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.guide_horse_set);
        anim.setAnimationListener(new AnimationListener() {
                public void onAnimationStart(Animation arg0) {
                }
                public void onAnimationRepeat(Animation arg0) {
                }
                public void onAnimationEnd(Animation arg0) {
                    horse.setVisibility(View.INVISIBLE);
                    afterStart.todo();
                }
        });
        horse.startAnimation(anim);
    }
    
    private void startAnimTopView(){
        final View top = findViewById(R.id.fl_guide_top);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.guide_horse_view_trans);
        anim.setAnimationListener(new AnimationListener() {
                public void onAnimationStart(Animation arg0) {
                }
                public void onAnimationRepeat(Animation arg0) {
                }
                public void onAnimationEnd(Animation arg0) {
                    top.setVisibility(View.INVISIBLE);
                }
        });
        top.startAnimation(anim);
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
    
    private interface OnAfterStart{
        public void todo();
    }
}
