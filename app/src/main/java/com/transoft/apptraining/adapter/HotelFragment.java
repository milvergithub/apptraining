package com.transoft.apptraining.adapter;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.transoft.apptraining.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotelFragment extends Fragment {


  public HotelFragment() {
    // Required empty public constructor
  }

  public static HotelFragment newInstance() {
    return new HotelFragment();
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_hotel, container, false);
  }

}
