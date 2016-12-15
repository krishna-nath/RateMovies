package com.example.krishna.ratemoviez;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public class MoviesTabAppCompatActivity extends AppCompatActivity implements ActivityInterface {


    private TabLayout mTablayout;
    private ViewPager mViewpager;
    private ViewPagerAdapter mAdapter;
    private int[] mIcons = {
            R.drawable.ico_1,
            R.drawable.ico_2,
            R.drawable.ico_3
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_tab_app_compat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Rate Moviez");
        initComponents();
        tabSettings();
        addTab();
        setupTabIcons();


    }

    private void setupTabIcons() {
        mTablayout.getTabAt(0).setIcon(mIcons[0]);
        mTablayout.getTabAt(1).setIcon(mIcons[1]);
        mTablayout.getTabAt(2).setIcon(mIcons[2]);
    }

    private void initComponents() {

        mTablayout = (TabLayout) findViewById(R.id.tabs);
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewpager.setAdapter(mAdapter);
        mViewpager.setOffscreenPageLimit(2);

    }

    private void tabSettings() {

        mTablayout = (TabLayout) findViewById(R.id.tabs);
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewpager.setAdapter(mAdapter);

    }

    private void addTab() {

        final TabLayout.Tab cntmvs = mTablayout.newTab();
        final TabLayout.Tab rtgmvs = mTablayout.newTab();
        final TabLayout.Tab fvtmvs = mTablayout.newTab();

        mTablayout.addTab(cntmvs,0);
        mTablayout.addTab(rtgmvs, 1);
        mTablayout.addTab(fvtmvs, 2);

        mTablayout.setTabTextColors(ContextCompat.getColorStateList(this, R.color.tab_selector));
        mTablayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.indicator));
        mViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTablayout));
        mTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                 int num = tab.getPosition();
                mViewpager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    @Override
    public void passToActivity(Bundle item) {

       Intent intent = new Intent(this,MovieShowActivity.class);
       intent.putExtra(item);
    }
}
