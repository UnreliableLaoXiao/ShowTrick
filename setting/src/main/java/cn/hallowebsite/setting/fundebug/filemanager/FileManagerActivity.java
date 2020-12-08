package cn.hallowebsite.setting.fundebug.filemanager;

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
import cn.hallowebsite.lib.router.ActivityRouter;
import cn.hallowebsite.setting.R;

@ActivityRouter(path = "Setiting/About/Fun/FileManager")
public class FileManagerActivity extends AbsActivity implements AbsRecyclerViewAdapter.OnItemClickListener<File> {

    private RecyclerView fileRvList;
    private FileListRvAdapter adapter;
    private RecyclerView filepathNav;
    private FilePathNavRvAdapter filePathNavRvAdapter;
    private HashMap<String,File> navMap = new HashMap<>();
    private ArrayList<File> startPath = new ArrayList<>();

    @Override
    protected void initOnCreate() {
        //文件夹/文件列表
        fileRvList = findViewById(R.id.file_rv_list);
        fileRvList.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        adapter = new FileListRvAdapter(this);
        getDataSource();
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
        filePathNavRvAdapter = new FilePathNavRvAdapter(this);
        filePathNavRvAdapter.addPath("/");
        filepathNav.addItemDecoration(new FilePathNavItemDecoration(this,FilePathNavItemDecoration.HORIZONTAL_LIST));
        filepathNav.setAdapter(filePathNavRvAdapter);
        filePathNavRvAdapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View view, int position, String path) {
                if (path.equals("/")) {
                    adapter.updateDataSource(startPath);
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

    private void getDataSource() {
        //为了适配10/11  直接使用官方推荐的getExternalFilesDir
        ArrayList<File> files = adapter.getmDataSource();
        files.add(getCacheDir());
        files.add(getFilesDir());
        files.add(getExternalCacheDir());
        files.add(getExternalFilesDir(null));
        startPath.addAll(files);
        navMap.put("/", null);
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
            ArrayList<File> files = new ArrayList<>(listFiles.length);
            for (File listFile : listFiles) {
                files.add(listFile);
            }
            filePathNavRvAdapter.addPath(file.getName());
            navMap.put(file.getName(),file);
            adapter.updateDataSource(files);
        }
    }

    @Override
    public void onItemLongClick(View view, int position, File file) {
    }
}
