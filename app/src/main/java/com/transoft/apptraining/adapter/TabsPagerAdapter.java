package com.transoft.apptraining.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.transoft.apptraining.R;
import com.transoft.apptraining.ui.main.AboutFragment;
import com.transoft.apptraining.ui.main.ContactFragment;
import com.transoft.apptraining.ui.main.LoginFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

  private static final int[] TAB_TITLES = new int[] { R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3 };
  private final Context context;

  public TabsPagerAdapter(@NonNull Context context, FragmentManager fm) {
    super(fm);
    this.context = context;
  }

  @NonNull
  @Override
  public Fragment getItem(int position) {
    switch (position) {
      case 0:
        return LoginFragment.newInstance();
      case 1:
        return ContactFragment.newInstance();
      case 2:
        return AboutFragment.newInstance();
      default:
        return null;
    }
  }

  @Nullable
  @Override
  public CharSequence getPageTitle(int position) {
    return context.getResources().getString(TAB_TITLES[position]);
  }

  @Override
  public int getCount() {
    return 3;
  }
}
