package com.tiger.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 原材料预估消耗表
 */
public class MaterialsConsumptionView extends View {

    public static final int LEFT_TEXT_HEIGHT = (int) dp2px(17);
    public static final int LEFT_TEXT_PADDING_TOP = (int) dp2px(10);
    public static final int LEFT_TEXT_PADDING_BOTTOM = (int) dp2px(10);
    public static final int LEFT_TEXT_WIDTH = (int) dp2px(50);
    public static final int LINE_WIDTH = (int) dp2px(1);
    public static final int MARGIN_RIGHT = (int) dp2px(14);
    public static final int BOTTOME_TEXT_HEIGHT = (int) dp2px(15);
    public static final int BOTTOM_TEXT_PADDING_TOP = (int) dp2px(7);
    public static final int BOTTOM_TEXT_PADDING_BOTTOM = (int) dp2px(7);


    private float LEFT_TEXT_SIZE = sp2px(12);
    private float BOTTOM_TEXT_SIZE = sp2px(11);
    private float PURCHASE_TEXT_SIZE = sp2px(13);
    private float CONSUME_TEXT_SIZE = sp2px(13);

    private Paint leftTextPaint = new Paint();
    private Paint bottomTextPaint = new Paint();
    private Paint linePaint = new Paint();
    private Paint dottedLinePaint = new Paint();

    private Paint blueRectanglePaint = new Paint();
    private Paint grayRectanglePaint = new Paint();

    private int leftTextColor = Color.parseColor("#666666");
    private int bottomTextColor = Color.parseColor("#333333");
    private int lineColor = Color.parseColor("#969fa9");
    private int blueRectangleColor = Color.parseColor("#50abff");
    private int grayRectangleColor = Color.parseColor("#eeeeee");
    private int dottedLineColor = Color.parseColor("#e9e9e9");

    private int mHeight, mWidth;

    private int itemCount = 7;


    private int tempBaseTop;  //折线的左边x坐标
    private int tempBaseBottom; //折线的右边x坐标

    public MaterialsConsumptionView(Context context) {
        this(context, null);
    }

    public MaterialsConsumptionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaterialsConsumptionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private double maxTemp = 10;
    private double minTemp = 0;
    private List<MaterialsConsumption> itemList = new ArrayList<>();

    private void init() {
        itemList = new ArrayList<>();
        MaterialsConsumption materials30Item;
        Random random = new Random();

        for (int i = 0; i < itemCount; i++) {
            materials30Item = new MaterialsConsumption();
            double s = random.nextInt((int) maxTemp) % (maxTemp - minTemp + 1) + minTemp;
            double s2 = random.nextInt((int) maxTemp) % (maxTemp - minTemp + 1) + minTemp;
            materials30Item.setTotalConsumeTon(s);
            materials30Item.setTotalSignTon(s2 + 10);
            materials30Item.setRawMaterial("水泥" + i);
            itemList.add(materials30Item);
        }


        mWidth = getScreenWidth();
        mHeight = itemCount * (LEFT_TEXT_HEIGHT + LEFT_TEXT_PADDING_TOP + LEFT_TEXT_PADDING_BOTTOM) + 100;

        tempBaseTop = LEFT_TEXT_WIDTH;
        tempBaseBottom = mWidth - MARGIN_RIGHT - LEFT_TEXT_WIDTH;
        //左边文字
        leftTextPaint.setTextSize(LEFT_TEXT_SIZE);
        leftTextPaint.setColor(leftTextColor);
        leftTextPaint.setAntiAlias(true);
        //直线
        linePaint.setColor(lineColor);
        linePaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(dp2px(1));
        //虚线

        //虚线
        dottedLinePaint.setAntiAlias(true);
        dottedLinePaint.setStyle(Paint.Style.STROKE);
        dottedLinePaint.setStrokeWidth(dp2px(1));
        dottedLinePaint.setColor(dottedLineColor);
        dottedLinePaint.setPathEffect(new DashPathEffect(new float[]{6, 6}, 0));


        blueRectanglePaint.setStyle(Paint.Style.FILL);
        blueRectanglePaint.setColor(blueRectangleColor);
        blueRectanglePaint.setAntiAlias(true);

        grayRectanglePaint.setStyle(Paint.Style.FILL);
        grayRectanglePaint.setColor(grayRectangleColor);
        grayRectanglePaint.setAntiAlias(true);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getScreenWidth();
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        onDrawLeftText(canvas);
        onDrawLine(canvas);
        onDrawRectangle(canvas);
        onDrawBottomText(canvas);
    }

