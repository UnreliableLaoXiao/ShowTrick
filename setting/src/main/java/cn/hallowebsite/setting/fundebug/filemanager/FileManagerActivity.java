package cn.hallowebsite.setting.fundebug.filemanager;

import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.util.ArrayList;

import cn.hallowebsite.lib.adapter.AbsActivity;
import cn.hallowebsite.lib.router.ActivityRouter;
import cn.hallowebsite.lib.utils.FileUtil;
import cn.hallowebsite.setting.R;

@ActivityRouter(path = "Setiting/About/Fun/FileManager")
public class FileManagerActivity extends AbsActivity {

    private Bundle extras;
    private boolean isExtern;
    private String file_path;
    private ArrayList<File> files;

    @Override
    protected void initOnCreate() {
        /*
        extras = getIntent().getExtras();
        file_path = extras.getString("file_path");
        files = FileUtil.getList(file_path);
         */
    }

    @Override
    protected int setLayoutXmlid() {
        return R.layout.activity_file_manager;
    }

    public void onBack(View view) {
        finish();
    }
}
