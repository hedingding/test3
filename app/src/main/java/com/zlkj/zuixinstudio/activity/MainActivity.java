package com.zlkj.zuixinstudio.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.zlkj.zuixinstudio.R;
import com.zlkj.zuixinstudio.fragment.MineFragment;
import com.zlkj.zuixinstudio.fragment.SXSCFragment;
import com.zlkj.zuixinstudio.fragment.ShouyeFragment;
import com.zlkj.zuixinstudio.fragment.ZhuiAiFragment;

public class MainActivity extends FragmentActivity {
    private Button[] mTabs;
    private ShouyeFragment shouyeFragment;
    private SXSCFragment sxscFragment;
    private ZhuiAiFragment zhuiAiFragment;
    private MineFragment mineFragment;
    private Fragment[] fragments;
    private int index;
    private int currentTabIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabs = new Button[4];
        mTabs[0] = (Button) findViewById(R.id.btn_shouye);
        mTabs[1] = (Button) findViewById(R.id.btn_shangxiaoshangcheng);
        mTabs[2] = (Button) findViewById(R.id.btn_zhuiai);
        mTabs[3] = (Button) findViewById(R.id.btn_mine);
        // set first tab as selected
        mTabs[0].setSelected(true);
        shouyeFragment = new ShouyeFragment();
        sxscFragment = new SXSCFragment();
        zhuiAiFragment = new ZhuiAiFragment();
        mineFragment = new MineFragment();
        fragments = new Fragment[] { shouyeFragment, sxscFragment, zhuiAiFragment,mineFragment };
        // add and show first fragment
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, shouyeFragment)
                .add(R.id.fragment_container, sxscFragment).hide(sxscFragment).show(shouyeFragment)
                .commit();
    }

    public void onTabClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_shouye:
                index = 0;
                break;
            case R.id.btn_shangxiaoshangcheng:
                index = 1;
                break;
            case R.id.btn_zhuiai:
                index = 2;
                break;
            case R.id.btn_mine:
                index = 3;
                break;
        }
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.fragment_container, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        mTabs[currentTabIndex].setSelected(false);
        // set current tab as selected.
        mTabs[index].setSelected(true);
        currentTabIndex = index;
    }

}
