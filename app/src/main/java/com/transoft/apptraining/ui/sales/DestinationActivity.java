package com.transoft.apptraining.ui.sales;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.transoft.apptraining.R;
import com.transoft.apptraining.adapter.DestinationTabsPagerAdapter;

public class DestinationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_destination);
    setupwidgets();
  }

  private void setupwidgets() {
    setupNavigationView();
    setupViewPager();
  }

  private void setupViewPager() {
    DestinationTabsPagerAdapter tabsPagerAdapter = new DestinationTabsPagerAdapter(this, getSupportFragmentManager());

    ViewPager viewPager = findViewById(R.id.destination_view_pager);
    viewPager.setAdapter(tabsPagerAdapter);

    TabLayout tabs = findViewById(R.id.destinationtabs);
    tabs.setupWithViewPager(viewPager);
    tabs.getTabAt(0).setIcon(R.drawable.ic_lock);
    tabs.getTabAt(0).select();
    tabs.getTabAt(1).setIcon(R.drawable.ic_pass);
    tabs.getTabAt(2).setIcon(R.drawable.ic_business);
    tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override
      public void onTabSelected(TabLayout.Tab tab) {

      }

      @Override
      public void onTabUnselected(TabLayout.Tab tab) {
      }

      @Override
      public void onTabReselected(TabLayout.Tab tab) {

      }
    });
  }

  private void setupNavigationView() {
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
        R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = findViewById(R.id.nav_view);
    Menu menu = navigationView.getMenu();

    // find MenuItem you want to change
    MenuItem nav_camara = menu.findItem(R.id.nav_setting);
    nav_camara.setVisible(false);
    navigationView.setNavigationItemSelectedListener(this);
  }

  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
    return false;
  }
}
