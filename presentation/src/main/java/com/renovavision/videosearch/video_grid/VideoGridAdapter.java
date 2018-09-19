package com.renovavision.videosearch.video_grid;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.vyng.videosearch.R;
import com.renovavision.videosearch.domain.model.VideoItem;
import com.renovavision.videosearch.recycler.EndlessPagerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */
public class VideoGridAdapter extends EndlessPagerAdapter<VideoItem, VideoGridAdapter.ViewHolder> {

    private final int gridItemSize;

    private OnItemClickListener onItemClickListener;

    public VideoGridAdapter(int gridItemSize) {
        super();
        this.gridItemSize = gridItemSize;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_item_video_grid, parent, false);
        return new ViewHolder(view, gridItemSize, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        Picasso.get()
                .load(models.get(position).getPreviewUrl())
                .into(holder.imageView);
    }

    public void setInput(@NonNull List<VideoItem> models) {
        this.models = models;
        notifyDataSetChanged();
    }

    public void loadedMore(@NonNull List<VideoItem> newModels) {
        // need to remove progress item here
        this.models.addAll(newModels);
        notifyItemRangeInserted(models.size() - 1, newModels.size());
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClicked(int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_view)
        ImageView imageView;

        ViewHolder(View itemView, int gridItemSize, @Nullable OnItemClickListener onItemClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(gridItemSize, gridItemSize);
            imageView.setLayoutParams(params);

            imageView.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClicked(getAdapterPosition());
                }
            });
        }
    }
}
