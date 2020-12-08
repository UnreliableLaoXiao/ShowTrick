package cn.hallowebsite.setting.fundebug.filemanager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.Date;

import cn.hallowebsite.lib.adapter.AbsRecyclerViewAdapter;
import cn.hallowebsite.setting.R;

public class FileListRvAdapter extends AbsRecyclerViewAdapter<File> {

    public FileListRvAdapter(Activity context) {
        super(context);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.setting_item_fun_file;
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "SetTextI18n"})
    @Override
    public void convert(VH holder, File data, int position) {
        File file = getDataByIndex(position);
        holder.<TextView>getView(R.id.item_about_title).setText((file.getPath().startsWith("/data")?"内部":"外部")+file.getName());
        holder.<TextView>getView(R.id.item_about_mes).setText(new Date(file.lastModified()).toString());
        holder.<ImageView>getView(R.id.item_about_img).setImageDrawable(context.getResources()
                .getDrawable(file.isDirectory()?
                        R.drawable.ic_setting_dir:R.drawable.ic_setting_file));
    }
}
