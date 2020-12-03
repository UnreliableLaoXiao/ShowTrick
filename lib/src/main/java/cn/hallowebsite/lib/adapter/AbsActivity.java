package cn.hallowebsite.lib.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class AbsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutXmlid());
        //沉浸式有问题
//        ImmersiveAdapter.setActivityAdapter(this,isLightStatusBar());
        initOnCreate();
    }

    protected boolean isLightStatusBar() {
        return false;
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    protected abstract void initOnCreate();

    protected abstract int setLayoutXmlid();

    /**
     * 可重写此方法，设置需要传入的参数
     */
    public void startActivity(Context context) {
        Intent intent = new Intent(context,this.getClass());
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
