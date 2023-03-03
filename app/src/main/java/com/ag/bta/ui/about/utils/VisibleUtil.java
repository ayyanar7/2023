package com.ag.bta.ui.about.utils;

 import androidx.annotation.Nullable;
 import android.view.View;

public final class VisibleUtil {

    public static void handle(View v, @Nullable String s) {
        v.setVisibility(s == null || s.isEmpty() ? View.GONE : View.VISIBLE);
    }
}
