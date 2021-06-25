package com.example.cookbook;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.List;

@Dao
public interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Recipe recipe);

    @Update
    public void updateWords(Recipe... recipes);

    @Query("SELECT * from Recipe")
    LiveData<List<Recipe>> getAllWords();
}
