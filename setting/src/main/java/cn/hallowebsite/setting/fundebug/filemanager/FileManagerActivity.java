package cn.hallowebsite.setting.fundebug.filemanager;

import android.util.Log;
import android.view.Gravity;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import cn.hallowebsite.lib.adapter.AbsActivity;
import cn.hallowebsite.lib.adapter.AbsRecyclerViewAdapter;
import cn.hallowebsite.lib.dialog.ListDialog;
import cn.hallowebsite.lib.router.ActivityRouter;
import cn.hallowebsite.setting.R;

@ActivityRouter(path = "Setiting/About/Fun/FileManager")
public class FileManagerActivity extends AbsActivity implements AbsRecyclerViewAdapter.OnItemClickListener<File> {

    private static final String TAG = "FileManagerActivity";

    private RecyclerView fileRvList;
    private FileListRvAdapter adapter;
    private RecyclerView filepathNav;
    private FilePathNavRvAdapter filePathNavRvAdapter;
    private HashMap<String,File> navMap = new HashMap<>();
    private ArrayList<File> startPath = new ArrayList<>();
    private ArrayList<File> files;

    @Override
    protected void initOnCreate() {
        //文件夹/文件列表
        fileRvList = findViewById(R.id.file_rv_list);
        fileRvList.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        adapter = new FileListRvAdapter(getDataSource());
        fileRvList.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        fileRvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }
        });


        //文件路径导航
        filepathNav = findViewById(R.id.setting_rv_filepath_nav);
        filepathNav.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        filePathNavRvAdapter = new FilePathNavRvAdapter(new ArrayList<String>());
        filePathNavRvAdapter.addPath("/");
        filepathNav.addItemDecoration(new FilePathNavItemDecoration(this,FilePathNavItemDecoration.HORIZONTAL_LIST));
        filepathNav.setAdapter(filePathNavRvAdapter);
        filePathNavRvAdapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View view, int position, String path) {
                if (path.equals("/")) {
                    files.clear();
                    files.addAll(startPath);
                    adapter.notifyDataSetChanged();
                    filePathNavRvAdapter.moveToIndex(position);
                    return;
                }

                File file = navMap.get(path);
            }

            @Override
            public void onItemLongClick(View view, int position, String path) {

            }
        });
    }

    private ArrayList<File> getDataSource() {
        //为了适配10/11  直接使用官方推荐的getExternalFilesDir
        files = new ArrayList<>();
        files.add(getCacheDir());
        files.add(getFilesDir());
        files.add(getExternalCacheDir());
        files.add(getExternalFilesDir(null));
        startPath.addAll(files);
        navMap.put("/", null);
        return files;
    }

    @Override
    protected int setLayoutXmlid() {
        return R.layout.activity_file_manager;
    }

    public void onBack(View view) {
        finish();
    }

    @Override
    public void onItemClick(View view, int position, File file) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            ArrayList<File> newFiles = new ArrayList<>(listFiles.length);
            for (File listFile : listFiles) {
                newFiles.add(listFile);
            }
            filePathNavRvAdapter.addPath(file.getName());
            navMap.put(file.getName(),file);
            files.clear();
            files.addAll(newFiles);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemLongClick(View view, int position, File file) {
        Log.d(TAG, "onItemLongClick: ");
        ArrayList<String> strings = new ArrayList<>();
        strings.add("新建文件");
        strings.add("新建文件夹");
        strings.add("重命名");
        strings.add("移动到");
        strings.add("复制到");
        new ListDialog(FileManagerActivity.this)
                .setItem(strings)
                .show();
    }
}
