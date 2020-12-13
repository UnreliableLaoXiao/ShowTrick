package cn.hallowebsite.setting.fundebug.filemanager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import cn.hallowebsite.lib.adapter.AbsRecyclerViewAdapter;
import cn.hallowebsite.setting.R;

public class FileListRvAdapter extends AbsRecyclerViewAdapter<File> {

    private ArrayList<File> files;

    public FileListRvAdapter(ArrayList<File> files) {
        this.files = files;
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.setting_item_fun_file;
    }

    @Override
    protected File getDataByPosition(int position) {
        return files.get(position);
    }

    @Override
    protected int getTotalSize() {
        return files.size();
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "SetTextI18n"})
    @Override
    public void convert(VH holder, File data, int position) {
        File file = files.get(position);
        holder.<TextView>getView(R.id.item_about_title).setText((file.getPath().startsWith("/data")?"内部":"外部")+file.getName());
        holder.<TextView>getView(R.id.item_about_mes).setText(new Date(file.lastModified()).toString());
        holder.<ImageView>getView(R.id.item_about_img).setImageDrawable(holder.itemView.getContext().getResources()
                .getDrawable(file.isDirectory()?
                        R.drawable.ic_setting_dir:R.drawable.ic_setting_file));
    }
}
