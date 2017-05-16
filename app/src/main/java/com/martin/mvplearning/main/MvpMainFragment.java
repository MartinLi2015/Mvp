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
import com.martin.common.base.helper.FragmentAdapter;
import com.martin.mvplearning.Constants;
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

        for (int i = 0; i < mTabs.length; i++) {
            tabs.addTab(tabs.newTab().setText(mTabs[i]));
            switch (i) {
                case 0:
                    fragments.add(new DailyFragment());
                    break;
                case 1:
                    fragments.add(new SectionFragment());
                    break;
                case 2:
                    fragments.add(new WechatFragment());
                    break;
                default:
                    fragments.add(new QuickFragment());
                    break;
            }
        }
        int position = 0;
        if (getArguments() != null) {
            position = getArguments().getInt(Constants.ARG_POSITION, 0);
        }

        viewpager.setAdapter(new FragmentAdapter(getChildFragmentManager(), fragments));
        viewpager.setCurrentItem(position);
        tabs.setupWithViewPager(viewpager);
        tabs.setVerticalScrollbarPosition(position);
        for (int i = 0; i < mTabs.length; i++) {
            tabs.getTabAt(i).setText(mTabs[i]);
        }


    }

}
