package com.t3h.musicplayer.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment<BD extends ViewDataBinding> extends Fragment {

    protected BD binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,getLayoutId(),container,false);
        initViews();
        return binding.getRoot();
    }

    protected abstract void initViews();

    protected abstract int getLayoutId();

    public abstract @StringRes
    int getTitle();

}
