package cn.hallowebsite.setting.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

//用于展示手机区域
public class PhoneModelView extends View {

    private static final String TAG = "PhoneModelView";

    private Context mContext;
    private Paint paint;
    private RectF leftTopRect;
    private RectF rightTopRect;
    private RectF leftBottomRect;
    private RectF rightBottomRect;
    //StatusArea
    private RectF leftTopRectStatus;
    private RectF rightTopRectStatus;
    //ContextArea
    private RectF leftBottomRectContext;
    private RectF rightBottomRectContext;
    private int width;
    private int height;
    private PointF dowPoint;
    private boolean isXArea = false;
    private int yArea = 0;
    private Paint selectPaint;

    //默认边距
    private float defaultMargin = 50f;
    //画笔宽度
    private float defaultPaintWidth = 20;
    //弧度
    private float radius = 50;
    //默认颜色
    private int defaultColor = Color.GRAY;
    //选中的颜色
    private int selectColor = Color.BLUE;
    //区域选中的回调
    private OnAreaSelectListener listener = null;

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public void setMargin(float margin) {
        this.defaultMargin = margin;
    }

    public void setPaintWidth(float paintWidth) {
        this.defaultPaintWidth = paintWidth;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setDefaultColor(int defaultColor) {
        this.defaultColor = defaultColor;
        paint.setColor(defaultColor);
    }

    public void setSelectColor(int selectColor) {
        this.selectColor = selectColor;
    }

    public void setListener(OnAreaSelectListener listener) {
        this.listener = listener;
    }

    public PhoneModelView(Context context) {
        super(context);
        init(context);
    }

    public PhoneModelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PhoneModelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        paint = new Paint();
        paint.setColor(defaultColor);
        paint.setStrokeWidth(defaultPaintWidth);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        selectPaint = new Paint();
        selectPaint.setColor(selectColor);
        selectPaint.setStrokeWidth(defaultPaintWidth);
        selectPaint.setAntiAlias(true);
        selectPaint.setStyle(Paint.Style.STROKE);
    }

    public interface OnAreaSelectListener {
        void onAreaSelectedListener(int index);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;

