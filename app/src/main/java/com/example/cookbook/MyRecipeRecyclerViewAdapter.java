package com.example.cookbook;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cookbook.databinding.CardRecipeBinding;
import com.example.cookbook.databinding.FragmentHomeBinding;
import com.example.cookbook.databinding.FragmentHomeListBinding;

import java.util.ArrayList;
import java.util.List;

public class MyRecipeRecyclerViewAdapter extends RecyclerView.Adapter<MyRecipeRecyclerViewAdapter.ViewHolder> {

    private List<Recipe> mValues;

    public MyRecipeRecyclerViewAdapter() {
        mValues = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(CardRecipeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getRecipeName());
    }

    public void setRecepies(List<Recipe> recepies){
        mValues = recepies;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public Recipe mItem;

        public ViewHolder(CardRecipeBinding binding) {
            super(binding.getRoot());
            mIdView = binding.recipeName;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mIdView.getText() + "'";
        }
    }
}