package com.zlkj.zuixinstudio.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.zlkj.shangxiaowang.MyApplication;
import com.zlkj.shangxiaowang.R;
import com.zlkj.shangxiaowang.dialog.TanKuangDialog;
import com.zlkj.shangxiaowang.util.BaseAppManager;
import com.zlkj.shangxiaowang.util.Hint;
import com.zlkj.shangxiaowang.util.Judge;
import com.zlkj.shangxiaowang.util.Network;
import com.zlkj.shangxiaowang.util.Store;
import com.zlkj.shangxiaowang.util.Tools;
import com.zlkj.shangxiaowang.volley.VolleyResultCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public class RegistActivity extends Activity implements OnClickListener {
    EditText et_phone, et_code, et_mima1, et_tuiguangma;
    Button btn_code;
    Button btn_zhuce;
    String phone, code, mima11, tuiguangma;
    Store store;
    ViewGroup rl_back;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_regist);
        store = Store.init(this, "app", Context.MODE_APPEND);
        BaseAppManager.getInstance().addActivity(this);
        rl_back = (ViewGroup) findViewById(R.id.rl_back);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_phone.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        et_code = (EditText) findViewById(R.id.et_code);
        et_code.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        et_mima1 = (EditText) findViewById(R.id.et_mima1);
        et_tuiguangma = (EditText) findViewById(R.id.et_tuiguangma);
        btn_code = (Button) findViewById(R.id.btn_code);
        btn_code.setOnClickListener(this);
        btn_zhuce = (Button) findViewById(R.id.btn_zhuce);
        btn_zhuce.setOnClickListener(this);
        rl_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        phone = et_phone.getText().toString().trim();
        if (v.getId() == R.id.rl_back) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        } else if (v.getId() == R.id.btn_code) {
            if (Network.isOffline(RegistActivity.this)) {
                Hint.Short(RegistActivity.this, "请检查网络！");
                return;
            }
            if (phone.length() == 0) {
                Hint.Short(this, "手机号码不能为空");
                return;
            }
            if (!Judge.isMobile(phone)) {
                Hint.Short(this, "手机号码输入有误");
                return;
            }

            Map<String, String> params = new HashMap<String, String>();
//            params.put("mobile", phone);
//            params.put("type", "register");
            Tools.requestServer(RegistActivity.this, MyApplication.APP_URL + "mobile/login/send_code"+"?mobile="+phone+"&type=register", Request.Method.POST, params, MyApplication.headers(null), new VolleyResultCallback() {
                @Override
                public void onSuccess(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String result = jsonObject.getString("result");
                        if (result.equals("true")) {
                            btn_code.setClickable(false);
                            btn_code.setPressed(true);
                            timer = new CountDownTimer(60000, 1000) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    btn_code.setBackgroundResource(R.drawable.button_submit_hui8);
                                    btn_code.setText((millisUntilFinished / 1000) + "秒");
                                }

                                @Override
                                public void onFinish() {
                                    btn_code.setClickable(true);
                                    btn_code.setPressed(false);
                                    btn_code.setText("立即获取");
                                    btn_code.setBackgroundResource(R.drawable.button_code);
                                }
                            }.start();
                        } else {
                            Hint.Short(RegistActivity.this, jsonObject.getString("data"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(VolleyError error) {
                    Hint.Short(RegistActivity.this, "请检查网络");
                }
            });


            return;
        } else if (v.getId() == R.id.btn_zhuce) {
            if (Network.isOffline(RegistActivity.this)) {
                Hint.Short(RegistActivity.this, "请检查网络！");
                return;
            }
            onRegistButtonClick(v);
            return;
        }
    }


    //注册
    private void onRegistButtonClick(View v) {
        phone = et_phone.getText().toString().trim();
        code = et_code.getText().toString().trim();
        mima11 = et_mima1.getText().toString().trim();
        tuiguangma = et_tuiguangma.getText().toString().trim();
        if (phone.length() == 0) {
            Hint.Short(this, "手机号码不能为空");
            return;
        }
        if (!Judge.isMobile(phone)) {
            Hint.Short(this, "手机号码输入有误");
            return;
        }
        if (code.length() == 0) {
            Hint.Short(this, "验证码不能为空！");
            return;
        }

        if (mima11.length() == 0) {
            Hint.Short(this, "登录密码不能为空");
            return;
        }

        if (mima11.length() < 6 || mima11.length() > 16) {
            Hint.Short(this, "请输入6-16位登录密码");
            return;
        }

        MyApplication.disableView(v);
        Map<String, String> params = new HashMap<String, String>();
//        params.put("mobile", phone);
//        params.put("code", code);
//        params.put("pwd", mima11);
//        params.put("type", "register");
        Tools.requestServer(RegistActivity.this, MyApplication.APP_URL + "mobile/login/sign"+"?mobile="+phone+"&code="+code+"&pwd="+mima11+"&type=register", Request.Method.POST, params, MyApplication.headers(null), new VolleyResultCallback() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean("result")) {
                        JSONObject user = jsonObject.getJSONObject("data");
                        String user_id = user.getString("user_id");
                        String mobile = user.getString("mobile");
                        String secret = user.getString("secret");
                        Hint.Short(RegistActivity.this, "注册成功");
                        TanKuangDialog lDialog = new TanKuangDialog(RegistActivity.this, mobile) {
                            // 提交
                            @Override
                            public void tijiao() {
                                Intent intent = new Intent(RegistActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        };
                        Window window = lDialog.getWindow();
                        // // 设置显示动画
                        // window.setWindowAnimations(R.style.add_new_style);
                        window.setBackgroundDrawableResource(R.color.android_transparent);
                        WindowManager.LayoutParams wl = window.getAttributes();
                        // // 设置显示位置
                        lDialog.onWindowAttributesChanged(wl);
                        // 设置点击外围解散
                        lDialog.setCanceledOnTouchOutside(false);
                        lDialog.setCancelable(true);
                        lDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        lDialog.show();
                    }
                    // 登录失败
                    else {
                        Hint.Short(RegistActivity.this, jsonObject.getString("data"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(VolleyError error) {
                Hint.Short(RegistActivity.this, "请检查网络");
            }
        });

    }


    /**
     * 登录成功，到主界面
     */
    private void startIndex() {
        finish();
        Intent intent = new Intent(RegistActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // 这里重写返回键
            Intent intent = new Intent(RegistActivity.this, LoginActivity.class);
            intent.putExtra("shouye", "shouye0");
            startActivity(intent);
            return true;
        }
        return false;
    }
}
