package com.zlkj.zuixinstudio.banner;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.zlkj.zuixinstudio.R;

import java.util.ArrayList;
import java.util.List;


public class AdviseCycleView extends LinearLayout {

	int selectIndex;
	private Context context;
	private LinearLayout ll_advise_cycle;
	private ViewPager vp_advise_cycle;

	private List<ImageView> list_nav;
	private List<ImageView> list_iv;

	private List<String> list_url;

	private Handler handler = new Handler();

	private OnAdviseClickListener listener;

	public AdviseCycleView(Context context) {
		this(context, null);
	}

	public AdviseCycleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();

	}

	private void init() {
		LayoutInflater.from(context).inflate(R.layout.view_advise_cycle, this);

		ll_advise_cycle = (LinearLayout) findViewById(R.id.ll_advise_cycle);
		vp_advise_cycle = (ViewPager) findViewById(R.id.vp_advise_cycle);
		vp_advise_cycle.addOnPageChangeListener(new MyPageChangeListener());
		vp_advise_cycle.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_UP:
					startImageTimerTask();
					break;
				default:
					stopImageTimerTask();
					break;
				}
				return false;
			}
		});
	}

	public void setData(List<String> list, OnAdviseClickListener listener) {
		ll_advise_cycle.removeAllViews();

		this.list_url = list;
		this.listener = listener;
		this.list_nav = new ArrayList<ImageView>();
		this.list_iv = new ArrayList<ImageView>();

		boolean temp = true;
		for (String url : list_url) {
			ImageView iv = new ImageView(context);
			LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
			params.leftMargin = 10;
			iv.setScaleType(ImageView.ScaleType.FIT_XY);
			iv.setLayoutParams(params);
			iv.setBackgroundResource(R.mipmap.page_unfocused);
			if (temp) {
				temp = false;
				iv.setBackgroundResource(R.mipmap.page_focuese);
			}

			list_nav.add(iv);
			ll_advise_cycle.addView(iv);
		}

		vp_advise_cycle.setAdapter(new MyPagerAdapter());
		startImageTimerTask();
	}

	private  void startImageTimerTask() {
		stopImageTimerTask();
		handler.postDelayed(autoTask, 2000);

	}

	private void stopImageTimerTask() {
		handler.removeCallbacks(autoTask);
	}

	private Runnable autoTask = new Runnable() {
		@Override
		public void run() {
			if (list_url != null) {
				vp_advise_cycle.setCurrentItem(vp_advise_cycle.getCurrentItem() + 1);
			}
		}
	};

	private final class MyPageChangeListener implements
			ViewPager.OnPageChangeListener {
		@Override
		public void onPageScrollStateChanged(int state) {
			if (state == ViewPager.SCROLL_STATE_IDLE) {//(空闲，挂空挡)， 理解为：只要拖动/滑动结束
				startImageTimerTask();
			}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int index) {
			index = index % list_nav.size();
			selectIndex=index;
			int temp = 0;
			for (ImageView iv : list_nav) {
				if (index == temp) {
					iv.setBackgroundResource(R.mipmap.page_focuese);
				} else {
					iv.setBackgroundResource(R.mipmap.page_unfocused);
				}
				temp++;
			}
		}
	}

	private class MyPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view == obj;
		}

		@Override
		public Object instantiateItem(ViewGroup container, final int position) {
			String url = list_url.get(position % list_url.size());

			ImageView imageView = null;
			if (list_iv.isEmpty()) {
				imageView = new ImageView(context);
				imageView.setLayoutParams(new LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				imageView.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {

						listener.onClick(selectIndex, v);
					}
				});
			} else {
				imageView = list_iv.remove(0);
			}
//			imageView.setTag(url);
			container.addView(imageView);
			Glide.with(context).load(url).into(imageView);
			return imageView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			ImageView view = (ImageView) object;
			vp_advise_cycle.removeView(view);
			list_iv.add(view);
		}

	}

	public interface OnAdviseClickListener {
		void onClick(int position, View view);
	}
}
