package com.royshow.rxjavatest;

import java.util.List;

/**
 * Created by royshow on 2016/11/19.
 */

public class ProjectViewModel {
    private List<Project> projects;

    public ProjectViewModel(List<Project> projects) {
        this.projects = projects;
    }

    public List<Project> getProjects() {
        return projects;
    }
}
