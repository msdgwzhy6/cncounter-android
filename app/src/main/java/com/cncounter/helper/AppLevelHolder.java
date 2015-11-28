package com.cncounter.helper;

import android.app.Activity;

/**
 * App 全局持有类. 工具类
 * Created by enfufei on 2015/11/28.
 */
public abstract class AppLevelHolder {

    /**
     * 当前活动的Activity
     */
    public static Activity currentActiveActivity = null;

    public static Activity getCurrentActiveActivity() {
        return currentActiveActivity;
    }

    public static void setCurrentActiveActivity(Activity currentActiveActivity) {
        AppLevelHolder.currentActiveActivity = currentActiveActivity;
    }
}
