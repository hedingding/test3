package com.zlkj.zuixinstudio.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zlkj.zuixinstudio.App;
import com.zlkj.zuixinstudio.HttpConfig;
import com.zlkj.zuixinstudio.R;
import com.zlkj.zuixinstudio.banner.AdviseCycleView;
import com.zlkj.zuixinstudio.bean.HomeRequset;
import com.zlkj.zuixinstudio.dialog.ZProgressHUD;
import com.zlkj.zuixinstudio.utils.BaseAppManager;
import com.zlkj.zuixinstudio.utils.Hint;
import com.zlkj.zuixinstudio.utils.Network;
import com.zlkj.zuixinstudio.utils.Tools;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.zlkj.zuixinstudio.R.id.home_cycle_view1;
import static com.zlkj.zuixinstudio.R.id.lv_home1;

/**
 * 登录界面
 * 
 * @company 紫兰科技
 * @author niuchao
 * @version 1.0
 * @since 2016年8月10日, 上午11:05:03
 */
public class LoginActivity extends Activity implements OnClickListener {

	EditText et_phone, et_password;
	Button btn_login;
	TextView text_forget;
	TextView text_zhuce;
	String phone,password;
	Store store;
	ZProgressHUD progressHUD;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		BaseAppManager.getInstance().addActivity(this);
		store = Store.init(this, "app", Context.MODE_APPEND);
		et_password = (EditText) findViewById(R.id.et_password);
		et_phone = (EditText) findViewById(R.id.et_phone);
		et_phone.setInputType(EditorInfo.TYPE_CLASS_PHONE);
		text_zhuce = (TextView) findViewById(R.id.text_zhuce);
		text_zhuce.setOnClickListener(this);
		text_forget = (TextView) findViewById(R.id.text_forget);
		btn_login = (Button) findViewById(R.id.btn_login);
		progressHUD = ZProgressHUD.getInstance(this);
		String userid = store.getString("userid");
		if (userid != null) {
			startIndex();
		} 
		btn_login.setOnClickListener(this);
		text_forget.setOnClickListener(this);

	}


	/**
	 * 登录成功，到主界面
	 */
	private void startIndex() {
		finish();
		Intent intent = new Intent(LoginActivity.this, MainActivity.class);
		startActivity(intent);
	}

	/** 登录按钮处理 **/
	private void onLoginButtonClick(View v) {
		password = et_password.getText().toString().trim();
		phone = et_phone.getText().toString().trim();
		if (phone.length() == 0) {
			Hint.Short(this, "手机号码不能为空");
			return;
		}
		if (!Judge.isMobile(phone)) {
			Hint.Short(this, "手机号码输入有误");
			return;
		}
		if (password.length() == 0) {
			Hint.Short(this, "登录密码不能为空");
			return;
		}

		App.disableView(v);


		progressHUD.setMessage("正在登录……");
		progressHUD.setSpinnerType(ZProgressHUD.SIMPLE_ROUND_SPINNER);
		progressHUD.show();

		RequestBody formBody = new FormBody.Builder()
//                    .add("size", "10")
				.build();
		Request request = new Request.Builder()
				.url(HttpConfig.BASEURL+HttpConfig.INDEX)
				.post(formBody)
				.build();
		Call call = App.mOkHttpClient.newCall(request);
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {

			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				final String str = response.body().string();
				Log.i("wangshu", str);
				getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {
						baseRequset = JSON.parseObject(str, HomeRequset.class);
						if (baseRequset.getStatus() == 100) {
							homeListAdapter.clean();
							lv_home1.setAdapter(homeListAdapter);
							homeListAdapter.addAllItem(baseRequset.getDatas().getIndex().getGoodsList());
							int advlistSize = baseRequset.getDatas().getIndex().getAdvList().size();
							for (int i = 0; i < advlistSize; i++) {
								homeImageList1.add(baseRequset.getDatas().getIndex().getAdvList().get(i).getAdvImg());
							}

							home_cycle_view1.setData(homeImageList1, new AdviseCycleView.OnAdviseClickListener() {
								@Override
								public void onClick(int position, View view) {
									Log.e("home", "" + position);

								}
							});
						}
						Toast.makeText(getActivity(), "请求成功", Toast.LENGTH_SHORT).show();
					}
				});
			}

		});
		Map<String, String> params=new HashMap<String, String>();
		Map<String, String> paramsHeaders = new HashMap<String, String>();
		Tools.requestServer(LoginActivity.this, MyApplication.APP_URL + "mobile/login/getlogin"+"?mobile="+phone+"&pwd="+password, Request.Method.GET, params, paramsHeaders, new VolleyResultCallback() {
			@Override
			public void onSuccess(String response) {
				try {
					JSONObject jsonObject = new JSONObject(response);
					progressHUD.dismiss();
					if (jsonObject.getBoolean("result")) {
						JSONObject user = jsonObject.getJSONObject("data");
						String userid = user.getString("user_id");
						String mobile = user.getString("mobile");
						String secret = user.getString("secret");
						MyApplication.setUser(userid, mobile, secret);
						Hint.Short(LoginActivity.this, "登录成功");
						startIndex();
					}
					// 登录失败
					else {
						Hint.Short(LoginActivity.this, jsonObject.getString("data"));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onError(VolleyError error) {
				Hint.Short(LoginActivity.this, "请检查网络");
			}
		});
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btn_login) {
			if(!Network.isOnline(this)){
				Hint.Short(LoginActivity.this, "请打开网络");
				return;
			}
			onLoginButtonClick(v);
			return;
		} else if (v.getId() == R.id.text_forget) {
			Intent intent = new Intent(this, ResetActivity.class);
			startActivity(intent);
			return;
		} else if (v.getId() == R.id.text_zhuce) {
			Intent intent = new Intent(this, RegistActivity.class);
			startActivity(intent);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			// 这里重写返回键
			if (MyApplication.userid == null) {
				Intent intent = new Intent(LoginActivity.this, MainActivity.class);
				intent.putExtra("shouye", "shouye0");
				startActivity(intent);
			}else{
				finish();
			}
			return true;
		}
		return false;
	}

}
