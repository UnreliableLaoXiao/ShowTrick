package cn.hallowebsite.lib.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cn.hallowebsite.lib.utils.StatusBarUtil;

public abstract class AbsBaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(setLayoutXmlid(), container,false);
        StatusBarUtil.adaperHeight(inflate,getContext());
        return inflate;
    }

    protected abstract int setLayoutXmlid();

}
