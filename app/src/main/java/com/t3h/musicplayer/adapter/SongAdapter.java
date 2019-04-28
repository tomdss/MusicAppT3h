package com.t3h.musicplayer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.t3h.musicplayer.databinding.ItemSongBinding;
import com.t3h.musicplayer.model.Song;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    private ArrayList<Song> data;
    private LayoutInflater inflater;
    private ItemClickListerner listerner;

    public SongAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemSongBinding binding = ItemSongBinding.inflate(inflater);
        return new ViewHolder(binding);
    }

    public void setListerner(ItemClickListerner listerner) {
        this.listerner = listerner;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.bindData(data.get(i));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listerner!=null){
                    listerner.onItemClicked(i);
                }
            }
        });
    }

    public ArrayList<Song> getData() {
        return data;
    }

    public void setData(ArrayList<Song> data) {
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ItemSongBinding binding;


        public ViewHolder(@NonNull ItemSongBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

        public void bindData(Song item){
            binding.tvTitle.setText(item.getTitle());
            binding.tvArtist.setText(item.getArtist());
            SimpleDateFormat format = new SimpleDateFormat("mm:ss");
            String duration = format.format(new Date(item.getDuration()));
        }

        public String formatSize(long bytes) {
            if (bytes < 1024) return bytes + " B";
            int exp = (int) (Math.log(bytes) / Math.log(1024));
            String pre = "KMGTPE".charAt(exp - 1) + "";
            return String.format("%.1f %sB", bytes / Math.pow(1024, exp), pre);
        }

    }

    public interface ItemClickListerner{
        void onItemClicked(int position);
    }

}

