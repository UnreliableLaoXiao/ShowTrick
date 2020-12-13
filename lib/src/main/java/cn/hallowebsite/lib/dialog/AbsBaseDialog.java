package cn.hallowebsite.lib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/*
存在问题1：当设置背景透明时，alpha动画 1 -> 0会失效
不建议使用alpha动画
 */
public abstract class AbsBaseDialog extends Dialog {

    private Context mContext;

    private View contentView;
    private LayoutInflater inflater;
    //TODO 待完善开发动画效果
    private AnimationSet defaultEnterAnim = null;
    private AnimationSet defaultExitAnim = null;

    //是否使用动画
    boolean isUserAnim = true;
    //窗口属性
    private WindowManager.LayoutParams attributes;

    public AbsBaseDialog(@NonNull Context context) {
        super(context);
        initAbsBaseDialog(context);
    }

    public AbsBaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        initAbsBaseDialog(context);
    }

    protected AbsBaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initAbsBaseDialog(context);
    }


    //初始化的操作
    private void initAbsBaseDialog(Context context) {
        mContext = context;
        //设置默认点击外面区域关闭
        setCanceledOnTouchOutside(true);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        attributes = getWindow().getAttributes();
    }

    public AbsBaseDialog contentView(int layoutResID) {
        inflater = LayoutInflater.from(mContext);
        contentView = inflater.inflate(layoutResID, null, false);
        super.setContentView(contentView);
        return this;
    }

    public AbsBaseDialog contentView(@NonNull View view, @Nullable ViewGroup.LayoutParams params) {
        this.contentView = view;
        super.setContentView(view, params);
        return this;
    }

    public AbsBaseDialog contentView(View contentView) {
        this.contentView = contentView;
        super.setContentView(contentView);
        return this;
    }

    @Override
    public void show() {
        getWindow().setAttributes(attributes);
        this.create();
        super.show();
        if (isUserAnim &&  defaultEnterAnim != null && contentView != null) {
            contentView.startAnimation(getEnterAnimation());
        }
    }

    @Override
    public void dismiss() {
        if (isUserAnim && defaultExitAnim != null && contentView != null){
            contentView.startAnimation(getExitAnimation());
        } else {
            super.dismiss();
        }
    }

    public AbsBaseDialog isUseAnim(boolean isUserAnim) {
        this.isUserAnim = isUserAnim;
        return this;
    }

    public AbsBaseDialog canceledOnTouchOutside(boolean cancel) {
        setCanceledOnTouchOutside(cancel);
        return this;
    }

    public AbsBaseDialog setWindowWidth(int windowWidth) {
        attributes.width = windowWidth;
        return this;
    }

    public AbsBaseDialog setWindowHeight(int windowHeight) {
        attributes.height = windowHeight;
        return this;
    }

    public AbsBaseDialog setPosition(int gravity) {
        getWindow().setGravity(gravity);
        return this;
    }

    //设置默认区域背景颜色透明度
    public AbsBaseDialog setDimAmount(float amount) {
        getWindow().setDimAmount(amount);
        return this;
    }

    protected Animation getExitAnimation() {
        return defaultExitAnim;
    }

    protected Animation getEnterAnimation() {
        return defaultEnterAnim;
    }

    public View getViewById(int id) {
        return contentView.findViewById(id);
    }

    public View getContentView() {
        return contentView;
    }


}
