package com.tabi.dmv_tv.ui.home;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.tabi.dmv_tv.databinding.ActivityHomeBinding;
import com.tabi.dmv_tv.models.ApiResponse;
import com.tabi.dmv_tv.models.Category;
import com.tabi.dmv_tv.models.Movie;
import com.tabi.dmv_tv.models.Playlist;
import com.tabi.dmv_tv.models.TvSpecial;
import com.tabi.dmv_tv.network.NetworkApiClient;
import com.tabi.dmv_tv.ui.home.adapter.CategoryAdapter;
import com.tabi.dmv_tv.ui.player.PlaybackActivity;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends FragmentActivity {
    private ActivityHomeBinding binding;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addCategoryAdapter();
        networkRequest();
        addClickListener();
        addFocusListener();
    }

    private void addFocusListener() {
        if (categoryAdapter != null) {
            categoryAdapter.addItemFocusListener((video, hasFocus) -> {
                if (hasFocus) {
                    setBackgroundData(video);
                }
            });
        }
    }

    private void addClickListener() {
        if (categoryAdapter != null) {
            categoryAdapter.addItemClickListener(video -> {
                Intent intent = new Intent(this, PlaybackActivity.class);
                Movie movie = new Movie();
                movie.setId(-1);
                movie.setBackgroundImageUrl(video.getThumbnail());
                movie.setCardImageUrl(video.getThumbnail());
                movie.setTitle(video.getTitle());
                movie.setVideoUrl(video.getContent().getVideos().get(0).getUrl());
                movie.setDescription(video.getShortDescription());
                intent.putExtra(PlaybackActivity.MOVIE, movie);
                startActivity(intent);
            });
        }

    }

    private void addCategoryAdapter() {
        categoryAdapter = new CategoryAdapter();
        binding.rvVideosByCategory.setAdapter(categoryAdapter);
    }

    private void setBackgroundData(TvSpecial tvSpecial) {
        try {

            Glide.with(this)
                    .load(tvSpecial.getThumbnail())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.ivPoster);

            if (tvSpecial.getContent().getVideos().size() > 0) {
                String quality = tvSpecial.getContent().getVideos().get(0).getQuality();
                binding.tvQuality.setText(quality);
            }

            if (tvSpecial.getReleaseDate().isEmpty()) {
                binding.tvReleaseDate.setVisibility(View.GONE);
            } else {
                binding.tvReleaseDate.setVisibility(View.VISIBLE);
                binding.tvReleaseDate.setText(tvSpecial.getReleaseDate());
            }
            binding.tvDescription.setText(tvSpecial.getShortDescription());
            binding.tvVideoName.setText(tvSpecial.getTitle());

            StringBuilder genres = new StringBuilder();
            for (String genre : tvSpecial.getGenres()) {
                genres.append(genre).append(", ");
            }
            genres = new StringBuilder(genres.substring(0, genres.length() - 2));
            binding.tvGenres.setText(genres);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void networkRequest() {
        NetworkApiClient.getInstance().getVideos().enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                try {
                    Log.w("network api", "onResponse: " + response.body());
                    assert response.body() != null;
                    HashMap<Category, ArrayList<TvSpecial>> videosByCategory = filterData(response.body());
                    categoryAdapter.addVideosByCategories(videosByCategory);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                Log.w("network api", "onError: " + t.getMessage());
            }
        });
    }

    private HashMap<Category, ArrayList<TvSpecial>> filterData(ApiResponse apiResponse) {
        ArrayList<Category> categories = apiResponse.getCategories();
        ArrayList<TvSpecial> tvSpecials = apiResponse.getTvSpecials();
        ArrayList<Playlist> playlists = apiResponse.getPlaylists();
        HashMap<Category, ArrayList<TvSpecial>> videosByCategories = new HashMap<>();
        for (Playlist playlist : playlists) {
            for (Category category : categories) {

                if (playlist.getName().contains(category.getPlaylistName())) {
                    ArrayList<TvSpecial> filterTVSpecials = new ArrayList<>();
                    for (String playListItemId : playlist.getItemIds()) {
                        for (TvSpecial tvSpecial : tvSpecials) {
                            if (playListItemId.contains(tvSpecial.getId())) {
                                filterTVSpecials.add(tvSpecial);
                            }
                        }
                    }
                    videosByCategories.put(category, filterTVSpecials);
                }

            }
        }

        return videosByCategories;
    }

}