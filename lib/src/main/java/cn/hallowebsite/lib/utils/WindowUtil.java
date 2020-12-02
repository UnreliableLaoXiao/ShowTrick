package cn.hallowebsite.lib.utils;

import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

//TODO 待测试
public class WindowUtil {

    //获取屏幕区域的宽高等尺寸获取
    public void getWindowMetrics(AppCompatActivity activity){
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;
    }

    //应用程序App区域宽高等尺寸获取
    public void getAppWindowMetrics(AppCompatActivity activity){
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int height = rect.height();
        int width = rect.width();
    }

    //获取状态栏高度
    public void getAppStatusWindowMetrics(AppCompatActivity activity){
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int statusheight = rect.top;
    }

    //View布局区域宽高等尺寸获取
    public void getAppViewWindowMetrics(AppCompatActivity activity){
        Rect rect = new Rect();
        activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(rect);
        int height = rect.height();
        int width = rect.width();
    }

    /**
     * 得到当前设备的像素密度,
     * 返回 0.75 就是 LDPI;
     * 返回 1.0 就是 MDPI;
     * 返回 1.5 就是 HDPI;
     * 返回 2.0 就是 XHDPI;
     * 返回 3.0 就是 XXHDPI;
     * 返回 4.0 就是 XXXHDPI.
     * @return float 像素密度值
     */
    public static float getDensity() {
        return Resources.getSystem().getDisplayMetrics().density;
    }

    public static int dpToPx(int dp){
        return (int)(dp * Resources.getSystem().getDisplayMetrics().density + 0.5f);
    }

}
