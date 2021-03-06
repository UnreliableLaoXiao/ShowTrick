package cn.hallowebsite.setting.fundebug;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.hallowebsite.lib.adapter.AbsRecyclerViewAdapter;
import cn.hallowebsite.lib.router.Router;
import cn.hallowebsite.setting.R;

public class FunListRvAdapter extends AbsRecyclerViewAdapter<FunListItem> {

    private static final String TAG = "FunListRvAdapter";
    private ArrayList<FunListItem> funListItems;
    private final Context mContext;

    public FunListRvAdapter(Context context) {
        mContext = context;
        //初始化数据
        initLocalData(context);
        //设置点击事件
        setOnItemClickListener(new ItemClickListener());
    }

    @SuppressLint("ResourceType")
    private void initLocalData(Context context) {
        funListItems = new ArrayList<>();
        Resources resources = context.getResources();
        TypedArray settingAbout = resources.obtainTypedArray(R.array.fun_list);
        String[] stringArray = resources.getStringArray(R.array.fun_list);
        for (int i = 0; i < stringArray.length; i++) {
            FunListItem funListItem = new FunListItem();
            TypedArray typedArray = resources.obtainTypedArray(settingAbout.getResourceId(i, 0));
            funListItem.title = typedArray.getString(0);
            funListItem.mes = typedArray.getString(1);
            funListItem.routerPath = typedArray.getString(2);
            funListItem.iconId = typedArray.getResourceId(3,0);
            funListItems.add(funListItem);
            typedArray.recycle();
        }
        settingAbout.recycle();
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.setting_item_fun;
    }

    @Override
    protected FunListItem getDataByPosition(int position) {
        return funListItems.get(position);
    }

    @Override
    protected int getTotalSize() {
        return funListItems.size();
    }

    @Override
    public void convert(VH holder, FunListItem data, int position) {
        holder.<TextView>getView(R.id.item_about_title).setText(data.title);
        holder.<TextView>getView(R.id.item_about_mes).setText(data.mes);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.<ImageView>getView(R.id.item_about_img).setImageDrawable(mContext.getDrawable(data.iconId));
        } else {
            holder.<ImageView>getView(R.id.item_about_img).setImageDrawable(mContext.getResources().getDrawable(data.iconId));
        }
    }

    private class ItemClickListener implements OnItemClickListener<FunListItem> {
        @Override
        public void onItemClick(View view, int position, FunListItem funListItem) {
            Log.d(TAG, "onItemClick: " + funListItem);
            Router.startPath(funListItem.routerPath,mContext);
        }

        @Override
        public void onItemLongClick(View view, int position, FunListItem funListItem) {

        }
    }
}
