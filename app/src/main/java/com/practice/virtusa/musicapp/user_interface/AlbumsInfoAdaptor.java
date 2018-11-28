package com.practice.virtusa.musicapp.user_interface;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.practice.virtusa.musicapp.R;
import com.practice.virtusa.musicapp.model.AlbumInfo;
import com.practice.virtusa.musicapp.viewmodel.AlbumsInfoViewModel;

public class AlbumsInfoAdaptor extends RecyclerView.Adapter<AlbumsInfoAdaptor.ViewHolder> {
    private AlbumsInfoViewModel albumsInfoViewModel;

    public AlbumsInfoAdaptor(AlbumsInfoViewModel albumsInfoViewModel) {
        this.albumsInfoViewModel = albumsInfoViewModel;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = (View) inflater.inflate(R.layout.item_album_info, parent, false);
        return new ViewHolder(view, parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setViewModel(albumsInfoViewModel);
        holder.bind(albumsInfoViewModel.getAlbumInfo(position));
    }

    @Override
    public int getItemCount() {
        return albumsInfoViewModel.getAlbumInfoCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View parent;
        private View albumItemView;
        private AlbumsInfoViewModel albumsInfoViewModel;

        private AlbumInfo album;
        private TextView albumDesc;

        public ViewHolder(View albumItemView, View parent) {
            super(albumItemView);

            this.parent = parent;
            this.albumItemView = albumItemView;
            albumDesc = albumItemView.findViewById(R.id.albumDesc);
        }

        public void setViewModel(AlbumsInfoViewModel albumsInfoViewModel) {
            this.albumsInfoViewModel = albumsInfoViewModel;
        }

        private void bind(AlbumInfo album) {
            this.album = album;
            albumDesc.setText(albumsInfoViewModel.getAlbumDesc(album));
        }
    }
}