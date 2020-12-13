package cn.hallowebsite.lib.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cn.hallowebsite.lib.R;
import cn.hallowebsite.lib.adapter.AbsRecyclerViewAdapter;
import cn.hallowebsite.lib.utils.WindowUtil;

public class ListDialog extends AbsBaseDialog {

    private ArrayList<String> arrayList = new ArrayList<>();

    public ListDialog(@NonNull Context context) {
        super(context);
        init(context);
    }

    public ListDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    protected ListDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    public ListDialog setItem(ArrayList<String> array) {
        arrayList.addAll(array);
        return this;
    }

    private void init(Context context) {
        contentView(R.layout.list_dialog);
        //设置圆角
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(Color.WHITE);
        bg.setCornerRadii(new float[]{WindowUtil.dpToPx(20),WindowUtil.dpToPx(20),WindowUtil.dpToPx(20),WindowUtil.dpToPx(20),0.0f,0.0f,0.0f,0.0f});
        bg.setStroke(WindowUtil.dpToPx(1),Color.parseColor("#9f9f9f"));
        getContentView().setBackground(bg);
        //设置宽度
        Display defaultDisplay = getWindow().getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        setWindowWidth(point.x);
        //设置弹窗参数
        canceledOnTouchOutside(true);
        setDimAmount(0);
        setPosition(Gravity.BOTTOM);

        //获取适配器
        Adapter adapter = new Adapter(arrayList);
        RecyclerView rvList = (RecyclerView) getViewById(R.id.dialog_list);
        rvList.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
        rvList.setAdapter(adapter);
    }

    class Adapter extends AbsRecyclerViewAdapter<String> {

        private ArrayList<String> source;
        private TextView textView;

        public Adapter(ArrayList<String> source) {
            this.source = source;
        }

        @Override
        public int getLayoutId(int viewType) {
            return R.layout.item_list_dialog;
        }

        @Override
        protected String getDataByPosition(int position) {
            return source.get(position);
        }

        @Override
        protected int getTotalSize() {
            return source.size();
        }

        @Override
        public void convert(VH holder, String data, int position) {
            textView = holder.<TextView>getView(R.id.tv);
            textView.setText(data);
        }
    }

}
