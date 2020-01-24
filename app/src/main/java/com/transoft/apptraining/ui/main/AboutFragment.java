package com.transoft.apptraining.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.transoft.apptraining.R;

public class AboutFragment extends Fragment {

  private static final String TAG = "About";
  private PageViewModel pageViewModel;
  private CarouselView carouselView;
  private int[] images = {R.drawable.autobus, R.drawable.bus_nuevo, R.drawable.bus1, R.drawable.bus2, R.drawable.bus3};

  public AboutFragment() {
  }

  public static AboutFragment newInstance() {
    return new AboutFragment();
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
    pageViewModel.setIndex(TAG);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_about, container, false);
    carouselView = root.findViewById(R.id.carouselView);
    carouselView.setPageCount(images.length);
    carouselView.setImageListener(imageListener);
    return root;
  }

  ImageListener imageListener = new ImageListener() {
    @Override
    public void setImageForPosition(int position, ImageView imageView) {
      imageView.setImageResource(images[position]);
    }
  };
}
