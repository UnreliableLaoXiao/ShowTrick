package cn.hallowebsite.setting;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cn.hallowebsite.lib.adapter.AbsActivity;
import cn.hallowebsite.setting.phoneinfo.AboutRvAdapter;


public class SettingActivity extends AbsActivity {

    private static final String TAG = "SettingActivity";

    @Override
    protected void initOnCreate() {
        //设置中关于列表
        RecyclerView setting_rv_about = findViewById(R.id.setting_rv_about);
        setting_rv_about.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        setting_rv_about.setAdapter(new AboutRvAdapter(this));
    }

    @Override
    protected int setLayoutXmlid() {
        return R.layout.activity_setting;
    }

    @Override
    protected boolean isLightStatusBar() {
        return true;
    }


    public void onBack(View v) {
        Log.d(TAG, "onBack: ");
    }
}
