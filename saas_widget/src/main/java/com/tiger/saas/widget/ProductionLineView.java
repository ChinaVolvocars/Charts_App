package com.tiger.saas.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;


/**
 * 生产线统计的图表
 */
public class ProductionLineView extends View {

    private Context mContext;
    private int mWidth;
    private int mHeight;

    public ProductionLineView(Context context) {
        this(context, null);
    }

    public ProductionLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProductionLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.reset();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true); //消除锯齿
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);  //绘制空心圆或 空心矩形,只显示边缘的线，不显示内部
        RectF rect = new RectF(1, 99, 85, 663);
        //false 不画圆心
        paint.setAlpha(255);
        canvas.drawArc(rect, 10, 90, false, paint);
    }

}
