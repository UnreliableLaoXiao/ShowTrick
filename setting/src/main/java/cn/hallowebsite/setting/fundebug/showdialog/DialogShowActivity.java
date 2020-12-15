package cn.hallowebsite.setting.fundebug.showdialog;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;

import androidx.annotation.StyleRes;

import cn.hallowebsite.lib.adapter.AbsActivity;
import cn.hallowebsite.lib.dialog.TrickDialog;
import cn.hallowebsite.lib.router.ActivityRouter;
import cn.hallowebsite.lib.utils.WindowUtil;
import cn.hallowebsite.setting.R;

@ActivityRouter(path = "Setiting/About/Fun/DialogShow")
public class DialogShowActivity extends AbsActivity implements SeekBar.OnSeekBarChangeListener {

    private View contentView;
    private SeekBar leftTopSeekbar;
    private SeekBar rightTopSeekbar;
    private SeekBar leftBottomSeekbar;
    private SeekBar rightBottomSeekbar;
    private float lTmRadius;
    private float rTmRadius;
    private float lBmRadius;
    private float rBmRadius;

    @Override
    protected void initOnCreate() {
        //初始化配置项
        leftTopSeekbar = findViewById(R.id.left_top_seekbar);
        rightTopSeekbar = findViewById(R.id.right_top_seekbar);
        leftBottomSeekbar = findViewById(R.id.left_bottom_seekbar);
        rightBottomSeekbar = findViewById(R.id.right_bottom_seekbar);
        leftTopSeekbar.setOnSeekBarChangeListener(this);
        rightTopSeekbar.setOnSeekBarChangeListener(this);
        leftBottomSeekbar.setOnSeekBarChangeListener(this);
        rightBottomSeekbar.setOnSeekBarChangeListener(this);


    }

    @Override
    protected int setLayoutXmlid() {
        return R.layout.activity_dialog_show;
    }

    public void onBack(View view) {
        finish();
    }

    public void showDialog(View view) {
        contentView = LayoutInflater.from(this).inflate(R.layout.dialog_style_1, null, false);
        new TrickDialog.TrickDialogAttribute()
                //单位dp
                .contentView(contentView)
                .setCorners(lTmRadius,rTmRadius,lBmRadius,rBmRadius)
                .setWindowSize(WindowUtil.dpToPxInt(300), -1)
                .setGravity(Gravity.CENTER)
                .setWindowTranslate(0, 0)
                .setBgTrasparent(true)
                .setBgOutSizeAlpha(0.2f)
                .setAnimations(0)
                .build(this)
                .show();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        switch (seekBar.getId()) {
            case R.id.left_top_seekbar:
                lTmRadius = progress * 1.0f;
                break;
            case R.id.right_top_seekbar:
                rTmRadius = progress * 1.0f;
                break;
            case R.id.left_bottom_seekbar:
                lBmRadius = progress * 1.0f;
                break;
            case R.id.right_bottom_seekbar:
                rBmRadius = progress * 1.0f;
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
