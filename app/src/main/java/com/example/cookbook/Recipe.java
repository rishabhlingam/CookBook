package com.example.cookbook;

import androidx.annotation.NonNull;
import androidx.room.*;

@Entity
public class Recipe {

    @PrimaryKey(autoGenerate=true)
    private int uid;

    @NonNull
    private String recipeName;

    @NonNull
    private String recipeBody;

    public Recipe(@NonNull String recipeName, @NonNull String recipeBody) {
        this.recipeName = recipeName;
        this.recipeBody = recipeBody;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @NonNull
    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(@NonNull String recipeName) {
        this.recipeName = recipeName;
    }

    @NonNull
    public String getRecipeBody() {
        return recipeBody;
    }

    public void setRecipeBody(@NonNull String recipeBody) {
        this.recipeBody = recipeBody;
    }
}
