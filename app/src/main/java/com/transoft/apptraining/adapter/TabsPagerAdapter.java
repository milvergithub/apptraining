package com.transoft.apptraining.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.transoft.apptraining.ui.main.AboutFragment;
import com.transoft.apptraining.ui.main.ContactFragment;
import com.transoft.apptraining.ui.main.LoginFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

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

  @Override
  public int getCount() {
    return 3;
  }
}
