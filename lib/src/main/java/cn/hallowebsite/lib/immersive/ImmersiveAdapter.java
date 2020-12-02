package cn.hallowebsite.lib.immersive;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

//TODO 重新测试功能
//有问题  无法生效  小米6
public class ImmersiveAdapter {


     /*
      测试手机  谷歌安卓模拟器  侧边栏为DrawerLayout时，设置这个属性实现沉浸式   可用
    */

    public static void setFragmentAdapter(Fragment fragment, View rootView, boolean headerIsImage) {
        /*
        适配安卓4.4--5.0
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            if (!headerIsImage) {
                int resourceId = fragment.getActivity().getResources().getIdentifier("status_bar_height", "dimen", "android");
                int height = fragment.getActivity().getResources().getDimensionPixelSize(resourceId);
                rootView.setPadding(0, height, 0, 0);
            }
        }
        /*
        适配安卓5.0以上
         */
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            if (!headerIsImage) {
                int resourceId = fragment.getActivity().getResources().getIdentifier("status_bar_height", "dimen", "android");
                int height = fragment.getActivity().getResources().getDimensionPixelSize(resourceId);
                rootView.setPadding(0, height, 0, 0);
            }
        }
    }

    /*
    toolbar.getBackground()
     */
    public static void setNavigationViewAdapter(DrawerLayout drawer, Context context, NavigationView navigationView, View rootLayout, Drawable bg) {
        /*
        适配安卓4.4--5.0
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //将侧边栏顶部延伸至status bar
            drawer.setFitsSystemWindows(true);
            //将主页面顶部延伸至status bar;虽默认为false,但经测试,DrawerLayout需显示设置
            drawer.setClipToPadding(false);
        }
        /*
        适配安卓5.0以上
         */
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            int height = context.getResources().getDimensionPixelSize(resourceId);
            View headerView = navigationView.getHeaderView(0);
            navigationView.setForeground(new BitmapDrawable());
            rootLayout.setPadding(0, height, 0, 0);
            headerView.setPadding(headerView.getPaddingLeft(),headerView.getPaddingTop()+height,headerView.getPaddingRight(),headerView.getPaddingBottom());
            rootLayout.setBackground(bg);
        }
    }

    public static void setActivityAdapter(Activity activity) {
        setActivityAdapter(activity,true);
    }

    public static void setActivityAdapter(Activity activity, boolean isSetLightStatusBar) {
        /*
        适配安卓4.4--5.0
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        /*
        适配安卓5.0以上
         */
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//                //注意要清除 FLAG_TRANSLUCENT_STATUS flag
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

            /*
               适配6.0以上状态栏文字颜色,设置为深色
            */
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (isSetLightStatusBar)
                    activity.getWindow().getDecorView().setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }

        }
    }


}
