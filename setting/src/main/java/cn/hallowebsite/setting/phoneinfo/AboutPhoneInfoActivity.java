package cn.hallowebsite.setting.phoneinfo;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import cn.hallowebsite.lib.adapter.AbsActivity;
import cn.hallowebsite.lib.router.ActivityRouter;
import cn.hallowebsite.lib.utils.WindowUtil;
import cn.hallowebsite.setting.R;
import cn.hallowebsite.setting.view.PhoneModelView;

@ActivityRouter(path = "Setiting/About/PhoneInfo")
public class AboutPhoneInfoActivity extends AbsActivity implements PhoneModelView.OnAreaSelectListener {

    private TextView screenSize;
    private TextView appArea;
    private TextView statusHeight;
    private TextView contextArea;
    private TextView density;
    private TextView currentAreaName;
    private PhoneModelView phoneModel;

    @Override
    protected void initOnCreate() {
        screenSize = findViewById(R.id.screen_size);
        appArea = findViewById(R.id.app_area);
        statusHeight = findViewById(R.id.status_height);
        contextArea = findViewById(R.id.context_area);
        density = findViewById(R.id.density);
        currentAreaName = findViewById(R.id.current_area_name);
        phoneModel = findViewById(R.id.phone_model);
        phoneModel.setDefaultColor(getResources().getColor(R.color.style_color));
        initData();
    }

    @SuppressLint("SetTextI18n")
    private void initData() {
        //获取屏幕分辨率
        int[] size = new int[2];
        WindowUtil.getWindowMetrics(this, size);
        screenSize.setText("屏幕分辨率：" + size[0] + "*" + size[1] + "");
        //获取应用区宽高
        WindowUtil.getAppWindowMetrics(this, size);
        appArea.setText("应用区宽高：" + size[0] + "*" + size[1] + "");
        //获取状态栏高度
        statusHeight.setText("状态栏高度：" + WindowUtil.getAppStatusWindowMetrics(this));
        //获取View布局区域宽高
        WindowUtil.getAppViewWindowMetrics(this, size);
        contextArea.setText("View布局区域宽高：" + size[0] + "*" + size[1] + "");
        //获取像素密度
        density.setText("像素密度：" + WindowUtil.getDensity());
        phoneModel.setListener(this);
    }

    public void onBack(View view) {
        finish();
    }

    @Override
    protected int setLayoutXmlid() {
        return R.layout.activity_phone_info;
    }

    @Override
    public void onAreaSelectedListener(int index) {
        switch (index) {
            case 1:
                currentAreaName.setText("当前选中区域：状态栏");
                break;
            case 2:
                currentAreaName.setText("当前选中区域：工具栏");
                break;
            case 3:
                currentAreaName.setText("当前选中区域：内容区域");
                break;
            default:
                currentAreaName.setText("当前选中区域：无");
                break;
        }
    }
}
