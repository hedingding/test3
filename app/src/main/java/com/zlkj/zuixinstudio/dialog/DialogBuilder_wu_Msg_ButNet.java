package com.zlkj.zuixinstudio.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.zlkj.zuixinstudio.R;


public class DialogBuilder_wu_Msg_ButNet {
	private Context context;
	private Dialog dialog;
	private LinearLayout lLayout_bg;
	private TextView txt_title;
	private Button btn_pos;
	private Display display;
	private boolean showTitle = false;
	private boolean showPosBtn = false;

	public DialogBuilder_wu_Msg_ButNet(Context context) {
		this.context = context;
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		display = windowManager.getDefaultDisplay();
	}

	public static DialogBuilder_wu_Msg_ButNet builder(Context context) {
		return new DialogBuilder_wu_Msg_ButNet(context).builder();
	}

	public DialogBuilder_wu_Msg_ButNet builder() {
		// 获取Dialog布局
		View view = LayoutInflater.from(context).inflate(R.layout.dialog_alert4, null);

		// 获取自定义Dialog布局中的控件
		lLayout_bg = (LinearLayout) view.findViewById(R.id.dialog_bg);
		txt_title = (TextView) view.findViewById(R.id.dialog_title);
		txt_title.setVisibility(View.GONE);
		btn_pos = (Button) view.findViewById(R.id.dialog_pos);
		btn_pos.setVisibility(View.GONE);

		// 定义Dialog布局和参数
		dialog = new Dialog(context, R.style.AlertDialogStyle);
		dialog.setContentView(view);

		// 调整dialog背景大小
		lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display.getWidth() * 0.85),
				LayoutParams.WRAP_CONTENT));

		return this;
	}

	public DialogBuilder_wu_Msg_ButNet setTitle(String title) {
		showTitle = true;
		if ("".equals(title)) {
			txt_title.setText("标题");
		} else {
			txt_title.setText(title);
		}
		return this;
	}


	public DialogBuilder_wu_Msg_ButNet setCancelable(boolean cancel) {
		dialog.setCancelable(cancel);
		return this;
	}

	public DialogBuilder_wu_Msg_ButNet setPositiveButton(String text, final OnClickListener listener) {
		showPosBtn = true;
		if ("".equals(text)) {
			btn_pos.setText("确定");
		} else {
			btn_pos.setText(text);
		}
		btn_pos.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (null != listener) {
					listener.onClick(v);
				}
				dialog.dismiss();
			}
		});
		return this;
	}

	private void setLayout() {
		if (!showTitle) {
			txt_title.setText("提示");
			txt_title.setVisibility(View.VISIBLE);
		}

		if (showTitle) {
			txt_title.setVisibility(View.VISIBLE);
		}


		if (!showPosBtn) {
			btn_pos.setText("确定");
			btn_pos.setVisibility(View.VISIBLE);
			btn_pos.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});
		}


		if (showPosBtn) {
			btn_pos.setVisibility(View.VISIBLE);
		}

	}

	public void show() {
		setLayout();
		dialog.show();
	}

	public void dismiss() {
		dialog.dismiss();
	}
}
