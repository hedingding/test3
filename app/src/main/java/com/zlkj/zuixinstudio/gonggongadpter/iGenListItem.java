package com.zlkj.zuixinstudio.gonggongadpter;


public interface iGenListItem {
    public void setData(Object o, int index);

    public void setViewCallback(IViewEventListener listener);

    public int getLayoutType();
}
