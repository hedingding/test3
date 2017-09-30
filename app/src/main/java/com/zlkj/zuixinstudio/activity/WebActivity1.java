package com.zlkj.zuixinstudio.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Network;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.webkit.HttpAuthHandler;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.zlkj.zuixinstudio.App;
import com.zlkj.zuixinstudio.HttpConfig;
import com.zlkj.zuixinstudio.R;
import com.zlkj.zuixinstudio.dialog.DialogBuilder;
import com.zlkj.zuixinstudio.dialog.DialogBuilder_Wu_But_Qd;
import com.zlkj.zuixinstudio.dialog.LoadingView;
import com.zlkj.zuixinstudio.utils.BaseAppManager;
import com.zlkj.zuixinstudio.utils.Hint;
import com.zlkj.zuixinstudio.utils.Tools;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.zlkj.zuixinstudio.App.askLogin;

@SuppressLint({"JavascriptInterface", "DefaultLocale", "NewApi"})
public class WebActivity1 extends FragmentActivity implements OnClickListener {

    private TextView text_title;
    RelativeLayout rl_back11, rl_back;
    private WebView mWebView;
    private String url, title;
    private ViewGroup errorView;
    private Handler handler = new Handler();
    private Map<String, String> headers = new HashMap<String, String>();
    public static String closeActionName = "com.zlkj.ymeg.action.closewebativity";
//    static Store store;
    String userid;
    String touTitle;

    LoadingView loadView;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            timeoutAction();
        }
    };

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            unregisterReceiver(this);
            ((Activity) context).finish();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web1);
        BaseAppManager.getInstance().addActivity(this);
//        store = Store.init(this, "app", Context.MODE_APPEND);
//        userid = store.getString("userid");
        loadView = (LoadingView) findViewById(R.id.loadView);
        rl_back11 = (RelativeLayout) findViewById(R.id.rl_back11);
        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        rl_back.setOnClickListener(this);
        loadView = (LoadingView) findViewById(R.id.loadView);
        text_title = (TextView) findViewById(R.id.text_title);
        // build headers
