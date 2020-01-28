package com.transoft.apptraining.ui.sales;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.transoft.apptraining.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DestinationFragment extends Fragment {


  public DestinationFragment() {
    // Required empty public constructor
  }

  public static DestinationFragment newInstance() {
    return new DestinationFragment();
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_destination, container, false);
  }

}
