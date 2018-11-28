package com.practice.virtusa.musicapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.practice.virtusa.musicapp.app_start.Injection;
import com.practice.virtusa.musicapp.repository.RepositoryInterface;

public class MusicAppViewModel extends AndroidViewModel {
    public final RepositoryInterface repository;

    public final RepositoryInterface mockTestRepository;

    public MusicAppViewModel(Application application) {
        super(application);

        this.repository = Injection.getInstance().provideRepository();
        this.mockTestRepository = Injection.getInstance().provideMockTestRepository();
    }
}
