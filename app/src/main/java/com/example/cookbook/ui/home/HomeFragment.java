package com.example.cookbook.ui.home;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import com.example.cookbook.MainActivity;
import com.example.cookbook.MyRecipeRecyclerViewAdapter;
import com.example.cookbook.R;
import com.example.cookbook.Recipe;
import com.example.cookbook.RecipeRepository;
import com.example.cookbook.RecipeViewModel;
import com.example.cookbook.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class HomeFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    private RecipeViewModel mRecipeViewModel;
    private NavHostFragment navHostFragment;
    private NavController navController;
    private MyRecipeRecyclerViewAdapter adapter;
    private List<Recipe> list;
    private static final String TAG = "HomeFragment";
    private FragmentHomeBinding binding;

    public HomeFragment() {}

    @SuppressWarnings("unused")
    public static HomeFragment newInstance(int columnCount) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        mRecipeViewModel = new RecipeViewModel( getActivity().getApplication() );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        Button button = binding.tempButton;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_homeFragment_to_addRecipeFragment);
            }
        });

        // Set the adapter
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) binding.list.getRoot();
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            adapter = new MyRecipeRecyclerViewAdapter();
            recyclerView.setAdapter(adapter);
            mRecipeViewModel.getAllWords().observe(getViewLifecycleOwner(), new Observer<List<Recipe>>() {
                @Override
                public void onChanged(@Nullable final List<Recipe> recipes) {
                    // Update the UI.
                    Log.i(TAG, "onChanged: Executed.");
                    adapter.setRecepies(recipes);
                }
            });

        return view;
    }
}