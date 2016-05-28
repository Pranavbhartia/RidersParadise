package com.pranav.ridersparadise;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        String[] brakeStrings = getResources().getStringArray(R.array.brakes);
        String[] chainStrings = getResources().getStringArray(R.array.chain);
        String[] oilStrings = getResources().getStringArray(R.array.engine_oil);
        String[] tyreStrings = getResources().getStringArray(R.array.tyre_pressure);
        String[] sparkStrings = getResources().getStringArray(R.array.spark_plug);
        adapter.addFragment(new MainFragment().newInstance(brakeStrings[0], brakeStrings[1], brakeStrings[2], brakeStrings[3]), "Brakes");
        adapter.addFragment(new MainFragment().newInstance(oilStrings[0], oilStrings[1], oilStrings[2], oilStrings[3]), "Engine Oil");
        adapter.addFragment(new MainFragment().newInstance(tyreStrings[0], tyreStrings[1], tyreStrings[2], tyreStrings[3]), "Tyre Pressure");
        adapter.addFragment(new MainFragment().newInstance(chainStrings[0], chainStrings[1], chainStrings[2], chainStrings[3]), "Chain");
        adapter.addFragment(new MainFragment().newInstance(sparkStrings[0], sparkStrings[1], sparkStrings[2], sparkStrings[3]), "Spark Plugs");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
