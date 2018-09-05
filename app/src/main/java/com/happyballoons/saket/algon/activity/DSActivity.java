package com.happyballoons.saket.algon.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.happyballoons.saket.algon.fragments.AVLTrees;
import com.happyballoons.saket.algon.fragments.ArraysFragment;
import com.happyballoons.saket.algon.fragments.BST;
import com.happyballoons.saket.algon.fragments.HashMapsFragment;
import com.happyballoons.saket.algon.fragments.LinkedListFragment;
import com.happyballoons.saket.algon.R;
import com.happyballoons.saket.algon.fragments.QueueFragment;
import com.happyballoons.saket.algon.fragments.RedBlackTrees;
import com.happyballoons.saket.algon.fragments.StacksFragment;

import java.util.ArrayList;
import java.util.List;

public class DSActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds);

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
        adapter.addFrag(new ArraysFragment(), "Arrays");
        adapter.addFrag(new LinkedListFragment(), "Linked List");
        adapter.addFrag(new StacksFragment(), "Stacks");
        adapter.addFrag(new QueueFragment(), "Queues");
        adapter.addFrag(new HashMapsFragment(), "Hash Maps");
        adapter.addFrag(new BST(), "Binary Search Trees");
        adapter.addFrag(new RedBlackTrees(), "Red-Black Trees");
        adapter.addFrag(new AVLTrees(), "AVL Trees");
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
