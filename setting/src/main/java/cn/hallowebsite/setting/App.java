package cn.hallowebsite.setting;

import android.app.Application;
import android.content.res.Configuration;

import androidx.annotation.NonNull;

import cn.hallowebsite.lib.router.Router;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Router.init(getApplicationContext());
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
