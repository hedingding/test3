package com.zlkj.zuixinstudio.gonggongadpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class SetBaseAdapter<E extends Object> extends BaseAdapter {
    protected Context ctx;
    protected List<E> datas;
    protected LayoutInflater mInflater;

    public SetBaseAdapter(Context ctx) {
        this.ctx = ctx;
        this.datas = new ArrayList<E>();
        mInflater = LayoutInflater.from(ctx);
    }

    public SetBaseAdapter() {
        datas = new ArrayList<E>();
    }

    public List<E> getmListObject() {
        return datas;
    }

    public int getCount() {
        return datas.size();
    }

    public Object getItem(int position) {
        return datas.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    public abstract View getView(int position, View convertView,
                                 ViewGroup parent);

    public void replaceAll(List<E> list) {
        datas.clear();
        if (list != null) {
            datas.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void addItem(E e) {
        datas.add(e);
        notifyDataSetChanged();
    }

    public void addAllItem(List<E> list) {
        datas.addAll(list);
        notifyDataSetChanged();
    }

    public void removeItem(E e) {
        datas.remove(e);
        notifyDataSetChanged();
    }

    public void removeAllItem(List<E> list) {
        datas.removeAll(list);
        notifyDataSetChanged();
    }

    public void clean() {
        datas.clear();
        notifyDataSetChanged();
    }

}
