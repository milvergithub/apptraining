package com.transoft.apptraining.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.transoft.apptraining.ui.AirFragment;
import com.transoft.apptraining.ui.home.HomeFragment;

public class HomeTabsPagerAdapter extends FragmentPagerAdapter {

  private final Context context;

  public HomeTabsPagerAdapter(@NonNull Context context, FragmentManager fm) {
    super(fm);
    this.context = context;
  }

  @NonNull
  @Override
  public Fragment getItem(int position) {
    switch (position) {
      case 0:
        return HomeFragment.newInstance();
      case 1:
        return AirFragment.newInstance();
      case 2:
        return HotelFragment.newInstance();
      default:
        return null;
    }
  }

  @Override
  public int getCount() {
    return 3;
  }
}
