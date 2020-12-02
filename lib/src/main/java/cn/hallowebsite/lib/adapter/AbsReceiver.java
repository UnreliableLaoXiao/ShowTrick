package cn.hallowebsite.lib.adapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

//TODO 待使用
public abstract class AbsReceiver extends BroadcastReceiver {

    //用于判断当前是否已经注册了
    private boolean isRegister = false;
    private static final String TAG = "AbsReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: action = " + intent.getAction());
        onAction(intent);
    }

    /**
     * 处理收到的广播信息
     * @param intent
     */
    protected abstract void onAction(Intent intent);

    public void register(Context context, String... actions) {
        if (!isRegister) {
            IntentFilter filter = new IntentFilter();
            for (String action : actions) {
                filter.addAction(action);
            }
            isRegister = true;
            context.registerReceiver(this, filter);
        } else {
            Log.d(TAG, "register: already register");
        }
    }

    public void unRegister(Context context) {
        if (isRegister) {
            context.unregisterReceiver(this);
        } else {
            Log.d(TAG, "unRegister: already unRegister");
        }
    }
}
