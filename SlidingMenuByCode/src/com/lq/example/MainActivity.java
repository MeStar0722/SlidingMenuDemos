package com.lq.example;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;

import com.lq.fragment.ContentFragment;
import com.lq.fragment.MenuFragment;
import com.slidingmenu.lib.SlidingMenu;

public class MainActivity extends Activity {
	SlidingMenu mSlidingMenu = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// (1)设置主题内容的布局（该布局仅包含一个Framelayout）
		setContentView(R.layout.layout_content);
		// (2)设置SlidingMenu
		initSlidingMenu();
		// (3)为Menu和Content指定Fragment
		FragmentTransaction fragmentTransaction = getFragmentManager()
				.beginTransaction();
		MenuFragment menuFragment = new MenuFragment();
		fragmentTransaction.replace(R.id.frame_menu, menuFragment);
		fragmentTransaction.replace(R.id.frame_content, new ContentFragment(
				"Welcome"), "Welcome");
		fragmentTransaction.commit();

		getActionBar().setDisplayHomeAsUpEnabled(true);
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


	public SlidingMenu getSlidingMenu() {
		return mSlidingMenu;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			mSlidingMenu.toggle();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
