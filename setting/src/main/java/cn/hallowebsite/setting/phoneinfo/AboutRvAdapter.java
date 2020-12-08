package cn.hallowebsite.setting.phoneinfo;

import android.annotation.SuppressLint;
import android.app.Activity;
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

public class AboutRvAdapter extends AbsRecyclerViewAdapter<AboutItem> {

    private static final String TAG = "AboutRvAdapter";

    public AboutRvAdapter(Activity context) {
        super(context);
        //初始化数据
        initLocalData();
        //设置点击事件
        setOnItemClickListener(new ItemClickListener());
    }

    //初始化资源
    @SuppressLint("ResourceType")
    private void initLocalData() {
        ArrayList<AboutItem> aboutItems = getmDataSource();
        Resources resources = context.getApplicationContext().getResources();
        TypedArray settingAbout = resources.obtainTypedArray(R.array.setting_about);
        String[] stringArray = resources.getStringArray(R.array.setting_about);
        for (int i = 0; i < stringArray.length; i++) {
            AboutItem aboutItem = new AboutItem();
            TypedArray typedArray = resources.obtainTypedArray(settingAbout.getResourceId(i, 0));
            aboutItem.title = typedArray.getString(0);
            aboutItem.mes = typedArray.getString(1);
            aboutItem.routerPath = typedArray.getString(2);
            aboutItem.iconId = typedArray.getResourceId(3,0);
            aboutItems.add(aboutItem);
            typedArray.recycle();
        }
        settingAbout.recycle();
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.setting_item_about;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void convert(VH holder, AboutItem data, int position) {
        holder.<TextView>getView(R.id.item_about_title).setText(data.title);
        holder.<TextView>getView(R.id.item_about_mes).setText(data.mes);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.<ImageView>getView(R.id.item_about_img).setImageDrawable(context.getDrawable(data.iconId));
        } else {
            holder.<ImageView>getView(R.id.item_about_img).setImageDrawable(context.getResources().getDrawable(data.iconId));
        }
    }

    private class ItemClickListener implements OnItemClickListener<AboutItem> {
        @Override
        public void onItemClick(View view, int position, AboutItem aboutItem) {
            Log.d(TAG, "onItemClick: " + aboutItem);
            Router.startPath(aboutItem.routerPath,context);
        }

        @Override
        public void onItemLongClick(View view, int position, AboutItem aboutItem) {

        }
    }
}
