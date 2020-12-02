package cn.hallowebsite.setting;

import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import cn.hallowebsite.lib.adapter.AbsActivity;


public class SettingActivity extends AbsActivity implements View.OnClickListener {

    private static final String TAG = "SettingActivity";

    @Override
    protected void initOnCreate() {
    }

    @Override
    protected int setLayoutXmlid() {
        return R.layout.activity_setting;
    }

    @Override
    protected boolean isLightStatusBar() {
        return true;
    }

    @Override
    public void onClick(View v) {
    }
}
