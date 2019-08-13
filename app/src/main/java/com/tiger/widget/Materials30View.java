package com.tiger.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Materials30View extends View {

    public static final int MARGIN_LEFT_ITEM = (int) dp2px(14);
    public static final int MARGIN_RIGHT_ITEM = (int) dp2px(14);
    public static final int BOTTOM_TEXT_WIDTH = (int) dp2px(33);
    public static final int BOTTOM_TEXT_HEIGHT = (int) dp2px(27);
    public static final int BOTTOM_TEXT_MARGIN_TOP = (int) dp2px(7);
    public static final int BOTTOM_TEXT_MARGIN_LIFT = (int) dp2px(10);
    public static final int BOTTOM_TEXT_MARGIN_RIGHT = (int) dp2px(10);

    public static final int BOX_WIDTH = (int) dp2px(130);
    public static final int BOX_HEIGHT = (int) dp2px(60);
    public static final int BOX_MARGIN = (int) dp2px(10);
    public static final int BOX_MARGIN_TOP = (int) dp2px(40);


    private int mHeight, mWidth;

    private int itemCount = 7;
    private int dashedBetweenHeight = (int) dp2px(40);//虚线之间的距离
    private int bottomLineMarginDashedLine = (int) dp2px(6);//底部的线和虚线之间的距离
    private int clickPosition = -1;

    private int dashedColor = Color.parseColor("#e9e9e9");
    private int bottomLineColor = Color.parseColor("#969fa9");
    private int verticalLineColor = Color.parseColor("#BFBFBF");
    private int bottomTextColor = Color.parseColor("#333333");
    private int pointColor = Color.parseColor("#0084ff");
    private int lineColor = Color.parseColor("#0084ff");
    private int brokenLineColor = Color.parseColor("#ffffff");
    private int tempBaseTop;  //折线的上边Y坐标
    private int tempBaseBottom; //折线的下边Y坐标

    Paint dashedPaint = new Paint();
    Paint bottomLinePaint = new Paint();
    private Paint textPaint = new Paint();
    private Paint pointPaint = new Paint();
    private Paint linePaint = new Paint();
    private Paint brokenPaint = new Paint();
    private Paint verticalLinePaint = new Paint();

    private Path brokenPath;

    public Materials30View(Context context) {
        this(context, null);
    }

    public Materials30View(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Materials30View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        itemList = new ArrayList<>();
        Materials30Item materials30Item;
        Random random = new Random();

        for (int i = 0; i < itemCount; i++) {
            materials30Item = new Materials30Item();
            double s = random.nextInt(26) % (maxTemp - minTemp + 1) + minTemp;
            materials30Item.setCountTon(s);
            materials30Item.setCountMaterial(i);
            materials30Item.setTime("07-" + i);
            itemList.add(materials30Item);
        }


        mWidth = MARGIN_LEFT_ITEM + MARGIN_RIGHT_ITEM + itemCount * (BOTTOM_TEXT_WIDTH + BOTTOM_TEXT_MARGIN_LIFT + BOTTOM_TEXT_MARGIN_RIGHT);
        mHeight = MARGIN_LEFT_ITEM + dashedBetweenHeight * 4 + bottomLineMarginDashedLine + BOTTOM_TEXT_HEIGHT;

        tempBaseTop = MARGIN_LEFT_ITEM;
        tempBaseBottom = MARGIN_LEFT_ITEM + dashedBetweenHeight * 4;
        //虚线
        dashedPaint.setAntiAlias(true);
        dashedPaint.setStyle(Paint.Style.STROKE);
        dashedPaint.setStrokeWidth(dp2px(1));
        dashedPaint.setColor(dashedColor);
        dashedPaint.setPathEffect(new DashPathEffect(new float[]{6, 6}, 0));
        //底部的线
        bottomLinePaint.setAntiAlias(true);
        bottomLinePaint.setStrokeWidth(dp2px(1));
        bottomLinePaint.setColor(bottomLineColor);

        //底部的文字
        textPaint.setTextSize(sp2px(12));
        textPaint.setColor(bottomTextColor);
        textPaint.setAntiAlias(true);

        //点
        pointPaint.setColor(pointColor);
        pointPaint.setAntiAlias(true);

        //折线
        linePaint.setColor(lineColor);
        linePaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(dp2px(1));

        //浮动的
        brokenPath = new Path();
        brokenPaint.setAntiAlias(true);
        brokenPaint.setStyle(Paint.Style.STROKE);
        brokenPaint.setStrokeWidth((dp2px(1)));
        brokenPaint.setStrokeCap(Paint.Cap.ROUND);
        int shadowColor = Color.parseColor("#999999");

        //阴影尺寸
        int shadowSize = (int) dp2px(4);
        brokenPaint.setShadowLayer(shadowSize, 0, 0, shadowColor);

        //竖线
        verticalLinePaint.setAntiAlias(true);
        verticalLinePaint.setStrokeWidth(dp2px(1));
        verticalLinePaint.setColor(verticalLineColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MARGIN_LEFT_ITEM + MARGIN_RIGHT_ITEM + itemCount * (BOTTOM_TEXT_WIDTH + BOTTOM_TEXT_MARGIN_LIFT + BOTTOM_TEXT_MARGIN_RIGHT);
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        onDrawDashedLineAndBottomLine(canvas);
        onDrawBottomText(canvas);
        onDrawPoint(canvas);
        onDrawLine(canvas);
        onDrawFloatBackground(canvas);
    }

    private void onDrawFloatBackground(Canvas canvas) {

        for (int i = 0; i < itemCount; i++) {
            if (i == clickPosition) {
                //画白色背景浮动
                drawFloatTextBackground(canvas, linePoint.get(i).x, linePoint.get(i).y, i);
                //画竖线
                //canvas.drawLine(linePoint.get(i).x, tempBaseTop, linePoint.get(i).x, tempBaseBottom + bottomLineMarginDashedLine, verticalLinePaint);
                //画文字
            }
        }

    }


    private ArrayList<Point> linePoint;

    private void onDrawLine(Canvas canvas) {
        for (int i = 0; i < itemCount; i++) {
            Point point = linePoint.get(i);
            if (i != 0) {
                Point pointPre = linePoint.get(i - 1);
                Path path = new Path();
                path.moveTo(pointPre.x, pointPre.y);
                if (i % 2 == 0)
                    path.cubicTo((pointPre.x + point.x) / 2, (pointPre.y + point.y) / 2 - 7, (pointPre.x + point.x) / 2, (pointPre.y + point.y) / 2 + 7, point.x, point.y);
                else
                    path.cubicTo((pointPre.x + point.x) / 2, (pointPre.y + point.y) / 2 + 7, (pointPre.x + point.x) / 2, (pointPre.y + point.y) / 2 - 7, point.x, point.y);
                canvas.drawPath(path, linePaint);
            }
        }
    }

    private void onDrawPoint(Canvas canvas) {
        ArrayList<Double> temp = new ArrayList<>();
        for (int i = 0; i < itemCount; i++) {
            double countTon = itemList.get(i).getCountTon();
            temp.add(countTon);
        }

        maxTemp = Collections.max(temp);
        minTemp = Collections.min(temp);


        linePoint = new ArrayList<>();
        for (int i = 0; i < itemCount; i++) {
            int left = MARGIN_LEFT_ITEM + i * (BOTTOM_TEXT_WIDTH + BOTTOM_TEXT_MARGIN_LIFT + BOTTOM_TEXT_MARGIN_RIGHT);
            int right = left + (BOTTOM_TEXT_WIDTH + BOTTOM_TEXT_MARGIN_LIFT + BOTTOM_TEXT_MARGIN_RIGHT) - 1;
            int top = dashedBetweenHeight * 4 + bottomLineMarginDashedLine;
            int bottom = dashedBetweenHeight * 4 + bottomLineMarginDashedLine + BOTTOM_TEXT_HEIGHT;
            Rect rect = new Rect(left, top, right, bottom);
//            Point point = calculateTempPoint(left, right, temp.get(i));
            Point point = calculateTempPoint(left, right, (int) itemList.get(i).getCountTon());
            linePoint.add(point);
            //画竖线
            if (i == clickPosition) {
                canvas.drawLine(linePoint.get(i).x, tempBaseTop, linePoint.get(i).x, tempBaseBottom + bottomLineMarginDashedLine, verticalLinePaint);
            }
            canvas.drawCircle(point.x, point.y, dp2px(3), pointPaint);


        }
    }


    //绘制显示浮动文字的背景
    private void drawFloatTextBackground(Canvas canvas, int x, int y, int i) {
        brokenPath.reset();
        brokenPaint.setColor(brokenLineColor);
        brokenPaint.setStyle(Paint.Style.FILL);
        //P1
        Point point = new Point(x, BOX_MARGIN_TOP + MARGIN_LEFT_ITEM);
        brokenPath.moveTo(point.x + BOX_MARGIN, point.y);
        //P2
        point.x = point.x + BOX_WIDTH;
        brokenPath.lineTo(point.x, point.y);
        //P3
        point.y = point.y + BOX_HEIGHT;
        brokenPath.lineTo(point.x, point.y);
        //P4
        brokenPath.lineTo(x + BOX_MARGIN, BOX_MARGIN_TOP + MARGIN_LEFT_ITEM + BOX_HEIGHT);
        //最后一个点连接到第一个点
        canvas.drawPath(brokenPath, brokenPaint);

        int left = (int) (x + BOX_MARGIN * 3.5);
        int right = left + BOX_WIDTH;
        int top = BOX_MARGIN_TOP;
        int bottom = BOX_MARGIN_TOP + BOX_HEIGHT / 2;
        Rect rect = new Rect(left, top, right, bottom);

        Rect targetRect = new Rect(rect.left, rect.bottom, rect.right, rect.bottom);
        Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
        int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
        textPaint.setTextAlign(Paint.Align.RIGHT);

        String text = "入场" + itemList.get(i).getCountMaterial() + "种原材料";
        canvas.drawText(text, targetRect.centerX(), baseline, textPaint);


        int left1 = (int) (x + BOX_MARGIN * 3.5);
        int right1 = left + BOX_WIDTH;
        int top1 = (int) (BOX_MARGIN_TOP + BOX_HEIGHT / 2);
        int bottom1 = BOX_MARGIN_TOP + BOX_HEIGHT;
        Rect rect1 = new Rect(left1, top1, right1, bottom1);

        Rect targetRect1 = new Rect(rect1.left, rect1.bottom, rect1.right, rect1.bottom);
        Paint.FontMetricsInt fontMetrics1 = textPaint.getFontMetricsInt();
        int baseline1 = (targetRect1.bottom + targetRect1.top - fontMetrics1.bottom - fontMetrics1.top) / 2;
        textPaint.setTextAlign(Paint.Align.RIGHT);

//        String textTotal = "合计 9856.00吨";
        String textTotal = "合计 +" + itemList.get(i).getCountTon() + " 吨";
        canvas.drawText(textTotal, targetRect1.centerX(), baseline1, textPaint);


    }

    private double maxTemp = 26;
    private double minTemp = 5;

    // [16, 13, 17, 12, 25, 12, 23, 26, 16, 13, 18, 19, 22, 19, 21, 14, 14, 19, 17, 23, 17, 14, 13, 18, 19, 19, 13, 14, 12, 11, 11]
    private Point calculateTempPoint(int left, int right, int temp) {
        double minHeight = tempBaseTop;
        double maxHeight = tempBaseBottom;
        double tempY = maxHeight - (temp - minTemp) * 1.0 / (maxTemp - minTemp) * (maxHeight - minHeight);
        Point point = new Point((left + right) / 2, (int) tempY);
        return point;
    }

    private void onDrawBottomText(Canvas canvas) {
        for (int i = 0; i < itemCount; i++) {
            int left = MARGIN_LEFT_ITEM + i * (BOTTOM_TEXT_WIDTH + BOTTOM_TEXT_MARGIN_LIFT + BOTTOM_TEXT_MARGIN_RIGHT);
            int right = left + (BOTTOM_TEXT_WIDTH + BOTTOM_TEXT_MARGIN_LIFT + BOTTOM_TEXT_MARGIN_RIGHT) - 1;
            int top = dashedBetweenHeight * 4 + bottomLineMarginDashedLine;
            int bottom = dashedBetweenHeight * 4 + bottomLineMarginDashedLine + BOTTOM_TEXT_HEIGHT;
            Rect rect = new Rect(left, top, right, bottom);

            Rect targetRect = new Rect(rect.left, rect.bottom, rect.right, rect.bottom);
            Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
            int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
            textPaint.setTextAlign(Paint.Align.CENTER);

            String text = itemList.get(i).getTime();
            canvas.drawText(text, targetRect.centerX(), baseline, textPaint);

        }
    }

    private void onDrawDashedLineAndBottomLine(Canvas canvas) {
        for (int i = 0; i < 5; i++) {
            canvas.drawLine(
                    MARGIN_LEFT_ITEM,
                    MARGIN_LEFT_ITEM + dashedBetweenHeight * i,
                    itemCount * (BOTTOM_TEXT_WIDTH + BOTTOM_TEXT_MARGIN_LIFT + BOTTOM_TEXT_MARGIN_RIGHT),
                    MARGIN_LEFT_ITEM + dashedBetweenHeight * i,
                    dashedPaint
            );
        }

        //底部的线
        canvas.drawLine(
                MARGIN_LEFT_ITEM,
                MARGIN_LEFT_ITEM + dashedBetweenHeight * 4 + bottomLineMarginDashedLine,
                itemCount * (BOTTOM_TEXT_WIDTH + BOTTOM_TEXT_MARGIN_LIFT + BOTTOM_TEXT_MARGIN_RIGHT),
                MARGIN_LEFT_ITEM + dashedBetweenHeight * 4 + bottomLineMarginDashedLine,
                bottomLinePaint
        );
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                onActionUpEvent(event);
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }

    private void onActionUpEvent(MotionEvent event) {
        boolean isValidTouch = validateTouch(event.getX(), event.getY());
        if (isValidTouch) {
            invalidate();
        }
    }

    //是否是有效的触摸范围
    private boolean validateTouch(float x, float y) {
        //曲线触摸区域
        for (int i = 0; i < linePoint.size(); i++) {
            // dp2px(8)乘以2为了适当增大触摸面积
            if (x > (linePoint.get(i).x - dp2px(8) * 2) && x < (linePoint.get(i).x + dp2px(8) * 2)) {
                if (y > (linePoint.get(i).y - dp2px(8) * 2) && y < (linePoint.get(i).y + dp2px(8) * 2)) {
                    clickPosition = i;
                    System.out.println("触摸事件：" + clickPosition);
                    return true;
                }
            }
        }
        return false;
    }


    private List<Materials30Item> itemList = new ArrayList<>();

    public void setData(List<Materials30Item> itemList) {
        this.itemList = itemList;
        itemCount = itemList.size();
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
