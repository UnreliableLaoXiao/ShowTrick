package cn.hallowebsite.lib.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public final class ActivityUtil {

    /**
     * 情况当前Activity栈 重新启动一个Activity到一个新的栈中
     * 例如：重新启动应用（重新进入主界面）
     * @param context
     * @param cls
     * @param <T>
     */
    public static <T extends Activity> void clearAllTaskAndStartNewActivity(Context context , Class<T> cls) {
        Log.e("language", "重新启动");
        Intent intent = new Intent(context, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
