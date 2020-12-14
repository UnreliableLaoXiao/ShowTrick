package cn.hallowebsite.setting.fundebug.showdialog;

import android.view.Gravity;
import android.view.View;

import androidx.annotation.StyleRes;

import cn.hallowebsite.lib.adapter.AbsActivity;
import cn.hallowebsite.lib.dialog.TrickDialog;
import cn.hallowebsite.lib.router.ActivityRouter;
import cn.hallowebsite.setting.R;

@ActivityRouter(path = "Setiting/About/Fun/DialogShow")
public class DialogShowActivity extends AbsActivity {
    @Override
    protected void initOnCreate() {

    }

    @Override
    protected int setLayoutXmlid() {
        return R.layout.activity_dialog_show;
    }

    public void onBack(View view) {
        finish();
    }

    public void showDialog(View view) {
        TrickDialog dialog = new TrickDialog.TrickDialogAttribute()
                //单位dp
                .setCorners(20)
                .setWindowSize(300, 300)
                .setGravity(Gravity.CENTER)
                .setWindowTranslate(100, 200)
                .setBgTrasparent(true)
                .setBgOutSizeAlpha(1.0f)
                .setAnimations(0)
                .build(this);
    }
}
