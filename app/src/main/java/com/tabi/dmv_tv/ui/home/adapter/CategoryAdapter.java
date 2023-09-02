package com.tabi.dmv_tv.ui.home.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tabi.dmv_tv.databinding.ItemVideoByCategoryBinding;
import com.tabi.dmv_tv.models.Category;
import com.tabi.dmv_tv.models.TvSpecial;

import java.util.ArrayList;
import java.util.HashMap;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    HashMap<Category, ArrayList<TvSpecial>> videosByCategory = new HashMap<>();
    private ItemClickListener itemClickListener = null;

    private ItemFocusListener itemFocusListener= null;

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemVideoByCategoryBinding binding = ItemVideoByCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return videosByCategory.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addVideosByCategories(HashMap<Category, ArrayList<TvSpecial>> videosByCategory) {
        this.videosByCategory.clear();
        this.videosByCategory = videosByCategory;
        notifyDataSetChanged();
    }

    public void addItemFocusListener(ItemFocusListener itemFocusListener) {
        this.itemFocusListener = itemFocusListener;
    }

    public void addItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        ItemVideoByCategoryBinding binding;

        public CategoryViewHolder(@NonNull ItemVideoByCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void onBind(int position) {
            ArrayList<Category> categories = new ArrayList<>(videosByCategory.keySet());
            binding.tvCategoryName.setText(categories.get(position).getName());

            VideosAdapter videosAdapter = new VideosAdapter();
            videosAdapter.addVideos(videosByCategory.get(categories.get(position)));
            videosAdapter.addItemClickListener(itemClickListener);
            videosAdapter.addItemFocusListener(itemFocusListener);
            binding.rvVideos.setAdapter(videosAdapter);

        }
    }

    public interface ItemClickListener {
        void onItemClick(TvSpecial video);
    }

    public interface ItemFocusListener {
        void onItemFocus(TvSpecial video,Boolean hasFocus);
    }
}
