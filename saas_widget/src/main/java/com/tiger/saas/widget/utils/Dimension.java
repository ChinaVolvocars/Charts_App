package com.tiger.saas.widget.utils;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * 像素转换工具
 */
public class Dimension {

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
    public static float sp2px(float sp) {
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
