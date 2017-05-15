package com.martin.mvplearning.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.martin.common.base.BaseFragment;
import com.martin.mvplearning.R;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class MvpMainFragment extends BaseFragment<ZhiHuMianPresenter, ZhihuMainModel> implements ZhiHuContract.ZhihuMainView {

    private TabLayout tabs;
    private Toolbar toolbar;
    private ViewPager viewpager;
    private FloatingActionButton fab;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {

        tabs = (TabLayout) view.findViewById(R.id.tabs);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);

        toolbar.setTitle("首页");
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mvp_main;
    }

    @Override
    public void showTabList(String[] mTabs) {

    }

}
