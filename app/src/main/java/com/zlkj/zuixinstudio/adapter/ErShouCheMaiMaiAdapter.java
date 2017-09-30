package com.zlkj.zuixinstudio.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zlkj.zuixinstudio.R;
import com.zlkj.zuixinstudio.bean.HomeRequset;
import com.zlkj.zuixinstudio.gonggongadpter.GenLinearItem;


public class ErShouCheMaiMaiAdapter extends GenLinearItem {
    private HomeRequset.DatasBean.IndexBean.GoodsListBean bean;
    View s2, h2;
    TextView title,qian1,qian2;
    ImageView tupian;


    public ErShouCheMaiMaiAdapter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onInit() {
        tupian = (ImageView)findViewById(R.id.tupian);
        title = (TextView)findViewById(R.id.title);
        qian1 = (TextView)findViewById(R.id.qian1);
        qian2 = (TextView)findViewById(R.id.qian2);
        s2 = (View)findViewById(R.id.s2);
        h2 = (View)findViewById(R.id.h2);
    }

    protected void onSetData(Object o) {
        bean = (HomeRequset.DatasBean.IndexBean.GoodsListBean) o;
        Glide.with(getContext()).load(bean.getGoodsMainPic()).into(tupian);
        title.setText(bean.getGoodsTitle());
        qian1.setText("￥"+bean.getGoodsMoney());
        qian2.setText("￥"+bean.getGoodsCanMoney());
        qian2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

    }


}


