package com.tabi.dmv_tv.ui.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tabi.dmv_tv.R;
import com.tabi.dmv_tv.databinding.ItemVideoBinding;
import com.tabi.dmv_tv.models.TvSpecial;

import java.util.ArrayList;
import java.util.List;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.VideoViewHolder> {
    List<TvSpecial> videos = new ArrayList<>();
    private CategoryAdapter.ItemClickListener itemClickListener = null;

    private CategoryAdapter.ItemFocusListener itemFocusListener = null;


    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemVideoBinding binding = ItemVideoBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new VideoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }


    public void addItemClickListener(CategoryAdapter.ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void addItemFocusListener(CategoryAdapter.ItemFocusListener itemFocusListener) {
        this.itemFocusListener = itemFocusListener;
    }

    public void addVideos(List<TvSpecial> videos) {
        this.videos.clear();
        this.videos.addAll(videos);
        notifyDataSetChanged();
    }


    public class VideoViewHolder extends RecyclerView.ViewHolder {
        private ItemVideoBinding binding;

        public VideoViewHolder(@NonNull ItemVideoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(int position) {
            TvSpecial video = videos.get(position);

            binding.tvName.setText(video.getTitle());

            Glide.with(binding.getRoot().getContext())
                    .load(video.getThumbnail())
                    .placeholder(R.drawable.splash_screen)
                    .into(binding.ivPoster);

            if (itemClickListener != null) {
                binding.getRoot().setOnClickListener(v -> itemClickListener.onItemClick(video));
            }

            if (itemFocusListener != null) {
                binding.getRoot().setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        itemFocusListener.onItemFocus(video, hasFocus);
                    }
                });
            }
        }
    }
}
