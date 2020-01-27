package com.transoft.apptraining.ui.main;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.transoft.apptraining.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

  private static final String TAG = "Login";
  private PageViewModel pageViewModel;
  private View root;

  public LoginFragment() {
    // Required empty public constructor
  }

  public static LoginFragment newInstance() {
    return new LoginFragment();
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
    pageViewModel.setIndex(TAG);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    root = inflater.inflate(R.layout.fragment_login, container, false);
    ButterKnife.bind(this, root);
    return root;
  }

  @OnClick(R.id.btn_login)
  public void onLogin() {
    startActivity(new Intent(root.getContext(), HomeActivity.class));
  }

}
