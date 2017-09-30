package com.zlkj.zuixinstudio.gonggongadpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public final class GenListviewAdapter extends SetBaseAdapter {
    private int layoutid;

    private IViewEventListener callback;

    public GenListviewAdapter(int layoutID) {
        layoutid = layoutID;
    }

    @Override
    public final View getView(int position, View convertView, ViewGroup parent) {
        iGenListItem item;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(
                    layoutid, parent, false);
        }
        item = (iGenListItem) convertView;
        item.setData(this.datas.get(position), position);
        if (callback != null) {
            item.setViewCallback(callback);
        }

        return convertView;
    }

    public final void setLayoutID(int layoutID) {
        layoutid = layoutID;
    }

    public final void setViewEventListener(IViewEventListener listener) {
        callback = listener;
    }

}
