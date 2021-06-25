package com.example.cookbook.ui.add;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cookbook.MyRecipeRecyclerViewAdapter;
import com.example.cookbook.R;
import com.example.cookbook.Recipe;
import com.example.cookbook.RecipeViewModel;
import com.example.cookbook.databinding.FragmentAddRecipeBinding;
import com.example.cookbook.databinding.FragmentHomeBinding;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddRecipeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddRecipeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecipeViewModel mRecipeViewModel;
    private NavHostFragment navHostFragment;
    private NavController navController;
    private MyRecipeRecyclerViewAdapter adapter;
    private List<Recipe> list;
    private static final String TAG = "AddRecipeFragment";
    private FragmentAddRecipeBinding binding;
    private static String CHANNEL_ID = "CookBook Notification Channel";

    public AddRecipeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddRecipeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddRecipeFragment newInstance(String param1, String param2) {
        AddRecipeFragment fragment = new AddRecipeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        mRecipeViewModel = new RecipeViewModel( getActivity().getApplication() );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddRecipeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        EditText recipeName = binding.name;
        EditText recipeBody = binding.body;
        Button addRecipe = binding.addRecipe;
        addRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = recipeName.getText().toString().trim();
                if(name.isEmpty()){
                    name = "dummy recipe name";
                }
                String body = recipeBody.getText().toString().trim();
                if(body.isEmpty()){
                    body = "dummy recipe body";
                }

                mRecipeViewModel.insert( new Recipe(name, body) );

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel notificationChannel =
                            new NotificationChannel(CHANNEL_ID, "CookBook Notification",
                                    NotificationManager.IMPORTANCE_DEFAULT);

                    NotificationManager mNotificationManager =
                            (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotificationManager.createNotificationChannel(notificationChannel);

                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(getContext(), CHANNEL_ID)
                                    .setSmallIcon(R.drawable.ic_launcher_background)
                                    .setContentTitle("Ummm!!! " + name + " looks delicious!")
                                    .setContentText("Added it to your CookBook..");

                    mNotificationManager.notify(1,mBuilder.build());
                }

                navController.navigate(R.id.homeFragment);
            }
        });
        return view;
    }
}