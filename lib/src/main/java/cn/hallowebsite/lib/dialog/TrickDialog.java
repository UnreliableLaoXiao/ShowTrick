package cn.hallowebsite.lib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;

import java.util.Arrays;

import cn.hallowebsite.lib.utils.WindowUtil;

/*
存在问题1：当设置背景透明时，alpha动画 1 -> 0会失效
不建议使用alpha动画
 */
public class TrickDialog extends Dialog {

    //内容View
    private View mContentView;
    //窗口属性
    private WindowManager.LayoutParams mWindowAttributes;
    //透明背景
    private ColorDrawable mBgTransparent = new ColorDrawable(Color.parseColor("#00000000"));
    //圆角背景
    private GradientDrawable gradientDrawable;

    public TrickDialog(TrickDialogAttribute attribute, Context context) {
        super(context);
        init(attribute,context);
    }

    public TrickDialog(TrickDialogAttribute attribute, Context context, int themeResId) {
        super(context, themeResId);
        init(attribute,context);
    }

    public TrickDialog(TrickDialogAttribute attribute,  Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(attribute,context);
    }

    public static TrickDialog create(TrickDialogAttribute attribute, Context context) {
        return new TrickDialog(attribute,context);
    }

    public static TrickDialog create(TrickDialogAttribute attribute, Context context, int themeResId) {
        return new TrickDialog(attribute,context, themeResId);
    }

    public static TrickDialog create(TrickDialogAttribute attribute, Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        return new TrickDialog(attribute,context, cancelable, cancelListener);
    }

    //初始化的操作
    private void init(TrickDialogAttribute attribute, Context context) {
        //生产内容背景
        buildGradientDrawable(attribute);
        //设置内容
        if ((attribute.mLayoutParams != null)) {
            setContentView(attribute.mContentView, attribute.mLayoutParams);
        } else {
            setContentView(attribute.mContentView);
        }
        mContentView = attribute.mContentView;
        mContentView.setBackground(gradientDrawable);
        //设置宽高
        mWindowAttributes = getWindow().getAttributes();
        if (attribute.mWindowSize != null) {
            if (attribute.mWindowSize.x != -1) {
                mWindowAttributes.width = attribute.mWindowSize.x;
            }

            if (attribute.mWindowSize.y != -1) {
                mWindowAttributes.height = attribute.mWindowSize.y;
            }
        }

        //设置显示位置
        getWindow().setGravity(attribute.mGravity);
        //设置窗口偏移
        if (attribute.mWindowTranslate != null) {
            mWindowAttributes.x = attribute.mWindowTranslate.x;
            mWindowAttributes.y = attribute.mWindowTranslate.y;
        }
        //设置背景透明
        if (attribute.mIsBgTransparent) {
            getWindow().setBackgroundDrawable(mBgTransparent);
        }
        //设置外圈透明度
        getWindow().setDimAmount(attribute.mOutSideAlphe);
        //设置出入动画
        if (attribute.mAnimationsId != 0) {
            getWindow().setWindowAnimations(attribute.mAnimationsId);
        }
    }

