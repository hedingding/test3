package com.zlkj.zuixinstudio.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class Hint {
	
	private static Toast toast = null;

	/**
	 * 普通文本消息提示
	 * 
	 * @param context
	 * @param text
	 */
	public static void Short(Context context, CharSequence text) {
		// 创建一个Toast提示消息
		toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		// 设置Toast提示消息在屏幕上的位置
		toast.setGravity(Gravity.BOTTOM, 0, (int)(Tools.getWindowHeight(context)*0.1));
		// 显示消息
		toast.show();
	}


}
