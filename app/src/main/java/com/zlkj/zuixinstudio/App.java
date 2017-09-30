package com.zlkj.zuixinstudio;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.zlkj.zuixinstudio.activity.LoginActivity;
import com.zlkj.zuixinstudio.dialog.DialogBuilder;
import com.zlkj.zuixinstudio.utils.Store;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;


public class App extends Application {

    public static Context context;
    private static App instance;
    public static OkHttpClient mOkHttpClient;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        instance = this;

        initOkHttpClient();
    }

    public static App getInstance() {
        return instance;
    }

    private void initOkHttpClient() {
        File sdcache = getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize));
        mOkHttpClient = builder.build();
    }

    public static void askLogin(final Context activity, final boolean close) {
        final Intent intent = new Intent();
        final DialogBuilder dialog = DialogBuilder.builder(activity).setCancelable(false);
        dialog.setTitle("登录提醒").setMessage("你尚未登录，请登录");
        dialog.setNegativeButton("取消", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (close && activity instanceof Activity) {
                    ((Activity) activity).finish();
                }
            }
        });
        dialog.setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(instance, LoginActivity.class);
                activity.startActivity(intent);
                if (close && activity instanceof Activity) {
                    ((Activity) activity).finish();
                }
            }
        });
        dialog.show();
    }

    public static void disableView(final View v) {
        disableView(v, 3000);
    }

    public static void disableView(final View v, int time) {
        if (false == v.isClickable()) {
            return;
        }
        v.setClickable(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                v.setClickable(true);
            }
        }, time);
    }

    static public void setUser(String userid, String mobile, String secret) {
        MyApplication.secret = secret;
        MyApplication.userid = userid;
        MyApplication.mobile = mobile;
        Store store = MyApplication.store("app");
        store.put("userid", userid);
        store.put("mobile", mobile);
        store.put("secret", secret);
        store.commit();
    }

    /**
     * 清除登录信息
     */
    static public void removeUser() {
        secret = userid = mobile = null;
        Store store = MyApplication.store("app");
        store.remove("userid");
        store.remove("mobile");
        store.remove("secret");
        store.remove("password1");
        store.commit();
        MyApplication.store("gesture").clear();
    }



}