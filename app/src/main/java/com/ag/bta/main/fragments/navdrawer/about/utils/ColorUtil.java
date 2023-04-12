package com.ag.bta.main.fragments.navdrawer.about.utils;

import android.content.Context;
import android.content.res.Resources;
import androidx.annotation.NonNull;
//import android.support.v4.content.ContextCompat;


public final class ColorUtil {

    public static int get(@NonNull Context context, int res) {
        try {
        	return context.getResources().getColor(res);
            //return Context.getColor(context, res);
        } catch (Resources.NotFoundException e) {
            return res;
        }
    }
}
