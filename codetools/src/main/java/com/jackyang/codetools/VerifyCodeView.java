/*
  * JackYang   2017-06-29 10:26
  * Copyright (c)2017 7see Co.Ltd. All right reserved.
  * Email  pgrammer.ybj@outlook.com
  */
package com.jackyang.codetools;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 自定义验证码、密码输入框@ybj
 *
 * @author jackyang
 * @version 1.0.0
 * @since 2017-06-29 10:26
 */
public class VerifyCodeView extends View {
    StringBuilder verifyCodeBuilder;
    //一个字符或横线占用的宽度
    private int characterWidth;
    //一个数字后中间的间隔
    private int centerSpacing;
    //两字符间隔
    private int characterSpacing;
    private int textSize;
    Paint textPaint;
    float textBaseY;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public VerifyCodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //能获取焦点才能弹出软键盘

        verifyCodeBuilder = new StringBuilder(6);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        centerSpacing = getResources().getDimensionPixelSize(R.dimen.verficCodePaddingWidth);
        characterSpacing = getResources().getDimensionPixelSize(R.dimen.verficCodePaddingWidth);
        textSize = getResources().getDimensionPixelSize(R.dimen.p11);
        showKeyInput();

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        showKeyInput();

        return super.onTouchEvent(event);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void showKeyInput() {
        //在View上点击时弹出软键盘
        setFocusableInTouchMode(true);
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        //反射调隐藏的focusIn方法，如果不调，在VerifyCodeView之前有EditText等可输入控件时，不会弹出输入法
        //可能有其他google提供的方法，但我没找到
        Class<? extends InputMethodManager> immClass = imm.getClass();
        try {
            Method focusIn = immClass.getDeclaredMethod("focusIn", View.class);
            focusIn.invoke(imm, this);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        //  imm.viewClicked(this);
        imm.showSoftInput(this, 0);
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        //定义软键盘样式为数字键盘
        outAttrs.inputType = InputType.TYPE_CLASS_NUMBER;
        return super.onCreateInputConnection(outAttrs);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //接收按键事件，67是删除键(backspace),7-16就是0-9
        if (keyCode == 67 && verifyCodeBuilder.length() > 0) {
            verifyCodeBuilder.deleteCharAt(verifyCodeBuilder.length() - 1);
            //重新绘图
            invalidate();
        } else if (keyCode >= 7 && keyCode <= 16 && verifyCodeBuilder.length() < 6) {
            verifyCodeBuilder.append(keyCode - 7);
            invalidate();
        }
        //到了6位自动隐藏软键盘
        if (verifyCodeBuilder.length() >= 6) {
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getWindowToken(), 0);
            inputFinishListener.inputFinish(getVerifyCodeStr());

        }
        return super.onKeyDown(keyCode, event);
    }

    InputFinishListener inputFinishListener;

    public void setInputFinishListener(InputFinishListener listener) {
        inputFinishListener = listener;
    }

    public interface InputFinishListener {
        void inputFinish(String strCode);
    }

    /**
     * 获取输入的校验码
     *
     * @return
     */
    public String getVerifyCodeStr() {
        return verifyCodeBuilder.toString();
    }

    /**
     * 设置显示的校验码
     *
     * @param verifyCode
     */
    public void setVerifyCode(String verifyCode) {
        if (verifyCodeBuilder.length() > 0) {
            verifyCodeBuilder.delete(0, verifyCodeBuilder.length());
        }
        verifyCodeBuilder.append(verifyCode);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //计算一个字符的宽度
        if (characterWidth == 0) {
            int w = getWidth() - getPaddingLeft() - getPaddingRight();
            // int w = getWidth() - 140 - 140;
            characterWidth = (w - centerSpacing - 8 * characterSpacing) / 6;
        }


        textPaint.setTextSize(textSize);
        textPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.bottom - fontMetrics.top;
        if (textBaseY == 0)
            textBaseY = getHeight() - (getHeight() - fontHeight) / 2 - fontMetrics.bottom;
        //写已输入的验证码
        if (verifyCodeBuilder.length() > 0) {
            textPaint.setColor(getResources().getColor(R.color.defaultMainText));
            String verifyCodeStr = getVerifyCodeStr();
            char[] chars = verifyCodeStr.toCharArray();
            int x, y = (int) textBaseY;
            for (int i = 0; i < chars.length; i++) {
                //计算x,y
                if (i <= 2) {
                    x = (characterWidth + characterSpacing) * i + 90;
                } else {
                    x = (characterWidth + characterSpacing) * 2 + characterWidth + centerSpacing +
                            (characterWidth + characterSpacing) * (i - 3) + 90;
                }
                canvas.drawText(chars, i, 1, x, y, textPaint);
            }
        }
        //画白线
        if (verifyCodeBuilder.length() < 6) {
            for (int i = verifyCodeBuilder.length(); i < 6; i++) {
                textPaint.setColor(getResources().getColor(R.color.defaultMainText));
                textPaint.setStrokeWidth(5);
                int x, y = (int) textBaseY;
                if (i <= 2) {
                    x = (characterWidth + characterSpacing) * i + 90;
                } else {
                    x = (characterWidth + characterSpacing) * 2 + characterWidth + centerSpacing +
                            (characterWidth + characterSpacing) * (i - 3) + 90;
                }
                canvas.drawLine(x, y, x + characterWidth, y, textPaint);
            }
        }
    }

}