//        headers = MyApplication.headers(null);
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        errorView = (ViewGroup) findViewById(R.id.wv_error);

        mWebView = (WebView) findViewById(R.id.wv_custom);
        mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
        WebSettings ws = mWebView.getSettings();

        ws.setAllowFileAccess(true);
        ws.setAppCachePath(appCachePath);
        ws.setDatabaseEnabled(true);
        ws.setDomStorageEnabled(true);
        ws.setJavaScriptEnabled(true);
        ws.setLoadWithOverviewMode(true);
        ws.setNeedInitialFocus(false);
        ws.setUseWideViewPort(true);
        ws.setSupportMultipleWindows(true);
        ws.setTextZoom(100); // 不可放缩字体大小
        // 点击后退按钮,让WebView后退一页(也可以覆写Activity的onKeyDown方法)
        mWebView.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View arg0) {
                return true;
            }
        });
        mWebView.addJavascriptInterface(new MyWebViewJavascript(), "obj");
        mWebView.setWebChromeClient(new MyChromeViewClient());
        mWebView.setWebViewClient(new MyWebViewClient());
        loadView.setVisibility(View.VISIBLE);
        mWebView.post(new Runnable() {
            @Override
            public void run() {
                mWebView.loadUrl(url, headers);
            }
        });
        if (url.contains(HttpConfig.BASEURL+"api.php/userCenter/qqService")) {
            rl_back.setVisibility(View.GONE);
            rl_back11.setVisibility(View.VISIBLE);
            rl_back11.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mWebView.post(new Runnable() {
                        @Override
                        public void run() {
                            closeTask();
                            mWebView.removeAllViews();
                            mWebView.destroy();
                        }
                    });
                }
            });
        }
    }

    /**
     * 注册广播
     */
    public void onResume() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(closeActionName);
        registerReceiver(broadcastReceiver, filter);
        super.onResume();
    }


    void loginDialog() {
        App.askLogin(WebActivity1.this, true);
    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(final WebView view, String url) {
            int hitType = view.getHitTestResult().getType();
            // 301||302
            if (0 == hitType) {
                // view.loadUrl(url, headers);
                    Intent intent = new Intent(WebActivity1.this, WebActivity1.class);
                    intent.putExtra("url", url);
                    startActivity(intent);
            }
            // 正常链接,一般是7
            else {
                    Intent intent = new Intent(WebActivity1.this, WebActivity1.class);
                    intent.putExtra("url", url);
                    startActivity(intent);
            }
            return true;
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            onNetworkError(view);
        }

        @Override
        public void onPageStarted(final WebView view, String url, Bitmap favicon) {
            loadView.setVisibility(View.VISIBLE);
            errorView.setVisibility(View.INVISIBLE);
            mWebView.setVisibility(View.INVISIBLE);
            handler.postDelayed(runnable, 3000);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            errorView.setVisibility(View.INVISIBLE);
            loadView.setVisibility(View.INVISIBLE);
            mWebView.setVisibility(View.VISIBLE);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed(); // 接受所有证书
        }

    }

    private void timeoutAction() {
        errorView.setVisibility(View.INVISIBLE);
        loadView.setVisibility(View.INVISIBLE);
        mWebView.setVisibility(View.VISIBLE);
        handler.removeCallbacks(runnable);
    }

    /**
     * 关闭整个WebView栈
     */
    public void closeTask() {
        Intent intent = new Intent();
        intent.setAction(closeActionName);
        sendBroadcast(intent);
        finish();
    }

    /**
     * 关闭一个堆栈
     *
     * @author Administrator
     */
    final class MyWebViewJavascript {
        // 首页个人资料点击提交跳到必备认证页面
        @JavascriptInterface
        public void userCenter() {
            closeTask();
            Hint.Short(WebActivity1.this,"提交成功");
        }
        // 首页贷款页面点击申请跳返回首页
        @JavascriptInterface
        public void index() {
            closeTask();
            Hint.Short(WebActivity1.this,"申请成功");
        }


        // 我的页面自助服务点击跳到qq
        @JavascriptInterface
        public void kefu() {
            if (Tools.isAvilible(WebActivity1.this, "com.tencent.mobileqq") || Tools.isAvilible(WebActivity1.this, "com.tencent.qqlite")) {// 传入指定应用包名
//                    QQ:String url = "mqqwpa://im/chat?chat_type=wpa&uin=4008615610";
                String url = "mqqwpa://im/chat?chat_type=crm&uin=938182955&version=1&src_type=web&web_src=http:://yjydhk.zilankeji.com";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            } else {
                Toast.makeText(WebActivity1.this, "您当前未安装QQ或未登录！", Toast.LENGTH_LONG).show();
            }
        }

        // 打电话
        @JavascriptInterface
        public void callphone(final String phoneNum) {
            final DialogBuilder dialog = DialogBuilder.builder(WebActivity1.this).setTitle("拨打电话").setMessage(phoneNum);

            dialog.setPositiveButton("确定", new OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNum));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    v.getContext().startActivity(intent);
                }
            });
            dialog.setNegativeButton("取消", new OnClickListener() {
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }

    }

    private Bitmap getDiskBitmap(String pathString) {
        Bitmap bitmap = null;
        try {
            File file = new File(pathString);
            if (file.exists()) {
                bitmap = BitmapFactory.decodeFile(pathString);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return bitmap;
    }

    class MyChromeViewClient extends WebChromeClient {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (Network.isOnline(WebActivity1.this)) {
                if (!title.equals("找不到网页")) {
                    errorView.setVisibility(View.GONE);
                    if (title == null || title.startsWith("http")) {
                        return;
                    }
                    touTitle=title;
                    text_title.setText(title);
                    if (touTitle.equals("芝麻信用授权")||touTitle.equals("芝麻信用")) {
                        rl_back.setVisibility(View.GONE);
                        rl_back11.setVisibility(View.VISIBLE);
                        rl_back11.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mWebView.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        closeTask();
                                        mWebView.removeAllViews();
                                        mWebView.destroy();
                                        mWebView.reload();
                                    }
                                });
                            }
                        });
                    }
                }
            }
        }

        @Override
        public void onProgressChanged(WebView view, final int progress) {
            if (progress == 100) {
                loadView.setVisibility(View.INVISIBLE);
            }
            super.onProgressChanged(view, progress);
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
            DialogBuilder_Wu_But_Qd.builder(WebActivity1.this).setCancelable(false).setTitle("易捷易贷提示").setMessage(message)
                    .setNegativeButton("确定", new OnClickListener() {
                        public void onClick(View v) {
                            result.confirm();
                        }
                    }).show();
            return true;
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
            DialogBuilder.builder(WebActivity1.this).setCancelable(false).setTitle("易捷易贷提示").setMessage(message)
                    .setPositiveButton("确定", new OnClickListener() {
                        public void onClick(View v) {
                            result.confirm();
                        }
                    }).setNegativeButton("取消", new OnClickListener() {
                public void onClick(View v) {
                    result.cancel();
                }
            }).show();
            return true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                finish();
                return;
            case R.id.wv_error:
                mWebView.reload();
                mWebView.setVisibility(View.VISIBLE);
                loadView.setVisibility(View.INVISIBLE);
                Hint.Short(this, "正在重新加载，请稍候");
                App.disableView(v, 2000);
                return;
        }
    }

    public void onNetworkError(WebView view) {
        view.setVisibility(View.INVISIBLE);
        errorView.setVisibility(View.VISIBLE);
        loadView.setVisibility(View.INVISIBLE);
        text_title.setText("网络错误");
    }

    public byte[] bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(CompressFormat.PNG, 100, baos);
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {
            baos.reset();
            bm.compress(CompressFormat.JPEG, options, baos);
            options -= 10;

        }
        return baos.toByteArray();
    }

    public String Bitmap2StrByBase64(Bitmap bit) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bit.compress(CompressFormat.JPEG, 40, bos);// 参数100表示不压缩
        byte[] bytes = bos.toByteArray();
        return android.util.Base64.encodeToString(bytes, android.util.Base64.DEFAULT);
    }

    // 系统返回
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // 这里重写返回键
            if (touTitle.equals("芝麻信用授权")||touTitle.equals("芝麻信用")) {
                mWebView.post(new Runnable() {
                    @Override
                    public void run() {
                        closeTask();
                        mWebView.removeAllViews();
                        mWebView.destroy();
                    }
                });
            } else {
                finish();
            }

            return true;
        }
        return false;
    }

}
