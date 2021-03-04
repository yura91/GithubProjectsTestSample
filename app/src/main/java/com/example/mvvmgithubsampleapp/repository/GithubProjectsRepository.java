package com.example.mvvmgithubsampleapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmgithubsampleapp.model.ProjectsResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class GithubProjectsRepository {
    private GitHubService gitHubService;

    @Inject
    public GithubProjectsRepository(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    public LiveData<ProjectsResponse> getProjectsList(String query) {
        final MutableLiveData<ProjectsResponse> data = new MutableLiveData<>();

        gitHubService.getRepositoriesList(query).enqueue(new Callback<ProjectsResponse>() {
            @Override
            public void onResponse(Call<ProjectsResponse> call, Response<ProjectsResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ProjectsResponse> call, Throwable t) {
                // TODO better error handling in part #2 ...
                data.setValue(null);
            }
        });

        return data;
    }
}
