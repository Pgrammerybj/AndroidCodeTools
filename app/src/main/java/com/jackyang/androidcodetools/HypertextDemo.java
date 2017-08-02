/*
  * JackYang   2017-08-01 18:23
  * Copyright (c)2017 7see Co.Ltd. All right reserved. 
  *
  */
package com.jackyang.androidcodetools;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.jackyang.codetools.UIUtilsView;

/**
 * class description here@ybj
 *
 * @author jackyang
 * @version 1.0.0
 * @since 2017-08-01 18:23
 */
public class HypertextDemo extends BaseActivity {

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_hypertextdemo);
        initToolBar(true, R.string.hypertext_android);

        initialization();
    }

    ///////////////////////////////////////////////////////////////////////////
    // 分别演示TextView中的包含的 一个 和 两个超链接的情况。
    ///////////////////////////////////////////////////////////////////////////
    private void initialization() {

        TextView hyperTextOne = (TextView) findViewById(R.id.tv_user_protocol_1);
        //一个文本链接
        UIUtilsView.setTextViewHyperlink(hyperTextOne,
                getString(R.string.information_002),
                getResources().getColor(R.color.c3), 9, 17,
                new UIUtilsView.OnTextViewhyperlinkOnClickListener() {
                    @Override
                    public void onClick(String text) {
                        Toast.makeText(HypertextDemo.this, getString(R.string.information_003), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        TextView hyperTextTwo = (TextView) findViewById(R.id.tv_user_protocol_2);
        //两个文本链接
        UIUtilsView.setTextViewHyperlink(hyperTextTwo,
                getString(R.string.information_001),
                getResources().getColor(R.color.defaultLinkText), 7, 15, 16, 28,
                new UIUtilsView.OnTextViewhyperlinkOnClickListener() {
                    @Override
                    public void onClick(String text) {
                        Toast.makeText(HypertextDemo.this, text, Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}