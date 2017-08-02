/*
  * JackYang   2017-06-29 10:47
  * Copyright (c)2017 7see Co.Ltd. All right reserved.
  *
  */
package com.jackyang.androidcodetools;

import android.os.Bundle;
import android.widget.Toast;

import com.jackyang.codetools.VerifyCodeView;

/**
 * 适用于密码、验证码等文本输入@ybj
 *
 * @author jackyang
 * @version 1.0.0
 * @since 2017-06-29 10:47
 */
public class VerityCodeDemo extends BaseActivity {

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_veritycode);
        initToolBar(true, R.string.verify_code_input);
        VerifyCodeView verifyCode = (VerifyCodeView) findViewById(R.id.verify_code);
        verifyCode.setInputFinishListener(new VerifyCodeView.InputFinishListener() {
            @Override
            public void inputFinish(String strCode) {
                Toast.makeText(VerityCodeDemo.this, getString(R.string.verify_code_success), Toast.LENGTH_SHORT).show();
            }
        });
    }
}