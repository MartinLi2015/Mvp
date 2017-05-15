package com.martin.mvplearning;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.martin.common.base.BaseActivity;
import com.martin.common.base.utils.ToastUtil;
import com.martin.mvplearning.main.MvpMainFragment;


/**
 * 主页
 */

public class MvpMainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navView;
    DrawerLayout drawerLayout;


    @Override
    protected void onResume() {
        super.onResume();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean isOpen() {
        return true;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        navView = (NavigationView) findViewById(R.id.nav_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().add(R.id.frame_container, new MvpMainFragment()
                , MvpMainFragment.class.getSimpleName())
                .commitAllowingStateLoss();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mvp_main;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_publish) {

        } else if (id == R.id.menu_map) {

        } else if (id == R.id.menu_tv) {

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        ToastUtil.showToast(this, item.getTitle());
        return false;
    }
}
