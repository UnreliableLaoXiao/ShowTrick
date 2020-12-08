package cn.hallowebsite.setting.fundebug.filemanager;

import android.app.Activity;
import android.widget.TextView;

import java.util.ArrayList;

import cn.hallowebsite.lib.adapter.AbsRecyclerViewAdapter;
import cn.hallowebsite.setting.R;

/**
 * 文件管理界面 中上层的导航适配器
 */
public class FilePathNavRvAdapter extends AbsRecyclerViewAdapter<String> {

    private final ArrayList<String> paths;

    public FilePathNavRvAdapter(Activity context) {
        super(context);
        paths = getmDataSource();
    }

    public void addPath(String...paths) {
        for (String path : paths) {
            this.paths.add(path);
        }
        notifyDataSetChanged();
    }

    public void moveToIndex(int index) {
        for (int i = paths.size() - 1; i > index ; i--) {
            paths.remove(i);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.setting_item_fun_file_path;
    }

    @Override
    public void convert(VH holder, String data, int position) {
        holder.<TextView>getView(R.id.item_file_path).setText(data);
    }
}
