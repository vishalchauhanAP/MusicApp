package com.practice.virtusa.musicapp.user_interface;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.practice.virtusa.musicapp.R;
import com.practice.virtusa.musicapp.app_start.MusicApp;
import com.practice.virtusa.musicapp.common.ApiError;
import com.practice.virtusa.musicapp.helpers.ApiResponse;
import com.practice.virtusa.musicapp.helpers.ApiStatus;
import com.practice.virtusa.musicapp.model.AlbumInfo;
import com.practice.virtusa.musicapp.model.AlbumsInfoResponse;
import com.practice.virtusa.musicapp.viewmodel.AlbumsInfoViewModel;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AlbumsInfoFragment extends Fragment {
    private AlbumsInfoViewModel albumsInfoViewModel;

    protected RecyclerView recyclerViewAlbumsInfo;
    private AlbumsInfoAdaptor adaptorAlbumsInfo;

    private View appTitleView;
    private TextView appTitleTextView;
    private View appProgressIndicator;
    private ProgressBar appProgressBar;
    private Toolbar albumInfoToolbar;

    public static AlbumsInfoFragment newInstance(String param1, String param2) {
        AlbumsInfoFragment fragment = new AlbumsInfoFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_album_info, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        appTitleView = view.findViewById(R.id.appTitleView);
        appTitleTextView = appTitleView.findViewById(R.id.titleHeader);
        appTitleTextView.setText(R.string.album_info_title);
        albumInfoToolbar = view.findViewById(R.id.albumsInfoToolBar);

        appProgressIndicator = view.findViewById(R.id.app_progress_indicator);
        appProgressBar = appProgressIndicator.findViewById(R.id.app_progress_bar);
        appProgressBar.getIndeterminateDrawable().setColorFilter(Color.GRAY, android.graphics.PorterDuff.Mode.MULTIPLY);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        albumsInfoViewModel = ViewModelProviders.of(this).get(AlbumsInfoViewModel.class);
        appProgressIndicator.setVisibility(View.VISIBLE);

        final LiveData<List<AlbumInfo>> albumsInfoObservable = albumsInfoViewModel.getLocalAlbumsInfo();
        albumsInfoObservable.observe
                (this, new Observer<List<AlbumInfo>>() {
                    @Override
                    public final void onChanged(List<AlbumInfo> response) {
                        if (response != null &&  response.size() > 0) {
                            albumsInfoObservable.removeObserver(this);

                            AlbumsInfoResponse albumsInfo = new AlbumsInfoResponse();
                            albumsInfo.addAll(response);
                            bind(albumsInfo);

                            appProgressIndicator.setVisibility(View.GONE);
                            albumInfoToolbar.setTitle(R.string.remote_toolbar_title);

                        } else {
                            albumsInfoObservable.removeObserver(this);

                            albumsInfoViewModel.getAlbumsInfo().observe
                                    (getActivity(), new Observer<ApiResponse<AlbumsInfoResponse>>() {
                                        @Override
                                        public final void onChanged(ApiResponse response) {
                                            albumsInfoObservable.removeObservers(getActivity());

                                            appProgressIndicator.setVisibility(View.GONE);
                                            albumInfoToolbar.setTitle(R.string.local_toolbar_title);

                                            if ((response != null ? response.getStatus() : null) == ApiStatus.SUCCESS && response.getData() != null) {
                                                bind((AlbumsInfoResponse)response.getData());
                                            } else if ((response != null ? response.getError() : null) == ApiError.GET_USER_ALBUMS_ERROR) {
                                                Toast.makeText(getContext(), getString(R.string.error_albums_info), Toast.LENGTH_SHORT).show();
                                            }
                                            albumsInfoViewModel.setLocalAlbumsInfo((AlbumsInfoResponse)response.getData());
                                        }
                                    });
                        }
                    }
                });
    }

    private void bind(AlbumsInfoResponse dataAlbums) {
        Context context = MusicApp.getInstance().getBaseContext();
        Collections.sort(dataAlbums, new Comparator<AlbumInfo>() {
            public int compare(AlbumInfo o1, AlbumInfo o2) {
                return o1.albumTitle.compareTo(o2.albumTitle);
            }
        });
        albumsInfoViewModel.setDataAlbums(dataAlbums);

        if(albumsInfoViewModel.isDataAvailable()) {
            adaptorAlbumsInfo = new AlbumsInfoAdaptor(albumsInfoViewModel);

            recyclerViewAlbumsInfo = getView().findViewById(R.id.albumsInfoRecyclerView);
            recyclerViewAlbumsInfo.setAdapter(adaptorAlbumsInfo);
            recyclerViewAlbumsInfo.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        else {
            Toast.makeText(getContext(), getString(R.string.error_albums_info_nodata), Toast.LENGTH_SHORT).show();
        }
    }
}