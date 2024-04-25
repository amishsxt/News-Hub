package com.example.newshub.presentation.ui.MainScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.newshub.databinding.ActivityMainBinding;
import com.example.newshub.presentation.adapter.NewsPagerAdapter;
import com.example.newshub.presentation.ui.MainScreen.CategoryFrags.AllFragment;
import com.example.newshub.presentation.ui.MainScreen.CategoryFrags.EntertainmentFragment;
import com.example.newshub.presentation.ui.MainScreen.CategoryFrags.HealthFragment;
import com.example.newshub.presentation.ui.MainScreen.CategoryFrags.SportsFragment;
import com.example.newshub.presentation.ui.MainScreen.CategoryFrags.TechnologyFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ViewPager2 viewPager2;
    private NewsPagerAdapter adapter;
    private TabLayout tabLayout;

    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewPager2 = binding.viewPager2;
        tabLayout = binding.tabLayout;

        addFrags();
        adapter = new NewsPagerAdapter(getSupportFragmentManager(), getLifecycle(), fragments);
        viewPager2.setAdapter(adapter);

        // Link TabLayout with ViewPager2
//        new TabLayoutMediator(tabLayout, viewPager2, null).attach();

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("All");
                    break;
                case 1:
                    tab.setText("Technology");
                    break;
                case 2:
                    tab.setText("Sports");
                    break;
                case 3:
                    tab.setText("Health");
                    break;
                case 4:
                    tab.setText("Entertainment");
                    break;
                default:
                    tab.setText("Tab " + (position + 1));
            }
        }).attach();
    }

    private void addFrags(){

        // Create fragment list
        fragments = new ArrayList<>();

        fragments.add(new AllFragment());
        fragments.add(new TechnologyFragment());
        fragments.add(new SportsFragment());
        fragments.add(new HealthFragment());
        fragments.add(new EntertainmentFragment());
    }
}
