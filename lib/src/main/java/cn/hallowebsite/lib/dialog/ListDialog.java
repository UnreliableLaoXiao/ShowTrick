package cn.hallowebsite.lib.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Display;
import android.view.Gravity;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cn.hallowebsite.lib.R;
import cn.hallowebsite.lib.adapter.AbsRecyclerViewAdapter;
import cn.hallowebsite.lib.utils.WindowUtil;

public class ListDialog {

    private ArrayList<String> arrayList = new ArrayList<>();
    private TrickDialog trickDialog;
    private Context mContext;

    public ListDialog(Context context) {
        init(context);
    }

    public ListDialog setItem(ArrayList<String> array) {
        arrayList.addAll(array);
        return this;
    }

    private void init(Context context) {
        mContext = context;


    }

    public void show() {



        //得到屏幕尺寸
        trickDialog = new TrickDialog.TrickDialogAttribute()
                //单位dp
                .contentView(R.layout.list_dialog, mContext)
                .setCorners(20)
                .setWindowSize(WindowUtil.getWindowMetrics(mContext)[0], -1)
                .setGravity(Gravity.BOTTOM)
                .setWindowTranslate(0, 0)
                .setBgTrasparent(true)
                .setBgOutSizeAlpha(0.2f)
                .setAnimations(0)
                .build(mContext);

        trickDialog.setCanceledOnTouchOutside(true);
        RecyclerView rvList =  (RecyclerView) this.trickDialog.getViewById(R.id.dialog_list);
        //获取适配器
        Adapter adapter = new Adapter(arrayList);
        rvList.setLayoutManager(new LinearLayoutManager(mContext,RecyclerView.VERTICAL,false));
        rvList.setAdapter(adapter);
        trickDialog.show();
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