        leftTopRect = new RectF(defaultMargin,defaultMargin,radius*2.0f + defaultMargin,radius*2.0f + defaultMargin);
        rightTopRect = new RectF(w -radius*2.0f - defaultMargin,0 + defaultMargin,w - defaultMargin,radius*2.0f + defaultMargin);
        leftBottomRect = new RectF(0 + defaultMargin,h-radius*2.0f - defaultMargin,radius*2.0f + defaultMargin,h - defaultMargin);
        rightBottomRect = new RectF(w -radius*2.0f - defaultMargin,h-radius*2.0f - defaultMargin,w - defaultMargin,h - defaultMargin);
        //Statusbar
        leftTopRectStatus = new RectF(defaultMargin + defaultPaintWidth/2.0f,defaultMargin + defaultPaintWidth/2.0f,radius*2.0f + defaultMargin + defaultPaintWidth/2.0f,radius*2.0f + defaultMargin + defaultPaintWidth/2.0f);
        rightTopRectStatus = new RectF(w -radius*2.0f - defaultMargin - defaultPaintWidth/2.0f,0 + defaultMargin + defaultPaintWidth/2.0f,w - defaultMargin - defaultPaintWidth/2.0f,radius*2.0f + defaultMargin + defaultPaintWidth/2.0f);
        //Context
        leftBottomRectContext = new RectF(0 + defaultMargin + defaultPaintWidth/2.0f,h-radius*2.0f - defaultMargin - defaultPaintWidth/2.0f,radius*2.0f + defaultMargin + defaultPaintWidth/2.0f,h - defaultMargin - defaultPaintWidth/2.0f);
        rightBottomRectContext = new RectF(w -radius*2.0f - defaultMargin - defaultPaintWidth/2.0f,h-radius*2.0f - defaultMargin - defaultPaintWidth/2.0f,w - defaultMargin - defaultPaintWidth/2.0f,h - defaultMargin - defaultPaintWidth/2.0f);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: ");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:{
                isXArea = false;
                yArea = 0;
                Log.d(TAG, "onTouchEvent: event.getX() = " + event.getX() + " event.getY() = " + event.getY());
                dowPoint = new PointF(event.getX(),event.getY());
            } break;
            case MotionEvent.ACTION_UP:{
                Log.d(TAG, "onTouchEvent: ACTION_UP" );
                checkDownArea(dowPoint);
                invalidate();
            } break;
        }
        return true;
    }

    private void checkDownArea(PointF dowPoint) {
        if (dowPoint.x > defaultMargin + defaultPaintWidth/2.0f && dowPoint.x < width - defaultMargin - defaultPaintWidth/2.0f) {
            isXArea = true;
        }

        if (dowPoint.y > defaultMargin + defaultPaintWidth/2.0f && dowPoint.y < (radius*2.0f + defaultMargin + defaultPaintWidth/2.0f)*1.0f + (height*1.0f)/100f) {
            yArea = 1;
        } else if (dowPoint.y > (radius*2.0f + defaultMargin + defaultPaintWidth/2.0f)*1.0f + (height*1.0f)/100f && dowPoint.y < (radius*2.0f + defaultMargin + defaultPaintWidth/2.0f)*1.0f + (height*1.0f)/100f + (height*1.0f)/10f ) {
            yArea = 2;
        } else if (dowPoint.y > (radius*2.0f + defaultMargin + defaultPaintWidth/2.0f)*1.0f + (height*1.0f)/100f + (height*1.0f)/10f && dowPoint.y < height -radius*2.0f - defaultMargin - defaultPaintWidth/2.0f) {
            yArea = 3;
        }

        if (listener != null) {
            listener.onAreaSelectedListener(yArea);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制屏幕外框区域
        drawPhone(paint,canvas);

        //绘制状态栏
        drawStatusArea(paint,canvas);

        //绘制工具栏
        drawActionArea(paint,canvas);

        //绘制显示区
        drawContextArea(paint,canvas);

        if (isXArea) {
            switch (yArea) {
                case 1:drawStatusArea(selectPaint,canvas);break;
                case 2:drawActionArea(selectPaint,canvas);break;
                case 3:drawContextArea(selectPaint,canvas);break;
                default:break;
            }
        }
    }

    //绘制内容区域
    private void drawContextArea(Paint paint, Canvas canvas) {
        Path contextPath = new Path();
        contextPath.moveTo(defaultMargin + defaultPaintWidth/2.0f,(radius*2.0f + defaultMargin + defaultPaintWidth/2.0f)*1.0f + (height*1.0f)/100f + (height*1.0f)/10f);
        contextPath.lineTo(defaultMargin + defaultPaintWidth/2.0f,(radius*2.0f + defaultMargin + defaultPaintWidth/2.0f)*1.0f + (height*1.0f)/100f + (height*1.0f)/10f);
        contextPath.lineTo(0 + defaultMargin + defaultPaintWidth/2.0f,height -radius*2.0f - defaultMargin - defaultPaintWidth/2.0f);
        contextPath.arcTo(leftBottomRectContext,-180,-90);
        contextPath.arcTo(rightBottomRectContext,90,-90);
        contextPath.lineTo(width- defaultMargin - defaultPaintWidth/2.0f,(radius*2.0f + defaultMargin + defaultPaintWidth/2.0f)*1.0f + (height*1.0f)/100f + (height*1.0f)/10f);
        contextPath.close();
        canvas.drawPath(contextPath,paint);
    }

    //绘制工具栏区域
    private void drawActionArea(Paint paint, Canvas canvas) {
        Path actionBarPath = new Path();
        actionBarPath.moveTo(defaultMargin + defaultPaintWidth/2.0f,(radius*2.0f + defaultMargin + defaultPaintWidth/2.0f)*1.0f + (height*1.0f)/100f);
        actionBarPath.lineTo(width- defaultMargin - defaultPaintWidth/2.0f,(radius*2.0f + defaultMargin + defaultPaintWidth/2.0f)*1.0f + (height*1.0f)/100f);
        actionBarPath.lineTo(width- defaultMargin - defaultPaintWidth/2.0f,(radius*2.0f + defaultMargin + defaultPaintWidth/2.0f)*1.0f + (height*1.0f)/100f + (height*1.0f)/10f);
        actionBarPath.lineTo(defaultMargin + defaultPaintWidth/2.0f,(radius*2.0f + defaultMargin + defaultPaintWidth/2.0f)*1.0f + (height*1.0f)/100f + (height*1.0f)/10f);
        actionBarPath.close();
        canvas.drawPath(actionBarPath,paint);
    }

    //绘制状态栏区域
    private void drawStatusArea(Paint paint, Canvas canvas) {
        Path statusBarPath = new Path();
        statusBarPath.arcTo(leftTopRectStatus,180,90);
        statusBarPath.arcTo(rightTopRectStatus,270,90);
        statusBarPath.lineTo(width- defaultMargin - defaultPaintWidth/2.0f,(radius*2.0f + defaultMargin + defaultPaintWidth/2.0f)*1.0f + (height*1.0f)/100f);
        statusBarPath.lineTo(defaultMargin + defaultPaintWidth/2.0f,(radius*2.0f + defaultMargin + defaultPaintWidth/2.0f)*1.0f + (height*1.0f)/100f);
        statusBarPath.close();
        canvas.drawPath(statusBarPath,paint);
    }

    //绘制屏幕区域
    private void drawPhone(Paint paint, Canvas canvas) {
        Path path = new Path();
        path.arcTo(leftTopRect,180,90);
        path.arcTo(rightTopRect,270,90);
        path.arcTo(rightBottomRect,0,90);
        path.arcTo(leftBottomRect,90,90);
        path.close();
        canvas.drawPath(path,paint);
    }


}
