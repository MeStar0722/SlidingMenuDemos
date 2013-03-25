package com.lq.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lq.fragment.ColorMenuFragment;
import com.slidingmenu.lib.SlidingMenu;

public class MainActivity extends FragmentActivity {
	private SlidingMenu mSlidingMenu = null;
	private ViewPager mViewPager = null;
	private ArrayList<View> mPageList = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_viewpager);

		initSlidingMenu();
		initViewPager();
		initPopulateFragments();
	}

	private void initSlidingMenu() {
		mSlidingMenu = new SlidingMenu(this);
		// 1.设置SlidingMenu的宿主Activity
		mSlidingMenu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
		// 2.设置SlidingMenu的布局
		mSlidingMenu.setMenu(R.layout.layout_menu);
		// 3.设置SlidingMenu以何种手势弹出
		mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		// 4.设置SlidingMenu从屏幕的哪边弹出
		mSlidingMenu.setMode(SlidingMenu.LEFT);
		// 5.设置其他SlidingMenu参数
		mSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		mSlidingMenu.setShadowDrawable(R.drawable.shadow);
		mSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		mSlidingMenu.setFadeDegree(0.35f);

	}

	private void initViewPager() {
		LayoutInflater inflater = getLayoutInflater();
		mPageList = new ArrayList<View>();
		mPageList.add(inflater.inflate(R.layout.layout_content, null));
		mPageList.add(inflater.inflate(R.layout.layout_musicplay, null));

		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mViewPager.setAdapter(new MyPagerAdapter());
		mViewPager.setCurrentItem(0);
		mViewPager.setOnPageChangeListener(new SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				switch (position) {
				case 0:
					getSlidingMenu().setTouchModeAbove(
							SlidingMenu.TOUCHMODE_FULLSCREEN);

					break;
				default:
					getSlidingMenu().setTouchModeAbove(
							SlidingMenu.TOUCHMODE_NONE);
					break;
				}
			}
		});
	}

	private void initPopulateFragments() {
		FragmentTransaction fragmentTransaction = getSupportFragmentManager()
				.beginTransaction();
		fragmentTransaction.replace(R.id.frame_menu, new ColorMenuFragment());
		fragmentTransaction.commit();
	}

	public SlidingMenu getSlidingMenu() {
		return mSlidingMenu;
	}

	class MyPagerAdapter extends PagerAdapter {
		@Override
		public int getCount() {
			return mPageList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(mPageList.get(position));
			return mPageList.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(mPageList.get(position));
		}
	}

	public void switchContent(Fragment fragment) {
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.frame_content, fragment).addToBackStack(null)
				.commit();
		getSlidingMenu().showContent();
	}
}