    private void buildGradientDrawable(TrickDialogAttribute attribute) {
        //设置圆角
        gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.WHITE);
        gradientDrawable.setCornerRadii(attribute.mRadius);
        gradientDrawable.setStroke(attribute.mStrokeWidth,attribute.mStrokeColor);
        //TODO 实现变色
    }

    public View getViewById(int id) {
        return mContentView.findViewById(id);
    }

    public View getContentView() {
        return mContentView;
    }

    public static class TrickDialogAttribute {

        private float[] mRadius = new float[8];
        private Point mWindowSize = null;
        private Point mWindowTranslate = null;
        private int mGravity = -1;
        private boolean mIsBgTransparent = true;
        private float mOutSideAlphe = 1.0f;
        private int mAnimationsId = 0;
        private int mStrokeColor = 0xFFFFFFFF;
        private int mStrokeWidth = 0;
        private View mContentView = null;
        private ViewGroup.LayoutParams mLayoutParams = null;

        public TrickDialogAttribute setCorners(float radius) {
            Arrays.fill(mRadius,WindowUtil.dpToPxFloat(radius));
            return this;
        }

        public TrickDialogAttribute setCorners(float leftTopRadius, float rightTopRadius, float leftBottomRadius, float rightBottomRadius) {
            mRadius[0] = mRadius[1] = WindowUtil.dpToPxFloat(leftTopRadius);
            mRadius[2] = mRadius[3] = WindowUtil.dpToPxFloat(rightTopRadius);
            mRadius[4] = mRadius[5] = WindowUtil.dpToPxFloat(rightBottomRadius);
            mRadius[6] = mRadius[7] = WindowUtil.dpToPxFloat(leftBottomRadius);
            return this;
        }

        public TrickDialogAttribute setCorners(float leftTopRadius_x, float leftTopRadius_y,
                                               float rightTopRadius_x, float rightTopRadius_y,
                                               float leftBottomRadius_x, float leftBottomRadius_y,
                                               float rightBottomRadius_x, float rightBottomRadius_y) {
            mRadius[0] = WindowUtil.dpToPxFloat(leftTopRadius_x);
            mRadius[1] = WindowUtil.dpToPxFloat(leftTopRadius_y);
            mRadius[2] = WindowUtil.dpToPxFloat(rightTopRadius_x);
            mRadius[3] = WindowUtil.dpToPxFloat(rightTopRadius_y);
            mRadius[4] = WindowUtil.dpToPxFloat(leftBottomRadius_x);
            mRadius[5] = WindowUtil.dpToPxFloat(leftBottomRadius_y);
            mRadius[6] = WindowUtil.dpToPxFloat(rightBottomRadius_x);
            mRadius[7] = WindowUtil.dpToPxFloat(rightBottomRadius_y);
            return this;
        }

        /**
         * 设置的单位为 px ** 不是dp **
         * @param windowWidth
         * @param windowHeight
         * @return
         */
        public TrickDialogAttribute setWindowSize(int windowWidth, int windowHeight) {
            if (mWindowSize == null) {
                mWindowSize = new Point();
            }
            mWindowSize.x = windowWidth;
            mWindowSize.y = windowHeight;
            return this;
        }

        public TrickDialogAttribute setGravity(int gravity) {
            mGravity = gravity;
            return this;
        }

        public TrickDialogAttribute setWindowTranslate(int x, int y) {
            if (mWindowTranslate == null) {
                mWindowTranslate = new Point();
            }

            mWindowTranslate.x = WindowUtil.dpToPxInt(x);
            mWindowTranslate.y = WindowUtil.dpToPxInt(y);
            return this;
        }

        public TrickDialogAttribute setBgTrasparent(boolean isBgTransparent) {
            mIsBgTransparent = isBgTransparent;
            return this;
        }

        public TrickDialogAttribute setBgOutSizeAlpha(float alpha) {
            mOutSideAlphe = alpha;
            return this;
        }

        public TrickDialogAttribute setAnimations(@StyleRes int id) {
            mAnimationsId = id;
            return this;
        }

        public TrickDialogAttribute setStrokeColorAndWidth(int width, int color) {
            mStrokeColor = color;
            mStrokeWidth = width;
            return this;
        }

        public TrickDialogAttribute contentView(int layoutResID, Context context) {
            mContentView = LayoutInflater.from(context).inflate(layoutResID, null, false);
            return this;
        }

        public TrickDialogAttribute contentView(@NonNull View contentView, @Nullable ViewGroup.LayoutParams params) {
            mContentView = contentView;
            mLayoutParams = params;
            return this;
        }

        public TrickDialogAttribute contentView(View contentView) {
            mContentView = contentView;
            return this;
        }

        public TrickDialog build(Context context) {
            return create(this,context);
        }

        public TrickDialog build(Context context, int themeResId) {
            return create(this,context,themeResId);
        }

        public TrickDialog build(Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
            return create(this,context,cancelable,cancelListener);
        }
    }

}
