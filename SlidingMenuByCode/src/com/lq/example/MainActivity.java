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
		// (1)�����������ݵĲ��֣��ò��ֽ�����һ��Framelayout��
		setContentView(R.layout.layout_content);
		// (2)����SlidingMenu
		initSlidingMenu();
		// (3)ΪMenu��Contentָ��Fragment
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
		// 1.����SlidingMenu������Activity
		mSlidingMenu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
		// 2.����SlidingMenu�Ĳ���
		mSlidingMenu.setMenu(R.layout.layout_menu);
		// 3.����SlidingMenu�Ժ������Ƶ���
		mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		// 4.����SlidingMenu����Ļ���ıߵ���
		mSlidingMenu.setMode(SlidingMenu.LEFT);
		// 5.��������SlidingMenu����
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
