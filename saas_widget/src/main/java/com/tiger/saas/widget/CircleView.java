package com.tiger.saas.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.tiger.saas.widget.utils.Dimension;

public class CircleView extends View {

    private int radius = (int) Dimension.dp2px(5);
    private int color = Color.parseColor("#58CC74");
    private Paint paint = new Paint();

    public void setColor(@ColorInt int color) {
        this.color = color;
        invalidate();
    }

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int verticalCenter = getHeight() / 2;
        int horizontalCenter = getWidth() / 2;
        paint.setAntiAlias(true);
        paint.setColor(color);
        canvas.drawCircle(horizontalCenter, verticalCenter, radius, paint);
    }

}
