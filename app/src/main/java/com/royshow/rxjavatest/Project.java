package com.royshow.rxjavatest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by royshow on 2016/11/19.
 */

public class Project {
    public Project() {

    }

    @SerializedName("Id")
    private String id;
    @SerializedName("Name")
    private String name;
    @SerializedName("Code")
    private String code;
    @SerializedName("Description")
    private String description;

    public String getId() {
        return id;
    }

    public Project setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Project setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Project setCode(String code) {
        this.code = code;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Project setDescription(String description) {
        this.description = description;
        return this;
    }
}
