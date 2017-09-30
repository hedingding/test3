package com.zlkj.zuixinstudio.fragment;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zlkj.zuixinstudio.App;
import com.zlkj.zuixinstudio.HttpConfig;
import com.zlkj.zuixinstudio.R;
import com.zlkj.zuixinstudio.banner.AdviseCycleView;
import com.zlkj.zuixinstudio.bean.HomeRequset;
import com.zlkj.zuixinstudio.gonggongadpter.GenListviewAdapter;
import com.zlkj.zuixinstudio.utils.Tools;
import com.zlkj.zuixinstudio.view.MyGridView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class ShouyeFragment extends Fragment implements View.OnClickListener{
    View view;



    private AdviseCycleView home_cycle_view1;
    List<String> homeImageList1 = new ArrayList<>();

    MyGridView lv_home1;
    private GenListviewAdapter homeListAdapter;
    HomeRequset baseRequset;
    TextView tv_web;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_shouye, container, false);


        home_cycle_view1 = (AdviseCycleView) view.findViewById(R.id.home_cycle_view1);

        lv_home1 = (MyGridView) view.findViewById(R.id.lv_home1);
        homeListAdapter = new GenListviewAdapter(R.layout.escmm_item);
        tv_web= (TextView) view.findViewById(R.id.tv_web);
        tv_web.setOnClickListener(this);
        lv_home1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tools.startWebActivity(getActivity(), HttpConfig.BASEURL + "goods/goodsOne" + "?appKey="  + "&goodsId=" + baseRequset.getDatas().getIndex().getGoodsList().get(position).getGoodsId());
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        homeImageList1.clear();
        homeImage1();
    }

    private void homeImage() {
        Request.Builder requestBuilder = new Request.Builder().url(HttpConfig.BASEURL+HttpConfig.INDEX);
        requestBuilder.method("GET", null);
        Request request = requestBuilder.build();
        Call mcall = App.mOkHttpClient.newCall(request);
        mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.cacheResponse().toString();
                if (null != response.cacheResponse()) {
                    String str1 = response.cacheResponse().toString();
                } else {
//                   String str= response.body().string();
//                    final HomeRequset baseRequset = JSON.parseObject(str, HomeRequset.class);
//                    if(baseRequset.getStatus()==100) {
//                        int advlistSize = baseRequset.getDatas().getIndex().getAdvList().size();
//                        for (int i = 0; i < advlistSize; i++) {
//                            homeImageList.add(baseRequset.getDatas().getIndex().getAdvList().get(i).getAdvImg());
//                        }
//
//                        home_cycle_view.setData(homeImageList, new AdviseCycleView.OnAdviseClickListener() {
//                            @Override
//                            public void onClick(int position, View view) {
//                                Log.e("home", "" + position);
//
//                            }
//                        });
//                    }else{
//                        getActivity().runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(getActivity(),baseRequset.getMsgs() , Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                    Log.i("wangshu", "network---" + str);
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "请求成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
       }


/**
 * post异步请求
 */
        private void homeImage1() {
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
        }

    @Override
    public void onClick(View v) {
if(v.getId()==R.id.tv_web){
    Tools.startWebActivity(getActivity(), "http://www.yuexi.gov.cn/html/xxgk/");
    return;
}
    }


}