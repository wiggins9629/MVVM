package com.wiggins.mvvm.base;

import android.app.Activity;
import android.os.Bundle;

import com.wiggins.mvvm.utils.AppManager;

/**
 * @Description Activity的基类
 * @Author 一花一世界
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //将Activity实例添加到AppManager的堆栈
        AppManager.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //将Activity实例从AppManager的堆栈中移除
        AppManager.getInstance().finishActivity(this);
    }
}
