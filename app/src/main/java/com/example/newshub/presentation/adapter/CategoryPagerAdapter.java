package com.example.newshub.presentation.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class CategoryPagerAdapter extends FragmentStateAdapter {

    private final List<Fragment> fragments;

    public CategoryPagerAdapter(FragmentManager fm, Lifecycle lifecycle, List<Fragment> fragments) {
        super(fm, lifecycle);
        this.fragments = fragments;
    }

    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
