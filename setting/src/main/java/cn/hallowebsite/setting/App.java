package cn.hallowebsite.setting;

import android.app.Application;

import cn.hallowebsite.lib.router.Router;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Router.init(getApplicationContext());
    }
}
