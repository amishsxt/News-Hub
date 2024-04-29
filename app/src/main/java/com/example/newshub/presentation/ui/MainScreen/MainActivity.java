package com.example.newshub.presentation.ui.MainScreen;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.newshub.databinding.ActivityMainBinding;
import com.example.newshub.presentation.adapter.CategoryPagerAdapter;
import com.example.newshub.presentation.ui.MainScreen.CategoryFrags.BusinessFragment;
import com.example.newshub.presentation.ui.MainScreen.CategoryFrags.EntertainmentFragment;
import com.example.newshub.presentation.ui.MainScreen.CategoryFrags.HealthFragment;
import com.example.newshub.presentation.ui.MainScreen.CategoryFrags.SportsFragment;
import com.example.newshub.presentation.ui.MainScreen.CategoryFrags.TechnologyFragment;
import com.example.newshub.presentation.ui.MainScreen.CategoryFrags.TrendingFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ViewPager2 viewPager2;
    private CategoryPagerAdapter adapter;
    private TabLayout tabLayout;

    private TrendingFragment trendingFragment;
    private SportsFragment sportsFragment;
    private TechnologyFragment technologyFragment;
    private BusinessFragment businessFragment;
    private HealthFragment healthFragment;
    private EntertainmentFragment entertainmentFragment;

    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewPager2 = binding.viewPager2;
        tabLayout = binding.tabLayout;

        addFrags();
        adapter = new CategoryPagerAdapter(getSupportFragmentManager(), getLifecycle(), fragments);
        viewPager2.setAdapter(adapter);


        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            //currentTabPosition = position;
            switch (position) {
                case 0:
                    tab.setText("Trending");
                    break;
                case 1:
                    tab.setText("Technology");
                    break;
                case 2:
                    tab.setText("Sports");
                    break;
                case 3:
                    tab.setText("Business");
                    break;
                case 4:
                    tab.setText("Health");
                    break;
                case 5:
                    tab.setText("Entertainment");
                    break;
                default:
                    tab.setText("Tab " + (position + 1));
            }
        }).attach();

        //searching logic
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("onQueryTextSubmit","done: "+query);
                sendSearchToCurrentFragment(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void addFrags(){

        // Create fragment list
        fragments = new ArrayList<>();

        trendingFragment = new TrendingFragment();
        technologyFragment = new TechnologyFragment();
        sportsFragment = new SportsFragment();
        businessFragment = new BusinessFragment();
        healthFragment = new HealthFragment();
        entertainmentFragment = new EntertainmentFragment();

        fragments.add(trendingFragment);
        fragments.add(technologyFragment);
        fragments.add(sportsFragment);
        fragments.add(businessFragment);
        fragments.add(healthFragment);
        fragments.add(entertainmentFragment);
    }

    private void sendSearchToCurrentFragment(String searchText) {
        Log.d("sendSearchToCurrentFragment","done: "+searchText);

        int currentTabPosition = binding.tabLayout.getSelectedTabPosition();
        Log.d("currentTabPosition",String.valueOf(currentTabPosition));

        switch (currentTabPosition) {
            case 0:
                trendingFragment.onSearch(searchText);
                break;
            case 1:
                technologyFragment.onSearch(searchText);
                break;
            case 2:
                sportsFragment.onSearch(searchText);
                break;
            case 3:
                businessFragment.onSearch(searchText);
                break;
            case 4:
                healthFragment.onSearch(searchText);
                break;
            case 5:
                entertainmentFragment.onSearch(searchText);
                break;
            default:
                trendingFragment.onSearch(searchText);
        }
    }
}
