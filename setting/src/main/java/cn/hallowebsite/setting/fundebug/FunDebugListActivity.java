package cn.hallowebsite.setting.fundebug;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cn.hallowebsite.lib.adapter.AbsActivity;
import cn.hallowebsite.lib.router.ActivityRouter;
import cn.hallowebsite.setting.R;

@ActivityRouter(path = "Setiting/About/Debug")
public class FunDebugListActivity extends AbsActivity {

    private RecyclerView settingRvFunDebug;
    private FunListRvAdapter adapter;

    @Override
    protected void initOnCreate() {
        settingRvFunDebug = findViewById(R.id.setting_rv_fun_debug);
        settingRvFunDebug.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        adapter = new FunListRvAdapter(this);
        settingRvFunDebug.setAdapter(adapter);
    }

    @Override
    protected int setLayoutXmlid() {
        return R.layout.activity_fun_debug_list;
    }

    public void onBack(View view) {
        finish();
    }
}
