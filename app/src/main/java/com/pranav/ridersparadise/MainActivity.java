package com.pranav.ridersparadise;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        String[] brakeStrings = getResources().getStringArray(R.array.brakes);
        String[] chainStrings = getResources().getStringArray(R.array.chain);
        String[] oilStrings = getResources().getStringArray(R.array.engine_oil);
        String[] tyreStrings = getResources().getStringArray(R.array.tyre_pressure);
        String[] sparkStrings = getResources().getStringArray(R.array.spark_plug);
        adapter.addFragment(MainFragment.newInstance(brakeStrings[0], brakeStrings[1], brakeStrings[2], brakeStrings[3]), "Brakes");
        adapter.addFragment(MainFragment.newInstance(oilStrings[0], oilStrings[1], oilStrings[2], oilStrings[3]), "Engine Oil");
        adapter.addFragment(MainFragment.newInstance(tyreStrings[0], tyreStrings[1], tyreStrings[2], tyreStrings[3]), "Tyre Pressure");
        adapter.addFragment(MainFragment.newInstance(chainStrings[0], chainStrings[1], chainStrings[2], chainStrings[3]), "Chain");
        adapter.addFragment(MainFragment.newInstance(sparkStrings[0], sparkStrings[1], sparkStrings[2], sparkStrings[3]), "Spark Plugs");
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_about:
                Intent intent = new Intent(this, AboutActivity.class);
                this.startActivity(intent);
                return true;
            case R.id.action_nearest_petrol_bunks:
                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?&daddr=nearby petrol bunk");
                Intent mapsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                mapsIntent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                try
                {
                    startActivity(mapsIntent);
                }
                catch(ActivityNotFoundException ex)
                {
                    try
                    {
                        Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        startActivity(unrestrictedIntent);
                    }
                    catch(ActivityNotFoundException innerEx)
                    {
                        Toast.makeText(this, "Please install a maps application", Toast.LENGTH_LONG).show();
                    }
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
