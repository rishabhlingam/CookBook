package com.example.cookbook;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class RecipeViewModel extends AndroidViewModel {

    private RecipeRepository mRepository;

    private LiveData<List<Recipe>> mAllRecipies;

    public RecipeViewModel (Application application) {
        super(application);
        mRepository = new RecipeRepository(application);
        mAllRecipies = mRepository.getAllWords();
    }

    public LiveData<List<Recipe>> getAllWords() { return mAllRecipies; }

    public void insert(Recipe recipe) { mRepository.insert(recipe); }

}
