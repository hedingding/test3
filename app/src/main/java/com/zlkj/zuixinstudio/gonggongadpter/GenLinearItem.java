package com.zlkj.zuixinstudio.gonggongadpter;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public abstract class GenLinearItem extends LinearLayout implements
		iGenListItem {
	private boolean isInit;

	protected int index;

	public GenLinearItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		isInit = false;
	}

	public final void setData(Object o, int index) {
		if (!isInit) {
			isInit = false;
			onInit();
		}
		this.index = index;
		this.onSetData(o);
	}

	@Override
	public int getLayoutType() {
		return 0;
	}

	@Override
	public void setViewCallback(IViewEventListener listener) {

	}

	protected abstract void onInit();

	protected void onSetData(Object o) {
	}
}
