package com.example.cookbook;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.*;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.jetbrains.annotations.NotNull;

@Database(entities = {Recipe.class}, version = 1)
public abstract class RecipeRoomDatabase extends RoomDatabase {
    public abstract RecipeDao recipeDao();

    private static RecipeRoomDatabase INSTANCE;

    static RecipeRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (RecipeRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RecipeRoomDatabase.class, "recipe_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            .fallbackToDestructiveMigration()
                            .addCallback(roomcallBack)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomcallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull @NotNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new Populate(INSTANCE).execute();
        }
    };

    private static class Populate extends AsyncTask<Void, Void, Void>{
        private RecipeDao recipeDao;
        private Populate(RecipeRoomDatabase db){
            recipeDao = db.recipeDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            recipeDao.insert(new Recipe("Kofte Curry", "Dummy"));
            recipeDao.insert(new Recipe("Rajma Chawal", "Dummy"));
            recipeDao.insert(new Recipe("Pizza", "Dummy"));
            recipeDao.insert(new Recipe("Biryani", "Dummy"));
            recipeDao.insert(new Recipe("Pulav", "Dummy"));
            recipeDao.insert(new Recipe("Faluda", "Dummy"));
            recipeDao.insert(new Recipe("Chocolate pudding", "Dummy"));
            recipeDao.insert(new Recipe("Dum Biryani", "Dummy"));
            recipeDao.insert(new Recipe("dal bhatti churma", "Dummy"));
            recipeDao.insert(new Recipe("salad", "Dummy"));
            recipeDao.insert(new Recipe("Apple", "Dummy"));
            recipeDao.insert(new Recipe("Kofte fry", "Dummy"));
            recipeDao.insert(new Recipe("Butter chicken", "Dummy"));
            recipeDao.insert(new Recipe("Butter Masala", "Dummy"));

            return null;
        }
    }
}
