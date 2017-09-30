package com.zlkj.zuixinstudio.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zlkj.zuixinstudio.R;

public class ZhuiAiFragment extends Fragment{
 View view;

 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     view = inflater.inflate(R.layout.activity_zhuiai, container, false);
     Toast.makeText(getActivity(),"11",Toast.LENGTH_SHORT);
     return view;
 }



}
