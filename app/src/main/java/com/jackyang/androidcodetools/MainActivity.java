/*
  * JackYang   2017-07-03 10:37
  * Copyright (c)2017 7see Co.Ltd. All right reserved. 
  *
  */
package com.jackyang.androidcodetools;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * class description here@ybj
 *
 * @author jackyang
 * @version 1.0.0
 * @since 2017-07-03 10:37
 */
public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goVerifyCode(View view) {
        startActivity(new Intent(this,VerityCodeDemo.class));
    }

    public void goHypertext(View view) {
        startActivity(new Intent(this,HypertextDemo.class));
    }
}