package com.transoft.apptraining.ui.home;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.transoft.apptraining.R;
import com.transoft.apptraining.adapter.HomeTabsPagerAdapter;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    setupwidgets();
  }

  private void setupwidgets() {
    setupNavigationView();
    setupViewPager();
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

  private void setupViewPager() {
    HomeTabsPagerAdapter tabsPagerAdapter = new HomeTabsPagerAdapter(this, getSupportFragmentManager());

    ViewPager viewPager = findViewById(R.id.home_view_pager);
    viewPager.setAdapter(tabsPagerAdapter);

    TabLayout tabs = findViewById(R.id.hometabs);
    tabs.setupWithViewPager(viewPager);
    tabs.getTabAt(0).setIcon(R.drawable.ic_lock);
    tabs.getTabAt(0).select();
    tabs.getTabAt(1).setIcon(R.drawable.ic_pass);
    tabs.getTabAt(2).setIcon(R.drawable.ic_business);
  }

  public void setActionBarTitle(String title) {
    getSupportActionBar().setTitle(title);
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    return super.onOptionsItemSelected(item);
  }

  @Override
  @SuppressWarnings("StatementWithEmptyBody")
  public boolean onNavigationItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.nav_home) {

    } else if (id == R.id.nav_inbox) {

    } else if (id == R.id.nav_setting) {

    }

    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }
}
