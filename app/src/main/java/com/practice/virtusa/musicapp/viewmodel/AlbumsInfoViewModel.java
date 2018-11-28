package com.practice.virtusa.musicapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import com.practice.virtusa.musicapp.helpers.Utils;
import com.practice.virtusa.musicapp.model.AlbumInfo;
import com.practice.virtusa.musicapp.model.AlbumsInfoResponse;
import com.practice.virtusa.musicapp.repository.LocalAlbumsRepository;

import java.util.ArrayList;


public final class AlbumsInfoViewModel extends MusicAppViewModel {
    private final String TAG = "AlbumInfoViewModel";

    private LocalAlbumsRepository localAlbumsRepository;

    private Boolean isDataAvailable = true;

    private Boolean isLocalDataAvailable = false;

    private AlbumsInfoResponse dataAlbums;

    public AlbumsInfoViewModel(Application application) {
        super(application);
        localAlbumsRepository = new LocalAlbumsRepository(application);
    }

    public AlbumsInfoResponse getDataAlbums() {
        return dataAlbums;
    }

    public void setDataAlbums(AlbumsInfoResponse dataAlbums) {
        this.dataAlbums = dataAlbums;
    }

    public final LiveData getLocalAlbumsInfo() {
            return localAlbumsRepository.getAlbumsInfo();
    }

    public void setLocalAlbumsInfo(AlbumsInfoResponse dataAlbums) {
        for (AlbumInfo album : dataAlbums) {
            localAlbumsRepository.insert(album);
        }
    }

    public final LiveData getAlbumsInfo() {
            return repository.getAlbumsInfo();
    }

    public final LiveData checkForLocalAlbumsInfo() {
            return localAlbumsRepository.getAlbumsInfo();
        }

    public Boolean getLocalDataAvailable() {
        return isLocalDataAvailable;
    }

    public void setLocalDataAvailable(Boolean localDataAvailable) {
        isLocalDataAvailable = localDataAvailable;
    }

    public Boolean isDataAvailable() {
        return dataAlbums != null ? dataAlbums.size() > 0 : false;
    }

    public AlbumInfo getAlbumInfo(int position) {
        return dataAlbums.get(position);
    }

    public ArrayList<AlbumInfo> getAlbumsDetails() {
        return dataAlbums;
    }

    public int getAlbumInfoCount() {
        return dataAlbums != null ? dataAlbums.size() : 0;
    }

    public String getAlbumDesc(AlbumInfo album) {
        return Utils.StringUtil.toTitleCase(album.getAlbumTitle());
    }
}

