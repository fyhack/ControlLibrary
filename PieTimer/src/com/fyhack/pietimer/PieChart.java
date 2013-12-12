/**
 * Copyright (C) ELC
 * http://www.elclcd.com
 * Title: PieChart.java
 * Email: fyhack.cn@gmail.com
 * Date 2013-12-10
 * Version V1.0
 */
package com.fyhack.pietimer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * @Description 
 * 饼状倒计时
 *
 * @author elc_simayi
 * @date 2013-12-10 下午3:33:23
 */
public class PieChart extends View {
    private final String TAG = "PieChart";
    
    private RectF rectF = null;
    private Paint paintPieFill = null;
    
    private float f = 0;
    private float time = 1800;
    
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            invalidate();
            if(f < 360){
                f+=0.1f;
                handler.postDelayed(this, (int)(time / 360) * 100);
            }
        }
    };

    public PieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        rectF = new RectF(100, 100, 200, 200);
        paintPieFill = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintPieFill.setStyle(Paint.Style.FILL); 
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paintPieFill.setColor(Color.RED);
        canvas.drawArc(rectF, 0 , f, true, paintPieFill);
        paintPieFill.setColor(Color.BLACK);
        canvas.drawArc(rectF,  f, 360-f, true, paintPieFill);
        Log.i(TAG, "onDraw");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i(TAG, "onLayout");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i(TAG, "onMeasure");
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        handler.post(runnable);
        return super.onTouchEvent(event);
    }
       
}
