package com.happyballoons.saket.algon.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.happyballoons.saket.algon.R;
import com.happyballoons.saket.algon.fragments.Bubble;
import com.happyballoons.saket.algon.fragments.Bucket;
import com.happyballoons.saket.algon.fragments.HeapSort;
import com.happyballoons.saket.algon.fragments.Insertion;
import com.happyballoons.saket.algon.fragments.MergeSort;
import com.happyballoons.saket.algon.fragments.QuickSort;
import com.happyballoons.saket.algon.fragments.Radix;
import com.happyballoons.saket.algon.fragments.Selection;

import java.util.ArrayList;
import java.util.List;

public class SortingActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorting);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new QuickSort(), "Quick Sort");
        adapter.addFrag(new MergeSort(), "Merge Sort");
        adapter.addFrag(new HeapSort(), "Heap Sort");
        adapter.addFrag(new Radix(), "Radix Sort");
        adapter.addFrag(new Bucket(), "Bucket Sort");
        adapter.addFrag(new Bubble(), "Bubble Sort");
        adapter.addFrag(new Insertion(), "Insertion Sort");
        adapter.addFrag(new Selection(), "Selection Sort");

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

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
