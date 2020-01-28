package com.transoft.apptraining.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.transoft.apptraining.MainActivity;
import com.transoft.apptraining.R;
import com.transoft.apptraining.ui.sales.DestinationActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends Fragment {

  private View root;
  public HomeFragment() {
  }

  public static HomeFragment newInstance() {
    return new HomeFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    root = inflater.inflate(R.layout.fragment_home, container, false);
    ButterKnife.bind(this, root);
    return root;
  }

  @OnClick(R.id.btnsales)
  public void onClickSales() {
    startActivity(new Intent(root.getContext(), DestinationActivity.class));
  }
}
