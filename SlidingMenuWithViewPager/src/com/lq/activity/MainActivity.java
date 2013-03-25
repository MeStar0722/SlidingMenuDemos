package com.lq.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;

import com.lq.fragment.ContentFragment;
import com.lq.fragment.MusicPlayFragment;
import com.lq.fragment.SampleListFragment;
import com.slidingmenu.lib.SlidingMenu;

public class MainActivity extends FragmentActivity {
	SlidingMenu mSlidingMenu = null;
	ViewPager mViewPager = null;
	ArrayList<Fragment> mfragmentList = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_viewpager_main);

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
		mfragmentList.add(new ContentFragment("Welcome"));
		mfragmentList.add(new MusicPlayFragment());

		mViewPager = (ViewPager) findViewById(R.id.viewpager_main);
		mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),
				mfragmentList));
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
		fragmentTransaction.replace(R.id.frame_menu, new SampleListFragment());
		fragmentTransaction.commit();
	}

	public SlidingMenu getSlidingMenu() {
		return mSlidingMenu;
	}

	class MyPagerAdapter extends FragmentPagerAdapter {
		private List<Fragment> fragmentList;

		public MyPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
			super(fm);
			this.fragmentList = fragmentList;
		}

		@Override
		public Fragment getItem(int arg0) {
			return (fragmentList == null || fragmentList.size() == 0) ? null
					: fragmentList.get(arg0);
		}

		@Override
		public int getCount() {
			return fragmentList == null ? 0 : fragmentList.size();
		}
	}
}
