package com.royshow.rxjavatest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by royshow on 2016/11/19.
 */

public class ProjectResponse {
    @SerializedName("Data")
    private List<Project> projects;

    public List<Project> getProjects() {
        return projects;
    }
}
