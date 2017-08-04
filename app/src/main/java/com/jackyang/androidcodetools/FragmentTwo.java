/*
  * JackYang   2017-08-04 11:38
  * Copyright (c)2017 7see Co.Ltd. All right reserved. 
  *
  */
package com.jackyang.androidcodetools;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * class description here@ybj
 *
 * @author jackyang
 * @version 1.0.0
 * @since 2017-08-04 11:38
 */
public class FragmentTwo extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView(container);
    }

    private View initView(ViewGroup container) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_two, container, false);
    }
}