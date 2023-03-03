package com.ag.bta.utils;


import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.ag.bta.utils.constant.PermissionConstants;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class UtilsBridge  {
    public UtilsBridge(){

    }
    ///////////////////////////////////////////////////////////////////////////
    // FileIOUtils
    ///////////////////////////////////////////////////////////////////////////
 public static   boolean writeFileFromBytes(final File file,
                                      final byte[] bytes) {
        return FileIOUtils.writeFileFromBytesByChannel(file, bytes, true);
    }

 public static   byte[] readFile2Bytes(final File file) {
        return FileIOUtils.readFile2BytesByChannel(file);
    }

 public static   boolean writeFileFromString(final String filePath, final String content, final boolean append) {
        return FileIOUtils.writeFileFromString(filePath, content, append);
    }

 public static   boolean writeFileFromIS(final String filePath, final InputStream is) {
        return FileIOUtils.writeFileFromIS(filePath, is);
    }

    ///////////////////////////////////////////////////////////////////////////
    // FileUtils
    ///////////////////////////////////////////////////////////////////////////
 public static   boolean isFileExists(final File file) {
        return FileUtils.isFileExists(file);
    }

 public static   File getFileByPath(final String filePath) {
        return FileUtils.getFileByPath(filePath);
    }

 public static   boolean deleteAllInDir(final File dir) {
        return FileUtils.deleteAllInDir(dir);
    }

 public static   boolean createOrExistsFile(final File file) {
        return FileUtils.createOrExistsFile(file);
    }

 public static   boolean createOrExistsDir(final File file) {
        return FileUtils.createOrExistsDir(file);
    }

 public static   boolean createFileByDeleteOldFile(final File file) {
        return FileUtils.createFileByDeleteOldFile(file);
    }

 public static   long getFsTotalSize(String path) {
        return FileUtils.getFsTotalSize(path);
    }

 public static   long getFsAvailableSize(String path) {
        return FileUtils.getFsAvailableSize(path);
    }

 public static   void notifySystemToScan(File file) {
        FileUtils.notifySystemToScan(file);
    }
    ///////////////////////////////////////////////////////////////////////////
    // JsonUtils
    ///////////////////////////////////////////////////////////////////////////
 public static   String formatJson(String json) {
        return JsonUtils.formatJson(json);
    }

    ///////////////////////////////////////////////////////////////////////////
    // GsonUtils
    ///////////////////////////////////////////////////////////////////////////
 public static   String toJson(final Object object) {
        return GsonUtils.toJson(object);
    }

 public static   <T> T fromJson(final String json, final Type type) {
        return GsonUtils.fromJson(json, type);
    }

 public static   Gson getGson4LogUtils() {
        return GsonUtils.getGson4LogUtils();
    }


    public static boolean isSpace(final String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    ///////////////////////////////////////////////////////////////////////////
    // ConvertUtils
    ///////////////////////////////////////////////////////////////////////////
 public static   String byte2FitMemorySize(final long byteSize) {
        return ConvertUtils.byte2FitMemorySize(byteSize);
    }
 public static   String bytes2HexString(final byte[] bytes) {
        return ConvertUtils.bytes2HexString(bytes);
    }

 public static   byte[] hexString2Bytes(String hexString) {
        return ConvertUtils.hexString2Bytes(hexString);
    }

 public static   byte[] string2Bytes(final String string) {
        return ConvertUtils.string2Bytes(string);
    }

 public static   String bytes2String(final byte[] bytes) {
        return ConvertUtils.bytes2String(bytes);
    }

 public static   byte[] jsonObject2Bytes(final JSONObject jsonObject) {
        return ConvertUtils.jsonObject2Bytes(jsonObject);
    }

 public static   JSONObject bytes2JSONObject(final byte[] bytes) {
        return ConvertUtils.bytes2JSONObject(bytes);
    }

 public static   byte[] jsonArray2Bytes(final JSONArray jsonArray) {
        return ConvertUtils.jsonArray2Bytes(jsonArray);
    }

 public static   JSONArray bytes2JSONArray(final byte[] bytes) {
        return ConvertUtils.bytes2JSONArray(bytes);
    }

 public static   byte[] parcelable2Bytes(final Parcelable parcelable) {
        return ConvertUtils.parcelable2Bytes(parcelable);
    }

 public static   <T> T bytes2Parcelable(final byte[] bytes,
                                  final Parcelable.Creator<T> creator) {
        return ConvertUtils.bytes2Parcelable(bytes, creator);
    }

 public static   byte[] serializable2Bytes(final Serializable serializable) {
        return ConvertUtils.serializable2Bytes(serializable);
    }

 public static   Object bytes2Object(final byte[] bytes) {
        return ConvertUtils.bytes2Object(bytes);
    }


 public static   byte[] inputStream2Bytes(final InputStream is) {
        return ConvertUtils.inputStream2Bytes(is);
    }

 public static   ByteArrayOutputStream input2OutputStream(final InputStream is) {
        return ConvertUtils.input2OutputStream(is);
    }

 public static   List<String> inputStream2Lines(final InputStream is, final String charsetName) {
        return ConvertUtils.inputStream2Lines(is, charsetName);
    }

    ///////////////////////////////////////////////////////////////////////////
    // ImageUtils
    ///////////////////////////////////////////////////////////////////////////
 public static   byte[] bitmap2Bytes(final Bitmap bitmap) {
        return ImageUtils.bitmap2Bytes(bitmap);
    }

 public static   byte[] bitmap2Bytes(final Bitmap bitmap, final Bitmap.CompressFormat format, int quality) {
        return ImageUtils.bitmap2Bytes(bitmap, format, quality);
    }

 public static   Bitmap bytes2Bitmap(final byte[] bytes) {
        return ImageUtils.bytes2Bitmap(bytes);
    }

 public static   byte[] drawable2Bytes(final Drawable drawable) {
        return ImageUtils.drawable2Bytes(drawable);
    }

 public static   byte[] drawable2Bytes(final Drawable drawable, final Bitmap.CompressFormat format, int quality) {
        return ImageUtils.drawable2Bytes(drawable, format, quality);
    }

 public static   Drawable bytes2Drawable(final byte[] bytes) {
        return ImageUtils.bytes2Drawable(bytes);
    }

 public static   Bitmap view2Bitmap(final View view) {
        return ImageUtils.view2Bitmap(view);
    }

 public static   Bitmap drawable2Bitmap(final Drawable drawable) {
        return ImageUtils.drawable2Bitmap(drawable);
    }

 public static   Drawable bitmap2Drawable(final Bitmap bitmap) {
        return ImageUtils.bitmap2Drawable(bitmap);
    }
    ///////////////////////////////////////////////////////////////////////////
    // PermissionUtils
    ///////////////////////////////////////////////////////////////////////////


    public static boolean isGranted(final String... permissions) {
        Pair<List<String>, List<String>> requestAndDeniedPermissions = getRequestAndDeniedPermissions(permissions);
        List<String> deniedPermissions = requestAndDeniedPermissions.second;
        if (!deniedPermissions.isEmpty()) {
            return false;
        }
        List<String> requestPermissions = requestAndDeniedPermissions.first;
        for (String permission : requestPermissions) {
            if (!isGranted(permission)) {
                return false;
            }
        }
        return true;
    }
    private static Pair<List<String>, List<String>> getRequestAndDeniedPermissions(final String... permissionsParam) {
        List<String> requestPermissions = new ArrayList<>();
        List<String> deniedPermissions = new ArrayList<>();
        List<String> appPermissions = getPermissions();
        for (String param : permissionsParam) {
            boolean isIncludeInManifest = false;
            String[] permissions = PermissionConstants.getPermissions(param);
            for (String permission : permissions) {
                if (appPermissions.contains(permission)) {
                    requestPermissions.add(permission);
                    isIncludeInManifest = true;
                }
            }
            if (!isIncludeInManifest) {
                deniedPermissions.add(param);
                Log.e("PermissionUtils", "U should add the permission of " + param + " in manifest.");
            }
        }
        return Pair.create(requestPermissions, deniedPermissions);
    }

    public static List<String> getPermissions() {
        return getPermissions(Utils.getApp().getPackageName());
    }
    public static List<String> getPermissions(final String packageName) {
        PackageManager pm = Utils.getApp().getPackageManager();
        try {
            String[] permissions = pm.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS).requestedPermissions;
            if (permissions == null) return Collections.emptyList();
            return Arrays.asList(permissions);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    private static boolean isGranted(final String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M
                || PackageManager.PERMISSION_GRANTED
                == ContextCompat.checkSelfPermission(Utils.getApp(), permission);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean isGrantedDrawOverlays() {
        return canDrawOverlays(Utils.getApp());
    }
    public static boolean canDrawOverlays(Context context) {
        throw new RuntimeException("Stub!");
    }
    ///////////////////////////////////////////////////////////////////////////
    // SDCardUtils
    ///////////////////////////////////////////////////////////////////////////
 public static   boolean isSDCardEnableByEnvironment() {
        return SDCardUtils.isSDCardEnableByEnvironment();
    }
    ///////////////////////////////////////////////////////////////////////////
    // UriUtils
    ///////////////////////////////////////////////////////////////////////////
 public static   Uri file2Uri(final File file) {
        return UriUtils.file2Uri(file);
    }

 public static   File uri2File(final Uri uri) {
        return UriUtils.uri2File(uri);
    }
    ///////////////////////////////////////////////////////////////////////////
    // ThrowableUtils
    ///////////////////////////////////////////////////////////////////////////
 public static   String getFullStackTrace(Throwable throwable) {
        return ThrowableUtils.getFullStackTrace(throwable);
    }
    ///////////////////////////////////////////////////////////////////////////
    // Common
    ///////////////////////////////////////////////////////////////////////////
    public static final class FileHead {

        private String                        mName;
        private LinkedHashMap<String, String> mFirst = new LinkedHashMap<>();
        private LinkedHashMap<String, String> mLast  = new LinkedHashMap<>();

        FileHead(String name) {
            mName = name;
        }

        void addFirst(String key, String value) {
            append2Host(mFirst, key, value);
        }

        void append(Map<String, String> extra) {
            append2Host(mLast, extra);
        }

        void append(String key, String value) {
            append2Host(mLast, key, value);
        }

        private void append2Host(Map<String, String> host, Map<String, String> extra) {
            if (extra == null || extra.isEmpty()) {
                return;
            }
            for (Map.Entry<String, String> entry : extra.entrySet()) {
                append2Host(host, entry.getKey(), entry.getValue());
            }
        }

        private void append2Host(Map<String, String> host, String key, String value) {
            if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value)) {
                return;
            }
            int delta = 19 - key.length(); // 19 is length of "Device Manufacturer"
            if (delta > 0) {
                key = key + "                   ".substring(0, delta);
            }
            host.put(key, value);
        }

        public String getAppended() {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : mLast.entrySet()) {
                sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
            return sb.toString();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            String border = "************* " + mName + " Head ****************\n";
            sb.append(border);
            for (Map.Entry<String, String> entry : mFirst.entrySet()) {
                sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }

            //sb.append("Rom Info           : ").append(RomUtils.getRomInfo()).append("\n");
            sb.append("Device Manufacturer: ").append(Build.MANUFACTURER).append("\n");
            sb.append("Device Model       : ").append(Build.MODEL).append("\n");
            sb.append("Android Version    : ").append(Build.VERSION.RELEASE).append("\n");
            sb.append("Android SDK        : ").append(Build.VERSION.SDK_INT).append("\n");
            // sb.append("App VersionName    : ").append(AppUtils.getAppVersionName()).append("\n");
           // sb.append("App VersionCode    : ").append(AppUtils.getAppVersionCode()).append("\n");

            sb.append(getAppended());
            return sb.append(border).append("\n").toString();
        }
    }
}

