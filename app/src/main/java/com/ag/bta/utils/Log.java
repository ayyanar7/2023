package com.ag.bta.utils;

import com.ag.bta.utils.constant.Constants;

public class Log {
    public static void d(final Object... contents){
       LogUtils.d(contents);

    }

    public static void d(final String contents){
        // LogUtils.d(contents);
        if(Constants.BL_LOG_ENABLE)
        android.util.Log.d(Constants.STR_APP_ID, contents);
    }
}
