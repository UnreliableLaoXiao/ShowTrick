package cn.hallowebsite.setting.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.hallowebsite.lib.adapter.AbsRecyclerViewAdapter;
import cn.hallowebsite.lib.router.Router;
import cn.hallowebsite.setting.Item.AboutItem;
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

    //定义关于列表的小图标
    private int[] iconIds = {
            R.drawable.ic_phone_info, R.drawable.ic_about_help, R.drawable.ic_func_debug
    };

    //初始化资源
    private void initLocalData() {
        String[] titles = context.getApplicationContext().getResources().getStringArray(R.array.setting_about_title);
        String[] mess = context.getApplicationContext().getResources().getStringArray(R.array.setting_about_mes);
        String[] routerPaths = context.getApplicationContext().getResources().getStringArray(R.array.setting_about_router);
        ArrayList<AboutItem> aboutItems = new ArrayList<>();
        for (int i = 0 ; i < titles.length ; i++) {
            AboutItem aboutItem = new AboutItem(titles[i], iconIds[i], routerPaths[i], mess[i]);
            aboutItems.add(aboutItem);
        }
        addDataSource(aboutItems);
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
