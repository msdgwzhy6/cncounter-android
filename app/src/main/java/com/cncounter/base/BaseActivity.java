package com.cncounter.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.cncounter.helper.AppLevelHolder;

/**
 * 基础 Activity. 建议统一继承此类
 * Created by renfufei on 2015/11/28.
 */
public abstract class BaseActivity extends AppCompatActivity {

    //
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 将 this 对象置为当前活动的Activity
        AppLevelHolder.setCurrentActiveActivity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
