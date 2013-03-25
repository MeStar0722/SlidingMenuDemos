package com.lq.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;

import com.lq.fragment.ColorFragment;
import com.lq.fragment.ColorMenuFragment;
import com.lq.fragment.MusicPlayFragment;
import com.slidingmenu.lib.SlidingMenu;

public class MainActivity extends FragmentActivity {
	private SlidingMenu mSlidingMenu = null;
	private ViewPager mViewPager = null;
	private ArrayList<Fragment> mfragmentList = null;

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
		mfragmentList = new ArrayList<Fragment>();
		mfragmentList.add(new ColorFragment(R.color.red));
		mfragmentList.add(new MusicPlayFragment());

		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
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
		// MenuFragment menuFragment = new MenuFragment();
		fragmentTransaction.replace(R.id.frame_menu, new ColorMenuFragment());
		fragmentTransaction.commit();
	}

	public SlidingMenu getSlidingMenu() {
		return mSlidingMenu;
	}

	class MyPagerAdapter extends FragmentPagerAdapter {

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			return (mfragmentList == null || mfragmentList.size() == 0) ? null
					: mfragmentList.get(arg0);
		}

		@Override
		public int getCount() {
			return mfragmentList == null ? 0 : mfragmentList.size();
		}
	}

	public void switchContent(Fragment fragment) {
		// getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
		// fragment).commit();
		mfragmentList.set(0, fragment);
		mViewPager.getAdapter().notifyDataSetChanged();
		mViewPager.setCurrentItem(0);
		getSlidingMenu().showContent();
	}
}
