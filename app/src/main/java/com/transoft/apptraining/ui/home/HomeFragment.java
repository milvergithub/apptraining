package com.transoft.apptraining.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.transoft.apptraining.R;

public class HomeFragment extends Fragment {

  public HomeFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    ((HomeActivity)getActivity()).setActionBarTitle("Home");
    return inflater.inflate(R.layout.fragment_home, container, false);
  }
}
