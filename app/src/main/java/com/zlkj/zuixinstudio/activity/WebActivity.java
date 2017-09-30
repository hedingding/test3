package com.zlkj.zuixinstudio.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.just.library.AgentWeb;
import com.just.library.ChromeClientCallbackManager;
import com.just.library.LogUtils;
import com.zlkj.zuixinstudio.R;
import com.zlkj.zuixinstudio.dialog.LoadingView;

@SuppressLint("SetJavaScriptEnabled")
public class WebActivity extends Activity implements View.OnClickListener {
    private FrameLayout webFrame;
    protected AgentWeb mAgentWeb;

    private TextView text_title;
    RelativeLayout rl_back11, rl_back;
    private String url;
    LoadingView loadView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webFrame = (FrameLayout) findViewById(R.id.web_frame);

        loadView = (LoadingView) findViewById(R.id.loadView);
        rl_back11 = (RelativeLayout) findViewById(R.id.rl_back11);
        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        rl_back.setOnClickListener(this);
        text_title = (TextView) findViewById(R.id.text_title);
        url = getIntent().getStringExtra("url");

        mAgentWeb = AgentWeb.with(this)//
                .setAgentWebParent(webFrame, new LinearLayout.LayoutParams(-1, -1))//
                .useDefaultIndicator()//
                .defaultProgressBarColor()
                .addJavascriptInterface("obj", this)
                .setReceivedTitleCallback(mCallback)
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .setSecutityType(AgentWeb.SecurityType.strict)
                .createAgentWeb()//
                .ready()
                .go(url);
        mAgentWeb.getJsInterfaceHolder().addJavaObject("obj", this);
        mAgentWeb.getLoader().loadUrl(url);
    }

    private ChromeClientCallbackManager.ReceivedTitleCallback mCallback = new ChromeClientCallbackManager.ReceivedTitleCallback() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            text_title.setText(title);
        }
    };
    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            loadView.setVisibility(View.VISIBLE);
            webFrame.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            loadView.setVisibility(View.INVISIBLE);
            webFrame.setVisibility(View.VISIBLE);
        }
    };
    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {

        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (mAgentWeb.handleKeyEvent(keyCode, event)) {

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtils.i("Info", "result:" + requestCode + " result:" + resultCode);
        mAgentWeb.uploadFileResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mAgentWeb.destroy();
        mAgentWeb.getWebLifeCycle().onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                if (!mAgentWeb.back()) {
                    finish();
                } else {
                    mAgentWeb.back();
                }
                break;
        }
    }

    private Handler deliver = new Handler(Looper.getMainLooper());

    @JavascriptInterface
    public void cancel() {
        deliver.post(new Runnable() {
            @Override
            public void run() {
                if (!mAgentWeb.back()) {
                    finish();
                } else {
                    mAgentWeb.back();
                }
            }
        });
    }

    @JavascriptInterface
    public void goback() {
        deliver.post(new Runnable() {
            @Override
            public void run() {
                if (!mAgentWeb.back()) {
                    finish();
                } else {
                    mAgentWeb.back();
                }
            }
        });
    }

}
