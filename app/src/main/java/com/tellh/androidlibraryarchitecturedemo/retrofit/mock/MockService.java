package com.tellh.androidlibraryarchitecturedemo.retrofit.mock;

import com.google.gson.Gson;
import com.tellh.androidlibraryarchitecturedemo.retrofit.GithubApiClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Path;

final public class MockService extends BaseMockService<GithubApiClient.ReposService> implements GithubApiClient.ReposService {
    private final Map<String, Map<String, List<GithubApiClient.ReposService.Contributor>>> ownerRepoContributors;

    public MockService() {
        super(GithubApiClient.ReposService.class, GithubApiClient.GITHUB_URL);
        ownerRepoContributors = new LinkedHashMap<>();
        // Seed some mock data.
        addContributor("square", "retrofit", "John Doe", 12);
        addContributor("square", "retrofit", "Bob Smith", 2);
        addContributor("square", "retrofit", "Big Bird", 40);
        addContributor("square", "picasso", "Proposition Joe", 39);
        addContributor("square", "picasso", "Keiser Soze", 152);
    }

    public void addContributor(String owner, String repo, String name, int contributions) {
        Map<String, List<Contributor>> repoContributors = ownerRepoContributors.get(owner);
        if (repoContributors == null) {
            repoContributors = new LinkedHashMap<>();
            ownerRepoContributors.put(owner, repoContributors);
        }
        List<Contributor> contributors = repoContributors.get(repo);
        if (contributors == null) {
            contributors = new ArrayList<>();
            repoContributors.put(repo, contributors);
        }
        contributors.add(new Contributor(name, contributions));
    }

    @Override
    public Call<List<Contributor>> getContributors(@Path("owner") String owner, @Path("repo") String repo) {
        List<Contributor> response = Collections.emptyList();
        Map<String, List<Contributor>> repoContributors = ownerRepoContributors.get(owner);
        if (repoContributors != null) {
            List<Contributor> contributors = repoContributors.get(repo);
            if (contributors != null) {
                response = contributors;
            }
        }
        Gson gson = new Gson();
        return delegate.returningResponse(gson.toJson(response)).getContributors(owner, repo);
    }

}