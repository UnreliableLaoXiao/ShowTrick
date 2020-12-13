package cn.hallowebsite.lib.router;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;

public class Router {

    private static final String TAG = Router.class.getSimpleName();

    private static HashMap<String,Class<?>> routerMap = new HashMap<>();

    public static void init(Context context) {
        resolveActivity(context);
    }

    @SuppressLint("WrongConstant")
    private static void resolveActivity(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        ActivityInfo[] activityInfos;
        try {
            activityInfos = packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES).activities;
            for (ActivityInfo activityInfo : activityInfos) {
                Log.d(TAG, "resolveActivity: " + activityInfo.name);
                Class<?> aClass = Class.forName(activityInfo.name);
                if (aClass.isAnnotationPresent(ActivityRouter.class)) {
                    Log.d(TAG, "resolveActivity: path = " + aClass.getAnnotation(ActivityRouter.class).path());
                    routerMap.put(aClass.getAnnotation(ActivityRouter.class).path(),aClass);
                }
            }
        } catch (PackageManager.NameNotFoundException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void startPath(String path, Context context) {
        startPath(path,null,context);
    }

    public static void startPath(String path , Bundle bundle, Context context) {
        startPath(path,bundle,-1,context);
    }

    public static void startPath(String path , Bundle bundle,int requestCode, Context context) {
        Class<?> aClass = routerMap.get(path);

        if (aClass == null) {
            Log.d(TAG, "startPath: " + path + " don't exist!!!");
            return;
        }

        Intent intent = new Intent(context,aClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }

        if (requestCode != -1) {
            if (context instanceof Activity) {
                ((Activity)context).startActivityForResult(intent,requestCode);
            } else {
                Log.d(TAG, "startPath: context don't is activity");
            }

        } else {
            context.startActivity(intent);
        }
    }
}
