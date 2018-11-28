package com.practice.virtusa.musicapp;

import com.practice.virtusa.musicapp.model.AlbumInfo;
import com.practice.virtusa.musicapp.viewmodel.AlbumsInfoViewModel;

import org.junit.Test;

import android.app.Application;
import android.content.Context;
import androidx.test.core.app.ApplicationProvider;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

class AlbumsInfoUnitTest {
    private Context context = ApplicationProvider.getApplicationContext();
    AlbumsInfoViewModel viewModel;
    @Test
    public void isSizeEquals(){
        viewModel = new AlbumsInfoViewModel((Application)context);

        ArrayList<AlbumInfo> actual =  viewModel.repository.getAlbumsInfo().getValue().getData();

        ArrayList<AlbumInfo> expected = viewModel.mockTestRepository.getAlbumsInfo().getValue().getData();

        assertThat(actual, is(expected));

        assertThat(expected.size(), is(100));

        assertThat(actual.size(), is(100));

    }
}