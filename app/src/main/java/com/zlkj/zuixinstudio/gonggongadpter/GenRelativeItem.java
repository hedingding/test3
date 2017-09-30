package com.zlkj.zuixinstudio.gonggongadpter;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public abstract class GenRelativeItem extends RelativeLayout implements
        iGenListItem {
    protected int index;
    private boolean isInit;

    public GenRelativeItem(Context context, AttributeSet attrs) {
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