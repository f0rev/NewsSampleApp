package com.feevx.ravan.newssampleapp.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.feevx.ravan.newssampleapp.R;
import com.feevx.ravan.newssampleapp.ui.fragments.AllNewsFragment;
import com.feevx.ravan.newssampleapp.ui.fragments.BasketballFragment;
import com.feevx.ravan.newssampleapp.ui.fragments.FootballFragment;
import com.feevx.ravan.newssampleapp.ui.fragments.HockeyFragment;
import com.feevx.ravan.newssampleapp.ui.fragments.VolleyballFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;
    private ActionBarDrawerToggle mDrawerToggle;
    boolean mToolBarNavigationListenerIsRegistered;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureDagger();
        configureToolbar();
        configureSidePanel();


    }

    private void configureToolbar() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    private void configureSidePanel() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                mDrawerToggle.setDrawerIndicatorEnabled(true);
            } else {
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                mDrawerToggle.setDrawerIndicatorEnabled(false);

            }
        });

        navigationView.setCheckedItem(R.id.nav_all_news);
        switchFragment(new AllNewsFragment());


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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings)
            return true;

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_all_news) {
            switchFragment(new AllNewsFragment());
        } else if (id == R.id.nav_football) {
            switchFragment(new FootballFragment());
        } else if (id == R.id.nav_basketball) {
            switchFragment(new BasketballFragment());
        } else if (id == R.id.nav_volleyball) {
            switchFragment(new VolleyballFragment());
        } else if (id == R.id.nav_hockey) {
            switchFragment(new HockeyFragment());
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void switchFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commit();
    }

    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void showBackButton(boolean enable) {
        if (enable) {
            mDrawerToggle.setDrawerIndicatorEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            if (!mToolBarNavigationListenerIsRegistered) {
                mDrawerToggle.setToolbarNavigationClickListener(v -> onBackPressed());
                mToolBarNavigationListenerIsRegistered = true;
            }
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            mDrawerToggle.setDrawerIndicatorEnabled(true);
            mDrawerToggle.setToolbarNavigationClickListener(null);
            mToolBarNavigationListenerIsRegistered = false;
        }
    }
}