    private void onDrawLeftText(Canvas canvas) {
        for (int i = 0; i < itemCount; i++) {
            int left = 0;
            int right = LEFT_TEXT_WIDTH;
            int top = i * (LEFT_TEXT_HEIGHT + LEFT_TEXT_PADDING_TOP + LEFT_TEXT_PADDING_BOTTOM);
            int bottom = i * (LEFT_TEXT_HEIGHT + LEFT_TEXT_PADDING_TOP + LEFT_TEXT_PADDING_BOTTOM) + LEFT_TEXT_HEIGHT + LEFT_TEXT_PADDING_TOP + LEFT_TEXT_PADDING_BOTTOM;
            Rect rect = new Rect(left, top, right, bottom);
            Rect targetRect = new Rect(rect.left, rect.top, rect.right, rect.bottom);
            Paint.FontMetricsInt fontMetrics = leftTextPaint.getFontMetricsInt();
            int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
            leftTextPaint.setTextAlign(Paint.Align.CENTER);

            String text = itemList.get(i).getRawMaterial();
//            String text = "外加剂";
            canvas.drawText(text, targetRect.centerX(), baseline, leftTextPaint);
        }
    }

    private void onDrawLine(Canvas canvas) {
        //左边竖线
        canvas.drawLine(
                LEFT_TEXT_WIDTH,
                0,
                LEFT_TEXT_WIDTH,
                itemCount * (LEFT_TEXT_HEIGHT + LEFT_TEXT_PADDING_TOP + LEFT_TEXT_PADDING_BOTTOM),
                linePaint
        );
        //底部横线
        canvas.drawLine(
                LEFT_TEXT_WIDTH,
                itemCount * (LEFT_TEXT_HEIGHT + LEFT_TEXT_PADDING_TOP + LEFT_TEXT_PADDING_BOTTOM),
                mWidth - MARGIN_RIGHT,
                itemCount * (LEFT_TEXT_HEIGHT + LEFT_TEXT_PADDING_TOP + LEFT_TEXT_PADDING_BOTTOM),
                linePaint
        );
        //4条竖着的虚线
        for (int i = 1; i < 5; i++) {
            canvas.drawLine(
                    LEFT_TEXT_WIDTH + (mWidth - LEFT_TEXT_WIDTH - MARGIN_RIGHT) / 4 * i,
                    0,
                    LEFT_TEXT_WIDTH + (mWidth - LEFT_TEXT_WIDTH - MARGIN_RIGHT) / 4 * i,
                    itemCount * (LEFT_TEXT_HEIGHT + LEFT_TEXT_PADDING_TOP + LEFT_TEXT_PADDING_BOTTOM),
                    dottedLinePaint
            );
        }

    }


    private void onDrawRectangle(Canvas canvas) {

        ArrayList<Double> temp = new ArrayList<>();
        for (int i = 0; i < itemList.size(); i++) {
            double tonLeft = itemList.get(i).getTotalConsumeTon();
            double tonRight = itemList.get(i).getTotalSignTon();
            temp.add(tonLeft);
            temp.add(tonRight);
        }

        maxTemp = Collections.max(temp);
        minTemp = Collections.min(temp);


        for (int i = 0; i < itemCount; i++) {
            int left = LEFT_TEXT_WIDTH;
            Point point = calculateTempPoint(left, left, (int) itemList.get(i).getTotalConsumeTon());
            int right = left + point.y + 1;
            int top = i * (LEFT_TEXT_HEIGHT + LEFT_TEXT_PADDING_TOP + LEFT_TEXT_PADDING_BOTTOM) + (int) dp2px(10);
            int bottom = i * (LEFT_TEXT_HEIGHT + LEFT_TEXT_PADDING_TOP + LEFT_TEXT_PADDING_BOTTOM) + LEFT_TEXT_HEIGHT + LEFT_TEXT_PADDING_TOP + LEFT_TEXT_PADDING_BOTTOM - (int) dp2px(10);
            Rect rect = new Rect(left, top, right, bottom);


            Point point1 = calculateTempPoint(left, left, (int) itemList.get(i).getTotalSignTon());
            int right1 = left + point1.y + 1;

            Rect rect1 = new Rect(left, top, right1, bottom);
            canvas.drawRect(rect1, grayRectanglePaint);
            canvas.drawRect(rect, blueRectanglePaint);
        }


    }


    // [16, 13, 17, 12, 25, 12, 23, 26, 16, 13, 18, 19, 22, 19, 21, 14, 14, 19, 17, 23, 17, 14, 13, 18, 19, 19, 13, 14, 12, 11, 11]
    private Point calculateTempPoint(int left, int right, int temp) {
        double minHeight = tempBaseTop;
        double maxHeight = tempBaseBottom;
        double tempY = maxHeight - (temp - minTemp) * 1.0 / (maxTemp - minTemp) * (maxHeight - minHeight);
        Point point = new Point((left + right) / 2, (int) tempY);
        return point;
    }


    private void onDrawBottomText(Canvas canvas) {

    }


    public void setData(List<MaterialsConsumption> itemList) {
        if (itemList.size() == 0) {
            init();
        } else {
            this.itemList = itemList;
            itemCount = itemList.size();
        }

        invalidate();
        requestLayout();

    }


    /**
     * dp单位转换成 px
     *
     * @param dp
     * @return
     */
    public static float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    /**
     * sp 转换成 px
     *
     * @param sp
     * @return
     */
    public float sp2px(float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, Resources.getSystem().getDisplayMetrics());
    }

    /**
     * 获取屏幕宽度
     *
     * @return width of the screen.
     */
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @return heiht of the screen.
     */
    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }


}
