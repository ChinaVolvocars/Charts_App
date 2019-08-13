package com.tiger.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

public class IndexHorizontalScrollView1 extends HorizontalScrollView {

    private static final String TAG = "IndexHorizontal";
    private Paint textPaint;

    public IndexHorizontalScrollView1(Context context) {
        this(context, null);
    }

    public IndexHorizontalScrollView1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndexHorizontalScrollView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(new Color().WHITE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
